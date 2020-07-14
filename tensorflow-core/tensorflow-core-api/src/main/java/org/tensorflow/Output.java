/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import java.util.Objects;
import org.bytedeco.javacpp.Pointer;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.family.TType;

/**
 * A symbolic handle to a tensor produced by an {@link Operation}.
 *
 * <p>An {@code Output<T>} is a symbolic handle to a {@code Tensor<T>}. The value of the tensor is
 * computed by executing the {@link Operation} in a {@link Session}.
 *
 * <p>By implementing the {@link Operand} interface, instances of this class also act as operands to
 * {@link org.tensorflow.op.Op Op} instances.
 */
public final class Output<T extends TType> implements Operand<T> {

  /** Returns the index into the outputs of the Operation. */
  public int index() {
    return index;
  }

  /** Returns the (possibly partially known) shape of the tensor referred to by this Output. */
  public Shape shape() {
    return operation.shape(index);
  }

  /** Returns the DataType of the tensor referred to by this Output. */
  @SuppressWarnings("unchecked")
  public DataType<T> dataType() {
    return (DataType<T>)operation.dtype(index);
  }

  /**
   * Returns this Output object with the type {@code Output<U>}. This method is useful when given a
   * value of type {@code Output<?>}.
   *
   * @param dt any supported tensor data type
   * @throws IllegalArgumentException if the actual data type of this object does not match the type
   *     {@code U}.
   */
  @SuppressWarnings("unchecked")
  public <U extends TType> Output<U> expect(DataType<U> dt) {
    if (!dt.equals(this.dataType())) {
      throw new IllegalArgumentException(
          "Cannot cast from output of " + this.dataType() + " to output of " + dt);
    }
    return ((Output<U>) this);
  }


  /**
   * Returns the tensor at this output.
   *
   * <p>This operation is only supported on the outputs of an operation executed eagerly. For graph
   * environments, output tensors must be fetched by running a session, using {@link
   * Session.Runner#fetch(Output)}.
   *
   * <p>It is recommended to close explicitly the returned tensor as soon as possible, since the
   * garbage collector is not aware of the amount of memory it consumes, which can be significant.
   *
   * @return tensor
   * @throws IllegalStateException if this output results from a graph
   * @see EagerSession
   */
  @SuppressWarnings("unchecked")
  public Tensor<T> tensor() {
    return (Tensor<T>) operation.tensor(index);
  }

  @Override
  public Operation op() {
    return operation;
  }

  @Override
  public Output<T> asOutput() {
    return this;
  }

  @Override
  public int hashCode() {
    return Objects.hash(operation, index);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Output<?>) {
      Output<?> that = (Output<?>) o;
      return index == that.index && operation.equals(that.operation);
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format(
        "<%s '%s:%d' shape=%s dtype=%s>",
        operation.type(), operation.name(), index, shape().toString(), dataType());
  }

  /** Handle to the idx-th output of the Operation {@code op}. */
  Output(AbstractOperation op, int idx) {
    operation = op;
    index = idx;
  }

  Pointer getUnsafeNativeHandle() {
    return operation.getUnsafeNativeHandle(index);
  }

  private final AbstractOperation operation;
  private final int index;
}
