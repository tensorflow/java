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
 * Creates a dataset that changes the batch size.
 * Creates a dataset that changes the batch size of the dataset to current batch
 * size // num_workers.
 */
public final class RebatchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RebatchDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private RebatchDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RebatchDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param numReplicas A scalar representing the number of replicas to distribute this batch across. As
   * a result of this transformation the current batch size would end up being
   * divided  by this parameter.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @param options carries optional attribute values
   * @return a new instance of RebatchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static RebatchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> numReplicas, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RebatchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(numReplicas.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useFallback != null) {
          opBuilder.setAttr("use_fallback", opts.useFallback);
        }
      }
    }
    return new RebatchDataset(opBuilder.build());
  }

  /**
   * Sets the useFallback option.
   *
   * @param useFallback the useFallback option
   * @return this Options instance.
   */
  public static Options useFallback(Boolean useFallback) {
    return new Options().useFallback(useFallback);
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
   * Optional attributes for {@link org.tensorflow.op.data.RebatchDataset}
   */
  public static class Options {
    private Boolean useFallback;

    private Options() {
    }

    /**
     * Sets the useFallback option.
     *
     * @param useFallback the useFallback option
     * @return this Options instance.
     */
    public Options useFallback(Boolean useFallback) {
      this.useFallback = useFallback;
      return this;
    }
  }
}
