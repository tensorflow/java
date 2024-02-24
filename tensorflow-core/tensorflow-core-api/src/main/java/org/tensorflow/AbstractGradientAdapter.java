/*
 Copyright 2024 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/
package org.tensorflow;

import java.util.ArrayList;
import java.util.List;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TFJ_GradFuncAdapter;
import org.tensorflow.internal.c_api.TFJ_GraphId;
import org.tensorflow.internal.c_api.TFJ_Scope;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;

/** Helper base class for custom gradient adapters <b>INTERNAL USE ONLY</b> */
public abstract class AbstractGradientAdapter extends TFJ_GradFuncAdapter {

  protected AbstractGradientAdapter() {
    super();
  }

  protected abstract List<Operand<?>> apply(
      Graph graph, TFJ_Scope scope, GraphOperation operation, List<Output<?>> gradInputs);

  @Override
  public int call(
      TFJ_GraphId nativeGraphId,
      TFJ_Scope nativeScope,
      TF_Operation nativeOperation,
      TF_Output nativeGradInputs,
      int nativeGradInputsLength,
      PointerPointer nativeGradOutputsPtr) {
    try (PointerScope callScope = new PointerScope()) {
      var graph = Graph.findGraph(nativeGraphId);
      var operation = new GraphOperation(graph, nativeOperation);
      var gradInputs = fromNativeOutputs(graph, nativeGradInputs, nativeGradInputsLength);

      // The graph locks are not re-entrant, so attempting to add an op to a graph that has been
      // locked by the gradient builder will fail without this.
      graph.setDangerousGradientBuilder(true);
      List<Operand<?>> gradOutputs = apply(graph, nativeScope, operation, gradInputs);
      graph.setDangerousGradientBuilder(false);

      nativeGradOutputsPtr.put(toNativeOutputs(gradOutputs));
      return gradOutputs.size();
    }
  }

  /**
   * Convert an array of native outputs to a list of {@link Output}s.
   *
   * @param g the graph the outputs are in
   * @param nativeOutputs array of outputs
   * @param length number of outputs
   * @return a list of Outputs
   */
  private static List<Output<?>> fromNativeOutputs(Graph g, TF_Output nativeOutputs, int length) {
    List<Output<?>> outputs = new ArrayList<>(length);
    for (int i = 0; i < length; ++i) {
      var nativeOutput = nativeOutputs.position(i);
      outputs.add(
          i, new Output<>(new GraphOperation(g, nativeOutput.oper()), nativeOutput.index()));
    }
    return outputs;
  }

  /**
   * Put the Java outputs into the array of native outputs, resizing it to the necessary size.
   *
   * @param outputs the outputs to put
   * @return pointer to the native array of outputs
   */
  private static TF_Output toNativeOutputs(List<Operand<?>> outputs) {
    // Use malloc to allocate native outputs, as they will be freed by the native layer and we do
    // not want JavaCPP to deallocate them
    var nativeOutputs =
        new TF_Output(Pointer.malloc((long) outputs.size() * Pointer.sizeof(TF_Output.class)));

    for (int i = 0; i < outputs.size(); ++i) {
      var output = outputs.get(i).asOutput();
      var nativeOutput = nativeOutputs.getPointer(i);
      nativeOutput.oper(((GraphOperation) output.op()).getUnsafeNativeHandle());
      nativeOutput.index(output.index());
    }
    return nativeOutputs;
  }
}
