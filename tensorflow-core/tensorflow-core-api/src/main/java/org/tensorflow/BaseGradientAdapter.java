/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.GradFunc;
import org.tensorflow.internal.c_api.NativeOutput;
import org.tensorflow.internal.c_api.NativeOutputVector;
import org.tensorflow.internal.c_api.Node;
import org.tensorflow.internal.c_api.TF_Operation;

/** Helper base class for custom gradient adapters <b>INTERNAL USE ONLY</b> */
public abstract class BaseGradientAdapter extends GradFunc {

  protected BaseGradientAdapter() {
    super();
  }

  /**
   * Convert an array of native outputs to a list of {@link Output}s.
   *
   * @param g the graph the outputs are in
   * @param nativeOutputs the native outputs to convert
   * @return a list of Outputs
   */
  protected static List<Output<?>> fromNativeOutputs(Graph g, NativeOutputVector nativeOutputs) {
    List<Output<?>> gradInputs = new ArrayList<>((int) nativeOutputs.size());
    for (int i = 0; i < nativeOutputs.size(); i++) {
      NativeOutput output = nativeOutputs.get(i);
      gradInputs.add(new Output<>(getGraphOp(g, output.node()), output.index()));
    }
    return gradInputs;
  }

  /**
   * Put the Java outputs into the array of native outputs, resizing it to the necessary size.
   *
   * @param outputs the outputs to put
   * @param nativeOutputs the native array to put the outputs into
   */
  protected static void putToNativeOutputs(
      List<Operand<?>> outputs, NativeOutputVector nativeOutputs) {
    nativeOutputs.resize(outputs.size());
    for (int i = 0; i < outputs.size(); i++) {
      Output<?> output = outputs.get(i).asOutput();
      Node node = ((GraphOperation) output.op()).getUnsafeNativeHandle().node();
      nativeOutputs.put(i, new NativeOutput(node, output.index()));
    }
  }

  /**
   * Make a {@link GraphOperation} from a native {@link Node}
   *
   * @param g the graph the operation is in
   * @param node the native node
   * @return a graph operation with the underlying native node
   */
  protected static GraphOperation getGraphOp(Graph g, Node node) {
    try (PointerScope scope = new PointerScope();
        Graph.Reference ref = g.ref()) {
      return new GraphOperation(g, new TF_Operation(node));
    }
  }

  /**
   * Use builders without locking. This should only be used during custom gradient building.
   *
   * <p>The graph locks are not re-entrant, so attempting to add an op to a graph that has been
   * locked by the gradient builder will fail without this.
   */
  protected static void useDangerousLockedBuilders(Graph g, boolean dangerous) {
    g.setDangerousGradientBuilder(dangerous);
  }
}
