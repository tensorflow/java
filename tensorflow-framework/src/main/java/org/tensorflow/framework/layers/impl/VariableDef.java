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
package org.tensorflow.framework.layers.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Glorot;
import org.tensorflow.framework.initializers.Initializer;
import org.tensorflow.framework.initializers.VarianceScaling;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.regularizers.Regularizer;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.AssignAdd;
import org.tensorflow.op.core.AssignSub;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

import java.util.function.UnaryOperator;

public class VariableDef<T extends TNumber> {
  private final Ops tf;
  private final String name;
  private final Shape shape;
  private final Initializer<T> initializer;
  private final UnaryOperator<Operand<T>> constraint;
  private final Regularizer regularizer;
  private final boolean trainable;
  private final Variable<T> variable;
  private final Operand<T> initOperand;
  private final Class<T> type;

  public VariableDef(
      Ops tf,
      String name,
      Shape shape,
      Initializer<T> initializer,
      UnaryOperator<Operand<T>> constraint,
      Regularizer regularizer,
      boolean trainable,
      long seed,
      Class<T> type) {
    this.tf = tf.withName(name);
    this.type = type;
    this.name = name;
    this.constraint = constraint;
    this.regularizer = regularizer;
    this.trainable = trainable;

    this.shape = shape == null ? Shape.scalar() : shape;
    this.initializer = initializer == null ? getDefaultInitializer(seed) : initializer;
    initOperand = this.initializer.call(tf.constant(this.shape), type);
    variable = tf.withSubScope(name).variable(initOperand);
  }

  public VariableDef(
      Ops tf,
      String name,
      Variable<T> variable,
      Initializer<T> initializer,
      UnaryOperator<Operand<T>> constraint,
      Regularizer regularizer,
      boolean trainable,
      long seed) {
    this.tf = tf.withName(name);
    this.name = name == null ? variable.toString() : name;
    this.constraint = constraint;
    this.regularizer = regularizer;
    this.trainable = trainable;
    this.variable = variable;
    shape = variable.shape();
    type = variable.type();
    this.initializer = initializer == null ? getDefaultInitializer(seed) : initializer;
    initOperand = this.initializer.call(tf.constant(this.shape), type);
  }

  /**
   * Initializes the variable
   *
   * @return the operand that initializes this variable
   */
  public Operand<T> init() {
    return assign(initOperand);
  }

  /**
   * Assigns a value to the variable, with locking set to false
   *
   * @param value the value to assign
   * @return the operand that assigns the value to this variable
   */
  public Operand<T> assign(Operand<T> value) {
    return assign(value, false);
  }
  /**
   * Assigns a value to the variable
   *
   * @param value the value to assign
   * @param useLocking If true, use locking during the assignment.
   * @return the operand that assigns the value to this variable
   */
  public Operand<T> assign(Operand<T> value, boolean useLocking) {
    return tf.assign(variable, value, Assign.useLocking(useLocking));
  }

  /**
   * Adds a value to the variable, without locking.
   *
   * @param value the value to add
   * @return the operand that adds the value to this variable
   */
  public Operand<T> assignAdd(Operand<T> value) {
    return assignAdd(value, false);
  }

  /**
   * Adds a value to the variable
   *
   * @param value the value to add
   * @param useLocking If true, use locking during the assignment.
   * @return the operand that adds the value to this variable
   */
  public Operand<T> assignAdd(Operand<T> value, boolean useLocking) {
    return tf.assignAdd(variable, value, AssignAdd.useLocking(useLocking));
  }

  /**
   * Subtracts a value from the variable, without locking.
   *
   * @param value the value to subtract
   * @return the operand that subtracts the value from this variable
   */
  public Operand<T> assignSub(Operand<T> value) {
    return assignSub(value, false);
  }

  /**
   * Subtracts a value from the variable
   *
   * @param value the value to subtract
   * @param useLocking If true, use locking during the assignment.
   * @return the operand that subtracts the value from this variable
   */
  public Operand<T> assignSub(Operand<T> value, boolean useLocking) {
    return tf.assignSub(variable, value, AssignSub.useLocking(useLocking));
  }

  /**
   * Gets the default initializer based on type
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and type.
   * @return the default initializer
   */
  @SuppressWarnings("unchecked")
  private Initializer<T> getDefaultInitializer(long seed) {
    Initializer<T> initializer;

    if (TFloating.class.isAssignableFrom(type)) {
      // this creates a "Casting 'new Glorot<>(...)' to 'Initializer<T>' is redundant" warning.
      // Ignored here as Glorot takes a TFloating which is a subclass of <T extends TNumber>
      // and is checked in the if statement above. If you remove this cast, you'll get an error.

      //noinspection RedundantCast
      initializer = (Initializer<T>) new Glorot<>(tf, VarianceScaling.Distribution.UNIFORM, seed);
    } else {
      initializer = new Zeros<>(tf);
    }
    return initializer;
  }

  /**
   * Gets the variable name
   *
   * @return the variable name
   */
  public String getName() {
    return name;
  }
  /**
   * Gets the variable shape
   *
   * @return the variable shape
   */
  public Shape getShape() {
    return shape;
  }
  /**
   * Gets the variable initializer
   *
   * @return the variable initializer
   */
  public Initializer<T> getInitializer() {
    return initializer;
  }
  /**
   * Gets the variable constraint
   *
   * @return the variable constraint
   */
  public UnaryOperator<Operand<T>> getConstraint() {
    return constraint;
  }

  /**
   * Gets the variable constraint
   *
   * @return the variable constraint
   */
  public Regularizer getRegularizer() {
    return regularizer;
  }
  /**
   * Gets the variable trainable indicator
   *
   * @return the variable trainable indicator
   */
  public boolean isTrainable() {
    return trainable;
  }
  /**
   * Gets the variable
   *
   * @return the variable
   */
  public Variable<T> getVariable() {
    return variable;
  }

  /**
   * Gets the variable initialization operand.
   *
   * @return the variable initialization operand.
   */
  public Operand<T> getInitOperand() {
    return initOperand;
  }

  /**
   * Gets the variable data type
   *
   * @return the variable data tupe
   */
  public Class<T> getType() {
    return type;
  }
}
