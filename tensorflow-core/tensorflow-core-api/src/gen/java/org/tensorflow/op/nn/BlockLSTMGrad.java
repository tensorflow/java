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
 * Computes the LSTM cell backward propagation for the entire time sequence.
 * This implementation is to be used in conjunction of BlockLSTMV2.
 *
 * @param <T> data type for {@code x_grad} output
 */
@OpMetadata(
    opType = BlockLSTMGrad.OP_NAME,
    inputsClass = BlockLSTMGrad.Inputs.class
)
public final class BlockLSTMGrad<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BlockLSTMGradV2";

  private Output<T> xGrad;

  private Output<T> csPrevGrad;

  private Output<T> hPrevGrad;

  private Output<T> wGrad;

  private Output<T> wciGrad;

  private Output<T> wcfGrad;

  private Output<T> wcoGrad;

  private Output<T> bGrad;

  public BlockLSTMGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    xGrad = operation.output(outputIdx++);
    csPrevGrad = operation.output(outputIdx++);
    hPrevGrad = operation.output(outputIdx++);
    wGrad = operation.output(outputIdx++);
    wciGrad = operation.output(outputIdx++);
    wcfGrad = operation.output(outputIdx++);
    wcoGrad = operation.output(outputIdx++);
    bGrad = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BlockLSTMGradV2 operation.
   *
   * @param scope current scope
   * @param seqLenMax Maximum time length actually used by this input. Outputs are padded
   * with zeros beyond this length.
   * @param x The sequence input to the LSTM, shape (timelen, batch_size, num_inputs).
   * @param csPrev Value of the initial cell state.
   * @param hPrev Initial output of cell (to be used for peephole).
   * @param w The weight matrix.
   * @param wci The weight matrix for input gate peephole connection.
   * @param wcf The weight matrix for forget gate peephole connection.
   * @param wco The weight matrix for output gate peephole connection.
   * @param b The bias vector.
   * @param i The input gate over the whole time sequence.
   * @param cs The cell state before the tanh over the whole time sequence.
   * @param f The forget gate over the whole time sequence.
   * @param o The output gate over the whole time sequence.
   * @param ci The cell input over the whole time sequence.
   * @param co The cell after the tanh over the whole time sequence.
   * @param h The output h vector over the whole time sequence.
   * @param csGrad The current gradient of cs.
   * @param hGrad The gradient of h vector.
   * @param usePeephole Whether to use peephole weights.
   * @param <T> data type for {@code BlockLSTMGradV2} output and operands
   * @return a new instance of BlockLSTMGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> BlockLSTMGrad<T> create(Scope scope, Operand<TInt64> seqLenMax,
      Operand<T> x, Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci,
      Operand<T> wcf, Operand<T> wco, Operand<T> b, Operand<T> i, Operand<T> cs, Operand<T> f,
      Operand<T> o, Operand<T> ci, Operand<T> co, Operand<T> h, Operand<T> csGrad, Operand<T> hGrad,
      Boolean usePeephole) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BlockLSTMGrad");
    opBuilder.addInput(seqLenMax.asOutput());
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
    opBuilder.addInput(h.asOutput());
    opBuilder.addInput(csGrad.asOutput());
    opBuilder.addInput(hGrad.asOutput());
    opBuilder.setAttr("use_peephole", usePeephole);
    return new BlockLSTMGrad<>(opBuilder.build());
  }

  /**
   * Gets xGrad.
   * The gradient of x to be back-propped.
   * @return xGrad.
   */
  public Output<T> xGrad() {
    return xGrad;
  }

  /**
   * Gets csPrevGrad.
   * The gradient of cs_prev to be back-propped.
   * @return csPrevGrad.
   */
  public Output<T> csPrevGrad() {
    return csPrevGrad;
  }

  /**
   * Gets hPrevGrad.
   * The gradient of h_prev to be back-propped.
   * @return hPrevGrad.
   */
  public Output<T> hPrevGrad() {
    return hPrevGrad;
  }

  /**
   * Gets wGrad.
   * The gradient for w to be back-propped.
   * @return wGrad.
   */
  public Output<T> wGrad() {
    return wGrad;
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

  /**
   * Gets bGrad.
   * The gradient for w to be back-propped.
   * @return bGrad.
   */
  public Output<T> bGrad() {
    return bGrad;
  }

  @OpInputsMetadata(
      outputsClass = BlockLSTMGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<BlockLSTMGrad<T>> {
    /**
     * Maximum time length actually used by this input. Outputs are padded
     * with zeros beyond this length.
     */
    public final Operand<TInt64> seqLenMax;

    /**
     * The sequence input to the LSTM, shape (timelen, batch_size, num_inputs).
     */
    public final Operand<T> x;

    /**
     * Value of the initial cell state.
     */
    public final Operand<T> csPrev;

    /**
     * Initial output of cell (to be used for peephole).
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
     * The input gate over the whole time sequence.
     */
    public final Operand<T> i;

    /**
     * The cell state before the tanh over the whole time sequence.
     */
    public final Operand<T> cs;

    /**
     * The forget gate over the whole time sequence.
     */
    public final Operand<T> f;

    /**
     * The output gate over the whole time sequence.
     */
    public final Operand<T> o;

    /**
     * The cell input over the whole time sequence.
     */
    public final Operand<T> ci;

    /**
     * The cell after the tanh over the whole time sequence.
     */
    public final Operand<T> co;

    /**
     * The output h vector over the whole time sequence.
     */
    public final Operand<T> h;

    /**
     * The current gradient of cs.
     */
    public final Operand<T> csGrad;

    /**
     * The gradient of h vector.
     */
    public final Operand<T> hGrad;

    /**
     * Whether to use peephole weights.
     */
    public final boolean usePeephole;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new BlockLSTMGrad<>(op), op, Arrays.asList("use_peephole", "T"));
      int inputIndex = 0;
      seqLenMax = (Operand<TInt64>) op.input(inputIndex++);
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
      h = (Operand<T>) op.input(inputIndex++);
      csGrad = (Operand<T>) op.input(inputIndex++);
      hGrad = (Operand<T>) op.input(inputIndex++);
      usePeephole = op.attributes().getAttrBool("use_peephole");
      T = op.attributes().getAttrType("T");
    }
  }
}
