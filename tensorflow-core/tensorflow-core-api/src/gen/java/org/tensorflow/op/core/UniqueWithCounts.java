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
 * Finds unique elements along an axis of a tensor.
 * This operation either returns a tensor {@code y} containing unique elements
 * along the {@code axis} of a tensor. The returned unique elements is sorted
 * in the same order as they occur along {@code axis} in {@code x}.
 * This operation also returns a tensor {@code idx} and a tensor {@code count}
 * that are the same size as the number of the elements in {@code x} along the
 * {@code axis} dimension. The {@code idx} contains the index in the unique output {@code y}
 * and the {@code count} contains the count in the unique output {@code y}.
 * In other words, for an {@code 1-D} tensor {@code x} with `axis = None:
 * <p>{@code y[idx[i]] = x[i] for i in [0, 1,...,rank(x) - 1]}
 * <p>For example:
 * <pre>
 * x = tf.constant([1, 1, 2, 4, 4, 4, 7, 8, 8])
 * y, idx, count = UniqueWithCountsV2(x, axis = [0])
 * y ==&gt; [1, 2, 4, 7, 8]
 * idx ==&gt; [0, 0, 1, 2, 2, 2, 3, 4, 4]
 * count ==&gt; [2, 1, 3, 1, 2]
 * </pre>
 * <p>For a {@code 2-D} tensor {@code x} with {@code axis = 0}:
 * <pre>
 * x = tf.constant([[1, 0, 0],
 *                 [1, 0, 0],
 *                 [2, 0, 0]])
 * y, idx, count = UniqueWithCountsV2(x, axis=[0])
 * y ==&gt; [[1, 0, 0],
 *        [2, 0, 0]]
 * idx ==&gt; [0, 0, 1]
 * count ==&gt; [2, 1]
 * </pre>
 * <p>For a {@code 2-D} tensor {@code x} with {@code axis = 1}:
 * <pre>
 * x = tf.constant([[1, 0, 0],
 *                 [1, 0, 0],
 *                 [2, 0, 0]])
 * y, idx, count = UniqueWithCountsV2(x, axis=[1])
 * y ==&gt; [[1, 0],
 *        [1, 0],
 *        [2, 0]]
 * idx ==&gt; [0, 1, 1]
 * count ==&gt; [1, 2]
 * </pre>
 *
 * @param <T> data type for {@code y} output
 *
 * @param <V> data type for {@code idx} output
 */
@OpMetadata(
    opType = UniqueWithCounts.OP_NAME,
    inputsClass = UniqueWithCounts.Inputs.class
)
@Operator
public final class UniqueWithCounts<T extends TType, V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniqueWithCountsV2";

  private Output<T> y;

  private Output<V> idx;

  private Output<V> count;

  public UniqueWithCounts(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
    idx = operation.output(outputIdx++);
    count = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniqueWithCountsV2 operation.
   *
   * @param scope current scope
   * @param x A {@code Tensor}.
   * @param axis A {@code Tensor} of type {@code int32} (default: None). The axis of the Tensor to
   * find the unique elements.
   * @param outIdx The value of the outIdx attribute
   * @param <T> data type for {@code UniqueWithCountsV2} output and operands
   * @param <V> data type for {@code UniqueWithCountsV2} output and operands
   * @return a new instance of UniqueWithCounts
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, V extends TNumber> UniqueWithCounts<T, V> create(Scope scope,
      Operand<T> x, Operand<? extends TNumber> axis, Class<V> outIdx) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniqueWithCounts");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder.setAttr("out_idx", Operands.toDataType(outIdx));
    return new UniqueWithCounts<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new UniqueWithCountsV2 operation, with the default output types.
   *
   * @param scope current scope
   * @param x A {@code Tensor}.
   * @param axis A {@code Tensor} of type {@code int32} (default: None). The axis of the Tensor to
   * find the unique elements.
   * @param <T> data type for {@code UniqueWithCountsV2} output and operands
   * @return a new instance of UniqueWithCounts, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> UniqueWithCounts<T, TInt32> create(Scope scope, Operand<T> x,
      Operand<? extends TNumber> axis) {
    return create(scope, x, axis, TInt32.class);
  }

  /**
   * Gets y.
   * A {@code Tensor}. Unique elements along the {@code axis} of {@code Tensor} x.
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  /**
   * Gets idx.
   * A 1-D Tensor. Has the same type as x that contains the index of each
   * value of x in the output y.
   * @return idx.
   */
  public Output<V> idx() {
    return idx;
  }

  /**
   * Gets count.
   * A 1-D Tensor. The count of each value of x in the output y.
   * @return count.
   */
  public Output<V> count() {
    return count;
  }

  @OpInputsMetadata(
      outputsClass = UniqueWithCounts.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<UniqueWithCounts<T, ?>> {
    /**
     * A {@code Tensor}.
     */
    public final Operand<T> x;

    /**
     * A {@code Tensor} of type {@code int32} (default: None). The axis of the Tensor to
     * find the unique elements.
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Taxis attribute
     */
    public final DataType Taxis;

    /**
     * The outIdx attribute
     */
    public final DataType outIdx;

    public Inputs(GraphOperation op) {
      super(new UniqueWithCounts<>(op), op, Arrays.asList("T", "Taxis", "out_idx"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Taxis = op.attributes().getAttrType("Taxis");
      outIdx = op.attributes().getAttrType("out_idx");
    }
  }
}
