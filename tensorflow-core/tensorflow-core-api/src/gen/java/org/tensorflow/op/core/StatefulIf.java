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
 * output = cond ? then_branch(input) : else_branch(input)
 */
@OpMetadata(
    opType = StatefulIf.OP_NAME,
    inputsClass = StatefulIf.Inputs.class
)
@Operator
public final class StatefulIf extends RawOp implements If {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "If";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public StatefulIf(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new If operation.
   *
   * @param scope current scope
   * @param cond <pre>
   *   A Tensor. If the tensor is a scalar of non-boolean type, the
   *   scalar is converted to a boolean according to the
   *   following rule: if the scalar is a numerical value, non-zero means
   *   `True` and zero means False; if the scalar is a string, non-empty
   *   means `True` and empty means `False`. If the tensor is not a scalar,
   *   being empty means False and being non-empty means True.
   * </pre>
   * @param input A list of input tensors.
   * @param Tout A list of output types.
   * @param thenBranch <pre>
   *   A function that takes 'inputs' and returns a list of tensors, whose
   *   types are the same as what else_branch returns.
   * </pre>
   * @param elseBranch <pre>
   * A function that takes 'inputs' and returns a list of tensors, whose
   * types are the same as what then_branch returns.
   * </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatefulIf
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatefulIf create(Scope scope, Operand<? extends TType> cond,
      Iterable<Operand<?>> input, List<Class<? extends TType>> Tout, ConcreteFunction thenBranch,
      ConcreteFunction elseBranch, If.Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatefulIf");
    opBuilder.addInput(cond.asOutput());
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    opBuilder.setAttr("then_branch", thenBranch);
    opBuilder.setAttr("else_branch", elseBranch);
    if (options != null) {
      for (If.Options opts : options) {
        if (opts.outputShapes != null) {
          Shape[] outputShapesArray = new Shape[opts.outputShapes.size()];
          for (int i = 0 ; i < outputShapesArray.length ; i++) {
            outputShapesArray[i] = opts.outputShapes.get(i);
          }
          opBuilder.setAttr("output_shapes", outputShapesArray);
        }
      }
    }
    return new StatefulIf(opBuilder.build());
  }

  /**
   * Gets output.
   * A list of return values.
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
      outputsClass = StatefulIf.class
  )
  public static class Inputs extends RawOpInputs<StatefulIf> {
    /**
     * <pre>
     *   A Tensor. If the tensor is a scalar of non-boolean type, the
     *   scalar is converted to a boolean according to the
     *   following rule: if the scalar is a numerical value, non-zero means
     *   `True` and zero means False; if the scalar is a string, non-empty
     *   means `True` and empty means `False`. If the tensor is not a scalar,
     *   being empty means False and being non-empty means True.
     * </pre>
     */
    public final Operand<? extends TType> cond;

    /**
     * A list of input tensors.
     */
    public final Iterable<Operand<?>> input;

    /**
     * The Tcond attribute
     */
    public final DataType Tcond;

    /**
     * A list of input types.
     */
    public final DataType[] Tin;

    /**
     * A list of output types.
     */
    public final DataType[] Tout;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new StatefulIf(op), op, Arrays.asList("Tcond", "Tin", "Tout", "output_shapes"));
      int inputIndex = 0;
      cond = (Operand<? extends TType>) op.input(inputIndex++);
      int inputLength = op.inputListLength("input");
      input = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputLength));
      inputIndex += inputLength;
      Tcond = op.attributes().getAttrType("Tcond");
      Tin = op.attributes().getAttrTypeList("Tin");
      Tout = op.attributes().getAttrTypeList("Tout");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
