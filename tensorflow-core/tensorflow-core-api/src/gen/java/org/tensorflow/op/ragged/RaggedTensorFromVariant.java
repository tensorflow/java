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
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Decodes a `variant` Tensor into a `RaggedTensor`.
 * <p>
 * Decodes the given `variant` Tensor and returns a `RaggedTensor`. The input
 * could be a scalar, meaning it encodes a single `RaggedTensor` with ragged_rank
 * `output_ragged_rank`. It could also have an arbitrary rank, in which case each
 * element is decoded into a `RaggedTensor` with ragged_rank `input_ragged_rank`
 * and these are then stacked according to the input shape to output a single
 * `RaggedTensor` with ragged_rank `output_ragged_rank`. Each `variant` element in
 * the input Tensor is decoded by retrieving from the element a 1-D `variant`
 * Tensor with `input_ragged_rank + 1` Tensors, corresponding to the splits and
 * values of the decoded `RaggedTensor`. If `input_ragged_rank` is -1, then it is
 * inferred as `output_ragged_rank` - `rank(encoded_ragged)`. See
 * `RaggedTensorToVariant` for the corresponding encoding logic.
 * 
 * 
 * @param <U> data type for {@code outputNestedSplits()} output
 * @param <T> data type for {@code outputDenseValues()} output
 */
public final class RaggedTensorFromVariant<U extends TNumber, T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new RaggedTensorFromVariant operation.
   * 
   * @param scope current scope
   * @param encodedRagged A `variant` Tensor containing encoded `RaggedTensor`s.
   * @param inputRaggedRank The ragged rank of each encoded `RaggedTensor` component in the input. If set to
   * -1, this is inferred as `output_ragged_rank` - `rank(encoded_ragged)`
   * @param outputRaggedRank The expected ragged rank of the output `RaggedTensor`. The following must hold:
   * `output_ragged_rank = rank(encoded_ragged) + input_ragged_rank`.
   * @param Tvalues 
   * @param Tsplits 
   * @return a new instance of RaggedTensorFromVariant
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TType> RaggedTensorFromVariant<U, T> create(Scope scope, Operand<?> encodedRagged, Long inputRaggedRank, Long outputRaggedRank, DataType<T> Tvalues, DataType<U> Tsplits) {
    OperationBuilder opBuilder = scope.env().opBuilder("RaggedTensorFromVariant", scope.makeOpName("RaggedTensorFromVariant"));
    opBuilder.addInput(encodedRagged.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("input_ragged_rank", inputRaggedRank);
    opBuilder.setAttr("output_ragged_rank", outputRaggedRank);
    opBuilder.setAttr("Tvalues", Tvalues);
    opBuilder.setAttr("Tsplits", Tsplits);
    return new RaggedTensorFromVariant<U, T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new RaggedTensorFromVariant operation using default output types.
   * 
   * @param scope current scope
   * @param encodedRagged A `variant` Tensor containing encoded `RaggedTensor`s.
   * @param inputRaggedRank The ragged rank of each encoded `RaggedTensor` component in the input. If set to
   * -1, this is inferred as `output_ragged_rank` - `rank(encoded_ragged)`
   * @param outputRaggedRank The expected ragged rank of the output `RaggedTensor`. The following must hold:
   * `output_ragged_rank = rank(encoded_ragged) + input_ragged_rank`.
   * @param Tvalues 
   * @return a new instance of RaggedTensorFromVariant
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> RaggedTensorFromVariant<TInt64, T> create(Scope scope, Operand<?> encodedRagged, Long inputRaggedRank, Long outputRaggedRank, DataType<T> Tvalues) {
    return create(scope, encodedRagged, inputRaggedRank, outputRaggedRank, Tvalues, TInt64.DTYPE);
  }
  
  /**
   * A list of one or more Tensors representing the splits of the output
   * `RaggedTensor`.
   */
  public List<Output<U>> outputNestedSplits() {
    return outputNestedSplits;
  }
  
  /**
   * A Tensor representing the values of the output `RaggedTensor`.
   */
  public Output<T> outputDenseValues() {
    return outputDenseValues;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RaggedTensorFromVariant";
  
  private List<Output<U>> outputNestedSplits;
  private Output<T> outputDenseValues;
  
  @SuppressWarnings("unchecked")
  private RaggedTensorFromVariant(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputNestedSplitsLength = operation.outputListLength("output_nested_splits");
    outputNestedSplits = Arrays.asList((Output<U>[])operation.outputList(outputIdx, outputNestedSplitsLength));
    outputIdx += outputNestedSplitsLength;
    outputDenseValues = operation.output(outputIdx++);
  }
}
