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
package org.tensorflow.op.kotlin

import kotlin.Long
import kotlin.String
import org.tensorflow.ConcreteFunction
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.tpu.Compile
import org.tensorflow.op.tpu.CompileSucceededAssert
import org.tensorflow.op.tpu.Execute
import org.tensorflow.op.tpu.ExecuteAndUpdateVariables
import org.tensorflow.op.tpu.PartitionedInput
import org.tensorflow.op.tpu.PartitionedOutput
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TType

/**
 * An API for building `tpu` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class TpuOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.TpuOps = ops.java.tpu

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Compiles a computations for execution on one or more TPU devices.
     *  For the internal use of the distributed TPU compiler.
     *  
     * 'num_computations' is the number of computations to be compiled.
     *  'function' is a function containing the computation to compile.
     *  'dynamic_shapes' contains dynamic shapes of arguments whose shapes were not
     *  known statically at TPUReplication rewrite time.
     *  'guaranteed_constants' is a list of tensors which have been guaranteed to not
     *  change their values during the session lifetime. These contain tensors marked as
     *  constant using the GuaranteeConstOp.
     *  'metadata' is a serialized TPUCompileMetadataProto describing
     *  the shapes and types of the inputs to the computation, as well as a mapping onto
     *  the TPU pod topology.
     *  Each 'program' output is a string key that is passed to the _TPUExecute op and
     *  used to look up the program in the compilation cache.
     *  'may_modify_variables' indicates whether variables may be modified.
     *
     * @param dynamicShapes The dynamicShapes value
     * @param guaranteedConstants The guaranteedConstants value
     * @param numComputations The value of the numComputations attribute
     * @param function The value of the function attribute
     * @param metadata The value of the metadata attribute
     * @return a new instance of Compile
     * @see org.tensorflow.op.TpuOps.compile
     */
    public fun compile(
        dynamicShapes: Iterable<Operand<TInt64>>,
        guaranteedConstants: Iterable<Operand<*>>,
        numComputations: Long,
        function: ConcreteFunction,
        metadata: String
    ): Compile = java.compile(    
        dynamicShapes,
        guaranteedConstants,
        numComputations,
        function,
        metadata
        )

    /**
     * Asserts that compilation succeeded.
     *  This op produces no output and closes the device during failure to ensure all
     *  pending device interactions fail.
     *  
     * 'compilation_status' is a serialized CompilationResultProto.
     *
     * @param compilationStatus The compilationStatus value
     * @return a new instance of CompileSucceededAssert
     * @see org.tensorflow.op.TpuOps.compileSucceededAssert
     */
    public fun compileSucceededAssert(compilationStatus: Operand<TString>): CompileSucceededAssert =
            java.compileSucceededAssert(    
        compilationStatus
        )

    /**
     * Op that loads and executes a TPU program on a TPU device.
     *  For the internal use of the distributed TPU compiler.
     *
     * @param args The args value
     * @param key The key value
     * @param Tresults The value of the Tresults attribute
     * @return a new instance of Execute
     * @see org.tensorflow.op.TpuOps.execute
     */
    public fun execute(
        args: Iterable<Operand<*>>,
        key: Operand<TString>,
        Tresults: List<Class<out TType>>
    ): Execute = java.execute(    
        args,
        key,
        Tresults
        )

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
     * @param args The args value
     * @param key The key value
     * @param Tresults The value of the Tresults attribute
     * @param deviceVarReadsIndices The value of the deviceVarReadsIndices attribute
     * @param deviceVarUpdatesIndices The value of the deviceVarUpdatesIndices attribute
     * @return a new instance of ExecuteAndUpdateVariables
     * @see org.tensorflow.op.TpuOps.executeAndUpdateVariables
     */
    public fun executeAndUpdateVariables(
        args: Iterable<Operand<*>>,
        key: Operand<TString>,
        Tresults: List<Class<out TType>>,
        deviceVarReadsIndices: List<Long>,
        deviceVarUpdatesIndices: List<Long>
    ): ExecuteAndUpdateVariables = java.executeAndUpdateVariables(    
        args,
        key,
        Tresults,
        deviceVarReadsIndices,
        deviceVarUpdatesIndices
        )

    /**
     * An op that groups a list of partitioned inputs together. This op
     *
     * @param <T> data type for `output` output
     * @param inputs A list of partitioned inputs which must have the same shape.
     * @param options carries optional attribute values
     * @param <T> data type for `TPUPartitionedInput` output and operands
     * @return a new instance of PartitionedInput
     * @see org.tensorflow.op.TpuOps.partitionedInput
     * @param partitionDim Sets the partitionDim option.
     *
     * @param partitionDim An integer describles which dimension is partitioned. -1 means
     *  those inputs are replicated.
     * @return this Options instance.
     */
    public fun <T : TType> partitionedInput(inputs: Iterable<Operand<T>>, partitionDim: Long? =
            null): PartitionedInput<T> = java.partitionedInput<T>(    
        inputs,
        *listOfNotNull(
            partitionDim?.let{ org.tensorflow.op.tpu.PartitionedInput.partitionDim(it) }
        ).toTypedArray()
        )

    /**
     * An op that demultiplexes a tensor to be sharded by XLA to a list of partitioned
     *  outputs outside the XLA computation.
     *
     * @param <T> data type for `output` output
     * @param inputs A tensor which represents the full shape of partitioned tensors.
     * @param numSplits The value of the numSplits attribute
     * @param options carries optional attribute values
     * @param <T> data type for `TPUPartitionedOutput` output and operands
     * @return a new instance of PartitionedOutput
     * @see org.tensorflow.op.TpuOps.partitionedOutput
     * @param partitionDim Sets the partitionDim option.
     *
     * @param partitionDim An integer describles which dimension is partitioned.
     * @return this Options instance.
     */
    public fun <T : TType> partitionedOutput(
        inputs: Operand<T>,
        numSplits: Long,
        partitionDim: Long? = null
    ): PartitionedOutput<T> = java.partitionedOutput<T>(    
        inputs,
        numSplits,
        *listOfNotNull(
            partitionDim?.let{ org.tensorflow.op.tpu.PartitionedOutput.partitionDim(it) }
        ).toTypedArray()
        )
}
