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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An op merges elements of integer and float tensors into deduplication data as
 * XLA tuple.
 * This op merges outputs of SplitDedupDataOp, which gives two 1-D tensors, integer
 * and floating point. With respect to tuple_mask, this op merges values of these
 * two tensors into an XLA tuple, which should be as same as input to
 * SplitDedupDataOp.
 */
@OpMetadata(
    opType = MergeDedupData.OP_NAME,
    inputsClass = MergeDedupData.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class MergeDedupData extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MergeDedupData";

  private Output<? extends TType> output;

  @SuppressWarnings("unchecked")
  public MergeDedupData(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MergeDedupData operation.
   *
   * @param scope current scope
   * @param integerTensor A 1-D integer tensor, includes integer elements of deduplication data tuple.
   * @param floatTensor A 1-D float tensor, includes float elements of deduplication data tuple.
   * @param tupleMask A serialized TensorProto string of output tuple mask. This mask is a 2-D tensor,
   * with first column as tuple element type, and second column as span of this type.
   * For example, an output tuple of (1, 2, 0.1, 3), its mask is [[0, 2], [1, 1], [0,
   * 1]]. We expect only two types of elements: integer(0) and float(1).
   * @param options carries optional attribute values
   * @return a new instance of MergeDedupData
   */
  @Endpoint(
      describeByClass = true
  )
  public static MergeDedupData create(Scope scope, Operand<? extends TNumber> integerTensor,
      Operand<? extends TNumber> floatTensor, String tupleMask, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MergeDedupData");
    opBuilder.addInput(integerTensor.asOutput());
    opBuilder.addInput(floatTensor.asOutput());
    opBuilder.setAttr("tuple_mask", tupleMask);
    if (options != null) {
      for (Options opts : options) {
        if (opts.config != null) {
          opBuilder.setAttr("config", opts.config);
        }
      }
    }
    return new MergeDedupData(opBuilder.build());
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
   * Gets output.
   * An XLA tuple merging integer and float elements as deduplication data tuple.
   * @return output.
   */
  public Output<? extends TType> output() {
    return output;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.MergeDedupData}
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
      outputsClass = MergeDedupData.class
  )
  public static class Inputs extends RawOpInputs<MergeDedupData> {
    /**
     * A 1-D integer tensor, includes integer elements of deduplication data tuple.
     */
    public final Operand<? extends TNumber> integerTensor;

    /**
     * A 1-D float tensor, includes float elements of deduplication data tuple.
     */
    public final Operand<? extends TNumber> floatTensor;

    /**
     * A serialized TensorProto string of output tuple mask. This mask is a 2-D tensor,
     * with first column as tuple element type, and second column as span of this type.
     * For example, an output tuple of (1, 2, 0.1, 3), its mask is [[0, 2], [1, 1], [0,
     * 1]]. We expect only two types of elements: integer(0) and float(1).
     */
    public final String tupleMask;

    /**
     * integer_tensor type. Allowed types: {int32, int64, uint32, uint64}.
     */
    public final DataType integerType;

    /**
     * float_tensor type. Allowed types: {half, bfloat16, float}.
     */
    public final DataType floatType;

    /**
     * The config attribute
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new MergeDedupData(op), op, Arrays.asList("tuple_mask", "integer_type", "float_type", "config"));
      int inputIndex = 0;
      integerTensor = (Operand<? extends TNumber>) op.input(inputIndex++);
      floatTensor = (Operand<? extends TNumber>) op.input(inputIndex++);
      tupleMask = op.attributes().getAttrString("tuple_mask");
      integerType = op.attributes().getAttrType("integer_type");
      floatType = op.attributes().getAttrType("float_type");
      config = op.attributes().getAttrString("config");
    }
  }
}
