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

package org.tensorflow.op.nn;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradients of the maxpooling function.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = MaxPoolGradWithArgmax.OP_NAME,
    inputsClass = MaxPoolGradWithArgmax.Inputs.class
)
public final class MaxPoolGradWithArgmax<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MaxPoolGradWithArgmax";

  private Output<T> output;

  public MaxPoolGradWithArgmax(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MaxPoolGradWithArgmax operation.
   *
   * @param scope current scope
   * @param input The original input.
   * @param grad 4-D with shape {@code [batch, height, width, channels]}.  Gradients w.r.t. the
   * output of {@code max_pool}.
   * @param argmax The indices of the maximum values chosen for each output of {@code max_pool}.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolGradWithArgmax} output and operands
   * @return a new instance of MaxPoolGradWithArgmax
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> MaxPoolGradWithArgmax<T> create(Scope scope, Operand<T> input,
      Operand<T> grad, Operand<? extends TNumber> argmax, List<Long> ksize, List<Long> strides,
      String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MaxPoolGradWithArgmax");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(argmax.asOutput());
    long[] ksizeArray = new long[ksize.size()];
    for (int i = 0 ; i < ksizeArray.length ; i++) {
      ksizeArray[i] = ksize.get(i);
    }
    opBuilder.setAttr("ksize", ksizeArray);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.includeBatchInIndex != null) {
          opBuilder.setAttr("include_batch_in_index", opts.includeBatchInIndex);
        }
      }
    }
    return new MaxPoolGradWithArgmax<>(opBuilder.build());
  }

  /**
   * Sets the includeBatchInIndex option.
   *
   * @param includeBatchInIndex Whether to include batch dimension in flattened index of {@code argmax}.
   * @return this Options instance.
   */
  public static Options includeBatchInIndex(Boolean includeBatchInIndex) {
    return new Options().includeBatchInIndex(includeBatchInIndex);
  }

  /**
   * Gets output.
   * Gradients w.r.t. the input of {@code max_pool}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.MaxPoolGradWithArgmax}
   */
  public static class Options {
    private Boolean includeBatchInIndex;

    private Options() {
    }

    /**
     * Sets the includeBatchInIndex option.
     *
     * @param includeBatchInIndex Whether to include batch dimension in flattened index of {@code argmax}.
     * @return this Options instance.
     */
    public Options includeBatchInIndex(Boolean includeBatchInIndex) {
      this.includeBatchInIndex = includeBatchInIndex;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = MaxPoolGradWithArgmax.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<MaxPoolGradWithArgmax<T>> {
    /**
     * The original input.
     */
    public final Operand<T> input;

    /**
     * 4-D with shape {@code [batch, height, width, channels]}.  Gradients w.r.t. the
     * output of {@code max_pool}.
     */
    public final Operand<T> grad;

    /**
     * The indices of the maximum values chosen for each output of {@code max_pool}.
     */
    public final Operand<? extends TNumber> argmax;

    /**
     * The size of the window for each dimension of the input tensor.
     */
    public final long[] ksize;

    /**
     * The stride of the sliding window for each dimension of the
     * input tensor.
     */
    public final long[] strides;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    /**
     * Whether to include batch dimension in flattened index of `argmax`.
     */
    public final boolean includeBatchInIndex;

    /**
     * The Targmax attribute
     */
    public final DataType Targmax;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new MaxPoolGradWithArgmax<>(op), op, Arrays.asList("ksize", "strides", "padding", "include_batch_in_index", "Targmax", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      argmax = (Operand<? extends TNumber>) op.input(inputIndex++);
      ksize = op.attributes().getAttrIntList("ksize");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      includeBatchInIndex = op.attributes().getAttrBool("include_batch_in_index");
      Targmax = op.attributes().getAttrType("Targmax");
      T = op.attributes().getAttrType("T");
    }
  }
}
