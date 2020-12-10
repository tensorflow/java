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
import org.tensorflow.DataType;
import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

@Operator
public abstract class Variable<T extends TType> implements Operand<T> {
  private final Scope initialScope;
  private final String name;

  protected final Shape shape;
  protected final DataType<T> dataType;

  protected boolean hasInitialized = false;

  protected Variable(Scope scope, Shape shape, DataType<T> dataType){
    this.initialScope = scope.withName(null);

    this.shape = shape;
    this.dataType = dataType;
    this.name = scope.makeOpName("Variable");
    scope.env().registerVariable(this);
  }

  public Shape getShape() {
    return shape;
  }

  public DataType<T> getDataType() {
    return dataType;
  }

  public boolean isInitialized() {
    return hasInitialized;
  }

  public String getName() {
    return name;
  }

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

  public Operand<T> initialize(Operand<T> value){
    if(hasInitialized){
      throw new IllegalStateException("Variable has already been initialized, can't initialize again.");
    }
    checkInput(value);
    doInitialize(initialScope, value);
    hasInitialized = true;
    return value();
  }

  public Operand<T> assign(Operand<T> value, Op... controlDependencies){
    checkInput(value);
    doAssign(initialScope.withControlDependencies(Arrays.asList(controlDependencies)), value);
    hasInitialized = true;
    return value();
  }

  protected abstract Operand<T> getValue();
  protected abstract void doInitialize(Scope scope, Operand<T> value);
  protected abstract void doAssign(Scope scope, Operand<T> value);

  @Override
  public Output<T> asOutput() {
    return value().asOutput();
  }

  @Override
  public Operation op() {
    return value().op();
  }

  @Override
  public Shape shape() {
    if(isInitialized()) {
      return value().shape();
    } else {
      return getShape();
    }
  }

  @Endpoint(name = "Variable")
  public static <T extends TType> Variable<T> create(Scope scope, Shape shape, DataType<T> dataType){
    if(scope.env().isEager()) {
      return new EagerVariable<>(scope, shape, dataType);
    } else {
      return new GraphVariable<>(scope, shape, dataType);
    }
  }

  @Endpoint(name = "Variable")
  public static <T extends TType> Variable<T> create(Scope scope, Operand<T> initialValue){
    Variable<T> variable = create(scope, initialValue.shape(), initialValue.asOutput().dataType());
    variable.initialize(variable);
    return variable;
  }
}
