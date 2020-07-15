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

import java.util.List;
import java.util.Optional;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Optimizer that implements the Adam algorithm.
 * <p>
 * See the <a href="http://arxiv.org/abs/1412.6980">paper</a>.
 */
@Operator
public class Adam extends Optimizer {

  public static final String FIRST_MOMENT = "m";
  public static final String SECOND_MOMENT = "v";

  private final float learningRate;

  private final float betaOne;

  private final float betaTwo;

  private final float epsilon;

  private Constant<TFloat32> learningRateConst;
  private Constant<TFloat32> epsilonConst;
  private Constant<TFloat32> betaOneConst;
  private Constant<TFloat32> betaTwoConst;
  private Variable<TFloat32> betaOnePower;
  private Variable<TFloat32> betaTwoPower;

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

  public Adam(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, 0.9f, 0.999f, 1e-8f);
  }

  public Adam(Graph graph, String name, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(graph, name);
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
  }

  @Endpoint(name = "adam_minimize")
  public static <T extends TType> Op createAdamMinimize(Scope scope, Operand<T> loss,
      float learningRate, float betaOne, float betaTwo, float epsilon,
      Optimizer.Options... options) {
    if (!(scope.env() instanceof Graph)) {
      throw new IllegalArgumentException("Optimizers are only supported on Graphs");
    }
    Adam adam = new Adam((Graph) scope.env(), learningRate, betaOne, betaTwo, epsilon);
    String name = null;
    for (Options o : options) {
      if (o.sharedName != null) {
        name = o.sharedName;
      }
    }
    if (name == null) {
      return adam.minimize(loss);
    } else {
      return adam.minimize(loss, name);
    }
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdamSlot(v.asOutput());
    }
    betaOnePower = tf.withName("beta1_power").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> betaOnePowerInit = tf
        .assign(betaOnePower, tf.constant(betaOne));
    graph.addInitializer(betaOnePowerInit);
    betaTwoPower = tf.withName("beta2_power").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> betaTwoPowerInit = tf
        .assign(betaTwoPower, tf.constant(betaTwo));
    graph.addInitializer(betaTwoPowerInit);
  }

  @Override
  protected Optional<Op> prepare(String scopeName) {
    betaOneConst = tf.constant(betaOne);
    betaTwoConst = tf.constant(betaTwo);
    learningRateConst = tf.constant(learningRate);
    epsilonConst = tf.constant(epsilon);
    return Optional.empty();
  }

  private <T extends TType> void createAdamSlot(Output<T> v) {
    Operand<T> firstMomentInitializer = tf
        .fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), FIRST_MOMENT, firstMomentInitializer);
    Operand<T> secondMomentInitializer = tf
        .fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), SECOND_MOMENT, secondMomentInitializer);
  }

  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> firstMomentSlot = getSlot(variable, FIRST_MOMENT).get();
    Variable<T> secondMomentSlot = getSlot(variable, SECOND_MOMENT).get();
    return tf.train.applyAdam(variable, firstMomentSlot, secondMomentSlot,
        tf.dtypes.cast(betaOnePower, gradient.dataType()),
        tf.dtypes.cast(betaTwoPower, gradient.dataType()),
        tf.dtypes.cast(learningRateConst, gradient.dataType()),
        tf.dtypes.cast(betaOneConst, gradient.dataType()),
        tf.dtypes.cast(betaTwoConst, gradient.dataType()),
        tf.dtypes.cast(epsilonConst, gradient.dataType()),
        gradient);
  }

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   * <p>
   * Adds the betaOne and betaTwo updates to the end of the updates list.
   *
   * @param updateOperations The update operations.
   * @param name             The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  @Override
  protected Op finish(List<Op> updateOperations, String name) {
    updateOperations.add(tf.assign(betaOnePower, tf.math.mul(betaOnePower, betaOneConst)));
    updateOperations.add(tf.assign(betaTwoPower, tf.math.mul(betaTwoPower, betaTwoConst)));
    return super.finish(updateOperations, name);
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
