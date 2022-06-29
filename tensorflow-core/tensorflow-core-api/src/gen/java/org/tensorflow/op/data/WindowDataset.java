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

package org.tensorflow.op.data;

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
import org.tensorflow.types.family.TType;

/**
 * Combines (nests of) input elements into a dataset of (nests of) windows.
 * <p>A &quot;window&quot; is a finite dataset of flat elements of size {@code size} (or possibly
 * fewer if there are not enough input elements to fill the window and
 * {@code drop_remainder} evaluates to false).
 * <p>The {@code shift} argument determines the number of input elements by which
 * the window moves on each iteration.  The first element in the {@code k}th window
 * will be element
 * <pre>
 * 1 + (k-1) * shift
 * </pre>
 * <p>of the input dataset. In particular, the first element of the first window
 * will always be the first element of the input dataset.
 * <p>If the {@code stride} parameter is greater than 1, then each window will skip
 * {@code (stride - 1)} input elements between each element that appears in the
 * window. Output windows will still contain {@code size} elements regardless of
 * the value of {@code stride}.
 * <p>The {@code stride} argument determines the stride of the input elements, and the
 * {@code shift} argument determines the shift of the window.
 * <p>For example, letting {@code {...}} to represent a Dataset:
 * <ul>
 * <li>{@code tf.data.Dataset.range(7).window(2)} produces
 * {@code {{0, 1}, {2, 3}, {4, 5}, {6}}}</li>
 * <li>{@code tf.data.Dataset.range(7).window(3, 2, 1, True)} produces
 * {@code {{0, 1, 2}, {2, 3, 4}, {4, 5, 6}}}</li>
 * <li>{@code tf.data.Dataset.range(7).window(3, 1, 2, True)} produces
 * {@code {{0, 2, 4}, {1, 3, 5}, {2, 4, 6}}}</li>
 * </ul>
 * <p>Note that when the {@code window} transformation is applied to a dataset of
 * nested elements, it produces a dataset of nested windows.
 * <p>For example:
 * <ul>
 * <li>{@code tf.data.Dataset.from_tensor_slices((range(4), range(4))).window(2)}
 * produces {@code {({0, 1}, {0, 1}), ({2, 3}, {2, 3})}}</li>
 * <li>{@code tf.data.Dataset.from_tensor_slices({"a": range(4)}).window(2)}
 * produces {@code {{"a": {0, 1}}, {"a": {2, 3}}}}</li>
 * </ul>
 */
@OpMetadata(
    opType = WindowDataset.OP_NAME,
    inputsClass = WindowDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class WindowDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "WindowDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public WindowDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new WindowDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param sizeOutput An integer scalar, representing the number of elements
   * of the input dataset to combine into a window. Must be positive.
   * @param shift An integer scalar, representing the number of input elements
   * by which the window moves in each iteration.  Defaults to {@code size}.
   * Must be positive.
   * @param stride An integer scalar, representing the stride of the input elements
   * in the sliding window. Must be positive. The default value of 1 means
   * &quot;retain every input element&quot;.
   * @param dropRemainder A Boolean scalar, representing whether the last window should be
   * dropped if its size is smaller than {@code window_size}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of WindowDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static WindowDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> sizeOutput, Operand<TInt64> shift, Operand<TInt64> stride,
      Operand<TBool> dropRemainder, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "WindowDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder.addInput(shift.asOutput());
    opBuilder.addInput(stride.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new WindowDataset(opBuilder.build());
  }

  /**
   * Sets the metadata option.
   *
   * @param metadata the metadata option
   * @return this Options instance.
   */
  public static Options metadata(String metadata) {
    return new Options().metadata(metadata);
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
   * Optional attributes for {@link org.tensorflow.op.data.WindowDataset}
   */
  public static class Options {
    private String metadata;

    private Options() {
    }

    /**
     * Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public Options metadata(String metadata) {
      this.metadata = metadata;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = WindowDataset.class
  )
  public static class Inputs extends RawOpInputs<WindowDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * An integer scalar, representing the number of elements
     * of the input dataset to combine into a window. Must be positive.
     */
    public final Operand<TInt64> sizeOutput;

    /**
     * An integer scalar, representing the number of input elements
     * by which the window moves in each iteration.  Defaults to {@code size}.
     * Must be positive.
     */
    public final Operand<TInt64> shift;

    /**
     * An integer scalar, representing the stride of the input elements
     * in the sliding window. Must be positive. The default value of 1 means
     * &quot;retain every input element&quot;.
     */
    public final Operand<TInt64> stride;

    /**
     * A Boolean scalar, representing whether the last window should be
     * dropped if its size is smaller than {@code window_size}.
     */
    public final Operand<TBool> dropRemainder;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new WindowDataset(op), op, Arrays.asList("output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt64>) op.input(inputIndex++);
      shift = (Operand<TInt64>) op.input(inputIndex++);
      stride = (Operand<TInt64>) op.input(inputIndex++);
      dropRemainder = (Operand<TBool>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
