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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * Creates a handle to a TensorForestTreeResource
 */
public final class TensorForestTreeResourceHandleOp extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorForestTreeResourceHandleOp";

  private Output<? extends TType> resource;

  @SuppressWarnings("unchecked")
  private TensorForestTreeResourceHandleOp(Operation operation) {
    super(operation);
    int outputIdx = 0;
    resource = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorForestTreeResourceHandleOp operation.
   *
   * @param scope current scope
   * @param options carries optional attribute values
   * @return a new instance of TensorForestTreeResourceHandleOp
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorForestTreeResourceHandleOp create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorForestTreeResourceHandleOp", scope.makeOpName("TensorForestTreeResourceHandleOp"));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new TensorForestTreeResourceHandleOp(opBuilder.build());
  }

  /**
   * Sets the container option.
   *
   * @param container the container option
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
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
   * Gets resource.
   *
   * @return resource.
   */
  public Output<? extends TType> resource() {
    return resource;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) resource;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorForestTreeResourceHandleOp}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
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
  }
}
