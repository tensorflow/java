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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

/**
 * Debug Numeric Summary Op.
 * <p>
 * Provide a basic summary of numeric value types, range and distribution.
 * <p>
 * output: A double tensor of shape [14 + nDimensions], where nDimensions is the
 *   the number of dimensions of the tensor's shape. The elements of output are:
 *   [0]: is initialized (1.0) or not (0.0).
 *   [1]: total number of elements
 *   [2]: NaN element count
 *   [3]: generalized -inf count: elements <= lower_bound. lower_bound is -inf by
 *     default.
 *   [4]: negative element count (excluding -inf), if lower_bound is the default
 *     -inf. Otherwise, this is the count of elements > lower_bound and < 0.
 *   [5]: zero element count
 *   [6]: positive element count (excluding +inf), if upper_bound is the default
 *     -inf. Otherwise, this is the count of elements < upper_bound and > 0.
 *   [7]: generalized +inf count, elements >= upper_bound. upper_bound is +inf by
 *     default.
 * Output elements [1:8] are all zero, if the tensor is uninitialized.
 *   [8]: minimum of all non-inf and non-NaN elements.
 *        If uninitialized or no such element exists: +inf.
 *   [9]: maximum of all non-inf and non-NaN elements.
 *        If uninitialized or no such element exists: -inf.
 *   [10]: mean of all non-inf and non-NaN elements.
 *         If uninitialized or no such element exists: NaN.
 *   [11]: variance of all non-inf and non-NaN elements.
 *         If uninitialized or no such element exists: NaN.
 *   [12]: Data type of the tensor encoded as an enum integer. See the DataType
 *         proto for more details.
 *   [13]: Number of dimensions of the tensor (ndims).
 *   [14+]: Sizes of the dimensions.
 * 
 */
