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
package org.tensorflow.framework.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.NoOp;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TType;

import java.util.*;
import java.util.stream.Collectors;

/** Base class for gradient optimizers. */
public abstract class Optimizer {

  public static final String VARIABLE_V2 = "VariableV2";
  /** Global state variables */
  // TODO make this be used.
  protected final List<Variable<?>> globals;
  /** The Graph this optimizer is operating on. */
  protected final Graph graph;
  /** The ops builder for the graph. */
  protected final Ops tf;
  /** Top level map key is the variable name, lower level map key is the slot name. */
  private final Map<String, Map<String, Variable<?>>> slots;

  /**
   * Builds an optimizer for the supplied graph.
   *
   * <p>Uses the name from {@link Optimizer#getOptimizerName()} to name the operations.
   *
   * @param graph The graph to optimize.
   */
  protected Optimizer(Graph graph) {
    this.graph = graph;
    this.tf = Ops.create(graph).withName(getOptimizerName());
    this.slots = new HashMap<>();
    this.globals = new ArrayList<>();
  }

  /**
   * Builds an optimizer for the supplied graph.
   *
   * @param graph The graph to optimize.
   * @param name The base name for the operations.
   */
  protected Optimizer(Graph graph, String name) {
    this.graph = graph;
    this.tf = Ops.create(graph).withName(name);
    this.slots = new HashMap<>();
    this.globals = new ArrayList<>();
  }

  /**
   * Gets the Optimizer's Ops instance
   * @return the Optimizer's Ops instance
   */
  public final Ops getTF() {
    return tf;
  }

  /**
   * Creates a name by combining a variable name and a slot name
   *
   * @param variable the variable
   * @param slotName the name of the slot
   * @return the combined name
   */
  public static String createName(Output<? extends TType> variable, String slotName) {
    return variable.op().name() + "-" + slotName;
  }

  /**
   * Minimizes the loss by updating the variables
   *
   * @param loss the loss operation that returns the value to minimize
   * @return returns op that minimizes the loss by updating the listed variables
   */
  public Op minimize(Operand<?> loss) {
    return minimize(loss, getOptimizerName() + "-minimize");
  }

  /**
   * Minimizes the loss by updating the variables
   *
   * @param loss the loss operation that returns the value to minimize
   * @param name the name for the minimize operation
   * @return op that minimizes the loss by updating the listed variables
   */
  public Op minimize(Operand<?> loss, String name) {
    List<GradAndVar<?>> gradsAndVars = computeGradients(loss);

    return applyGradients(gradsAndVars, name);
  }

  /**
   * Computes the gradients based on a loss operand.
   *
   * @param loss the loss operation
   * @param <T> the data type of the loss, gradients and variables.
   * @return the computed gradients
   */
  public <T extends TType> List<GradAndVar<?>> computeGradients(Operand<?> loss) {
    List<Operation> variables = new ArrayList<>();
    graph
        .operations()
        .forEachRemaining(
            (Operation op) -> {
              if (op.type().equals(VARIABLE_V2)) {
                variables.add(op);
              }
            });

    Output<?>[] variableOutputArray = new Output[variables.size()];
    for (int i = 0; i < variables.size(); i++) {
      // First output of a variable is it's output.
      variableOutputArray[i] = variables.get(i).output(0);
    }

    Output<?>[] gradients = graph.addGradients(loss.asOutput(), variableOutputArray);
    List<GradAndVar<? extends TType>> gradVarPairs = new ArrayList<>();

    for (int i = 0; i < variableOutputArray.length; i++) {
      @SuppressWarnings("unchecked")
      Output<T> typedGrad = (Output<T>) gradients[i];
      @SuppressWarnings("unchecked")
      Output<T> typedVar = (Output<T>) variableOutputArray[i];
      gradVarPairs.add(new GradAndVar<>(typedGrad, typedVar));
    }

    return gradVarPairs;
  }

  /**
   * Applies gradients to variables
   *
   * @param gradsAndVars the list of (gradient, variable) pairs.
   * @param name the name of the apply gradients operation
   * @return an Op that applies the gradients to the variables.
   */
  public Op applyGradients(List<GradAndVar<? extends TType>> gradsAndVars, String name) {
    List<Output<? extends TType>> variables =
        gradsAndVars.stream().map(GradAndVar::getVariable).collect(Collectors.toList());

    createSlots(variables);

    Optional<Op> prepOp = prepare(name + "/prepare");

    List<Op> updateOps = new ArrayList<>();
    prepOp.ifPresent(updateOps::add);
    for (GradAndVar<? extends TType> pair : gradsAndVars) {
      updateOps.add(applyDense(pair));
    }

    return finish(updateOps, name);
  }

