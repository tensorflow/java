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

package org.tensorflow.op.data.experimental;

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
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that uses a custom thread pool to compute {@code input_dataset}.
 */
@OpMetadata(
    opType = ThreadPoolHandle.OP_NAME,
    inputsClass = ThreadPoolHandle.Inputs.class
)
public final class ThreadPoolHandle extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalThreadPoolHandle";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ThreadPoolHandle(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalThreadPoolHandle operation.
   *
   * @param scope current scope
   * @param numThreads The number of threads in the thread pool.
   * @param displayName A human-readable name for the threads that may be visible in some
   * visualizations.
   * threadpool.
   * @param options carries optional attribute values
   * @return a new instance of ThreadPoolHandle
   */
  @Endpoint(
      describeByClass = true
  )
  public static ThreadPoolHandle create(Scope scope, Long numThreads, String displayName,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ThreadPoolHandle");
    opBuilder.setAttr("num_threads", numThreads);
    opBuilder.setAttr("display_name", displayName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxIntraOpParallelism != null) {
          opBuilder.setAttr("max_intra_op_parallelism", opts.maxIntraOpParallelism);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new ThreadPoolHandle(opBuilder.build());
  }

  /**
   * Sets the maxIntraOpParallelism option.
   *
   * @param maxIntraOpParallelism The maximum degree of parallelism to use within operations that execute on this
   * threadpool.
   * @return this Options instance.
   */
  public static Options maxIntraOpParallelism(Long maxIntraOpParallelism) {
    return new Options().maxIntraOpParallelism(maxIntraOpParallelism);
  }

  /**
   * Sets the container option.
   *
   * @param container the container option
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName the sharedName option
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Gets handle.
   * A resource that can be consumed by one or more ExperimentalThreadPoolDataset
   * ops.
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.experimental.ThreadPoolHandle}
   */
  public static class Options {
    private Long maxIntraOpParallelism;

    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the maxIntraOpParallelism option.
     *
     * @param maxIntraOpParallelism The maximum degree of parallelism to use within operations that execute on this
     * threadpool.
     * @return this Options instance.
     */
    public Options maxIntraOpParallelism(Long maxIntraOpParallelism) {
      this.maxIntraOpParallelism = maxIntraOpParallelism;
      return this;
    }

    /**
     * Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ThreadPoolHandle.class
  )
  public static class Inputs extends RawOpInputs<ThreadPoolHandle> {
    /**
     * The number of threads in the thread pool.
     */
    public final long numThreads;

    /**
     * The maximum degree of parallelism to use within operations that execute on this
     * threadpool.
     */
    public final long maxIntraOpParallelism;

    /**
     * A human-readable name for the threads that may be visible in some
     * visualizations.
     * threadpool.
     */
    public final String displayName;

    /**
     * The container attribute
     */
    public final String container;

    /**
     * The sharedName attribute
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new ThreadPoolHandle(op), op, Arrays.asList("num_threads", "max_intra_op_parallelism", "display_name", "container", "shared_name"));
      int inputIndex = 0;
      numThreads = op.attributes().getAttrInt("num_threads");
      maxIntraOpParallelism = op.attributes().getAttrInt("max_intra_op_parallelism");
      displayName = op.attributes().getAttrString("display_name");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
