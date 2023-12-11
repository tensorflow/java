/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;

/**
 * Stage values similar to a lightweight Enqueue.
 * The basic functionality of this Op is similar to a queue with many
 * fewer capabilities and options.  This Op is optimized for performance.
 */
@OpMetadata(
    opType = Stage.OP_NAME,
    inputsClass = Stage.Inputs.class
)
@Operator
public final class Stage extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Stage";

  public Stage(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new Stage operation.
   *
   * @param scope current scope
   * @param values a list of tensors
   * dtypes A list of data types that inserted values should adhere to.
   * @param options carries optional attribute values
   * @return a new instance of Stage
   */
  @Endpoint(
      describeByClass = true
  )
  public static Stage create(Scope scope, Iterable<Operand<?>> values, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Stage");
    opBuilder.addInputList(Operands.asOutputs(values));
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
    return new Stage(opBuilder.build());
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
   * @param memoryLimit The maximum number of bytes allowed for Tensors in the Staging Area.
   * If &gt; 0, inserts will block until sufficient space is available.
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
   * Optional attributes for {@link org.tensorflow.op.core.Stage}
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
     * @param memoryLimit The maximum number of bytes allowed for Tensors in the Staging Area.
     * If &gt; 0, inserts will block until sufficient space is available.
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
      outputsClass = Stage.class
  )
  public static class Inputs extends RawOpInputs<Stage> {
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
     * The maximum number of bytes allowed for Tensors in the Staging Area.
     * If > 0, inserts will block until sufficient space is available.
     */
    public final long memoryLimit;

    /**
     * The dtypes attribute
     */
    public final DataType[] dtypes;

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
      super(new Stage(op), op, Arrays.asList("capacity", "memory_limit", "dtypes", "container", "shared_name"));
      int inputIndex = 0;
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      capacity = op.attributes().getAttrInt("capacity");
      memoryLimit = op.attributes().getAttrInt("memory_limit");
      dtypes = op.attributes().getAttrTypeList("dtypes");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
