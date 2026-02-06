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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * The XlaSparseActivationsUnstack operation
 */
@OpMetadata(
    opType = XlaSparseActivationsUnstack.OP_NAME,
    inputsClass = XlaSparseActivationsUnstack.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseActivationsUnstack<U extends TType> extends RawOp implements Iterable<Operand<U>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseActivationsUnstack";

  private List<Output<U>> unstackedActivations;

  @SuppressWarnings("unchecked")
  public XlaSparseActivationsUnstack(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int unstackedActivationsLength = operation.outputListLength("unstacked_activations");
    unstackedActivations = Arrays.asList((Output<U>[]) operation.outputList(outputIdx, unstackedActivationsLength));
    outputIdx += unstackedActivationsLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseActivationsUnstack operation.
   *
   * @param scope current scope
   * @param stackedActivations The stackedActivations value
   * @param numTables The value of the numTables attribute
   * @param sampleCounts The value of the sampleCounts attribute
   * @param features The value of the features attribute
   * @param interleaved The value of the interleaved attribute
   * @param dtype The value of the dtype attribute
   * @param <U> data type for {@code XlaSparseActivationsUnstack} output and operands
   * @return a new instance of XlaSparseActivationsUnstack
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> XlaSparseActivationsUnstack<U> create(Scope scope,
      Operand<? extends TType> stackedActivations, Long numTables, List<Long> sampleCounts,
      List<Long> features, Boolean interleaved, Class<U> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseActivationsUnstack");
    opBuilder.addInput(stackedActivations.asOutput());
    opBuilder.setAttr("num_tables", numTables);
    long[] sampleCountsArray = new long[sampleCounts.size()];
    for (int i = 0 ; i < sampleCountsArray.length ; i++) {
      sampleCountsArray[i] = sampleCounts.get(i);
    }
    opBuilder.setAttr("sample_counts", sampleCountsArray);
    long[] featuresArray = new long[features.size()];
    for (int i = 0 ; i < featuresArray.length ; i++) {
      featuresArray[i] = features.get(i);
    }
    opBuilder.setAttr("features", featuresArray);
    opBuilder.setAttr("interleaved", interleaved);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new XlaSparseActivationsUnstack<>(opBuilder.build());
  }

  /**
   * Gets unstackedActivations.
   *
   * @return unstackedActivations.
   */
  public List<Output<U>> unstackedActivations() {
    return unstackedActivations;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<U>> iterator() {
    return (Iterator) unstackedActivations.iterator();
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseActivationsUnstack.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseActivationsUnstack<?>> {
    /**
     * The stackedActivations input
     */
    public final Operand<? extends TType> stackedActivations;

    /**
     * The sampleCounts attribute
     */
    public final long[] sampleCounts;

    /**
     * The features attribute
     */
    public final long[] features;

    /**
     * The interleaved attribute
     */
    public final boolean interleaved;

    /**
     * The inputDtype attribute
     */
    public final DataType inputDtype;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new XlaSparseActivationsUnstack<>(op), op, Arrays.asList("sample_counts", "features", "interleaved", "input_dtype", "dtype"));
      int inputIndex = 0;
      stackedActivations = (Operand<? extends TType>) op.input(inputIndex++);
      sampleCounts = op.attributes().getAttrIntList("sample_counts");
      features = op.attributes().getAttrIntList("features");
      interleaved = op.attributes().getAttrBool("interleaved");
      inputDtype = op.attributes().getAttrType("input_dtype");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
