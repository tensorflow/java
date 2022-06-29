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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Says whether the targets are in the top {@code K} predictions.
 * This outputs a {@code batch_size} bool array, an entry {@code out[i]} is {@code true} if the
 * prediction for the target class is among the top {@code k} predictions among
 * all predictions for example {@code i}. Note that the behavior of {@code InTopK} differs
 * from the {@code TopK} op in its handling of ties; if multiple classes have the
 * same prediction value and straddle the top-{@code k} boundary, all of those
 * classes are considered to be in the top {@code k}.
 * <p>More formally, let
 * <p>\(predictions_i\) be the predictions for all classes for example {@code i},
 * \(targets_i\) be the target class for example {@code i},
 * \(out_i\) be the output for example {@code i},
 * <p>$$out_i = predictions_{i, targets_i} \in TopKIncludingTies(predictions_i)$$
 */
@OpMetadata(
    opType = InTopK.OP_NAME,
    inputsClass = InTopK.Inputs.class
)
@Operator(
    group = "nn"
)
public final class InTopK extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InTopKV2";

  private Output<TBool> precision;

  public InTopK(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    precision = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new InTopKV2 operation.
   *
   * @param scope current scope
   * @param predictions A {@code batch_size} x {@code classes} tensor.
   * @param targets A {@code batch_size} vector of class ids.
   * @param k Number of top elements to look at for computing precision.
   * @param <T> data type for {@code InTopKV2} output and operands
   * @return a new instance of InTopK
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> InTopK create(Scope scope, Operand<TFloat32> predictions,
      Operand<T> targets, Operand<T> k) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "InTopK");
    opBuilder.addInput(predictions.asOutput());
    opBuilder.addInput(targets.asOutput());
    opBuilder.addInput(k.asOutput());
    return new InTopK(opBuilder.build());
  }

  /**
   * Gets precision.
   * Computed precision at {@code k} as a {@code bool Tensor}.
   * @return precision.
   */
  public Output<TBool> precision() {
    return precision;
  }

  @Override
  public Output<TBool> asOutput() {
    return precision;
  }

  @OpInputsMetadata(
      outputsClass = InTopK.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<InTopK> {
    /**
     * A {@code batch_size} x {@code classes} tensor.
     */
    public final Operand<TFloat32> predictions;

    /**
     * A {@code batch_size} vector of class ids.
     */
    public final Operand<T> targets;

    /**
     * Number of top elements to look at for computing precision.
     */
    public final Operand<T> k;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new InTopK(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      predictions = (Operand<TFloat32>) op.input(inputIndex++);
      targets = (Operand<T>) op.input(inputIndex++);
      k = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
