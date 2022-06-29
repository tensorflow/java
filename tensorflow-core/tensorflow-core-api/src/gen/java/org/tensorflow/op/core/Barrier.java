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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Defines a barrier that persists across different graph executions.
 * A barrier represents a key-value map, where each key is a string, and
 * each value is a tuple of tensors.
 * <p>At runtime, the barrier contains 'complete' and 'incomplete'
 * elements. A complete element has defined tensors for all components of
 * its value tuple, and may be accessed using BarrierTakeMany. An
 * incomplete element has some undefined components in its value tuple,
 * and may be updated using BarrierInsertMany.
 */
@OpMetadata(
    opType = Barrier.OP_NAME,
    inputsClass = Barrier.Inputs.class
)
@Operator
public final class Barrier extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Barrier";

  private Output<TString> handle;

  public Barrier(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Barrier operation.
   *
   * @param scope current scope
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attribute values
   * @return a new instance of Barrier
   */
  @Endpoint(
      describeByClass = true
  )
  public static Barrier create(Scope scope, List<Class<? extends TType>> componentTypes,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Barrier");
    opBuilder.setAttr("component_types", Operands.toDataTypes(componentTypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.shapes != null) {
          Shape[] shapesArray = new Shape[opts.shapes.size()];
          for (int i = 0 ; i < shapesArray.length ; i++) {
            shapesArray[i] = opts.shapes.get(i);
          }
          opBuilder.setAttr("shapes", shapesArray);
        }
        if (opts.capacity != null) {
          opBuilder.setAttr("capacity", opts.capacity);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new Barrier(opBuilder.build());
  }

  /**
   * Sets the shapes option.
   *
   * @param shapes The shape of each component in a value. Each shape must be 1 in the
   * first dimension. The length of this attr must be the same as the length of
   * component_types.
   * @return this Options instance.
   */
  public static Options shapes(List<Shape> shapes) {
    return new Options().shapes(shapes);
  }

  /**
   * Sets the shapes option.
   *
   * @param shapes The shape of each component in a value. Each shape must be 1 in the
   * first dimension. The length of this attr must be the same as the length of
   * component_types.
   * @return this Options instance.
   */
  public static Options shapes(Shape... shapes) {
    return new Options().shapes(shapes);
  }

  /**
   * Sets the capacity option.
   *
   * @param capacity The capacity of the barrier.  The default capacity is MAX_INT32,
   * which is the largest capacity of the underlying queue.
   * @return this Options instance.
   */
  public static Options capacity(Long capacity) {
    return new Options().capacity(capacity);
  }

  /**
   * Sets the container option.
   *
   * @param container If non-empty, this barrier is placed in the given container.
   * Otherwise, a default container is used.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName If non-empty, this barrier will be shared under the given name
   * across multiple sessions.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Gets handle.
   * The handle to the barrier.
   * @return handle.
   */
  public Output<TString> handle() {
    return handle;
  }

  @Override
  public Output<TString> asOutput() {
    return handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Barrier}
   */
  public static class Options {
    private List<Shape> shapes;

    private Long capacity;

    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the shapes option.
     *
     * @param shapes The shape of each component in a value. Each shape must be 1 in the
     * first dimension. The length of this attr must be the same as the length of
     * component_types.
     * @return this Options instance.
     */
    public Options shapes(List<Shape> shapes) {
      this.shapes = shapes;
      return this;
    }

    /**
     * Sets the shapes option.
     *
     * @param shapes The shape of each component in a value. Each shape must be 1 in the
     * first dimension. The length of this attr must be the same as the length of
     * component_types.
     * @return this Options instance.
     */
    public Options shapes(Shape... shapes) {
      this.shapes = Arrays.asList(shapes);
      return this;
    }

    /**
     * Sets the capacity option.
     *
     * @param capacity The capacity of the barrier.  The default capacity is MAX_INT32,
     * which is the largest capacity of the underlying queue.
     * @return this Options instance.
     */
    public Options capacity(Long capacity) {
      this.capacity = capacity;
      return this;
    }

    /**
     * Sets the container option.
     *
     * @param container If non-empty, this barrier is placed in the given container.
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
     * @param sharedName If non-empty, this barrier will be shared under the given name
     * across multiple sessions.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Barrier.class
  )
  public static class Inputs extends RawOpInputs<Barrier> {
    /**
     * The type of each component in a value.
     */
    public final DataType[] componentTypes;

    /**
     * The shape of each component in a value. Each shape must be 1 in the
     * first dimension. The length of this attr must be the same as the length of
     * component_types.
     */
    public final Shape[] shapes;

    /**
     * The capacity of the barrier.  The default capacity is MAX_INT32,
     * which is the largest capacity of the underlying queue.
     */
    public final long capacity;

    /**
     * If non-empty, this barrier is placed in the given container.
     * Otherwise, a default container is used.
     */
    public final String container;

    /**
     * If non-empty, this barrier will be shared under the given name
     * across multiple sessions.
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new Barrier(op), op, Arrays.asList("component_types", "shapes", "capacity", "container", "shared_name"));
      int inputIndex = 0;
      componentTypes = op.attributes().getAttrTypeList("component_types");
      shapes = op.attributes().getAttrShapeList("shapes");
      capacity = op.attributes().getAttrInt("capacity");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
