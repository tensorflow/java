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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Finds values and indices of the {@code k} largest elements for the last dimension.
 * If the input is a vector (rank-1), finds the {@code k} largest entries in the vector
 * and outputs their values and indices as vectors.  Thus {@code values[j]} is the
 * {@code j}-th largest entry in {@code input}, and its index is {@code indices[j]}.
 * <p>For matrices (resp. higher rank input), computes the top {@code k} entries in each
 * row (resp. vector along the last dimension).  Thus,
 * <pre>
 * values.shape = indices.shape = input.shape[:-1] + [k]
 * </pre>
 * <p>If two elements are equal, the lower-index element appears first.
 *
 * @param <T> data type for {@code values} output
 */
@OpMetadata(
    opType = TopK.OP_NAME,
    inputsClass = TopK.Inputs.class
)
@Operator(
    group = "nn"
)
public final class TopK<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TopKV2";

  private Output<T> values;

  private Output<TInt32> indices;

  public TopK(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    values = operation.output(outputIdx++);
    indices = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TopKV2 operation.
   *
   * @param scope current scope
   * @param input 1-D or higher with last dimension at least {@code k}.
   * @param k 0-D.  Number of top elements to look for along the last dimension (along each
   * row for matrices).
   * @param options carries optional attribute values
   * @param <T> data type for {@code TopKV2} output and operands
   * @return a new instance of TopK
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> TopK<T> create(Scope scope, Operand<T> input, Operand<TInt32> k,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TopK");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(k.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.sorted != null) {
          opBuilder.setAttr("sorted", opts.sorted);
        }
      }
    }
    return new TopK<>(opBuilder.build());
  }

  /**
   * Sets the sorted option.
   *
   * @param sorted If true the resulting {@code k} elements will be sorted by the values in
   * descending order.
   * @return this Options instance.
   */
  public static Options sorted(Boolean sorted) {
    return new Options().sorted(sorted);
  }

  /**
   * Gets values.
   * The {@code k} largest elements along each last dimensional slice.
   * @return values.
   */
  public Output<T> values() {
    return values;
  }

  /**
   * Gets indices.
   * The indices of {@code values} within the last dimension of {@code input}.
   * @return indices.
   */
  public Output<TInt32> indices() {
    return indices;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.TopK}
   */
  public static class Options {
    private Boolean sorted;

    private Options() {
    }

    /**
     * Sets the sorted option.
     *
     * @param sorted If true the resulting {@code k} elements will be sorted by the values in
     * descending order.
     * @return this Options instance.
     */
    public Options sorted(Boolean sorted) {
      this.sorted = sorted;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TopK.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<TopK<T>> {
    /**
     * 1-D or higher with last dimension at least {@code k}.
     */
    public final Operand<T> input;

    /**
     * 0-D.  Number of top elements to look for along the last dimension (along each
     * row for matrices).
     */
    public final Operand<TInt32> k;

    /**
     * If true the resulting `k` elements will be sorted by the values in
     * descending order.
     */
    public final boolean sorted;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TopK<>(op), op, Arrays.asList("sorted", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      k = (Operand<TInt32>) op.input(inputIndex++);
      sorted = op.attributes().getAttrBool("sorted");
      T = op.attributes().getAttrType("T");
    }
  }
}
