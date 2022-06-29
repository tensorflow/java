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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Stacks all tensors in the list.
 * Requires that all tensors have the same shape.
 * <p>input_handle: the input list
 * tensor: the gathered result
 * num_elements: optional. If not -1, the number of elements in the list.
 *
 * @param <T> data type for {@code tensor} output
 */
@OpMetadata(
    opType = TensorListStack.OP_NAME,
    inputsClass = TensorListStack.Inputs.class
)
@Operator
public final class TensorListStack<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListStack";

  private Output<T> tensor;

  public TensorListStack(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListStack operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param elementShape The elementShape value
   * @param elementDtype The value of the elementDtype attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorListStack} output and operands
   * @return a new instance of TensorListStack
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorListStack<T> create(Scope scope,
      Operand<? extends TType> inputHandle, Operand<TInt32> elementShape, Class<T> elementDtype,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListStack");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.numElements != null) {
          opBuilder.setAttr("num_elements", opts.numElements);
        }
      }
    }
    return new TensorListStack<>(opBuilder.build());
  }

  /**
   * Sets the numElements option.
   *
   * @param numElements the numElements option
   * @return this Options instance.
   */
  public static Options numElements(Long numElements) {
    return new Options().numElements(numElements);
  }

  /**
   * Gets tensor.
   *
   * @return tensor.
   */
  public Output<T> tensor() {
    return tensor;
  }

  @Override
  public Output<T> asOutput() {
    return tensor;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorListStack}
   */
  public static class Options {
    private Long numElements;

    private Options() {
    }

    /**
     * Sets the numElements option.
     *
     * @param numElements the numElements option
     * @return this Options instance.
     */
    public Options numElements(Long numElements) {
      this.numElements = numElements;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TensorListStack.class
  )
  public static class Inputs extends RawOpInputs<TensorListStack<?>> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The elementShape input
     */
    public final Operand<TInt32> elementShape;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    /**
     * The numElements attribute
     */
    public final long numElements;

    public Inputs(GraphOperation op) {
      super(new TensorListStack<>(op), op, Arrays.asList("element_dtype", "num_elements"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      elementShape = (Operand<TInt32>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
      numElements = op.attributes().getAttrInt("num_elements");
    }
  }
}
