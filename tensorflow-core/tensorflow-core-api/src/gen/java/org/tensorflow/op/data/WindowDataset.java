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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 *   Combines (nests of) input elements into a dataset of (nests of) windows.
 * <p>
 *   A "window" is a finite dataset of flat elements of size `size` (or possibly
 *   fewer if there are not enough input elements to fill the window and
 *   `drop_remainder` evaluates to false).
 * <p>
 *   The `shift` argument determines the number of input elements by which
 *   the window moves on each iteration.  The first element in the `k`th window
 *   will be element
 * <p>
 *   <pre>{@code
 *   1 + (k-1) * shift
 *   }</pre>
 * of the input dataset. In particular, the first element of the first window
 *   will always be the first element of the input dataset.  
 * <p>
 *   If the `stride` parameter is greater than 1, then each window will skip
 *   `(stride - 1)` input elements between each element that appears in the
 *   window. Output windows will still contain `size` elements regardless of
 *   the value of `stride`.
 * <p>
 *   The `stride` argument determines the stride of the input elements, and the
 *   `shift` argument determines the shift of the window.
 * <p>
 *   For example, letting `{...}` to represent a Dataset:
 * <p>
 *   - `tf.data.Dataset.range(7).window(2)` produces
 *     `{{0, 1}, {2, 3}, {4, 5}, {6}}`
 *   - `tf.data.Dataset.range(7).window(3, 2, 1, True)` produces
 *     `{{0, 1, 2}, {2, 3, 4}, {4, 5, 6}}`
 *   - `tf.data.Dataset.range(7).window(3, 1, 2, True)` produces
 *     `{{0, 2, 4}, {1, 3, 5}, {2, 4, 6}}`
 * <p>
 *   Note that when the `window` transformation is applied to a dataset of
 *   nested elements, it produces a dataset of nested windows.
 * <p>
 *   For example:
 * <p>
 *   - `tf.data.Dataset.from_tensor_slices((range(4), range(4))).window(2)`
 *     produces `{({0, 1}, {0, 1}), ({2, 3}, {2, 3})}`
 *   - `tf.data.Dataset.from_tensor_slices({"a": range(4)}).window(2)`
 *     produces `{{"a": {0, 1}}, {"a": {2, 3}}}`
 */
public final class WindowDataset extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new WindowDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param size An integer scalar, representing the number of elements
   * of the input dataset to combine into a window. Must be positive.
   * @param shift An integer scalar, representing the number of input elements
   * by which the window moves in each iteration.  Defaults to `size`.
   * Must be positive.
   * @param stride An integer scalar, representing the stride of the input elements
   * in the sliding window. Must be positive. The default value of 1 means
   * "retain every input element".
   * @param dropRemainder A Boolean scalar, representing whether the last window should be
   * dropped if its size is smaller than `window_size`.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of WindowDataset
   */
  @Endpoint(describeByClass = true)
  public static WindowDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> size, Operand<TInt64> shift, Operand<TInt64> stride, Operand<TBool> dropRemainder, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("WindowDataset", scope.makeOpName("WindowDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder.addInput(shift.asOutput());
    opBuilder.addInput(stride.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new WindowDataset(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "WindowDataset";
  
  private Output<?> handle;
  
  private WindowDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
