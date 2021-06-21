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
package org.tensorflow.framework.losses;

import static org.tensorflow.framework.utils.CastHelper.cast;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.ReduceAll;
import org.tensorflow.op.core.ReduceMax;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.math.Mean;
import org.tensorflow.op.math.Softplus;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/** Built-in loss functions. */
public class Losses {

  /** Default Fuzz factor. */
  public static final float EPSILON = 1e-7f;

  public static final int CHANNELS_LAST = -1;
  public static final int CHANNELS_FIRST = 1;

  /**
   * Calculates the mean absolute error between labels and predictions.
   *
   * <p><code>loss = reduceMean(abs(labels - predictions))</code>
   *
   * @param tf The TensorFlow Ops
   * @param labels the labels
   * @param predictions the predictions
   * @param <T> the data type of the predictions and result
   * @return the mean absolute error
   */
  public static <T extends TNumber> Operand<T> meanAbsoluteError(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Operand<T> tLabels = cast(tf, labels, predictions.type());
    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = ops.getTarget();
    tLabels = ops.getLabels();
    return tf.math.mean(
        tf.math.abs(tf.math.sub(tLabels, predictions)), tf.constant(-1), Mean.keepDims(false));
  }

  /**
   * Computes the mean squared error between labels and predictions.
   *
   * <p><code>loss = reduceMean(square(labels - predictions))</code>
   *
   * @param tf The TensorFlow Ops
   * @param labels the labels
   * @param predictions the predictions
   * @param <T> the data type of the predictions and result
   * @return the mean squared error
   */
  public static <T extends TNumber> Operand<T> meanSquaredError(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Operand<T> tLabels = cast(tf, labels, predictions.type());
    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = ops.getTarget();
    tLabels = ops.getLabels();
    return tf.math.mean(tf.math.squaredDifference(predictions, tLabels), tf.constant(-1));
  }

  /**
   * Calculates the mean absolute percentage error between labels and predictions.
   *
   * <p><code>loss = 100 * reduceMean(abs((labels - predictions) / labels))</code>
   *
   * @param tf The TensorFlow Ops
   * @param labels the labels
   * @param predictions the predictions
   * @param <T> the data type of the predictions and result
   * @return the mean absolute percentage error
   */
  public static <T extends TNumber> Operand<T> meanAbsolutePercentageError(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = ops.getTarget();
    tLabels = ops.getLabels();
    Operand<T> diff =
        tf.math.abs(
            tf.math.div(
                tf.math.sub(tLabels, predictions),
                tf.math.maximum(
                    tf.math.abs(tLabels), cast(tf, tf.constant(EPSILON), predictionType))));
    return tf.math.mul(
        cast(tf, tf.constant(100), predictionType), tf.math.mean(diff, tf.constant(-1)));
  }

  /**
   * Calculates the mean squared logarithmic error between labels and predictions.
   *
   * <p><code>loss = reduceMean(square(log(labels + 1) - log(predictions + 1)))</code>
   *
   * @param tf The TensorFlow Ops
   * @param labels the labels
   * @param predictions the predictions
   * @param <T> the data type of the predictions and result
   * @return the mean squared logarithmic percentage error
   */
  public static <T extends TNumber> Operand<T> meanSquaredLogarithmicError(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = ops.getTarget();
    tLabels = ops.getLabels();

    Operand<T> epsilonConst = cast(tf, tf.constant(EPSILON), predictionType);
    Operand<T> one = cast(tf, tf.constant(1), predictionType);

    Operand<T> firstLog = tf.math.log(tf.math.add(tf.math.maximum(predictions, epsilonConst), one));
    Operand<T> secondLog = tf.math.log(tf.math.add(tf.math.maximum(tLabels, epsilonConst), one));

    return tf.math.mean(tf.math.squaredDifference(firstLog, secondLog), tf.constant(-1));
  }

