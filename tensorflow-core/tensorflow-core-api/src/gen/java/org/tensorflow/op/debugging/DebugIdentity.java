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
import org.tensorflow.types.family.TType;

/**
 * Debug Identity V2 Op.
 * <p>
 * Provides an identity mapping from input to output, while writing the content of
 * the input tensor by calling DebugEventsWriter.
 * <p>
 * The semantics of the input tensor depends on tensor_debug_mode. In typical
 * usage, the input tensor comes directly from the user computation only when
 * graph_debug_mode is FULL_TENSOR (see protobuf/debug_event.proto for a
 * list of all the possible values of graph_debug_mode). For the other debug modes,
 * the input tensor should be produced by an additional op or subgraph that
 * computes summary information about one or more tensors.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class DebugIdentity<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.debugging.DebugIdentity}
   */
  public static class Options {
    
    /**
     * @param tfdbgContextId A tfdbg-generated ID for the context that the op belongs to,
     *   e.g., a concrete compiled tf.function.
     */
    public Options tfdbgContextId(String tfdbgContextId) {
      this.tfdbgContextId = tfdbgContextId;
      return this;
    }
    
    /**
     * @param opName Optional. Name of the op that the debug op is concerned with.
     *   Used only for single-tensor trace.
     */
    public Options opName(String opName) {
      this.opName = opName;
      return this;
    }
    
    /**
     * @param outputSlot Optional. Output slot index of the tensor that the debug op
     *   is concerned with. Used only for single-tensor trace.
     */
    public Options outputSlot(Long outputSlot) {
      this.outputSlot = outputSlot;
      return this;
    }
    
    /**
     * @param tensorDebugMode TensorDebugMode enum value. See debug_event.proto for details.
     */
    public Options tensorDebugMode(Long tensorDebugMode) {
      this.tensorDebugMode = tensorDebugMode;
      return this;
    }
    
    /**
     * @param debugUrls List of URLs to debug targets, e.g., file:///foo/tfdbg_dump.
     */
    public Options debugUrls(List<String> debugUrls) {
      this.debugUrls = debugUrls;
      return this;
    }
    
    /**
     * @param circularBufferSize 
     */
    public Options circularBufferSize(Long circularBufferSize) {
      this.circularBufferSize = circularBufferSize;
      return this;
    }
    
    /**
     * @param tfdbgRunId 
     */
    public Options tfdbgRunId(String tfdbgRunId) {
      this.tfdbgRunId = tfdbgRunId;
      return this;
    }
    
    private String tfdbgContextId;
    private String opName;
    private Long outputSlot;
    private Long tensorDebugMode;
    private List<String> debugUrls;
    private Long circularBufferSize;
    private String tfdbgRunId;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DebugIdentity operation.
   * 
   * @param scope current scope
   * @param input Input tensor, non-Reference type
   * @param options carries optional attributes values
   * @return a new instance of DebugIdentity
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DebugIdentity<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DebugIdentityV2", scope.makeOpName("DebugIdentity"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.tfdbgContextId != null) {
          opBuilder.setAttr("tfdbg_context_id", opts.tfdbgContextId);
        }
        if (opts.opName != null) {
          opBuilder.setAttr("op_name", opts.opName);
        }
        if (opts.outputSlot != null) {
          opBuilder.setAttr("output_slot", opts.outputSlot);
        }
        if (opts.tensorDebugMode != null) {
          opBuilder.setAttr("tensor_debug_mode", opts.tensorDebugMode);
        }
        if (opts.debugUrls != null) {
          String[] debugUrlsArray = new String[opts.debugUrls.size()];
          for (int i = 0; i < debugUrlsArray.length; ++i) {
            debugUrlsArray[i] = opts.debugUrls.get(i);
          }
          opBuilder.setAttr("debug_urls", debugUrlsArray);
        }
        if (opts.circularBufferSize != null) {
          opBuilder.setAttr("circular_buffer_size", opts.circularBufferSize);
        }
        if (opts.tfdbgRunId != null) {
          opBuilder.setAttr("tfdbg_run_id", opts.tfdbgRunId);
        }
      }
    }
    return new DebugIdentity<T>(opBuilder.build());
  }
  
  /**
   * @param tfdbgContextId A tfdbg-generated ID for the context that the op belongs to,
   *   e.g., a concrete compiled tf.function.
   */
  public static Options tfdbgContextId(String tfdbgContextId) {
    return new Options().tfdbgContextId(tfdbgContextId);
  }
  
  /**
   * @param opName Optional. Name of the op that the debug op is concerned with.
   *   Used only for single-tensor trace.
   */
  public static Options opName(String opName) {
    return new Options().opName(opName);
  }
  
  /**
   * @param outputSlot Optional. Output slot index of the tensor that the debug op
   *   is concerned with. Used only for single-tensor trace.
   */
  public static Options outputSlot(Long outputSlot) {
    return new Options().outputSlot(outputSlot);
  }
  
  /**
   * @param tensorDebugMode TensorDebugMode enum value. See debug_event.proto for details.
   */
  public static Options tensorDebugMode(Long tensorDebugMode) {
    return new Options().tensorDebugMode(tensorDebugMode);
  }
  
  /**
   * @param debugUrls List of URLs to debug targets, e.g., file:///foo/tfdbg_dump.
   */
  public static Options debugUrls(List<String> debugUrls) {
    return new Options().debugUrls(debugUrls);
  }
  
  /**
   * @param circularBufferSize 
   */
  public static Options circularBufferSize(Long circularBufferSize) {
    return new Options().circularBufferSize(circularBufferSize);
  }
  
  /**
   * @param tfdbgRunId 
   */
  public static Options tfdbgRunId(String tfdbgRunId) {
    return new Options().tfdbgRunId(tfdbgRunId);
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DebugIdentityV2";
  
  private Output<T> output;
  
  private DebugIdentity(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
