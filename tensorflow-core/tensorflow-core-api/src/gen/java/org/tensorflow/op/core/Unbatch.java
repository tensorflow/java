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
 * Reverses the operation of Batch for a single output Tensor.
 * An instance of Unbatch either receives an empty batched_tensor, in which case it
 * asynchronously waits until the values become available from a concurrently
 * running instance of Unbatch with the same container and shared_name, or receives
 * a non-empty batched_tensor in which case it finalizes all other concurrently
 * running instances and outputs its own element from the batch.
 * <p>batched_tensor: The possibly transformed output of Batch. The size of the first
 * dimension should remain unchanged by the transformations for the operation to
 * work.
 * batch_index: The matching batch_index obtained from Batch.
 * id: The id scalar emitted by Batch.
 * unbatched_tensor: The Tensor corresponding to this execution.
 * timeout_micros: Maximum amount of time (in microseconds) to wait to receive the
 * batched input tensor associated with a given invocation of the op.
 * container: Container to control resource sharing.
 * shared_name: Instances of Unbatch with the same container and shared_name are
 * assumed to possibly belong to the same batch. If left empty, the op name will
 * be used as the shared name.
 *
 * @param <T> data type for {@code unbatched_tensor} output
 */
@OpMetadata(
    opType = Unbatch.OP_NAME,
    inputsClass = Unbatch.Inputs.class
)
@Operator
public final class Unbatch<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Unbatch";

  private Output<T> unbatchedTensor;

  public Unbatch(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    unbatchedTensor = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Unbatch operation.
   *
   * @param scope current scope
   * @param batchedTensor The batchedTensor value
   * @param batchIndex The batchIndex value
   * @param id The id value
   * @param timeoutMicros The value of the timeoutMicros attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code Unbatch} output and operands
   * @return a new instance of Unbatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Unbatch<T> create(Scope scope, Operand<T> batchedTensor,
      Operand<TInt64> batchIndex, Operand<TInt64> id, Long timeoutMicros, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Unbatch");
    opBuilder.addInput(batchedTensor.asOutput());
    opBuilder.addInput(batchIndex.asOutput());
    opBuilder.addInput(id.asOutput());
    opBuilder.setAttr("timeout_micros", timeoutMicros);
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
    return new Unbatch<>(opBuilder.build());
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
   * Gets unbatchedTensor.
   *
   * @return unbatchedTensor.
   */
  public Output<T> unbatchedTensor() {
    return unbatchedTensor;
  }

  @Override
  public Output<T> asOutput() {
    return unbatchedTensor;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Unbatch}
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
      outputsClass = Unbatch.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Unbatch<T>> {
    /**
     * The batchedTensor input
     */
    public final Operand<T> batchedTensor;

    /**
     * The batchIndex input
     */
    public final Operand<TInt64> batchIndex;

    /**
     * The id input
     */
    public final Operand<TInt64> id;

    /**
     * The timeoutMicros attribute
     */
    public final long timeoutMicros;

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
      super(new Unbatch<>(op), op, Arrays.asList("timeout_micros", "container", "shared_name", "T"));
      int inputIndex = 0;
      batchedTensor = (Operand<T>) op.input(inputIndex++);
      batchIndex = (Operand<TInt64>) op.input(inputIndex++);
      id = (Operand<TInt64>) op.input(inputIndex++);
      timeoutMicros = op.attributes().getAttrInt("timeout_micros");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      T = op.attributes().getAttrType("T");
    }
  }
}