  /**
   * Computes the binary crossentropy loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of labelSmoothing
   *     correspond to heavier smoothing.
   * @param <T> the data type of the predictions and labels
   * @return the binary crossentropy loss.
   */
  public static <T extends TNumber> Operand<T> binaryCrossentropy(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<T> predictions,
      boolean fromLogits,
      float labelSmoothing) {
    Operand<T> tLabels = cast(tf, labels, predictions.type());
    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = ops.getTarget();
    tLabels = ops.getLabels();

    if (labelSmoothing != 0.0f) {
      tLabels = smoothBinaryLabels(tf, tLabels, labelSmoothing);
    }
    Operand<T> bce = binaryCrossentropyHelper(tf, tLabels, predictions, fromLogits);
    return tf.math.mean(bce, tf.constant(-1));
  }

  /**
   * Computes the unreduced crossentropy loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param target the target Operand
   * @param output the output, either logits or a probability distribution
   * @param fromLogits whether `output` is expected to be a logits tensor. By default, we consider
   *     that `output` encodes a probability distribution.
   * @param <T> the data type of the Operands
   * @return the binary crossentropy loss.
   */
  private static <T extends TNumber> Operand<T> binaryCrossentropyHelper(
      Ops tf, Operand<T> target, Operand<T> output, boolean fromLogits) {
    if (fromLogits) return tf.nn.sigmoidCrossEntropyWithLogits(target, output);

    /* TODO - skip this logic for now. It requires walking back the inputs which is not yet possible
    if (!(output instanceof Variable) && (!tf.scope().env().isEager())) {
      // TODO - this does not work
      // TODO output = backtrackIdentity(output);
      // TODO if (output.op().type().equals(Sigmoid.OP_NAME)) {
      // TODO   if (output.op().numInputess() != 1)
      // TODO     throw new IllegalArgumentException("output can only have 1 output");
      // TODO   output = output.op().inout(0);
       // TODO   return tf.nn.sigmoidCrossEntropyWithLogits(target, output);
      // TODO}
    }
    */

    Class<T> outputType = output.type();
    Operand<T> one = cast(tf, tf.constant(1), outputType);
    Operand<T> epsilonConst = cast(tf, tf.constant(EPSILON), outputType);
    Operand<T> oneMinusEpsilonConst = tf.math.sub(one, epsilonConst);
    output = tf.clipByValue(output, epsilonConst, oneMinusEpsilonConst);

    // Compute cross entropy from probabilities.
    Operand<T> bce = tf.math.mul(target, tf.math.log(tf.math.add(output, epsilonConst)));
    bce =
        tf.math.add(
            bce,
            tf.math.mul(
                tf.math.sub(one, target),
                tf.math.log(tf.math.add(tf.math.sub(one, output), epsilonConst))));
    return tf.math.neg(bce);
  }

  /**
   * Computes the categorical crossentropy loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in <code>[0, 1]</code>. When <code>&gt; 0</code>, label values are
   *     smoothed, meaning the confidence on label values are relaxed. e.g. <code>labelSmoothing=0.2
   *     </code> means that we will use a value of <code>0.1</code> for label <code>0</code> and
   *     <code>0.9</code> for label <code>1</code>
   * @param axis the
   * @param <T> the data type of the predictions and labels
   * @return the categorical crossentropy loss.
   */
  public static <T extends TNumber> Operand<T> categoricalCrossentropy(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<T> predictions,
      boolean fromLogits,
      float labelSmoothing,
      int axis) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = ops.getTarget();
    tLabels = ops.getLabels();

    if (labelSmoothing != 0.0f) {
      tLabels = smoothCategoricalLabels(tf, tLabels, labelSmoothing);
    }
    if (fromLogits) {
      return tf.nn.softmaxCrossEntropyWithLogits(tLabels, predictions, axis);
    }
    /* TODO
    if (!(predictions instanceof Variable) && (!tf.scope().env().isEager())) {

      // TODO output = backtrackIdentity(output); doesn't seem to work with Java version.
      if (predictions.op().type().equals("Softmax")) {
        if (predictions.op().numOutputs() != 1)
          throw new IllegalArgumentException("output can only have 1 output");
        predictions = predictions.op().output(0);
        return tf.nn.softmaxCrossEntropyWithLogits(tLabels, predictions, -1);
      }
    }
    */

