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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the difference between two lists of numbers or strings.
 * Given a list {@code x} and a list {@code y}, this operation returns a list {@code out} that
 * represents all values that are in {@code x} but not in {@code y}. The returned list {@code out}
 * is sorted in the same order that the numbers appear in {@code x} (duplicates are
 * preserved). This operation also returns a list {@code idx} that represents the
 * position of each {@code out} element in {@code x}. In other words:
 * <p>{@code out[i] = x[idx[i]] for i in [0, 1, ..., len(out) - 1]}
 * <p>For example, given this input:
 * <pre>
 * x = [1, 2, 3, 4, 5, 6]
 * y = [1, 3, 5]
 * </pre>
 * <p>This operation would return:
 * <pre>
 * out ==&gt; [2, 4, 6]
 * idx ==&gt; [1, 3, 5]
 * </pre>
 *
 * @param <T> data type for {@code out} output
 *
 * @param <U> data type for {@code idx} output
 */
@OpMetadata(
    opType = SetDiff1d.OP_NAME,
    inputsClass = SetDiff1d.Inputs.class
)
@Operator
public final class SetDiff1d<T extends TType, U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ListDiff";

  private Output<T> out;

  private Output<U> idx;

  public SetDiff1d(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
    idx = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ListDiff operation.
   *
   * @param scope current scope
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @param outIdx The value of the outIdx attribute
   * @param <T> data type for {@code ListDiff} output and operands
   * @param <U> data type for {@code ListDiff} output and operands
   * @return a new instance of SetDiff1d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> SetDiff1d<T, U> create(Scope scope,
      Operand<T> x, Operand<T> y, Class<U> outIdx) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SetDiff1d");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder.setAttr("out_idx", Operands.toDataType(outIdx));
    return new SetDiff1d<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new ListDiff operation, with the default output types.
   *
   * @param scope current scope
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @param <T> data type for {@code ListDiff} output and operands
   * @return a new instance of SetDiff1d, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SetDiff1d<T, TInt32> create(Scope scope, Operand<T> x,
      Operand<T> y) {
    return create(scope, x, y, TInt32.class);
  }

  /**
   * Gets out.
   * 1-D. Values present in {@code x} but not in {@code y}.
   * @return out.
   */
  public Output<T> out() {
    return out;
  }

  /**
   * Gets idx.
   * 1-D. Positions of {@code x} values preserved in {@code out}.
   * @return idx.
   */
  public Output<U> idx() {
    return idx;
  }

  @OpInputsMetadata(
      outputsClass = SetDiff1d.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SetDiff1d<T, ?>> {
    /**
     * 1-D. Values to keep.
     */
    public final Operand<T> x;

    /**
     * 1-D. Values to remove.
     */
    public final Operand<T> y;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The outIdx attribute
     */
    public final DataType outIdx;

    public Inputs(GraphOperation op) {
      super(new SetDiff1d<>(op), op, Arrays.asList("T", "out_idx"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      y = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      outIdx = op.attributes().getAttrType("out_idx");
    }
  }
}
