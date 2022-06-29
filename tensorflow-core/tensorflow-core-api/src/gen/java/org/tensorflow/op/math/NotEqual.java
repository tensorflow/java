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

package org.tensorflow.op.math;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Returns the truth value of (x != y) element-wise.
 * <em>NOTE</em>: {@code math.NotEqual} supports broadcasting. More about broadcasting
 *  <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a>
 */
@OpMetadata(
    opType = NotEqual.OP_NAME,
    inputsClass = NotEqual.Inputs.class
)
@Operator(
    group = "math"
)
public final class NotEqual extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "NotEqual";

  private Output<TBool> z;

  public NotEqual(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new NotEqual operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @param options carries optional attribute values
   * @param <T> data type for {@code NotEqual} output and operands
   * @return a new instance of NotEqual
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> NotEqual create(Scope scope, Operand<T> x, Operand<T> y,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "NotEqual");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.incompatibleShapeError != null) {
          opBuilder.setAttr("incompatible_shape_error", opts.incompatibleShapeError);
        }
      }
    }
    return new NotEqual(opBuilder.build());
  }

  /**
   * Sets the incompatibleShapeError option.
   *
   * @param incompatibleShapeError the incompatibleShapeError option
   * @return this Options instance.
   */
  public static Options incompatibleShapeError(Boolean incompatibleShapeError) {
    return new Options().incompatibleShapeError(incompatibleShapeError);
  }

  /**
   * Gets z.
   *
   * @return z.
   */
  public Output<TBool> z() {
    return z;
  }

  @Override
  public Output<TBool> asOutput() {
    return z;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.math.NotEqual}
   */
  public static class Options {
    private Boolean incompatibleShapeError;

    private Options() {
    }

    /**
     * Sets the incompatibleShapeError option.
     *
     * @param incompatibleShapeError the incompatibleShapeError option
     * @return this Options instance.
     */
    public Options incompatibleShapeError(Boolean incompatibleShapeError) {
      this.incompatibleShapeError = incompatibleShapeError;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = NotEqual.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<NotEqual> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The y input
     */
    public final Operand<T> y;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The incompatibleShapeError attribute
     */
    public final boolean incompatibleShapeError;

    public Inputs(GraphOperation op) {
      super(new NotEqual(op), op, Arrays.asList("T", "incompatible_shape_error"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      y = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      incompatibleShapeError = op.attributes().getAttrBool("incompatible_shape_error");
    }
  }
}
