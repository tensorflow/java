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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Creates or finds a child frame, and makes {@code data} available to the child frame.
 * This op is used together with {@code Exit} to create loops in the graph.
 * The unique {@code frame_name} is used by the {@code Executor} to identify frames. If
 * {@code is_constant} is true, {@code output} is a constant in the child frame; otherwise
 * it may be changed in the child frame. At most {@code parallel_iterations} iterations
 * are run in parallel in the child frame.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Enter.OP_NAME,
    inputsClass = Enter.Inputs.class
)
public final class Enter<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Enter";

  private Output<T> output;

  public Enter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Enter operation.
   *
   * @param scope current scope
   * @param data The tensor to be made available to the child frame.
   * @param frameName The name of the child frame.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Enter} output and operands
   * @return a new instance of Enter
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Enter<T> create(Scope scope, Operand<T> data, String frameName,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Enter");
    opBuilder.addInput(data.asOutput());
    opBuilder.setAttr("frame_name", frameName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.isConstant != null) {
          opBuilder.setAttr("is_constant", opts.isConstant);
        }
        if (opts.parallelIterations != null) {
          opBuilder.setAttr("parallel_iterations", opts.parallelIterations);
        }
      }
    }
    return new Enter<>(opBuilder.build());
  }

  /**
   * Sets the isConstant option.
   *
   * @param isConstant If true, the output is constant within the child frame.
   * @return this Options instance.
   */
  public static Options isConstant(Boolean isConstant) {
    return new Options().isConstant(isConstant);
  }

  /**
   * Sets the parallelIterations option.
   *
   * @param parallelIterations The number of iterations allowed to run in parallel.
   * @return this Options instance.
   */
  public static Options parallelIterations(Long parallelIterations) {
    return new Options().parallelIterations(parallelIterations);
  }

  /**
   * Gets output.
   * The same tensor as {@code data}.
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
   * Optional attributes for {@link org.tensorflow.op.core.Enter}
   */
  public static class Options {
    private Boolean isConstant;

    private Long parallelIterations;

    private Options() {
    }

    /**
     * Sets the isConstant option.
     *
     * @param isConstant If true, the output is constant within the child frame.
     * @return this Options instance.
     */
    public Options isConstant(Boolean isConstant) {
      this.isConstant = isConstant;
      return this;
    }

    /**
     * Sets the parallelIterations option.
     *
     * @param parallelIterations The number of iterations allowed to run in parallel.
     * @return this Options instance.
     */
    public Options parallelIterations(Long parallelIterations) {
      this.parallelIterations = parallelIterations;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Enter.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Enter<T>> {
    /**
     * The tensor to be made available to the child frame.
     */
    public final Operand<T> data;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The name of the child frame.
     */
    public final String frameName;

    /**
     * If true, the output is constant within the child frame.
     */
    public final boolean isConstant;

    /**
     * The number of iterations allowed to run in parallel.
     */
    public final long parallelIterations;

    public Inputs(GraphOperation op) {
      super(new Enter<>(op), op, Arrays.asList("T", "frame_name", "is_constant", "parallel_iterations"));
      int inputIndex = 0;
      data = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      frameName = op.attributes().getAttrString("frame_name");
      isConstant = op.attributes().getAttrBool("is_constant");
      parallelIterations = op.attributes().getAttrInt("parallel_iterations");
    }
  }
}
