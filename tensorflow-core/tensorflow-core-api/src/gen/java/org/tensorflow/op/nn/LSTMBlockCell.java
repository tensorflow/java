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
 * Computes the LSTM cell forward propagation for 1 time step.
 * <p>
 * This implementation uses 1 weight matrix and 1 bias vector, and there's an
 * optional peephole connection.
 * <p>
 * This kernel op implements the following mathematical equations:
 * <pre>{@code
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
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code i()} output
 */
public final class LSTMBlockCell<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.LSTMBlockCell}
   */
  public static class Options {
    
    /**
     * @param forgetBias The forget gate bias.
     */
    public Options forgetBias(Float forgetBias) {
      this.forgetBias = forgetBias;
      return this;
    }
    
    /**
     * @param cellClip Value to clip the 'cs' value to.
     */
    public Options cellClip(Float cellClip) {
      this.cellClip = cellClip;
      return this;
    }
    
    /**
     * @param usePeephole Whether to use peephole weights.
     */
    public Options usePeephole(Boolean usePeephole) {
      this.usePeephole = usePeephole;
      return this;
    }
    
    private Float forgetBias;
    private Float cellClip;
    private Boolean usePeephole;
    
    private Options() {
    }
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
   * @param options carries optional attributes values
   * @return a new instance of LSTMBlockCell
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> LSTMBlockCell<T> create(Scope scope, Operand<T> x, Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf, Operand<T> wco, Operand<T> b, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("LSTMBlockCell", scope.makeOpName("LSTMBlockCell"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(csPrev.asOutput());
    opBuilder.addInput(hPrev.asOutput());
    opBuilder.addInput(w.asOutput());
    opBuilder.addInput(wci.asOutput());
    opBuilder.addInput(wcf.asOutput());
    opBuilder.addInput(wco.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new LSTMBlockCell<T>(opBuilder.build());
  }
  
  /**
   * @param forgetBias The forget gate bias.
   */
  public static Options forgetBias(Float forgetBias) {
    return new Options().forgetBias(forgetBias);
  }
  
  /**
   * @param cellClip Value to clip the 'cs' value to.
   */
  public static Options cellClip(Float cellClip) {
    return new Options().cellClip(cellClip);
  }
  
  /**
   * @param usePeephole Whether to use peephole weights.
   */
  public static Options usePeephole(Boolean usePeephole) {
    return new Options().usePeephole(usePeephole);
  }
  
  /**
   * The input gate.
   */
  public Output<T> i() {
    return i;
  }
  
  /**
   * The cell state before the tanh.
   */
  public Output<T> cs() {
    return cs;
  }
  
  /**
   * The forget gate.
   */
  public Output<T> f() {
    return f;
  }
  
  /**
   * The output gate.
   */
  public Output<T> o() {
    return o;
  }
  
  /**
   * The cell input.
   */
  public Output<T> ci() {
    return ci;
  }
  
  /**
   * The cell after the tanh.
   */
  public Output<T> co() {
    return co;
  }
  
  /**
   * The output h vector.
   */
  public Output<T> h() {
    return h;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LSTMBlockCell";
  
  private Output<T> i;
  private Output<T> cs;
  private Output<T> f;
  private Output<T> o;
  private Output<T> ci;
  private Output<T> co;
  private Output<T> h;
  
  private LSTMBlockCell(Operation operation) {
    super(operation);
    int outputIdx = 0;
    i = operation.output(outputIdx++);
    cs = operation.output(outputIdx++);
    f = operation.output(outputIdx++);
    o = operation.output(outputIdx++);
    ci = operation.output(outputIdx++);
    co = operation.output(outputIdx++);
    h = operation.output(outputIdx++);
  }
}
