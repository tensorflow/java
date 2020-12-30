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

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Finds values and indices of the `k` largest elements for the last dimension.
 * <p>
 * If the input is a vector (rank-1), finds the `k` largest entries in the vector
 * and outputs their values and indices as vectors.  Thus `values[j]` is the
 * `j`-th largest entry in `input`, and its index is `indices[j]`.
 * <p>
 * For matrices (resp. higher rank input), computes the top `k` entries in each
 * row (resp. vector along the last dimension).  Thus,
 * <p>
 *     values.shape = indices.shape = input.shape[:-1] + [k]
 * <p>
 * If two elements are equal, the lower-index element appears first.
 * 
 * @param <T> data type for {@code values()} output
 */
@Operator(group = "nn")
public final class TopK<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.TopK}
   */
  public static class Options {
    
    /**
     * @param sorted If true the resulting `k` elements will be sorted by the values in
     * descending order.
     */
    public Options sorted(Boolean sorted) {
      this.sorted = sorted;
      return this;
    }
    
    private Boolean sorted;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TopK operation.
   * 
   * @param scope current scope
   * @param input 1-D or higher with last dimension at least `k`.
   * @param k 0-D.  Number of top elements to look for along the last dimension (along each
   * row for matrices).
   * @param options carries optional attributes values
   * @return a new instance of TopK
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> TopK<T> create(Scope scope, Operand<T> input, Operand<TInt32> k, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TopKV2", scope.makeOpName("TopK"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(k.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.sorted != null) {
          opBuilder.setAttr("sorted", opts.sorted);
        }
      }
    }
    return new TopK<T>(opBuilder.build());
  }
  
  /**
   * @param sorted If true the resulting `k` elements will be sorted by the values in
   * descending order.
   */
  public static Options sorted(Boolean sorted) {
    return new Options().sorted(sorted);
  }
  
  /**
   * The `k` largest elements along each last dimensional slice.
   */
  public Output<T> values() {
    return values;
  }
  
  /**
   * The indices of `values` within the last dimension of `input`.
   */
  public Output<TInt32> indices() {
    return indices;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TopKV2";
  
  private Output<T> values;
  private Output<TInt32> indices;
  
  private TopK(Operation operation) {
    super(operation);
    int outputIdx = 0;
    values = operation.output(outputIdx++);
    indices = operation.output(outputIdx++);
  }
}
