/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * Provides an identity mapping of the non-Ref type input tensor for debugging.
 * Provides an identity mapping of the non-Ref type input tensor for debugging.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DebugIdentityV3.OP_NAME,
    inputsClass = DebugIdentityV3.Inputs.class
)
public final class DebugIdentityV3<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DebugIdentityV3";

  private Output<T> output;

  public DebugIdentityV3(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DebugIdentityV3 operation.
   *
   * @param scope current scope
   * @param input Input tensor, non-Reference type
   * @param options carries optional attribute values
   * @param <T> data type for {@code DebugIdentityV3} output and operands
   * @return a new instance of DebugIdentityV3
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DebugIdentityV3<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DebugIdentityV3");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.deviceName != null) {
          opBuilder.setAttr("device_name", opts.deviceName);
        }
        if (opts.tensorName != null) {
          opBuilder.setAttr("tensor_name", opts.tensorName);
        }
        if (opts.ioOfNode != null) {
          opBuilder.setAttr("io_of_node", opts.ioOfNode);
        }
        if (opts.isInput != null) {
          opBuilder.setAttr("is_input", opts.isInput);
        }
        if (opts.ioIndex != null) {
          opBuilder.setAttr("io_index", opts.ioIndex);
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
    return new DebugIdentityV3<>(opBuilder.build());
  }

  /**
   * Sets the deviceName option.
   *
   * @param deviceName Name of the device on which the tensor resides.
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
   * Sets the ioOfNode option.
   *
   * @param ioOfNode Name of the node of which the tensor is an input or output.
   * @return this Options instance.
   */
  public static Options ioOfNode(String ioOfNode) {
    return new Options().ioOfNode(ioOfNode);
  }

  /**
   * Sets the isInput option.
   *
   * @param isInput If true, the tensor is an input of the node; otherwise the output.
   * @return this Options instance.
   */
  public static Options isInput(Boolean isInput) {
    return new Options().isInput(isInput);
  }

  /**
   * Sets the ioIndex option.
   *
   * @param ioIndex The index of which the tensor is an input or output of the node.
   * @return this Options instance.
   */
  public static Options ioIndex(Long ioIndex) {
    return new Options().ioIndex(ioIndex);
  }

  /**
   * Sets the debugUrls option.
   *
   * @param debugUrls List of URLs to debug targets, e.g.,
   * file:///foo/tfdbg_dump, grpc:://localhost:11011
   * @return this Options instance.
   */
  public static Options debugUrls(List<String> debugUrls) {
    return new Options().debugUrls(debugUrls);
  }

  /**
   * Sets the debugUrls option.
   *
   * @param debugUrls List of URLs to debug targets, e.g.,
   * file:///foo/tfdbg_dump, grpc:://localhost:11011
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
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.DebugIdentityV3}
   */
  public static class Options {
    private String deviceName;

    private String tensorName;

    private String ioOfNode;

    private Boolean isInput;

    private Long ioIndex;

    private List<String> debugUrls;

    private Boolean gatedGrpc;

    private Options() {
    }

    /**
     * Sets the deviceName option.
     *
     * @param deviceName Name of the device on which the tensor resides.
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
     * Sets the ioOfNode option.
     *
     * @param ioOfNode Name of the node of which the tensor is an input or output.
     * @return this Options instance.
     */
    public Options ioOfNode(String ioOfNode) {
      this.ioOfNode = ioOfNode;
      return this;
    }

    /**
     * Sets the isInput option.
     *
     * @param isInput If true, the tensor is an input of the node; otherwise the output.
     * @return this Options instance.
     */
    public Options isInput(Boolean isInput) {
      this.isInput = isInput;
      return this;
    }

    /**
     * Sets the ioIndex option.
     *
     * @param ioIndex The index of which the tensor is an input or output of the node.
     * @return this Options instance.
     */
    public Options ioIndex(Long ioIndex) {
      this.ioIndex = ioIndex;
      return this;
    }

    /**
     * Sets the debugUrls option.
     *
     * @param debugUrls List of URLs to debug targets, e.g.,
     * file:///foo/tfdbg_dump, grpc:://localhost:11011
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
     * file:///foo/tfdbg_dump, grpc:://localhost:11011
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
      outputsClass = DebugIdentityV3.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<DebugIdentityV3<T>> {
    /**
     * Input tensor, non-Reference type
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Name of the device on which the tensor resides.
     */
    public final String deviceName;

    /**
     * Name of the input tensor.
     */
    public final String tensorName;

    /**
     * Name of the node of which the tensor is an input or output.
     */
    public final String ioOfNode;

    /**
     * If true, the tensor is an input of the node; otherwise the output.
     */
    public final boolean isInput;

    /**
     * The index of which the tensor is an input or output of the node.
     */
    public final long ioIndex;

    /**
     * List of URLs to debug targets, e.g.,
     *   file:///foo/tfdbg_dump, grpc:://localhost:11011
     */
    public final String[] debugUrls;

    /**
     * Whether this op will be gated. If any of the debug_urls of this
     *   debug node is of the grpc:// scheme, when the value of this attribute is set
     *   to True, the data will not actually be sent via the grpc stream unless this
     *   debug op has been enabled at the debug_url. If all of the debug_urls of this
     *   debug node are of the grpc:// scheme and the debug op is enabled at none of
     *   them, the output will be an empty Tensor.
     */
    public final boolean gatedGrpc;

    public Inputs(GraphOperation op) {
      super(new DebugIdentityV3<>(op), op, Arrays.asList("T", "device_name", "tensor_name", "io_of_node", "is_input", "io_index", "debug_urls", "gated_grpc"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      deviceName = op.attributes().getAttrString("device_name");
      tensorName = op.attributes().getAttrString("tensor_name");
      ioOfNode = op.attributes().getAttrString("io_of_node");
      isInput = op.attributes().getAttrBool("is_input");
      ioIndex = op.attributes().getAttrInt("io_index");
      debugUrls = op.attributes().getAttrStringList("debug_urls");
      gatedGrpc = op.attributes().getAttrBool("gated_grpc");
    }
  }
}
