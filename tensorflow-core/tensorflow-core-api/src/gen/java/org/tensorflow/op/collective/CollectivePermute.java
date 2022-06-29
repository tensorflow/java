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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * An Op to permute tensors across replicated TPU instances.
 * Each instance supplies its own input.
 * <p>For example, suppose there are 4 TPU instances: {@code [A, B, C, D]}. Passing
 * source_target_pairs={@code [[0,1],[1,2],[2,3],[3,0]]} gets the outputs:
 * {@code [D, A, B, C]}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = CollectivePermute.OP_NAME,
    inputsClass = CollectivePermute.Inputs.class
)
public final class CollectivePermute<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CollectivePermute";

  private Output<T> output;

  public CollectivePermute(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CollectivePermute operation.
   *
   * @param scope current scope
   * @param input The local input to be permuted. Currently only supports float and
   * bfloat16.
   * @param sourceTargetPairs A tensor with shape [num_pairs, 2].
   * @param <T> data type for {@code CollectivePermute} output and operands
   * @return a new instance of CollectivePermute
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CollectivePermute<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> sourceTargetPairs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CollectivePermute");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(sourceTargetPairs.asOutput());
    return new CollectivePermute<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The permuted input.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = CollectivePermute.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<CollectivePermute<T>> {
    /**
     * The local input to be permuted. Currently only supports float and
     * bfloat16.
     */
    public final Operand<T> input;

    /**
     * A tensor with shape [num_pairs, 2].
     */
    public final Operand<TInt32> sourceTargetPairs;

    /**
     * The type of elements to be exchanged.
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new CollectivePermute<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      sourceTargetPairs = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
