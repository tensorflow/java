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

package org.tensorflow.op.image;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Extract `patches` from `images` and put them in the "depth" output dimension.
 * 
 * @param <T> data type for {@code patches()} output
 */
@Operator(group = "image")
public final class ExtractImagePatches<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ExtractImagePatches operation.
   * 
   * @param scope current scope
   * @param images 4-D Tensor with shape `[batch, in_rows, in_cols, depth]`.
   * @param ksizes The size of the sliding window for each dimension of `images`.
   * @param strides How far the centers of two consecutive patches are in
   * the images. Must be: `[1, stride_rows, stride_cols, 1]`.
   * @param rates Must be: `[1, rate_rows, rate_cols, 1]`. This is the
   * input stride, specifying how far two consecutive patch samples are in the
   * input. Equivalent to extracting patches with
   * `patch_sizes_eff = patch_sizes + (patch_sizes - 1) * (rates - 1)`, followed by
   * subsampling them spatially by a factor of `rates`. This is equivalent to
   * `rate` in dilated (a.k.a. Atrous) convolutions.
   * @param padding The type of padding algorithm to use.
   * @return a new instance of ExtractImagePatches
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ExtractImagePatches<T> create(Scope scope, Operand<T> images, List<Long> ksizes, List<Long> strides, List<Long> rates, String padding) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExtractImagePatches", scope.makeOpName("ExtractImagePatches"));
    opBuilder.addInput(images.asOutput());
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
    long[] ratesArray = new long[rates.size()];
    for (int i = 0; i < ratesArray.length; ++i) {
      ratesArray[i] = rates.get(i);
    }
    opBuilder.setAttr("rates", ratesArray);
    opBuilder.setAttr("padding", padding);
    return new ExtractImagePatches<T>(opBuilder.build());
  }
  
  /**
   * 4-D Tensor with shape `[batch, out_rows, out_cols, ksize_rows *
   * ksize_cols * depth]` containing image patches with size
   * `ksize_rows x ksize_cols x depth` vectorized in the "depth" dimension. Note
   * `out_rows` and `out_cols` are the dimensions of the output patches.
   */
  public Output<T> patches() {
    return patches;
  }
  
  @Override
  public Output<T> asOutput() {
    return patches;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ExtractImagePatches";
  
  private Output<T> patches;
  
  private ExtractImagePatches(Operation operation) {
    super(operation);
    int outputIdx = 0;
    patches = operation.output(outputIdx++);
  }
}
