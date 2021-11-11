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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Flush the quantile summaries from each quantile stream resource.
 * An op that outputs a list of quantile summaries of a quantile stream resource.
 * Each summary Tensor is rank 2, containing summaries (value, weight, min_rank,
 * max_rank) for a single feature.
 */
@OpMetadata(
    opType = BoostedTreesFlushQuantileSummaries.OP_NAME,
    inputsClass = BoostedTreesFlushQuantileSummaries.Inputs.class
)
public final class BoostedTreesFlushQuantileSummaries extends RawOp implements Iterable<Operand<TFloat32>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesFlushQuantileSummaries";

  private List<Output<TFloat32>> summaries;

  @SuppressWarnings("unchecked")
  public BoostedTreesFlushQuantileSummaries(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int summariesLength = operation.outputListLength("summaries");
    summaries = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, summariesLength));
    outputIdx += summariesLength;
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesFlushQuantileSummaries operation.
   *
   * @param scope current scope
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param numFeatures The value of the numFeatures attribute
   * @return a new instance of BoostedTreesFlushQuantileSummaries
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesFlushQuantileSummaries create(Scope scope,
      Operand<? extends TType> quantileStreamResourceHandle, Long numFeatures) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesFlushQuantileSummaries");
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.setAttr("num_features", numFeatures);
    return new BoostedTreesFlushQuantileSummaries(opBuilder.build());
  }

  /**
   * Gets summaries.
   *
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
      outputsClass = BoostedTreesFlushQuantileSummaries.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesFlushQuantileSummaries> {
    /**
     * resource handle referring to a QuantileStreamResource.
     */
    public final Operand<? extends TType> quantileStreamResourceHandle;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesFlushQuantileSummaries(op), op, Arrays.asList());
      int inputIndex = 0;
      quantileStreamResourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
