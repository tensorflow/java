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
 * Creates an empty hash table that uses tensors as the backing store.
 * It uses &quot;open addressing&quot; with quadratic reprobing to resolve
 * collisions.
 * <p>This op creates a mutable hash table, specifying the type of its keys and
 * values. Each value must be a scalar. Data can be inserted into the table using
 * the insert operations. It does not support the initialization operation.
 */
@OpMetadata(
    opType = MutableDenseHashTable.OP_NAME,
    inputsClass = MutableDenseHashTable.Inputs.class
)
@Operator
public final class MutableDenseHashTable extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MutableDenseHashTableV2";

  private Output<? extends TType> tableHandle;

  @SuppressWarnings("unchecked")
  public MutableDenseHashTable(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tableHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MutableDenseHashTableV2 operation.
   *
   * @param scope current scope
   * @param emptyKey The key used to represent empty key buckets internally. Must not
   * be used in insert or lookup operations.
   * @param deletedKey The deletedKey value
   * @param valueDtype Type of the table values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MutableDenseHashTableV2} output and operands
   * @param <U> data type for {@code MutableDenseHashTableV2} output and operands
   * @return a new instance of MutableDenseHashTable
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TType> MutableDenseHashTable create(Scope scope,
      Operand<T> emptyKey, Operand<T> deletedKey, Class<U> valueDtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MutableDenseHashTable");
    opBuilder.addInput(emptyKey.asOutput());
    opBuilder.addInput(deletedKey.asOutput());
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
        if (opts.initialNumBuckets != null) {
          opBuilder.setAttr("initial_num_buckets", opts.initialNumBuckets);
        }
        if (opts.maxLoadFactor != null) {
          opBuilder.setAttr("max_load_factor", opts.maxLoadFactor);
        }
      }
    }
    return new MutableDenseHashTable(opBuilder.build());
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
   * @param valueShape The shape of each value.
   * @return this Options instance.
   */
  public static Options valueShape(Shape valueShape) {
    return new Options().valueShape(valueShape);
  }

  /**
   * Sets the initialNumBuckets option.
   *
   * @param initialNumBuckets The initial number of hash table buckets. Must be a power
   * to 2.
   * @return this Options instance.
   */
  public static Options initialNumBuckets(Long initialNumBuckets) {
    return new Options().initialNumBuckets(initialNumBuckets);
  }

  /**
   * Sets the maxLoadFactor option.
   *
   * @param maxLoadFactor The maximum ratio between number of entries and number of
   * buckets before growing the table. Must be between 0 and 1.
   * @return this Options instance.
   */
  public static Options maxLoadFactor(Float maxLoadFactor) {
    return new Options().maxLoadFactor(maxLoadFactor);
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
   * Optional attributes for {@link org.tensorflow.op.core.MutableDenseHashTable}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private Boolean useNodeNameSharing;

    private Shape valueShape;

    private Long initialNumBuckets;

    private Float maxLoadFactor;

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
     * @param valueShape The shape of each value.
     * @return this Options instance.
     */
    public Options valueShape(Shape valueShape) {
      this.valueShape = valueShape;
      return this;
    }

    /**
     * Sets the initialNumBuckets option.
     *
     * @param initialNumBuckets The initial number of hash table buckets. Must be a power
     * to 2.
     * @return this Options instance.
     */
    public Options initialNumBuckets(Long initialNumBuckets) {
      this.initialNumBuckets = initialNumBuckets;
      return this;
    }

    /**
     * Sets the maxLoadFactor option.
     *
     * @param maxLoadFactor The maximum ratio between number of entries and number of
     * buckets before growing the table. Must be between 0 and 1.
     * @return this Options instance.
     */
    public Options maxLoadFactor(Float maxLoadFactor) {
      this.maxLoadFactor = maxLoadFactor;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = MutableDenseHashTable.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<MutableDenseHashTable> {
    /**
     * The key used to represent empty key buckets internally. Must not
     * be used in insert or lookup operations.
     */
    public final Operand<T> emptyKey;

    /**
     * The deletedKey input
     */
    public final Operand<T> deletedKey;

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
     * The shape of each value.
     */
    public final Shape valueShape;

    /**
     * The initial number of hash table buckets. Must be a power
     * to 2.
     */
    public final long initialNumBuckets;

    /**
     * The maximum ratio between number of entries and number of
     * buckets before growing the table. Must be between 0 and 1.
     */
    public final float maxLoadFactor;

    public Inputs(GraphOperation op) {
      super(new MutableDenseHashTable(op), op, Arrays.asList("container", "shared_name", "use_node_name_sharing", "key_dtype", "value_dtype", "value_shape", "initial_num_buckets", "max_load_factor"));
      int inputIndex = 0;
      emptyKey = (Operand<T>) op.input(inputIndex++);
      deletedKey = (Operand<T>) op.input(inputIndex++);
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      useNodeNameSharing = op.attributes().getAttrBool("use_node_name_sharing");
      keyDtype = op.attributes().getAttrType("key_dtype");
      valueDtype = op.attributes().getAttrType("value_dtype");
      valueShape = op.attributes().getAttrShape("value_shape");
      initialNumBuckets = op.attributes().getAttrInt("initial_num_buckets");
      maxLoadFactor = op.attributes().getAttrFloat("max_load_factor");
    }
  }
}
