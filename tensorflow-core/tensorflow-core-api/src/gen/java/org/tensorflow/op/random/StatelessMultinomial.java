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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Draws samples from a multinomial distribution.
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessMultinomial.OP_NAME,
    inputsClass = StatelessMultinomial.Inputs.class
)
@Operator(
    group = "random"
)
public final class StatelessMultinomial<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessMultinomial";

  private Output<V> output;

  public StatelessMultinomial(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessMultinomial operation.
   *
   * @param scope current scope
   * @param logits 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
   * represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @param outputDtype The value of the outputDtype attribute
   * @param <V> data type for {@code StatelessMultinomial} output and operands
   * @return a new instance of StatelessMultinomial
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> StatelessMultinomial<V> create(Scope scope,
      Operand<? extends TNumber> logits, Operand<TInt32> numSamples,
      Operand<? extends TNumber> seed, Class<V> outputDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessMultinomial");
    opBuilder.addInput(logits.asOutput());
    opBuilder.addInput(numSamples.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.setAttr("output_dtype", Operands.toDataType(outputDtype));
    return new StatelessMultinomial<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new StatelessMultinomial operation, with the default output types.
   *
   * @param scope current scope
   * @param logits 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
   * represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessMultinomial, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatelessMultinomial<TInt64> create(Scope scope, Operand<? extends TNumber> logits,
      Operand<TInt32> numSamples, Operand<? extends TNumber> seed) {
    return create(scope, logits, numSamples, seed, TInt64.class);
  }

  /**
   * Gets output.
   * 2-D Tensor with shape {@code [batch_size, num_samples]}.  Each slice {@code [i, :]}
   * contains the drawn class labels with range {@code [0, num_classes)}.
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
      outputsClass = StatelessMultinomial.class
  )
  public static class Inputs extends RawOpInputs<StatelessMultinomial<?>> {
    /**
     * 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
     * represents the unnormalized log probabilities for all classes.
     */
    public final Operand<? extends TNumber> logits;

    /**
     * 0-D.  Number of independent samples to draw for each row slice.
     */
    public final Operand<TInt32> numSamples;

    /**
     * 2 seeds (shape [2]).
     */
    public final Operand<? extends TNumber> seed;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tseed attribute
     */
    public final DataType Tseed;

    /**
     * The outputDtype attribute
     */
    public final DataType outputDtype;

    public Inputs(GraphOperation op) {
      super(new StatelessMultinomial<>(op), op, Arrays.asList("T", "Tseed", "output_dtype"));
      int inputIndex = 0;
      logits = (Operand<? extends TNumber>) op.input(inputIndex++);
      numSamples = (Operand<TInt32>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tseed = op.attributes().getAttrType("Tseed");
      outputDtype = op.attributes().getAttrType("output_dtype");
    }
  }
}
