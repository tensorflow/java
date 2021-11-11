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

package org.tensorflow.op.io;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Transforms a vector of tf.Example protos (as strings) into typed tensors.
 */
@OpMetadata(
    opType = ParseExample.OP_NAME,
    inputsClass = ParseExample.Inputs.class
)
@Operator(
    group = "io"
)
public final class ParseExample extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParseExampleV2";

  private List<Output<TInt64>> sparseIndices;

  private List<Output<?>> sparseValues;

  private List<Output<TInt64>> sparseShapes;

  private List<Output<?>> denseValues;

  private List<Output<?>> raggedValues;

  private List<Output<?>> raggedRowSplits;

  @SuppressWarnings("unchecked")
  public ParseExample(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int sparseIndicesLength = operation.outputListLength("sparse_indices");
    sparseIndices = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, sparseIndicesLength));
    outputIdx += sparseIndicesLength;
    int sparseValuesLength = operation.outputListLength("sparse_values");
    sparseValues = Arrays.asList(operation.outputList(outputIdx, sparseValuesLength));
    outputIdx += sparseValuesLength;
    int sparseShapesLength = operation.outputListLength("sparse_shapes");
    sparseShapes = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, sparseShapesLength));
    outputIdx += sparseShapesLength;
    int denseValuesLength = operation.outputListLength("dense_values");
    denseValues = Arrays.asList(operation.outputList(outputIdx, denseValuesLength));
    outputIdx += denseValuesLength;
    int raggedValuesLength = operation.outputListLength("ragged_values");
    raggedValues = Arrays.asList(operation.outputList(outputIdx, raggedValuesLength));
    outputIdx += raggedValuesLength;
    int raggedRowSplitsLength = operation.outputListLength("ragged_row_splits");
    raggedRowSplits = Arrays.asList(operation.outputList(outputIdx, raggedRowSplitsLength));
    outputIdx += raggedRowSplitsLength;
  }

  /**
   * Factory method to create a class wrapping a new ParseExampleV2 operation.
   *
   * @param scope current scope
   * @param serialized A scalar or vector containing binary serialized Example protos.
   * @param names A tensor containing the names of the serialized protos.
   * Corresponds 1:1 with the {@code serialized} tensor.
   * May contain, for example, table key (descriptive) names for the
   * corresponding serialized protos.  These are purely useful for debugging
   * purposes, and the presence of values here has no effect on the output.
   * May also be an empty vector if no names are available.
   * If non-empty, this tensor must have the same shape as &quot;serialized&quot;.
   * @param sparseKeys Vector of strings.
   * The keys expected in the Examples' features associated with sparse values.
   * @param denseKeys Vector of strings.
   * The keys expected in the Examples' features associated with dense values.
   * @param raggedKeys Vector of strings.
   * The keys expected in the Examples' features associated with ragged values.
   * @param denseDefaults A list of Tensors (some may be empty).  Corresponds 1:1 with {@code dense_keys}.
   * dense_defaults[j] provides default values
   * when the example's feature_map lacks dense_key[j].  If an empty Tensor is
   * provided for dense_defaults[j], then the Feature dense_keys[j] is required.
   * The input type is inferred from dense_defaults[j], even when it's empty.
   * If dense_defaults[j] is not empty, and dense_shapes[j] is fully defined,
   * then the shape of dense_defaults[j] must match that of dense_shapes[j].
   * If dense_shapes[j] has an undefined major dimension (variable strides dense
   * feature), dense_defaults[j] must contain a single element:
   * the padding element.
   * @param numSparse The number of sparse keys.
   * @param sparseTypes A list of {@code num_sparse} types; the data types of data in each Feature
   * given in sparse_keys.
   * Currently the ParseExample supports DT_FLOAT (FloatList),
   * DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param raggedValueTypes A list of {@code num_ragged} types; the data types of data in each Feature
   * given in ragged_keys (where {@code num_ragged = sparse_keys.size()}).
   * Currently the ParseExample supports DT_FLOAT (FloatList),
   * DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param raggedSplitTypes A list of {@code num_ragged} types; the data types of row_splits in each Feature
   * given in ragged_keys (where {@code num_ragged = sparse_keys.size()}).
   * May be DT_INT32 or DT_INT64.
   * @param denseShapes A list of {@code num_dense} shapes; the shapes of data in each Feature
   * given in dense_keys (where {@code num_dense = dense_keys.size()}).
   * The number of elements in the Feature corresponding to dense_key[j]
   * must always equal dense_shapes[j].NumEntries().
   * If dense_shapes[j] == (D0, D1, ..., DN) then the shape of output
   * Tensor dense_values[j] will be (|serialized|, D0, D1, ..., DN):
   * The dense outputs are just the inputs row-stacked by batch.
   * This works for dense_shapes[j] = (-1, D1, ..., DN).  In this case
   * the shape of the output Tensor dense_values[j] will be
   * (|serialized|, M, D1, .., DN), where M is the maximum number of blocks
   * of elements of length D1 * .... * DN, across all minibatch entries
   * in the input.  Any minibatch entry with less than M blocks of elements of
   * length D1 * ... * DN will be padded with the corresponding default_value
   * scalar element along the second dimension.
   * @return a new instance of ParseExample
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParseExample create(Scope scope, Operand<TString> serialized,
      Operand<TString> names, Operand<TString> sparseKeys, Operand<TString> denseKeys,
      Operand<TString> raggedKeys, Iterable<Operand<?>> denseDefaults, Long numSparse,
      List<Class<? extends TType>> sparseTypes, List<Class<? extends TType>> raggedValueTypes,
      List<Class<? extends TNumber>> raggedSplitTypes, List<Shape> denseShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParseExample");
    opBuilder.addInput(serialized.asOutput());
    opBuilder.addInput(names.asOutput());
    opBuilder.addInput(sparseKeys.asOutput());
    opBuilder.addInput(denseKeys.asOutput());
    opBuilder.addInput(raggedKeys.asOutput());
    opBuilder.addInputList(Operands.asOutputs(denseDefaults));
    opBuilder.setAttr("num_sparse", numSparse);
    opBuilder.setAttr("sparse_types", Operands.toDataTypes(sparseTypes));
    opBuilder.setAttr("ragged_value_types", Operands.toDataTypes(raggedValueTypes));
    opBuilder.setAttr("ragged_split_types", Operands.toDataTypes(raggedSplitTypes));
    Shape[] denseShapesArray = new Shape[denseShapes.size()];
    for (int i = 0 ; i < denseShapesArray.length ; i++) {
      denseShapesArray[i] = denseShapes.get(i);
    }
    opBuilder.setAttr("dense_shapes", denseShapesArray);
    return new ParseExample(opBuilder.build());
  }

  /**
   * Gets sparseIndices.
   *
   * @return sparseIndices.
   */
  public List<Output<TInt64>> sparseIndices() {
    return sparseIndices;
  }

  /**
   * Gets sparseValues.
   *
   * @return sparseValues.
   */
  public List<Output<?>> sparseValues() {
    return sparseValues;
  }

  /**
   * Gets sparseShapes.
   *
   * @return sparseShapes.
   */
  public List<Output<TInt64>> sparseShapes() {
    return sparseShapes;
  }

  /**
   * Gets denseValues.
   *
   * @return denseValues.
   */
  public List<Output<?>> denseValues() {
    return denseValues;
  }

  /**
   * Gets raggedValues.
   *
   * @return raggedValues.
   */
  public List<Output<?>> raggedValues() {
    return raggedValues;
  }

  /**
   * Gets raggedRowSplits.
   *
   * @return raggedRowSplits.
   */
  public List<Output<?>> raggedRowSplits() {
    return raggedRowSplits;
  }

  @OpInputsMetadata(
      outputsClass = ParseExample.class
  )
  public static class Inputs extends RawOpInputs<ParseExample> {
    /**
     * A scalar or vector containing binary serialized Example protos.
     */
    public final Operand<TString> serialized;

    /**
     * A tensor containing the names of the serialized protos.
     * Corresponds 1:1 with the {@code serialized} tensor.
     * May contain, for example, table key (descriptive) names for the
     * corresponding serialized protos.  These are purely useful for debugging
     * purposes, and the presence of values here has no effect on the output.
     * May also be an empty vector if no names are available.
     * If non-empty, this tensor must have the same shape as &quot;serialized&quot;.
     */
    public final Operand<TString> names;

    /**
     * Vector of strings.
     * The keys expected in the Examples' features associated with sparse values.
     */
    public final Operand<TString> sparseKeys;

    /**
     * Vector of strings.
     * The keys expected in the Examples' features associated with dense values.
     */
    public final Operand<TString> denseKeys;

    /**
     * Vector of strings.
     * The keys expected in the Examples' features associated with ragged values.
     */
    public final Operand<TString> raggedKeys;

    /**
     * A list of Tensors (some may be empty).  Corresponds 1:1 with {@code dense_keys}.
     * dense_defaults[j] provides default values
     * when the example's feature_map lacks dense_key[j].  If an empty Tensor is
     * provided for dense_defaults[j], then the Feature dense_keys[j] is required.
     * The input type is inferred from dense_defaults[j], even when it's empty.
     * If dense_defaults[j] is not empty, and dense_shapes[j] is fully defined,
     * then the shape of dense_defaults[j] must match that of dense_shapes[j].
     * If dense_shapes[j] has an undefined major dimension (variable strides dense
     * feature), dense_defaults[j] must contain a single element:
     * the padding element.
     */
    public final Iterable<Operand<?>> denseDefaults;

    /**
     * The Tdense attribute
     */
    public final DataType[] Tdense;

    /**
     * A list of `num_sparse` types; the data types of data in each Feature
     * given in sparse_keys.
     * Currently the ParseExample supports DT_FLOAT (FloatList),
     * DT_INT64 (Int64List), and DT_STRING (BytesList).
     */
    public final DataType[] sparseTypes;

    /**
     * A list of `num_ragged` types; the data types of data in each Feature
     * given in ragged_keys (where `num_ragged = sparse_keys.size()`).
     * Currently the ParseExample supports DT_FLOAT (FloatList),
     * DT_INT64 (Int64List), and DT_STRING (BytesList).
     */
    public final DataType[] raggedValueTypes;

    /**
     * A list of `num_ragged` types; the data types of row_splits in each Feature
     * given in ragged_keys (where `num_ragged = sparse_keys.size()`).
     * May be DT_INT32 or DT_INT64.
     */
    public final DataType[] raggedSplitTypes;

    /**
     * A list of `num_dense` shapes; the shapes of data in each Feature
     * given in dense_keys (where `num_dense = dense_keys.size()`).
     * The number of elements in the Feature corresponding to dense_key[j]
     * must always equal dense_shapes[j].NumEntries().
     * If dense_shapes[j] == (D0, D1, ..., DN) then the shape of output
     * Tensor dense_values[j] will be (|serialized|, D0, D1, ..., DN):
     * The dense outputs are just the inputs row-stacked by batch.
     * This works for dense_shapes[j] = (-1, D1, ..., DN).  In this case
     * the shape of the output Tensor dense_values[j] will be
     * (|serialized|, M, D1, .., DN), where M is the maximum number of blocks
     * of elements of length D1 * .... * DN, across all minibatch entries
     * in the input.  Any minibatch entry with less than M blocks of elements of
     * length D1 * ... * DN will be padded with the corresponding default_value
     * scalar element along the second dimension.
     */
    public final Shape[] denseShapes;

    public Inputs(GraphOperation op) {
      super(new ParseExample(op), op, Arrays.asList("Tdense", "sparse_types", "ragged_value_types", "ragged_split_types", "dense_shapes"));
      int inputIndex = 0;
      serialized = (Operand<TString>) op.input(inputIndex++);
      names = (Operand<TString>) op.input(inputIndex++);
      sparseKeys = (Operand<TString>) op.input(inputIndex++);
      denseKeys = (Operand<TString>) op.input(inputIndex++);
      raggedKeys = (Operand<TString>) op.input(inputIndex++);
      int denseDefaultsLength = op.inputListLength("dense_defaults");
      denseDefaults = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, denseDefaultsLength));
      inputIndex += denseDefaultsLength;
      Tdense = op.attributes().getAttrTypeList("Tdense");
      sparseTypes = op.attributes().getAttrTypeList("sparse_types");
      raggedValueTypes = op.attributes().getAttrTypeList("ragged_value_types");
      raggedSplitTypes = op.attributes().getAttrTypeList("ragged_split_types");
      denseShapes = op.attributes().getAttrShapeList("dense_shapes");
    }
  }
}
