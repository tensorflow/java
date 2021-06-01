/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.activations;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.ReduceMax;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.types.family.TFloating;

/**
 * Softmax converts a real vector to a vector of categorical probabilities.
 *
 * <p>The elements of the output vector are in range (0, 1) and sum to 1.
 *
 * <p>Each vector is handled independently. The <code>axis</code> argument sets which axis of the
 * input the function is applied along.
 *
 * <p>Softmax is often used as the activation for the last layer of a classification network because
 * the result could be interpreted as a probability distribution.
 *
 * <p>The softmax of each vector x is computed as: <code>exp(x) / tf.sum(exp(x))</code>.
 *
 * <p>The input values in are the log-odds of the resulting probability.
 *
 * @param <T> the data type of the activation
 */
public class Softmax<T extends TFloating> extends AbstractActivation<T> {

  private static final int AXIS_DEFAULT = -1;

  private final int axis;

  /**
   * Creates a softmax activation where the default axis is {@link #AXIS_DEFAULT} which indicates
   * the last dimension.
   */
  public Softmax() {
    this(AXIS_DEFAULT);
  }

  /**
   * Creates a Softmax activation
   *
   * @param axis The dimension softmax would be performed on.
   */
  public Softmax(int axis) {
    super();
    this.axis = axis;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Ops tf, Operand<T> input) {
    Shape shape = input.shape();
    int numDimensions = shape.numDimensions();
    if (numDimensions == 2) {
      return tf.nn.softmax(input);
    } else {
      Operand<T> e =
          tf.math.exp(
              tf.math.sub(input, tf.reduceMax(input, tf.constant(axis), ReduceMax.keepDims(true))));
      Operand<T> s = tf.reduceSum(e, tf.constant(axis), ReduceSum.keepDims(true));
      return tf.math.div(e, s);
    }
  }
}
