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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Reverses variable length slices.
 * <p>
 * This op first slices `input` along the dimension `batch_dim`, and for each
 * slice `i`, reverses the first `seq_lengths[i]` elements along
 * the dimension `seq_dim`.
 * <p>
 * The elements of `seq_lengths` must obey `seq_lengths[i] <= input.dims[seq_dim]`,
 * and `seq_lengths` must be a vector of length `input.dims[batch_dim]`.
 * <p>
 * The output slice `i` along dimension `batch_dim` is then given by input
 * slice `i`, with the first `seq_lengths[i]` slices along dimension
 * `seq_dim` reversed.
 * <p>
 * For example:
 * <pre>{@code
 * # Given this:
 * batch_dim = 0
 * seq_dim = 1
 * input.dims = (4, 8, ...)
 * seq_lengths = [7, 2, 3, 5]
 * 
 * # then slices of input are reversed on seq_dim, but only up to seq_lengths:
 * output[0, 0:7, :, ...] = input[0, 7:0:-1, :, ...]
 * output[1, 0:2, :, ...] = input[1, 2:0:-1, :, ...]
 * output[2, 0:3, :, ...] = input[2, 3:0:-1, :, ...]
 * output[3, 0:5, :, ...] = input[3, 5:0:-1, :, ...]
 * 
 * # while entries past seq_lens are copied through:
 * output[0, 7:, :, ...] = input[0, 7:, :, ...]
 * output[1, 2:, :, ...] = input[1, 2:, :, ...]
 * output[2, 3:, :, ...] = input[2, 3:, :, ...]
 * output[3, 2:, :, ...] = input[3, 2:, :, ...]
 * }</pre>
 * In contrast, if:
 * <pre>{@code
 * # Given this:
 * batch_dim = 2
 * seq_dim = 0
 * input.dims = (8, ?, 4, ...)
 * seq_lengths = [7, 2, 3, 5]
 * 
 * # then slices of input are reversed on seq_dim, but only up to seq_lengths:
 * output[0:7, :, 0, :, ...] = input[7:0:-1, :, 0, :, ...]
 * output[0:2, :, 1, :, ...] = input[2:0:-1, :, 1, :, ...]
 * output[0:3, :, 2, :, ...] = input[3:0:-1, :, 2, :, ...]
 * output[0:5, :, 3, :, ...] = input[5:0:-1, :, 3, :, ...]
 * 
 * # while entries past seq_lens are copied through:
 * output[7:, :, 0, :, ...] = input[7:, :, 0, :, ...]
 * output[2:, :, 1, :, ...] = input[2:, :, 1, :, ...]
 * output[3:, :, 2, :, ...] = input[3:, :, 2, :, ...]
 * output[2:, :, 3, :, ...] = input[2:, :, 3, :, ...]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class ReverseSequence<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.ReverseSequence}
   */
  public static class Options {
    
    /**
     * @param batchDim The dimension along which reversal is performed.
     */
    public Options batchDim(Long batchDim) {
      this.batchDim = batchDim;
      return this;
    }
    
    private Long batchDim;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ReverseSequence operation.
   * 
   * @param scope current scope
   * @param input The input to reverse.
   * @param seqLengths 1-D with length `input.dims(batch_dim)` and
   * `max(seq_lengths) <= input.dims(seq_dim)`
   * @param seqDim The dimension which is partially reversed.
   * @param options carries optional attributes values
   * @return a new instance of ReverseSequence
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> ReverseSequence<T> create(Scope scope, Operand<T> input, Operand<U> seqLengths, Long seqDim, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ReverseSequence", scope.makeOpName("ReverseSequence"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(seqLengths.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("seq_dim", seqDim);
    if (options != null) {
      for (Options opts : options) {
        if (opts.batchDim != null) {
          opBuilder.setAttr("batch_dim", opts.batchDim);
        }
      }
    }
    return new ReverseSequence<T>(opBuilder.build());
  }
  
  /**
   * @param batchDim The dimension along which reversal is performed.
   */
  public static Options batchDim(Long batchDim) {
    return new Options().batchDim(batchDim);
  }
  
  /**
   * The partially reversed input. It has the same shape as `input`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ReverseSequence";
  
  private Output<T> output;
  
  private ReverseSequence(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
