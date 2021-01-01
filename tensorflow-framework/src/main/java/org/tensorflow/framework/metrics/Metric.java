/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.metrics;

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

import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Initializer;
import org.tensorflow.framework.metrics.impl.MetricVariable;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Base class for Metrics
 *
 * @param <U> The data type for the metric values
 * @param <T> The data type for the metric result
 */
public abstract class Metric<U extends TNumber, T extends TNumber> {

  /** variables are stored by ExecutionEnvironment, and then by an identifier name */
  protected static Map<ExecutionEnvironment, Map<String, MetricVariable<? extends TNumber>>>
      variableMap = new WeakHashMap<>();
  /** The TensorFlow Ops */
  private final Ops tf;
  /** The random number generator seed value */
  private final long seed;

  // TODO: how to handle variables across new ExecutionEnvironments.
  //  Metrics may be instantiated multiple times using the same variables,
  //  These variables become stale when a new ExecutionEnvironment is created
  //  (most commonly seen in Unit Tests), so the question is how to best handle this.
  //  Option 1, which is used here is to map the variables against an instance of
  //    an ExecutionEnvironment in a WeakHashMap, when a new ExecutionEnvironment is presented, the
  // new
  //    variables are mapped to it. A WeakHashMap is used to throw away the old ExecutionEnvironment
  //    mappings, when the old ExecutionEnvironment is finalized.
  //  Option 2, keep an instance of the newly presented ExecutionEnvironment and if it changes,
  //    clear the variable maps.
  //  My guess is that in a non-unit test environment, only one ExecutionEnvironment will be used,
  //  I welcome thoughts on this.
  /** The name for this metric. Defaults to {@link Class#getSimpleName()}. */
  private final String name;

  private final Class<T> type;

