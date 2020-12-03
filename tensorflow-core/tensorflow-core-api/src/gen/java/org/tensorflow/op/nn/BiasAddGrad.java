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
import org.tensorflow.types.family.TType;

/**
 * The backward operation for "BiasAdd" on the "bias" tensor.
 * <p>
 * It accumulates all the values from out_backprop into the feature dimension.
 * For NHWC data format, the feature dimension is the last. For NCHW data format,
 * the feature dimension is the third-to-last.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class BiasAddGrad<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.BiasAddGrad}
   */
  public static class Options {
    
    /**
     * @param dataFormat Specify the data format of the input and output data. With the
     * default format "NHWC", the bias tensor will be added to the last dimension
     * of the value tensor.
     * Alternatively, the format could be "NCHW", the data storage order of:
     *     [batch, in_channels, in_height, in_width].
     * The tensor will be added to "in_channels", the third-to-the-last
     *     dimension.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
    
    private String dataFormat;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BiasAddGrad operation.
   * 
   * @param scope current scope
   * @param outBackprop Any number of dimensions.
   * @param options carries optional attributes values
   * @return a new instance of BiasAddGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> BiasAddGrad<T> create(Scope scope, Operand<T> outBackprop, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BiasAddGrad", scope.makeOpName("BiasAddGrad"));
    opBuilder.addInput(outBackprop.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
      }
    }
    return new BiasAddGrad<T>(opBuilder.build());
  }
  
  /**
   * @param dataFormat Specify the data format of the input and output data. With the
   * default format "NHWC", the bias tensor will be added to the last dimension
   * of the value tensor.
   * Alternatively, the format could be "NCHW", the data storage order of:
   *     [batch, in_channels, in_height, in_width].
   * The tensor will be added to "in_channels", the third-to-the-last
   *     dimension.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }
  
  /**
   * 1-D with size the feature dimension of `out_backprop`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BiasAddGrad";
  
  private Output<T> output;
  
  private BiasAddGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
