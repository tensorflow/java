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

package org.tensorflow.op.xla;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Splits resource variable input tensor across all dimensions.
 * An op which splits the resource variable input tensor based on the given
 * num_splits attribute, pads slices optionally, and returned the slices. Slices
 * are returned in row-major order.
 * <p>This op may be generated via the TPU bridge.
 * <p>For example, with {@code input} tensor:
 * <pre>
 * [[0, 1, 2],
 *  [3, 4, 5],
 *  [6, 7, 8]]
 * </pre>
 * <p>{@code num_splits}:
 * <pre>
 * [2, 2]
 * </pre>
 * <p>and {@code paddings}:
 * <pre>
 * [1, 1]
 * </pre>
 * <p>the expected {@code outputs} is:
 * <pre>
 * [[0, 1],
 *  [3, 4]]
 * [[2, 0],
 *  [5, 0]]
 * [[6, 7],
 *  [0, 0]]
 * [[8, 0],
 *  [0, 0]]
 * </pre>
 *
 * @param <T> data type for {@code outputs} output
 */
@OpMetadata(
    opType = ReadVariableSplitND.OP_NAME,
    inputsClass = ReadVariableSplitND.Inputs.class
)
public final class ReadVariableSplitND<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReadVariableXlaSplitND";

  private List<Output<T>> outputs;

  @SuppressWarnings("unchecked")
  public ReadVariableSplitND(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new ReadVariableXlaSplitND operation.
   *
   * @param scope current scope
   * @param resource Resource variable of input tensor to split across all dimensions.
   * }
   * out_arg {
   * name: &quot;outputs&quot;
   * description: &lt;&lt;END
   * Output slices based on input and num_splits defined, in row-major order.
   * @param T The value of the T attribute
   * @param N The value of the N attribute
   * @param numSplits Number of ways to split per dimension. Shape dimensions must be evenly
   * divisible.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ReadVariableXlaSplitND} output and operands
   * @return a new instance of ReadVariableSplitND
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ReadVariableSplitND<T> create(Scope scope,
      Operand<? extends TType> resource, Class<T> T, Long N, List<Long> numSplits,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReadVariableSplitND");
    opBuilder.addInput(resource.asOutput());
    opBuilder.setAttr("T", Operands.toDataType(T));
    opBuilder.setAttr("N", N);
    long[] numSplitsArray = new long[numSplits.size()];
    for (int i = 0 ; i < numSplitsArray.length ; i++) {
      numSplitsArray[i] = numSplits.get(i);
    }
    opBuilder.setAttr("num_splits", numSplitsArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.paddings != null) {
          long[] paddingsArray = new long[opts.paddings.size()];
          for (int i = 0 ; i < paddingsArray.length ; i++) {
            paddingsArray[i] = opts.paddings.get(i);
          }
          opBuilder.setAttr("paddings", paddingsArray);
        }
      }
    }
    return new ReadVariableSplitND<>(opBuilder.build());
  }

  /**
   * Sets the paddings option.
   *
   * @param paddings Optional list of right paddings per dimension of input tensor to apply before
   * splitting. This can be used to make a dimension evenly divisible.
   * @return this Options instance.
   */
  public static Options paddings(List<Long> paddings) {
    return new Options().paddings(paddings);
  }

  /**
   * Sets the paddings option.
   *
   * @param paddings Optional list of right paddings per dimension of input tensor to apply before
   * splitting. This can be used to make a dimension evenly divisible.
   * @return this Options instance.
   */
  public static Options paddings(Long... paddings) {
    return new Options().paddings(paddings);
  }

  /**
   * Gets outputs.
   *
   * @return outputs.
   */
  public List<Output<T>> outputs() {
    return outputs;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) outputs.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.ReadVariableSplitND}
   */
  public static class Options {
    private List<Long> paddings;

    private Options() {
    }

    /**
     * Sets the paddings option.
     *
     * @param paddings Optional list of right paddings per dimension of input tensor to apply before
     * splitting. This can be used to make a dimension evenly divisible.
     * @return this Options instance.
     */
    public Options paddings(List<Long> paddings) {
      this.paddings = paddings;
      return this;
    }

    /**
     * Sets the paddings option.
     *
     * @param paddings Optional list of right paddings per dimension of input tensor to apply before
     * splitting. This can be used to make a dimension evenly divisible.
     * @return this Options instance.
     */
    public Options paddings(Long... paddings) {
      this.paddings = Arrays.asList(paddings);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ReadVariableSplitND.class
  )
  public static class Inputs extends RawOpInputs<ReadVariableSplitND<?>> {
    /**
     * Resource variable of input tensor to split across all dimensions.
     * }
     * out_arg {
     * name: &quot;outputs&quot;
     * description: &lt;&lt;END
     * Output slices based on input and num_splits defined, in row-major order.
     */
    public final Operand<? extends TType> resource;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Number of ways to split per dimension. Shape dimensions must be evenly
     * divisible.
     */
    public final long[] numSplits;

    /**
     * Optional list of right paddings per dimension of input tensor to apply before
     * splitting. This can be used to make a dimension evenly divisible.
     */
    public final long[] paddings;

    public Inputs(GraphOperation op) {
      super(new ReadVariableSplitND<>(op), op, Arrays.asList("T", "num_splits", "paddings"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      numSplits = op.attributes().getAttrIntList("num_splits");
      paddings = op.attributes().getAttrIntList("paddings");
    }
  }
}
