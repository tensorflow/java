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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * V2 format specific: merges the metadata files of sharded checkpoints.  The
 * <p>
 * result is one logical checkpoint, with one physical metadata file and renamed
 * data files.
 * <p>
 * Intended for "grouping" multiple checkpoints in a sharded checkpoint setup.
 * <p>
 * If delete_old_dirs is true, attempts to delete recursively the dirname of each
 * path in the input checkpoint_prefixes.  This is useful when those paths are non
 * user-facing temporary locations.
 */
@Operator(group = "train")
public final class MergeV2Checkpoints extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.MergeV2Checkpoints}
   */
  public static class Options {
    
    /**
     * @param deleteOldDirs see above.
     */
    public Options deleteOldDirs(Boolean deleteOldDirs) {
      this.deleteOldDirs = deleteOldDirs;
      return this;
    }
    
    private Boolean deleteOldDirs;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new MergeV2Checkpoints operation.
   * 
   * @param scope current scope
   * @param checkpointPrefixes prefixes of V2 checkpoints to merge.
   * @param destinationPrefix scalar.  The desired final prefix.  Allowed to be the same
   * as one of the checkpoint_prefixes.
   * @param options carries optional attributes values
   * @return a new instance of MergeV2Checkpoints
   */
  @Endpoint(describeByClass = true)
  public static MergeV2Checkpoints create(Scope scope, Operand<TString> checkpointPrefixes, Operand<TString> destinationPrefix, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MergeV2Checkpoints", scope.makeOpName("MergeV2Checkpoints"));
    opBuilder.addInput(checkpointPrefixes.asOutput());
    opBuilder.addInput(destinationPrefix.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param deleteOldDirs see above.
   */
  public static Options deleteOldDirs(Boolean deleteOldDirs) {
    return new Options().deleteOldDirs(deleteOldDirs);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MergeV2Checkpoints";
  
  private MergeV2Checkpoints(Operation operation) {
    super(operation);
  }
}
