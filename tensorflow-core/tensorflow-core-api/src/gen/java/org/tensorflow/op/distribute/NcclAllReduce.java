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

package org.tensorflow.op.distribute;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs a tensor containing the reduction across all input tensors.
 * Outputs a tensor containing the reduction across all input tensors passed to ops
 * within the same `shared_name.
 * <p>The graph should be constructed so if one op runs with shared_name value {@code c},
 * then {@code num_devices} ops will run with shared_name value {@code c}.  Failure to do so
 * will cause the graph execution to fail to complete.
 * <p>input: the input to the reduction
 * data: the value of the reduction across all {@code num_devices} devices.
 * reduction: the reduction operation to perform.
 * num_devices: The number of devices participating in this reduction.
 * shared_name: Identifier that shared between ops of the same reduction.
 *
 * @param <T> data type for {@code data} output
 */
@OpMetadata(
    opType = NcclAllReduce.OP_NAME,
    inputsClass = NcclAllReduce.Inputs.class
)
public final class NcclAllReduce<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "NcclAllReduce";

  private Output<T> data;

  public NcclAllReduce(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    data = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new NcclAllReduce operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param reduction The value of the reduction attribute
   * @param numDevices The value of the numDevices attribute
   * @param sharedName The value of the sharedName attribute
   * @param <T> data type for {@code NcclAllReduce} output and operands
   * @return a new instance of NcclAllReduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> NcclAllReduce<T> create(Scope scope, Operand<T> input,
      String reduction, Long numDevices, String sharedName) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "NcclAllReduce");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("reduction", reduction);
    opBuilder.setAttr("num_devices", numDevices);
    opBuilder.setAttr("shared_name", sharedName);
    return new NcclAllReduce<>(opBuilder.build());
  }

  /**
   * Gets data.
   *
   * @return data.
   */
  public Output<T> data() {
    return data;
  }

  @Override
  public Output<T> asOutput() {
    return data;
  }

  @OpInputsMetadata(
      outputsClass = NcclAllReduce.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<NcclAllReduce<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The reduction attribute
     */
    public final String reduction;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The numDevices attribute
     */
    public final long numDevices;

    /**
     * The sharedName attribute
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new NcclAllReduce<>(op), op, Arrays.asList("reduction", "T", "num_devices", "shared_name"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      reduction = op.attributes().getAttrString("reduction");
      T = op.attributes().getAttrType("T");
      numDevices = op.attributes().getAttrInt("num_devices");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
