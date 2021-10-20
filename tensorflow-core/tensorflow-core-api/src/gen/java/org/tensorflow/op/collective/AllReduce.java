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
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Mutually reduces multiple tensors of identical type and shape.
 *
 * @param <T> data type for {@code data} output
 *
 * @deprecated use {@link org.tensorflow.op.collective.Reduce} instead
 */
@Deprecated
public final class AllReduce<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectiveReduce";

  private Output<T> data;

  private AllReduce(Operation operation) {
    super(operation);
    int outputIdx = 0;
    data = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectiveReduce operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param groupSize The value of the groupSize attribute
   * @param groupKey The value of the groupKey attribute
   * @param instanceKey The value of the instanceKey attribute
   * @param mergeOp The value of the mergeOp attribute
   * @param finalOp The value of the finalOp attribute
   * @param subdivOffsets The value of the subdivOffsets attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code CollectiveReduce} output and operands
   * @return a new instance of AllReduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> AllReduce<T> create(Scope scope, Operand<T> input,
      Long groupSize, Long groupKey, Long instanceKey, String mergeOp, String finalOp,
      List<Long> subdivOffsets, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AllReduce");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("group_size", groupSize);
    opBuilder.setAttr("group_key", groupKey);
    opBuilder.setAttr("instance_key", instanceKey);
    opBuilder.setAttr("merge_op", mergeOp);
    opBuilder.setAttr("final_op", finalOp);
    long[] subdivOffsetsArray = new long[subdivOffsets.size()];
    for (int i = 0 ; i < subdivOffsetsArray.length ; i++) {
      subdivOffsetsArray[i] = subdivOffsets.get(i);
    }
    opBuilder.setAttr("subdiv_offsets", subdivOffsetsArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.waitFor != null) {
          long[] waitForArray = new long[opts.waitFor.size()];
          for (int i = 0 ; i < waitForArray.length ; i++) {
            waitForArray[i] = opts.waitFor.get(i);
          }
          opBuilder.setAttr("wait_for", waitForArray);
        }
        if (opts.communicationHint != null) {
          opBuilder.setAttr("communication_hint", opts.communicationHint);
        }
        if (opts.timeoutSeconds != null) {
          opBuilder.setAttr("timeout_seconds", opts.timeoutSeconds);
        }
      }
    }
    return new AllReduce<>(opBuilder.build());
  }

  /**
   * Sets the waitFor option.
   *
   * @param waitFor the waitFor option
   * @return this Options instance.
   */
  public static Options waitFor(List<Long> waitFor) {
    return new Options().waitFor(waitFor);
  }

  /**
   * Sets the waitFor option.
   *
   * @param waitFor the waitFor option
   * @return this Options instance.
   */
  public static Options waitFor(Long... waitFor) {
    return new Options().waitFor(waitFor);
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
   * Optional attributes for {@link org.tensorflow.op.collective.AllReduce}
   */
  public static class Options {
    private List<Long> waitFor;

    private String communicationHint;

    private Float timeoutSeconds;

    private Options() {
    }

    /**
     * Sets the waitFor option.
     *
     * @param waitFor the waitFor option
     * @return this Options instance.
     */
    public Options waitFor(List<Long> waitFor) {
      this.waitFor = waitFor;
      return this;
    }

    /**
     * Sets the waitFor option.
     *
     * @param waitFor the waitFor option
     * @return this Options instance.
     */
    public Options waitFor(Long... waitFor) {
      this.waitFor = Arrays.asList(waitFor);
      return this;
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

  public static class Inputs<T extends TNumber> extends RawOpInputs<AllReduce<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The groupSize attribute
     */
    public final long groupSize;

    /**
     * The groupKey attribute
     */
    public final long groupKey;

    /**
     * The instanceKey attribute
     */
    public final long instanceKey;

    /**
     * The mergeOp attribute
     */
    public final String mergeOp;

    /**
     * The finalOp attribute
     */
    public final String finalOp;

    /**
     * The subdivOffsets attribute
     */
    public final long[] subdivOffsets;

    /**
     * The waitFor attribute
     */
    public final long[] waitFor;

    /**
     * The communicationHint attribute
     */
    public final String communicationHint;

    /**
     * The timeoutSeconds attribute
     */
    public final float timeoutSeconds;

    public Inputs(GraphOperation op) {
      super(new AllReduce<>(op), op, Arrays.asList("T", "group_size", "group_key", "instance_key", "merge_op", "final_op", "subdiv_offsets", "wait_for", "communication_hint", "timeout_seconds"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      groupSize = op.attributes().getAttrInt("group_size");
      groupKey = op.attributes().getAttrInt("group_key");
      instanceKey = op.attributes().getAttrInt("instance_key");
      mergeOp = op.attributes().getAttrString("merge_op");
      finalOp = op.attributes().getAttrString("final_op");
      subdivOffsets = op.attributes().getAttrIntList("subdiv_offsets");
      waitFor = op.attributes().getAttrIntList("wait_for");
      communicationHint = op.attributes().getAttrString("communication_hint");
      timeoutSeconds = op.attributes().getAttrFloat("timeout_seconds");
    }
  }
}
