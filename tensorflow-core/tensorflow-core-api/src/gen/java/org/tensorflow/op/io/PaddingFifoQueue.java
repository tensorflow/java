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

package org.tensorflow.op.io;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * A queue that produces elements in first-in first-out order.
 * <p>
 * Variable-size shapes are allowed by setting the corresponding shape dimensions
 * to 0 in the shape attr.  In this case DequeueMany will pad up to the maximum
 * size of any given element in the minibatch.  See below for details.
 */
@Operator(group = "io")
public final class PaddingFifoQueue extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.io.PaddingFifoQueue}
   */
  public static class Options {
    
    /**
     * @param shapes The shape of each component in a value. The length of this attr must
     * be either 0 or the same as the length of component_types.
     * Shapes of fixed rank but variable size are allowed by setting
     * any shape dimension to -1.  In this case, the inputs' shape may vary along
     * the given dimension, and DequeueMany will pad the given dimension with
     * zeros up to the maximum shape of all elements in the given batch.
     * If the length of this attr is 0, different queue elements may have
     * different ranks and shapes, but only one element may be dequeued at a time.
     */
    public Options shapes(List<Shape> shapes) {
      this.shapes = shapes;
      return this;
    }
    
    /**
     * @param capacity The upper bound on the number of elements in this queue.
     * Negative numbers mean no limit.
     */
    public Options capacity(Long capacity) {
      this.capacity = capacity;
      return this;
    }
    
    /**
     * @param container If non-empty, this queue is placed in the given container.
     * Otherwise, a default container is used.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName If non-empty, this queue will be shared under the given name
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
   * Factory method to create a class wrapping a new PaddingFifoQueue operation.
   * 
   * @param scope current scope
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of PaddingFifoQueue
   */
  @Endpoint(describeByClass = true)
  public static PaddingFifoQueue create(Scope scope, List<Class<? extends TType>> componentTypes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("PaddingFIFOQueueV2", scope.makeOpName("PaddingFifoQueue"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("component_types", Operands.toDataTypes(componentTypes));
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
    return new PaddingFifoQueue(opBuilder.build());
  }
  
  /**
   * @param shapes The shape of each component in a value. The length of this attr must
   * be either 0 or the same as the length of component_types.
   * Shapes of fixed rank but variable size are allowed by setting
   * any shape dimension to -1.  In this case, the inputs' shape may vary along
   * the given dimension, and DequeueMany will pad the given dimension with
   * zeros up to the maximum shape of all elements in the given batch.
   * If the length of this attr is 0, different queue elements may have
   * different ranks and shapes, but only one element may be dequeued at a time.
   */
  public static Options shapes(List<Shape> shapes) {
    return new Options().shapes(shapes);
  }
  
  /**
   * @param capacity The upper bound on the number of elements in this queue.
   * Negative numbers mean no limit.
   */
  public static Options capacity(Long capacity) {
    return new Options().capacity(capacity);
  }
  
  /**
   * @param container If non-empty, this queue is placed in the given container.
   * Otherwise, a default container is used.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName If non-empty, this queue will be shared under the given name
   * across multiple sessions.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * The handle to the queue.
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "PaddingFIFOQueueV2";
  
  private Output<?> handle;
  
  private PaddingFifoQueue(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
