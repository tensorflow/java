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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Performs a padding as a preprocess during a convolution.
 * <p>
 * Similar to FusedResizeAndPadConv2d, this op allows for an optimized
 * implementation where the spatial padding transformation stage is fused with the
 * im2col lookup, but in this case without the bilinear filtering required for
 * resizing. Fusing the padding prevents the need to write out the intermediate
 * results as whole tensors, reducing memory pressure, and we can get some latency
 * gains by merging the transformation calculations.
 * The data_format attribute for Conv2D isn't supported by this op, and 'NHWC'
 * order is used instead.
 * Internally this op uses a single per-graph scratch buffer, which means that it
 * will block if multiple versions are being run in parallel. This is because this
 * operator is primarily an optimization to minimize memory usage.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class FusedPadConv2d<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new FusedPadConv2d operation.
   * 
   * @param scope current scope
   * @param input 4-D with shape `[batch, in_height, in_width, in_channels]`.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * rows must be the same as the rank of `input`.
   * @param filter 4-D with shape
   * `[filter_height, filter_width, in_channels, out_channels]`.
   * @param mode 
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   * of `input`. Must be in the same order as the dimension specified with format.
   * @param padding The type of padding algorithm to use.
   * @return a new instance of FusedPadConv2d
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> FusedPadConv2d<T> create(Scope scope, Operand<T> input, Operand<TInt32> paddings, Operand<T> filter, String mode, List<Long> strides, String padding) {
    OperationBuilder opBuilder = scope.env().opBuilder("FusedPadConv2D", scope.makeOpName("FusedPadConv2d"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("mode", mode);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    return new FusedPadConv2d<T>(opBuilder.build());
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
  public static final String OP_NAME = "FusedPadConv2D";
  
  private Output<T> output;
  
  private FusedPadConv2d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
