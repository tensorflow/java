/*
  Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.variable;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Operands;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.AssignAddVariableOp;
import org.tensorflow.op.core.AssignSubVariableOp;
import org.tensorflow.op.core.AssignVariableOp;
import org.tensorflow.op.core.Init;
import org.tensorflow.op.core.IsVariableInitialized;
import org.tensorflow.op.core.ReadVariableOp;
import org.tensorflow.op.core.VarHandleOp;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * A class representing a read-only tensor variable with a constant shape and data type.  Analogous to Python's tf.Variable.
 * Any access will always return the most recent assignment.
 * <p>
 * Supports eager and graph mode, and will use {@code VariableV2} in graph mode and enforce ordered assignments.
 * <p>
 * Provides methods to get the value and initialize the value if it hasn't already been set.
 * Also implements {@code Operand<T>} using the stored value.
 * <p>
 * Variables will be registered in their execution environment's {@link ExecutionEnvironment#variables()}.
 *
 */
@Operator
public class Variable<T extends TType> implements Operand<T> {

  private final Scope initialScope;
  private final String name;

  private final Shape shape;
  private final DataType dataType;
  private final boolean trainable;
  private final Class<T> tType;

  private final VarHandleOp handle;

  private IsVariableInitialized isInitializedOp = null;
  private Op initializationOp = null;
  private ReadVariableOp<T> cachedRead = null;
  private Op lastAssign = null;

  private boolean hasInitialized = false;

  protected Variable(Scope scope, Shape shape, Class<T> dataType,
      Options[] options) {
    this.shape = shape;
    this.dataType = Operands.toDataType(dataType);
    this.tType = dataType;

    boolean trainable = true;
    if (options != null) {
      for (Options opts : options) {
        if (opts.trainable != null) {
          trainable = opts.trainable;
        }
      }
    }
    this.trainable = trainable;

    this.name = scope.makeOpName("Variable");

    scope = scope.withName(null);
    this.initialScope = scope.withSubScope(this.name);



    VarHandleOp.Options[] handleOptions;

    if (scope.env().isGraph()) {
      handleOptions = new VarHandleOp.Options[]{VarHandleOp.sharedName(this.name)};
    } else {
      handleOptions = new VarHandleOp.Options[0];
    }

    this.handle = VarHandleOp.create(initialScope.withName(name), dataType, shape, handleOptions);

    scope.env().registerVariable(this);
  }

  /**
     * Get whether the variable is trainable (whether it should be updated by optimizers).
     */
  public boolean isTrainable() {
    return trainable;
  }

  /**
     * Get the variable handle operation.
     */
  public VarHandleOp getHandle() {
    return handle;
  }

  /**
     * Get the variable's constant shape.
     * This may have unknown dimensions, which do not impose a requirement on the value's dimensions.
     */
  public Shape getShape() {
    return shape;
  }

  /**
     * Get the variable's constant data type.
     */
  public DataType getDataType() {
    return dataType;
  }

  /**
     * Get whether the variable has had a value assigned to it.  This method relates to the Java object, not the graph variable.
     * <p>
     * This operation returns true if {@code initialize} or {@code assign} methods have been used on
     * the variable object, it does not provide any information about the state of the graph variable.
     * For that, use {@link #isValueInitialized()}
     */
  public boolean isInitialized() {
    return hasInitialized;
  }

  /**
     * Get the name of the variable, set using {@link org.tensorflow.op.Ops#withName(String)} the same as any other op.
     */
  public String getName() {
    return name;
  }

  /**
   * Get the current value as an Output.
   */
  @Override
  public Output<T> asOutput() {
    return value().asOutput();
  }

  /**
   * Get the op of the current value.
   */
  @Override
  public Operation op() {
    return value().op();
  }

  /**
   * Gets the current shape of this variable.  May have less unknown dimensions than {@link #getShape()},
   * in which case they will be filled in from the current value.
   */
  @Override
  public Shape shape() {
    if(isInitialized()) {
      return value().shape();
    } else {
      return getShape();
    }
  }

  /**
     * Get the current value of this variable, using the given scope.
     */
  public synchronized Operand<T> value(Scope scope) {
    if (!hasInitialized) {
      throw new IllegalStateException("Variable has not been initialized, can not get.");
    }
    ReadVariableOp<T> ret = cachedRead;
    if (ret == null) {
      if (lastAssign != null) {
        scope = scope.withControlDependencies(Collections.singletonList(lastAssign));
      }
      ret = ReadVariableOp.create(scope, handle, tType);
    }
    cachedRead = ret;
    return ret;
  }

  /**
     * Get the current value of this variable, using the variable's scope.
     */
  public Operand<T> value() {
    return value(initialScope);
  }

  private void checkInput(Operand<T> value) {
    if (!value.shape().isCompatibleWith(this.shape)) {
      throw new IllegalArgumentException("Shape of new value (" + value.shape() +
          ") is not compatible with the variable's shape (" + this.shape + ").");
    }

    if (!tType.isAssignableFrom(value.asOutput().type())) {
      throw new IllegalArgumentException("Data type of new value (" + value.asOutput().dataType() +
          ") is not compatible with the variable's data type (" + dataType + ").");
    }
  }

  /**
     * Initialize this variable with a value, if it hasn't already been assigned a value.  No-op if it has.
     * @param value the value to initialize this variable with.
     */
  public synchronized Op initialize(Operand<T> value) {
    if (hasInitialized) {
      return initializationOp;
    }
    checkInput(value);
    initializationOp = AssignVariableOp.create(initialScope, handle, value);

    //TODO this if will be unnecessary after the init PR
    if(initialScope.env().isGraph())
      Init.add(initialScope, initializationOp);

    hasInitialized = true;
    cachedRead = null;
    return initializationOp;
  }

