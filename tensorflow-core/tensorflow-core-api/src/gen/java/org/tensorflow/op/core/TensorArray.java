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
 * An array of Tensors of given size.
 * <p>
 * Write data via Write and read via Read or Pack.
 */
@Operator
public final class TensorArray extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorArray}
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
    
    /**
     * @param dynamicSize A boolean that determines whether writes to the TensorArray
     * are allowed to grow the size.  By default, this is not allowed.
     */
    public Options dynamicSize(Boolean dynamicSize) {
      this.dynamicSize = dynamicSize;
      return this;
    }
    
    /**
     * @param clearAfterRead If true (default), Tensors in the TensorArray are cleared
     * after being read.  This disables multiple read semantics but allows early
     * release of memory.
     */
    public Options clearAfterRead(Boolean clearAfterRead) {
      this.clearAfterRead = clearAfterRead;
      return this;
    }
    
    /**
     * @param identicalElementShapes If true (default is false), then all
     * elements in the TensorArray will be expected to have have identical shapes.
     * This allows certain behaviors, like dynamically checking for
     * consistent shapes on write, and being able to fill in properly
     * shaped zero tensors on stack -- even if the element_shape attribute
     * is not fully defined.
     */
    public Options identicalElementShapes(Boolean identicalElementShapes) {
      this.identicalElementShapes = identicalElementShapes;
      return this;
    }
    
    /**
     * @param tensorArrayName Overrides the name used for the temporary tensor_array
     * resource. Default value is the name of the 'TensorArray' op (which
     * is guaranteed unique).
     */
    public Options tensorArrayName(String tensorArrayName) {
      this.tensorArrayName = tensorArrayName;
      return this;
    }
    
    private Shape elementShape;
    private Boolean dynamicSize;
    private Boolean clearAfterRead;
    private Boolean identicalElementShapes;
    private String tensorArrayName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TensorArray operation.
   * 
   * @param scope current scope
   * @param size The size of the array.
   * @param dtype The type of the elements on the tensor_array.
   * @param options carries optional attributes values
   * @return a new instance of TensorArray
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorArray create(Scope scope, Operand<TInt32> size, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArrayV3", scope.makeOpName("TensorArray"));
    opBuilder.addInput(size.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.elementShape != null) {
          opBuilder.setAttr("element_shape", opts.elementShape);
        }
        if (opts.dynamicSize != null) {
          opBuilder.setAttr("dynamic_size", opts.dynamicSize);
        }
        if (opts.clearAfterRead != null) {
          opBuilder.setAttr("clear_after_read", opts.clearAfterRead);
        }
        if (opts.identicalElementShapes != null) {
          opBuilder.setAttr("identical_element_shapes", opts.identicalElementShapes);
        }
        if (opts.tensorArrayName != null) {
          opBuilder.setAttr("tensor_array_name", opts.tensorArrayName);
        }
      }
    }
    return new TensorArray(opBuilder.build());
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
   * @param dynamicSize A boolean that determines whether writes to the TensorArray
   * are allowed to grow the size.  By default, this is not allowed.
   */
  public static Options dynamicSize(Boolean dynamicSize) {
    return new Options().dynamicSize(dynamicSize);
  }
  
  /**
   * @param clearAfterRead If true (default), Tensors in the TensorArray are cleared
   * after being read.  This disables multiple read semantics but allows early
   * release of memory.
   */
  public static Options clearAfterRead(Boolean clearAfterRead) {
    return new Options().clearAfterRead(clearAfterRead);
  }
  
  /**
   * @param identicalElementShapes If true (default is false), then all
   * elements in the TensorArray will be expected to have have identical shapes.
   * This allows certain behaviors, like dynamically checking for
   * consistent shapes on write, and being able to fill in properly
   * shaped zero tensors on stack -- even if the element_shape attribute
   * is not fully defined.
   */
  public static Options identicalElementShapes(Boolean identicalElementShapes) {
    return new Options().identicalElementShapes(identicalElementShapes);
  }
  
  /**
   * @param tensorArrayName Overrides the name used for the temporary tensor_array
   * resource. Default value is the name of the 'TensorArray' op (which
   * is guaranteed unique).
   */
  public static Options tensorArrayName(String tensorArrayName) {
    return new Options().tensorArrayName(tensorArrayName);
  }
  
  /**
   * The handle to the TensorArray.
   */
  public Output<?> handle() {
    return handle;
  }
  
  /**
   * A scalar used to control gradient flow.
   */
  public Output<TFloat32> flow() {
    return flow;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorArrayV3";
  
  private Output<?> handle;
  private Output<TFloat32> flow;
  
  private TensorArray(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    flow = operation.output(outputIdx++);
  }
}
