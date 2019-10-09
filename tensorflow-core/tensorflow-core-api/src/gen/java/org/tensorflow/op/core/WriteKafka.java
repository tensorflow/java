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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 */
@Operator
public final class WriteKafka extends PrimitiveOp implements Operand<TString> {
  
  /**
   * Factory method to create a class wrapping a new WriteKafka operation.
   * 
   * @param scope current scope
   * @param message 
   * @param topic 
   * @param servers 
   * @return a new instance of WriteKafka
   */
  public static WriteKafka create(Scope scope, Operand<TString> message, Operand<TString> topic, Operand<TString> servers) {
    OperationBuilder opBuilder = scope.env().opBuilder("WriteKafka", scope.makeOpName("WriteKafka"));
    opBuilder.addInput(message.asOutput());
    opBuilder.addInput(topic.asOutput());
    opBuilder.addInput(servers.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new WriteKafka(opBuilder.build());
  }
  
  /**
   */
  public Output<TString> content() {
    return content;
  }
  
  @Override
  public Output<TString> asOutput() {
    return content;
  }
  
  private Output<TString> content;
  
  private WriteKafka(Operation operation) {
    super(operation);
    int outputIdx = 0;
    content = operation.output(outputIdx++);
  }
}
