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
import org.tensorflow.op.JavaScope;
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
  public final Ops core;
  public final NnOps nn;
  public final SetOps sets;
  public final MathOps math;
  public final LinalgOps linalg;
  private final Scope scope;

  /**
   * Creates a FrameworkOps instance with the provided scope
   *
   * @param scope the scope
   */
  private FrameworkOps(Scope scope) {
    this.core = Ops.create(scope.env());
    this.scope = scope;
    nn = new NnOps(this);
    sets = new SetOps(this);
    math = new MathOps(this);
    linalg = new LinalgOps(this);
  }

  /**
   * Creates a FrameworkOps instance based on the provided Core Ops
   *
   * @param core The TensorFlow Core Ops
   */
  private FrameworkOps(Ops core) {
    this.core = core;
    this.scope = core.scope();
    nn = new NnOps(this);
    sets = new SetOps(this);
    math = new MathOps(this);
    linalg = new LinalgOps(this);
  }

  /**
   * Creates an API for building operations in the provided execution environment
   *
   * @param env the exection environment
   * @return the FrameworkOps
   */
  public static FrameworkOps create(ExecutionEnvironment env) {
    return new FrameworkOps(new JavaScope(env));
  }

  /**
   * Creates an API for building operations in the default eager execution environment
   *
   * <p>Invoking this method is equivalent to {@code
   * FrameworkOps.create(EagerSession.getDefault())}.
   *
   * @return the FrameworkOps
   */
  public static FrameworkOps create() {
    return new FrameworkOps(new JavaScope(EagerSession.getDefault()));
  }

  /**
   * Creates an API for building operations in the default eager execution environment
   *
   * @param coreOps the TensorFlow core Ops
   * @return the FrameworkOps
   */
  public static FrameworkOps create(Ops coreOps) {
    return new FrameworkOps(coreOps);
  }

  /**
   * Returns the current {@link Scope scope} of this API
   *
   * @return the current {@link Scope scope} of this API
   */
  public final Scope scope() {
    return scope;
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
   *
   * @param deviceSpec the device specification for the scope
   * @return the FrameworkOps
   */
  public FrameworkOps withDevice(DeviceSpec deviceSpec) {
    return new FrameworkOps(scope.withDevice(deviceSpec));
  }

  /**
   * Returns an API that adds operations to the graph with the provided control dependencies.
   *
   * <p>{@link Scope#withControlDependencies(Iterable)}
   *
   * @param controls the operations
   * @return the FrameworkOps
   */
  public FrameworkOps withControlDependencies(Iterable<Op> controls) {
    return new FrameworkOps(scope.withControlDependencies(controls));
  }
}
