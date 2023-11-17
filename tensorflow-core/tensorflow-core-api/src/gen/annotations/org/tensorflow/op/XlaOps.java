// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.xla.XlaHostCompute;
import org.tensorflow.op.xla.XlaRecvFromHost;
import org.tensorflow.op.xla.XlaSendToHost;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code xla} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class XlaOps {
  private final Scope scope;

  private final Ops ops;

  XlaOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * A pseudo-op to represent host-side computation in an XLA program.
   *
   * @param inputs A list of tensors that will be sent to the host.
   * @param Toutputs The element types of each element in {@code outputs}.
   * @param ancestors A list of names of HostCompute computations that must be
   *  sequenced before this computation.
   * @param shapes If shape_inference_graph is empty, a list of the shapes of {@code outputs}.
   * @param shapeInferenceGraph If non-empty, a serialized GraphDef representing a graph
   *  that must be analyzed at compile time to determine the shapes of the outputs.
   * @param key A unique identifier for this region used to match up host transfers.
   * @param options carries optional attribute values
   * @return a new instance of XlaHostCompute
   */
  public XlaHostCompute xlaHostCompute(Iterable<Operand<?>> inputs,
      List<Class<? extends TType>> Toutputs, List<String> ancestors, List<Shape> shapes,
      ConcreteFunction shapeInferenceGraph, String key, XlaHostCompute.Options... options) {
    return XlaHostCompute.create(scope, inputs, Toutputs, ancestors, shapes, shapeInferenceGraph, key, options);
  }

  /**
   * An op to receive a tensor from the host.
   *  output: the tensor that will be received from the host.
   *  Toutput: element type for output.
   *  shape: shape for output.
   *  key: A unique identifier for this region used to match up host transfers.
   *
   * @param <T> data type for {@code output} output
   * @param Toutput The value of the Toutput attribute
   * @param shape The value of the shape attribute
   * @param key The value of the key attribute
   * @param <T> data type for {@code XlaRecvFromHost} output and operands
   * @return a new instance of XlaRecvFromHost
   */
  public <T extends TType> XlaRecvFromHost<T> xlaRecvFromHost(Class<T> Toutput, Shape shape,
      String key) {
    return XlaRecvFromHost.create(scope, Toutput, shape, key);
  }

  /**
   * An op to send a tensor to the host.
   *  input: the tensor that will be sent to the host.
   *  Tinput: element type for input.
   *  key: A unique identifier for this region used to match up host transfers.
   *
   * @param input The input value
   * @param key The value of the key attribute
   * @return a new instance of XlaSendToHost
   */
  public XlaSendToHost xlaSendToHost(Operand<? extends TType> input, String key) {
    return XlaSendToHost.create(scope, input, key);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
