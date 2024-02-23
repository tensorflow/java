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
 * Creates a dataset that emits each dim-0 slice of {@code components} once.
 */
@OpMetadata(
    opType = TensorSliceDataset.OP_NAME,
    inputsClass = TensorSliceDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class TensorSliceDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorSliceDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public TensorSliceDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorSliceDataset operation.
   *
   * @param scope current scope
   * @param components The components value
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of TensorSliceDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorSliceDataset create(Scope scope, Iterable<Operand<?>> components,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorSliceDataset");
    opBuilder.addInputList(Operands.asOutputs(components));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.isFiles != null) {
          opBuilder.setAttr("is_files", opts.isFiles);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
        if (opts.replicateOnSplit != null) {
          opBuilder.setAttr("replicate_on_split", opts.replicateOnSplit);
        }
      }
    }
    return new TensorSliceDataset(opBuilder.build());
  }

  /**
   * Sets the isFiles option.
   *
   * @param isFiles the isFiles option
   * @return this Options instance.
   */
  public static Options isFiles(Boolean isFiles) {
    return new Options().isFiles(isFiles);
  }

  /**
   * Sets the metadata option.
   *
   * @param metadata the metadata option
   * @return this Options instance.
   */
  public static Options metadata(String metadata) {
    return new Options().metadata(metadata);
  }

  /**
   * Sets the replicateOnSplit option.
   *
   * @param replicateOnSplit the replicateOnSplit option
   * @return this Options instance.
   */
  public static Options replicateOnSplit(Boolean replicateOnSplit) {
    return new Options().replicateOnSplit(replicateOnSplit);
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
   * Optional attributes for {@link org.tensorflow.op.data.TensorSliceDataset}
   */
  public static class Options {
    private Boolean isFiles;

    private String metadata;

    private Boolean replicateOnSplit;

    private Options() {
    }

    /**
     * Sets the isFiles option.
     *
     * @param isFiles the isFiles option
     * @return this Options instance.
     */
    public Options isFiles(Boolean isFiles) {
      this.isFiles = isFiles;
      return this;
    }

    /**
     * Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public Options metadata(String metadata) {
      this.metadata = metadata;
      return this;
    }

    /**
     * Sets the replicateOnSplit option.
     *
     * @param replicateOnSplit the replicateOnSplit option
     * @return this Options instance.
     */
    public Options replicateOnSplit(Boolean replicateOnSplit) {
      this.replicateOnSplit = replicateOnSplit;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TensorSliceDataset.class
  )
  public static class Inputs extends RawOpInputs<TensorSliceDataset> {
    /**
     * The components input
     */
    public final Iterable<Operand<?>> components;

    /**
     * The ToutputTypes attribute
     */
    public final DataType[] ToutputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The isFiles attribute
     */
    public final boolean isFiles;

    /**
     * The metadata attribute
     */
    public final String metadata;

    /**
     * The replicateOnSplit attribute
     */
    public final boolean replicateOnSplit;

    public Inputs(GraphOperation op) {
      super(new TensorSliceDataset(op), op, Arrays.asList("Toutput_types", "output_shapes", "is_files", "metadata", "replicate_on_split"));
      int inputIndex = 0;
      int componentsLength = op.inputListLength("components");
      components = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, componentsLength));
      inputIndex += componentsLength;
      ToutputTypes = op.attributes().getAttrTypeList("Toutput_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      isFiles = op.attributes().getAttrBool("is_files");
      metadata = op.attributes().getAttrString("metadata");
      replicateOnSplit = op.attributes().getAttrBool("replicate_on_split");
    }
  }
}
