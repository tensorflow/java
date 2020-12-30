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

package org.tensorflow.op.sparse;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;

/**
 * Generates sparse cross from a list of sparse and dense tensors.
 * <p>
 * The op takes two lists, one of 2D `SparseTensor` and one of 2D `Tensor`, each
 * representing features of one feature column. It outputs a 2D `SparseTensor` with
 * the batchwise crosses of these features.
 * <p>
 * For example, if the inputs are
 * <p>
 *     inputs[0]: SparseTensor with shape = [2, 2]
 *     [0, 0]: "a"
 *     [1, 0]: "b"
 *     [1, 1]: "c"
 * <p>
 *     inputs[1]: SparseTensor with shape = [2, 1]
 *     [0, 0]: "d"
 *     [1, 0]: "e"
 * <p>
 *     inputs[2]: Tensor [["f"], ["g"]]
 * <p>
 * then the output will be
 * <p>
 *     shape = [2, 2]
 *     [0, 0]: "a_X_d_X_f"
 *     [1, 0]: "b_X_e_X_g"
 *     [1, 1]: "c_X_e_X_g"
 * <p>
 * if hashed_output=true then the output will be
 * <p>
 *     shape = [2, 2]
 *     [0, 0]: FingerprintCat64(
 *                 Fingerprint64("f"), FingerprintCat64(
 *                     Fingerprint64("d"), Fingerprint64("a")))
 *     [1, 0]: FingerprintCat64(
 *                 Fingerprint64("g"), FingerprintCat64(
 *                     Fingerprint64("e"), Fingerprint64("b")))
 *     [1, 1]: FingerprintCat64(
 *                 Fingerprint64("g"), FingerprintCat64(
 *                     Fingerprint64("e"), Fingerprint64("c")))
 */
@Operator(group = "sparse")
public final class SparseCrossHashed extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseCrossHashed operation.
   * 
   * @param scope current scope
   * @param indices 2-D.  Indices of each input `SparseTensor`.
   * @param values 1-D.   values of each `SparseTensor`.
   * @param shapes 1-D.   Shapes of each `SparseTensor`.
   * @param denseInputs 2-D.    Columns represented by dense `Tensor`.
   * @param numBuckets It is used if hashed_output is true.
   * output = hashed_value%num_buckets if num_buckets > 0 else hashed_value.
   * @param strongHash boolean, if true, siphash with salt will be used instead of farmhash.
   * @param salt Specify the salt that will be used by the siphash function.
   * @return a new instance of SparseCrossHashed
   */
  @Endpoint(describeByClass = true)
  public static SparseCrossHashed create(Scope scope, Iterable<Operand<TInt64>> indices, Iterable<Operand<?>> values, Iterable<Operand<TInt64>> shapes, Iterable<Operand<?>> denseInputs, Operand<TInt64> numBuckets, Operand<TBool> strongHash, Operand<TInt64> salt) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseCrossHashed", scope.makeOpName("SparseCrossHashed"));
    opBuilder.addInputList(Operands.asOutputs(indices));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInputList(Operands.asOutputs(shapes));
    opBuilder.addInputList(Operands.asOutputs(denseInputs));
    opBuilder.addInput(numBuckets.asOutput());
    opBuilder.addInput(strongHash.asOutput());
    opBuilder.addInput(salt.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseCrossHashed(opBuilder.build());
  }
  
  /**
   * 2-D.  Indices of the concatenated `SparseTensor`.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * 1-D.  Non-empty values of the concatenated or hashed
   * `SparseTensor`.
   */
  public Output<TInt64> outputValues() {
    return outputValues;
  }
  
  /**
   * 1-D.  Shape of the concatenated `SparseTensor`.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseCrossHashed";
  
  private Output<TInt64> outputIndices;
  private Output<TInt64> outputValues;
  private Output<TInt64> outputShape;
  
  private SparseCrossHashed(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }
}
