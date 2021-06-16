/* Copyright 2019-2021 The TensorFlow Authors. All Rights Reserved.

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

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.DeviceSpec;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;

/**
 * Manages groups of related properties when creating Tensorflow Operations, such as a common name
 * prefix.
 *
 * <p>A {@code Scope} is a container for common properties applied to TensorFlow Ops. Normal user
 * code initializes a {@code Scope} and provides it to Operation building classes. For example:
 *
 * <pre>{@code
 * Scope scope = new Scope(graph);
 * Constant c = Constant.create(scope, 42);
 * }</pre>
 *
 * <p>An Operation building class acquires a Scope, and uses it to set properties on the underlying
 * Tensorflow ops. For example:
 *
 * <pre>{@code
 * // An operator class that adds a constant.
 * public class Constant {
 *   public static Constant create(Scope scope, ...) {
 *      scope.graph().opBuilder(
 *        "Const", scope.makeOpName("Const"))
 *        .setAttr(...)
 *        .build()
 *      ...
 *   }
 * }
 * }</pre>
 *
 * <p><b>Scope hierarchy:</b>
 *
 * <p>A {@code Scope} provides various {@code with()} methods that create a new scope. The new scope
 * typically has one property changed while other properties are inherited from the parent scope.
 *
 * <p>An example using {@code Constant} implemented as before:
 *
 * <pre>{@code
 * Scope root = new Scope(graph);
 *
 * // The linear subscope will generate names like linear/...
 * Scope linear = Scope.withSubScope("linear");
 *
 * // This op name will be "linear/W"
 * Constant.create(linear.withName("W"), ...);
 *
 * // This op will be "linear/Const", using the default
 * // name provided by Constant
 * Constant.create(linear, ...);
 *
 * // This op will be "linear/Const_1", using the default
 * // name provided by Constant and making it unique within
 * // this scope
 * Constant.create(linear, ...);
 * }</pre>
 *
 * <p>Scope objects are <b>not</b> thread-safe.
 */
public final class Scope {

  /**
   * Create a new top-level scope.
   *
   * <p><b>For internal use only</b>, use {@link ExecutionEnvironment#baseScope()} if you need a
   * base level scope.
   *
   * @param env The execution environment used by the scope.
   */
  public Scope(ExecutionEnvironment env) {
    this(env, new NameScope(env), new ArrayList<>(), DeviceSpec.newBuilder().build(), false);
  }

  /** Returns the execution environment used by this scope. */
  public ExecutionEnvironment env() {
    return env;
  }

  /**
   * Returns a new scope where added operations will have the provided name prefix.
   *
   * <p>Ops created with this scope will have {@code name/childScopeName/} as the prefix. The actual
   * name will be unique in the returned scope. All other properties are inherited from the current
   * scope.
   *
   * <p>The child scope name must match the regular expression {@code [A-Za-z0-9.][A-Za-z0-9_.\-]*}
   *
   * @param childScopeName name for the new child scope
   * @return a new subscope
   * @throws IllegalArgumentException if the name is invalid
   */
  public Scope withSubScope(String childScopeName) {
    return new Scope(
        env, nameScope.withSubScope(childScopeName, env), controlDependencies, deviceSpec, isInit);
  }

  /**
   * Return a new scope that uses the provided name for an op.
   *
   * <p>Operations created within this scope will have a name of the form {@code
   * name/opName[_suffix]}. This lets you name a specific operator more meaningfully.
   *
   * <p>Names must match the regular expression {@code [A-Za-z0-9.][A-Za-z0-9_.\-]*}
   *
   * @param opName name for an operator in the returned scope
   * @return a new Scope that uses opName for operations.
   * @throws IllegalArgumentException if the name is invalid
   */
  public Scope withName(String opName) {
    return new Scope(env, nameScope.withName(opName), controlDependencies, deviceSpec, isInit);
  }

  /**
   * Returns a new scope where added operations will be prefixed by this scope's op name (set by
   * {@link #withName(String)}), or the given default if it is unset. This is intended to be used
   * for composite ops.
   *
   * <p>Ops created with this scope will have {@code name/opName/} as the prefix. The actual name
   * will be unique in the returned scope. All other properties are inherited from the current
   * scope.
   *
   * <p>The default child scope name must match the regular expression {@code
   * [A-Za-z0-9.][A-Za-z0-9_.\-]*}
   *
   * @param defaultName name of the sub scope if this scope's name hasn't been set.
   * @return a new subscope
   * @throws IllegalArgumentException if the name is invalid
   */
  public Scope withNameAsSubScope(String defaultName) {
    return new Scope(
        env,
        nameScope.withSubScope(nameScope.makeOpName(defaultName), env),
        controlDependencies,
        deviceSpec,
        isInit);
  }

