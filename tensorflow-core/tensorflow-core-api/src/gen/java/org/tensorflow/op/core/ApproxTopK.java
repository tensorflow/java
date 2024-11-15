/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Returns min/max k values and their indices of the input operand in an approximate manner.
 * See https://arxiv.org/abs/2206.14286 for the algorithm details.
 * This op is only optimized on TPU currently.
 */
@OpMetadata(
    opType = ApproxTopK.OP_NAME,
    inputsClass = ApproxTopK.Inputs.class
)
@Operator
public final class ApproxTopK<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ApproxTopK";

  private Output<T> values;

  private Output<TInt32> indices;

  public ApproxTopK(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    values = operation.output(outputIdx++);
    indices = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ApproxTopK operation.
   *
   * @param scope current scope
   * @param input Array to search. Must be at least 1-D of the floating type
   * @param k Specifies the number of min/max-k.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApproxTopK} output and operands
   * @return a new instance of ApproxTopK
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ApproxTopK<T> create(Scope scope, Operand<T> input, Long k,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ApproxTopK");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("k", k);
    if (options != null) {
      for (Options opts : options) {
        if (opts.reductionDimension != null) {
          opBuilder.setAttr("reduction_dimension", opts.reductionDimension);
        }
        if (opts.recallTarget != null) {
          opBuilder.setAttr("recall_target", opts.recallTarget);
        }
        if (opts.isMaxK != null) {
          opBuilder.setAttr("is_max_k", opts.isMaxK);
        }
        if (opts.reductionInputSizeOverride != null) {
          opBuilder.setAttr("reduction_input_size_override", opts.reductionInputSizeOverride);
        }
        if (opts.aggregateToTopk != null) {
          opBuilder.setAttr("aggregate_to_topk", opts.aggregateToTopk);
        }
      }
    }
    return new ApproxTopK<>(opBuilder.build());
  }

  /**
   * Sets the reductionDimension option.
   *
   * @param reductionDimension Integer dimension along which to search. Default: -1.
   * @return this Options instance.
   */
  public static Options reductionDimension(Long reductionDimension) {
    return new Options().reductionDimension(reductionDimension);
  }

  /**
   * Sets the recallTarget option.
   *
   * @param recallTarget Recall target for the approximation. Range in (0,1]
   * @return this Options instance.
   */
  public static Options recallTarget(Float recallTarget) {
    return new Options().recallTarget(recallTarget);
  }

  /**
   * Sets the isMaxK option.
   *
   * @param isMaxK When true, computes max-k; otherwise computes min-k.
   * @return this Options instance.
   */
  public static Options isMaxK(Boolean isMaxK) {
    return new Options().isMaxK(isMaxK);
  }

  /**
   * Sets the reductionInputSizeOverride option.
   *
   * @param reductionInputSizeOverride When set to a positive value, it overrides the size determined by
   * {@code input[reduction_dim]} for evaluating the recall. This option is useful when
   * the given {@code input} is only a subset of the overall computation in SPMD or
   * distributed pipelines, where the true input size cannot be deferred by the
   * {@code input} shape.
   * @return this Options instance.
   */
  public static Options reductionInputSizeOverride(Long reductionInputSizeOverride) {
    return new Options().reductionInputSizeOverride(reductionInputSizeOverride);
  }

  /**
   * Sets the aggregateToTopk option.
   *
   * @param aggregateToTopk When true, aggregates approximate results to top-k. When false, returns the
   * approximate results. The number of the approximate results is implementation
   * defined and is greater equals to the specified {@code k}.
   * @return this Options instance.
   */
  public static Options aggregateToTopk(Boolean aggregateToTopk) {
    return new Options().aggregateToTopk(aggregateToTopk);
  }

  /**
   * Gets values.
   * The min/max k values along the {@code reduction_dimension} of the {@code input} operand.
   * The dimension are the same as the {@code input} operand except for the
   * {@code reduction_dimension}: when {@code aggregate_to_topk} is true, the reduction
   * dimension is {@code k}; otherwise, it is greater equals to {@code k} where the size is
   * implementation-defined.
   * @return values.
   */
  public Output<T> values() {
    return values;
  }

  /**
   * Gets indices.
   * The indices of {@code values} along the {@code reduction_dimension} of the {@code input} operand.
   * @return indices.
   */
  public Output<TInt32> indices() {
    return indices;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.ApproxTopK}
   */
  public static class Options {
    private Long reductionDimension;

    private Float recallTarget;

    private Boolean isMaxK;

    private Long reductionInputSizeOverride;

    private Boolean aggregateToTopk;

    private Options() {
    }

    /**
     * Sets the reductionDimension option.
     *
     * @param reductionDimension Integer dimension along which to search. Default: -1.
     * @return this Options instance.
     */
    public Options reductionDimension(Long reductionDimension) {
      this.reductionDimension = reductionDimension;
      return this;
    }

    /**
     * Sets the recallTarget option.
     *
     * @param recallTarget Recall target for the approximation. Range in (0,1]
     * @return this Options instance.
     */
    public Options recallTarget(Float recallTarget) {
      this.recallTarget = recallTarget;
      return this;
    }

    /**
     * Sets the isMaxK option.
     *
     * @param isMaxK When true, computes max-k; otherwise computes min-k.
     * @return this Options instance.
     */
    public Options isMaxK(Boolean isMaxK) {
      this.isMaxK = isMaxK;
      return this;
    }

    /**
     * Sets the reductionInputSizeOverride option.
     *
     * @param reductionInputSizeOverride When set to a positive value, it overrides the size determined by
     * {@code input[reduction_dim]} for evaluating the recall. This option is useful when
     * the given {@code input} is only a subset of the overall computation in SPMD or
     * distributed pipelines, where the true input size cannot be deferred by the
     * {@code input} shape.
     * @return this Options instance.
     */
    public Options reductionInputSizeOverride(Long reductionInputSizeOverride) {
      this.reductionInputSizeOverride = reductionInputSizeOverride;
      return this;
    }

    /**
     * Sets the aggregateToTopk option.
     *
     * @param aggregateToTopk When true, aggregates approximate results to top-k. When false, returns the
     * approximate results. The number of the approximate results is implementation
     * defined and is greater equals to the specified {@code k}.
     * @return this Options instance.
     */
    public Options aggregateToTopk(Boolean aggregateToTopk) {
      this.aggregateToTopk = aggregateToTopk;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ApproxTopK.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<ApproxTopK<T>> {
    /**
     * Array to search. Must be at least 1-D of the floating type
     */
    public final Operand<T> input;

    /**
     * Specifies the number of min/max-k.
     */
    public final long k;

    /**
     * Integer dimension along which to search. Default: -1.
     */
    public final long reductionDimension;

    /**
     * Recall target for the approximation. Range in (0,1]
     */
    public final float recallTarget;

    /**
     * When true, computes max-k; otherwise computes min-k.
     */
    public final boolean isMaxK;

    /**
     * When set to a positive value, it overrides the size determined by
     * {@code input[reduction_dim]} for evaluating the recall. This option is useful when
     * the given {@code input} is only a subset of the overall computation in SPMD or
     * distributed pipelines, where the true input size cannot be deferred by the
     * {@code input} shape.
     */
    public final long reductionInputSizeOverride;

    /**
     * When true, aggregates approximate results to top-k. When false, returns the
     * approximate results. The number of the approximate results is implementation
     * defined and is greater equals to the specified {@code k}.
     */
    public final boolean aggregateToTopk;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new ApproxTopK<>(op), op, Arrays.asList("k", "reduction_dimension", "recall_target", "is_max_k", "reduction_input_size_override", "aggregate_to_topk", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      k = op.attributes().getAttrInt("k");
      reductionDimension = op.attributes().getAttrInt("reduction_dimension");
      recallTarget = op.attributes().getAttrFloat("recall_target");
      isMaxK = op.attributes().getAttrBool("is_max_k");
      reductionInputSizeOverride = op.attributes().getAttrInt("reduction_input_size_override");
      aggregateToTopk = op.attributes().getAttrBool("aggregate_to_topk");
      T = op.attributes().getAttrType("T");
    }
  }
}
