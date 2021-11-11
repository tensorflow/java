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
import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Batches all the inputs tensors to the computation done by the function.
 * So, for example, in the following code
 * <pre>
 *
 * # This input will be captured.
 * y = tf.placeholder_with_default(1.0, shape=[])
 *
 * {@literal @}tf.Defun(tf.float32)
 * def computation(a):
 *   return tf.matmul(a, a) + y
 *
 * b = gen_batch_ops.batch_function(
 *         f=computation
 *         in_tensors=[a],
 *         captured_tensors=computation.captured_inputs,
 *         Tout=[o.type for o in computation.definition.signature.output_arg],
 *         num_batch_threads=1,
 *         max_batch_size=10,
 *         batch_timeout_micros=100000,  # 100ms
 *         allowed_batch_sizes=[3, 10],
 *         batching_queue=&quot;&quot;)
 * </pre>
 * <p>If more than one session.run call is simultaneously trying to compute {@code b}
 * the values of {@code a} will be gathered, non-deterministically concatenated
 * along the first axis, and only one thread will run the computation.
 * <p>Assumes that all arguments of the function are Tensors which will be batched
 * along their first dimension.
 * <p>Arguments that are captured, are not batched. The session.run call which does
 * the concatenation, will use the values of the captured tensors available to it.
 * Therefore, typical uses of captured tensors should involve values which remain
 * unchanged across session.run calls. Inference is a good example of this.
 * <p>SparseTensor is not supported. The return value of the decorated function
 * must be a Tensor or a list/tuple of Tensors.
 */
