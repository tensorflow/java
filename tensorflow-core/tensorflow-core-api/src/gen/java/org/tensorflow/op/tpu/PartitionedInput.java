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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * An op that groups a list of partitioned inputs together. This op
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "tpu"
)
public final class PartitionedInput<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUPartitionedInput";

  private Output<T> output;

  private PartitionedInput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TPUPartitionedInput operation.
   *
   * @param scope current scope
   * @param inputs A list of partitioned inputs which must have the same shape.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUPartitionedInput} output and operands
   * @return a new instance of PartitionedInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> PartitionedInput<T> create(Scope scope,
      Iterable<Operand<T>> inputs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TPUPartitionedInput", scope.makeOpName("PartitionedInput"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.partitionDim != null) {
          opBuilder.setAttr("partition_dim", opts.partitionDim);
        }
      }
    }
    return new PartitionedInput<>(opBuilder.build());
  }

  /**
   * Sets the partitionDim option.
   *
   * @param partitionDim An integer describles which dimension is partitioned. -1 means
   * those inputs are replicated.
   * @return this Options instance.
   */
  public static Options partitionDim(Long partitionDim) {
    return new Options().partitionDim(partitionDim);
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
    private Long partitionDim;

    private Options() {
    }

    /**
     * Sets the partitionDim option.
     *
     * @param partitionDim An integer describles which dimension is partitioned. -1 means
     * those inputs are replicated.
     * @return this Options instance.
     */
    public Options partitionDim(Long partitionDim) {
      this.partitionDim = partitionDim;
      return this;
    }
  }
}
