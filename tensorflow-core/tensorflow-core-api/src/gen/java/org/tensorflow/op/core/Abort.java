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
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;

/**
 * Raise a exception to abort the process when called.
 * If exit_without_error is true, the process will exit normally,
 * otherwise it will exit with a SIGABORT signal.
 * <p>Returns nothing but an exception.
 */
@OpMetadata(
    opType = Abort.OP_NAME,
    inputsClass = Abort.Inputs.class
)
@Operator
public final class Abort extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Abort";

  public Abort(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new Abort operation.
   *
   * @param scope current scope
   * @param options carries optional attribute values
   * @return a new instance of Abort
   */
  @Endpoint(
      describeByClass = true
  )
  public static Abort create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Abort");
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
   * Sets the errorMsg option.
   *
   * @param errorMsg A string which is the message associated with the exception.
   * @return this Options instance.
   */
  public static Options errorMsg(String errorMsg) {
    return new Options().errorMsg(errorMsg);
  }

  /**
   * Sets the exitWithoutError option.
   *
   * @param exitWithoutError the exitWithoutError option
   * @return this Options instance.
   */
  public static Options exitWithoutError(Boolean exitWithoutError) {
    return new Options().exitWithoutError(exitWithoutError);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Abort}
   */
  public static class Options {
    private String errorMsg;

    private Boolean exitWithoutError;

    private Options() {
    }

    /**
     * Sets the errorMsg option.
     *
     * @param errorMsg A string which is the message associated with the exception.
     * @return this Options instance.
     */
    public Options errorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
      return this;
    }

    /**
     * Sets the exitWithoutError option.
     *
     * @param exitWithoutError the exitWithoutError option
     * @return this Options instance.
     */
    public Options exitWithoutError(Boolean exitWithoutError) {
      this.exitWithoutError = exitWithoutError;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Abort.class
  )
  public static class Inputs extends RawOpInputs<Abort> {
    /**
     * A string which is the message associated with the exception.
     */
    public final String errorMsg;

    /**
     * The exitWithoutError attribute
     */
    public final boolean exitWithoutError;

    public Inputs(GraphOperation op) {
      super(new Abort(op), op, Arrays.asList("error_msg", "exit_without_error"));
      int inputIndex = 0;
      errorMsg = op.attributes().getAttrString("error_msg");
      exitWithoutError = op.attributes().getAttrBool("exit_without_error");
    }
  }
}
