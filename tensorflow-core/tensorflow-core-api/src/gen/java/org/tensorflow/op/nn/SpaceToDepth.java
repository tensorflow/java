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
 * SpaceToDepth for tensors of type T.
 * Rearranges blocks of spatial data, into depth. More specifically,
 * this op outputs a copy of the input tensor where values from the {@code height}
 * and {@code width} dimensions are moved to the {@code depth} dimension.
 * The attr {@code block_size} indicates the input block size.
 * <ul>
 * <li>Non-overlapping blocks of size {@code block_size x block size} are rearranged
 * into depth at each location.</li>
 * <li>The depth of the output tensor is {@code block_size * block_size * input_depth}.</li>
 * <li>The Y, X coordinates within each block of the input become the high order
 * component of the output channel index.</li>
 * <li>The input tensor's height and width must be divisible by block_size.</li>
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
 * n,oY,bY,oX,bX,iC  (where n=batch index, oX, oY means X or Y coordinates
 * within the output image, bX, bY means coordinates
 * within the input block, iC means input channels).
 * The output would be a transpose to the following layout:
 * n,oY,oX,bY,bX,iC
 * <p>This operation is useful for resizing the activations between convolutions
 * (but keeping all data), e.g. instead of pooling. It is also useful for training
 * purely convolutional models.
 * <p>For example, given an input of shape {@code [1, 2, 2, 1]}, data_format = &quot;NHWC&quot; and
 * block_size = 2:
 * <pre>
 * x = [[[[1], [2]],
 *       [[3], [4]]]]
 * </pre>
 * <p>This operation will output a tensor of shape {@code [1, 1, 1, 4]}:
 * <pre>
 * [[[[1, 2, 3, 4]]]]
 * </pre>
 * <p>Here, the input has a batch of 1 and each batch element has shape {@code [2, 2, 1]},
 * the corresponding output will have a single element (i.e. width and height are
 * both 1) and will have a depth of 4 channels (1 * block_size * block_size).
 * The output element shape is {@code [1, 1, 4]}.
 * <p>For an input tensor with larger depth, here of shape {@code [1, 2, 2, 3]}, e.g.
 * <pre>
 * x = [[[[1, 2, 3], [4, 5, 6]],
 *       [[7, 8, 9], [10, 11, 12]]]]
 * </pre>
 * <p>This operation, for block_size of 2, will return the following tensor of shape
 * {@code [1, 1, 1, 12]}
 * <pre>
 * [[[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]]]
 * </pre>
 * <p>Similarly, for the following input of shape {@code [1 4 4 1]}, and a block size of 2:
 * <pre>
 * x = [[[[1],   [2],  [5],  [6]],
 *       [[3],   [4],  [7],  [8]],
 *       [[9],  [10], [13],  [14]],
 *       [[11], [12], [15],  [16]]]]
 * </pre>
 * <p>the operator will return the following tensor of shape {@code [1 2 2 4]}:
 * <pre>
 * x = [[[[1, 2, 3, 4],
 *        [5, 6, 7, 8]],
 *       [[9, 10, 11, 12],
 *        [13, 14, 15, 16]]]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SpaceToDepth.OP_NAME,
    inputsClass = SpaceToDepth.Inputs.class
)
@Operator(
    group = "nn"
)
public final class SpaceToDepth<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SpaceToDepth";

  private Output<T> output;

  public SpaceToDepth(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SpaceToDepth operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param blockSize The size of the spatial block.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SpaceToDepth} output and operands
   * @return a new instance of SpaceToDepth
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SpaceToDepth<T> create(Scope scope, Operand<T> input,
      Long blockSize, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SpaceToDepth");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("block_size", blockSize);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
      }
    }
    return new SpaceToDepth<>(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.nn.SpaceToDepth}
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
      outputsClass = SpaceToDepth.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SpaceToDepth<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The size of the spatial block.
     */
    public final long blockSize;

    /**
     * The dataFormat attribute
     */
    public final String dataFormat;

    public Inputs(GraphOperation op) {
      super(new SpaceToDepth<>(op), op, Arrays.asList("T", "block_size", "data_format"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      blockSize = op.attributes().getAttrInt("block_size");
      dataFormat = op.attributes().getAttrString("data_format");
    }
  }
}
