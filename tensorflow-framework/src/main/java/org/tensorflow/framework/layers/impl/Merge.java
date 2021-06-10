/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.layers.impl;

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.tensorflow.Operand;
import org.tensorflow.framework.layers.Layer;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Generic abstract merge layer for element-wise merge functions.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public abstract class Merge<T extends TFloating> extends Layer<T> {

  private boolean reshapeRequired;

  /**
   * Creates a Merge base class using {@link Class#getSimpleName()} for the name.
   *
   * @param type the data type for the weights and computation
   */
  protected Merge(Class<T> type) {

    this(null, true, type, null);
  }

  /**
   * Creates a Merge base class using {@link Class#getSimpleName()} for the name.
   *
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  protected Merge(Class<T> type, Options options) {

    this(null, true, type, options);
  }

  /**
   * Creates a Merge base class.
   *
   * @param name the unique name for this layer, if null will use {@link Class#getSimpleName()} for
   *     the name.
   * @param type the data type for the weights and computation
   */
  protected Merge(String name, Class<T> type) {

    this(name, true, type, null);
  }

  /**
   * Creates a Merge base class.
   *
   * @param name the unique name for this layer, if null will use {@link Class#getSimpleName()} for
   *     the name.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  protected Merge(String name, Class<T> type, Options options) {

    this(name, true, type, options);
  }

  /**
   * Creates the base Layer class
   *
   * @param name the unique name for this layer, if null will use {@link Class#getSimpleName()} for
   *     the name.
   * @param trainable whether the layer's variables should be trainable or not.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options
   */
  protected Merge(String name, boolean trainable, Class<T> type, Options options) {
    super(name, trainable, type, options);
    this.setSupportsMasking(true);
  }

  /** {@inheritDoc} */
  @Override
  public List<Operand<TBool>> computeMask(
      List<Operand<? extends TType>> inputs, List<Operand<? extends TType>> masks) {
    if (masks == null || masks.isEmpty()) {
      return null;
    }
    if (inputs.size() != masks.size()) {
      throw new IllegalArgumentException("The lists inputs and masks should have the same length.");
    }

    boolean allNull = true;
    for (Operand<? extends TType> m : masks) {
      if (m != null) {
        allNull = false;
        break;
      }
    }
    if (allNull) {
      return null;
    }
    checkTFIsSet();
    final Ops tf = getTF();
    List<Operand<TBool>> rMasks =
        masks.stream()
            .map(m -> cast(getTF(), m, TBool.class))
            .map(m -> tf.expandDims(m, tf.constant(0)))
            .collect(Collectors.toList());

    Operand<TBool> concat = tf.concat(rMasks, tf.constant(0));
    Operand<TBool> bool = cast(tf, concat, TBool.class);
    return Collections.singletonList(tf.reduceAll(bool, tf.constant(0)));
  }

  /**
   * Computes the merged result
   *
   * @param inputs the inputs
   * @return the merged result
   */
  protected abstract Operand<? extends TType> mergeFunction(
      List<Operand<? extends TNumber>> inputs);

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      Ops tf,
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {

    Ops ltf = init(tf);

    if (reshapeRequired) {
      List<Operand<? extends TNumber>> reshapedInputs = new ArrayList<>();
      List<Integer> inputDimensions = new ArrayList<>();
      inputs.forEach(s -> inputDimensions.add(s.shape().numDimensions()));
      if (!inputDimensions.contains((int) Shape.UNKNOWN_SIZE)) {
        // If ranks of all inputs are available,
        // we simply expand each of them at axis=1
        // until all of them have the same rank.
        int maxDimension = Collections.max(inputDimensions);
        for (Operand<? extends TType> input : inputs) {
          int numDims = input.shape().numDimensions();
          for (int i = numDims; i < maxDimension; i++) {
            input = ltf.expandDims(input, ltf.constant(1));
          }
          Operand<T> tInput = cast(ltf, input, getType());
          reshapedInputs.add(tInput);
        }
        Operand<U> result = cast(ltf, mergeFunction(reshapedInputs), resultType);
        return Collections.singletonList(result);

      } else {
        // Transpose all inputs so that batch size is the last dimension.
        // (batch_size, dim1, dim2, ... ) -> (dim1, dim2, ... , batch_size)
        boolean transposed = false;
        for (Operand<? extends TType> input : inputs) {
          Operand<T> tInput = cast(ltf, input, getType());
          int nDims = tInput.shape().numDimensions();
          if (nDims == Shape.UNKNOWN_SIZE) {
            org.tensorflow.op.core.Shape<TInt32> tShape = getTF().shape(tInput);
            Operand<TInt32> batchSize = ltf.shape.size(tShape, getTF().constant(0));
            Operand<TInt32> remainderShape =
                ltf.shape.takeLast(tShape, ltf.math.sub(ltf.rank(tInput), ltf.constant(1)));
            Operand<TInt32> newShape =
                ltf.shape.append(remainderShape, ltf.expandDims(batchSize, ltf.constant(-1)));

            Operand<T> transposedInput =
                ltf.reshape(
                    tInput,
                    ltf.shape.append(batchSize, ltf.reduceProd(remainderShape, ltf.constant(0))));

            transposedInput = ltf.linalg.transpose(transposedInput, ltf.constant(new int[] {1, 0}));
            transposedInput = ltf.reshape(transposedInput, newShape);
            reshapedInputs.add(transposedInput);
            transposed = true;

          } else if (nDims > 1) {
            int[] perms = new int[nDims];
            for (int i = 1; i < nDims - 1; i++) {
              perms[i - 1] = i;
            }
            perms[nDims - 1] = 0;
            reshapedInputs.add(ltf.linalg.transpose(tInput, ltf.constant(perms)));
          } else {
            reshapedInputs.add(tInput);
          }
        }
        Operand<U> result = cast(ltf, mergeFunction(reshapedInputs), resultType);

        if (transposed) {
          int nDim = result.shape().numDimensions();
          if (nDim == Shape.UNKNOWN_SIZE) {
            org.tensorflow.op.core.Shape<TInt32> rShape = ltf.shape(result);
            Operand<TInt32> batchSize = ltf.shape.takeLast(rShape, ltf.constant(1));
            Operand<TInt32> baseShape =
                ltf.shape.take(rShape, ltf.math.sub(ltf.rank(result), ltf.constant(1)));
            Operand<TInt32> newShape = ltf.shape.append(batchSize, baseShape);
            result =
                ltf.reshape(
                    result,
                    ltf.concat(
                        Arrays.asList(ltf.constant(new int[] {-1}), batchSize), ltf.constant(0)));
            result = ltf.linalg.transpose(result, ltf.constant(new int[] {1, 0}));
            result = ltf.reshape(result, newShape);
          } else if (nDim > 1) {
            int[] perms = new int[nDim];
            perms[0] = nDim - 1;
            for (int i = 0; i < nDim - 1; i++) {
              perms[i + 1] = i;
            }
            result = ltf.linalg.transpose(result, ltf.constant(perms));
          }
        }
        return callPostProcess(Collections.singletonList(result), training);
      }
    } else {
      List<Operand<? extends TNumber>> tInputs = new ArrayList<>();
      inputs.forEach(i -> tInputs.add(cast(getTF(), i, getType())));
      Operand<U> merged = cast(ltf, mergeFunction(tInputs), resultType);

      return callPostProcess(Collections.singletonList(merged), training);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void build(List<Shape> inputShapes) {
    if (inputShapes == null || inputShapes.size() <= 1) {
      throw new IllegalArgumentException(
          String.format(
              "A merge layer should be called on a list of at least 2 inputs. Got %d inputs",
              inputShapes == null ? 0 : inputShapes.size()));
    }
    Set<Long> batchSizes = new HashSet<>();
    inputShapes.forEach(s -> batchSizes.add(s.size(0)));
    if (batchSizes.size() > 1) {
      throw new IllegalArgumentException(
          String.format(
              "Can not merge tensors with different batch sizes. Got tensors with shapes %s: ",
              Arrays.toString(inputShapes.toArray())));
    }

    Shape inputShape = inputShapes.get(0);
    Shape outputShape = inputShape.takeLast(inputShape.numDimensions() - 1);
    Shape shape;
    for (int i = 1; i < inputShape.size(); i++) {
      shape = inputShapes.get(i);
      outputShape = computeElementWiseOpOutputShape(outputShape, shape);
    }

    Set<Integer> ranks = new HashSet<>();
    inputShapes.forEach(s -> ranks.add(s.numDimensions()));
    boolean hasUnknown = false;
    for (Shape s : inputShapes) {
      if (s.isUnknown()) {
        hasUnknown = true;
        break;
      }
    }
    reshapeRequired = hasUnknown || ranks.size() > 1;
    super.build(inputShapes);
  }

  /** {@inheritDoc} */
  @Override
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    Shape outputShape;
    if (inputShapes.isEmpty() || inputShapes.get(0) == null) {
      outputShape = Shape.of();
    } else {
      Shape shape1 = inputShapes.get(0);
      if (shape1.numDimensions() > 0) {
        outputShape = shape1.takeLast(shape1.numDimensions() - 1);
      } else {
        outputShape = Shape.of();
      }
    }
    Shape shape;
    for (int i = 1; i < inputShapes.size(); i++) {
      Shape shapei = inputShapes.get(i);
      if (shapei == null) {
        shape = Shape.of();
      } else {
        if (shapei.numDimensions() > 0) {
          shape = shapei.takeLast(shapei.numDimensions() - 1);
        } else {
          shape = Shape.of();
        }
      }
      outputShape = computeElementWiseOpOutputShape(outputShape, shape);
    }

    Set<Long> batchSizes = new HashSet<>();
    for (Shape s : inputShapes) {
      if (s != null) {
        batchSizes.add(s.size(0));
      }
    }
    if (batchSizes.size() == 1) {
      outputShape = outputShape.prepend(batchSizes.toArray(new Long[1])[0]);
    } else {
      outputShape = outputShape.prepend(Shape.UNKNOWN_SIZE);
    }

    return Collections.singletonList(outputShape);
  }

  /**
   * Computes the shape of the resultant of an element-wise operation.
   *
   * @param shape1 Shape of the first tensor
   * @param shape2 Shape of the second tensor
   * @return expected output shape when an element-wise operation is carried out on 2 tensors with
   *     shapes shape1 and shape2
   */
  protected Shape computeElementWiseOpOutputShape(Shape shape1, Shape shape2) {
    if (shape2 == null) {
      return shape1;
    }
    if (shape1.isUnknown() || shape2.isUnknown()) {
      return Shape.unknown();
    }
    if (shape1.numDimensions() < shape2.numDimensions()) {
      return computeElementWiseOpOutputShape(shape2, shape1);
    }
    Shape outputShape = shape1.take(shape1.numDimensions() - shape2.numDimensions());

    for (int i = shape1.numDimensions() - shape2.numDimensions(), j = 0;
        j < shape2.numDimensions();
        j++, i++) {
      if (shape1.size(i) == Shape.UNKNOWN_SIZE || shape2.size(i) == Shape.UNKNOWN_SIZE) {
        outputShape = outputShape.append(Shape.UNKNOWN_SIZE);
      } else if (shape1.size(i) == 1) {
        outputShape = outputShape.append(shape2.size(j));
      } else if (shape2.size(j) == 1) {
        outputShape = outputShape.append(shape1.size(i));
      } else if (shape1.size(i) != shape2.size(j)) {
        throw new IllegalArgumentException(
            String.format(
                "Operands could not be broadcast together with shapes %s %s", shape1, shape2));
      } else {
        outputShape = outputShape.append(shape1.size(i));
      }
    }
    return outputShape;
  }
}
