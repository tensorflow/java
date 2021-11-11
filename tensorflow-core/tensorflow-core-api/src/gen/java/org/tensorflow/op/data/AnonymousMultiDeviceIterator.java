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
 * A container for a multi device iterator resource.
 */
@OpMetadata(
    opType = AnonymousMultiDeviceIterator.OP_NAME,
    inputsClass = AnonymousMultiDeviceIterator.Inputs.class
)
public final class AnonymousMultiDeviceIterator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AnonymousMultiDeviceIterator";

  private Output<? extends TType> handle;

  private Output<? extends TType> deleter;

  @SuppressWarnings("unchecked")
  public AnonymousMultiDeviceIterator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    deleter = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AnonymousMultiDeviceIterator operation.
   *
   * @param scope current scope
   * @param devices The value of the devices attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of AnonymousMultiDeviceIterator
   */
  @Endpoint(
      describeByClass = true
  )
  public static AnonymousMultiDeviceIterator create(Scope scope, List<String> devices,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AnonymousMultiDeviceIterator");
    String[] devicesArray = new String[devices.size()];
    for (int i = 0 ; i < devicesArray.length ; i++) {
      devicesArray[i] = devices.get(i);
    }
    opBuilder.setAttr("devices", devicesArray);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new AnonymousMultiDeviceIterator(opBuilder.build());
  }

  /**
   * Gets handle.
   * A handle to a multi device iterator that can be passed to a
   * &quot;MultiDeviceIteratorGetNextFromShard&quot; op. In contrast to MultiDeviceIterator,
   * AnonymousIterator prevents resource sharing by name, and does not keep a
   * reference to the resource container.
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  /**
   * Gets deleter.
   * A variant deleter that should be passed into the op that deletes the iterator.
   * @return deleter.
   */
  public Output<? extends TType> deleter() {
    return deleter;
  }

  @OpInputsMetadata(
      outputsClass = AnonymousMultiDeviceIterator.class
  )
  public static class Inputs extends RawOpInputs<AnonymousMultiDeviceIterator> {
    /**
     * The devices attribute
     */
    public final String[] devices;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new AnonymousMultiDeviceIterator(op), op, Arrays.asList("devices", "output_types", "output_shapes"));
      int inputIndex = 0;
      devices = op.attributes().getAttrStringList("devices");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
