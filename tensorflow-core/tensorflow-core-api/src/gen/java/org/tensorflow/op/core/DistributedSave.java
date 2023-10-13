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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The DistributedSave operation
 */
@OpMetadata(
    opType = DistributedSave.OP_NAME,
    inputsClass = DistributedSave.Inputs.class
)
public final class DistributedSave extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DistributedSave";

  public DistributedSave(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DistributedSave operation.
   *
   * @param scope current scope
   * @param dataset The dataset value
   * @param directory The directory value
   * @param address The address value
   * @param options carries optional attribute values
   * @return a new instance of DistributedSave
   */
  @Endpoint(
      describeByClass = true
  )
  public static DistributedSave create(Scope scope, Operand<? extends TType> dataset,
      Operand<TString> directory, Operand<TString> address, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DistributedSave");
    opBuilder.addInput(dataset.asOutput());
    opBuilder.addInput(directory.asOutput());
    opBuilder.addInput(address.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new DistributedSave(opBuilder.build());
  }

  /**
   * Sets the metadata option.
   *
   * @param metadata the metadata option
   * @return this Options instance.
   */
  public static Options metadata(String metadata) {
    return new Options().metadata(metadata);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.DistributedSave}
   */
  public static class Options {
    private String metadata;

    private Options() {
    }

    /**
     * Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public Options metadata(String metadata) {
      this.metadata = metadata;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DistributedSave.class
  )
  public static class Inputs extends RawOpInputs<DistributedSave> {
    /**
     * The dataset input
     */
    public final Operand<? extends TType> dataset;

    /**
     * The directory input
     */
    public final Operand<TString> directory;

    /**
     * The address input
     */
    public final Operand<TString> address;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new DistributedSave(op), op, Arrays.asList("metadata"));
      int inputIndex = 0;
      dataset = (Operand<? extends TType>) op.input(inputIndex++);
      directory = (Operand<TString>) op.input(inputIndex++);
      address = (Operand<TString>) op.input(inputIndex++);
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
