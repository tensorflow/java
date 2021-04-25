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

import org.tensorflow.framework.callbacks.util.ProgressBar;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Callback that prints metrics to console. */
public class ProgbarLogger extends Callback {

  private final ProgressBar.CountMode unit;
  private Set<String> statefulMetrics;
  private int seen = 0;
  private ProgressBar progbar = null;
  private Integer target = null;
  private ProgressBar.VerboseMode verbose = ProgressBar.VerboseMode.VERBOSE;
  private int epochs = 1;
  private boolean calledInFit = false;

  // TODO wire these up to Model
  private final IntSupplier getTrainCounter = null;
  private final IntSupplier getTestCounter = null;
  private final IntSupplier getPredictCounter = null;

  private PrintWriter writer = null;

  /** Create a ProgbarLogger */
  public ProgbarLogger() {
    this(null, null, ProgressBar.CountMode.SAMPLES, (List<String>) null);
  }

  /**
   * Create a ProgbarLogger
   *
   * @param mode Whether the progress bar should count SAMPLES seen or STEPS (batches) seen.
   */
  public ProgbarLogger(ProgressBar.CountMode mode) {
    this(null, null, mode, (List<String>) null);
  }

  /**
   * Create a ProgbarLogger
   *
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgbarLogger(List<String> statefulMetrics) {
    this(null, null, ProgressBar.CountMode.SAMPLES, statefulMetrics);
  }

  /**
   * Create a ProgbarLogger
   *
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgbarLogger(String... statefulMetrics) {
    this(null, null, ProgressBar.CountMode.SAMPLES, Arrays.asList(statefulMetrics));
  }

  /**
   * Create a ProgbarLogger
   *
   * @param mode Whether the progress bar should count SAMPLES seen or STEPS (batches) seen.
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgbarLogger(ProgressBar.CountMode mode, List<String> statefulMetrics) {
    this(null, null, mode, statefulMetrics);
  }

  /**
   * Create a ProgbarLogger
   *
   * @param mode Whether the progress bar should count SAMPLES seen or STEPS (batches) seen.
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgbarLogger(ProgressBar.CountMode mode, String... statefulMetrics) {
    this(null, null, mode, Arrays.asList(statefulMetrics));
  }

  /**
   * Create a ProgbarLogger
   *
   * @param params Training parameters
   */
  public ProgbarLogger(Map<String, Object> params) {
    this(params, null, ProgressBar.CountMode.SAMPLES, (List<String>) null);
  }

  /**
   * Create a ProgbarLogger
   *
   * @param params Training parameters
   * @param model Reference of the model being trained.
   */
  public ProgbarLogger(Map<String, Object> params, Object model) {
    this(params, model, ProgressBar.CountMode.SAMPLES, (List<String>) null);
  }

