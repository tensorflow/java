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
 * Gather slices from `params` axis `axis` according to `indices`.
 * <p>
 * `indices` must be an integer tensor of any dimension (usually 0-D or 1-D).
 * Produces an output tensor with shape `params.shape[:axis] +
 * indices.shape[batch_dims:] + params.shape[axis + 1:]` where:
 * <pre>{@code
 *     # Scalar indices (output is rank(params) - 1).
 *     output[a_0, ..., a_n, b_0, ..., b_n] =
 *       params[a_0, ..., a_n, indices, b_0, ..., b_n]
 * 
 *     # Vector indices (output is rank(params)).
 *     output[a_0, ..., a_n, i, b_0, ..., b_n] =
 *       params[a_0, ..., a_n, indices[i], b_0, ..., b_n]
 * 
 *     # Higher rank indices (output is rank(params) + rank(indices) - 1).
 *     output[a_0, ..., a_n, i, ..., j, b_0, ... b_n] =
 *       params[a_0, ..., a_n, indices[i, ..., j], b_0, ..., b_n]
 * }</pre>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/Gather.png" alt>
 * </div>
 * <p>
 * Note that on CPU, if an out of bound index is found, an error is returned.
 * On GPU, if an out of bound index is found, a 0 is stored in the
 * corresponding output value.
 * <p>
 * See also `tf.batch_gather` and `tf.gather_nd`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Gather<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Gather}
   */
  public static class Options {
    
    /**
     * @param batchDims 
     */
    public Options batchDims(Long batchDims) {
      this.batchDims = batchDims;
      return this;
    }
    
    private Long batchDims;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Gather operation.
   * 
   * @param scope current scope
   * @param params The tensor from which to gather values. Must be at least rank
   * `axis + 1`.
   * @param indices Index tensor. Must be in range `[0, params.shape[axis])`.
   * @param axis The axis in `params` to gather `indices` from. Defaults to the first
   * dimension. Supports negative indexes.
   * @param options carries optional attributes values
   * @return a new instance of Gather
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber, V extends TNumber> Gather<T> create(Scope scope, Operand<T> params, Operand<U> indices, Operand<V> axis, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("GatherV2", scope.makeOpName("Gather"));
    opBuilder.addInput(params.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.batchDims != null) {
          opBuilder.setAttr("batch_dims", opts.batchDims);
        }
      }
    }
    return new Gather<T>(opBuilder.build());
  }
  
  /**
   * @param batchDims 
   */
  public static Options batchDims(Long batchDims) {
    return new Options().batchDims(batchDims);
  }
  
  /**
   * Values from `params` gathered from indices given by `indices`, with
   * shape `params.shape[:axis] + indices.shape + params.shape[axis + 1:]`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "GatherV2";
  
  private Output<T> output;
  
  private Gather(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
