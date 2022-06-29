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

package org.tensorflow.op.xla;

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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Stateless PRNG bit generator.
 * Wraps the XLA RngBitGenerator operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#rngbitgenerator.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = RngBitGenerator.OP_NAME,
    inputsClass = RngBitGenerator.Inputs.class
)
@Operator(
    group = "xla"
)
public final class RngBitGenerator<U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaRngBitGenerator";

  private Output<? extends TType> outputKey;

  private Output<U> output;

  @SuppressWarnings("unchecked")
  public RngBitGenerator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputKey = operation.output(outputIdx++);
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaRngBitGenerator operation.
   *
   * @param scope current scope
   * @param algorithm The PRNG algorithm to use, one of
   * tf.random.Algorithm.{PHILOX, THREEFRY, AUTO_SELECT}.
   * @param initialState Initial state for the PRNG algorithm. For THREEFRY, it should be
   * a u64[2] and for PHILOX a u64[3].
   * @param shape The output shape of the generated data.
   * @param dtype The type of the tensor.
   * @param <U> data type for {@code XlaRngBitGenerator} output and operands
   * @return a new instance of RngBitGenerator
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> RngBitGenerator<U> create(Scope scope,
      Operand<TInt32> algorithm, Operand<? extends TType> initialState,
      Operand<? extends TNumber> shape, Class<U> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RngBitGenerator");
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(initialState.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new RngBitGenerator<>(opBuilder.build());
  }

  /**
   * Gets outputKey.
   *
   * @return outputKey.
   */
  public Output<? extends TType> outputKey() {
    return outputKey;
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = RngBitGenerator.class
  )
  public static class Inputs extends RawOpInputs<RngBitGenerator<?>> {
    /**
     * The PRNG algorithm to use, one of
     * tf.random.Algorithm.{PHILOX, THREEFRY, AUTO_SELECT}.
     */
    public final Operand<TInt32> algorithm;

    /**
     * Initial state for the PRNG algorithm. For THREEFRY, it should be
     * a u64[2] and for PHILOX a u64[3].
     */
    public final Operand<? extends TType> initialState;

    /**
     * The output shape of the generated data.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * The type of the tensor.
     */
    public final DataType dtype;

    /**
     * The Tshape attribute
     */
    public final DataType Tshape;

    public Inputs(GraphOperation op) {
      super(new RngBitGenerator<>(op), op, Arrays.asList("dtype", "Tshape"));
      int inputIndex = 0;
      algorithm = (Operand<TInt32>) op.input(inputIndex++);
      initialState = (Operand<? extends TType>) op.input(inputIndex++);
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      Tshape = op.attributes().getAttrType("Tshape");
    }
  }
}
