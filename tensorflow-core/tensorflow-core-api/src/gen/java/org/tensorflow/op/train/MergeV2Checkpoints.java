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

package org.tensorflow.op.train;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * V2 format specific: merges the metadata files of sharded checkpoints.  The
 * result is one logical checkpoint, with one physical metadata file and renamed
 * data files.
 * <p>Intended for &quot;grouping&quot; multiple checkpoints in a sharded checkpoint setup.
 * <p>If delete_old_dirs is true, attempts to delete recursively the dirname of each
 * path in the input checkpoint_prefixes.  This is useful when those paths are non
 * user-facing temporary locations.
 */
@OpMetadata(
    opType = MergeV2Checkpoints.OP_NAME,
    inputsClass = MergeV2Checkpoints.Inputs.class
)
@Operator(
    group = "train"
)
public final class MergeV2Checkpoints extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MergeV2Checkpoints";

  public MergeV2Checkpoints(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new MergeV2Checkpoints operation.
   *
   * @param scope current scope
   * @param checkpointPrefixes prefixes of V2 checkpoints to merge.
   * @param destinationPrefix scalar.  The desired final prefix.  Allowed to be the same
   * as one of the checkpoint_prefixes.
   * @param options carries optional attribute values
   * @return a new instance of MergeV2Checkpoints
   */
  @Endpoint(
      describeByClass = true
  )
  public static MergeV2Checkpoints create(Scope scope, Operand<TString> checkpointPrefixes,
      Operand<TString> destinationPrefix, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MergeV2Checkpoints");
    opBuilder.addInput(checkpointPrefixes.asOutput());
    opBuilder.addInput(destinationPrefix.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.deleteOldDirs != null) {
          opBuilder.setAttr("delete_old_dirs", opts.deleteOldDirs);
        }
      }
    }
    return new MergeV2Checkpoints(opBuilder.build());
  }

  /**
   * Sets the deleteOldDirs option.
   *
   * @param deleteOldDirs see above.
   * @return this Options instance.
   */
  public static Options deleteOldDirs(Boolean deleteOldDirs) {
    return new Options().deleteOldDirs(deleteOldDirs);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.MergeV2Checkpoints}
   */
  public static class Options {
    private Boolean deleteOldDirs;

    private Options() {
    }

    /**
     * Sets the deleteOldDirs option.
     *
     * @param deleteOldDirs see above.
     * @return this Options instance.
     */
    public Options deleteOldDirs(Boolean deleteOldDirs) {
      this.deleteOldDirs = deleteOldDirs;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = MergeV2Checkpoints.class
  )
  public static class Inputs extends RawOpInputs<MergeV2Checkpoints> {
    /**
     * prefixes of V2 checkpoints to merge.
     */
    public final Operand<TString> checkpointPrefixes;

    /**
     * scalar.  The desired final prefix.  Allowed to be the same
     * as one of the checkpoint_prefixes.
     */
    public final Operand<TString> destinationPrefix;

    /**
     * see above.
     */
    public final boolean deleteOldDirs;

    public Inputs(GraphOperation op) {
      super(new MergeV2Checkpoints(op), op, Arrays.asList("delete_old_dirs"));
      int inputIndex = 0;
      checkpointPrefixes = (Operand<TString>) op.input(inputIndex++);
      destinationPrefix = (Operand<TString>) op.input(inputIndex++);
      deleteOldDirs = op.attributes().getAttrBool("delete_old_dirs");
    }
  }
}
