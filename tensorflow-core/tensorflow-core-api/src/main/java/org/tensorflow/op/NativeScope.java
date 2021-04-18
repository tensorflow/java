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
package org.tensorflow.op;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.tensorflow.DeviceSpec;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.internal.c_api.NativeOperation;
import org.tensorflow.internal.c_api.TF_Scope;

/**
 * A {@link Scope} implementation backed by a native scope.  Only used for gradient declarations.
 */
public final class NativeScope implements Scope {

  @Override
  public ExecutionEnvironment env() {
    return graph;
  }

  @Override
  public NativeScope withSubScope(String childScopeName) {
    return new NativeScope(nativeScope.NewSubScope(childScopeName), graph, childScopeName);
  }

  @Override
  public NativeScope withName(String opName) {
    return new NativeScope(nativeScope, graph, opName);
  }

  @Override
  public NativeScope withNameAsSubScope(String defaultName) {
    return withSubScope(opName);
  }

  @Override
  public NativeScope withDevice(DeviceSpec deviceSpec) {
    return new NativeScope(nativeScope.WithDevice(deviceSpec.toString()), graph);
  }

  @Override
  public String makeOpName(String defaultName) {
    String name = opName != null ? opName : defaultName;
    return nativeScope.GetUniqueNameForOp(name);
  }

  @Override
  public NativeScope withControlDependencies(Iterable<Op> controls) {
    List<Op> controlDeps = StreamSupport.stream(controls.spliterator(), false)
        .collect(Collectors.toList());
    NativeOperation ops = new NativeOperation(controlDeps.size());

    for (int i = 0; i < controlDeps.size(); i++) {
      Operation op = controlDeps.get(i).op();
      if (!(op instanceof GraphOperation)) {
        throw new IllegalArgumentException("Can only add graph ops as control dependencies");
      }
      ops.position(i)
          .put(new NativeOperation(((GraphOperation) op).getUnsafeNativeHandle().node()));
    }

    return new NativeScope(nativeScope.WithControlDependencies(new NativeOperation(ops)), graph);
  }

  @Override
  public OperationBuilder apply(OperationBuilder builder) {
    return builder;
  }

  NativeScope(TF_Scope nativeScope, Graph graph) {
    this(nativeScope, graph, null);
  }

  private NativeScope(TF_Scope nativeScope, Graph graph, String opName) {
    this.graph = graph;
    this.nativeScope = nativeScope;
    this.opName = opName;
  }

  private final Graph graph;
  private final TF_Scope nativeScope;
  private final String opName;
}
