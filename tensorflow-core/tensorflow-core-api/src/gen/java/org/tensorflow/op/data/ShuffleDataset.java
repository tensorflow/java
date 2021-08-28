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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The ShuffleDatasetV3 operation
 */
public final class ShuffleDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ShuffleDatasetV3";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private ShuffleDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ShuffleDatasetV3 operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param bufferSize the bufferSize value
   * @param seed the seed value
   * @param seed2 the seed2 value
   * @param seedGenerator the seedGenerator value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @param options carries optional attribute values
   * @return a new instance of ShuffleDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ShuffleDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> bufferSize, Operand<TInt64> seed, Operand<TInt64> seed2,
      Operand<? extends TType> seedGenerator, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ShuffleDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder.addInput(seedGenerator.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.reshuffleEachIteration != null) {
          opBuilder.setAttr("reshuffle_each_iteration", opts.reshuffleEachIteration);
        }
      }
    }
    return new ShuffleDataset(opBuilder.build());
  }

  /**
   * Sets the reshuffleEachIteration option.
   *
   * @param reshuffleEachIteration the reshuffleEachIteration option
   * @return this Options instance.
   */
  public static Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
    return new Options().reshuffleEachIteration(reshuffleEachIteration);
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

  /**
   * Optional attributes for {@link org.tensorflow.op.data.ShuffleDataset}
   */
  public static class Options {
    private Boolean reshuffleEachIteration;

    private Options() {
    }

    /**
     * Sets the reshuffleEachIteration option.
     *
     * @param reshuffleEachIteration the reshuffleEachIteration option
     * @return this Options instance.
     */
    public Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
      this.reshuffleEachIteration = reshuffleEachIteration;
      return this;
    }
  }
}
