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

package org.tensorflow.op.sparse;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Counts the number of occurrences of each value in an integer array.
 * <p>
 * Outputs a vector with length `size` and the same dtype as `weights`. If
 * `weights` are empty, then index `i` stores the number of times the value `i` is
 * counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
 * the value in `weights` at each index where the corresponding value in `arr` is
 * `i`.
 * <p>
 * Values in `arr` outside of the range [0, size) are ignored.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator(group = "sparse")
public final class SparseBincount<U extends TNumber> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseBincount}
   */
  public static class Options {
    
    /**
     * @param binaryOutput bool; Whether the kernel should count the appearance or number of occurrences.
     */
    public Options binaryOutput(Boolean binaryOutput) {
      this.binaryOutput = binaryOutput;
      return this;
    }
    
    private Boolean binaryOutput;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseBincount operation.
   * 
   * @param scope current scope
   * @param indices 2D int64 `Tensor`.
   * @param values 1D int `Tensor`.
   * @param denseShape 1D int64 `Tensor`.
   * @param size non-negative int scalar `Tensor`.
   * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
   * shape as `input`, or a length-0 `Tensor`, in which case it acts as all weights
   * equal to 1.
   * @param options carries optional attributes values
   * @return a new instance of SparseBincount
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TNumber> SparseBincount<U> create(Scope scope, Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape, Operand<T> size, Operand<U> weights, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseBincount", scope.makeOpName("SparseBincount"));
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.binaryOutput != null) {
          opBuilder.setAttr("binary_output", opts.binaryOutput);
        }
      }
    }
    return new SparseBincount<U>(opBuilder.build());
  }
  
  /**
   * @param binaryOutput bool; Whether the kernel should count the appearance or number of occurrences.
   */
  public static Options binaryOutput(Boolean binaryOutput) {
    return new Options().binaryOutput(binaryOutput);
  }
  
  /**
   * 1D `Tensor` with length equal to `size` or 2D `Tensor` with [batch_size, `size`].
   * The counts or summed weights for each value in the range [0, size).
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseBincount";
  
  private Output<U> output;
  
  private SparseBincount(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
