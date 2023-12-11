/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.strings;

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * The UnsortedSegmentJoin operation
 */
@OpMetadata(
    opType = UnsortedSegmentJoin.OP_NAME,
    inputsClass = UnsortedSegmentJoin.Inputs.class
)
public final class UnsortedSegmentJoin extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UnsortedSegmentJoin";

  private Output<TString> output;

  public UnsortedSegmentJoin(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UnsortedSegmentJoin operation.
   *
   * @param scope current scope
   * @param inputs The inputs value
   * @param segmentIds The segmentIds value
   * @param numSegments The numSegments value
   * @param options carries optional attribute values
   * @return a new instance of UnsortedSegmentJoin
   */
  @Endpoint(
      describeByClass = true
  )
  public static UnsortedSegmentJoin create(Scope scope, Operand<TString> inputs,
      Operand<? extends TNumber> segmentIds, Operand<? extends TNumber> numSegments,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UnsortedSegmentJoin");
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(numSegments.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.separator != null) {
          opBuilder.setAttr("separator", opts.separator);
        }
      }
    }
    return new UnsortedSegmentJoin(opBuilder.build());
  }

  /**
   * Sets the separator option.
   *
   * @param separator the separator option
   * @return this Options instance.
   */
  public static Options separator(String separator) {
    return new Options().separator(separator);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TString> output() {
    return output;
  }

  @Override
  public Output<TString> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.strings.UnsortedSegmentJoin}
   */
  public static class Options {
    private String separator;

    private Options() {
    }

    /**
     * Sets the separator option.
     *
     * @param separator the separator option
     * @return this Options instance.
     */
    public Options separator(String separator) {
      this.separator = separator;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UnsortedSegmentJoin.class
  )
  public static class Inputs extends RawOpInputs<UnsortedSegmentJoin> {
    /**
     * The inputs input
     */
    public final Operand<TString> inputs;

    /**
     * The segmentIds input
     */
    public final Operand<? extends TNumber> segmentIds;

    /**
     * The numSegments input
     */
    public final Operand<? extends TNumber> numSegments;

    /**
     * The separator attribute
     */
    public final String separator;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * The Tnumsegments attribute
     */
    public final DataType Tnumsegments;

    public Inputs(GraphOperation op) {
      super(new UnsortedSegmentJoin(op), op, Arrays.asList("separator", "Tindices", "Tnumsegments"));
      int inputIndex = 0;
      inputs = (Operand<TString>) op.input(inputIndex++);
      segmentIds = (Operand<? extends TNumber>) op.input(inputIndex++);
      numSegments = (Operand<? extends TNumber>) op.input(inputIndex++);
      separator = op.attributes().getAttrString("separator");
      Tindices = op.attributes().getAttrType("Tindices");
      Tnumsegments = op.attributes().getAttrType("Tnumsegments");
    }
  }
}
