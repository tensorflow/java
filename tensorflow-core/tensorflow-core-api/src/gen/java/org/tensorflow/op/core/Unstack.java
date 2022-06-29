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
import java.util.Iterator;
import java.util.List;
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
import org.tensorflow.types.family.TType;

/**
 * Unpacks a given dimension of a rank-{@code R} tensor into {@code num} rank-{@code (R-1)} tensors.
 * Unpacks {@code num} tensors from {@code value} by chipping it along the {@code axis} dimension.
 * For example, given a tensor of shape {@code (A, B, C, D)};
 * <p>If {@code axis == 0} then the i'th tensor in {@code output} is the slice {@code value[i, :, :, :]}
 * and each tensor in {@code output} will have shape {@code (B, C, D)}. (Note that the
 * dimension unpacked along is gone, unlike {@code split}).
 * <p>If {@code axis == 1} then the i'th tensor in {@code output} is the slice {@code value[:, i, :, :]}
 * and each tensor in {@code output} will have shape {@code (A, C, D)}.
 * Etc.
 * <p>This is the opposite of {@code pack}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Unstack.OP_NAME,
    inputsClass = Unstack.Inputs.class
)
@Operator
public final class Unstack<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Unpack";

  private List<Output<T>> output;

  @SuppressWarnings("unchecked")
  public Unstack(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new Unpack operation.
   *
   * @param scope current scope
   * @param value 1-D or higher, with {@code axis} dimension size equal to {@code num}.
   * @param num The value of the num attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code Unpack} output and operands
   * @return a new instance of Unstack
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Unstack<T> create(Scope scope, Operand<T> value, Long num,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Unstack");
    opBuilder.addInput(value.asOutput());
    opBuilder.setAttr("num", num);
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new Unstack<>(opBuilder.build());
  }

  /**
   * Sets the axis option.
   *
   * @param axis Dimension along which to unpack.  Negative values wrap around, so the
   * valid range is {@code [-R, R)}.
   * @return this Options instance.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }

  /**
   * Gets output.
   * The list of tensors unpacked from {@code value}.
   * @return output.
   */
  public List<Output<T>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) output.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Unstack}
   */
  public static class Options {
    private Long axis;

    private Options() {
    }

    /**
     * Sets the axis option.
     *
     * @param axis Dimension along which to unpack.  Negative values wrap around, so the
     * valid range is {@code [-R, R)}.
     * @return this Options instance.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Unstack.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Unstack<T>> {
    /**
     * 1-D or higher, with {@code axis} dimension size equal to {@code num}.
     */
    public final Operand<T> value;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Dimension along which to unpack.  Negative values wrap around, so the
     * valid range is `[-R, R)`.
     */
    public final long axis;

    public Inputs(GraphOperation op) {
      super(new Unstack<>(op), op, Arrays.asList("T", "axis"));
      int inputIndex = 0;
      value = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      axis = op.attributes().getAttrInt("axis");
    }
  }
}
