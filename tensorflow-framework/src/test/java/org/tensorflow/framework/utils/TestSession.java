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
package org.tensorflow.framework.utils;

import org.tensorflow.EagerSession;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/** Base class for Test Session */
public abstract class TestSession implements AutoCloseable {

  protected static final Map<
          Class<? extends TType>, TriConsumer<PrintWriter, long[], NdArray<? extends TType>>>
      printMap =
          new HashMap<
              Class<? extends TType>,
              TriConsumer<PrintWriter, long[], NdArray<? extends TType>>>() {
            {
              put(
                  TUint8.class,
                  (writer, idx, o) ->
                      writer.printf(
                          "%s. %s\n", Arrays.toString(idx), ((Number) o.getObject()).byteValue()));
              put(
                  TInt32.class,
                  (writer, idx, o) ->
                      writer.printf(
                          "%s. %d\n", Arrays.toString(idx), ((Number) o.getObject()).intValue()));
              put(
                  TInt64.class,
                  (writer, idx, o) ->
                      writer.printf(
                          "%s. %d\n", Arrays.toString(idx), ((Number) o.getObject()).longValue()));
              put(
                  TFloat32.class,
                  (writer, idx, o) ->
                      writer.printf(
                          "%s. %f\n", Arrays.toString(idx), ((Number) o.getObject()).floatValue()));
              put(
                  TFloat64.class,
                  (writer, idx, o) ->
                      writer.printf(
                          "%s. %f\n",
                          Arrays.toString(idx), ((Number) o.getObject()).doubleValue()));
              put(
                  TBfloat16.class,
                  (writer, idx, o) ->
                      writer.printf(
                          "%s. %f\n", Arrays.toString(idx), ((Number) o.getObject()).floatValue()));
              put(
                  TFloat16.class,
                  (writer, idx, o) ->
                      writer.printf(
                          "%s. %f\n", Arrays.toString(idx), ((Number) o.getObject()).floatValue()));
              put(
                  TBool.class,
                  (writer, idx, o) ->
                      writer.printf("%s. %b\n", Arrays.toString(idx), o.getObject()));
              put(
                  TString.class,
                  (writer, idx, o) ->
                      writer.printf("%s. %s\n", Arrays.toString(idx), o.getObject()));
            }
          };
  protected static final Map<
          Class<? extends TType>,
          QuadConsumer<PrintWriter, long[], Predicate<Number>, NdArray<? extends TType>>>
      printPredicate =
          new HashMap<
              Class<? extends TType>,
              QuadConsumer<PrintWriter, long[], Predicate<Number>, NdArray<? extends TType>>>() {
            {
              put(
                  TUint8.class,
                  (writer, idx, predicate, o) ->
                      writer.printf(
                          "%s. %b <==> %d\n",
                          Arrays.toString(idx),
                          predicate.test(((Number) o.getObject()).byteValue()),
                          ((Number) o.getObject()).byteValue()));
              put(
                  TInt32.class,
                  (writer, idx, predicate, o) ->
                      writer.printf(
                          "%s. %b <==> %d\n",
                          Arrays.toString(idx),
                          predicate.test(((Number) o.getObject()).intValue()),
                          ((Number) o.getObject()).intValue()));
              put(
                  TInt64.class,
                  (writer, idx, predicate, o) ->
                      writer.printf(
                          "%s. %b <==> %d\n",
                          Arrays.toString(idx),
                          predicate.test(((Number) o.getObject()).longValue()),
                          ((Number) o.getObject()).longValue()));
              put(
                  TFloat32.class,
                  (writer, idx, predicate, o) ->
                      writer.printf(
                          "%s. %b <==> %f\n",
                          Arrays.toString(idx),
                          predicate.test(((Number) o.getObject()).floatValue()),
                          ((Number) o.getObject()).floatValue()));
              put(
                  TFloat64.class,
                  (writer, idx, predicate, o) ->
                      writer.printf(
                          "%s. %b <==> %f\n",
                          Arrays.toString(idx),
                          predicate.test(((Number) o.getObject()).doubleValue()),
                          ((Number) o.getObject()).doubleValue()));
              put(
                  TBfloat16.class,
                  (writer, idx, predicate, o) ->
                      writer.printf(
                          "%s. %b <==> %f\n",
                          Arrays.toString(idx),
                          predicate.test(((Number) o.getObject()).floatValue()),
                          ((Number) o.getObject()).floatValue()));
              put(
                  TFloat16.class,
                  (writer, idx, predicate, o) ->
                      writer.printf(
                          "%s. %b <==> %f\n",
                          Arrays.toString(idx),
                          predicate.test(((Number) o.getObject()).floatValue()),
                          ((Number) o.getObject()).floatValue()));
            }
          };
  protected static final Map<
          Class<? extends TType>, BiConsumer<Predicate<Number>, NdArray<? extends TType>>>
      evalPredicate =
          new HashMap<
              Class<? extends TType>, BiConsumer<Predicate<Number>, NdArray<? extends TType>>>() {
            {
              put(
                  TUint8.class,
                  (predicate, o) ->
                      assertTrue(predicate.test(((Number) o.getObject()).byteValue())));
              put(
                  TInt32.class,
                  (predicate, o) ->
                      assertTrue(predicate.test(((Number) o.getObject()).intValue())));
              put(
                  TInt64.class,
                  (predicate, o) ->
                      assertTrue(predicate.test(((Number) o.getObject()).longValue())));
              put(
                  TFloat32.class,
                  (predicate, o) ->
                      assertTrue(predicate.test(((Number) o.getObject()).floatValue())));
              put(
                  TFloat64.class,
                  (predicate, o) ->
                      assertTrue(predicate.test(((Number) o.getObject()).doubleValue())));
              put(
                  TBfloat16.class,
                  (predicate, o) ->
                      assertTrue(predicate.test(((Number) o.getObject()).floatValue())));
              put(
                  TFloat16.class,
                  (predicate, o) ->
                      assertTrue(predicate.test(((Number) o.getObject()).floatValue())));
            }
          };
  private static final long[] ZERO_IDX = new long[0];
  private static final PrintWriter DEFAULT_WRITER = new PrintWriter(System.out);
  protected static float epsilon = 1e-5F;
  protected static final Map<Class<? extends TType>, BiConsumer<Object, NdArray<? extends TType>>>
      evalMap =
          new HashMap<Class<? extends TType>, BiConsumer<Object, NdArray<? extends TType>>>() {
            {
              put(
                  TUint8.class,
                  (expected, o) ->
                      assertEquals(
                          ((Number) expected).byteValue(), ((Number) o.getObject()).byteValue()));
              put(
                  TInt32.class,
                  (expected, o) ->
                      assertEquals(
                          ((Number) expected).intValue(), ((Number) o.getObject()).intValue()));
              put(
                  TInt64.class,
                  (expected, o) ->
                      assertEquals(
                          ((Number) expected).longValue(), ((Number) o.getObject()).longValue()));
              put(
                  TFloat32.class,
                  (expected, o) ->
                      assertEquals(
                          ((Number) expected).floatValue(),
                          ((Number) o.getObject()).floatValue(),
                          epsilon));
              put(
                  TFloat64.class,
                  (expected, o) ->
                      assertEquals(
                          ((Number) expected).doubleValue(),
                          ((Number) o.getObject()).doubleValue(),
                          epsilon));
              put(
                  TBfloat16.class,
                  (expected, o) ->
                      assertEquals(
                          ((Number) expected).floatValue(),
                          ((Number) o.getObject()).floatValue(),
                          epsilon));
              put(
                  TFloat16.class,
                  (expected, o) ->
                      assertEquals(
                          ((Number) expected).floatValue(),
                          ((Number) o.getObject()).floatValue(),
                          epsilon));
              put(TBool.class, (expected, o) -> assertEquals(expected, o.getObject()));
              put(TString.class, (expected, o) -> assertEquals(expected, o.getObject().toString()));
            }
          };
  protected boolean debug;

