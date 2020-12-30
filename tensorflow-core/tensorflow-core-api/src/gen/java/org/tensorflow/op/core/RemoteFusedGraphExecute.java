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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Execute a sub graph on a remote processor.
 * <p>
 * The graph specifications(such as graph itself, input tensors and output names)
 * are stored as a serialized protocol buffer of RemoteFusedGraphExecuteInfo
 * as serialized_remote_fused_graph_execute_info.
 * The specifications will be passed to a dedicated registered
 * remote fused graph executor.  The executor will send the graph specifications
 * to a remote processor and execute that graph.  The execution results
 * will be passed to consumer nodes as outputs of this node.
 */
@Operator
public final class RemoteFusedGraphExecute extends RawOp implements Iterable<Operand<TType>> {
  
  /**
   * Factory method to create a class wrapping a new RemoteFusedGraphExecute operation.
   * 
   * @param scope current scope
   * @param inputs Arbitrary number of tensors with arbitrary data types
   * @param Toutputs 
   * @param serializedRemoteFusedGraphExecuteInfo Serialized protocol buffer
   * of RemoteFusedGraphExecuteInfo which contains graph specifications.
   * @return a new instance of RemoteFusedGraphExecute
   */
  @Endpoint(describeByClass = true)
  public static RemoteFusedGraphExecute create(Scope scope, Iterable<Operand<?>> inputs, List<Class<? extends TType>> Toutputs, String serializedRemoteFusedGraphExecuteInfo) {
    OperationBuilder opBuilder = scope.env().opBuilder("RemoteFusedGraphExecute", scope.makeOpName("RemoteFusedGraphExecute"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Toutputs", Operands.toDataTypes(Toutputs));
    opBuilder.setAttr("serialized_remote_fused_graph_execute_info", serializedRemoteFusedGraphExecuteInfo);
    return new RemoteFusedGraphExecute(opBuilder.build());
  }
  
  /**
   * Arbitrary number of tensors with arbitrary data types
   */
  public List<Output<?>> outputs() {
    return outputs;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) outputs.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RemoteFusedGraphExecute";
  
  private List<Output<?>> outputs;
  
  private RemoteFusedGraphExecute(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }
}
