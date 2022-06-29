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
 * Creates a dataset successively reduces {@code f} over the elements of {@code input_dataset}.
 */
@OpMetadata(
    opType = ScanDataset.OP_NAME,
    inputsClass = ScanDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ScanDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ScanDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ScanDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScanDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param initialState The initialState value
   * @param otherArguments The otherArguments value
   * @param f The value of the f attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ScanDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ScanDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> initialState, Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ScanDataset");
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
        if (opts.preserveCardinality != null) {
          opBuilder.setAttr("preserve_cardinality", opts.preserveCardinality);
        }
        if (opts.useDefaultDevice != null) {
          opBuilder.setAttr("use_default_device", opts.useDefaultDevice);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new ScanDataset(opBuilder.build());
  }

  /**
   * Sets the preserveCardinality option.
   *
   * @param preserveCardinality the preserveCardinality option
   * @return this Options instance.
   */
  public static Options preserveCardinality(Boolean preserveCardinality) {
    return new Options().preserveCardinality(preserveCardinality);
  }

  /**
   * Sets the useDefaultDevice option.
   *
   * @param useDefaultDevice the useDefaultDevice option
   * @return this Options instance.
   */
  public static Options useDefaultDevice(Boolean useDefaultDevice) {
    return new Options().useDefaultDevice(useDefaultDevice);
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
   * Optional attributes for {@link org.tensorflow.op.data.ScanDataset}
   */
  public static class Options {
    private Boolean preserveCardinality;

    private Boolean useDefaultDevice;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the preserveCardinality option.
     *
     * @param preserveCardinality the preserveCardinality option
     * @return this Options instance.
     */
    public Options preserveCardinality(Boolean preserveCardinality) {
      this.preserveCardinality = preserveCardinality;
      return this;
    }

    /**
     * Sets the useDefaultDevice option.
     *
     * @param useDefaultDevice the useDefaultDevice option
     * @return this Options instance.
     */
    public Options useDefaultDevice(Boolean useDefaultDevice) {
      this.useDefaultDevice = useDefaultDevice;
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
      outputsClass = ScanDataset.class
  )
  public static class Inputs extends RawOpInputs<ScanDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The initialState input
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
     * The preserveCardinality attribute
     */
    public final boolean preserveCardinality;

    /**
     * The useDefaultDevice attribute
     */
    public final boolean useDefaultDevice;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new ScanDataset(op), op, Arrays.asList("Tstate", "Targuments", "output_types", "output_shapes", "preserve_cardinality", "use_default_device", "metadata"));
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
      preserveCardinality = op.attributes().getAttrBool("preserve_cardinality");
      useDefaultDevice = op.attributes().getAttrBool("use_default_device");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