  /**
   * Return a new scope that uses the provided device specification for an op.
   *
   * <p>Operations created within this scope will place the created operations on the device(s)
   * matching the provided spec.
   *
   * @param deviceSpec device specification for an operator in the returned scope
   * @return a new Scope that uses opName for operations.
   */
  public Scope withDevice(DeviceSpec deviceSpec) {
    return new Scope(env, nameScope, controlDependencies, deviceSpec,
        isInit);
  }

  //TODO stop gradient recording in init scopes (once we have gradient recording)

  /**
   * Get an extension of this scope that generated initialization ops.
   */
  public Scope initScope(){
    return new Scope(env.initEnv(), nameScope, new ArrayList<>(), deviceSpec, true);
  }

  /**
   * Create a unique name for an operator, using a provided default if necessary.
   *
   * <p>This is normally called only by operator building classes.
   *
   * <p>This method generates a unique name, appropriate for the name scope controlled by this
   * instance. Typical operator building code might look like
   *
   * <pre>{@code
   * scope.env().opBuilder("Const", scope.makeOpName("Const"))...
   * }</pre>
   *
   * <p><b>Note:</b> if you provide a composite operator building class (i.e, a class that creates a
   * set of related operations by calling other operator building code), the provided name will act
   * as a subscope to all underlying operators.
   *
   * @param defaultName name for the underlying operator.
   * @return unique name for the operator.
   * @throws IllegalArgumentException if the default name is invalid.
   */
  public String makeOpName(String defaultName) {
    return nameScope.makeOpName(defaultName);
  }

  /**
   * Returns a builder to create a new {@link Operation}.
   *
   * Note that {@code name} is automatically made unique.
   *
   * @param type of the Operation (i.e., identifies the computation to be performed)
   * @param name to refer to the created Operation in this environment scope.  Is uniquified.
   * @return an {@link OperationBuilder} to create an Operation when {@link
   *     OperationBuilder#build()} is invoked. If {@link OperationBuilder#build()} is not invoked,
   *     then some resources may leak.
   */
  public OperationBuilder opBuilder(String type, String name){
    return env.opBuilder(type, makeOpName(name), this);
  }

  public static boolean isValidOpName(String name) {
    return NameScope.isValidName(name);
  }

  private Scope(
      ExecutionEnvironment env,
      NameScope nameScope,
      Iterable<Op> controlDependencies,
      DeviceSpec deviceSpec,
      boolean isInit) {
    this.env = env;
    this.nameScope = nameScope;
    this.controlDependencies = controlDependencies;
    this.deviceSpec = deviceSpec;
    this.isInit = isInit;
  }

  /**
   * Returns a new scope where added operations will have the provided control dependencies.
   *
   * <p>Ops created with this scope will have a control edge from each of the provided controls. All
   * other properties are inherited from the current scope.
   *
   * <p>Init ops will never be used as control dependencies, they are assumed to be created
   * during session initialization.
   *
   * @param controls control dependencies for ops created with the returned scope
   * @return a new scope with the provided control dependencies
   */
  public Scope withControlDependencies(Iterable<Op> controls) {
    for (Op control : controls) {
      env.checkInput(control);
    }

    List<Op> nonInitControls = new ArrayList<>();
    for(Op op : controls){
      if(!env.isInitOp(op.op())){
        nonInitControls.add(op);
      }
    }

    return new Scope(env, nameScope, nonInitControls, deviceSpec, isInit);
  }

  /**
   * Applies device specification and adds each Operand in controlDependencies as a control input to
   * the provided builder.
   * <p>
   *
   * <b>Should only be used from {@link OperationBuilder} implementations</b>
   *
   * @param builder OperationBuilder to add control inputs and device specification to
   */
  public OperationBuilder apply(OperationBuilder builder) {
    builder.setDevice(deviceSpec.toString());
    for (Op control : controlDependencies) {
      builder.addControlInput(control.op());
    }
    return builder;
  }

  /**
   * Handle op creation, like registering it as an init op if the scope is init.
   * <p><b>FOR INTERNAL USE ONLY</b>
   */
  public void onOpCreated(Operation op){
    if(isInit){
      env.registerInitOp(op);
    }
  }

  private final ExecutionEnvironment env;
  private final Iterable<Op> controlDependencies;
  private final NameScope nameScope;
  private final DeviceSpec deviceSpec;
  private final boolean isInit;

  /** Returns device string from the scope. */
  public String getDeviceString() {
    return deviceSpec.toString();
  }

  /**
   * Get whether this scope is building init ops.
   */
  public boolean isInit() {
    return isInit;
  }
}
