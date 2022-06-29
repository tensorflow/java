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

package org.tensorflow.op.summary;

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
import org.tensorflow.types.family.TType;

/**
 * The SummaryWriter operation
 */
@OpMetadata(
    opType = SummaryWriter.OP_NAME,
    inputsClass = SummaryWriter.Inputs.class
)
public final class SummaryWriter extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SummaryWriter";

  private Output<? extends TType> writer;

  @SuppressWarnings("unchecked")
  public SummaryWriter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    writer = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SummaryWriter operation.
   *
   * @param scope current scope
   * @param options carries optional attribute values
   * @return a new instance of SummaryWriter
   */
  @Endpoint(
      describeByClass = true
  )
  public static SummaryWriter create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SummaryWriter");
    if (options != null) {
      for (Options opts : options) {
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
      }
    }
    return new SummaryWriter(opBuilder.build());
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
   * Sets the container option.
   *
   * @param container the container option
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Gets writer.
   *
   * @return writer.
   */
  public Output<? extends TType> writer() {
    return writer;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) writer;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.summary.SummaryWriter}
   */
  public static class Options {
    private String sharedName;

    private String container;

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
     * Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SummaryWriter.class
  )
  public static class Inputs extends RawOpInputs<SummaryWriter> {
    /**
     * The sharedName attribute
     */
    public final String sharedName;

    /**
     * The container attribute
     */
    public final String container;

    public Inputs(GraphOperation op) {
      super(new SummaryWriter(op), op, Arrays.asList("shared_name", "container"));
      int inputIndex = 0;
      sharedName = op.attributes().getAttrString("shared_name");
      container = op.attributes().getAttrString("container");
    }
  }
}
