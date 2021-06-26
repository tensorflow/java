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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that takes a Bernoulli sample of the contents of another dataset.
 * There is no transformation in the {@code tf.data} Python API for creating this dataset.
 * Instead, it is created as a result of the {@code filter_with_random_uniform_fusion}
 * static optimization. Whether this optimization is performed is determined by the
 * {@code experimental_optimization.filter_with_random_uniform_fusion} option of
 * {@code tf.data.Options}.
 */
@Operator(
    group = "data"
)
public final class SamplingDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SamplingDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private SamplingDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SamplingDataset operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param rate A scalar representing the sample rate. Each element of {@code input_dataset} is
   * retained with this probability, independent of all other elements.
   * @param seed A scalar representing seed of random number generator.
   * @param seed2 A scalar representing seed2 of random number generator.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of SamplingDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static SamplingDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TFloat32> rate, Operand<TInt64> seed, Operand<TInt64> seed2,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("SamplingDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(rate.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new SamplingDataset(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
}