  /**
   * Creates an Eager Test Session
   *
   * @return the Eager Test Session
   */
  public static TestSession createEagerSession() {
    return new EagerTestSession();
  }

  /**
   * Creates a Graph Test Session
   *
   * @return the Graph Test Session
   */
  public static TestSession createGraphSession() {
    return new GraphTestSession();
  }

  /**
   * Creates a Graph Test Session without creating its own graph
   *
   * @param graph the graph
   * @param session the session
   * @param tf the TensorFlow Ops
   * @return the Graph Test Session
   */
  public static TestSession createGraphSession(Graph graph, Session session, Ops tf) {
    return new GraphTestSession(graph, session, tf);
  }

  /**
   * Creates a Test Session
   *
   * @param mode the type of Session, either eager or graph
   * @return returns the test session
   */
  public static TestSession createTestSession(Mode mode) {
    return mode == Mode.EAGER ? createEagerSession() : createGraphSession();
  }

  /**
   * Get the epsilon value for evaluating float values
   *
   * @return the epsilon value for evaluating float values
   */
  public static float getEpsilon() {
    return epsilon;
  }

  /**
   * Set the epsilon value for evaluating float values
   *
   * @param epsilonValue the epsilon value for evaluating float values
   */
  public static void setEpsilon(float epsilonValue) {
    epsilon = epsilonValue;
  }

