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
 * Computes the GRU cell forward propagation for 1 time step.
 * <p>
 * Args
 *     x: Input to the GRU cell.
 *     h_prev: State input from the previous GRU cell.
 *     w_ru: Weight matrix for the reset and update gate.
 *     w_c: Weight matrix for the cell connection gate.
 *     b_ru: Bias vector for the reset and update gate.
 *     b_c: Bias vector for the cell connection gate.
 * <p>
 * Returns
 *     r: Output of the reset gate.
 *     u: Output of the update gate.
 *     c: Output of the cell connection gate.
 *     h: Current state of the GRU cell.
 * <p>
 * Note on notation of the variables:
 * <p>
 * Concatenation of a and b is represented by a_b
 * Element-wise dot product of a and b is represented by ab
 * Element-wise dot product is represented by \circ
 * Matrix multiplication is represented by *
 * <p>
 * Biases are initialized with :
 * `b_ru` - constant_initializer(1.0)
 * `b_c` - constant_initializer(0.0)
 * <p>
 * This kernel op implements the following mathematical equations:
 * <pre>{@code
 * x_h_prev = [x, h_prev]
 * 
 * [r_bar u_bar] = x_h_prev * w_ru + b_ru
 * 
 * r = sigmoid(r_bar)
 * u = sigmoid(u_bar)
 * 
 * h_prevr = h_prev \circ r
 * 
 * x_h_prevr = [x h_prevr]
 * 
 * c_bar = x_h_prevr * w_c + b_c
 * c = tanh(c_bar)
 * 
 * h = (1-u) \circ c + u \circ h_prev
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code r()} output
 */
public final class GRUBlockCell<T extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new GRUBlockCell operation.
   * 
   * @param scope current scope
   * @param x 
   * @param hPrev 
   * @param wRu 
   * @param wC 
   * @param bRu 
   * @param bC 
   * @return a new instance of GRUBlockCell
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> GRUBlockCell<T> create(Scope scope, Operand<T> x, Operand<T> hPrev, Operand<T> wRu, Operand<T> wC, Operand<T> bRu, Operand<T> bC) {
    OperationBuilder opBuilder = scope.env().opBuilder("GRUBlockCell", scope.makeOpName("GRUBlockCell"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(hPrev.asOutput());
    opBuilder.addInput(wRu.asOutput());
    opBuilder.addInput(wC.asOutput());
    opBuilder.addInput(bRu.asOutput());
    opBuilder.addInput(bC.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new GRUBlockCell<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> r() {
    return r;
  }
  
  /**
   */
  public Output<T> u() {
    return u;
  }
  
  /**
   */
  public Output<T> c() {
    return c;
  }
  
  /**
   */
  public Output<T> h() {
    return h;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "GRUBlockCell";
  
  private Output<T> r;
  private Output<T> u;
  private Output<T> c;
  private Output<T> h;
  
  private GRUBlockCell(Operation operation) {
    super(operation);
    int outputIdx = 0;
    r = operation.output(outputIdx++);
    u = operation.output(outputIdx++);
    c = operation.output(outputIdx++);
    h = operation.output(outputIdx++);
  }
}
