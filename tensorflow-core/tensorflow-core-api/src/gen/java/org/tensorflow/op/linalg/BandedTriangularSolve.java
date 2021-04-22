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

package org.tensorflow.op.linalg;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * The BandedTriangularSolve operation
 *
 * @param <T> data type for {@code output} output
 */
public final class BandedTriangularSolve<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BandedTriangularSolve";

  private Output<T> output;

  private BandedTriangularSolve(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BandedTriangularSolve operation.
   *
   * @param scope current scope
   * @param matrix the matrix value
   * @param rhs the rhs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BandedTriangularSolve} output and operands
   * @return a new instance of BandedTriangularSolve
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BandedTriangularSolve<T> create(Scope scope, Operand<T> matrix,
      Operand<T> rhs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BandedTriangularSolve", scope.makeOpName("BandedTriangularSolve"));
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.lower != null) {
          opBuilder.setAttr("lower", opts.lower);
        }
        if (opts.adjoint != null) {
          opBuilder.setAttr("adjoint", opts.adjoint);
        }
      }
    }
    return new BandedTriangularSolve<>(opBuilder.build());
  }

  /**
   * Sets the lower option.
   *
   * @param lower the lower option
   * @return this Options instance.
   */
  public static Options lower(Boolean lower) {
    return new Options().lower(lower);
  }

  /**
   * Sets the adjoint option.
   *
   * @param adjoint the adjoint option
   * @return this Options instance.
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.BandedTriangularSolve}
   */
  public static class Options {
    private Boolean lower;

    private Boolean adjoint;

    private Options() {
    }

    /**
     * Sets the lower option.
     *
     * @param lower the lower option
     * @return this Options instance.
     */
    public Options lower(Boolean lower) {
      this.lower = lower;
      return this;
    }

    /**
     * Sets the adjoint option.
     *
     * @param adjoint the adjoint option
     * @return this Options instance.
     */
    public Options adjoint(Boolean adjoint) {
      this.adjoint = adjoint;
      return this;
    }
  }
}
