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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.Iterator;
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
 * Fetches multiple values from infeed as an XLA tuple.
 */
@OpMetadata(
    opType = InfeedDequeueTuple.OP_NAME,
    inputsClass = InfeedDequeueTuple.Inputs.class
)
public final class InfeedDequeueTuple extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InfeedDequeueTuple";

  private List<Output<?>> outputs;

  @SuppressWarnings("unchecked")
  public InfeedDequeueTuple(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new InfeedDequeueTuple operation.
   *
   * @param scope current scope
   * @param dtypes The element types of each element in {@code outputs}.
   * @param shapes The shapes of each tensor in {@code outputs}.
   * @return a new instance of InfeedDequeueTuple
   */
  @Endpoint(
      describeByClass = true
  )
  public static InfeedDequeueTuple create(Scope scope, List<Class<? extends TType>> dtypes,
      List<Shape> shapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "InfeedDequeueTuple");
    opBuilder.setAttr("dtypes", Operands.toDataTypes(dtypes));
    Shape[] shapesArray = new Shape[shapes.size()];
    for (int i = 0 ; i < shapesArray.length ; i++) {
      shapesArray[i] = shapes.get(i);
    }
    opBuilder.setAttr("shapes", shapesArray);
    return new InfeedDequeueTuple(opBuilder.build());
  }

  /**
   * Gets outputs.
   * A list of tensors that will be provided using the infeed mechanism.
   * @return outputs.
   */
  public List<Output<?>> outputs() {
    return outputs;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) outputs.iterator();
  }

  @OpInputsMetadata(
      outputsClass = InfeedDequeueTuple.class
  )
  public static class Inputs extends RawOpInputs<InfeedDequeueTuple> {
    /**
     * The element types of each element in `outputs`.
     */
    public final DataType[] dtypes;

    /**
     * The shapes of each tensor in `outputs`.
     */
    public final Shape[] shapes;

    public Inputs(GraphOperation op) {
      super(new InfeedDequeueTuple(op), op, Arrays.asList("dtypes", "shapes"));
      int inputIndex = 0;
      dtypes = op.attributes().getAttrTypeList("dtypes");
      shapes = op.attributes().getAttrShapeList("shapes");
    }
  }
}
