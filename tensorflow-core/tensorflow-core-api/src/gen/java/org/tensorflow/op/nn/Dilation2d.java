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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the grayscale dilation of 4-D {@code input} and 3-D {@code filter} tensors.
 * The {@code input} tensor has shape {@code [batch, in_height, in_width, depth]} and the
 * {@code filter} tensor has shape {@code [filter_height, filter_width, depth]}, i.e., each
 * input channel is processed independently of the others with its own structuring
 * function. The {@code output} tensor has shape
 * {@code [batch, out_height, out_width, depth]}. The spatial dimensions of the output
 * tensor depend on the {@code padding} algorithm. We currently only support the default
 * &quot;NHWC&quot; {@code data_format}.
 * <p>In detail, the grayscale morphological 2-D dilation is the max-sum correlation
 * (for consistency with {@code conv2d}, we use unmirrored filters):
 * <pre>
 * output[b, y, x, c] =
 *    max_{dy, dx} input[b,
 *                       strides[1] * y + rates[1] * dy,
 *                       strides[2] * x + rates[2] * dx,
 *                       c] +
 *                 filter[dy, dx, c]
 * </pre>
 * <p>Max-pooling is a special case when the filter has size equal to the pooling
 * kernel size and contains all zeros.
 * <p>Note on duality: The dilation of {@code input} by the {@code filter} is equal to the
 * negation of the erosion of {@code -input} by the reflected {@code filter}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Dilation2d.OP_NAME,
    inputsClass = Dilation2d.Inputs.class
)
@Operator(
    group = "nn"
)
public final class Dilation2d<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Dilation2D";

  private Output<T> output;

  public Dilation2d(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Dilation2D operation.
   *
   * @param scope current scope
   * @param input 4-D with shape {@code [batch, in_height, in_width, depth]}.
   * @param filter 3-D with shape {@code [filter_height, filter_width, depth]}.
   * @param strides The stride of the sliding window for each dimension of the input
   * tensor. Must be: {@code [1, stride_height, stride_width, 1]}.
   * @param rates The input stride for atrous morphological dilation. Must be:
   * {@code [1, rate_height, rate_width, 1]}.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code Dilation2D} output and operands
   * @return a new instance of Dilation2d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Dilation2d<T> create(Scope scope, Operand<T> input,
      Operand<T> filter, List<Long> strides, List<Long> rates, String padding) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Dilation2d");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
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
    return new Dilation2d<>(opBuilder.build());
  }

  /**
   * Gets output.
   * 4-D with shape {@code [batch, out_height, out_width, depth]}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Dilation2d.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Dilation2d<T>> {
    /**
     * 4-D with shape {@code [batch, in_height, in_width, depth]}.
     */
    public final Operand<T> input;

    /**
     * 3-D with shape {@code [filter_height, filter_width, depth]}.
     */
    public final Operand<T> filter;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The stride of the sliding window for each dimension of the input
     * tensor. Must be: `[1, stride_height, stride_width, 1]`.
     */
    public final long[] strides;

    /**
     * The input stride for atrous morphological dilation. Must be:
     * `[1, rate_height, rate_width, 1]`.
     */
    public final long[] rates;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    public Inputs(GraphOperation op) {
      super(new Dilation2d<>(op), op, Arrays.asList("T", "strides", "rates", "padding"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      filter = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      strides = op.attributes().getAttrIntList("strides");
      rates = op.attributes().getAttrIntList("rates");
      padding = op.attributes().getAttrString("padding");
    }
  }
}
