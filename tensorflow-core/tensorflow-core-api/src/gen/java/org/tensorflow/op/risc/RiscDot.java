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

package org.tensorflow.op.risc;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscDot operation
 *
 * @param <T> data type for {@code product} output
 */
public final class RiscDot<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscDot";

  private Output<T> product;

  private RiscDot(Operation operation) {
    super(operation);
    int outputIdx = 0;
    product = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscDot operation.
   *
   * @param scope current scope
   * @param a the a value
   * @param b the b value
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscDot} output and operands
   * @return a new instance of RiscDot
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscDot<T> create(Scope scope, Operand<T> a, Operand<T> b,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscDot");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.transposeA != null) {
          opBuilder.setAttr("transpose_a", opts.transposeA);
        }
        if (opts.transposeB != null) {
          opBuilder.setAttr("transpose_b", opts.transposeB);
        }
      }
    }
    return new RiscDot<>(opBuilder.build());
  }

  /**
   * Sets the transposeA option.
   *
   * @param transposeA the transposeA option
   * @return this Options instance.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }

  /**
   * Sets the transposeB option.
   *
   * @param transposeB the transposeB option
   * @return this Options instance.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }

  /**
   * Gets product.
   *
   * @return product.
   */
  public Output<T> product() {
    return product;
  }

  @Override
  public Output<T> asOutput() {
    return product;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.risc.RiscDot}
   */
  public static class Options {
    private Boolean transposeA;

    private Boolean transposeB;

    private Options() {
    }

    /**
     * Sets the transposeA option.
     *
     * @param transposeA the transposeA option
     * @return this Options instance.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }

    /**
     * Sets the transposeB option.
     *
     * @param transposeB the transposeB option
     * @return this Options instance.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }
  }
}
