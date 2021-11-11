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

package org.tensorflow.op.train;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;

/**
 * Saves input tensors slices to disk.
 * This is like {@code Save} except that tensors can be listed in the saved file as being
 * a slice of a larger tensor.  {@code shapes_and_slices} specifies the shape of the
 * larger tensor and the slice that this tensor covers. {@code shapes_and_slices} must
 * have as many elements as {@code tensor_names}.
 * <p>Elements of the {@code shapes_and_slices} input must either be:
 * <ul>
 * <li>The empty string, in which case the corresponding tensor is
 * saved normally.</li>
 * <li>A string of the form {@code dim0 dim1 ... dimN-1 slice-spec} where the
 * {@code dimI} are the dimensions of the larger tensor and {@code slice-spec}
 * specifies what part is covered by the tensor to save.</li>
 * </ul>
 * <p>{@code slice-spec} itself is a {@code :}-separated list: {@code slice0:slice1:...:sliceN-1}
 * where each {@code sliceI} is either:
 * <ul>
 * <li>The string {@code -} meaning that the slice covers all indices of this dimension</li>
 * <li>{@code start,length} where {@code start} and {@code length} are integers.  In that
 * case the slice covers {@code length} indices starting at {@code start}.</li>
 * </ul>
 * <p>See also {@code Save}.
 */
@OpMetadata(
    opType = SaveSlices.OP_NAME,
    inputsClass = SaveSlices.Inputs.class
)
@Operator(
    group = "train"
)
public final class SaveSlices extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SaveSlices";

  public SaveSlices(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new SaveSlices operation.
   *
   * @param scope current scope
   * @param filename Must have a single element. The name of the file to which we write the
   * tensor.
   * @param tensorNames Shape {@code [N]}. The names of the tensors to be saved.
   * @param shapesAndSlices Shape {@code [N]}.  The shapes and slice specifications to use when
   * saving the tensors.
   * @param data {@code N} tensors to save.
   * @return a new instance of SaveSlices
   */
  @Endpoint(
      describeByClass = true
  )
  public static SaveSlices create(Scope scope, Operand<TString> filename,
      Operand<TString> tensorNames, Operand<TString> shapesAndSlices, Iterable<Operand<?>> data) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SaveSlices");
    opBuilder.addInput(filename.asOutput());
    opBuilder.addInput(tensorNames.asOutput());
    opBuilder.addInput(shapesAndSlices.asOutput());
    opBuilder.addInputList(Operands.asOutputs(data));
    return new SaveSlices(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = SaveSlices.class
  )
  public static class Inputs extends RawOpInputs<SaveSlices> {
    /**
     * Must have a single element. The name of the file to which we write the
     * tensor.
     */
    public final Operand<TString> filename;

    /**
     * Shape {@code [N]}. The names of the tensors to be saved.
     */
    public final Operand<TString> tensorNames;

    /**
     * Shape {@code [N]}.  The shapes and slice specifications to use when
     * saving the tensors.
     */
    public final Operand<TString> shapesAndSlices;

    /**
     * {@code N} tensors to save.
     */
    public final Iterable<Operand<?>> data;

    /**
     * The T attribute
     */
    public final DataType[] T;

    public Inputs(GraphOperation op) {
      super(new SaveSlices(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      filename = (Operand<TString>) op.input(inputIndex++);
      tensorNames = (Operand<TString>) op.input(inputIndex++);
      shapesAndSlices = (Operand<TString>) op.input(inputIndex++);
      int dataLength = op.inputListLength("data");
      data = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, dataLength));
      inputIndex += dataLength;
      T = op.attributes().getAttrTypeList("T");
    }
  }
}
