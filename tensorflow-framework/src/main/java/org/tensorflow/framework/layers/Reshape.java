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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Layer that reshapes inputs into the given shape.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Reshape<T extends TFloating> extends Layer<T> {

  private final Shape targetShape;

  /**
   * Creates a Reshape layer using a unique name will be generated based on {@link
   * Class#getSimpleName()}.
   *
   * @param tf the TensorFlow Ops
   * @param targetShape the target shape. Does not include the batch size dimension.
   * @param type the data type for the layer's weights and computation.
   */
  public Reshape(Ops tf, Shape targetShape, Class<T> type) {
    this(tf, null, targetShape, type, null);
  }

  /**
   * Creates a Reshape layer using a unique name will be generated based on {@link
   * Class#getSimpleName()}.
   *
   * @param tf the TensorFlow Ops
   * @param targetShape the target shape. Does not include the batch size dimension.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Reshape(Ops tf, Shape targetShape, Class<T> type, Options options) {
    this(tf, null, targetShape, type, options);
  }

  /**
   * Creates a Reshape layer.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this layer
   * @param targetShape the target shape. Does not include the batch size dimension.
   * @param type the data type for the layer's weights and computation.
   */
  public Reshape(Ops tf, String name, Shape targetShape, Class<T> type) {
    this(tf, name, targetShape, type, null);
  }

  /**
   * Creates a Reshape layer.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this layer
   * @param targetShape the target shape. Does not include the batch size dimension.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Reshape(Ops tf, String name, Shape targetShape, Class<T> type, Options options) {
    super(tf, name, true, type, options);
    this.targetShape = targetShape;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    if (inputs.isEmpty()) {
      return Collections.emptyList();
    }
    Ops tf = getTF();
    Operand<? extends TType> input = inputs.get(0);
    long batchSize = input.shape().size(0);
    Shape newShape = targetShape.prepend(batchSize);
    List<Operand<? extends TType>> result = new ArrayList<>();
    Operand<TInt64> newShapeOp = tf.constant(newShape);
    inputs.forEach(inp -> result.add(tf.reshape(cast(tf, inp, getType()), newShapeOp)));
    return callPostProcess(convertList(result, resultType), training);
  }
}
