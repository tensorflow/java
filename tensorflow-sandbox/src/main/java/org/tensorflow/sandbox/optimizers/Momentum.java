/*
 * Copyright © 2019, Oracle and/or its affiliates. All rights reserved.
 */
package org.tensorflow.sandbox.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.train.ApplyMomentum;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.family.TType;

import java.util.List;

/**
 * SGD plus momentum, either nesterov or traditional.
 *
 * See the <a href="http://jmlr.org/proceedings/papers/v28/sutskever13.pdf">paper</a> for
 * details of nesterov momentum.
 */
public class Momentum extends Optimizer {

  public static final String MOMENTUM = "momentum";

  private final float learningRate;

  private final float momentum;

  private final boolean useNesterov;

  public Momentum(Graph graph, float learningRate, float momentum, boolean useNesterov) {
    super(graph);
    this.learningRate = learningRate;
    this.momentum = momentum;
    this.useNesterov = useNesterov;
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createMomentumSlot(v);
    }
  }

  private <T extends TType> void createMomentumSlot(Output<T> v) {
    Operand<T> initializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(0.0f, TFloat.DTYPE));//v.dataType()));
    createSlot(v.asOutput(), MOMENTUM, initializer);
  }

  @Override
  protected <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable) {
    @SuppressWarnings("unchecked") // suppressed as the slots are created to have the dtype of the variable.
    Variable<T> slot = (Variable<T>) getSlot(variable,MOMENTUM).get();
    return tf.train.applyMomentum(variable, slot, tf.constant(learningRate, gradient.dataType()), gradient, tf.constant(momentum, gradient.dataType()), ApplyMomentum.useNesterov(useNesterov));
  }

  @Override
  public String toString() {
    return "Momentum{" +
        "learningRate=" + learningRate +
        ", momentum=" + momentum +
        ", useNesterov=" + useNesterov +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "Momentum";
  }
}
