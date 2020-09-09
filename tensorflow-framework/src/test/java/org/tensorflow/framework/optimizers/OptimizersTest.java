package org.tensorflow.framework.optimizers;

import org.junit.jupiter.api.*;
import org.tensorflow.Graph;
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
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.ADADELTA.createOptimizer(graph);
      String expResult = "Adadelta";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAGRAD enum */
  @Test
  public void testADAGRAD() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.ADAGRAD.createOptimizer(graph);
      String expResult = "Adagrad";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAGRAD_DA enum */
  @Test
  public void testADAGRAD_DA() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.ADAGRAD_DA.createOptimizer(graph);
      String expResult = "adagrad-da";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAM enum */
  @Test
  public void testADAM() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.ADAM.createOptimizer(graph);
      String expResult = "Adam";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test ADAMAX enum */
  @Test
  public void testADAMAX() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.ADAMAX.createOptimizer(graph);
      String expResult = "Adamax";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test FTRL enum */
  @Test
  public void testFTRL() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.FTRL.createOptimizer(graph);
      String expResult = "Ftrl";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test NADAM enum */
  @Test
  public void testNADAM() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.NADAM.createOptimizer(graph);
      String expResult = "Nadam";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test RMSPROP enum */
  @Test
  public void testRMSPROP() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.RMSPROP.createOptimizer(graph);
      String expResult = "RMSProp";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test MOMENTUM enum */
  @Test
  public void testMOMENTUM() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.MOMENTUM.createOptimizer(graph);
      String expResult = "Momentum";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }

  /** Test GRADIENT_DESCENT enum */
  @Test
  public void testGRADIENT_DESCENT() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Graph graph = session.getGraph();
      Optimizer instance = Optimizers.GRADIENT_DESCENT.createOptimizer(graph);
      String expResult = "GradientDescent";
      String result = instance.getOptimizerName();
      assertEquals(expResult, result);
    }
  }
}
