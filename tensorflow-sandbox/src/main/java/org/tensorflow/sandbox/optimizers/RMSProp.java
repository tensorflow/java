/*
 * Copyright © 2019, Oracle and/or its affiliates. All rights reserved.
 */
package org.tensorflow.sandbox.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.family.TType;

import java.util.List;

/**
 * Optimizer that implements the RMSProp algorithm.
 *
 * See the <a href="http://www.cs.toronto.edu/~tijmen/csc321/slides/lecture_slides_lec6.pdf">lecture notes</a>
 * that is inexplicably the canonical reference.
 */
public class RMSProp extends Optimizer {

  public static final String RMS = "rms";
  public static final String MG = "mg"; // mean gradient?
  public static final String MOMENTUM = "momentum";

  private final float learningRate;
  private final float decay;
  private final float momentum;
  private final float epsilon;
  private final boolean centered;

  public RMSProp(Graph graph, float learningRate) {
    this(graph, learningRate, 0.9f, 0.0f, 1e-10f, false);
  }

  public RMSProp(Graph graph, float learningRate, float decay, float momentum, float epsilon, boolean centered) {
    super(graph);
    this.learningRate = learningRate;
    this.decay = decay;
    this.momentum = momentum;
    this.epsilon = epsilon;
    this.centered = centered;
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createRMSPropSlot(v);
    }
  }

  private <T extends TType> void createRMSPropSlot(Output<T> v) {
    Operand<T> rmsInitializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(1.0f, TFloat.DTYPE));//v.dataType()));
    createSlot(v.asOutput(), RMS, rmsInitializer);
    Operand<T> momentumInitializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(0.0f, TFloat.DTYPE));//v.dataType()));
    createSlot(v.asOutput(), MOMENTUM, momentumInitializer);
    if (centered) {
      Operand<T> mgInitializer = tf.fill(tf.shape(v), (Constant<T>) tf.constant(0.0f, TFloat.DTYPE));//v.dataType()));
      createSlot(v.asOutput(), MG, mgInitializer);
    }
  }

  @Override
  protected <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable) {
    @SuppressWarnings("unchecked") // suppressed as the slots are created to have the dtype of the variable.
    Variable<T> rmsSlot = (Variable<T>) getSlot(variable,RMS).get();
    @SuppressWarnings("unchecked") // suppressed as the slots are created to have the dtype of the variable.
    Variable<T> momentumSlot = (Variable<T>) getSlot(variable,MOMENTUM).get();
    if (centered) {
      @SuppressWarnings("unchecked") // suppressed as the slots are created to have the dtype of the variable.
      Variable<T> mgSlot = (Variable<T>) getSlot(variable, MG).get();
      return tf.train.applyCenteredRmsProp(variable, mgSlot, rmsSlot, momentumSlot,
          tf.constant(learningRate, gradient.dataType()),
          tf.constant(decay, gradient.dataType()),
          tf.constant(momentum, gradient.dataType()),
          tf.constant(epsilon, gradient.dataType()),
          gradient);
    } else {
      return tf.train.applyRmsProp(variable, rmsSlot, momentumSlot,
          tf.constant(learningRate, gradient.dataType()),
          tf.constant(decay, gradient.dataType()),
          tf.constant(momentum, gradient.dataType()),
          tf.constant(epsilon, gradient.dataType()),
          gradient);
    }
  }

  @Override
  public String toString() {
    return "RMSProp{" +
        "learningRate=" + learningRate +
        ", decay=" + decay +
        ", momentum=" + momentum +
        ", epsilon=" + epsilon +
        ", centered=" + centered +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "RMSProp";
  }
}
