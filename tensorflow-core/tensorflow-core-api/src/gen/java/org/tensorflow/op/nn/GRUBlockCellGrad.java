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
 * Computes the GRU cell back-propagation for 1 time step.
 * Args
 * x: Input to the GRU cell.
 * h_prev: State input from the previous GRU cell.
 * w_ru: Weight matrix for the reset and update gate.
 * w_c: Weight matrix for the cell connection gate.
 * b_ru: Bias vector for the reset and update gate.
 * b_c: Bias vector for the cell connection gate.
 * r: Output of the reset gate.
 * u: Output of the update gate.
 * c: Output of the cell connection gate.
 * d_h: Gradients of the h_new wrt to objective function.
 * <p>Returns
 * d_x: Gradients of the x wrt to objective function.
 * d_h_prev: Gradients of the h wrt to objective function.
 * d_c_bar Gradients of the c_bar wrt to objective function.
 * d_r_bar_u_bar Gradients of the r_bar &amp; u_bar wrt to objective function.
 * <p>This kernel op implements the following mathematical equations:
 * <p>Note on notation of the variables:
 * <p>Concatenation of a and b is represented by a_b
 * Element-wise dot product of a and b is represented by ab
 * Element-wise dot product is represented by \circ
 * Matrix multiplication is represented by *
 * <p>Additional notes for clarity:
 * <p>{@code w_ru} can be segmented into 4 different matrices.
 * <pre>
 * w_ru = [w_r_x w_u_x
 *         w_r_h_prev w_u_h_prev]
 * </pre>
 * <p>Similarly, {@code w_c} can be segmented into 2 different matrices.
 * <pre>
 * w_c = [w_c_x w_c_h_prevr]
 * </pre>
 * <p>Same goes for biases.
 * <pre>
 * b_ru = [b_ru_x b_ru_h]
 * b_c = [b_c_x b_c_h]
 * </pre>
 * <p>Another note on notation:
 * <pre>
 * d_x = d_x_component_1 + d_x_component_2
 *
 * where d_x_component_1 = d_r_bar * w_r_x^T + d_u_bar * w_r_x^T
 * and d_x_component_2 = d_c_bar * w_c_x^T
 *
 * d_h_prev = d_h_prev_component_1 + d_h_prevr \circ r + d_h \circ u
 * where d_h_prev_componenet_1 = d_r_bar * w_r_h_prev^T + d_u_bar * w_r_h_prev^T
 * </pre>
 * <p>Mathematics behind the Gradients below:
 * <pre>
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
 * </pre>
 * <p>Below calculation is performed in the python wrapper for the Gradients
 * (not in the gradient kernel.)
 * <pre>
 * d_w_ru = x_h_prevr^T * d_c_bar
 *
 * d_w_c = x_h_prev^T * d_r_bar_u_bar
 *
 * d_b_ru = sum of d_r_bar_u_bar along axis = 0
 *
 * d_b_c = sum of d_c_bar along axis = 0
 * </pre>
 *
 * @param <T> data type for {@code d_x} output
 */
@OpMetadata(
    opType = GRUBlockCellGrad.OP_NAME,
    inputsClass = GRUBlockCellGrad.Inputs.class
)
public final class GRUBlockCellGrad<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GRUBlockCellGrad";

  private Output<T> dX;

  private Output<T> dHPrev;

  private Output<T> dCBar;

  private Output<T> dRBarUBar;

  public GRUBlockCellGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    dX = operation.output(outputIdx++);
    dHPrev = operation.output(outputIdx++);
    dCBar = operation.output(outputIdx++);
    dRBarUBar = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GRUBlockCellGrad operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param hPrev The hPrev value
   * @param wRu The wRu value
   * @param wC The wC value
   * @param bRu The bRu value
   * @param bC The bC value
   * @param r The r value
   * @param u The u value
   * @param c The c value
   * @param dH The dH value
   * @param <T> data type for {@code GRUBlockCellGrad} output and operands
   * @return a new instance of GRUBlockCellGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> GRUBlockCellGrad<T> create(Scope scope, Operand<T> x,
      Operand<T> hPrev, Operand<T> wRu, Operand<T> wC, Operand<T> bRu, Operand<T> bC, Operand<T> r,
      Operand<T> u, Operand<T> c, Operand<T> dH) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GRUBlockCellGrad");
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
    return new GRUBlockCellGrad<>(opBuilder.build());
  }

  /**
   * Gets dX.
   *
   * @return dX.
   */
  public Output<T> dX() {
    return dX;
  }

  /**
   * Gets dHPrev.
   *
   * @return dHPrev.
   */
  public Output<T> dHPrev() {
    return dHPrev;
  }

  /**
   * Gets dCBar.
   *
   * @return dCBar.
   */
  public Output<T> dCBar() {
    return dCBar;
  }

  /**
   * Gets dRBarUBar.
   *
   * @return dRBarUBar.
   */
  public Output<T> dRBarUBar() {
    return dRBarUBar;
  }

  @OpInputsMetadata(
      outputsClass = GRUBlockCellGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<GRUBlockCellGrad<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The hPrev input
     */
    public final Operand<T> hPrev;

    /**
     * The wRu input
     */
    public final Operand<T> wRu;

    /**
     * The wC input
     */
    public final Operand<T> wC;

    /**
     * The bRu input
     */
    public final Operand<T> bRu;

    /**
     * The bC input
     */
    public final Operand<T> bC;

    /**
     * The r input
     */
    public final Operand<T> r;

    /**
     * The u input
     */
    public final Operand<T> u;

    /**
     * The c input
     */
    public final Operand<T> c;

    /**
     * The dH input
     */
    public final Operand<T> dH;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new GRUBlockCellGrad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      hPrev = (Operand<T>) op.input(inputIndex++);
      wRu = (Operand<T>) op.input(inputIndex++);
      wC = (Operand<T>) op.input(inputIndex++);
      bRu = (Operand<T>) op.input(inputIndex++);
      bC = (Operand<T>) op.input(inputIndex++);
      r = (Operand<T>) op.input(inputIndex++);
      u = (Operand<T>) op.input(inputIndex++);
      c = (Operand<T>) op.input(inputIndex++);
      dH = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
