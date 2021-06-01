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

package org.tensorflow.op.xla;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * A pseudo-op to represent host-side computation in an XLA program.
 */
@Operator(
    group = "xla"
)
public final class XlaHostCompute extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaHostCompute";

  private List<Output<?>> outputs;

  @SuppressWarnings("unchecked")
  private XlaHostCompute(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaHostCompute operation.
   *
   * @param scope current scope
   * @param inputs A list of tensors that will be sent to the host.
   * @param Toutputs The element types of each element in {@code outputs}.
   * @param ancestors A list of names of HostCompute computations that must be
   * sequenced before this computation.
   * @param shapes If shape_inference_graph is empty, a list of the shapes of {@code outputs}.
   * @param shapeInferenceGraph If non-empty, a serialized GraphDef representing a graph
   * that must be analyzed at compile time to determine the shapes of the outputs.
   * @param key A unique identifier for this region used to match up host transfers.
   * @param options carries optional attribute values
   * @return a new instance of XlaHostCompute
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaHostCompute create(Scope scope, Iterable<Operand<?>> inputs,
      List<Class<? extends TType>> Toutputs, List<String> ancestors, List<Shape> shapes,
      ConcreteFunction shapeInferenceGraph, String key, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("XlaHostCompute"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Toutputs", Operands.toDataTypes(Toutputs));
    String[] ancestorsArray = new String[ancestors.size()];
    for (int i = 0 ; i < ancestorsArray.length ; i++) {
      ancestorsArray[i] = ancestors.get(i);
    }
    opBuilder.setAttr("ancestors", ancestorsArray);
    Shape[] shapesArray = new Shape[shapes.size()];
    for (int i = 0 ; i < shapesArray.length ; i++) {
      shapesArray[i] = shapes.get(i);
    }
    opBuilder.setAttr("shapes", shapesArray);
    opBuilder.setAttr("shape_inference_graph", shapeInferenceGraph);
    opBuilder.setAttr("key", key);
    if (options != null) {
      for (Options opts : options) {
        if (opts.costEstimateNs != null) {
          opBuilder.setAttr("cost_estimate_ns", opts.costEstimateNs);
        }
        if (opts.tpuCore != null) {
          opBuilder.setAttr("tpu_core", opts.tpuCore);
        }
      }
    }
    return new XlaHostCompute(opBuilder.build());
  }

  /**
   * Sets the costEstimateNs option.
   *
   * @param costEstimateNs Estimated duration of the host computation in nanoseconds.
   * @return this Options instance.
   */
  public static Options costEstimateNs(Long costEstimateNs) {
    return new Options().costEstimateNs(costEstimateNs);
  }

  /**
   * Sets the tpuCore option.
   *
   * @param tpuCore Default core to use for host to device transfers.
   * @return this Options instance.
   */
  public static Options tpuCore(Long tpuCore) {
    return new Options().tpuCore(tpuCore);
  }

  /**
   * Gets outputs.
   * A list of tensors that will be returned to the device.
   * @return outputs.
   */
  public List<Output<?>> outputs() {
    return outputs;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) outputs.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaHostCompute}
   */
  public static class Options {
    private Long costEstimateNs;

    private Long tpuCore;

    private Options() {
    }

    /**
     * Sets the costEstimateNs option.
     *
     * @param costEstimateNs Estimated duration of the host computation in nanoseconds.
     * @return this Options instance.
     */
    public Options costEstimateNs(Long costEstimateNs) {
      this.costEstimateNs = costEstimateNs;
      return this;
    }

    /**
     * Sets the tpuCore option.
     *
     * @param tpuCore Default core to use for host to device transfers.
     * @return this Options instance.
     */
    public Options tpuCore(Long tpuCore) {
      this.tpuCore = tpuCore;
      return this;
    }
  }
}
