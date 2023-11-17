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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that emits the records from one or more TFRecord files.
 */
@OpMetadata(
    opType = TFRecordDatasetV2.OP_NAME,
    inputsClass = TFRecordDatasetV2.Inputs.class
)
public final class TFRecordDatasetV2 extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TFRecordDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public TFRecordDatasetV2(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TFRecordDatasetV2 operation.
   *
   * @param scope current scope
   * @param filenames A scalar or vector containing the name(s) of the file(s) to be
   * read.
   * @param compressionType A scalar containing either (i) the empty string (no
   * compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @param bufferSize A scalar representing the number of bytes to buffer. A value of
   * 0 means no buffering will be performed.
   * @param byteOffsets A scalar or vector containing the number of bytes for each file
   * that will be skipped prior to reading.
   * @param options carries optional attribute values
   * @return a new instance of TFRecordDatasetV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static TFRecordDatasetV2 create(Scope scope, Operand<TString> filenames,
      Operand<TString> compressionType, Operand<TInt64> bufferSize, Operand<TInt64> byteOffsets,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TFRecordDatasetV2");
    opBuilder.addInput(filenames.asOutput());
    opBuilder.addInput(compressionType.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(byteOffsets.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new TFRecordDatasetV2(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.core.TFRecordDatasetV2}
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
      outputsClass = TFRecordDatasetV2.class
  )
  public static class Inputs extends RawOpInputs<TFRecordDatasetV2> {
    /**
     * A scalar or vector containing the name(s) of the file(s) to be
     * read.
     */
    public final Operand<TString> filenames;

    /**
     * A scalar containing either (i) the empty string (no
     * compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
     */
    public final Operand<TString> compressionType;

    /**
     * A scalar representing the number of bytes to buffer. A value of
     * 0 means no buffering will be performed.
     */
    public final Operand<TInt64> bufferSize;

    /**
     * A scalar or vector containing the number of bytes for each file
     * that will be skipped prior to reading.
     */
    public final Operand<TInt64> byteOffsets;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new TFRecordDatasetV2(op), op, Arrays.asList("metadata"));
      int inputIndex = 0;
      filenames = (Operand<TString>) op.input(inputIndex++);
      compressionType = (Operand<TString>) op.input(inputIndex++);
      bufferSize = (Operand<TInt64>) op.input(inputIndex++);
      byteOffsets = (Operand<TInt64>) op.input(inputIndex++);
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
