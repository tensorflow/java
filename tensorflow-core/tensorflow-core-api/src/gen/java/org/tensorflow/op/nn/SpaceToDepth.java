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
 * SpaceToDepth for tensors of type T.
 * <p>
 * Rearranges blocks of spatial data, into depth. More specifically,
 * this op outputs a copy of the input tensor where values from the `height`
 * and `width` dimensions are moved to the `depth` dimension.
 * The attr `block_size` indicates the input block size.
 * <p>
 *   * Non-overlapping blocks of size `block_size x block size` are rearranged
 *     into depth at each location.
 *   * The depth of the output tensor is `block_size * block_size * input_depth`.
 *   * The Y, X coordinates within each block of the input become the high order
 *     component of the output channel index.
 *   * The input tensor's height and width must be divisible by block_size.
 * <p>
 * The `data_format` attr specifies the layout of the input and output tensors
 * with the following options:
 *   "NHWC": `[ batch, height, width, channels ]`
 *   "NCHW": `[ batch, channels, height, width ]`
 *   "NCHW_VECT_C":
 *       `qint8 [ batch, channels / 4, height, width, 4 ]`
 * <p>
 * It is useful to consider the operation as transforming a 6-D Tensor.
 * e.g. for data_format = NHWC,
 *      Each element in the input tensor can be specified via 6 coordinates,
 *      ordered by decreasing memory layout significance as:
 *      n,oY,bY,oX,bX,iC  (where n=batch index, oX, oY means X or Y coordinates
 *                         within the output image, bX, bY means coordinates
 *                         within the input block, iC means input channels).
 *      The output would be a transpose to the following layout:
 *      n,oY,oX,bY,bX,iC
 * <p>
 * This operation is useful for resizing the activations between convolutions
 * (but keeping all data), e.g. instead of pooling. It is also useful for training
 * purely convolutional models.
 * <p>
 * For example, given an input of shape `[1, 2, 2, 1]`, data_format = "NHWC" and
 * block_size = 2:
 * <pre>{@code
 * x = [[[[1], [2]],
 *       [[3], [4]]]]
 * }</pre>
 * This operation will output a tensor of shape `[1, 1, 1, 4]`:
 * <pre>{@code
 * [[[[1, 2, 3, 4]]]]
 * }</pre>
 * Here, the input has a batch of 1 and each batch element has shape `[2, 2, 1]`,
 * the corresponding output will have a single element (i.e. width and height are
 * both 1) and will have a depth of 4 channels (1 * block_size * block_size).
 * The output element shape is `[1, 1, 4]`.
 * <p>
 * For an input tensor with larger depth, here of shape `[1, 2, 2, 3]`, e.g.
 * <pre>{@code
 * x = [[[[1, 2, 3], [4, 5, 6]],
 *       [[7, 8, 9], [10, 11, 12]]]]
 * }</pre>
 * This operation, for block_size of 2, will return the following tensor of shape
 * `[1, 1, 1, 12]`
 * <pre>{@code
 * [[[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]]]
 * }</pre>
 * Similarly, for the following input of shape `[1 4 4 1]`, and a block size of 2:
 * <pre>{@code
 * x = [[[[1],   [2],  [5],  [6]],
 *       [[3],   [4],  [7],  [8]],
 *       [[9],  [10], [13],  [14]],
 *       [[11], [12], [15],  [16]]]]
 * }</pre>
 * the operator will return the following tensor of shape `[1 2 2 4]`:
 * <pre>{@code
 * x = [[[[1, 2, 3, 4],
 *        [5, 6, 7, 8]],
 *       [[9, 10, 11, 12],
 *        [13, 14, 15, 16]]]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class SpaceToDepth<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.SpaceToDepth}
   */
  public static class Options {
    
    /**
     * @param dataFormat 
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
   * Factory method to create a class wrapping a new SpaceToDepth operation.
   * 
   * @param scope current scope
   * @param input 
   * @param blockSize The size of the spatial block.
   * @param options carries optional attributes values
   * @return a new instance of SpaceToDepth
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SpaceToDepth<T> create(Scope scope, Operand<T> input, Long blockSize, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SpaceToDepth", scope.makeOpName("SpaceToDepth"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("block_size", blockSize);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
      }
    }
    return new SpaceToDepth<T>(opBuilder.build());
  }
  
  /**
   * @param dataFormat 
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
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
  public static final String OP_NAME = "SpaceToDepth";
  
  private Output<T> output;
  
  private SpaceToDepth(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