@OpMetadata(
    opType = BatchFunction.OP_NAME,
    inputsClass = BatchFunction.Inputs.class
)
@Operator
public final class BatchFunction extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchFunction";

  private List<Output<?>> outTensors;

  @SuppressWarnings("unchecked")
  public BatchFunction(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outTensorsLength = operation.outputListLength("out_tensors");
    outTensors = Arrays.asList(operation.outputList(outputIdx, outTensorsLength));
    outputIdx += outTensorsLength;
  }

  /**
   * Factory method to create a class wrapping a new BatchFunction operation.
   *
   * @param scope current scope
   * @param inTensors The tensors to be batched.
   * @param capturedTensors The tensors which are captured in the function, and don't need
   * to be batched.
   * @param f The value of the f attribute
   * @param numBatchThreads Number of scheduling threads for processing batches of work.
   * Determines the number of batches processed in parallel.
   * @param maxBatchSize Batch sizes will never be bigger than this.
   * @param batchTimeoutMicros Maximum number of microseconds to wait before outputting
   * an incomplete batch.
   * @param Tout the types of the output tensors.
   * @param options carries optional attribute values
   * @return a new instance of BatchFunction
   */
  @Endpoint(
      describeByClass = true
  )
  public static BatchFunction create(Scope scope, Iterable<Operand<?>> inTensors,
      Iterable<Operand<?>> capturedTensors, ConcreteFunction f, Long numBatchThreads,
      Long maxBatchSize, Long batchTimeoutMicros, List<Class<? extends TType>> Tout,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchFunction");
    opBuilder.addInputList(Operands.asOutputs(inTensors));
    opBuilder.addInputList(Operands.asOutputs(capturedTensors));
    opBuilder.setAttr("f", f);
    opBuilder.setAttr("num_batch_threads", numBatchThreads);
    opBuilder.setAttr("max_batch_size", maxBatchSize);
    opBuilder.setAttr("batch_timeout_micros", batchTimeoutMicros);
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxEnqueuedBatches != null) {
          opBuilder.setAttr("max_enqueued_batches", opts.maxEnqueuedBatches);
        }
        if (opts.allowedBatchSizes != null) {
          long[] allowedBatchSizesArray = new long[opts.allowedBatchSizes.size()];
          for (int i = 0 ; i < allowedBatchSizesArray.length ; i++) {
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
        if (opts.enableLargeBatchSplitting != null) {
          opBuilder.setAttr("enable_large_batch_splitting", opts.enableLargeBatchSplitting);
        }
      }
    }
    return new BatchFunction(opBuilder.build());
  }

  /**
   * Sets the maxEnqueuedBatches option.
   *
   * @param maxEnqueuedBatches Maximum number of batches enqueued. Default: 10.
   * @return this Options instance.
   */
  public static Options maxEnqueuedBatches(Long maxEnqueuedBatches) {
    return new Options().maxEnqueuedBatches(maxEnqueuedBatches);
  }

  /**
   * Sets the allowedBatchSizes option.
   *
   * @param allowedBatchSizes Optional list of allowed batch sizes. If left empty, does
   * nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
   * batches up to one of those sizes. The entries must increase monotonically.
   * If enable_large_batch_splitting is false (i.e., large-input-split is not
   * enabled) the final entry must equal max_batch_size.
   * @return this Options instance.
   */
  public static Options allowedBatchSizes(List<Long> allowedBatchSizes) {
    return new Options().allowedBatchSizes(allowedBatchSizes);
  }

  /**
   * Sets the allowedBatchSizes option.
   *
   * @param allowedBatchSizes Optional list of allowed batch sizes. If left empty, does
   * nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
   * batches up to one of those sizes. The entries must increase monotonically.
   * If enable_large_batch_splitting is false (i.e., large-input-split is not
   * enabled) the final entry must equal max_batch_size.
   * @return this Options instance.
   */
  public static Options allowedBatchSizes(Long... allowedBatchSizes) {
    return new Options().allowedBatchSizes(allowedBatchSizes);
  }

  /**
   * Sets the container option.
   *
   * @param container Controls the scope of sharing of this batch.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName Concurrently running instances of batch in the same device with the
   * same container and shared_name will batch their elements together. If left
   * empty, the op name will be used as the shared name.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Sets the batchingQueue option.
   *
   * @param batchingQueue the batchingQueue option
   * @return this Options instance.
   */
  public static Options batchingQueue(String batchingQueue) {
    return new Options().batchingQueue(batchingQueue);
  }

  /**
   * Sets the enableLargeBatchSplitting option.
   *
   * @param enableLargeBatchSplitting input with a large size (i.e., larger than the largest value of
   * {@code allowed_batch_sizes}) will be splitted into multiple batches with batch size.
   * @return this Options instance.
   */
  public static Options enableLargeBatchSplitting(Boolean enableLargeBatchSplitting) {
    return new Options().enableLargeBatchSplitting(enableLargeBatchSplitting);
  }

  /**
   * Gets outTensors.
   * The output tensors.
   * @return outTensors.
   */
  public List<Output<?>> outTensors() {
    return outTensors;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) outTensors.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.BatchFunction}
   */
  public static class Options {
    private Long maxEnqueuedBatches;

    private List<Long> allowedBatchSizes;

    private String container;

    private String sharedName;

    private String batchingQueue;

    private Boolean enableLargeBatchSplitting;

    private Options() {
    }

    /**
     * Sets the maxEnqueuedBatches option.
     *
     * @param maxEnqueuedBatches Maximum number of batches enqueued. Default: 10.
     * @return this Options instance.
     */
    public Options maxEnqueuedBatches(Long maxEnqueuedBatches) {
      this.maxEnqueuedBatches = maxEnqueuedBatches;
      return this;
    }

    /**
     * Sets the allowedBatchSizes option.
     *
     * @param allowedBatchSizes Optional list of allowed batch sizes. If left empty, does
     * nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
     * batches up to one of those sizes. The entries must increase monotonically.
     * If enable_large_batch_splitting is false (i.e., large-input-split is not
     * enabled) the final entry must equal max_batch_size.
     * @return this Options instance.
     */
    public Options allowedBatchSizes(List<Long> allowedBatchSizes) {
      this.allowedBatchSizes = allowedBatchSizes;
      return this;
    }

    /**
     * Sets the allowedBatchSizes option.
     *
     * @param allowedBatchSizes Optional list of allowed batch sizes. If left empty, does
     * nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
     * batches up to one of those sizes. The entries must increase monotonically.
     * If enable_large_batch_splitting is false (i.e., large-input-split is not
     * enabled) the final entry must equal max_batch_size.
     * @return this Options instance.
     */
    public Options allowedBatchSizes(Long... allowedBatchSizes) {
      this.allowedBatchSizes = Arrays.asList(allowedBatchSizes);
      return this;
    }

    /**
     * Sets the container option.
     *
     * @param container Controls the scope of sharing of this batch.
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName Concurrently running instances of batch in the same device with the
     * same container and shared_name will batch their elements together. If left
     * empty, the op name will be used as the shared name.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }

    /**
     * Sets the batchingQueue option.
     *
     * @param batchingQueue the batchingQueue option
     * @return this Options instance.
     */
    public Options batchingQueue(String batchingQueue) {
      this.batchingQueue = batchingQueue;
      return this;
    }

    /**
     * Sets the enableLargeBatchSplitting option.
     *
     * @param enableLargeBatchSplitting input with a large size (i.e., larger than the largest value of
     * {@code allowed_batch_sizes}) will be splitted into multiple batches with batch size.
     * @return this Options instance.
     */
    public Options enableLargeBatchSplitting(Boolean enableLargeBatchSplitting) {
      this.enableLargeBatchSplitting = enableLargeBatchSplitting;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BatchFunction.class
  )
  public static class Inputs extends RawOpInputs<BatchFunction> {
    /**
     * The tensors to be batched.
     */
    public final Iterable<Operand<?>> inTensors;

    /**
     * The tensors which are captured in the function, and don't need
     * to be batched.
     */
    public final Iterable<Operand<?>> capturedTensors;

    /**
     * Number of scheduling threads for processing batches of work.
     * Determines the number of batches processed in parallel.
     */
    public final long numBatchThreads;

    /**
     * Batch sizes will never be bigger than this.
     */
    public final long maxBatchSize;

    /**
     * Maximum number of microseconds to wait before outputting
     * an incomplete batch.
     */
    public final long batchTimeoutMicros;

    /**
     * Maximum number of batches enqueued. Default: 10.
     */
    public final long maxEnqueuedBatches;

    /**
     * Optional list of allowed batch sizes. If left empty, does
     * nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
     * batches up to one of those sizes. The entries must increase monotonically.
     * If enable_large_batch_splitting is false (i.e., large-input-split is not
     * enabled) the final entry must equal max_batch_size.
     */
    public final long[] allowedBatchSizes;

    /**
     * Controls the scope of sharing of this batch.
     */
    public final String container;

    /**
     * Concurrently running instances of batch in the same device with the
     * same container and shared_name will batch their elements together. If left
     * empty, the op name will be used as the shared name.
     */
    public final String sharedName;

    /**
     * The batchingQueue attribute
     */
    public final String batchingQueue;

    /**
     * the types of tensors to be batched.
     */
    public final DataType[] Tin;

    /**
     * the types of the captured tensors.
     */
    public final DataType[] Tcaptured;

    /**
     * the types of the output tensors.
     */
    public final DataType[] Tout;

    /**
     * input with a large size (i.e., larger than the largest value of
     * `allowed_batch_sizes`) will be splitted into multiple batches with batch size.
     */
    public final boolean enableLargeBatchSplitting;

    public Inputs(GraphOperation op) {
      super(new BatchFunction(op), op, Arrays.asList("num_batch_threads", "max_batch_size", "batch_timeout_micros", "max_enqueued_batches", "allowed_batch_sizes", "container", "shared_name", "batching_queue", "Tin", "Tcaptured", "Tout", "enable_large_batch_splitting"));
      int inputIndex = 0;
      int inTensorsLength = op.inputListLength("in_tensors");
      inTensors = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inTensorsLength));
      inputIndex += inTensorsLength;
      int capturedTensorsLength = op.inputListLength("captured_tensors");
      capturedTensors = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, capturedTensorsLength));
      inputIndex += capturedTensorsLength;
      numBatchThreads = op.attributes().getAttrInt("num_batch_threads");
      maxBatchSize = op.attributes().getAttrInt("max_batch_size");
      batchTimeoutMicros = op.attributes().getAttrInt("batch_timeout_micros");
      maxEnqueuedBatches = op.attributes().getAttrInt("max_enqueued_batches");
      allowedBatchSizes = op.attributes().getAttrIntList("allowed_batch_sizes");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      batchingQueue = op.attributes().getAttrString("batching_queue");
      Tin = op.attributes().getAttrTypeList("Tin");
      Tcaptured = op.attributes().getAttrTypeList("Tcaptured");
      Tout = op.attributes().getAttrTypeList("Tout");
      enableLargeBatchSplitting = op.attributes().getAttrBool("enable_large_batch_splitting");
    }
  }
}
