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

/** A {@link Scope} implementation backed by a native scope. Only used for gradient declarations. */
public final class GradientScope implements Scope {

  @Override
  public ExecutionEnvironment env() {
    return graph;
  }

  @Override
  public GradientScope withSubScope(String childScopeName) {
    return new GradientScope(nativeScope.NewSubScope(childScopeName), graph, null, device);
  }

  @Override
  public GradientScope withName(String opName) {
    return new GradientScope(nativeScope, graph, opName, device);
  }

  @Override
  public GradientScope withNameAsSubScope(String defaultName) {
    if (opName == null) {
      return withSubScope(defaultName);
    } else {
      return withSubScope(opName);
    }
  }

  @Override
  public GradientScope withDevice(DeviceSpec newDevice) {
    return new GradientScope(
        nativeScope.WithDevice(newDevice.toString()), graph, newDevice.toString());
  }

  @Override
  public Scope withInitScope() {
    throw new IllegalStateException("Can't add init operations in a gradient scope");
  }

  @Override
  public String makeOpName(String defaultName) {
    String name = opName != null ? opName : defaultName;
    return nativeScope.GetUniqueNameForOp(name);
  }

  @Override
  public String makeUnique(String id) {
    return nativeScope.GetUniqueNameForOp(id);
  }

  @Override
  public void refreshNames() {}

  @Override
  public GradientScope withControlDependencies(Iterable<Op> controls) {
    List<Op> controlDeps =
        StreamSupport.stream(controls.spliterator(), false).collect(Collectors.toList());
    NativeOperation ops = new NativeOperation(controlDeps.size());

    for (int i = 0; i < controlDeps.size(); i++) {
      Operation op = controlDeps.get(i).op();
      if (!(op instanceof GraphOperation)) {
        throw new IllegalArgumentException("Can only add graph ops as control dependencies");
      }
      ops.position(i)
          .put(new NativeOperation(((GraphOperation) op).getUnsafeNativeHandle().node()));
    }

    return new GradientScope(
        nativeScope.WithControlDependencies(new NativeOperation(ops)), graph, device);
  }

  @Override
  public Scope withControlDependencyOps(Iterable<Operation> controls) {
    List<Operation> controlDeps =
        StreamSupport.stream(controls.spliterator(), false).collect(Collectors.toList());
    NativeOperation ops = new NativeOperation(controlDeps.size());

    for (int i = 0; i < controlDeps.size(); i++) {
      Operation op = controlDeps.get(i);
      if (!(op instanceof GraphOperation)) {
        throw new IllegalArgumentException("Can only add graph ops as control dependencies");
      }
      ops.position(i)
          .put(new NativeOperation(((GraphOperation) op).getUnsafeNativeHandle().node()));
    }

    return new GradientScope(
        nativeScope.WithControlDependencies(new NativeOperation(ops)), graph, device);
  }

  @Override
  public OperationBuilder apply(OperationBuilder builder) {
    return builder;
  }

  @Override
  public void onOpCreated(Operation op) {}

  @Override
  public String getDeviceString() {
    if (device == null) {
      throw new UnsupportedOperationException(
          "Can't get device string for gradient scope unless it has been explicitly set");
    } else {
      return device;
    }
  }

  @Override
  public boolean isInit() {
    return false;
  }

  GradientScope(TF_Scope nativeScope, Graph graph, String device) {
    this(nativeScope, graph, null, device);
  }

  private GradientScope(TF_Scope nativeScope, Graph graph, String opName, String device) {
    this.graph = graph;
    this.nativeScope = nativeScope;
    this.opName = opName;
    this.device = device;
  }

  private final Graph graph;
  private final TF_Scope nativeScope;
  private final String opName;
  private final String device;
}
