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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Creates a MultiDeviceIterator resource.
 */
@OpMetadata(
    opType = MultiDeviceIterator.OP_NAME,
    inputsClass = MultiDeviceIterator.Inputs.class
)
public final class MultiDeviceIterator extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MultiDeviceIterator";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public MultiDeviceIterator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MultiDeviceIterator operation.
   *
   * @param scope current scope
   * @param devices A list of devices the iterator works across.
   * @param sharedName If non-empty, this resource will be shared under the given name
   * across multiple sessions.
   * @param container If non-empty, this resource is placed in the given container.
   * Otherwise, a default container is used.
   * @param outputTypes The type list for the return values.
   * @param outputShapes The list of shapes being produced.
   * @return a new instance of MultiDeviceIterator
   */
  @Endpoint(
      describeByClass = true
  )
  public static MultiDeviceIterator create(Scope scope, List<String> devices, String sharedName,
      String container, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MultiDeviceIterator");
    String[] devicesArray = new String[devices.size()];
    for (int i = 0 ; i < devicesArray.length ; i++) {
      devicesArray[i] = devices.get(i);
    }
    opBuilder.setAttr("devices", devicesArray);
    opBuilder.setAttr("shared_name", sharedName);
    opBuilder.setAttr("container", container);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new MultiDeviceIterator(opBuilder.build());
  }

  /**
   * Gets handle.
   * Handle to the resource created.
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

  @OpInputsMetadata(
      outputsClass = MultiDeviceIterator.class
  )
  public static class Inputs extends RawOpInputs<MultiDeviceIterator> {
    /**
     * A list of devices the iterator works across.
     */
    public final String[] devices;

    /**
     * If non-empty, this resource will be shared under the given name
     * across multiple sessions.
     */
    public final String sharedName;

    /**
     * If non-empty, this resource is placed in the given container.
     * Otherwise, a default container is used.
     */
    public final String container;

    /**
     * The type list for the return values.
     */
    public final DataType[] outputTypes;

    /**
     * The list of shapes being produced.
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new MultiDeviceIterator(op), op, Arrays.asList("devices", "shared_name", "container", "output_types", "output_shapes"));
      int inputIndex = 0;
      devices = op.attributes().getAttrStringList("devices");
      sharedName = op.attributes().getAttrString("shared_name");
      container = op.attributes().getAttrString("container");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