  /** Initializes the Test Session, default implementation is do nothing. */
  public void initialize() {
    // empty
  }

  /**
   * Runs the Operation, in EagerMode this does nothing
   *
   * @param op the Operation to run.
   */
  @SuppressWarnings("unused")
  public void run(Op op) {
    run(op, null);
  }

  /**
   * Runs the Operation, in EagerMode this does nothing
   *
   * @param op the Operation to run.
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   */
  @SuppressWarnings("unused")
  public void run(Op op, Map<Operand<? extends TType>, Tensor> feedMap) {
    // empty
  }

  /**
   * Gets the Graph
   *
   * @return the graph if in Graph Mode, otherwise null.
   */
  public Graph getGraph() {
    return null;
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(Number expected, Operand<T> input) {
    evaluate(new Number[] {expected}, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      Number expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    evaluate(new Number[] {expected}, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(byte expected, Operand<T> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      byte expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    evaluate((double) expected, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(int expected, Operand<T> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      int expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    evaluate((double) expected, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(long expected, Operand<T> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      long expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    evaluate((double) expected, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(float expected, Operand<T> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      float expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    evaluate((double) expected, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(double expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TNumber> void evaluate(
      double expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(byte[] expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      byte[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    Byte[] iArray = new Byte[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(int[] expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      int[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    Integer[] iArray = new Integer[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(long[] expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      long[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    Long[] iArray = new Long[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input, feedMap);
  }

  /**
   * Evaluates the input against the expected values
   *
   * @param expected the expected values
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(float[] expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected values
   *
   * @param expected the expected values
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      float[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    Float[] iArray = new Float[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(double[] expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(
      double[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    Double[] iArray = new Double[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(Number[] expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TNumber> void evaluate(
      Number[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(String expected, Operand<TString> input) {
    evaluate(new String[] {expected}, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(
      String expected, Operand<TString> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    evaluate(new String[] {expected}, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(String[] expected, Operand<TString> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract void evaluate(
      String[] expected, Operand<TString> input, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Boolean expected, Operand<TBool> input) {
    evaluate(new Boolean[] {expected}, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(
      Boolean expected, Operand<TBool> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    evaluate(new Boolean[] {expected}, input, feedMap);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Boolean[] expected, Operand<TBool> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract void evaluate(
      Boolean[] expected, Operand<TBool> input, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TType> void evaluate(Operand<T> expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TType> void evaluate(
      Operand<T> expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TType> void evaluate(FloatNdArray expected, Operand<T> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TType> void evaluate(
      FloatNdArray expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TType> void evaluate(Operand<T> input, Predicate<Number> predicate) {
    evaluate(input, predicate, null);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TType> void evaluate(
      Operand<T> input, Predicate<Number> predicate, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected string value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   */
  public void evaluateString(Operand<TString> input, Predicate<String> predicate) {
    evaluateString(input, predicate, null);
  }

  /**
   * Evaluates the input against the expected string value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract void evaluateString(
      Operand<TString> input,
      Predicate<String> predicate,
      Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Evaluates the input against the expected value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(FloatNdArray input, Predicate<Number> predicate) {
    input.scalars().forEach(f -> assertTrue(predicate.test(f.getFloat())));
  }

  /**
   * Prints the input's values to standard out
   *
   * @param input the operand to print
   * @param <T> the data type of the input
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public <T extends TType> void print(Operand<T> input) {
    print(new PrintWriter(new OutputStreamWriter(System.out)), input, null);
  }

  /**
   * Prints the input's values to standard out
   *
   * @param input the operand to print
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public <T extends TType> void print(
      Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    print(new PrintWriter(new OutputStreamWriter(System.out)), input, feedMap);
  }

  /**
   * Prints the input's values to the output stream
   *
   * @param out the output stream
   * @param input the operand to print
   * @param <T> the data type of the input
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public <T extends TType> void print(OutputStream out, Operand<T> input) {
    print(new PrintWriter(new OutputStreamWriter(out)), input, null);
  }

  /**
   * Prints the input's values to the output stream
   *
   * @param out the output stream
   * @param input the operand to print
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public <T extends TType> void print(
      OutputStream out, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    print(new PrintWriter(new OutputStreamWriter(out)), input, feedMap);
  }

  /**
   * Prints the input's values to the PrintWriter
   *
   * @param writer the output writer
   * @param input the op to print
   * @param <T> the data type of the input
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public <T extends TType> void print(Writer writer, Operand<T> input) {
    print(new PrintWriter(writer), input, null);
  }

  /**
   * Prints the input's values to the PrintWriter
   *
   * @param writer the output writer
   * @param input the op to print
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @param <T> the data type of the input
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public <T extends TType> void print(
      Writer writer, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    print(new PrintWriter(writer), input, feedMap);
  }

  /**
   * Prints the input's values to the PrintWriter
   *
   * @param writer the output writer
   * @param input the op to print
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public <T extends TType> void print(PrintWriter writer, Operand<T> input) {
    print(writer, input, null);
  }

  /**
   * Prints the input's values to the PrintWriter
   *
   * @param writer the output writer
   * @param input the op to print
   * @param feedMap a optional Map to feed to the run session when placeholders are used.
   * @throws IllegalArgumentException if the data type for the input does not have a print function
   *     registered.
   */
  public abstract <T extends TType> void print(
      PrintWriter writer, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap);

  /**
   * Get the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  public abstract Ops getTF();

  /**
   * Determine if this Test Session represents an Eager Session
   *
   * @return true, if this Test Session represents an Eager Session
   */
  public abstract boolean isEager();

  /**
   * Determine if this Test Session represents a Graph Session
   *
   * @return true, if this Test Session represents a Graph Session
   */
  public boolean isGraph() {
    return !isEager();
  }

  /**
   * Get the TensorFlow session object associated with this Test Session
   *
   * @return a TensorFlow session if this is a Graph session, otherwise null
   */
  public abstract Session getGraphSession();

  /**
   * Get the TensorFlow eager session object associated with this Test Session
   *
   * @return a TensorFlow session if this is an eager session, otherwise null
   */
  public abstract EagerSession getEagerSession();

  // The following methods are called by the subclasses,
  // after resolving the tensor for the Operands

  /**
   * Evaluates the tensor's values against the expected value
   *
   * @param expected the expected value
   * @param tensor the tensor whose values are compared to the expected values.
   * @param type the data type of the tensor
   * @param <T> the data type of the tensor
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  @SuppressWarnings("unchecked")
  protected <T extends TNumber> void evaluate(double expected, Tensor tensor, Class<T> type) {
    boolean isScalar = tensor.shape().equals(Shape.scalar());
    if (debug) {
      print(DEFAULT_WRITER, tensor, type);
    }
    BiConsumer<Object, NdArray<? extends TType>> evaluateFunc = evalMap.get(type);
    if (evaluateFunc == null) fail("Unexpected Type Class: " + type);
    if (isScalar) evaluateFunc.accept(expected, (NdArray<T>) tensor);
    else ((NdArray<T>) tensor).scalars().forEach(f -> evaluateFunc.accept(expected, f));
  }

  /**
   * Evaluates the tensor's values against the expected values
   *
   * @param expected the expected values
   * @param tensor the tensor whose values are compared to the expected values.
   * @param type the data type of the tensor
   * @param <T> the data type of the tensor
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  @SuppressWarnings("unchecked")
  protected <T extends TNumber> void evaluate(Number[] expected, Tensor tensor, Class<T> type) {
    int size = tensor.shape().size() == 0 ? 1 : (int) tensor.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));

    boolean isScalar = tensor.shape().equals(Shape.scalar());
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      print(DEFAULT_WRITER, tensor, type);
    }
    BiConsumer<Object, NdArray<? extends TType>> evaluateFunc = evalMap.get(type);
    if (evaluateFunc == null) fail("Unexpected Type Class: " + type);
    if (isScalar) evaluateFunc.accept(expected[0], (NdArray<T>) tensor);
    else
      ((NdArray<T>) tensor)
          .scalars()
          .forEach(f -> evaluateFunc.accept(expected[index.getAndIncrement()], f));
  }

  /**
   * Evaluates the tensor's values against the expected values
   *
   * @param expected the expected values
   * @param tensor the tensor whose values are compared to the expected values.
   * @param type the data type of the tensor
   * @param <T> the data type of the tensor
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  @SuppressWarnings("unchecked")
  protected <T extends TType> void evaluate(FloatNdArray expected, Tensor tensor, Class<T> type) {
    boolean isScalar = tensor.shape().equals(Shape.scalar());
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      print(DEFAULT_WRITER, tensor, type);
    }
    BiConsumer<Object, NdArray<? extends TType>> evaluateFunc = evalMap.get(type);
    if (evaluateFunc == null) fail("Unexpected Type Class: " + type);
    if (isScalar)
      evaluateFunc.accept(expected.getObject(index.getAndIncrement()), (NdArray<T>) tensor);
    else
      ((NdArray<T>) tensor)
          .scalars()
          .forEach(f -> evaluateFunc.accept(expected.getObject(index.getAndIncrement()), f));
  }

  /**
   * Evaluates the tensor's values against the predicate test
   *
   * @param tensor the tensor to evaluate
   * @param predicate the predicate to test the value of the tensor values
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  protected void evaluateString(TString tensor, Predicate<String> predicate) {
    AtomicInteger index = new AtomicInteger();
    boolean isScalar = tensor.shape().equals(Shape.scalar());
    if (debug) {
      print(DEFAULT_WRITER, tensor, TString.class);
    }
    index.set(0);
    if (isScalar) {
      assertTrue(predicate.test(tensor.getObject()));
    } else {
      tensor.scalars().forEachIndexed((idx, s) -> assertTrue(predicate.test(s.getObject())));
    }
  }

  /**
   * Evaluates the tensor's values against the predicate test
   *
   * @param tensor the tensor to evaluate
   * @param type the data type of the tensor
   * @param predicate the predicate to test the value of the tensor values
   * @param <T> the data type of the tensor
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  @SuppressWarnings("unchecked")
  protected <T extends TType> void evaluate(
      Tensor tensor, Class<T> type, Predicate<Number> predicate) {
    boolean isScalar = tensor.shape().equals(Shape.scalar());
    if (debug) {
      print(DEFAULT_WRITER, tensor, type);
    }
    BiConsumer<Predicate<Number>, NdArray<? extends TType>> evalFunc = evalPredicate.get(type);
    if (evalFunc == null) fail("Unexpected Type Class: " + type);
    if (isScalar) {
      evalFunc.accept(predicate, (NdArray<T>) tensor);
    } else {
      ((NdArray<T>) tensor).scalars().forEach(f -> evalFunc.accept(predicate, f));
    }
  }

  /**
   * Evaluates the tensor's values against the expected values
   *
   * @param expected the expected values
   * @param tensor the tensor whose values are compared to the expected values
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  protected void evaluate(String[] expected, TString tensor) {
    int size = tensor.shape().size() == 0 ? 1 : (int) tensor.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    boolean isScalar = tensor.shape().equals(Shape.scalar());
    if (debug) {
      print(DEFAULT_WRITER, tensor, TString.class);
    }
    if (isScalar) assertEquals(expected[0], tensor.getObject());
    else
      tensor.scalars().forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getObject()));
  }

  /**
   * Evaluates the tensor's values against the expected values
   *
   * @param expected the expected value
   * @param tensor the tensor whose values are compared to the expected values
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  protected void evaluate(Boolean[] expected, TBool tensor) {
    int size = tensor.shape().size() == 0 ? 1 : (int) tensor.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected size (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    boolean isScalar = tensor.shape().equals(Shape.scalar());
    if (debug) {
      print(DEFAULT_WRITER, tensor, TBool.class);
    }
    if (isScalar) assertEquals(expected[index.getAndIncrement()], tensor.getBoolean());
    else
      tensor
          .scalars()
          .forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getBoolean()));
  }

  /**
   * Evaluates the tensor's values against the expected tensor's values
   *
   * @param expected the tensor whose values are expected
   * @param tensor the tensor whose values are compared to the expected values.
   * @param type the data type of the tensor
   * @param <T> the data type of the tensor
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  @SuppressWarnings("unchecked")
  protected <T extends TType> void evaluate(Tensor expected, Tensor tensor, Class<T> type) {
    assert tensor.shape().equals(expected.shape())
        : String.format(
            "expected shape (%s) != to input shape (%s)",
            expected.shape().toString(), tensor.shape().toString());
    boolean isScalar = tensor.shape().equals(Shape.scalar());

    AtomicInteger index = new AtomicInteger();
    if (debug) {
      print(DEFAULT_WRITER, tensor, type);
    }
    index.set(0);
    BiConsumer<Object, NdArray<? extends TType>> evaluateFunc = evalMap.get(type);
    if (evaluateFunc == null) fail("Unexpected Type Class: " + type);
    NdArray<T> expectedArray = (NdArray<T>) expected;
    if (isScalar) evaluateFunc.accept(expectedArray.getObject(), (NdArray<T>) tensor);
    else
      ((NdArray<T>) tensor)
          .scalars()
          .forEachIndexed((idx, f) -> evaluateFunc.accept(expectedArray.getObject(idx), f));
  }

  /**
   * Prints the tensor's values to the print writer
   *
   * @param writer the output writer
   * @param tensor teh tensor to print
   * @param type the data type of the tensor
   * @param <T> the data type of the tensor
   * @throws IllegalArgumentException if the data type for the tensor does not have a print function
   *     registered.
   */
  @SuppressWarnings("unchecked")
  protected <T extends TType> void print(PrintWriter writer, Tensor tensor, Class<T> type) {
    boolean isScalar = tensor.shape().equals(Shape.scalar());
    TriConsumer<PrintWriter, long[], NdArray<? extends TType>> printFunc = printMap.get(type);
    if (printFunc == null) throw new IllegalArgumentException("Unexpected Type Class: " + type);
    if (isScalar) printFunc.accept(writer, ZERO_IDX, (NdArray<T>) tensor);
    else
      ((NdArray<T>) tensor).scalars().forEachIndexed((idx, f) -> printFunc.accept(writer, idx, f));
    writer.flush();
  }

  /** {@inheritDoc} */
  @Override
  public abstract void close();

  /** @return the debug setting */
  public boolean isDebug() {
    return debug;
  }

  /**
   * Set the debug flag
   *
   * @param debug the setting for debugging
   */
  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  /** The Test Session mode, either Eager or Graph */
  public enum Mode {
    EAGER,
    GRAPH
  }
}
