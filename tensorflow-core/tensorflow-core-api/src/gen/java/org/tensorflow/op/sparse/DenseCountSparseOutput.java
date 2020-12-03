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
import org.tensorflow.types.family.TType;

/**
 * Performs sparse-output bin counting for a tf.tensor input.
 * <p>
 *   Counts the number of times each value occurs in the input.
 * 
 * @param <U> data type for {@code outputValues()} output
 */
public final class DenseCountSparseOutput<U extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.DenseCountSparseOutput}
   */
  public static class Options {
    
    /**
     * @param minlength Minimum value to count. Can be set to -1 for no minimum.
     */
    public Options minlength(Long minlength) {
      this.minlength = minlength;
      return this;
    }
    
    /**
     * @param maxlength Maximum value to count. Can be set to -1 for no maximum.
     */
    public Options maxlength(Long maxlength) {
      this.maxlength = maxlength;
      return this;
    }
    
    private Long minlength;
    private Long maxlength;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DenseCountSparseOutput operation.
   * 
   * @param scope current scope
   * @param values Tensor containing data to count.
   * @param weights A Tensor of the same shape as indices containing per-index weight values. May
   * also be the empty tensor if no weights are used.
   * @param binaryOutput Whether to output the number of occurrences of each value or 1.
   * @param options carries optional attributes values
   * @return a new instance of DenseCountSparseOutput
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TNumber> DenseCountSparseOutput<U> create(Scope scope, Operand<T> values, Operand<U> weights, Boolean binaryOutput, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DenseCountSparseOutput", scope.makeOpName("DenseCountSparseOutput"));
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("binary_output", binaryOutput);
    if (options != null) {
      for (Options opts : options) {
        if (opts.minlength != null) {
          opBuilder.setAttr("minlength", opts.minlength);
        }
        if (opts.maxlength != null) {
          opBuilder.setAttr("maxlength", opts.maxlength);
        }
      }
    }
    return new DenseCountSparseOutput<U>(opBuilder.build());
  }
  
  /**
   * @param minlength Minimum value to count. Can be set to -1 for no minimum.
   */
  public static Options minlength(Long minlength) {
    return new Options().minlength(minlength);
  }
  
  /**
   * @param maxlength Maximum value to count. Can be set to -1 for no maximum.
   */
  public static Options maxlength(Long maxlength) {
    return new Options().maxlength(maxlength);
  }
  
  /**
   * Indices tensor for the resulting sparse tensor object.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * Values tensor for the resulting sparse tensor object.
   */
  public Output<U> outputValues() {
    return outputValues;
  }
  
  /**
   * Shape tensor for the resulting sparse tensor object.
   */
  public Output<TInt64> outputDenseShape() {
    return outputDenseShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DenseCountSparseOutput";
  
  private Output<TInt64> outputIndices;
  private Output<U> outputValues;
  private Output<TInt64> outputDenseShape;
  
  private DenseCountSparseOutput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputDenseShape = operation.output(outputIdx++);
  }
}
