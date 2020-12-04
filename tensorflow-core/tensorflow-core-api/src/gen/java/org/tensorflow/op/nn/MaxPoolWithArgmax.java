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
import org.tensorflow.DataType;
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
 * Performs max pooling on the input and outputs both max values and indices.
 * <p>
 * The indices in `argmax` are flattened, so that a maximum value at position
 * `[b, y, x, c]` becomes flattened index:
 * `(y * width + x) * channels + c` if `include_batch_in_index` is False;
 * `((b * height + y) * width + x) * channels + c` if `include_batch_in_index` is True.
 * <p>
 * The indices returned are always in `[0, height) x [0, width)` before flattening,
 * even if padding is involved and the mathematically correct answer is outside
 * (either negative or too large).  This is a bug, but fixing it is difficult to do
 * in a safe backwards compatible way, especially due to flattening.
 * 
 * @param <T> data type for {@code output()} output
 * @param <U> data type for {@code argmax()} output
 */
@Operator(group = "nn")
public final class MaxPoolWithArgmax<T extends TNumber, U extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.MaxPoolWithArgmax}
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
   * Factory method to create a class wrapping a new MaxPoolWithArgmax operation.
   * 
   * @param scope current scope
   * @param input 4-D with shape `[batch, height, width, channels]`.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * input tensor.
   * @param Targmax 
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolWithArgmax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> MaxPoolWithArgmax<T, U> create(Scope scope, Operand<T> input, List<Long> ksize, List<Long> strides, DataType<U> Targmax, String padding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MaxPoolWithArgmax", scope.makeOpName("MaxPoolWithArgmax"));
    opBuilder.addInput(input.asOutput());
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
    opBuilder.setAttr("Targmax", Targmax);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.includeBatchInIndex != null) {
          opBuilder.setAttr("include_batch_in_index", opts.includeBatchInIndex);
        }
      }
    }
    return new MaxPoolWithArgmax<T, U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new MaxPoolWithArgmax operation using default output types.
   * 
   * @param scope current scope
   * @param input 4-D with shape `[batch, height, width, channels]`.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolWithArgmax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> MaxPoolWithArgmax<T, TInt64> create(Scope scope, Operand<T> input, List<Long> ksize, List<Long> strides, String padding, Options... options) {
    return create(scope, input, ksize, strides, TInt64.DTYPE, padding, options);
  }
  
  /**
   * @param includeBatchInIndex Whether to include batch dimension in flattened index of `argmax`.
   */
  public static Options includeBatchInIndex(Boolean includeBatchInIndex) {
    return new Options().includeBatchInIndex(includeBatchInIndex);
  }
  
  /**
   * The max pooled output tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  /**
   * 4-D.  The flattened indices of the max values chosen for each output.
   */
  public Output<U> argmax() {
    return argmax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MaxPoolWithArgmax";
  
  private Output<T> output;
  private Output<U> argmax;
  
  private MaxPoolWithArgmax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    argmax = operation.output(outputIdx++);
  }
}
