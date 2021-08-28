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

package org.tensorflow.op.core;

import java.util.Arrays;
import java.util.Iterator;
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
import org.tensorflow.types.family.TType;

/**
 * Reduces the input dataset to a singleton using a reduce function.
 */
public final class ReduceDataset extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReduceDataset";

  private List<Output<?>> components;

  @SuppressWarnings("unchecked")
  private ReduceDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int componentsLength = operation.outputListLength("components");
    components = Arrays.asList(operation.outputList(outputIdx, componentsLength));
    outputIdx += componentsLength;
  }

  /**
   * Factory method to create a class wrapping a new ReduceDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param initialState A nested structure of tensors, representing the initial state of the
   * transformation.
   * @param otherArguments the otherArguments value
   * @param f A function that maps {@code (old_state, input_element)} to {@code new_state}. It must take
   * two arguments and return a nested structures of tensors. The structure of
   * {@code new_state} must match the structure of {@code initial_state}.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @param options carries optional attribute values
   * @return a new instance of ReduceDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReduceDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> initialState, Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReduceDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInputList(Operands.asOutputs(initialState));
    opBuilder.addInputList(Operands.asOutputs(otherArguments));
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useInterOpParallelism != null) {
          opBuilder.setAttr("use_inter_op_parallelism", opts.useInterOpParallelism);
        }
      }
    }
    return new ReduceDataset(opBuilder.build());
  }

  /**
   * Sets the useInterOpParallelism option.
   *
   * @param useInterOpParallelism the useInterOpParallelism option
   * @return this Options instance.
   */
  public static Options useInterOpParallelism(Boolean useInterOpParallelism) {
    return new Options().useInterOpParallelism(useInterOpParallelism);
  }

  /**
   * Gets components.
   *
   * @return components.
   */
  public List<Output<?>> components() {
    return components;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) components.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.ReduceDataset}
   */
  public static class Options {
    private Boolean useInterOpParallelism;

    private Options() {
    }

    /**
     * Sets the useInterOpParallelism option.
     *
     * @param useInterOpParallelism the useInterOpParallelism option
     * @return this Options instance.
     */
    public Options useInterOpParallelism(Boolean useInterOpParallelism) {
      this.useInterOpParallelism = useInterOpParallelism;
      return this;
    }
  }
}
