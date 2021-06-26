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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The LoadDataset operation
 */
@Operator(
    group = "data"
)
public final class LoadDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LoadDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  private LoadDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LoadDataset operation.
   *
   * @param scope current scope
   * @param path the path value
   * @param readerFuncOtherArgs the readerFuncOtherArgs value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @param readerFunc the value of the readerFunc property
   * @param options carries optional attribute values
   * @return a new instance of LoadDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static LoadDataset create(Scope scope, Operand<TString> path,
      Iterable<Operand<?>> readerFuncOtherArgs, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, ConcreteFunction readerFunc, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("LoadDataset"));
    opBuilder.addInput(path.asOutput());
    opBuilder.addInputList(Operands.asOutputs(readerFuncOtherArgs));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    opBuilder.setAttr("reader_func", readerFunc);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
      }
    }
    return new LoadDataset(opBuilder.build());
  }

  /**
   * Sets the compression option.
   *
   * @param compression the compression option
   * @return this Options instance.
   */
  public static Options compression(String compression) {
    return new Options().compression(compression);
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
   * Optional attributes for {@link org.tensorflow.op.data.LoadDataset}
   */
  public static class Options {
    private String compression;

    private Options() {
    }

    /**
     * Sets the compression option.
     *
     * @param compression the compression option
     * @return this Options instance.
     */
    public Options compression(String compression) {
      this.compression = compression;
      return this;
    }
  }
}
