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
import org.tensorflow.op.train.ApplyMomentum;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.List;

/**
 * SGD plus momentum, either nesterov or traditional.
 * <p>
 * See the <a href="http://jmlr.org/proceedings/papers/v28/sutskever13.pdf">paper</a> for details of
 * nesterov momentum.
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
    Operand<T> initializer = tf
        .fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f, TFloat32.DTYPE), v.dataType()));
    createSlot(v.asOutput(), MOMENTUM, initializer);
  }

  @Override
  protected <T extends TType> Operand<T> applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> slot = getSlot(variable, MOMENTUM).get();
    return tf.train
        .applyMomentum(variable, slot, tf.constant(learningRate, gradient.dataType()), gradient,
            tf.constant(momentum, gradient.dataType()), ApplyMomentum.useNesterov(useNesterov));
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
