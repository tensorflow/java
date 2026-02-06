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

package org.tensorflow.op.dtypes;

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Converts each entry in the given tensor to strings.
 * Supports many numeric types and boolean.
 * <p>For Unicode, see the
 * [https://www.tensorflow.org/text/guide/unicode](Working with Unicode text)
 * tutorial.
 * <p>Examples:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>tf.strings.as_string([3, 2])
 * &lt;tf.Tensor: shape=(2,), dtype=string, numpy=array([b'3', b'2'], dtype=object)&gt;
 * tf.strings.as_string([3.1415926, 2.71828], precision=2).numpy()
 * array([b'3.14', b'2.72'], dtype=object)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 */
@OpMetadata(
    opType = AsString.OP_NAME,
    inputsClass = AsString.Inputs.class
)
@Operator(
    group = "dtypes"
)
public final class AsString extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AsString";

  private Output<TString> output;

  public AsString(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AsString operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param options carries optional attribute values
   * @return a new instance of AsString
   */
  @Endpoint(
      describeByClass = true
  )
  public static AsString create(Scope scope, Operand<? extends TType> input, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AsString");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.precision != null) {
          opBuilder.setAttr("precision", opts.precision);
        }
        if (opts.scientific != null) {
          opBuilder.setAttr("scientific", opts.scientific);
        }
        if (opts.shortest != null) {
          opBuilder.setAttr("shortest", opts.shortest);
        }
        if (opts.width != null) {
          opBuilder.setAttr("width", opts.width);
        }
        if (opts.fill != null) {
          opBuilder.setAttr("fill", opts.fill);
        }
      }
    }
    return new AsString(opBuilder.build());
  }

  /**
   * Sets the precision option.
   *
   * @param precision The post-decimal precision to use for floating point numbers.
   * Only used if precision &gt; -1.
   * @return this Options instance.
   */
  public static Options precision(Long precision) {
    return new Options().precision(precision);
  }

  /**
   * Sets the scientific option.
   *
   * @param scientific Use scientific notation for floating point numbers.
   * @return this Options instance.
   */
  public static Options scientific(Boolean scientific) {
    return new Options().scientific(scientific);
  }

  /**
   * Sets the shortest option.
   *
   * @param shortest Use shortest representation (either scientific or standard) for
   * floating point numbers.
   * @return this Options instance.
   */
  public static Options shortest(Boolean shortest) {
    return new Options().shortest(shortest);
  }

  /**
   * Sets the width option.
   *
   * @param width Pad pre-decimal numbers to this width.
   * Applies to both floating point and integer numbers.
   * Only used if width &gt; -1.
   * @return this Options instance.
   */
  public static Options width(Long width) {
    return new Options().width(width);
  }

  /**
   * Sets the fill option.
   *
   * @param fill The value to pad if width &gt; -1.  If empty, pads with spaces.
   * Another typical value is '0'.  String cannot be longer than 1 character.
   * @return this Options instance.
   */
  public static Options fill(String fill) {
    return new Options().fill(fill);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TString> output() {
    return output;
  }

  @Override
  public Output<TString> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.dtypes.AsString}
   */
  public static class Options {
    private Long precision;

    private Boolean scientific;

    private Boolean shortest;

    private Long width;

    private String fill;

    private Options() {
    }

    /**
     * Sets the precision option.
     *
     * @param precision The post-decimal precision to use for floating point numbers.
     * Only used if precision &gt; -1.
     * @return this Options instance.
     */
    public Options precision(Long precision) {
      this.precision = precision;
      return this;
    }

    /**
     * Sets the scientific option.
     *
     * @param scientific Use scientific notation for floating point numbers.
     * @return this Options instance.
     */
    public Options scientific(Boolean scientific) {
      this.scientific = scientific;
      return this;
    }

    /**
     * Sets the shortest option.
     *
     * @param shortest Use shortest representation (either scientific or standard) for
     * floating point numbers.
     * @return this Options instance.
     */
    public Options shortest(Boolean shortest) {
      this.shortest = shortest;
      return this;
    }

    /**
     * Sets the width option.
     *
     * @param width Pad pre-decimal numbers to this width.
     * Applies to both floating point and integer numbers.
     * Only used if width &gt; -1.
     * @return this Options instance.
     */
    public Options width(Long width) {
      this.width = width;
      return this;
    }

    /**
     * Sets the fill option.
     *
     * @param fill The value to pad if width &gt; -1.  If empty, pads with spaces.
     * Another typical value is '0'.  String cannot be longer than 1 character.
     * @return this Options instance.
     */
    public Options fill(String fill) {
      this.fill = fill;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AsString.class
  )
  public static class Inputs extends RawOpInputs<AsString> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The post-decimal precision to use for floating point numbers.
     * Only used if precision &gt; -1.
     */
    public final long precision;

    /**
     * Use scientific notation for floating point numbers.
     */
    public final boolean scientific;

    /**
     * Use shortest representation (either scientific or standard) for
     * floating point numbers.
     */
    public final boolean shortest;

    /**
     * Pad pre-decimal numbers to this width.
     * Applies to both floating point and integer numbers.
     * Only used if width &gt; -1.
     */
    public final long width;

    /**
     * The value to pad if width &gt; -1.  If empty, pads with spaces.
     * Another typical value is '0'.  String cannot be longer than 1 character.
     */
    public final String fill;

    public Inputs(GraphOperation op) {
      super(new AsString(op), op, Arrays.asList("T", "precision", "scientific", "shortest", "width", "fill"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      precision = op.attributes().getAttrInt("precision");
      scientific = op.attributes().getAttrBool("scientific");
      shortest = op.attributes().getAttrBool("shortest");
      width = op.attributes().getAttrInt("width");
      fill = op.attributes().getAttrString("fill");
    }
  }
}
