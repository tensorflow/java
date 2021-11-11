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

package org.tensorflow.op.linalg;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Loads a 2-D (matrix) {@code Tensor} with name {@code old_tensor_name} from the checkpoint
 * at {@code ckpt_path} and potentially reorders its rows and columns using the
 * specified remappings.
 * <p>Most users should use one of the wrapper initializers (such as
 * {@code tf.contrib.framework.load_and_remap_matrix_initializer}) instead of this
 * function directly.
 * <p>The remappings are 1-D tensors with the following properties:
 * <ul>
 * <li>{@code row_remapping} must have exactly {@code num_rows} entries. Row {@code i} of the output
 * matrix will be initialized from the row corresponding to index
 * {@code row_remapping[i]} in the old {@code Tensor} from the checkpoint.</li>
 * <li>{@code col_remapping} must have either 0 entries (indicating that no column
 * reordering is needed) or {@code num_cols} entries. If specified, column {@code j} of the
 * output matrix will be initialized from the column corresponding to index
 * {@code col_remapping[j]} in the old {@code Tensor} from the checkpoint.</li>
 * <li>A value of -1 in either of the remappings signifies a &quot;missing&quot; entry. In that
 * case, values from the {@code initializing_values} tensor will be used to fill that
 * missing row or column. If {@code row_remapping} has {@code r} missing entries and
 * {@code col_remapping} has {@code c} missing entries, then the following condition must be
 * true:</li>
 * </ul>
 * <p>{@code (r * num_cols) + (c * num_rows) - (r * c) == len(initializing_values)}
 * <p>The remapping tensors can be generated using the GenerateVocabRemapping op.
 * <p>As an example, with row_remapping = [1, 0, -1], col_remapping = [0, 2, -1],
 * initializing_values = [0.5, -0.5, 0.25, -0.25, 42], and w(i, j) representing
 * the value from row i, column j of the old tensor in the checkpoint, the output
 * matrix will look like the following:
 * <p>[[w(1, 0),  w(1, 2),  0.5],
 * [w(0, 0),  w(0, 2), -0.5],
 * [0.25,    -0.25,      42]]
 */
