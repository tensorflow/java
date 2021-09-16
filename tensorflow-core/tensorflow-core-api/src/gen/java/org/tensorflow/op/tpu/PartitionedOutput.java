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
import java.util.Iterator;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * An op that demultiplexes a tensor to be sharded by XLA to a list of partitioned
 * outputs outside the XLA computation.
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "tpu"
)
public final class PartitionedOutput<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUPartitionedOutput";

  private List<Output<T>> output;

  @SuppressWarnings("unchecked")
  private PartitionedOutput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new TPUPartitionedOutput operation.
   *
   * @param scope current scope
   * @param inputs A tensor which represents the full shape of partitioned tensors.
   * @param numSplits the value of the numSplits property
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUPartitionedOutput} output and operands
   * @return a new instance of PartitionedOutput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> PartitionedOutput<T> create(Scope scope, Operand<T> inputs,
      Long numSplits, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "PartitionedOutput");
    opBuilder.addInput(inputs.asOutput());
    opBuilder.setAttr("num_splits", numSplits);
    if (options != null) {
      for (Options opts : options) {
        if (opts.partitionDim != null) {
          opBuilder.setAttr("partition_dim", opts.partitionDim);
        }
      }
    }
    return new PartitionedOutput<>(opBuilder.build());
  }

  /**
   * Sets the partitionDim option.
   *
   * @param partitionDim An integer describles which dimension is partitioned.
   * @return this Options instance.
   */
  public static Options partitionDim(Long partitionDim) {
    return new Options().partitionDim(partitionDim);
  }

  /**
   * Gets output.
   * A list of partitioned inputs which must have the same shape.
   * @return output.
   */
  public List<Output<T>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) output.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.PartitionedOutput}
   */
  public static class Options {
    private Long partitionDim;

    private Options() {
    }

    /**
     * Sets the partitionDim option.
     *
     * @param partitionDim An integer describles which dimension is partitioned.
     * @return this Options instance.
     */
    public Options partitionDim(Long partitionDim) {
      this.partitionDim = partitionDim;
      return this;
    }
  }
}