  /**
   * Gets the slot associated with the specified variable and slot name.
   *
   * @param var The variable to lookup.
   * @param slotName The slot name.
   * @param <T> a tensor type
   * @return The slot or {@link Optional#empty}.
   */
  public <T extends TType> Optional<Variable<T>> getSlot(Output<T> var, String slotName) {
    return getSlot(var.op().name(), slotName);
  }

  /**
   * Gets the slot associated with the specified variable and slot name.
   *
   * @param varName The variable to lookup.
   * @param slotName The slot name.
   * @return The slot or {@link Optional#empty}.
   */
  private <T extends TType> Optional<Variable<T>> getSlot(String varName, String slotName) {
    Map<String, Variable<? extends TType>> variables = slots.get(slotName);
    if (variables != null) {
      Variable<? extends TType> slot = variables.get(varName);
      if (slot != null) {
        @SuppressWarnings("unchecked") // This method should only be called when the type is known.
        Optional<Variable<T>> opt = Optional.of((Variable<T>) slot);
        return opt;
      }
      return Optional.empty();
    }
    return Optional.empty();
  }

  /**
   * Creates a slot in the graph for the specified variable with the specified name. Adds the slot's
   * initializer to the graph's initializers, and the slot to the Optimizer's slot map.
   *
   * @param variable The variable to create the slot for.
   * @param slotName The name of the slot.
   * @param initializer The initializer for the slot.
   * @param <T> The type of the variable.
   */
  protected <T extends TType> void createSlot(
      Output<T> variable, String slotName, Operand<T> initializer) {
    Variable<T> slot =
        tf.withName(createName(variable, slotName)).variable(variable.shape(), variable.type());
    Assign<T> slotInit = tf.assign(slot, initializer);
    graph.addInitializer(slotInit);
    String varName = variable.op().name();
    Map<String, Variable<? extends TType>> variables =
        slots.computeIfAbsent(slotName, (k) -> new HashMap<>());
    variables.put(varName, slot);
  }

  /**
   * Returns a No-op prepare.
   *
   * @param scopeName The scope name to use for any variable creations.
   * @return a No-op to prepare this optimizer, or empty if none.
   */
  protected Optional<Op> prepare(String scopeName) {
    return Optional.empty();
  }

  /**
   * Performs a No-op slot creation method.
   *
   * @param variables The variables to create slots for.
   */
  protected void createSlots(List<Output<? extends TType>> variables) {}

  /**
   * Generates the gradient update operations for the specific variable and gradient.
   *
   * @param gradVarPair the list of (gradient, variable) pairs.
   * @param <T> the datatype of the gradients and variables.
   * @return An operand which applies the desired optimizer update to the variable.
   */
  private <T extends TType> Op applyDense(GradAndVar<T> gradVarPair) {
    return applyDense(gradVarPair.getGradient(), gradVarPair.getVariable());
  }

  /**
   * Generates the gradient update operations for the specific variable and gradient.
   *
   * @param gradient The gradient to use.
   * @param variable The variable to update.
   * @param <T> The type of the variable.
   * @return An operand which applies the desired optimizer update to the variable.
   */
  protected abstract <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable);

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   *
   * @param updateOperations The update operations.
   * @param name The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  protected Op finish(List<Op> updateOperations, String name) {
    Scope scope = new Scope(graph);
    scope = scope.withName(name);
    scope = scope.withControlDependencies(updateOperations);
    return NoOp.create(scope);
  }

  /**
   * Get the Name of the optimizer.
   *
   * @return The optimizer name.
   */
  public abstract String getOptimizerName();

  /** Optional attributes for {@link org.tensorflow.framework.optimizers.Optimizer} */
  public static class Options {

    protected String sharedName;

    private Options() {}

    /**
     * Sets the shared name
     *
     * @param sharedName If non-empty, this variable is named in the given bucket with this
     *     shared_name. Otherwise, the node name is used instead.
     */
    public Optimizer.Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  /**
   * A class that holds a paired gradient and variable.
   *
   * @param <T> the data type for the gradient and variable
   */
  public static class GradAndVar<T extends TType> {

    private final Output<T> gradient;
    private final Output<T> variable;

    /**
     * Creates a Gradient and Variable pair
     *
     * @param gradient the gradient
     * @param variable the variable
     */
    public GradAndVar(Output<T> gradient, Output<T> variable) {
      this.gradient = gradient;
      this.variable = variable;
    }

    /**
     * Gets the gradient
     *
     * @return the gradient
     */
    public Output<T> getGradient() {
      return gradient;
    }

    /**
     * Gets the variable
     *
     * @return the variable
     */
    public Output<T> getVariable() {
      return variable;
    }
  }
}
