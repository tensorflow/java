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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * A transformation that asserts which transformations happen next.
 * This transformation checks whether the camel-case names (i.e. &quot;FlatMap&quot;, not
 * &quot;flat_map&quot;) of the transformations following this transformation match the list
 * of names in the {@code transformations} argument. If there is a mismatch, the
 * transformation raises an exception.
 * <p>The check occurs when iterating over the contents of the dataset, which
 * means that the check happens <em>after</em> any static optimizations are applied
 * to the dataset graph.
 */
public final class AssertNextDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AssertNextDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private AssertNextDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AssertNextDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * {@code data.AssertNextDataset} passes through the outputs of its input dataset.
   * @param transformations A {@code tf.string} vector {@code tf.Tensor} identifying the transformations that are
   * expected to happen next.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of AssertNextDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static AssertNextDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TString> transformations, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("AssertNextDataset", scope.makeOpName("AssertNextDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(transformations.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new AssertNextDataset(opBuilder.build());
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
