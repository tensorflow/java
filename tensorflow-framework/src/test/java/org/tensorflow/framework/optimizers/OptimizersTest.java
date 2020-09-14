package org.tensorflow.framework.optimizers;

import org.junit.jupiter.api.*;
import org.tensorflow.framework.utils.TestSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimizersTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public OptimizersTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  /** Test ADADELTA enum */
  @Test
  public void testADADELTA() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.ADADELTA.createOptimizer(session.getGraph())) {
      String expResult = "Adadelta";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAGRAD enum */
  @Test
  public void testADAGRAD() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.ADAGRAD.createOptimizer(session.getGraph())) {
      String expResult = "Adagrad";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAGRAD_DA enum */
  @Test
  public void testADAGRAD_DA() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.ADAGRAD_DA.createOptimizer(session.getGraph())) {
      String expResult = "adagrad-da";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAM enum */
  @Test
  public void testADAM() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.ADAM.createOptimizer(session.getGraph())) {
      String expResult = "Adam";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAMAX enum */
  @Test
  public void testADAMAX() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.ADAMAX.createOptimizer(session.getGraph())) {
      String expResult = "Adamax";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test FTRL enum */
  @Test
  public void testFTRL() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.FTRL.createOptimizer(session.getGraph())) {
      String expResult = "Ftrl";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test NADAM enum */
  @Test
  public void testNADAM() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.NADAM.createOptimizer(session.getGraph())) {
      String expResult = "Nadam";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test RMSPROP enum */
  @Test
  public void testRMSPROP() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.RMSPROP.createOptimizer(session.getGraph())) {
      String expResult = "RMSProp";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test MOMENTUM enum */
  @Test
  public void testMOMENTUM() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.MOMENTUM.createOptimizer(session.getGraph())) {
      String expResult = "Momentum";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test GRADIENT_DESCENT enum */
  @Test
  public void testGRADIENT_DESCENT() {
    try (TestSession session = TestSession.createTestSession(tfMode);
        Optimizer instance = Optimizers.GRADIENT_DESCENT.createOptimizer(session.getGraph())) {
      String expResult = "GradientDescent";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }
}
