/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.tpu;

import java.util.Arrays;
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

/**
 * Enqueue multiple Tensor values on the computation outfeed.
 */
@OpMetadata(
    opType = OutfeedEnqueueTuple.OP_NAME,
    inputsClass = OutfeedEnqueueTuple.Inputs.class
)
public final class OutfeedEnqueueTuple extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OutfeedEnqueueTuple";

  public OutfeedEnqueueTuple(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new OutfeedEnqueueTuple operation.
   *
   * @param scope current scope
   * @param inputs A list of tensors that will be inserted into the outfeed queue as an
   * XLA tuple.
   * @return a new instance of OutfeedEnqueueTuple
   */
  @Endpoint(
      describeByClass = true
  )
  public static OutfeedEnqueueTuple create(Scope scope, Iterable<Operand<?>> inputs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OutfeedEnqueueTuple");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    return new OutfeedEnqueueTuple(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = OutfeedEnqueueTuple.class
  )
  public static class Inputs extends RawOpInputs<OutfeedEnqueueTuple> {
    /**
     * A list of tensors that will be inserted into the outfeed queue as an
     * XLA tuple.
     */
    public final Iterable<Operand<?>> inputs;

    /**
     * The dtypes attribute
     */
    public final DataType[] dtypes;

    public Inputs(GraphOperation op) {
      super(new OutfeedEnqueueTuple(op), op, Arrays.asList("dtypes"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      dtypes = op.attributes().getAttrTypeList("dtypes");
    }
  }
}
