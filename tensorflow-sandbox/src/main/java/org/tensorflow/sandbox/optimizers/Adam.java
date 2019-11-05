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
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.Optional;

/**
 * Optimizer that implements the Adam algorithm.
 *
 * See the <a href="http://arxiv.org/abs/1412.6980">paper</a>.
 */
public class Adam extends Optimizer {

  public static final String FIRST_MOMENT = "m";
  public static final String SECOND_MOMENT = "v";

  private final float learningRate;

  private final float betaOne;

  private final float betaTwo;

  private final float epsilon;

  private Constant<TFloat> learningRateConst;
  private Constant<TFloat> epsilonConst;
  private Constant<TFloat> betaOneConst;
  private Constant<TFloat> betaTwoConst;
  private Variable<TFloat> betaOnePower;
  private Variable<TFloat> betaTwoPower;

  public Adam(Graph graph, float learningRate) {
    this(graph, learningRate, 0.9f, 0.999f, 1e-8f);
  }

  public Adam(Graph graph, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(graph);
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdamSlot(v);
    }
    betaOnePower = tf.withName("beta1_power").variable(Shape.make(),TFloat.DTYPE);
    Assign<TFloat> betaOnePowerInit = tf.assign(betaOnePower, tf.constant(betaOne, TFloat.DTYPE));
    graph.addInitializer(betaOnePowerInit);
    betaTwoPower = tf.withName("beta2_power").variable(Shape.make(),TFloat.DTYPE);
    Assign<TFloat> betaTwoPowerInit = tf.assign(betaTwoPower, tf.constant(betaTwo, TFloat.DTYPE));
    graph.addInitializer(betaTwoPowerInit);
  }

  @Override
  protected Optional<Operand> prepare(String scopeName) {
    betaOneConst = tf.constant(betaOne);
    betaTwoConst = tf.constant(betaTwo);
    learningRateConst = tf.constant(learningRate);
    epsilonConst = tf.constant(epsilon);
    return Optional.empty();
  }

  private <T extends TType> void createAdamSlot(Output<T> v) {
    Operand<T> firstMomentInitializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(0.0f, TFloat.DTYPE));//v.dataType()));
    createSlot(v.asOutput(), FIRST_MOMENT, firstMomentInitializer);
    Operand<T> secondMomentInitializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(0.0f, TFloat.DTYPE));//v.dataType()));
    createSlot(v.asOutput(), SECOND_MOMENT, secondMomentInitializer);
  }

  @Override
  protected <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable) {
    @SuppressWarnings("unchecked") // suppressed as the slots are created to have the dtype of the variable.
    Variable<T> firstMomentSlot = (Variable<T>) getSlot(variable,FIRST_MOMENT).get();
    @SuppressWarnings("unchecked") // suppressed as the slots are created to have the dtype of the variable.
    Variable<T> secondMomentSlot = (Variable<T>) getSlot(variable,SECOND_MOMENT).get();
    return tf.train.applyAdam(variable, firstMomentSlot, secondMomentSlot,
        tf.dtypes.cast(betaOnePower,gradient.dataType()),
        tf.dtypes.cast(betaTwoPower,gradient.dataType()),
        tf.dtypes.cast(learningRateConst,gradient.dataType()),
        tf.dtypes.cast(betaOneConst,gradient.dataType()),
        tf.dtypes.cast(betaTwoConst,gradient.dataType()),
        tf.dtypes.cast(epsilonConst,gradient.dataType()),
        gradient);
  }

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   * <p>
   * Adds the betaOne and betaTwo updates to the end of the updates list.
   * @param updateOperations The update operations.
   * @param name The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  @Override
  protected Op finish(List<Operand<?>> updateOperations, String name) {
    updateOperations.add(tf.assign(betaOnePower,tf.math.mul(betaOnePower,betaOneConst)));
    updateOperations.add(tf.assign(betaTwoPower,tf.math.mul(betaTwoPower,betaTwoConst)));
    return super.finish(updateOperations,name);
  }

  @Override
  public String toString() {
    return "Adam{" +
        "learningRate=" + learningRate +
        ", betaOne=" + betaOne +
        ", betaTwo=" + betaTwo +
        ", epsilon=" + epsilon +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "Adam";
  }
}
