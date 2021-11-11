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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Gradient of Unbatch.
 * Acts like Batch but using the given batch_index index of batching things as they
 * become available. This ensures that the gradients are propagated back in the
 * same session which did the forward pass.
 * <p>original_input: The input to the Unbatch operation this is the gradient of.
 * batch_index: The batch_index given to the Unbatch operation this is the gradient
 * of.
 * grad: The downstream gradient.
 * id: The id scalar emitted by Batch.
 * batched_grad: The return value, either an empty tensor or the batched gradient.
 * container: Container to control resource sharing.
 * shared_name: Instances of UnbatchGrad with the same container and shared_name
 * are assumed to possibly belong to the same batch. If left empty, the op name
 * will be used as the shared name.
 *
 * @param <T> data type for {@code batched_grad} output
 */
@OpMetadata(
    opType = UnbatchGrad.OP_NAME,
    inputsClass = UnbatchGrad.Inputs.class
)
@Operator
public final class UnbatchGrad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UnbatchGrad";

  private Output<T> batchedGrad;

  public UnbatchGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    batchedGrad = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UnbatchGrad operation.
   *
   * @param scope current scope
   * @param originalInput The originalInput value
   * @param batchIndex The batchIndex value
   * @param grad The grad value
   * @param id The id value
   * @param options carries optional attribute values
   * @param <T> data type for {@code UnbatchGrad} output and operands
   * @return a new instance of UnbatchGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> UnbatchGrad<T> create(Scope scope, Operand<T> originalInput,
      Operand<TInt64> batchIndex, Operand<T> grad, Operand<TInt64> id, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UnbatchGrad");
    opBuilder.addInput(originalInput.asOutput());
    opBuilder.addInput(batchIndex.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(id.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new UnbatchGrad<>(opBuilder.build());
  }

  /**
   * Sets the container option.
   *
   * @param container the container option
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName the sharedName option
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Gets batchedGrad.
   *
   * @return batchedGrad.
   */
  public Output<T> batchedGrad() {
    return batchedGrad;
  }

  @Override
  public Output<T> asOutput() {
    return batchedGrad;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.UnbatchGrad}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UnbatchGrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<UnbatchGrad<T>> {
    /**
     * The originalInput input
     */
    public final Operand<T> originalInput;

    /**
     * The batchIndex input
     */
    public final Operand<TInt64> batchIndex;

    /**
     * The grad input
     */
    public final Operand<T> grad;

    /**
     * The id input
     */
    public final Operand<TInt64> id;

    /**
     * The container attribute
     */
    public final String container;

    /**
     * The sharedName attribute
     */
    public final String sharedName;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new UnbatchGrad<>(op), op, Arrays.asList("container", "shared_name", "T"));
      int inputIndex = 0;
      originalInput = (Operand<T>) op.input(inputIndex++);
      batchIndex = (Operand<TInt64>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      id = (Operand<TInt64>) op.input(inputIndex++);
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      T = op.attributes().getAttrType("T");
    }
  }
}
