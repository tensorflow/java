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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Maps a function on the list of tensors unpacked from arguments on dimension 0.
 * The function given by {@code f} is assumed to be stateless, and is executed
 * concurrently on all the slices; up to batch_size (i.e. the size of the 0th
 * dimension of each argument) functions will be scheduled at once.
 * <p>The {@code max_intra_op_parallelism} attr, which defaults to 1, can be used to
 * limit the intra op parallelism. To limit inter-op parallelism, a user can
 * set a private threadpool on the dataset using {@code tf.data.Options}'s
 * {@code ThreadingOptions}.
 * <p>Note that this op is not exposed to users directly, but is invoked in tf.data
 * rewrites.
 */
@OpMetadata(
    opType = MapDefun.OP_NAME,
    inputsClass = MapDefun.Inputs.class
)
public final class MapDefun extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MapDefun";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public MapDefun(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new MapDefun operation.
   *
   * @param scope current scope
   * @param arguments <pre>
   * A list of tensors whose types are `Targuments`, corresponding to the inputs
   * the function should be mapped over.
   * </pre>
   * @param capturedInputs <pre>
   * A list of tensors whose types are `Tcaptured`, corresponding to the captured
   * inputs of the defun.
   * </pre>
   * @param outputTypes A list of types.
   * @param outputShapes A list of shapes.
   * @param f The value of the f attribute
   * @param options carries optional attribute values
   * @return a new instance of MapDefun
   */
  @Endpoint(
      describeByClass = true
  )
  public static MapDefun create(Scope scope, Iterable<Operand<?>> arguments,
      Iterable<Operand<?>> capturedInputs, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, ConcreteFunction f, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MapDefun");
    opBuilder.addInputList(Operands.asOutputs(arguments));
    opBuilder.addInputList(Operands.asOutputs(capturedInputs));
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    opBuilder.setAttr("f", f);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxIntraOpParallelism != null) {
          opBuilder.setAttr("max_intra_op_parallelism", opts.maxIntraOpParallelism);
        }
      }
    }
    return new MapDefun(opBuilder.build());
  }

  /**
   * Sets the maxIntraOpParallelism option.
   *
   * @param maxIntraOpParallelism the maxIntraOpParallelism option
   * @return this Options instance.
   */
  public static Options maxIntraOpParallelism(Long maxIntraOpParallelism) {
    return new Options().maxIntraOpParallelism(maxIntraOpParallelism);
  }

  /**
   * Gets output.
   * <pre>
   * A list of output tensors whose types are `output_types` and whose dimensions
   * 0 are the same as the dimensions 0 of the tensors in `arguments`, and whose
   * remaining dimensions correspond to those in `output_shapes`.
   * </pre>
   * @return output.
   */
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.MapDefun}
   */
  public static class Options {
    private Long maxIntraOpParallelism;

    private Options() {
    }

    /**
     * Sets the maxIntraOpParallelism option.
     *
     * @param maxIntraOpParallelism the maxIntraOpParallelism option
     * @return this Options instance.
     */
    public Options maxIntraOpParallelism(Long maxIntraOpParallelism) {
      this.maxIntraOpParallelism = maxIntraOpParallelism;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = MapDefun.class
  )
  public static class Inputs extends RawOpInputs<MapDefun> {
    /**
     * <pre>
     * A list of tensors whose types are `Targuments`, corresponding to the inputs
     * the function should be mapped over.
     * </pre>
     */
    public final Iterable<Operand<?>> arguments;

    /**
     * <pre>
     * A list of tensors whose types are `Tcaptured`, corresponding to the captured
     * inputs of the defun.
     * </pre>
     */
    public final Iterable<Operand<?>> capturedInputs;

    /**
     * A list of types.
     */
    public final DataType[] Targuments;

    /**
     * A list of types.
     */
    public final DataType[] Tcaptured;

    /**
     * A list of types.
     */
    public final DataType[] outputTypes;

    /**
     * A list of shapes.
     */
    public final Shape[] outputShapes;

    /**
     * The maxIntraOpParallelism attribute
     */
    public final long maxIntraOpParallelism;

    public Inputs(GraphOperation op) {
      super(new MapDefun(op), op, Arrays.asList("Targuments", "Tcaptured", "output_types", "output_shapes", "max_intra_op_parallelism"));
      int inputIndex = 0;
      int argumentsLength = op.inputListLength("arguments");
      arguments = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argumentsLength));
      inputIndex += argumentsLength;
      int capturedInputsLength = op.inputListLength("captured_inputs");
      capturedInputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, capturedInputsLength));
      inputIndex += capturedInputsLength;
      Targuments = op.attributes().getAttrTypeList("Targuments");
      Tcaptured = op.attributes().getAttrTypeList("Tcaptured");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      maxIntraOpParallelism = op.attributes().getAttrInt("max_intra_op_parallelism");
    }
  }
}
