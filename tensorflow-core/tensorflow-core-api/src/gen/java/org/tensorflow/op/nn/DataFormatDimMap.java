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
 * Returns the dimension index in the destination data format given the one in
 * the source data format.
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = DataFormatDimMap.OP_NAME,
    inputsClass = DataFormatDimMap.Inputs.class
)
@Operator(
    group = "nn"
)
public final class DataFormatDimMap<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DataFormatDimMap";

  private Output<T> y;

  public DataFormatDimMap(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DataFormatDimMap operation.
   *
   * @param scope current scope
   * @param x A Tensor with each element as a dimension index in source data format.
   * Must be in the range [-4, 4).
   * @param options carries optional attribute values
   * @param <T> data type for {@code DataFormatDimMap} output and operands
   * @return a new instance of DataFormatDimMap
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> DataFormatDimMap<T> create(Scope scope, Operand<T> x,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DataFormatDimMap");
    opBuilder.addInput(x.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.srcFormat != null) {
          opBuilder.setAttr("src_format", opts.srcFormat);
        }
        if (opts.dstFormat != null) {
          opBuilder.setAttr("dst_format", opts.dstFormat);
        }
      }
    }
    return new DataFormatDimMap<>(opBuilder.build());
  }

  /**
   * Sets the srcFormat option.
   *
   * @param srcFormat source data format.
   * @return this Options instance.
   */
  public static Options srcFormat(String srcFormat) {
    return new Options().srcFormat(srcFormat);
  }

  /**
   * Sets the dstFormat option.
   *
   * @param dstFormat destination data format.
   * @return this Options instance.
   */
  public static Options dstFormat(String dstFormat) {
    return new Options().dstFormat(dstFormat);
  }

  /**
   * Gets y.
   * A Tensor with each element as a dimension index in destination data format.
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  @Override
  public Output<T> asOutput() {
    return y;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.DataFormatDimMap}
   */
  public static class Options {
    private String srcFormat;

    private String dstFormat;

    private Options() {
    }

    /**
     * Sets the srcFormat option.
     *
     * @param srcFormat source data format.
     * @return this Options instance.
     */
    public Options srcFormat(String srcFormat) {
      this.srcFormat = srcFormat;
      return this;
    }

    /**
     * Sets the dstFormat option.
     *
     * @param dstFormat destination data format.
     * @return this Options instance.
     */
    public Options dstFormat(String dstFormat) {
      this.dstFormat = dstFormat;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DataFormatDimMap.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<DataFormatDimMap<T>> {
    /**
     * A Tensor with each element as a dimension index in source data format.
     * Must be in the range [-4, 4).
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * source data format.
     */
    public final String srcFormat;

    /**
     * destination data format.
     */
    public final String dstFormat;

    public Inputs(GraphOperation op) {
      super(new DataFormatDimMap<>(op), op, Arrays.asList("T", "src_format", "dst_format"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      srcFormat = op.attributes().getAttrString("src_format");
      dstFormat = op.attributes().getAttrString("dst_format");
    }
  }
}
