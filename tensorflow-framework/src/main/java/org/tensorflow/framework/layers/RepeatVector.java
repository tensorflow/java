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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

/**
 * Repeats the input {@code repeatCount} times.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class RepeatVector<T extends TFloating> extends Layer<T> {

  private final int repeatCount;

  /**
   * Creates a RepeatCount using a unique name will be generated based on * {@link
   * Class#getSimpleName()}.
   *
   * @param repeatCount the repetition factor.
   * @param type the data type for the layer's weights and computation.
   */
  public RepeatVector(int repeatCount, Class<T> type) {
    this(null, repeatCount, type, null);
  }

  /**
   * Creates a RepeatVector using a unique name will be generated based on * {@link
   * Class#getSimpleName()}.
   *
   * @param repeatCount the repetition factor.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public RepeatVector(int repeatCount, Class<T> type, Options options) {
    this(null, repeatCount, type, options);
  }

  /**
   * Creates a RepeatCount
   *
   * @param name he unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param repeatCount the repetition factor.
   * @param type the data type for the layer's weights and computation.
   */
  public RepeatVector(String name, int repeatCount, Class<T> type) {
    this(name, repeatCount, type, null);
  }

  /**
   * Creates a RepeatCount
   *
   * @param name he unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param repeatCount the repetition factor.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public RepeatVector(String name, int repeatCount, Class<T> type, Options options) {
    super(name, true, type, options);
    this.repeatCount = repeatCount;
  }

  /**
   * @param inputs the input Operands, 2D tensor of shape (num_samples, features)
   * @param masks a list of masks, one for each input, to apply to the result, may be null
   * @param training whether the call is in inference mode or training mode
   * @param resultType the result type
   * @param <U> the data type of the result
   * @return a 3D tensor of shape (num_samples, repeatCount, features)
   */
  @Override
  public <U extends TType> List<Operand<U>> call(
      Ops tf,
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {

    if (inputs.isEmpty()) {
      return Collections.emptyList();
    }
    Ops ltf = init(tf);
    List<Operand<? extends TType>> outputs = new ArrayList<>();
    for (Operand<? extends TType> input : inputs) {
      if (input.shape().numDimensions() != 2) {
        throw new IllegalArgumentException("RepeatVector inputs must be rank 2.");
      }
      Operand<? extends TType> output = input;
      Operand<TInt32> one = ltf.constant(1);
      output = ltf.expandDims(output, ltf.constant(1));
      Operand<TInt32> pattern = ltf.stack(Arrays.asList(one, ltf.constant(repeatCount), one));
      output = ltf.tile(output, pattern);
      outputs.add(output);
    }
    return convertList(outputs, resultType);
  }
}
