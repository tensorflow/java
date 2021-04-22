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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The IteratorFromStringHandleV2 operation
 */
public final class IteratorFromStringHandle extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IteratorFromStringHandleV2";

  private Output<? extends TType> resourceHandle;

  @SuppressWarnings("unchecked")
  private IteratorFromStringHandle(Operation operation) {
    super(operation);
    int outputIdx = 0;
    resourceHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IteratorFromStringHandleV2 operation.
   *
   * @param scope current scope
   * @param stringHandle the stringHandle value
   * @param outputTypes the value of the outputTypes property
   * @param options carries optional attribute values
   * @return a new instance of IteratorFromStringHandle
   */
  @Endpoint(
      describeByClass = true
  )
  public static IteratorFromStringHandle create(Scope scope, Operand<TString> stringHandle,
      List<Class<? extends TType>> outputTypes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("IteratorFromStringHandleV2", scope.makeOpName("IteratorFromStringHandle"));
    opBuilder.addInput(stringHandle.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.outputShapes != null) {
          Shape[] outputShapesArray = new Shape[opts.outputShapes.size()];
          for (int i = 0 ; i < outputShapesArray.length ; i++) {
            outputShapesArray[i] = opts.outputShapes.get(i);
          }
          opBuilder.setAttr("output_shapes", outputShapesArray);
        }
      }
    }
    return new IteratorFromStringHandle(opBuilder.build());
  }

  /**
   * Sets the outputShapes option.
   *
   * @param outputShapes the outputShapes option
   * @return this Options instance.
   */
  public static Options outputShapes(List<Shape> outputShapes) {
    return new Options().outputShapes(outputShapes);
  }

  /**
   * Sets the outputShapes option.
   *
   * @param outputShapes the outputShapes option
   * @return this Options instance.
   */
  public static Options outputShapes(Shape[] outputShapes) {
    return new Options().outputShapes(outputShapes);
  }

  /**
   * Gets resourceHandle.
   *
   * @return resourceHandle.
   */
  public Output<? extends TType> resourceHandle() {
    return resourceHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) resourceHandle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.IteratorFromStringHandle}
   */
  public static class Options {
    private List<Shape> outputShapes;

    private Options() {
    }

    /**
     * Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     */
    public Options outputShapes(List<Shape> outputShapes) {
      this.outputShapes = outputShapes;
      return this;
    }

    /**
     * Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     */
    public Options outputShapes(Shape... outputShapes) {
      this.outputShapes = Arrays.asList(outputShapes);
      return this;
    }
  }
}
