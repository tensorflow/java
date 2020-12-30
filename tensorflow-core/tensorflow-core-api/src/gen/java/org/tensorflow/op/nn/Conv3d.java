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
 * Computes a 3-D convolution given 5-D `input` and `filter` tensors.
 * <p>
 * In signal processing, cross-correlation is a measure of similarity of
 * two waveforms as a function of a time-lag applied to one of them. This
 * is also known as a sliding dot product or sliding inner-product.
 * <p>
 * Our Conv3D implements a form of cross-correlation.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class Conv3d<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.Conv3d}
   */
  public static class Options {
    
    /**
     * @param dataFormat The data format of the input and output data. With the
     * default format "NDHWC", the data is stored in the order of:
     *     [batch, in_depth, in_height, in_width, in_channels].
     * Alternatively, the format could be "NCDHW", the data storage order is:
     *     [batch, in_channels, in_depth, in_height, in_width].
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
    
    /**
     * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
     * `input`. If set to k > 1, there will be k-1 skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of `data_format`, see above for details. Dilations in the batch and
     * depth dimensions must be 1.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }
    
    private String dataFormat;
    private List<Long> dilations;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Conv3d operation.
   * 
   * @param scope current scope
   * @param input Shape `[batch, in_depth, in_height, in_width, in_channels]`.
   * @param filter Shape `[filter_depth, filter_height, filter_width, in_channels,
   * out_channels]`. `in_channels` must match between `input` and `filter`.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * dimension of `input`. Must have `strides[0] = strides[4] = 1`.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv3d
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Conv3d<T> create(Scope scope, Operand<T> input, Operand<T> filter, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Conv3D", scope.makeOpName("Conv3d"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder = scope.apply(opBuilder);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
        if (opts.dilations != null) {
          long[] dilationsArray = new long[opts.dilations.size()];
          for (int i = 0; i < dilationsArray.length; ++i) {
            dilationsArray[i] = opts.dilations.get(i);
          }
          opBuilder.setAttr("dilations", dilationsArray);
        }
      }
    }
    return new Conv3d<T>(opBuilder.build());
  }
  
  /**
   * @param dataFormat The data format of the input and output data. With the
   * default format "NDHWC", the data is stored in the order of:
   *     [batch, in_depth, in_height, in_width, in_channels].
   * Alternatively, the format could be "NCDHW", the data storage order is:
   *     [batch, in_channels, in_depth, in_height, in_width].
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }
  
  /**
   * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
   * `input`. If set to k > 1, there will be k-1 skipped cells between each
   * filter element on that dimension. The dimension order is determined by the
   * value of `data_format`, see above for details. Dilations in the batch and
   * depth dimensions must be 1.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Conv3D";
  
  private Output<T> output;
  
  private Conv3d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
