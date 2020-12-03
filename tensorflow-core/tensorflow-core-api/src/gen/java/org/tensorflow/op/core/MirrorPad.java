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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Pads a tensor with mirrored values.
 * <p>
 * This operation pads a `input` with mirrored values according to the `paddings`
 * you specify. `paddings` is an integer tensor with shape `[n, 2]`, where n is
 * the rank of `input`. For each dimension D of `input`, `paddings[D, 0]` indicates
 * how many values to add before the contents of `input` in that dimension, and
 * `paddings[D, 1]` indicates how many values to add after the contents of `input`
 * in that dimension. Both `paddings[D, 0]` and `paddings[D, 1]` must be no greater
 * than `input.dim_size(D)` (or `input.dim_size(D) - 1`) if `copy_border` is true
 * (if false, respectively).
 * <p>
 * The padded size of each dimension D of the output is:
 * <p>
 * `paddings(D, 0) + input.dim_size(D) + paddings(D, 1)`
 * <p>
 * For example:
 * <pre>{@code
 * # 't' is [[1, 2, 3], [4, 5, 6]].
 * # 'paddings' is [[1, 1]], [2, 2]].
 * # 'mode' is SYMMETRIC.
 * # rank of 't' is 2.
 * pad(t, paddings) ==> [[2, 1, 1, 2, 3, 3, 2]
 *                       [2, 1, 1, 2, 3, 3, 2]
 *                       [5, 4, 4, 5, 6, 6, 5]
 *                       [5, 4, 4, 5, 6, 6, 5]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class MirrorPad<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new MirrorPad operation.
   * 
   * @param scope current scope
   * @param input The input tensor to be padded.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * rows must be the same as the rank of `input`.
   * @param mode Either `REFLECT` or `SYMMETRIC`. In reflect mode the padded regions
   * do not include the borders, while in symmetric mode the padded regions
   * do include the borders. For example, if `input` is `[1, 2, 3]` and `paddings`
   * is `[0, 2]`, then the output is `[1, 2, 3, 2, 1]` in reflect mode, and
   * it is `[1, 2, 3, 3, 2]` in symmetric mode.
   * @return a new instance of MirrorPad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> MirrorPad<T> create(Scope scope, Operand<T> input, Operand<U> paddings, String mode) {
    OperationBuilder opBuilder = scope.env().opBuilder("MirrorPad", scope.makeOpName("MirrorPad"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("mode", mode);
    return new MirrorPad<T>(opBuilder.build());
  }
  
  /**
   * The padded tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MirrorPad";
  
  private Output<T> output;
  
  private MirrorPad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
