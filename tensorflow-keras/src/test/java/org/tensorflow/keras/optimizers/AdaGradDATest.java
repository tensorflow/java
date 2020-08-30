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
package org.tensorflow.keras.optimizers;

import org.junit.jupiter.api.*;
import org.tensorflow.framework.optimizers.Optimizer;
import org.tensorflow.keras.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tensorflow.keras.optimizers.AdaGradDA.INITIAL_ACCUM_KEY;
import static org.tensorflow.keras.optimizers.AdaGradDA.LEARNING_RATE_KEY;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;

/** Test cases for AdaGradDA Optimizer */
public class AdaGradDATest {

  private TestSession.Mode tf_mode = TestSession.Mode.GRAPH;

  int index;

  public AdaGradDATest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of create method, of class AdaGradDA. */
  @Test
  public void testCreate() {
    try (TestSession testSession = TestSession.createTestSession(tf_mode)) {
      Ops tf = testSession.getTF();
      Map<String, Object> config = new HashMap<>();
      config.put(NAME_KEY, "AdaDelta");
      config.put(LEARNING_RATE_KEY, 2.0F);
      config.put(INITIAL_ACCUM_KEY, 0.1F);
      AdaGradDA expResult = new AdaGradDA(tf, 2.0F, 0.1F, 0.0F, 0.0F);
      AdaGradDA result = AdaGradDA.create(tf, config);
      assertEquals(expResult.getConfig(), result.getConfig());
    }
  }

  @Test
  public void testBasic() {
    float[] var0_init = {0.0F, 0.0F};
    float[] var1_init = {0.0F, 0.0F};
    float[] grads0_init = {0.1F, 0.2F};
    float[] grads1_init = {0.01F, 0.02F};
    float epsilon = 1e-8F;
    float epsilon1 = 1e-5F;
    try (TestSession session = TestSession.createTestSession(tf_mode)) {
      Ops tf = session.getTF();

      Shape shape0 = Shape.of(var0_init.length);
      Shape shape1 = Shape.of(var1_init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0_init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1_init));

      Constant<TFloat32> grads0 = tf.constant(grads0_init);
      Constant<TFloat32> grads1 = tf.constant(grads1_init);

      /* initialize the local variables */
      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      float learningRate = 3.0F;

      AdaGrad instance = new AdaGrad(tf, learningRate);

      /* build the GradsAnvVars */
      List gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op ada_update = instance.applyGradients(gradsAndVars, "AdGradDATest");

      /** initialize the accumulators */
      session.run(tf.init());

      session.evaluate(var0_init, var0);
      session.evaluate(var1_init, var1);
      session.run(ada_update, instance.getFeedDict());
      float[] expected0 = {-0.904534F, -1.603567F};
      session.evaluate(expected0, var0);
      float[] expected1 = {-0.094821f, -0.189358f};
      session.evaluate(expected1, var1);
    }
  }
}
