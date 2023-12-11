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

package org.tensorflow.op.core;

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Stochastically cast a given tensor from floats to ints.
 * The values are cast with a deterministic pseudo-random tensor from a uniform distribution generated from user given key, counter, algorithm. Values will saturate if out of the specified integer type range, and will become zero if inputs are NaN.
 * <p>The outputs are a deterministic function of {@code input}, {@code key}, {@code counter}, {@code alg}.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = StochasticCastToInt.OP_NAME,
    inputsClass = StochasticCastToInt.Inputs.class
)
public final class StochasticCastToInt<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StochasticCastToInt";

  private Output<U> output;

  public StochasticCastToInt(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StochasticCastToInt operation.
   *
   * @param scope current scope
   * @param input The operand to stochastically cast to int.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param Tout The type of the output.
   * @param <U> data type for {@code StochasticCastToInt} output and operands
   * @return a new instance of StochasticCastToInt
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> StochasticCastToInt<U> create(Scope scope,
      Operand<? extends TNumber> input, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Class<U> Tout) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StochasticCastToInt");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(key.asOutput());
    opBuilder.addInput(counter.asOutput());
    opBuilder.addInput(alg.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    return new StochasticCastToInt<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The cast result with the same shape as the input.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = StochasticCastToInt.class
  )
  public static class Inputs extends RawOpInputs<StochasticCastToInt<?>> {
    /**
     * The operand to stochastically cast to int.
     */
    public final Operand<? extends TNumber> input;

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
     * The type of the input.
     */
    public final DataType Tin;

    /**
     * The type of the output.
     */
    public final DataType Tout;

    public Inputs(GraphOperation op) {
      super(new StochasticCastToInt<>(op), op, Arrays.asList("Tin", "Tout"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      key = (Operand<? extends TType>) op.input(inputIndex++);
      counter = (Operand<? extends TType>) op.input(inputIndex++);
      alg = (Operand<TInt32>) op.input(inputIndex++);
      Tin = op.attributes().getAttrType("Tin");
      Tout = op.attributes().getAttrType("Tout");
    }
  }
}
