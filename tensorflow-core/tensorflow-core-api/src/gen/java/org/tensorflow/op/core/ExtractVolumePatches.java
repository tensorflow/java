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

package org.tensorflow.op.core;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Extract `patches` from `input` and put them in the "depth" output dimension. 3D extension of `extract_image_patches`.
 * 
 * @param <T> data type for {@code patches()} output
 */
@Operator
public final class ExtractVolumePatches<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ExtractVolumePatches operation.
   * 
   * @param scope current scope
   * @param input 5-D Tensor with shape `[batch, in_planes, in_rows, in_cols, depth]`.
   * @param ksizes The size of the sliding window for each dimension of `input`.
   * @param strides 1-D of length 5. How far the centers of two consecutive patches are in
   * `input`. Must be: `[1, stride_planes, stride_rows, stride_cols, 1]`.
   * @param padding The type of padding algorithm to use.
   * <p>
   * We specify the size-related attributes as:
   * <pre>{@code
   *       ksizes = [1, ksize_planes, ksize_rows, ksize_cols, 1]
   *       strides = [1, stride_planes, strides_rows, strides_cols, 1]
   * }</pre>
   * 
   * @return a new instance of ExtractVolumePatches
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> ExtractVolumePatches<T> create(Scope scope, Operand<T> input, List<Long> ksizes, List<Long> strides, String padding) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExtractVolumePatches", scope.makeOpName("ExtractVolumePatches"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    long[] ksizesArray = new long[ksizes.size()];
    for (int i = 0; i < ksizesArray.length; ++i) {
      ksizesArray[i] = ksizes.get(i);
    }
    opBuilder.setAttr("ksizes", ksizesArray);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    return new ExtractVolumePatches<T>(opBuilder.build());
  }
  
  /**
   * 5-D Tensor with shape `[batch, out_planes, out_rows, out_cols,
   * ksize_planes * ksize_rows * ksize_cols * depth]` containing patches
   * with size `ksize_planes x ksize_rows x ksize_cols x depth` vectorized
   * in the "depth" dimension. Note `out_planes`, `out_rows` and `out_cols`
   * are the dimensions of the output patches.
   */
  public Output<T> patches() {
    return patches;
  }
  
  @Override
  public Output<T> asOutput() {
    return patches;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ExtractVolumePatches";
  
  private Output<T> patches;
  
  private ExtractVolumePatches(Operation operation) {
    super(operation);
    int outputIdx = 0;
    patches = operation.output(outputIdx++);
  }
}
