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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Computes the (possibly normalized) Levenshtein Edit Distance.
 * <p>
 * The inputs are variable-length sequences provided by SparseTensors
 *   (hypothesis_indices, hypothesis_values, hypothesis_shape)
 * and
 *   (truth_indices, truth_values, truth_shape).
 * <p>
 * The inputs are:
 */
@Operator
public final class EditDistance extends RawOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.EditDistance}
   */
  public static class Options {
    
    /**
     * @param normalize boolean (if true, edit distances are normalized by length of truth).
     * <p>
     * The output is:
     */
    public Options normalize(Boolean normalize) {
      this.normalize = normalize;
      return this;
    }
    
    private Boolean normalize;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new EditDistance operation.
   * 
   * @param scope current scope
   * @param hypothesisIndices The indices of the hypothesis list SparseTensor.
   * This is an N x R int64 matrix.
   * @param hypothesisValues The values of the hypothesis list SparseTensor.
   * This is an N-length vector.
   * @param hypothesisShape The shape of the hypothesis list SparseTensor.
   * This is an R-length vector.
   * @param truthIndices The indices of the truth list SparseTensor.
   * This is an M x R int64 matrix.
   * @param truthValues The values of the truth list SparseTensor.
   * This is an M-length vector.
   * @param truthShape truth indices, vector.
   * @param options carries optional attributes values
   * @return a new instance of EditDistance
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> EditDistance create(Scope scope, Operand<TInt64> hypothesisIndices, Operand<T> hypothesisValues, Operand<TInt64> hypothesisShape, Operand<TInt64> truthIndices, Operand<T> truthValues, Operand<TInt64> truthShape, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("EditDistance", scope.makeOpName("EditDistance"));
    opBuilder.addInput(hypothesisIndices.asOutput());
    opBuilder.addInput(hypothesisValues.asOutput());
    opBuilder.addInput(hypothesisShape.asOutput());
    opBuilder.addInput(truthIndices.asOutput());
    opBuilder.addInput(truthValues.asOutput());
    opBuilder.addInput(truthShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.normalize != null) {
          opBuilder.setAttr("normalize", opts.normalize);
        }
      }
    }
    return new EditDistance(opBuilder.build());
  }
  
  /**
   * @param normalize boolean (if true, edit distances are normalized by length of truth).
   * <p>
   * The output is:
   */
  public static Options normalize(Boolean normalize) {
    return new Options().normalize(normalize);
  }
  
  /**
   * A dense float tensor with rank R - 1.
   * <p>
   * For the example input:
   * <p>
   *     // hypothesis represents a 2x1 matrix with variable-length values:
   *     //   (0,0) = ["a"]
   *     //   (1,0) = ["b"]
   *     hypothesis_indices = [[0, 0, 0],
   *                           [1, 0, 0]]
   *     hypothesis_values = ["a", "b"]
   *     hypothesis_shape = [2, 1, 1]
   * <p>
   *     // truth represents a 2x2 matrix with variable-length values:
   *     //   (0,0) = []
   *     //   (0,1) = ["a"]
   *     //   (1,0) = ["b", "c"]
   *     //   (1,1) = ["a"]
   *     truth_indices = [[0, 1, 0],
   *                      [1, 0, 0],
   *                      [1, 0, 1],
   *                      [1, 1, 0]]
   *     truth_values = ["a", "b", "c", "a"]
   *     truth_shape = [2, 2, 2]
   *     normalize = true
   * <p>
   * The output will be:
   * <p>
   *     // output is a 2x2 matrix with edit distances normalized by truth lengths.
   *     output = [[inf, 1.0],  // (0,0): no truth, (0,1): no hypothesis
   *               [0.5, 1.0]]  // (1,0): addition, (1,1): no hypothesis
   */
  public Output<TFloat32> output() {
    return output;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "EditDistance";
  
  private Output<TFloat32> output;
  
  private EditDistance(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
