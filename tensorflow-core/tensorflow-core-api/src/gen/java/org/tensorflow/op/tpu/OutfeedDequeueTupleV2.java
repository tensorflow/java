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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Retrieve multiple values from the computation outfeed. Device ordinal is a
 * tensor allowing dynamic outfeed.
 * This operation will block indefinitely until data is available. Output {@code i}
 * corresponds to XLA tuple element {@code i}.
 */
public final class OutfeedDequeueTupleV2 extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OutfeedDequeueTupleV2";

  private List<Output<?>> outputs;

  @SuppressWarnings("unchecked")
  private OutfeedDequeueTupleV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new OutfeedDequeueTupleV2 operation.
   *
   * @param scope current scope
   * @param deviceOrdinal An int scalar tensor, representing the TPU device to use. This should be -1 when
   * the Op is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
   * device.
   * @param dtypes The element types of each element in {@code outputs}.
   * @param shapes The shapes of each tensor in {@code outputs}.
   * @return a new instance of OutfeedDequeueTupleV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static OutfeedDequeueTupleV2 create(Scope scope, Operand<TInt32> deviceOrdinal,
      List<Class<? extends TType>> dtypes, List<Shape> shapes) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("OutfeedDequeueTupleV2"));
    opBuilder.addInput(deviceOrdinal.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtypes", Operands.toDataTypes(dtypes));
    Shape[] shapesArray = new Shape[shapes.size()];
    for (int i = 0 ; i < shapesArray.length ; i++) {
      shapesArray[i] = shapes.get(i);
    }
    opBuilder.setAttr("shapes", shapesArray);
    return new OutfeedDequeueTupleV2(opBuilder.build());
  }

  /**
   * Gets outputs.
   * A list of tensors that will be read from the outfeed.
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
}
