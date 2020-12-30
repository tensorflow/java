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

package org.tensorflow.op.train;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;

/**
 * Applies L1 regularization shrink step on the parameters.
 */
@Operator(group = "train")
public final class SdcaShrinkL1 extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SdcaShrinkL1 operation.
   * 
   * @param scope current scope
   * @param weights a list of vectors where each value is the weight associated with a
   * feature group.
   * @param l1 Symmetric l1 regularization strength.
   * @param l2 Symmetric l2 regularization strength. Should be a positive float.
   * @return a new instance of SdcaShrinkL1
   */
  @Endpoint(describeByClass = true)
  public static SdcaShrinkL1 create(Scope scope, Iterable<Operand<TFloat32>> weights, Float l1, Float l2) {
    OperationBuilder opBuilder = scope.env().opBuilder("SdcaShrinkL1", scope.makeOpName("SdcaShrinkL1"));
    opBuilder.addInputList(Operands.asOutputs(weights));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("l1", l1);
    opBuilder.setAttr("l2", l2);
    return new SdcaShrinkL1(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SdcaShrinkL1";
  
  private SdcaShrinkL1(Operation operation) {
    super(operation);
  }
}
