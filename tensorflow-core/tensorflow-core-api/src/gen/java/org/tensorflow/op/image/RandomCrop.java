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

package org.tensorflow.op.image;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Randomly crop {@code image}.
 * {@code size} is a 1-D int64 tensor with 2 elements representing the crop height and
 * width.  The values must be non negative.
 * <p>This Op picks a random location in {@code image} and crops a {@code height} by {@code width}
 * rectangle from that location.  The random location is picked so the cropped
 * area will fit inside the original image.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RandomCrop.OP_NAME,
    inputsClass = RandomCrop.Inputs.class
)
@Operator(
    group = "image"
)
public final class RandomCrop<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomCrop";

  private Output<T> output;

  public RandomCrop(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomCrop operation.
   *
   * @param scope current scope
   * @param image 3-D of shape {@code [height, width, channels]}.
   * @param sizeOutput 1-D of length 2 containing: {@code crop_height}, {@code crop_width}..
   * @param options carries optional attribute values
   * @param <T> data type for {@code RandomCrop} output and operands
   * @return a new instance of RandomCrop
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RandomCrop<T> create(Scope scope, Operand<T> image,
      Operand<TInt64> sizeOutput, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomCrop");
    opBuilder.addInput(image.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
      }
    }
    return new RandomCrop<>(opBuilder.build());
  }

  /**
   * Sets the seed option.
   *
   * @param seed If either seed or seed2 are set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, it is seeded by a
   * random seed.
   * @return this Options instance.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }

  /**
   * Sets the seed2 option.
   *
   * @param seed2 An second seed to avoid seed collision.
   * @return this Options instance.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }

  /**
   * Gets output.
   * 3-D of shape {@code [crop_height, crop_width, channels].}
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.RandomCrop}
   */
  public static class Options {
    private Long seed;

    private Long seed2;

    private Options() {
    }

    /**
     * Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     * @return this Options instance.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }

    /**
     * Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RandomCrop.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RandomCrop<T>> {
    /**
     * 3-D of shape {@code [height, width, channels]}.
     */
    public final Operand<T> image;

    /**
     * 1-D of length 2 containing: {@code crop_height}, {@code crop_width}..
     */
    public final Operand<TInt64> sizeOutput;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If either seed or seed2 are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public final long seed;

    /**
     * An second seed to avoid seed collision.
     */
    public final long seed2;

    public Inputs(GraphOperation op) {
      super(new RandomCrop<>(op), op, Arrays.asList("T", "seed", "seed2"));
      int inputIndex = 0;
      image = (Operand<T>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
    }
  }
}
