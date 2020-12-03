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

package org.tensorflow.op.math;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TType;

/**
 * Compare values of `input` to `threshold` and pack resulting bits into a `uint8`.
 * <p>
 * Each comparison returns a boolean `true` (if `input_value > threshold`)
 * or and `false` otherwise.
 * <p>
 * This operation is useful for Locality-Sensitive-Hashing (LSH) and other
 * algorithms that use hashing approximations of cosine and `L2` distances;
 * codes can be generated from an input via:
 * <pre>{@code
 * codebook_size = 50
 * codebook_bits = codebook_size * 32
 * codebook = tf.get_variable('codebook', [x.shape[-1].value, codebook_bits],
 *                            dtype=x.dtype,
 *                            initializer=tf.orthogonal_initializer())
 * codes = compare_and_threshold(tf.matmul(x, codebook), threshold=0.)
 * codes = tf.bitcast(codes, tf.int32)  # go from uint8 to int32
 * # now codes has shape x.shape[:-1] + [codebook_size]
 * }</pre>
 * <b>NOTE</b>: Currently, the innermost dimension of the tensor must be divisible
 * by 8.
 * <p>
 * Given an `input` shaped `[s0, s1, ..., s_n]`, the output is
 * a `uint8` tensor shaped `[s0, s1, ..., s_n / 8]`.
 */
@Operator(group = "math")
public final class CompareAndBitpack extends RawOp implements Operand<TUint8> {
  
  /**
   * Factory method to create a class wrapping a new CompareAndBitpack operation.
   * 
   * @param scope current scope
   * @param input Values to compare against `threshold` and bitpack.
   * @param threshold Threshold to compare against.
   * @return a new instance of CompareAndBitpack
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> CompareAndBitpack create(Scope scope, Operand<T> input, Operand<T> threshold) {
    OperationBuilder opBuilder = scope.env().opBuilder("CompareAndBitpack", scope.makeOpName("CompareAndBitpack"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(threshold.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new CompareAndBitpack(opBuilder.build());
  }
  
  /**
   * The bitpacked comparisons.
   */
  public Output<TUint8> output() {
    return output;
  }
  
  @Override
  public Output<TUint8> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CompareAndBitpack";
  
  private Output<TUint8> output;
  
  private CompareAndBitpack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
