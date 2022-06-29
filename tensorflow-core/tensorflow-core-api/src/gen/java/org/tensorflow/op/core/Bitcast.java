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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Bitcasts a tensor from one type to another without copying data.
 * Given a tensor {@code input}, this operation returns a tensor that has the same buffer
 * data as {@code input} with datatype {@code type}.
 * <p>If the input datatype {@code T} is larger than the output datatype {@code type} then the
 * shape changes from [...] to [..., sizeof({@code T})/sizeof({@code type})].
 * <p>If {@code T} is smaller than {@code type}, the operator requires that the rightmost
 * dimension be equal to sizeof({@code type})/sizeof({@code T}). The shape then goes from
 * [..., sizeof({@code type})/sizeof({@code T})] to [...].
 * <p>tf.bitcast() and tf.cast() work differently when real dtype is casted as a complex dtype
 * (e.g. tf.complex64 or tf.complex128) as tf.cast() make imaginary part 0 while tf.bitcast()
 * gives module error.
 * For example,
 * <p>Example 1:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>a = [1., 2., 3.]
 * equality_bitcast = tf.bitcast(a, tf.complex128)
 * Traceback (most recent call last):
 * ...
 * InvalidArgumentError: Cannot bitcast from 1 to 18 [Op:Bitcast]
 * equality_cast = tf.cast(a, tf.complex128)
 * print(equality_cast)
 * tf.Tensor([1.+0.j 2.+0.j 3.+0.j], shape=(3,), dtype=complex128)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 * <p>Example 2:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>tf.bitcast(tf.constant(0xffffffff, dtype=tf.uint32), tf.uint8)
 * &lt;tf.Tensor: shape=(4,), dtype=uint8, numpy=array([255, 255, 255, 255], dtype=uint8)&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 * <p>Example 3:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>x = [1., 2., 3.]
 * y = [0., 2., 3.]
 * equality= tf.equal(x,y)
 * equality_cast = tf.cast(equality,tf.float32)
 * equality_bitcast = tf.bitcast(equality_cast,tf.uint8)
 * print(equality)
 * tf.Tensor([False True True], shape=(3,), dtype=bool)
 * print(equality_cast)
 * tf.Tensor([0. 1. 1.], shape=(3,), dtype=float32)
 * print(equality_bitcast)
 * tf.Tensor(
 * [[  0   0   0   0]
 * [  0   0 128  63]
 * [  0   0 128  63]], shape=(3, 4), dtype=uint8)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 * <p><em>NOTE</em>: Bitcast is implemented as a low-level cast, so machines with different
 * endian orderings will give different results.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = Bitcast.OP_NAME,
    inputsClass = Bitcast.Inputs.class
)
@Operator
public final class Bitcast<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Bitcast";

  private Output<U> output;

  public Bitcast(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Bitcast operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param type The value of the type attribute
   * @param <U> data type for {@code Bitcast} output and operands
   * @return a new instance of Bitcast
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> Bitcast<U> create(Scope scope, Operand<? extends TType> input,
      Class<U> type) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Bitcast");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("type", Operands.toDataType(type));
    return new Bitcast<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = Bitcast.class
  )
  public static class Inputs extends RawOpInputs<Bitcast<?>> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The type attribute
     */
    public final DataType type;

    public Inputs(GraphOperation op) {
      super(new Bitcast<>(op), op, Arrays.asList("T", "type"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      type = op.attributes().getAttrType("type");
    }
  }
}
