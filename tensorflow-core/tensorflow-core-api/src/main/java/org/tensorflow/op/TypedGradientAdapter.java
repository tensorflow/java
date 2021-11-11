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
=======================================================================

*/
package org.tensorflow.op;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.BaseGradientAdapter;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.internal.c_api.NativeOperation;
import org.tensorflow.internal.c_api.NativeOutputVector;
import org.tensorflow.internal.c_api.NativeStatus;
import org.tensorflow.internal.c_api.TF_Scope;

/** A native adapter for {@link CustomGradient}. */
final class TypedGradientAdapter<T extends RawOpInputs<?>> extends BaseGradientAdapter {

  private final CustomGradient<T> gradient;
  private final Class<T> opInputClass;
  private final Constructor<T> ctor;

  TypedGradientAdapter(CustomGradient<T> gradient, Class<T> opInputClass) {
    super();
    this.gradient = gradient;
    this.opInputClass = opInputClass;
    //noinspection unchecked
    this.ctor = (Constructor<T>) this.opInputClass.getDeclaredConstructors()[0];
  }

  @Override
  public NativeStatus call(
      TF_Scope scope,
      NativeOperation op,
      NativeOutputVector grad_inputs,
      NativeOutputVector grad_outputs) {
    try (PointerScope pointerScope = new PointerScope()) {
      Graph g = Graph.findGraphForPointer(scope.graph());
      if (g == null) {
        throw new IllegalStateException("No graph found for native gradient scope.");
      }

      T rawOp = ctor.newInstance(BaseGradientAdapter.getGraphOp(g, op.node()));

      Scope nativeScope =
          new GradientScope(scope, g, null).withSubScope(rawOp.getOutputs().op().name());

      Ops tf = new Ops(nativeScope);

      List<Output<?>> gradInputs = BaseGradientAdapter.fromNativeOutputs(g, grad_inputs);

      // The graph locks are not re-entrant, so attempting to add an op to a graph that has been
      // locked by the gradient builder will fail without this.
      BaseGradientAdapter.useDangerousLockedBuilders(g, true);
      List<Operand<?>> gradOutputs = gradient.call(tf, rawOp, gradInputs);
      BaseGradientAdapter.useDangerousLockedBuilders(g, false);

      BaseGradientAdapter.putToNativeOutputs(gradOutputs, grad_outputs);

    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Could not instantiate Op class " + opInputClass, e);
    }
    return NativeStatus.OK();
  }
}
