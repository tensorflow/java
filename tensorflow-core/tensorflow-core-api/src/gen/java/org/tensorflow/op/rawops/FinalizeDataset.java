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

package org.tensorflow.op.rawops;

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
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset by applying {@code tf.data.Options} to {@code input_dataset}.
 */
public final class FinalizeDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FinalizeDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private FinalizeDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FinalizeDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @param options carries optional attribute values
   * @return a new instance of FinalizeDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static FinalizeDataset create(Scope scope, Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("FinalizeDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.hasCapturedRef != null) {
          opBuilder.setAttr("has_captured_ref", opts.hasCapturedRef);
        }
      }
    }
    return new FinalizeDataset(opBuilder.build());
  }

  /**
   * Sets the hasCapturedRef option.
   *
   * @param hasCapturedRef the hasCapturedRef option
   * @return this Options instance.
   */
  public static Options hasCapturedRef(Boolean hasCapturedRef) {
    return new Options().hasCapturedRef(hasCapturedRef);
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
   * Optional attributes for {@link org.tensorflow.op.rawops.FinalizeDataset}
   */
  public static class Options {
    private Boolean hasCapturedRef;

    private Options() {
    }

    /**
     * Sets the hasCapturedRef option.
     *
     * @param hasCapturedRef the hasCapturedRef option
     * @return this Options instance.
     */
    public Options hasCapturedRef(Boolean hasCapturedRef) {
      this.hasCapturedRef = hasCapturedRef;
      return this;
    }
  }
}
