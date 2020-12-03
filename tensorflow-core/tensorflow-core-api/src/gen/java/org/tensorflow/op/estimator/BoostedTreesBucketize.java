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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Bucketize each feature based on bucket boundaries.
 * <p>
 * An op that returns a list of float tensors, where each tensor represents the
 * bucketized values for a single feature.
 */
public final class BoostedTreesBucketize extends RawOp implements Iterable<Operand<TInt32>> {
  
  /**
   * Factory method to create a class wrapping a new BoostedTreesBucketize operation.
   * 
   * @param scope current scope
   * @param floatValues float; List of Rank 1 Tensor each containing float values for a single feature.
   * @param bucketBoundaries float; List of Rank 1 Tensors each containing the bucket boundaries for a single
   * feature.
   * @return a new instance of BoostedTreesBucketize
   */
  @Endpoint(describeByClass = true)
  public static BoostedTreesBucketize create(Scope scope, Iterable<Operand<TFloat32>> floatValues, Iterable<Operand<TFloat32>> bucketBoundaries) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesBucketize", scope.makeOpName("BoostedTreesBucketize"));
    opBuilder.addInputList(Operands.asOutputs(floatValues));
    opBuilder.addInputList(Operands.asOutputs(bucketBoundaries));
    opBuilder = scope.apply(opBuilder);
    return new BoostedTreesBucketize(opBuilder.build());
  }
  
  /**
   * int; List of Rank 1 Tensors each containing the bucketized values for a single feature.
   */
  public List<Output<TInt32>> buckets() {
    return buckets;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TInt32>> iterator() {
    return (Iterator) buckets.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BoostedTreesBucketize";
  
  private List<Output<TInt32>> buckets;
  
  @SuppressWarnings("unchecked")
  private BoostedTreesBucketize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int bucketsLength = operation.outputListLength("buckets");
    buckets = Arrays.asList((Output<TInt32>[])operation.outputList(outputIdx, bucketsLength));
    outputIdx += bucketsLength;
  }
}
