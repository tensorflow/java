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

package org.tensorflow.op.collective;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Receives a tensor value broadcast from another device.
 *
 * @param <U> data type for {@code data} output
 */
@OpMetadata(
    opType = CollectiveBcastRecv.OP_NAME,
    inputsClass = CollectiveBcastRecv.Inputs.class
)
public final class CollectiveBcastRecv<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectiveBcastRecvV2";

  private Output<U> data;

  public CollectiveBcastRecv(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    data = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectiveBcastRecvV2 operation.
   *
   * @param scope current scope
   * @param groupSize The groupSize value
   * @param groupKey The groupKey value
   * @param instanceKey The instanceKey value
   * @param shape The shape value
   * @param T The value of the T attribute
   * @param options carries optional attribute values
   * @param <U> data type for {@code CollectiveBcastRecvV2} output and operands
   * @return a new instance of CollectiveBcastRecv
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> CollectiveBcastRecv<U> create(Scope scope,
      Operand<TInt32> groupSize, Operand<TInt32> groupKey, Operand<TInt32> instanceKey,
      Operand<? extends TNumber> shape, Class<U> T, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CollectiveBcastRecv");
    opBuilder.addInput(groupSize.asOutput());
    opBuilder.addInput(groupKey.asOutput());
    opBuilder.addInput(instanceKey.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.setAttr("T", Operands.toDataType(T));
    if (options != null) {
      for (Options opts : options) {
        if (opts.communicationHint != null) {
          opBuilder.setAttr("communication_hint", opts.communicationHint);
        }
        if (opts.timeoutSeconds != null) {
          opBuilder.setAttr("timeout_seconds", opts.timeoutSeconds);
        }
      }
    }
    return new CollectiveBcastRecv<>(opBuilder.build());
  }

  /**
   * Sets the communicationHint option.
   *
   * @param communicationHint the communicationHint option
   * @return this Options instance.
   */
  public static Options communicationHint(String communicationHint) {
    return new Options().communicationHint(communicationHint);
  }

  /**
   * Sets the timeoutSeconds option.
   *
   * @param timeoutSeconds the timeoutSeconds option
   * @return this Options instance.
   */
  public static Options timeoutSeconds(Float timeoutSeconds) {
    return new Options().timeoutSeconds(timeoutSeconds);
  }

  /**
   * Gets data.
   *
   * @return data.
   */
  public Output<U> data() {
    return data;
  }

  @Override
  public Output<U> asOutput() {
    return data;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.collective.CollectiveBcastRecv}
   */
  public static class Options {
    private String communicationHint;

    private Float timeoutSeconds;

    private Options() {
    }

    /**
     * Sets the communicationHint option.
     *
     * @param communicationHint the communicationHint option
     * @return this Options instance.
     */
    public Options communicationHint(String communicationHint) {
      this.communicationHint = communicationHint;
      return this;
    }

    /**
     * Sets the timeoutSeconds option.
     *
     * @param timeoutSeconds the timeoutSeconds option
     * @return this Options instance.
     */
    public Options timeoutSeconds(Float timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CollectiveBcastRecv.class
  )
  public static class Inputs extends RawOpInputs<CollectiveBcastRecv<?>> {
    /**
     * The groupSize input
     */
    public final Operand<TInt32> groupSize;

    /**
     * The groupKey input
     */
    public final Operand<TInt32> groupKey;

    /**
     * The instanceKey input
     */
    public final Operand<TInt32> instanceKey;

    /**
     * The shape input
     */
    public final Operand<? extends TNumber> shape;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tshape attribute
     */
    public final DataType Tshape;

    /**
     * The communicationHint attribute
     */
    public final String communicationHint;

    /**
     * The timeoutSeconds attribute
     */
    public final float timeoutSeconds;

    public Inputs(GraphOperation op) {
      super(new CollectiveBcastRecv<>(op), op, Arrays.asList("T", "Tshape", "communication_hint", "timeout_seconds"));
      int inputIndex = 0;
      groupSize = (Operand<TInt32>) op.input(inputIndex++);
      groupKey = (Operand<TInt32>) op.input(inputIndex++);
      instanceKey = (Operand<TInt32>) op.input(inputIndex++);
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tshape = op.attributes().getAttrType("Tshape");
      communicationHint = op.attributes().getAttrString("communication_hint");
      timeoutSeconds = op.attributes().getAttrFloat("timeout_seconds");
    }
  }
}
