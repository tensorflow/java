/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Sets the index-th position of the list to contain the given tensor.
 * input_handle: the list
 * index: the position in the list to which the tensor will be assigned
 * item: the element to be assigned to that position
 * output_handle: the new list, with the element in the proper position
 */
@OpMetadata(
    opType = TensorListSetItem.OP_NAME,
    inputsClass = TensorListSetItem.Inputs.class
)
@Operator
public final class TensorListSetItem extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListSetItem";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  public TensorListSetItem(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListSetItem operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param index The index value
   * @param item The item value
   * @param options carries optional attribute values
   * @return a new instance of TensorListSetItem
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListSetItem create(Scope scope, Operand<? extends TType> inputHandle,
      Operand<TInt32> index, Operand<? extends TType> item, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListSetItem");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.addInput(item.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.resizeIfIndexOutOfBounds != null) {
          opBuilder.setAttr("resize_if_index_out_of_bounds", opts.resizeIfIndexOutOfBounds);
        }
      }
    }
    return new TensorListSetItem(opBuilder.build());
  }

  /**
   * Sets the resizeIfIndexOutOfBounds option.
   *
   * @param resizeIfIndexOutOfBounds the resizeIfIndexOutOfBounds option
   * @return this Options instance.
   */
  public static Options resizeIfIndexOutOfBounds(Boolean resizeIfIndexOutOfBounds) {
    return new Options().resizeIfIndexOutOfBounds(resizeIfIndexOutOfBounds);
  }

  /**
   * Gets outputHandle.
   *
   * @return outputHandle.
   */
  public Output<? extends TType> outputHandle() {
    return outputHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) outputHandle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorListSetItem}
   */
  public static class Options {
    private Boolean resizeIfIndexOutOfBounds;

    private Options() {
    }

    /**
     * Sets the resizeIfIndexOutOfBounds option.
     *
     * @param resizeIfIndexOutOfBounds the resizeIfIndexOutOfBounds option
     * @return this Options instance.
     */
    public Options resizeIfIndexOutOfBounds(Boolean resizeIfIndexOutOfBounds) {
      this.resizeIfIndexOutOfBounds = resizeIfIndexOutOfBounds;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TensorListSetItem.class
  )
  public static class Inputs extends RawOpInputs<TensorListSetItem> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The index input
     */
    public final Operand<TInt32> index;

    /**
     * The item input
     */
    public final Operand<? extends TType> item;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    /**
     * The resizeIfIndexOutOfBounds attribute
     */
    public final boolean resizeIfIndexOutOfBounds;

    public Inputs(GraphOperation op) {
      super(new TensorListSetItem(op), op, Arrays.asList("element_dtype", "resize_if_index_out_of_bounds"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      index = (Operand<TInt32>) op.input(inputIndex++);
      item = (Operand<? extends TType>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
      resizeIfIndexOutOfBounds = op.attributes().getAttrBool("resize_if_index_out_of_bounds");
    }
  }
}
