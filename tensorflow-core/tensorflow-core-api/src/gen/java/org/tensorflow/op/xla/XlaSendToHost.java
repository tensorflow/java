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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * An op to send a tensor to the host.
 * input: the tensor that will be sent to the host.
 * Tinput: element type for input.
 * key: A unique identifier for this region used to match up host transfers.
 */
@Operator(
    group = "xla"
)
public final class XlaSendToHost extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSendToHost";

  private XlaSendToHost(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new XlaSendToHost operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param key the value of the key property
   * @return a new instance of XlaSendToHost
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSendToHost create(Scope scope, Operand<? extends TType> input, String key) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSendToHost");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("key", key);
    return new XlaSendToHost(opBuilder.build());
  }
}
