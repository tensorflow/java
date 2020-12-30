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

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Returns the dimension index in the destination data format given the one in
 * <p>
 * the source data format.
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "nn")
public final class DataFormatDimMap<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.DataFormatDimMap}
   */
  public static class Options {
    
    /**
     * @param srcFormat source data format.
     */
    public Options srcFormat(String srcFormat) {
      this.srcFormat = srcFormat;
      return this;
    }
    
    /**
     * @param dstFormat destination data format.
     */
    public Options dstFormat(String dstFormat) {
      this.dstFormat = dstFormat;
      return this;
    }
    
    private String srcFormat;
    private String dstFormat;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DataFormatDimMap operation.
   * 
   * @param scope current scope
   * @param x A Tensor with each element as a dimension index in source data format.
   * Must be in the range [-4, 4).
   * @param options carries optional attributes values
   * @return a new instance of DataFormatDimMap
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> DataFormatDimMap<T> create(Scope scope, Operand<T> x, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DataFormatDimMap", scope.makeOpName("DataFormatDimMap"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.srcFormat != null) {
          opBuilder.setAttr("src_format", opts.srcFormat);
        }
        if (opts.dstFormat != null) {
          opBuilder.setAttr("dst_format", opts.dstFormat);
        }
      }
    }
    return new DataFormatDimMap<T>(opBuilder.build());
  }
  
  /**
   * @param srcFormat source data format.
   */
  public static Options srcFormat(String srcFormat) {
    return new Options().srcFormat(srcFormat);
  }
  
  /**
   * @param dstFormat destination data format.
   */
  public static Options dstFormat(String dstFormat) {
    return new Options().dstFormat(dstFormat);
  }
  
  /**
   * A Tensor with each element as a dimension index in destination data format.
   */
  public Output<T> y() {
    return y;
  }
  
  @Override
  public Output<T> asOutput() {
    return y;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DataFormatDimMap";
  
  private Output<T> y;
  
  private DataFormatDimMap(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
