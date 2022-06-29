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

package org.tensorflow.op.strings;

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
import org.tensorflow.types.TInt32;

/**
 * Determine the script codes of a given tensor of Unicode integer code points.
 * This operation converts Unicode code points to script codes corresponding to
 * each code point. Script codes correspond to International Components for
 * Unicode (ICU) UScriptCode values.
 * <p>See
 *  <a href="http://icu-project.org/apiref/icu4c/uscript_8h.html">ICU project docs</a> 
 * for more details on script codes.
 * <p>For an example, see the unicode strings guide on [unicode scripts]
 * (https://www.tensorflow.org/tutorials/load_data/unicode#representing_unicode).
 * <p>Returns -1 (USCRIPT_INVALID_CODE) for invalid codepoints. Output shape will
 * match input shape.
 * <p>Examples:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>tf.strings.unicode_script([1, 31, 38])
 * &lt;tf.Tensor: shape=(3,), dtype=int32, numpy=array([0, 0, 0], dtype=int32)&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 */
@OpMetadata(
    opType = UnicodeScript.OP_NAME,
    inputsClass = UnicodeScript.Inputs.class
)
@Operator(
    group = "strings"
)
public final class UnicodeScript extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UnicodeScript";

  private Output<TInt32> output;

  public UnicodeScript(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UnicodeScript operation.
   *
   * @param scope current scope
   * @param input A Tensor of int32 Unicode code points.
   * @return a new instance of UnicodeScript
   */
  @Endpoint(
      describeByClass = true
  )
  public static UnicodeScript create(Scope scope, Operand<TInt32> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UnicodeScript");
    opBuilder.addInput(input.asOutput());
    return new UnicodeScript(opBuilder.build());
  }

  /**
   * Gets output.
   * A Tensor of int32 script codes corresponding to each input code point.
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = UnicodeScript.class
  )
  public static class Inputs extends RawOpInputs<UnicodeScript> {
    /**
     * A Tensor of int32 Unicode code points.
     */
    public final Operand<TInt32> input;

    public Inputs(GraphOperation op) {
      super(new UnicodeScript(op), op, Arrays.asList());
      int inputIndex = 0;
      input = (Operand<TInt32>) op.input(inputIndex++);
    }
  }
}
