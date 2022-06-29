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
import java.util.Iterator;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TFloat32;

/**
 * Makes the summary of quantiles for the batch.
 * An op that takes a list of tensors (one tensor per feature) and outputs the
 * quantile summaries for each tensor.
 */
@OpMetadata(
    opType = BoostedTreesMakeQuantileSummaries.OP_NAME,
    inputsClass = BoostedTreesMakeQuantileSummaries.Inputs.class
)
public final class BoostedTreesMakeQuantileSummaries extends RawOp implements Iterable<Operand<TFloat32>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesMakeQuantileSummaries";

  private List<Output<TFloat32>> summaries;

  @SuppressWarnings("unchecked")
  public BoostedTreesMakeQuantileSummaries(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int summariesLength = operation.outputListLength("summaries");
    summaries = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, summariesLength));
    outputIdx += summariesLength;
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesMakeQuantileSummaries operation.
   *
   * @param scope current scope
   * @param floatValues float; List of Rank 1 Tensors each containing values for a single feature.
   * @param exampleWeights float; Rank 1 Tensor with weights per instance.
   * @param epsilon float; The required maximum approximation error.
   * @return a new instance of BoostedTreesMakeQuantileSummaries
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesMakeQuantileSummaries create(Scope scope,
      Iterable<Operand<TFloat32>> floatValues, Operand<TFloat32> exampleWeights,
      Operand<TFloat32> epsilon) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesMakeQuantileSummaries");
    opBuilder.addInputList(Operands.asOutputs(floatValues));
    opBuilder.addInput(exampleWeights.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    return new BoostedTreesMakeQuantileSummaries(opBuilder.build());
  }

  /**
   * Gets summaries.
   * float; List of Rank 2 Tensors each containing the quantile summary
   * (value, weight, min_rank, max_rank) of a single feature.
   * @return summaries.
   */
  public List<Output<TFloat32>> summaries() {
    return summaries;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TFloat32>> iterator() {
    return (Iterator) summaries.iterator();
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesMakeQuantileSummaries.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesMakeQuantileSummaries> {
    /**
     * float; List of Rank 1 Tensors each containing values for a single feature.
     */
    public final Iterable<Operand<TFloat32>> floatValues;

    /**
     * float; Rank 1 Tensor with weights per instance.
     */
    public final Operand<TFloat32> exampleWeights;

    /**
     * float; The required maximum approximation error.
     */
    public final Operand<TFloat32> epsilon;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesMakeQuantileSummaries(op), op, Arrays.asList());
      int inputIndex = 0;
      int floatValuesLength = op.inputListLength("float_values");
      floatValues = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, floatValuesLength));
      inputIndex += floatValuesLength;
      exampleWeights = (Operand<TFloat32>) op.input(inputIndex++);
      epsilon = (Operand<TFloat32>) op.input(inputIndex++);
    }
  }
}