  /**
   * Create a ProgbarLogger
   *
   * @param params Training parameters
   * @param model Reference of the model being trained.
   * @param mode Whether the progress bar should count SAMPLES seen or STEPS (batches) seen.
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgbarLogger(
      Map<String, Object> params,
      Object model,
      ProgressBar.CountMode mode,
      String... statefulMetrics) {
    this(params, model, mode, Arrays.asList(statefulMetrics));
  }

  /**
   * Create a ProgbarLogger
   *
   * @param params Training parameters
   * @param model Reference of the model being trained.
   * @param unit Whether the progress bar should count SAMPLES seen or STEPS (batches) seen.
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgbarLogger(
      Map<String, Object> params,
      Object model,
      ProgressBar.CountMode unit,
      List<String> statefulMetrics) {
    // TODO super(params, model);

    this.unit = unit;
    this.statefulMetrics =
        statefulMetrics != null ? new HashSet<>(statefulMetrics) : new HashSet<>();
    setParams(params);
  }

  /** {@inheritDoc} */
  @Override
  public final void setParams(Map<String, Object> params) {
    if (params == null) {
      return;
    }
    super.setParams(params);
    verbose =
        ((ProgressBar.VerboseMode) params.getOrDefault("verbose", ProgressBar.VerboseMode.VERBOSE));
    epochs = (Integer) params.getOrDefault("epochs", 1);
    target =
        unit == ProgressBar.CountMode.STEPS
            ? (Integer) params.get("steps")
            : (Integer) params.get("samples");
    writer = (PrintWriter) params.get("writer");

    if (target == null) {
      /* TODO wire into Model
      getTrainCounter = () -> model.getTrainCounter();
      getTestCounter = () -> model.getTestCounter();
      getPredictCounter = () -> model.getPredictCounter();

       */
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onTrainBegin(Map<String, Number> logs) {
    // When this logger is called inside fit, validation is silent.
    calledInFit = true;
  }

  /** {@inheritDoc} */
  @Override
  public void onTestBegin(Map<String, Number> logs) {
    if (!calledInFit) {
      resetProgBar();
      maybeInitProgbar();
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onPredictBegin(Map<String, Number> logs) {
    resetProgBar();
    maybeInitProgbar();
  }

  /** {@inheritDoc} */
  @Override
  public void onEpochBegin(int epoch, Map<String, Number> logs) {
    resetProgBar();
    maybeInitProgbar();

    if (verbose != ProgressBar.VerboseMode.SILENT && epochs > 1) {
      Logger.getLogger(ProgbarLogger.class.getName())
          .log(Level.INFO, String.format("Epoch %d/%d", (epoch + 1), epochs));
    }
  }

  @Override
  public void onTrainBatchEnd(int batch, Map<String, Number> logs) {
    batchUpdateProgbar(batch, logs);
  }

  @Override
  public void onTestBatchEnd(int batch, Map<String, Number> logs) {
    if (!calledInFit) {
      batchUpdateProgbar(batch, logs);
    }
  }

  @Override
  public void onPredictBatchEnd(int batch, Map<String, Number> logs) {
    // Don't pass prediction results.
    super.onPredictBatchEnd(batch, null);
  }

  /** {@inheritDoc} */
  @Override
  public void onEpochEnd(int epoch, Map<String, Number> logs) {
    finalizeProgbar(logs, getTrainCounter);
  }

  /** {@inheritDoc} */
  @Override
  public void onTestEnd(Map<String, Number> logs) {
    if (!calledInFit) {
      finalizeProgbar(logs, getTestCounter);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void onPredictEnd(Map<String, Number> logs) {
    finalizeProgbar(logs, getPredictCounter);
  }

  /**
   * Updates the {@link ProgressBar}
   *
   * @param batch the batch number
   * @param logs loss or metric results
   */
  private void batchUpdateProgbar(int batch, Map<String, Number> logs) {
    Map<String, Number> llogs = logs == null ? Collections.emptyMap() : logs;
    maybeInitProgbar();

    if (unit == ProgressBar.CountMode.STEPS) {
      seen = batch + 1;
    } else {
      // make shallow copy
      llogs = new HashMap<>(llogs);
      Number batchSize = llogs.getOrDefault("size", 0);
      Number numSteps = llogs.getOrDefault("num_steps", 1);
      llogs.remove("batch");
      int addSeen = numSteps.intValue() * batchSize.intValue();
      seen += addSeen;
    }

    if (verbose != ProgressBar.VerboseMode.SILENT) {
      progbar.update(seen, llogs, false);
    }
  }

  /**
   * Finalizes the Progess Bar
   *
   * @param logs results to apply
   * @param getCounter gets the counter from the model
   */
  private void finalizeProgbar(Map<String, Number> logs, IntSupplier getCounter) {
    if (progbar != null) {
      Integer counter = null;
      if (target == null) {
        if (getCounter != null) {
          int counterValue = getCounter.getAsInt();
          if (unit == ProgressBar.CountMode.SAMPLES) {
            Number size = logs.getOrDefault("", 1);
            counterValue *= size.intValue();
          }
          counter = counterValue;
        }
        target = counter == null ? seen : counter;
        progbar.setTarget(target);
      }
      progbar.update(seen, logs, true);
    }
  }

  private void resetProgBar() {
    seen = 0;
    progbar = null;
  }

  private void maybeInitProgbar() {
    if (statefulMetrics == null) {
      /* TODO - Model
      if(model != null) {
        statefulMetrics = new ArrayList<>();
        model.getMetrics().forEach(m -> statefulMetrics.add(m.getName));
      }else {
       */
      statefulMetrics = new HashSet<>();
      // TODO - Model }
    }
    if (progbar == null) {
      if (writer == null) {
        progbar = new ProgressBar(target, verbose, unit, statefulMetrics);
      } else {
        progbar =
            new ProgressBar(
                writer,
                target,
                ProgressBar.DEFAULT_WIDTH,
                verbose,
                ProgressBar.DEFAULT_INTERVAL,
                unit,
                statefulMetrics);
      }
    }
  }
}
