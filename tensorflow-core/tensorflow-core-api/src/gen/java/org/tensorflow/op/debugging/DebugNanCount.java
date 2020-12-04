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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Debug NaN Value Counter Op.
 * <p>
 * Counts number of NaNs in the input tensor, for debugging.
 */
public final class DebugNanCount extends RawOp implements Operand<TInt64> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.debugging.DebugNanCount}
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
     * @param gatedGrpc  Whether this op will be gated. If any of the debug_urls of this
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
    private Boolean gatedGrpc;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DebugNanCount operation.
   * 
   * @param scope current scope
   * @param input Input tensor, non-Reference type.
   * @param options carries optional attributes values
   * @return a new instance of DebugNanCount
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DebugNanCount create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DebugNanCount", scope.makeOpName("DebugNanCount"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
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
        if (opts.gatedGrpc != null) {
          opBuilder.setAttr("gated_grpc", opts.gatedGrpc);
        }
      }
    }
    return new DebugNanCount(opBuilder.build());
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
   * @param gatedGrpc  Whether this op will be gated. If any of the debug_urls of this
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
  public Output<TInt64> output() {
    return output;
  }
  
  @Override
  public Output<TInt64> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DebugNanCount";
  
  private Output<TInt64> output;
  
  private DebugNanCount(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
