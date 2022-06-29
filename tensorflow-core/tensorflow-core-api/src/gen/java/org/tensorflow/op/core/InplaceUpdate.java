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
 * Updates specified rows 'i' with values 'v'.
 * Computes {@code x[i, :] = v; return x}.
 * <p>Originally this function is mutative however for compilation we make this
 * operation create / operate on a copy of {@code x}.
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = InplaceUpdate.OP_NAME,
    inputsClass = InplaceUpdate.Inputs.class
)
@Operator
public final class InplaceUpdate<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InplaceUpdate";

  private Output<T> y;

  public InplaceUpdate(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new InplaceUpdate operation.
   *
   * @param scope current scope
   * @param x A tensor of type {@code T}.
   * @param i A vector. Indices into the left-most dimension of {@code x}.
   * @param v A {@code Tensor} of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @param <T> data type for {@code InplaceUpdate} output and operands
   * @return a new instance of InplaceUpdate
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> InplaceUpdate<T> create(Scope scope, Operand<T> x,
      Operand<TInt32> i, Operand<T> v) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "InplaceUpdate");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(i.asOutput());
    opBuilder.addInput(v.asOutput());
    return new InplaceUpdate<>(opBuilder.build());
  }

  /**
   * Gets y.
   * A {@code Tensor} of type T. An alias of {@code x}. The content of {@code y} is undefined if there are duplicates in {@code i}.
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  @Override
  public Output<T> asOutput() {
    return y;
  }

  @OpInputsMetadata(
      outputsClass = InplaceUpdate.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<InplaceUpdate<T>> {
    /**
     * A tensor of type {@code T}.
     */
    public final Operand<T> x;

    /**
     * A vector. Indices into the left-most dimension of {@code x}.
     */
    public final Operand<TInt32> i;

    /**
     * A {@code Tensor} of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
     */
    public final Operand<T> v;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new InplaceUpdate<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      i = (Operand<TInt32>) op.input(inputIndex++);
      v = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
