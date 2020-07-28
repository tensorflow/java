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
package org.tensorflow.keras.backend.tf;

import org.tensorflow.keras.backend.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.tensorflow.Operand;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TType;

/** Container for ControlDepencies, so that the primary Operand is remembered. */
public class ControlDependencies {

  /**
   * Create a control dependency for the operand;
   *
   * @param tf the TensorFlow Ops
   * @param createOperand a function that creates an operand with the control dependency context
   * @param name the scope name to use
   * @param dependencies a list of control ops.
   * @param <T> the type of Operand
   * @return the Operand with control dependency scope
   */
  public static <T extends TType> Operand<T> addControlDependencies(
      Ops tf, Function<Ops, Operand<T>> createOperand, String name, Op... dependencies) {
    return addControlDependencies(tf, createOperand, name, Arrays.asList(dependencies));
  }

  /**
   * Create a control dependency for an operand.
   *
   * @param tf the TensorFlow Ops
   * @param createOperand function that creates an operand with the control dependency context
   * @param name the scope name to use
   * @param dependencies a list of control ops.
   * @param <T> the type of Operand
   * @return the Operand with control dependency scope
   */
  public static <T extends TType> Operand<T> addControlDependencies(
      Ops tf, Function<Ops, Operand<T>> createOperand, String name, List<Op> dependencies) {
    tf = tf.withSubScope(name).withControlDependencies(dependencies);
    return createOperand.apply(tf);
  }

  /**
   * Create a control dependency as a NoOp
   *
   * @param tf the TensorFlow Ops
   * @param name the scope name to use
   * @param dependencies a list of control ops.
   * @return NoOp with control dependency scope
   */
  public static Op addControlDependencies(Ops tf, String name, Op... dependencies) {
    return addControlDependencies(tf, name, Arrays.asList(dependencies));
  }

  /**
   * Create a control dependency as a NoOp
   *
   * @param tf the TensorFlow Ops
   * @param name the scope name to use
   * @param dependencies a list of control ops.
   * @return NoOp with control dependency scope
   */
  public static Op addControlDependencies(Ops tf, String name, List<Op> dependencies) {
    tf = tf.withSubScope(name).withControlDependencies(dependencies);
    return tf.noOp();
  }
}
