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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Concats input tensor across all dimensions.
 * An op which merges slices the input tensor based on the given num_splits
 * attribute, strips paddings optionally, and writes the merged tensor without
 * paddings to the resource variable.
 * <p>This op may be generated via the TPU bridge.
 * <p>For example, with {@code input} tensor:
 * <pre>
 * [[0, 1],
 *  [4, 5]]
 * [[2, 3],
 *  [6, 7]]
 * [[8, 9],
 *  [12, 13]]
 * [[10, 11],
 *  [14, 15]]
 * </pre>
 * <p>{@code num_splits}:
 * <pre>
 * [2, 2]
 * </pre>
 * <p>and {@code paddings}:
 * <pre>
 * [1, 1]
 * </pre>
 * <p>the expected {@code outputs} is:
 * <pre>
 * [[0, 1, 2],
 *  [4, 5, 6],
 *  [8, 9, 10]]
 * </pre>
 */
@OpMetadata(
    opType = AssignVariableConcatND.OP_NAME,
    inputsClass = AssignVariableConcatND.Inputs.class
)
public final class AssignVariableConcatND extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AssignVariableXlaConcatND";

  public AssignVariableConcatND(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new AssignVariableXlaConcatND operation.
   *
   * @param scope current scope
   * @param resource Resource variable for concatenated input tensors across all dimensions.
   * }
   * in_arg {
   * name: &quot;inputs&quot;
   * description: &lt;&lt;END
   * Input tensor slices in row-major order to merge across all dimensions. All
   * inputs must have the same shape.
   * }
   * out_arg {
   * name: &quot;output&quot;
   * description: &lt;&lt;END
   * Output tensor formed from merging input slices based on num_concats defined.
   * @param inputs The inputs value
   * @param numConcats Number of ways to merge per dimension.
   * @param options carries optional attribute values
   * @return a new instance of AssignVariableConcatND
   */
  @Endpoint(
      describeByClass = true
  )
  public static AssignVariableConcatND create(Scope scope, Operand<? extends TType> resource,
      Iterable<Operand<? extends TType>> inputs, List<Long> numConcats, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AssignVariableConcatND");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInputList(Operands.asOutputs(inputs));
    long[] numConcatsArray = new long[numConcats.size()];
    for (int i = 0 ; i < numConcatsArray.length ; i++) {
      numConcatsArray[i] = numConcats.get(i);
    }
    opBuilder.setAttr("num_concats", numConcatsArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.paddings != null) {
          long[] paddingsArray = new long[opts.paddings.size()];
          for (int i = 0 ; i < paddingsArray.length ; i++) {
            paddingsArray[i] = opts.paddings.get(i);
          }
          opBuilder.setAttr("paddings", paddingsArray);
        }
      }
    }
    return new AssignVariableConcatND(opBuilder.build());
  }

  /**
   * Sets the paddings option.
   *
   * @param paddings Optional list of right paddings per dimension to strip from the final merged
   * tensor. These paddings must not exceed the dimension size of the merged result
   * prior to stripping paddings.
   * @return this Options instance.
   */
  public static Options paddings(List<Long> paddings) {
    return new Options().paddings(paddings);
  }

  /**
   * Sets the paddings option.
   *
   * @param paddings Optional list of right paddings per dimension to strip from the final merged
   * tensor. These paddings must not exceed the dimension size of the merged result
   * prior to stripping paddings.
   * @return this Options instance.
   */
  public static Options paddings(Long... paddings) {
    return new Options().paddings(paddings);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.AssignVariableConcatND}
   */
  public static class Options {
    private List<Long> paddings;

    private Options() {
    }

    /**
     * Sets the paddings option.
     *
     * @param paddings Optional list of right paddings per dimension to strip from the final merged
     * tensor. These paddings must not exceed the dimension size of the merged result
     * prior to stripping paddings.
     * @return this Options instance.
     */
    public Options paddings(List<Long> paddings) {
      this.paddings = paddings;
      return this;
    }

    /**
     * Sets the paddings option.
     *
     * @param paddings Optional list of right paddings per dimension to strip from the final merged
     * tensor. These paddings must not exceed the dimension size of the merged result
     * prior to stripping paddings.
     * @return this Options instance.
     */
    public Options paddings(Long... paddings) {
      this.paddings = Arrays.asList(paddings);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AssignVariableConcatND.class
  )
  public static class Inputs extends RawOpInputs<AssignVariableConcatND> {
    /**
     * Resource variable for concatenated input tensors across all dimensions.
     * }
     * in_arg {
     * name: &quot;inputs&quot;
     * description: &lt;&lt;END
     * Input tensor slices in row-major order to merge across all dimensions. All
     * inputs must have the same shape.
     * }
     * out_arg {
     * name: &quot;output&quot;
     * description: &lt;&lt;END
     * Output tensor formed from merging input slices based on num_concats defined.
     */
    public final Operand<? extends TType> resource;

    /**
     * The inputs input
     */
    public final Iterable<Operand<? extends TType>> inputs;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Number of ways to merge per dimension.
     */
    public final long[] numConcats;

    /**
     * Optional list of right paddings per dimension to strip from the final merged
     * tensor. These paddings must not exceed the dimension size of the merged result
     * prior to stripping paddings.
     */
    public final long[] paddings;

    public Inputs(GraphOperation op) {
      super(new AssignVariableConcatND(op), op, Arrays.asList("T", "num_concats", "paddings"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      T = op.attributes().getAttrType("T");
      numConcats = op.attributes().getAttrIntList("num_concats");
      paddings = op.attributes().getAttrIntList("paddings");
    }
  }
}
