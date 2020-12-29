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
import java.util.function.Supplier;
import org.tensorflow.DataType;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * The implementation of {@link Variable}, with mutation methods.
 *
 * @see Variable
 */
public abstract class MutableVariable<T extends TType> implements Variable<T> {
  private final Scope initialScope;
  private final String name;

  protected final Shape shape;
  protected final DataType<T> dataType;

  protected boolean hasInitialized = false;

  //TODO use the new resource API.

  protected MutableVariable(Scope scope, Shape shape, DataType<T> dataType){
    this.initialScope = scope.withName(null);

    this.shape = shape;
    this.dataType = dataType;
    this.name = scope.makeOpName("Variable");
    scope.env().registerVariable(this);
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
  public DataType<T> getDataType() {
    return dataType;
  }

  /**
   * Get whether the variable has had a value assigned to it.
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
   * Get the current value of this variable.
   */
  public Operand<T> value(){
    if(!hasInitialized){
      throw new IllegalStateException("Variable has not been initialized, can not get.");
    }
    return getValue();
  }

  private void checkInput(Operand<T> value){
    if(value.shape().isCompatibleWith(this.shape)){
      throw new IllegalArgumentException("Shape of new value (" + value.shape() +
          ") is not compatible with the variable's shape (" + this.shape + ").");
    }
    //TODO better checking w/ new types after refactor
    if(value.asOutput().dataType() != dataType){
      throw new IllegalArgumentException("Data type of new value (" + value.asOutput().dataType() +
          ") is not compatible with the variable's data type (" + dataType + ").");
    }
  }

  /**
   * Initialize this variable with a value, if it hasn't already been assigned a value.  No-op if it has.
   * @param value the value to initialize this variable with.
   * @return the new value (or current if it was already initialized).
   */
  public Operand<T> initialize(Operand<T> value){
    if(hasInitialized){
      return value();
    }
    checkInput(value);
    doInitialize(initialScope, value);
    hasInitialized = true;
    return value();
  }


  /**
   * Initialize this variable with a value, if it hasn't already been assigned a value.  No-op if it has.
   * <p>
   * The provided function will not be invoked if this function no-ops.
   * @param value a function returning the value to initialize this variable with.
   * Will only be called if initialization is done.
   * @return the new value (or current if it was already initialized).
   */
  public Operand<T> initialize(Supplier<Operand<T>> value){
    if(hasInitialized){
      return value();
    }
    return initialize(value.get());
  }

  /**
   * Assign a new value to this variable.
   * @param value the value to assign.
   * @param controlDependencies any control dependencies of the assignment.
   * @return the new value
   */
  public Operand<T> assign(Operand<T> value, Op... controlDependencies){
    checkInput(value);
    doAssign(initialScope.withControlDependencies(Arrays.asList(controlDependencies)), value);
    hasInitialized = true;
    return value();
  }

  protected abstract Operand<T> getValue();
  protected abstract void doInitialize(Scope scope, Operand<T> value);
  protected abstract void doAssign(Scope scope, Operand<T> value);
}
