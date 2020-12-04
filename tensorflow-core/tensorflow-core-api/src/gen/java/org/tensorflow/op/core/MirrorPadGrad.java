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
 * Gradient op for `MirrorPad` op. This op folds a mirror-padded tensor.
 * <p>
 * This operation folds the padded areas of `input` by `MirrorPad` according to the
 * `paddings` you specify. `paddings` must be the same as `paddings` argument
 * given to the corresponding `MirrorPad` op.
 * <p>
 * The folded size of each dimension D of the output is:
 * <p>
 * `input.dim_size(D) - paddings(D, 0) - paddings(D, 1)`
 * <p>
 * For example:
 * <pre>{@code
 * # 't' is [[1, 2, 3], [4, 5, 6], [7, 8, 9]].
 * # 'paddings' is [[0, 1]], [0, 1]].
 * # 'mode' is SYMMETRIC.
 * # rank of 't' is 2.
 * pad(t, paddings) ==> [[ 1,  5]
 *                       [11, 28]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
public final class MirrorPadGrad<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new MirrorPadGrad operation.
   * 
   * @param scope current scope
   * @param input The input tensor to be folded.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * rows must be the same as the rank of `input`.
   * @param mode The mode used in the `MirrorPad` op.
   * @return a new instance of MirrorPadGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> MirrorPadGrad<T> create(Scope scope, Operand<T> input, Operand<U> paddings, String mode) {
    OperationBuilder opBuilder = scope.env().opBuilder("MirrorPadGrad", scope.makeOpName("MirrorPadGrad"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("mode", mode);
    return new MirrorPadGrad<T>(opBuilder.build());
  }
  
  /**
   * The folded tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MirrorPadGrad";
  
  private Output<T> output;
  
  private MirrorPadGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
