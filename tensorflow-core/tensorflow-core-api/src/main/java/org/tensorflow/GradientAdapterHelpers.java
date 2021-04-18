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

import static org.tensorflow.internal.c_api.global.tensorflow.ToOperation;

import java.util.ArrayList;
import java.util.List;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.NativeOutput;
import org.tensorflow.internal.c_api.NativeOutputVector;
import org.tensorflow.internal.c_api.Node;

/**
 * Helpers for {@link org.tensorflow.op.TypedGradientAdapter} and {@link
 * org.tensorflow.op.RawGradientAdapter}.
 */
public class GradientAdapterHelpers {

  /**
   * Convert a array of native outputs to a list of {@link Output}s.
   *
   * @param g             the graph the outputs are in
   * @param nativeOutputs the native outputs to convert
   */
  public static List<Output<?>> fromNativeOutputs(Graph g, NativeOutputVector nativeOutputs) {
    List<Output<?>> gradInputs = new ArrayList<>((int) nativeOutputs.size());
    for (int i = 0; i < nativeOutputs.size(); i++) {
      NativeOutput output = nativeOutputs.get(i);
      gradInputs.add(new Output<>(getGraphOp(g, output.node()),
          output.index()));
    }
    return gradInputs;
  }

  /**
   * Put the Java outputs into the array of native outputs, resizing it to the necessary size.
   *
   * @param outputs       the outputs to put
   * @param nativeOutputs the native array to put the outputs into
   */
  public static void putToNativeOutputs(List<Operand<?>> outputs,
      NativeOutputVector nativeOutputs) {
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
   * @param g    the graph the operation is in
   * @param node the native node
   * @return a graph operation with the underlying native node
   */
  public static GraphOperation getGraphOp(Graph g, Node node) {
    try (PointerScope scope = new PointerScope();
        Graph.Reference ref = g.ref()) {
      return new GraphOperation(g, ToOperation(node));
    }
  }

  public static void useDangerousLockedBuilders(Graph g, boolean dangerous) {
    g.setDangerousGradientBuilder(dangerous);
  }
}
