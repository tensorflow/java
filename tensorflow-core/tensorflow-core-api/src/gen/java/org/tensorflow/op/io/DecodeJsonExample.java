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

package org.tensorflow.op.io;

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
import org.tensorflow.types.TString;

/**
 * Convert JSON-encoded Example records to binary protocol buffer strings.
 * Note: This is <strong>not</strong> a general purpose JSON parsing op.
 * <p>This op converts JSON-serialized
 * {@code tf.train.Example} (created with {@code json_format.MessageToJson}, following the
 *  <a href="https://developers.google.com/protocol-buffers/docs/proto3#json">standard JSON mapping</a> )
 * to a binary-serialized {@code tf.train.Example} (equivalent to
 * {@code Example.SerializeToString()}) suitable for conversion to tensors with
 * {@code tf.io.parse_example}.
 */
@OpMetadata(
    opType = DecodeJsonExample.OP_NAME,
    inputsClass = DecodeJsonExample.Inputs.class
)
@Operator(
    group = "io"
)
public final class DecodeJsonExample extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeJSONExample";

  private Output<TString> binaryExamples;

  public DecodeJsonExample(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    binaryExamples = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodeJSONExample operation.
   *
   * @param scope current scope
   * @param jsonExamples Each string is a JSON object serialized according to the JSON
   * mapping of the Example proto.
   * @return a new instance of DecodeJsonExample
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeJsonExample create(Scope scope, Operand<TString> jsonExamples) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeJsonExample");
    opBuilder.addInput(jsonExamples.asOutput());
    return new DecodeJsonExample(opBuilder.build());
  }

  /**
   * Gets binaryExamples.
   * Each string is a binary Example protocol buffer corresponding
   * to the respective element of {@code json_examples}.
   * @return binaryExamples.
   */
  public Output<TString> binaryExamples() {
    return binaryExamples;
  }

  @Override
  public Output<TString> asOutput() {
    return binaryExamples;
  }

  @OpInputsMetadata(
      outputsClass = DecodeJsonExample.class
  )
  public static class Inputs extends RawOpInputs<DecodeJsonExample> {
    /**
     * Each string is a JSON object serialized according to the JSON
     * mapping of the Example proto.
     */
    public final Operand<TString> jsonExamples;

    public Inputs(GraphOperation op) {
      super(new DecodeJsonExample(op), op, Arrays.asList());
      int inputIndex = 0;
      jsonExamples = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
