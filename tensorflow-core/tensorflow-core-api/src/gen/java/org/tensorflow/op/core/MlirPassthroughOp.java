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
 * Wraps an arbitrary MLIR computation expressed as a module with a main() function.
 * This operation does not have an associated kernel and is not intended to be
 * executed in a regular TensorFlow session. Instead it is intended to be used for
 * testing or for special case where a user intends to pass custom MLIR computation
 * through a TensorFlow graph with the intent of having custom tooling processing
 * it downstream (when targeting a different environment, like TensorFlow lite for
 * example).
 * The MLIR module is expected to have a main() function that will be used as an
 * entry point. The inputs to the operations will be passed as argument to the
 * main() function and the returned values of the main function mapped to the
 * outputs.
 * Example usage:
 * <pre>
 * import tensorflow as tf
 * from tensorflow.compiler.mlir.tensorflow.gen_mlir_passthrough_op import mlir_passthrough_op
 *
 * mlir_module = '''python
 * func {@literal @}main(%arg0 : tensor&lt;10xf32&gt;, %arg1 : tensor&lt;10xf32&gt;) -&gt; tensor&lt;10x10xf32&gt; {
 *    %add = &quot;magic.op&quot;(%arg0, %arg1) : (tensor&lt;10xf32&gt;, tensor&lt;10xf32&gt;) -&gt; tensor&lt;10x10xf32&gt;
 *    return %ret : tensor&lt;10x10xf32&gt;
 * }
 * '''
 *
 * {@literal @}tf.function
 * def foo(x, y):
 *   return mlir_passthrough_op([x, y], mlir_module, Toutputs=[tf.float32])
 *
 * graph_def = foo.get_concrete_function(tf.TensorSpec([10], tf.float32), tf.TensorSpec([10], tf.float32)).graph.as_graph_def()
 * </pre>
 */
@Operator
public final class MlirPassthroughOp extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MlirPassthroughOp";

  private List<Output<?>> outputs;

  @SuppressWarnings("unchecked")
  private MlirPassthroughOp(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new MlirPassthroughOp operation.
   *
   * @param scope current scope
   * @param inputs the inputs value
   * @param mlirModule the value of the mlirModule property
   * @param Toutputs the value of the Toutputs property
   * @return a new instance of MlirPassthroughOp
   */
  @Endpoint(
      describeByClass = true
  )
  public static MlirPassthroughOp create(Scope scope, Iterable<Operand<?>> inputs,
      String mlirModule, List<Class<? extends TType>> Toutputs) {
    OperationBuilder opBuilder = scope.env().opBuilder("MlirPassthroughOp", scope.makeOpName("MlirPassthroughOp"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("mlir_module", mlirModule);
    opBuilder.setAttr("Toutputs", Operands.toDataTypes(Toutputs));
    return new MlirPassthroughOp(opBuilder.build());
  }

  /**
   * Gets outputs.
   *
   * @return outputs.
   */
  public List<Output<?>> outputs() {
    return outputs;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) outputs.iterator();
  }
}
