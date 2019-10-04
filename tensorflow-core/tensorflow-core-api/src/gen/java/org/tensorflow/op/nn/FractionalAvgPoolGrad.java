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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * Computes gradient of the FractionalAvgPool function.
 * <p>
 * Unlike FractionalMaxPoolGrad, we don't need to find arg_max for
 * FractionalAvgPoolGrad, we just need to evenly back-propagate each element of
 * out_backprop to those indices that form the same pooling cell. Therefore, we
 * just need to know the shape of original input tensor, instead of the whole
 * tensor.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class FractionalAvgPoolGrad<T extends Number> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FractionalAvgPoolGrad}
   */
  public static class Options {
    
    /**
     * @param overlapping When set to True, it means when pooling, the values at the boundary
     * of adjacent pooling cells are used by both cells. For example:
     * <p>
     * `index  0  1  2  3  4`
     * <p>
     * `value  20 5  16 3  7`
     * <p>
     * If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
     * The result would be [41/3, 26/3] for fractional avg pooling.
     */
    public Options overlapping(Boolean overlapping) {
      this.overlapping = overlapping;
      return this;
    }
    
    private Boolean overlapping;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FractionalAvgPoolGrad operation.
   * 
   * @param scope current scope
   * @param origInputTensorShape Original input tensor shape for `fractional_avg_pool`
   * @param outBackprop 4-D with shape `[batch, height, width, channels]`.  Gradients
   * w.r.t. the output of `fractional_avg_pool`.
   * @param rowPoolingSequence row pooling sequence, form pooling region with
   * col_pooling_sequence.
   * @param colPoolingSequence column pooling sequence, form pooling region with
   * row_pooling sequence.
   * @param options carries optional attributes values
   * @return a new instance of FractionalAvgPoolGrad
   */
  public static <T extends Number> FractionalAvgPoolGrad<T> create(Scope scope, Operand<Long> origInputTensorShape, Operand<T> outBackprop, Operand<Long> rowPoolingSequence, Operand<Long> colPoolingSequence, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FractionalAvgPoolGrad", scope.makeOpName("FractionalAvgPoolGrad"));
    opBuilder.addInput(origInputTensorShape.asOutput());
    opBuilder.addInput(outBackprop.asOutput());
    opBuilder.addInput(rowPoolingSequence.asOutput());
    opBuilder.addInput(colPoolingSequence.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.overlapping != null) {
          opBuilder.setAttr("overlapping", opts.overlapping);
        }
      }
    }
    return new FractionalAvgPoolGrad<T>(opBuilder.build());
  }
  
  /**
   * @param overlapping When set to True, it means when pooling, the values at the boundary
   * of adjacent pooling cells are used by both cells. For example:
   * <p>
   * `index  0  1  2  3  4`
   * <p>
   * `value  20 5  16 3  7`
   * <p>
   * If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
   * The result would be [41/3, 26/3] for fractional avg pooling.
   */
  public static Options overlapping(Boolean overlapping) {
    return new Options().overlapping(overlapping);
  }
  
  /**
   * 4-D.  Gradients w.r.t. the input of `fractional_avg_pool`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private FractionalAvgPoolGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
