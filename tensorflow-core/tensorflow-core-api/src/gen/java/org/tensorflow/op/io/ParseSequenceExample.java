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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Transforms a vector of tf.io.SequenceExample protos (as strings) into
 * typed tensors.
 */
@OpMetadata(
    opType = ParseSequenceExample.OP_NAME,
    inputsClass = ParseSequenceExample.Inputs.class
)
@Operator(
    group = "io"
)
public final class ParseSequenceExample extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParseSequenceExampleV2";

  private List<Output<TInt64>> contextSparseIndices;

  private List<Output<?>> contextSparseValues;

  private List<Output<TInt64>> contextSparseShapes;

  private List<Output<?>> contextDenseValues;

  private List<Output<?>> contextRaggedValues;

  private List<Output<?>> contextRaggedRowSplits;

  private List<Output<TInt64>> featureListSparseIndices;

  private List<Output<?>> featureListSparseValues;

  private List<Output<TInt64>> featureListSparseShapes;

  private List<Output<?>> featureListDenseValues;

  private List<Output<TInt64>> featureListDenseLengths;

  private List<Output<?>> featureListRaggedValues;

  private List<Output<?>> featureListRaggedOuterSplits;

  private List<Output<?>> featureListRaggedInnerSplits;

  @SuppressWarnings("unchecked")
  public ParseSequenceExample(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int contextSparseIndicesLength = operation.outputListLength("context_sparse_indices");
    contextSparseIndices = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, contextSparseIndicesLength));
    outputIdx += contextSparseIndicesLength;
    int contextSparseValuesLength = operation.outputListLength("context_sparse_values");
    contextSparseValues = Arrays.asList(operation.outputList(outputIdx, contextSparseValuesLength));
    outputIdx += contextSparseValuesLength;
    int contextSparseShapesLength = operation.outputListLength("context_sparse_shapes");
    contextSparseShapes = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, contextSparseShapesLength));
    outputIdx += contextSparseShapesLength;
    int contextDenseValuesLength = operation.outputListLength("context_dense_values");
    contextDenseValues = Arrays.asList(operation.outputList(outputIdx, contextDenseValuesLength));
    outputIdx += contextDenseValuesLength;
    int contextRaggedValuesLength = operation.outputListLength("context_ragged_values");
    contextRaggedValues = Arrays.asList(operation.outputList(outputIdx, contextRaggedValuesLength));
    outputIdx += contextRaggedValuesLength;
    int contextRaggedRowSplitsLength = operation.outputListLength("context_ragged_row_splits");
    contextRaggedRowSplits = Arrays.asList(operation.outputList(outputIdx, contextRaggedRowSplitsLength));
    outputIdx += contextRaggedRowSplitsLength;
    int featureListSparseIndicesLength = operation.outputListLength("feature_list_sparse_indices");
    featureListSparseIndices = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, featureListSparseIndicesLength));
    outputIdx += featureListSparseIndicesLength;
    int featureListSparseValuesLength = operation.outputListLength("feature_list_sparse_values");
    featureListSparseValues = Arrays.asList(operation.outputList(outputIdx, featureListSparseValuesLength));
    outputIdx += featureListSparseValuesLength;
    int featureListSparseShapesLength = operation.outputListLength("feature_list_sparse_shapes");
    featureListSparseShapes = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, featureListSparseShapesLength));
    outputIdx += featureListSparseShapesLength;
    int featureListDenseValuesLength = operation.outputListLength("feature_list_dense_values");
    featureListDenseValues = Arrays.asList(operation.outputList(outputIdx, featureListDenseValuesLength));
    outputIdx += featureListDenseValuesLength;
    int featureListDenseLengthsLength = operation.outputListLength("feature_list_dense_lengths");
    featureListDenseLengths = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, featureListDenseLengthsLength));
    outputIdx += featureListDenseLengthsLength;
    int featureListRaggedValuesLength = operation.outputListLength("feature_list_ragged_values");
    featureListRaggedValues = Arrays.asList(operation.outputList(outputIdx, featureListRaggedValuesLength));
    outputIdx += featureListRaggedValuesLength;
    int featureListRaggedOuterSplitsLength = operation.outputListLength("feature_list_ragged_outer_splits");
    featureListRaggedOuterSplits = Arrays.asList(operation.outputList(outputIdx, featureListRaggedOuterSplitsLength));
    outputIdx += featureListRaggedOuterSplitsLength;
    int featureListRaggedInnerSplitsLength = operation.outputListLength("feature_list_ragged_inner_splits");
    featureListRaggedInnerSplits = Arrays.asList(operation.outputList(outputIdx, featureListRaggedInnerSplitsLength));
    outputIdx += featureListRaggedInnerSplitsLength;
  }

  /**
   * Factory method to create a class wrapping a new ParseSequenceExampleV2 operation.
   *
   * @param scope current scope
   * @param serialized A scalar or vector containing binary serialized SequenceExample protos.
   * @param debugName A scalar or vector containing the names of the serialized protos.
   * May contain, for example, table key (descriptive) name for the
   * corresponding serialized proto.  This is purely useful for debugging
   * purposes, and the presence of values here has no effect on the output.
   * May also be an empty vector if no name is available.
   * @param contextSparseKeys The keys expected in the Examples' features associated with context_sparse
   * values.
   * @param contextDenseKeys The keys expected in the SequenceExamples' context features associated with
   * dense values.
   * @param contextRaggedKeys The keys expected in the Examples' features associated with context_ragged
   * values.
   * @param featureListSparseKeys The keys expected in the FeatureLists associated with sparse values.
   * @param featureListDenseKeys The keys expected in the SequenceExamples' feature_lists associated
   * with lists of dense values.
   * @param featureListRaggedKeys The keys expected in the FeatureLists associated with ragged values.
   * @param featureListDenseMissingAssumedEmpty A vector corresponding 1:1 with feature_list_dense_keys, indicating which
   * features may be missing from the SequenceExamples.  If the associated
   * FeatureList is missing, it is treated as empty.
   * @param contextDenseDefaults A list of Ncontext_dense Tensors (some may be empty).
   * context_dense_defaults[j] provides default values
   * when the SequenceExample's context map lacks context_dense_key[j].
   * If an empty Tensor is provided for context_dense_defaults[j],
   * then the Feature context_dense_keys[j] is required.
   * The input type is inferred from context_dense_defaults[j], even when it's
   * empty.  If context_dense_defaults[j] is not empty, its shape must match
   * context_dense_shapes[j].
   * @param contextSparseTypes A list of Ncontext_sparse types; the data types of data in
   * each context Feature given in context_sparse_keys.
   * Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
   * DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param contextRaggedValueTypes RaggedTensor.value dtypes for the ragged context features.
   * @param contextRaggedSplitTypes RaggedTensor.row_split dtypes for the ragged context features.
   * @param featureListDenseTypes The value of the featureListDenseTypes attribute
   * @param featureListSparseTypes A list of Nfeature_list_sparse types; the data types
   * of data in each FeatureList given in feature_list_sparse_keys.
   * Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
   * DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param featureListRaggedValueTypes RaggedTensor.value dtypes for the ragged FeatureList features.
   * @param featureListRaggedSplitTypes RaggedTensor.row_split dtypes for the ragged FeatureList features.
   * @param options carries optional attribute values
   * @return a new instance of ParseSequenceExample
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParseSequenceExample create(Scope scope, Operand<TString> serialized,
      Operand<TString> debugName, Operand<TString> contextSparseKeys,
      Operand<TString> contextDenseKeys, Operand<TString> contextRaggedKeys,
      Operand<TString> featureListSparseKeys, Operand<TString> featureListDenseKeys,
      Operand<TString> featureListRaggedKeys, Operand<TBool> featureListDenseMissingAssumedEmpty,
      Iterable<Operand<?>> contextDenseDefaults, List<Class<? extends TType>> contextSparseTypes,
      List<Class<? extends TType>> contextRaggedValueTypes,
      List<Class<? extends TNumber>> contextRaggedSplitTypes,
      List<Class<? extends TType>> featureListDenseTypes,
      List<Class<? extends TType>> featureListSparseTypes,
      List<Class<? extends TType>> featureListRaggedValueTypes,
      List<Class<? extends TNumber>> featureListRaggedSplitTypes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParseSequenceExample");
    opBuilder.addInput(serialized.asOutput());
    opBuilder.addInput(debugName.asOutput());
    opBuilder.addInput(contextSparseKeys.asOutput());
    opBuilder.addInput(contextDenseKeys.asOutput());
    opBuilder.addInput(contextRaggedKeys.asOutput());
    opBuilder.addInput(featureListSparseKeys.asOutput());
    opBuilder.addInput(featureListDenseKeys.asOutput());
    opBuilder.addInput(featureListRaggedKeys.asOutput());
    opBuilder.addInput(featureListDenseMissingAssumedEmpty.asOutput());
    opBuilder.addInputList(Operands.asOutputs(contextDenseDefaults));
    opBuilder.setAttr("context_sparse_types", Operands.toDataTypes(contextSparseTypes));
    opBuilder.setAttr("context_ragged_value_types", Operands.toDataTypes(contextRaggedValueTypes));
    opBuilder.setAttr("context_ragged_split_types", Operands.toDataTypes(contextRaggedSplitTypes));
    opBuilder.setAttr("feature_list_dense_types", Operands.toDataTypes(featureListDenseTypes));
    opBuilder.setAttr("feature_list_sparse_types", Operands.toDataTypes(featureListSparseTypes));
    opBuilder.setAttr("feature_list_ragged_value_types", Operands.toDataTypes(featureListRaggedValueTypes));
    opBuilder.setAttr("feature_list_ragged_split_types", Operands.toDataTypes(featureListRaggedSplitTypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.NcontextSparse != null) {
          opBuilder.setAttr("Ncontext_sparse", opts.NcontextSparse);
        }
        if (opts.contextDenseShapes != null) {
          Shape[] contextDenseShapesArray = new Shape[opts.contextDenseShapes.size()];
          for (int i = 0 ; i < contextDenseShapesArray.length ; i++) {
            contextDenseShapesArray[i] = opts.contextDenseShapes.get(i);
          }
          opBuilder.setAttr("context_dense_shapes", contextDenseShapesArray);
        }
        if (opts.NfeatureListSparse != null) {
          opBuilder.setAttr("Nfeature_list_sparse", opts.NfeatureListSparse);
        }
        if (opts.NfeatureListDense != null) {
          opBuilder.setAttr("Nfeature_list_dense", opts.NfeatureListDense);
        }
        if (opts.featureListDenseShapes != null) {
          Shape[] featureListDenseShapesArray = new Shape[opts.featureListDenseShapes.size()];
          for (int i = 0 ; i < featureListDenseShapesArray.length ; i++) {
            featureListDenseShapesArray[i] = opts.featureListDenseShapes.get(i);
          }
          opBuilder.setAttr("feature_list_dense_shapes", featureListDenseShapesArray);
        }
      }
    }
    return new ParseSequenceExample(opBuilder.build());
  }

  /**
   * Sets the NcontextSparse option.
   *
   * @param NcontextSparse the NcontextSparse option
   * @return this Options instance.
   */
  public static Options NcontextSparse(Long NcontextSparse) {
    return new Options().NcontextSparse(NcontextSparse);
  }

  /**
   * Sets the contextDenseShapes option.
   *
   * @param contextDenseShapes A list of Ncontext_dense shapes; the shapes of data in
   * each context Feature given in context_dense_keys.
   * The number of elements in the Feature corresponding to context_dense_key[j]
   * must always equal context_dense_shapes[j].NumEntries().
   * The shape of context_dense_values[j] will match context_dense_shapes[j].
   * @return this Options instance.
   */
  public static Options contextDenseShapes(List<Shape> contextDenseShapes) {
    return new Options().contextDenseShapes(contextDenseShapes);
  }

  /**
   * Sets the contextDenseShapes option.
   *
   * @param contextDenseShapes A list of Ncontext_dense shapes; the shapes of data in
   * each context Feature given in context_dense_keys.
   * The number of elements in the Feature corresponding to context_dense_key[j]
   * must always equal context_dense_shapes[j].NumEntries().
   * The shape of context_dense_values[j] will match context_dense_shapes[j].
   * @return this Options instance.
   */
  public static Options contextDenseShapes(Shape... contextDenseShapes) {
    return new Options().contextDenseShapes(contextDenseShapes);
  }

  /**
   * Sets the NfeatureListSparse option.
   *
   * @param NfeatureListSparse the NfeatureListSparse option
   * @return this Options instance.
   */
  public static Options NfeatureListSparse(Long NfeatureListSparse) {
    return new Options().NfeatureListSparse(NfeatureListSparse);
  }

  /**
   * Sets the NfeatureListDense option.
   *
   * @param NfeatureListDense the NfeatureListDense option
   * @return this Options instance.
   */
  public static Options NfeatureListDense(Long NfeatureListDense) {
    return new Options().NfeatureListDense(NfeatureListDense);
  }

  /**
   * Sets the featureListDenseShapes option.
   *
   * @param featureListDenseShapes A list of Nfeature_list_dense shapes; the shapes of
   * data in each FeatureList given in feature_list_dense_keys.
   * The shape of each Feature in the FeatureList corresponding to
   * feature_list_dense_key[j] must always equal
   * feature_list_dense_shapes[j].NumEntries().
   * @return this Options instance.
   */
  public static Options featureListDenseShapes(List<Shape> featureListDenseShapes) {
    return new Options().featureListDenseShapes(featureListDenseShapes);
  }

  /**
   * Sets the featureListDenseShapes option.
   *
   * @param featureListDenseShapes A list of Nfeature_list_dense shapes; the shapes of
   * data in each FeatureList given in feature_list_dense_keys.
   * The shape of each Feature in the FeatureList corresponding to
   * feature_list_dense_key[j] must always equal
   * feature_list_dense_shapes[j].NumEntries().
   * @return this Options instance.
   */
  public static Options featureListDenseShapes(Shape... featureListDenseShapes) {
    return new Options().featureListDenseShapes(featureListDenseShapes);
  }

  /**
   * Gets contextSparseIndices.
   *
   * @return contextSparseIndices.
   */
  public List<Output<TInt64>> contextSparseIndices() {
    return contextSparseIndices;
  }

  /**
   * Gets contextSparseValues.
   *
   * @return contextSparseValues.
   */
  public List<Output<?>> contextSparseValues() {
    return contextSparseValues;
  }

  /**
   * Gets contextSparseShapes.
   *
   * @return contextSparseShapes.
   */
  public List<Output<TInt64>> contextSparseShapes() {
    return contextSparseShapes;
  }

  /**
   * Gets contextDenseValues.
   *
   * @return contextDenseValues.
   */
  public List<Output<?>> contextDenseValues() {
    return contextDenseValues;
  }

  /**
   * Gets contextRaggedValues.
   *
   * @return contextRaggedValues.
   */
  public List<Output<?>> contextRaggedValues() {
    return contextRaggedValues;
  }

  /**
   * Gets contextRaggedRowSplits.
   *
   * @return contextRaggedRowSplits.
   */
  public List<Output<?>> contextRaggedRowSplits() {
    return contextRaggedRowSplits;
  }

  /**
   * Gets featureListSparseIndices.
   *
   * @return featureListSparseIndices.
   */
  public List<Output<TInt64>> featureListSparseIndices() {
    return featureListSparseIndices;
  }

  /**
   * Gets featureListSparseValues.
   *
   * @return featureListSparseValues.
   */
  public List<Output<?>> featureListSparseValues() {
    return featureListSparseValues;
  }

  /**
   * Gets featureListSparseShapes.
   *
   * @return featureListSparseShapes.
   */
  public List<Output<TInt64>> featureListSparseShapes() {
    return featureListSparseShapes;
  }

  /**
   * Gets featureListDenseValues.
   *
   * @return featureListDenseValues.
   */
  public List<Output<?>> featureListDenseValues() {
    return featureListDenseValues;
  }

  /**
   * Gets featureListDenseLengths.
   *
   * @return featureListDenseLengths.
   */
  public List<Output<TInt64>> featureListDenseLengths() {
    return featureListDenseLengths;
  }

  /**
   * Gets featureListRaggedValues.
   *
   * @return featureListRaggedValues.
   */
  public List<Output<?>> featureListRaggedValues() {
    return featureListRaggedValues;
  }

  /**
   * Gets featureListRaggedOuterSplits.
   *
   * @return featureListRaggedOuterSplits.
   */
  public List<Output<?>> featureListRaggedOuterSplits() {
    return featureListRaggedOuterSplits;
  }

  /**
   * Gets featureListRaggedInnerSplits.
   *
   * @return featureListRaggedInnerSplits.
   */
  public List<Output<?>> featureListRaggedInnerSplits() {
    return featureListRaggedInnerSplits;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.ParseSequenceExample}
   */
  public static class Options {
    private Long NcontextSparse;

    private List<Shape> contextDenseShapes;

    private Long NfeatureListSparse;

    private Long NfeatureListDense;

    private List<Shape> featureListDenseShapes;

    private Options() {
    }

    /**
     * Sets the NcontextSparse option.
     *
     * @param NcontextSparse the NcontextSparse option
     * @return this Options instance.
     */
    public Options NcontextSparse(Long NcontextSparse) {
      this.NcontextSparse = NcontextSparse;
      return this;
    }

    /**
     * Sets the contextDenseShapes option.
     *
     * @param contextDenseShapes A list of Ncontext_dense shapes; the shapes of data in
     * each context Feature given in context_dense_keys.
     * The number of elements in the Feature corresponding to context_dense_key[j]
     * must always equal context_dense_shapes[j].NumEntries().
     * The shape of context_dense_values[j] will match context_dense_shapes[j].
     * @return this Options instance.
     */
    public Options contextDenseShapes(List<Shape> contextDenseShapes) {
      this.contextDenseShapes = contextDenseShapes;
      return this;
    }

    /**
     * Sets the contextDenseShapes option.
     *
     * @param contextDenseShapes A list of Ncontext_dense shapes; the shapes of data in
     * each context Feature given in context_dense_keys.
     * The number of elements in the Feature corresponding to context_dense_key[j]
     * must always equal context_dense_shapes[j].NumEntries().
     * The shape of context_dense_values[j] will match context_dense_shapes[j].
     * @return this Options instance.
     */
    public Options contextDenseShapes(Shape... contextDenseShapes) {
      this.contextDenseShapes = Arrays.asList(contextDenseShapes);
      return this;
    }

    /**
     * Sets the NfeatureListSparse option.
     *
     * @param NfeatureListSparse the NfeatureListSparse option
     * @return this Options instance.
     */
    public Options NfeatureListSparse(Long NfeatureListSparse) {
      this.NfeatureListSparse = NfeatureListSparse;
      return this;
    }

    /**
     * Sets the NfeatureListDense option.
     *
     * @param NfeatureListDense the NfeatureListDense option
     * @return this Options instance.
     */
    public Options NfeatureListDense(Long NfeatureListDense) {
      this.NfeatureListDense = NfeatureListDense;
      return this;
    }

    /**
     * Sets the featureListDenseShapes option.
     *
     * @param featureListDenseShapes A list of Nfeature_list_dense shapes; the shapes of
     * data in each FeatureList given in feature_list_dense_keys.
     * The shape of each Feature in the FeatureList corresponding to
     * feature_list_dense_key[j] must always equal
     * feature_list_dense_shapes[j].NumEntries().
     * @return this Options instance.
     */
    public Options featureListDenseShapes(List<Shape> featureListDenseShapes) {
      this.featureListDenseShapes = featureListDenseShapes;
      return this;
    }

    /**
     * Sets the featureListDenseShapes option.
     *
     * @param featureListDenseShapes A list of Nfeature_list_dense shapes; the shapes of
     * data in each FeatureList given in feature_list_dense_keys.
     * The shape of each Feature in the FeatureList corresponding to
     * feature_list_dense_key[j] must always equal
     * feature_list_dense_shapes[j].NumEntries().
     * @return this Options instance.
     */
    public Options featureListDenseShapes(Shape... featureListDenseShapes) {
      this.featureListDenseShapes = Arrays.asList(featureListDenseShapes);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ParseSequenceExample.class
  )
  public static class Inputs extends RawOpInputs<ParseSequenceExample> {
    /**
     * A scalar or vector containing binary serialized SequenceExample protos.
     */
    public final Operand<TString> serialized;

    /**
     * A scalar or vector containing the names of the serialized protos.
     * May contain, for example, table key (descriptive) name for the
     * corresponding serialized proto.  This is purely useful for debugging
     * purposes, and the presence of values here has no effect on the output.
     * May also be an empty vector if no name is available.
     */
    public final Operand<TString> debugName;

    /**
     * The keys expected in the Examples' features associated with context_sparse
     * values.
     */
    public final Operand<TString> contextSparseKeys;

    /**
     * The keys expected in the SequenceExamples' context features associated with
     * dense values.
     */
    public final Operand<TString> contextDenseKeys;

    /**
     * The keys expected in the Examples' features associated with context_ragged
     * values.
     */
    public final Operand<TString> contextRaggedKeys;

    /**
     * The keys expected in the FeatureLists associated with sparse values.
     */
    public final Operand<TString> featureListSparseKeys;

    /**
     * The keys expected in the SequenceExamples' feature_lists associated
     * with lists of dense values.
     */
    public final Operand<TString> featureListDenseKeys;

    /**
     * The keys expected in the FeatureLists associated with ragged values.
     */
    public final Operand<TString> featureListRaggedKeys;

    /**
     * A vector corresponding 1:1 with feature_list_dense_keys, indicating which
     * features may be missing from the SequenceExamples.  If the associated
     * FeatureList is missing, it is treated as empty.
     */
    public final Operand<TBool> featureListDenseMissingAssumedEmpty;

    /**
     * A list of Ncontext_dense Tensors (some may be empty).
     * context_dense_defaults[j] provides default values
     * when the SequenceExample's context map lacks context_dense_key[j].
     * If an empty Tensor is provided for context_dense_defaults[j],
     * then the Feature context_dense_keys[j] is required.
     * The input type is inferred from context_dense_defaults[j], even when it's
     * empty.  If context_dense_defaults[j] is not empty, its shape must match
     * context_dense_shapes[j].
     */
    public final Iterable<Operand<?>> contextDenseDefaults;

    /**
     * The TcontextDense attribute
     */
    public final DataType[] TcontextDense;

    /**
     * A list of Ncontext_sparse types; the data types of data in
     * each context Feature given in context_sparse_keys.
     * Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
     * DT_INT64 (Int64List), and DT_STRING (BytesList).
     */
    public final DataType[] contextSparseTypes;

    /**
     * RaggedTensor.value dtypes for the ragged context features.
     */
    public final DataType[] contextRaggedValueTypes;

    /**
     * RaggedTensor.row_split dtypes for the ragged context features.
     */
    public final DataType[] contextRaggedSplitTypes;

    /**
     * A list of Ncontext_dense shapes; the shapes of data in
     * each context Feature given in context_dense_keys.
     * The number of elements in the Feature corresponding to context_dense_key[j]
     * must always equal context_dense_shapes[j].NumEntries().
     * The shape of context_dense_values[j] will match context_dense_shapes[j].
     */
    public final Shape[] contextDenseShapes;

    /**
     * The featureListDenseTypes attribute
     */
    public final DataType[] featureListDenseTypes;

    /**
     * A list of Nfeature_list_sparse types; the data types
     * of data in each FeatureList given in feature_list_sparse_keys.
     * Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
     * DT_INT64 (Int64List), and DT_STRING (BytesList).
     */
    public final DataType[] featureListSparseTypes;

    /**
     * RaggedTensor.value dtypes for the ragged FeatureList features.
     */
    public final DataType[] featureListRaggedValueTypes;

    /**
     * RaggedTensor.row_split dtypes for the ragged FeatureList features.
     */
    public final DataType[] featureListRaggedSplitTypes;

    /**
     * A list of Nfeature_list_dense shapes; the shapes of
     * data in each FeatureList given in feature_list_dense_keys.
     * The shape of each Feature in the FeatureList corresponding to
     * feature_list_dense_key[j] must always equal
     * feature_list_dense_shapes[j].NumEntries().
     */
    public final Shape[] featureListDenseShapes;

    public Inputs(GraphOperation op) {
      super(new ParseSequenceExample(op), op, Arrays.asList("Tcontext_dense", "context_sparse_types", "context_ragged_value_types", "context_ragged_split_types", "context_dense_shapes", "feature_list_dense_types", "feature_list_sparse_types", "feature_list_ragged_value_types", "feature_list_ragged_split_types", "feature_list_dense_shapes"));
      int inputIndex = 0;
      serialized = (Operand<TString>) op.input(inputIndex++);
      debugName = (Operand<TString>) op.input(inputIndex++);
      contextSparseKeys = (Operand<TString>) op.input(inputIndex++);
      contextDenseKeys = (Operand<TString>) op.input(inputIndex++);
      contextRaggedKeys = (Operand<TString>) op.input(inputIndex++);
      featureListSparseKeys = (Operand<TString>) op.input(inputIndex++);
      featureListDenseKeys = (Operand<TString>) op.input(inputIndex++);
      featureListRaggedKeys = (Operand<TString>) op.input(inputIndex++);
      featureListDenseMissingAssumedEmpty = (Operand<TBool>) op.input(inputIndex++);
      int contextDenseDefaultsLength = op.inputListLength("context_dense_defaults");
      contextDenseDefaults = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, contextDenseDefaultsLength));
      inputIndex += contextDenseDefaultsLength;
      TcontextDense = op.attributes().getAttrTypeList("Tcontext_dense");
      contextSparseTypes = op.attributes().getAttrTypeList("context_sparse_types");
      contextRaggedValueTypes = op.attributes().getAttrTypeList("context_ragged_value_types");
      contextRaggedSplitTypes = op.attributes().getAttrTypeList("context_ragged_split_types");
      contextDenseShapes = op.attributes().getAttrShapeList("context_dense_shapes");
      featureListDenseTypes = op.attributes().getAttrTypeList("feature_list_dense_types");
      featureListSparseTypes = op.attributes().getAttrTypeList("feature_list_sparse_types");
      featureListRaggedValueTypes = op.attributes().getAttrTypeList("feature_list_ragged_value_types");
      featureListRaggedSplitTypes = op.attributes().getAttrTypeList("feature_list_ragged_split_types");
      featureListDenseShapes = op.attributes().getAttrShapeList("feature_list_dense_shapes");
    }
  }
}
