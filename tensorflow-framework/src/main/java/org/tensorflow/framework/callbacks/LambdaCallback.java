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

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Callback for creating simple, custom callbacks on-the-fly.
 *
 * <p>Example:
 *
 * <pre>{@code
 * LambdaCallbacks batchPrintCallback = new LambdaCallbacks();
 * batchPrintCallback.setOnTrainBatchBegin((batch, logs)->
 *     System.out.println("Batch: " + batch + " started");
 * }</pre>
 *
 * <p>This callback is constructed with anonymous functions that will be called at the appropriate
 * time. Note that the callbacks expects positional arguments, as:
 *
 * <ul>
 *   <li><code>onEpochBegin</code> and <code>onEpochEnd</code> expect two positional arguments:
 *       <code>epoch</code>, <code>logs</code>
 *   <li><code>onBatchBegin</code> and <code>onBatchEnd</code> expect two positional arguments:
 *       <code>batch</code>, <code>logs</code>
 *   <li><code>onTrainBegin</code> and <code>onTrainEnd</code> expect one positional argument:
 *       <code>logs</code>
 * </ul>
 */
public class LambdaCallback extends Callback {

  /** Called at the beginning of every epoch. expect two positional arguments: `epoch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onEpochBegin;

  /** Called at the end of every epoch. expect two positional arguments: `epoch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onEpochEnd;

  /** Called at the beginning of every batch. expect two positional arguments: `batch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onTrainBatchBegin;

  /** called at the end of every batch. expect two positional arguments: `batch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onTrainBatchEnd;

  /** called at the beginning of model training. expect one positional argument: `logs` */
  private Consumer<Map<String, Number>> onTrainBegin;

  /** called at the end of model training. expect one positional argument: `logs` */
  private Consumer<Map<String, Number>> onTrainEnd;

  /** Called at the beginning of every batch. expect two positional arguments: `batch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onTestBatchBegin;

  /** called at the end of every batch. expect two positional arguments: `batch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onTestBatchEnd;

  /** called at the beginning of model training. expect one positional argument: `logs` */
  private Consumer<Map<String, Number>> onTestBegin;

  /** called at the end of model training. expect one positional argument: `logs` */
  private Consumer<Map<String, Number>> onTestEnd;

  /** Called at the beginning of every batch. expect two positional arguments: `batch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onPredictBatchBegin;

  /** called at the end of every batch. expect two positional arguments: `batch`, `logs` */
  private BiConsumer<Integer, Map<String, Number>> onPredictBatchEnd;

  /** called at the beginning of model training. expect one positional argument: `logs` */
  private Consumer<Map<String, Number>> onPredictBegin;

  /** called at the end of model training. expect one positional argument: `logs` */
  private Consumer<Map<String, Number>> onPredictEnd;

  /** Creates a LambdaCallbacks callback */
  public LambdaCallback() {
    super();
  }

  /**
   * Creates a LambdaCallbacks callback
   *
   * @param params Training parameters
   */
  public LambdaCallback(Map<String, Object> params) {
    super(params);
  }

  /** {@inheritDoc} */
  @Override
  public void onEpochBegin(int epoch, Map<String, Number> logs) {
    if (this.onEpochBegin != null) {
      this.onEpochBegin.accept(epoch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onEpochEnd(int epoch, Map<String, Number> logs) {
    if (this.onEpochEnd != null) {
      this.onEpochEnd.accept(epoch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTrainBatchBegin(int batch, Map<String, Number> logs) {
    if (this.onTrainBatchBegin != null) {
      this.onTrainBatchBegin.accept(batch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTrainBatchEnd(int batch, Map<String, Number> logs) {
    if (this.onTrainBatchEnd != null) {
      this.onTrainBatchEnd.accept(batch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTrainBegin(Map<String, Number> logs) {
    if (this.onTrainBegin != null) {
      this.onTrainBegin.accept(logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTrainEnd(Map<String, Number> logs) {
    if (this.onTrainEnd != null) {
      this.onTrainEnd.accept(logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTestBatchBegin(int batch, Map<String, Number> logs) {
    if (this.onTestBatchBegin != null) {
      this.onTestBatchBegin.accept(batch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTestBatchEnd(int batch, Map<String, Number> logs) {
    if (this.onTestBatchEnd != null) {
      this.onTestBatchEnd.accept(batch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTestBegin(Map<String, Number> logs) {
    if (this.onTestBegin != null) {
      this.onTestBegin.accept(logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTestEnd(Map<String, Number> logs) {
    if (this.onTestEnd != null) {
      this.onTestEnd.accept(logs);
    }
  }


  /** {@inheritDoc} */
  @Override
  public void onPredictBatchBegin(int batch, Map<String, Number> logs) {
    if (this.onPredictBatchBegin != null) {
      this.onPredictBatchBegin.accept(batch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onPredictBatchEnd(int batch, Map<String, Number> logs) {
    if (this.onPredictBatchEnd != null) {
      this.onPredictBatchEnd.accept(batch, logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onPredictBegin(Map<String, Number> logs) {
    if (this.onPredictBegin != null) {
      this.onPredictBegin.accept(logs);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onPredictEnd(Map<String, Number> logs) {
    if (this.onPredictEnd != null) {
      this.onPredictEnd.accept(logs);
    }
  }


  /**
   * Gets the onEpochBegin lambda function
   *
   * @return the onEpochBegin lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnEpochBegin() {
    return onEpochBegin;
  }

  /**
   * Sets the onEpochBegin lambda function
   *
   * @param onEpochBegin lambda function to set
   */
  public void setOnEpochBegin(BiConsumer<Integer, Map<String, Number>> onEpochBegin) {
    this.onEpochBegin = onEpochBegin;
  }

  /**
   * Gets the onEpochEnd lambda function
   *
   * @return the onEpochEnd lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnEpochEnd() {
    return onEpochEnd;
  }

  /**
   * Sets the onEpochEnd lambda function
   *
   * @param onEpochEnd the lambda function
   */
  public void setOnEpochEnd(BiConsumer<Integer, Map<String, Number>> onEpochEnd) {
    this.onEpochEnd = onEpochEnd;
  }

  /**
   * Gets the onTrainBatchBegin lambda function
   *
   * @return the onTrainBatchBegin lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnTrainBatchBegin() {
    return onTrainBatchBegin;
  }

  /**
   * Sets the onTrainBatchBegin lambda function
   *
   * @param onTrainBatchBegin the lambda function
   */
  public void setOnTrainBatchBegin(BiConsumer<Integer, Map<String, Number>> onTrainBatchBegin) {
    this.onTrainBatchBegin = onTrainBatchBegin;
  }

  /**
   * Gets the onTrainBatchEnd lambda function
   *
   * @return the onTrainBatchEnd lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnTrainBatchEnd() {
    return onTrainBatchEnd;
  }

  /**
   * Sets the onTrainBatchEnd lambda function
   *
   * @param onTrainBatchEnd the onTrainBatchEnd lambda function
   */
  public void setOnTrainBatchEnd(BiConsumer<Integer, Map<String, Number>> onTrainBatchEnd) {
    this.onTrainBatchEnd = onTrainBatchEnd;
  }

  /**
   * Gets the onTrainBegin lambda function
   *
   * @return the onTrainBegin lambda function
   */
  public Consumer<Map<String, Number>> getOnTrainBegin() {
    return onTrainBegin;
  }

  /**
   * Sets the onTrainBegin lambda function
   *
   * @param onTrainBegin the onTrainBegin lambda function
   */
  public void setOnTrainBegin(Consumer<Map<String, Number>> onTrainBegin) {
    this.onTrainBegin = onTrainBegin;
  }

  /**
   * Gets the onTrainEnd lambda function
   *
   * @return the onTrainEnd lambda function
   */
  public Consumer<Map<String, Number>> getOnTrainEnd() {
    return onTrainEnd;
  }

  /**
   * Sets the onTrainEnd lambda function
   *
   * @param onTrainEnd the onTrainEnd lambda function
   */
  public void setOnTrainEnd(Consumer<Map<String, Number>> onTrainEnd) {
    this.onTrainEnd = onTrainEnd;
  }

  /**
   * Gets the onTestBatchBegin lambda function
   *
   * @return the onTestBatchBegin lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnTestBatchBegin() {
    return onTestBatchBegin;
  }

  /**
   * Sets the onTestBatchBegin lambda function
   *
   * @param onTestBatchBegin the lambda function
   */
  public void setOnTestBatchBegin(BiConsumer<Integer, Map<String, Number>> onTestBatchBegin) {
    this.onTestBatchBegin = onTestBatchBegin;
  }

  /**
   * Gets the onTestBatchEnd lambda function
   *
   * @return the onTestBatchEnd lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnTestBatchEnd() {
    return onTestBatchEnd;
  }

  /**
   * Sets the onTestBatchEnd lambda function
   *
   * @param onTestBatchEnd the onTestBatchEnd lambda function
   */
  public void setOnTestBatchEnd(BiConsumer<Integer, Map<String, Number>> onTestBatchEnd) {
    this.onTestBatchEnd = onTestBatchEnd;
  }

  /**
   * Gets the onTestBegin lambda function
   *
   * @return the onTestBegin lambda function
   */
  public Consumer<Map<String, Number>> getOnTestBegin() {
    return onTestBegin;
  }

  /**
   * Sets the onTestBegin lambda function
   *
   * @param onTestBegin the onTestBegin lambda function
   */
  public void setOnTestBegin(Consumer<Map<String, Number>> onTestBegin) {
    this.onTestBegin = onTestBegin;
  }

  /**
   * Gets the onTestBegin lambda function
   *
   * @return the onTestEnd lambda function
   */
  public Consumer<Map<String, Number>> onTestEnd() {
    return onTestEnd;
  }

  /**
   * Sets the onTestEnd lambda function
   *
   * @param onTestEnd the onTestBegin lambda function
   */
  public void setOnTestEnd(Consumer<Map<String, Number>> onTestEnd) {
    this.onTestEnd = onTestEnd;
  }


  /**
   * Gets the onPredictBatchBegin lambda function
   *
   * @return the onPredictBatchBegin lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnPredictBatchBegin() {
    return onPredictBatchBegin;
  }

  /**
   * Sets the onPredictBatchBegin lambda function
   *
   * @param onPredictBatchBegin the lambda function
   */
  public void setOnPredictBatchBegin(BiConsumer<Integer, Map<String, Number>> onPredictBatchBegin) {
    this.onPredictBatchBegin = onPredictBatchBegin;
  }

  /**
   * Gets the onPredictBatchEnd lambda function
   *
   * @return the onPredictBatchEnd lambda function
   */
  public BiConsumer<Integer, Map<String, Number>> getOnPredictBatchEnd() {
    return onPredictBatchEnd;
  }

  /**
   * Sets the onPredictBatchEnd lambda function
   *
   * @param onPredictBatchEnd the onPredictBatchEnd lambda function
   */
  public void setOnPredictBatchEnd(BiConsumer<Integer, Map<String, Number>> onPredictBatchEnd) {
    this.onPredictBatchEnd = onPredictBatchEnd;
  }

  /**
   * Gets the onPredictBegin lambda function
   *
   * @return the onPredictBegin lambda function
   */
  public Consumer<Map<String, Number>> getOnPredictBegin() {
    return onPredictBegin;
  }

  /**
   * Sets the onPredictBegin lambda function
   *
   * @param onPredictBegin the onPredictBegin lambda function
   */
  public void setOnPredictBegin(Consumer<Map<String, Number>> onPredictBegin) {
    this.onPredictBegin = onPredictBegin;
  }

  /**
   * Gets the onPredictEnd lambda function
   *
   * @return the onPredictEnd lambda function
   */
  public Consumer<Map<String, Number>> getOnPredictEnd() {
    return onPredictEnd;
  }

  /**
   * Sets the onPredictEnd lambda function
   *
   * @param onPredictEnd the onPredictEnd lambda function
   */
  public void setOnPredictEnd(Consumer<Map<String, Number>> onPredictEnd) {
    this.onPredictEnd = onPredictEnd;
  }


}
