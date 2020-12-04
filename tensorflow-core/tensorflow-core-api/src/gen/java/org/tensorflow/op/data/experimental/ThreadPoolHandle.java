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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that uses a custom thread pool to compute `input_dataset`.
 */
public final class ThreadPoolHandle extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.experimental.ThreadPoolHandle}
   */
  public static class Options {
    
    /**
     * @param maxIntraOpParallelism The maximum degree of parallelism to use within operations that execute on this
     * threadpool.
     */
    public Options maxIntraOpParallelism(Long maxIntraOpParallelism) {
      this.maxIntraOpParallelism = maxIntraOpParallelism;
      return this;
    }
    
    /**
     * @param container 
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName 
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    private Long maxIntraOpParallelism;
    private String container;
    private String sharedName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ThreadPoolHandle operation.
   * 
   * @param scope current scope
   * @param numThreads The number of threads in the thread pool.
   * @param displayName A human-readable name for the threads that may be visible in some
   * visualizations.
   * threadpool.
   * @param options carries optional attributes values
   * @return a new instance of ThreadPoolHandle
   */
  @Endpoint(describeByClass = true)
  public static ThreadPoolHandle create(Scope scope, Long numThreads, String displayName, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalThreadPoolHandle", scope.makeOpName("ThreadPoolHandle"));
    opBuilder = scope.apply(opBuilder);
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
   * @param maxIntraOpParallelism The maximum degree of parallelism to use within operations that execute on this
   * threadpool.
   */
  public static Options maxIntraOpParallelism(Long maxIntraOpParallelism) {
    return new Options().maxIntraOpParallelism(maxIntraOpParallelism);
  }
  
  /**
   * @param container 
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName 
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * A resource that can be consumed by one or more ExperimentalThreadPoolDataset
   * ops.
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ExperimentalThreadPoolHandle";
  
  private Output<?> handle;
  
  private ThreadPoolHandle(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
