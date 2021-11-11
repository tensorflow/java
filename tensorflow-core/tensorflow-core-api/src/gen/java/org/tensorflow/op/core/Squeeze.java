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

package org.tensorflow.op.core;

import java.util.Arrays;
import java.util.List;
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
 * Removes dimensions of size 1 from the shape of a tensor.
 * Given a tensor {@code input}, this operation returns a tensor of the same type with
 * all dimensions of size 1 removed. If you don't want to remove all size 1
 * dimensions, you can remove specific size 1 dimensions by specifying
 * {@code axis}.
 * <p>For example:
 * <pre>
 * # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
 * shape(squeeze(t)) ==&gt; [2, 3]
 * </pre>
 * <p>Or, to remove specific size 1 dimensions:
 * <pre>
 * # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
 * shape(squeeze(t, [2, 4])) ==&gt; [1, 2, 3, 1]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Squeeze.OP_NAME,
    inputsClass = Squeeze.Inputs.class
)
@Operator
public final class Squeeze<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Squeeze";

  private Output<T> output;

  public Squeeze(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Squeeze operation.
   *
   * @param scope current scope
   * @param input The {@code input} to squeeze.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Squeeze} output and operands
   * @return a new instance of Squeeze
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Squeeze<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Squeeze");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          long[] squeezeDimsArray = new long[opts.axis.size()];
          for (int i = 0 ; i < squeezeDimsArray.length ; i++) {
            squeezeDimsArray[i] = opts.axis.get(i);
          }
          opBuilder.setAttr("squeeze_dims", squeezeDimsArray);
        }
      }
    }
    return new Squeeze<>(opBuilder.build());
  }

  /**
   * Sets the axis option.
   *
   * @param axis If specified, only squeezes the dimensions listed. The dimension
   * index starts at 0. It is an error to squeeze a dimension that is not 1. Must
   * be in the range {@code [-rank(input), rank(input))}.
   * @return this Options instance.
   */
  public static Options axis(List<Long> axis) {
    return new Options().axis(axis);
  }

  /**
   * Sets the axis option.
   *
   * @param axis If specified, only squeezes the dimensions listed. The dimension
   * index starts at 0. It is an error to squeeze a dimension that is not 1. Must
   * be in the range {@code [-rank(input), rank(input))}.
   * @return this Options instance.
   */
  public static Options axis(Long... axis) {
    return new Options().axis(axis);
  }

  /**
   * Gets output.
   * Contains the same data as {@code input}, but has one or more dimensions of
   * size 1 removed.
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
   * Optional attributes for {@link org.tensorflow.op.core.Squeeze}
   */
  public static class Options {
    private List<Long> axis;

    private Options() {
    }

    /**
     * Sets the axis option.
     *
     * @param axis If specified, only squeezes the dimensions listed. The dimension
     * index starts at 0. It is an error to squeeze a dimension that is not 1. Must
     * be in the range {@code [-rank(input), rank(input))}.
     * @return this Options instance.
     */
    public Options axis(List<Long> axis) {
      this.axis = axis;
      return this;
    }

    /**
     * Sets the axis option.
     *
     * @param axis If specified, only squeezes the dimensions listed. The dimension
     * index starts at 0. It is an error to squeeze a dimension that is not 1. Must
     * be in the range {@code [-rank(input), rank(input))}.
     * @return this Options instance.
     */
    public Options axis(Long... axis) {
      this.axis = Arrays.asList(axis);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Squeeze.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Squeeze<T>> {
    /**
     * The {@code input} to squeeze.
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If specified, only squeezes the dimensions listed. The dimension
     * index starts at 0. It is an error to squeeze a dimension that is not 1. Must
     * be in the range `[-rank(input), rank(input))`.
     */
    public final long[] axis;

    public Inputs(GraphOperation op) {
      super(new Squeeze<>(op), op, Arrays.asList("T", "squeeze_dims"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      axis = op.attributes().getAttrIntList("squeeze_dims");
    }
  }
}
