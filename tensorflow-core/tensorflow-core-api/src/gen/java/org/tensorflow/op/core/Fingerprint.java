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
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TType;

/**
 * Generates fingerprint values.
 * Generates fingerprint values of {@code data}.
 * <p>Fingerprint op considers the first dimension of {@code data} as the batch dimension,
 * and {@code output[i]} contains the fingerprint value generated from contents in
 * {@code data[i, ...]} for all {@code i}.
 * <p>Fingerprint op writes fingerprint values as byte arrays. For example, the
 * default method {@code farmhash64} generates a 64-bit fingerprint value at a time.
 * This 8-byte value is written out as an {@code uint8} array of size 8, in little-endian
 * order.
 * <p>For example, suppose that {@code data} has data type {@code DT_INT32} and shape (2, 3, 4),
 * and that the fingerprint method is {@code farmhash64}. In this case, the output shape
 * is (2, 8), where 2 is the batch dimension size of {@code data}, and 8 is the size of
 * each fingerprint value in bytes. {@code output[0, :]} is generated from 12 integers in
 * {@code data[0, :, :]} and similarly {@code output[1, :]} is generated from other 12 integers
 * in {@code data[1, :, :]}.
 * <p>Note that this op fingerprints the raw underlying buffer, and it does not
 * fingerprint Tensor's metadata such as data type and/or shape. For example, the
 * fingerprint values are invariant under reshapes and bitcasts as long as the
 * batch dimension remain the same:
 * <pre>
 * Fingerprint(data) == Fingerprint(Reshape(data, ...))
 * Fingerprint(data) == Fingerprint(Bitcast(data, ...))
 * </pre>
 * <p>For string data, one should expect {@code Fingerprint(data) != Fingerprint(ReduceJoin(data))} in general.
 */
@OpMetadata(
    opType = Fingerprint.OP_NAME,
    inputsClass = Fingerprint.Inputs.class
)
@Operator
public final class Fingerprint extends RawOp implements Operand<TUint8> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Fingerprint";

  private Output<TUint8> fingerprint;

  public Fingerprint(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    fingerprint = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Fingerprint operation.
   *
   * @param scope current scope
   * @param data Must have rank 1 or higher.
   * @param method Fingerprint method used by this op. Currently available method is
   * {@code farmhash::fingerprint64}.
   * @return a new instance of Fingerprint
   */
  @Endpoint(
      describeByClass = true
  )
  public static Fingerprint create(Scope scope, Operand<? extends TType> data,
      Operand<TString> method) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Fingerprint");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(method.asOutput());
    return new Fingerprint(opBuilder.build());
  }

  /**
   * Gets fingerprint.
   * A two-dimensional {@code Tensor} of type {@code tf.uint8}. The first dimension equals to
   * {@code data}'s first dimension, and the second dimension size depends on the
   * fingerprint algorithm.
   * @return fingerprint.
   */
  public Output<TUint8> fingerprint() {
    return fingerprint;
  }

  @Override
  public Output<TUint8> asOutput() {
    return fingerprint;
  }

  @OpInputsMetadata(
      outputsClass = Fingerprint.class
  )
  public static class Inputs extends RawOpInputs<Fingerprint> {
    /**
     * Must have rank 1 or higher.
     */
    public final Operand<? extends TType> data;

    /**
     * Fingerprint method used by this op. Currently available method is
     * {@code farmhash::fingerprint64}.
     */
    public final Operand<TString> method;

    /**
     * This can be a POD-type or string type.
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Fingerprint(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      data = (Operand<? extends TType>) op.input(inputIndex++);
      method = (Operand<TString>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
