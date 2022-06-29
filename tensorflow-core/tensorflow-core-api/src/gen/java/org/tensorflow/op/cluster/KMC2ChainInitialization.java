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
 * Returns the index of a data point that should be added to the seed set.
 * Entries in distances are assumed to be squared distances of candidate points to
 * the already sampled centers in the seed set. The op constructs one Markov chain
 * of the k-MC^2 algorithm and returns the index of one candidate point to be added
 * as an additional cluster center.
 */
@OpMetadata(
    opType = KMC2ChainInitialization.OP_NAME,
    inputsClass = KMC2ChainInitialization.Inputs.class
)
public final class KMC2ChainInitialization extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "KMC2ChainInitialization";

  private Output<TInt64> index;

  public KMC2ChainInitialization(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    index = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new KMC2ChainInitialization operation.
   *
   * @param scope current scope
   * @param distances Vector with squared distances to the closest previously sampled cluster center
   * for each candidate point.
   * @param seed Scalar. Seed for initializing the random number generator.
   * @return a new instance of KMC2ChainInitialization
   */
  @Endpoint(
      describeByClass = true
  )
  public static KMC2ChainInitialization create(Scope scope, Operand<TFloat32> distances,
      Operand<TInt64> seed) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "KMC2ChainInitialization");
    opBuilder.addInput(distances.asOutput());
    opBuilder.addInput(seed.asOutput());
    return new KMC2ChainInitialization(opBuilder.build());
  }

  /**
   * Gets index.
   * Scalar with the index of the sampled point.
   * @return index.
   */
  public Output<TInt64> index() {
    return index;
  }

  @Override
  public Output<TInt64> asOutput() {
    return index;
  }

  @OpInputsMetadata(
      outputsClass = KMC2ChainInitialization.class
  )
  public static class Inputs extends RawOpInputs<KMC2ChainInitialization> {
    /**
     * Vector with squared distances to the closest previously sampled cluster center
     * for each candidate point.
     */
    public final Operand<TFloat32> distances;

    /**
     * Scalar. Seed for initializing the random number generator.
     */
    public final Operand<TInt64> seed;

    public Inputs(GraphOperation op) {
      super(new KMC2ChainInitialization(op), op, Arrays.asList());
      int inputIndex = 0;
      distances = (Operand<TFloat32>) op.input(inputIndex++);
      seed = (Operand<TInt64>) op.input(inputIndex++);
    }
  }
}
