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
 * Connects N inputs to an N-way replicated TPU computation.
 * This operation holds a replicated input to a {@code tpu.replicate()} computation subgraph.
 * Each replicated input has the same shape and type alongside the output.
 * <p>For example:
 * <pre>
 * %a = &quot;tf.opA&quot;()
 * %b = &quot;tf.opB&quot;()
 * %replicated_input = &quot;tf.TPUReplicatedInput&quot;(%a, %b)
 * %computation = &quot;tf.Computation&quot;(%replicated_input)
 * </pre>
 * <p>The above computation has a replicated input of two replicas.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ReplicatedInput.OP_NAME,
    inputsClass = ReplicatedInput.Inputs.class
)
public final class ReplicatedInput<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUReplicatedInput";

  private Output<T> output;

  public ReplicatedInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TPUReplicatedInput operation.
   *
   * @param scope current scope
   * @param inputs The inputs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUReplicatedInput} output and operands
   * @return a new instance of ReplicatedInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ReplicatedInput<T> create(Scope scope,
      Iterable<Operand<T>> inputs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReplicatedInput");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    if (options != null) {
      for (Options opts : options) {
        if (opts.isMirroredVariable != null) {
          opBuilder.setAttr("is_mirrored_variable", opts.isMirroredVariable);
        }
        if (opts.index != null) {
          opBuilder.setAttr("index", opts.index);
        }
        if (opts.isPacked != null) {
          opBuilder.setAttr("is_packed", opts.isPacked);
        }
      }
    }
    return new ReplicatedInput<>(opBuilder.build());
  }

  /**
   * Sets the isMirroredVariable option.
   *
   * @param isMirroredVariable the isMirroredVariable option
   * @return this Options instance.
   */
  public static Options isMirroredVariable(Boolean isMirroredVariable) {
    return new Options().isMirroredVariable(isMirroredVariable);
  }

  /**
   * Sets the index option.
   *
   * @param index the index option
   * @return this Options instance.
   */
  public static Options index(Long index) {
    return new Options().index(index);
  }

  /**
   * Sets the isPacked option.
   *
   * @param isPacked the isPacked option
   * @return this Options instance.
   */
  public static Options isPacked(Boolean isPacked) {
    return new Options().isPacked(isPacked);
  }

  /**
   * Gets output.
   *
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
   * Optional attributes for {@link org.tensorflow.op.tpu.ReplicatedInput}
   */
  public static class Options {
    private Boolean isMirroredVariable;

    private Long index;

    private Boolean isPacked;

    private Options() {
    }

    /**
     * Sets the isMirroredVariable option.
     *
     * @param isMirroredVariable the isMirroredVariable option
     * @return this Options instance.
     */
    public Options isMirroredVariable(Boolean isMirroredVariable) {
      this.isMirroredVariable = isMirroredVariable;
      return this;
    }

    /**
     * Sets the index option.
     *
     * @param index the index option
     * @return this Options instance.
     */
    public Options index(Long index) {
      this.index = index;
      return this;
    }

    /**
     * Sets the isPacked option.
     *
     * @param isPacked the isPacked option
     * @return this Options instance.
     */
    public Options isPacked(Boolean isPacked) {
      this.isPacked = isPacked;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ReplicatedInput.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ReplicatedInput<T>> {
    /**
     * The inputs input
     */
    public final Iterable<Operand<T>> inputs;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The isMirroredVariable attribute
     */
    public final boolean isMirroredVariable;

    /**
     * The index attribute
     */
    public final long index;

    /**
     * The isPacked attribute
     */
    public final boolean isPacked;

    public Inputs(GraphOperation op) {
      super(new ReplicatedInput<>(op), op, Arrays.asList("T", "is_mirrored_variable", "index", "is_packed"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      T = op.attributes().getAttrType("T");
      isMirroredVariable = op.attributes().getAttrBool("is_mirrored_variable");
      index = op.attributes().getAttrInt("index");
      isPacked = op.attributes().getAttrBool("is_packed");
    }
  }
}
