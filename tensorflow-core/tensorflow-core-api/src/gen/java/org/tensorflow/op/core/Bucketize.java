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
import java.util.List;
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
 * Bucketizes 'input' based on 'boundaries'.
 * For example, if the inputs are
 * boundaries = [0, 10, 100]
 * input = [[-5, 10000]
 * [150,   10]
 * [5,    100]]
 * <p>then the output will be
 * output = [[0, 3]
 * [3, 2]
 * [1, 3]]
 */
@OpMetadata(
    opType = Bucketize.OP_NAME,
    inputsClass = Bucketize.Inputs.class
)
@Operator
public final class Bucketize extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Bucketize";

  private Output<TInt32> output;

  public Bucketize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Bucketize operation.
   *
   * @param scope current scope
   * @param input Any shape of Tensor contains with int or float type.
   * @param boundaries A sorted list of floats gives the boundary of the buckets.
   * @return a new instance of Bucketize
   */
  @Endpoint(
      describeByClass = true
  )
  public static Bucketize create(Scope scope, Operand<? extends TNumber> input,
      List<Float> boundaries) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Bucketize");
    opBuilder.addInput(input.asOutput());
    float[] boundariesArray = new float[boundaries.size()];
    for (int i = 0 ; i < boundariesArray.length ; i++) {
      boundariesArray[i] = boundaries.get(i);
    }
    opBuilder.setAttr("boundaries", boundariesArray);
    return new Bucketize(opBuilder.build());
  }

  /**
   * Gets output.
   * Same shape with 'input', each value of input replaced with bucket index.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.digitize.
   * <br>{@literal @}end_compatibility
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Bucketize.class
  )
  public static class Inputs extends RawOpInputs<Bucketize> {
    /**
     * Any shape of Tensor contains with int or float type.
     */
    public final Operand<? extends TNumber> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * A sorted list of floats gives the boundary of the buckets.
     */
    public final float[] boundaries;

    public Inputs(GraphOperation op) {
      super(new Bucketize(op), op, Arrays.asList("T", "boundaries"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      boundaries = op.attributes().getAttrFloatList("boundaries");
    }
  }
}
