package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.bitwise.BitwiseAnd;
import org.tensorflow.op.bitwise.BitwiseOr;
import org.tensorflow.op.bitwise.BitwiseXor;
import org.tensorflow.op.bitwise.Invert;
import org.tensorflow.op.bitwise.LeftShift;
import org.tensorflow.op.bitwise.RightShift;

/**
 * An API for building {@code bitwise} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class BitwiseOps {
  private final Scope scope;

  BitwiseOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link Invert} operation
   *
   * @param x 
   * @return a new instance of Invert
   * @see org.tensorflow.op.bitwise.Invert
   */
  public <T extends Number> Invert<T> invert(Operand<T> x) {
    return Invert.create(scope, x);
  }

  /**
   * Builds an {@link LeftShift} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of LeftShift
   * @see org.tensorflow.op.bitwise.LeftShift
   */
  public <T extends Number> LeftShift<T> leftShift(Operand<T> x, Operand<T> y) {
    return LeftShift.create(scope, x, y);
  }

  /**
   * Builds an {@link BitwiseAnd} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of BitwiseAnd
   * @see org.tensorflow.op.bitwise.BitwiseAnd
   */
  public <T extends Number> BitwiseAnd<T> bitwiseAnd(Operand<T> x, Operand<T> y) {
    return BitwiseAnd.create(scope, x, y);
  }

  /**
   * Builds an {@link BitwiseXor} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of BitwiseXor
   * @see org.tensorflow.op.bitwise.BitwiseXor
   */
  public <T extends Number> BitwiseXor<T> bitwiseXor(Operand<T> x, Operand<T> y) {
    return BitwiseXor.create(scope, x, y);
  }

  /**
   * Builds an {@link BitwiseOr} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of BitwiseOr
   * @see org.tensorflow.op.bitwise.BitwiseOr
   */
  public <T extends Number> BitwiseOr<T> bitwiseOr(Operand<T> x, Operand<T> y) {
    return BitwiseOr.create(scope, x, y);
  }

  /**
   * Builds an {@link RightShift} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of RightShift
   * @see org.tensorflow.op.bitwise.RightShift
   */
  public <T extends Number> RightShift<T> rightShift(Operand<T> x, Operand<T> y) {
    return RightShift.create(scope, x, y);
  }
}
