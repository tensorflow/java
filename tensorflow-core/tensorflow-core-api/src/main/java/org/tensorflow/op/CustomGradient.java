/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/
package org.tensorflow.op;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.TensorFlow;
import org.tensorflow.internal.c_api.GradFunc;

/**
 * A custom gradient for ops of type {@link T}. Should be registered using {@link
 * TensorFlow#registerCustomGradient(Class, CustomGradient)}.
 *
 * <p>Creates the gradient based off of an instance of the op inputs class, which is created using
 * reflection. To operate on the {@link org.tensorflow.GraphOperation} directly use {@link
 * RawCustomGradient}.
 *
 * <p>The type of the op is not checked here, but it is required to match the class given to the
 * adapter.
 *
 * @param <T> the type of op this gradient is for.
 */
@SuppressWarnings("rawtypes")
@FunctionalInterface
public interface CustomGradient<T extends RawOpInputs> {

  /**
   * Calculate the gradients for {@code op}.
   *
   * @param tf the {@link Ops} instance used to create ops
   * @param op the op to calculate the gradients of.
   * @param gradInputs the gradients of the op's outputs.
   * @return the gradients of the op's inputs.
   */
  List<Operand<?>> call(Ops tf, T op, List<Output<?>> gradInputs);

  /**
   * Create an adapter for the custom gradient so that it can be used by native code.
   *
   * <p>You should not be calling this yourself, use {@link TensorFlow#registerCustomGradient(Class,
   * CustomGradient)}.
   */
  public static <T extends RawOpInputs<?>> GradFunc adapter(
      CustomGradient<T> gradient, Class<T> opClass) {
    return new TypedGradientAdapter<T>(gradient, opClass);
  }
}
