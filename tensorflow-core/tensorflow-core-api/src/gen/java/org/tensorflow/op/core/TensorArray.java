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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * An array of Tensors of given size.
 * Write data via Write and read via Read or Pack.
 */
@OpMetadata(
    opType = TensorArray.OP_NAME,
    inputsClass = TensorArray.Inputs.class
)
@Operator
public final class TensorArray extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorArrayV3";

  private Output<? extends TType> handle;

  private Output<TFloat32> flow;

  @SuppressWarnings("unchecked")
  public TensorArray(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    flow = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorArrayV3 operation.
   *
   * @param scope current scope
   * @param sizeOutput The size of the array.
   * @param dtype The type of the elements on the tensor_array.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorArrayV3} output and operands
   * @return a new instance of TensorArray
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorArray create(Scope scope, Operand<TInt32> sizeOutput,
      Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorArray");
    opBuilder.addInput(sizeOutput.asOutput());
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
   * Sets the elementShape option.
   *
   * @param elementShape The expected shape of an element, if known. Used to
   * validate the shapes of TensorArray elements. If this shape is not
   * fully specified, gathering zero-size TensorArrays is an error.
   * @return this Options instance.
   */
  public static Options elementShape(Shape elementShape) {
    return new Options().elementShape(elementShape);
  }

  /**
   * Sets the dynamicSize option.
   *
   * @param dynamicSize A boolean that determines whether writes to the TensorArray
   * are allowed to grow the size.  By default, this is not allowed.
   * @return this Options instance.
   */
  public static Options dynamicSize(Boolean dynamicSize) {
    return new Options().dynamicSize(dynamicSize);
  }

  /**
   * Sets the clearAfterRead option.
   *
   * @param clearAfterRead If true (default), Tensors in the TensorArray are cleared
   * after being read.  This disables multiple read semantics but allows early
   * release of memory.
   * @return this Options instance.
   */
  public static Options clearAfterRead(Boolean clearAfterRead) {
    return new Options().clearAfterRead(clearAfterRead);
  }

  /**
   * Sets the identicalElementShapes option.
   *
   * @param identicalElementShapes If true (default is false), then all
   * elements in the TensorArray will be expected to have identical shapes.
   * This allows certain behaviors, like dynamically checking for
   * consistent shapes on write, and being able to fill in properly
   * shaped zero tensors on stack -- even if the element_shape attribute
   * is not fully defined.
   * @return this Options instance.
   */
  public static Options identicalElementShapes(Boolean identicalElementShapes) {
    return new Options().identicalElementShapes(identicalElementShapes);
  }

  /**
   * Sets the tensorArrayName option.
   *
   * @param tensorArrayName Overrides the name used for the temporary tensor_array
   * resource. Default value is the name of the 'TensorArray' op (which
   * is guaranteed unique).
   * @return this Options instance.
   */
  public static Options tensorArrayName(String tensorArrayName) {
    return new Options().tensorArrayName(tensorArrayName);
  }

  /**
   * Gets handle.
   * The handle to the TensorArray.
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  /**
   * Gets flow.
   * A scalar used to control gradient flow.
   * @return flow.
   */
  public Output<TFloat32> flow() {
    return flow;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorArray}
   */
  public static class Options {
    private Shape elementShape;

    private Boolean dynamicSize;

    private Boolean clearAfterRead;

    private Boolean identicalElementShapes;

    private String tensorArrayName;

    private Options() {
    }

    /**
     * Sets the elementShape option.
     *
     * @param elementShape The expected shape of an element, if known. Used to
     * validate the shapes of TensorArray elements. If this shape is not
     * fully specified, gathering zero-size TensorArrays is an error.
     * @return this Options instance.
     */
    public Options elementShape(Shape elementShape) {
      this.elementShape = elementShape;
      return this;
    }

    /**
     * Sets the dynamicSize option.
     *
     * @param dynamicSize A boolean that determines whether writes to the TensorArray
     * are allowed to grow the size.  By default, this is not allowed.
     * @return this Options instance.
     */
    public Options dynamicSize(Boolean dynamicSize) {
      this.dynamicSize = dynamicSize;
      return this;
    }

    /**
     * Sets the clearAfterRead option.
     *
     * @param clearAfterRead If true (default), Tensors in the TensorArray are cleared
     * after being read.  This disables multiple read semantics but allows early
     * release of memory.
     * @return this Options instance.
     */
    public Options clearAfterRead(Boolean clearAfterRead) {
      this.clearAfterRead = clearAfterRead;
      return this;
    }

    /**
     * Sets the identicalElementShapes option.
     *
     * @param identicalElementShapes If true (default is false), then all
     * elements in the TensorArray will be expected to have identical shapes.
     * This allows certain behaviors, like dynamically checking for
     * consistent shapes on write, and being able to fill in properly
     * shaped zero tensors on stack -- even if the element_shape attribute
     * is not fully defined.
     * @return this Options instance.
     */
    public Options identicalElementShapes(Boolean identicalElementShapes) {
      this.identicalElementShapes = identicalElementShapes;
      return this;
    }

    /**
     * Sets the tensorArrayName option.
     *
     * @param tensorArrayName Overrides the name used for the temporary tensor_array
     * resource. Default value is the name of the 'TensorArray' op (which
     * is guaranteed unique).
     * @return this Options instance.
     */
    public Options tensorArrayName(String tensorArrayName) {
      this.tensorArrayName = tensorArrayName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TensorArray.class
  )
  public static class Inputs extends RawOpInputs<TensorArray> {
    /**
     * The size of the array.
     */
    public final Operand<TInt32> sizeOutput;

    /**
     * The type of the elements on the tensor_array.
     */
    public final DataType dtype;

    /**
     * The expected shape of an element, if known. Used to
     * validate the shapes of TensorArray elements. If this shape is not
     * fully specified, gathering zero-size TensorArrays is an error.
     */
    public final Shape elementShape;

    /**
     * A boolean that determines whether writes to the TensorArray
     * are allowed to grow the size.  By default, this is not allowed.
     */
    public final boolean dynamicSize;

    /**
     * If true (default), Tensors in the TensorArray are cleared
     * after being read.  This disables multiple read semantics but allows early
     * release of memory.
     */
    public final boolean clearAfterRead;

    /**
     * If true (default is false), then all
     * elements in the TensorArray will be expected to have identical shapes.
     * This allows certain behaviors, like dynamically checking for
     * consistent shapes on write, and being able to fill in properly
     * shaped zero tensors on stack -- even if the element_shape attribute
     * is not fully defined.
     */
    public final boolean identicalElementShapes;

    /**
     * Overrides the name used for the temporary tensor_array
     * resource. Default value is the name of the 'TensorArray' op (which
     * is guaranteed unique).
     */
    public final String tensorArrayName;

    public Inputs(GraphOperation op) {
      super(new TensorArray(op), op, Arrays.asList("dtype", "element_shape", "dynamic_size", "clear_after_read", "identical_element_shapes", "tensor_array_name"));
      int inputIndex = 0;
      sizeOutput = (Operand<TInt32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      elementShape = op.attributes().getAttrShape("element_shape");
      dynamicSize = op.attributes().getAttrBool("dynamic_size");
      clearAfterRead = op.attributes().getAttrBool("clear_after_read");
      identicalElementShapes = op.attributes().getAttrBool("identical_element_shapes");
      tensorArrayName = op.attributes().getAttrString("tensor_array_name");
    }
  }
}
