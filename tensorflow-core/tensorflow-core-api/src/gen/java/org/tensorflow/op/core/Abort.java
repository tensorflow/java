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

import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Raise a exception to abort the process when called.
 * <p>
 * If exit_without_error is true, the process will exit normally,
 * otherwise it will exit with a SIGABORT signal.
 * <p>
 * Returns nothing but an exception.
 */
@Operator
public final class Abort extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Abort}
   */
  public static class Options {
    
    /**
     * @param errorMsg A string which is the message associated with the exception.
     */
    public Options errorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
      return this;
    }
    
    /**
     * @param exitWithoutError 
     */
    public Options exitWithoutError(Boolean exitWithoutError) {
      this.exitWithoutError = exitWithoutError;
      return this;
    }
    
    private String errorMsg;
    private Boolean exitWithoutError;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Abort operation.
   * 
   * @param scope current scope
   * @param options carries optional attributes values
   * @return a new instance of Abort
   */
  @Endpoint(describeByClass = true)
  public static Abort create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Abort", scope.makeOpName("Abort"));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.errorMsg != null) {
          opBuilder.setAttr("error_msg", opts.errorMsg);
        }
        if (opts.exitWithoutError != null) {
          opBuilder.setAttr("exit_without_error", opts.exitWithoutError);
        }
      }
    }
    return new Abort(opBuilder.build());
  }
  
  /**
   * @param errorMsg A string which is the message associated with the exception.
   */
  public static Options errorMsg(String errorMsg) {
    return new Options().errorMsg(errorMsg);
  }
  
  /**
   * @param exitWithoutError 
   */
  public static Options exitWithoutError(Boolean exitWithoutError) {
    return new Options().exitWithoutError(exitWithoutError);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Abort";
  
  private Abort(Operation operation) {
    super(operation);
  }
}
