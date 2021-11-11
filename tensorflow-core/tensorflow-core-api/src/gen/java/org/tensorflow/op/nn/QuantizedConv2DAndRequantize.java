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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * The QuantizedConv2DAndRequantize operation
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = QuantizedConv2DAndRequantize.OP_NAME,
    inputsClass = QuantizedConv2DAndRequantize.Inputs.class
)
public final class QuantizedConv2DAndRequantize<V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedConv2DAndRequantize";

  private Output<V> output;

  private Output<TFloat32> minOutput;

  private Output<TFloat32> maxOutput;

  public QuantizedConv2DAndRequantize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedConv2DAndRequantize operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param filter The filter value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DAndRequantize} output and operands
   * @return a new instance of QuantizedConv2DAndRequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> QuantizedConv2DAndRequantize<V> create(Scope scope,
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<V> outType, List<Long> strides, String padding,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedConv2DAndRequantize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(minInput.asOutput());
    opBuilder.addInput(maxInput.asOutput());
    opBuilder.addInput(minFilter.asOutput());
    opBuilder.addInput(maxFilter.asOutput());
    opBuilder.addInput(minFreezedOutput.asOutput());
    opBuilder.addInput(maxFreezedOutput.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dilations != null) {
          long[] dilationsArray = new long[opts.dilations.size()];
          for (int i = 0 ; i < dilationsArray.length ; i++) {
            dilationsArray[i] = opts.dilations.get(i);
          }
          opBuilder.setAttr("dilations", dilationsArray);
        }
        if (opts.paddingList != null) {
          long[] paddingListArray = new long[opts.paddingList.size()];
          for (int i = 0 ; i < paddingListArray.length ; i++) {
            paddingListArray[i] = opts.paddingList.get(i);
          }
          opBuilder.setAttr("padding_list", paddingListArray);
        }
      }
    }
    return new QuantizedConv2DAndRequantize<>(opBuilder.build());
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations the dilations option
   * @return this Options instance.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations the dilations option
   * @return this Options instance.
   */
  public static Options dilations(Long... dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the paddingList option.
   *
   * @param paddingList the paddingList option
   * @return this Options instance.
   */
  public static Options paddingList(List<Long> paddingList) {
    return new Options().paddingList(paddingList);
  }

  /**
   * Sets the paddingList option.
   *
   * @param paddingList the paddingList option
   * @return this Options instance.
   */
  public static Options paddingList(Long... paddingList) {
    return new Options().paddingList(paddingList);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<V> output() {
    return output;
  }

  /**
   * Gets minOutput.
   *
   * @return minOutput.
   */
  public Output<TFloat32> minOutput() {
    return minOutput;
  }

  /**
   * Gets maxOutput.
   *
   * @return maxOutput.
   */
  public Output<TFloat32> maxOutput() {
    return maxOutput;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedConv2DAndRequantize}
   */
  public static class Options {
    private List<Long> dilations;

    private List<Long> paddingList;

    private Options() {
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations the dilations option
     * @return this Options instance.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations the dilations option
     * @return this Options instance.
     */
    public Options dilations(Long... dilations) {
      this.dilations = Arrays.asList(dilations);
      return this;
    }

    /**
     * Sets the paddingList option.
     *
     * @param paddingList the paddingList option
     * @return this Options instance.
     */
    public Options paddingList(List<Long> paddingList) {
      this.paddingList = paddingList;
      return this;
    }

    /**
     * Sets the paddingList option.
     *
     * @param paddingList the paddingList option
     * @return this Options instance.
     */
    public Options paddingList(Long... paddingList) {
      this.paddingList = Arrays.asList(paddingList);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = QuantizedConv2DAndRequantize.class
  )
  public static class Inputs extends RawOpInputs<QuantizedConv2DAndRequantize<?>> {
    /**
     * The input input
     */
    public final Operand<? extends TNumber> input;

    /**
     * The filter input
     */
    public final Operand<? extends TNumber> filter;

    /**
     * The minInput input
     */
    public final Operand<TFloat32> minInput;

    /**
     * The maxInput input
     */
    public final Operand<TFloat32> maxInput;

    /**
     * The minFilter input
     */
    public final Operand<TFloat32> minFilter;

    /**
     * The maxFilter input
     */
    public final Operand<TFloat32> maxFilter;

    /**
     * The minFreezedOutput input
     */
    public final Operand<TFloat32> minFreezedOutput;

    /**
     * The maxFreezedOutput input
     */
    public final Operand<TFloat32> maxFreezedOutput;

    /**
     * The Tinput attribute
     */
    public final DataType Tinput;

    /**
     * The Tfilter attribute
     */
    public final DataType Tfilter;

    /**
     * The outType attribute
     */
    public final DataType outType;

    /**
     * The strides attribute
     */
    public final long[] strides;

    /**
     * The padding attribute
     */
    public final String padding;

    /**
     * The dilations attribute
     */
    public final long[] dilations;

    /**
     * The paddingList attribute
     */
    public final long[] paddingList;

    public Inputs(GraphOperation op) {
      super(new QuantizedConv2DAndRequantize<>(op), op, Arrays.asList("Tinput", "Tfilter", "out_type", "strides", "padding", "dilations", "padding_list"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      filter = (Operand<? extends TNumber>) op.input(inputIndex++);
      minInput = (Operand<TFloat32>) op.input(inputIndex++);
      maxInput = (Operand<TFloat32>) op.input(inputIndex++);
      minFilter = (Operand<TFloat32>) op.input(inputIndex++);
      maxFilter = (Operand<TFloat32>) op.input(inputIndex++);
      minFreezedOutput = (Operand<TFloat32>) op.input(inputIndex++);
      maxFreezedOutput = (Operand<TFloat32>) op.input(inputIndex++);
      Tinput = op.attributes().getAttrType("Tinput");
      Tfilter = op.attributes().getAttrType("Tfilter");
      outType = op.attributes().getAttrType("out_type");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      dilations = op.attributes().getAttrIntList("dilations");
      paddingList = op.attributes().getAttrIntList("padding_list");
    }
  }
}
