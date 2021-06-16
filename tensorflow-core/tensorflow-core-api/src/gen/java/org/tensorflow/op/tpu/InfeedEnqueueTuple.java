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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;

/**
 * Feeds multiple Tensor values into the computation as an XLA tuple.
 */
public final class InfeedEnqueueTuple extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InfeedEnqueueTuple";

  private InfeedEnqueueTuple(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new InfeedEnqueueTuple operation.
   *
   * @param scope current scope
   * @param inputs A list of tensors that will be provided using the infeed mechanism.
   * @param shapes The shapes of each tensor in {@code inputs}.
   * @param options carries optional attribute values
   * @return a new instance of InfeedEnqueueTuple
   */
  @Endpoint(
      describeByClass = true
  )
  public static InfeedEnqueueTuple create(Scope scope, Iterable<Operand<?>> inputs,
      List<Shape> shapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("InfeedEnqueueTuple"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    Shape[] shapesArray = new Shape[shapes.size()];
    for (int i = 0 ; i < shapesArray.length ; i++) {
      shapesArray[i] = shapes.get(i);
    }
    opBuilder.setAttr("shapes", shapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.layouts != null) {
          long[] layoutsArray = new long[opts.layouts.size()];
          for (int i = 0 ; i < layoutsArray.length ; i++) {
            layoutsArray[i] = opts.layouts.get(i);
          }
          opBuilder.setAttr("layouts", layoutsArray);
        }
        if (opts.deviceOrdinal != null) {
          opBuilder.setAttr("device_ordinal", opts.deviceOrdinal);
        }
      }
    }
    return new InfeedEnqueueTuple(opBuilder.build());
  }

  /**
   * Sets the layouts option.
   *
   * @param layouts A vector holding the requested layout in minor-to-major sequence for
   * all the tuple shapes, in the order the shapes appear in the &quot;shapes&quot; input.
   * The layout elements for a sub-shape can be set to -1, in which case the
   * corresponding layout will be computed by the infeed operation.
   * @return this Options instance.
   */
  public static Options layouts(List<Long> layouts) {
    return new Options().layouts(layouts);
  }

  /**
   * Sets the layouts option.
   *
   * @param layouts A vector holding the requested layout in minor-to-major sequence for
   * all the tuple shapes, in the order the shapes appear in the &quot;shapes&quot; input.
   * The layout elements for a sub-shape can be set to -1, in which case the
   * corresponding layout will be computed by the infeed operation.
   * @return this Options instance.
   */
  public static Options layouts(Long[] layouts) {
    return new Options().layouts(layouts);
  }

  /**
   * Sets the deviceOrdinal option.
   *
   * @param deviceOrdinal The TPU device to use. This should be -1 when the Op
   * is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
   * device.
   * @return this Options instance.
   */
  public static Options deviceOrdinal(Long deviceOrdinal) {
    return new Options().deviceOrdinal(deviceOrdinal);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.InfeedEnqueueTuple}
   */
  public static class Options {
    private List<Long> layouts;

    private Long deviceOrdinal;

    private Options() {
    }

    /**
     * Sets the layouts option.
     *
     * @param layouts A vector holding the requested layout in minor-to-major sequence for
     * all the tuple shapes, in the order the shapes appear in the &quot;shapes&quot; input.
     * The layout elements for a sub-shape can be set to -1, in which case the
     * corresponding layout will be computed by the infeed operation.
     * @return this Options instance.
     */
    public Options layouts(List<Long> layouts) {
      this.layouts = layouts;
      return this;
    }

    /**
     * Sets the layouts option.
     *
     * @param layouts A vector holding the requested layout in minor-to-major sequence for
     * all the tuple shapes, in the order the shapes appear in the &quot;shapes&quot; input.
     * The layout elements for a sub-shape can be set to -1, in which case the
     * corresponding layout will be computed by the infeed operation.
     * @return this Options instance.
     */
    public Options layouts(Long... layouts) {
      this.layouts = Arrays.asList(layouts);
      return this;
    }

    /**
     * Sets the deviceOrdinal option.
     *
     * @param deviceOrdinal The TPU device to use. This should be -1 when the Op
     * is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
     * device.
     * @return this Options instance.
     */
    public Options deviceOrdinal(Long deviceOrdinal) {
      this.deviceOrdinal = deviceOrdinal;
      return this;
    }
  }
}
