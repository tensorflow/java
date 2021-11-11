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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Deletes the resource specified by the handle.
 * All subsequent operations using the resource will result in a NotFound
 * error status.
 */
@OpMetadata(
    opType = DestroyResourceOp.OP_NAME,
    inputsClass = DestroyResourceOp.Inputs.class
)
@Operator
public final class DestroyResourceOp extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DestroyResourceOp";

  public DestroyResourceOp(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DestroyResourceOp operation.
   *
   * @param scope current scope
   * @param resource handle to the resource to delete.
   * @param options carries optional attribute values
   * @return a new instance of DestroyResourceOp
   */
  @Endpoint(
      describeByClass = true
  )
  public static DestroyResourceOp create(Scope scope, Operand<? extends TType> resource,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DestroyResourceOp");
    opBuilder.addInput(resource.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.ignoreLookupError != null) {
          opBuilder.setAttr("ignore_lookup_error", opts.ignoreLookupError);
        }
      }
    }
    return new DestroyResourceOp(opBuilder.build());
  }

  /**
   * Sets the ignoreLookupError option.
   *
   * @param ignoreLookupError whether to ignore the error when the resource
   * doesn't exist.
   * @return this Options instance.
   */
  public static Options ignoreLookupError(Boolean ignoreLookupError) {
    return new Options().ignoreLookupError(ignoreLookupError);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.DestroyResourceOp}
   */
  public static class Options {
    private Boolean ignoreLookupError;

    private Options() {
    }

    /**
     * Sets the ignoreLookupError option.
     *
     * @param ignoreLookupError whether to ignore the error when the resource
     * doesn't exist.
     * @return this Options instance.
     */
    public Options ignoreLookupError(Boolean ignoreLookupError) {
      this.ignoreLookupError = ignoreLookupError;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DestroyResourceOp.class
  )
  public static class Inputs extends RawOpInputs<DestroyResourceOp> {
    /**
     * handle to the resource to delete.
     */
    public final Operand<? extends TType> resource;

    /**
     * whether to ignore the error when the resource
     * doesn't exist.
     */
    public final boolean ignoreLookupError;

    public Inputs(GraphOperation op) {
      super(new DestroyResourceOp(op), op, Arrays.asList("ignore_lookup_error"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      ignoreLookupError = op.attributes().getAttrBool("ignore_lookup_error");
    }
  }
}
