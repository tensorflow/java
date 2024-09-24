// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.cluster.KMC2ChainInitialization;
import org.tensorflow.op.cluster.KmeansPlusPlusInitialization;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;

/**
 * An API for building {@code cluster} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class ClusterOps {
  private final Scope scope;

  private final Ops ops;

  ClusterOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Returns the index of a data point that should be added to the seed set.
   *  Entries in distances are assumed to be squared distances of candidate points to
   *  the already sampled centers in the seed set. The op constructs one Markov chain
   *  of the k-MC^2 algorithm and returns the index of one candidate point to be added
   *  as an additional cluster center.
   *
   * @param distances Vector with squared distances to the closest previously sampled cluster center
   *  for each candidate point.
   * @param seed Scalar. Seed for initializing the random number generator.
   * @return a new instance of KMC2ChainInitialization
   */
  public KMC2ChainInitialization kMC2ChainInitialization(Operand<TFloat32> distances,
      Operand<TInt64> seed) {
    return KMC2ChainInitialization.create(scope, distances, seed);
  }

  /**
   * Selects num_to_sample rows of input using the KMeans++ criterion.
   *  Rows of points are assumed to be input points. One row is selected at random.
   *  Subsequent rows are sampled with probability proportional to the squared L2
   *  distance from the nearest row selected thus far till num_to_sample rows have
   *  been sampled.
   *
   * @param points Matrix of shape (n, d). Rows are assumed to be input points.
   * @param numToSample Scalar. The number of rows to sample. This value must not be larger than n.
   * @param seed Scalar. Seed for initializing the random number generator.
   * @param numRetriesPerSample Scalar. For each row that is sampled, this parameter
   *  specifies the number of additional points to draw from the current
   *  distribution before selecting the best. If a negative value is specified, a
   *  heuristic is used to sample O(log(num_to_sample)) additional points.
   * @return a new instance of KmeansPlusPlusInitialization
   */
  public KmeansPlusPlusInitialization kmeansPlusPlusInitialization(Operand<TFloat32> points,
      Operand<TInt64> numToSample, Operand<TInt64> seed, Operand<TInt64> numRetriesPerSample) {
    return KmeansPlusPlusInitialization.create(scope, points, numToSample, seed, numRetriesPerSample);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
