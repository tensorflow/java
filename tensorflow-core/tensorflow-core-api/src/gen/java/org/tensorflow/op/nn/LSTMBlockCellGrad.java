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
 * Computes the LSTM cell backward propagation for 1 timestep.
 * This implementation is to be used in conjunction of LSTMBlockCell.
 *
 * @param <T> data type for {@code cs_prev_grad} output
 */
@OpMetadata(
    opType = LSTMBlockCellGrad.OP_NAME,
    inputsClass = LSTMBlockCellGrad.Inputs.class
)
public final class LSTMBlockCellGrad<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LSTMBlockCellGrad";

  private Output<T> csPrevGrad;

  private Output<T> dicfo;

  private Output<T> wciGrad;

  private Output<T> wcfGrad;

  private Output<T> wcoGrad;

  public LSTMBlockCellGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    csPrevGrad = operation.output(outputIdx++);
    dicfo = operation.output(outputIdx++);
    wciGrad = operation.output(outputIdx++);
    wcfGrad = operation.output(outputIdx++);
    wcoGrad = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LSTMBlockCellGrad operation.
   *
   * @param scope current scope
   * @param x The input to the LSTM cell, shape (batch_size, num_inputs).
   * @param csPrev The previous cell state.
   * @param hPrev The previous h state.
   * @param w The weight matrix.
   * @param wci The weight matrix for input gate peephole connection.
   * @param wcf The weight matrix for forget gate peephole connection.
   * @param wco The weight matrix for output gate peephole connection.
   * @param b The bias vector.
   * @param i The input gate.
   * @param cs The cell state before the tanh.
   * @param f The forget gate.
   * @param o The output gate.
   * @param ci The cell input.
   * @param co The cell after the tanh.
   * @param csGrad The current gradient of cs.
   * @param hGrad The gradient of h vector.
   * @param usePeephole Whether the cell uses peephole connections.
   * @param <T> data type for {@code LSTMBlockCellGrad} output and operands
   * @return a new instance of LSTMBlockCellGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> LSTMBlockCellGrad<T> create(Scope scope, Operand<T> x,
      Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf,
      Operand<T> wco, Operand<T> b, Operand<T> i, Operand<T> cs, Operand<T> f, Operand<T> o,
      Operand<T> ci, Operand<T> co, Operand<T> csGrad, Operand<T> hGrad, Boolean usePeephole) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LSTMBlockCellGrad");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(csPrev.asOutput());
    opBuilder.addInput(hPrev.asOutput());
    opBuilder.addInput(w.asOutput());
    opBuilder.addInput(wci.asOutput());
    opBuilder.addInput(wcf.asOutput());
    opBuilder.addInput(wco.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.addInput(i.asOutput());
    opBuilder.addInput(cs.asOutput());
    opBuilder.addInput(f.asOutput());
    opBuilder.addInput(o.asOutput());
    opBuilder.addInput(ci.asOutput());
    opBuilder.addInput(co.asOutput());
    opBuilder.addInput(csGrad.asOutput());
    opBuilder.addInput(hGrad.asOutput());
    opBuilder.setAttr("use_peephole", usePeephole);
    return new LSTMBlockCellGrad<>(opBuilder.build());
  }

  /**
   * Gets csPrevGrad.
   * The gradient of cs to be back-propped.
   * @return csPrevGrad.
   */
  public Output<T> csPrevGrad() {
    return csPrevGrad;
  }

  /**
   * Gets dicfo.
   * The derivative wrt to [i, cs, f, o].
   * @return dicfo.
   */
  public Output<T> dicfo() {
    return dicfo;
  }

  /**
   * Gets wciGrad.
   * The gradient for wci to be back-propped.
   * @return wciGrad.
   */
  public Output<T> wciGrad() {
    return wciGrad;
  }

  /**
   * Gets wcfGrad.
   * The gradient for wcf to be back-propped.
   * @return wcfGrad.
   */
  public Output<T> wcfGrad() {
    return wcfGrad;
  }

  /**
   * Gets wcoGrad.
   * The gradient for wco to be back-propped.
   * @return wcoGrad.
   */
  public Output<T> wcoGrad() {
    return wcoGrad;
  }

  @OpInputsMetadata(
      outputsClass = LSTMBlockCellGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<LSTMBlockCellGrad<T>> {
    /**
     * The input to the LSTM cell, shape (batch_size, num_inputs).
     */
    public final Operand<T> x;

    /**
     * The previous cell state.
     */
    public final Operand<T> csPrev;

    /**
     * The previous h state.
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
     * The input gate.
     */
    public final Operand<T> i;

    /**
     * The cell state before the tanh.
     */
    public final Operand<T> cs;

    /**
     * The forget gate.
     */
    public final Operand<T> f;

    /**
     * The output gate.
     */
    public final Operand<T> o;

    /**
     * The cell input.
     */
    public final Operand<T> ci;

    /**
     * The cell after the tanh.
     */
    public final Operand<T> co;

    /**
     * The current gradient of cs.
     */
    public final Operand<T> csGrad;

    /**
     * The gradient of h vector.
     */
    public final Operand<T> hGrad;

    /**
     * Whether the cell uses peephole connections.
     */
    public final boolean usePeephole;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new LSTMBlockCellGrad<>(op), op, Arrays.asList("use_peephole", "T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      csPrev = (Operand<T>) op.input(inputIndex++);
      hPrev = (Operand<T>) op.input(inputIndex++);
      w = (Operand<T>) op.input(inputIndex++);
      wci = (Operand<T>) op.input(inputIndex++);
      wcf = (Operand<T>) op.input(inputIndex++);
      wco = (Operand<T>) op.input(inputIndex++);
      b = (Operand<T>) op.input(inputIndex++);
      i = (Operand<T>) op.input(inputIndex++);
      cs = (Operand<T>) op.input(inputIndex++);
      f = (Operand<T>) op.input(inputIndex++);
      o = (Operand<T>) op.input(inputIndex++);
      ci = (Operand<T>) op.input(inputIndex++);
      co = (Operand<T>) op.input(inputIndex++);
      csGrad = (Operand<T>) op.input(inputIndex++);
      hGrad = (Operand<T>) op.input(inputIndex++);
      usePeephole = op.attributes().getAttrBool("use_peephole");
      T = op.attributes().getAttrType("T");
    }
  }
}
