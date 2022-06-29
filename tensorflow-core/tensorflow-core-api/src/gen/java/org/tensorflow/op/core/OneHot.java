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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns a one-hot tensor.
 * The locations represented by indices in {@code indices} take value {@code on_value},
 * while all other locations take value {@code off_value}.
 * <p>If the input {@code indices} is rank {@code N}, the output will have rank {@code N+1},
 * The new axis is created at dimension {@code axis} (default: the new axis is
 * appended at the end).
 * <p>If {@code indices} is a scalar the output shape will be a vector of length {@code depth}.
 * <p>If {@code indices} is a vector of length {@code features}, the output shape will be:
 * <pre>
 *   features x depth if axis == -1
 *   depth x features if axis == 0
 * </pre>
 * <p>If {@code indices} is a matrix (batch) with shape {@code [batch, features]},
 * the output shape will be:
 * <pre>
 *   batch x features x depth if axis == -1
 *   batch x depth x features if axis == 1
 *   depth x batch x features if axis == 0
 * </pre>
 * <strong>Examples</strong><br>
 * <p>Suppose that
 * <pre>
 *   indices = [0, 2, -1, 1]
 *   depth = 3
 *   on_value = 5.0
 *   off_value = 0.0
 *   axis = -1
 * </pre>
 * <p>Then output is {@code [4 x 3]}:
 * <pre>
 * output =
 *   [5.0 0.0 0.0]  // one_hot(0)
 *   [0.0 0.0 5.0]  // one_hot(2)
 *   [0.0 0.0 0.0]  // one_hot(-1)
 *   [0.0 5.0 0.0]  // one_hot(1)
 * </pre>
 * <p>Suppose that
 * <pre>
 *   indices = [0, 2, -1, 1]
 *   depth = 3
 *   on_value = 0.0
 *   off_value = 3.0
 *   axis = 0
 * </pre>
 * <p>Then output is {@code [3 x 4]}:
 * <pre>
 * output =
 *   [0.0 3.0 3.0 3.0]
 *   [3.0 3.0 3.0 0.0]
 *   [3.0 3.0 3.0 3.0]
 *   [3.0 0.0 3.0 3.0]
 * //  ^                one_hot(0)
 * //      ^            one_hot(2)
 * //          ^        one_hot(-1)
 * //              ^    one_hot(1)
 * </pre>
 * <p>Suppose that
 * <pre>
 *   indices = [[0, 2], [1, -1]]
 *   depth = 3
 *   on_value = 1.0
 *   off_value = 0.0
 *   axis = -1
 * </pre>
 * <p>Then output is {@code [2 x 2 x 3]}:
 * <pre>
 * output =
 *   [
 *     [1.0, 0.0, 0.0]  // one_hot(0)
 *     [0.0, 0.0, 1.0]  // one_hot(2)
 *   ][
 *     [0.0, 1.0, 0.0]  // one_hot(1)
 *     [0.0, 0.0, 0.0]  // one_hot(-1)
 *   ]
 * </pre>
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = OneHot.OP_NAME,
    inputsClass = OneHot.Inputs.class
)
@Operator
public final class OneHot<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OneHot";

  private Output<U> output;

  public OneHot(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new OneHot operation.
   *
   * @param scope current scope
   * @param indices A tensor of indices.
   * @param depth A scalar defining the depth of the one hot dimension.
   * @param onValue A scalar defining the value to fill in output when {@code indices[j] = i}.
   * @param offValue A scalar defining the value to fill in output when {@code indices[j] != i}.
   * @param options carries optional attribute values
   * @param <U> data type for {@code OneHot} output and operands
   * @return a new instance of OneHot
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> OneHot<U> create(Scope scope, Operand<? extends TNumber> indices,
      Operand<TInt32> depth, Operand<U> onValue, Operand<U> offValue, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OneHot");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(depth.asOutput());
    opBuilder.addInput(onValue.asOutput());
    opBuilder.addInput(offValue.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new OneHot<>(opBuilder.build());
  }

  /**
   * Sets the axis option.
   *
   * @param axis The axis to fill (default: -1, a new inner-most axis).
   * @return this Options instance.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }

  /**
   * Gets output.
   * The one-hot tensor.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.OneHot}
   */
  public static class Options {
    private Long axis;

    private Options() {
    }

    /**
     * Sets the axis option.
     *
     * @param axis The axis to fill (default: -1, a new inner-most axis).
     * @return this Options instance.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = OneHot.class
  )
  public static class Inputs<U extends TType> extends RawOpInputs<OneHot<U>> {
    /**
     * A tensor of indices.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * A scalar defining the depth of the one hot dimension.
     */
    public final Operand<TInt32> depth;

    /**
     * A scalar defining the value to fill in output when {@code indices[j] = i}.
     */
    public final Operand<U> onValue;

    /**
     * A scalar defining the value to fill in output when {@code indices[j] != i}.
     */
    public final Operand<U> offValue;

    /**
     * The axis to fill (default: -1, a new inner-most axis).
     */
    public final long axis;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The TI attribute
     */
    public final DataType TI;

    public Inputs(GraphOperation op) {
      super(new OneHot<>(op), op, Arrays.asList("axis", "T", "TI"));
      int inputIndex = 0;
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      depth = (Operand<TInt32>) op.input(inputIndex++);
      onValue = (Operand<U>) op.input(inputIndex++);
      offValue = (Operand<U>) op.input(inputIndex++);
      axis = op.attributes().getAttrInt("axis");
      T = op.attributes().getAttrType("T");
      TI = op.attributes().getAttrType("TI");
    }
  }
}
