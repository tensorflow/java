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

package org.tensorflow.op.xla;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
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
 * An op used by XLA SPMD partitioner to switch from manual partitioning to
 * automatic partitioning. It converts the shard-shaped, manually partitioned input
 * into full-shaped tensor to be partitioned automatically with the same sharding
 * used by manual partitioning. The conversion can happen partially in subgroups,
 * by specifying the dim attribute, where only that dim will be converted.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SpmdShardToFullShape.OP_NAME,
    inputsClass = SpmdShardToFullShape.Inputs.class
)
@Operator(
    group = "xla"
)
public final class SpmdShardToFullShape<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSpmdShardToFullShape";

  private Output<T> output;

  public SpmdShardToFullShape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSpmdShardToFullShape operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param manualSharding The value of the manualSharding attribute
   * @param fullShape The value of the fullShape attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSpmdShardToFullShape} output and operands
   * @return a new instance of SpmdShardToFullShape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SpmdShardToFullShape<T> create(Scope scope, Operand<T> input,
      String manualSharding, Shape fullShape, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SpmdShardToFullShape");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("manual_sharding", manualSharding);
    opBuilder.setAttr("full_shape", fullShape);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dim != null) {
          opBuilder.setAttr("dim", opts.dim);
        }
        if (opts.unspecifiedDims != null) {
          long[] unspecifiedDimsArray = new long[opts.unspecifiedDims.size()];
          for (int i = 0 ; i < unspecifiedDimsArray.length ; i++) {
            unspecifiedDimsArray[i] = opts.unspecifiedDims.get(i);
          }
          opBuilder.setAttr("unspecified_dims", unspecifiedDimsArray);
        }
      }
    }
    return new SpmdShardToFullShape<>(opBuilder.build());
  }

  /**
   * Sets the dim option.
   *
   * @param dim the dim option
   * @return this Options instance.
   */
  public static Options dim(Long dim) {
    return new Options().dim(dim);
  }

  /**
   * Sets the unspecifiedDims option.
   *
   * @param unspecifiedDims the unspecifiedDims option
   * @return this Options instance.
   */
  public static Options unspecifiedDims(List<Long> unspecifiedDims) {
    return new Options().unspecifiedDims(unspecifiedDims);
  }

  /**
   * Sets the unspecifiedDims option.
   *
   * @param unspecifiedDims the unspecifiedDims option
   * @return this Options instance.
   */
  public static Options unspecifiedDims(Long... unspecifiedDims) {
    return new Options().unspecifiedDims(unspecifiedDims);
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
   * Optional attributes for {@link org.tensorflow.op.xla.SpmdShardToFullShape}
   */
  public static class Options {
    private Long dim;

    private List<Long> unspecifiedDims;

    private Options() {
    }

    /**
     * Sets the dim option.
     *
     * @param dim the dim option
     * @return this Options instance.
     */
    public Options dim(Long dim) {
      this.dim = dim;
      return this;
    }

    /**
     * Sets the unspecifiedDims option.
     *
     * @param unspecifiedDims the unspecifiedDims option
     * @return this Options instance.
     */
    public Options unspecifiedDims(List<Long> unspecifiedDims) {
      this.unspecifiedDims = unspecifiedDims;
      return this;
    }

    /**
     * Sets the unspecifiedDims option.
     *
     * @param unspecifiedDims the unspecifiedDims option
     * @return this Options instance.
     */
    public Options unspecifiedDims(Long... unspecifiedDims) {
      this.unspecifiedDims = Arrays.asList(unspecifiedDims);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SpmdShardToFullShape.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SpmdShardToFullShape<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The manualSharding attribute
     */
    public final String manualSharding;

    /**
     * The fullShape attribute
     */
    public final Shape fullShape;

    /**
     * The dim attribute
     */
    public final long dim;

    /**
     * The unspecifiedDims attribute
     */
    public final long[] unspecifiedDims;

    public Inputs(GraphOperation op) {
      super(new SpmdShardToFullShape<>(op), op, Arrays.asList("T", "manual_sharding", "full_shape", "dim", "unspecified_dims"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      manualSharding = op.attributes().getAttrString("manual_sharding");
      fullShape = op.attributes().getAttrShape("full_shape");
      dim = op.attributes().getAttrInt("dim");
      unspecifiedDims = op.attributes().getAttrIntList("unspecified_dims");
    }
  }
}