@OpMetadata(
    opType = LoadAndRemapMatrix.OP_NAME,
    inputsClass = LoadAndRemapMatrix.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class LoadAndRemapMatrix extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LoadAndRemapMatrix";

  private Output<TFloat32> outputMatrix;

  public LoadAndRemapMatrix(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputMatrix = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LoadAndRemapMatrix operation.
   *
   * @param scope current scope
   * @param ckptPath Path to the TensorFlow checkpoint (version 2, {@code TensorBundle}) from
   * which the old matrix {@code Tensor} will be loaded.
   * @param oldTensorName Name of the 2-D {@code Tensor} to load from checkpoint.
   * @param rowRemapping An int {@code Tensor} of row remappings (generally created by
   * {@code generate_vocab_remapping}).  Even if no row remapping is needed, this must
   * still be an index-valued Tensor (e.g. [0, 1, 2, ...]), or a shifted
   * index-valued {@code Tensor} (e.g. [8, 9, 10, ...], for partitioned {@code Variables}).
   * @param colRemapping An int {@code Tensor} of column remappings (generally created by
   * {@code generate_vocab_remapping}).  May be a size-0 {@code Tensor} if only row remapping
   * is to be done (e.g. column ordering is the same).
   * @param initializingValues A float {@code Tensor} containing  values to fill in for cells
   * in the output matrix that are not loaded from the checkpoint. Length must be
   * exactly the same as the number of missing / new cells.
   * @param numRows Number of rows (length of the 1st dimension) in the output matrix.
   * @param numCols Number of columns (length of the 2nd dimension) in the output matrix.
   * @param options carries optional attribute values
   * @return a new instance of LoadAndRemapMatrix
   */
  @Endpoint(
      describeByClass = true
  )
  public static LoadAndRemapMatrix create(Scope scope, Operand<TString> ckptPath,
      Operand<TString> oldTensorName, Operand<TInt64> rowRemapping, Operand<TInt64> colRemapping,
      Operand<TFloat32> initializingValues, Long numRows, Long numCols, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LoadAndRemapMatrix");
    opBuilder.addInput(ckptPath.asOutput());
    opBuilder.addInput(oldTensorName.asOutput());
    opBuilder.addInput(rowRemapping.asOutput());
    opBuilder.addInput(colRemapping.asOutput());
    opBuilder.addInput(initializingValues.asOutput());
    opBuilder.setAttr("num_rows", numRows);
    opBuilder.setAttr("num_cols", numCols);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxRowsInMemory != null) {
          opBuilder.setAttr("max_rows_in_memory", opts.maxRowsInMemory);
        }
      }
    }
    return new LoadAndRemapMatrix(opBuilder.build());
  }

  /**
   * Sets the maxRowsInMemory option.
   *
   * @param maxRowsInMemory The maximum number of rows to load from the checkpoint at
   * once. If less than or equal to 0, the entire matrix will be loaded into
   * memory. Setting this arg trades increased disk reads for lower memory usage.
   * @return this Options instance.
   */
  public static Options maxRowsInMemory(Long maxRowsInMemory) {
    return new Options().maxRowsInMemory(maxRowsInMemory);
  }

  /**
   * Gets outputMatrix.
   * Output matrix containing existing values loaded from the
   * checkpoint, and with any missing values filled in from initializing_values.
   * @return outputMatrix.
   */
  public Output<TFloat32> outputMatrix() {
    return outputMatrix;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return outputMatrix;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.LoadAndRemapMatrix}
   */
  public static class Options {
    private Long maxRowsInMemory;

    private Options() {
    }

    /**
     * Sets the maxRowsInMemory option.
     *
     * @param maxRowsInMemory The maximum number of rows to load from the checkpoint at
     * once. If less than or equal to 0, the entire matrix will be loaded into
     * memory. Setting this arg trades increased disk reads for lower memory usage.
     * @return this Options instance.
     */
    public Options maxRowsInMemory(Long maxRowsInMemory) {
      this.maxRowsInMemory = maxRowsInMemory;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = LoadAndRemapMatrix.class
  )
  public static class Inputs extends RawOpInputs<LoadAndRemapMatrix> {
    /**
     * Path to the TensorFlow checkpoint (version 2, {@code TensorBundle}) from
     * which the old matrix {@code Tensor} will be loaded.
     */
    public final Operand<TString> ckptPath;

    /**
     * Name of the 2-D {@code Tensor} to load from checkpoint.
     */
    public final Operand<TString> oldTensorName;

    /**
     * An int {@code Tensor} of row remappings (generally created by
     * {@code generate_vocab_remapping}).  Even if no row remapping is needed, this must
     * still be an index-valued Tensor (e.g. [0, 1, 2, ...]), or a shifted
     * index-valued {@code Tensor} (e.g. [8, 9, 10, ...], for partitioned {@code Variables}).
     */
    public final Operand<TInt64> rowRemapping;

    /**
     * An int {@code Tensor} of column remappings (generally created by
     * {@code generate_vocab_remapping}).  May be a size-0 {@code Tensor} if only row remapping
     * is to be done (e.g. column ordering is the same).
     */
    public final Operand<TInt64> colRemapping;

    /**
     * A float {@code Tensor} containing  values to fill in for cells
     * in the output matrix that are not loaded from the checkpoint. Length must be
     * exactly the same as the number of missing / new cells.
     */
    public final Operand<TFloat32> initializingValues;

    /**
     * Number of rows (length of the 1st dimension) in the output matrix.
     */
    public final long numRows;

    /**
     * Number of columns (length of the 2nd dimension) in the output matrix.
     */
    public final long numCols;

    /**
     * The maximum number of rows to load from the checkpoint at
     * once. If less than or equal to 0, the entire matrix will be loaded into
     * memory. Setting this arg trades increased disk reads for lower memory usage.
     */
    public final long maxRowsInMemory;

    public Inputs(GraphOperation op) {
      super(new LoadAndRemapMatrix(op), op, Arrays.asList("num_rows", "num_cols", "max_rows_in_memory"));
      int inputIndex = 0;
      ckptPath = (Operand<TString>) op.input(inputIndex++);
      oldTensorName = (Operand<TString>) op.input(inputIndex++);
      rowRemapping = (Operand<TInt64>) op.input(inputIndex++);
      colRemapping = (Operand<TInt64>) op.input(inputIndex++);
      initializingValues = (Operand<TFloat32>) op.input(inputIndex++);
      numRows = op.attributes().getAttrInt("num_rows");
      numCols = op.attributes().getAttrInt("num_cols");
      maxRowsInMemory = op.attributes().getAttrInt("max_rows_in_memory");
    }
  }
}