  /**
   * Creates a Metric with a name of {@link Class#getSimpleName()} }
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected Metric(Ops tf, long seed, Class<T> type) {
    this(tf, null, seed, type);
  }

  /**
   * Creates a Metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected Metric(Ops tf, String name, long seed, Class<T> type) {
    if (!tf.scope().env().isGraph())
      throw new IllegalArgumentException("Metrics are required to execute in Graph mode.");
    this.seed = seed;
    this.name = name != null ? name : this.getClass().getSimpleName();
    this.tf = tf.withSubScope(this.name);
    this.type = type;
  }

  /**
   * Creates a List of Operations to update the metric state based on input values.
   *
   * <p>This is an empty implementation that should be overridden in a subclass, if needed.
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return a List of Operations to update the metric state
   */
  @SuppressWarnings({"unchecked", "unused"})
  public <V extends TNumber> List<Op> updateStateList(Operand<V> values, Operand<T> sampleWeights) {
    return Collections.EMPTY_LIST;
  }

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * <p>This is an empty implementation that should be overridden in a sub class, if needed.
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @param <V> the data type for the sample weights
   * @return a List of Operations to update the metric state
   */
  @SuppressWarnings({"unchecked","unused"})
  public <V extends TNumber> List<Op> updateStateList(
      Operand<V> labels, Operand<U> predictions, Operand<T> sampleWeights) {
    return Collections.EMPTY_LIST;
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the Operation to update the metric state
   */
  public final <V extends TNumber> Op updateState(Operand<V> values, Operand<T> sampleWeights) {
    List<Op> controlOps = updateStateList(values, sampleWeights);
    return tf.withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the Operation to update the metric state
   */
  public final <V extends TNumber> Op updateState(
      Operand<V> labels, Operand<U> predictions, Operand<T> sampleWeights) {
    List<Op> controlOps = updateStateList(labels, predictions, sampleWeights);
    return tf.withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Gets the current result of the metric
   *
   * @param tf the TensorFlow Ops used to create the result
   * @return the result, possibly with control dependencies
   */
  public abstract Operand<T> result(Ops tf);

  /**
   * Gets the current result of the metric using the metric's {@link #getTF()}
   *
   * @return the result, possibly with control dependencies
   */
  public Operand<T> result() {
    return result(this.tf);
  }

  /**
   * Calls update state once, followed by a call to get the result
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the result, possibly with control dependencies
   */
  public final  Operand<T> callOnce(
      Operand<U> values, Operand<T> sampleWeights) {
    List<Op> controlOps = updateStateList(values, sampleWeights);
    Ops ltf = tf.withSubScope("callOnce").withControlDependencies(controlOps);
    return result(ltf);
  }

  /**
   * Adds a variable to collect metric values
   *
   * @param variable the variable
   * @param initializer the initializer for the variable, if null, then the default for floating
   *     point types is {@link org.tensorflow.framework.initializers.Glorot} with distribution
   *     {@link org.tensorflow.framework.initializers.VarianceScaling.Distribution#UNIFORM}, for
   *     other types the default initializer is {@link org.tensorflow.framework.initializers.Zeros}
   */
  protected <V extends TNumber> void addVariable(
      String varName, Variable<V> variable, Initializer<V> initializer) {
    // TODO option 2 would be to keep track of tf.scope().env() and if it changes, clear to old Map.
    Map<String, MetricVariable<? extends TNumber>> variables =
        variableMap.computeIfAbsent(tf.scope().env(), k -> new HashMap<>());
    variables.put(varName, new MetricVariable<>(tf, variable, initializer, seed));
  }

  /**
   * Gets the list of added variables
   *
   * @return the list of added variables
   */
  public List<Variable<? extends TNumber>> getVariables() {
    List<Variable<? extends TNumber>> result = new ArrayList<>();
    Map<String, MetricVariable<? extends TNumber>> variables = variableMap.get(tf.scope().env());
    if (variables != null) variables.values().forEach(mv -> result.add(mv.getVariable()));
    return result;
  }

  /**
   * Gets a formatted name for a variable, in the form {@link #name} + "_" + varName.
   *
   * @param varName the base name for the variable
   * @return the formatted variable name
   */
  protected String getVariableName(String varName) {
    return String.format("%s_%s", this.name, varName);
  }

  /**
   * Gets an Operation that initializes the variables.
   *
   * @param subScopeName the sub scope name
   * @return the Operation used to initialize the variables.
   */
  public Op initialize(String subScopeName) {

    List<Op> initializeOperations = initializeVarsList(subScopeName);
    return tf.withControlDependencies(initializeOperations).noOp();
  }

  /**
   * Gets the list of Operations that initializes the variables
   *
   * @param subScopeName the sub scope name
   * @return the list of Operations that initializes the variables
   */
  @SuppressWarnings("unchecked")
  private List<Op> initializeVarsList(String subScopeName) {
    Map<String, MetricVariable<? extends TNumber>> variables = variableMap.get(tf.scope().env());
    if (variables != null)
      return variables.values().stream()
          .map(metricVariable -> variableAssign(subScopeName, metricVariable))
          .collect(Collectors.toList());
    else return Collections.EMPTY_LIST;
  }

  /**
   * Resets all variables to their initial state
   *
   * @return An Operation that sets all variables to their initial state
   */
  public Op resetStates() {
    return initialize("resetStates");
  }

  /**
   * Assigns a value to a Variable
   *
   * <p>This assumes the variable has already been initialized
   *
   * @param subScopeName the subscope for creating the variable
   * @param mv the metric value used to assign the initializer to the variable.
   * @return the variable add operation with necessary control dependencies
   */
  private <V extends TNumber> Operand<? extends TNumber> variableAssign(
      String subScopeName, MetricVariable<V> mv) {
    return tf.withSubScope(subScopeName).assign(mv.getVariable(), mv.initialize());
  }

  /**
   * Gets a stored variable by name, Variables are cached first by the TensorFlow Environment, then
   * by a variable name.
   *
   * @param varName the name assigned to the variable
   * @return the variable, or null if the variable is not found.
   */
  public Variable<? extends TNumber> getVariable(String varName) {
    Map<String, MetricVariable<? extends TNumber>> variables = variableMap.get(tf.scope().env());
    if (variables == null) return null;
    MetricVariable<? extends TNumber> mv = variables.get(varName);
    return mv != null ? mv.getVariable() : null;
  }

  /**
   * Gets the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  public Ops getTF() {
    return tf;
  }

  /**
   * Gets the name of this metric.
   *
   * @return the name of this metric
   */
  public String getName() {
    return name;
  }

  public Class<T> getType() {
    return type;
  }
}
