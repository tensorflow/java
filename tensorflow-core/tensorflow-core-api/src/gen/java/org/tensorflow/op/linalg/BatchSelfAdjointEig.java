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

package org.tensorflow.op.linalg;

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
import org.tensorflow.types.family.TNumber;

/**
 * The BatchSelfAdjointEigV2 operation
 *
 * @param <T> data type for {@code e} output
 */
@OpMetadata(
    opType = BatchSelfAdjointEig.OP_NAME,
    inputsClass = BatchSelfAdjointEig.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class BatchSelfAdjointEig<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchSelfAdjointEigV2";

  private Output<T> e;

  private Output<T> v;

  public BatchSelfAdjointEig(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    e = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchSelfAdjointEigV2 operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchSelfAdjointEigV2} output and operands
   * @return a new instance of BatchSelfAdjointEig
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> BatchSelfAdjointEig<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchSelfAdjointEig");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.computeV != null) {
          opBuilder.setAttr("compute_v", opts.computeV);
        }
      }
    }
    return new BatchSelfAdjointEig<>(opBuilder.build());
  }

  /**
   * Sets the computeV option.
   *
   * @param computeV the computeV option
   * @return this Options instance.
   */
  public static Options computeV(Boolean computeV) {
    return new Options().computeV(computeV);
  }

  /**
   * Gets e.
   *
   * @return e.
   */
  public Output<T> e() {
    return e;
  }

  /**
   * Gets v.
   *
   * @return v.
   */
  public Output<T> v() {
    return v;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.BatchSelfAdjointEig}
   */
  public static class Options {
    private Boolean computeV;

    private Options() {
    }

    /**
     * Sets the computeV option.
     *
     * @param computeV the computeV option
     * @return this Options instance.
     */
    public Options computeV(Boolean computeV) {
      this.computeV = computeV;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BatchSelfAdjointEig.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<BatchSelfAdjointEig<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The computeV attribute
     */
    public final boolean computeV;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new BatchSelfAdjointEig<>(op), op, Arrays.asList("compute_v", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      computeV = op.attributes().getAttrBool("compute_v");
      T = op.attributes().getAttrType("T");
    }
  }
}
