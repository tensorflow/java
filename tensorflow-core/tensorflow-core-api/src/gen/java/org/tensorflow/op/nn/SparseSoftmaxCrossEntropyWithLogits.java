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
 * Computes softmax cross entropy cost and gradients to backpropagate.
 * Unlike {@code SoftmaxCrossEntropyWithLogits}, this operation does not accept
 * a matrix of label probabilities, but rather a single label per row
 * of features.  This label is considered to have probability 1.0 for the
 * given row.
 * <p>Inputs are the logits, not probabilities.
 *
 * @param <T> data type for {@code loss} output
 */
@OpMetadata(
    opType = SparseSoftmaxCrossEntropyWithLogits.OP_NAME,
    inputsClass = SparseSoftmaxCrossEntropyWithLogits.Inputs.class
)
@Operator(
    group = "nn"
)
public final class SparseSoftmaxCrossEntropyWithLogits<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSoftmaxCrossEntropyWithLogits";

  private Output<T> loss;

  private Output<T> backprop;

  public SparseSoftmaxCrossEntropyWithLogits(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    loss = operation.output(outputIdx++);
    backprop = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSoftmaxCrossEntropyWithLogits operation.
   *
   * @param scope current scope
   * @param features batch_size x num_classes matrix
   * @param labels batch_size vector with values in [0, num_classes).
   * This is the label for the given minibatch entry.
   * @param <T> data type for {@code SparseSoftmaxCrossEntropyWithLogits} output and operands
   * @return a new instance of SparseSoftmaxCrossEntropyWithLogits
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseSoftmaxCrossEntropyWithLogits<T> create(Scope scope,
      Operand<T> features, Operand<? extends TNumber> labels) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSoftmaxCrossEntropyWithLogits");
    opBuilder.addInput(features.asOutput());
    opBuilder.addInput(labels.asOutput());
    return new SparseSoftmaxCrossEntropyWithLogits<>(opBuilder.build());
  }

  /**
   * Gets loss.
   * Per example loss (batch_size vector).
   * @return loss.
   */
  public Output<T> loss() {
    return loss;
  }

  /**
   * Gets backprop.
   * backpropagated gradients (batch_size x num_classes matrix).
   * @return backprop.
   */
  public Output<T> backprop() {
    return backprop;
  }

  @OpInputsMetadata(
      outputsClass = SparseSoftmaxCrossEntropyWithLogits.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SparseSoftmaxCrossEntropyWithLogits<T>> {
    /**
     * batch_size x num_classes matrix
     */
    public final Operand<T> features;

    /**
     * batch_size vector with values in [0, num_classes).
     * This is the label for the given minibatch entry.
     */
    public final Operand<? extends TNumber> labels;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tlabels attribute
     */
    public final DataType Tlabels;

    public Inputs(GraphOperation op) {
      super(new SparseSoftmaxCrossEntropyWithLogits<>(op), op, Arrays.asList("T", "Tlabels"));
      int inputIndex = 0;
      features = (Operand<T>) op.input(inputIndex++);
      labels = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tlabels = op.attributes().getAttrType("Tlabels");
    }
  }
}
