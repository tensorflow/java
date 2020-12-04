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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the LSTM cell backward propagation for 1 timestep.
 * <p>
 * This implementation is to be used in conjunction of LSTMBlockCell.
 * 
 * @param <T> data type for {@code csPrevGrad()} output
 */
public final class LSTMBlockCellGrad<T extends TNumber> extends RawOp {
  
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
   * @return a new instance of LSTMBlockCellGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> LSTMBlockCellGrad<T> create(Scope scope, Operand<T> x, Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf, Operand<T> wco, Operand<T> b, Operand<T> i, Operand<T> cs, Operand<T> f, Operand<T> o, Operand<T> ci, Operand<T> co, Operand<T> csGrad, Operand<T> hGrad, Boolean usePeephole) {
    OperationBuilder opBuilder = scope.env().opBuilder("LSTMBlockCellGrad", scope.makeOpName("LSTMBlockCellGrad"));
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
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("use_peephole", usePeephole);
    return new LSTMBlockCellGrad<T>(opBuilder.build());
  }
  
  /**
   * The gradient of cs to be back-propped.
   */
  public Output<T> csPrevGrad() {
    return csPrevGrad;
  }
  
  /**
   * The derivative wrt to [i, cs, f, o].
   */
  public Output<T> dicfo() {
    return dicfo;
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
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LSTMBlockCellGrad";
  
  private Output<T> csPrevGrad;
  private Output<T> dicfo;
  private Output<T> wciGrad;
  private Output<T> wcfGrad;
  private Output<T> wcoGrad;
  
  private LSTMBlockCellGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    csPrevGrad = operation.output(outputIdx++);
    dicfo = operation.output(outputIdx++);
    wciGrad = operation.output(outputIdx++);
    wcfGrad = operation.output(outputIdx++);
    wcoGrad = operation.output(outputIdx++);
  }
}
