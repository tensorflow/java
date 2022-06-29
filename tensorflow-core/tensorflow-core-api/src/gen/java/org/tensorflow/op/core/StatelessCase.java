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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * An n-way switch statement which calls a single branch function.
 * <pre>
 * An n-way switch statement, implementing the following:
 * ```
 * switch (branch_index) {
 *   case 0:
 *     output = branches[0](input);
 *     break;
 *   case 1:
 *     output = branches[1](input);
 *     break;
 *   ...
 *   case [[nbranches-1]]:
 *   default:
 *     output = branches[nbranches-1](input);
 *     break;
 * }
 * ```
 *
 * This should only be used when the none of branches has stateful ops.
 * </pre>
 */
@OpMetadata(
    opType = StatelessCase.OP_NAME,
    inputsClass = StatelessCase.Inputs.class
)
public final class StatelessCase extends RawOp implements Case {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessCase";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public StatelessCase(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new StatelessCase operation.
   *
   * @param scope current scope
   * @param branchIndex The branch selector, an int32 Tensor.
   * @param input A list of input tensors passed to the branch function.
   * @param Tout A list of output types.
   * @param branches <pre>
   *   A list of functions each of which takes 'inputs' and returns a list of
   *   tensors, whose types are the same as what every other branch returns.
   * </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatelessCase
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatelessCase create(Scope scope, Operand<TInt32> branchIndex,
      Iterable<Operand<?>> input, List<Class<? extends TType>> Tout,
      List<ConcreteFunction> branches, Case.Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessCase");
    opBuilder.addInput(branchIndex.asOutput());
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    ConcreteFunction[] branchesArray = new ConcreteFunction[branches.size()];
    for (int i = 0 ; i < branchesArray.length ; i++) {
      branchesArray[i] = branches.get(i);
    }
    opBuilder.setAttr("branches", branchesArray);
    if (options != null) {
      for (Case.Options opts : options) {
        if (opts.outputShapes != null) {
          Shape[] outputShapesArray = new Shape[opts.outputShapes.size()];
          for (int i = 0 ; i < outputShapesArray.length ; i++) {
            outputShapesArray[i] = opts.outputShapes.get(i);
          }
          opBuilder.setAttr("output_shapes", outputShapesArray);
        }
      }
    }
    return new StatelessCase(opBuilder.build());
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
      outputsClass = StatelessCase.class
  )
  public static class Inputs extends RawOpInputs<StatelessCase> {
    /**
     * The branch selector, an int32 Tensor.
     */
    public final Operand<TInt32> branchIndex;

    /**
     * A list of input tensors passed to the branch function.
     */
    public final Iterable<Operand<?>> input;

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
      super(new StatelessCase(op), op, Arrays.asList("Tin", "Tout", "output_shapes"));
      int inputIndex = 0;
      branchIndex = (Operand<TInt32>) op.input(inputIndex++);
      int inputLength = op.inputListLength("input");
      input = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputLength));
      inputIndex += inputLength;
      Tin = op.attributes().getAttrTypeList("Tin");
      Tout = op.attributes().getAttrTypeList("Tout");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
