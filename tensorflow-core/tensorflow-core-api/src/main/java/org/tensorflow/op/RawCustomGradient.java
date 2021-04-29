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
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.TensorFlow;

/**
 * A custom gradient for an op of unspecified type. Should be registered using {@link
 * TensorFlow#registerCustomGradient(String, RawCustomGradient)}.
 *
 * <p>The op type of {@code op} will depend on the op type string passed to the registration method.
 * Note that the registration method can be called more than once, resulting this gradient function
 * being used for multiple different op types.
 */
@FunctionalInterface
public interface RawCustomGradient {

  /**
   * Calculate the gradients for {@code op}.
   *
   * @param tf the {@link Ops} instance used to create ops
   * @param op the op to calculate the gradients of.
   * @param gradInputs the gradients of the op's outputs.
   * @return the gradients of the op's inputs.
   */
  List<Operand<?>> call(Ops tf, GraphOperation op, List<Output<?>> gradInputs);
}
