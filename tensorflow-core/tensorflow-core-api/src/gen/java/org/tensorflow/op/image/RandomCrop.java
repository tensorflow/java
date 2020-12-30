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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Randomly crop `image`.
 * <p>
 * `size` is a 1-D int64 tensor with 2 elements representing the crop height and
 * width.  The values must be non negative.
 * <p>
 * This Op picks a random location in `image` and crops a `height` by `width`
 * rectangle from that location.  The random location is picked so the cropped
 * area will fit inside the original image.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "image")
public final class RandomCrop<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.RandomCrop}
   */
  public static class Options {
    
    /**
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2 An second seed to avoid seed collision.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
    
    private Long seed;
    private Long seed2;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new RandomCrop operation.
   * 
   * @param scope current scope
   * @param image 3-D of shape `[height, width, channels]`.
   * @param size 1-D of length 2 containing: `crop_height`, `crop_width`..
   * @param options carries optional attributes values
   * @return a new instance of RandomCrop
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> RandomCrop<T> create(Scope scope, Operand<T> image, Operand<TInt64> size, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("RandomCrop", scope.makeOpName("RandomCrop"));
    opBuilder.addInput(image.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new RandomCrop<T>(opBuilder.build());
  }
  
  /**
   * @param seed If either seed or seed2 are set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, it is seeded by a
   * random seed.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2 An second seed to avoid seed collision.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }
  
  /**
   * 3-D of shape `[crop_height, crop_width, channels].`
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RandomCrop";
  
  private Output<T> output;
  
  private RandomCrop(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
