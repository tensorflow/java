/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op;

import org.tensorflow.Operation;

/**
 * A base class for {@link Op} implementations that are backed by a single {@link Operation}.
 *
 * <p>Each operation registered in the TensorFlow core is provided as a {@code RawOp}. Custom
 * operations working with only a single operation may also derive from this class.
 */
public abstract class RawOp implements Op {

  @Override
  public Operation op() {
    return operation;
  }

  @Override
  public final int hashCode() {
    return operation.hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    // Note: we consider that all objects wrapping the same operation are equal, no matter their
    // implementation
    if (!(obj instanceof RawOp)) {
      return false;
    }
    return operation.equals(((RawOp) obj).operation);
  }

  @Override
  public final String toString() {
    return String.format("<%s '%s'>", operation.type(), operation.name());
  }

  protected final Operation operation;

  /**
   * Constructor.
   *
   * @param operation the underlying operation
   * @param requiredType the type that the underlying operation must be
   */
  protected RawOp(Operation operation, String requiredType) {
    if (!requiredType.equals(operation.type())) {
      throw new IllegalArgumentException(
          "Can't create a "
              + this.getClass()
              + " from an operation with type \""
              + operation.type()
              + "\", operation must have type \""
              + requiredType
              + "\".");
    }
    this.operation = operation;
  }
}
