/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.collective;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;

/**
 * Assign group keys based on group assignment.
 */
@OpMetadata(
    opType = CollectiveAssignGroup.OP_NAME,
    inputsClass = CollectiveAssignGroup.Inputs.class
)
@Operator(
    group = "collective"
)
public final class CollectiveAssignGroup extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectiveAssignGroupV2";

  private Output<TInt32> groupSize;

  private Output<TInt32> groupKey;

  public CollectiveAssignGroup(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    groupSize = operation.output(outputIdx++);
    groupKey = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectiveAssignGroupV2 operation.
   *
   * @param scope current scope
   * @param groupAssignment The groupAssignment value
   * @param deviceIndex The deviceIndex value
   * @param baseKey The baseKey value
   * @return a new instance of CollectiveAssignGroup
   */
  @Endpoint(
      describeByClass = true
  )
  public static CollectiveAssignGroup create(Scope scope, Operand<TInt32> groupAssignment,
      Operand<TInt32> deviceIndex, Operand<TInt32> baseKey) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CollectiveAssignGroup");
    opBuilder.addInput(groupAssignment.asOutput());
    opBuilder.addInput(deviceIndex.asOutput());
    opBuilder.addInput(baseKey.asOutput());
    return new CollectiveAssignGroup(opBuilder.build());
  }

  /**
   * Gets groupSize.
   *
   * @return groupSize.
   */
  public Output<TInt32> groupSize() {
    return groupSize;
  }

  /**
   * Gets groupKey.
   *
   * @return groupKey.
   */
  public Output<TInt32> groupKey() {
    return groupKey;
  }

  @OpInputsMetadata(
      outputsClass = CollectiveAssignGroup.class
  )
  public static class Inputs extends RawOpInputs<CollectiveAssignGroup> {
    /**
     * The groupAssignment input
     */
    public final Operand<TInt32> groupAssignment;

    /**
     * The deviceIndex input
     */
    public final Operand<TInt32> deviceIndex;

    /**
     * The baseKey input
     */
    public final Operand<TInt32> baseKey;

    public Inputs(GraphOperation op) {
      super(new CollectiveAssignGroup(op), op, Arrays.asList());
      int inputIndex = 0;
      groupAssignment = (Operand<TInt32>) op.input(inputIndex++);
      deviceIndex = (Operand<TInt32>) op.input(inputIndex++);
      baseKey = (Operand<TInt32>) op.input(inputIndex++);
    }
  }
}
