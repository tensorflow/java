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
package org.tensorflow.keras.utils;

import org.tensorflow.*;
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
import java.util.Map;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** @author Jim Clarke */
public abstract class TestSession implements AutoCloseable {

  protected float epsilon = 1e-5F;
  protected boolean debug;

  /** Enumerate between Eager and Graph Mode */
  public enum Mode {
    EAGER,
    GRAPH;
  }

  public static TestSession createEagerSession() {
    return new EagerTestSession();
  }

  public static TestSession createGraphSession() {
    return new GraphTestSession();
  }

  public static TestSession createTestSession(Mode mode) {
    return mode == Mode.EAGER ? createEagerSession() : createGraphSession();
  }

  public void initialize() {
    // empty
  }

  /**
   * Perform session.run()
   *
   * <p>If in eager mode, this does nothing.
   *
   * @param op The Operation to run
   */
  public abstract void run(Op op);

  /**
   * Perform session.run()
   *
   * <p>If in eager mode, this does nothing.
   *
   * @param op The Operation to run
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public abstract void run(Op op, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(Number expected, Operand<U> input) {
    evaluate(new Number[] {expected}, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      Number expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate(new Number[] {expected}, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(Number expected, Op input) {
    evaluate(new Number[] {expected}, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <T> the data type for the feedDict entries
   */
  public <T extends TType> void evaluate(
      Number expected, Op input, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate(new Number[] {expected}, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type for the input
   */
  public <U extends TNumber> void evaluate(Number[] expected, Op input) {
    Output<U> output = input.op().output(0);
    evaluate(expected, output, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type for the input
   */
  public <U extends TNumber> void evaluate(
      Number[] expected,
      Op input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Output<U> output = input.op().output(0);
    evaluate(expected, output, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(Number[] expected, Operand<U> input) {
    Output<U> output = input.asOutput();
    evaluate(expected, output, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      Number[] expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Output output = input.asOutput();
    evaluate(expected, output, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(byte expected, Operand<U> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      byte expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate((double) expected, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(int expected, Operand<U> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      int expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate((double) expected, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(long expected, Operand<U> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      long expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate((double) expected, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(float expected, Operand<U> input) {
    evaluate((double) expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      float expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate((double) expected, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(double expected, Operand<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public abstract <U extends TNumber> void evaluate(
      double expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(byte[] expected, Operand<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      byte[] expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Byte[] iArray = new Byte[expected.length];
    for (int i = 0; i < expected.length; i++) {
      iArray[i] = expected[i];
    }
    evaluate(iArray, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(int[] expected, Operand<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      int[] expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Integer[] iArray = new Integer[expected.length];
    for (int i = 0; i < expected.length; i++) {
      iArray[i] = expected[i];
    }
    evaluate(iArray, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(long[] expected, Operand<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      long[] expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Long[] iArray = new Long[expected.length];
    for (int i = 0; i < expected.length; i++) {
      iArray[i] = expected[i];
    }
    evaluate(iArray, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(float[] expected, Operand<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      float[] expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Float[] iArray = new Float[expected.length];
    for (int i = 0; i < expected.length; i++) {
      iArray[i] = expected[i];
    }
    evaluate(iArray, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(double[] expected, Operand<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      double[] expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Double[] iArray = new Double[expected.length];
    for (int i = 0; i < expected.length; i++) {
      iArray[i] = expected[i];
    }
    evaluate(iArray, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(Number[] expected, Output<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public abstract <U extends TNumber> void evaluate(
      Number[] expected,
      Output<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(String expected, Operand<TString> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void evaluate(
      String expected,
      Operand<TString> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate(new String[] {expected}, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(String expected, Op input) {
    evaluate(new String[] {expected}, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void evaluate(
      String expected, Op input, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate(new String[] {expected}, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(String[] expected, Op input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void evaluate(
      String[] expected,
      Op input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Output output = input.op().output(0);
    evaluate(expected, output, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(String[] expected, Operand<TString> input) {
    Output output = input.asOutput();
    evaluate(expected, output, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public abstract void evaluate(
      String[] expected,
      Output<TString> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(Boolean expected, Operand<TBool> input) {
    evaluate(new Boolean[] {expected}, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void evaluate(
      Boolean expected,
      Operand<TBool> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate(new Boolean[] {expected}, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(Boolean expected, Op input) {
    evaluate(new Boolean[] {expected}, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void evaluate(
      Boolean expected, Op input, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate(new Boolean[] {expected}, input, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(Boolean[] expected, Op input) {
    Output output = input.op().output(0);
    evaluate(expected, output, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void evaluate(
      Boolean[] expected,
      Op input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Output output = input.op().output(0);
    evaluate(expected, output, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(Boolean[] expected, Operand<TBool> input) {
    Output output = input.asOutput();
    evaluate(expected, output, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void evaluate(
      Boolean[] expected,
      Operand<TBool> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Output output = input.asOutput();
    evaluate(expected, output, feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   */
  public void evaluate(Boolean[] expected, Output<TBool> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public abstract void evaluate(
      Boolean[] expected,
      Output<TBool> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <T> the data type of the input
   */
  public <T extends TType> void evaluate(Operand<T> expected, Output<T> input) {
    evaluate(expected.asOutput(), input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <T> the data type for the feedDict entries
   */
  public <T extends TType> void evaluate(Operand<T> expected, Operand<T> input) {
    evaluate(expected.asOutput(), input.asOutput(), null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <T> the data type for the feedDict entries
   */
  public abstract <T extends TType> void evaluate(
      Output<T> expected,
      Output<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(FloatNdArray expected, Operand<U> input) {
    evaluate(expected, input.asOutput(), null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(
      FloatNdArray expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    evaluate(expected, input.asOutput(), feedDict);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(FloatNdArray expected, Output<U> input) {
    evaluate(expected, input, null);
  }

  /**
   * Evaluate the expected results versus the actual results
   *
   * @param expected the expected value
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public abstract <U extends TNumber> void evaluate(
      FloatNdArray expected,
      Output<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the actual results using a predicate
   *
   * @param input the actual value
   * @param predicate a predicate that accepts a Number as an argument, if the result of the
   *     predicate is false, then the test will fail
   * @param <U> the data type of the input
   */
  public <U extends TNumber> void evaluate(Operand<U> input, Predicate<Number> predicate) {
    evaluate(input.asOutput(), predicate, null);
  }

  /**
   * Evaluate the actual results using a predicate
   *
   * @param input the actual value
   * @param predicate a predicate that accepts a Number as an argument, if the result of the
   *     predicate is false, then the test will fail
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <U> the data type of the input
   */
  public abstract <U extends TNumber> void evaluate(
      Output<U> input,
      Predicate<Number> predicate,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Evaluate the actual results using a predicate
   *
   * @param input the actual value
   * @param predicate a predicate that accepts a Number as an argument, if the result of the
   *     predicate is false, then the test will fail
   */
  public void evaluate(FloatNdArray input, Predicate<Number> predicate) {
    input.scalars().forEach(f -> assertTrue(predicate.test(f.getFloat())));
  }

  /**
   * Print the results to output stream
   *
   * @param out the output stream
   * @param input the actual value
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(OutputStream out, Operand<T> input) {
    print(out, input, null);
  }

  /**
   * Print the results to output stream
   *
   * @param out the output stream
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <T> the data type for the feedDict entries
   */
  public <T extends TType> void print(
      OutputStream out,
      Operand<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    print(new PrintWriter(new OutputStreamWriter(out)), input.asOutput(), feedDict);
  }

  /**
   * Print the results to output stream
   *
   * @param out the output stream
   * @param input the actual value
   */
  public void print(OutputStream out, Op input) {
    print(out, input, null);
  }

  /**
   * Print the results to output stream
   *
   * @param out the output stream
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void print(
      OutputStream out, Op input, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    print(new PrintWriter(new OutputStreamWriter(out)), input.op().output(0), feedDict);
  }

  /**
   * Print the results to output stream
   *
   * @param out the output stream
   * @param input the actual value
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(OutputStream out, Output<T> input) {
    print(out, input, null);
  }

  /**
   * Print the results to output stream
   *
   * @param out the output stream
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(
      OutputStream out,
      Output<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    print(new PrintWriter(new OutputStreamWriter(out)), input, feedDict);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(Writer writer, Operand<T> input) {
    print(writer, input, null);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(
      Writer writer,
      Operand<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    print(new PrintWriter(writer), input.asOutput(), feedDict);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   */
  public void print(Writer writer, Op input) {
    print(writer, input, null);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   */
  public void print(
      Writer writer, Op input, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    print(new PrintWriter(writer), input.op().output(0), feedDict);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(Writer writer, Output<T> input) {
    print(writer, input, null);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(
      Writer writer,
      Output<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    print(new PrintWriter(writer), input, feedDict);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   * @param <T> the data type for the input
   */
  public <T extends TType> void print(PrintWriter writer, Output<T> input) {
    print(writer, input, null);
  }

  /**
   * Print the results to the character stream
   *
   * @param writer the character stream
   * @param input the actual value
   * @param feedDict The dictionary of values to pass to the feed() operation of the runner,
   *     required for placeholders.
   * @param <T> the data type for the input
   */
  public abstract <T extends TType> void print(
      PrintWriter writer,
      Output<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict);

  /**
   * Get the TensorFlow Ops for this test session
   *
   * @return the TensorFlow Ops for this test session
   */
  public abstract Ops getTF();

  /**
   * Determine whether this session is in Eager mode
   *
   * @return true if the this session is in Eager mode
   */
  public abstract boolean isEager();

  /**
   * Determine whether this session is in Graph mode
   *
   * @return true if the this session is in Graph mode
   */
  public boolean isGraph() {
    return !isEager();
  }

  /**
   * Get the current EPSILON value for floating point number comparison.
   *
   * @return the current EPSILON value for floating point number comparison.
   */
  public float getEpsilon() {
    return this.epsilon;
  }

  /**
   * Set the current EPSILON value for floating point number comparison.
   *
   * @param epsilon the new EPSILON value for floating point number comparison.
   */
  public void setEpsilon(float epsilon) {
    this.epsilon = epsilon;
  }

  /**
   * Get the TensorFlow Session object
   *
   * @return the TensorFlow Session object, returns null if this is not a Graph Test Session
   */
  public abstract Session getGraphSession();

  /**
   * Get the TensorFlow EagerSession object
   *
   * @return the TensorFlow Session object, returns null if this is not a Graph Test Session
   */
  public abstract EagerSession getEagerSession();

  /** {@inheritDoc} */
  @Override
  public abstract void close();

  /**
   * Get the debug setting
   *
   * @return the debug setting
   */
  public boolean isDebug() {
    return debug;
  }

  /**
   * Sets the debug setting.
   *
   * <p>If true, then evaluate methods will also print the Tensor values to System.out.
   *
   * @param debug the debug to set
   */
  public void setDebug(boolean debug) {
    this.debug = debug;
  }
}
