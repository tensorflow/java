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

import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract base class used to build new callbacks.
 *
 * <p>The logs map that callback methods take as argument will contain keys for quantities relevant
 * to the current batch or epoch (see method-specific docstrings).
 *
 * <p>This class has empty implementations for {@code onTrainBatchBegin/End}, {@code
 * onTrainBegin/End}, {@code onTestBatchBegin/End}, {@code onTestBegin/End}, {@code
 * onPredictBatchBegin/End}, and {@code onPredictBegin/End}. Subclasses should override these
 * methods for specific processing.
 */
public abstract class Callback {
  protected final Map<String, Object> params;
  // TODO  protected Model model;

  /** Creates a Callback */
  protected Callback() {
    this(Collections.emptyMap());
  }

  /**
   * Creates a Callback
   *
   * @param params Training parameters
   */
  protected Callback(Map<String, Object> params) {
    this.params = params;
  }

  /* TODO with Model
   * Creates a Callback
   *
   * @param params Training parameters
   * @param model the Model
   */
  /* TODO  with Model
  protected Callback(Map<String, Object> params, Model model) {=
    this.params = params;
    this.model = model;
  }
   */

  /**
   * Performs custom processing at the the start of an epoch. This method should only be called
   * during TRAIN mode.
   *
   * @param epoch index of epoch.
   * @param logs metric results
   */
  @SuppressWarnings("unused")
  public void onEpochBegin(int epoch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the end of an epoch. This method should only be called during
   * TRAIN mode.
   *
   * @param epoch index of epoch.
   * @param logs metric results for this training epoch, and for the validation epoch if validation
   *     is performed. Validation result keys are prefixed with {@code val_}.
   */
  @SuppressWarnings("unused")
  public void onEpochEnd(int epoch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the beginning of a training batch in {@code model.fit} methods.
   *
   * @param batch the batch index
   * @param logs Has keys {@code batch} and {@code size} representing the current batch number and
   *     the size of the batch.
   */
  @SuppressWarnings("unused")
  public void onTrainBatchBegin(int batch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the end of a training batch in {@code model.fit} methods.
   *
   * @param batch index of batch within the current epoch.
   * @param logs Metric results for this batch.
   */
  @SuppressWarnings("unused")
  public void onTrainBatchEnd(int batch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the beginning of training.
   *
   * @param logs metric results
   */
  @SuppressWarnings("unused")
  public void onTrainBegin(Map<String, Number> logs) {}

  /**
   * Performs custom processing at the end of training.
   *
   * @param logs metric results
   */
  @SuppressWarnings("unused")
  public void onTrainEnd(Map<String, Number> logs) {}

  /**
   * Performs custom processing at the beginning of a batch in {@code model.evaluate} methods. Also
   * Performs custom processing at the beginning of a validation batch in the {@code fit} methods,
   * if validation data is provided.
   *
   * @param batch the batch number
   * @param logs Has keys {@code batch} and {@code size} representing the current batch number and
   *     the size of the batch.
   */
  @SuppressWarnings("unused")
  public void onTestBatchBegin(int batch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the end of a batch in {@code model.evaluate} methods. Also Performs
   * custom processing at the end of a validation batch in the {@code fit} methods, if validation
   * data is provided.
   *
   * @param batch the batch number
   * @param logs Metric results for this batch.
   */
  @SuppressWarnings("unused")
  public void onTestBatchEnd(int batch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the beginning of evaluation or validation.
   *
   * @param logs metric results
   */
  @SuppressWarnings("unused")
  public void onTestBegin(Map<String, Number> logs) {}

  /**
   * Performs custom processing at the end of evaluation or validation.
   *
   * @param logs metric results
   */
  @SuppressWarnings("unused")
  public void onTestEnd(Map<String, Number> logs) {}

  /**
   * Performs custom processing at the beginning of a batch in {@code model.predict} methods.
   *
   * @param batch index of batch within the current epoch.
   * @param logs Has keys {@code batch} and {@code size} representing the current batch number and
   *     the size of the batch.
   */
  @SuppressWarnings("unused")
  public void onPredictBatchBegin(int batch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the end of a batch in {@code model.predict} methods.
   *
   * @param batch index of batch within the current epoch.
   * @param logs Metric results for this batch.
   */
  @SuppressWarnings("unused")
  public void onPredictBatchEnd(int batch, Map<String, Number> logs) {}

  /**
   * Performs custom processing at the beginning of prediction.
   *
   * @param logs metric results
   */
  @SuppressWarnings("unused")
  public void onPredictBegin(Map<String, Number> logs) {}

  /**
   * Performs custom processing at the end of prediction.
   *
   * @param logs metric results
   */
  @SuppressWarnings("unused")
  public void onPredictEnd(Map<String, Number> logs) {}

  /**
   * Gets a monitor value from the value logs
   *
   * @param logs the value logs
   * @param monitor the monitor to fetch
   * @return the monitor value, returns null if the monitor value is not in logs.
   */
  @SuppressWarnings("unchecked")
  protected Number getMonitorValue(Map<String, Number> logs, String monitor) {
    logs = logs == null ? Collections.EMPTY_MAP : logs;
    Number monitorValue = logs.get(monitor);
    if (monitorValue != null) {
      Logger.getLogger(getClass().getName())
          .log(
              Level.WARNING,
              String.format(
                  "Early stopping conditioned on metric `%s` which is not available. Available metrics are: %s",
                  monitor, String.join(",", logs.keySet())));
    }
    return monitorValue;
  }

  /**
   * Gets the params
   *
   * @return the params
   */
  public Map<String, Object> getParams() {
    return params;
  }

  /**
   * Gets the model
   *
   * @return the model
   */
  /* TODO
  public Model getModel() {
    return model;
  TODO */

  /**
   * Sets the model
   *
   * @param model the model
   */
  /* TODO
  public void setModel(Model model) {
    this.model = model;
  }
  TODO */
}
