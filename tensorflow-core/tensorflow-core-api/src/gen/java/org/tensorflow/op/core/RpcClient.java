/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The RpcClient operation
 */
@OpMetadata(
    opType = RpcClient.OP_NAME,
    inputsClass = RpcClient.Inputs.class
)
@Operator
public final class RpcClient extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RpcClient";

  private Output<? extends TType> client;

  private Output<TString> methodSpecs;

  @SuppressWarnings("unchecked")
  public RpcClient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    client = operation.output(outputIdx++);
    methodSpecs = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RpcClient operation.
   *
   * @param scope current scope
   * @param serverAddress The serverAddress value
   * @param timeoutInMs The timeoutInMs value
   * @param options carries optional attribute values
   * @return a new instance of RpcClient
   */
  @Endpoint(
      describeByClass = true
  )
  public static RpcClient create(Scope scope, Operand<TString> serverAddress,
      Operand<TInt64> timeoutInMs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RpcClient");
    opBuilder.addInput(serverAddress.asOutput());
    opBuilder.addInput(timeoutInMs.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.listRegisteredMethods != null) {
          opBuilder.setAttr("list_registered_methods", opts.listRegisteredMethods);
        }
      }
    }
    return new RpcClient(opBuilder.build());
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName the sharedName option
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Sets the listRegisteredMethods option.
   *
   * @param listRegisteredMethods the listRegisteredMethods option
   * @return this Options instance.
   */
  public static Options listRegisteredMethods(Boolean listRegisteredMethods) {
    return new Options().listRegisteredMethods(listRegisteredMethods);
  }

  /**
   * Gets client.
   *
   * @return client.
   */
  public Output<? extends TType> client() {
    return client;
  }

  /**
   * Gets methodSpecs.
   *
   * @return methodSpecs.
   */
  public Output<TString> methodSpecs() {
    return methodSpecs;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.RpcClient}
   */
  public static class Options {
    private String sharedName;

    private Boolean listRegisteredMethods;

    private Options() {
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }

    /**
     * Sets the listRegisteredMethods option.
     *
     * @param listRegisteredMethods the listRegisteredMethods option
     * @return this Options instance.
     */
    public Options listRegisteredMethods(Boolean listRegisteredMethods) {
      this.listRegisteredMethods = listRegisteredMethods;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RpcClient.class
  )
  public static class Inputs extends RawOpInputs<RpcClient> {
    /**
     * The serverAddress input
     */
    public final Operand<TString> serverAddress;

    /**
     * The timeoutInMs input
     */
    public final Operand<TInt64> timeoutInMs;

    /**
     * The sharedName attribute
     */
    public final String sharedName;

    /**
     * The listRegisteredMethods attribute
     */
    public final boolean listRegisteredMethods;

    public Inputs(GraphOperation op) {
      super(new RpcClient(op), op, Arrays.asList("shared_name", "list_registered_methods"));
      int inputIndex = 0;
      serverAddress = (Operand<TString>) op.input(inputIndex++);
      timeoutInMs = (Operand<TInt64>) op.input(inputIndex++);
      sharedName = op.attributes().getAttrString("shared_name");
      listRegisteredMethods = op.attributes().getAttrBool("list_registered_methods");
    }
  }
}
