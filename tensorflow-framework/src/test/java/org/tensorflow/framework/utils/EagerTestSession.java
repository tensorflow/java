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
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.io.PrintWriter;
import java.util.Map;
import java.util.function.Predicate;

/** Eager Mode Test Session */
public class EagerTestSession extends TestSession {

  private final EagerSession session;
  private final Ops tf;

  /** Create an Eager mode test session. */
  public EagerTestSession() {
    this.session = EagerSession.create();
    this.tf = Ops.create(session).withName("test");
  }

  /** {@inheritDoc} */
  @Override
  public Ops getTF() {
    return tf;
  }

  /**
   * Get the TensorFlow EagerSession instance
   *
   * @return the TensorFlow EagerSession instance
   */
  public EagerSession getSession() {
    return session;
  }

  /** {@inheritDoc} */
  @Override
  public void close() {
    session.close();
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEager() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Session getGraphSession() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public EagerSession getEagerSession() {
    return this.session;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> void evaluate(
      double expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    super.evaluate(expected, input.asTensor(), input.type());
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> void evaluate(
      Number[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    super.evaluate(expected, input.asTensor(), input.type());
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(
      FloatNdArray expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    super.evaluate(expected, input.asTensor(), input.type());
  }

  /** {@inheritDoc} */
  @Override
  public void evaluateString(
      Operand<TString> input,
      Predicate<String> predicate,
      Map<Operand<? extends TType>, Tensor> feedMap) {

    super.evaluateString(input.asTensor(), predicate);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(
      Operand<T> input,
      Predicate<Number> predicate,
      Map<Operand<? extends TType>, Tensor> feedMap) {
    super.evaluate(input.asTensor(), input.type(), predicate);
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(
      String[] expected, Operand<TString> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    super.evaluate(expected, input.asTensor());
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(
      Boolean[] expected, Operand<TBool> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    super.evaluate(expected, input.asTensor());
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(
      Operand<T> expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {

    super.evaluate(expected.asTensor(), input.asTensor(), input.type());
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void print(
      PrintWriter writer, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    super.print(writer, input.asTensor(), input.type());
  }
}
