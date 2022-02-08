/*
Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
Copyright 2022 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.proto.framework.RunMetadata;

/**
 * An {@link AutoCloseable} wrapper around a {@link Map} containing {@link Tensor}s.
 *
 * <p>When this is closed it closes all the {@link Tensor}s inside it. If you maintain a reference
 * to a value after this object has been closed it will throw an {@link IllegalStateException} upon
 * access.
 *
 * <p>This class is not thread-safe with respect to the close operation. Multiple closers or one
 * thread closing a tensor while another is reading may throw exceptions.
 *
 * <p>Note this class is used to manage the lifetimes of tensors produced by the TensorFlow runtime,
 * from sessions and function calls. It is not used as an argument to {@code session.run} or
 * function calls as users are in control of the creation of input tensors.
 */
public final class Result implements AutoCloseable, Iterable<Map.Entry<String, Tensor>> {
  @Override
  public void close() {
    if (!closed) {
      for (Tensor t : list) {
        try {
          t.close();
        } catch (TensorFlowException e) {
          logger.log(Level.WARNING, "Exception raised when closing tensor inside result.", e);
        }
      }
      closed = true;
    } else {
      logger.warning("Closing an already closed Result");
    }
  }

  @Override
  public Iterator<Map.Entry<String, Tensor>> iterator() {
    if (!closed) {
      return map.entrySet().iterator();
    } else {
      throw new IllegalStateException("Result is closed");
    }
  }

  /**
   * Returns the number of outputs in this Result.
   *
   * @return The number of outputs.
   */
  public int size() {
    return map.size();
  }

  /**
   * Gets the set containing all the tensor names.
   *
   * @return The tensor names set.
   */
  public Set<String> keySet() {
    return Collections.unmodifiableSet(map.keySet());
  }

  /**
   * Does this result object have a tensor for the supplied key?
   *
   * @param key The key to check.
   * @return True if this result object has a tensor for this key.
   */
  public boolean containsKey(String key) {
    return map.containsKey(key);
  }

  /**
   * Gets the value from the container at the specified index.
   *
   * <p>Throws {@link IllegalStateException} if the container has been closed, and {@link
   * IndexOutOfBoundsException} if the index is invalid.
   *
   * @param index The index to lookup.
   * @return The value at the index.
   */
  public Tensor get(int index) {
    if (!closed) {
      return list.get(index);
    } else {
      throw new IllegalStateException("Result is closed");
    }
  }

  /**
   * Gets the value from the container assuming it's not been closed.
   *
   * <p>Throws {@link IllegalStateException} if the container has been closed.
   *
   * @param key The key to lookup.
   * @return Optional.of the value if it exists.
   */
  public Optional<Tensor> get(String key) {
    if (!closed) {
      return Optional.ofNullable(map.get(key));
    } else {
      throw new IllegalStateException("Result is closed");
    }
  }

  /**
   * Metadata about the run.
   *
   * <p>A <a
   * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunMetadata
   * protocol buffer</a>.
   */
  public Optional<RunMetadata> getMetadata() {
    return Optional.ofNullable(metadata);
  }

  /**
   * Creates a Result from the names and values produced by {@link Session.Runner#run()}.
   *
   * @param names The output names.
   * @param values The output values.
   * @param metadata The run metadata, may be null.
   */
  Result(List<String> names, List<Tensor> values, RunMetadata metadata) {
    this.map = new LinkedHashMap<>();
    this.list = new ArrayList<>(values);

    if (names.size() != values.size()) {
      throw new IllegalArgumentException(
          "Expected same number of names and values, found names.length = "
              + names.size()
              + ", values.length = "
              + values.size());
    }

    for (int i = 0; i < names.size(); i++) {
      Tensor old = this.map.put(names.get(i), values.get(i));
      if (old != null) {
        throw new IllegalArgumentException(
            "Name collision in the result set, two outputs are named '" + names.get(i) + "'");
      }
    }
    this.metadata = metadata;
    this.closed = false;
  }

  /**
   * Creates a Result from the names and values.
   *
   * @param outputs The run outputs.
   */
  Result(LinkedHashMap<String, Tensor> outputs) {
    this.map = outputs;
    this.list = new ArrayList<>(outputs.size());
    for (Map.Entry<String, Tensor> e : outputs.entrySet()) {
      list.add(e.getValue());
    }
    this.metadata = null;
    this.closed = false;
  }

  private final Map<String, Tensor> map;

  private final List<Tensor> list;

  private final RunMetadata metadata;

  private boolean closed;

  private static final Logger logger = Logger.getLogger(Result.class.getName());
}
