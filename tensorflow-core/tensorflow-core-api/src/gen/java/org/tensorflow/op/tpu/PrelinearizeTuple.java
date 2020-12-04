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
 * An op which linearizes multiple Tensor values to an opaque variant tensor.
 */
public final class PrelinearizeTuple extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.PrelinearizeTuple}
   */
  public static class Options {
    
    /**
     * @param layouts A vector holding the requested layout in minor-to-major sequence for all the
     * tuple shapes in the order the shapes appear in the "shapes" input. The layout
     * elements for a sub-shape can be set to -1 in which case the corresponding layout
     * will be computed by the infeed operation.
     */
    public Options layouts(List<Long> layouts) {
      this.layouts = layouts;
      return this;
    }
    
    private List<Long> layouts;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new PrelinearizeTuple operation.
   * 
   * @param scope current scope
   * @param inputs A list of tensors that will be provided using the infeed mechanism.
   * @param shapes The shapes of each tensor in `inputs`.
   * @param options carries optional attributes values
   * @return a new instance of PrelinearizeTuple
   */
  @Endpoint(describeByClass = true)
  public static PrelinearizeTuple create(Scope scope, Iterable<Operand<?>> inputs, List<Shape> shapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("PrelinearizeTuple", scope.makeOpName("PrelinearizeTuple"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    Shape[] shapesArray = new Shape[shapes.size()];
    for (int i = 0; i < shapesArray.length; ++i) {
      shapesArray[i] = shapes.get(i);
    }
    opBuilder.setAttr("shapes", shapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.layouts != null) {
          long[] layoutsArray = new long[opts.layouts.size()];
          for (int i = 0; i < layoutsArray.length; ++i) {
            layoutsArray[i] = opts.layouts.get(i);
          }
          opBuilder.setAttr("layouts", layoutsArray);
        }
      }
    }
    return new PrelinearizeTuple(opBuilder.build());
  }
  
  /**
   * @param layouts A vector holding the requested layout in minor-to-major sequence for all the
   * tuple shapes in the order the shapes appear in the "shapes" input. The layout
   * elements for a sub-shape can be set to -1 in which case the corresponding layout
   * will be computed by the infeed operation.
   */
  public static Options layouts(List<Long> layouts) {
    return new Options().layouts(layouts);
  }
  
  /**
   */
  public Output<?> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "PrelinearizeTuple";
  
  private Output<?> output;
  
  private PrelinearizeTuple(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
