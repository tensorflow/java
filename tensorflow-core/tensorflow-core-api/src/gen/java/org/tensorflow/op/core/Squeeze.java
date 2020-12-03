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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Removes dimensions of size 1 from the shape of a tensor.
 * <p>
 * Given a tensor `input`, this operation returns a tensor of the same type with
 * all dimensions of size 1 removed. If you don't want to remove all size 1
 * dimensions, you can remove specific size 1 dimensions by specifying
 * `axis`.
 * <p>
 * For example:
 * <pre>{@code
 * # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
 * shape(squeeze(t)) ==> [2, 3]
 * }</pre>
 * Or, to remove specific size 1 dimensions:
 * <pre>{@code
 * # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
 * shape(squeeze(t, [2, 4])) ==> [1, 2, 3, 1]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Squeeze<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Squeeze}
   */
  public static class Options {
    
    /**
     * @param axis If specified, only squeezes the dimensions listed. The dimension
     * index starts at 0. It is an error to squeeze a dimension that is not 1. Must
     * be in the range `[-rank(input), rank(input))`.
     */
    public Options axis(List<Long> axis) {
      this.axis = axis;
      return this;
    }
    
    private List<Long> axis;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Squeeze operation.
   * 
   * @param scope current scope
   * @param input The `input` to squeeze.
   * @param options carries optional attributes values
   * @return a new instance of Squeeze
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Squeeze<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Squeeze", scope.makeOpName("Squeeze"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          long[] axisArray = new long[opts.axis.size()];
          for (int i = 0; i < axisArray.length; ++i) {
            axisArray[i] = opts.axis.get(i);
          }
          opBuilder.setAttr("squeeze_dims", axisArray);
        }
      }
    }
    return new Squeeze<T>(opBuilder.build());
  }
  
  /**
   * @param axis If specified, only squeezes the dimensions listed. The dimension
   * index starts at 0. It is an error to squeeze a dimension that is not 1. Must
   * be in the range `[-rank(input), rank(input))`.
   */
  public static Options axis(List<Long> axis) {
    return new Options().axis(axis);
  }
  
  /**
   * Contains the same data as `input`, but has one or more dimensions of
   * size 1 removed.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Squeeze";
  
  private Output<T> output;
  
  private Squeeze(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
