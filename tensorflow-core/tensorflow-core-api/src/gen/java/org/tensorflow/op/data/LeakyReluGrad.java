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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Computes rectified linear gradients for a LeakyRelu operation.
 *
 * @param <T> data type for {@code backprops} output
 */
@OpMetadata(
    opType = LeakyReluGrad.OP_NAME,
    inputsClass = LeakyReluGrad.Inputs.class
)
public final class LeakyReluGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LeakyReluGrad";

  private Output<T> backprops;

  public LeakyReluGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    backprops = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LeakyReluGrad operation.
   *
   * @param scope current scope
   * @param gradients The backpropagated gradients to the corresponding LeakyRelu operation.
   * @param features The features passed as input to the corresponding LeakyRelu operation,
   * OR the outputs of that operation (both work equivalently).
   * @param options carries optional attribute values
   * @param <T> data type for {@code LeakyReluGrad} output and operands
   * @return a new instance of LeakyReluGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> LeakyReluGrad<T> create(Scope scope, Operand<T> gradients,
      Operand<T> features, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LeakyReluGrad");
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(features.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.alpha != null) {
          opBuilder.setAttr("alpha", opts.alpha);
        }
      }
    }
    return new LeakyReluGrad<>(opBuilder.build());
  }

  /**
   * Sets the alpha option.
   *
   * @param alpha the alpha option
   * @return this Options instance.
   */
  public static Options alpha(Float alpha) {
    return new Options().alpha(alpha);
  }

  /**
   * Gets backprops.
   * {@code gradients * (features > 0) + alpha * gradients * (features <= 0)}.
   * @return backprops.
   */
  public Output<T> backprops() {
    return backprops;
  }

  @Override
  public Output<T> asOutput() {
    return backprops;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.LeakyReluGrad}
   */
  public static class Options {
    private Float alpha;

    private Options() {
    }

    /**
     * Sets the alpha option.
     *
     * @param alpha the alpha option
     * @return this Options instance.
     */
    public Options alpha(Float alpha) {
      this.alpha = alpha;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = LeakyReluGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<LeakyReluGrad<T>> {
    /**
     * The backpropagated gradients to the corresponding LeakyRelu operation.
     */
    public final Operand<T> gradients;

    /**
     * The features passed as input to the corresponding LeakyRelu operation,
     * OR the outputs of that operation (both work equivalently).
     */
    public final Operand<T> features;

    /**
     * The alpha attribute
     */
    public final float alpha;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new LeakyReluGrad<>(op), op, Arrays.asList("alpha", "T"));
      int inputIndex = 0;
      gradients = (Operand<T>) op.input(inputIndex++);
      features = (Operand<T>) op.input(inputIndex++);
      alpha = op.attributes().getAttrFloat("alpha");
      T = op.attributes().getAttrType("T");
    }
  }
}
