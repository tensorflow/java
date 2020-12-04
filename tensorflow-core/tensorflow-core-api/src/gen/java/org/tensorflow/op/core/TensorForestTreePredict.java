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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;

/**
 * Output the logits for the given input data
 */
public final class TensorForestTreePredict extends RawOp implements Operand<TFloat32> {
  
  /**
   * Factory method to create a class wrapping a new TensorForestTreePredict operation.
   * 
   * @param scope current scope
   * @param treeHandle Handle to the tree resource.
   * @param denseFeatures Rank 2 dense features tensor.
   * @param logitsDimension Scalar, dimension of the logits.
   * @return a new instance of TensorForestTreePredict
   */
  @Endpoint(describeByClass = true)
  public static TensorForestTreePredict create(Scope scope, Operand<?> treeHandle, Operand<TFloat32> denseFeatures, Long logitsDimension) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorForestTreePredict", scope.makeOpName("TensorForestTreePredict"));
    opBuilder.addInput(treeHandle.asOutput());
    opBuilder.addInput(denseFeatures.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("logits_dimension", logitsDimension);
    return new TensorForestTreePredict(opBuilder.build());
  }
  
  /**
   * The logits predictions from the tree for each instance in the batch.
   */
  public Output<TFloat32> logits() {
    return logits;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return logits;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorForestTreePredict";
  
  private Output<TFloat32> logits;
  
  private TensorForestTreePredict(Operation operation) {
    super(operation);
    int outputIdx = 0;
    logits = operation.output(outputIdx++);
  }
}
