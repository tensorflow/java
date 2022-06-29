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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
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
 * Concatenates a list of {@code N} tensors along the first dimension.
 * The input tensors are all required to have size 1 in the first dimension.
 * <p>For example:
 * <pre>
 * # 'x' is [[1, 4]]
 * # 'y' is [[2, 5]]
 * # 'z' is [[3, 6]]
 * parallel_concat([x, y, z]) =&gt; [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
 * </pre>
 * <p>The difference between concat and parallel_concat is that concat requires all
 * of the inputs be computed before the operation will begin but doesn't require
 * that the input shapes be known during graph construction.  Parallel concat
 * will copy pieces of the input into the output as they become available, in
 * some situations this can provide a performance benefit.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ParallelConcat.OP_NAME,
    inputsClass = ParallelConcat.Inputs.class
)
@Operator
public final class ParallelConcat<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParallelConcat";

  private Output<T> output;

  public ParallelConcat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParallelConcat operation.
   *
   * @param scope current scope
   * @param values Tensors to be concatenated. All must have size 1 in the first dimension
   * and same shape.
   * @param shape the final shape of the result; should be equal to the shapes of any input
   * but with the number of input values in the first dimension.
   * @param <T> data type for {@code ParallelConcat} output and operands
   * @return a new instance of ParallelConcat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ParallelConcat<T> create(Scope scope, Iterable<Operand<T>> values,
      Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParallelConcat");
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.setAttr("shape", shape);
    return new ParallelConcat<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The concatenated tensor.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = ParallelConcat.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ParallelConcat<T>> {
    /**
     * Tensors to be concatenated. All must have size 1 in the first dimension
     * and same shape.
     */
    public final Iterable<Operand<T>> values;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * the final shape of the result; should be equal to the shapes of any input
     * but with the number of input values in the first dimension.
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new ParallelConcat<>(op), op, Arrays.asList("T", "shape"));
      int inputIndex = 0;
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      T = op.attributes().getAttrType("T");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
