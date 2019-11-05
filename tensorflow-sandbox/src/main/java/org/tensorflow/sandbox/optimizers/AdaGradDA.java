/*
 * Copyright © 2019, Oracle and/or its affiliates. All rights reserved.
 */
package org.tensorflow.sandbox.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.Optional;

/**
 * Optimizer that implements the Adagrad Dual-Averaging algorithm.
 *
 * See the <a href="http://www.jmlr.org/papers/volume12/duchi11a/duchi11a.pdf">paper</a>.
 */
public class AdaGradDA extends Optimizer {

  public static final String ACCUMULATOR = "gradient_accumulator";
  public static final String SQUARED_ACCUMULATOR = "gradient_squared_accumulator";

  private Variable<TInt64> globalStep;

  private final float learningRate;

  private final float initialAccumulatorValue;

  private final float l1Strength;

  private final float l2Strength;

  public AdaGradDA(Graph graph, float learningRate) {
    this(graph, learningRate, 0.1f, 0.0f, 0.0f);
  }

  public AdaGradDA(Graph graph, float learningRate, float initialAccumulatorValue, float l1Strength, float l2Strength) {
    super(graph);
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1Strength = l1Strength;
    this.l2Strength = l2Strength;
  }

  @Override
  protected Optional<Operand> prepare(String name) {
    return Optional.of(tf.assignAdd(globalStep,tf.constant(1L)));
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdaGradDASlot(v);
    }
    globalStep = tf.withName("adagrad-da-global-step").variable(Shape.make(),TInt64.DTYPE);
    Assign<TInt64> globalStepInitializer = tf.assign(globalStep, tf.constant(0L));
    graph.addInitializer(globalStepInitializer);
  }

  private <T extends TType> void createAdaGradDASlot(Output<T> v) {
    Operand<T> initializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(0.0f, TFloat.DTYPE));//v.dataType()));
    createSlot(v.asOutput(), ACCUMULATOR, initializer);
    Operand<T> sqInitializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(initialAccumulatorValue, TFloat.DTYPE));//v.dataType()));
    createSlot(v.asOutput(), SQUARED_ACCUMULATOR, sqInitializer);
  }

  @Override
  protected <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable) {
    @SuppressWarnings("unchecked") // suppressed as the slots are created to have the dtype of the variable.
    Variable<T> gradSlot = (Variable<T>) getSlot(variable,ACCUMULATOR).get();
    @SuppressWarnings("unchecked")
    Variable<T> gradSquaredSlot = (Variable<T>) getSlot(variable,SQUARED_ACCUMULATOR).get();
    return tf.train.applyAdagradDa(variable, gradSlot, gradSquaredSlot, gradient,
        tf.constant(learningRate, gradient.dataType()),
        tf.constant(l1Strength, gradient.dataType()),
        tf.constant(l2Strength, gradient.dataType()),
        globalStep);
  }

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   * <p>
   * Adds the global step update to the end of the updates list.
   * @param updateOperations The update operations.
   * @param name The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  @Override
  protected Op finish(List<Operand<?>> updateOperations, String name) {
    updateOperations.add(tf.assignAdd(globalStep,tf.constant(1L)));
    return super.finish(updateOperations,name);
  }

  @Override
  public String toString() {
    return "AdaGradDA{" +
        "globalStep=" + globalStep +
        ", learningRate=" + learningRate +
        ", initialAccumulatorValue=" + initialAccumulatorValue +
        ", l1Strength=" + l1Strength +
        ", l2Strength=" + l2Strength +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "adagrad-da";
  }
}
