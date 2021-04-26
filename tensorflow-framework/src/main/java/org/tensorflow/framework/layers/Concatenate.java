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
package org.tensorflow.framework.layers;

import org.tensorflow.Operand;
import org.tensorflow.framework.layers.impl.Merge;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Layer that concatenates a list of inputs element-wise.
 *
 * <p>It takes as input a list of tensors, all of the same shape except for the concatenation axis,
 * and returns a single tensor that is the concatenation of all inputs.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Concatenate<T extends TFloating> extends Merge<T> {
  public static final int DEFAULT_AXIS = -1;
  private int axis;

  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name , and using
   * {@link #DEFAULT_AXIS} for the axis along which to concatenate.
   *
   * @param type the data type for the weights and computation
   */
  public Concatenate(Class<T> type) {
    this(null, null, DEFAULT_AXIS, type, null);
  }

  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name , and using
   * {@link #DEFAULT_AXIS} for the axis along which to concatenate.
   *
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(Class<T> type, Options options) {
    this(null, null, DEFAULT_AXIS, type, options);
  }

  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name.
   *
   * @param axis Axis along which to concatenate.
   * @param type the data type for the weights and computation
   */
  public Concatenate(int axis, Class<T> type) {
    this(null, null, axis, type, null);
  }

  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name.
   *
   * @param axis Axis along which to concatenate.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(int axis, Class<T> type, Options options) {
    this(null, null, axis, type, options);
  }

  /**
   * Creates a Concatenate Layer using {@link #DEFAULT_AXIS} for the axis along which to
   * concatenate.
   *
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   */
  public Concatenate(String name, Class<T> type) {
    this(null, name, DEFAULT_AXIS, type, null);
  }

  /**
   * Creates a Concatenate Layer using {@link #DEFAULT_AXIS} for the axis along which to
   * concatenate.
   *
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(String name, Class<T> type, Options options) {
    this(null, name, DEFAULT_AXIS, type, options);
  }

  /**
   * Creates a Concatenate Layer
   *
   * @param axis Axis along which to concatenate.
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   */
  public Concatenate(String name, int axis, Class<T> type) {
    this(null, name, axis, type, null);
  }

  /**
   * Creates a Concatenate Layer
   *
   * @param axis Axis along which to concatenate.
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(String name, int axis, Class<T> type, Options options) {
    this(null, name, axis, type, options);
  }

  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name, and using
   * {@link #DEFAULT_AXIS} for the axis along which to concatenate.
   *
   * @param tf the TensorFlow Ops
   * @param type the data type for the weights and computation
   */
  public Concatenate(Ops tf, Class<T> type) {
    this(tf, null, DEFAULT_AXIS, type, null);
  }

  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name, and using
   * {@link #DEFAULT_AXIS} for the axis along which to concatenate.
   *
   * @param tf the TensorFlow Ops
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(Ops tf, Class<T> type, Options options) {
    this(tf, null, DEFAULT_AXIS, type, options);
  }


  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name.
   *
   * @param tf the TensorFlow Ops
   * @param axis Axis along which to concatenate.
   * @param type the data type for the weights and computation
   */
  public Concatenate(Ops tf, int axis, Class<T> type) {
    this(tf, null, axis, type, null);
  }

  /**
   * Creates a Concatenate Layer using {@link Class#getSimpleName()} as the layer name.
   *
   * @param tf the TensorFlow Ops
   * @param axis Axis along which to concatenate.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(Ops tf, int axis, Class<T> type, Options options) {
    this(tf, null, axis, type, options);
  }

  /**
   * Creates a Concatenate Layer using {@link #DEFAULT_AXIS} for the axis along which to
   * concatenate.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   */
  public Concatenate(Ops tf, String name, Class<T> type) {
    this(tf, name, DEFAULT_AXIS, type, null);
  }

  /**
   * Creates a Concatenate Layer using {@link #DEFAULT_AXIS} for the axis along which to
   * concatenate.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(Ops tf, String name, Class<T> type, Options options) {
    this(tf, name, DEFAULT_AXIS, type, options);
  }

  /**
   * Creates a Concatenate Layer
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axis Axis along which to concatenate.
   * @param type the data type for the weights and computation
   */
  public Concatenate(Ops tf, String name, int axis, Class<T> type) {
    this(tf, name, axis, type, null);
  }
  /**
   * Creates a Concatenate Layer
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axis Axis along which to concatenate.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Concatenate(Ops tf, String name, int axis, Class<T> type, Options options) {
    super(tf, name, type, options);
    this.axis = axis;
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

    final Ops tf = getTF();

    List<Operand<TBool>> rMasks =
        masks.stream().map(m -> cast(getTF(), m, TBool.class)).collect(Collectors.toList());

    List<Operand<TBool>> newMasks = new ArrayList<>();
    for (int i = 0; i < inputs.size(); i++) {
      Operand<? extends TType> input = inputs.get(i);
      Operand<TBool> mask = rMasks.get(i);
      if (mask == null) {
        newMasks.add(cast(tf, tf.onesLike(input), TBool.class));
      } else if (mask.rank() < input.rank()) {
        newMasks.add(tf.expandDims(mask, tf.constant(-1)));
      } else {
        newMasks.add(mask);
      }
    }
    Operand<TBool> concat = tf.concat(newMasks, tf.constant(axis));
    return Collections.singletonList(tf.reduceAll(concat, tf.constant(-1)));
  }

  /** {@inheritDoc} */
  @Override
  public void build(List<Shape> inputShapes) {

    // Used purely for shape validation.
    if (inputShapes.size() < 2) {
      throw new IllegalArgumentException("A Concatenate layer must have at least 2 inputs.");
    }
    boolean allShapesUnknown = true;
    for (Shape shape : inputShapes) {
      if (!shape.isUnknown()) {
        allShapesUnknown = false;
        break;
      }
    }
    if (allShapesUnknown) {
      this.setBuilt(true);
      return;
    }
    Integer rank = null;
    long[][] shapesArray = new long[inputShapes.size()][];
    for (int i = 0; i < inputShapes.size(); i++) {

      Shape shape = inputShapes.get(i);
      long[] dims = new long[shape.numDimensions() - 1];
      for (int j = 0, k = 0; j < dims.length; k++) {
        if (k == axis) continue;
        dims[j++] = shape.size(i);
      }

      if (rank == null || rank == Shape.UNKNOWN_SIZE) {
        rank = shape.numDimensions();
      } else if (rank != shape.numDimensions()) {
        throw new IllegalArgumentException(
            String.format(
                "A Concatenate layer requires inputs with matching shapes %s",
                shapesToString(inputShapes)));
      }
      shapesArray[i] = dims;
    }

    if (axis < 0) {
      axis = Math.floorMod(axis, rank);
    }
    long[] firstShape = shapesArray[0];
    for (int i = 1; i < shapesArray.length; i++) {
      for (int j = 0; j < shapesArray[i].length; j++) {
        if (shapesArray[i][j] != firstShape[j]
            && shapesArray[i][j] != Shape.UNKNOWN_SIZE
            && firstShape[j] != Shape.UNKNOWN_SIZE) {
          throw new IllegalArgumentException(
              String.format(
                  "A Concatenate layer requires inputs with matching shapes %s",
                  shapesToString(inputShapes)));
        }
      }
    }

    this.setBuilt(true);
  }

  /**
   * Coverts a list of shapes to a String
   *
   * @param shapes the list of shapes.
   * @return list of shapes as a String
   */
  private String shapesToString(List<Shape> shapes) {
    StringBuilder sb = new StringBuilder("[ ");
    boolean first = true;
    for (Shape shape : shapes) {
      if (!first) {
        sb.append(", ");
      } else {
        first = false;
      }
      sb.append(shape);
    }
    sb.append(" ]");
    return sb.toString();
  }

  /** {@inheritDoc} */
  @Override
  protected Operand<? extends TType> mergeFunction(List<Operand<? extends TNumber>> inputs) {
    Ops tf = getTF();
    if (inputs.size() < 2) {
      throw new IllegalArgumentException("A Concatenate layer must have at least 2 inputs.");
    }
    Class<? extends TType> inputType = inputs.get(0).type();
    List<Operand<T>> tList =
        inputs.stream().map(item -> cast(tf, item, getType())).collect(Collectors.toList());
    return cast(tf, tf.concat(tList, tf.constant(axis)), inputType);
  }

  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    build(inputShapes);
    Shape outputShape = inputShapes.get(0);
    long[] dims = outputShape.asArray();
    if (dims == null) {
      dims = new long[] {Shape.UNKNOWN_SIZE};
    }

    for (int i = 1; i < inputShapes.size(); i++) {
      Shape shape = inputShapes.get(0);
      if (outputShape.size(axis) == Shape.UNKNOWN_SIZE || shape.size(axis) == Shape.UNKNOWN_SIZE) {
        dims[axis] = Shape.UNKNOWN_SIZE;
        break;
      }
      dims[axis] += shape.size(axis);
    }

    Shape result = Shape.of(dims);
    return Collections.singletonList(result);
  }
}
