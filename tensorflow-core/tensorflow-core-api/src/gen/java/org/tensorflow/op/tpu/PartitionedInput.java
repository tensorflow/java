/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * An op that groups a list of partitioned inputs together. Supports ND sharding.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = PartitionedInput.OP_NAME,
    inputsClass = PartitionedInput.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class PartitionedInput<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUPartitionedInputV2";

  private Output<T> output;

  public PartitionedInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TPUPartitionedInputV2 operation.
   *
   * @param scope current scope
   * @param inputs A list of partitioned inputs which must have the same shape.
   * @param partitionDims A list of integers describing how each dimension is partitioned. Emptiness
   * indicates the inputs are replicated.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUPartitionedInputV2} output and operands
   * @return a new instance of PartitionedInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> PartitionedInput<T> create(Scope scope,
      Iterable<Operand<T>> inputs, List<Long> partitionDims, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "PartitionedInput");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    long[] partitionDimsArray = new long[partitionDims.size()];
    for (int i = 0 ; i < partitionDimsArray.length ; i++) {
      partitionDimsArray[i] = partitionDims.get(i);
    }
    opBuilder.setAttr("partition_dims", partitionDimsArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.isPacked != null) {
          opBuilder.setAttr("is_packed", opts.isPacked);
        }
      }
    }
    return new PartitionedInput<>(opBuilder.build());
  }

  /**
   * Sets the isPacked option.
   *
   * @param isPacked Indicates whether the input is a packed resource.
   * @return this Options instance.
   */
  public static Options isPacked(Boolean isPacked) {
    return new Options().isPacked(isPacked);
  }

  /**
   * Gets output.
   * A handle which represents the full shape of partitioned tensors.
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
   * Optional attributes for {@link org.tensorflow.op.tpu.PartitionedInput}
   */
  public static class Options {
    private Boolean isPacked;

    private Options() {
    }

    /**
     * Sets the isPacked option.
     *
     * @param isPacked Indicates whether the input is a packed resource.
     * @return this Options instance.
     */
    public Options isPacked(Boolean isPacked) {
      this.isPacked = isPacked;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = PartitionedInput.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<PartitionedInput<T>> {
    /**
     * A list of partitioned inputs which must have the same shape.
     */
    public final Iterable<Operand<T>> inputs;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * A list of integers describing how each dimension is partitioned. Emptiness
     * indicates the inputs are replicated.
     */
    public final long[] partitionDims;

    /**
     * Indicates whether the input is a packed resource.
     */
    public final boolean isPacked;

    public Inputs(GraphOperation op) {
      super(new PartitionedInput<>(op), op, Arrays.asList("T", "partition_dims", "is_packed"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      T = op.attributes().getAttrType("T");
      partitionDims = op.attributes().getAttrIntList("partition_dims");
      isPacked = op.attributes().getAttrBool("is_packed");
    }
  }
}
