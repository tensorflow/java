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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Gather specific elements from the TensorArray into output `value`.
 * <p>
 * All elements selected by `indices` must have the same shape.
 * 
 * @param <T> data type for {@code value()} output
 */
@Operator
public final class TensorArrayGather<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorArrayGather}
   */
  public static class Options {
    
    /**
     * @param elementShape The expected shape of an element, if known. Used to
     * validate the shapes of TensorArray elements. If this shape is not
     * fully specified, gathering zero-size TensorArrays is an error.
     */
    public Options elementShape(Shape elementShape) {
      this.elementShape = elementShape;
      return this;
    }
    
    private Shape elementShape;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TensorArrayGather operation.
   * 
   * @param scope current scope
   * @param handle The handle to a TensorArray.
   * @param indices The locations in the TensorArray from which to read tensor elements.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param options carries optional attributes values
   * @return a new instance of TensorArrayGather
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorArrayGather<T> create(Scope scope, Operand<?> handle, Operand<TInt32> indices, Operand<TFloat32> flowIn, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArrayGatherV3", scope.makeOpName("TensorArrayGather"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.elementShape != null) {
          opBuilder.setAttr("element_shape", opts.elementShape);
        }
      }
    }
    return new TensorArrayGather<T>(opBuilder.build());
  }
  
  /**
   * @param elementShape The expected shape of an element, if known. Used to
   * validate the shapes of TensorArray elements. If this shape is not
   * fully specified, gathering zero-size TensorArrays is an error.
   */
  public static Options elementShape(Shape elementShape) {
    return new Options().elementShape(elementShape);
  }
  
  /**
   * All of the elements in the TensorArray, concatenated along a new
   * axis (the new dimension 0).
   */
  public Output<T> value() {
    return value;
  }
  
  @Override
  public Output<T> asOutput() {
    return value;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorArrayGatherV3";
  
  private Output<T> value;
  
  private TensorArrayGather(Operation operation) {
    super(operation);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }
}
