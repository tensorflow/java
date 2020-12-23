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

package org.tensorflow.op.quantization;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * @param <W> data type for {@code out()} output
 */
public final class QuantizedMatMulWithBiasAndRequantize<W extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.QuantizedMatMulWithBiasAndRequantize}
   */
  public static class Options {
    
    /**
     * @param transposeA 
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }
    
    /**
     * @param transposeB 
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }
    
    /**
     * @param inputQuantMode 
     */
    public Options inputQuantMode(String inputQuantMode) {
      this.inputQuantMode = inputQuantMode;
      return this;
    }
    
    private Boolean transposeA;
    private Boolean transposeB;
    private String inputQuantMode;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new QuantizedMatMulWithBiasAndRequantize operation.
   * 
   * @param scope current scope
   * @param a 
   * @param b 
   * @param bias 
   * @param minA 
   * @param maxA 
   * @param minB 
   * @param maxB 
   * @param minFreezedOutput 
   * @param maxFreezedOutput 
   * @param Toutput 
   * @param options carries optional attributes values
   * @return a new instance of QuantizedMatMulWithBiasAndRequantize
   */
  @Endpoint(describeByClass = true)
  public static <W extends TType, T extends TType, U extends TType, V extends TType> QuantizedMatMulWithBiasAndRequantize<W> create(Scope scope, Operand<T> a, Operand<U> b, Operand<V> bias, Operand<TFloat32> minA, Operand<TFloat32> maxA, Operand<TFloat32> minB, Operand<TFloat32> maxB, Operand<TFloat32> minFreezedOutput, Operand<TFloat32> maxFreezedOutput, Class<W> Toutput, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedMatMulWithBiasAndRequantize", scope.makeOpName("QuantizedMatMulWithBiasAndRequantize"));
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.addInput(bias.asOutput());
    opBuilder.addInput(minA.asOutput());
    opBuilder.addInput(maxA.asOutput());
    opBuilder.addInput(minB.asOutput());
    opBuilder.addInput(maxB.asOutput());
    opBuilder.addInput(minFreezedOutput.asOutput());
    opBuilder.addInput(maxFreezedOutput.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Toutput", Operands.toDataType(Toutput));
    if (options != null) {
      for (Options opts : options) {
        if (opts.transposeA != null) {
          opBuilder.setAttr("transpose_a", opts.transposeA);
        }
        if (opts.transposeB != null) {
          opBuilder.setAttr("transpose_b", opts.transposeB);
        }
        if (opts.inputQuantMode != null) {
          opBuilder.setAttr("input_quant_mode", opts.inputQuantMode);
        }
      }
    }
    return new QuantizedMatMulWithBiasAndRequantize<W>(opBuilder.build());
  }
  
  /**
   * @param transposeA 
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }
  
  /**
   * @param transposeB 
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }
  
  /**
   * @param inputQuantMode 
   */
  public static Options inputQuantMode(String inputQuantMode) {
    return new Options().inputQuantMode(inputQuantMode);
  }
  
  /**
   */
  public Output<W> out() {
    return out;
  }
  
  /**
   */
  public Output<TFloat32> minOut() {
    return minOut;
  }
  
  /**
   */
  public Output<TFloat32> maxOut() {
    return maxOut;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedMatMulWithBiasAndRequantize";
  
  private Output<W> out;
  private Output<TFloat32> minOut;
  private Output<TFloat32> maxOut;
  
  private QuantizedMatMulWithBiasAndRequantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
    minOut = operation.output(outputIdx++);
    maxOut = operation.output(outputIdx++);
  }
}
