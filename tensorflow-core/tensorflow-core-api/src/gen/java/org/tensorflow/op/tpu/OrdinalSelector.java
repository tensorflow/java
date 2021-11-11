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
import org.tensorflow.types.TInt32;

/**
 * A TPU core selector Op.
 * This Op produces a set of TPU cores (for warm-up) or a single TPU core
 * (for regular inference) to execute the TPU program on. The output is
 * consumed by TPUPartitionedCall.
 */
@OpMetadata(
    opType = OrdinalSelector.OP_NAME,
    inputsClass = OrdinalSelector.Inputs.class
)
public final class OrdinalSelector extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUOrdinalSelector";

  private Output<TInt32> deviceOrdinals;

  public OrdinalSelector(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    deviceOrdinals = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TPUOrdinalSelector operation.
   *
   * @param scope current scope
   * @return a new instance of OrdinalSelector
   */
  @Endpoint(
      describeByClass = true
  )
  public static OrdinalSelector create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OrdinalSelector");
    return new OrdinalSelector(opBuilder.build());
  }

  /**
   * Gets deviceOrdinals.
   * A vector 1 or more TPU cores.
   * @return deviceOrdinals.
   */
  public Output<TInt32> deviceOrdinals() {
    return deviceOrdinals;
  }

  @Override
  public Output<TInt32> asOutput() {
    return deviceOrdinals;
  }

  @OpInputsMetadata(
      outputsClass = OrdinalSelector.class
  )
  public static class Inputs extends RawOpInputs<OrdinalSelector> {
    public Inputs(GraphOperation op) {
      super(new OrdinalSelector(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
