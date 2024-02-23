/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.io;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * Generate a sharded filename. The filename is printf formatted as
 * %s-%05d-of-%05d, basename, shard, num_shards.
 */
@OpMetadata(
    opType = ShardedFilename.OP_NAME,
    inputsClass = ShardedFilename.Inputs.class
)
@Operator(
    group = "io"
)
public final class ShardedFilename extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ShardedFilename";

  private Output<TString> filename;

  public ShardedFilename(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    filename = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ShardedFilename operation.
   *
   * @param scope current scope
   * @param basename The basename value
   * @param shard The shard value
   * @param numShards The numShards value
   * @return a new instance of ShardedFilename
   */
  @Endpoint(
      describeByClass = true
  )
  public static ShardedFilename create(Scope scope, Operand<TString> basename,
      Operand<TInt32> shard, Operand<TInt32> numShards) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ShardedFilename");
    opBuilder.addInput(basename.asOutput());
    opBuilder.addInput(shard.asOutput());
    opBuilder.addInput(numShards.asOutput());
    return new ShardedFilename(opBuilder.build());
  }

  /**
   * Gets filename.
   *
   * @return filename.
   */
  public Output<TString> filename() {
    return filename;
  }

  @Override
  public Output<TString> asOutput() {
    return filename;
  }

  @OpInputsMetadata(
      outputsClass = ShardedFilename.class
  )
  public static class Inputs extends RawOpInputs<ShardedFilename> {
    /**
     * The basename input
     */
    public final Operand<TString> basename;

    /**
     * The shard input
     */
    public final Operand<TInt32> shard;

    /**
     * The numShards input
     */
    public final Operand<TInt32> numShards;

    public Inputs(GraphOperation op) {
      super(new ShardedFilename(op), op, Arrays.asList());
      int inputIndex = 0;
      basename = (Operand<TString>) op.input(inputIndex++);
      shard = (Operand<TInt32>) op.input(inputIndex++);
      numShards = (Operand<TInt32>) op.input(inputIndex++);
    }
  }
}
