/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import java.util.Iterator;
import java.util.List;
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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes offsets of concat inputs within its output.
 * For example:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>x = [2, 2, 7]
 * y = [2, 3, 7]
 * z = [2, 9, 7]
 * offsets = concat_offset(1, [x, y, z])
 * [list(off.numpy()) for off in offsets]
 * [[0, 0, 0], [0, 2, 0], [0, 5, 0]]
 * </blockquote>
 * </blockquote>
 * </blockquote>
 * <p>This is typically used by gradient computations for a concat operation.
 */
@OpMetadata(
    opType = ConcatOffset.OP_NAME,
    inputsClass = ConcatOffset.Inputs.class
)
@Operator
public final class ConcatOffset<T extends TNumber> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConcatOffset";

  private List<Output<T>> offset;

  @SuppressWarnings("unchecked")
  public ConcatOffset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int offsetLength = operation.outputListLength("offset");
    offset = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, offsetLength));
    outputIdx += offsetLength;
  }

  /**
   * Factory method to create a class wrapping a new ConcatOffset operation.
   *
   * @param scope current scope
   * @param concatDim The dimension along which to concatenate.
   * @param shape The {@code N} int32 or int64 vectors representing shape of tensors being concatenated.
   * @param <T> data type for {@code ConcatOffset} output and operands
   * @return a new instance of ConcatOffset
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ConcatOffset<T> create(Scope scope, Operand<TInt32> concatDim,
      Iterable<Operand<T>> shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConcatOffset");
    opBuilder.addInput(concatDim.asOutput());
    opBuilder.addInputList(Operands.asOutputs(shape));
    return new ConcatOffset<>(opBuilder.build());
  }

  /**
   * Gets offset.
   * The {@code N} vectors representing the starting offset
   * of input tensors within the concatenated output with type matching {@code shape}.
   * @return offset.
   */
  public List<Output<T>> offset() {
    return offset;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) offset.iterator();
  }

  @OpInputsMetadata(
      outputsClass = ConcatOffset.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<ConcatOffset<T>> {
    /**
     * The dimension along which to concatenate.
     */
    public final Operand<TInt32> concatDim;

    /**
     * The {@code N} int32 or int64 vectors representing shape of tensors being concatenated.
     */
    public final Iterable<Operand<T>> shape;

    /**
     * The shapeType attribute
     */
    public final DataType shapeType;

    public Inputs(GraphOperation op) {
      super(new ConcatOffset<>(op), op, Arrays.asList("shape_type"));
      int inputIndex = 0;
      concatDim = (Operand<TInt32>) op.input(inputIndex++);
      int shapeLength = op.inputListLength("shape");
      shape = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, shapeLength));
      inputIndex += shapeLength;
      shapeType = op.attributes().getAttrType("shape_type");
    }
  }
}
