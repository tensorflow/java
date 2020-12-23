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
import org.tensorflow.types.family.TType;

/**
 * Returns x + y element-wise, working on quantized buffers.
 * 
 * @param <V> data type for {@code z()} output
 */
@Operator(group = "math")
public final class QuantizedAdd<V extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new QuantizedAdd operation.
   * 
   * @param scope current scope
   * @param x 
   * @param y 
   * @param minX The float value that the lowest quantized `x` value represents.
   * @param maxX The float value that the highest quantized `x` value represents.
   * @param minY The float value that the lowest quantized `y` value represents.
   * @param maxY The float value that the highest quantized `y` value represents.
   * @param Toutput 
   * @return a new instance of QuantizedAdd
   */
  @Endpoint(describeByClass = true)
  public static <V extends TType, T extends TType, U extends TType> QuantizedAdd<V> create(Scope scope, Operand<T> x, Operand<U> y, Operand<TFloat32> minX, Operand<TFloat32> maxX, Operand<TFloat32> minY, Operand<TFloat32> maxY, Class<V> Toutput) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedAdd", scope.makeOpName("QuantizedAdd"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder.addInput(minX.asOutput());
    opBuilder.addInput(maxX.asOutput());
    opBuilder.addInput(minY.asOutput());
    opBuilder.addInput(maxY.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Toutput", Operands.toDataType(Toutput));
    return new QuantizedAdd<V>(opBuilder.build());
  }
  
  /**
   */
  public Output<V> z() {
    return z;
  }
  
  /**
   * The float value that the lowest quantized output value represents.
   */
  public Output<TFloat32> minZ() {
    return minZ;
  }
  
  /**
   * The float value that the highest quantized output value represents.
   * <p>
   * <i>NOTE</i>: `math.QuantizedAdd` supports limited forms of broadcasting. More about
   * broadcasting [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   */
  public Output<TFloat32> maxZ() {
    return maxZ;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedAdd";
  
  private Output<V> z;
  private Output<TFloat32> minZ;
  private Output<TFloat32> maxZ;
  
  private QuantizedAdd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
    minZ = operation.output(outputIdx++);
    maxZ = operation.output(outputIdx++);
  }
}
