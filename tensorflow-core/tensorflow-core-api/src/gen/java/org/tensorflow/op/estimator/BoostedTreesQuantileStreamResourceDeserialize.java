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

package org.tensorflow.op.estimator;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Deserialize bucket boundaries and ready flag into current QuantileAccumulator.
 * An op that deserializes bucket boundaries and are boundaries ready flag into current QuantileAccumulator.
 */
@OpMetadata(
    opType = BoostedTreesQuantileStreamResourceDeserialize.OP_NAME,
    inputsClass = BoostedTreesQuantileStreamResourceDeserialize.Inputs.class
)
public final class BoostedTreesQuantileStreamResourceDeserialize extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesQuantileStreamResourceDeserialize";

  public BoostedTreesQuantileStreamResourceDeserialize(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesQuantileStreamResourceDeserialize operation.
   *
   * @param scope current scope
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param bucketBoundaries float; List of Rank 1 Tensors each containing the bucket boundaries for a feature.
   * @return a new instance of BoostedTreesQuantileStreamResourceDeserialize
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesQuantileStreamResourceDeserialize create(Scope scope,
      Operand<? extends TType> quantileStreamResourceHandle,
      Iterable<Operand<TFloat32>> bucketBoundaries) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesQuantileStreamResourceDeserialize");
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(bucketBoundaries));
    return new BoostedTreesQuantileStreamResourceDeserialize(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesQuantileStreamResourceDeserialize.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesQuantileStreamResourceDeserialize> {
    /**
     * resource handle referring to a QuantileStreamResource.
     */
    public final Operand<? extends TType> quantileStreamResourceHandle;

    /**
     * float; List of Rank 1 Tensors each containing the bucket boundaries for a feature.
     */
    public final Iterable<Operand<TFloat32>> bucketBoundaries;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesQuantileStreamResourceDeserialize(op), op, Arrays.asList());
      int inputIndex = 0;
      quantileStreamResourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
      int bucketBoundariesLength = op.inputListLength("bucket_boundaries");
      bucketBoundaries = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, bucketBoundariesLength));
      inputIndex += bucketBoundariesLength;
    }
  }
}
