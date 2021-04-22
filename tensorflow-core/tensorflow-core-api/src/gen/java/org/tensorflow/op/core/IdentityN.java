/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

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

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Returns a list of tensors with the same shapes and contents as the input
 * tensors.
 * <p>This op can be used to override the gradient for complicated functions. For
 * example, suppose y = f(x) and we wish to apply a custom function g for backprop
 * such that dx = g(dy). In Python,
 * <pre>
 * with tf.get_default_graph().gradient_override_map(
 *     {'IdentityN': 'OverrideGradientWithG'}):
 *   y, _ = identity_n([f(x), x])
 *
 * {@literal @}tf.RegisterGradient('OverrideGradientWithG')
 * def ApplyG(op, dy, _):
 *   return [None, g(dy)]  # Do not backprop to f(x).
 * </pre>
 */
@Operator
public final class IdentityN extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IdentityN";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  private IdentityN(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new IdentityN operation.
   *
   * @param scope current scope
   * @param input the input value
   * @return a new instance of IdentityN
   */
  @Endpoint(
      describeByClass = true
  )
  public static IdentityN create(Scope scope, Iterable<Operand<?>> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("IdentityN", scope.makeOpName("IdentityN"));
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder = scope.apply(opBuilder);
    return new IdentityN(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }
}
