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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * DepthToSpace for tensors of type T.
 * Rearranges data from depth into blocks of spatial data.
 * This is the reverse transformation of SpaceToDepth. More specifically,
 * this op outputs a copy of the input tensor where values from the {@code depth}
 * dimension are moved in spatial blocks to the {@code height} and {@code width} dimensions.
 * The attr {@code block_size} indicates the input block size and how the data is moved.
 * <ul>
 * <li>Chunks of data of size {@code block_size * block_size} from depth are rearranged
 * into non-overlapping blocks of size {@code block_size x block_size}</li>
 * <li>The width the output tensor is {@code input_depth * block_size}, whereas the
 * height is {@code input_height * block_size}.</li>
 * <li>The Y, X coordinates within each block of the output image are determined
 * by the high order component of the input channel index.</li>
 * <li>The depth of the input tensor must be divisible by
 * {@code block_size * block_size}.</li>
 * </ul>
 * <p>The {@code data_format} attr specifies the layout of the input and output tensors
 * with the following options:
 * &quot;NHWC&quot;: {@code [ batch, height, width, channels ]}
 * &quot;NCHW&quot;: {@code [ batch, channels, height, width ]}
 * &quot;NCHW_VECT_C&quot;:
 * {@code qint8 [ batch, channels / 4, height, width, 4 ]}
 * <p>It is useful to consider the operation as transforming a 6-D Tensor.
 * e.g. for data_format = NHWC,
 * Each element in the input tensor can be specified via 6 coordinates,
 * ordered by decreasing memory layout significance as:
 * n,iY,iX,bY,bX,oC  (where n=batch index, iX, iY means X or Y coordinates
 * within the input image, bX, bY means coordinates
 * within the output block, oC means output channels).
 * The output would be the input transposed to the following layout:
 * n,iY,bY,iX,bX,oC
 * <p>This operation is useful for resizing the activations between convolutions
 * (but keeping all data), e.g. instead of pooling. It is also useful for training
 * purely convolutional models.
 * <p>For example, given an input of shape {@code [1, 1, 1, 4]}, data_format = &quot;NHWC&quot; and
 * block_size = 2:
 * <pre>
 * x = [[[[1, 2, 3, 4]]]]
 *
 * </pre>
 * <p>This operation will output a tensor of shape {@code [1, 2, 2, 1]}:
 * <pre>
 *    [[[[1], [2]],
 *      [[3], [4]]]]
 * </pre>
 * <p>Here, the input has a batch of 1 and each batch element has shape {@code [1, 1, 4]},
 * the corresponding output will have 2x2 elements and will have a depth of
 * 1 channel (1 = {@code 4 / (block_size * block_size)}).
 * The output element shape is {@code [2, 2, 1]}.
 * <p>For an input tensor with larger depth, here of shape {@code [1, 1, 1, 12]}, e.g.
 * <pre>
 * x = [[[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]]]
 * </pre>
 * <p>This operation, for block size of 2, will return the following tensor of shape
 * {@code [1, 2, 2, 3]}
 * <pre>
 *    [[[[1, 2, 3], [4, 5, 6]],
 *      [[7, 8, 9], [10, 11, 12]]]]
 *
 * </pre>
 * <p>Similarly, for the following input of shape {@code [1 2 2 4]}, and a block size of 2:
 * <pre>
 * x =  [[[[1, 2, 3, 4],
 *        [5, 6, 7, 8]],
 *       [[9, 10, 11, 12],
 *        [13, 14, 15, 16]]]]
 * </pre>
 * <p>the operator will return the following tensor of shape {@code [1 4 4 1]}:
 * <pre>
 * x = [[[ [1],   [2],  [5],  [6]],
 *       [ [3],   [4],  [7],  [8]],
 *       [ [9],  [10], [13],  [14]],
 *       [ [11], [12], [15],  [16]]]]
 *
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DepthToSpace.OP_NAME,
    inputsClass = DepthToSpace.Inputs.class
)
@Operator(
    group = "nn"
)
public final class DepthToSpace<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DepthToSpace";

  private Output<T> output;

  public DepthToSpace(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DepthToSpace operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param blockSize The size of the spatial block, same as in Space2Depth.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DepthToSpace} output and operands
   * @return a new instance of DepthToSpace
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DepthToSpace<T> create(Scope scope, Operand<T> input,
      Long blockSize, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DepthToSpace");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("block_size", blockSize);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
      }
    }
    return new DepthToSpace<>(opBuilder.build());
  }

  /**
   * Sets the dataFormat option.
   *
   * @param dataFormat the dataFormat option
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.DepthToSpace}
   */
  public static class Options {
    private String dataFormat;

    private Options() {
    }

    /**
     * Sets the dataFormat option.
     *
     * @param dataFormat the dataFormat option
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DepthToSpace.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<DepthToSpace<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The size of the spatial block, same as in Space2Depth.
     */
    public final long blockSize;

    /**
     * The dataFormat attribute
     */
    public final String dataFormat;

    public Inputs(GraphOperation op) {
      super(new DepthToSpace<>(op), op, Arrays.asList("T", "block_size", "data_format"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      blockSize = op.attributes().getAttrInt("block_size");
      dataFormat = op.attributes().getAttrString("data_format");
    }
  }
}
