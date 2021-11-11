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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Joins the elements of {@code inputs} based on {@code segment_ids}.
 * Computes the string join along segments of a tensor.
 * Given {@code segment_ids} with rank {@code N} and {@code data} with rank {@code N+M}:
 * <pre>
 * `output[i, k1...kM] = strings.join([data[j1...jN, k1...kM])`
 * </pre>
 * <p>where the join is over all [j1...jN] such that segment_ids[j1...jN] = i.
 * Strings are joined in row-major order.
 * <p>For example:
 * <pre>
 * inputs = [['Y', 'q', 'c'], ['Y', '6', '6'], ['p', 'G', 'a']]
 * output_array = string_ops.unsorted_segment_join(inputs=inputs,
 *                                                 segment_ids=[1, 0, 1],
 *                                                 num_segments=2,
 *                                                 separator=':'))
 * # output_array ==&gt; [['Y', '6', '6'], ['Y:p', 'q:G', 'c:a']]
 *
 *
 * inputs = ['this', 'is', 'a', 'test']
 * output_array = string_ops.unsorted_segment_join(inputs=inputs,
 *                                                 segment_ids=[0, 0, 0, 0],
 *                                                 num_segments=1,
 *                                                 separator=':'))
 * # output_array ==&gt; ['this:is:a:test']
 * </pre>
 */
@OpMetadata(
    opType = UnsortedSegmentJoin.OP_NAME,
    inputsClass = UnsortedSegmentJoin.Inputs.class
)
@Operator(
    group = "strings"
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
   * @param inputs The input to be joined.
   * @param segmentIds A tensor whose shape is a prefix of data.shape.  Negative segment ids are not
   * supported.
   * @param numSegments A scalar.
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
   * @param separator The separator to use when joining.
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
     * @param separator The separator to use when joining.
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
     * The input to be joined.
     */
    public final Operand<TString> inputs;

    /**
     * A tensor whose shape is a prefix of data.shape.  Negative segment ids are not
     * supported.
     */
    public final Operand<? extends TNumber> segmentIds;

    /**
     * A scalar.
     */
    public final Operand<? extends TNumber> numSegments;

    /**
     * The separator to use when joining.
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
