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
package org.tensorflow.framework.initializers;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.framework.utils.ShapeUtils;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.linalg.Qr;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Initializer that generates an orthogonal matrix.
 * <p>
 *     If the shape of the tensor to initialize is two-dimensional, it is initialized with an orthogonal matrix obtained from the QR
 *     decomposition of a matrix of random numbers drawn from a normal distribution. If the matrix has fewer rows than
 *     columns then the output will have orthogonal rows. Otherwise, the output will have orthogonal columns.
 * </p>
 * <p>
 *    If the shape of the tensor to initialize is more than two-dimensional,
 *    a matrix of shape <code>(shape.size(0) * ... * shape.size(n - 2), shape.size(n - 1))</code> is initialized,
 *    where <code>n</code> is the length of the shape vector. The matrix is subsequently reshaped
 *    to give a tensor of the desired shape.
 * </p>
 * <p>Examples:
 *
 * <pre>
 *      Orthogonal&lt;TFloat32, TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.Orthogonal&lt;&gt;(tf);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 *
 * @param <T> The TType for the call operation
 * @param <U> The TNumber for the call operation
 */
public class Orthogonal<T extends TType, U extends TNumber> extends BaseInitializer<T> {

  public static final double GAIN_DEFAULT = 1.0;

  private final double gain;
  private final Long seed;

  /**
   * Creates an Orthogonal Initializer
   *
   * @param tf the TensorFlow Ops
   */
  public Orthogonal(Ops tf) {
    this(tf, GAIN_DEFAULT, null);
  }

  /**
   * Creates an Orthogonal Initializer
   *
   * @param tf the TensorFlow Ops
   * @param gain the gain to be applied to the Matrix
   */
  public Orthogonal(Ops tf, double gain) {
    this(tf, gain, null);
  }

  /**
   * Creates an Orthogonal Initializer
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation
   */
  public Orthogonal(Ops tf, Long seed) {
    this(tf, GAIN_DEFAULT, seed);
  }

  /**
   * Creates an Orthogonal Initializer
   *
   * @param tf the TensorFlow Ops
   * @param gain the gain to be applied to the Matrix
   * @param seed the seed for random number generation
   */
  public Orthogonal(Ops tf, double gain, Long seed) {
    super(tf);
    this.gain = gain;
    this.seed = seed;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<TInt64> dims, DataType<T> dtype) {
    if (!dtype.isFloating()) {
      throw new IllegalArgumentException("Expected floating point type, got " + dtype.name());
    }
    Shape dimsShape = ShapeUtils.toShape(tf.scope(), dims);
    if (dimsShape.numDimensions() < 2) {
      throw new IllegalArgumentException(
          "The tensor to initialize must be at least two-dimensional, got "
              + dimsShape.numDimensions());
    }
    long num_rows = 1;
    int i = 0;
    for (; i < dimsShape.numDimensions() - 1; i++) num_rows *= dimsShape.size(i);
    long num_cols = dimsShape.size(i);
    Shape flat_shape = Shape.of(Math.max(num_rows, num_cols), Math.min(num_rows, num_cols));
    long lseed = this.seed == null ? 0L : this.seed;
    long[] seeds = {lseed, 0};
    @SuppressWarnings("unchecked")
    DataType<U> numdType = (DataType<U>) dtype;
    @SuppressWarnings("unchecked")
    Operand<T> op =
        (Operand<T>)
            tf.random.statelessRandomNormal(tf.constant(flat_shape), tf.constant(seeds), numdType);

    Qr.Options qrOptions = Qr.fullMatrices(false);
    Qr<T> qrOp = tf.linalg.qr(op, qrOptions);
    Output<T> qo = qrOp.q();
    Output<T> ro = qrOp.r();
    Operand<T> diagOp =
        tf.linalg.matrixDiagPart(ro, tf.constant(0), tf.dtypes.cast(tf.constant(0), dtype));
    Operand<T> qop = tf.math.mul(qo, tf.math.sign(diagOp));
    if (num_rows < num_cols) qop = tf.linalg.transpose(qop, null);

    return tf.math.mul(qop, tf.dtypes.cast(tf.constant(this.gain), dtype));
  }
}
