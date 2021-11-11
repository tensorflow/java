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
import org.tensorflow.types.family.TNumber;

/**
 * The StatelessParameterizedTruncatedNormal operation
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessParameterizedTruncatedNormal.OP_NAME,
    inputsClass = StatelessParameterizedTruncatedNormal.Inputs.class
)
public final class StatelessParameterizedTruncatedNormal<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessParameterizedTruncatedNormal";

  private Output<V> output;

  public StatelessParameterizedTruncatedNormal(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessParameterizedTruncatedNormal operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param means The mean parameter of each batch.
   * @param stddevs The standard deviation parameter of each batch. Must be greater than 0.
   * @param minvals The minimum cutoff. May be -infinity.
   * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
   * for each batch.
   * @param <V> data type for {@code StatelessParameterizedTruncatedNormal} output and operands
   * @return a new instance of StatelessParameterizedTruncatedNormal
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> StatelessParameterizedTruncatedNormal<V> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> means,
      Operand<V> stddevs, Operand<V> minvals, Operand<V> maxvals) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessParameterizedTruncatedNormal");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(means.asOutput());
    opBuilder.addInput(stddevs.asOutput());
    opBuilder.addInput(minvals.asOutput());
    opBuilder.addInput(maxvals.asOutput());
    return new StatelessParameterizedTruncatedNormal<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The outputs are truncated normal samples and are a deterministic function of
   * {@code shape}, {@code seed}, {@code minvals}, {@code maxvals}, {@code means} and {@code stddevs}.
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
      outputsClass = StatelessParameterizedTruncatedNormal.class
  )
  public static class Inputs<V extends TNumber> extends RawOpInputs<StatelessParameterizedTruncatedNormal<V>> {
    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * 2 seeds (shape [2]).
     */
    public final Operand<? extends TNumber> seed;

    /**
     * The mean parameter of each batch.
     */
    public final Operand<V> means;

    /**
     * The standard deviation parameter of each batch. Must be greater than 0.
     */
    public final Operand<V> stddevs;

    /**
     * The minimum cutoff. May be -infinity.
     */
    public final Operand<V> minvals;

    /**
     * The maximum cutoff. May be +infinity, and must be more than the minval
     * for each batch.
     */
    public final Operand<V> maxvals;

    /**
     * The S attribute
     */
    public final DataType S;

    /**
     * The Tseed attribute
     */
    public final DataType Tseed;

    /**
     * The type of the output.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new StatelessParameterizedTruncatedNormal<>(op), op, Arrays.asList("S", "Tseed", "dtype"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      means = (Operand<V>) op.input(inputIndex++);
      stddevs = (Operand<V>) op.input(inputIndex++);
      minvals = (Operand<V>) op.input(inputIndex++);
      maxvals = (Operand<V>) op.input(inputIndex++);
      S = op.attributes().getAttrType("S");
      Tseed = op.attributes().getAttrType("Tseed");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
