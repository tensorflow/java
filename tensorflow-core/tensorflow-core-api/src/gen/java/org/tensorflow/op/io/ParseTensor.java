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

package org.tensorflow.op.io;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Transforms a serialized tensorflow.TensorProto proto into a Tensor.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "io")
public final class ParseTensor<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ParseTensor operation.
   * 
   * @param scope current scope
   * @param serialized A scalar string containing a serialized TensorProto proto.
   * @param outType The type of the serialized tensor.  The provided type must match the
   * type of the serialized tensor and no implicit conversion will take place.
   * @return a new instance of ParseTensor
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ParseTensor<T> create(Scope scope, Operand<TString> serialized, DataType<T> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("ParseTensor", scope.makeOpName("ParseTensor"));
    opBuilder.addInput(serialized.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", outType);
    return new ParseTensor<T>(opBuilder.build());
  }
  
  /**
   * A Tensor of type `out_type`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ParseTensor";
  
  private Output<T> output;
  
  private ParseTensor(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
