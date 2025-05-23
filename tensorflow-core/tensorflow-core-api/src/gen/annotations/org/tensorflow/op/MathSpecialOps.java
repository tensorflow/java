// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.math.special.BesselJ0;
import org.tensorflow.op.math.special.BesselJ1;
import org.tensorflow.op.math.special.BesselK0;
import org.tensorflow.op.math.special.BesselK0e;
import org.tensorflow.op.math.special.BesselK1;
import org.tensorflow.op.math.special.BesselK1e;
import org.tensorflow.op.math.special.BesselY0;
import org.tensorflow.op.math.special.BesselY1;
import org.tensorflow.op.math.special.Dawsn;
import org.tensorflow.op.math.special.Expint;
import org.tensorflow.op.math.special.FresnelCos;
import org.tensorflow.op.math.special.FresnelSin;
import org.tensorflow.op.math.special.Spence;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code math.special} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class MathSpecialOps {
  private final Scope scope;

  private final Ops ops;

  MathSpecialOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * The BesselJ0 operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselJ0} output and operands
   * @return a new instance of BesselJ0
   */
  public <T extends TNumber> BesselJ0<T> besselJ0(Operand<T> x) {
    return BesselJ0.create(scope, x);
  }

  /**
   * The BesselJ1 operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselJ1} output and operands
   * @return a new instance of BesselJ1
   */
  public <T extends TNumber> BesselJ1<T> besselJ1(Operand<T> x) {
    return BesselJ1.create(scope, x);
  }

  /**
   * The BesselK0 operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselK0} output and operands
   * @return a new instance of BesselK0
   */
  public <T extends TNumber> BesselK0<T> besselK0(Operand<T> x) {
    return BesselK0.create(scope, x);
  }

  /**
   * The BesselK0e operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselK0e} output and operands
   * @return a new instance of BesselK0e
   */
  public <T extends TNumber> BesselK0e<T> besselK0e(Operand<T> x) {
    return BesselK0e.create(scope, x);
  }

  /**
   * The BesselK1 operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselK1} output and operands
   * @return a new instance of BesselK1
   */
  public <T extends TNumber> BesselK1<T> besselK1(Operand<T> x) {
    return BesselK1.create(scope, x);
  }

  /**
   * The BesselK1e operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselK1e} output and operands
   * @return a new instance of BesselK1e
   */
  public <T extends TNumber> BesselK1e<T> besselK1e(Operand<T> x) {
    return BesselK1e.create(scope, x);
  }

  /**
   * The BesselY0 operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselY0} output and operands
   * @return a new instance of BesselY0
   */
  public <T extends TNumber> BesselY0<T> besselY0(Operand<T> x) {
    return BesselY0.create(scope, x);
  }

  /**
   * The BesselY1 operation
   *
   * @param x The x value
   * @param <T> data type for {@code BesselY1} output and operands
   * @return a new instance of BesselY1
   */
  public <T extends TNumber> BesselY1<T> besselY1(Operand<T> x) {
    return BesselY1.create(scope, x);
  }

  /**
   * The Dawsn operation
   *
   * @param x The x value
   * @param <T> data type for {@code Dawsn} output and operands
   * @return a new instance of Dawsn
   */
  public <T extends TNumber> Dawsn<T> dawsn(Operand<T> x) {
    return Dawsn.create(scope, x);
  }

  /**
   * The Expint operation
   *
   * @param x The x value
   * @param <T> data type for {@code Expint} output and operands
   * @return a new instance of Expint
   */
  public <T extends TNumber> Expint<T> expint(Operand<T> x) {
    return Expint.create(scope, x);
  }

  /**
   * The FresnelCos operation
   *
   * @param x The x value
   * @param <T> data type for {@code FresnelCos} output and operands
   * @return a new instance of FresnelCos
   */
  public <T extends TNumber> FresnelCos<T> fresnelCos(Operand<T> x) {
    return FresnelCos.create(scope, x);
  }

  /**
   * The FresnelSin operation
   *
   * @param x The x value
   * @param <T> data type for {@code FresnelSin} output and operands
   * @return a new instance of FresnelSin
   */
  public <T extends TNumber> FresnelSin<T> fresnelSin(Operand<T> x) {
    return FresnelSin.create(scope, x);
  }

  /**
   * The Spence operation
   *
   * @param x The x value
   * @param <T> data type for {@code Spence} output and operands
   * @return a new instance of Spence
   */
  public <T extends TNumber> Spence<T> spence(Operand<T> x) {
    return Spence.create(scope, x);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
