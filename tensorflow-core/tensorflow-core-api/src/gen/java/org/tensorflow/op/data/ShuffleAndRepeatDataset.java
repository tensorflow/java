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

package org.tensorflow.op.data;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that shuffles and repeats elements from `input_dataset`
 * <p>
 * pseudorandomly.
 */
public final class ShuffleAndRepeatDataset extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new ShuffleAndRepeatDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param bufferSize The number of output elements to buffer in an iterator over
   * this dataset. Compare with the `min_after_dequeue` attr when creating a
   * `RandomShuffleQueue`.
   * @param seed A scalar seed for the random number generator. If either `seed` or
   * `seed2` is set to be non-zero, the random number generator is seeded
   * by the given seed.  Otherwise, a random seed is used.
   * @param seed2 A second scalar seed to avoid seed collision.
   * @param count A scalar representing the number of times the underlying dataset
   * should be repeated. The default is `-1`, which results in infinite repetition.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of ShuffleAndRepeatDataset
   */
  @Endpoint(describeByClass = true)
  public static ShuffleAndRepeatDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> bufferSize, Operand<TInt64> seed, Operand<TInt64> seed2, Operand<TInt64> count, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("ShuffleAndRepeatDataset", scope.makeOpName("ShuffleAndRepeatDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder.addInput(count.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    DataType[] outputTypesArray = new DataType[outputTypes.size()];
    for (int i = 0; i < outputTypesArray.length; ++i) {
      outputTypesArray[i] = outputTypes.get(i);
    }
    opBuilder.setAttr("output_types", outputTypesArray);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new ShuffleAndRepeatDataset(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  private Output<?> handle;
  
  private ShuffleAndRepeatDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
