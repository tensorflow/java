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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Writes an image summary.
 * Writes image {@code tensor} at {@code step} with {@code tag} using summary {@code writer}.
 * {@code tensor} is image with shape [height, width, channels].
 */
@OpMetadata(
    opType = WriteImageSummary.OP_NAME,
    inputsClass = WriteImageSummary.Inputs.class
)
public final class WriteImageSummary extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "WriteImageSummary";

  public WriteImageSummary(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new WriteImageSummary operation.
   *
   * @param scope current scope
   * @param writer The writer value
   * @param step The step value
   * @param tag The tag value
   * @param tensor The tensor value
   * @param badColor The badColor value
   * @param options carries optional attribute values
   * @return a new instance of WriteImageSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static WriteImageSummary create(Scope scope, Operand<? extends TType> writer,
      Operand<TInt64> step, Operand<TString> tag, Operand<? extends TNumber> tensor,
      Operand<TUint8> badColor, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "WriteImageSummary");
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(step.asOutput());
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(badColor.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxImages != null) {
          opBuilder.setAttr("max_images", opts.maxImages);
        }
      }
    }
    return new WriteImageSummary(opBuilder.build());
  }

  /**
   * Sets the maxImages option.
   *
   * @param maxImages the maxImages option
   * @return this Options instance.
   */
  public static Options maxImages(Long maxImages) {
    return new Options().maxImages(maxImages);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.summary.WriteImageSummary}
   */
  public static class Options {
    private Long maxImages;

    private Options() {
    }

    /**
     * Sets the maxImages option.
     *
     * @param maxImages the maxImages option
     * @return this Options instance.
     */
    public Options maxImages(Long maxImages) {
      this.maxImages = maxImages;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = WriteImageSummary.class
  )
  public static class Inputs extends RawOpInputs<WriteImageSummary> {
    /**
     * The writer input
     */
    public final Operand<? extends TType> writer;

    /**
     * The step input
     */
    public final Operand<TInt64> step;

    /**
     * The tag input
     */
    public final Operand<TString> tag;

    /**
     * The tensor input
     */
    public final Operand<? extends TNumber> tensor;

    /**
     * The badColor input
     */
    public final Operand<TUint8> badColor;

    /**
     * The maxImages attribute
     */
    public final long maxImages;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new WriteImageSummary(op), op, Arrays.asList("max_images", "T"));
      int inputIndex = 0;
      writer = (Operand<? extends TType>) op.input(inputIndex++);
      step = (Operand<TInt64>) op.input(inputIndex++);
      tag = (Operand<TString>) op.input(inputIndex++);
      tensor = (Operand<? extends TNumber>) op.input(inputIndex++);
      badColor = (Operand<TUint8>) op.input(inputIndex++);
      maxImages = op.attributes().getAttrInt("max_images");
      T = op.attributes().getAttrType("T");
    }
  }
}
