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

package org.tensorflow.op.data.experimental;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Transforms {@code input_dataset} containing {@code Example} protos as vectors of DT_STRING into a dataset of {@code Tensor} or {@code SparseTensor} objects representing the parsed features.
 */
public final class ParseExampleDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParseExampleDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private ParseExampleDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParseExampleDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param numParallelCalls the numParallelCalls value
   * @param denseDefaults A dict mapping string keys to {@code Tensor}s.
   * The keys of the dict must match the dense_keys of the feature.
   * @param sparseKeys A list of string keys in the examples features.
   * The results for these keys will be returned as {@code SparseTensor} objects.
   * @param denseKeys A list of Ndense string Tensors (scalars).
   * The keys expected in the Examples features associated with dense values.
   * @param sparseTypes A list of {@code DTypes} of the same length as {@code sparse_keys}.
   * Only {@code tf.float32} ({@code FloatList}), {@code tf.int64} ({@code Int64List}),
   * and {@code tf.string} ({@code BytesList}) are supported.
   * @param denseShapes List of tuples with the same length as {@code dense_keys}.
   * The shape of the data for each dense feature referenced by {@code dense_keys}.
   * Required for any input tensors identified by {@code dense_keys}.  Must be
   * either fully defined, or may contain an unknown first dimension.
   * An unknown first dimension means the feature is treated as having
   * a variable number of blocks, and the output shape along this dimension
   * is considered unknown at graph build time.  Padding is applied for
   * minibatch elements smaller than the maximum number of blocks for the
   * given feature along this dimension.
   * @param outputTypes The type list for the return values.
   * @param outputShapes The list of shapes being produced.
   * @param raggedValueTypes the value of the raggedValueTypes property
   * @param raggedSplitTypes the value of the raggedSplitTypes property
   * @param options carries optional attribute values
   * @return a new instance of ParseExampleDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParseExampleDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> numParallelCalls, Iterable<Operand<?>> denseDefaults, List<String> sparseKeys,
      List<String> denseKeys, List<Class<? extends TType>> sparseTypes, List<Shape> denseShapes,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      List<Class<? extends TType>> raggedValueTypes,
      List<Class<? extends TNumber>> raggedSplitTypes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ParseExampleDatasetV2", scope.makeOpName("ParseExampleDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(numParallelCalls.asOutput());
    opBuilder.addInputList(Operands.asOutputs(denseDefaults));
    opBuilder = scope.apply(opBuilder);
    String[] sparseKeysArray = new String[sparseKeys.size()];
    for (int i = 0 ; i < sparseKeysArray.length ; i++) {
      sparseKeysArray[i] = sparseKeys.get(i);
    }
    opBuilder.setAttr("sparse_keys", sparseKeysArray);
    String[] denseKeysArray = new String[denseKeys.size()];
    for (int i = 0 ; i < denseKeysArray.length ; i++) {
      denseKeysArray[i] = denseKeys.get(i);
    }
    opBuilder.setAttr("dense_keys", denseKeysArray);
    opBuilder.setAttr("sparse_types", Operands.toDataTypes(sparseTypes));
    Shape[] denseShapesArray = new Shape[denseShapes.size()];
    for (int i = 0 ; i < denseShapesArray.length ; i++) {
      denseShapesArray[i] = denseShapes.get(i);
    }
    opBuilder.setAttr("dense_shapes", denseShapesArray);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    opBuilder.setAttr("ragged_value_types", Operands.toDataTypes(raggedValueTypes));
    opBuilder.setAttr("ragged_split_types", Operands.toDataTypes(raggedSplitTypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.deterministic != null) {
          opBuilder.setAttr("deterministic", opts.deterministic);
        }
        if (opts.raggedKeys != null) {
          String[] raggedKeysArray = new String[opts.raggedKeys.size()];
          for (int i = 0 ; i < raggedKeysArray.length ; i++) {
            raggedKeysArray[i] = opts.raggedKeys.get(i);
          }
          opBuilder.setAttr("ragged_keys", raggedKeysArray);
        }
      }
    }
    return new ParseExampleDataset(opBuilder.build());
  }

  /**
   * Sets the deterministic option.
   *
   * @param deterministic A string indicating the op-level determinism to use. Deterministic controls
   * whether the dataset is allowed to return elements out of order if the next
   * element to be returned isn't available, but a later element is. Options are
   * &quot;true&quot;, &quot;false&quot;, and &quot;default&quot;. &quot;default&quot; indicates that determinism should be
   * decided by the {@code experimental_deterministic} parameter of {@code tf.data.Options}.
   * @return this Options instance.
   */
  public static Options deterministic(String deterministic) {
    return new Options().deterministic(deterministic);
  }

  /**
   * Sets the raggedKeys option.
   *
   * @param raggedKeys the raggedKeys option
   * @return this Options instance.
   */
  public static Options raggedKeys(List<String> raggedKeys) {
    return new Options().raggedKeys(raggedKeys);
  }

  /**
   * Sets the raggedKeys option.
   *
   * @param raggedKeys the raggedKeys option
   * @return this Options instance.
   */
  public static Options raggedKeys(String[] raggedKeys) {
    return new Options().raggedKeys(raggedKeys);
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.experimental.ParseExampleDataset}
   */
  public static class Options {
    private String deterministic;

    private List<String> raggedKeys;

    private Options() {
    }

    /**
     * Sets the deterministic option.
     *
     * @param deterministic A string indicating the op-level determinism to use. Deterministic controls
     * whether the dataset is allowed to return elements out of order if the next
     * element to be returned isn't available, but a later element is. Options are
     * &quot;true&quot;, &quot;false&quot;, and &quot;default&quot;. &quot;default&quot; indicates that determinism should be
     * decided by the {@code experimental_deterministic} parameter of {@code tf.data.Options}.
     * @return this Options instance.
     */
    public Options deterministic(String deterministic) {
      this.deterministic = deterministic;
      return this;
    }

    /**
     * Sets the raggedKeys option.
     *
     * @param raggedKeys the raggedKeys option
     * @return this Options instance.
     */
    public Options raggedKeys(List<String> raggedKeys) {
      this.raggedKeys = raggedKeys;
      return this;
    }

    /**
     * Sets the raggedKeys option.
     *
     * @param raggedKeys the raggedKeys option
     * @return this Options instance.
     */
    public Options raggedKeys(String... raggedKeys) {
      this.raggedKeys = Arrays.asList(raggedKeys);
      return this;
    }
  }
}
