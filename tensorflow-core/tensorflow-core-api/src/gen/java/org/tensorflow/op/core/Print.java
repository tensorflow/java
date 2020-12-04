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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Prints a string scalar.
 * <p>
 * Prints a string scalar to the desired output_stream.
 */
@Operator
public final class Print extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Print}
   */
  public static class Options {
    
    /**
     * @param outputStream A string specifying the output stream or logging level to print to.
     */
    public Options outputStream(String outputStream) {
      this.outputStream = outputStream;
      return this;
    }
    
    /**
     * @param end 
     */
    public Options end(String end) {
      this.end = end;
      return this;
    }
    
    private String outputStream;
    private String end;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Print operation.
   * 
   * @param scope current scope
   * @param input The string scalar to print.
   * @param options carries optional attributes values
   * @return a new instance of Print
   */
  @Endpoint(describeByClass = true)
  public static Print create(Scope scope, Operand<TString> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("PrintV2", scope.makeOpName("Print"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.outputStream != null) {
          opBuilder.setAttr("output_stream", opts.outputStream);
        }
        if (opts.end != null) {
          opBuilder.setAttr("end", opts.end);
        }
      }
    }
    return new Print(opBuilder.build());
  }
  
  /**
   * @param outputStream A string specifying the output stream or logging level to print to.
   */
  public static Options outputStream(String outputStream) {
    return new Options().outputStream(outputStream);
  }
  
  /**
   * @param end 
   */
  public static Options end(String end) {
    return new Options().end(end);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "PrintV2";
  
  private Print(Operation operation) {
    super(operation);
  }
}
