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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the LSTM cell backward propagation for the entire time sequence.
 * <p>
 * This implementation is to be used in conjunction of BlockLSTMV2.
 * 
 * @param <T> data type for {@code xGrad()} output
 */
public final class BlockLSTMGrad<T extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new BlockLSTMGrad operation.
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
   * @return a new instance of BlockLSTMGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> BlockLSTMGrad<T> create(Scope scope, Operand<TInt64> seqLenMax, Operand<T> x, Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf, Operand<T> wco, Operand<T> b, Operand<T> i, Operand<T> cs, Operand<T> f, Operand<T> o, Operand<T> ci, Operand<T> co, Operand<T> h, Operand<T> csGrad, Operand<T> hGrad, Boolean usePeephole) {
    OperationBuilder opBuilder = scope.env().opBuilder("BlockLSTMGradV2", scope.makeOpName("BlockLSTMGrad"));
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
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("use_peephole", usePeephole);
    return new BlockLSTMGrad<T>(opBuilder.build());
  }
  
  /**
   * The gradient of x to be back-propped.
   */
  public Output<T> xGrad() {
    return xGrad;
  }
  
  /**
   * The gradient of cs_prev to be back-propped.
   */
  public Output<T> csPrevGrad() {
    return csPrevGrad;
  }
  
  /**
   * The gradient of h_prev to be back-propped.
   */
  public Output<T> hPrevGrad() {
    return hPrevGrad;
  }
  
  /**
   * The gradient for w to be back-propped.
   */
  public Output<T> wGrad() {
    return wGrad;
  }
  
  /**
   * The gradient for wci to be back-propped.
   */
  public Output<T> wciGrad() {
    return wciGrad;
  }
  
  /**
   * The gradient for wcf to be back-propped.
   */
  public Output<T> wcfGrad() {
    return wcfGrad;
  }
  
  /**
   * The gradient for wco to be back-propped.
   */
  public Output<T> wcoGrad() {
    return wcoGrad;
  }
  
  /**
   * The gradient for w to be back-propped.
   */
  public Output<T> bGrad() {
    return bGrad;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BlockLSTMGradV2";
  
  private Output<T> xGrad;
  private Output<T> csPrevGrad;
  private Output<T> hPrevGrad;
  private Output<T> wGrad;
  private Output<T> wciGrad;
  private Output<T> wcfGrad;
  private Output<T> wcoGrad;
  private Output<T> bGrad;
  
  private BlockLSTMGrad(Operation operation) {
    super(operation);
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
}
