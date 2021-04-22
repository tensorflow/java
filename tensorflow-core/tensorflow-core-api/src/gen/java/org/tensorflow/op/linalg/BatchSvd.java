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

package org.tensorflow.op.linalg;

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
 * The BatchSvd operation
 *
 * @param <T> data type for {@code s} output
 */
@Operator(
    group = "linalg"
)
public final class BatchSvd<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchSvd";

  private Output<T> s;

  private Output<T> u;

  private Output<T> v;

  private BatchSvd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    s = operation.output(outputIdx++);
    u = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchSvd operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchSvd} output and operands
   * @return a new instance of BatchSvd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BatchSvd<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchSvd", scope.makeOpName("BatchSvd"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.computeUv != null) {
          opBuilder.setAttr("compute_uv", opts.computeUv);
        }
        if (opts.fullMatrices != null) {
          opBuilder.setAttr("full_matrices", opts.fullMatrices);
        }
      }
    }
    return new BatchSvd<>(opBuilder.build());
  }

  /**
   * Sets the computeUv option.
   *
   * @param computeUv the computeUv option
   * @return this Options instance.
   */
  public static Options computeUv(Boolean computeUv) {
    return new Options().computeUv(computeUv);
  }

  /**
   * Sets the fullMatrices option.
   *
   * @param fullMatrices the fullMatrices option
   * @return this Options instance.
   */
  public static Options fullMatrices(Boolean fullMatrices) {
    return new Options().fullMatrices(fullMatrices);
  }

  /**
   * Gets s.
   *
   * @return s.
   */
  public Output<T> s() {
    return s;
  }

  /**
   * Gets u.
   *
   * @return u.
   */
  public Output<T> u() {
    return u;
  }

  /**
   * Gets v.
   *
   * @return v.
   */
  public Output<T> v() {
    return v;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.BatchSvd}
   */
  public static class Options {
    private Boolean computeUv;

    private Boolean fullMatrices;

    private Options() {
    }

    /**
     * Sets the computeUv option.
     *
     * @param computeUv the computeUv option
     * @return this Options instance.
     */
    public Options computeUv(Boolean computeUv) {
      this.computeUv = computeUv;
      return this;
    }

    /**
     * Sets the fullMatrices option.
     *
     * @param fullMatrices the fullMatrices option
     * @return this Options instance.
     */
    public Options fullMatrices(Boolean fullMatrices) {
      this.fullMatrices = fullMatrices;
      return this;
    }
  }
}
