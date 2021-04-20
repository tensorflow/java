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
import org.tensorflow.types.TString;

/**
 * Perform batches of RPC requests.
 * This op asynchronously performs either a single RPC request, or a batch
 * of requests.  RPC requests are defined by three main parameters:
 * <ul>
 * <li>{@code address} (the host+port or BNS address of the request)</li>
 * <li>{@code method} (the RPC method name for the request)</li>
 * <li>{@code request} (the serialized proto string, or vector of strings,
 * of the RPC request argument).</li>
 * </ul>
 * <p>For example, if you have an RPC service running on port localhost:2345,
 * and its interface is configured with the following proto declaration:
 * <pre>
 * service MyService {
 *   rpc MyMethod(MyRequestProto) returns (MyResponseProto) {
 *   }
 * };
 * </pre>
 * <p>then call this op with arguments:
 * <pre>
 * address = &quot;localhost:2345&quot;
 * method = &quot;MyService/MyMethod&quot;
 * </pre>
 * <p>The {@code request} tensor is a string tensor representing serialized {@code MyRequestProto}
 * strings; and the output string tensor {@code response} will have the same shape
 * and contain (upon successful completion) corresponding serialized
 * {@code MyResponseProto} strings.
 * <p>For example, to send a single, empty, {@code MyRequestProto}, call
 * this op with {@code request = ""}.  To send 5 <strong>parallel</strong> empty requests,
 * call this op with {@code request = ["", "", "", "", ""]}.
 * <p>More generally, one can create a batch of {@code MyRequestProto} serialized protos
 * from regular batched tensors using the {@code encode_proto} op, and convert
 * the response {@code MyResponseProto} serialized protos to batched tensors
 * using the {@code decode_proto} op.
 * <p><strong>NOTE</strong> Working with serialized proto strings is faster than instantiating
 * actual proto objects in memory, so no performance degradation is expected
 * compared to writing custom kernels for this workflow.
 * <p>If the connection fails or the remote worker returns an error
 * status, the op reraises this exception locally.
 * <p>See the {@code TryRpc} op if you prefer to handle RPC failures manually in the graph.
 */
@Operator
public final class Rpc extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Rpc";

  private Output<TString> response;

  private Rpc(Operation operation) {
    super(operation);
    int outputIdx = 0;
    response = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Rpc operation.
   *
   * @param scope current scope
   * @param address {@code 0-D} or {@code 1-D}.  The address (i.e. host_name:port) of the RPC server.
   * If this tensor has more than 1 element, then multiple parallel rpc requests
   * are sent.  This argument broadcasts with {@code method} and {@code request}.
   * @param method {@code 0-D} or {@code 1-D}.  The method address on the RPC server.
   * If this tensor has more than 1 element, then multiple parallel rpc requests
   * are sent.  This argument broadcasts with {@code address} and {@code request}.
   * @param request {@code 0-D} or {@code 1-D}.  Serialized proto strings: the rpc request argument.
   * If this tensor has more than 1 element, then multiple parallel rpc requests
   * are sent.  This argument broadcasts with {@code address} and {@code method}.
   * @param options carries optional attribute values
   * @return a new instance of Rpc
   */
  @Endpoint(
      describeByClass = true
  )
  public static Rpc create(Scope scope, Operand<TString> address, Operand<TString> method,
      Operand<TString> request, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Rpc", scope.makeOpName("Rpc"));
    opBuilder.addInput(address.asOutput());
    opBuilder.addInput(method.asOutput());
    opBuilder.addInput(request.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.protocol != null) {
          opBuilder.setAttr("protocol", opts.protocol);
        }
        if (opts.failFast != null) {
          opBuilder.setAttr("fail_fast", opts.failFast);
        }
        if (opts.timeoutInMs != null) {
          opBuilder.setAttr("timeout_in_ms", opts.timeoutInMs);
        }
      }
    }
    return new Rpc(opBuilder.build());
  }

  /**
   * Sets the protocol option.
   *
   * @param protocol RPC protocol to use.  Empty string means use the default protocol.
   * Options include 'grpc'.
   * @return this Options instance.
   */
  public static Options protocol(String protocol) {
    return new Options().protocol(protocol);
  }

  /**
   * Sets the failFast option.
   *
   * @param failFast {@code boolean}. If {@code true} (default), then failures to connect
   * (i.e., the server does not immediately respond) cause an RPC failure.
   * @return this Options instance.
   */
  public static Options failFast(Boolean failFast) {
    return new Options().failFast(failFast);
  }

  /**
   * Sets the timeoutInMs option.
   *
   * @param timeoutInMs {@code int}. If {@code 0} (default), then the kernel will run the RPC
   * request and only time out if the RPC deadline passes or the session times out.
   * If this value is greater than {@code 0}, then the op will raise an exception if
   * the RPC takes longer than {@code timeout_in_ms}.
   * @return this Options instance.
   */
  public static Options timeoutInMs(Long timeoutInMs) {
    return new Options().timeoutInMs(timeoutInMs);
  }

  /**
   * Gets response.
   * Same shape as {@code request}. Serialized proto strings: the rpc responses.
   * @return response.
   */
  public Output<TString> response() {
    return response;
  }

  @Override
  public Output<TString> asOutput() {
    return response;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Rpc}
   */
  public static class Options {
    private String protocol;

    private Boolean failFast;

    private Long timeoutInMs;

    private Options() {
    }

    /**
     * Sets the protocol option.
     *
     * @param protocol RPC protocol to use.  Empty string means use the default protocol.
     * Options include 'grpc'.
     * @return this Options instance.
     */
    public Options protocol(String protocol) {
      this.protocol = protocol;
      return this;
    }

    /**
     * Sets the failFast option.
     *
     * @param failFast {@code boolean}. If {@code true} (default), then failures to connect
     * (i.e., the server does not immediately respond) cause an RPC failure.
     * @return this Options instance.
     */
    public Options failFast(Boolean failFast) {
      this.failFast = failFast;
      return this;
    }

    /**
     * Sets the timeoutInMs option.
     *
     * @param timeoutInMs {@code int}. If {@code 0} (default), then the kernel will run the RPC
     * request and only time out if the RPC deadline passes or the session times out.
     * If this value is greater than {@code 0}, then the op will raise an exception if
     * the RPC takes longer than {@code timeout_in_ms}.
     * @return this Options instance.
     */
    public Options timeoutInMs(Long timeoutInMs) {
      this.timeoutInMs = timeoutInMs;
      return this;
    }
  }
}
