/*
 Copyright 2026 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.tensorflow.op.CustomGradient;
import org.tensorflow.op.Ops;
import org.tensorflow.op.RawCustomGradient;
import org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

@DisabledOnOs(OS.WINDOWS)
public class CustomGradientsTest {

  @Test
  public void noGradientNullIsSupported() {
    // Register a custom gradient for an op that has NO native gradient in TF core.
    CustomGradient<SparseSoftmaxCrossEntropyWithLogits.Inputs> grad =
        (tf, op, gradInputs) -> {
          @SuppressWarnings("unchecked")
          Operand<TFloat32> gLoss = (Operand<TFloat32>) gradInputs.get(0); // [B]

          @SuppressWarnings("unchecked")
          Operand<TFloat32> logits = op.features;

          SparseSoftmaxCrossEntropyWithLogits<TFloat32> xent =
              SparseSoftmaxCrossEntropyWithLogits.create(tf.scope(), logits, op.labels);

          Operand<TFloat32> backprop = xent.backprop(); // [B,C]
          Operand<TFloat32> gLossE = tf.expandDims(gLoss, tf.constant(1)); // [B,1]
          Operand<TFloat32> dLogits = tf.math.mul(gLossE, backprop); // [B,C]

          // labels: NoGradient
          return java.util.Arrays.asList(dLogits, null);
        };

    assertTrue(
        TensorFlow.registerCustomGradient(SparseSoftmaxCrossEntropyWithLogits.Inputs.class, grad));

    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      // Small fixed shapes to be able to create an explicit seed (avoid OnesLike in addGradients).
      Operand<TFloat32> logits = tf.constant(new float[][] {{1f, 2f, 3f}, {3f, 2f, 1f}});
      Operand<TInt32> labels = tf.constant(new int[] {2, 0});

      SparseSoftmaxCrossEntropyWithLogits<TFloat32> xent =
          SparseSoftmaxCrossEntropyWithLogits.create(tf.scope(), logits, labels);

      Output<TFloat32> loss = xent.loss(); // [2]
      Operand<TFloat32> seed = tf.constant(new float[] {1f, 1f}); // same shape as loss

      Output<?>[] grads =
          g.addGradients(
              "seed",
              new Output<?>[] {loss},
              new Output<?>[] {logits.asOutput(), labels.asOutput()},
              new Output<?>[] {seed.asOutput()});

      // logits grad exists, labels grad must be "NoGradient" (represented as a CLOSED Output)
      assertNotNull(grads);
      assertEquals(2, grads.length);
      assertNotNull(grads[0], "Expected gradient for logits");
      assertNotNull(grads[1], "Expected an Output placeholder for labels gradient");
      assertTrue(grads[1].isClosed(), "Expected closed gradient (NoGradient) for labels");
    }
  }

  @Test
  public void sigmoidGradHasCustomGradientWithoutOnesLikeSeed() {
    // Register custom gradient for SigmoidGrad (if already registered, it will return false,
    // but the test can still pass because the gradient exists in the current process).
    TensorFlow.registerCustomGradient(
        "SigmoidGrad",
        (RawCustomGradient)
            (tf, op, gradInputs) -> {
              @SuppressWarnings("unchecked")
              Operand<TFloat32> y = (Operand<TFloat32>) op.input(0); // sigmoid(x)
              @SuppressWarnings("unchecked")
              Operand<TFloat32> dy = (Operand<TFloat32>) op.input(1); // upstream into SigmoidGrad
              @SuppressWarnings("unchecked")
              Operand<TFloat32> upstream = (Operand<TFloat32>) gradInputs.get(0);

              Operand<TFloat32> one = tf.constant(1.0f);
              Operand<TFloat32> yTimesOneMinusY = tf.math.mul(y, tf.math.sub(one, y));

              // dL/d(dy) = upstream * y*(1-y)
              Operand<TFloat32> dDy = tf.math.mul(upstream, yTimesOneMinusY);

              // dL/d(y) not needed for this test; return zeros to keep it non-null.
              Operand<TFloat32> dY = tf.zerosLike(y);

              return java.util.Arrays.asList(dY, dDy);
            });

    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Operand<TFloat32> x = tf.placeholder(TFloat32.class);
      Operand<TFloat32> y = tf.math.sigmoid(x);

      // Provide an explicit seed dy to avoid Graph.addGradients defaulting to OnesLike(y)
      Operand<TFloat32> seed = tf.fill(tf.shape(y), tf.constant(1.0f));

      Output<?>[] grads =
          g.addGradients(
              "seed",
              new Output<?>[] {y.asOutput()},
              new Output<?>[] {x.asOutput()},
              new Output<?>[] {seed.asOutput()});

      assertNotNull(grads);
      assertEquals(1, grads.length);
      assertNotNull(grads[0], "Expected a non-null gradient for sigmoid(x) wrt x.");
      assertFalse(grads[0].isClosed(), "Expected an active Output for d(sigmoid)/dx.");
    }
  }
}
