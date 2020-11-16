// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.op.nn.raw.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.raw.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code nn.raw} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class NnRawOps {
  private final Scope scope;

  private final Ops ops;

  NnRawOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Computes softmax cross entropy cost and gradients to backpropagate.
   *  <p>
   *  Inputs are the logits, not probabilities.
   *
   * @param <T> data type for {@code loss()} output
   * @param features batch_size x num_classes matrix
   * @param labels batch_size x num_classes matrix
   *  The caller must ensure that each batch of labels represents a valid
   *  probability distribution.
   * @return a new instance of SoftmaxCrossEntropyWithLogits
   */
  public <T extends TNumber> SoftmaxCrossEntropyWithLogits<T> softmaxCrossEntropyWithLogits(
      Operand<T> features, Operand<T> labels) {
    return SoftmaxCrossEntropyWithLogits.create(scope, features, labels);
  }

  /**
   * Computes softmax cross entropy cost and gradients to backpropagate.
   *  <p>
   *  Unlike `SoftmaxCrossEntropyWithLogits`, this operation does not accept
   *  a matrix of label probabilities, but rather a single label per row
   *  of features.  This label is considered to have probability 1.0 for the
   *  given row.
   *  <p>
   *  Inputs are the logits, not probabilities.
   *
   * @param <T> data type for {@code loss()} output
   * @param features batch_size x num_classes matrix
   * @param labels batch_size vector with values in [0, num_classes).
   *  This is the label for the given minibatch entry.
   * @return a new instance of SparseSoftmaxCrossEntropyWithLogits
   */
  public <T extends TNumber, U extends TNumber> SparseSoftmaxCrossEntropyWithLogits<T> sparseSoftmaxCrossEntropyWithLogits(
      Operand<T> features, Operand<U> labels) {
    return SparseSoftmaxCrossEntropyWithLogits.create(scope, features, labels);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
