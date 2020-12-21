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

package org.tensorflow.op.debugging;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Debug Numeric Summary V2 Op.
 * <p>
 * Computes a numeric summary of the input tensor. The shape of the output
 * depends on the tensor_debug_mode attribute.
 * This op is used internally by TensorFlow Debugger (tfdbg) v2.
 * 
 * @param <U> data type for {@code output()} output
 */
public final class DebugNumericsSummary<U extends TNumber> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.debugging.DebugNumericsSummary}
   */
  public static class Options {
    
    /**
     * @param tensorDebugMode Tensor debug mode: the mode in which the input tensor is summarized
     *   by the op. See the TensorDebugMode enum in
     *   tensorflow/core/protobuf/debug_event.proto for details.
     * <p>
     * Supported values:
     *   2 (CURT_HEALTH): Output a float32/64 tensor of shape [2]. The 1st
     *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
     *   element is a bit which is set to 1 if the input tensor has an
     *   infinity or nan value, or zero otherwise.
     * <p>
     *   3 (CONCISE_HEALTH): Output a float32/64 tensor of shape [5]. The 1st
     *   element is the tensor_id, if provided, and -1 otherwise. The
     *   remaining four slots are the total number of elements, -infs,
     *   +infs, and nans in the input tensor respectively.
     * <p>
     *   4 (FULL_HEALTH): Output a float32/64 tensor of shape [11]. The 1st
     *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
     *   element is the device_id, if provided, and -1 otherwise. The 3rd
     *   element holds the datatype value of the input tensor as according
     *   to the enumerated type in tensorflow/core/framework/types.proto.
     *   The remaining elements hold the total number of elements, -infs,
     *   +infs, nans, negative finite numbers, zeros, and positive finite
     *   numbers in the input tensor respectively.
     * <p>
     *   5 (SHAPE): Output a float32/64 tensor of shape [10]. The 1st
     *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
     *   element holds the datatype value of the input tensor as according
     *   to the enumerated type in tensorflow/core/framework/types.proto.
     *   The 3rd element holds the rank of the tensor. The 4th element holds
     *   the number of elements within the tensor. Finally the remaining 6
     *   elements hold the shape of the tensor. If the rank of the tensor
     *   is lower than 6, the shape is right padded with zeros. If the rank
     *   is greater than 6, the head of the shape is truncated.
     * <p>
     *   6 (FULL_NUMERICS): Output a float32/64 tensor of shape [22]. The 1st
     *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
     *   element is the device_id, if provided, and -1 otherwise. The 3rd
     *   element holds the datatype value of the input tensor as according
     *   to the enumerated type in tensorflow/core/framework/types.proto.
     *   The 4th element holds the rank of the tensor. The 5th to 11th
     *   elements hold the shape of the tensor. If the rank of the tensor
     *   is lower than 6, the shape is right padded with zeros. If the rank
     *   is greater than 6, the head of the shape is truncated. The 12th to
     *   18th elements hold the number of elements, -infs, +infs, nans,
     *   denormal floats, negative finite numbers, zeros, and positive
     *   finite numbers in the input tensor respectively. The final four
     *   elements hold the min value, max value, mean, and variance of the
     *   input tensor.
     * <p>
     *   8 (REDUCE_INF_NAN_THREE_SLOTS): Output a float32/64 tensor of shape
     *   [3]. The 1st element is -inf if any elements of the input tensor
     *   is -inf, or zero otherwise. The 2nd element is +inf if any elements
     *   of the input tensor is +inf, or zero otherwise.  The 3rd element is
     *   nan if any element of the input tensor is nan, or zero otherwise.
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
   * Factory method to create a class wrapping a new DebugNumericsSummary operation.
   * 
   * @param scope current scope
   * @param input Input tensor, to be summarized by the op.
   * @param outputDtype Optional. The type of the output. Can be float32 or float64 (default: float32).
   * @param options carries optional attributes values
   * @return a new instance of DebugNumericsSummary
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TType> DebugNumericsSummary<U> create(Scope scope, Operand<T> input, Class<U> outputDtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DebugNumericSummaryV2", scope.makeOpName("DebugNumericsSummary"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_dtype", Operands.toDataType(outputDtype));
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
    return new DebugNumericsSummary<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new DebugNumericsSummary operation using default output types.
   * 
   * @param scope current scope
   * @param input Input tensor, to be summarized by the op.
   * @param options carries optional attributes values
   * @return a new instance of DebugNumericsSummary
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DebugNumericsSummary<TFloat32> create(Scope scope, Operand<T> input, Options... options) {
    return create(scope, input, TFloat32.class, options);
  }
  
  /**
   * @param tensorDebugMode Tensor debug mode: the mode in which the input tensor is summarized
   *   by the op. See the TensorDebugMode enum in
   *   tensorflow/core/protobuf/debug_event.proto for details.
   * <p>
   * Supported values:
   *   2 (CURT_HEALTH): Output a float32/64 tensor of shape [2]. The 1st
   *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
   *   element is a bit which is set to 1 if the input tensor has an
   *   infinity or nan value, or zero otherwise.
   * <p>
   *   3 (CONCISE_HEALTH): Output a float32/64 tensor of shape [5]. The 1st
   *   element is the tensor_id, if provided, and -1 otherwise. The
   *   remaining four slots are the total number of elements, -infs,
   *   +infs, and nans in the input tensor respectively.
   * <p>
   *   4 (FULL_HEALTH): Output a float32/64 tensor of shape [11]. The 1st
   *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
   *   element is the device_id, if provided, and -1 otherwise. The 3rd
   *   element holds the datatype value of the input tensor as according
   *   to the enumerated type in tensorflow/core/framework/types.proto.
   *   The remaining elements hold the total number of elements, -infs,
   *   +infs, nans, negative finite numbers, zeros, and positive finite
   *   numbers in the input tensor respectively.
   * <p>
   *   5 (SHAPE): Output a float32/64 tensor of shape [10]. The 1st
   *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
   *   element holds the datatype value of the input tensor as according
   *   to the enumerated type in tensorflow/core/framework/types.proto.
   *   The 3rd element holds the rank of the tensor. The 4th element holds
   *   the number of elements within the tensor. Finally the remaining 6
   *   elements hold the shape of the tensor. If the rank of the tensor
   *   is lower than 6, the shape is right padded with zeros. If the rank
   *   is greater than 6, the head of the shape is truncated.
   * <p>
   *   6 (FULL_NUMERICS): Output a float32/64 tensor of shape [22]. The 1st
   *   element is the tensor_id, if provided, and -1 otherwise. The 2nd
   *   element is the device_id, if provided, and -1 otherwise. The 3rd
   *   element holds the datatype value of the input tensor as according
   *   to the enumerated type in tensorflow/core/framework/types.proto.
   *   The 4th element holds the rank of the tensor. The 5th to 11th
   *   elements hold the shape of the tensor. If the rank of the tensor
   *   is lower than 6, the shape is right padded with zeros. If the rank
   *   is greater than 6, the head of the shape is truncated. The 12th to
   *   18th elements hold the number of elements, -infs, +infs, nans,
   *   denormal floats, negative finite numbers, zeros, and positive
   *   finite numbers in the input tensor respectively. The final four
   *   elements hold the min value, max value, mean, and variance of the
   *   input tensor.
   * <p>
   *   8 (REDUCE_INF_NAN_THREE_SLOTS): Output a float32/64 tensor of shape
   *   [3]. The 1st element is -inf if any elements of the input tensor
   *   is -inf, or zero otherwise. The 2nd element is +inf if any elements
   *   of the input tensor is +inf, or zero otherwise.  The 3rd element is
   *   nan if any element of the input tensor is nan, or zero otherwise.
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
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DebugNumericSummaryV2";
  
  private Output<U> output;
  
  private DebugNumericsSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
