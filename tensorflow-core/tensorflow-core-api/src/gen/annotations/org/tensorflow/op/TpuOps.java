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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.op.tpu.CompileSucceededAssert;
import org.tensorflow.op.tpu.Execute;
import org.tensorflow.op.tpu.ExecuteAndUpdateVariables;
import org.tensorflow.op.tpu.PartitionedInput;
import org.tensorflow.op.tpu.PartitionedOutput;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code tpu} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class TpuOps {
  private final Scope scope;

  private final Ops ops;

  TpuOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Asserts that compilation succeeded. This op produces no output and closes the
   *  device during failure to ensure all pending device interactions fail.
   *  <p>'compilation_status' is a serialized CompilationResultProto.
   *
   * @param compilationStatus the compilationStatus value
   * @return a new instance of CompileSucceededAssert
   */
  public CompileSucceededAssert compileSucceededAssert(Operand<TString> compilationStatus) {
    return CompileSucceededAssert.create(scope, compilationStatus);
  }

  /**
   * Op that loads and executes a TPU program on a TPU device.
   *  For the internal use of the distributed TPU compiler.
   *
   * @param args the args value
   * @param key the key value
   * @param Tresults the value of the Tresults property
   * @return a new instance of Execute
   */
  public Execute execute(Iterable<Operand<?>> args, Operand<TString> key,
      List<Class<? extends TType>> Tresults) {
    return Execute.create(scope, args, key, Tresults);
  }

  /**
   * Op that executes a program with optional in-place variable updates.
   *  It (optionally) reads device variables, loads and executes a TPU program on a
   *  TPU device, and then (optionally) in-place updates variables using the program
   *  outputs, as specified in attributes device_var_reads_indices (program input
   *  indices from directly reading variables) and device_var_updates_indices (program
   *  output indices used to update variables, -1 means no-update/read-only). Such
   *  program outputs are consumed by these variables will not appear in the op
   *  output. For the internal use of the distributed TPU compiler.
   *
   * @param args the args value
   * @param key the key value
   * @param Tresults the value of the Tresults property
   * @param deviceVarReadsIndices the value of the deviceVarReadsIndices property
   * @param deviceVarUpdatesIndices the value of the deviceVarUpdatesIndices property
   * @return a new instance of ExecuteAndUpdateVariables
   */
  public ExecuteAndUpdateVariables executeAndUpdateVariables(Iterable<Operand<?>> args,
      Operand<TString> key, List<Class<? extends TType>> Tresults, List<Long> deviceVarReadsIndices,
      List<Long> deviceVarUpdatesIndices) {
    return ExecuteAndUpdateVariables.create(scope, args, key, Tresults, deviceVarReadsIndices, deviceVarUpdatesIndices);
  }

  /**
   * An op that groups a list of partitioned inputs together. This op
   *
   * @param <T> data type for {@code output} output
   * @param inputs A list of partitioned inputs which must have the same shape.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUPartitionedInput} output and operands
   * @return a new instance of PartitionedInput
   */
  public <T extends TType> PartitionedInput<T> partitionedInput(Iterable<Operand<T>> inputs,
      PartitionedInput.Options... options) {
    return PartitionedInput.create(scope, inputs, options);
  }

  /**
   * An op that demultiplexes a tensor to be sharded by XLA to a list of partitioned
   *  outputs outside the XLA computation.
   *
   * @param <T> data type for {@code output} output
   * @param inputs A tensor which represents the full shape of partitioned tensors.
   * @param numSplits the value of the numSplits property
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUPartitionedOutput} output and operands
   * @return a new instance of PartitionedOutput
   */
  public <T extends TType> PartitionedOutput<T> partitionedOutput(Operand<T> inputs, Long numSplits,
      PartitionedOutput.Options... options) {
    return PartitionedOutput.create(scope, inputs, numSplits, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
