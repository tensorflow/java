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
 * Mutually reduces multiple tensors of identical type and shape.
 *
 * @param <T> data type for {@code data} output
 */
@OpMetadata(
    opType = CollectiveReduce.OP_NAME,
    inputsClass = CollectiveReduce.Inputs.class
)
public final class CollectiveReduce<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectiveReduceV3";

  private Output<T> data;

  public CollectiveReduce(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    data = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectiveReduceV3 operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param communicator The communicator value
   * @param groupAssignment The groupAssignment value
   * @param reduction The value of the reduction attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveReduceV3} output and operands
   * @return a new instance of CollectiveReduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CollectiveReduce<T> create(Scope scope, Operand<T> input,
      Operand<? extends TType> communicator, Operand<TInt32> groupAssignment, String reduction,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CollectiveReduce");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(communicator.asOutput());
    opBuilder.addInput(groupAssignment.asOutput());
    opBuilder.setAttr("reduction", reduction);
    if (options != null) {
      for (Options opts : options) {
        if (opts.timeoutSeconds != null) {
          opBuilder.setAttr("timeout_seconds", opts.timeoutSeconds);
        }
      }
    }
    return new CollectiveReduce<>(opBuilder.build());
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
  public Output<T> data() {
    return data;
  }

  @Override
  public Output<T> asOutput() {
    return data;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.collective.CollectiveReduce}
   */
  public static class Options {
    private Float timeoutSeconds;

    private Options() {
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
      outputsClass = CollectiveReduce.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<CollectiveReduce<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The communicator input
     */
    public final Operand<? extends TType> communicator;

    /**
     * The groupAssignment input
     */
    public final Operand<TInt32> groupAssignment;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The reduction attribute
     */
    public final String reduction;

    /**
     * The timeoutSeconds attribute
     */
    public final float timeoutSeconds;

    public Inputs(GraphOperation op) {
      super(new CollectiveReduce<>(op), op, Arrays.asList("T", "reduction", "timeout_seconds"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      communicator = (Operand<? extends TType>) op.input(inputIndex++);
      groupAssignment = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      reduction = op.attributes().getAttrString("reduction");
      timeoutSeconds = op.attributes().getAttrFloat("timeout_seconds");
    }
  }
}
