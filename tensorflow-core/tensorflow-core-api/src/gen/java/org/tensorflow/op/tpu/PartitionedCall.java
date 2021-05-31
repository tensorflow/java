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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Calls a function placed on a specified TPU device.
 */
public final class PartitionedCall extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUPartitionedCall";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  private PartitionedCall(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new TPUPartitionedCall operation.
   *
   * @param scope current scope
   * @param args The arguments to the function.
   * @param deviceOrdinal The TPU device ordinal to run the function on.
   * @param Tout The types of the outputs of the function.
   * @param f The function to call.
   * @param options carries optional attribute values
   * @return a new instance of PartitionedCall
   */
  @Endpoint(
      describeByClass = true
  )
  public static PartitionedCall create(Scope scope, Iterable<Operand<?>> args,
      Operand<TInt32> deviceOrdinal, List<Class<? extends TType>> Tout, ConcreteFunction f,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TPUPartitionedCall", scope.makeOpName("PartitionedCall"));
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.addInput(deviceOrdinal.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    opBuilder.setAttr("f", f);
    if (options != null) {
      for (Options opts : options) {
        if (opts.autotunerThresh != null) {
          opBuilder.setAttr("autotuner_thresh", opts.autotunerThresh);
        }
      }
    }
    return new PartitionedCall(opBuilder.build());
  }

  /**
   * Sets the autotunerThresh option.
   *
   * @param autotunerThresh the autotunerThresh option
   * @return this Options instance.
   */
  public static Options autotunerThresh(Long autotunerThresh) {
    return new Options().autotunerThresh(autotunerThresh);
  }

  /**
   * Gets output.
   * The output of the function call.
   * @return output.
   */
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.PartitionedCall}
   */
  public static class Options {
    private Long autotunerThresh;

    private Options() {
    }

    /**
     * Sets the autotunerThresh option.
     *
     * @param autotunerThresh the autotunerThresh option
     * @return this Options instance.
     */
    public Options autotunerThresh(Long autotunerThresh) {
      this.autotunerThresh = autotunerThresh;
      return this;
    }
  }
}
