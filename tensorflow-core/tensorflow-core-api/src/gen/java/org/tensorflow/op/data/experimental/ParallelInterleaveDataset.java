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

package org.tensorflow.op.data.experimental;

import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
 * The resulting dataset is similar to the {@code InterleaveDataset}, with the exception
 * that if retrieving the next value from a dataset would cause the requester to
 * block, it will skip that input dataset. This dataset is especially useful
 * when loading data from a variable-latency datastores (e.g. HDFS, GCS), as it
 * allows the training step to proceed so long as some data is available.
 * <p>!! WARNING !! This dataset is not deterministic!
 */
public final class ParallelInterleaveDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalParallelInterleaveDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private ParallelInterleaveDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalParallelInterleaveDataset operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param otherArguments the otherArguments value
   * @param cycleLength the cycleLength value
   * @param blockLength the blockLength value
   * @param sloppy the sloppy value
   * @param bufferOutputElements the bufferOutputElements value
   * @param prefetchInputElements the prefetchInputElements value
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   * {@code other_arguments}, to a Dataset variant that contains elements matching
   * {@code output_types} and {@code output_shapes}.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of ParallelInterleaveDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ParallelInterleaveDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> cycleLength, Operand<TInt64> blockLength,
      Operand<TBool> sloppy, Operand<TInt64> bufferOutputElements,
      Operand<TInt64> prefetchInputElements, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("ParallelInterleaveDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.addInput(cycleLength.asOutput());
    opBuilder.addInput(blockLength.asOutput());
    opBuilder.addInput(sloppy.asOutput());
    opBuilder.addInput(bufferOutputElements.asOutput());
    opBuilder.addInput(prefetchInputElements.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new ParallelInterleaveDataset(opBuilder.build());
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
