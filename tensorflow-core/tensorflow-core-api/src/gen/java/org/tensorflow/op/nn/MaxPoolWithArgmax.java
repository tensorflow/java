/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Performs max pooling on the input and outputs both max values and indices.
 * The indices in {@code argmax} are flattened, so that a maximum value at position
 * {@code [b, y, x, c]} becomes flattened index:
 * {@code (y * width + x) * channels + c} if {@code include_batch_in_index} is False;
 * {@code ((b * height + y) * width + x) * channels + c} if {@code include_batch_in_index} is True.
 * <p>The indices returned are always in {@code [0, height) x [0, width)} before flattening,
 * even if padding is involved and the mathematically correct answer is outside
 * (either negative or too large).  This is a bug, but fixing it is difficult to do
 * in a safe backwards compatible way, especially due to flattening.
 */
@OpMetadata(
    opType = MaxPoolWithArgmax.OP_NAME,
    inputsClass = MaxPoolWithArgmax.Inputs.class
)
@Operator(
    group = "nn"
)
public final class MaxPoolWithArgmax<T extends TNumber, U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MaxPoolWithArgmax";

  private Output<T> output;

  private Output<U> argmax;

  public MaxPoolWithArgmax(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    argmax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MaxPoolWithArgmax operation.
   *
   * @param scope current scope
   * @param input 4-D with shape {@code [batch, height, width, channels]}.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * input tensor.
   * @param Targmax The value of the Targmax attribute
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolWithArgmax} output and operands
   * @param <U> data type for {@code MaxPoolWithArgmax} output and operands
   * @return a new instance of MaxPoolWithArgmax
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TNumber> MaxPoolWithArgmax<T, U> create(Scope scope,
      Operand<T> input, List<Long> ksize, List<Long> strides, Class<U> Targmax, String padding,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MaxPoolWithArgmax");
    opBuilder.addInput(input.asOutput());
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
    opBuilder.setAttr("Targmax", Operands.toDataType(Targmax));
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.includeBatchInIndex != null) {
          opBuilder.setAttr("include_batch_in_index", opts.includeBatchInIndex);
        }
      }
    }
    return new MaxPoolWithArgmax<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new MaxPoolWithArgmax operation, with the default output types.
   *
   * @param scope current scope
   * @param input 4-D with shape {@code [batch, height, width, channels]}.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolWithArgmax} output and operands
   * @return a new instance of MaxPoolWithArgmax, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> MaxPoolWithArgmax<T, TInt64> create(Scope scope,
      Operand<T> input, List<Long> ksize, List<Long> strides, String padding, Options... options) {
    return create(scope, input, ksize, strides, TInt64.class, padding, options);
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
   * The max pooled output tensor.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  /**
   * Gets argmax.
   * 4-D.  The flattened indices of the max values chosen for each output.
   * @return argmax.
   */
  public Output<U> argmax() {
    return argmax;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.MaxPoolWithArgmax}
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
      outputsClass = MaxPoolWithArgmax.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<MaxPoolWithArgmax<T, ?>> {
    /**
     * 4-D with shape {@code [batch, height, width, channels]}.  Input to pool over.
     */
    public final Operand<T> input;

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
     * The Targmax attribute
     */
    public final DataType Targmax;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    /**
     * Whether to include batch dimension in flattened index of {@code argmax}.
     */
    public final boolean includeBatchInIndex;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new MaxPoolWithArgmax<>(op), op, Arrays.asList("ksize", "strides", "Targmax", "padding", "include_batch_in_index", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      ksize = op.attributes().getAttrIntList("ksize");
      strides = op.attributes().getAttrIntList("strides");
      Targmax = op.attributes().getAttrType("Targmax");
      padding = op.attributes().getAttrString("padding");
      includeBatchInIndex = op.attributes().getAttrBool("include_batch_in_index");
      T = op.attributes().getAttrType("T");
    }
  }
}
