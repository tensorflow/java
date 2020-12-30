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
 * Computes the LSTM cell forward propagation for all the time steps.
 * <p>
 * This is equivalent to applying LSTMBlockCell in a loop, like so:
 * <pre>{@code
 * for x1 in unpack(x):
 *   i1, cs1, f1, o1, ci1, co1, h1 = LSTMBlock(
 *     x1, cs_prev, h_prev, w, wci, wcf, wco, b)
 *   cs_prev = cs1
 *   h_prev = h1
 *   i.append(i1)
 *   cs.append(cs1)
 *   f.append(f1)
 *   o.append(o1)
 *   ci.append(ci1)
 *   co.append(co1)
 *   h.append(h1)
 * return pack(i), pack(cs), pack(f), pack(o), pack(ci), pack(ch), pack(h)
 * 
 * Note that unlike LSTMBlockCell (and BlockLSTM) which uses ICFO gate layout,
 * this op uses IFCO. So in order for the following snippet to be equivalent
 * all gate-related outputs should be reordered.
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code i()} output
 */
public final class BlockLSTM<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.BlockLSTM}
   */
  public static class Options {
    
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
    
    private Float cellClip;
    private Boolean usePeephole;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BlockLSTM operation.
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
   * @param options carries optional attributes values
   * @return a new instance of BlockLSTM
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> BlockLSTM<T> create(Scope scope, Operand<TInt64> seqLenMax, Operand<T> x, Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf, Operand<T> wco, Operand<T> b, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BlockLSTMV2", scope.makeOpName("BlockLSTM"));
    opBuilder.addInput(seqLenMax.asOutput());
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
        if (opts.cellClip != null) {
          opBuilder.setAttr("cell_clip", opts.cellClip);
        }
        if (opts.usePeephole != null) {
          opBuilder.setAttr("use_peephole", opts.usePeephole);
        }
      }
    }
    return new BlockLSTM<T>(opBuilder.build());
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
   * The input gate over the whole time sequence.
   */
  public Output<T> i() {
    return i;
  }
  
  /**
   * The cell state before the tanh over the whole time sequence.
   */
  public Output<T> cs() {
    return cs;
  }
  
  /**
   * The forget gate over the whole time sequence.
   */
  public Output<T> f() {
    return f;
  }
  
  /**
   * The output gate over the whole time sequence.
   */
  public Output<T> o() {
    return o;
  }
  
  /**
   * The cell input over the whole time sequence.
   */
  public Output<T> ci() {
    return ci;
  }
  
  /**
   * The cell after the tanh over the whole time sequence.
   */
  public Output<T> co() {
    return co;
  }
  
  /**
   * The output h vector over the whole time sequence.
   */
  public Output<T> h() {
    return h;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BlockLSTMV2";
  
  private Output<T> i;
  private Output<T> cs;
  private Output<T> f;
  private Output<T> o;
  private Output<T> ci;
  private Output<T> co;
  private Output<T> h;
  
  private BlockLSTM(Operation operation) {
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
