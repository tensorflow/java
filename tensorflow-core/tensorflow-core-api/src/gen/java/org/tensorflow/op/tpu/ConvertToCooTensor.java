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

package org.tensorflow.op.tpu;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * The ConvertToCooTensor operation
 */
@OpMetadata(
    opType = ConvertToCooTensor.OP_NAME,
    inputsClass = ConvertToCooTensor.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class ConvertToCooTensor extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConvertToCooTensor";

  private Output<TInt32> rowIds;

  private Output<TInt32> colIds;

  private Output<TFloat32> gains;

  public ConvertToCooTensor(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    rowIds = operation.output(outputIdx++);
    colIds = operation.output(outputIdx++);
    gains = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ConvertToCooTensor operation.
   *
   * @param scope current scope
   * @param indicesOrRowSplits The indicesOrRowSplits value
   * @param values The values value
   * @param weights The weights value
   * @param sampleCount The value of the sampleCount attribute
   * @param combiner The value of the combiner attribute
   * @return a new instance of ConvertToCooTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConvertToCooTensor create(Scope scope, Operand<TInt32> indicesOrRowSplits,
      Operand<TInt32> values, Operand<TFloat32> weights, Long sampleCount, String combiner) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConvertToCooTensor");
    opBuilder.addInput(indicesOrRowSplits.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder.setAttr("sample_count", sampleCount);
    opBuilder.setAttr("combiner", combiner);
    return new ConvertToCooTensor(opBuilder.build());
  }

  /**
   * Gets rowIds.
   *
   * @return rowIds.
   */
  public Output<TInt32> rowIds() {
    return rowIds;
  }

  /**
   * Gets colIds.
   *
   * @return colIds.
   */
  public Output<TInt32> colIds() {
    return colIds;
  }

  /**
   * Gets gains.
   *
   * @return gains.
   */
  public Output<TFloat32> gains() {
    return gains;
  }

  @OpInputsMetadata(
      outputsClass = ConvertToCooTensor.class
  )
  public static class Inputs extends RawOpInputs<ConvertToCooTensor> {
    /**
     * The indicesOrRowSplits input
     */
    public final Operand<TInt32> indicesOrRowSplits;

    /**
     * The values input
     */
    public final Operand<TInt32> values;

    /**
     * The weights input
     */
    public final Operand<TFloat32> weights;

    /**
     * The sampleCount attribute
     */
    public final long sampleCount;

    /**
     * The combiner attribute
     */
    public final String combiner;

    public Inputs(GraphOperation op) {
      super(new ConvertToCooTensor(op), op, Arrays.asList("sample_count", "combiner"));
      int inputIndex = 0;
      indicesOrRowSplits = (Operand<TInt32>) op.input(inputIndex++);
      values = (Operand<TInt32>) op.input(inputIndex++);
      weights = (Operand<TFloat32>) op.input(inputIndex++);
      sampleCount = op.attributes().getAttrInt("sample_count");
      combiner = op.attributes().getAttrString("combiner");
    }
  }
}
