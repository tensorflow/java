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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Extracts a glimpse from the input tensor.
 * Returns a set of windows called glimpses extracted at location
 * {@code offsets} from the input tensor. If the windows only partially
 * overlaps the inputs, the non overlapping areas will be filled with
 * random noise.
 * <p>The result is a 4-D tensor of shape {@code [batch_size, glimpse_height, glimpse_width, channels]}. The channels and batch dimensions are the
 * same as that of the input tensor. The height and width of the output
 * windows are specified in the {@code size} parameter.
 * <p>The argument {@code normalized} and {@code centered} controls how the windows are built:
 * <ul>
 * <li>If the coordinates are normalized but not centered, 0.0 and 1.0
 * correspond to the minimum and maximum of each height and width
 * dimension.</li>
 * <li>If the coordinates are both normalized and centered, they range from
 * -1.0 to 1.0. The coordinates (-1.0, -1.0) correspond to the upper
 * left corner, the lower right corner is located at (1.0, 1.0) and the
 * center is at (0, 0).</li>
 * <li>If the coordinates are not normalized they are interpreted as
 * numbers of pixels.</li>
 * </ul>
 */
@OpMetadata(
    opType = ExtractGlimpse.OP_NAME,
    inputsClass = ExtractGlimpse.Inputs.class
)
public final class ExtractGlimpse extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExtractGlimpseV2";

  private Output<TFloat32> glimpse;

  public ExtractGlimpse(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    glimpse = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExtractGlimpseV2 operation.
   *
   * @param scope current scope
   * @param input A 4-D float tensor of shape {@code [batch_size, height, width, channels]}.
   * @param sizeOutput A 1-D tensor of 2 elements containing the size of the glimpses
   * to extract.  The glimpse height must be specified first, following
   * by the glimpse width.
   * @param offsets A 2-D integer tensor of shape {@code [batch_size, 2]} containing
   * the y, x locations of the center of each window.
   * @param options carries optional attribute values
   * @return a new instance of ExtractGlimpse
   */
  @Endpoint(
      describeByClass = true
  )
  public static ExtractGlimpse create(Scope scope, Operand<TFloat32> input,
      Operand<TInt32> sizeOutput, Operand<TFloat32> offsets, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ExtractGlimpse");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder.addInput(offsets.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.centered != null) {
          opBuilder.setAttr("centered", opts.centered);
        }
        if (opts.normalized != null) {
          opBuilder.setAttr("normalized", opts.normalized);
        }
        if (opts.uniformNoise != null) {
          opBuilder.setAttr("uniform_noise", opts.uniformNoise);
        }
        if (opts.noise != null) {
          opBuilder.setAttr("noise", opts.noise);
        }
      }
    }
    return new ExtractGlimpse(opBuilder.build());
  }

  /**
   * Sets the centered option.
   *
   * @param centered indicates if the offset coordinates are centered relative to
   * the image, in which case the (0, 0) offset is relative to the center
   * of the input images. If false, the (0,0) offset corresponds to the
   * upper left corner of the input images.
   * @return this Options instance.
   */
  public static Options centered(Boolean centered) {
    return new Options().centered(centered);
  }

  /**
   * Sets the normalized option.
   *
   * @param normalized indicates if the offset coordinates are normalized.
   * @return this Options instance.
   */
  public static Options normalized(Boolean normalized) {
    return new Options().normalized(normalized);
  }

  /**
   * Sets the uniformNoise option.
   *
   * @param uniformNoise indicates if the noise should be generated using a
   * uniform distribution or a Gaussian distribution.
   * @return this Options instance.
   */
  public static Options uniformNoise(Boolean uniformNoise) {
    return new Options().uniformNoise(uniformNoise);
  }

  /**
   * Sets the noise option.
   *
   * @param noise indicates if the noise should {@code uniform}, {@code gaussian}, or
   * {@code zero}. The default is {@code uniform} which means the noise type
   * will be decided by {@code uniform_noise}.
   * @return this Options instance.
   */
  public static Options noise(String noise) {
    return new Options().noise(noise);
  }

  /**
   * Gets glimpse.
   * A tensor representing the glimpses {@code [batch_size, glimpse_height, glimpse_width, channels]}.
   * @return glimpse.
   */
  public Output<TFloat32> glimpse() {
    return glimpse;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return glimpse;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.ExtractGlimpse}
   */
  public static class Options {
    private Boolean centered;

    private Boolean normalized;

    private Boolean uniformNoise;

    private String noise;

    private Options() {
    }

    /**
     * Sets the centered option.
     *
     * @param centered indicates if the offset coordinates are centered relative to
     * the image, in which case the (0, 0) offset is relative to the center
     * of the input images. If false, the (0,0) offset corresponds to the
     * upper left corner of the input images.
     * @return this Options instance.
     */
    public Options centered(Boolean centered) {
      this.centered = centered;
      return this;
    }

    /**
     * Sets the normalized option.
     *
     * @param normalized indicates if the offset coordinates are normalized.
     * @return this Options instance.
     */
    public Options normalized(Boolean normalized) {
      this.normalized = normalized;
      return this;
    }

    /**
     * Sets the uniformNoise option.
     *
     * @param uniformNoise indicates if the noise should be generated using a
     * uniform distribution or a Gaussian distribution.
     * @return this Options instance.
     */
    public Options uniformNoise(Boolean uniformNoise) {
      this.uniformNoise = uniformNoise;
      return this;
    }

    /**
     * Sets the noise option.
     *
     * @param noise indicates if the noise should {@code uniform}, {@code gaussian}, or
     * {@code zero}. The default is {@code uniform} which means the noise type
     * will be decided by {@code uniform_noise}.
     * @return this Options instance.
     */
    public Options noise(String noise) {
      this.noise = noise;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ExtractGlimpse.class
  )
  public static class Inputs extends RawOpInputs<ExtractGlimpse> {
    /**
     * A 4-D float tensor of shape {@code [batch_size, height, width, channels]}.
     */
    public final Operand<TFloat32> input;

    /**
     * A 1-D tensor of 2 elements containing the size of the glimpses
     * to extract.  The glimpse height must be specified first, following
     * by the glimpse width.
     */
    public final Operand<TInt32> sizeOutput;

    /**
     * A 2-D integer tensor of shape {@code [batch_size, 2]} containing
     * the y, x locations of the center of each window.
     */
    public final Operand<TFloat32> offsets;

    /**
     * indicates if the offset coordinates are centered relative to
     * the image, in which case the (0, 0) offset is relative to the center
     * of the input images. If false, the (0,0) offset corresponds to the
     * upper left corner of the input images.
     */
    public final boolean centered;

    /**
     * indicates if the offset coordinates are normalized.
     */
    public final boolean normalized;

    /**
     * indicates if the noise should be generated using a
     * uniform distribution or a Gaussian distribution.
     */
    public final boolean uniformNoise;

    /**
     * indicates if the noise should `uniform`, `gaussian`, or
     * `zero`. The default is `uniform` which means the noise type
     * will be decided by `uniform_noise`.
     */
    public final String noise;

    public Inputs(GraphOperation op) {
      super(new ExtractGlimpse(op), op, Arrays.asList("centered", "normalized", "uniform_noise", "noise"));
      int inputIndex = 0;
      input = (Operand<TFloat32>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt32>) op.input(inputIndex++);
      offsets = (Operand<TFloat32>) op.input(inputIndex++);
      centered = op.attributes().getAttrBool("centered");
      normalized = op.attributes().getAttrBool("normalized");
      uniformNoise = op.attributes().getAttrBool("uniform_noise");
      noise = op.attributes().getAttrString("noise");
    }
  }
}
