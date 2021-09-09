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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The ParallelBatchDataset operation
 */
@Operator(
    group = "data"
)
public final class ParallelBatchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParallelBatchDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private ParallelBatchDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParallelBatchDataset operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param batchSize the batchSize value
   * @param numParallelCalls the numParallelCalls value
   * @param dropRemainder the dropRemainder value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @param options carries optional attribute values
   * @return a new instance of ParallelBatchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParallelBatchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Operand<TInt64> numParallelCalls, Operand<TBool> dropRemainder,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParallelBatchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInput(numParallelCalls.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.deterministic != null) {
          opBuilder.setAttr("deterministic", opts.deterministic);
        }
      }
    }
    return new ParallelBatchDataset(opBuilder.build());
  }

  /**
   * Sets the deterministic option.
   *
   * @param deterministic the deterministic option
   * @return this Options instance.
   */
  public static Options deterministic(String deterministic) {
    return new Options().deterministic(deterministic);
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
   * Optional attributes for {@link org.tensorflow.op.data.ParallelBatchDataset}
   */
  public static class Options {
    private String deterministic;

    private Options() {
    }

    /**
     * Sets the deterministic option.
     *
     * @param deterministic the deterministic option
     * @return this Options instance.
     */
    public Options deterministic(String deterministic) {
      this.deterministic = deterministic;
      return this;
    }
  }
}
