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
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Operands;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.AssignAddVariableOp;
import org.tensorflow.op.core.AssignSubVariableOp;
import org.tensorflow.op.core.AssignVariableOp;
import org.tensorflow.op.core.Init;
import org.tensorflow.op.core.IsVariableInitialized;
import org.tensorflow.op.core.ReadVariableOp;
import org.tensorflow.op.core.VarHandleOp;
import org.tensorflow.op.core.VarHandleOp.Options;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * The implementation of {@link Variable}, with mutation methods.
 *
 * @see Variable
 */
public class MutableVariable<T extends TType> implements Variable<T> {

  private final Scope initialScope;
  private final String name;

  private final Shape shape;
  private final DataType dataType;
  private final Class<T> tType;
  private final VarHandleOp handle;

  private IsVariableInitialized isInitializedOp = null;
  private Op initializationOp = null;
  private ReadVariableOp<T> cachedRead = null;
  private Op lastAssign = null;

  private boolean hasInitialized = false;

  protected MutableVariable(Scope scope, Shape shape, Class<T> dataType) {
    this.shape = shape;
    this.dataType = Operands.toDataType(dataType);
    this.tType = dataType;

    this.name = scope.makeOpName("Variable");

    scope = scope.withName(null);
    this.initialScope = scope.withSubScope(this.name);

    VarHandleOp.Options[] options;

    if (scope.env().isGraph()) {
      options = new Options[]{VarHandleOp.sharedName(this.name)};
    } else {
      options = new Options[0];
    }

    this.handle = VarHandleOp.create(initialScope.withName(name), dataType, shape, options);

    scope.env().registerVariable(this);
  }

  @Override
  public Shape getShape() {
    return shape;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }

  @Override
  public boolean isInitialized() {
    return hasInitialized;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Operand<T> value(Scope scope) {
    if (!hasInitialized) {
      throw new IllegalStateException("Variable has not been initialized, can not get.");
    }
    if (cachedRead == null) {
      if (lastAssign != null) {
        scope = scope.withControlDependencies(Collections.singletonList(lastAssign));
      }
      cachedRead = ReadVariableOp.create(scope, handle, tType);
    }
    return cachedRead;
  }

  @Override
  public Operand<T> value() {
    return value(initialScope);
  }

  private void checkInput(Operand<T> value) {
    if (!value.shape().isCompatibleWith(this.shape)) {
      throw new IllegalArgumentException("Shape of new value (" + value.shape() +
          ") is not compatible with the variable's shape (" + this.shape + ").");
    }
    //TODO better checking w/ new types after refactor
    if (value.asOutput().dataType() != dataType) {
      throw new IllegalArgumentException("Data type of new value (" + value.asOutput().dataType() +
          ") is not compatible with the variable's data type (" + dataType + ").");
    }
  }

  @Override
  public Op initialize(Operand<T> value) {
    if (hasInitialized) {
      return initializationOp;
    }
    checkInput(value);
    initializationOp = AssignVariableOp.create(initialScope, handle, value);
    lastAssign = initializationOp;
    Init.add(initialScope, lastAssign);
    hasInitialized = true;
    cachedRead = null;
    return initializationOp;
  }

  @Override
  public Op initialize(Supplier<Operand<T>> value) {
    if (hasInitialized) {
      return initializationOp;
    }
    return initialize(value.get());
  }

  @Override
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
  public Op assign(Scope scope, Operand<T> value) {
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
  public Op assignSub(Scope scope, Operand<T> value) {
    if (!hasInitialized) {
      throw new IllegalStateException("Variable has not been initialized, can not decrement.");
    }
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
  public Op assignAdd(Scope scope, Operand<T> value) {
    if (!hasInitialized) {
      throw new IllegalStateException("Variable has not been initialized, can not increment.");
    }
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
}
