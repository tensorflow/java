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

package org.tensorflow.op.train;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Returns the gradient of `Tile`.
 * <p>
 * Since `Tile` takes an input and repeats the input `multiples` times
 * along each dimension, `train.TileGrad` takes in `multiples` and aggregates
 * each repeated tile of `input` into `output`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "train")
public final class TileGrad<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TileGrad operation.
   * 
   * @param scope current scope
   * @param input 
   * @param multiples 
   * @return a new instance of TileGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TileGrad<T> create(Scope scope, Operand<T> input, Operand<TInt32> multiples) {
    OperationBuilder opBuilder = scope.env().opBuilder("TileGrad", scope.makeOpName("TileGrad"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(multiples.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TileGrad<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TileGrad";
  
  private Output<T> output;
  
  private TileGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
