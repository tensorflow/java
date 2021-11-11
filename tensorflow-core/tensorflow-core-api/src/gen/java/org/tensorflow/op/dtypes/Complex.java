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

package org.tensorflow.op.dtypes;

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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts two real numbers to a complex number.
 * Given a tensor {@code real} representing the real part of a complex number, and a
 * tensor {@code imag} representing the imaginary part of a complex number, this
 * operation returns complex numbers elementwise of the form \(a + bj\), where
 * <em>a</em> represents the {@code real} part and <em>b</em> represents the {@code imag} part.
 * <p>The input tensors {@code real} and {@code imag} must have the same shape.
 * <p>For example:
 * <pre>
 * # tensor 'real' is [2.25, 3.25]
 * # tensor `imag` is [4.75, 5.75]
 * tf.complex(real, imag) ==&gt; [[2.25 + 4.75j], [3.25 + 5.75j]]
 * </pre>
 *
 * @param <U> data type for {@code out} output
 */
@OpMetadata(
    opType = Complex.OP_NAME,
    inputsClass = Complex.Inputs.class
)
@Operator(
    group = "dtypes"
)
public final class Complex<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Complex";

  private Output<U> out;

  public Complex(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Complex operation.
   *
   * @param scope current scope
   * @param real The real value
   * @param imag The imag value
   * @param Tout The value of the Tout attribute
   * @param <U> data type for {@code Complex} output and operands
   * @param <T> data type for {@code Complex} output and operands
   * @return a new instance of Complex
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType, T extends TNumber> Complex<U> create(Scope scope, Operand<T> real,
      Operand<T> imag, Class<U> Tout) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Complex");
    opBuilder.addInput(real.asOutput());
    opBuilder.addInput(imag.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    return new Complex<>(opBuilder.build());
  }

  /**
   * Gets out.
   *
   * @return out.
   */
  public Output<U> out() {
    return out;
  }

  @Override
  public Output<U> asOutput() {
    return out;
  }

  @OpInputsMetadata(
      outputsClass = Complex.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Complex<?>> {
    /**
     * The real input
     */
    public final Operand<T> real;

    /**
     * The imag input
     */
    public final Operand<T> imag;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tout attribute
     */
    public final DataType Tout;

    public Inputs(GraphOperation op) {
      super(new Complex<>(op), op, Arrays.asList("T", "Tout"));
      int inputIndex = 0;
      real = (Operand<T>) op.input(inputIndex++);
      imag = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tout = op.attributes().getAttrType("Tout");
    }
  }
}
