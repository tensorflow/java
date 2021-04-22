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
 * The hash function is deterministic on the content of the string within the
 * process.
 * <p>Note that the hash function may change from time to time.
 * This functionality will be deprecated and it's recommended to use
 * {@code tf.string_to_hash_bucket_fast()} or {@code tf.string_to_hash_bucket_strong()}.
 */
@Operator(
    group = "strings"
)
public final class ToHashBucket extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringToHashBucket";

  private Output<TInt64> output;

  private ToHashBucket(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringToHashBucket operation.
   *
   * @param scope current scope
   * @param stringTensor the stringTensor value
   * @param numBuckets The number of buckets.
   * @return a new instance of ToHashBucket
   */
  @Endpoint(
      describeByClass = true
  )
  public static ToHashBucket create(Scope scope, Operand<TString> stringTensor, Long numBuckets) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringToHashBucket", scope.makeOpName("ToHashBucket"));
    opBuilder.addInput(stringTensor.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num_buckets", numBuckets);
    return new ToHashBucket(opBuilder.build());
  }

  /**
   * Gets output.
   * A Tensor of the same shape as the input {@code string_tensor}.
   * @return output.
   */
  public Output<TInt64> output() {
    return output;
  }

  @Override
  public Output<TInt64> asOutput() {
    return output;
  }
}
