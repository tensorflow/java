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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Packs a list of `N` rank-`R` tensors into one rank-`(R+1)` tensor.
 * <p>
 * Packs the `N` tensors in `values` into a tensor with rank one higher than each
 * tensor in `values`, by packing them along the `axis` dimension.
 * Given a list of tensors of shape `(A, B, C)`;
 * <p>
 * if `axis == 0` then the `output` tensor will have the shape `(N, A, B, C)`.
 * if `axis == 1` then the `output` tensor will have the shape `(A, N, B, C)`.
 * Etc.
 * <p>
 * For example:
 * <pre>{@code
 * # 'x' is [1, 4]
 * # 'y' is [2, 5]
 * # 'z' is [3, 6]
 * pack([x, y, z]) => [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
 * pack([x, y, z], axis=1) => [[1, 2, 3], [4, 5, 6]]
 * }</pre>
 * This is the opposite of `unpack`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Stack<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Stack}
   */
  public static class Options {
    
    /**
     * @param axis Dimension along which to pack.  Negative values wrap around, so the
     * valid range is `[-(R+1), R+1)`.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
    
    private Long axis;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Stack operation.
   * 
   * @param scope current scope
   * @param values Must be of same shape and type.
   * @param options carries optional attributes values
   * @return a new instance of Stack
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Stack<T> create(Scope scope, Iterable<Operand<T>> values, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Pack", scope.makeOpName("Stack"));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new Stack<T>(opBuilder.build());
  }
  
  /**
   * @param axis Dimension along which to pack.  Negative values wrap around, so the
   * valid range is `[-(R+1), R+1)`.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }
  
  /**
   * The packed tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Pack";
  
  private Output<T> output;
  
  private Stack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
