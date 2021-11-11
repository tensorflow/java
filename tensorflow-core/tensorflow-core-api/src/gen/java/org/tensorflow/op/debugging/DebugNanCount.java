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

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Debug NaN Value Counter Op.
 * Counts number of NaNs in the input tensor, for debugging.
 */
@OpMetadata(
    opType = DebugNanCount.OP_NAME,
    inputsClass = DebugNanCount.Inputs.class
)
public final class DebugNanCount extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DebugNanCount";

  private Output<TInt64> output;

  public DebugNanCount(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DebugNanCount operation.
   *
   * @param scope current scope
   * @param input Input tensor, non-Reference type.
   * @param options carries optional attribute values
   * @return a new instance of DebugNanCount
   */
  @Endpoint(
      describeByClass = true
  )
  public static DebugNanCount create(Scope scope, Operand<? extends TType> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DebugNanCount");
    opBuilder.addInput(input.asOutput());
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
          for (int i = 0 ; i < debugUrlsArray.length ; i++) {
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
   * Sets the deviceName option.
   *
   * @param deviceName the deviceName option
   * @return this Options instance.
   */
  public static Options deviceName(String deviceName) {
    return new Options().deviceName(deviceName);
  }

  /**
   * Sets the tensorName option.
   *
   * @param tensorName Name of the input tensor.
   * @return this Options instance.
   */
  public static Options tensorName(String tensorName) {
    return new Options().tensorName(tensorName);
  }

  /**
   * Sets the debugUrls option.
   *
   * @param debugUrls List of URLs to debug targets, e.g.,
   * file:///foo/tfdbg_dump, grpc:://localhost:11011.
   * @return this Options instance.
   */
  public static Options debugUrls(List<String> debugUrls) {
    return new Options().debugUrls(debugUrls);
  }

  /**
   * Sets the debugUrls option.
   *
   * @param debugUrls List of URLs to debug targets, e.g.,
   * file:///foo/tfdbg_dump, grpc:://localhost:11011.
   * @return this Options instance.
   */
  public static Options debugUrls(String... debugUrls) {
    return new Options().debugUrls(debugUrls);
  }

  /**
   * Sets the gatedGrpc option.
   *
   * @param gatedGrpc Whether this op will be gated. If any of the debug_urls of this
   * debug node is of the grpc:// scheme, when the value of this attribute is set
   * to True, the data will not actually be sent via the grpc stream unless this
   * debug op has been enabled at the debug_url. If all of the debug_urls of this
   * debug node are of the grpc:// scheme and the debug op is enabled at none of
   * them, the output will be an empty Tensor.
   * @return this Options instance.
   */
  public static Options gatedGrpc(Boolean gatedGrpc) {
    return new Options().gatedGrpc(gatedGrpc);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TInt64> output() {
    return output;
  }

  @Override
  public Output<TInt64> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.debugging.DebugNanCount}
   */
  public static class Options {
    private String deviceName;

    private String tensorName;

    private List<String> debugUrls;

    private Boolean gatedGrpc;

    private Options() {
    }

    /**
     * Sets the deviceName option.
     *
     * @param deviceName the deviceName option
     * @return this Options instance.
     */
    public Options deviceName(String deviceName) {
      this.deviceName = deviceName;
      return this;
    }

    /**
     * Sets the tensorName option.
     *
     * @param tensorName Name of the input tensor.
     * @return this Options instance.
     */
    public Options tensorName(String tensorName) {
      this.tensorName = tensorName;
      return this;
    }

    /**
     * Sets the debugUrls option.
     *
     * @param debugUrls List of URLs to debug targets, e.g.,
     * file:///foo/tfdbg_dump, grpc:://localhost:11011.
     * @return this Options instance.
     */
    public Options debugUrls(List<String> debugUrls) {
      this.debugUrls = debugUrls;
      return this;
    }

    /**
     * Sets the debugUrls option.
     *
     * @param debugUrls List of URLs to debug targets, e.g.,
     * file:///foo/tfdbg_dump, grpc:://localhost:11011.
     * @return this Options instance.
     */
    public Options debugUrls(String... debugUrls) {
      this.debugUrls = Arrays.asList(debugUrls);
      return this;
    }

    /**
     * Sets the gatedGrpc option.
     *
     * @param gatedGrpc Whether this op will be gated. If any of the debug_urls of this
     * debug node is of the grpc:// scheme, when the value of this attribute is set
     * to True, the data will not actually be sent via the grpc stream unless this
     * debug op has been enabled at the debug_url. If all of the debug_urls of this
     * debug node are of the grpc:// scheme and the debug op is enabled at none of
     * them, the output will be an empty Tensor.
     * @return this Options instance.
     */
    public Options gatedGrpc(Boolean gatedGrpc) {
      this.gatedGrpc = gatedGrpc;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DebugNanCount.class
  )
  public static class Inputs extends RawOpInputs<DebugNanCount> {
    /**
     * Input tensor, non-Reference type.
     */
    public final Operand<? extends TType> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The deviceName attribute
     */
    public final String deviceName;

    /**
     * Name of the input tensor.
     */
    public final String tensorName;

    /**
     * List of URLs to debug targets, e.g.,
     *   file:///foo/tfdbg_dump, grpc:://localhost:11011.
     */
    public final String[] debugUrls;

    /**
     *  Whether this op will be gated. If any of the debug_urls of this
     *   debug node is of the grpc:// scheme, when the value of this attribute is set
     *   to True, the data will not actually be sent via the grpc stream unless this
     *   debug op has been enabled at the debug_url. If all of the debug_urls of this
     *   debug node are of the grpc:// scheme and the debug op is enabled at none of
     *   them, the output will be an empty Tensor.
     */
    public final boolean gatedGrpc;

    public Inputs(GraphOperation op) {
      super(new DebugNanCount(op), op, Arrays.asList("T", "device_name", "tensor_name", "debug_urls", "gated_grpc"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      deviceName = op.attributes().getAttrString("device_name");
      tensorName = op.attributes().getAttrString("tensor_name");
      debugUrls = op.attributes().getAttrStringList("debug_urls");
      gatedGrpc = op.attributes().getAttrBool("gated_grpc");
    }
  }
}
