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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Broadcasts a tensor value to one or more other devices.
 *
 * @param <T> data type for {@code data} output
 */
public final class CollectiveBcastSendV2<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectiveBcastSendV2";

  private Output<T> data;

  private CollectiveBcastSendV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    data = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectiveBcastSendV2 operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param groupSize the groupSize value
   * @param groupKey the groupKey value
   * @param instanceKey the instanceKey value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveBcastSendV2} output and operands
   * @return a new instance of CollectiveBcastSendV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CollectiveBcastSendV2<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> groupSize, Operand<TInt32> groupKey, Operand<TInt32> instanceKey,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CollectiveBcastSendV2", scope.makeOpName("CollectiveBcastSendV2"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(groupSize.asOutput());
    opBuilder.addInput(groupKey.asOutput());
    opBuilder.addInput(instanceKey.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new CollectiveBcastSendV2<>(opBuilder.build());
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
  public Output<T> data() {
    return data;
  }

  @Override
  public Output<T> asOutput() {
    return data;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.CollectiveBcastSendV2}
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
}
