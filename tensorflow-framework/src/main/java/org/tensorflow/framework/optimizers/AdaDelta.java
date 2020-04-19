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
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TType;

/**
 * Optimizer that implements the Adadelta algorithm.
 * <p>
 * See the <a href="http://arxiv.org/abs/1212.5701">paper</a>.
 */
public class AdaDelta extends Optimizer {

  public static final String ACCUMULATOR = "accum";
  public static final String ACCUMULATOR_UPDATE = "accum_update";

  private final float learningRate;

  private final float rho;

  private final float epsilon;

  public AdaDelta(Graph graph, float learningRate) {
    this(graph, learningRate, 0.95f, 1e-8f);
  }

  public AdaDelta(Graph graph, float learningRate, float rho, float epsilon) {
    super(graph);
    this.learningRate = learningRate;
    this.rho = rho;
    this.epsilon = epsilon;
  }

  public AdaDelta(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, 0.95f, 1e-8f);
  }

  public AdaDelta(Graph graph, String name, float learningRate, float rho, float epsilon) {
    super(graph, name);
    this.learningRate = learningRate;
    this.rho = rho;
    this.epsilon = epsilon;
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdaDeltaSlot(v);
    }
  }

  private <T extends TType> void createAdaDeltaSlot(Output<T> v) {
    Operand<T> accumulatorInitializer = tf
        .fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), ACCUMULATOR, accumulatorInitializer);
    Operand<T> updateInitializer = tf
        .fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), ACCUMULATOR_UPDATE, updateInitializer);
  }

  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> accumSlot = getSlot(variable, ACCUMULATOR).get();
    Variable<T> accumUpdateSlot = getSlot(variable, ACCUMULATOR_UPDATE).get();
    return tf.train.applyAdadelta(variable, accumSlot, accumUpdateSlot,
        tf.dtypes.cast(tf.constant(learningRate), gradient.dataType()),
        tf.dtypes.cast(tf.constant(rho), gradient.dataType()),
        tf.dtypes.cast(tf.constant(epsilon), gradient.dataType()),
        gradient);
  }

  @Override
  public String toString() {
    return "AdaDelta{" +
        "learningRate=" + learningRate +
        ", rho=" + rho +
        ", epsilon=" + epsilon +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "Adadelta";
  }
}
