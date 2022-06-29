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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs deterministic pseudorandom random numbers from a Poisson distribution.
 * Outputs random values from a Poisson distribution.
 * <p>The outputs are a deterministic function of {@code shape}, {@code seed}, and {@code lam}.
 *
 * @param <W> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessRandomPoisson.OP_NAME,
    inputsClass = StatelessRandomPoisson.Inputs.class
)
public final class StatelessRandomPoisson<W extends TNumber> extends RawOp implements Operand<W> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessRandomPoisson";

  private Output<W> output;

  public StatelessRandomPoisson(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomPoisson operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param lam The rate of the Poisson distribution. Shape must match the rightmost dimensions
   * of {@code shape}.
   * @param dtype The type of the output.
   * @param <W> data type for {@code StatelessRandomPoisson} output and operands
   * @return a new instance of StatelessRandomPoisson
   */
  @Endpoint(
      describeByClass = true
  )
  public static <W extends TNumber> StatelessRandomPoisson<W> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed,
      Operand<? extends TNumber> lam, Class<W> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessRandomPoisson");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(lam.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new StatelessRandomPoisson<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Random values with specified shape.
   * @return output.
   */
  public Output<W> output() {
    return output;
  }

  @Override
  public Output<W> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = StatelessRandomPoisson.class
  )
  public static class Inputs extends RawOpInputs<StatelessRandomPoisson<?>> {
    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * 2 seeds (shape [2]).
     */
    public final Operand<? extends TNumber> seed;

    /**
     * The rate of the Poisson distribution. Shape must match the rightmost dimensions
     * of {@code shape}.
     */
    public final Operand<? extends TNumber> lam;

    /**
     * The Rtype attribute
     */
    public final DataType Rtype;

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
      super(new StatelessRandomPoisson<>(op), op, Arrays.asList("Rtype", "dtype", "T", "Tseed"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      lam = (Operand<? extends TNumber>) op.input(inputIndex++);
      Rtype = op.attributes().getAttrType("Rtype");
      dtype = op.attributes().getAttrType("dtype");
      T = op.attributes().getAttrType("T");
      Tseed = op.attributes().getAttrType("Tseed");
    }
  }
}
