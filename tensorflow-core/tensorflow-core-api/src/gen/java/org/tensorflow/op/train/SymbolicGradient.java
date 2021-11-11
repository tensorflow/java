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

package org.tensorflow.op.train;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
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
 * Computes the gradient function for function f via backpropagation.
 */
@OpMetadata(
    opType = SymbolicGradient.OP_NAME,
    inputsClass = SymbolicGradient.Inputs.class
)
@Operator(
    group = "train"
)
public final class SymbolicGradient extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SymbolicGradient";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public SymbolicGradient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new SymbolicGradient operation.
   *
   * @param scope current scope
   * @param input a list of input tensors of size N + M;
   * @param Tout the type list for the input list.
   * @param f The function we want to compute the gradient for.
   * <p>The function 'f' must be a numerical function which takes N inputs and
   * produces M outputs. Its gradient function 'g', which is computed by
   * this SymbolicGradient op is a function taking N + M inputs and
   * produces N outputs.
   * <p>I.e. if we have
   * (y1, y2, ..., y_M) = f(x1, x2, ..., x_N),
   * then, g is
   * (dL/dx1, dL/dx2, ..., dL/dx_N) = g(x1, x2, ..., x_N,
   * dL/dy1, dL/dy2, ..., dL/dy_M),
   * <p>where L is a scalar-value function of (x1, x2, ..., xN) (e.g., the
   * loss function). dL/dx_i is the partial derivative of L with respect
   * to x_i.
   * <p>(Needs some math expert to say the comment above better.)
   * @return a new instance of SymbolicGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static SymbolicGradient create(Scope scope, Iterable<Operand<?>> input,
      List<Class<? extends TType>> Tout, ConcreteFunction f) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SymbolicGradient");
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    opBuilder.setAttr("f", f);
    return new SymbolicGradient(opBuilder.build());
  }

  /**
   * Gets output.
   * a list of output tensors of size N;
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

  @OpInputsMetadata(
      outputsClass = SymbolicGradient.class
  )
  public static class Inputs extends RawOpInputs<SymbolicGradient> {
    /**
     * a list of input tensors of size N + M;
     */
    public final Iterable<Operand<?>> input;

    /**
     * the type list for the input list.
     */
    public final DataType[] Tin;

    /**
     * the type list for the input list.
     */
    public final DataType[] Tout;

    public Inputs(GraphOperation op) {
      super(new SymbolicGradient(op), op, Arrays.asList("Tin", "Tout"));
      int inputIndex = 0;
      int inputLength = op.inputListLength("input");
      input = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputLength));
      inputIndex += inputLength;
      Tin = op.attributes().getAttrTypeList("Tin");
      Tout = op.attributes().getAttrTypeList("Tout");
    }
  }
}
