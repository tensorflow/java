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

package org.tensorflow.op.nn;

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

/**
 * Computes softmax activations.
 * For each batch {@code i} and class {@code j} we have
 * <pre>
 * $$softmax[i, j] = exp(logits[i, j]) / sum_j(exp(logits[i, j]))$$
 * </pre>
 *
 * @param <T> data type for {@code softmax} output
 */
@OpMetadata(
    opType = Softmax.OP_NAME,
    inputsClass = Softmax.Inputs.class
)
@Operator(
    group = "nn"
)
public final class Softmax<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Softmax";

  private Output<T> softmax;

  public Softmax(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    softmax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Softmax operation.
   *
   * @param scope current scope
   * @param logits 2-D with shape {@code [batch_size, num_classes]}.
   * @param <T> data type for {@code Softmax} output and operands
   * @return a new instance of Softmax
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Softmax<T> create(Scope scope, Operand<T> logits) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Softmax");
    opBuilder.addInput(logits.asOutput());
    return new Softmax<>(opBuilder.build());
  }

  /**
   * Gets softmax.
   * Same shape as {@code logits}.
   * @return softmax.
   */
  public Output<T> softmax() {
    return softmax;
  }

  @Override
  public Output<T> asOutput() {
    return softmax;
  }

  @OpInputsMetadata(
      outputsClass = Softmax.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Softmax<T>> {
    /**
     * 2-D with shape {@code [batch_size, num_classes]}.
     */
    public final Operand<T> logits;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Softmax<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      logits = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
