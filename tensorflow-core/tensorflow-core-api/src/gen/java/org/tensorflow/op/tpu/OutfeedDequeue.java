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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Retrieves a single tensor from the computation outfeed.
 * This operation will block indefinitely until data is available.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = OutfeedDequeue.OP_NAME,
    inputsClass = OutfeedDequeue.Inputs.class
)
public final class OutfeedDequeue<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OutfeedDequeue";

  private Output<T> output;

  public OutfeedDequeue(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new OutfeedDequeue operation.
   *
   * @param scope current scope
   * @param dtype The type of elements in the tensor.
   * @param shape The shape of the tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code OutfeedDequeue} output and operands
   * @return a new instance of OutfeedDequeue
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> OutfeedDequeue<T> create(Scope scope, Class<T> dtype, Shape shape,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OutfeedDequeue");
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("shape", shape);
    if (options != null) {
      for (Options opts : options) {
        if (opts.deviceOrdinal != null) {
          opBuilder.setAttr("device_ordinal", opts.deviceOrdinal);
        }
      }
    }
    return new OutfeedDequeue<>(opBuilder.build());
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
   * Gets output.
   * A tensor that will be read from the device outfeed.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.OutfeedDequeue}
   */
  public static class Options {
    private Long deviceOrdinal;

    private Options() {
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
      outputsClass = OutfeedDequeue.class
  )
  public static class Inputs extends RawOpInputs<OutfeedDequeue<?>> {
    /**
     * The type of elements in the tensor.
     */
    public final DataType dtype;

    /**
     * The shape of the tensor.
     */
    public final Shape shape;

    /**
     * The TPU device to use. This should be -1 when the Op
     * is running on a TPU device, and >= 0 when the Op is running on the CPU
     * device.
     */
    public final long deviceOrdinal;

    public Inputs(GraphOperation op) {
      super(new OutfeedDequeue<>(op), op, Arrays.asList("dtype", "shape", "device_ordinal"));
      int inputIndex = 0;
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
      deviceOrdinal = op.attributes().getAttrInt("device_ordinal");
    }
  }
}
