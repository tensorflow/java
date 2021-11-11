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
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * A pseudo-op to represent host-side computation in an XLA program.
 */
@OpMetadata(
    opType = XlaHostCompute.OP_NAME,
    inputsClass = XlaHostCompute.Inputs.class
)
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
  public XlaHostCompute(Operation operation) {
    super(operation, OP_NAME);
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
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaHostCompute");
    opBuilder.addInputList(Operands.asOutputs(inputs));
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
        if (opts.sendKey != null) {
          opBuilder.setAttr("send_key", opts.sendKey);
        }
        if (opts.recvKey != null) {
          opBuilder.setAttr("recv_key", opts.recvKey);
        }
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
   * Sets the sendKey option.
   *
   * @param sendKey the sendKey option
   * @return this Options instance.
   */
  public static Options sendKey(String sendKey) {
    return new Options().sendKey(sendKey);
  }

  /**
   * Sets the recvKey option.
   *
   * @param recvKey the recvKey option
   * @return this Options instance.
   */
  public static Options recvKey(String recvKey) {
    return new Options().recvKey(recvKey);
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
    private String sendKey;

    private String recvKey;

    private Long costEstimateNs;

    private Long tpuCore;

    private Options() {
    }

    /**
     * Sets the sendKey option.
     *
     * @param sendKey the sendKey option
     * @return this Options instance.
     */
    public Options sendKey(String sendKey) {
      this.sendKey = sendKey;
      return this;
    }

    /**
     * Sets the recvKey option.
     *
     * @param recvKey the recvKey option
     * @return this Options instance.
     */
    public Options recvKey(String recvKey) {
      this.recvKey = recvKey;
      return this;
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

  @OpInputsMetadata(
      outputsClass = XlaHostCompute.class
  )
  public static class Inputs extends RawOpInputs<XlaHostCompute> {
    /**
     * A list of tensors that will be sent to the host.
     */
    public final Iterable<Operand<?>> inputs;

    /**
     * The element types of each element in `inputs`.
     */
    public final DataType[] Tinputs;

    /**
     * The element types of each element in `outputs`.
     */
    public final DataType[] Toutputs;

    /**
     * A list of names of HostCompute computations that must be
     * sequenced before this computation.
     */
    public final String[] ancestors;

    /**
     * If shape_inference_graph is empty, a list of the shapes of `outputs`.
     */
    public final Shape[] shapes;

    /**
     * A unique identifier for this region used to match up host transfers.
     */
    public final String key;

    /**
     * The sendKey attribute
     */
    public final String sendKey;

    /**
     * The recvKey attribute
     */
    public final String recvKey;

    /**
     * Estimated duration of the host computation in nanoseconds.
     */
    public final long costEstimateNs;

    /**
     * Default core to use for host to device transfers.
     */
    public final long tpuCore;

    public Inputs(GraphOperation op) {
      super(new XlaHostCompute(op), op, Arrays.asList("Tinputs", "Toutputs", "ancestors", "shapes", "key", "send_key", "recv_key", "cost_estimate_ns", "tpu_core"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      Tinputs = op.attributes().getAttrTypeList("Tinputs");
      Toutputs = op.attributes().getAttrTypeList("Toutputs");
      ancestors = op.attributes().getAttrStringList("ancestors");
      shapes = op.attributes().getAttrShapeList("shapes");
      key = op.attributes().getAttrString("key");
      sendKey = op.attributes().getAttrString("send_key");
      recvKey = op.attributes().getAttrString("recv_key");
      costEstimateNs = op.attributes().getAttrInt("cost_estimate_ns");
      tpuCore = op.attributes().getAttrInt("tpu_core");
    }
  }
}
