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
import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
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
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Reduces the input dataset to a singleton using a reduce function.
 */
@OpMetadata(
    opType = ReduceDataset.OP_NAME,
    inputsClass = ReduceDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ReduceDataset extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReduceDataset";

  private List<Output<?>> components;

  @SuppressWarnings("unchecked")
  public ReduceDataset(Operation operation) {
    super(operation, OP_NAME);
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
   * @param otherArguments The otherArguments value
   * @param f A function that maps {@code (old_state, input_element)} to {@code new_state}. It must take
   * two arguments and return a nested structures of tensors. The structure of
   * {@code new_state} must match the structure of {@code initial_state}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
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
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
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
   * Sets the metadata option.
   *
   * @param metadata the metadata option
   * @return this Options instance.
   */
  public static Options metadata(String metadata) {
    return new Options().metadata(metadata);
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
   * Optional attributes for {@link org.tensorflow.op.data.ReduceDataset}
   */
  public static class Options {
    private Boolean useInterOpParallelism;

    private String metadata;

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

    /**
     * Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public Options metadata(String metadata) {
      this.metadata = metadata;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ReduceDataset.class
  )
  public static class Inputs extends RawOpInputs<ReduceDataset> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A nested structure of tensors, representing the initial state of the
     * transformation.
     */
    public final Iterable<Operand<?>> initialState;

    /**
     * The otherArguments input
     */
    public final Iterable<Operand<?>> otherArguments;

    /**
     * The Tstate attribute
     */
    public final DataType[] Tstate;

    /**
     * The Targuments attribute
     */
    public final DataType[] Targuments;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The useInterOpParallelism attribute
     */
    public final boolean useInterOpParallelism;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new ReduceDataset(op), op, Arrays.asList("Tstate", "Targuments", "output_types", "output_shapes", "use_inter_op_parallelism", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      int initialStateLength = op.inputListLength("initial_state");
      initialState = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, initialStateLength));
      inputIndex += initialStateLength;
      int otherArgumentsLength = op.inputListLength("other_arguments");
      otherArguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, otherArgumentsLength));
      inputIndex += otherArgumentsLength;
      Tstate = op.attributes().getAttrTypeList("Tstate");
      Targuments = op.attributes().getAttrTypeList("Targuments");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      useInterOpParallelism = op.attributes().getAttrBool("use_inter_op_parallelism");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
