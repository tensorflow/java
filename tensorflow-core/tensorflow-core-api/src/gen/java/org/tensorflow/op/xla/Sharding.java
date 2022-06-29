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
 * An op which shards the input based on the given sharding attribute. It can
 * selectively annotate a subset of tensor dimensions by skipping unspecified_dims,
 * and the sharding annotation should be replicated in those dims.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Sharding.OP_NAME,
    inputsClass = Sharding.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Sharding<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSharding";

  private Output<T> output;

  public Sharding(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSharding operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSharding} output and operands
   * @return a new instance of Sharding
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Sharding<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Sharding");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.sharding != null) {
          opBuilder.setAttr("sharding", opts.sharding);
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
    return new Sharding<>(opBuilder.build());
  }

  /**
   * Sets the sharding option.
   *
   * @param sharding the sharding option
   * @return this Options instance.
   */
  public static Options sharding(String sharding) {
    return new Options().sharding(sharding);
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
   * Optional attributes for {@link org.tensorflow.op.xla.Sharding}
   */
  public static class Options {
    private String sharding;

    private List<Long> unspecifiedDims;

    private Options() {
    }

    /**
     * Sets the sharding option.
     *
     * @param sharding the sharding option
     * @return this Options instance.
     */
    public Options sharding(String sharding) {
      this.sharding = sharding;
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
      outputsClass = Sharding.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Sharding<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The sharding attribute
     */
    public final String sharding;

    /**
     * The unspecifiedDims attribute
     */
    public final long[] unspecifiedDims;

    public Inputs(GraphOperation op) {
      super(new Sharding<>(op), op, Arrays.asList("T", "sharding", "unspecified_dims"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      sharding = op.attributes().getAttrString("sharding");
      unspecifiedDims = op.attributes().getAttrIntList("unspecified_dims");
    }
  }
}
