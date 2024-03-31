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
import org.tensorflow.op.collective.CollectiveAllToAll;
import org.tensorflow.op.collective.CollectiveAssignGroup;
import org.tensorflow.op.collective.CollectiveBcastRecv;
import org.tensorflow.op.collective.CollectiveBcastSend;
import org.tensorflow.op.collective.CollectiveGather;
import org.tensorflow.op.collective.CollectiveInitializeCommunicator;
import org.tensorflow.op.collective.CollectivePermute;
import org.tensorflow.op.collective.CollectiveReduce;
import org.tensorflow.op.collective.CollectiveReduceScatter;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code collective} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class CollectiveOps {
  private final Scope scope;

  private final Ops ops;

  CollectiveOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Mutually exchanges multiple tensors of identical type and shape.
   *
   * @param <T> data type for {@code data} output
   * @param input The input value
   * @param communicator The communicator value
   * @param groupAssignment The groupAssignment value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveAllToAllV3} output and operands
   * @return a new instance of CollectiveAllToAll
   */
  public <T extends TNumber> CollectiveAllToAll<T> collectiveAllToAll(Operand<T> input,
      Operand<? extends TType> communicator, Operand<TInt32> groupAssignment,
      CollectiveAllToAll.Options... options) {
    return CollectiveAllToAll.create(scope, input, communicator, groupAssignment, options);
  }

  /**
   * Assign group keys based on group assignment.
   *
   * @param groupAssignment The groupAssignment value
   * @param deviceIndex The deviceIndex value
   * @param baseKey The baseKey value
   * @return a new instance of CollectiveAssignGroup
   */
  public CollectiveAssignGroup collectiveAssignGroup(Operand<TInt32> groupAssignment,
      Operand<TInt32> deviceIndex, Operand<TInt32> baseKey) {
    return CollectiveAssignGroup.create(scope, groupAssignment, deviceIndex, baseKey);
  }

  /**
   * Receives a tensor value broadcast from another device.
   *
   * @param <U> data type for {@code data} output
   * @param groupSize The groupSize value
   * @param groupKey The groupKey value
   * @param instanceKey The instanceKey value
   * @param shape The shape value
   * @param T The value of the T attribute
   * @param options carries optional attribute values
   * @param <U> data type for {@code CollectiveBcastRecvV2} output and operands
   * @return a new instance of CollectiveBcastRecv
   */
  public <U extends TType> CollectiveBcastRecv<U> collectiveBcastRecv(Operand<TInt32> groupSize,
      Operand<TInt32> groupKey, Operand<TInt32> instanceKey, Operand<? extends TNumber> shape,
      Class<U> T, CollectiveBcastRecv.Options... options) {
    return CollectiveBcastRecv.create(scope, groupSize, groupKey, instanceKey, shape, T, options);
  }

  /**
   * Broadcasts a tensor value to one or more other devices.
   *
   * @param <T> data type for {@code data} output
   * @param input The input value
   * @param groupSize The groupSize value
   * @param groupKey The groupKey value
   * @param instanceKey The instanceKey value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveBcastSendV2} output and operands
   * @return a new instance of CollectiveBcastSend
   */
  public <T extends TType> CollectiveBcastSend<T> collectiveBcastSend(Operand<T> input,
      Operand<TInt32> groupSize, Operand<TInt32> groupKey, Operand<TInt32> instanceKey,
      CollectiveBcastSend.Options... options) {
    return CollectiveBcastSend.create(scope, input, groupSize, groupKey, instanceKey, options);
  }

  /**
   * Mutually accumulates multiple tensors of identical type and shape.
   *  {@code is_stateless} means each op does not need control dependencies to other
   *  collective ops. In this case, keys that are unique at runtime
   *  (e.g. {@code instance_key}) should be used to distinguish collective groups.
   *
   * @param <T> data type for {@code data} output
   * @param input The input value
   * @param groupSize The groupSize value
   * @param groupKey The groupKey value
   * @param instanceKey The instanceKey value
   * @param orderingToken The orderingToken value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveGatherV2} output and operands
   * @return a new instance of CollectiveGather
   */
  public <T extends TNumber> CollectiveGather<T> collectiveGather(Operand<T> input,
      Operand<TInt32> groupSize, Operand<TInt32> groupKey, Operand<TInt32> instanceKey,
      Iterable<Operand<? extends TType>> orderingToken, CollectiveGather.Options... options) {
    return CollectiveGather.create(scope, input, groupSize, groupKey, instanceKey, orderingToken, options);
  }

  /**
   * Initializes a group for collective operations.
   *
   * @param groupKey The groupKey value
   * @param rank The rank value
   * @param groupSize The groupSize value
   * @param options carries optional attribute values
   * @return a new instance of CollectiveInitializeCommunicator
   */
  public CollectiveInitializeCommunicator collectiveInitializeCommunicator(Operand<TInt32> groupKey,
      Operand<TInt32> rank, Operand<TInt32> groupSize,
      CollectiveInitializeCommunicator.Options... options) {
    return CollectiveInitializeCommunicator.create(scope, groupKey, rank, groupSize, options);
  }

  /**
   * An Op to permute tensors across replicated TPU instances.
   *  Each instance supplies its own input.
   *  <p>For example, suppose there are 4 TPU instances: {@code [A, B, C, D]}. Passing
   *  source_target_pairs={@code [[0,1],[1,2],[2,3],[3,0]]} gets the outputs:
   *  {@code [D, A, B, C]}.
   *
   * @param <T> data type for {@code output} output
   * @param input The local input to be permuted. Currently only supports float and
   *  bfloat16.
   * @param sourceTargetPairs A tensor with shape [num_pairs, 2].
   * @param <T> data type for {@code CollectivePermute} output and operands
   * @return a new instance of CollectivePermute
   */
  public <T extends TType> CollectivePermute<T> collectivePermute(Operand<T> input,
      Operand<TInt32> sourceTargetPairs) {
    return CollectivePermute.create(scope, input, sourceTargetPairs);
  }

  /**
   * Mutually reduces multiple tensors of identical type and shape.
   *
   * @param <T> data type for {@code data} output
   * @param input The input value
   * @param communicator The communicator value
   * @param groupAssignment The groupAssignment value
   * @param reduction The value of the reduction attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveReduceV3} output and operands
   * @return a new instance of CollectiveReduce
   */
  public <T extends TNumber> CollectiveReduce<T> collectiveReduce(Operand<T> input,
      Operand<? extends TType> communicator, Operand<TInt32> groupAssignment, String reduction,
      CollectiveReduce.Options... options) {
    return CollectiveReduce.create(scope, input, communicator, groupAssignment, reduction, options);
  }

  /**
   * Mutually reduces multiple tensors of identical type and shape and scatters the result.
   *  {@code is_stateless} means each op does not need control dependencies to other
   *  collective ops. In this case, keys that are unique at runtime
   *  (e.g. {@code instance_key}) should be used to distinguish collective groups.
   *
   * @param <T> data type for {@code data} output
   * @param input The input value
   * @param groupSize The groupSize value
   * @param groupKey The groupKey value
   * @param instanceKey The instanceKey value
   * @param orderingToken The orderingToken value
   * @param mergeOp The value of the mergeOp attribute
   * @param finalOp The value of the finalOp attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveReduceScatterV2} output and operands
   * @return a new instance of CollectiveReduceScatter
   */
  public <T extends TNumber> CollectiveReduceScatter<T> collectiveReduceScatter(Operand<T> input,
      Operand<TInt32> groupSize, Operand<TInt32> groupKey, Operand<TInt32> instanceKey,
      Iterable<Operand<? extends TType>> orderingToken, String mergeOp, String finalOp,
      CollectiveReduceScatter.Options... options) {
    return CollectiveReduceScatter.create(scope, input, groupSize, groupKey, instanceKey, orderingToken, mergeOp, finalOp, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
