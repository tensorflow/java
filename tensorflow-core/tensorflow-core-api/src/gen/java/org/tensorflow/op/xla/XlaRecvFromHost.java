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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * An op to receive a tensor from the host.
 * output: the tensor that will be received from the host.
 * Toutput: element type for output.
 * shape: shape for output.
 * key: A unique identifier for this region used to match up host transfers.
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "xla"
)
public final class XlaRecvFromHost<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaRecvFromHost";

  private Output<T> output;

  private XlaRecvFromHost(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaRecvFromHost operation.
   *
   * @param scope current scope
   * @param Toutput the value of the Toutput property
   * @param shape the value of the shape property
   * @param key the value of the key property
   * @param <T> data type for {@code XlaRecvFromHost} output and operands
   * @return a new instance of XlaRecvFromHost
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> XlaRecvFromHost<T> create(Scope scope, Class<T> Toutput,
      Shape shape, String key) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaRecvFromHost", scope.makeOpName("XlaRecvFromHost"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Toutput", Operands.toDataType(Toutput));
    opBuilder.setAttr("shape", shape);
    opBuilder.setAttr("key", key);
    return new XlaRecvFromHost<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }
}
