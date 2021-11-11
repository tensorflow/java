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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Returns the rank of a tensor.
 * This operation returns an integer representing the rank of {@code input}.
 * <p>For example:
 * <pre>
 * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
 * # shape of tensor 't' is [2, 2, 3]
 * rank(t) ==&gt; 3
 * </pre>
 * <p><strong>Note</strong>: The rank of a tensor is not the same as the rank of a matrix. The rank
 * of a tensor is the number of indices required to uniquely select each element
 * of the tensor. Rank is also known as &quot;order&quot;, &quot;degree&quot;, or &quot;ndims.&quot;
 */
@OpMetadata(
    opType = Rank.OP_NAME,
    inputsClass = Rank.Inputs.class
)
@Operator
public final class Rank extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Rank";

  private Output<TInt32> output;

  public Rank(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Rank operation.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of Rank
   */
  @Endpoint(
      describeByClass = true
  )
  public static Rank create(Scope scope, Operand<? extends TType> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Rank");
    opBuilder.addInput(input.asOutput());
    return new Rank(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Rank.class
  )
  public static class Inputs extends RawOpInputs<Rank> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Rank(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
