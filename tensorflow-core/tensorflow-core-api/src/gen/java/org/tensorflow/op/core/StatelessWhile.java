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

package org.tensorflow.op.core;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * output = input; While (Cond(output)) { output = Body(output) }
 */
@OpMetadata(
    opType = StatelessWhile.OP_NAME,
    inputsClass = StatelessWhile.Inputs.class
)
@Operator
public final class StatelessWhile extends RawOp implements While {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessWhile";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public StatelessWhile(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new StatelessWhile operation.
   *
   * @param scope current scope
   * @param input A list of input tensors whose types are T.
   * @param cond <pre>
   *   A function takes 'input' and returns a tensor.  If the tensor is
   *   a scalar of non-boolean, the scalar is converted to a boolean
   *   according to the following rule: if the scalar is a numerical
   *   value, non-zero means True and zero means False; if the scalar is
   *   a string, non-empty means True and empty means False. If the
   *   tensor is not a scalar, non-emptiness means True and False
   *   otherwise.
   *
   *   This should only be used when the while condition and body functions
   *   do not have stateful ops.
   * </pre>
   * @param body <pre>
   *   A function that takes a list of tensors and returns another
   *   list of tensors. Both lists have the same types as specified
   *   by T.
   * </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatelessWhile
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatelessWhile create(Scope scope, Iterable<Operand<?>> input,
      ConcreteFunction cond, ConcreteFunction body, While.Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessWhile");
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.setAttr("cond", cond);
    opBuilder.setAttr("body", body);
    if (options != null) {
      for (While.Options opts : options) {
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
    return new StatelessWhile(opBuilder.build());
  }

  /**
   * Gets output.
   * A list of output tensors whose types are T.
   * @return output.
   */
  @Override
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  @OpInputsMetadata(
      outputsClass = StatelessWhile.class
  )
  public static class Inputs extends RawOpInputs<StatelessWhile> {
    /**
     * A list of input tensors whose types are T.
     */
    public final Iterable<Operand<?>> input;

    /**
     * dtype in use.
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
      super(new StatelessWhile(op), op, Arrays.asList("T", "output_shapes", "parallel_iterations"));
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
