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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that asynchronously prefetches elements from {@code input_dataset}.
 */
@OpMetadata(
    opType = PrefetchDataset.OP_NAME,
    inputsClass = PrefetchDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class PrefetchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PrefetchDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public PrefetchDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new PrefetchDataset operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @param bufferSize The maximum number of elements to buffer in an iterator over
   * this dataset.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of PrefetchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static PrefetchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> bufferSize, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "PrefetchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.slackPeriod != null) {
          opBuilder.setAttr("slack_period", opts.slackPeriod);
        }
        if (opts.legacyAutotune != null) {
          opBuilder.setAttr("legacy_autotune", opts.legacyAutotune);
        }
        if (opts.bufferSizeMin != null) {
          opBuilder.setAttr("buffer_size_min", opts.bufferSizeMin);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new PrefetchDataset(opBuilder.build());
  }

  /**
   * Sets the slackPeriod option.
   *
   * @param slackPeriod the slackPeriod option
   * @return this Options instance.
   */
  public static Options slackPeriod(Long slackPeriod) {
    return new Options().slackPeriod(slackPeriod);
  }

  /**
   * Sets the legacyAutotune option.
   *
   * @param legacyAutotune the legacyAutotune option
   * @return this Options instance.
   */
  public static Options legacyAutotune(Boolean legacyAutotune) {
    return new Options().legacyAutotune(legacyAutotune);
  }

  /**
   * Sets the bufferSizeMin option.
   *
   * @param bufferSizeMin the bufferSizeMin option
   * @return this Options instance.
   */
  public static Options bufferSizeMin(Long bufferSizeMin) {
    return new Options().bufferSizeMin(bufferSizeMin);
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
   * Optional attributes for {@link org.tensorflow.op.data.PrefetchDataset}
   */
  public static class Options {
    private Long slackPeriod;

    private Boolean legacyAutotune;

    private Long bufferSizeMin;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the slackPeriod option.
     *
     * @param slackPeriod the slackPeriod option
     * @return this Options instance.
     */
    public Options slackPeriod(Long slackPeriod) {
      this.slackPeriod = slackPeriod;
      return this;
    }

    /**
     * Sets the legacyAutotune option.
     *
     * @param legacyAutotune the legacyAutotune option
     * @return this Options instance.
     */
    public Options legacyAutotune(Boolean legacyAutotune) {
      this.legacyAutotune = legacyAutotune;
      return this;
    }

    /**
     * Sets the bufferSizeMin option.
     *
     * @param bufferSizeMin the bufferSizeMin option
     * @return this Options instance.
     */
    public Options bufferSizeMin(Long bufferSizeMin) {
      this.bufferSizeMin = bufferSizeMin;
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
  }

  @OpInputsMetadata(
      outputsClass = PrefetchDataset.class
  )
  public static class Inputs extends RawOpInputs<PrefetchDataset> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * The maximum number of elements to buffer in an iterator over
     * this dataset.
     */
    public final Operand<TInt64> bufferSize;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The slackPeriod attribute
     */
    public final long slackPeriod;

    /**
     * The legacyAutotune attribute
     */
    public final boolean legacyAutotune;

    /**
     * The bufferSizeMin attribute
     */
    public final long bufferSizeMin;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new PrefetchDataset(op), op, Arrays.asList("output_types", "output_shapes", "slack_period", "legacy_autotune", "buffer_size_min", "metadata"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      bufferSize = (Operand<TInt64>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      slackPeriod = op.attributes().getAttrInt("slack_period");
      legacyAutotune = op.attributes().getAttrBool("legacy_autotune");
      bufferSizeMin = op.attributes().getAttrInt("buffer_size_min");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
