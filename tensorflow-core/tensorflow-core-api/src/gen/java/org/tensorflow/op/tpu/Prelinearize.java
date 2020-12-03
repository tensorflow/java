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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * An op which linearizes one Tensor value to an opaque variant tensor.
 */
public final class Prelinearize extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.Prelinearize}
   */
  public static class Options {
    
    /**
     * @param shape The shape of the tensor.
     */
    public Options shape(Shape shape) {
      this.shape = shape;
      return this;
    }
    
    /**
     * @param layout A vector holding the requested layout in minor-to-major sequence. If a layout
     * attribute is passed but its values are all -1 the layout will be computed by
     * the infeed operation.
     */
    public Options layout(List<Long> layout) {
      this.layout = layout;
      return this;
    }
    
    private Shape shape;
    private List<Long> layout;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Prelinearize operation.
   * 
   * @param scope current scope
   * @param input A tensor that will be linearized.
   * @param options carries optional attributes values
   * @return a new instance of Prelinearize
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Prelinearize create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Prelinearize", scope.makeOpName("Prelinearize"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.shape != null) {
          opBuilder.setAttr("shape", opts.shape);
        }
        if (opts.layout != null) {
          long[] layoutArray = new long[opts.layout.size()];
          for (int i = 0; i < layoutArray.length; ++i) {
            layoutArray[i] = opts.layout.get(i);
          }
          opBuilder.setAttr("layout", layoutArray);
        }
      }
    }
    return new Prelinearize(opBuilder.build());
  }
  
  /**
   * @param shape The shape of the tensor.
   */
  public static Options shape(Shape shape) {
    return new Options().shape(shape);
  }
  
  /**
   * @param layout A vector holding the requested layout in minor-to-major sequence. If a layout
   * attribute is passed but its values are all -1 the layout will be computed by
   * the infeed operation.
   */
  public static Options layout(List<Long> layout) {
    return new Options().layout(layout);
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
  public static final String OP_NAME = "Prelinearize";
  
  private Output<?> output;
  
  private Prelinearize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
