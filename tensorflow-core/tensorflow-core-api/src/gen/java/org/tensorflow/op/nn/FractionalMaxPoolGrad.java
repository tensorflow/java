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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradient of the FractionalMaxPool function.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = FractionalMaxPoolGrad.OP_NAME,
    inputsClass = FractionalMaxPoolGrad.Inputs.class
)
public final class FractionalMaxPoolGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FractionalMaxPoolGrad";

  private Output<T> output;

  public FractionalMaxPoolGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FractionalMaxPoolGrad operation.
   *
   * @param scope current scope
   * @param origInput Original input for {@code fractional_max_pool}
   * @param origOutput Original output for {@code fractional_max_pool}
   * @param outBackprop 4-D with shape {@code [batch, height, width, channels]}.  Gradients
   * w.r.t. the output of {@code fractional_max_pool}.
   * @param rowPoolingSequence row pooling sequence, form pooling region with
   * col_pooling_sequence.
   * @param colPoolingSequence column pooling sequence, form pooling region with
   * row_pooling sequence.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FractionalMaxPoolGrad} output and operands
   * @return a new instance of FractionalMaxPoolGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> FractionalMaxPoolGrad<T> create(Scope scope,
      Operand<T> origInput, Operand<T> origOutput, Operand<T> outBackprop,
      Operand<TInt64> rowPoolingSequence, Operand<TInt64> colPoolingSequence, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FractionalMaxPoolGrad");
    opBuilder.addInput(origInput.asOutput());
    opBuilder.addInput(origOutput.asOutput());
    opBuilder.addInput(outBackprop.asOutput());
    opBuilder.addInput(rowPoolingSequence.asOutput());
    opBuilder.addInput(colPoolingSequence.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.overlapping != null) {
          opBuilder.setAttr("overlapping", opts.overlapping);
        }
      }
    }
    return new FractionalMaxPoolGrad<>(opBuilder.build());
  }

  /**
   * Sets the overlapping option.
   *
   * @param overlapping When set to True, it means when pooling, the values at the boundary
   * of adjacent pooling cells are used by both cells. For example:
   * <p>{@code index  0  1  2  3  4}
   * <p>{@code value  20 5  16 3  7}
   * <p>If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
   * The result would be [20, 16] for fractional max pooling.
   * @return this Options instance.
   */
  public static Options overlapping(Boolean overlapping) {
    return new Options().overlapping(overlapping);
  }

  /**
   * Gets output.
   * 4-D.  Gradients w.r.t. the input of {@code fractional_max_pool}.
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
   * Optional attributes for {@link org.tensorflow.op.nn.FractionalMaxPoolGrad}
   */
  public static class Options {
    private Boolean overlapping;

    private Options() {
    }

    /**
     * Sets the overlapping option.
     *
     * @param overlapping When set to True, it means when pooling, the values at the boundary
     * of adjacent pooling cells are used by both cells. For example:
     * <p>{@code index  0  1  2  3  4}
     * <p>{@code value  20 5  16 3  7}
     * <p>If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
     * The result would be [20, 16] for fractional max pooling.
     * @return this Options instance.
     */
    public Options overlapping(Boolean overlapping) {
      this.overlapping = overlapping;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = FractionalMaxPoolGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<FractionalMaxPoolGrad<T>> {
    /**
     * Original input for {@code fractional_max_pool}
     */
    public final Operand<T> origInput;

    /**
     * Original output for {@code fractional_max_pool}
     */
    public final Operand<T> origOutput;

    /**
     * 4-D with shape {@code [batch, height, width, channels]}.  Gradients
     * w.r.t. the output of {@code fractional_max_pool}.
     */
    public final Operand<T> outBackprop;

    /**
     * row pooling sequence, form pooling region with
     * col_pooling_sequence.
     */
    public final Operand<TInt64> rowPoolingSequence;

    /**
     * column pooling sequence, form pooling region with
     * row_pooling sequence.
     */
    public final Operand<TInt64> colPoolingSequence;

    /**
     * When set to True, it means when pooling, the values at the boundary
     * of adjacent pooling cells are used by both cells. For example:
     *
     * `index  0  1  2  3  4`
     *
     * `value  20 5  16 3  7`
     *
     * If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
     * The result would be [20, 16] for fractional max pooling.
     */
    public final boolean overlapping;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new FractionalMaxPoolGrad<>(op), op, Arrays.asList("overlapping", "T"));
      int inputIndex = 0;
      origInput = (Operand<T>) op.input(inputIndex++);
      origOutput = (Operand<T>) op.input(inputIndex++);
      outBackprop = (Operand<T>) op.input(inputIndex++);
      rowPoolingSequence = (Operand<TInt64>) op.input(inputIndex++);
      colPoolingSequence = (Operand<TInt64>) op.input(inputIndex++);
      overlapping = op.attributes().getAttrBool("overlapping");
      T = op.attributes().getAttrType("T");
    }
  }
}
