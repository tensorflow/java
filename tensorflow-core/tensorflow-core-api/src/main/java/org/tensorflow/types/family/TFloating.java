package org.tensorflow.types.family;

/**
 * Common interface for all floating point tensors.
 *
 * <p>Operations that only accepts floating point values as some of their operands enforce that the tensor
 * types for these operands to be bound to this interface. For example:
 *
 * <pre>{@code
 * Ops tf = Ops.create();
 *
 * Constant<TFloat32> c1 = tf.array(1.0f, 2.0f, 3.0f);
 * Constant<TBool> c2 = tf.array(true, false, true);
 *
 * Exponential<TFloat32> exp = new Exponential<>(tf);
 * exp.call(c1);  // OK
 * exp.call(c2);  // Compilation failure
 * }</pre>
 */
public interface TFloating extends TNumber {}
