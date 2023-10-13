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

package org.tensorflow.op.tpu;

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

/**
 * Converts XRT's uid handles to TensorFlow-friendly input format.
 * Converts a uid handle for a compiled program into a vector of proto keys.
 * <p>XRT compile ops return uids, and the TensorFlow execute op takes a proto
 * key. This op enables a client to compile on TPU using XRT and execute using the
 * standard TensorFlow execute op.
 * <p>'uid' is the input handle.
 * 'proto_keys' is a vector of proto keys, one for each core program.
 */
@OpMetadata(
    opType = TpuHandleToProtoKey.OP_NAME,
    inputsClass = TpuHandleToProtoKey.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class TpuHandleToProtoKey extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TpuHandleToProtoKey";

  private Output<TString> protoKeys;

  public TpuHandleToProtoKey(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    protoKeys = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TpuHandleToProtoKey operation.
   *
   * @param scope current scope
   * @param uid The uid value
   * @return a new instance of TpuHandleToProtoKey
   */
  @Endpoint(
      describeByClass = true
  )
  public static TpuHandleToProtoKey create(Scope scope, Operand<TInt64> uid) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TpuHandleToProtoKey");
    opBuilder.addInput(uid.asOutput());
    return new TpuHandleToProtoKey(opBuilder.build());
  }

  /**
   * Gets protoKeys.
   *
   * @return protoKeys.
   */
  public Output<TString> protoKeys() {
    return protoKeys;
  }

  @Override
  public Output<TString> asOutput() {
    return protoKeys;
  }

  @OpInputsMetadata(
      outputsClass = TpuHandleToProtoKey.class
  )
  public static class Inputs extends RawOpInputs<TpuHandleToProtoKey> {
    /**
     * The uid input
     */
    public final Operand<TInt64> uid;

    public Inputs(GraphOperation op) {
      super(new TpuHandleToProtoKey(op), op, Arrays.asList());
      int inputIndex = 0;
      uid = (Operand<TInt64>) op.input(inputIndex++);
    }
  }
}
