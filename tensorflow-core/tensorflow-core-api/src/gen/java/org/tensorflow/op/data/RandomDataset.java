/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a Dataset that returns pseudorandom numbers.
 * Creates a Dataset that returns a stream of uniformly distributed
 * pseudorandom 64-bit signed integers. It accepts a boolean attribute that
 * determines if the random number generators are re-applied at each epoch. The
 * default value is True which means that the seeds are applied and the same
 * sequence of random numbers are generated at each epoch. If set to False, the
 * seeds are not re-applied and a different sequence of random numbers are
 * generated at each epoch.
 * <p>In the TensorFlow Python API, you can instantiate this dataset via the
 * class {@code tf.data.experimental.RandomDatasetV2}.
 */
@OpMetadata(
    opType = RandomDataset.OP_NAME,
    inputsClass = RandomDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class RandomDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public RandomDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomDatasetV2 operation.
   *
   * @param scope current scope
   * @param seed A scalar seed for the random number generator. If either seed or
   * seed2 is set to be non-zero, the random number generator is seeded
   * by the given seed.  Otherwise, a random seed is used.
   * @param seed2 A second scalar seed to avoid seed collision.
   * @param seedGenerator A resource for the random number seed generator.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of RandomDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static RandomDataset create(Scope scope, Operand<TInt64> seed, Operand<TInt64> seed2,
      Operand<? extends TType> seedGenerator, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomDataset");
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder.addInput(seedGenerator.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.rerandomizeEachIteration != null) {
          opBuilder.setAttr("rerandomize_each_iteration", opts.rerandomizeEachIteration);
        }
        if (opts.metadata != null) {
          opBuilder.setAttr("metadata", opts.metadata);
        }
      }
    }
    return new RandomDataset(opBuilder.build());
  }

  /**
   * Sets the rerandomizeEachIteration option.
   *
   * @param rerandomizeEachIteration A boolean attribute to rerandomize the sequence of random numbers generated
   * at each epoch.
   * @return this Options instance.
   */
  public static Options rerandomizeEachIteration(Boolean rerandomizeEachIteration) {
    return new Options().rerandomizeEachIteration(rerandomizeEachIteration);
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
   * Optional attributes for {@link org.tensorflow.op.data.RandomDataset}
   */
  public static class Options {
    private Boolean rerandomizeEachIteration;

    private String metadata;

    private Options() {
    }

    /**
     * Sets the rerandomizeEachIteration option.
     *
     * @param rerandomizeEachIteration A boolean attribute to rerandomize the sequence of random numbers generated
     * at each epoch.
     * @return this Options instance.
     */
    public Options rerandomizeEachIteration(Boolean rerandomizeEachIteration) {
      this.rerandomizeEachIteration = rerandomizeEachIteration;
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
      outputsClass = RandomDataset.class
  )
  public static class Inputs extends RawOpInputs<RandomDataset> {
    /**
     * A scalar seed for the random number generator. If either seed or
     * seed2 is set to be non-zero, the random number generator is seeded
     * by the given seed.  Otherwise, a random seed is used.
     */
    public final Operand<TInt64> seed;

    /**
     * A second scalar seed to avoid seed collision.
     */
    public final Operand<TInt64> seed2;

    /**
     * A resource for the random number seed generator.
     */
    public final Operand<? extends TType> seedGenerator;

    /**
     * A boolean attribute to rerandomize the sequence of random numbers generated
     * at each epoch.
     */
    public final boolean rerandomizeEachIteration;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The metadata attribute
     */
    public final String metadata;

    public Inputs(GraphOperation op) {
      super(new RandomDataset(op), op, Arrays.asList("rerandomize_each_iteration", "output_types", "output_shapes", "metadata"));
      int inputIndex = 0;
      seed = (Operand<TInt64>) op.input(inputIndex++);
      seed2 = (Operand<TInt64>) op.input(inputIndex++);
      seedGenerator = (Operand<? extends TType>) op.input(inputIndex++);
      rerandomizeEachIteration = op.attributes().getAttrBool("rerandomize_each_iteration");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      metadata = op.attributes().getAttrString("metadata");
    }
  }
}
