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

package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Concat the elements from the TensorArray into value {@code value}.
 * Takes {@code T} elements of shapes
 * <pre>
 * (n0 x d0 x d1 x ...), (n1 x d0 x d1 x ...), ..., (n(T-1) x d0 x d1 x ...)
 * </pre>
 * <p>and concatenates them into a Tensor of shape:
 * <p>{@code (n0 + n1 + ... + n(T-1) x d0 x d1 x ...)}
 * <p>All elements must have the same shape (excepting the first dimension).
 *
 * @param <T> data type for {@code value} output
 */
@OpMetadata(
    opType = TensorArrayConcat.OP_NAME,
    inputsClass = TensorArrayConcat.Inputs.class
)
@Operator
public final class TensorArrayConcat<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorArrayConcatV3";

  private Output<T> value;

  private Output<TInt64> lengths;

  public TensorArrayConcat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
    lengths = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorArrayConcatV3 operation.
   *
   * @param scope current scope
   * @param handle The handle to a TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorArrayConcatV3} output and operands
   * @return a new instance of TensorArrayConcat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorArrayConcat<T> create(Scope scope,
      Operand<? extends TType> handle, Operand<TFloat32> flowIn, Class<T> dtype,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorArrayConcat");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.elementShapeExcept0 != null) {
          opBuilder.setAttr("element_shape_except0", opts.elementShapeExcept0);
        }
      }
    }
    return new TensorArrayConcat<>(opBuilder.build());
  }

  /**
   * Sets the elementShapeExcept0 option.
   *
   * @param elementShapeExcept0 The expected shape of an element, if known,
   * excluding the first dimension. Used to validate the shapes of
   * TensorArray elements. If this shape is not fully specified, concatenating
   * zero-size TensorArrays is an error.
   * @return this Options instance.
   */
  public static Options elementShapeExcept0(Shape elementShapeExcept0) {
    return new Options().elementShapeExcept0(elementShapeExcept0);
  }

  /**
   * Gets value.
   * All of the elements in the TensorArray, concatenated along the first
   * axis.
   * @return value.
   */
  public Output<T> value() {
    return value;
  }

  /**
   * Gets lengths.
   * A vector of the row sizes of the original T elements in the
   * value output.  In the example above, this would be the values:
   * {@code (n1, n2, ..., n(T-1))}.
   * @return lengths.
   */
  public Output<TInt64> lengths() {
    return lengths;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorArrayConcat}
   */
  public static class Options {
    private Shape elementShapeExcept0;

    private Options() {
    }

    /**
     * Sets the elementShapeExcept0 option.
     *
     * @param elementShapeExcept0 The expected shape of an element, if known,
     * excluding the first dimension. Used to validate the shapes of
     * TensorArray elements. If this shape is not fully specified, concatenating
     * zero-size TensorArrays is an error.
     * @return this Options instance.
     */
    public Options elementShapeExcept0(Shape elementShapeExcept0) {
      this.elementShapeExcept0 = elementShapeExcept0;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TensorArrayConcat.class
  )
  public static class Inputs extends RawOpInputs<TensorArrayConcat<?>> {
    /**
     * The handle to a TensorArray.
     */
    public final Operand<? extends TType> handle;

    /**
     * A float scalar that enforces proper chaining of operations.
     */
    public final Operand<TFloat32> flowIn;

    /**
     * The type of the elem that is returned.
     */
    public final DataType dtype;

    /**
     * The expected shape of an element, if known,
     * excluding the first dimension. Used to validate the shapes of
     * TensorArray elements. If this shape is not fully specified, concatenating
     * zero-size TensorArrays is an error.
     */
    public final Shape elementShapeExcept0;

    public Inputs(GraphOperation op) {
      super(new TensorArrayConcat<>(op), op, Arrays.asList("dtype", "element_shape_except0"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      flowIn = (Operand<TFloat32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      elementShapeExcept0 = op.attributes().getAttrShape("element_shape_except0");
    }
  }
}
