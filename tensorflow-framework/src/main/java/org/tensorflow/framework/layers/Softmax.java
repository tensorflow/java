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

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.framework.op.FrameworkOps;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

/** Softmax activation function. */
public class Softmax<T extends TFloating> extends Layer<T> {

  private final int[] axes;

  /**
   * Creates a SoftMax layer
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param axes axes along which the softmax normalization is applied.
   * @param type the data type for the layer's weights and computation.
   */
  public Softmax(String name, int[] axes, Class<T> type) {
    this(name, axes, type, null);
  }

  /**
   * Creates a SoftMax layer
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param axes axes along which the softmax normalization is applied.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Softmax(String name, int[] axes, Class<T> type, Options options) {
    super(name, true, type, options);
    this.axes = axes;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      Ops tf,
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    Ops ltf = init(tf);
    FrameworkOps fops = FrameworkOps.create(ltf);
    // TODO mask

    List<Operand<T>> results = new ArrayList<>();

    for (int i = 0; i < inputs.size(); i++) {
      Operand<T> input = cast(ltf, inputs.get(i), getType());
      Operand<T> result;
      if (masks != null && !masks.isEmpty()) {
        // Since attention_mask is 1.0 for positions we want to attend and 0.0 for
        // masked positions, this operation will create a tensor which is 0.0 for
        // positions we want to attend and -1e.9 for masked positions.
        Operand<TBool> mask = masks.get(i);
        Operand<T> one = cast(ltf, ltf.constant(1), getType());

        Operand<T> adder =
            ltf.math.mul(ltf.math.sub(one, cast(ltf, mask, getType())), largeCompatibleNegative());
        // Since we are adding it to the raw scores before the softmax, this is
        // effectively the same as removing these entirely.

        input = ltf.math.add(input, adder);
      }
      if (axes.length > 1) {
        result = ltf.math.exp(ltf.math.sub(input, fops.math.reduceLogSumExp(input, axes, true)));
      } else {
        result = fops.nn.softmax(input, axes[0]);
      }
      results.add(result);
    }
    return callPostProcess(convertTo(results, resultType), training);
  }

  /**
   * Gets a large number based on the data type
   *
   * @return a large number based on the data type
   */
  private Operand<T> largeCompatibleNegative() {
    Ops tf = getTF();
    if (getType() == TFloat16.class) {
      return cast(tf, tf.constant(-0xffdc), getType());
    } else {
      return cast(tf, tf.constant(-1e9), getType());
    }
  }
}
