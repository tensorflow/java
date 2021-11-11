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
import org.tensorflow.types.family.TNumber;

/**
 * Extract {@code patches} from {@code input} and put them in the {@code "depth"} output dimension. 3D extension of {@code extract_image_patches}.
 *
 * @param <T> data type for {@code patches} output
 */
@OpMetadata(
    opType = ExtractVolumePatches.OP_NAME,
    inputsClass = ExtractVolumePatches.Inputs.class
)
@Operator
public final class ExtractVolumePatches<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExtractVolumePatches";

  private Output<T> patches;

  public ExtractVolumePatches(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    patches = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExtractVolumePatches operation.
   *
   * @param scope current scope
   * @param input 5-D Tensor with shape {@code [batch, in_planes, in_rows, in_cols, depth]}.
   * @param ksizes The size of the sliding window for each dimension of {@code input}.
   * @param strides 1-D of length 5. How far the centers of two consecutive patches are in
   * {@code input}. Must be: {@code [1, stride_planes, stride_rows, stride_cols, 1]}.
   * @param padding The type of padding algorithm to use.
   * <p>The size-related attributes are specified as follows:
   * <pre>
   * ksizes = [1, ksize_planes, ksize_rows, ksize_cols, 1]
   * strides = [1, stride_planes, strides_rows, strides_cols, 1]
   * </pre>
   * @param <T> data type for {@code ExtractVolumePatches} output and operands
   * @return a new instance of ExtractVolumePatches
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ExtractVolumePatches<T> create(Scope scope, Operand<T> input,
      List<Long> ksizes, List<Long> strides, String padding) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ExtractVolumePatches");
    opBuilder.addInput(input.asOutput());
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
    opBuilder.setAttr("padding", padding);
    return new ExtractVolumePatches<>(opBuilder.build());
  }

  /**
   * Gets patches.
   * 5-D Tensor with shape {@code [batch, out_planes, out_rows, out_cols, ksize_planes * ksize_rows * ksize_cols * depth]} containing patches
   * with size {@code ksize_planes x ksize_rows x ksize_cols x depth} vectorized
   * in the &quot;depth&quot; dimension. Note {@code out_planes}, {@code out_rows} and {@code out_cols}
   * are the dimensions of the output patches.
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
      outputsClass = ExtractVolumePatches.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<ExtractVolumePatches<T>> {
    /**
     * 5-D Tensor with shape {@code [batch, in_planes, in_rows, in_cols, depth]}.
     */
    public final Operand<T> input;

    /**
     * The size of the sliding window for each dimension of `input`.
     */
    public final long[] ksizes;

    /**
     * 1-D of length 5. How far the centers of two consecutive patches are in
     * `input`. Must be: `[1, stride_planes, stride_rows, stride_cols, 1]`.
     */
    public final long[] strides;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The type of padding algorithm to use.
     *
     * The size-related attributes are specified as follows:
     *
     * ```python
     * ksizes = [1, ksize_planes, ksize_rows, ksize_cols, 1]
     * strides = [1, stride_planes, strides_rows, strides_cols, 1]
     * ```
     */
    public final String padding;

    public Inputs(GraphOperation op) {
      super(new ExtractVolumePatches<>(op), op, Arrays.asList("ksizes", "strides", "T", "padding"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      ksizes = op.attributes().getAttrIntList("ksizes");
      strides = op.attributes().getAttrIntList("strides");
      T = op.attributes().getAttrType("T");
      padding = op.attributes().getAttrString("padding");
    }
  }
}
