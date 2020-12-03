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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;

/**
 * Batches all input tensors nondeterministically.
 * <p>
 * When many instances of this Op are being run concurrently with the same
 * container/shared_name in the same device, some will output zero-shaped Tensors
 * and others will output Tensors of size up to max_batch_size.
 * <p>
 * All Tensors in in_tensors are batched together (so, for example, labels and
 * features should be batched with a single instance of this operation.
 * <p>
 * Each invocation of batch emits an `id` scalar which will be used to identify
 * this particular invocation when doing unbatch or its gradient.
 * <p>
 * Each op which emits a non-empty batch will also emit a non-empty batch_index
 * Tensor, which, is a [K, 3] matrix where each row contains the invocation's id,
 * start, and length of elements of each set of Tensors present in batched_tensors.
 * <p>
 * Batched tensors are concatenated along the first dimension, and all tensors in
 * in_tensors must have the first dimension of the same size.
 * <p>
 * in_tensors: The tensors to be batched.
 * num_batch_threads: Number of scheduling threads for processing batches of work.
 *  Determines the number of batches processed in parallel.
 * max_batch_size: Batch sizes will never be bigger than this.
 * batch_timeout_micros: Maximum number of microseconds to wait before outputting
 *  an incomplete batch.
 * allowed_batch_sizes: Optional list of allowed batch sizes. If left empty, does
 *  nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
 *  batches up to one of those sizes. The entries must increase monotonically, and
 *  the final entry must equal max_batch_size.
 * grad_timeout_micros: The timeout to use for the gradient. See Unbatch.
 * batched_tensors: Either empty tensors or a batch of concatenated Tensors.
 * batch_index: If out_tensors is non-empty, has information to invert it.
 * container: Controls the scope of sharing of this batch.
 * id: always contains a scalar with a unique ID for this invocation of Batch.
 * shared_name: Concurrently running instances of batch in the same device with the
 *  same container and shared_name will batch their elements together. If left
 *  empty, the op name will be used as the shared name.
 * T: the types of tensors to be batched.
 */
@Operator
public final class Batch extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Batch}
   */
  public static class Options {
    
    /**
     * @param maxEnqueuedBatches 
     */
    public Options maxEnqueuedBatches(Long maxEnqueuedBatches) {
      this.maxEnqueuedBatches = maxEnqueuedBatches;
      return this;
    }
    
    /**
     * @param allowedBatchSizes 
     */
    public Options allowedBatchSizes(List<Long> allowedBatchSizes) {
      this.allowedBatchSizes = allowedBatchSizes;
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
    
    /**
     * @param batchingQueue 
     */
    public Options batchingQueue(String batchingQueue) {
      this.batchingQueue = batchingQueue;
      return this;
    }
    
    private Long maxEnqueuedBatches;
    private List<Long> allowedBatchSizes;
    private String container;
    private String sharedName;
    private String batchingQueue;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Batch operation.
   * 
   * @param scope current scope
   * @param inTensors 
   * @param numBatchThreads 
   * @param maxBatchSize 
   * @param batchTimeoutMicros 
   * @param gradTimeoutMicros 
   * @param options carries optional attributes values
   * @return a new instance of Batch
   */
  @Endpoint(describeByClass = true)
  public static Batch create(Scope scope, Iterable<Operand<?>> inTensors, Long numBatchThreads, Long maxBatchSize, Long batchTimeoutMicros, Long gradTimeoutMicros, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Batch", scope.makeOpName("Batch"));
    opBuilder.addInputList(Operands.asOutputs(inTensors));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num_batch_threads", numBatchThreads);
    opBuilder.setAttr("max_batch_size", maxBatchSize);
    opBuilder.setAttr("batch_timeout_micros", batchTimeoutMicros);
    opBuilder.setAttr("grad_timeout_micros", gradTimeoutMicros);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxEnqueuedBatches != null) {
          opBuilder.setAttr("max_enqueued_batches", opts.maxEnqueuedBatches);
        }
        if (opts.allowedBatchSizes != null) {
          long[] allowedBatchSizesArray = new long[opts.allowedBatchSizes.size()];
          for (int i = 0; i < allowedBatchSizesArray.length; ++i) {
            allowedBatchSizesArray[i] = opts.allowedBatchSizes.get(i);
          }
          opBuilder.setAttr("allowed_batch_sizes", allowedBatchSizesArray);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.batchingQueue != null) {
          opBuilder.setAttr("batching_queue", opts.batchingQueue);
        }
      }
    }
    return new Batch(opBuilder.build());
  }
  
  /**
   * @param maxEnqueuedBatches 
   */
  public static Options maxEnqueuedBatches(Long maxEnqueuedBatches) {
    return new Options().maxEnqueuedBatches(maxEnqueuedBatches);
  }
  
  /**
   * @param allowedBatchSizes 
   */
  public static Options allowedBatchSizes(List<Long> allowedBatchSizes) {
    return new Options().allowedBatchSizes(allowedBatchSizes);
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
   * @param batchingQueue 
   */
  public static Options batchingQueue(String batchingQueue) {
    return new Options().batchingQueue(batchingQueue);
  }
  
  /**
   */
  public List<Output<?>> batchedTensors() {
    return batchedTensors;
  }
  
  /**
   */
  public Output<TInt64> batchIndex() {
    return batchIndex;
  }
  
  /**
   */
  public Output<TInt64> id() {
    return id;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Batch";
  
  private List<Output<?>> batchedTensors;
  private Output<TInt64> batchIndex;
  private Output<TInt64> id;
  
  private Batch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int batchedTensorsLength = operation.outputListLength("batched_tensors");
    batchedTensors = Arrays.asList(operation.outputList(outputIdx, batchedTensorsLength));
    outputIdx += batchedTensorsLength;
    batchIndex = operation.output(outputIdx++);
    id = operation.output(outputIdx++);
  }
}
