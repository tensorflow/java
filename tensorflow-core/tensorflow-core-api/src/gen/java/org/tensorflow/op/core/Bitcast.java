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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Bitcasts a tensor from one type to another without copying data.
 * <p>
 * Given a tensor `input`, this operation returns a tensor that has the same buffer
 * data as `input` with datatype `type`.
 * <p>
 * If the input datatype `T` is larger than the output datatype `type` then the
 * shape changes from [...] to [..., sizeof(`T`)/sizeof(`type`)].
 * <p>
 * If `T` is smaller than `type`, the operator requires that the rightmost
 * dimension be equal to sizeof(`type`)/sizeof(`T`). The shape then goes from
 * [..., sizeof(`type`)/sizeof(`T`)] to [...].
 * <p>
 * tf.bitcast() and tf.cast() work differently when real dtype is casted as a complex dtype
 * (e.g. tf.complex64 or tf.complex128) as tf.cast() make imaginary part 0 while tf.bitcast()
 * gives module error.
 * For example,
 * <p>
 * Example 1:
 * <p>
 * >>> a = [1., 2., 3.]
 * >>> equality_bitcast = tf.bitcast(a, tf.complex128)
 * Traceback (most recent call last):
 * ...
 * InvalidArgumentError: Cannot bitcast from 1 to 18 [Op:Bitcast]
 * >>> equality_cast = tf.cast(a, tf.complex128)
 * >>> print(equality_cast)
 * tf.Tensor([1.+0.j 2.+0.j 3.+0.j], shape=(3,), dtype=complex128)
 * <p>
 * Example 2:
 * <p>
 * >>> tf.bitcast(tf.constant(0xffffffff, dtype=tf.uint32), tf.uint8)
 * <tf.Tensor: shape=(4,), dtype=uint8, numpy=array([255, 255, 255, 255], dtype=uint8)>
 * <p>
 * Example 3:
 * <p>
 * >>> x = [1., 2., 3.]
 * >>> y = [0., 2., 3.]
 * >>> equality= tf.equal(x,y)
 * >>> equality_cast = tf.cast(equality,tf.float32)
 * >>> equality_bitcast = tf.bitcast(equality_cast,tf.uint8)
 * >>> print(equality)
 * tf.Tensor([False True True], shape=(3,), dtype=bool)
 * >>> print(equality_cast)
 * tf.Tensor([0. 1. 1.], shape=(3,), dtype=float32)
 * >>> print(equality_bitcast)
 * tf.Tensor(
 *     [[  0   0   0   0]
 *      [  0   0 128  63]
 *      [  0   0 128  63]], shape=(3, 4), dtype=uint8)
 * <p>
 * <i>NOTE</i>: Bitcast is implemented as a low-level cast, so machines with different
 * endian orderings will give different results.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class Bitcast<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new Bitcast operation.
   * 
   * @param scope current scope
   * @param input 
   * @param type 
   * @return a new instance of Bitcast
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TType> Bitcast<U> create(Scope scope, Operand<T> input, DataType<U> type) {
    OperationBuilder opBuilder = scope.env().opBuilder("Bitcast", scope.makeOpName("Bitcast"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("type", type);
    return new Bitcast<U>(opBuilder.build());
  }
  
  /**
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Bitcast";
  
  private Output<U> output;
  
  private Bitcast(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
