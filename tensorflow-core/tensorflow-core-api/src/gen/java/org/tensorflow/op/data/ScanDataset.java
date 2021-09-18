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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset successively reduces {@code f} over the elements of {@code input_dataset}.
 */
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
  private ScanDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScanDataset operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param initialState the initialState value
   * @param otherArguments the otherArguments value
   * @param f the value of the f property
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
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
  }
}
