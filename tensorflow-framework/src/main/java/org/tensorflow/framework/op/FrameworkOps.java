/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.op;

import org.tensorflow.DeviceSpec;
import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;

/**
 * An API for building framework operations as {@link Op Op}s
 *
 * <p>These are higher level ops that may invoke core ops. Higher level Ops may perform the
 * operation solely in the TensorFlow framework or do preprocessing of the Operands before invoking
 * a core level Op.
 */
public class FrameworkOps {
  public final Ops coreOps;
  private final Scope scope;

  public final NnOps nn;
  public final SetsOps sets;
  public final MathOps math;

  /**
   * Creates a FrameworkOps instance with the provided scope
   *
   * @param scope the scope
   */
  private FrameworkOps(Scope scope) {
    this.coreOps = Ops.create(scope.env());
    this.scope = scope;
    nn = new NnOps(this);
    sets = new SetsOps(this);
    math = new MathOps(this);
  }

  /**
   * Creates a FrameworkOps instance based on the provided Core Ops
   *
   * @param coreOps The TensorFlow Core Ops
   */
  private FrameworkOps(Ops coreOps) {
    this.coreOps = coreOps;
    this.scope = coreOps.scope();
    nn = new NnOps(this);
    sets = new SetsOps(this);
    math = new MathOps(this);
  }


  /** Returns the current {@link Scope scope} of this API */
  public final Scope scope() {
    return scope;
  }

  /**
   * Gets the core Ops
   *
   * @return coreOps
   */
  public final Ops coreOps() {
    return coreOps;
  }

  /**
   * Returns an API that builds operations with the provided name prefix.
   *
   * <p>@link Scope#withSubScope(String)}
   */
  public FrameworkOps withSubScope(String childScopeName) {
    return new FrameworkOps(scope.withSubScope(childScopeName));
  }

  /**
   * Returns an API that uses the provided name for an op.
   *
   * <p>{@link Scope#withName(String)}
   */
  public FrameworkOps withName(String opName) {
    return new FrameworkOps(scope.withName(opName));
  }

  /**
   * Returns an API that places the created operations on the device(s) matching the provided spec.
   *
   * <p>{@link Scope#withDevice(DeviceSpec)}
   */
  public FrameworkOps withDevice(DeviceSpec deviceSpec) {
    return new FrameworkOps(scope.withDevice(deviceSpec));
  }

  /**
   * Returns an API that adds operations to the graph with the provided control dependencies.
   *
   * <p>{@link Scope#withControlDependencies(Iterable)}
   */
  public FrameworkOps withControlDependencies(Iterable<Op> controls) {
    return new FrameworkOps(scope.withControlDependencies(controls));
  }

  /** Creates an API for building operations in the provided execution environment */
  public static FrameworkOps create(ExecutionEnvironment env) {
    return new FrameworkOps(new Scope(env));
  }

  /**
   * Creates an API for building operations in the default eager execution environment
   *
   * <p>Invoking this method is equivalent to {@code
   * FrameworkOps.create(EagerSession.getDefault())}.
   */
  public static FrameworkOps create() {
    return new FrameworkOps(new Scope(EagerSession.getDefault()));
  }

  /**
   * Creates an API for building operations in the default eager execution environment
   *
   * @param coreOps the TensorFlow core Ops
   */
  public static FrameworkOps create(Ops coreOps) {
    return new FrameworkOps(coreOps);
  }
}
