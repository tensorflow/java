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
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
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
 * </pre>
 *
 * <p>Selects between {@link StatefulCase} and {@link StatelessCase} based on the statefulness of the function arguments.
 */
@Operator
public interface Case extends Iterable<Operand<TType>> {
  /**
   * Factory method to create a class wrapping a new Case operation.
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
   * @return a new instance of Case
   */
  @Endpoint(
      describeByClass = true,
      name = "caseOp"
  )
  static Case create(Scope scope, Operand<TInt32> branchIndex, Iterable<Operand<?>> input,
      List<Class<? extends TType>> Tout, List<ConcreteFunction> branches, Options... options) {
    boolean isStateful = false;
    if (branches.stream().anyMatch(x -> x.isStateful())) {
      isStateful = true;
    }
    if (isStateful) {
      return StatefulCase.create(scope, branchIndex, input, Tout, branches, options);
    } else {
      return StatelessCase.create(scope, branchIndex, input, Tout, branches, options);
    }
  }

  /**
   * Sets the outputShapes option.
   *
   * @param outputShapes the outputShapes option
   * @return this Options instance.
   */
  static Options outputShapes(List<Shape> outputShapes) {
    return new Options().outputShapes(outputShapes);
  }

  /**
   * Sets the outputShapes option.
   *
   * @param outputShapes the outputShapes option
   * @return this Options instance.
   */
  static Options outputShapes(Shape... outputShapes) {
    return new Options().outputShapes(outputShapes);
  }

  /**
   * Gets output.
   * A list of return values.
   * @return output.
   */
  List<Output<?>> output();

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  Iterator<Operand<TType>> iterator();

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Case}
   */
  class Options {
    List<Shape> outputShapes;

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
