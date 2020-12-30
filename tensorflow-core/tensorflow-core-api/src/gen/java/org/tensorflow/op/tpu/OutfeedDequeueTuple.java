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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Retrieve multiple values from the computation outfeed.
 * <p>
 * This operation will block indefinitely until data is available. Output `i`
 * corresponds to XLA tuple element `i`.
 */
public final class OutfeedDequeueTuple extends RawOp implements Iterable<Operand<TType>> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.OutfeedDequeueTuple}
   */
  public static class Options {
    
    /**
     * @param deviceOrdinal The TPU device to use. This should be -1 when the Op
     * is running on a TPU device, and >= 0 when the Op is running on the CPU
     * device.
     */
    public Options deviceOrdinal(Long deviceOrdinal) {
      this.deviceOrdinal = deviceOrdinal;
      return this;
    }
    
    private Long deviceOrdinal;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new OutfeedDequeueTuple operation.
   * 
   * @param scope current scope
   * @param dtypes The element types of each element in `outputs`.
   * @param shapes The shapes of each tensor in `outputs`.
   * @param options carries optional attributes values
   * @return a new instance of OutfeedDequeueTuple
   */
  @Endpoint(describeByClass = true)
  public static OutfeedDequeueTuple create(Scope scope, List<Class<? extends TType>> dtypes, List<Shape> shapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("OutfeedDequeueTuple", scope.makeOpName("OutfeedDequeueTuple"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtypes", Operands.toDataTypes(dtypes));
    Shape[] shapesArray = new Shape[shapes.size()];
    for (int i = 0; i < shapesArray.length; ++i) {
      shapesArray[i] = shapes.get(i);
    }
    opBuilder.setAttr("shapes", shapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.deviceOrdinal != null) {
          opBuilder.setAttr("device_ordinal", opts.deviceOrdinal);
        }
      }
    }
    return new OutfeedDequeueTuple(opBuilder.build());
  }
  
  /**
   * @param deviceOrdinal The TPU device to use. This should be -1 when the Op
   * is running on a TPU device, and >= 0 when the Op is running on the CPU
   * device.
   */
  public static Options deviceOrdinal(Long deviceOrdinal) {
    return new Options().deviceOrdinal(deviceOrdinal);
  }
  
  /**
   * A list of tensors that will be read from the outfeed.
   */
  public List<Output<?>> outputs() {
    return outputs;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) outputs.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "OutfeedDequeueTuple";
  
  private List<Output<?>> outputs;
  
  private OutfeedDequeueTuple(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }
}
