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
import org.tensorflow.framework.layers.impl.TensorFormat;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

import java.util.Collections;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Flattens the input. Does not affect the batch size.
 *
 * <p><em>Note:</em> If inputs are shaped {@code (batch,)} without a feature axis, then flattening
 * adds an extra channel dimension and output shape is {@code (batch, 1)}.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Flatten<T extends TFloating> extends Layer<T> {
  private final TensorFormat dataFormat;

  /**
   * Creates a Flatten Layer with a unique name generated based on * {@link Class#getSimpleName()}
   * and {@link TensorFormat#NHWC} for the data format
   *
   * @param tf the TensorFlow Ops.
   * @param type the data type for the layer's weights and computation.
   */
  public Flatten(Ops tf, Class<T> type) {
    this(tf, null, TensorFormat.NHWC, type, null);
  }

  /**
   * Creates a Flatten Layer with a unique name generated based on {@link Class#getSimpleName()} and
   * {@link TensorFormat#NHWC} for the data format
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   */
  public Flatten(Ops tf, String name, Class<T> type) {
    this(tf, name, TensorFormat.NHWC, type, null);
  }

  /**
   * Creates a Flatten Layer with a unique name generated based on * {@link Class#getSimpleName()}.
   *
   * @param tf the TensorFlow Ops.
   * @param dataFormat The ordering of the dimensions in the inputs. {@link TensorFormat#NHWC}
   *     corresponds to inputs with shape {@code (batch, ..., channels) } while {@link
   *     TensorFormat#NCHW} corresponds to inputs with shape {@code (batch, channels, ...)}.
   * @param type the data type for the layer's weights and computation.
   */
  public Flatten(Ops tf, TensorFormat dataFormat, Class<T> type) {
    this(tf, null, dataFormat, type, null);
  }

  /**
   * Creates a Flatten Layer with a unique name generated based on * {@link Class#getSimpleName()}.
   *
   * @param tf the TensorFlow Ops.
   * @param dataFormat The ordering of the dimensions in the inputs. {@link TensorFormat#NHWC}
   *     corresponds to inputs with shape {@code (batch, ..., channels) } while {@link
   *     TensorFormat#NCHW} corresponds to inputs with shape {@code (batch, channels, ...)}.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options
   */
  public Flatten(Ops tf, TensorFormat dataFormat, Class<T> type, Options options) {
    this(tf, null, dataFormat, type, options);
  }

  /**
   * Creates a Flatten Layer
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param dataFormat The ordering of the dimensions in the inputs. {@link TensorFormat#NHWC}
   *     corresponds to inputs with shape {@code (batch, ..., channels) } while {@link
   *     TensorFormat#NCHW} corresponds to inputs with shape {@code (batch, channels, ...)}.
   * @param type the data type for the layer's weights and computation.
   */
  public Flatten(Ops tf, String name, TensorFormat dataFormat, Class<T> type) {
    this(tf, name, dataFormat, type, null);
  }
  /**
   * Creates a Flatten Layer
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param dataFormat The ordering of the dimensions in the inputs. {@link TensorFormat#NHWC}
   *     corresponds to inputs with shape {@code (batch, ..., channels) } while {@link
   *     TensorFormat#NCHW} corresponds to inputs with shape {@code (batch, channels, ...)}.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Flatten(Ops tf, String name, TensorFormat dataFormat, Class<T> type, Options options) {
    super(tf, name, true, type, options);
    this.dataFormat = dataFormat;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    // this layer only accepts one input
    if (inputs == null || inputs.size() != 1)
      throw new IllegalArgumentException("Flatten layer: only accepts 1 input");

    Operand<? extends TType> input = inputs.get(0);
    if (!isBuilt()) build(input.shape());
    Shape shape = input.shape();
    int rank = shape.numDimensions();
    if (this.dataFormat == TensorFormat.NCHW) {
      if (rank != Shape.UNKNOWN_SIZE && rank > 1) {
        long[] permutation = new long[rank + 1];
        permutation[0] = 0;
        for (int i = 2; i < rank; i++) permutation[i - 1] = i;
        permutation[rank] = 1;
        input = getTF().linalg.transpose(input, getTF().constant(permutation));
      }
    }

    if (rank == 1) {
      input = getTF().expandDims(input, getTF().constant(1));
    } else {
      Operand<TInt64> flattenedShape;
      long[] dims = shape.asArray();
      if (dims != null) {
        long batchDim = dims[0];
        long[] nonBatchDims = new long[dims.length - 1];
        System.arraycopy(dims, 1, nonBatchDims, 0, nonBatchDims.length);
        Shape nonBatchShape = Shape.of(nonBatchDims);
        if (!nonBatchShape.hasUnknownDimension()) {
          int lastDim = 1;
          for (long dim : nonBatchDims) lastDim *= dim;
          flattenedShape = getTF().constant(Shape.of(-1L, lastDim));
        } else if (batchDim != Shape.UNKNOWN_SIZE) {
          flattenedShape = getTF().constant(Shape.of(batchDim, -1L));
        } else {
          Operand<TInt64> batchDimension =
              getTF().shape.size(input, getTF().constant(0L), TInt64.class);
          flattenedShape = getTF().shape.append(batchDimension, getTF().constant(0L));
        }
        input = getTF().reshape(input, flattenedShape);
      }
    }
    return callPostProcess(Collections.singletonList(cast(getTF(), input, resultType)), training);
  }

  /** {@inheritDoc} */
  @Override
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    if (inputShapes == null || inputShapes.size() != 1)
      throw new IllegalArgumentException("Dense layer:  there must be one input shape");
    if (!isBuilt()) build(inputShapes);
    Shape inputShape = inputShapes.get(0);
    long lastDim = 1L;
    for (int i = 1; i < inputShape.numDimensions(); i++) {
      lastDim *= inputShape.size(i);
    }
    // creates a new shape of (batchSize, rest)
    Shape newShape = Shape.of(inputShape.size(0));
    newShape = newShape.append(lastDim);
    return Collections.singletonList(newShape);
  }
}
