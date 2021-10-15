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

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that batches {@code batch_size} elements from {@code input_dataset}.
 */
@Operator(
    group = "data"
)
public final class BatchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private BatchDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param batchSize A scalar representing the number of elements to accumulate in a batch.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   * is smaller than desired.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of BatchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static BatchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Operand<TBool> dropRemainder,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.parallelCopy != null) {
          opBuilder.setAttr("parallel_copy", opts.parallelCopy);
        }
      }
    }
    return new BatchDataset(opBuilder.build());
  }

  /**
   * Sets the parallelCopy option.
   *
   * @param parallelCopy the parallelCopy option
   * @return this Options instance.
   */
  public static Options parallelCopy(Boolean parallelCopy) {
    return new Options().parallelCopy(parallelCopy);
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
   * Optional attributes for {@link org.tensorflow.op.data.BatchDataset}
   */
  public static class Options {
    private Boolean parallelCopy;

    private Options() {
    }

    /**
     * Sets the parallelCopy option.
     *
     * @param parallelCopy the parallelCopy option
     * @return this Options instance.
     */
    public Options parallelCopy(Boolean parallelCopy) {
      this.parallelCopy = parallelCopy;
      return this;
    }
  }

  public static class Inputs extends RawOpInputs<BatchDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A scalar representing the number of elements to accumulate in a batch.
     */
    public final Operand<TInt64> batchSize;

    /**
     * A scalar representing whether the last batch should be dropped in case its size
     * is smaller than desired.
     */
    public final Operand<TBool> dropRemainder;

    /**
     * The parallelCopy attribute
     */
    public final boolean parallelCopy;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new BatchDataset(op), op, Arrays.asList("parallel_copy", "output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      batchSize = (Operand<TInt64>) op.input(inputIndex++);
      dropRemainder = (Operand<TBool>) op.input(inputIndex++);
      parallelCopy = op.attributes().getAttrBool("parallel_copy");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
