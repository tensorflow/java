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
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * An op which feeds a single Tensor value into the computation.
 */
@OpMetadata(
    opType = InfeedEnqueue.OP_NAME,
    inputsClass = InfeedEnqueue.Inputs.class
)
public final class InfeedEnqueue extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InfeedEnqueue";

  public InfeedEnqueue(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new InfeedEnqueue operation.
   *
   * @param scope current scope
   * @param input A tensor that will be provided using the infeed mechanism.
   * @param options carries optional attribute values
   * @return a new instance of InfeedEnqueue
   */
  @Endpoint(
      describeByClass = true
  )
  public static InfeedEnqueue create(Scope scope, Operand<? extends TType> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "InfeedEnqueue");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.shape != null) {
          opBuilder.setAttr("shape", opts.shape);
        }
        if (opts.layout != null) {
          long[] layoutArray = new long[opts.layout.size()];
          for (int i = 0 ; i < layoutArray.length ; i++) {
            layoutArray[i] = opts.layout.get(i);
          }
          opBuilder.setAttr("layout", layoutArray);
        }
        if (opts.deviceOrdinal != null) {
          opBuilder.setAttr("device_ordinal", opts.deviceOrdinal);
        }
      }
    }
    return new InfeedEnqueue(opBuilder.build());
  }

  /**
   * Sets the shape option.
   *
   * @param shape The shape of the tensor.
   * @return this Options instance.
   */
  public static Options shape(Shape shape) {
    return new Options().shape(shape);
  }

  /**
   * Sets the layout option.
   *
   * @param layout A vector holding the requested layout in minor-to-major sequence.
   * If a layout attribute is passed, but its values are all -1, the layout will
   * be computed by the infeed operation.
   * @return this Options instance.
   */
  public static Options layout(List<Long> layout) {
    return new Options().layout(layout);
  }

  /**
   * Sets the layout option.
   *
   * @param layout A vector holding the requested layout in minor-to-major sequence.
   * If a layout attribute is passed, but its values are all -1, the layout will
   * be computed by the infeed operation.
   * @return this Options instance.
   */
  public static Options layout(Long... layout) {
    return new Options().layout(layout);
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
   * Optional attributes for {@link org.tensorflow.op.tpu.InfeedEnqueue}
   */
  public static class Options {
    private Shape shape;

    private List<Long> layout;

    private Long deviceOrdinal;

    private Options() {
    }

    /**
     * Sets the shape option.
     *
     * @param shape The shape of the tensor.
     * @return this Options instance.
     */
    public Options shape(Shape shape) {
      this.shape = shape;
      return this;
    }

    /**
     * Sets the layout option.
     *
     * @param layout A vector holding the requested layout in minor-to-major sequence.
     * If a layout attribute is passed, but its values are all -1, the layout will
     * be computed by the infeed operation.
     * @return this Options instance.
     */
    public Options layout(List<Long> layout) {
      this.layout = layout;
      return this;
    }

    /**
     * Sets the layout option.
     *
     * @param layout A vector holding the requested layout in minor-to-major sequence.
     * If a layout attribute is passed, but its values are all -1, the layout will
     * be computed by the infeed operation.
     * @return this Options instance.
     */
    public Options layout(Long... layout) {
      this.layout = Arrays.asList(layout);
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

  @OpInputsMetadata(
      outputsClass = InfeedEnqueue.class
  )
  public static class Inputs extends RawOpInputs<InfeedEnqueue> {
    /**
     * A tensor that will be provided using the infeed mechanism.
     */
    public final Operand<? extends TType> input;

    /**
     * The type of elements in the tensor.
     */
    public final DataType dtype;

    /**
     * The shape of the tensor.
     */
    public final Shape shape;

    /**
     * A vector holding the requested layout in minor-to-major sequence.
     * If a layout attribute is passed, but its values are all -1, the layout will
     * be computed by the infeed operation.
     */
    public final long[] layout;

    /**
     * The TPU device to use. This should be -1 when the Op
     * is running on a TPU device, and >= 0 when the Op is running on the CPU
     * device.
     */
    public final long deviceOrdinal;

    public Inputs(GraphOperation op) {
      super(new InfeedEnqueue(op), op, Arrays.asList("dtype", "shape", "layout", "device_ordinal"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
      layout = op.attributes().getAttrIntList("layout");
      deviceOrdinal = op.attributes().getAttrInt("device_ordinal");
    }
  }
}
