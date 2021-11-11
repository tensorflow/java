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

import java.util.Arrays;
import java.util.List;
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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Extract {@code patches} from {@code images} and put them in the &quot;depth&quot; output dimension.
 *
 * @param <T> data type for {@code patches} output
 */
@OpMetadata(
    opType = ExtractImagePatches.OP_NAME,
    inputsClass = ExtractImagePatches.Inputs.class
)
@Operator(
    group = "image"
)
public final class ExtractImagePatches<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExtractImagePatches";

  private Output<T> patches;

  public ExtractImagePatches(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    patches = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExtractImagePatches operation.
   *
   * @param scope current scope
   * @param images 4-D Tensor with shape {@code [batch, in_rows, in_cols, depth]}.
   * @param ksizes The size of the sliding window for each dimension of {@code images}.
   * @param strides How far the centers of two consecutive patches are in
   * the images. Must be: {@code [1, stride_rows, stride_cols, 1]}.
   * @param rates Must be: {@code [1, rate_rows, rate_cols, 1]}. This is the
   * input stride, specifying how far two consecutive patch samples are in the
   * input. Equivalent to extracting patches with
   * {@code patch_sizes_eff = patch_sizes + (patch_sizes - 1) * (rates - 1)}, followed by
   * subsampling them spatially by a factor of {@code rates}. This is equivalent to
   * {@code rate} in dilated (a.k.a. Atrous) convolutions.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code ExtractImagePatches} output and operands
   * @return a new instance of ExtractImagePatches
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ExtractImagePatches<T> create(Scope scope, Operand<T> images,
      List<Long> ksizes, List<Long> strides, List<Long> rates, String padding) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ExtractImagePatches");
    opBuilder.addInput(images.asOutput());
    long[] ksizesArray = new long[ksizes.size()];
    for (int i = 0 ; i < ksizesArray.length ; i++) {
      ksizesArray[i] = ksizes.get(i);
    }
    opBuilder.setAttr("ksizes", ksizesArray);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    long[] ratesArray = new long[rates.size()];
    for (int i = 0 ; i < ratesArray.length ; i++) {
      ratesArray[i] = rates.get(i);
    }
    opBuilder.setAttr("rates", ratesArray);
    opBuilder.setAttr("padding", padding);
    return new ExtractImagePatches<>(opBuilder.build());
  }

  /**
   * Gets patches.
   * 4-D Tensor with shape {@code [batch, out_rows, out_cols, ksize_rows * ksize_cols * depth]} containing image patches with size
   * {@code ksize_rows x ksize_cols x depth} vectorized in the &quot;depth&quot; dimension. Note
   * {@code out_rows} and {@code out_cols} are the dimensions of the output patches.
   * @return patches.
   */
  public Output<T> patches() {
    return patches;
  }

  @Override
  public Output<T> asOutput() {
    return patches;
  }

  @OpInputsMetadata(
      outputsClass = ExtractImagePatches.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ExtractImagePatches<T>> {
    /**
     * 4-D Tensor with shape {@code [batch, in_rows, in_cols, depth]}.
     */
    public final Operand<T> images;

    /**
     * The size of the sliding window for each dimension of `images`.
     */
    public final long[] ksizes;

    /**
     * How far the centers of two consecutive patches are in
     * the images. Must be: `[1, stride_rows, stride_cols, 1]`.
     */
    public final long[] strides;

    /**
     * Must be: `[1, rate_rows, rate_cols, 1]`. This is the
     * input stride, specifying how far two consecutive patch samples are in the
     * input. Equivalent to extracting patches with
     * `patch_sizes_eff = patch_sizes + (patch_sizes - 1) * (rates - 1)`, followed by
     * subsampling them spatially by a factor of `rates`. This is equivalent to
     * `rate` in dilated (a.k.a. Atrous) convolutions.
     */
    public final long[] rates;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    public Inputs(GraphOperation op) {
      super(new ExtractImagePatches<>(op), op, Arrays.asList("ksizes", "strides", "rates", "T", "padding"));
      int inputIndex = 0;
      images = (Operand<T>) op.input(inputIndex++);
      ksizes = op.attributes().getAttrIntList("ksizes");
      strides = op.attributes().getAttrIntList("strides");
      rates = op.attributes().getAttrIntList("rates");
      T = op.attributes().getAttrType("T");
      padding = op.attributes().getAttrString("padding");
    }
  }
}
