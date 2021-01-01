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
package org.tensorflow.framework.metrics.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Glorot;
import org.tensorflow.framework.initializers.Initializer;
import org.tensorflow.framework.initializers.VarianceScaling;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TIntegral;
import org.tensorflow.types.family.TNumber;

/**
 * Helper class that holds a metric variable
 *
 * @param <U> the data type of the variable
 */
public class MetricVariable<U extends TNumber> {
  private final Variable<U> variable;
  private final Initializer<U> initializer;
  private final Ops tf;
  private boolean initialized;

  /**
   * Creates a Metric Variable
   *
   * @param tf the TensorFlow Ops
   * @param variable the variable
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variable
   */
  public MetricVariable(Ops tf, Variable<U> variable, long seed, Class<U> type) {
    this(tf, variable, null, seed, type);
  }

  /**
   * Creates a Metric Variable
   *
   * @param tf the TensorFlow Ops
   * @param variable the variable
   * @param initializer the initializer for the variable, if null, then the default for floating
   *     point types is {@link org.tensorflow.framework.initializers.Glorot} with distribution
   *     {@link org.tensorflow.framework.initializers.VarianceScaling.Distribution#UNIFORM}, for
   *     other types the default initializer is {@link org.tensorflow.framework.initializers.Zeros}
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variable
   */
  @SuppressWarnings("unchecked")
  public MetricVariable(
      Ops tf, Variable<U> variable, Initializer<U> initializer, long seed, Class<U> type) {
    this.tf = tf;
    this.variable = variable;

    if (initializer == null) {
      if (TFloating.class.isAssignableFrom(type)) {
        //noinspection RedundantCast
        this.initializer =
            (Initializer<U>) new Glorot<>(tf, VarianceScaling.Distribution.UNIFORM, seed);
      } else if (TIntegral.class.isAssignableFrom(type)) {
        this.initializer = new Zeros<>(tf);
      } else {
        throw new IllegalArgumentException(
            String.format(
                "An initializer for variable %s of type %s is required",
                variable.toString(), type.getSimpleName()));
      }
    } else {
      this.initializer = initializer;
    }
  }

  /**
   * Initializers the variable based on the initializer
   *
   * @return the initialized variable
   */
  public Operand<U> initialize() {
    initialized = true;
    return initializer.call(tf.constant(variable.shape()), variable.type());
  }

  /**
   * Gets the variable
   *
   * @return the variable
   */
  public Variable<U> getVariable() {
    return variable;
  }

  /**
   * Gets the initializer
   *
   * @return the initializer
   */
  public Initializer<U> getInitializer() {
    return initializer;
  }

  /**
   * Gets the value of initialized
   *
   * @return the value of initialized
   */
  public boolean isInitialized() {
    return initialized;
  }
}
