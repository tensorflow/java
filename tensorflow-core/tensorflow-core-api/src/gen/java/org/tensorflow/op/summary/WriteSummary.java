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

package org.tensorflow.op.summary;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 */
public final class WriteSummary extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new WriteSummary operation.
   * 
   * @param scope current scope
   * @param writer 
   * @param step 
   * @param tensor 
   * @param tag 
   * @param summaryMetadata 
   * @return a new instance of WriteSummary
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> WriteSummary create(Scope scope, Operand<?> writer, Operand<TInt64> step, Operand<T> tensor, Operand<TString> tag, Operand<TString> summaryMetadata) {
    OperationBuilder opBuilder = scope.env().opBuilder("WriteSummary", scope.makeOpName("WriteSummary"));
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(step.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(summaryMetadata.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new WriteSummary(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "WriteSummary";
  
  private WriteSummary(Operation operation) {
    super(operation);
  }
}
