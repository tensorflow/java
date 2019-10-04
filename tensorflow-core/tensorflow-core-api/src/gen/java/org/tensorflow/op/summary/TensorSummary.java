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
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Outputs a `Summary` protocol buffer with a tensor and per-plugin data.
 */
@Operator(group = "summary")
public final class TensorSummary extends PrimitiveOp implements Operand<String> {
  
  /**
   * Factory method to create a class wrapping a new TensorSummary operation.
   * 
   * @param scope current scope
   * @param tag A string attached to this summary. Used for organization in TensorBoard.
   * @param tensor A tensor to serialize.
   * @param serializedSummaryMetadata A serialized SummaryMetadata proto. Contains plugin
   * data.
   * @return a new instance of TensorSummary
   */
  public static <T> TensorSummary create(Scope scope, Operand<String> tag, Operand<T> tensor, Operand<String> serializedSummaryMetadata) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorSummaryV2", scope.makeOpName("TensorSummary"));
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(serializedSummaryMetadata.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new TensorSummary(opBuilder.build());
  }
  
  /**
   */
  public Output<String> summary() {
    return summary;
  }
  
  @Override
  public Output<String> asOutput() {
    return summary;
  }
  
  private Output<String> summary;
  
  private TensorSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }
}
