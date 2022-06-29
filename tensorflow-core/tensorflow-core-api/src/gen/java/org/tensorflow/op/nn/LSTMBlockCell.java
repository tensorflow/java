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
import org.tensorflow.types.family.TNumber;

/**
 * Computes the LSTM cell forward propagation for 1 time step.
 * This implementation uses 1 weight matrix and 1 bias vector, and there's an
 * optional peephole connection.
 * <p>This kernel op implements the following mathematical equations:
 * <pre>
 * xh = [x, h_prev]
 * [i, f, ci, o] = xh * w + b
 * f = f + forget_bias
 *
 * if not use_peephole:
 *   wci = wcf = wco = 0
 *
 * i = sigmoid(cs_prev * wci + i)
 * f = sigmoid(cs_prev * wcf + f)
 * ci = tanh(ci)
 *
 * cs = ci .* i + cs_prev .* f
 * cs = clip(cs, cell_clip)
 *
 * o = sigmoid(cs * wco + o)
 * co = tanh(cs)
 * h = co .* o
 * </pre>
 *
 * @param <T> data type for {@code i} output
 */
@OpMetadata(
    opType = LSTMBlockCell.OP_NAME,
    inputsClass = LSTMBlockCell.Inputs.class
)
public final class LSTMBlockCell<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LSTMBlockCell";

  private Output<T> i;

  private Output<T> cs;

  private Output<T> f;

  private Output<T> o;

  private Output<T> ci;

  private Output<T> co;

  private Output<T> h;

  public LSTMBlockCell(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    i = operation.output(outputIdx++);
    cs = operation.output(outputIdx++);
    f = operation.output(outputIdx++);
    o = operation.output(outputIdx++);
    ci = operation.output(outputIdx++);
    co = operation.output(outputIdx++);
    h = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LSTMBlockCell operation.
   *
   * @param scope current scope
   * @param x The input to the LSTM cell, shape (batch_size, num_inputs).
   * @param csPrev Value of the cell state at previous time step.
   * @param hPrev Output of the previous cell at previous time step.
   * @param w The weight matrix.
   * @param wci The weight matrix for input gate peephole connection.
   * @param wcf The weight matrix for forget gate peephole connection.
   * @param wco The weight matrix for output gate peephole connection.
   * @param b The bias vector.
   * @param options carries optional attribute values
   * @param <T> data type for {@code LSTMBlockCell} output and operands
   * @return a new instance of LSTMBlockCell
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> LSTMBlockCell<T> create(Scope scope, Operand<T> x,
      Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf,
      Operand<T> wco, Operand<T> b, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LSTMBlockCell");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(csPrev.asOutput());
    opBuilder.addInput(hPrev.asOutput());
    opBuilder.addInput(w.asOutput());
    opBuilder.addInput(wci.asOutput());
    opBuilder.addInput(wcf.asOutput());
    opBuilder.addInput(wco.asOutput());
    opBuilder.addInput(b.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.forgetBias != null) {
          opBuilder.setAttr("forget_bias", opts.forgetBias);
        }
        if (opts.cellClip != null) {
          opBuilder.setAttr("cell_clip", opts.cellClip);
        }
        if (opts.usePeephole != null) {
          opBuilder.setAttr("use_peephole", opts.usePeephole);
        }
      }
    }
    return new LSTMBlockCell<>(opBuilder.build());
  }

  /**
   * Sets the forgetBias option.
   *
   * @param forgetBias The forget gate bias.
   * @return this Options instance.
   */
  public static Options forgetBias(Float forgetBias) {
    return new Options().forgetBias(forgetBias);
  }

  /**
   * Sets the cellClip option.
   *
   * @param cellClip Value to clip the 'cs' value to.
   * @return this Options instance.
   */
  public static Options cellClip(Float cellClip) {
    return new Options().cellClip(cellClip);
  }

  /**
   * Sets the usePeephole option.
   *
   * @param usePeephole Whether to use peephole weights.
   * @return this Options instance.
   */
  public static Options usePeephole(Boolean usePeephole) {
    return new Options().usePeephole(usePeephole);
  }

  /**
   * Gets i.
   * The input gate.
   * @return i.
   */
  public Output<T> i() {
    return i;
  }

  /**
   * Gets cs.
   * The cell state before the tanh.
   * @return cs.
   */
  public Output<T> cs() {
    return cs;
  }

  /**
   * Gets f.
   * The forget gate.
   * @return f.
   */
  public Output<T> f() {
    return f;
  }

  /**
   * Gets o.
   * The output gate.
   * @return o.
   */
  public Output<T> o() {
    return o;
  }

  /**
   * Gets ci.
   * The cell input.
   * @return ci.
   */
  public Output<T> ci() {
    return ci;
  }

  /**
   * Gets co.
   * The cell after the tanh.
   * @return co.
   */
  public Output<T> co() {
    return co;
  }

  /**
   * Gets h.
   * The output h vector.
   * @return h.
   */
  public Output<T> h() {
    return h;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.LSTMBlockCell}
   */
  public static class Options {
    private Float forgetBias;

    private Float cellClip;

    private Boolean usePeephole;

    private Options() {
    }

    /**
     * Sets the forgetBias option.
     *
     * @param forgetBias The forget gate bias.
     * @return this Options instance.
     */
    public Options forgetBias(Float forgetBias) {
      this.forgetBias = forgetBias;
      return this;
    }

    /**
     * Sets the cellClip option.
     *
     * @param cellClip Value to clip the 'cs' value to.
     * @return this Options instance.
     */
    public Options cellClip(Float cellClip) {
      this.cellClip = cellClip;
      return this;
    }

    /**
     * Sets the usePeephole option.
     *
     * @param usePeephole Whether to use peephole weights.
     * @return this Options instance.
     */
    public Options usePeephole(Boolean usePeephole) {
      this.usePeephole = usePeephole;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = LSTMBlockCell.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<LSTMBlockCell<T>> {
    /**
     * The input to the LSTM cell, shape (batch_size, num_inputs).
     */
    public final Operand<T> x;

    /**
     * Value of the cell state at previous time step.
     */
    public final Operand<T> csPrev;

    /**
     * Output of the previous cell at previous time step.
     */
    public final Operand<T> hPrev;

    /**
     * The weight matrix.
     */
    public final Operand<T> w;

    /**
     * The weight matrix for input gate peephole connection.
     */
    public final Operand<T> wci;

    /**
     * The weight matrix for forget gate peephole connection.
     */
    public final Operand<T> wcf;

    /**
     * The weight matrix for output gate peephole connection.
     */
    public final Operand<T> wco;

    /**
     * The bias vector.
     */
    public final Operand<T> b;

    /**
     * The forget gate bias.
     */
    public final float forgetBias;

    /**
     * Value to clip the 'cs' value to.
     */
    public final float cellClip;

    /**
     * Whether to use peephole weights.
     */
    public final boolean usePeephole;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new LSTMBlockCell<>(op), op, Arrays.asList("forget_bias", "cell_clip", "use_peephole", "T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      csPrev = (Operand<T>) op.input(inputIndex++);
      hPrev = (Operand<T>) op.input(inputIndex++);
      w = (Operand<T>) op.input(inputIndex++);
      wci = (Operand<T>) op.input(inputIndex++);
      wcf = (Operand<T>) op.input(inputIndex++);
      wco = (Operand<T>) op.input(inputIndex++);
      b = (Operand<T>) op.input(inputIndex++);
      forgetBias = op.attributes().getAttrFloat("forget_bias");
      cellClip = op.attributes().getAttrFloat("cell_clip");
      usePeephole = op.attributes().getAttrBool("use_peephole");
      T = op.attributes().getAttrType("T");
    }
  }
}
