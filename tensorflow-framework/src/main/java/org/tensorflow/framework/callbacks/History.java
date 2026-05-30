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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Callback that records events into a History object.
 *
 * <p>This callback is automatically applied to every model. The History object gets returned by the
 * fit method of models.
 */
public class History extends Callback {
  private final Map<String, List<Number>> history = new HashMap<>();
  private final List<Integer> epoch = new ArrayList<>();

  /** Creates a History Callback */
  public History() {
    super();
  }

  /* TODO
   * Creates a History Callback
   *
   * @param params Training parameters
   * @param model the Model
   *

  public History( Model model) {=
    super(null, model);
  }
   TODO */

  /** {@inheritDoc} */
  @Override
  public void onTrainBegin(Map<String, Number> logs) {
    epoch.clear();
  }

  /** {@inheritDoc} */
  @Override
  public void onEpochEnd(int epoch, Map<String, Number> logs) {
    Map<String, Number> localLogs = logs == null ? Collections.emptyMap() : logs;
    this.epoch.add(epoch);

    logs.entrySet()
        .forEach(
            e -> {
              List<Number> item = history.get(e.getKey());
              if (item == null) {
                item = new ArrayList<>();
                history.put(e.getKey(), item);
              }
              item.add(e.getValue());
            });
  }

  /**
   * Gets the History map for each log value
   *
   * @return the History map for each log value
   */
  public Map<String, List<Number>> getHistory() {
    return history;
  }

  /**
   * Gets the history of epochs
   *
   * @return the history of epochs
   */
  public List<Integer> getEpoch() {
    return epoch;
  }
}
