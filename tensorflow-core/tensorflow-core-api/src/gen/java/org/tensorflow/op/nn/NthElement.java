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
 * Finds values of the `n`-th order statistic for the last dimension.
 * <p>
 * If the input is a vector (rank-1), finds the entries which is the nth-smallest
 * value in the vector and outputs their values as scalar tensor.
 * <p>
 * For matrices (resp. higher rank input), computes the entries which is the
 * nth-smallest value in each row (resp. vector along the last dimension). Thus,
 * <p>
 *     values.shape = input.shape[:-1]
 * 
 * @param <T> data type for {@code values()} output
 */
@Operator(group = "nn")
public final class NthElement<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.NthElement}
   */
  public static class Options {
    
    /**
     * @param reverse When set to True, find the nth-largest value in the vector and vice
     * versa.
     */
    public Options reverse(Boolean reverse) {
      this.reverse = reverse;
      return this;
    }
    
    private Boolean reverse;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new NthElement operation.
   * 
   * @param scope current scope
   * @param input 1-D or higher with last dimension at least `n+1`.
   * @param n 0-D. Position of sorted vector to select along the last dimension (along
   * each row for matrices). Valid range of n is `[0, input.shape[:-1])`
   * @param options carries optional attributes values
   * @return a new instance of NthElement
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> NthElement<T> create(Scope scope, Operand<T> input, Operand<TInt32> n, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("NthElement", scope.makeOpName("NthElement"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(n.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.reverse != null) {
          opBuilder.setAttr("reverse", opts.reverse);
        }
      }
    }
    return new NthElement<T>(opBuilder.build());
  }
  
  /**
   * @param reverse When set to True, find the nth-largest value in the vector and vice
   * versa.
   */
  public static Options reverse(Boolean reverse) {
    return new Options().reverse(reverse);
  }
  
  /**
   * The `n`-th order statistic along each last dimensional slice.
   */
  public Output<T> values() {
    return values;
  }
  
  @Override
  public Output<T> asOutput() {
    return values;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "NthElement";
  
  private Output<T> values;
  
  private NthElement(Operation operation) {
    super(operation);
    int outputIdx = 0;
    values = operation.output(outputIdx++);
  }
}
