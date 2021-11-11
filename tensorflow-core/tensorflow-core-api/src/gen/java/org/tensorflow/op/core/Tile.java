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

package org.tensorflow.op.core;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Constructs a tensor by tiling a given tensor.
 * This operation creates a new tensor by replicating {@code input} {@code multiples} times.
 * The output tensor's i'th dimension has {@code input.dims(i) * multiples[i]} elements,
 * and the values of {@code input} are replicated {@code multiples[i]} times along the 'i'th
 * dimension. For example, tiling {@code [a b c d]} by {@code [2]} produces
 * {@code [a b c d a b c d]}.
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>a = tf.constant([[1,2,3],[4,5,6]], tf.int32)
 * b = tf.constant([1,2], tf.int32)
 * tf.tile(a, b)
 * &lt;tf.Tensor: shape=(2, 6), dtype=int32, numpy=
 * array([[1, 2, 3, 1, 2, 3],
 * [4, 5, 6, 4, 5, 6]], dtype=int32)&gt;
 * c = tf.constant([2,1], tf.int32)
 * tf.tile(a, c)
 * &lt;tf.Tensor: shape=(4, 3), dtype=int32, numpy=
 * array([[1, 2, 3],
 * [4, 5, 6],
 * [1, 2, 3],
 * [4, 5, 6]], dtype=int32)&gt;
 * d = tf.constant([2,2], tf.int32)
 * tf.tile(a, d)
 * &lt;tf.Tensor: shape=(4, 6), dtype=int32, numpy=
 * array([[1, 2, 3, 1, 2, 3],
 * [4, 5, 6, 4, 5, 6],
 * [1, 2, 3, 1, 2, 3],
 * [4, 5, 6, 4, 5, 6]], dtype=int32)&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Tile.OP_NAME,
    inputsClass = Tile.Inputs.class
)
@Operator
public final class Tile<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Tile";

  private Output<T> output;

  public Tile(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Tile operation.
   *
   * @param scope current scope
   * @param input 1-D or higher.
   * @param multiples 1-D. Length must be the same as the number of dimensions in {@code input}
   * @param <T> data type for {@code Tile} output and operands
   * @return a new instance of Tile
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Tile<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> multiples) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Tile");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(multiples.asOutput());
    return new Tile<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = Tile.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Tile<T>> {
    /**
     * 1-D or higher.
     */
    public final Operand<T> input;

    /**
     * 1-D. Length must be the same as the number of dimensions in {@code input}
     */
    public final Operand<? extends TNumber> multiples;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tmultiples attribute
     */
    public final DataType Tmultiples;

    public Inputs(GraphOperation op) {
      super(new Tile<>(op), op, Arrays.asList("T", "Tmultiples"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      multiples = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tmultiples = op.attributes().getAttrType("Tmultiples");
    }
  }
}
