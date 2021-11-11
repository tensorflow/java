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

package org.tensorflow.op.data;

import java.util.Arrays;
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
 * Makes a &quot;one-shot&quot; iterator that can be iterated only once.
 * A one-shot iterator bundles the logic for defining the dataset and
 * the state of the iterator in a single op, which allows simple input
 * pipelines to be defined without an additional initialization
 * (&quot;MakeIterator&quot;) step.
 * <p>One-shot iterators have the following limitations:
 * <ul>
 * <li>They do not support parameterization: all logic for creating the underlying
 * dataset must be bundled in the {@code dataset_factory} function.</li>
 * <li>They are not resettable. Once a one-shot iterator reaches the end of its
 * underlying dataset, subsequent &quot;IteratorGetNext&quot; operations on that
 * iterator will always produce an {@code OutOfRange} error.</li>
 * </ul>
 * <p>For greater flexibility, use &quot;Iterator&quot; and &quot;MakeIterator&quot; to define
 * an iterator using an arbitrary subgraph, which may capture tensors
 * (including fed values) as parameters, and which may be reset multiple
 * times by rerunning &quot;MakeIterator&quot;.
 */
@OpMetadata(
    opType = OneShotIterator.OP_NAME,
    inputsClass = OneShotIterator.Inputs.class
)
@Operator(
    group = "data"
)
public final class OneShotIterator extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OneShotIterator";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public OneShotIterator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new OneShotIterator operation.
   *
   * @param scope current scope
   * @param datasetFactory A function of type {@code () -> DT_VARIANT}, where the returned
   * DT_VARIANT is a dataset.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of OneShotIterator
   */
  @Endpoint(
      describeByClass = true
  )
  public static OneShotIterator create(Scope scope, ConcreteFunction datasetFactory,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OneShotIterator");
    opBuilder.setAttr("dataset_factory", datasetFactory);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new OneShotIterator(opBuilder.build());
  }

  /**
   * Sets the container option.
   *
   * @param container the container option
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName the sharedName option
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Gets handle.
   * A handle to the iterator that can be passed to an &quot;IteratorGetNext&quot;
   * op.
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.OneShotIterator}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = OneShotIterator.class
  )
  public static class Inputs extends RawOpInputs<OneShotIterator> {
    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The container attribute
     */
    public final String container;

    /**
     * The sharedName attribute
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new OneShotIterator(op), op, Arrays.asList("output_types", "output_shapes", "container", "shared_name"));
      int inputIndex = 0;
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
