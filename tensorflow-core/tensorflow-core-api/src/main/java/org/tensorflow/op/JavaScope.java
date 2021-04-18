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
import org.tensorflow.DeviceSpec;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.OperationBuilder;

/**
 * A Java implementation of {@link Scope}.  This is used in all cases except custom gradient
 * definitions.
 */
public final class JavaScope implements Scope {

  /**
   * Create a new top-level scope.
   *
   * @param env The execution environment used by the scope.
   */
  public JavaScope(ExecutionEnvironment env) {
    this(env, new NameScope(), new ArrayList<>(), DeviceSpec.newBuilder().build());
  }

  @Override
  public ExecutionEnvironment env() {
    return env;
  }

  @Override
  public JavaScope withSubScope(String childScopeName) {
    return new JavaScope(env, nameScope.withSubScope(childScopeName), controlDependencies,
        deviceSpec);
  }

  @Override
  public JavaScope withName(String opName) {
    return new JavaScope(env, nameScope.withName(opName), controlDependencies, deviceSpec);
  }

  @Override
  public JavaScope withNameAsSubScope(String defaultName) {
    return new JavaScope(env, nameScope.withSubScope(nameScope.makeOpName(defaultName)),
        controlDependencies, deviceSpec);
  }

  @Override
  public JavaScope withDevice(DeviceSpec deviceSpec) {
    return new JavaScope(env, nameScope, controlDependencies, deviceSpec);
  }

  @Override
  public String makeOpName(String defaultName) {
    return nameScope.makeOpName(defaultName);
  }

  private JavaScope(
      ExecutionEnvironment env, NameScope nameScope, Iterable<Op> controlDependencies,
      DeviceSpec deviceSpec) {
    this.env = env;
    this.nameScope = nameScope;
    this.controlDependencies = controlDependencies;
    this.deviceSpec = deviceSpec;
  }

  @Override
  public JavaScope withControlDependencies(Iterable<Op> controls) {
    for (Op control : controls) {
      env.checkInput(control);
    }
    return new JavaScope(env, nameScope, controls, deviceSpec);
  }

  @Override
  public OperationBuilder apply(OperationBuilder builder) {
    builder.setDevice(deviceSpec.toString());
    return applyControlDependencies(builder);
  }

  /**
   * Adds each Operand in controlDependencies as a control input to the provided builder.
   *
   * @param builder OperationBuilder to add control inputs to
   */
  private OperationBuilder applyControlDependencies(OperationBuilder builder) {
    for (Op control : controlDependencies) {
      builder = builder.addControlInput(control.op());
    }
    return builder;
  }

  private final ExecutionEnvironment env;
  private final Iterable<Op> controlDependencies;
  private final NameScope nameScope;
  private final DeviceSpec deviceSpec;

  /**
   * Returns device string from the scope.
   */
  public String getDeviceString() {
    return deviceSpec.toString();
  }
}
