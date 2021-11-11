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

package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Sends {@code input} to all devices that are connected to the output.
 * Sends {@code input} to all devices that are connected to the output.
 * <p>The graph should be constructed so that all ops connected to the output have a
 * valid device assignment, and the op itself is assigned one of these devices.
 * <p>input: The input to the broadcast.
 * output: The same as input.
 * shape: The shape of the input tensor.
 *
 * @param <T> data type for {@code output} output
 *
 * @deprecated use {@link org.tensorflow.op.distribute.NcclBroadcast} instead
 */
@OpMetadata(
    opType = NcclBroadcast.OP_NAME,
    inputsClass = NcclBroadcast.Inputs.class
)
@Deprecated
public final class NcclBroadcast<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "NcclBroadcast";

  private Output<T> output;

  public NcclBroadcast(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new NcclBroadcast operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param shape The value of the shape attribute
   * @param <T> data type for {@code NcclBroadcast} output and operands
   * @return a new instance of NcclBroadcast
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> NcclBroadcast<T> create(Scope scope, Operand<T> input,
      Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "NcclBroadcast");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("shape", shape);
    return new NcclBroadcast<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = NcclBroadcast.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<NcclBroadcast<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The shape attribute
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new NcclBroadcast<>(op), op, Arrays.asList("T", "shape"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
