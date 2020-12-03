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

package org.tensorflow.op.strings;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Converts each string in the input Tensor to its hash mod by a number of buckets.
 * <p>
 * The hash function is deterministic on the content of the string within the
 * process and will never change. However, it is not suitable for cryptography.
 * This function may be used when CPU time is scarce and inputs are trusted or
 * unimportant. There is a risk of adversaries constructing inputs that all hash
 * to the same bucket. To prevent this problem, use a strong hash function with
 * `tf.string_to_hash_bucket_strong`.
 * <p>
 * Examples:
 * <p>
 * >>> tf.strings.to_hash_bucket_fast(["Hello", "TensorFlow", "2.x"], 3).numpy()
 * array([0, 2, 2])
 */
@Operator(group = "strings")
public final class ToHashBucketFast extends RawOp implements Operand<TInt64> {
  
  /**
   * Factory method to create a class wrapping a new ToHashBucketFast operation.
   * 
   * @param scope current scope
   * @param input The strings to assign a hash bucket.
   * @param numBuckets The number of buckets.
   * @return a new instance of ToHashBucketFast
   */
  @Endpoint(describeByClass = true)
  public static ToHashBucketFast create(Scope scope, Operand<TString> input, Long numBuckets) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringToHashBucketFast", scope.makeOpName("ToHashBucketFast"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num_buckets", numBuckets);
    return new ToHashBucketFast(opBuilder.build());
  }
  
  /**
   * A Tensor of the same shape as the input `string_tensor`.
   */
  public Output<TInt64> output() {
    return output;
  }
  
  @Override
  public Output<TInt64> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StringToHashBucketFast";
  
  private Output<TInt64> output;
  
  private ToHashBucketFast(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
