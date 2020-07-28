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
package org.tensorflow.keras.utils;

import java.util.function.Supplier;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;

/** Implements a select Operation using Lambdas */
public class SmartCond {

  /**
   * Creates a select operation
   *
   * @param pred the predicate (boolean) used to select
   * @param then_fn the function to execute if the predicate is true
   * @param else_fn the function to execute if the predicate is false
   * @return the result of the select on the condition
   */
  public static Operand select(Boolean pred, Supplier<Operand> then_fn, Supplier<Operand> else_fn) {
    assert pred != null : "pred must not be null";
    assert then_fn != null : "then_fn must not be null";
    assert else_fn != null : "else_fn must not be null";
    return pred ? then_fn.get() : else_fn.get();
  }

  /**
   * Creates a select operation
   *
   * @param pred the predicate ( true == 1) used to select
   * @param then_fn the function to execute if the predicate is true
   * @param else_fn the function to execute if the predicate is false
   * @return the result of the select on the condition
   */
  public static Operand select(Number pred, Supplier<Operand> then_fn, Supplier<Operand> else_fn) {
    assert pred != null : "pred must not be null";
    assert then_fn != null : "then_fn must not be null";
    assert else_fn != null : "else_fn must not be null";
    return pred.intValue() == 1 ? then_fn.get() : else_fn.get();
  }

  /**
   * Creates a select operation
   *
   * @param pred the predicate ( true if the string argument is not null and is equal, ignoring
   *     case, to the string "true") used to select
   * @param then_fn the function to execute if the predicate is true
   * @param else_fn the function to execute if the predicate is false
   * @return the result of the select on the condition
   */
  public static Operand select(String pred, Supplier<Operand> then_fn, Supplier<Operand> else_fn) {
    assert pred != null : "pred must not be null";
    assert then_fn != null : "then_fn must not be null";
    assert else_fn != null : "else_fn must not be null";
    return Boolean.valueOf(pred) ? then_fn.get() : else_fn.get();
  }

  /**
   * Create a Select operation
   *
   * @param tf the tensorFlow Ops
   * @param pred the operand that evaluates to true or false
   * @param then_fn the function to execute if the predicate is true
   * @param else_fn the function to execute if the predicate is false
   * @return a Select Operation if in graph mode, else return the result of the select
   */
  public static Operand select(
      Ops tf, Operand<TBool> pred, Supplier<Operand> then_fn, Supplier<Operand> else_fn) {
    assert pred != null : "pred must not be null";
    assert then_fn != null : "then_fn must not be null";
    assert else_fn != null : "else_fn must not be null";
    if (tf.scope().env().isEager()) {
      return pred.asOutput().data().getBoolean() ? then_fn.get() : else_fn.get();
    } else { // TODO, maybe some day handle Supplier in the c interface
      return tf.select(pred, then_fn.get(), else_fn.get());
    }
  }

  /**
   * Creates a slect operation
   *
   * @param tf the tensorFlow Ops
   * @param pred the operand that evaluates to true or false
   * @param then_fn the function to execute if the predicate is true
   * @param else_fn the function to execute if the predicate is false
   * @return the Select opertion
   */
  public static Operand select(Ops tf, Operand<TBool> pred, Operand true_op, Operand false_op) {
    assert pred != null : "pred must not be null";
    assert true_op != null : "true_op must not be null";
    assert false_op != null : "false_op must not be null";
    return tf.select(pred, true_op, false_op);
  }
}
