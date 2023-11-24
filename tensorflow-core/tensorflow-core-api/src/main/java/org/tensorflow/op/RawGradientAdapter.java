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

final class RawGradientAdapter {}

// ---------------------------------------------------------
// NOTICE CUSTOM GRADIENT: In TF Java 0.6.0, custom gradient registration has been disabled due to the precarity of the
// Java bindings issued from the internal TensorFlow C++ APIs using JavaCPP. These APIs are subject to changes between
// TF releases, which make them difficult to maintain. If you want to reenable this feature, please uncomment the code
// between all occurrences of this notice and the "END OF CUSTOM GRADIENT" mention.
// ---------------------------------------------------------
//import java.util.List;
//import org.bytedeco.javacpp.PointerScope;
//import org.tensorflow.BaseGradientAdapter;
//import org.tensorflow.Graph;
//import org.tensorflow.GraphOperation;
//import org.tensorflow.Operand;
//import org.tensorflow.Output;
//import org.tensorflow.internal.c_api.NativeOperation;
//import org.tensorflow.internal.c_api.NativeOutputVector;
//import org.tensorflow.internal.c_api.NativeStatus;
//import org.tensorflow.internal.c_api.TF_Scope;
//
///** A native adapter for {@link RawCustomGradient}. */
//final class RawGradientAdapter extends BaseGradientAdapter {
//
//  private final RawCustomGradient gradient;
//
//  RawGradientAdapter(RawCustomGradient gradient) {
//    super();
//    this.gradient = gradient;
//  }
//
//  @Override
//  public NativeStatus call(
//      TF_Scope scope,
//      NativeOperation op,
//      NativeOutputVector grad_inputs,
//      NativeOutputVector grad_outputs) {
//    try (PointerScope pointerScope = new PointerScope()) {
//      Graph g = Graph.findGraphForPointer(scope.graph());
//      if (g == null) {
//        throw new IllegalStateException("No graph found for native gradient scope.");
//      }
//
//      GraphOperation operation = BaseGradientAdapter.getGraphOp(g, op.node());
//
//      Scope nativeScope = new GradientScope(scope, g, null).withSubScope(operation.name());
//      Ops tf = new Ops(nativeScope);
//
//      List<Output<?>> gradInputs = BaseGradientAdapter.fromNativeOutputs(g, grad_inputs);
//
//      // The graph locks are not re-entrant, so attempting to add an op to a graph that has been
//      // locked by the gradient builder will fail without this.
//      BaseGradientAdapter.useDangerousLockedBuilders(g, true);
//      List<Operand<?>> gradOutputs = gradient.call(tf, operation, gradInputs);
//      BaseGradientAdapter.useDangerousLockedBuilders(g, false);
//
//      BaseGradientAdapter.putToNativeOutputs(gradOutputs, grad_outputs);
//    }
//    return org.tensorflow.internal.c_api.global.tensorflow.OkStatus();
//  }
//}
// ---------------------------------------------------------
// END OF CUSTOM GRADIENT
// ---------------------------------------------------------