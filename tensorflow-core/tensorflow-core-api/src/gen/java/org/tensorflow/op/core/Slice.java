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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Return a slice from 'input'.
 * The output tensor is a tensor with dimensions described by 'size'
 * whose values are extracted from 'input' starting at the offsets in
 * 'begin'.
 * <p><em>Requirements</em>:
 * 0 &lt;= begin[i] &lt;= begin[i] + size[i] &lt;= Di  for i in [0, n)
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Slice.OP_NAME,
    inputsClass = Slice.Inputs.class
)
@Operator
public final class Slice<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Slice";

  private Output<T> output;

  public Slice(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Slice operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param begin begin[i] specifies the offset into the 'i'th dimension of
   * 'input' to slice from.
   * @param sizeOutput size[i] specifies the number of elements of the 'i'th dimension
   * of 'input' to slice. If size[i] is -1, all remaining elements in dimension
   * i are included in the slice (i.e. this is equivalent to setting
   * size[i] = input.dim_size(i) - begin[i]).
   * @param <T> data type for {@code Slice} output and operands
   * @param <U> data type for {@code Slice} output and operands
   * @return a new instance of Slice
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> Slice<T> create(Scope scope, Operand<T> input,
      Operand<U> begin, Operand<U> sizeOutput) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Slice");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    return new Slice<>(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = Slice.class
  )
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<Slice<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * begin[i] specifies the offset into the 'i'th dimension of
     * 'input' to slice from.
     */
    public final Operand<U> begin;

    /**
     * size[i] specifies the number of elements of the 'i'th dimension
     * of 'input' to slice. If size[i] is -1, all remaining elements in dimension
     * i are included in the slice (i.e. this is equivalent to setting
     * size[i] = input.dim_size(i) - begin[i]).
     */
    public final Operand<U> sizeOutput;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Index attribute
     */
    public final DataType Index;

    public Inputs(GraphOperation op) {
      super(new Slice<>(op), op, Arrays.asList("T", "Index"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      begin = (Operand<U>) op.input(inputIndex++);
      sizeOutput = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Index = op.attributes().getAttrType("Index");
    }
  }
}
