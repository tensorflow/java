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

package org.tensorflow.op.dtypes;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Cast x of type SrcT to y of DstT.
 *
 * @param <U> data type for {@code y} output
 */
@Operator(
    group = "dtypes"
)
public final class Cast<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Cast";

  private Output<U> y;

  private Cast(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Cast operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param DstT the value of the DstT property
   * @param options carries optional attribute values
   * @param <U> data type for {@code Cast} output and operands
   * @return a new instance of Cast
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> Cast<U> create(Scope scope, Operand<? extends TType> x,
      Class<U> DstT, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Cast", scope.makeOpName("Cast"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("DstT", Operands.toDataType(DstT));
    if (options != null) {
      for (Options opts : options) {
        if (opts.Truncate != null) {
          opBuilder.setAttr("Truncate", opts.Truncate);
        }
      }
    }
    return new Cast<>(opBuilder.build());
  }

  /**
   * Sets the Truncate option.
   *
   * @param Truncate the Truncate option
   * @return this Options instance.
   */
  public static Options Truncate(Boolean Truncate) {
    return new Options().Truncate(Truncate);
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<U> y() {
    return y;
  }

  @Override
  public Output<U> asOutput() {
    return y;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.dtypes.Cast}
   */
  public static class Options {
    private Boolean Truncate;

    private Options() {
    }

    /**
     * Sets the Truncate option.
     *
     * @param Truncate the Truncate option
     * @return this Options instance.
     */
    public Options Truncate(Boolean Truncate) {
      this.Truncate = Truncate;
      return this;
    }
  }
}
