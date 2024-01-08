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
=======================================================================

*/
package org.tensorflow.op;

import org.tensorflow.AbstractGradientAdapter;
import org.tensorflow.Graph;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.internal.c_api.TF_Scope;

import java.util.List;

/** A native adapter for {@link RawCustomGradient}. */
final class RawGradientAdapter extends AbstractGradientAdapter {

  private final RawCustomGradient gradient;

  RawGradientAdapter(RawCustomGradient gradient) {
    super();
    this.gradient = gradient;
  }

  @Override
  protected List<Operand<?>> apply(Graph graph, TF_Scope scope, GraphOperation operation, List<Output<?>> gradInputs) {
    Scope nativeScope = new NativeScope(scope, graph, null).withSubScope(operation.name());
    return gradient.call(new Ops(nativeScope), operation, gradInputs);
  }
}