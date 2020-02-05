package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.family.TType;

/**
 * Base class for {@link Const} operators
 *
 * @param <T>
 */
public abstract class Const<T extends TType> extends PrimitiveOp implements Operand<T> {

  /**
   * Builds a "Const" operation from a Tensor.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param tensor a Tensor holding the constant value
   * @return a "Const" operation
   */
  static Operation buildConstOp(Scope scope, Tensor<?> tensor) {
    return scope
        .env()
        .opBuilder("Const", scope.makeOpName("Const"))
        .setAttr("value", tensor)
        .setAttr("dtype", tensor.dataType())
        .build();
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  Const(Operation operation) {
    super(operation);
    output = operation.output(0);
  }

  private final Output<T> output;
}
