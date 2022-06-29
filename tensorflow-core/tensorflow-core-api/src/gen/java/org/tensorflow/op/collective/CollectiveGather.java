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
 * Mutually accumulates multiple tensors of identical type and shape.
 *
 * @param <T> data type for {@code data} output
 */
@OpMetadata(
    opType = CollectiveGather.OP_NAME,
    inputsClass = CollectiveGather.Inputs.class
)
public final class CollectiveGather<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectiveGatherV2";

  private Output<T> data;

  public CollectiveGather(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    data = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectiveGatherV2 operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param groupSize The groupSize value
   * @param groupKey The groupKey value
   * @param instanceKey The instanceKey value
   * @param orderingToken The orderingToken value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveGatherV2} output and operands
   * @return a new instance of CollectiveGather
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CollectiveGather<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> groupSize, Operand<TInt32> groupKey, Operand<TInt32> instanceKey,
      Iterable<Operand<? extends TType>> orderingToken, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CollectiveGather");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(groupSize.asOutput());
    opBuilder.addInput(groupKey.asOutput());
    opBuilder.addInput(instanceKey.asOutput());
    opBuilder.addInputList(Operands.asOutputs(orderingToken));
    if (options != null) {
      for (Options opts : options) {
        if (opts.communicationHint != null) {
          opBuilder.setAttr("communication_hint", opts.communicationHint);
        }
        if (opts.timeoutSeconds != null) {
          opBuilder.setAttr("timeout_seconds", opts.timeoutSeconds);
        }
        if (opts.NorderingToken != null) {
          opBuilder.setAttr("Nordering_token", opts.NorderingToken);
        }
      }
    }
    return new CollectiveGather<>(opBuilder.build());
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
   * Sets the NorderingToken option.
   *
   * @param NorderingToken the NorderingToken option
   * @return this Options instance.
   */
  public static Options NorderingToken(Long NorderingToken) {
    return new Options().NorderingToken(NorderingToken);
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
   * Optional attributes for {@link org.tensorflow.op.collective.CollectiveGather}
   */
  public static class Options {
    private String communicationHint;

    private Float timeoutSeconds;

    private Long NorderingToken;

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

    /**
     * Sets the NorderingToken option.
     *
     * @param NorderingToken the NorderingToken option
     * @return this Options instance.
     */
    public Options NorderingToken(Long NorderingToken) {
      this.NorderingToken = NorderingToken;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CollectiveGather.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<CollectiveGather<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

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
     * The orderingToken input
     */
    public final Iterable<Operand<? extends TType>> orderingToken;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The communicationHint attribute
     */
    public final String communicationHint;

    /**
     * The timeoutSeconds attribute
     */
    public final float timeoutSeconds;

    public Inputs(GraphOperation op) {
      super(new CollectiveGather<>(op), op, Arrays.asList("T", "communication_hint", "timeout_seconds"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      groupSize = (Operand<TInt32>) op.input(inputIndex++);
      groupKey = (Operand<TInt32>) op.input(inputIndex++);
      instanceKey = (Operand<TInt32>) op.input(inputIndex++);
      int orderingTokenLength = op.inputListLength("ordering_token");
      orderingToken = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, orderingTokenLength));
      inputIndex += orderingTokenLength;
      T = op.attributes().getAttrType("T");
      communicationHint = op.attributes().getAttrString("communication_hint");
      timeoutSeconds = op.attributes().getAttrFloat("timeout_seconds");
    }
  }
}
