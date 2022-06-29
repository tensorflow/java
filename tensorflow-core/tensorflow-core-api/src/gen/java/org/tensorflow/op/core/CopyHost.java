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
import org.tensorflow.types.family.TType;

/**
 * Copy a tensor to host.
 * Performs CPU-to-CPU deep-copying of tensor.
 * N.B.: If the all downstream attached debug ops are disabled given the current
 * gRPC gating status, the output will simply forward the input tensor without
 * deep-copying. See the documentation of Debug* ops for more details.
 * <p>Unlike the Copy Op, this op has HostMemory constraint on its input or output.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = CopyHost.OP_NAME,
    inputsClass = CopyHost.Inputs.class
)
public final class CopyHost<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CopyHost";

  private Output<T> output;

  public CopyHost(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CopyHost operation.
   *
   * @param scope current scope
   * @param input Input tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code CopyHost} output and operands
   * @return a new instance of CopyHost
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CopyHost<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CopyHost");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.tensorName != null) {
          opBuilder.setAttr("tensor_name", opts.tensorName);
        }
        if (opts.debugOpsSpec != null) {
          String[] debugOpsSpecArray = new String[opts.debugOpsSpec.size()];
          for (int i = 0 ; i < debugOpsSpecArray.length ; i++) {
            debugOpsSpecArray[i] = opts.debugOpsSpec.get(i);
          }
          opBuilder.setAttr("debug_ops_spec", debugOpsSpecArray);
        }
      }
    }
    return new CopyHost<>(opBuilder.build());
  }

  /**
   * Sets the tensorName option.
   *
   * @param tensorName The name of the input tensor.
   * @return this Options instance.
   */
  public static Options tensorName(String tensorName) {
    return new Options().tensorName(tensorName);
  }

  /**
   * Sets the debugOpsSpec option.
   *
   * @param debugOpsSpec A list of debug op spec (op, url, gated_grpc) for attached debug
   * ops. Each element of the list has the format
   * &lt;debug_op&gt;;&lt;grpc_url&gt;;&lt;gated_grpc&gt;, wherein gated_grpc is boolean represented
   * as 0/1. E.g., &quot;DebugIdentity;grpc://foo:3333;1&quot;,
   * &quot;DebugIdentity;file:///tmp/tfdbg_1;0&quot;.
   * @return this Options instance.
   */
  public static Options debugOpsSpec(List<String> debugOpsSpec) {
    return new Options().debugOpsSpec(debugOpsSpec);
  }

  /**
   * Sets the debugOpsSpec option.
   *
   * @param debugOpsSpec A list of debug op spec (op, url, gated_grpc) for attached debug
   * ops. Each element of the list has the format
   * &lt;debug_op&gt;;&lt;grpc_url&gt;;&lt;gated_grpc&gt;, wherein gated_grpc is boolean represented
   * as 0/1. E.g., &quot;DebugIdentity;grpc://foo:3333;1&quot;,
   * &quot;DebugIdentity;file:///tmp/tfdbg_1;0&quot;.
   * @return this Options instance.
   */
  public static Options debugOpsSpec(String... debugOpsSpec) {
    return new Options().debugOpsSpec(debugOpsSpec);
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
   * Optional attributes for {@link org.tensorflow.op.core.CopyHost}
   */
  public static class Options {
    private String tensorName;

    private List<String> debugOpsSpec;

    private Options() {
    }

    /**
     * Sets the tensorName option.
     *
     * @param tensorName The name of the input tensor.
     * @return this Options instance.
     */
    public Options tensorName(String tensorName) {
      this.tensorName = tensorName;
      return this;
    }

    /**
     * Sets the debugOpsSpec option.
     *
     * @param debugOpsSpec A list of debug op spec (op, url, gated_grpc) for attached debug
     * ops. Each element of the list has the format
     * &lt;debug_op&gt;;&lt;grpc_url&gt;;&lt;gated_grpc&gt;, wherein gated_grpc is boolean represented
     * as 0/1. E.g., &quot;DebugIdentity;grpc://foo:3333;1&quot;,
     * &quot;DebugIdentity;file:///tmp/tfdbg_1;0&quot;.
     * @return this Options instance.
     */
    public Options debugOpsSpec(List<String> debugOpsSpec) {
      this.debugOpsSpec = debugOpsSpec;
      return this;
    }

    /**
     * Sets the debugOpsSpec option.
     *
     * @param debugOpsSpec A list of debug op spec (op, url, gated_grpc) for attached debug
     * ops. Each element of the list has the format
     * &lt;debug_op&gt;;&lt;grpc_url&gt;;&lt;gated_grpc&gt;, wherein gated_grpc is boolean represented
     * as 0/1. E.g., &quot;DebugIdentity;grpc://foo:3333;1&quot;,
     * &quot;DebugIdentity;file:///tmp/tfdbg_1;0&quot;.
     * @return this Options instance.
     */
    public Options debugOpsSpec(String... debugOpsSpec) {
      this.debugOpsSpec = Arrays.asList(debugOpsSpec);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CopyHost.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<CopyHost<T>> {
    /**
     * Input tensor.
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The name of the input tensor.
     */
    public final String tensorName;

    /**
     * A list of debug op spec (op, url, gated_grpc) for attached debug
     * ops. Each element of the list has the format
     * <debug_op>;<grpc_url>;<gated_grpc>, wherein gated_grpc is boolean represented
     * as 0/1. E.g., "DebugIdentity;grpc://foo:3333;1",
     * "DebugIdentity;file:///tmp/tfdbg_1;0".
     */
    public final String[] debugOpsSpec;

    public Inputs(GraphOperation op) {
      super(new CopyHost<>(op), op, Arrays.asList("T", "tensor_name", "debug_ops_spec"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      tensorName = op.attributes().getAttrString("tensor_name");
      debugOpsSpec = op.attributes().getAttrStringList("debug_ops_spec");
    }
  }
}
