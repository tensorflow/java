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

package org.tensorflow.op.math;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Generates points from the Sobol sequence.
 * <p>
 * Creates a Sobol sequence with `num_results` samples. Each sample has dimension
 * `dim`. Skips the first `skip` samples.
 * 
 * @param <T> data type for {@code samples()} output
 */
public final class SobolSample<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SobolSample operation.
   * 
   * @param scope current scope
   * @param dim Positive scalar `Tensor` representing each sample's dimension.
   * @param numResults Positive scalar `Tensor` of dtype int32. The number of Sobol points to return
   * in the output.
   * @param skip Positive scalar `Tensor` of dtype int32. The number of initial points of the
   * Sobol sequence to skip.
   * @param dtype The type of the sample. One of: `float32` or `float64`.
   * @return a new instance of SobolSample
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> SobolSample<T> create(Scope scope, Operand<TInt32> dim, Operand<TInt32> numResults, Operand<TInt32> skip, Class<T> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("SobolSample", scope.makeOpName("SobolSample"));
    opBuilder.addInput(dim.asOutput());
    opBuilder.addInput(numResults.asOutput());
    opBuilder.addInput(skip.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new SobolSample<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new SobolSample operation using default output types.
   * 
   * @param scope current scope
   * @param dim Positive scalar `Tensor` representing each sample's dimension.
   * @param numResults Positive scalar `Tensor` of dtype int32. The number of Sobol points to return
   * in the output.
   * @param skip Positive scalar `Tensor` of dtype int32. The number of initial points of the
   * Sobol sequence to skip.
   * @return a new instance of SobolSample
   */
  @Endpoint(describeByClass = true)
  public static SobolSample<TFloat32> create(Scope scope, Operand<TInt32> dim, Operand<TInt32> numResults, Operand<TInt32> skip) {
    return create(scope, dim, numResults, skip, TFloat32.class);
  }
  
  /**
   * `Tensor` of samples from Sobol sequence with `shape` [num_results, dim].
   */
  public Output<T> samples() {
    return samples;
  }
  
  @Override
  public Output<T> asOutput() {
    return samples;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SobolSample";
  
  private Output<T> samples;
  
  private SobolSample(Operation operation) {
    super(operation);
    int outputIdx = 0;
    samples = operation.output(outputIdx++);
  }
}
