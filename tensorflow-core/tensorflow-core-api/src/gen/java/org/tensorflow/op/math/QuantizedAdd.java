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

package org.tensorflow.op.math;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Returns x + y element-wise, working on quantized buffers.
 *
 * @param <V> data type for {@code z} output
 */
@OpMetadata(
    opType = QuantizedAdd.OP_NAME,
    inputsClass = QuantizedAdd.Inputs.class
)
@Operator(
    group = "math"
)
public final class QuantizedAdd<V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedAdd";

  private Output<V> z;

  private Output<TFloat32> minZ;

  private Output<TFloat32> maxZ;

  public QuantizedAdd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
    minZ = operation.output(outputIdx++);
    maxZ = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedAdd operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @param minX The float value that the lowest quantized {@code x} value represents.
   * @param maxX The float value that the highest quantized {@code x} value represents.
   * @param minY The float value that the lowest quantized {@code y} value represents.
   * @param maxY The float value that the highest quantized {@code y} value represents.
   * @param Toutput The value of the Toutput attribute
   * @param <V> data type for {@code QuantizedAdd} output and operands
   * @return a new instance of QuantizedAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> QuantizedAdd<V> create(Scope scope,
      Operand<? extends TNumber> x, Operand<? extends TNumber> y, Operand<TFloat32> minX,
      Operand<TFloat32> maxX, Operand<TFloat32> minY, Operand<TFloat32> maxY, Class<V> Toutput) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedAdd");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder.addInput(minX.asOutput());
    opBuilder.addInput(maxX.asOutput());
    opBuilder.addInput(minY.asOutput());
    opBuilder.addInput(maxY.asOutput());
    opBuilder.setAttr("Toutput", Operands.toDataType(Toutput));
    return new QuantizedAdd<>(opBuilder.build());
  }

  /**
   * Gets z.
   *
   * @return z.
   */
  public Output<V> z() {
    return z;
  }

  /**
   * Gets minZ.
   * The float value that the lowest quantized output value represents.
   * @return minZ.
   */
  public Output<TFloat32> minZ() {
    return minZ;
  }

  /**
   * Gets maxZ.
   * The float value that the highest quantized output value represents.
   * <p><em>NOTE</em>: {@code math.QuantizedAdd} supports limited forms of broadcasting. More about
   * broadcasting  <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a>
   * @return maxZ.
   */
  public Output<TFloat32> maxZ() {
    return maxZ;
  }

  @OpInputsMetadata(
      outputsClass = QuantizedAdd.class
  )
  public static class Inputs extends RawOpInputs<QuantizedAdd<?>> {
    /**
     * The x input
     */
    public final Operand<? extends TNumber> x;

    /**
     * The y input
     */
    public final Operand<? extends TNumber> y;

    /**
     * The float value that the lowest quantized {@code x} value represents.
     */
    public final Operand<TFloat32> minX;

    /**
     * The float value that the highest quantized {@code x} value represents.
     */
    public final Operand<TFloat32> maxX;

    /**
     * The float value that the lowest quantized {@code y} value represents.
     */
    public final Operand<TFloat32> minY;

    /**
     * The float value that the highest quantized {@code y} value represents.
     */
    public final Operand<TFloat32> maxY;

    /**
     * The T1 attribute
     */
    public final DataType T1;

    /**
     * The T2 attribute
     */
    public final DataType T2;

    /**
     * The Toutput attribute
     */
    public final DataType Toutput;

    public Inputs(GraphOperation op) {
      super(new QuantizedAdd<>(op), op, Arrays.asList("T1", "T2", "Toutput"));
      int inputIndex = 0;
      x = (Operand<? extends TNumber>) op.input(inputIndex++);
      y = (Operand<? extends TNumber>) op.input(inputIndex++);
      minX = (Operand<TFloat32>) op.input(inputIndex++);
      maxX = (Operand<TFloat32>) op.input(inputIndex++);
      minY = (Operand<TFloat32>) op.input(inputIndex++);
      maxY = (Operand<TFloat32>) op.input(inputIndex++);
      T1 = op.attributes().getAttrType("T1");
      T2 = op.attributes().getAttrType("T2");
      Toutput = op.attributes().getAttrType("Toutput");
    }
  }
}