  /**
     * Initialize this variable with a value, if it hasn't already been assigned a value.  No-op if it has.
     * <p>
     * The provided function will not be invoked if this function no-ops.
     * @param value a function returning the value to initialize this variable with.
     * Will only be called if initialization is done.
     */
  public synchronized Op initialize(Supplier<Operand<T>> value) {
    if (hasInitialized) {
      return initializationOp;
    }
    return initialize(value.get());
  }

  /**
     * Get whether the graph value is initialized.  In eager mode, this will be the same as {@link #isInitialized()}.
     */
  public Operand<TBool> isValueInitialized() {
    if (isInitializedOp == null) {
      isInitializedOp = IsVariableInitialized.create(initialScope, handle);
    }

    return isInitializedOp;
  }

  /**
   * Assign a new value to this variable using the given scope.
   *
   * @param value the value to assign.
   * @see AssignVariableOp#create
   */
  public synchronized Op assign(Scope scope, Operand<T> value) {
    checkInput(value);
    lastAssign = AssignVariableOp.create(scope, handle, value);
    hasInitialized = true;
    cachedRead = null;
    return lastAssign;
  }

  /**
   * Assign a new value to this variable using the variable's scope.
   *
   * @param value the value to assign.
   * @param controlDependencies any control dependencies of the assignment.
   * @see AssignVariableOp#create
   */
  public Op assign(Operand<T> value, Op... controlDependencies) {
    return assign(initialScope.withControlDependencies(Arrays.asList(controlDependencies)), value);
  }

  /**
   * Decrement the variable's value by the given value, using the given scope.
   *
   * @param value amount to decrease the variable's value by.
   * @see AssignSubVariableOp#create
   */
  public synchronized Op assignSub(Scope scope, Operand<T> value) {
    if (!hasInitialized) {
      throw new IllegalStateException("Variable has not been initialized, can not decrement.");
    }

    if(cachedRead != null)
      scope = scope.withControlDependencies(Collections.singletonList(cachedRead));

    checkInput(value);
    lastAssign = AssignSubVariableOp.create(scope, handle, value);
    hasInitialized = true;
    cachedRead = null;
    return lastAssign;
  }

  /**
   * Decrement the variable's value by the given value, using the variable's scope.
   *
   * @param value amount to decrease the variable's value by.
   * @param controlDependencies any control dependencies of the assignment.
   * @see AssignSubVariableOp#create
   */
  public Op assignSub(Operand<T> value, Op... controlDependencies) {
    return assignSub(initialScope.withControlDependencies(Arrays.asList(controlDependencies)), value);
  }

  /**
   * Increment the variable's value by the given value, using the given scope.
   *
   * @param value amount to decrease the variable's value by.
   * @see AssignAddVariableOp#create
   */
  public synchronized Op assignAdd(Scope scope, Operand<T> value) {
    if (!hasInitialized) {
      throw new IllegalStateException("Variable has not been initialized, can not increment.");
    }

    if(cachedRead != null)
      scope = scope.withControlDependencies(Collections.singletonList(cachedRead));

    checkInput(value);
    lastAssign = AssignAddVariableOp.create(scope, handle, value);
    hasInitialized = true;
    cachedRead = null;
    return lastAssign;
  }

  /**
   * Increment the variable's value by the given value, using the variable's scope.
   *
   * @param value amount to decrease the variable's value by.
   * @param controlDependencies any control dependencies of the assignment.
   * @see AssignAddVariableOp#create
   */
  public Op assignAdd(Operand<T> value, Op... controlDependencies) {
    return assignAdd(initialScope.withControlDependencies(Arrays.asList(controlDependencies)), value);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Variable}
   */
  public static class Options {

    /**
     * @param trainable If non-null, this variable's trainability is as given.
     * Otherwise, it is trainable.
     */
    public Options trainable(boolean trainable) {
      this.trainable = trainable;
      return this;
    }

    Boolean trainable = null;

    private Options() {
    }
  }

  /**
   * Create a new {@link Variable} object, representing a mutable tensor value with constant shape and data type, with
   * support for assignment and initialization that works in both eager and graph modes.
   * <p>
   * The name can be set using {@link org.tensorflow.op.Ops#withName(String)} just like any other op.
   * @param scope
   * @param shape the static shape of the variable.
   * @param dataType the data type of the variable.
   * @param options carries optional attributes values
   * @return a new {@link Variable} instance.
   * @see Variable
   */
  @Endpoint(name = "Variable")
  public static <T extends TType> Variable<T> create(Scope scope, Shape shape, Class<T> dataType, Options... options){
    return new Variable<>(scope, shape, dataType, options);
  }

  /**
   * Create a new {@link Variable} object, representing a mutable tensor value with constant shape and data type, with
   * support for assignment and initialization that works in both eager and graph modes.
   * <p>
   * Initializes the variable with the provided value, and uses it to determin the variables shape and data type.
   * <p>
   * The name can be set using {@link org.tensorflow.op.Ops#withName(String)} just like any other op.
   * @param scope
   * @param initialValue the initial value of the variable.
   * @param options carries optional attributes values
   * @return a new {@link Variable} instance.
   * @see Variable
   */
  @Endpoint(name = "Variable")
  public static <T extends TType> Variable<T> create(Scope scope, Operand<T> initialValue, Options... options){
    Variable<T> variable = create(scope, initialValue.shape(), initialValue.type(), options);
    variable.initialize(initialValue);
    return variable;
  }
}
