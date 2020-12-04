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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Bucketizes 'input' based on 'boundaries'.
 * <p>
 * For example, if the inputs are
 *     boundaries = [0, 10, 100]
 *     input = [[-5, 10000]
 *              [150,   10]
 *              [5,    100]]
 * <p>
 * then the output will be
 *     output = [[0, 3]
 *               [3, 2]
 *               [1, 3]]
 */
@Operator
public final class Bucketize extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new Bucketize operation.
   * 
   * @param scope current scope
   * @param input Any shape of Tensor contains with int or float type.
   * @param boundaries A sorted list of floats gives the boundary of the buckets.
   * @return a new instance of Bucketize
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Bucketize create(Scope scope, Operand<T> input, List<Float> boundaries) {
    OperationBuilder opBuilder = scope.env().opBuilder("Bucketize", scope.makeOpName("Bucketize"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    float[] boundariesArray = new float[boundaries.size()];
    for (int i = 0; i < boundariesArray.length; ++i) {
      boundariesArray[i] = boundaries.get(i);
    }
    opBuilder.setAttr("boundaries", boundariesArray);
    return new Bucketize(opBuilder.build());
  }
  
  /**
   * Same shape with 'input', each value of input replaced with bucket index.
   * <p>
   * @compatibility(numpy)
   * Equivalent to np.digitize.
   * @end_compatibility
   */
  public Output<TInt32> output() {
    return output;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Bucketize";
  
  private Output<TInt32> output;
  
  private Bucketize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
