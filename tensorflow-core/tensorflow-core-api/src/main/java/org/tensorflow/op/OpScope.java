/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.op;

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.DeviceSpec;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;

/**
 * A Java implementation of {@link Scope}. This is used in all cases except custom gradient
 * definitions.
 */
public final class OpScope implements Scope {

  /**
   * Create a new top-level scope.
   *
   * @param env The execution environment used by the scope.
   */
  public OpScope(ExecutionEnvironment env) {
    this(env, new NameScope(env), new ArrayList<>(), DeviceSpec.newBuilder().build(), false);
  }

  @Override
  public ExecutionEnvironment env() {
    return env;
  }

  @Override
  public OpScope withSubScope(String childScopeName) {
    return new OpScope(
        env, nameScope.withSubScope(childScopeName, env), controlDependencies, deviceSpec, isInit);
  }

  @Override
  public OpScope withName(String opName) {
    return new OpScope(env, nameScope.withName(opName), controlDependencies, deviceSpec, isInit);
  }

  @Override
  public OpScope withNameAsSubScope(String defaultName) {
    return new OpScope(
        env,
        nameScope.withSubScope(nameScope.makeOpName(defaultName), env),
        controlDependencies,
        deviceSpec,
        isInit);
  }

  @Override
  public OpScope withDevice(DeviceSpec newDevice) {
    return new OpScope(env, nameScope, controlDependencies, newDevice, isInit);
  }

  @Override
  public OpScope withInitScope() {
    return new OpScope(env.initEnv(), nameScope, new ArrayList<>(), deviceSpec, true);
  }

  @Override
  public String makeOpName(String defaultName) {
    return nameScope.makeOpName(defaultName);
  }

  @Override
  public String makeUnique(String id) {
    return nameScope.makeUnique(id);
  }

  @Override
  public void refreshNames() {
    nameScope.importIdsFrom(env);
  }

  private OpScope(
      ExecutionEnvironment env,
      NameScope nameScope,
      List<Operation> controlDependencies,
      DeviceSpec deviceSpec,
      boolean isInit) {
    this.env = env;
    this.nameScope = nameScope;
    this.controlDependencies = controlDependencies;
    this.deviceSpec = deviceSpec;
    this.isInit = isInit;
  }

  @Override
  public Scope withControlDependencyOps(Iterable<Operation> controls) {
    ArrayList<Operation> toAdd = new ArrayList<>();
    for (Operation control : controls) {
      env.checkInput(control);
      if (isInit && !env.isInitOp(control)) {
        throw new IllegalArgumentException("Init scope can not have non-init control dependency.");
      }
      if (isInit || !env.isInitOp(control)) {
        toAdd.add(control);
      }
    }

    return new OpScope(env, nameScope, toAdd, deviceSpec, isInit);
  }

  @Override
  public OperationBuilder apply(OperationBuilder builder) {
    builder.setDevice(deviceSpec.toString());
    for (Operation control : controlDependencies) {
      if (isInit || !env.isInitOp(control)) {
        builder.addControlInput(control);
      }
    }
    return builder;
  }

  @Override
  public void onOpCreated(Operation op) {
    if (isInit) {
      env.registerInitOp(op);
    }
  }

  @Override
  public boolean isInit() {
    return isInit;
  }

  @Override
  public String getDeviceString() {
    return deviceSpec.toString();
  }

  private final ExecutionEnvironment env;
  private final List<Operation> controlDependencies;
  private final NameScope nameScope;
  private final DeviceSpec deviceSpec;
  private final boolean isInit;
}
