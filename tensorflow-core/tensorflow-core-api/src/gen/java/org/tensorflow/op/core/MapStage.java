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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Stage (key, values) in the underlying container which behaves like a hashtable.
 */
@Operator
public final class MapStage extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MapStage";

  private MapStage(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new MapStage operation.
   *
   * @param scope current scope
   * @param key int64
   * @param indices the indices value
   * @param values a list of tensors
   * dtypes A list of data types that inserted values should adhere to.
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapStage
   */
  @Endpoint(
      describeByClass = true
  )
  public static MapStage create(Scope scope, Operand<TInt64> key, Operand<TInt32> indices,
      Iterable<Operand<?>> values, List<Class<? extends TType>> dtypes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MapStage");
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
    return new MapStage(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.core.MapStage}
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
}
