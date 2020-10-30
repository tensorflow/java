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

import org.junit.jupiter.api.*;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/** Test the Orthogonal initializer */
public class OrthogonalTest {

  private static final long SEED = 1000L;
  private static final double GAIN_VALUE = 1.0;
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  public OrthogonalTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of call method, of class Orthogonal. */
  @Test
  public void testCallFloat() {
    float[] expected = {
      -0.3097564f,
      -0.11214957f,
      -0.04083291f,
      -0.24071707f,
      0.29931748f,
      0.4461752f,
      -0.16319607f,
      -0.30204326f,
      -0.26093683f,
      0.59770143f,
      0.15418966f,
      0.50748324f,
      -0.03822303f,
      -0.59814125f,
      0.11034431f,
      -0.01813965f,
      -0.21199228f,
      -0.04033701f,
      -0.40765563f,
      -0.36632827f,
      0.10572237f,
      0.27673772f,
      -0.00941799f,
      0.07603773f,
      0.48299354f,
      0.37719437f,
      0.65557724f,
      0.31341612f,
      0.04323304f,
      -0.03049367f,
      -0.00511622f,
      -0.30234647f,
      -0.24784878f,
      -0.27694383f,
      -0.6077379f,
      0.40848815f,
      0.40706915f,
      -0.0732277f,
      -0.16744994f,
      -0.18739915f,
      -0.151793f,
      -0.21273288f,
      0.24265847f,
      -0.00964088f,
      0.25967413f,
      0.40649366f,
      -0.20693113f,
      -0.3185814f,
      0.38828942f,
      -0.5873469f,
      -0.48195702f,
      0.32218578f,
      -0.29953587f,
      0.00851173f,
      0.01569128f,
      -0.33701414f,
      0.36372715f,
      -0.54230285f,
      0.17351612f,
      -0.06162076f,
      -0.2438229f,
      0.35682017f,
      0.7260855f,
      0.24974659f,
      -0.34703425f,
      0.14939374f,
      0.09953088f,
      -0.08766067f,
      -0.25020337f,
      0.02669237f,
      0.41220927f,
      0.4300388f,
      -0.03955907f,
      -0.11728173f,
      -0.2787032f,
      0.26550797f,
      -0.11485924f,
      -0.19093868f,
      0.5791758f,
      0.3107499f,
      -0.46279088f,
      -0.04041088f,
      0.23238355f,
      -0.5590758f,
      -0.07460429f,
      -0.13264497f,
      0.04314278f,
      0.47426552f,
      0.39604855f,
      0.10401782f,
      -0.41256273f,
      0.31454724f,
      -0.45164356f,
      0.33607012f,
      -0.1557368f,
      0.31974515f,
      -0.3645014f,
      0.37268594f,
      -0.00656797f,
      -0.12504758f
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(10, 10);
        Orthogonal<TFloat32> instance = new Orthogonal<>(tf, GAIN_VALUE, SEED);
        Operand<TFloat32> operand = instance.call(tf.constant(shape), TFloat32.class);
        session.evaluate(expected, operand);
      }
  }

  /** Test of call method, of class Orthogonal. */
  @Test
  public void testCallDouble() {
    double[] expected = {
      0.4852166440161694,
      -0.4290733656784607,
      0.09147039077482466,
      -0.3033533647665251,
      -0.13422222791377508,
      -0.3129540993206184,
      0.183062569636896,
      -0.0797586175921162,
      0.36040118003516713,
      -0.4408543207576007,
      -0.2732620793726008,
      -0.031650254737601205,
      -0.487642998274466,
      0.18503560531435395,
      -0.14802624287521232,
      0.11682409149136887,
      0.43002930688897106,
      0.39878194544024825,
      -0.10095741255484561,
      -0.5124333590595215,
      0.18311274512293216,
      0.14142110936521135,
      -0.21143499395594853,
      -0.11919423920003563,
      0.24017159729187723,
      -0.2593437441508134,
      0.667745346902609,
      -0.35315808322575254,
      -0.3404386389145398,
      0.2758862960934774,
      0.07139824569700268,
      0.09448264380916259,
      0.465791321612319,
      0.4543680210644348,
      0.5148494317797793,
      -0.1548002078084767,
      0.2763767527316248,
      0.37222851387188227,
      0.2398314168577794,
      -0.03275882219483219,
      0.19185631817009907,
      0.05900663337192141,
      0.018173647881195746,
      0.37339628154719684,
      0.11377436263350496,
      0.578439238185625,
      0.06494636168027997,
      -0.5948057813239421,
      0.1107116755187609,
      -0.319607142429973,
      0.2155568630609747,
      0.09929282909444799,
      -0.5490811366582051,
      -0.010954009451688201,
      0.11707862431173488,
      0.1617550218319554,
      0.01106019309067251,
      0.14579444371591477,
      0.6518483278305304,
      0.3948536518472629,
      0.2319871561912634,
      -0.18238927225826657,
      0.03282551370311214,
      -0.48208882285440263,
      0.46518806000653323,
      0.5239030340556176,
      -0.02248815414434615,
      0.3216103558486239,
      -0.2874388067830515,
      -0.044661384666030306,
      0.15464707821517193,
      -0.08187337600211494,
      0.3577511581572764,
      0.03953488082715882,
      -0.5961789252666962,
      0.3822951575732457,
      0.4187023892379448,
      0.1923143091248148,
      0.010556064157240419,
      0.35474683982006183,
      0.643204326887452,
      -0.07277000873865974,
      -0.22821669120828425,
      0.45985896233305346,
      -0.11635349685972587,
      -0.12498127959759603,
      -0.2799591321237366,
      0.20319311304196724,
      -0.4071624009218664,
      0.053248119820197976,
      0.2766685450718409,
      0.8528551980781793,
      0.0959402007341447,
      -0.2609469621757593,
      -0.15906257638032784,
      -0.013734816737670838,
      -0.02756903693269743,
      0.12075359886144169,
      0.028705024822326536,
      -0.27774030642345227
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(10, 10);
        Orthogonal<TFloat64> instance = new Orthogonal<>(tf, GAIN_VALUE, SEED);
        Operand<TFloat64> operand = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(expected, operand);
      }
  }

  @Test
  public void testReproducible() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Shape shape = Shape.of(2, 2);

        Orthogonal<TFloat64> instance = new Orthogonal<>(tf, GAIN_VALUE, SEED);
        Operand<TFloat64> operand1 = instance.call(tf.constant(shape), TFloat64.class);
        Operand<TFloat64> operand2 = instance.call(tf.constant(shape), TFloat64.class);
        session.evaluate(operand1, operand2);
      }
  }
}
