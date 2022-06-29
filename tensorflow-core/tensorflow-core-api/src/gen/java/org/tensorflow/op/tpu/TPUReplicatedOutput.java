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
import org.tensorflow.types.family.TType;

/**
 * Connects N outputs from an N-way replicated TPU computation.
 * This operation holds a replicated output from a {@code tpu.replicate()} computation subgraph.
 * Each replicated output has the same shape and type alongside the input.
 * <p>For example:
 * <pre>
 * %computation = &quot;tf.Computation&quot;()
 * %replicated_output:2 = &quot;tf.TPUReplicatedOutput&quot;(%computation)
 * </pre>
 * <p>The above computation has a replicated output of two replicas.
 *
 * @param <T> data type for {@code outputs} output
 *
 * @deprecated use {@link org.tensorflow.op.tpu.ReplicatedOutput} instead
 */
@OpMetadata(
    opType = TPUReplicatedOutput.OP_NAME,
    inputsClass = TPUReplicatedOutput.Inputs.class
)
@Deprecated
public final class TPUReplicatedOutput<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUReplicatedOutput";

  private List<Output<T>> outputs;

  @SuppressWarnings("unchecked")
  public TPUReplicatedOutput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new TPUReplicatedOutput operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param numReplicas The value of the numReplicas attribute
   * @param <T> data type for {@code TPUReplicatedOutput} output and operands
   * @return a new instance of TPUReplicatedOutput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TPUReplicatedOutput<T> create(Scope scope, Operand<T> input,
      Long numReplicas) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TPUReplicatedOutput");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("num_replicas", numReplicas);
    return new TPUReplicatedOutput<>(opBuilder.build());
  }

  /**
   * Gets outputs.
   *
   * @return outputs.
   */
  public List<Output<T>> outputs() {
    return outputs;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) outputs.iterator();
  }

  @OpInputsMetadata(
      outputsClass = TPUReplicatedOutput.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TPUReplicatedOutput<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TPUReplicatedOutput<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
