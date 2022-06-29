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

package org.tensorflow.op.risc;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscRandomUniform operation
 */
@OpMetadata(
    opType = RiscRandomUniform.OP_NAME,
    inputsClass = RiscRandomUniform.Inputs.class
)
public final class RiscRandomUniform extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscRandomUniform";

  private Output<TFloat32> output;

  public RiscRandomUniform(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscRandomUniform operation.
   *
   * @param scope current scope
   * @param shape The shape value
   * @param options carries optional attribute values
   * @return a new instance of RiscRandomUniform
   */
  @Endpoint(
      describeByClass = true
  )
  public static RiscRandomUniform create(Scope scope, Operand<? extends TNumber> shape,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscRandomUniform");
    opBuilder.addInput(shape.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
      }
    }
    return new RiscRandomUniform(opBuilder.build());
  }

  /**
   * Sets the seed option.
   *
   * @param seed the seed option
   * @return this Options instance.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TFloat32> output() {
    return output;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.risc.RiscRandomUniform}
   */
  public static class Options {
    private Long seed;

    private Options() {
    }

    /**
     * Sets the seed option.
     *
     * @param seed the seed option
     * @return this Options instance.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RiscRandomUniform.class
  )
  public static class Inputs extends RawOpInputs<RiscRandomUniform> {
    /**
     * The shape input
     */
    public final Operand<? extends TNumber> shape;

    /**
     * The seed attribute
     */
    public final long seed;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RiscRandomUniform(op), op, Arrays.asList("seed", "T"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      seed = op.attributes().getAttrInt("seed");
      T = op.attributes().getAttrType("T");
    }
  }
}
