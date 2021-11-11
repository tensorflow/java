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
 * Creates a tensor with the given shape.
 * <p>This operation creates a tensor of {@code shape} and {@code dtype}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Empty.OP_NAME,
    inputsClass = Empty.Inputs.class
)
@Operator
public final class Empty<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Empty";

  private Output<T> output;

  public Empty(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Empty operation.
   *
   * @param scope current scope
   * @param shape 1-D. Represents the shape of the output tensor.
   * @param dtype The value of the dtype attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code Empty} output and operands
   * @return a new instance of Empty
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Empty<T> create(Scope scope, Operand<TInt32> shape,
      Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Empty");
    opBuilder.addInput(shape.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.init != null) {
          opBuilder.setAttr("init", opts.init);
        }
      }
    }
    return new Empty<>(opBuilder.build());
  }

  /**
   * Sets the init option.
   *
   * @param init If True, initialize the returned tensor with the default value of dtype.  Otherwise, the implementation is free not to initializethe tensor's content.
   * @return this Options instance.
   */
  public static Options init(Boolean init) {
    return new Options().init(init);
  }

  /**
   * Gets output.
   * A {@code Tensor} of type {@code T}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Empty}
   */
  public static class Options {
    private Boolean init;

    private Options() {
    }

    /**
     * Sets the init option.
     *
     * @param init If True, initialize the returned tensor with the default value of dtype.  Otherwise, the implementation is free not to initializethe tensor's content.
     * @return this Options instance.
     */
    public Options init(Boolean init) {
      this.init = init;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Empty.class
  )
  public static class Inputs extends RawOpInputs<Empty<?>> {
    /**
     * 1-D. Represents the shape of the output tensor.
     */
    public final Operand<TInt32> shape;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    /**
     * If True, initialize the returned tensor with the default value of dtype.  Otherwise, the implementation is free not to initializethe tensor's content.
     */
    public final boolean init;

    public Inputs(GraphOperation op) {
      super(new Empty<>(op), op, Arrays.asList("dtype", "init"));
      int inputIndex = 0;
      shape = (Operand<TInt32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      init = op.attributes().getAttrBool("init");
    }
  }
}
