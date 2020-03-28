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

import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.types.family.TType;

/**
 * Basic SGD.
 */
public class GradientDescent extends Optimizer {

  private final float learningRate;

  public GradientDescent(Graph graph, float learningRate) {
    super(graph);
    this.learningRate = learningRate;
  }

  public GradientDescent(Graph graph, String name, float learningRate) {
    super(graph, name);
    this.learningRate = learningRate;
  }

  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    return tf.train.applyGradientDescent(variable,
        tf.dtypes.cast(tf.constant(learningRate), gradient.dataType()), gradient);
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
