package org.tensorflow.types.family;

/**
 * Marker interface for floating point tensor types.
 *
 * <p>Operations that only accepts floating point values as some of their operands enforce that the tensor
 * types for these operands to be bound to this interface. For example:
 *
 * <pre>{@code
 * TFloat32 tensor1 = TFloat32.vectorOf(1, 2, 3);
 * TBool tensor2 = TBool.vectorOf(true, false, true);
 *
 * Ops tf = Ops.create();
 * Exponential<TFloat32> exp = new Exponential<>(tf);
 * exp.call(tf.constant(tensor1));  // OK
 * exp.call(tf.constant(tensor2));  // Compilation failure
 * }</pre>
 */
public interface TFloating<T> extends TNumber<T> {}
