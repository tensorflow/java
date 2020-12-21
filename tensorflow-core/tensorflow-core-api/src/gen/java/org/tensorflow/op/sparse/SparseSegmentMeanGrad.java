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

package org.tensorflow.op.sparse;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradients for SparseSegmentMean.
 * <p>
 * Returns tensor "output" with same shape as grad, except for dimension 0 whose
 * value is output_dim0.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "sparse")
public final class SparseSegmentMeanGrad<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SparseSegmentMeanGrad operation.
   * 
   * @param scope current scope
   * @param grad gradient propagated to the SparseSegmentMean op.
   * @param indices indices passed to the corresponding SparseSegmentMean op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentMean op.
   * @param outputDim0 dimension 0 of "data" passed to SparseSegmentMean op.
   * @return a new instance of SparseSegmentMeanGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber, V extends TNumber> SparseSegmentMeanGrad<T> create(Scope scope, Operand<T> grad, Operand<U> indices, Operand<V> segmentIds, Operand<TInt32> outputDim0) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSegmentMeanGrad", scope.makeOpName("SparseSegmentMeanGrad"));
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(outputDim0.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseSegmentMeanGrad<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseSegmentMeanGrad";
  
  private Output<T> output;
  
  private SparseSegmentMeanGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
