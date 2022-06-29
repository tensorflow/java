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
 * Permute input tensor from {@code src_format} to {@code dst_format}.
 * Input tensor must be a vector of size 4, or a 4x2 tensor.
 * <p>For example, with {@code src_format} of {@code NHWC}, {@code dst_format} of {@code NCHW}, and inputs:
 * <pre>
 * [1, 2, 3, 4]
 * </pre>
 * <p>and
 * <pre>
 * [[1, 2, 3, 4],
 *  [5, 6, 7, 8]]
 * </pre>
 * <p>, the outputs will be (respectively):
 * <pre>
 * [1, 4, 2, 3]
 * </pre>
 * <p>and
 * <pre>
 * [[1, 4, 2, 3],
 *  [5, 8, 6, 7]]
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = DataFormatVecPermute.OP_NAME,
    inputsClass = DataFormatVecPermute.Inputs.class
)
@Operator(
    group = "nn"
)
public final class DataFormatVecPermute<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DataFormatVecPermute";

  private Output<T> y;

  public DataFormatVecPermute(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DataFormatVecPermute operation.
   *
   * @param scope current scope
   * @param x Vector of size 4 or Tensor of shape (4, 2) in source data format.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DataFormatVecPermute} output and operands
   * @return a new instance of DataFormatVecPermute
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> DataFormatVecPermute<T> create(Scope scope, Operand<T> x,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DataFormatVecPermute");
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
    return new DataFormatVecPermute<>(opBuilder.build());
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
   * Vector of size 4 or Tensor of shape (4, 2) in destination data format.
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
   * Optional attributes for {@link org.tensorflow.op.nn.DataFormatVecPermute}
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
      outputsClass = DataFormatVecPermute.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<DataFormatVecPermute<T>> {
    /**
     * Vector of size 4 or Tensor of shape (4, 2) in source data format.
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
      super(new DataFormatVecPermute<>(op), op, Arrays.asList("T", "src_format", "dst_format"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      srcFormat = op.attributes().getAttrString("src_format");
      dstFormat = op.attributes().getAttrString("dst_format");
    }
  }
}
