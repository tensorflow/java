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

import org.tensorflow.AbstractGradientAdapter;
import org.tensorflow.Graph;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.internal.c_api.TFJ_Scope;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/** A native adapter for {@link CustomGradient}. */
final class TypedGradientAdapter<T extends RawOpInputs<?>> extends AbstractGradientAdapter {

  private final CustomGradient<T> gradient;
  private final Class<T> opInputClass;
  private final Constructor<T> ctor;

  @SuppressWarnings("unchecked")
  TypedGradientAdapter(CustomGradient<T> gradient, Class<T> opInputClass) {
    super();
    this.gradient = gradient;
    this.opInputClass = opInputClass;
    this.ctor = (Constructor<T>) this.opInputClass.getDeclaredConstructors()[0];
  }

  @Override
  protected List<Operand<?>> apply(Graph graph, TFJ_Scope scope, GraphOperation operation, List<Output<?>> gradInputs) {
    try {
      T rawOp = ctor.newInstance(operation);
      Scope nativeScope = new NativeScope(scope, graph, null).withSubScope(rawOp.getOutputs().op().name());
      return gradient.call(new Ops(nativeScope), rawOp, gradInputs);

    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Could not instantiate Op class " + opInputClass, e);
    }
  }
}