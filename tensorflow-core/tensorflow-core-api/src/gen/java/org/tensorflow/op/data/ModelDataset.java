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
 * Identity transformation that models performance.
 * Identity transformation that models performance.
 */
@OpMetadata(
    opType = ModelDataset.OP_NAME,
    inputsClass = ModelDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class ModelDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ModelDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public ModelDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ModelDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ModelDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ModelDataset create(Scope scope, Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ModelDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.algorithm != null) {
          opBuilder.setAttr("algorithm", opts.algorithm);
        }
        if (opts.cpuBudget != null) {
          opBuilder.setAttr("cpu_budget", opts.cpuBudget);
        }
        if (opts.ramBudget != null) {
          opBuilder.setAttr("ram_budget", opts.ramBudget);
        }
      }
    }
    return new ModelDataset(opBuilder.build());
  }

  /**
   * Sets the algorithm option.
   *
   * @param algorithm the algorithm option
   * @return this Options instance.
   */
  public static Options algorithm(Long algorithm) {
    return new Options().algorithm(algorithm);
  }

  /**
   * Sets the cpuBudget option.
   *
   * @param cpuBudget the cpuBudget option
   * @return this Options instance.
   */
  public static Options cpuBudget(Long cpuBudget) {
    return new Options().cpuBudget(cpuBudget);
  }

  /**
   * Sets the ramBudget option.
   *
   * @param ramBudget the ramBudget option
   * @return this Options instance.
   */
  public static Options ramBudget(Long ramBudget) {
    return new Options().ramBudget(ramBudget);
  }

  /**
   * Gets handle.
   *
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
   * Optional attributes for {@link org.tensorflow.op.data.ModelDataset}
   */
  public static class Options {
    private Long algorithm;

    private Long cpuBudget;

    private Long ramBudget;

    private Options() {
    }

    /**
     * Sets the algorithm option.
     *
     * @param algorithm the algorithm option
     * @return this Options instance.
     */
    public Options algorithm(Long algorithm) {
      this.algorithm = algorithm;
      return this;
    }

    /**
     * Sets the cpuBudget option.
     *
     * @param cpuBudget the cpuBudget option
     * @return this Options instance.
     */
    public Options cpuBudget(Long cpuBudget) {
      this.cpuBudget = cpuBudget;
      return this;
    }

    /**
     * Sets the ramBudget option.
     *
     * @param ramBudget the ramBudget option
     * @return this Options instance.
     */
    public Options ramBudget(Long ramBudget) {
      this.ramBudget = ramBudget;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ModelDataset.class
  )
  public static class Inputs extends RawOpInputs<ModelDataset> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The algorithm attribute
     */
    public final long algorithm;

    /**
     * The cpuBudget attribute
     */
    public final long cpuBudget;

    /**
     * The ramBudget attribute
     */
    public final long ramBudget;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new ModelDataset(op), op, Arrays.asList("algorithm", "cpu_budget", "ram_budget", "output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      algorithm = op.attributes().getAttrInt("algorithm");
      cpuBudget = op.attributes().getAttrInt("cpu_budget");
      ramBudget = op.attributes().getAttrInt("ram_budget");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
