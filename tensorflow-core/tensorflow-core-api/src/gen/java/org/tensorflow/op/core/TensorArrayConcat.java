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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Concat the elements from the TensorArray into value `value`.
 * <p>
 * Takes `T` elements of shapes
 * <p>
 *   <pre>{@code
 *   (n0 x d0 x d1 x ...), (n1 x d0 x d1 x ...), ..., (n(T-1) x d0 x d1 x ...)
 *   }</pre>
 * and concatenates them into a Tensor of shape:
 * <p>
 *   <pre>{@code
 * (n0 + n1 + ... + n(T-1) x d0 x d1 x ...)}</pre>
 * All elements must have the same shape (excepting the first dimension).
 * 
 * @param <T> data type for {@code value()} output
 */
@Operator
public final class TensorArrayConcat<T extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorArrayConcat}
   */
  public static class Options {
    
    /**
     * @param elementShapeExcept0 The expected shape of an element, if known,
     * excluding the first dimension. Used to validate the shapes of
     * TensorArray elements. If this shape is not fully specified, concatenating
     * zero-size TensorArrays is an error.
     */
    public Options elementShapeExcept0(Shape elementShapeExcept0) {
      this.elementShapeExcept0 = elementShapeExcept0;
      return this;
    }
    
    private Shape elementShapeExcept0;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TensorArrayConcat operation.
   * 
   * @param scope current scope
   * @param handle The handle to a TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param options carries optional attributes values
   * @return a new instance of TensorArrayConcat
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorArrayConcat<T> create(Scope scope, Operand<?> handle, Operand<TFloat32> flowIn, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArrayConcatV3", scope.makeOpName("TensorArrayConcat"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.elementShapeExcept0 != null) {
          opBuilder.setAttr("element_shape_except0", opts.elementShapeExcept0);
        }
      }
    }
    return new TensorArrayConcat<T>(opBuilder.build());
  }
  
  /**
   * @param elementShapeExcept0 The expected shape of an element, if known,
   * excluding the first dimension. Used to validate the shapes of
   * TensorArray elements. If this shape is not fully specified, concatenating
   * zero-size TensorArrays is an error.
   */
  public static Options elementShapeExcept0(Shape elementShapeExcept0) {
    return new Options().elementShapeExcept0(elementShapeExcept0);
  }
  
  /**
   * All of the elements in the TensorArray, concatenated along the first
   * axis.
   */
  public Output<T> value() {
    return value;
  }
  
  /**
   * A vector of the row sizes of the original T elements in the
   * value output.  In the example above, this would be the values:
   * `(n1, n2, ..., n(T-1))`.
   */
  public Output<TInt64> lengths() {
    return lengths;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorArrayConcatV3";
  
  private Output<T> value;
  private Output<TInt64> lengths;
  
  private TensorArrayConcat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
    lengths = operation.output(outputIdx++);
  }
}
