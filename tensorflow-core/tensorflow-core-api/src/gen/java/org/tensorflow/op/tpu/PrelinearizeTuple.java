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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.List;
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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * An op which linearizes multiple Tensor values to an opaque variant tensor.
 */
@OpMetadata(
    opType = PrelinearizeTuple.OP_NAME,
    inputsClass = PrelinearizeTuple.Inputs.class
)
public final class PrelinearizeTuple extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PrelinearizeTuple";

  private Output<? extends TType> output;

  @SuppressWarnings("unchecked")
  public PrelinearizeTuple(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new PrelinearizeTuple operation.
   *
   * @param scope current scope
   * @param inputs A list of tensors that will be provided using the infeed mechanism.
   * @param shapes The shapes of each tensor in {@code inputs}.
   * @param options carries optional attribute values
   * @return a new instance of PrelinearizeTuple
   */
  @Endpoint(
      describeByClass = true
  )
  public static PrelinearizeTuple create(Scope scope, Iterable<Operand<?>> inputs,
      List<Shape> shapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "PrelinearizeTuple");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    Shape[] shapesArray = new Shape[shapes.size()];
    for (int i = 0 ; i < shapesArray.length ; i++) {
      shapesArray[i] = shapes.get(i);
    }
    opBuilder.setAttr("shapes", shapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.layouts != null) {
          long[] layoutsArray = new long[opts.layouts.size()];
          for (int i = 0 ; i < layoutsArray.length ; i++) {
            layoutsArray[i] = opts.layouts.get(i);
          }
          opBuilder.setAttr("layouts", layoutsArray);
        }
      }
    }
    return new PrelinearizeTuple(opBuilder.build());
  }

  /**
   * Sets the layouts option.
   *
   * @param layouts A vector holding the requested layout in minor-to-major sequence for all the
   * tuple shapes in the order the shapes appear in the &quot;shapes&quot; input. The layout
   * elements for a sub-shape can be set to -1 in which case the corresponding layout
   * will be computed by the infeed operation.
   * @return this Options instance.
   */
  public static Options layouts(List<Long> layouts) {
    return new Options().layouts(layouts);
  }

  /**
   * Sets the layouts option.
   *
   * @param layouts A vector holding the requested layout in minor-to-major sequence for all the
   * tuple shapes in the order the shapes appear in the &quot;shapes&quot; input. The layout
   * elements for a sub-shape can be set to -1 in which case the corresponding layout
   * will be computed by the infeed operation.
   * @return this Options instance.
   */
  public static Options layouts(Long... layouts) {
    return new Options().layouts(layouts);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<? extends TType> output() {
    return output;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.PrelinearizeTuple}
   */
  public static class Options {
    private List<Long> layouts;

    private Options() {
    }

    /**
     * Sets the layouts option.
     *
     * @param layouts A vector holding the requested layout in minor-to-major sequence for all the
     * tuple shapes in the order the shapes appear in the &quot;shapes&quot; input. The layout
     * elements for a sub-shape can be set to -1 in which case the corresponding layout
     * will be computed by the infeed operation.
     * @return this Options instance.
     */
    public Options layouts(List<Long> layouts) {
      this.layouts = layouts;
      return this;
    }

    /**
     * Sets the layouts option.
     *
     * @param layouts A vector holding the requested layout in minor-to-major sequence for all the
     * tuple shapes in the order the shapes appear in the &quot;shapes&quot; input. The layout
     * elements for a sub-shape can be set to -1 in which case the corresponding layout
     * will be computed by the infeed operation.
     * @return this Options instance.
     */
    public Options layouts(Long... layouts) {
      this.layouts = Arrays.asList(layouts);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = PrelinearizeTuple.class
  )
  public static class Inputs extends RawOpInputs<PrelinearizeTuple> {
    /**
     * A list of tensors that will be provided using the infeed mechanism.
     */
    public final Iterable<Operand<?>> inputs;

    /**
     * The element types of each element in `inputs`.
     */
    public final DataType[] dtypes;

    /**
     * The shapes of each tensor in `inputs`.
     */
    public final Shape[] shapes;

    /**
     * A vector holding the requested layout in minor-to-major sequence for all the
     * tuple shapes in the order the shapes appear in the "shapes" input. The layout
     * elements for a sub-shape can be set to -1 in which case the corresponding layout
     * will be computed by the infeed operation.
     */
    public final long[] layouts;

    public Inputs(GraphOperation op) {
      super(new PrelinearizeTuple(op), op, Arrays.asList("dtypes", "shapes", "layouts"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      dtypes = op.attributes().getAttrTypeList("dtypes");
      shapes = op.attributes().getAttrShapeList("shapes");
      layouts = op.attributes().getAttrIntList("layouts");
    }
  }
}
