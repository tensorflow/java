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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Computes the (possibly normalized) Levenshtein Edit Distance.
 * The inputs are variable-length sequences provided by SparseTensors
 * (hypothesis_indices, hypothesis_values, hypothesis_shape)
 * and
 * (truth_indices, truth_values, truth_shape).
 * <p>The inputs are:
 */
@OpMetadata(
    opType = EditDistance.OP_NAME,
    inputsClass = EditDistance.Inputs.class
)
@Operator
public final class EditDistance extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EditDistance";

  private Output<TFloat32> output;

  public EditDistance(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EditDistance operation.
   *
   * @param scope current scope
   * @param hypothesisIndices The indices of the hypothesis list SparseTensor.
   * This is an N x R int64 matrix.
   * @param hypothesisValues The values of the hypothesis list SparseTensor.
   * This is an N-length vector.
   * @param hypothesisShape The shape of the hypothesis list SparseTensor.
   * This is an R-length vector.
   * @param truthIndices The indices of the truth list SparseTensor.
   * This is an M x R int64 matrix.
   * @param truthValues The values of the truth list SparseTensor.
   * This is an M-length vector.
   * @param truthShape truth indices, vector.
   * @param options carries optional attribute values
   * @param <T> data type for {@code EditDistance} output and operands
   * @return a new instance of EditDistance
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> EditDistance create(Scope scope,
      Operand<TInt64> hypothesisIndices, Operand<T> hypothesisValues,
      Operand<TInt64> hypothesisShape, Operand<TInt64> truthIndices, Operand<T> truthValues,
      Operand<TInt64> truthShape, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EditDistance");
    opBuilder.addInput(hypothesisIndices.asOutput());
    opBuilder.addInput(hypothesisValues.asOutput());
    opBuilder.addInput(hypothesisShape.asOutput());
    opBuilder.addInput(truthIndices.asOutput());
    opBuilder.addInput(truthValues.asOutput());
    opBuilder.addInput(truthShape.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.normalize != null) {
          opBuilder.setAttr("normalize", opts.normalize);
        }
      }
    }
    return new EditDistance(opBuilder.build());
  }

  /**
   * Sets the normalize option.
   *
   * @param normalize boolean (if true, edit distances are normalized by length of truth).
   * <p>The output is:
   * @return this Options instance.
   */
  public static Options normalize(Boolean normalize) {
    return new Options().normalize(normalize);
  }

  /**
   * Gets output.
   * A dense float tensor with rank R - 1.
   * <p>For the example input:
   * <pre>
   * // hypothesis represents a 2x1 matrix with variable-length values:
   * //   (0,0) = [&quot;a&quot;]
   * //   (1,0) = [&quot;b&quot;]
   * hypothesis_indices = [[0, 0, 0],
   *                       [1, 0, 0]]
   * hypothesis_values = [&quot;a&quot;, &quot;b&quot;]
   * hypothesis_shape = [2, 1, 1]
   *
   * // truth represents a 2x2 matrix with variable-length values:
   * //   (0,0) = []
   * //   (0,1) = [&quot;a&quot;]
   * //   (1,0) = [&quot;b&quot;, &quot;c&quot;]
   * //   (1,1) = [&quot;a&quot;]
   * truth_indices = [[0, 1, 0],
   *                  [1, 0, 0],
   *                  [1, 0, 1],
   *                  [1, 1, 0]]
   * truth_values = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;a&quot;]
   * truth_shape = [2, 2, 2]
   * normalize = true
   * </pre>
   * <p>The output will be:
   * <pre>
   * // output is a 2x2 matrix with edit distances normalized by truth lengths.
   * output = [[inf, 1.0],  // (0,0): no truth, (0,1): no hypothesis
   *           [0.5, 1.0]]  // (1,0): addition, (1,1): no hypothesis
   * </pre>
   * @return output.
   */
  public Output<TFloat32> output() {
    return output;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.EditDistance}
   */
  public static class Options {
    private Boolean normalize;

    private Options() {
    }

    /**
     * Sets the normalize option.
     *
     * @param normalize boolean (if true, edit distances are normalized by length of truth).
     * <p>The output is:
     * @return this Options instance.
     */
    public Options normalize(Boolean normalize) {
      this.normalize = normalize;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EditDistance.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<EditDistance> {
    /**
     * The indices of the hypothesis list SparseTensor.
     * This is an N x R int64 matrix.
     */
    public final Operand<TInt64> hypothesisIndices;

    /**
     * The values of the hypothesis list SparseTensor.
     * This is an N-length vector.
     */
    public final Operand<T> hypothesisValues;

    /**
     * The shape of the hypothesis list SparseTensor.
     * This is an R-length vector.
     */
    public final Operand<TInt64> hypothesisShape;

    /**
     * The indices of the truth list SparseTensor.
     * This is an M x R int64 matrix.
     */
    public final Operand<TInt64> truthIndices;

    /**
     * The values of the truth list SparseTensor.
     * This is an M-length vector.
     */
    public final Operand<T> truthValues;

    /**
     * truth indices, vector.
     */
    public final Operand<TInt64> truthShape;

    /**
     * boolean (if true, edit distances are normalized by length of truth).
     *
     * The output is:
     */
    public final boolean normalize;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new EditDistance(op), op, Arrays.asList("normalize", "T"));
      int inputIndex = 0;
      hypothesisIndices = (Operand<TInt64>) op.input(inputIndex++);
      hypothesisValues = (Operand<T>) op.input(inputIndex++);
      hypothesisShape = (Operand<TInt64>) op.input(inputIndex++);
      truthIndices = (Operand<TInt64>) op.input(inputIndex++);
      truthValues = (Operand<T>) op.input(inputIndex++);
      truthShape = (Operand<TInt64>) op.input(inputIndex++);
      normalize = op.attributes().getAttrBool("normalize");
      T = op.attributes().getAttrType("T");
    }
  }
}