public final class DebugNumericSummary extends PrimitiveOp implements Operand<TFloat64> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.DebugNumericSummary}
   */
  public static class Options {
    
    /**
     * @param deviceName 
     */
    public Options deviceName(String deviceName) {
      this.deviceName = deviceName;
      return this;
    }
    
    /**
     * @param tensorName Name of the input tensor.
     */
    public Options tensorName(String tensorName) {
      this.tensorName = tensorName;
      return this;
    }
    
    /**
     * @param debugUrls List of URLs to debug targets, e.g.,
     *   file:///foo/tfdbg_dump, grpc:://localhost:11011.
     */
    public Options debugUrls(List<String> debugUrls) {
      this.debugUrls = debugUrls;
      return this;
    }
    
    /**
     * @param lowerBound (float) The lower bound <= which values will be included in the
     *   generalized -inf count. Default: -inf.
     */
    public Options lowerBound(Float lowerBound) {
      this.lowerBound = lowerBound;
      return this;
    }
    
    /**
     * @param upperBound (float) The upper bound >= which values will be included in the
     *   generalized +inf count. Default: +inf.
     */
    public Options upperBound(Float upperBound) {
      this.upperBound = upperBound;
      return this;
    }
    
    /**
     * @param muteIfHealthy (bool) Do not send data to the debug URLs unless at least one
     *   of elements [2], [3] and [7] (i.e., the nan count and the generalized -inf and
     *   inf counts) is non-zero.
     */
    public Options muteIfHealthy(Boolean muteIfHealthy) {
      this.muteIfHealthy = muteIfHealthy;
      return this;
    }
    
    /**
     * @param gatedGrpc Whether this op will be gated. If any of the debug_urls of this
     *   debug node is of the grpc:// scheme, when the value of this attribute is set
     *   to True, the data will not actually be sent via the grpc stream unless this
     *   debug op has been enabled at the debug_url. If all of the debug_urls of this
     *   debug node are of the grpc:// scheme and the debug op is enabled at none of
     *   them, the output will be an empty Tensor.
     */
    public Options gatedGrpc(Boolean gatedGrpc) {
      this.gatedGrpc = gatedGrpc;
      return this;
    }
    
    private String deviceName;
    private String tensorName;
    private List<String> debugUrls;
    private Float lowerBound;
    private Float upperBound;
    private Boolean muteIfHealthy;
    private Boolean gatedGrpc;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DebugNumericSummary operation.
   * 
   * @param scope current scope
   * @param input Input tensor, non-Reference type.
   * @param options carries optional attributes values
   * @return a new instance of DebugNumericSummary
   */
  public static <T extends TType> DebugNumericSummary create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DebugNumericSummary", scope.makeOpName("DebugNumericSummary"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.deviceName != null) {
          opBuilder.setAttr("device_name", opts.deviceName);
        }
        if (opts.tensorName != null) {
          opBuilder.setAttr("tensor_name", opts.tensorName);
        }
        if (opts.debugUrls != null) {
          String[] debugUrlsArray = new String[opts.debugUrls.size()];
          for (int i = 0; i < debugUrlsArray.length; ++i) {
            debugUrlsArray[i] = opts.debugUrls.get(i);
          }
          opBuilder.setAttr("debug_urls", debugUrlsArray);
        }
        if (opts.lowerBound != null) {
          opBuilder.setAttr("lower_bound", opts.lowerBound);
        }
        if (opts.upperBound != null) {
          opBuilder.setAttr("upper_bound", opts.upperBound);
        }
        if (opts.muteIfHealthy != null) {
          opBuilder.setAttr("mute_if_healthy", opts.muteIfHealthy);
        }
        if (opts.gatedGrpc != null) {
          opBuilder.setAttr("gated_grpc", opts.gatedGrpc);
        }
      }
    }
    return new DebugNumericSummary(opBuilder.build());
  }
  
  /**
   * @param deviceName 
   */
  public static Options deviceName(String deviceName) {
    return new Options().deviceName(deviceName);
  }
  
  /**
   * @param tensorName Name of the input tensor.
   */
  public static Options tensorName(String tensorName) {
    return new Options().tensorName(tensorName);
  }
  
  /**
   * @param debugUrls List of URLs to debug targets, e.g.,
   *   file:///foo/tfdbg_dump, grpc:://localhost:11011.
   */
  public static Options debugUrls(List<String> debugUrls) {
    return new Options().debugUrls(debugUrls);
  }
  
  /**
   * @param lowerBound (float) The lower bound <= which values will be included in the
   *   generalized -inf count. Default: -inf.
   */
  public static Options lowerBound(Float lowerBound) {
    return new Options().lowerBound(lowerBound);
  }
  
  /**
   * @param upperBound (float) The upper bound >= which values will be included in the
   *   generalized +inf count. Default: +inf.
   */
  public static Options upperBound(Float upperBound) {
    return new Options().upperBound(upperBound);
  }
  
  /**
   * @param muteIfHealthy (bool) Do not send data to the debug URLs unless at least one
   *   of elements [2], [3] and [7] (i.e., the nan count and the generalized -inf and
   *   inf counts) is non-zero.
   */
  public static Options muteIfHealthy(Boolean muteIfHealthy) {
    return new Options().muteIfHealthy(muteIfHealthy);
  }
  
  /**
   * @param gatedGrpc Whether this op will be gated. If any of the debug_urls of this
   *   debug node is of the grpc:// scheme, when the value of this attribute is set
   *   to True, the data will not actually be sent via the grpc stream unless this
   *   debug op has been enabled at the debug_url. If all of the debug_urls of this
   *   debug node are of the grpc:// scheme and the debug op is enabled at none of
   *   them, the output will be an empty Tensor.
   */
  public static Options gatedGrpc(Boolean gatedGrpc) {
    return new Options().gatedGrpc(gatedGrpc);
  }
  
  /**
   */
  public Output<TFloat64> output() {
    return output;
  }
  
  @Override
  public Output<TFloat64> asOutput() {
    return output;
  }
  
  private Output<TFloat64> output;
  
  private DebugNumericSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
