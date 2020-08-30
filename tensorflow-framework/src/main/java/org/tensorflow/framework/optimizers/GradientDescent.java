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

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.Collections;
import java.util.Map;

/**
 * Basic SGD.
 */
public class GradientDescent extends Optimizer {

  private float learningRate;
  private Tensor<TFloat32> learningRateTensor;
  private final Placeholder<TFloat32> learningRatePlaceholder;
  private Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict;

  public GradientDescent(Graph graph, float learningRate) {
    super(graph);
    this.learningRate = learningRate;
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.learningRatePlaceholder =
            tf.withSubScope(LEARNING_RATE).placeholder(TFloat32.DTYPE, Placeholder.shape(Shape.scalar()));
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
  }

  public GradientDescent(Graph graph, String name, float learningRate) {
    super(graph, name);
    this.learningRate = learningRate;
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.learningRatePlaceholder =
            tf.withSubScope(LEARNING_RATE).placeholder(TFloat32.DTYPE, Placeholder.shape(Shape.scalar()));
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
  }

  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    return tf.train.applyGradientDescent(variable,
        tf.dtypes.cast(learningRatePlaceholder, gradient.dataType()), gradient);
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

  /** {@inheritDoc} */
  @Override
  public float getLearningRate() {
    return this.learningRate;
  }

  /** {@inheritDoc} */
  @Override
  public final void setLearningRate(float learningRate) {
    this.learningRate = learningRate;
    if (this.learningRateTensor != null) {
      this.learningRateTensor.close();
    }
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
  }

  /** {@inheritDoc} */
  public Map<Operand<? extends TType>, Tensor<? extends TType>> getFeedDict() {
    return this.feedDict;
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws Exception {
    if (this.learningRateTensor != null) {
      this.learningRateTensor.close();
      this.learningRateTensor = null;
    }
  }
}
