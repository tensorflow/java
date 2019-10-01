package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.dtypes.AsString;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.dtypes.Complex;

/**
 * An API for building {@code dtypes} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class DtypesOps {
  private final Scope scope;

  DtypesOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link Cast} operation
   *
   * @param x 
   * @param DstT 
   * @param options carries optional attributes values
   * @return a new instance of Cast
   * @see org.tensorflow.op.dtypes.Cast
   */
  public <U, T> Cast<U> cast(Operand<T> x, Class<U> DstT, Cast.Options... options) {
    return Cast.create(scope, x, DstT, options);
  }

  /**
   * Builds an {@link AsString} operation
   *
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of AsString
   * @see org.tensorflow.op.dtypes.AsString
   */
  public <T> AsString asString(Operand<T> input, AsString.Options... options) {
    return AsString.create(scope, input, options);
  }

  /**
   * Builds an {@link Complex} operation
   *
   * @param real 
   * @param imag 
   * @param Tout 
   * @return a new instance of Complex
   * @see org.tensorflow.op.dtypes.Complex
   */
  public <U, T extends Number> Complex<U> complex(Operand<T> real, Operand<T> imag, Class<U> Tout) {
    return Complex.create(scope, real, imag, Tout);
  }
}
