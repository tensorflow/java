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
 * Basic SGD.
 */
public class GradientDescent extends Optimizer {

  private final float learningRate;

  public GradientDescent(Graph graph, float learningRate) {
    super(graph);
    this.learningRate = learningRate;
  }

  @Override
  protected <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable) {
    return tf.train.applyGradientDescent(variable, tf.constant(learningRate, gradient.dataType()), gradient);
  }

  @Override
  public String toString() {
    return "GradientDescent{" +
        "learningRate=" + learningRate +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "GradientDescent";
  }
}
