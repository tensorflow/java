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

package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * This op is used as a placeholder in If branch functions. It doesn't provide a
 * valid output when run, so must either be removed (e.g. replaced with a
 * function input) or guaranteed not to be used (e.g. if mirroring an
 * intermediate output needed for the gradient computation of the other branch).
 */
@OpMetadata(
    opType = FakeParam.OP_NAME,
    inputsClass = FakeParam.Inputs.class
)
@Operator
public final class FakeParam<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FakeParam";

  private Output<T> output;

  public FakeParam(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FakeParam operation.
   *
   * @param scope current scope
   * @param dtype The type of the output.
   * @param shape <pre>
   * The purported shape of the output. This is only used for shape inference;
   * the output will not necessarily have this shape. Can be a partial shape.
   * </pre>
   * @param <T> data type for {@code FakeParam} output and operands
   * @return a new instance of FakeParam
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> FakeParam<T> create(Scope scope, Class<T> dtype, Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FakeParam");
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("shape", shape);
    return new FakeParam<>(opBuilder.build());
  }

  /**
   * Gets output.
   * <pre>
   * \&quot;Fake\&quot; output value. This should not be consumed by another op.
   * </pre>
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
      outputsClass = FakeParam.class
  )
  public static class Inputs extends RawOpInputs<FakeParam<?>> {
    /**
     * The type of the output.
     */
    public final DataType dtype;

    /**
     * <pre>
     * The purported shape of the output. This is only used for shape inference;
     * the output will not necessarily have this shape. Can be a partial shape.
     * </pre>
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new FakeParam<>(op), op, Arrays.asList("dtype", "shape"));
      int inputIndex = 0;
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
