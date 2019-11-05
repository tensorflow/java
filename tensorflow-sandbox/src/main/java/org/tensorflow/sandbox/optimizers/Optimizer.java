/*
 * Copyright © 2019, Oracle and/or its affiliates. All rights reserved.
 */
package org.tensorflow.sandbox.optimizers;

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
import org.tensorflow.types.TFloat;
import org.tensorflow.types.family.TType;
import org.tensorflow.sandbox.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public abstract class Optimizer {
  public static final String VARIABLE_V2 = "VariableV2";

  /**
   * Top level map is variable name, bottom map is slot name.
   */
  private final Map<String, Map<String, Variable<?>>> slots;

  /**
   * Global state variables
   */
  //TODO make this be used.
  protected final List<Variable> globals;

  /**
   * The Graph this optimizer is operating on.
   */
  protected final Graph graph;

  /**
   * The ops builder for the graph.
   */
  protected final Ops tf;

  protected Optimizer(Graph graph) {
    this.graph = graph;
    this.tf = Ops.create(graph).withName(getOptimizerName());
    this.slots = new HashMap<>();
    this.globals = new ArrayList<>();
  }

  public Op minimize(Operand<?> loss) {
    return minimize(loss, getOptimizerName()+"-minimize");
  }

  public Op minimize(Operand<?> loss, String name) {
    List<Pair<Output<?>, Output<? extends TType>>> gradsAndVars = computeGradients(loss);

    return applyGradients(gradsAndVars, name);
  }

  public List<Pair<Output<?>, Output<? extends TType>>> computeGradients(Operand<?> loss) {
    List<Operation> variables = new ArrayList<>();
    Iterator<Operation> opItr = graph.operations();
    while (opItr.hasNext()) {
      Operation op = opItr.next();
      if (op.type().equals(VARIABLE_V2)) {
        variables.add(op);
      }
    }

    Output<?>[] variableOutputArray = new Output[variables.size()];
    for (int i = 0; i < variables.size(); i++) {
      // First output of a variable is it's output.
      variableOutputArray[i] = variables.get(i).output(0);
    }

    Output<?>[] gradients = graph.addGradients(loss.asOutput(), variableOutputArray);
    List<Pair<Output<?>, Output<? extends TType>>> gradVarPairs = new ArrayList<>();

    for (int i = 0; i < variableOutputArray.length; i++) {
      gradVarPairs.add(new Pair<>(gradients[i], (Output<? extends TType>)variableOutputArray[i]));
    }

    return gradVarPairs;
  }

  public Op applyGradients(List<Pair<Output<?>, Output<? extends TType>>> gradsAndVars, String name) {
    List<Output<? extends TType>> variables = gradsAndVars.stream().map(Pair::getB).collect(Collectors.toList());

    createSlots(variables);

    Optional<Operand> prepOp = prepare(name+"/prepare");

    List<Operand<?>> updateOps = new ArrayList<>();
    prepOp.ifPresent(updateOps::add);
    for (Pair pair : gradsAndVars) {
      updateOps.add(applyDense((Output)pair.getA(),(Output)pair.getB()));
    }

    return finish(updateOps,name);
  }

  /**
   * Gets the slot associated with the specified variable and slot name.
   * @param var The variable to lookup.
   * @param slotName The slot name.
   * @return The slot or {@link Optional#empty}.
   */
  public Optional<Variable<?>> getSlot(Output<?> var, String slotName) {
    return getSlot(var.op().name(),slotName);
  }

  /**
   * Gets the slot associated with the specified variable and slot name.
   * @param varName The variable to lookup.
   * @param slotName The slot name.
   * @return The slot or {@link Optional#empty}.
   */
  public Optional<Variable<?>> getSlot(String varName, String slotName) {
    Map<String,Variable<?>> variables = slots.get(slotName);
    if (variables != null) {
      Variable<?> slot = variables.get(varName);
      if (slot != null) {
        return Optional.of(slot);
      } else {
        return Optional.empty();
      }
    } else {
      return Optional.empty();
    }
  }

  /**
   * Creates a slot in the graph for the specified variable with the specified name. Adds the slot's initializer
   * to the graph's initializers, and the slot to the Optimizer's slot map.
   * @param variable The variable to create the slot for.
   * @param slotName The name of the slot.
   * @param initializer The initializer for the slot.
   * @param <T> The type of the variable.
   */
  protected <T extends TType> void createSlot(Output<T> variable, String slotName, Operand<T> initializer) {
    Variable<T> slot = (Variable<T>) tf.withName(createName(variable, slotName)).variable(variable.shape(), TFloat.DTYPE);
    Assign<T> slotInit = tf.assign(slot, initializer);
    graph.addInitializer(slotInit);
    String varName = variable.op().name();
    Map<String,Variable<?>> variables = slots.computeIfAbsent(slotName,(k) -> new HashMap<>());
    variables.put(varName,slot);
  }

  /**
   * No-op prepare method.
   *
   * @param scopeName The scope name to use for any variable creations.
   */
  protected Optional<Operand> prepare(String scopeName) {
    return Optional.empty();
  }

  /**
   * No-op slot creation method.
   * @param variables The variables to create slots for.
   */
  protected void createSlots(List<Output<? extends TType>> variables) { }

  /**
   * Generates
   * @param gradient
   * @param variable
   * @param <T>
   * @return
   */
  protected abstract <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable);

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   * @param updateOperations The update operations.
   * @param name The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  protected Op finish(List<Operand<?>> updateOperations, String name) {
    Scope scope = new Scope(graph);
    scope = scope.withName(name);
    scope = scope.withControlDependencies(updateOperations);
    return NoOp.create(scope);
  }

  /**
   * Name of the optimizer.
   * @return The optimizer name.
   */
  public abstract String getOptimizerName();

  public static String createName(Output<?> variable, String slotName) {
    return variable.op().name() + "-" + slotName;
  }
}
