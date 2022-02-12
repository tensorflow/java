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

package org.tensorflow.op.data;

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
 * Registers a dataset with the tf.data service.
 */
@OpMetadata(
    opType = RegisterDataset.OP_NAME,
    inputsClass = RegisterDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class RegisterDataset extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RegisterDataset";

  private Output<TInt64> datasetId;

  public RegisterDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    datasetId = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RegisterDataset operation.
   *
   * @param scope current scope
   * @param dataset The dataset value
   * @param address The address value
   * @param protocol The protocol value
   * @param externalStatePolicy The value of the externalStatePolicy attribute
   * @param options carries optional attribute values
   * @return a new instance of RegisterDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static RegisterDataset create(Scope scope, Operand<? extends TType> dataset,
      Operand<TString> address, Operand<TString> protocol, Long externalStatePolicy,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RegisterDataset");
    opBuilder.addInput(dataset.asOutput());
    opBuilder.addInput(address.asOutput());
    opBuilder.addInput(protocol.asOutput());
    opBuilder.setAttr("external_state_policy", externalStatePolicy);
    if (options != null) {
      for (Options opts : options) {
        if (opts.elementSpec != null) {
          opBuilder.setAttr("element_spec", opts.elementSpec);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new RegisterDataset(opBuilder.build());
  }

  /**
   * Sets the elementSpec option.
   *
   * @param elementSpec the elementSpec option
   * @return this Options instance.
   */
  public static Options elementSpec(String elementSpec) {
    return new Options().elementSpec(elementSpec);
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
   * Gets datasetId.
   *
   * @return datasetId.
   */
  public Output<TInt64> datasetId() {
    return datasetId;
  }

  @Override
  public Output<TInt64> asOutput() {
    return datasetId;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.RegisterDataset}
   */
  public static class Options {
    private String elementSpec;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the elementSpec option.
     *
     * @param elementSpec the elementSpec option
     * @return this Options instance.
     */
    public Options elementSpec(String elementSpec) {
      this.elementSpec = elementSpec;
      return this;
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
      outputsClass = RegisterDataset.class
  )
  public static class Inputs extends RawOpInputs<RegisterDataset> {
    /**
     * The dataset input
     */
    public final Operand<? extends TType> dataset;

    /**
     * The address input
     */
    public final Operand<TString> address;

    /**
     * The protocol input
     */
    public final Operand<TString> protocol;

    /**
     * The externalStatePolicy attribute
     */
    public final long externalStatePolicy;

    /**
     * The elementSpec attribute
     */
    public final String elementSpec;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new RegisterDataset(op), op, Arrays.asList("external_state_policy", "element_spec", "metadata"));
      int inputIndex = 0;
      dataset = (Operand<? extends TType>) op.input(inputIndex++);
      address = (Operand<TString>) op.input(inputIndex++);
      protocol = (Operand<TString>) op.input(inputIndex++);
      externalStatePolicy = op.attributes().getAttrInt("external_state_policy");
      elementSpec = op.attributes().getAttrString("element_spec");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
