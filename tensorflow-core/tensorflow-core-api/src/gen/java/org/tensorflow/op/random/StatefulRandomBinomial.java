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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * The StatefulRandomBinomial operation
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = StatefulRandomBinomial.OP_NAME,
    inputsClass = StatefulRandomBinomial.Inputs.class
)
@Operator(
    group = "random"
)
public final class StatefulRandomBinomial<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatefulRandomBinomial";

  private Output<V> output;

  public StatefulRandomBinomial(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatefulRandomBinomial operation.
   *
   * @param scope current scope
   * @param resource The resource value
   * @param algorithm The algorithm value
   * @param shape The shape value
   * @param counts The counts value
   * @param probs The probs value
   * @param dtype The value of the dtype attribute
   * @param <V> data type for {@code StatefulRandomBinomial} output and operands
   * @param <U> data type for {@code StatefulRandomBinomial} output and operands
   * @return a new instance of StatefulRandomBinomial
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber, U extends TNumber> StatefulRandomBinomial<V> create(Scope scope,
      Operand<? extends TType> resource, Operand<TInt64> algorithm,
      Operand<? extends TNumber> shape, Operand<U> counts, Operand<U> probs, Class<V> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatefulRandomBinomial");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(counts.asOutput());
    opBuilder.addInput(probs.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new StatefulRandomBinomial<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new StatefulRandomBinomial operation, with the default output types.
   *
   * @param scope current scope
   * @param resource The resource value
   * @param algorithm The algorithm value
   * @param shape The shape value
   * @param counts The counts value
   * @param probs The probs value
   * @param <U> data type for {@code StatefulRandomBinomial} output and operands
   * @return a new instance of StatefulRandomBinomial, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> StatefulRandomBinomial<TInt64> create(Scope scope,
      Operand<? extends TType> resource, Operand<TInt64> algorithm,
      Operand<? extends TNumber> shape, Operand<U> counts, Operand<U> probs) {
    return create(scope, resource, algorithm, shape, counts, probs, TInt64.class);
  }

  /**
   * Gets output.
   *
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
      outputsClass = StatefulRandomBinomial.class
  )
  public static class Inputs<U extends TNumber> extends RawOpInputs<StatefulRandomBinomial<?>> {
    /**
     * The resource input
     */
    public final Operand<? extends TType> resource;

    /**
     * The algorithm input
     */
    public final Operand<TInt64> algorithm;

    /**
     * The shape input
     */
    public final Operand<? extends TNumber> shape;

    /**
     * The counts input
     */
    public final Operand<U> counts;

    /**
     * The probs input
     */
    public final Operand<U> probs;

    /**
     * The S attribute
     */
    public final DataType S;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new StatefulRandomBinomial<>(op), op, Arrays.asList("S", "T", "dtype"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      algorithm = (Operand<TInt64>) op.input(inputIndex++);
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      counts = (Operand<U>) op.input(inputIndex++);
      probs = (Operand<U>) op.input(inputIndex++);
      S = op.attributes().getAttrType("S");
      T = op.attributes().getAttrType("T");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
