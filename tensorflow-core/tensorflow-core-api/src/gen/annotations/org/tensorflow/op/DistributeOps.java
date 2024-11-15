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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.distribute.NcclAllReduce;
import org.tensorflow.op.distribute.NcclBroadcast;
import org.tensorflow.op.distribute.NcclReduce;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code distribute} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class DistributeOps {
  private final Scope scope;

  private final Ops ops;

  DistributeOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Outputs a tensor containing the reduction across all input tensors.
   *  Outputs a tensor containing the reduction across all input tensors passed to ops
   *  within the same `shared_name.
   *  <p>The graph should be constructed so if one op runs with shared_name value {@code c},
   *  then {@code num_devices} ops will run with shared_name value {@code c}.  Failure to do so
   *  will cause the graph execution to fail to complete.
   *  <p>input: the input to the reduction
   *  data: the value of the reduction across all {@code num_devices} devices.
   *  reduction: the reduction operation to perform.
   *  num_devices: The number of devices participating in this reduction.
   *  shared_name: Identifier that shared between ops of the same reduction.
   *
   * @param input The input value
   * @param reduction The value of the reduction attribute
   * @param numDevices The value of the numDevices attribute
   * @param sharedName The value of the sharedName attribute
   * @param <T> data type for {@code NcclAllReduce} output and operands
   * @return a new instance of NcclAllReduce
   */
  public <T extends TNumber> NcclAllReduce<T> ncclAllReduce(Operand<T> input, String reduction,
      Long numDevices, String sharedName) {
    return NcclAllReduce.create(scope, input, reduction, numDevices, sharedName);
  }

  /**
   * Sends {@code input} to all devices that are connected to the output.
   *  Sends {@code input} to all devices that are connected to the output.
   *  <p>The graph should be constructed so that all ops connected to the output have a
   *  valid device assignment, and the op itself is assigned one of these devices.
   *  <p>input: The input to the broadcast.
   *  output: The same as input.
   *  shape: The shape of the input tensor.
   *
   * @param input The input value
   * @param shape The value of the shape attribute
   * @param <T> data type for {@code NcclBroadcast} output and operands
   * @return a new instance of NcclBroadcast
   */
  public <T extends TNumber> NcclBroadcast<T> ncclBroadcast(Operand<T> input, Shape shape) {
    return NcclBroadcast.create(scope, input, shape);
  }

  /**
   * Reduces {@code input} from {@code num_devices} using {@code reduction} to a single device.
   *  Reduces {@code input} from {@code num_devices} using {@code reduction} to a single device.
   *  <p>The graph should be constructed so that all inputs have a valid device
   *  assignment, and the op itself is assigned one of these devices.
   *  <p>input: The input to the reduction.
   *  data: the value of the reduction across all {@code num_devices} devices.
   *  reduction: the reduction operation to perform.
   *
   * @param input The input value
   * @param reduction The value of the reduction attribute
   * @param <T> data type for {@code NcclReduce} output and operands
   * @return a new instance of NcclReduce
   */
  public <T extends TNumber> NcclReduce<T> ncclReduce(Iterable<Operand<T>> input,
      String reduction) {
    return NcclReduce.create(scope, input, reduction);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
