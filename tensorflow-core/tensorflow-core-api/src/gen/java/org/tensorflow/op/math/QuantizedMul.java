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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Returns x * y element-wise, working on quantized buffers.
 *
 * @param <V> data type for {@code z} output
 */
@Operator(
    group = "math"
)
public final class QuantizedMul<V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedMul";

  private Output<V> z;

  private Output<TFloat32> minZ;

  private Output<TFloat32> maxZ;

  private QuantizedMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
    minZ = operation.output(outputIdx++);
    maxZ = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedMul operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param y the y value
   * @param minX The float value that the lowest quantized {@code x} value represents.
   * @param maxX The float value that the highest quantized {@code x} value represents.
   * @param minY The float value that the lowest quantized {@code y} value represents.
   * @param maxY The float value that the highest quantized {@code y} value represents.
   * @param Toutput the value of the Toutput property
   * @param <V> data type for {@code QuantizedMul} output and operands
   * @return a new instance of QuantizedMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> QuantizedMul<V> create(Scope scope,
      Operand<? extends TNumber> x, Operand<? extends TNumber> y, Operand<TFloat32> minX,
      Operand<TFloat32> maxX, Operand<TFloat32> minY, Operand<TFloat32> maxY, Class<V> Toutput) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedMul", scope.makeOpName("QuantizedMul"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder.addInput(minX.asOutput());
    opBuilder.addInput(maxX.asOutput());
    opBuilder.addInput(minY.asOutput());
    opBuilder.addInput(maxY.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Toutput", Operands.toDataType(Toutput));
    return new QuantizedMul<>(opBuilder.build());
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
   * <p><em>NOTE</em>: {@code math.QuantizedMul} supports limited forms of broadcasting. More about
   * broadcasting  <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a>
   * @return maxZ.
   */
  public Output<TFloat32> maxZ() {
    return maxZ;
  }
}
