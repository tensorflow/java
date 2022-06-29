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
 * Generate the bucket boundaries for each feature based on accumulated summaries.
 * An op that returns a list of float tensors for a quantile stream resource. Each
 * tensor is Rank 1 containing bucket boundaries for a single feature.
 */
@OpMetadata(
    opType = BoostedTreesQuantileStreamResourceGetBucketBoundaries.OP_NAME,
    inputsClass = BoostedTreesQuantileStreamResourceGetBucketBoundaries.Inputs.class
)
public final class BoostedTreesQuantileStreamResourceGetBucketBoundaries extends RawOp implements Iterable<Operand<TFloat32>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesQuantileStreamResourceGetBucketBoundaries";

  private List<Output<TFloat32>> bucketBoundaries;

  @SuppressWarnings("unchecked")
  public BoostedTreesQuantileStreamResourceGetBucketBoundaries(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int bucketBoundariesLength = operation.outputListLength("bucket_boundaries");
    bucketBoundaries = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, bucketBoundariesLength));
    outputIdx += bucketBoundariesLength;
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesQuantileStreamResourceGetBucketBoundaries operation.
   *
   * @param scope current scope
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param numFeatures inferred int; number of features to get bucket boundaries for.
   * @return a new instance of BoostedTreesQuantileStreamResourceGetBucketBoundaries
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesQuantileStreamResourceGetBucketBoundaries create(Scope scope,
      Operand<? extends TType> quantileStreamResourceHandle, Long numFeatures) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesQuantileStreamResourceGetBucketBoundaries");
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.setAttr("num_features", numFeatures);
    return new BoostedTreesQuantileStreamResourceGetBucketBoundaries(opBuilder.build());
  }

  /**
   * Gets bucketBoundaries.
   * float; List of Rank 1 Tensors each containing the bucket boundaries for a feature.
   * @return bucketBoundaries.
   */
  public List<Output<TFloat32>> bucketBoundaries() {
    return bucketBoundaries;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TFloat32>> iterator() {
    return (Iterator) bucketBoundaries.iterator();
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesQuantileStreamResourceGetBucketBoundaries.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesQuantileStreamResourceGetBucketBoundaries> {
    /**
     * resource handle referring to a QuantileStreamResource.
     */
    public final Operand<? extends TType> quantileStreamResourceHandle;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesQuantileStreamResourceGetBucketBoundaries(op), op, Arrays.asList());
      int inputIndex = 0;
      quantileStreamResourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