    Operand<T> one = cast(tf, tf.constant(1), predictionType);
    Operand<T> epsilonConst = cast(tf, tf.constant(EPSILON), predictionType);
    Operand<T> oneMinusEpsilonConst = tf.math.sub(one, epsilonConst);
    predictions =
        tf.math.div(
            predictions, tf.reduceSum(predictions, tf.constant(axis), ReduceSum.keepDims(true)));
    predictions = tf.clipByValue(predictions, epsilonConst, oneMinusEpsilonConst);

    // Compute cross entropy from probabilities.
    Operand<T> cce =
        tf.reduceSum(
            tf.math.mul(tLabels, tf.math.log(predictions)),
            tf.constant(axis),
            ReduceSum.keepDims(false));
    return tf.math.neg(cce);
  }

  /**
   * Computes the categorical hinge loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets, values are expected to be 0 or 1.
   * @param predictions the predictions
   * @param <T> the data type of the predictions and labels
   * @return the categorical hinge loss
   */
  public static <T extends TNumber> Operand<T> categoricalHinge(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();
    Operand<T> one = cast(tf, tf.constant(1), predictionType);
    Operand<T> zero = cast(tf, tf.constant(0), predictionType);

    Operand<T> pos =
        tf.reduceSum(
            tf.math.mul(tLabels, predictions), tf.constant(-1), ReduceSum.keepDims(Boolean.FALSE));
    Operand<T> neg =
        tf.reduceMax(
            tf.math.mul(tf.math.sub(one, tLabels), predictions),
            tf.constant(-1),
            ReduceMax.keepDims(Boolean.FALSE));
    Operand<T> sub = tf.math.sub(neg, pos);
    Operand<T> add = tf.math.add(sub, one);
    return tf.math.maximum(zero, add);
  }

  /**
   * Computes the cosine similarity loss between labels and predictions.
   *
   * <p>Note that it is a number between <code>-1</code> and <code>1</code>, which is different from
   * the mathematical definition of cosine similarity where <code>1</code> represents similar
   * vectors, and <code>0</code> represents dissimilar vectors. In this function, the numbers are
   * inverted in a range of <code>-1</code> to <code>1</code>. When it is a negative number between
   * <code>-1</code> and <code>0</code>, <code>0</code> indicates orthogonality and values closer to
   * <code>-1</code> indicate greater similarity. The values closer to <code>1</code> indicate
   * greater dissimilarity. This makes it usable as a loss function in a setting where you try to
   * maximize the proximity between predictions and targets. If either labels or predictions is a
   * zero vector, cosine similarity will be <code>0</code> regardless of the proximity between
   * predictions and targets.
   *
   * <p><code>loss = -sum(l2Norm(labels) * l2Norm(predictions))</code>
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param axis Axis along which to determine similarity.
   * @param <T> the data type of the predictions and labels
   * @return the cosine similarity loss
   */
  public static <T extends TNumber> Operand<T> cosineSimilarity(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, int[] axis) {
    Operand<T> tLabels = cast(tf, labels, predictions.type());
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();

    tLabels = l2Normalize(tf, tLabels, axis);
    predictions = l2Normalize(tf, predictions, axis);
    Operand<T> mathMul = tf.math.mul(tLabels, predictions);
    return tf.reduceSum(mathMul, tf.constant(axis), ReduceSum.keepDims(Boolean.FALSE));
  }

  /**
   * Computes the hinge loss between labels and predictions
   *
   * <p><code>loss = reduceMean(maximum(1 - labels * predictions, 0))</code>
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets, values are expected to be -1 or 1. If binary (0 or 1) labels are
   *     provided, they will be converted to -1 or 1.
   * @param predictions the predictions
   * @param <T> the data type of the predictions and labels
   * @return the hinge loss
   */
  public static <T extends TNumber> Operand<T> hinge(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();
    Operand<T> one = cast(tf, tf.constant(1), predictionType);
    Operand<T> zero = cast(tf, tf.constant(0), predictionType);

    tLabels = maybeConvertLabels(tf, tLabels);

    return tf.math.mean(
        tf.math.maximum(tf.math.sub(one, tf.math.mul(tLabels, predictions)), zero),
        tf.constant(-1));
  }

  /**
   * Computes the Huber loss between labels and predictions.
   *
   * <p>For each value x in error = labels - predictions:
   *
   * <pre>
   *     loss = 0.5 * x^2                  if |x| &lt;= d
   *     loss = 0.5 * d^2 + d * (|x| - d)  if |x| &gt; d
   * </pre>
   *
   * <p>where d is delta.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param delta the point where the Huber loss function changes from quadratic to linear.
   * @param <T> the data type of the predictions and labels
   * @return the Huber loss
   */
  public static <T extends TNumber> Operand<T> huber(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, float delta) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();

    Operand<T> error = tf.math.sub(predictions, tLabels);
    Operand<T> deltaConst = cast(tf, tf.constant(delta), predictionType);
    Operand<T> point5 = cast(tf, tf.constant(0.5), predictionType);
    Operand<T> absError = tf.math.abs(error);
    Operand<T> quadratic = tf.math.minimum(absError, deltaConst);
    Operand<T> linear = tf.math.sub(absError, quadratic);
    Operand<T> q2Point5 = tf.math.mul(point5, tf.math.mul(quadratic, quadratic));
    Operand<T> deltaLinear = tf.math.mul(deltaConst, linear);
    Operand<T> loss = tf.math.add(q2Point5, deltaLinear);
    return tf.math.mean(loss, tf.constant(-1));
  }

  /**
   * Computes the Kullback-Leibler divergence loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param <T> the data type of the predictions and labels
   * @return the Kullback-Leibler divergence loss
   * @see <a href="https://en.wikipedia.org/wiki/Kullback?Leibler_divergence">Kullback?Leibler
   *     divergence</a>
   */
  public static <T extends TNumber> Operand<T> kullbackLeiblerDivergence(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();
    Operand<T> one = cast(tf, tf.constant(1), predictionType);
    Operand<T> epsilonConst = cast(tf, tf.constant(EPSILON), predictionType);

    tLabels = tf.clipByValue(tLabels, epsilonConst, one);
    predictions = tf.clipByValue(predictions, epsilonConst, one);
    return tf.reduceSum(
        tf.math.mul(tLabels, tf.math.log(tf.math.div(tLabels, predictions))), tf.constant(-1));
  }

  /**
   * Computes the hyperbolic cosine loss between labels and predictions.
   *
   * <p><code>log(cosh(x))</code> is approximately equal to <code>(x ** 2) / 2</code> for small
   * <code>x</code> and to <code>abs(x) - log(2)</code> for large <code>x</code>. This means that
   * 'logCosh' works mostly like the mean squared error, but will not be so strongly affected by the
   * occasional wildly incorrect prediction.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param <T> the data type of the predictions and labels
   * @return the hyperbolic cosine divergence loss
   */
  public static <T extends TNumber> Operand<T> logCosh(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();
    Operand<T> minusTwo = cast(tf, tf.constant(-2), predictionType);
    Operand<T> two = cast(tf, tf.constant(2), predictionType);

    Operand<T> diff = tf.math.sub(predictions, tLabels);
    Softplus<T> softplus = tf.math.softplus(tf.math.mul(minusTwo, diff));
    Operand<T> logcosh = tf.math.sub(tf.math.add(diff, softplus), tf.math.log(two));
    return tf.math.mean(logcosh, tf.constant(-1));
  }

  /**
   * Computes the Poisson loss between labels and predictions.
   *
   * <p>The Poisson loss is the mean of the elements of the Tensor <code>
   * predictions - labels * log(predictions)</code>.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param <T> the data type of the predictions and labels
   * @return the Poisson loss
   */
  public static <T extends TNumber> Operand<T> poisson(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();
    Operand<T> epsilonConst = cast(tf, tf.constant(EPSILON), predictionType);

    return tf.math.mean(
        tf.math.sub(
            predictions, tf.math.mul(tLabels, tf.math.log(tf.math.add(predictions, epsilonConst)))),
        tf.constant(-1));
  }

  /**
   * Computes the sparse categorical crossentropy loss between labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param predictions the predictions
   * @param fromLogits Whether predictions is expected to be logits. By default, it is assumed that
   *     predictions encodes a probability distribution.
   * @param axis The dimension along which the entropy is computed.
   * @param <T> the data type of the predictions and labels
   * @return the sparse categorical crossentropy loss
   */
  public static <T extends TNumber> Operand<T> sparseCategoricalCrossentropy(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<T> predictions,
      boolean fromLogits,
      int axis) {
    Class<T> predictionType = predictions.type();
    Operand<T> epsilonConst = cast(tf, tf.constant(EPSILON), predictionType);
    Operand<T> one = cast(tf, tf.constant(1), predictionType);
    Operand<T> oneMinusEpsilonConst = tf.math.sub(one, epsilonConst);

    /* TODO need ability to walk back inputs
    if (!fromLogits && !(predictions instanceof Variable) && (!tf.scope().env().isEager())) {
      // TODO output = backtrackIdentity(output); doesn't seem to work with Java version.
      /* TODO
      if (predictions.op().type().equals(Softmax.OP_NAME)) {
        // When softmax activation function is used for output operation, we
        // use logits from the softmax function directly to compute loss in order
        // to prevent collapsing zero when training.
        // TODO  if( output.op().numOutputs() != 1)
        //          throw new IllegalArgumentException("output can only have 1 output");
        // TODO output = output.op.inputs[0]
        fromLogits = true;
      }

    }
     */
    if (!fromLogits) {

      predictions = tf.clipByValue(predictions, epsilonConst, oneMinusEpsilonConst);
      predictions = tf.math.log(predictions);
    }
    Shape predictionsShape = predictions.shape();
    int predictionsRank = predictionsShape.numDimensions();
    axis %= predictionsRank;
    if (axis < 0) {
      axis += predictionsRank;
    }
    if (axis != predictionsRank - 1) {
      int[] axisNew = moveAxisToEnd(axis, predictionsRank);
      predictions = tf.linalg.transpose(predictions, tf.constant(axisNew));
    }

    Operand<TInt64> iLabels = cast(tf, labels, TInt64.class);

    // Try to adjust the shape so that rank of labels = rank of logits - 1.
    Shape labelsShape = labels.shape();
    int labelsRank = labelsShape.numDimensions();

    boolean updateShape = labelsRank != predictionsRank - 1;
    if (updateShape) {
      Shape newShape = labelsShape.take(labelsRank - 1);
      iLabels = tf.reshape(iLabels, tf.constant(newShape)); // flatten one dimension
      predictions =
          tf.reshape(
              predictions,
              tf.constant(
                  new long[] {-1L, predictionsShape.get(predictionsShape.numDimensions() - 1)}));
    }

    @SuppressWarnings("unchecked")
    Operand<T> loss = tf.nn.sparseSoftmaxCrossEntropyWithLogits(iLabels, predictions);
    if (updateShape && predictionsRank >= 3) {
      Shape newShape = predictionsShape.take(predictionsShape.numDimensions() - 1);
      loss = tf.reshape(loss, tf.constant(newShape));
    }
    return loss;
  }

  /**
   * Computes the squared hinge loss between labels and predictions.
   *
   * <p><code>loss = reduceMean(square(maximum(1 - labels * predictions, 0)))</code>
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets, values are expected to be -1 or 1. If binary (0 or 1) labels are *
   *     provided, they will be converted to -1 or 1.
   * @param predictions the predictions
   * @param <T> the data type of the predictions and labels
   * @return the squared hinge loss
   */
  public static <T extends TNumber> Operand<T> squaredHinge(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions) {
    Class<T> predictionType = predictions.type();
    Operand<T> tLabels = cast(tf, labels, predictionType);
    LossTuple<T> lossTuple = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, predictions, null);
    predictions = lossTuple.getTarget();
    tLabels = lossTuple.getLabels();
    Operand<T> one = cast(tf, tf.constant(1), predictionType);
    Operand<T> zero = cast(tf, tf.constant(0), predictionType);

    tLabels = maybeConvertLabels(tf, tLabels);
    return tf.math.mean(
        tf.math.square(tf.math.maximum(tf.math.sub(one, tf.math.mul(tLabels, predictions)), zero)),
        tf.constant(-1));
  }

  /**
   * Smooths binary labels
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param labelSmoothing A number in the range [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of labelSmoothing
   *     correspond to heavier smoothing.
   * @param <T> the data type of the labels
   * @return the smoothed binary labels
   */
  private static <T extends TNumber> Operand<T> smoothBinaryLabels(
      Ops tf, Operand<T> labels, float labelSmoothing) {
    Class<T> labelType = labels.type();
    Operand<T> oneMinusSmoothing = cast(tf, tf.constant(1.f - labelSmoothing), labelType);
    Operand<T> halfSmoothing = cast(tf, tf.constant(0.5F * labelSmoothing), labelType);
    return tf.math.add(tf.math.mul(labels, oneMinusSmoothing), halfSmoothing);
  }

  /**
   * Smooths categorical labels
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param labelSmoothing Float in <code>[0, 1]</code>. When <code>&gt; 0</code>, label values are smoothed, meaning the
   *    confidence on label values are relaxed. e.g. <code>labelSmoothing=0.2<code> means that we will use a
   *    value of </code>0.1<code> for label </code>0<code> and </code>0.9<code> for label </code>1<code>
   * @param <T> the data type of the labels
   * @return the smoothed categorical labels
   */
  private static <T extends TNumber> Operand<T> smoothCategoricalLabels(
      Ops tf, Operand<T> labels, float labelSmoothing) {
    Class<T> labelType = labels.type();
    Operand<T> smoothing = cast(tf, tf.constant(labelSmoothing), labelType);
    Shape labelsShape = labels.shape();
    int numDims = labelsShape.numDimensions();
    Operand<T> numClasses = cast(tf, tf.constant(labelsShape.get(numDims - 1)), labelType);
    Operand<T> oneMinusSmoothing = cast(tf, tf.constant(1.f - labelSmoothing), labelType);
    return tf.math.add(tf.math.mul(labels, oneMinusSmoothing), tf.math.div(smoothing, numClasses));
  }

  // TODO this was tf.math.l2_normalize in TF Python
  /**
   * Normalizes along dimension axis using an L2 norm.
   *
   * @param tf The TensorFlow Ops
   * @param x the input
   * @param axis Dimension along which to normalize.
   * @param <T> the data type for the input and the result
   * @return the normalized values based on L2 norm
   */
  public static <T extends TNumber> Operand<T> l2Normalize(Ops tf, Operand<T> x, int[] axis) {
    Operand<T> squareSum =
        tf.reduceSum(tf.math.square(x), tf.constant(axis), ReduceSum.keepDims(Boolean.TRUE));
    Operand<T> invNorm =
        tf.math.rsqrt(tf.math.maximum(squareSum, cast(tf, tf.constant(1e-12F), x.type())));
    return tf.math.mul(x, invNorm);
  }

  /**
   * Converts binary labels into -1/1.
   *
   * @param tf the TensorFlow Ops
   * @param labels true targets
   * @param <T> the data type of the labels
   * @return the labels, possibly converted into -1/1.
   */
  private static <T extends TNumber> Operand<T> maybeConvertLabels(Ops tf, Operand<T> labels) {
    Class<T> labelType = labels.type();

    Operand<T> one = cast(tf, tf.constant(1), labelType);
    Operand<T> zero = cast(tf, tf.constant(0), labelType);
    Operand<T> two = cast(tf, tf.constant(2), labelType);
    Operand<TBool> areZeros = tf.math.equal(labels, zero);
    Operand<TBool> areOnes = tf.math.equal(labels, one);
    Operand<TBool> isBinary =
        tf.reduceAll(
            tf.math.logicalOr(areZeros, areOnes), tf.constant(-1), ReduceAll.keepDims(true));
    Operand<T> convertBinaryLabels = tf.math.sub(tf.math.mul(two, labels), one);
    return tf.select(isBinary, convertBinaryLabels, labels);
  }

  /**
   * Move the specified axis to end, to be used with transposes
   *
   * @param axis the axis to move
   * @param outputRank the rank of the shape
   * @return the new dimension array with the axis moved to the end.
   */
  private static int[] moveAxisToEnd(int axis, int outputRank) {
    int[] axisNew = new int[outputRank];
    for (int i = 0; i < axis; i++) {
      axisNew[i] = i;
    }
    for (int i = axis + 1; i < outputRank; i++) {
      axisNew[i - 1] = i;
    }
    axisNew[outputRank - 1] = axis;
    return axisNew;
  }
}
