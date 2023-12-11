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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An op splits input deduplication data XLA tuple into integer and floating point
 * tensors.
 * Deduplication data is an XLA tuple, which consists of integer and floating point
 * values. This op is to split these values into two groups for two types, and
 * construct each group as one tensor to return.
 *
 * @param <T> data type for {@code integer_tensor} output
 *
 * @param <U> data type for {@code float_tensor} output
 */
@OpMetadata(
    opType = SplitDedupData.OP_NAME,
    inputsClass = SplitDedupData.Inputs.class
)
public final class SplitDedupData<T extends TNumber, U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SplitDedupData";

  private Output<T> integerTensor;

  private Output<U> floatTensor;

  public SplitDedupData(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    integerTensor = operation.output(outputIdx++);
    floatTensor = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SplitDedupData operation.
   *
   * @param scope current scope
   * @param input An XLA tuple including integer and float elements as deduplication data tuple.
   * @param integerType integer_tensor type. Allowed types: int32, int64, uint32, uint64.
   * @param floatType float_tensor type. Allowed types: half, bfloat16, float.
   * @param tupleMask A serialized TensorProto string of output tuple mask. This mask is a 2-D tensor,
   * with first column as tuple element type, and second column as span of this type.
   * For example, an output tuple of (1, 2, 0.1, 3), its mask is [[0, 2], [1, 1], [0,
   * 1]]. We expect only two types of elements: integer(0) and float(1).
   * @param options carries optional attribute values
   * @param <T> data type for {@code SplitDedupData} output and operands
   * @param <U> data type for {@code SplitDedupData} output and operands
   * @return a new instance of SplitDedupData
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TNumber> SplitDedupData<T, U> create(Scope scope,
      Operand<? extends TType> input, Class<T> integerType, Class<U> floatType, String tupleMask,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SplitDedupData");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("integer_type", Operands.toDataType(integerType));
    opBuilder.setAttr("float_type", Operands.toDataType(floatType));
    opBuilder.setAttr("tuple_mask", tupleMask);
    if (options != null) {
      for (Options opts : options) {
        if (opts.config != null) {
          opBuilder.setAttr("config", opts.config);
        }
      }
    }
    return new SplitDedupData<>(opBuilder.build());
  }

  /**
   * Sets the config option.
   *
   * @param config the config option
   * @return this Options instance.
   */
  public static Options config(String config) {
    return new Options().config(config);
  }

  /**
   * Gets integerTensor.
   * A 1-D integer tensor, includes integer elements of deduplication data tuple.
   * @return integerTensor.
   */
  public Output<T> integerTensor() {
    return integerTensor;
  }

  /**
   * Gets floatTensor.
   * A 1-D float tensor, includes float elements of deduplication data tuple.
   * @return floatTensor.
   */
  public Output<U> floatTensor() {
    return floatTensor;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.SplitDedupData}
   */
  public static class Options {
    private String config;

    private Options() {
    }

    /**
     * Sets the config option.
     *
     * @param config the config option
     * @return this Options instance.
     */
    public Options config(String config) {
      this.config = config;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SplitDedupData.class
  )
  public static class Inputs extends RawOpInputs<SplitDedupData<?, ?>> {
    /**
     * An XLA tuple including integer and float elements as deduplication data tuple.
     */
    public final Operand<? extends TType> input;

    /**
     * integer_tensor type. Allowed types: int32, int64, uint32, uint64.
     */
    public final DataType integerType;

    /**
     * float_tensor type. Allowed types: half, bfloat16, float.
     */
    public final DataType floatType;

    /**
     * A serialized TensorProto string of output tuple mask. This mask is a 2-D tensor,
     * with first column as tuple element type, and second column as span of this type.
     * For example, an output tuple of (1, 2, 0.1, 3), its mask is [[0, 2], [1, 1], [0,
     * 1]]. We expect only two types of elements: integer(0) and float(1).
     */
    public final String tupleMask;

    /**
     * The config attribute
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new SplitDedupData<>(op), op, Arrays.asList("integer_type", "float_type", "tuple_mask", "config"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      integerType = op.attributes().getAttrType("integer_type");
      floatType = op.attributes().getAttrType("float_type");
      tupleMask = op.attributes().getAttrString("tuple_mask");
      config = op.attributes().getAttrString("config");
    }
  }
}
