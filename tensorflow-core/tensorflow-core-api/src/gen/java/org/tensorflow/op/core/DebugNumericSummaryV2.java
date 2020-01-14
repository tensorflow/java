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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.family.TType;

/**
 * Debug Numeric Summary V2 Op.
 * <p>
 * Computes a numeric summary of the input tensor. The shape of the output
 * depends on the tensor_debug_mode attribute.
 * This op is used internally by TensorFlow Debugger (tfdbg) v2.
 */
public final class DebugNumericSummaryV2 extends PrimitiveOp implements Operand<TFloat> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.DebugNumericSummaryV2}
   */
  public static class Options {
    
    /**
     * @param tensorDebugMode Tensor debug mode: the mode in which the input tensor is summarized
     *   by the op. See the TensorDebugMode enum in
     *   tensorflow/core/protobuf/debug_event.proto for details.
     * <p>
     * Supported values:
     *   8 (REDUCE_INF_NAN_THREE_SLOTS): Output a float32 tensor of shape
     *   [3]. The 1st element is -inf if any elements of the input tensor
     *   is -inf, or zero otherwise. The 2nd element is +inf if any elements
     *   of the input tensor is +inf, or zero otherwise.  The 3rd element is
     *   nan if any element of the input tensor is nan, or zero otherwise
     */
    public Options tensorDebugMode(Long tensorDebugMode) {
      this.tensorDebugMode = tensorDebugMode;
      return this;
    }
    
    /**
     * @param tensorId Optional. An integer identifier for the tensor being summarized by this op.
     */
    public Options tensorId(Long tensorId) {
      this.tensorId = tensorId;
      return this;
    }
    
    private Long tensorDebugMode;
    private Long tensorId;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DebugNumericSummaryV2 operation.
   * 
   * @param scope current scope
   * @param input Input tensor, to be summarized by the op.
   * @param options carries optional attributes values
   * @return a new instance of DebugNumericSummaryV2
   */
  public static <T extends TType> DebugNumericSummaryV2 create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DebugNumericSummaryV2", scope.makeOpName("DebugNumericSummaryV2"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.tensorDebugMode != null) {
          opBuilder.setAttr("tensor_debug_mode", opts.tensorDebugMode);
        }
        if (opts.tensorId != null) {
          opBuilder.setAttr("tensor_id", opts.tensorId);
        }
      }
    }
    return new DebugNumericSummaryV2(opBuilder.build());
  }
  
  /**
   * @param tensorDebugMode Tensor debug mode: the mode in which the input tensor is summarized
   *   by the op. See the TensorDebugMode enum in
   *   tensorflow/core/protobuf/debug_event.proto for details.
   * <p>
   * Supported values:
   *   8 (REDUCE_INF_NAN_THREE_SLOTS): Output a float32 tensor of shape
   *   [3]. The 1st element is -inf if any elements of the input tensor
   *   is -inf, or zero otherwise. The 2nd element is +inf if any elements
   *   of the input tensor is +inf, or zero otherwise.  The 3rd element is
   *   nan if any element of the input tensor is nan, or zero otherwise
   */
  public static Options tensorDebugMode(Long tensorDebugMode) {
    return new Options().tensorDebugMode(tensorDebugMode);
  }
  
  /**
   * @param tensorId Optional. An integer identifier for the tensor being summarized by this op.
   */
  public static Options tensorId(Long tensorId) {
    return new Options().tensorId(tensorId);
  }
  
  /**
   */
  public Output<TFloat> output() {
    return output;
  }
  
  @Override
  public Output<TFloat> asOutput() {
    return output;
  }
  
  private Output<TFloat> output;
  
  private DebugNumericSummaryV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
