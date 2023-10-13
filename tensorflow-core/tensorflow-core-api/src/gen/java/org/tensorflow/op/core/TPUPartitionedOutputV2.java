/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

import java.util.Arrays;
import java.util.Iterator;
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
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * An op that demultiplexes a tensor to be sharded by XLA to a list of partitioned
 * outputs outside the XLA computation. Supports ND sharding.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = TPUPartitionedOutputV2.OP_NAME,
    inputsClass = TPUPartitionedOutputV2.Inputs.class
)
@Operator
public final class TPUPartitionedOutputV2<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUPartitionedOutputV2";

  private List<Output<T>> output;

  @SuppressWarnings("unchecked")
  public TPUPartitionedOutputV2(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new TPUPartitionedOutputV2 operation.
   *
   * @param scope current scope
   * @param inputs A tensor which represents the full shape of partitioned tensors.
   * @param numSplits The value of the numSplits attribute
   * @param partitionDims A list of integers describing how each dimension is partitioned. Emptiness
   * indicates the inputs are replicated.
   * @param <T> data type for {@code TPUPartitionedOutputV2} output and operands
   * @return a new instance of TPUPartitionedOutputV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TPUPartitionedOutputV2<T> create(Scope scope, Operand<T> inputs,
      Long numSplits, List<Long> partitionDims) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TPUPartitionedOutputV2");
    opBuilder.addInput(inputs.asOutput());
    opBuilder.setAttr("num_splits", numSplits);
    long[] partitionDimsArray = new long[partitionDims.size()];
    for (int i = 0 ; i < partitionDimsArray.length ; i++) {
      partitionDimsArray[i] = partitionDims.get(i);
    }
    opBuilder.setAttr("partition_dims", partitionDimsArray);
    return new TPUPartitionedOutputV2<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A list of partitioned outputs which have the same shape.
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

  @OpInputsMetadata(
      outputsClass = TPUPartitionedOutputV2.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TPUPartitionedOutputV2<T>> {
    /**
     * A tensor which represents the full shape of partitioned tensors.
     */
    public final Operand<T> inputs;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * A list of integers describing how each dimension is partitioned. Emptiness
     * indicates the inputs are replicated.
     */
    public final long[] partitionDims;

    public Inputs(GraphOperation op) {
      super(new TPUPartitionedOutputV2<>(op), op, Arrays.asList("T", "partition_dims"));
      int inputIndex = 0;
      inputs = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      partitionDims = op.attributes().getAttrIntList("partition_dims");
    }
  }
}
