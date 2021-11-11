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

package org.tensorflow.op.nn;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Finds values of the {@code n}-th order statistic for the last dimension.
 * If the input is a vector (rank-1), finds the entries which is the nth-smallest
 * value in the vector and outputs their values as scalar tensor.
 * <p>For matrices (resp. higher rank input), computes the entries which is the
 * nth-smallest value in each row (resp. vector along the last dimension). Thus,
 * <pre>
 * values.shape = input.shape[:-1]
 * </pre>
 *
 * @param <T> data type for {@code values} output
 */
@OpMetadata(
    opType = NthElement.OP_NAME,
    inputsClass = NthElement.Inputs.class
)
@Operator(
    group = "nn"
)
public final class NthElement<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "NthElement";

  private Output<T> values;

  public NthElement(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    values = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new NthElement operation.
   *
   * @param scope current scope
   * @param input 1-D or higher with last dimension at least {@code n+1}.
   * @param n 0-D. Position of sorted vector to select along the last dimension (along
   * each row for matrices). Valid range of n is {@code [0, input.shape[:-1])}
   * @param options carries optional attribute values
   * @param <T> data type for {@code NthElement} output and operands
   * @return a new instance of NthElement
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> NthElement<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> n, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "NthElement");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(n.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.reverse != null) {
          opBuilder.setAttr("reverse", opts.reverse);
        }
      }
    }
    return new NthElement<>(opBuilder.build());
  }

  /**
   * Sets the reverse option.
   *
   * @param reverse When set to True, find the nth-largest value in the vector and vice
   * versa.
   * @return this Options instance.
   */
  public static Options reverse(Boolean reverse) {
    return new Options().reverse(reverse);
  }

  /**
   * Gets values.
   * The {@code n}-th order statistic along each last dimensional slice.
   * @return values.
   */
  public Output<T> values() {
    return values;
  }

  @Override
  public Output<T> asOutput() {
    return values;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.NthElement}
   */
  public static class Options {
    private Boolean reverse;

    private Options() {
    }

    /**
     * Sets the reverse option.
     *
     * @param reverse When set to True, find the nth-largest value in the vector and vice
     * versa.
     * @return this Options instance.
     */
    public Options reverse(Boolean reverse) {
      this.reverse = reverse;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = NthElement.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<NthElement<T>> {
    /**
     * 1-D or higher with last dimension at least {@code n+1}.
     */
    public final Operand<T> input;

    /**
     * 0-D. Position of sorted vector to select along the last dimension (along
     * each row for matrices). Valid range of n is {@code [0, input.shape[:-1])}
     */
    public final Operand<TInt32> n;

    /**
     * When set to True, find the nth-largest value in the vector and vice
     * versa.
     */
    public final boolean reverse;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new NthElement<>(op), op, Arrays.asList("reverse", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      n = (Operand<TInt32>) op.input(inputIndex++);
      reverse = op.attributes().getAttrBool("reverse");
      T = op.attributes().getAttrType("T");
    }
  }
}
