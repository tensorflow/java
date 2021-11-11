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

package org.tensorflow.op.ragged;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Gather ragged slices from {@code params} axis {@code 0} according to {@code indices}.
 * Outputs a {@code RaggedTensor} output composed from {@code output_dense_values} and
 * {@code output_nested_splits}, such that:
 * <pre>
 * output.shape = indices.shape + params.shape[1:]
 * output.ragged_rank = indices.shape.ndims + params.ragged_rank
 * output[i...j, d0...dn] = params[indices[i...j], d0...dn]
 * </pre>
 * <p>where
 * <ul>
 * <li>{@code params = ragged.from_nested_row_splits(params_dense_values, params_nested_splits)}
 * provides the values that should be gathered.</li>
 * <li>{@code indices} ia a dense tensor with dtype {@code int32} or {@code int64}, indicating which
 * values should be gathered.</li>
 * <li>{@code output = ragged.from_nested_row_splits(output_dense_values, output_nested_splits)}
 * is the output tensor.</li>
 * </ul>
 * <p>(Note: This c++ op is used to implement the higher-level python
 * {@code tf.ragged.gather} op, which also supports ragged indices.)
 *
 * @param <T> data type for {@code output_nested_splits} output
 *
 * @param <U> data type for {@code output_dense_values} output
 */
@OpMetadata(
    opType = RaggedGather.OP_NAME,
    inputsClass = RaggedGather.Inputs.class
)
public final class RaggedGather<T extends TNumber, U extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedGather";

  private List<Output<T>> outputNestedSplits;

  private Output<U> outputDenseValues;

  @SuppressWarnings("unchecked")
  public RaggedGather(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputNestedSplitsLength = operation.outputListLength("output_nested_splits");
    outputNestedSplits = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputNestedSplitsLength));
    outputIdx += outputNestedSplitsLength;
    outputDenseValues = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedGather operation.
   *
   * @param scope current scope
   * @param paramsNestedSplits The {@code nested_row_splits} tensors that define the row-partitioning for the
   * {@code params} RaggedTensor input.
   * @param paramsDenseValues The {@code flat_values} for the {@code params} RaggedTensor. There was a terminology change
   * at the python level from dense_values to flat_values, so dense_values is the
   * deprecated name.
   * @param indices Indices in the outermost dimension of {@code params} of the values that should be
   * gathered.
   * @param OUTPUTRAGGEDRANK The ragged rank of the output RaggedTensor. {@code output_nested_splits} will contain
   * this number of {@code row_splits} tensors. This value should equal
   * {@code indices.shape.ndims + params.ragged_rank - 1}.
   * @param <T> data type for {@code RaggedGather} output and operands
   * @param <U> data type for {@code RaggedGather} output and operands
   * @return a new instance of RaggedGather
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TType> RaggedGather<T, U> create(Scope scope,
      Iterable<Operand<T>> paramsNestedSplits, Operand<U> paramsDenseValues,
      Operand<? extends TNumber> indices, Long OUTPUTRAGGEDRANK) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedGather");
    opBuilder.addInputList(Operands.asOutputs(paramsNestedSplits));
    opBuilder.addInput(paramsDenseValues.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.setAttr("OUTPUT_RAGGED_RANK", OUTPUTRAGGEDRANK);
    return new RaggedGather<>(opBuilder.build());
  }

  /**
   * Gets outputNestedSplits.
   * The {@code nested_row_splits} tensors that define the row-partitioning for the
   * returned RaggedTensor.
   * @return outputNestedSplits.
   */
  public List<Output<T>> outputNestedSplits() {
    return outputNestedSplits;
  }

  /**
   * Gets outputDenseValues.
   * The {@code flat_values} for the returned RaggedTensor.
   * @return outputDenseValues.
   */
  public Output<U> outputDenseValues() {
    return outputDenseValues;
  }

  @OpInputsMetadata(
      outputsClass = RaggedGather.class
  )
  public static class Inputs<T extends TNumber, U extends TType> extends RawOpInputs<RaggedGather<T, U>> {
    /**
     * The {@code nested_row_splits} tensors that define the row-partitioning for the
     * {@code params} RaggedTensor input.
     */
    public final Iterable<Operand<T>> paramsNestedSplits;

    /**
     * The {@code flat_values} for the {@code params} RaggedTensor. There was a terminology change
     * at the python level from dense_values to flat_values, so dense_values is the
     * deprecated name.
     */
    public final Operand<U> paramsDenseValues;

    /**
     * Indices in the outermost dimension of {@code params} of the values that should be
     * gathered.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * The Tvalues attribute
     */
    public final DataType Tvalues;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new RaggedGather<>(op), op, Arrays.asList("Tvalues", "Tindices", "Tsplits"));
      int inputIndex = 0;
      int paramsNestedSplitsLength = op.inputListLength("params_nested_splits");
      paramsNestedSplits = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, paramsNestedSplitsLength));
      inputIndex += paramsNestedSplitsLength;
      paramsDenseValues = (Operand<U>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      Tvalues = op.attributes().getAttrType("Tvalues");
      Tindices = op.attributes().getAttrType("Tindices");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
