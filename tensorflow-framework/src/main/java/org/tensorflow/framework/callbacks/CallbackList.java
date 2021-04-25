/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.callbacks;

// TODO import org.tensorflow.framework.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Container for {@link Callback} instances.
 *
 * <p>This object wraps a list of {@link Callback} instances, making it possible to call them all at
 * once via a single endpoint (e.g. {@code callbackList.onEpochEnd(...)}).
 */
public class CallbackList extends Callback {
  private final List<Callback> callbacks = new ArrayList<>();

  // TODO private final Model<? extends TFloating> model;
  private History history;

  /** Creates a CallbackList */
  public CallbackList() {
    super();
  }

  /**
   * Creates a CallbackList
   *
   * @param addHistory Whether a {@link History} callback should be added, if one does not already
   *     exist in the {@code callbacks} list.
   */
  public CallbackList(boolean addHistory) {
    addDefaultCallbacks(addHistory);
  }

  /**
   * Creates a CallbackList
   *
   * @param callbacks List of {@link Callback} instances.
   * @param addHistory Whether a {@link History} callback should be added, if one does not already
   *     exist * in the {@code callbacks} list.
   */
  public CallbackList(List<Callback> callbacks, boolean addHistory) {
    this(addHistory);
    this.callbacks.addAll(callbacks);
  }

  /* TODO add when integrated with Model
  // /**
   * Creates a CallbackList
   *
   * @param model the model these callbacks are used with.
   * @param addHistory Whether a {@link History} callback should be added, if one does not already
   *     exist in the {@code callbacks} list.
  //  *

  public CallbackList(Model<? extends TFloating> model, boolean addHistory) {
    this.model = model;
    addDefaultCallbacks(addHistory);
  }
  TODO */

  /* TODO add when integrated with Model
  // /**
   * Creates a CallbackList
   *
   * @param model the model these callbacks are used with.
   * @param callbacks List of {@link Callback} instances.
   * @param addHistory Whether a {@link History} callback should be added, if one does not already
   *     exist in the {@code callbacks} list.
  //  *

  public CallbackList(Model<? extends TFloating> model, List<Callback> callbacks, boolean addHistory) {
    this(model, addHistory);
    this.callbacks.addAll(callbacks);
  }
  TODO */

  /**
   * Adds Callback's that are always present.
   *
   * @param addHistory Whether a {@link History} callback should be added, if one does not already
   *     exist in the {@code callbacks} list.
   */
  private void addDefaultCallbacks(boolean addHistory) {
    callbacks.forEach(
        c -> {
          if (c instanceof History) {
            history = (History) c;
          }
        });
    if (history == null && addHistory) {
      history = new History();
      addCallback(history);
    }
  }

  /**
   * Adds a callback
   *
   * @param callback the callback
   */
  public void addCallback(Callback callback) {
    callbacks.add(callback);
  }

  /**
   * Adds callbacks
   *
   * @param callbacks the callbacks
   */
  public void addCallbacks(List<Callback> callbacks) {
    this.callbacks.addAll(callbacks);
  }

  /** {@inheritDoc } */
  @Override
  public void onTrainBegin(Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTrainBegin(logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onTrainEnd(Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTrainEnd(logs));
  }
  /** {@inheritDoc } */
  @Override
  public void onEpochBegin(int epoch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onEpochBegin(epoch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onEpochEnd(int epoch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onEpochEnd(epoch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onTrainBatchBegin(int batch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTrainBatchBegin(batch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onTrainBatchEnd(int batch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTrainBatchEnd(batch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onTestBatchBegin(int batch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTestBatchBegin(batch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onTestBatchEnd(int batch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTestBatchEnd(batch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onTestBegin(Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTestBegin(logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onTestEnd(Map<String, Number> logs) {
    callbacks.forEach(c -> c.onTestEnd(logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onPredictBatchBegin(int batch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onPredictBatchBegin(batch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onPredictBatchEnd(int batch, Map<String, Number> logs) {
    callbacks.forEach(c -> c.onPredictBatchEnd(batch, logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onPredictBegin(Map<String, Number> logs) {
    callbacks.forEach(c -> c.onPredictBegin(logs));
  }

  /** {@inheritDoc } */
  @Override
  public void onPredictEnd(Map<String, Number> logs) {
    callbacks.forEach(c -> c.onPredictEnd(logs));
  }

  /**
   * Gets the callbacks
   *
   * @return the callbacks
   */
  public List<Callback> getCallbacks() {
    return callbacks;
  }

  /**
   * Gets the history callback
   *
   * @return the history callback
   */
  public History getHistory() {
    return history;
  }
}
