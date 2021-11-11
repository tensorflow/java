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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Decodes a {@code variant} Tensor into a {@code RaggedTensor}.
 * Decodes the given {@code variant} Tensor and returns a {@code RaggedTensor}. The input
 * could be a scalar, meaning it encodes a single {@code RaggedTensor} with ragged_rank
 * {@code output_ragged_rank}. It could also have an arbitrary rank, in which case each
 * element is decoded into a {@code RaggedTensor} with ragged_rank {@code input_ragged_rank}
 * and these are then stacked according to the input shape to output a single
 * {@code RaggedTensor} with ragged_rank {@code output_ragged_rank}. Each {@code variant} element in
 * the input Tensor is decoded by retrieving from the element a 1-D {@code variant}
 * Tensor with {@code input_ragged_rank + 1} Tensors, corresponding to the splits and
 * values of the decoded {@code RaggedTensor}. If {@code input_ragged_rank} is -1, then it is
 * inferred as {@code output_ragged_rank} - {@code rank(encoded_ragged)}. See
 * {@code RaggedTensorToVariant} for the corresponding encoding logic.
 *
 * @param <T> data type for {@code output_nested_splits} output
 *
 * @param <U> data type for {@code output_dense_values} output
 */
@OpMetadata(
    opType = RaggedTensorFromVariant.OP_NAME,
    inputsClass = RaggedTensorFromVariant.Inputs.class
)
public final class RaggedTensorFromVariant<T extends TNumber, U extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedTensorFromVariant";

  private List<Output<T>> outputNestedSplits;

  private Output<U> outputDenseValues;

  @SuppressWarnings("unchecked")
  public RaggedTensorFromVariant(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputNestedSplitsLength = operation.outputListLength("output_nested_splits");
    outputNestedSplits = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputNestedSplitsLength));
    outputIdx += outputNestedSplitsLength;
    outputDenseValues = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedTensorFromVariant operation.
   *
   * @param scope current scope
   * @param encodedRagged A {@code variant} Tensor containing encoded {@code RaggedTensor}s.
   * @param inputRaggedRank The ragged rank of each encoded {@code RaggedTensor} component in the input. If set to
   * -1, this is inferred as {@code output_ragged_rank} - {@code rank(encoded_ragged)}
   * @param outputRaggedRank The expected ragged rank of the output {@code RaggedTensor}. The following must hold:
   * {@code output_ragged_rank = rank(encoded_ragged) + input_ragged_rank}.
   * @param Tvalues The value of the Tvalues attribute
   * @param Tsplits The value of the Tsplits attribute
   * @param <T> data type for {@code RaggedTensorFromVariant} output and operands
   * @param <U> data type for {@code RaggedTensorFromVariant} output and operands
   * @return a new instance of RaggedTensorFromVariant
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TType> RaggedTensorFromVariant<T, U> create(
      Scope scope, Operand<? extends TType> encodedRagged, Long inputRaggedRank,
      Long outputRaggedRank, Class<U> Tvalues, Class<T> Tsplits) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedTensorFromVariant");
    opBuilder.addInput(encodedRagged.asOutput());
    opBuilder.setAttr("input_ragged_rank", inputRaggedRank);
    opBuilder.setAttr("output_ragged_rank", outputRaggedRank);
    opBuilder.setAttr("Tvalues", Operands.toDataType(Tvalues));
    opBuilder.setAttr("Tsplits", Operands.toDataType(Tsplits));
    return new RaggedTensorFromVariant<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new RaggedTensorFromVariant operation, with the default output types.
   *
   * @param scope current scope
   * @param encodedRagged A {@code variant} Tensor containing encoded {@code RaggedTensor}s.
   * @param inputRaggedRank The ragged rank of each encoded {@code RaggedTensor} component in the input. If set to
   * -1, this is inferred as {@code output_ragged_rank} - {@code rank(encoded_ragged)}
   * @param outputRaggedRank The expected ragged rank of the output {@code RaggedTensor}. The following must hold:
   * {@code output_ragged_rank = rank(encoded_ragged) + input_ragged_rank}.
   * @param Tvalues The value of the Tvalues attribute
   * @param <U> data type for {@code RaggedTensorFromVariant} output and operands
   * @return a new instance of RaggedTensorFromVariant, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> RaggedTensorFromVariant<TInt64, U> create(Scope scope,
      Operand<? extends TType> encodedRagged, Long inputRaggedRank, Long outputRaggedRank,
      Class<U> Tvalues) {
    return create(scope, encodedRagged, inputRaggedRank, outputRaggedRank, Tvalues, TInt64.class);
  }

  /**
   * Gets outputNestedSplits.
   * A list of one or more Tensors representing the splits of the output
   * {@code RaggedTensor}.
   * @return outputNestedSplits.
   */
  public List<Output<T>> outputNestedSplits() {
    return outputNestedSplits;
  }

  /**
   * Gets outputDenseValues.
   * A Tensor representing the values of the output {@code RaggedTensor}.
   * @return outputDenseValues.
   */
  public Output<U> outputDenseValues() {
    return outputDenseValues;
  }

  @OpInputsMetadata(
      outputsClass = RaggedTensorFromVariant.class
  )
  public static class Inputs extends RawOpInputs<RaggedTensorFromVariant<?, ?>> {
    /**
     * A {@code variant} Tensor containing encoded {@code RaggedTensor}s.
     */
    public final Operand<? extends TType> encodedRagged;

    /**
     * The ragged rank of each encoded `RaggedTensor` component in the input. If set to
     * -1, this is inferred as `output_ragged_rank` - `rank(encoded_ragged)`
     */
    public final long inputRaggedRank;

    /**
     * The Tvalues attribute
     */
    public final DataType Tvalues;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new RaggedTensorFromVariant<>(op), op, Arrays.asList("input_ragged_rank", "Tvalues", "Tsplits"));
      int inputIndex = 0;
      encodedRagged = (Operand<? extends TType>) op.input(inputIndex++);
      inputRaggedRank = op.attributes().getAttrInt("input_ragged_rank");
      Tvalues = op.attributes().getAttrType("Tvalues");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
