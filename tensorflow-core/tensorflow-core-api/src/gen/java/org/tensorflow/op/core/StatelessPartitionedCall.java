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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * returns {@code f(inputs)}, where {@code f}'s body is placed and partitioned.
 * Asynchronously executes a function, potentially across multiple devices but
 * within a single process. The kernel places and partitions a given function's
 * underlying graph, and executes each of the partitioned subgraphs as a function.
 */
@OpMetadata(
    opType = StatelessPartitionedCall.OP_NAME,
    inputsClass = StatelessPartitionedCall.Inputs.class
)
@Operator
public final class StatelessPartitionedCall extends RawOp implements PartitionedCall {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PartitionedCall";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public StatelessPartitionedCall(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new PartitionedCall operation.
   *
   * @param scope current scope
   * @param args A list of input tensors.
   * @param Tout A list of output types.
   * @param f <pre>
   *   A function that takes 'args', a list of tensors, and returns 'output',
   *   another list of tensors. Input and output types are specified by 'Tin'
   *   and 'Tout'. The function body of f will be placed and partitioned across
   *   devices, setting this op apart from the regular Call op.
   * </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatelessPartitionedCall
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatelessPartitionedCall create(Scope scope, Iterable<Operand<?>> args,
      List<Class<? extends TType>> Tout, ConcreteFunction f, PartitionedCall.Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessPartitionedCall");
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    opBuilder.setAttr("f", f);
    if (options != null) {
      for (PartitionedCall.Options opts : options) {
        if (opts.config != null) {
          opBuilder.setAttr("config", opts.config);
        }
        if (opts.configProto != null) {
          opBuilder.setAttr("config_proto", opts.configProto);
        }
        if (opts.executorType != null) {
          opBuilder.setAttr("executor_type", opts.executorType);
        }
      }
    }
    return new StatelessPartitionedCall(opBuilder.build());
  }

  /**
   * Gets output.
   * A list of return values.
   * @return output.
   */
  @Override
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  @OpInputsMetadata(
      outputsClass = StatelessPartitionedCall.class
  )
  public static class Inputs extends RawOpInputs<StatelessPartitionedCall> {
    /**
     * A list of input tensors.
     */
    public final Iterable<Operand<?>> args;

    /**
     * A list of input types.
     */
    public final DataType[] Tin;

    /**
     * A list of output types.
     */
    public final DataType[] Tout;

    /**
     * The config attribute
     */
    public final String config;

    /**
     * The configProto attribute
     */
    public final String configProto;

    /**
     * The executorType attribute
     */
    public final String executorType;

    public Inputs(GraphOperation op) {
      super(new StatelessPartitionedCall(op), op, Arrays.asList("Tin", "Tout", "config", "config_proto", "executor_type"));
      int inputIndex = 0;
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      Tin = op.attributes().getAttrTypeList("Tin");
      Tout = op.attributes().getAttrTypeList("Tout");
      config = op.attributes().getAttrString("config");
      configProto = op.attributes().getAttrString("config_proto");
      executorType = op.attributes().getAttrString("executor_type");
    }
  }
}
