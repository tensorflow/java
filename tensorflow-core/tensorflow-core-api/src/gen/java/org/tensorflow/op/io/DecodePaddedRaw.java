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

package org.tensorflow.op.io;

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Reinterpret the bytes of a string as a vector of numbers.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DecodePaddedRaw.OP_NAME,
    inputsClass = DecodePaddedRaw.Inputs.class
)
@Operator(
    group = "io"
)
public final class DecodePaddedRaw<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodePaddedRaw";

  private Output<T> output;

  public DecodePaddedRaw(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodePaddedRaw operation.
   *
   * @param scope current scope
   * @param inputBytes Tensor of string to be decoded.
   * @param fixedLength Length in bytes for each element of the decoded output. Must be a multiple
   * of the size of the output type.
   * @param outType The value of the outType attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code DecodePaddedRaw} output and operands
   * @return a new instance of DecodePaddedRaw
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> DecodePaddedRaw<T> create(Scope scope,
      Operand<TString> inputBytes, Operand<TInt32> fixedLength, Class<T> outType,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodePaddedRaw");
    opBuilder.addInput(inputBytes.asOutput());
    opBuilder.addInput(fixedLength.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    if (options != null) {
      for (Options opts : options) {
        if (opts.littleEndian != null) {
          opBuilder.setAttr("little_endian", opts.littleEndian);
        }
      }
    }
    return new DecodePaddedRaw<>(opBuilder.build());
  }

  /**
   * Sets the littleEndian option.
   *
   * @param littleEndian Whether the input {@code input_bytes} is in little-endian order. Ignored for
   * {@code out_type} values that are stored in a single byte, like {@code uint8}
   * @return this Options instance.
   */
  public static Options littleEndian(Boolean littleEndian) {
    return new Options().littleEndian(littleEndian);
  }

  /**
   * Gets output.
   * A Tensor with one more dimension than the input {@code bytes}. The added dimension
   * will have size equal to the length of the elements of {@code bytes} divided by the
   * number of bytes to represent {@code out_type}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.DecodePaddedRaw}
   */
  public static class Options {
    private Boolean littleEndian;

    private Options() {
    }

    /**
     * Sets the littleEndian option.
     *
     * @param littleEndian Whether the input {@code input_bytes} is in little-endian order. Ignored for
     * {@code out_type} values that are stored in a single byte, like {@code uint8}
     * @return this Options instance.
     */
    public Options littleEndian(Boolean littleEndian) {
      this.littleEndian = littleEndian;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodePaddedRaw.class
  )
  public static class Inputs extends RawOpInputs<DecodePaddedRaw<?>> {
    /**
     * Tensor of string to be decoded.
     */
    public final Operand<TString> inputBytes;

    /**
     * Length in bytes for each element of the decoded output. Must be a multiple
     * of the size of the output type.
     */
    public final Operand<TInt32> fixedLength;

    /**
     * The outType attribute
     */
    public final DataType outType;

    /**
     * Whether the input `input_bytes` is in little-endian order. Ignored for
     * `out_type` values that are stored in a single byte, like `uint8`
     */
    public final boolean littleEndian;

    public Inputs(GraphOperation op) {
      super(new DecodePaddedRaw<>(op), op, Arrays.asList("out_type", "little_endian"));
      int inputIndex = 0;
      inputBytes = (Operand<TString>) op.input(inputIndex++);
      fixedLength = (Operand<TInt32>) op.input(inputIndex++);
      outType = op.attributes().getAttrType("out_type");
      littleEndian = op.attributes().getAttrBool("little_endian");
    }
  }
}
