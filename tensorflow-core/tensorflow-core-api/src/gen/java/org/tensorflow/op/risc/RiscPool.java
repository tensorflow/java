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
import java.util.List;
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
import org.tensorflow.types.family.TNumber;

/**
 * The RiscPool operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscPool.OP_NAME,
    inputsClass = RiscPool.Inputs.class
)
public final class RiscPool<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscPool";

  private Output<T> output;

  public RiscPool(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscPool operation.
   *
   * @param scope current scope
   * @param value The value value
   * @param ksize The value of the ksize attribute
   * @param strides The value of the strides attribute
   * @param poolingType The value of the poolingType attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscPool} output and operands
   * @return a new instance of RiscPool
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscPool<T> create(Scope scope, Operand<T> value,
      List<Long> ksize, List<Long> strides, String poolingType, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscPool");
    opBuilder.addInput(value.asOutput());
    long[] ksizeArray = new long[ksize.size()];
    for (int i = 0 ; i < ksizeArray.length ; i++) {
      ksizeArray[i] = ksize.get(i);
    }
    opBuilder.setAttr("ksize", ksizeArray);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("pooling_type", poolingType);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
      }
    }
    return new RiscPool<>(opBuilder.build());
  }

  /**
   * Sets the dataFormat option.
   *
   * @param dataFormat the dataFormat option
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Gets output.
   *
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
   * Optional attributes for {@link org.tensorflow.op.risc.RiscPool}
   */
  public static class Options {
    private String dataFormat;

    private Options() {
    }

    /**
     * Sets the dataFormat option.
     *
     * @param dataFormat the dataFormat option
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RiscPool.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RiscPool<T>> {
    /**
     * The value input
     */
    public final Operand<T> value;

    /**
     * The ksize attribute
     */
    public final long[] ksize;

    /**
     * The strides attribute
     */
    public final long[] strides;

    /**
     * The poolingType attribute
     */
    public final String poolingType;

    /**
     * The dataFormat attribute
     */
    public final String dataFormat;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RiscPool<>(op), op, Arrays.asList("ksize", "strides", "pooling_type", "data_format", "T"));
      int inputIndex = 0;
      value = (Operand<T>) op.input(inputIndex++);
      ksize = op.attributes().getAttrIntList("ksize");
      strides = op.attributes().getAttrIntList("strides");
      poolingType = op.attributes().getAttrString("pooling_type");
      dataFormat = op.attributes().getAttrString("data_format");
      T = op.attributes().getAttrType("T");
    }
  }
}
