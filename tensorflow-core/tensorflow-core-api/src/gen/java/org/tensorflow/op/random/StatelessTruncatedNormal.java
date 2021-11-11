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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs deterministic pseudorandom values from a truncated normal distribution.
 * The generated values follow a normal distribution with mean 0 and standard
 * deviation 1, except that values whose magnitude is more than 2 standard
 * deviations from the mean are dropped and re-picked.
 * <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessTruncatedNormal.OP_NAME,
    inputsClass = StatelessTruncatedNormal.Inputs.class
)
@Operator(
    group = "random"
)
public final class StatelessTruncatedNormal<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessTruncatedNormal";

  private Output<V> output;

  public StatelessTruncatedNormal(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessTruncatedNormal operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @param <V> data type for {@code StatelessTruncatedNormal} output and operands
   * @return a new instance of StatelessTruncatedNormal
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> StatelessTruncatedNormal<V> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Class<V> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessTruncatedNormal");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new StatelessTruncatedNormal<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new StatelessTruncatedNormal operation, with the default output types.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessTruncatedNormal, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatelessTruncatedNormal<TFloat32> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed) {
    return create(scope, shape, seed, TFloat32.class);
  }

  /**
   * Gets output.
   * Random values with specified shape.
   * @return output.
   */
  public Output<V> output() {
    return output;
  }

  @Override
  public Output<V> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = StatelessTruncatedNormal.class
  )
  public static class Inputs extends RawOpInputs<StatelessTruncatedNormal<?>> {
    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * 2 seeds (shape [2]).
     */
    public final Operand<? extends TNumber> seed;

    /**
     * The type of the output.
     */
    public final DataType dtype;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tseed attribute
     */
    public final DataType Tseed;

    public Inputs(GraphOperation op) {
      super(new StatelessTruncatedNormal<>(op), op, Arrays.asList("dtype", "T", "Tseed"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      T = op.attributes().getAttrType("T");
      Tseed = op.attributes().getAttrType("Tseed");
    }
  }
}
