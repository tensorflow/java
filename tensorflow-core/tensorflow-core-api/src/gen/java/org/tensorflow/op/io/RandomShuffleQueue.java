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
import org.tensorflow.types.family.TType;

/**
 * A queue that randomizes the order of elements.
 */
@OpMetadata(
    opType = RandomShuffleQueue.OP_NAME,
    inputsClass = RandomShuffleQueue.Inputs.class
)
@Operator(
    group = "io"
)
public final class RandomShuffleQueue extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomShuffleQueueV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public RandomShuffleQueue(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomShuffleQueueV2 operation.
   *
   * @param scope current scope
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attribute values
   * @return a new instance of RandomShuffleQueue
   */
  @Endpoint(
      describeByClass = true
  )
  public static RandomShuffleQueue create(Scope scope, List<Class<? extends TType>> componentTypes,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomShuffleQueue");
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
        if (opts.minAfterDequeue != null) {
          opBuilder.setAttr("min_after_dequeue", opts.minAfterDequeue);
        }
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new RandomShuffleQueue(opBuilder.build());
  }

  /**
   * Sets the shapes option.
   *
   * @param shapes The shape of each component in a value. The length of this attr must
   * be either 0 or the same as the length of component_types. If the length of
   * this attr is 0, the shapes of queue elements are not constrained, and
   * only one element may be dequeued at a time.
   * @return this Options instance.
   */
  public static Options shapes(List<Shape> shapes) {
    return new Options().shapes(shapes);
  }

  /**
   * Sets the shapes option.
   *
   * @param shapes The shape of each component in a value. The length of this attr must
   * be either 0 or the same as the length of component_types. If the length of
   * this attr is 0, the shapes of queue elements are not constrained, and
   * only one element may be dequeued at a time.
   * @return this Options instance.
   */
  public static Options shapes(Shape... shapes) {
    return new Options().shapes(shapes);
  }

  /**
   * Sets the capacity option.
   *
   * @param capacity The upper bound on the number of elements in this queue.
   * Negative numbers mean no limit.
   * @return this Options instance.
   */
  public static Options capacity(Long capacity) {
    return new Options().capacity(capacity);
  }

  /**
   * Sets the minAfterDequeue option.
   *
   * @param minAfterDequeue Dequeue will block unless there would be this
   * many elements after the dequeue or the queue is closed. This
   * ensures a minimum level of mixing of elements.
   * @return this Options instance.
   */
  public static Options minAfterDequeue(Long minAfterDequeue) {
    return new Options().minAfterDequeue(minAfterDequeue);
  }

  /**
   * Sets the seed option.
   *
   * @param seed If either seed or seed2 is set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, a random seed is used.
   * @return this Options instance.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }

  /**
   * Sets the seed2 option.
   *
   * @param seed2 A second seed to avoid seed collision.
   * @return this Options instance.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }

  /**
   * Sets the container option.
   *
   * @param container If non-empty, this queue is placed in the given container.
   * Otherwise, a default container is used.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName If non-empty, this queue will be shared under the given name
   * across multiple sessions.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Gets handle.
   * The handle to the queue.
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.RandomShuffleQueue}
   */
  public static class Options {
    private List<Shape> shapes;

    private Long capacity;

    private Long minAfterDequeue;

    private Long seed;

    private Long seed2;

    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the shapes option.
     *
     * @param shapes The shape of each component in a value. The length of this attr must
     * be either 0 or the same as the length of component_types. If the length of
     * this attr is 0, the shapes of queue elements are not constrained, and
     * only one element may be dequeued at a time.
     * @return this Options instance.
     */
    public Options shapes(List<Shape> shapes) {
      this.shapes = shapes;
      return this;
    }

    /**
     * Sets the shapes option.
     *
     * @param shapes The shape of each component in a value. The length of this attr must
     * be either 0 or the same as the length of component_types. If the length of
     * this attr is 0, the shapes of queue elements are not constrained, and
     * only one element may be dequeued at a time.
     * @return this Options instance.
     */
    public Options shapes(Shape... shapes) {
      this.shapes = Arrays.asList(shapes);
      return this;
    }

    /**
     * Sets the capacity option.
     *
     * @param capacity The upper bound on the number of elements in this queue.
     * Negative numbers mean no limit.
     * @return this Options instance.
     */
    public Options capacity(Long capacity) {
      this.capacity = capacity;
      return this;
    }

    /**
     * Sets the minAfterDequeue option.
     *
     * @param minAfterDequeue Dequeue will block unless there would be this
     * many elements after the dequeue or the queue is closed. This
     * ensures a minimum level of mixing of elements.
     * @return this Options instance.
     */
    public Options minAfterDequeue(Long minAfterDequeue) {
      this.minAfterDequeue = minAfterDequeue;
      return this;
    }

    /**
     * Sets the seed option.
     *
     * @param seed If either seed or seed2 is set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, a random seed is used.
     * @return this Options instance.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }

    /**
     * Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }

    /**
     * Sets the container option.
     *
     * @param container If non-empty, this queue is placed in the given container.
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
     * @param sharedName If non-empty, this queue will be shared under the given name
     * across multiple sessions.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RandomShuffleQueue.class
  )
  public static class Inputs extends RawOpInputs<RandomShuffleQueue> {
    /**
     * The type of each component in a value.
     */
    public final DataType[] componentTypes;

    /**
     * The shape of each component in a value. The length of this attr must
     * be either 0 or the same as the length of component_types. If the length of
     * this attr is 0, the shapes of queue elements are not constrained, and
     * only one element may be dequeued at a time.
     */
    public final Shape[] shapes;

    /**
     * The upper bound on the number of elements in this queue.
     * Negative numbers mean no limit.
     */
    public final long capacity;

    /**
     * Dequeue will block unless there would be this
     * many elements after the dequeue or the queue is closed. This
     * ensures a minimum level of mixing of elements.
     */
    public final long minAfterDequeue;

    /**
     * If either seed or seed2 is set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, a random seed is used.
     */
    public final long seed;

    /**
     * A second seed to avoid seed collision.
     */
    public final long seed2;

    /**
     * If non-empty, this queue is placed in the given container.
     * Otherwise, a default container is used.
     */
    public final String container;

    /**
     * If non-empty, this queue will be shared under the given name
     * across multiple sessions.
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new RandomShuffleQueue(op), op, Arrays.asList("component_types", "shapes", "capacity", "min_after_dequeue", "seed", "seed2", "container", "shared_name"));
      int inputIndex = 0;
      componentTypes = op.attributes().getAttrTypeList("component_types");
      shapes = op.attributes().getAttrShapeList("shapes");
      capacity = op.attributes().getAttrInt("capacity");
      minAfterDequeue = op.attributes().getAttrInt("min_after_dequeue");
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
