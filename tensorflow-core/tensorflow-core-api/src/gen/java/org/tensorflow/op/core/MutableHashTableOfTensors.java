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
import org.tensorflow.types.family.TType;

/**
 * Creates an empty hash table.
 * This op creates a mutable hash table, specifying the type of its keys and
 * values. Each value must be a vector. Data can be inserted into the table using
 * the insert operations. It does not support the initialization operation.
 */
@OpMetadata(
    opType = MutableHashTableOfTensors.OP_NAME,
    inputsClass = MutableHashTableOfTensors.Inputs.class
)
@Operator
public final class MutableHashTableOfTensors extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MutableHashTableOfTensorsV2";

  private Output<? extends TType> tableHandle;

  @SuppressWarnings("unchecked")
  public MutableHashTableOfTensors(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tableHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MutableHashTableOfTensorsV2 operation.
   *
   * @param scope current scope
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MutableHashTableOfTensorsV2} output and operands
   * @param <U> data type for {@code MutableHashTableOfTensorsV2} output and operands
   * @return a new instance of MutableHashTableOfTensors
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TType> MutableHashTableOfTensors create(Scope scope,
      Class<T> keyDtype, Class<U> valueDtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MutableHashTableOfTensors");
    opBuilder.setAttr("key_dtype", Operands.toDataType(keyDtype));
    opBuilder.setAttr("value_dtype", Operands.toDataType(valueDtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.useNodeNameSharing != null) {
          opBuilder.setAttr("use_node_name_sharing", opts.useNodeNameSharing);
        }
        if (opts.valueShape != null) {
          opBuilder.setAttr("value_shape", opts.valueShape);
        }
      }
    }
    return new MutableHashTableOfTensors(opBuilder.build());
  }

  /**
   * Sets the container option.
   *
   * @param container If non-empty, this table is placed in the given container.
   * Otherwise, a default container is used.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName If non-empty, this table is shared under the given name across
   * multiple sessions.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Sets the useNodeNameSharing option.
   *
   * @param useNodeNameSharing the useNodeNameSharing option
   * @return this Options instance.
   */
  public static Options useNodeNameSharing(Boolean useNodeNameSharing) {
    return new Options().useNodeNameSharing(useNodeNameSharing);
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
   * Handle to a table.
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
   * Optional attributes for {@link org.tensorflow.op.core.MutableHashTableOfTensors}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private Boolean useNodeNameSharing;

    private Shape valueShape;

    private Options() {
    }

    /**
     * Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     * Otherwise, a default container is used.
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     * multiple sessions.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }

    /**
     * Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing the useNodeNameSharing option
     * @return this Options instance.
     */
    public Options useNodeNameSharing(Boolean useNodeNameSharing) {
      this.useNodeNameSharing = useNodeNameSharing;
      return this;
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
      outputsClass = MutableHashTableOfTensors.class
  )
  public static class Inputs extends RawOpInputs<MutableHashTableOfTensors> {
    /**
     * If non-empty, this table is placed in the given container.
     * Otherwise, a default container is used.
     */
    public final String container;

    /**
     * If non-empty, this table is shared under the given name across
     * multiple sessions.
     */
    public final String sharedName;

    /**
     * The useNodeNameSharing attribute
     */
    public final boolean useNodeNameSharing;

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
      super(new MutableHashTableOfTensors(op), op, Arrays.asList("container", "shared_name", "use_node_name_sharing", "key_dtype", "value_dtype", "value_shape"));
      int inputIndex = 0;
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      useNodeNameSharing = op.attributes().getAttrBool("use_node_name_sharing");
      keyDtype = op.attributes().getAttrType("key_dtype");
      valueDtype = op.attributes().getAttrType("value_dtype");
      valueShape = op.attributes().getAttrShape("value_shape");
    }
  }
}
