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
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that contains the elements of {@code input_dataset} ignoring errors.
 */
@OpMetadata(
    opType = IgnoreErrorsDataset.OP_NAME,
    inputsClass = IgnoreErrorsDataset.Inputs.class
)
public final class IgnoreErrorsDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalIgnoreErrorsDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public IgnoreErrorsDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalIgnoreErrorsDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of IgnoreErrorsDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static IgnoreErrorsDataset create(Scope scope, Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IgnoreErrorsDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.logWarning != null) {
          opBuilder.setAttr("log_warning", opts.logWarning);
        }
      }
    }
    return new IgnoreErrorsDataset(opBuilder.build());
  }

  /**
   * Sets the logWarning option.
   *
   * @param logWarning the logWarning option
   * @return this Options instance.
   */
  public static Options logWarning(Boolean logWarning) {
    return new Options().logWarning(logWarning);
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
   * Optional attributes for {@link org.tensorflow.op.data.experimental.IgnoreErrorsDataset}
   */
  public static class Options {
    private Boolean logWarning;

    private Options() {
    }

    /**
     * Sets the logWarning option.
     *
     * @param logWarning the logWarning option
     * @return this Options instance.
     */
    public Options logWarning(Boolean logWarning) {
      this.logWarning = logWarning;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = IgnoreErrorsDataset.class
  )
  public static class Inputs extends RawOpInputs<IgnoreErrorsDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The logWarning attribute
     */
    public final boolean logWarning;

    public Inputs(GraphOperation op) {
      super(new IgnoreErrorsDataset(op), op, Arrays.asList("output_types", "output_shapes", "log_warning"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      logWarning = op.attributes().getAttrBool("log_warning");
    }
  }
}
