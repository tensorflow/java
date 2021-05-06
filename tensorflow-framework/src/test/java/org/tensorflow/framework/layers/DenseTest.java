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
package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.constraints.MinMaxNorm;
import org.tensorflow.framework.constraints.NonNeg;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DenseTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  public void testShape3_2() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Shape inputShape = Shape.of(3, 2);
      int units = 3;

      Dense<TFloat32> instance =
          new Dense<>(
              tf, units, 1001L, TFloat32.class, Layer.Options.create().inputShape(inputShape));

      float[][] data = {
        {6.600953f, 4.659476f},
        {6.943807f, 2.113826f},
        {4.667166f, 6.931125f}
      };
      float[][] expected = {
        {5.866010f, 8.906790f, 7.214075f},
        {5.409174f, 8.020676f, 7.272593f},
        {5.140971f, 8.056995f, 5.513147f}
      };

      List<Variable<TFloat32>> weights = instance.getWeights();
      instance.setWeights(weights);
      Operand<TFloat32> input = tf.constant(data);

      Operand<TFloat32> y = instance.call(input, TFloat32.class);
      session.run(tf.init());

      List<Shape> computed = instance.computeOutputShape(Collections.singletonList(inputShape));
      assertEquals(1, computed.size());
      assertEquals(computed.get(0), y.shape());
      Shape expectedOutput = Shape.of(3, units);
      assertEquals(expectedOutput, y.shape());
      session.evaluate(tf.constant(expected), y);
    }
  }

  @Test
  public void testShape4_2() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Shape inputShape = Shape.of(4, 2);
      int units = 3;

      Dense<TFloat32> instance =
          new Dense<>(
              tf, units, 1001L, TFloat32.class, Layer.Options.create().inputShape(inputShape));

      float[][] inputArray = {
        {6.600953f, 4.659476f},
        {6.943807f, 2.113826f},
        {4.667166f, 6.931125f},
        {7.716860f, 3.205337f}
      };

      List<Variable<TFloat32>> weights = instance.getWeights();
      instance.setWeights(weights);
      Operand<TFloat32> input = tf.reshape(tf.constant(inputArray), tf.constant(inputShape));

      Operand<TFloat32> y = instance.call(input, TFloat32.class);
      session.run(tf.init());
      List<Shape> computedShapes =
          instance.computeOutputShape(Collections.singletonList(input.shape()));
      assertFalse(computedShapes.isEmpty());
      Shape computedShape = computedShapes.get(0);
      Shape expectedShape = Shape.of(4, units);
      assertEquals(expectedShape, computedShape);
      assertEquals(expectedShape, y.shape());

      float[][] expected = {
        {5.866010f, 8.906790f, 7.214075f},
        {5.409174f, 8.020676f, 7.272593f},
        {5.140971f, 8.056996f, 5.513148f},
        {6.245262f, 9.327854f, 8.179358f}
      };

      session.evaluate(tf.constant(expected), y);
    }
  }

  @Test
  public void testShapeN_N_2() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Shape inputShape = Shape.of(Shape.UNKNOWN_SIZE, Shape.UNKNOWN_SIZE, 2);
      int units = 3;

      Dense<TFloat32> instance =
          new Dense<>(
              tf, units, 1001L, TFloat32.class, Layer.Options.create().inputShape(inputShape));

      Shape fullShape = Shape.of(5, 10, 2);
      float[][][] data = {
        {
          {6.600953f, 4.659476f},
          {6.943807f, 2.113826f},
          {4.667166f, 6.931125f},
          {7.716860f, 3.205337f},
          {8.066205f, 2.362994f},
          {0.686355f, 8.934626f},
          {1.293296f, 9.073912f},
          {4.554000f, 0.347209f},
          {6.760708f, 8.464749f},
          {9.203295f, 6.147404f}
        },
        {
          {7.022987f, 3.022041f},
          {0.175645f, 7.057390f},
          {4.537057f, 3.270523f},
          {5.694380f, 0.481678f},
          {1.267088f, 4.573346f},
          {7.239103f, 2.671200f},
          {4.631621f, 1.366283f},
          {4.380660f, 0.902928f},
          {7.663558f, 8.725193f},
          {4.102549f, 2.243720f}
        },
        {
          {0.251945f, 1.804798f},
          {5.300526f, 7.791917f},
          {-0.071388f, 9.458032f},
          {7.492148f, 1.584492f},
          {6.854610f, 2.461785f},
          {4.187295f, 3.974617f},
          {-0.015711f, 1.355883f},
          {1.855492f, 7.734279f},
          {3.403170f, 7.473061f},
          {4.243813f, 6.584970f}
        },
        {
          {1.645227f, 0.730085f},
          {3.999032f, 5.628812f},
          {5.522727f, 3.001995f},
          {2.459637f, 9.221226f},
          {0.305633f, 9.156766f},
          {8.218584f, 7.329232f},
          {2.657161f, 3.237010f},
          {3.008971f, 7.147655f},
          {2.788105f, 2.895133f},
          {2.805755f, 3.646185f}
        },
        {
          {2.086996f, 5.481725f},
          {4.222548f, 4.396897f},
          {1.799221f, 7.522835f},
          {3.549520f, 9.244308f},
          {4.980303f, 0.475735f},
          {3.644282f, 0.544247f},
          {6.282454f, 8.306262f},
          {3.650939f, 1.386086f},
          {3.526051f, 1.671946f},
          {7.763572f, 6.653723f}
        },
      };

      float[][][] expected = {
        {
          {5.866010f, 8.906790f, 7.214075f},
          {5.409174f, 8.020676f, 7.272593f},
          {5.140971f, 8.056995f, 5.513147f},
          {6.245262f, 9.327854f, 8.179358f},
          {6.258242f, 9.272379f, 8.437642f},
          {2.918294f, 5.014478f, 1.708536f},
          {3.378673f, 5.693542f, 2.339058f},
          {3.263673f, 4.757503f, 4.651771f},
          {7.016673f, 10.908866f, 7.807479f},
          {8.083269f, 12.249319f, 10.018546f}
        },
        {
          {5.712371f, 8.539888f, 7.455799f},
          {2.050113f, 3.591536f, 0.978359f},
          {4.050456f, 6.154792f, 4.966178f},
          {4.093921f, 5.971835f, 5.822024f},
          {2.131000f, 3.489655f, 1.802051f},
          {5.766911f, 8.587945f, 7.634893f},
          {3.596069f, 5.328780f, 4.845973f},
          {3.294865f, 4.851680f, 4.539239f},
          {7.716053f, 11.944765f, 8.751445f},
          {3.467615f, 5.220104f, 4.409636f}
        },
        {
          {0.668335f, 1.127111f, 0.459879f},
          {5.816830f, 9.111765f, 6.252261f},
          {2.534012f, 4.504061f, 1.000444f},
          {5.646128f, 8.317189f, 7.767926f},
          {5.442162f, 8.099133f, 7.221718f},
          {3.999421f, 6.142959f, 4.691792f},
          {0.359459f, 0.640173f, 0.137874f},
          {3.403915f, 5.611978f, 2.756518f},
          {4.409483f, 7.045343f, 4.294413f},
          {4.751827f, 7.462863f, 5.045105f}
        },
        {
          {1.344244f, 2.011289f, 1.749129f},
          {4.320304f, 6.753564f, 4.688737f},
          {4.662963f, 7.018229f, 5.934030f},
          {4.230494f, 6.940253f, 3.537062f},
          {2.714058f, 4.738264f, 1.348129f},
          {7.720919f, 11.828723f, 9.155255f},
          {2.733208f, 4.244021f, 3.058378f},
          {4.046294f, 6.490631f, 3.858251f},
          {2.730931f, 4.210578f, 3.152225f},
          {2.948380f, 4.591742f, 3.255287f}
        },
        {
          {2.949665f, 4.755452f, 2.735502f},
          {4.139306f, 6.382794f, 4.775392f},
          {3.306999f, 5.452967f, 2.675543f},
          {4.995176f, 8.049803f, 4.643537f},
          {3.595419f, 5.249314f, 5.098118f},
          {2.684487f, 3.936022f, 3.752738f},
          {6.640594f, 10.350204f, 7.305118f},
          {2.919088f, 4.350031f, 3.854963f},
          {2.910276f, 4.362474f, 3.760896f},
          {7.219775f, 11.043336f, 8.617791f}
        }
      };

      List<Variable<TFloat32>> weights = instance.getWeights();
      instance.setWeights(weights);
      Operand<TFloat32> input = tf.constant(data);

      Operand<TFloat32> y = instance.call(input, TFloat32.class);
      session.run(tf.init());

      List<Shape> computed = instance.computeOutputShape(Collections.singletonList(fullShape));
      assertEquals(1, computed.size());
      assertEquals(computed.get(0), y.shape());
      Shape expectedOutput = Shape.of(5, 10, units);
      assertEquals(expectedOutput, y.shape());
      session.evaluate(tf.constant(expected), y);
    }
  }

  @Test
  public void testShape3_4_5_2() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      int units = 3;

      Shape inputShape = Shape.of(3, 4, 5, 2);

      Dense<TFloat32> instance =
          new Dense<>(
              tf, units, 1001L, TFloat32.class, Layer.Options.create().inputShape(inputShape));
      assertEquals("Dense", instance.getName());
      session.run(tf.init());

      float[][][][] data = {
        {
          {
            {6.600953f, 4.659476f},
            {6.943807f, 2.113826f},
            {4.667166f, 6.931125f},
            {7.716860f, 3.205337f},
            {8.066205f, 2.362994f},
          },
          {
            {0.686355f, 8.934626f},
            {1.293296f, 9.073912f},
            {4.554000f, 0.347209f},
            {6.760708f, 8.464749f},
            {9.203295f, 6.147404f},
          },
          {
            {7.022987f, 3.022041f},
            {0.175645f, 7.057390f},
            {4.537057f, 3.270523f},
            {5.694380f, 0.481678f},
            {1.267088f, 4.573346f},
          },
          {
            {7.239103f, 2.671200f},
            {4.631621f, 1.366283f},
            {4.380660f, 0.902928f},
            {7.663558f, 8.725193f},
            {4.102549f, 2.243720f},
          },
        },
        {
          {
            {0.251945f, 1.804798f},
            {5.300526f, 7.791917f},
            {-0.071388f, 9.458032f},
            {7.492148f, 1.584492f},
            {6.854610f, 2.461785f},
          },
          {
            {4.187295f, 3.974617f},
            {-0.015711f, 1.355883f},
            {1.855492f, 7.734279f},
            {3.403170f, 7.473061f},
            {4.243813f, 6.584970f},
          },
          {
            {1.645227f, 0.730085f},
            {3.999032f, 5.628812f},
            {5.522727f, 3.001995f},
            {2.459637f, 9.221226f},
            {0.305633f, 9.156766f},
          },
          {
            {8.218584f, 7.329232f},
            {2.657161f, 3.237010f},
            {3.008971f, 7.147655f},
            {2.788105f, 2.895133f},
            {2.805755f, 3.646185f},
          },
        },
        {
          {
            {2.086996f, 5.481725f},
            {4.222548f, 4.396897f},
            {1.799221f, 7.522835f},
            {3.549520f, 9.244308f},
            {4.980303f, 0.475735f},
          },
          {
            {3.644282f, 0.544247f},
            {6.282454f, 8.306262f},
            {3.650939f, 1.386086f},
            {3.526051f, 1.671946f},
            {7.763572f, 6.653723f},
          },
          {
            {2.367239f, 3.317834f},
            {2.330428f, 9.358873f},
            {3.638705f, 5.096712f},
            {9.156695f, 4.436713f},
            {-0.416358f, 8.118915f},
          },
          {
            {6.330701f, 6.326071f},
            {4.724874f, -0.368026f},
            {3.975863f, 0.017570f},
            {3.545376f, 7.946171f},
            {-0.495031f, 7.853283f},
          }
        }
      };

      float[][][][] expected = {
        {
          {
            {5.866010f, 8.906790f, 7.214075f},
            {5.409174f, 8.020676f, 7.272593f},
            {5.140971f, 8.056995f, 5.513147f},
            {6.245262f, 9.327854f, 8.179358f},
            {6.258242f, 9.272379f, 8.437642f},
          },
          {
            {2.918294f, 5.014478f, 1.708536f},
            {3.378673f, 5.693542f, 2.339058f},
            {3.263673f, 4.757503f, 4.651771f},
            {7.016673f, 10.908866f, 7.807479f},
            {8.083269f, 12.249319f, 10.018546f},
          },
          {
            {5.712371f, 8.539888f, 7.455799f},
            {2.050113f, 3.591536f, 0.978359f},
            {4.050456f, 6.154792f, 4.966178f},
            {4.093921f, 5.971835f, 5.822024f},
            {2.131000f, 3.489655f, 1.802051f},
          },
          {
            {5.766911f, 8.587945f, 7.634893f},
            {3.596069f, 5.328780f, 4.845973f},
            {3.294865f, 4.851680f, 4.539239f},
            {7.716053f, 11.944765f, 8.751445f},
            {3.467615f, 5.220104f, 4.409636f},
          }
        },
        {
          {
            {0.668335f, 1.127111f, 0.459879f},
            {5.816830f, 9.111765f, 6.252261f},
            {2.534012f, 4.504061f, 1.000444f},
            {5.646128f, 8.317189f, 7.767926f},
            {5.442162f, 8.099133f, 7.221718f},
          },
          {
            {3.999421f, 6.142959f, 4.691792f},
            {0.359459f, 0.640173f, 0.137874f},
            {3.403915f, 5.611978f, 2.756518f},
            {4.409483f, 7.045343f, 4.294413f},
            {4.751827f, 7.462863f, 5.045105f},
          },
          {
            {1.344244f, 2.011289f, 1.749129f},
            {4.320304f, 6.753564f, 4.688737f},
            {4.662963f, 7.018229f, 5.934030f},
            {4.230494f, 6.940253f, 3.537062f},
            {2.714058f, 4.738264f, 1.348129f},
          },
          {
            {7.720919f, 11.828723f, 9.155255f},
            {2.733208f, 4.244021f, 3.058378f},
            {4.046294f, 6.490631f, 3.858251f},
            {2.730931f, 4.210578f, 3.152225f},
            {2.948380f, 4.591742f, 3.255287f},
          }
        },
        {
          {
            {2.949665f, 4.755452f, 2.735502f},
            {4.139306f, 6.382794f, 4.775392f},
            {3.306999f, 5.452967f, 2.675543f},
            {4.995176f, 8.049803f, 4.643537f},
            {3.595419f, 5.249314f, 5.098118f},
          },
          {
            {2.684487f, 3.936022f, 3.752738f},
            {6.640594f, 10.350204f, 7.305118f},
            {2.919088f, 4.350031f, 3.854963f},
            {2.910276f, 4.362474f, 3.760896f},
            {7.219775f, 11.043336f, 8.617791f},
          },
          {
            {2.553549f, 3.990942f, 2.773906f},
            {4.178188f, 6.876633f, 3.421808f},
            {3.924220f, 6.132985f, 4.263438f},
            {7.583528f, 11.374685f, 9.777319f},
            {1.928159f, 3.508506f, 0.499166f},
          },
          {
            {6.133229f, 9.440765f, 7.129385f},
            {3.187190f, 4.583663f, 4.743713f},
            {2.771337f, 4.015369f, 4.028832f},
            {4.637676f, 7.417559f, 4.492103f},
            {1.800851f, 3.300701f, 0.389355f},
          }
        }
      };

      List<Variable<TFloat32>> weights = instance.getWeights();
      instance.setWeights(weights);
      Operand<TFloat32> input = tf.constant(data);

      Operand<TFloat32> y = instance.call(input, TFloat32.class);
      session.run(tf.init());

      List<Shape> computed = instance.computeOutputShape(Collections.singletonList(inputShape));
      assertEquals(1, computed.size());
      assertEquals(computed.get(0), y.shape());
      Shape expectedOutput = Shape.of(3, 4, 5, units);
      assertEquals(expectedOutput, y.shape());
      session.evaluate(tf.constant(expected), y);
    }
  }

  @Test
  public void testConstraintsNonNeg() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Shape inputShape = Shape.of(3, 2);
      int units = 3;

      NonNeg nonNeg = new NonNeg(tf);

      Dense<TFloat32> instance =
          new Dense<>(
              tf,
              "constraintTest",
              units,
              null,
              true,
              null,
              null,
              null,
              null,
              null,
              nonNeg::call,
              nonNeg::call,
              1001L,
              TFloat32.class,
              Layer.Options.create().inputShape(inputShape));

      float[][] data = {
        {6.600953f, 4.659476f},
        {6.943807f, 2.113826f},
        {4.667166f, 6.931125f}
      };

      float[][] constraintInput = {
        {-1, 2, 5},
        {-2, 4, -4}
      };
      float[][] constraintExpected = {
        {-0, 2, 5},
        {-0, 4, -0}
      };

      float[] biasConstraintInput = {-1, 2, 5};
      float[] biasConstraintExpected = {-0, 2, 5};

      Operand<TFloat32> input = tf.constant(data);

      @SuppressWarnings("unused")
      Operand<TFloat32> y = instance.call(input, TFloat32.class);
      // initialize variables
      session.run(tf.init());

      List<Variable<TFloat32>> weights = instance.getWeights();
      instance.setWeights(weights);

      // Test kernel
      Variable<TFloat32> kernel = instance.getKernel();
      Operand<TFloat32> varUpdate = instance.assign(kernel, tf.constant(constraintInput));
      session.run(varUpdate);
      session.evaluate(tf.constant(constraintExpected), instance.applyConstraint(kernel));

      // test bias
      Variable<TFloat32> bias = instance.getBias();
      assertEquals(Shape.of(units), bias.shape());
      varUpdate = instance.assignAdd(bias, tf.constant(biasConstraintInput));
      session.run(varUpdate);
      session.evaluate(tf.constant(biasConstraintExpected), instance.applyConstraint(bias));
    }
  }

  @Test
  public void testConstraintsMinMaxNorm() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();

      Shape inputShape = Shape.of(3, 2);
      int units = 3;

      MinMaxNorm minMaxNorm = new MinMaxNorm(tf);

      Dense<TFloat32> instance =
          new Dense<>(
              tf,
              "constraintTest",
              units,
              null,
              true,
              null,
              null,
              null,
              null,
              null,
              minMaxNorm::call,
              minMaxNorm::call,
              1001L,
              TFloat32.class,
              Layer.Options.create().inputShape(inputShape));

      float[][] data = {
        {6.600953f, 4.659476f},
        {6.943807f, 2.113826f},
        {4.667166f, 6.931125f}
      };

      float[][] constraintInput = {
        {1, 0.5f, 2},
        {-2, 0.75f, 0}
      };
      float[][] constraintExpected = {
        {0.447214f, 0.5f, 1},
        {-0.894427f, 0.75f, 0}
      };

      float[] biasConstraintInput = {-1, 2, 5};
      float[] biasConstraintExpected = {-0.182574f, 0.365148f, 0.912871f};

      Operand<TFloat32> input = tf.constant(data);

      @SuppressWarnings("unused")
      Operand<TFloat32> y = instance.call(input, TFloat32.class);
      // initialize variables
      session.run(tf.init());

      List<Variable<TFloat32>> weights = instance.getWeights();
      instance.setWeights(weights);

      // Test kernel
      Variable<TFloat32> kernel = instance.getKernel();
      Operand<TFloat32> varUpdate = instance.assign(kernel, tf.constant(constraintInput));
      session.run(varUpdate);
      session.evaluate(tf.constant(constraintExpected), instance.applyConstraint(kernel));

      // test bias
      Variable<TFloat32> bias = instance.getBias();
      assertEquals(Shape.of(units), bias.shape());

      varUpdate = instance.assignAdd(bias, tf.constant(biasConstraintInput));
      session.run(varUpdate);
      session.evaluate(tf.constant(biasConstraintExpected), instance.applyConstraint(bias));
    }
  }
}
