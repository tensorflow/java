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

package org.tensorflow.op.data;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Makes a new iterator from the given {@code dataset} and stores it in {@code iterator}.
 * This operation may be executed multiple times. Each execution will reset the
 * iterator in {@code iterator} to the first element of {@code dataset}.
 */
@OpMetadata(
    opType = MakeIterator.OP_NAME,
    inputsClass = MakeIterator.Inputs.class
)
@Operator(
    group = "data"
)
public final class MakeIterator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MakeIterator";

  public MakeIterator(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new MakeIterator operation.
   *
   * @param scope current scope
   * @param dataset The dataset value
   * @param iterator The iterator value
   * @return a new instance of MakeIterator
   */
  @Endpoint(
      describeByClass = true
  )
  public static MakeIterator create(Scope scope, Operand<? extends TType> dataset,
      Operand<? extends TType> iterator) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MakeIterator");
    opBuilder.addInput(dataset.asOutput());
    opBuilder.addInput(iterator.asOutput());
    return new MakeIterator(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = MakeIterator.class
  )
  public static class Inputs extends RawOpInputs<MakeIterator> {
    /**
     * The dataset input
     */
    public final Operand<? extends TType> dataset;

    /**
     * The iterator input
     */
    public final Operand<? extends TType> iterator;

    public Inputs(GraphOperation op) {
      super(new MakeIterator(op), op, Arrays.asList());
      int inputIndex = 0;
      dataset = (Operand<? extends TType>) op.input(inputIndex++);
      iterator = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
