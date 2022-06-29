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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Converts each string in the input Tensor to its hash mod by a number of buckets.
 * The hash function is deterministic on the content of the string within the
 * process. The hash function is a keyed hash function, where attribute {@code key}
 * defines the key of the hash function. {@code key} is an array of 2 elements.
 * <p>A strong hash is important when inputs may be malicious, e.g. URLs with
 * additional components. Adversaries could try to make their inputs hash to the
 * same bucket for a denial-of-service attack or to skew the results. A strong
 * hash can be used to make it difficult to find inputs with a skewed hash value
 * distribution over buckets. This requires that the hash function is
 * seeded by a high-entropy (random) &quot;key&quot; unknown to the adversary.
 * <p>The additional robustness comes at a cost of roughly 4x higher compute
 * time than {@code tf.string_to_hash_bucket_fast}.
 * <p>Examples:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>tf.strings.to_hash_bucket_strong([&quot;Hello&quot;, &quot;TF&quot;], 3, [1, 2]).numpy()
 * array([2, 0])
 * </blockquote>
 * </blockquote>
 * </blockquote>
 */
@OpMetadata(
    opType = ToHashBucketStrong.OP_NAME,
    inputsClass = ToHashBucketStrong.Inputs.class
)
@Operator(
    group = "strings"
)
public final class ToHashBucketStrong extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringToHashBucketStrong";

  private Output<TInt64> output;

  public ToHashBucketStrong(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringToHashBucketStrong operation.
   *
   * @param scope current scope
   * @param input The strings to assign a hash bucket.
   * @param numBuckets The number of buckets.
   * @param key The key used to seed the hash function, passed as a list of two uint64
   * elements.
   * @return a new instance of ToHashBucketStrong
   */
  @Endpoint(
      describeByClass = true
  )
  public static ToHashBucketStrong create(Scope scope, Operand<TString> input, Long numBuckets,
      List<Long> key) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ToHashBucketStrong");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("num_buckets", numBuckets);
    long[] keyArray = new long[key.size()];
    for (int i = 0 ; i < keyArray.length ; i++) {
      keyArray[i] = key.get(i);
    }
    opBuilder.setAttr("key", keyArray);
    return new ToHashBucketStrong(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = ToHashBucketStrong.class
  )
  public static class Inputs extends RawOpInputs<ToHashBucketStrong> {
    /**
     * The strings to assign a hash bucket.
     */
    public final Operand<TString> input;

    /**
     * The number of buckets.
     */
    public final long numBuckets;

    /**
     * The key used to seed the hash function, passed as a list of two uint64
     * elements.
     */
    public final long[] key;

    public Inputs(GraphOperation op) {
      super(new ToHashBucketStrong(op), op, Arrays.asList("num_buckets", "key"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      numBuckets = op.attributes().getAttrInt("num_buckets");
      key = op.attributes().getAttrIntList("key");
    }
  }
}
