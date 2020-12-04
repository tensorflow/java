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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * String lengths of `input`.
 * <p>
 * Computes the length of each string given in the input tensor.
 * <p>
 * >>> strings = tf.constant(['Hello','TensorFlow', '\U0001F642'])
 * >>> tf.strings.length(strings).numpy() # default counts bytes
 * array([ 5, 10, 4], dtype=int32)
 * >>> tf.strings.length(strings, unit="UTF8_CHAR").numpy()
 * array([ 5, 10, 1], dtype=int32)
 * 
 */
@Operator(group = "strings")
public final class StringLength extends RawOp implements Operand<TInt32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.StringLength}
   */
  public static class Options {
    
    /**
     * @param unit The unit that is counted to compute string length.  One of: `"BYTE"` (for
     * the number of bytes in each string) or `"UTF8_CHAR"` (for the number of UTF-8
     * encoded Unicode code points in each string).  Results are undefined
     * if `unit=UTF8_CHAR` and the `input` strings do not contain structurally
     * valid UTF-8.
     */
    public Options unit(String unit) {
      this.unit = unit;
      return this;
    }
    
    private String unit;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new StringLength operation.
   * 
   * @param scope current scope
   * @param input The strings for which to compute the length for each element.
   * @param options carries optional attributes values
   * @return a new instance of StringLength
   */
  @Endpoint(describeByClass = true)
  public static StringLength create(Scope scope, Operand<TString> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringLength", scope.makeOpName("StringLength"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.unit != null) {
          opBuilder.setAttr("unit", opts.unit);
        }
      }
    }
    return new StringLength(opBuilder.build());
  }
  
  /**
   * @param unit The unit that is counted to compute string length.  One of: `"BYTE"` (for
   * the number of bytes in each string) or `"UTF8_CHAR"` (for the number of UTF-8
   * encoded Unicode code points in each string).  Results are undefined
   * if `unit=UTF8_CHAR` and the `input` strings do not contain structurally
   * valid UTF-8.
   */
  public static Options unit(String unit) {
    return new Options().unit(unit);
  }
  
  /**
   * Integer tensor that has the same shape as `input`. The output contains the
   * element-wise string lengths of `input`.
   */
  public Output<TInt32> output() {
    return output;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StringLength";
  
  private Output<TInt32> output;
  
  private StringLength(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
