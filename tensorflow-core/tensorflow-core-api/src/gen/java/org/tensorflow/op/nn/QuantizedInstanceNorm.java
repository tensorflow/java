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
import org.tensorflow.types.family.TType;

/**
 * Quantized Instance normalization.
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "nn")
public final class QuantizedInstanceNorm<T extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedInstanceNorm}
   */
  public static class Options {
    
    /**
     * @param outputRangeGiven If True, `given_y_min` and `given_y_min`
     * and `given_y_max` are used as the output range. Otherwise,
     * the implementation computes the output range.
     */
    public Options outputRangeGiven(Boolean outputRangeGiven) {
      this.outputRangeGiven = outputRangeGiven;
      return this;
    }
    
    /**
     * @param givenYMin Output in `y_min` if `output_range_given` is True.
     */
    public Options givenYMin(Float givenYMin) {
      this.givenYMin = givenYMin;
      return this;
    }
    
    /**
     * @param givenYMax Output in `y_max` if `output_range_given` is True.
     */
    public Options givenYMax(Float givenYMax) {
      this.givenYMax = givenYMax;
      return this;
    }
    
    /**
     * @param varianceEpsilon A small float number to avoid dividing by 0.
     */
    public Options varianceEpsilon(Float varianceEpsilon) {
      this.varianceEpsilon = varianceEpsilon;
      return this;
    }
    
    /**
     * @param minSeparation Minimum value of `y_max - y_min`
     */
    public Options minSeparation(Float minSeparation) {
      this.minSeparation = minSeparation;
      return this;
    }
    
    private Boolean outputRangeGiven;
    private Float givenYMin;
    private Float givenYMax;
    private Float varianceEpsilon;
    private Float minSeparation;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new QuantizedInstanceNorm operation.
   * 
   * @param scope current scope
   * @param x A 4D input Tensor.
   * @param xMin The value represented by the lowest quantized input.
   * @param xMax The value represented by the highest quantized input.
   * @param options carries optional attributes values
   * @return a new instance of QuantizedInstanceNorm
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> QuantizedInstanceNorm<T> create(Scope scope, Operand<T> x, Operand<TFloat32> xMin, Operand<TFloat32> xMax, Options... options) {
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
    return new QuantizedInstanceNorm<T>(opBuilder.build());
  }
  
  /**
   * @param outputRangeGiven If True, `given_y_min` and `given_y_min`
   * and `given_y_max` are used as the output range. Otherwise,
   * the implementation computes the output range.
   */
  public static Options outputRangeGiven(Boolean outputRangeGiven) {
    return new Options().outputRangeGiven(outputRangeGiven);
  }
  
  /**
   * @param givenYMin Output in `y_min` if `output_range_given` is True.
   */
  public static Options givenYMin(Float givenYMin) {
    return new Options().givenYMin(givenYMin);
  }
  
  /**
   * @param givenYMax Output in `y_max` if `output_range_given` is True.
   */
  public static Options givenYMax(Float givenYMax) {
    return new Options().givenYMax(givenYMax);
  }
  
  /**
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   */
  public static Options varianceEpsilon(Float varianceEpsilon) {
    return new Options().varianceEpsilon(varianceEpsilon);
  }
  
  /**
   * @param minSeparation Minimum value of `y_max - y_min`
   */
  public static Options minSeparation(Float minSeparation) {
    return new Options().minSeparation(minSeparation);
  }
  
  /**
   * A 4D Tensor.
   */
  public Output<T> y() {
    return y;
  }
  
  /**
   * The value represented by the lowest quantized output.
   */
  public Output<TFloat32> yMin() {
    return yMin;
  }
  
  /**
   * The value represented by the highest quantized output.
   */
  public Output<TFloat32> yMax() {
    return yMax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
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
}
