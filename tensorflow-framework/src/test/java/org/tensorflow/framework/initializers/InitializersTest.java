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
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Test the Initializers class */
public class InitializersTest {

  private final TestSession.Mode tfMode = TestSession.Mode.EAGER;

  public InitializersTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_IDENTITY() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.IDENTITY.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof Identity);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_ONES() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.ONES.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof Ones);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_ZEROS() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.ZEROS.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof Zeros);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_GLOROT_NORMAL() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.GLOROT_NORMAL.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof GlorotNormal);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_GLOROT_UNIFORM() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.GLOROT_UNIFORM.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof GlorotUniform);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_ORTHOGONAL() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.ORTHOGONAL.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof Orthogonal);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_RANDOM_NORMAL() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.RANDOM_NORMAL.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof RandomNormal);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_RANDOM_UNIFORM() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.RANDOM_UNIFORM.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof RandomUniform);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_TRUNCATED_NORMAL() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.TRUNCATED_NORMAL.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof TruncatedNormal);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_VARIANCE_SCALING() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.VARIANCE_SCALING.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof VarianceScaling);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_HE_NORMAL() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.HE_NORMAL.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof HeNormal);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_HE_UNIFORM() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.HE_UNIFORM.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof HeUniform);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_LECUN_NORMAL() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.LECUN_NORMAL.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof LeCunNormal);
    }
  }

  /** Test of get method, of class Initializers. */
  @Test
  public void testGet_LECUN_UNIFORM() {

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Initializer result = Initializers.LECUN_UNIFORM.createInitializer(tf);
      assertNotNull(result);
      assertTrue(result instanceof LeCunUniform);
    }
  }
}
