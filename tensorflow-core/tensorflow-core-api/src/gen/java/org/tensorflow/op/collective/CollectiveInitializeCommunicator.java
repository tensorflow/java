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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Initializes a group for collective operations.
 */
@OpMetadata(
    opType = CollectiveInitializeCommunicator.OP_NAME,
    inputsClass = CollectiveInitializeCommunicator.Inputs.class
)
public final class CollectiveInitializeCommunicator extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectiveInitializeCommunicator";

  private Output<? extends TType> communicator;

  @SuppressWarnings("unchecked")
  public CollectiveInitializeCommunicator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    communicator = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectiveInitializeCommunicator operation.
   *
   * @param scope current scope
   * @param groupKey The groupKey value
   * @param rank The rank value
   * @param groupSize The groupSize value
   * @param options carries optional attribute values
   * @return a new instance of CollectiveInitializeCommunicator
   */
  @Endpoint(
      describeByClass = true
  )
  public static CollectiveInitializeCommunicator create(Scope scope, Operand<TInt32> groupKey,
      Operand<TInt32> rank, Operand<TInt32> groupSize, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CollectiveInitializeCommunicator");
    opBuilder.addInput(groupKey.asOutput());
    opBuilder.addInput(rank.asOutput());
    opBuilder.addInput(groupSize.asOutput());
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
    return new CollectiveInitializeCommunicator(opBuilder.build());
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
   * Gets communicator.
   *
   * @return communicator.
   */
  public Output<? extends TType> communicator() {
    return communicator;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) communicator;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.collective.CollectiveInitializeCommunicator}
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
      outputsClass = CollectiveInitializeCommunicator.class
  )
  public static class Inputs extends RawOpInputs<CollectiveInitializeCommunicator> {
    /**
     * The groupKey input
     */
    public final Operand<TInt32> groupKey;

    /**
     * The rank input
     */
    public final Operand<TInt32> rank;

    /**
     * The groupSize input
     */
    public final Operand<TInt32> groupSize;

    /**
     * The communicationHint attribute
     */
    public final String communicationHint;

    /**
     * The timeoutSeconds attribute
     */
    public final float timeoutSeconds;

    public Inputs(GraphOperation op) {
      super(new CollectiveInitializeCommunicator(op), op, Arrays.asList("communication_hint", "timeout_seconds"));
      int inputIndex = 0;
      groupKey = (Operand<TInt32>) op.input(inputIndex++);
      rank = (Operand<TInt32>) op.input(inputIndex++);
      groupSize = (Operand<TInt32>) op.input(inputIndex++);
      communicationHint = op.attributes().getAttrString("communication_hint");
      timeoutSeconds = op.attributes().getAttrFloat("timeout_seconds");
    }
  }
}
