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

package org.tensorflow.op.cluster;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;

/**
 * Selects num_to_sample rows of input using the KMeans++ criterion.
 * Rows of points are assumed to be input points. One row is selected at random.
 * Subsequent rows are sampled with probability proportional to the squared L2
 * distance from the nearest row selected thus far till num_to_sample rows have
 * been sampled.
 */
@OpMetadata(
    opType = KmeansPlusPlusInitialization.OP_NAME,
    inputsClass = KmeansPlusPlusInitialization.Inputs.class
)
public final class KmeansPlusPlusInitialization extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "KmeansPlusPlusInitialization";

  private Output<TFloat32> samples;

  public KmeansPlusPlusInitialization(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    samples = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new KmeansPlusPlusInitialization operation.
   *
   * @param scope current scope
   * @param points Matrix of shape (n, d). Rows are assumed to be input points.
   * @param numToSample Scalar. The number of rows to sample. This value must not be larger than n.
   * @param seed Scalar. Seed for initializing the random number generator.
   * @param numRetriesPerSample Scalar. For each row that is sampled, this parameter
   * specifies the number of additional points to draw from the current
   * distribution before selecting the best. If a negative value is specified, a
   * heuristic is used to sample O(log(num_to_sample)) additional points.
   * @return a new instance of KmeansPlusPlusInitialization
   */
  @Endpoint(
      describeByClass = true
  )
  public static KmeansPlusPlusInitialization create(Scope scope, Operand<TFloat32> points,
      Operand<TInt64> numToSample, Operand<TInt64> seed, Operand<TInt64> numRetriesPerSample) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "KmeansPlusPlusInitialization");
    opBuilder.addInput(points.asOutput());
    opBuilder.addInput(numToSample.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(numRetriesPerSample.asOutput());
    return new KmeansPlusPlusInitialization(opBuilder.build());
  }

  /**
   * Gets samples.
   * Matrix of shape (num_to_sample, d). The sampled rows.
   * @return samples.
   */
  public Output<TFloat32> samples() {
    return samples;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return samples;
  }

  @OpInputsMetadata(
      outputsClass = KmeansPlusPlusInitialization.class
  )
  public static class Inputs extends RawOpInputs<KmeansPlusPlusInitialization> {
    /**
     * Matrix of shape (n, d). Rows are assumed to be input points.
     */
    public final Operand<TFloat32> points;

    /**
     * Scalar. The number of rows to sample. This value must not be larger than n.
     */
    public final Operand<TInt64> numToSample;

    /**
     * Scalar. Seed for initializing the random number generator.
     */
    public final Operand<TInt64> seed;

    /**
     * Scalar. For each row that is sampled, this parameter
     * specifies the number of additional points to draw from the current
     * distribution before selecting the best. If a negative value is specified, a
     * heuristic is used to sample O(log(num_to_sample)) additional points.
     */
    public final Operand<TInt64> numRetriesPerSample;

    public Inputs(GraphOperation op) {
      super(new KmeansPlusPlusInitialization(op), op, Arrays.asList());
      int inputIndex = 0;
      points = (Operand<TFloat32>) op.input(inputIndex++);
      numToSample = (Operand<TInt64>) op.input(inputIndex++);
      seed = (Operand<TInt64>) op.input(inputIndex++);
      numRetriesPerSample = (Operand<TInt64>) op.input(inputIndex++);
    }
  }
}
