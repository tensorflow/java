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
package org.tensorflow.op;

import static org.tensorflow.internal.c_api.global.tensorflow.TFJ_GetUniqueNameForOp;
import static org.tensorflow.internal.c_api.global.tensorflow.TFJ_NewScopeWithControlDependencies;
import static org.tensorflow.internal.c_api.global.tensorflow.TFJ_NewScopeWithDevice;
import static org.tensorflow.internal.c_api.global.tensorflow.TFJ_NewSubScope;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.tensorflow.DeviceSpec;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TFJ_Scope;

/** A {@link Scope} implementation backed by a native scope. */
public final class NativeScope implements Scope {

  @Override
  public ExecutionEnvironment env() {
    return graph;
  }

  @Override
  public NativeScope withSubScope(String childScopeName) {
    return new NativeScope(TFJ_NewSubScope(nativeScope, childScopeName), graph, null, device);
  }

  @Override
  public NativeScope withName(String opName) {
    return new NativeScope(nativeScope, graph, opName, device);
  }

  @Override
  public NativeScope withNameAsSubScope(String defaultName) {
    if (opName == null) {
      return withSubScope(defaultName);
    } else {
      return withSubScope(opName);
    }
  }

  @Override
  public NativeScope withDevice(DeviceSpec newDevice) {
    return new NativeScope(
        TFJ_NewScopeWithDevice(nativeScope, newDevice.toString()), graph, newDevice.toString());
  }

  @Override
  public Scope withInitScope() {
    throw new IllegalStateException("Can't add init operations in a native scope");
  }

  @Override
  public String makeOpName(String defaultName) {
    String name = opName != null ? opName : defaultName;
    return TFJ_GetUniqueNameForOp(nativeScope, name);
  }

  @Override
  public String makeUnique(String id) {
    return TFJ_GetUniqueNameForOp(nativeScope, id);
  }

  @Override
  public void refreshNames() {}

  @Override
  public Scope withControlDependencies(Iterable<Op> controls) {
    return withControlDependencyOps(StreamSupport.stream(controls.spliterator(), false)
            .map(Op::op)
            .collect(Collectors.toList()));
  }

  @Override
  public Scope withControlDependencyOps(Iterable<Operation> controls) {
    List<Operation> controlDeps =
        StreamSupport.stream(controls.spliterator(), false).collect(Collectors.toList());
    TF_Operation[] ops = new TF_Operation[controlDeps.size()];

    for (int i = 0; i < controlDeps.size(); i++) {
      Operation op = controlDeps.get(i);
      if (!(op instanceof GraphOperation)) {
        throw new IllegalArgumentException("Can only add graph ops as control dependencies");
      }
      ops[i] = ((GraphOperation) op).getUnsafeNativeHandle();
    }

    return new NativeScope(
        TFJ_NewScopeWithControlDependencies(nativeScope, ops[0], ops.length), graph, device);
  }

  @Override
  public OperationBuilder apply(OperationBuilder builder) {
    return builder;
  }

  @Override
  public String getDeviceString() {
    if (device == null) {
      throw new UnsupportedOperationException(
          "Can't get device string for native scope unless it has been explicitly set");
    } else {
      return device;
    }
  }

  @Override
  public boolean isInit() {
    return false;
  }

  NativeScope(TFJ_Scope nativeScope, Graph graph, String device) {
    this(nativeScope, graph, null, device);
  }

  private NativeScope(TFJ_Scope nativeScope, Graph graph, String opName, String device) {
    this.graph = graph;
    this.nativeScope = nativeScope;
    this.opName = opName;
    this.device = device;
  }

  private final Graph graph;
  private final TFJ_Scope nativeScope;
  private final String opName;
  private final String device;
}
