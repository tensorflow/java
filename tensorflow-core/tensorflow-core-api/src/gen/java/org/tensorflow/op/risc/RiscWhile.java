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

package org.tensorflow.op.risc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
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
 * The RiscWhile operation
 */
@OpMetadata(
    opType = RiscWhile.OP_NAME,
    inputsClass = RiscWhile.Inputs.class
)
public final class RiscWhile extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscWhile";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public RiscWhile(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new RiscWhile operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param cond The value of the cond attribute
   * @param body The value of the body attribute
   * @param options carries optional attribute values
   * @return a new instance of RiscWhile
   */
  @Endpoint(
      describeByClass = true
  )
  public static RiscWhile create(Scope scope, Iterable<Operand<?>> input, ConcreteFunction cond,
      ConcreteFunction body, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscWhile");
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.setAttr("cond", cond);
    opBuilder.setAttr("body", body);
    if (options != null) {
      for (Options opts : options) {
        if (opts.outputShapes != null) {
          Shape[] outputShapesArray = new Shape[opts.outputShapes.size()];
          for (int i = 0 ; i < outputShapesArray.length ; i++) {
            outputShapesArray[i] = opts.outputShapes.get(i);
          }
          opBuilder.setAttr("output_shapes", outputShapesArray);
        }
        if (opts.parallelIterations != null) {
          opBuilder.setAttr("parallel_iterations", opts.parallelIterations);
        }
      }
    }
    return new RiscWhile(opBuilder.build());
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
  public static Options outputShapes(Shape... outputShapes) {
    return new Options().outputShapes(outputShapes);
  }

  /**
   * Sets the parallelIterations option.
   *
   * @param parallelIterations the parallelIterations option
   * @return this Options instance.
   */
  public static Options parallelIterations(Long parallelIterations) {
    return new Options().parallelIterations(parallelIterations);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.risc.RiscWhile}
   */
  public static class Options {
    private List<Shape> outputShapes;

    private Long parallelIterations;

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

    /**
     * Sets the parallelIterations option.
     *
     * @param parallelIterations the parallelIterations option
     * @return this Options instance.
     */
    public Options parallelIterations(Long parallelIterations) {
      this.parallelIterations = parallelIterations;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RiscWhile.class
  )
  public static class Inputs extends RawOpInputs<RiscWhile> {
    /**
     * The input input
     */
    public final Iterable<Operand<?>> input;

    /**
     * The T attribute
     */
    public final DataType[] T;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The parallelIterations attribute
     */
    public final long parallelIterations;

    public Inputs(GraphOperation op) {
      super(new RiscWhile(op), op, Arrays.asList("T", "output_shapes", "parallel_iterations"));
      int inputIndex = 0;
      int inputLength = op.inputListLength("input");
      input = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputLength));
      inputIndex += inputLength;
      T = op.attributes().getAttrTypeList("T");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      parallelIterations = op.attributes().getAttrInt("parallel_iterations");
    }
  }
}
