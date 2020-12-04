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
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Defines a barrier that persists across different graph executions.
 * <p>
 * A barrier represents a key-value map, where each key is a string, and
 * each value is a tuple of tensors.
 * <p>
 * At runtime, the barrier contains 'complete' and 'incomplete'
 * elements. A complete element has defined tensors for all components of
 * its value tuple, and may be accessed using BarrierTakeMany. An
 * incomplete element has some undefined components in its value tuple,
 * and may be updated using BarrierInsertMany.
 */
@Operator
public final class Barrier extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Barrier}
   */
  public static class Options {
    
    /**
     * @param shapes The shape of each component in a value. Each shape must be 1 in the
     * first dimension. The length of this attr must be the same as the length of
     * component_types.
     */
    public Options shapes(List<Shape> shapes) {
      this.shapes = shapes;
      return this;
    }
    
    /**
     * @param capacity The capacity of the barrier.  The default capacity is MAX_INT32,
     * which is the largest capacity of the underlying queue.
     */
    public Options capacity(Long capacity) {
      this.capacity = capacity;
      return this;
    }
    
    /**
     * @param container If non-empty, this barrier is placed in the given container.
     * Otherwise, a default container is used.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName If non-empty, this barrier will be shared under the given name
     * across multiple sessions.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    private List<Shape> shapes;
    private Long capacity;
    private String container;
    private String sharedName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Barrier operation.
   * 
   * @param scope current scope
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of Barrier
   */
  @Endpoint(describeByClass = true)
  public static Barrier create(Scope scope, List<DataType<?>> componentTypes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Barrier", scope.makeOpName("Barrier"));
    opBuilder = scope.apply(opBuilder);
    DataType[] componentTypesArray = new DataType[componentTypes.size()];
    for (int i = 0; i < componentTypesArray.length; ++i) {
      componentTypesArray[i] = componentTypes.get(i);
    }
    opBuilder.setAttr("component_types", componentTypesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.shapes != null) {
          Shape[] shapesArray = new Shape[opts.shapes.size()];
          for (int i = 0; i < shapesArray.length; ++i) {
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
   * @param shapes The shape of each component in a value. Each shape must be 1 in the
   * first dimension. The length of this attr must be the same as the length of
   * component_types.
   */
  public static Options shapes(List<Shape> shapes) {
    return new Options().shapes(shapes);
  }
  
  /**
   * @param capacity The capacity of the barrier.  The default capacity is MAX_INT32,
   * which is the largest capacity of the underlying queue.
   */
  public static Options capacity(Long capacity) {
    return new Options().capacity(capacity);
  }
  
  /**
   * @param container If non-empty, this barrier is placed in the given container.
   * Otherwise, a default container is used.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName If non-empty, this barrier will be shared under the given name
   * across multiple sessions.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * The handle to the barrier.
   */
  public Output<TString> handle() {
    return handle;
  }
  
  @Override
  public Output<TString> asOutput() {
    return handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Barrier";
  
  private Output<TString> handle;
  
  private Barrier(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
