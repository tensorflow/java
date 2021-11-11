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

import java.util.Arrays;
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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Generates points from the Sobol sequence.
 * Creates a Sobol sequence with {@code num_results} samples. Each sample has dimension
 * {@code dim}. Skips the first {@code skip} samples.
 *
 * @param <T> data type for {@code samples} output
 */
@OpMetadata(
    opType = SobolSample.OP_NAME,
    inputsClass = SobolSample.Inputs.class
)
public final class SobolSample<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SobolSample";

  private Output<T> samples;

  public SobolSample(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    samples = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SobolSample operation.
   *
   * @param scope current scope
   * @param dim Positive scalar {@code Tensor} representing each sample's dimension.
   * @param numResults Positive scalar {@code Tensor} of dtype int32. The number of Sobol points to return
   * in the output.
   * @param skip Positive scalar {@code Tensor} of dtype int32. The number of initial points of the
   * Sobol sequence to skip.
   * @param dtype The type of the sample. One of: {@code float32} or {@code float64}.
   * @param <T> data type for {@code SobolSample} output and operands
   * @return a new instance of SobolSample
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SobolSample<T> create(Scope scope, Operand<TInt32> dim,
      Operand<TInt32> numResults, Operand<TInt32> skip, Class<T> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SobolSample");
    opBuilder.addInput(dim.asOutput());
    opBuilder.addInput(numResults.asOutput());
    opBuilder.addInput(skip.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new SobolSample<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new SobolSample operation, with the default output types.
   *
   * @param scope current scope
   * @param dim Positive scalar {@code Tensor} representing each sample's dimension.
   * @param numResults Positive scalar {@code Tensor} of dtype int32. The number of Sobol points to return
   * in the output.
   * @param skip Positive scalar {@code Tensor} of dtype int32. The number of initial points of the
   * Sobol sequence to skip.
   * @return a new instance of SobolSample, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static SobolSample<TFloat32> create(Scope scope, Operand<TInt32> dim,
      Operand<TInt32> numResults, Operand<TInt32> skip) {
    return create(scope, dim, numResults, skip, TFloat32.class);
  }

  /**
   * Gets samples.
   * {@code Tensor} of samples from Sobol sequence with {@code shape} [num_results, dim].
   * @return samples.
   */
  public Output<T> samples() {
    return samples;
  }

  @Override
  public Output<T> asOutput() {
    return samples;
  }

  @OpInputsMetadata(
      outputsClass = SobolSample.class
  )
  public static class Inputs extends RawOpInputs<SobolSample<?>> {
    /**
     * Positive scalar {@code Tensor} representing each sample's dimension.
     */
    public final Operand<TInt32> dim;

    /**
     * Positive scalar {@code Tensor} of dtype int32. The number of Sobol points to return
     * in the output.
     */
    public final Operand<TInt32> numResults;

    /**
     * Positive scalar {@code Tensor} of dtype int32. The number of initial points of the
     * Sobol sequence to skip.
     */
    public final Operand<TInt32> skip;

    /**
     * The type of the sample. One of: `float32` or `float64`.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new SobolSample<>(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      dim = (Operand<TInt32>) op.input(inputIndex++);
      numResults = (Operand<TInt32>) op.input(inputIndex++);
      skip = (Operand<TInt32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
