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

package org.tensorflow.op.random;

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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Outputs deterministic pseudorandom random integers from a uniform distribution.
 * The generated values follow a uniform distribution in the range {@code [minval, maxval)}.
 * <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter}, {@code alg}, {@code minval} and {@code maxval}.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessRandomUniformIntV2.OP_NAME,
    inputsClass = StatelessRandomUniformIntV2.Inputs.class
)
public final class StatelessRandomUniformIntV2<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessRandomUniformIntV2";

  private Output<U> output;

  public StatelessRandomUniformIntV2(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomUniformIntV2 operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param minval Minimum value (inclusive, scalar).
   * @param maxval Maximum value (exclusive, scalar).
   * @param <U> data type for {@code StatelessRandomUniformIntV2} output and operands
   * @return a new instance of StatelessRandomUniformIntV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> StatelessRandomUniformIntV2<U> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Operand<U> minval, Operand<U> maxval) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessRandomUniformIntV2");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(key.asOutput());
    opBuilder.addInput(counter.asOutput());
    opBuilder.addInput(alg.asOutput());
    opBuilder.addInput(minval.asOutput());
    opBuilder.addInput(maxval.asOutput());
    return new StatelessRandomUniformIntV2<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Random values with specified shape.
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
      outputsClass = StatelessRandomUniformIntV2.class
  )
  public static class Inputs<U extends TNumber> extends RawOpInputs<StatelessRandomUniformIntV2<U>> {
    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

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
     * Minimum value (inclusive, scalar).
     */
    public final Operand<U> minval;

    /**
     * Maximum value (exclusive, scalar).
     */
    public final Operand<U> maxval;

    /**
     * The type of the output.
     */
    public final DataType dtype;

    /**
     * The Tshape attribute
     */
    public final DataType Tshape;

    public Inputs(GraphOperation op) {
      super(new StatelessRandomUniformIntV2<>(op), op, Arrays.asList("dtype", "Tshape"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      key = (Operand<? extends TType>) op.input(inputIndex++);
      counter = (Operand<? extends TType>) op.input(inputIndex++);
      alg = (Operand<TInt32>) op.input(inputIndex++);
      minval = (Operand<U>) op.input(inputIndex++);
      maxval = (Operand<U>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      Tshape = op.attributes().getAttrType("Tshape");
    }
  }
}
