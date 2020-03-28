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
package org.tensorflow.training.optimizers;

import java.util.List;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TType;

/**
 * Optimizer that implements the Adagrad algorithm.
 * <p>
 * See the <a href="http://www.jmlr.org/papers/volume12/duchi11a/duchi11a.pdf">paper</a> or this <a
 * href="https://ppasupat.github.io/a9online/uploads/proximal_notes.pdf">intro</a>.
 */
public class AdaGrad extends Optimizer {

  public static final String ACCUMULATOR = "accumulator";

  private final float learningRate;

  private final float initialAccumulatorValue;

  public AdaGrad(Graph graph, float learningRate) {
    this(graph, learningRate, 0.01f);
  }

  public AdaGrad(Graph graph, float learningRate, float initialAccumulatorValue) {
    super(graph);
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
  }

  public AdaGrad(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, 0.01f);
  }

  public AdaGrad(Graph graph, String name, float learningRate, float initialAccumulatorValue) {
    super(graph, name);
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdaGradSlot(v);
    }
  }

  private <T extends TType> void createAdaGradSlot(Output<T> v) {
    Operand<T> initializer = tf.fill(tf.shape(v),
        tf.dtypes.cast(tf.constant(initialAccumulatorValue), v.dataType()));
    createSlot(v.asOutput(), ACCUMULATOR, initializer);
  }

  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> slot = getSlot(variable, ACCUMULATOR).get();
    return tf.train
        .applyAdagrad(variable, slot, tf.dtypes.cast(tf.constant(learningRate), gradient.dataType()),
            gradient);
  }

  @Override
  public String toString() {
    return "AdaGrad{" +
        "learningRate=" + learningRate +
        ", initialAccumulatorValue=" + initialAccumulatorValue +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "Adagrad";
  }
}
