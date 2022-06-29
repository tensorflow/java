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

package org.tensorflow.op.sparse;

import java.util.Arrays;
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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Generates sparse cross from a list of sparse and dense tensors.
 * The op takes two lists, one of 2D {@code SparseTensor} and one of 2D {@code Tensor}, each
 * representing features of one feature column. It outputs a 2D {@code SparseTensor} with
 * the batchwise crosses of these features.
 * <p>For example, if the inputs are
 * <pre>
 * inputs[0]: SparseTensor with shape = [2, 2]
 * [0, 0]: &quot;a&quot;
 * [1, 0]: &quot;b&quot;
 * [1, 1]: &quot;c&quot;
 *
 * inputs[1]: SparseTensor with shape = [2, 1]
 * [0, 0]: &quot;d&quot;
 * [1, 0]: &quot;e&quot;
 *
 * inputs[2]: Tensor [[&quot;f&quot;], [&quot;g&quot;]]
 * </pre>
 * <p>then the output will be
 * <pre>
 * shape = [2, 2]
 * [0, 0]: &quot;a_X_d_X_f&quot;
 * [1, 0]: &quot;b_X_e_X_g&quot;
 * [1, 1]: &quot;c_X_e_X_g&quot;
 * </pre>
 * <p>if hashed_output=true then the output will be
 * <pre>
 * shape = [2, 2]
 * [0, 0]: FingerprintCat64(
 *             Fingerprint64(&quot;f&quot;), FingerprintCat64(
 *                 Fingerprint64(&quot;d&quot;), Fingerprint64(&quot;a&quot;)))
 * [1, 0]: FingerprintCat64(
 *             Fingerprint64(&quot;g&quot;), FingerprintCat64(
 *                 Fingerprint64(&quot;e&quot;), Fingerprint64(&quot;b&quot;)))
 * [1, 1]: FingerprintCat64(
 *             Fingerprint64(&quot;g&quot;), FingerprintCat64(
 *                 Fingerprint64(&quot;e&quot;), Fingerprint64(&quot;c&quot;)))
 * </pre>
 */
@OpMetadata(
    opType = SparseCross.OP_NAME,
    inputsClass = SparseCross.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseCross extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseCrossV2";

  private Output<TInt64> outputIndices;

  private Output<TString> outputValues;

  private Output<TInt64> outputShape;

  public SparseCross(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseCrossV2 operation.
   *
   * @param scope current scope
   * @param indices 2-D.  Indices of each input {@code SparseTensor}.
   * @param values 1-D.   values of each {@code SparseTensor}.
   * @param shapes 1-D.   Shapes of each {@code SparseTensor}.
   * @param denseInputs 2-D.    Columns represented by dense {@code Tensor}.
   * @param sep string used when joining a list of string inputs, can be used as separator later.
   * @return a new instance of SparseCross
   */
  @Endpoint(
      describeByClass = true
  )
  public static SparseCross create(Scope scope, Iterable<Operand<TInt64>> indices,
      Iterable<Operand<?>> values, Iterable<Operand<TInt64>> shapes,
      Iterable<Operand<?>> denseInputs, Operand<TString> sep) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseCross");
    opBuilder.addInputList(Operands.asOutputs(indices));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInputList(Operands.asOutputs(shapes));
    opBuilder.addInputList(Operands.asOutputs(denseInputs));
    opBuilder.addInput(sep.asOutput());
    return new SparseCross(opBuilder.build());
  }

  /**
   * Gets outputIndices.
   * 2-D.  Indices of the concatenated {@code SparseTensor}.
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * 1-D.  Non-empty values of the concatenated or hashed
   * {@code SparseTensor}.
   * @return outputValues.
   */
  public Output<TString> outputValues() {
    return outputValues;
  }

  /**
   * Gets outputShape.
   * 1-D.  Shape of the concatenated {@code SparseTensor}.
   * @return outputShape.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }

  @OpInputsMetadata(
      outputsClass = SparseCross.class
  )
  public static class Inputs extends RawOpInputs<SparseCross> {
    /**
     * 2-D.  Indices of each input {@code SparseTensor}.
     */
    public final Iterable<Operand<TInt64>> indices;

    /**
     * 1-D.   values of each {@code SparseTensor}.
     */
    public final Iterable<Operand<?>> values;

    /**
     * 1-D.   Shapes of each {@code SparseTensor}.
     */
    public final Iterable<Operand<TInt64>> shapes;

    /**
     * 2-D.    Columns represented by dense {@code Tensor}.
     */
    public final Iterable<Operand<?>> denseInputs;

    /**
     * string used when joining a list of string inputs, can be used as separator later.
     */
    public final Operand<TString> sep;

    /**
     * The sparseTypes attribute
     */
    public final DataType[] sparseTypes;

    /**
     * The denseTypes attribute
     */
    public final DataType[] denseTypes;

    public Inputs(GraphOperation op) {
      super(new SparseCross(op), op, Arrays.asList("sparse_types", "dense_types"));
      int inputIndex = 0;
      int indicesLength = op.inputListLength("indices");
      indices = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, indicesLength));
      inputIndex += indicesLength;
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      int shapesLength = op.inputListLength("shapes");
      shapes = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, shapesLength));
      inputIndex += shapesLength;
      int denseInputsLength = op.inputListLength("dense_inputs");
      denseInputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, denseInputsLength));
      inputIndex += denseInputsLength;
      sep = (Operand<TString>) op.input(inputIndex++);
      sparseTypes = op.attributes().getAttrTypeList("sparse_types");
      denseTypes = op.attributes().getAttrTypeList("dense_types");
    }
  }
}
