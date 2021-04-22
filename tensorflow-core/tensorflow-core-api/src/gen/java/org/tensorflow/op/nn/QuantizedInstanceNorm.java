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

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Quantized Instance normalization.
 *
 * @param <T> data type for {@code y} output
 */
@Operator(
    group = "nn"
)
public final class QuantizedInstanceNorm<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedInstanceNorm";

  private Output<T> y;

  private Output<TFloat32> yMin;

  private Output<TFloat32> yMax;

  private QuantizedInstanceNorm(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
    yMin = operation.output(outputIdx++);
    yMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedInstanceNorm operation.
   *
   * @param scope current scope
   * @param x A 4D input Tensor.
   * @param xMin The value represented by the lowest quantized input.
   * @param xMax The value represented by the highest quantized input.
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizedInstanceNorm} output and operands
   * @return a new instance of QuantizedInstanceNorm
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> QuantizedInstanceNorm<T> create(Scope scope, Operand<T> x,
      Operand<TFloat32> xMin, Operand<TFloat32> xMax, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedInstanceNorm", scope.makeOpName("QuantizedInstanceNorm"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(xMin.asOutput());
    opBuilder.addInput(xMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.outputRangeGiven != null) {
          opBuilder.setAttr("output_range_given", opts.outputRangeGiven);
        }
        if (opts.givenYMin != null) {
          opBuilder.setAttr("given_y_min", opts.givenYMin);
        }
        if (opts.givenYMax != null) {
          opBuilder.setAttr("given_y_max", opts.givenYMax);
        }
        if (opts.varianceEpsilon != null) {
          opBuilder.setAttr("variance_epsilon", opts.varianceEpsilon);
        }
        if (opts.minSeparation != null) {
          opBuilder.setAttr("min_separation", opts.minSeparation);
        }
      }
    }
    return new QuantizedInstanceNorm<>(opBuilder.build());
  }

  /**
   * Sets the outputRangeGiven option.
   *
   * @param outputRangeGiven If True, {@code given_y_min} and {@code given_y_min}
   * and {@code given_y_max} are used as the output range. Otherwise,
   * the implementation computes the output range.
   * @return this Options instance.
   */
  public static Options outputRangeGiven(Boolean outputRangeGiven) {
    return new Options().outputRangeGiven(outputRangeGiven);
  }

  /**
   * Sets the givenYMin option.
   *
   * @param givenYMin Output in {@code y_min} if {@code output_range_given} is True.
   * @return this Options instance.
   */
  public static Options givenYMin(Float givenYMin) {
    return new Options().givenYMin(givenYMin);
  }

  /**
   * Sets the givenYMax option.
   *
   * @param givenYMax Output in {@code y_max} if {@code output_range_given} is True.
   * @return this Options instance.
   */
  public static Options givenYMax(Float givenYMax) {
    return new Options().givenYMax(givenYMax);
  }

  /**
   * Sets the varianceEpsilon option.
   *
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @return this Options instance.
   */
  public static Options varianceEpsilon(Float varianceEpsilon) {
    return new Options().varianceEpsilon(varianceEpsilon);
  }

  /**
   * Sets the minSeparation option.
   *
   * @param minSeparation Minimum value of {@code y_max - y_min}
   * @return this Options instance.
   */
  public static Options minSeparation(Float minSeparation) {
    return new Options().minSeparation(minSeparation);
  }

  /**
   * Gets y.
   * A 4D Tensor.
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  /**
   * Gets yMin.
   * The value represented by the lowest quantized output.
   * @return yMin.
   */
  public Output<TFloat32> yMin() {
    return yMin;
  }

  /**
   * Gets yMax.
   * The value represented by the highest quantized output.
   * @return yMax.
   */
  public Output<TFloat32> yMax() {
    return yMax;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedInstanceNorm}
   */
  public static class Options {
    private Boolean outputRangeGiven;

    private Float givenYMin;

    private Float givenYMax;

    private Float varianceEpsilon;

    private Float minSeparation;

    private Options() {
    }

    /**
     * Sets the outputRangeGiven option.
     *
     * @param outputRangeGiven If True, {@code given_y_min} and {@code given_y_min}
     * and {@code given_y_max} are used as the output range. Otherwise,
     * the implementation computes the output range.
     * @return this Options instance.
     */
    public Options outputRangeGiven(Boolean outputRangeGiven) {
      this.outputRangeGiven = outputRangeGiven;
      return this;
    }

    /**
     * Sets the givenYMin option.
     *
     * @param givenYMin Output in {@code y_min} if {@code output_range_given} is True.
     * @return this Options instance.
     */
    public Options givenYMin(Float givenYMin) {
      this.givenYMin = givenYMin;
      return this;
    }

    /**
     * Sets the givenYMax option.
     *
     * @param givenYMax Output in {@code y_max} if {@code output_range_given} is True.
     * @return this Options instance.
     */
    public Options givenYMax(Float givenYMax) {
      this.givenYMax = givenYMax;
      return this;
    }

    /**
     * Sets the varianceEpsilon option.
     *
     * @param varianceEpsilon A small float number to avoid dividing by 0.
     * @return this Options instance.
     */
    public Options varianceEpsilon(Float varianceEpsilon) {
      this.varianceEpsilon = varianceEpsilon;
      return this;
    }

    /**
     * Sets the minSeparation option.
     *
     * @param minSeparation Minimum value of {@code y_max - y_min}
     * @return this Options instance.
     */
    public Options minSeparation(Float minSeparation) {
      this.minSeparation = minSeparation;
      return this;
    }
  }
}
