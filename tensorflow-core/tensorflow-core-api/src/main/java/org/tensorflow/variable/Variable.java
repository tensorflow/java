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

import java.util.function.Supplier;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.internal.types.registry.TensorTypeRegistry;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Operands;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
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
 * The exposed value will not usually be a {@link org.tensorflow.op.core.Variable}.
 * <p>
 * Variables will be registered in their execution environment's {@link ExecutionEnvironment#variables()}.
 *
 * @see MutableVariable
 */
@Operator
public interface Variable<T extends TType> extends Operand<T> {
  /**
   * Get the variable's constant shape.
   * This may have unknown dimensions, which do not impose a requirement on the value's dimensions.
   */
  Shape getShape();

  /**
   * Get the variable's constant data type.
   */
  DataType getDataType();

  /**
   * Get whether the variable has had a value assigned to it.  This method relates to the Java object, not the graph variable.
   * <p>
   * This operation returns true if {@code initialize} or {@code assign} methods have been used on
   * the variable object, it does not provide any information about the state of the graph variable.
   * For that, use {@link #isValueInitialized()}
   */
  boolean isInitialized();

  /**
   * Get whether the graph value is initialized.  In eager mode, this will be the same as {@link #isInitialized()}.
   */
  Operand<TBool> isValueInitialized();

  /**
   * Get the name of the variable, set using {@link org.tensorflow.op.Ops#withName(String)} the same as any other op.
   */
  String getName();

  /**
   * Get the current value of this variable, using the variable's scope.
   */
  Operand<T> value();

  /**
   * Get the current value of this variable, using the given scope.
   */
  Operand<T> value(Scope scope);

  /**
   * Initialize this variable with a value, if it hasn't already been assigned a value.  No-op if it has.
   * @param value the value to initialize this variable with.
   */
  Op initialize(Operand<T> value);

  /**
   * Initialize this variable with a value, if it hasn't already been assigned a value.  No-op if it has.
   * <p>
   * The provided function will not be invoked if this function no-ops.
   * @param value a function returning the value to initialize this variable with.
   * Will only be called if initialization is done.
   */
  Op initialize(Supplier<Operand<T>> value);

  /**
   * Get the current value as an Output.
   */
  @Override
  default Output<T> asOutput() {
    return value().asOutput();
  }

  /**
   * Get the op of the current value.
   */
  @Override
  default Operation op() {
    return value().op();
  }

  /**
   * Gets the current shape of this variable.  May have less unknown dimensions than {@link #getShape()},
   * in which case they will be filled in from the current value.
   */
  @Override
  default Shape shape() {
    if(isInitialized()) {
      return value().shape();
    } else {
      return getShape();
    }
  }

  /**
   * Get the underlying mutable variable.
   */
  default MutableVariable<T> asMutableVariable(){
    return (MutableVariable<T>) this;
  }

  /**
   * Create a new {@link Variable} object, representing a mutable tensor value with constant shape and data type, with
   * support for assignment and initialization that works in both eager and graph modes.
   * <p>
   * The name can be set using {@link org.tensorflow.op.Ops#withName(String)} just like any other op.
   * @param scope
   * @param shape the static shape of the variable.
   * @param dataType the data type of the variable.
   * @return a new {@link Variable} instance.
   * @see Variable
   */
  @Endpoint(name = "Variable")
  public static <T extends TType> Variable<T> create(Scope scope, Shape shape, Class<T> dataType){
    return new MutableVariable<>(scope, shape, dataType);
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
   * @return a new {@link Variable} instance.
   * @see Variable
   */
  @Endpoint(name = "Variable")
  public static <T extends TType> Variable<T> create(Scope scope, Operand<T> initialValue){
    Variable<T> variable = create(scope, initialValue.shape(), initialValue.type());
    variable.initialize(variable);
    return variable;
  }
}
