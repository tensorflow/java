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
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Stage (key, values) in the underlying container which behaves like a ordered
 * associative container.   Elements are ordered by key.
 */
@OpMetadata(
    opType = OrderedMapStage.OP_NAME,
    inputsClass = OrderedMapStage.Inputs.class
)
@Operator
public final class OrderedMapStage extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OrderedMapStage";

  public OrderedMapStage(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new OrderedMapStage operation.
   *
   * @param scope current scope
   * @param key int64
   * @param indices The indices value
   * @param values a list of tensors
   * dtypes A list of data types that inserted values should adhere to.
   * @param dtypes The value of the dtypes attribute
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapStage
   */
  @Endpoint(
      describeByClass = true
  )
  public static OrderedMapStage create(Scope scope, Operand<TInt64> key, Operand<TInt32> indices,
      Iterable<Operand<?>> values, List<Class<? extends TType>> dtypes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OrderedMapStage");
    opBuilder.addInput(key.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.setAttr("dtypes", Operands.toDataTypes(dtypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.capacity != null) {
          opBuilder.setAttr("capacity", opts.capacity);
        }
        if (opts.memoryLimit != null) {
          opBuilder.setAttr("memory_limit", opts.memoryLimit);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new OrderedMapStage(opBuilder.build());
  }

  /**
   * Sets the capacity option.
   *
   * @param capacity Maximum number of elements in the Staging Area. If &gt; 0, inserts
   * on the container will block when the capacity is reached.
   * @return this Options instance.
   */
  public static Options capacity(Long capacity) {
    return new Options().capacity(capacity);
  }

  /**
   * Sets the memoryLimit option.
   *
   * @param memoryLimit the memoryLimit option
   * @return this Options instance.
   */
  public static Options memoryLimit(Long memoryLimit) {
    return new Options().memoryLimit(memoryLimit);
  }

  /**
   * Sets the container option.
   *
   * @param container If non-empty, this queue is placed in the given container. Otherwise,
   * a default container is used.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName It is necessary to match this name to the matching Unstage Op.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.OrderedMapStage}
   */
  public static class Options {
    private Long capacity;

    private Long memoryLimit;

    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the capacity option.
     *
     * @param capacity Maximum number of elements in the Staging Area. If &gt; 0, inserts
     * on the container will block when the capacity is reached.
     * @return this Options instance.
     */
    public Options capacity(Long capacity) {
      this.capacity = capacity;
      return this;
    }

    /**
     * Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     */
    public Options memoryLimit(Long memoryLimit) {
      this.memoryLimit = memoryLimit;
      return this;
    }

    /**
     * Sets the container option.
     *
     * @param container If non-empty, this queue is placed in the given container. Otherwise,
     * a default container is used.
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName It is necessary to match this name to the matching Unstage Op.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = OrderedMapStage.class
  )
  public static class Inputs extends RawOpInputs<OrderedMapStage> {
    /**
     * int64
     */
    public final Operand<TInt64> key;

    /**
     * The indices input
     */
    public final Operand<TInt32> indices;

    /**
     * a list of tensors
     * dtypes A list of data types that inserted values should adhere to.
     */
    public final Iterable<Operand<?>> values;

    /**
     * Maximum number of elements in the Staging Area. If > 0, inserts
     * on the container will block when the capacity is reached.
     */
    public final long capacity;

    /**
     * The memoryLimit attribute
     */
    public final long memoryLimit;

    /**
     * The dtypes attribute
     */
    public final DataType[] dtypes;

    /**
     * The fakeDtypes attribute
     */
    public final DataType[] fakeDtypes;

    /**
     * If non-empty, this queue is placed in the given container. Otherwise,
     * a default container is used.
     */
    public final String container;

    /**
     * It is necessary to match this name to the matching Unstage Op.
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new OrderedMapStage(op), op, Arrays.asList("capacity", "memory_limit", "dtypes", "fake_dtypes", "container", "shared_name"));
      int inputIndex = 0;
      key = (Operand<TInt64>) op.input(inputIndex++);
      indices = (Operand<TInt32>) op.input(inputIndex++);
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      capacity = op.attributes().getAttrInt("capacity");
      memoryLimit = op.attributes().getAttrInt("memory_limit");
      dtypes = op.attributes().getAttrTypeList("dtypes");
      fakeDtypes = op.attributes().getAttrTypeList("fake_dtypes");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
