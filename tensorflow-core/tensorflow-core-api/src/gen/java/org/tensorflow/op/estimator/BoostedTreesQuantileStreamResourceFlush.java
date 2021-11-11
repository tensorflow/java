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

package org.tensorflow.op.estimator;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Flush the summaries for a quantile stream resource.
 * An op that flushes the summaries for a quantile stream resource.
 */
@OpMetadata(
    opType = BoostedTreesQuantileStreamResourceFlush.OP_NAME,
    inputsClass = BoostedTreesQuantileStreamResourceFlush.Inputs.class
)
public final class BoostedTreesQuantileStreamResourceFlush extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesQuantileStreamResourceFlush";

  public BoostedTreesQuantileStreamResourceFlush(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesQuantileStreamResourceFlush operation.
   *
   * @param scope current scope
   * @param quantileStreamResourceHandle resource handle referring to a QuantileStreamResource.
   * @param numBuckets int; approximate number of buckets unless using generate_quantiles.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesQuantileStreamResourceFlush
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesQuantileStreamResourceFlush create(Scope scope,
      Operand<? extends TType> quantileStreamResourceHandle, Operand<TInt64> numBuckets,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesQuantileStreamResourceFlush");
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.addInput(numBuckets.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.generateQuantiles != null) {
          opBuilder.setAttr("generate_quantiles", opts.generateQuantiles);
        }
      }
    }
    return new BoostedTreesQuantileStreamResourceFlush(opBuilder.build());
  }

  /**
   * Sets the generateQuantiles option.
   *
   * @param generateQuantiles bool; If True, the output will be the num_quantiles for each stream where the ith
   * entry is the ith quantile of the input with an approximation error of epsilon.
   * Duplicate values may be present.
   * If False, the output will be the points in the histogram that we got which roughly
   * translates to 1/epsilon boundaries and without any duplicates.
   * Default to False.
   * @return this Options instance.
   */
  public static Options generateQuantiles(Boolean generateQuantiles) {
    return new Options().generateQuantiles(generateQuantiles);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.estimator.BoostedTreesQuantileStreamResourceFlush}
   */
  public static class Options {
    private Boolean generateQuantiles;

    private Options() {
    }

    /**
     * Sets the generateQuantiles option.
     *
     * @param generateQuantiles bool; If True, the output will be the num_quantiles for each stream where the ith
     * entry is the ith quantile of the input with an approximation error of epsilon.
     * Duplicate values may be present.
     * If False, the output will be the points in the histogram that we got which roughly
     * translates to 1/epsilon boundaries and without any duplicates.
     * Default to False.
     * @return this Options instance.
     */
    public Options generateQuantiles(Boolean generateQuantiles) {
      this.generateQuantiles = generateQuantiles;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesQuantileStreamResourceFlush.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesQuantileStreamResourceFlush> {
    /**
     * resource handle referring to a QuantileStreamResource.
     */
    public final Operand<? extends TType> quantileStreamResourceHandle;

    /**
     * int; approximate number of buckets unless using generate_quantiles.
     */
    public final Operand<TInt64> numBuckets;

    /**
     * bool; If True, the output will be the num_quantiles for each stream where the ith
     * entry is the ith quantile of the input with an approximation error of epsilon.
     * Duplicate values may be present.
     * If False, the output will be the points in the histogram that we got which roughly
     * translates to 1/epsilon boundaries and without any duplicates.
     * Default to False.
     */
    public final boolean generateQuantiles;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesQuantileStreamResourceFlush(op), op, Arrays.asList("generate_quantiles"));
      int inputIndex = 0;
      quantileStreamResourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
      numBuckets = (Operand<TInt64>) op.input(inputIndex++);
      generateQuantiles = op.attributes().getAttrBool("generate_quantiles");
    }
  }
}
