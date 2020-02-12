// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.data.DeserializeIterator;
import org.tensorflow.op.data.IteratorGetNext;
import org.tensorflow.op.data.IteratorGetNextAsOptional;
import org.tensorflow.op.data.IteratorGetNextSync;
import org.tensorflow.op.data.IteratorToStringHandle;
import org.tensorflow.op.data.MakeIterator;
import org.tensorflow.op.data.OptionalFromValue;
import org.tensorflow.op.data.OptionalGetValue;
import org.tensorflow.op.data.OptionalHasValue;
import org.tensorflow.op.data.OptionalNone;
import org.tensorflow.op.data.SerializeIterator;
import org.tensorflow.tools.Shape;

/**
 * An API for building {@code data} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class DataOps {
  private final Scope scope;

  DataOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Converts the given variant tensor to an iterator and stores it in the given resource.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @param serialized A variant tensor storing the state of the iterator contained in the
   *  resource.
   * @return a new instance of DeserializeIterator
   */
  public DeserializeIterator deserializeIterator(Operand<?> resourceHandle, Operand<?> serialized) {
    return DeserializeIterator.create(scope, resourceHandle, serialized);
  }

  /**
   * Gets the next output from the given iterator .
   *
   * @param iterator
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of IteratorGetNext
   */
  public IteratorGetNext iteratorGetNext(Operand<?> iterator, List<DataType<?>> outputTypes,
      List<Shape> outputShapes) {
    return IteratorGetNext.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator as an Optional variant.
   *
   * @param iterator
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of IteratorGetNextAsOptional
   */
  public IteratorGetNextAsOptional iteratorGetNextAsOptional(Operand<?> iterator,
      List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNextAsOptional.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator.
   *  <p>
   *  This operation is a synchronous version IteratorGetNext. It should only be used
   *  in situations where the iterator does not block the calling thread, or where
   *  the calling thread is not a member of the thread pool used to execute parallel
   *  operations (e.g. in eager mode).
   *
   * @param iterator
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of IteratorGetNextSync
   */
  public IteratorGetNextSync iteratorGetNextSync(Operand<?> iterator, List<DataType<?>> outputTypes,
      List<Shape> outputShapes) {
    return IteratorGetNextSync.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Converts the given `resource_handle` representing an iterator to a string.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @return a new instance of IteratorToStringHandle
   */
  public IteratorToStringHandle iteratorToStringHandle(Operand<?> resourceHandle) {
    return IteratorToStringHandle.create(scope, resourceHandle);
  }

  /**
   * Makes a new iterator from the given `dataset` and stores it in `iterator`.
   *  <p>
   *  This operation may be executed multiple times. Each execution will reset the
   *  iterator in `iterator` to the first element of `dataset`.
   *
   * @param dataset
   * @param iterator
   * @return a new instance of MakeIterator
   */
  public MakeIterator makeIterator(Operand<?> dataset, Operand<?> iterator) {
    return MakeIterator.create(scope, dataset, iterator);
  }

  /**
   * Constructs an Optional variant from a tuple of tensors.
   *
   * @param components
   * @return a new instance of OptionalFromValue
   */
  public OptionalFromValue optionalFromValue(Iterable<Operand<?>> components) {
    return OptionalFromValue.create(scope, components);
  }

  /**
   * Returns the value stored in an Optional variant or raises an error if none exists.
   *
   * @param optional
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of OptionalGetValue
   */
  public OptionalGetValue optionalGetValue(Operand<?> optional, List<DataType<?>> outputTypes,
      List<Shape> outputShapes) {
    return OptionalGetValue.create(scope, optional, outputTypes, outputShapes);
  }

  /**
   * Returns true if and only if the given Optional variant has a value.
   *
   * @param optional
   * @return a new instance of OptionalHasValue
   */
  public OptionalHasValue optionalHasValue(Operand<?> optional) {
    return OptionalHasValue.create(scope, optional);
  }

  /**
   * Creates an Optional variant with no value.
   *
   * @return a new instance of OptionalNone
   */
  public OptionalNone optionalNone() {
    return OptionalNone.create(scope);
  }

  /**
   * Converts the given `resource_handle` representing an iterator to a variant tensor.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @return a new instance of SerializeIterator
   */
  public SerializeIterator serializeIterator(Operand<?> resourceHandle) {
    return SerializeIterator.create(scope, resourceHandle);
  }
}
