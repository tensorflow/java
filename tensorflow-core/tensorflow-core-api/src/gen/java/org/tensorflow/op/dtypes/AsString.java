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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Converts each entry in the given tensor to strings.
 * <p>
 * Supports many numeric types and boolean.
 * <p>
 * For Unicode, see the
 * [https://www.tensorflow.org/tutorials/representation/unicode](Working with Unicode text)
 * tutorial.
 * <p>
 * Examples:
 * <p>
 * >>> tf.strings.as_string([3, 2])
 * <tf.Tensor: shape=(2,), dtype=string, numpy=array([b'3', b'2'], dtype=object)>
 * >>> tf.strings.as_string([3.1415926, 2.71828], precision=2).numpy()
 * array([b'3.14', b'2.72'], dtype=object)
 */
@Operator(group = "dtypes")
public final class AsString extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.dtypes.AsString}
   */
  public static class Options {
    
    /**
     * @param precision The post-decimal precision to use for floating point numbers.
     * Only used if precision > -1.
     */
    public Options precision(Long precision) {
      this.precision = precision;
      return this;
    }
    
    /**
     * @param scientific Use scientific notation for floating point numbers.
     */
    public Options scientific(Boolean scientific) {
      this.scientific = scientific;
      return this;
    }
    
    /**
     * @param shortest Use shortest representation (either scientific or standard) for
     * floating point numbers.
     */
    public Options shortest(Boolean shortest) {
      this.shortest = shortest;
      return this;
    }
    
    /**
     * @param width Pad pre-decimal numbers to this width.
     * Applies to both floating point and integer numbers.
     * Only used if width > -1.
     */
    public Options width(Long width) {
      this.width = width;
      return this;
    }
    
    /**
     * @param fill The value to pad if width > -1.  If empty, pads with spaces.
     * Another typical value is '0'.  String cannot be longer than 1 character.
     */
    public Options fill(String fill) {
      this.fill = fill;
      return this;
    }
    
    private Long precision;
    private Boolean scientific;
    private Boolean shortest;
    private Long width;
    private String fill;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new AsString operation.
   * 
   * @param scope current scope
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of AsString
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> AsString create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("AsString", scope.makeOpName("AsString"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param precision The post-decimal precision to use for floating point numbers.
   * Only used if precision > -1.
   */
  public static Options precision(Long precision) {
    return new Options().precision(precision);
  }
  
  /**
   * @param scientific Use scientific notation for floating point numbers.
   */
  public static Options scientific(Boolean scientific) {
    return new Options().scientific(scientific);
  }
  
  /**
   * @param shortest Use shortest representation (either scientific or standard) for
   * floating point numbers.
   */
  public static Options shortest(Boolean shortest) {
    return new Options().shortest(shortest);
  }
  
  /**
   * @param width Pad pre-decimal numbers to this width.
   * Applies to both floating point and integer numbers.
   * Only used if width > -1.
   */
  public static Options width(Long width) {
    return new Options().width(width);
  }
  
  /**
   * @param fill The value to pad if width > -1.  If empty, pads with spaces.
   * Another typical value is '0'.  String cannot be longer than 1 character.
   */
  public static Options fill(String fill) {
    return new Options().fill(fill);
  }
  
  /**
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "AsString";
  
  private Output<TString> output;
  
  private AsString(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
