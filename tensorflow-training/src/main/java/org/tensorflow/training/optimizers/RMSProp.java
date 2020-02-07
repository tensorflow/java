/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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
package org.tensorflow.training.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
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
    Operand<T> rmsInitializer = tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(1.0f, TFloat32.DTYPE),v.dataType()));
    createSlot(v.asOutput(), RMS, rmsInitializer);
    Operand<T> momentumInitializer = tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f, TFloat32.DTYPE),v.dataType()));
    createSlot(v.asOutput(), MOMENTUM, momentumInitializer);
    if (centered) {
      Operand<T> mgInitializer = tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f, TFloat32.DTYPE),v.dataType()));
      createSlot(v.asOutput(), MG, mgInitializer);
    }
  }

  @Override
  protected <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> rmsSlot = getSlot(variable,RMS).get();
    Variable<T> momentumSlot = getSlot(variable,MOMENTUM).get();
    if (centered) {
      Variable<T> mgSlot = getSlot(variable, MG).get();
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
