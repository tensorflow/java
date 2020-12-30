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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradients of the maxpooling function.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class MaxPoolGradWithArgmax<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.MaxPoolGradWithArgmax}
   */
  public static class Options {
    
    /**
     * @param includeBatchInIndex Whether to include batch dimension in flattened index of `argmax`.
     */
    public Options includeBatchInIndex(Boolean includeBatchInIndex) {
      this.includeBatchInIndex = includeBatchInIndex;
      return this;
    }
    
    private Boolean includeBatchInIndex;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new MaxPoolGradWithArgmax operation.
   * 
   * @param scope current scope
   * @param input The original input.
   * @param grad 4-D with shape `[batch, height, width, channels]`.  Gradients w.r.t. the
   * output of `max_pool`.
   * @param argmax The indices of the maximum values chosen for each output of `max_pool`.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolGradWithArgmax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> MaxPoolGradWithArgmax<T> create(Scope scope, Operand<T> input, Operand<T> grad, Operand<U> argmax, List<Long> ksize, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MaxPoolGradWithArgmax", scope.makeOpName("MaxPoolGradWithArgmax"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(argmax.asOutput());
    opBuilder = scope.apply(opBuilder);
    long[] ksizeArray = new long[ksize.size()];
    for (int i = 0; i < ksizeArray.length; ++i) {
      ksizeArray[i] = ksize.get(i);
    }
    opBuilder.setAttr("ksize", ksizeArray);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.includeBatchInIndex != null) {
          opBuilder.setAttr("include_batch_in_index", opts.includeBatchInIndex);
        }
      }
    }
    return new MaxPoolGradWithArgmax<T>(opBuilder.build());
  }
  
  /**
   * @param includeBatchInIndex Whether to include batch dimension in flattened index of `argmax`.
   */
  public static Options includeBatchInIndex(Boolean includeBatchInIndex) {
    return new Options().includeBatchInIndex(includeBatchInIndex);
  }
  
  /**
   * Gradients w.r.t. the input of `max_pool`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MaxPoolGradWithArgmax";
  
  private Output<T> output;
  
  private MaxPoolGradWithArgmax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
