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

/**
 * Computes the GRU cell back-propagation for 1 time step.
 * <p>
 * Args
 *     x: Input to the GRU cell.
 *     h_prev: State input from the previous GRU cell.
 *     w_ru: Weight matrix for the reset and update gate.
 *     w_c: Weight matrix for the cell connection gate.
 *     b_ru: Bias vector for the reset and update gate.
 *     b_c: Bias vector for the cell connection gate.
 *     r: Output of the reset gate.
 *     u: Output of the update gate.
 *     c: Output of the cell connection gate.
 *     d_h: Gradients of the h_new wrt to objective function.
 * <p>
 * Returns
 *     d_x: Gradients of the x wrt to objective function.
 *     d_h_prev: Gradients of the h wrt to objective function.
 *     d_c_bar Gradients of the c_bar wrt to objective function.
 *     d_r_bar_u_bar Gradients of the r_bar & u_bar wrt to objective function.
 * <p>
 * This kernel op implements the following mathematical equations:
 * <p>
 * Note on notation of the variables:
 * <p>
 * Concatenation of a and b is represented by a_b
 * Element-wise dot product of a and b is represented by ab
 * Element-wise dot product is represented by \circ
 * Matrix multiplication is represented by *
 * <p>
 * Additional notes for clarity:
 * <p>
 * `w_ru` can be segmented into 4 different matrices.
 * <pre>{@code
 * w_ru = [w_r_x w_u_x
 *         w_r_h_prev w_u_h_prev]
 * }</pre>
 * Similarly, `w_c` can be segmented into 2 different matrices.
 * <pre>{@code
 * w_c = [w_c_x w_c_h_prevr]
 * }</pre>
 * Same goes for biases.
 * <pre>{@code
 * b_ru = [b_ru_x b_ru_h]
 * b_c = [b_c_x b_c_h]
 * }</pre>
 * Another note on notation:
 * <pre>{@code
 * d_x = d_x_component_1 + d_x_component_2
 * 
 * where d_x_component_1 = d_r_bar * w_r_x^T + d_u_bar * w_r_x^T
 * and d_x_component_2 = d_c_bar * w_c_x^T
 * 
 * d_h_prev = d_h_prev_component_1 + d_h_prevr \circ r + d_h \circ u
 * where d_h_prev_componenet_1 = d_r_bar * w_r_h_prev^T + d_u_bar * w_r_h_prev^T
 * }</pre>
 * Mathematics behind the Gradients below:
 * <pre>{@code
 * d_c_bar = d_h \circ (1-u) \circ (1-c \circ c)
 * d_u_bar = d_h \circ (h-c) \circ u \circ (1-u)
 * 
 * d_r_bar_u_bar = [d_r_bar d_u_bar]
 * 
 * [d_x_component_1 d_h_prev_component_1] = d_r_bar_u_bar * w_ru^T
 * 
 * [d_x_component_2 d_h_prevr] = d_c_bar * w_c^T
 * 
 * d_x = d_x_component_1 + d_x_component_2
 * 
 * d_h_prev = d_h_prev_component_1 + d_h_prevr \circ r + u
 * }</pre>
 * Below calculation is performed in the python wrapper for the Gradients
 * (not in the gradient kernel.)
 * <pre>{@code
 * d_w_ru = x_h_prevr^T * d_c_bar
 * 
 * d_w_c = x_h_prev^T * d_r_bar_u_bar
 * 
 * d_b_ru = sum of d_r_bar_u_bar along axis = 0
 * 
 * d_b_c = sum of d_c_bar along axis = 0
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code dX()} output
 */
public final class GRUBlockCellGrad<T extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new GRUBlockCellGrad operation.
   * 
   * @param scope current scope
   * @param x 
   * @param hPrev 
   * @param wRu 
   * @param wC 
   * @param bRu 
   * @param bC 
   * @param r 
   * @param u 
   * @param c 
   * @param dH 
   * @return a new instance of GRUBlockCellGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> GRUBlockCellGrad<T> create(Scope scope, Operand<T> x, Operand<T> hPrev, Operand<T> wRu, Operand<T> wC, Operand<T> bRu, Operand<T> bC, Operand<T> r, Operand<T> u, Operand<T> c, Operand<T> dH) {
    OperationBuilder opBuilder = scope.env().opBuilder("GRUBlockCellGrad", scope.makeOpName("GRUBlockCellGrad"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(hPrev.asOutput());
    opBuilder.addInput(wRu.asOutput());
    opBuilder.addInput(wC.asOutput());
    opBuilder.addInput(bRu.asOutput());
    opBuilder.addInput(bC.asOutput());
    opBuilder.addInput(r.asOutput());
    opBuilder.addInput(u.asOutput());
    opBuilder.addInput(c.asOutput());
    opBuilder.addInput(dH.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new GRUBlockCellGrad<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> dX() {
    return dX;
  }
  
  /**
   */
  public Output<T> dHPrev() {
    return dHPrev;
  }
  
  /**
   */
  public Output<T> dCBar() {
    return dCBar;
  }
  
  /**
   */
  public Output<T> dRBarUBar() {
    return dRBarUBar;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "GRUBlockCellGrad";
  
  private Output<T> dX;
  private Output<T> dHPrev;
  private Output<T> dCBar;
  private Output<T> dRBarUBar;
  
  private GRUBlockCellGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    dX = operation.output(outputIdx++);
    dHPrev = operation.output(outputIdx++);
    dCBar = operation.output(outputIdx++);
    dRBarUBar = operation.output(outputIdx++);
  }
}
