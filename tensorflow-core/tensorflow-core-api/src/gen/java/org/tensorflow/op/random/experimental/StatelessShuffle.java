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

package org.tensorflow.op.random.experimental;

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Randomly and deterministically shuffles a tensor along its first dimension.
 * The tensor is shuffled along dimension 0, such that each {@code value[j]} is mapped
 * to one and only one {@code output[i]}. For example, a mapping that might occur for a
 * 3x2 tensor is:
 * <pre>
 * [[1, 2],       [[5, 6],
 *  [3, 4],  ==&gt;   [1, 2],
 *  [5, 6]]        [3, 4]]
 * </pre>
 * <p>The outputs are a deterministic function of {@code value}, {@code key}, {@code counter} and {@code alg}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessShuffle.OP_NAME,
    inputsClass = StatelessShuffle.Inputs.class
)
public final class StatelessShuffle<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessShuffle";

  private Output<T> output;

  public StatelessShuffle(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessShuffle operation.
   *
   * @param scope current scope
   * @param value The tensor to be shuffled.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param <T> data type for {@code StatelessShuffle} output and operands
   * @return a new instance of StatelessShuffle
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> StatelessShuffle<T> create(Scope scope, Operand<T> value,
      Operand<? extends TType> key, Operand<? extends TType> counter, Operand<TInt32> alg) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessShuffle");
    opBuilder.addInput(value.asOutput());
    opBuilder.addInput(key.asOutput());
    opBuilder.addInput(counter.asOutput());
    opBuilder.addInput(alg.asOutput());
    return new StatelessShuffle<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A tensor of same shape and type as {@code value}, shuffled along its first
   * dimension.
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
      outputsClass = StatelessShuffle.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<StatelessShuffle<T>> {
    /**
     * The tensor to be shuffled.
     */
    public final Operand<T> value;

    /**
     * Key for the counter-based RNG algorithm (shape uint64[1]).
     */
    public final Operand<? extends TType> key;

    /**
     * Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
     */
    public final Operand<? extends TType> counter;

    /**
     * The RNG algorithm (shape int32[]).
     */
    public final Operand<TInt32> alg;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new StatelessShuffle<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      value = (Operand<T>) op.input(inputIndex++);
      key = (Operand<? extends TType>) op.input(inputIndex++);
      counter = (Operand<? extends TType>) op.input(inputIndex++);
      alg = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
