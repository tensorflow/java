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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TFloat;

/**
 * Makes the summary of quantiles for the batch.
 * <p>
 * An op that takes a list of tensors (one tensor per feature) and outputs the
 * quantile summaries for each tensor.
 */
public final class BoostedTreesMakeQuantileSummaries extends PrimitiveOp implements Iterable<Operand<TFloat>> {
  
  /**
   * Factory method to create a class wrapping a new BoostedTreesMakeQuantileSummaries operation.
   * 
   * @param scope current scope
   * @param floatValues float; List of Rank 1 Tensors each containing values for a single feature.
   * @param exampleWeights float; Rank 1 Tensor with weights per instance.
   * @param epsilon float; The required maximum approximation error.
   * @return a new instance of BoostedTreesMakeQuantileSummaries
   */
  public static BoostedTreesMakeQuantileSummaries create(Scope scope, Iterable<Operand<TFloat>> floatValues, Operand<TFloat> exampleWeights, Operand<TFloat> epsilon) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesMakeQuantileSummaries", scope.makeOpName("BoostedTreesMakeQuantileSummaries"));
    opBuilder.addInputList(Operands.asOutputs(floatValues));
    opBuilder.addInput(exampleWeights.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new BoostedTreesMakeQuantileSummaries(opBuilder.build());
  }
  
  /**
   * float; List of Rank 2 Tensors each containing the quantile summary
   * (value, weight, min_rank, max_rank) of a single feature.
   */
  public List<Output<TFloat>> summaries() {
    return summaries;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TFloat>> iterator() {
    return (Iterator) summaries.iterator();
  }
  
  private List<Output<TFloat>> summaries;
  
  @SuppressWarnings("unchecked")
  private BoostedTreesMakeQuantileSummaries(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int summariesLength = operation.outputListLength("summaries");
    summaries = Arrays.asList((Output<TFloat>[])operation.outputList(outputIdx, summariesLength));
    outputIdx += summariesLength;
  }
}
