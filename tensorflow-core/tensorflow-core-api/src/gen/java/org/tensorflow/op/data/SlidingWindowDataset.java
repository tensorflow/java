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
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that passes a sliding window over {@code input_dataset}.
 */
@OpMetadata(
    opType = SlidingWindowDataset.OP_NAME,
    inputsClass = SlidingWindowDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class SlidingWindowDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SlidingWindowDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public SlidingWindowDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SlidingWindowDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param windowSize A scalar representing the number of elements in the
   * sliding window.
   * @param windowShift A scalar representing the steps moving the sliding window
   * forward in one iteration. It must be positive.
   * @param windowStride A scalar representing the stride of the input elements of the sliding window.
   * It must be positive.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of SlidingWindowDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static SlidingWindowDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> windowSize, Operand<TInt64> windowShift, Operand<TInt64> windowStride,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SlidingWindowDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(windowSize.asOutput());
    opBuilder.addInput(windowShift.asOutput());
    opBuilder.addInput(windowStride.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dropRemainder != null) {
          opBuilder.setAttr("drop_remainder", opts.dropRemainder);
        }
      }
    }
    return new SlidingWindowDataset(opBuilder.build());
  }

  /**
   * Sets the dropRemainder option.
   *
   * @param dropRemainder the dropRemainder option
   * @return this Options instance.
   */
  public static Options dropRemainder(Boolean dropRemainder) {
    return new Options().dropRemainder(dropRemainder);
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
   * Optional attributes for {@link org.tensorflow.op.data.SlidingWindowDataset}
   */
  public static class Options {
    private Boolean dropRemainder;

    private Options() {
    }

    /**
     * Sets the dropRemainder option.
     *
     * @param dropRemainder the dropRemainder option
     * @return this Options instance.
     */
    public Options dropRemainder(Boolean dropRemainder) {
      this.dropRemainder = dropRemainder;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SlidingWindowDataset.class
  )
  public static class Inputs extends RawOpInputs<SlidingWindowDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A scalar representing the number of elements in the
     * sliding window.
     */
    public final Operand<TInt64> windowSize;

    /**
     * A scalar representing the steps moving the sliding window
     * forward in one iteration. It must be positive.
     */
    public final Operand<TInt64> windowShift;

    /**
     * A scalar representing the stride of the input elements of the sliding window.
     * It must be positive.
     */
    public final Operand<TInt64> windowStride;

    /**
     * The dropRemainder attribute
     */
    public final boolean dropRemainder;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new SlidingWindowDataset(op), op, Arrays.asList("drop_remainder", "output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      windowSize = (Operand<TInt64>) op.input(inputIndex++);
      windowShift = (Operand<TInt64>) op.input(inputIndex++);
      windowStride = (Operand<TInt64>) op.input(inputIndex++);
      dropRemainder = op.attributes().getAttrBool("drop_remainder");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
