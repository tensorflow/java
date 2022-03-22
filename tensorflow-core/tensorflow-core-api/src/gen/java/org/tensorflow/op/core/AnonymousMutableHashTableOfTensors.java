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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Creates an empty anonymous mutable hash table of vector values.
 * This op creates a new anonymous mutable hash table (as a resource) everytime
 * it is executed, with the specified dtype of its keys and values,
 * returning the resource handle. Each value must be a vector.
 * Data can be inserted into the table using
 * the insert operations. It does not support the initialization operation.
 * The table is anonymous in the sense that it can only be
 * accessed by the returned resource handle (e.g. it cannot be looked up
 * by a name in a resource manager). The table will be automatically
 * deleted when all resource handles pointing to it are gone.
 */
@OpMetadata(
    opType = AnonymousMutableHashTableOfTensors.OP_NAME,
    inputsClass = AnonymousMutableHashTableOfTensors.Inputs.class
)
public final class AnonymousMutableHashTableOfTensors extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AnonymousMutableHashTableOfTensors";

  private Output<? extends TType> tableHandle;

  @SuppressWarnings("unchecked")
  public AnonymousMutableHashTableOfTensors(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tableHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AnonymousMutableHashTableOfTensors operation.
   *
   * @param scope current scope
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AnonymousMutableHashTableOfTensors} output and operands
   * @param <U> data type for {@code AnonymousMutableHashTableOfTensors} output and operands
   * @return a new instance of AnonymousMutableHashTableOfTensors
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TType> AnonymousMutableHashTableOfTensors create(
      Scope scope, Class<T> keyDtype, Class<U> valueDtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AnonymousMutableHashTableOfTensors");
    opBuilder.setAttr("key_dtype", Operands.toDataType(keyDtype));
    opBuilder.setAttr("value_dtype", Operands.toDataType(valueDtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.valueShape != null) {
          opBuilder.setAttr("value_shape", opts.valueShape);
        }
      }
    }
    return new AnonymousMutableHashTableOfTensors(opBuilder.build());
  }

  /**
   * Sets the valueShape option.
   *
   * @param valueShape the valueShape option
   * @return this Options instance.
   */
  public static Options valueShape(Shape valueShape) {
    return new Options().valueShape(valueShape);
  }

  /**
   * Gets tableHandle.
   * The resource handle to the newly created hash-table resource.
   * @return tableHandle.
   */
  public Output<? extends TType> tableHandle() {
    return tableHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) tableHandle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.AnonymousMutableHashTableOfTensors}
   */
  public static class Options {
    private Shape valueShape;

    private Options() {
    }

    /**
     * Sets the valueShape option.
     *
     * @param valueShape the valueShape option
     * @return this Options instance.
     */
    public Options valueShape(Shape valueShape) {
      this.valueShape = valueShape;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AnonymousMutableHashTableOfTensors.class
  )
  public static class Inputs extends RawOpInputs<AnonymousMutableHashTableOfTensors> {
    /**
     * Type of the table keys.
     */
    public final DataType keyDtype;

    /**
     * Type of the table values.
     */
    public final DataType valueDtype;

    /**
     * The valueShape attribute
     */
    public final Shape valueShape;

    public Inputs(GraphOperation op) {
      super(new AnonymousMutableHashTableOfTensors(op), op, Arrays.asList("key_dtype", "value_dtype", "value_shape"));
      int inputIndex = 0;
      keyDtype = op.attributes().getAttrType("key_dtype");
      valueDtype = op.attributes().getAttrType("value_dtype");
      valueShape = op.attributes().getAttrShape("value_shape");
    }
  }
}
