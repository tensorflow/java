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
 * The FixedLengthRecordDatasetV2 operation
 */
@OpMetadata(
    opType = FixedLengthRecordDataset.OP_NAME,
    inputsClass = FixedLengthRecordDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class FixedLengthRecordDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FixedLengthRecordDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public FixedLengthRecordDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FixedLengthRecordDatasetV2 operation.
   *
   * @param scope current scope
   * @param filenames The filenames value
   * @param headerBytes The headerBytes value
   * @param recordBytes The recordBytes value
   * @param footerBytes The footerBytes value
   * @param bufferSize The bufferSize value
   * @param compressionType The compressionType value
   * @param options carries optional attribute values
   * @return a new instance of FixedLengthRecordDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static FixedLengthRecordDataset create(Scope scope, Operand<TString> filenames,
      Operand<TInt64> headerBytes, Operand<TInt64> recordBytes, Operand<TInt64> footerBytes,
      Operand<TInt64> bufferSize, Operand<TString> compressionType, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FixedLengthRecordDataset");
    opBuilder.addInput(filenames.asOutput());
    opBuilder.addInput(headerBytes.asOutput());
    opBuilder.addInput(recordBytes.asOutput());
    opBuilder.addInput(footerBytes.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(compressionType.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new FixedLengthRecordDataset(opBuilder.build());
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
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.FixedLengthRecordDataset}
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
      outputsClass = FixedLengthRecordDataset.class
  )
  public static class Inputs extends RawOpInputs<FixedLengthRecordDataset> {
    /**
     * The filenames input
     */
    public final Operand<TString> filenames;

    /**
     * The headerBytes input
     */
    public final Operand<TInt64> headerBytes;

    /**
     * The recordBytes input
     */
    public final Operand<TInt64> recordBytes;

    /**
     * The footerBytes input
     */
    public final Operand<TInt64> footerBytes;

    /**
     * The bufferSize input
     */
    public final Operand<TInt64> bufferSize;

    /**
     * The compressionType input
     */
    public final Operand<TString> compressionType;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new FixedLengthRecordDataset(op), op, Arrays.asList("metadata"));
      int inputIndex = 0;
      filenames = (Operand<TString>) op.input(inputIndex++);
      headerBytes = (Operand<TInt64>) op.input(inputIndex++);
      recordBytes = (Operand<TInt64>) op.input(inputIndex++);
      footerBytes = (Operand<TInt64>) op.input(inputIndex++);
      bufferSize = (Operand<TInt64>) op.input(inputIndex++);
      compressionType = (Operand<TString>) op.input(inputIndex++);
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
