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

import org.tensorflow.*;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Base class for Test Session */
public abstract class TestSession implements AutoCloseable {

  protected float epsilon = 1e-5F;
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
   * Creates a Test Session
   *
   * @param mode the type of Session, either eager or graph
   * @return returns the test session
   */
  public static TestSession createTestSession(Mode mode) {
    return mode == Mode.EAGER ? createEagerSession() : createGraphSession();
  }

  /** Initializes the Test Session, default implementation is do nothing. */
  public void initialize() {
    // empty
  }

  /**
   * Runs the Operation
   *
   * @param op the Operation to run
   */
  public void run(Op op) {
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
    evaluate(new Number[] {expected}, input);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Number expected, Op input) {
    evaluate(new Number[] {expected}, input);
  }

  /**
   * Evaluates the input against the expected values
   *
   * @param expected the expected values
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Number[] expected, Op input) {
    Output<? extends TNumber> output = input.op().output(0);
    evaluate(expected, output);
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
    Output<T> output = input.asOutput();
    evaluate(expected, output);
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
    evaluate((double) expected, input);
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
    evaluate((double) expected, input);
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
    evaluate((double) expected, input);
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
    evaluate((double) expected, input);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TNumber> void evaluate(double expected, Operand<T> input);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TNumber> void evaluate(byte[] expected, Operand<T> input) {
    Byte[] iArray = new Byte[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input);
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
    Integer[] iArray = new Integer[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input);
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
    Long[] iArray = new Long[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input);
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
    Float[] iArray = new Float[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input);
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
    Double[] iArray = new Double[expected.length];
    for (int i = 0; i < expected.length; i++) iArray[i] = expected[i];
    evaluate(iArray, input);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TNumber> void evaluate(Number[] expected, Output<T> input);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(String expected, Operand<TString> input) {
    evaluate(new String[] {expected}, input);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(String expected, Op input) {
    evaluate(new String[] {expected}, input);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(String[] expected, Op input) {
    Output<TString> output = input.op().output(0);
    evaluate(expected, output);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(String[] expected, Operand<TString> input) {
    Output<TString> output = input.asOutput();
    evaluate(expected, output);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract void evaluate(String[] expected, Output<TString> input);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Boolean expected, Operand<TBool> input) {
    evaluate(new Boolean[] {expected}, input);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Boolean expected, Op input) {
    evaluate(new Boolean[] {expected}, input);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Boolean[] expected, Op input) {
    Output<TBool> output = input.op().output(0);
    evaluate(expected, output);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(Boolean[] expected, Operand<TBool> input) {
    Output<TBool> output = input.asOutput();
    evaluate(expected, output);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract void evaluate(Boolean[] expected, Output<TBool> input);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the expected Operand
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TType> void evaluate(Operand<T> expected, Op input) {
    Output<T> output = input.op().output(0);
    evaluate(expected, output);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TType> void evaluate(Operand<T> expected, Operand<T> input) {
    evaluate(expected.asOutput(), input.asOutput());
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TType> void evaluate(Output<T> expected, Output<T> input);

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TType> void evaluate(FloatNdArray expected, Operand<T> input) {
    evaluate(expected, input.asOutput());
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param expected the expected value
   * @param input the operand to evaluate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TType> void evaluate(FloatNdArray expected, Output<T> input);

  /**
   * Evaluates the input against the expected value
   *
   * @param input the operand to evaluate
   * @param predicate the Predicate
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public <T extends TType> void evaluate(Operand<T> input, Predicate<Number> predicate) {
    evaluate(input.asOutput(), predicate);
  }

  /**
   * Evaluates the input against the expected value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   * @param <T> the data type of the input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public abstract <T extends TType> void evaluate(Output<T> input, Predicate<Number> predicate);

  /**
   * Evaluates the input against the expected string value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   */
  public void evaluateString(Operand<TString> input, Predicate<String> predicate) {
    evaluateString(input.asOutput(), predicate);
  }

  /**
   * Evaluates the input against the expected string value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   */
  public abstract void evaluateString(Output<TString> input, Predicate<String> predicate);

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
   * Evaluates the input against the expected value
   *
   * @param input the operand to evaluate
   * @param predicate The Predicate that evaluates the each value from input
   * @throws org.opentest4j.AssertionFailedError if the evaluation fails
   */
  public void evaluate(DoubleNdArray input, Predicate<Number> predicate) {
    input.scalars().forEach(f -> assertTrue(predicate.test(f.getDouble())));
  }

  /**
   * Print the input
   *
   * @param out the output stream
   * @param input the operand to print
   * @param <T> the data type of the input
   */
  public <T extends TType> void print(OutputStream out, Operand<T> input) {
    print(new PrintWriter(new OutputStreamWriter(out)), input.asOutput());
  }

  /**
   * Print the input to standard out
   *
   * @param input the op to print
   */
  public void print(Op input) {
    print(new PrintWriter(new OutputStreamWriter(System.out)), input.op().output(0));
  }

  /**
   * Print the input
   *
   * @param out the output stream
   * @param input the op to print
   */
  public void print(OutputStream out, Op input) {
    print(new PrintWriter(new OutputStreamWriter(out)), input.op().output(0));
  }

  /**
   * Print the input to standard out
   *
   * @param input the op to print
   * @param <T> the data type of the input
   */
  public <T extends TType> void print(Output<T> input) {
    print(new PrintWriter(new OutputStreamWriter(System.out)), input);
  }

  /**
   * Print the input
   *
   * @param out the output stream
   * @param input the op to print
   * @param <T> the data type of the input
   */
  public <T extends TType> void print(OutputStream out, Output<T> input) {
    print(new PrintWriter(new OutputStreamWriter(out)), input);
  }

  /**
   * Print the input
   *
   * @param writer the output writer
   * @param input the operand to print
   * @param <T> the data type of the input
   */
  public <T extends TType> void print(Writer writer, Operand<T> input) {
    print(new PrintWriter(writer), input.asOutput());
  }

  /**
   * Print the input
   *
   * @param writer the output writer
   * @param input the op to print
   */
  public void print(Writer writer, Op input) {
    print(new PrintWriter(writer), input.op().output(0));
  }

  /**
   * Print the input
   *
   * @param writer the output writer
   * @param input the op to print
   * @param <T> the data type of the input
   */
  public <T extends TType> void print(Writer writer, Output<T> input) {
    print(new PrintWriter(writer), input);
  }

  /**
   * Print the input
   *
   * @param writer the output writer
   * @param input the op to print
   */
  public abstract <T extends TType> void print(PrintWriter writer, Output<T> input);

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
   * Get the epsilon value for evaluating float values
   *
   * @return the epsilon value for evaluating float values
   */
  public float getEpsilon() {
    return this.epsilon;
  }

  /**
   * Set the epsilon value for evaluating float values
   *
   * @param epsilon the epsilon value for evaluating float values
   */
  public void setEpsilon(float epsilon) {
    this.epsilon = epsilon;
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
