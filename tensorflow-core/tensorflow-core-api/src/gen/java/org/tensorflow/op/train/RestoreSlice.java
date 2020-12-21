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
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Restores a tensor from checkpoint files.
 * <p>
 * This is like `Restore` except that restored tensor can be listed as filling
 * only a slice of a larger tensor.  `shape_and_slice` specifies the shape of the
 * larger tensor and the slice that the restored tensor covers.
 * <p>
 * The `shape_and_slice` input has the same format as the
 * elements of the `shapes_and_slices` input of the `SaveSlices` op.
 * 
 * @param <T> data type for {@code tensor()} output
 */
@Operator(group = "train")
public final class RestoreSlice<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.RestoreSlice}
   */
  public static class Options {
    
    /**
     * @param preferredShard Index of file to open first if multiple files match
     * `file_pattern`. See the documentation for `Restore`.
     */
    public Options preferredShard(Long preferredShard) {
      this.preferredShard = preferredShard;
      return this;
    }
    
    private Long preferredShard;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new RestoreSlice operation.
   * 
   * @param scope current scope
   * @param filePattern Must have a single element. The pattern of the files from
   * which we read the tensor.
   * @param tensorName Must have a single element. The name of the tensor to be
   * restored.
   * @param shapeAndSlice Scalar. The shapes and slice specifications to use when
   * restoring a tensors.
   * @param dt The type of the tensor to be restored.
   * @param options carries optional attributes values
   * @return a new instance of RestoreSlice
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> RestoreSlice<T> create(Scope scope, Operand<TString> filePattern, Operand<TString> tensorName, Operand<TString> shapeAndSlice, Class<T> dt, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("RestoreSlice", scope.makeOpName("RestoreSlice"));
    opBuilder.addInput(filePattern.asOutput());
    opBuilder.addInput(tensorName.asOutput());
    opBuilder.addInput(shapeAndSlice.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dt", Operands.toDataType(dt));
    if (options != null) {
      for (Options opts : options) {
        if (opts.preferredShard != null) {
          opBuilder.setAttr("preferred_shard", opts.preferredShard);
        }
      }
    }
    return new RestoreSlice<T>(opBuilder.build());
  }
  
  /**
   * @param preferredShard Index of file to open first if multiple files match
   * `file_pattern`. See the documentation for `Restore`.
   */
  public static Options preferredShard(Long preferredShard) {
    return new Options().preferredShard(preferredShard);
  }
  
  /**
   * The restored tensor.
   */
  public Output<T> tensor() {
    return tensor;
  }
  
  @Override
  public Output<T> asOutput() {
    return tensor;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RestoreSlice";
  
  private Output<T> tensor;
  
  private RestoreSlice(Operation operation) {
    super(operation);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
  }
}
