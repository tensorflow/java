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
package org.tensorflow.framework.callbacks.util;

import java.io.Console;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Displays a progress bar.
 *
 * <p>Output may be sent to {@link Console} if one is present, otherwise it is sent to {@link
 * System#out}. Whether a Java virtual machine has a console is dependent upon the underlying
 * platform.
 */
public class ProgressBar {
  public static final int DEFAULT_WIDTH = 30;
  public static final VerboseMode DEFAULT_VERBOSE = VerboseMode.VERBOSE;

  // NOTE: Console may be null
  public static final double DEFAULT_INTERVAL = 50; // msecs
  public static final CountMode DEFAULT_COUNT_MODE = CountMode.STEPS;
  private static final double MICRO_SECONDS = 1E-3;
  private static final long MILLI_SECOND = 1L;
  private static final long SECOND = 1000L;
  private static final long MINUTE = 60L * SECOND;
  private static final long HOUR = 60L * MINUTE;
  private final int width;
  private final VerboseMode verbose;
  private final long interval;
  private final Set<String> statefulMetrics;
  private final CountMode unit;
  private final Map<String, List<Number>> values = new HashMap<>();
  private final List<String> valuesOrdered = new ArrayList<>();
  private final long start = System.currentTimeMillis();
  // will be null if java system console is not present
  private final Console console;
  // defaults to stdout if console is null;
  private final PrintWriter writer;
  private final boolean dynamicDisplay;
  private Integer target;
  private int totalWidth;
  private int seenSoFar;
  private long lastUpdate;
  private Long timeAfterFirstStep;
  /** Create a ProgressBar */
  public ProgressBar() {
    this(null, DEFAULT_WIDTH, DEFAULT_VERBOSE, DEFAULT_INTERVAL, DEFAULT_COUNT_MODE, null);
  }
  /**
   * Create a ProgressBar
   *
   * @param target Total number of steps expected, null if unknown.
   */
  public ProgressBar(Integer target) {
    this(target, DEFAULT_WIDTH, DEFAULT_VERBOSE, DEFAULT_INTERVAL, DEFAULT_COUNT_MODE, null);
  }

  /**
   * Create a ProgressBar
   *
   * @param target Total number of steps expected, null if unknown.
   * @param verbose Verbosity mode
   * @param unit Display name for step counts, "step" or "sample".
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgressBar(
      Integer target, VerboseMode verbose, CountMode unit, Set<String> statefulMetrics) {
    this(target, DEFAULT_WIDTH, verbose, DEFAULT_INTERVAL, unit, statefulMetrics);
  }

  /**
   * Create a ProgressBar
   *
   * @param target Total number of steps expected, null if unknown.
   * @param width Progress bar width on screen.
   * @param verbose Verbosity mode, false is silent
   * @param interval Minimum visual progress update interval (in milliseconds).
   * @param unit Display name for step counts, "step" or "sample".
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgressBar(
      Integer target,
      int width,
      VerboseMode verbose,
      double interval,
      CountMode unit,
      Set<String> statefulMetrics) {
    this.target = target;
    this.width = width;
    this.verbose = verbose;
    this.interval = (long) interval;
    this.unit = unit;
    this.statefulMetrics = statefulMetrics == null ? Collections.emptySet() : statefulMetrics;
    switch (verbose) {
      case VERBOSE:
        console = System.console();
        writer = console != null ? null : new PrintWriter(System.out);
        break;
      case SEMI_VERBOSE:
        writer = new PrintWriter(System.out);
        console = null;
        break;
      default:
        writer = null;
        console = null;
        break;
    }
    dynamicDisplay = console != null;
  }

  /**
   * Create a ProgressBar
   *
   * @param writer the writer to use rather than the defaults of {@link Console} or {@link
   *     System#out}
   * @param target Total number of steps expected, null if unknown.
   * @param width Progress bar width on screen.
   * @param verbose Verbosity mode, false is silent
   * @param interval Minimum visual progress update interval (in milliseconds).
   * @param unit Display name for step counts, "step" or "sample".
   * @param statefulMetrics names of metrics that should not be averaged over an epoch. Metrics in
   *     this list will be logged as-is. All others will be averaged over time (e.g. loss, etc). If
   *     not provided, defaults to the Model's metrics.
   */
  public ProgressBar(
      PrintWriter writer,
      Integer target,
      int width,
      VerboseMode verbose,
      double interval,
      CountMode unit,
      Set<String> statefulMetrics) {
    this.target = target;
    this.width = width;
    this.verbose = verbose;
    this.interval = (long) interval;
    this.unit = unit;
    this.statefulMetrics = statefulMetrics == null ? Collections.emptySet() : statefulMetrics;
    switch (verbose) {
      case VERBOSE:
      case SEMI_VERBOSE:
        console = null;
        this.writer = writer;
        break;
      default:
        this.writer = null;
        console = null;
        break;
    }
    dynamicDisplay = false;
  }

  /**
   * Updates the progress bar.
   *
   * @param seen Index of current step.
   * @param logValues map of log values to apply
   */
  public void update(int seen, Map<String, Number> logValues) {
    update(seen, logValues, null);
  }

  /**
   * Updates the progress bar.
   *
   * @param current Index of current step, if null the current step is calculated.
   * @param logValues Map of log values
   * @param finalize Whether this is the last update for the progress bar. If null, defaults to
   *     {@code current >= self.target}.
   */
  public void update(Integer current, Map<String, Number> logValues, Boolean finalize) {
    boolean shouldFinalize;

    int iCurrent = current == null ? 0 : current;

    if (finalize == null) {
      if (target == null) {
        shouldFinalize = false;
      } else {
        shouldFinalize = iCurrent >= target;
      }
    } else {
      shouldFinalize = finalize;
    }

    Map<String, Number> lValues = logValues == null ? Collections.emptyMap() : logValues;
    lValues.forEach(
        (key, value) -> {
          if (!this.valuesOrdered.contains(key)) {
            this.valuesOrdered.add(key);
          }
          if (!this.statefulMetrics.contains(key)) {
            // In the case that progress bar doesn't have a target value in the first
            // epoch, both onTrainBatchEnd and onEpochEnd will be called, which will
            // cause 'current' and 'seenSoFar' to have the same value. Force
            // the minimal value to 1 here, otherwise stateful_metric will be 0s.
            int valueBase = Math.max(iCurrent - seenSoFar, 1);
            // stores the pair, the value and its base, (10, 10) == 1, 10,100 = .1
            if (!values.containsKey(key)) {
              values.put(key, Arrays.asList(value.doubleValue() * valueBase, valueBase));
            } else {
              List<Number> currentValues = values.get(key);
              double v1 = currentValues.get(0).doubleValue() + value.doubleValue() * valueBase;
              int b1 = currentValues.get(1).intValue() + valueBase;
              values.put(key, Arrays.asList(v1, b1));
            }
          } else {
            values.put(key, Arrays.asList(value, 1));
          }
        });
    this.seenSoFar = iCurrent;
    long now = System.currentTimeMillis();
    // convert time to seconds
    double timeSinceStart = (double) (now - this.start) / SECOND;
    StringBuilder info = new StringBuilder(String.format(" - %.0fs", timeSinceStart));

    if (this.verbose == VerboseMode.VERBOSE) {
      if (now - lastUpdate < this.interval && !shouldFinalize) {
        return;
      }

      int prevTotalWidth = this.totalWidth;
      if (dynamicDisplay) {
        // backspace to beginning of  line
        this.console.printf("%s\r", repeat("\b", prevTotalWidth));
      } else {
        writer.print('\n');
      }
      StringBuilder bar = new StringBuilder();
      if (target != null) {
        int numDigits = target > 0 ? (int) Math.log10(target) + 1 : 1;
        String formatStr = String.format("%%%dd/%%d [", numDigits);
        bar.append(String.format(formatStr, iCurrent, target));
        double prog = (double) iCurrent / target;
        int progWidth = (int) (this.width * prog);
        if (progWidth > 0) {
          bar.append(repeat("=", progWidth - 1));
          if (iCurrent < target) {
            bar.append('>');
          } else {
            bar.append('=');
          }
          bar.append(repeat(".", width - progWidth));
          bar.append(']');
        }
      } else {
        bar.append(String.format("%7d/Unknown", iCurrent));
      }
      this.totalWidth = bar.length();
      print(bar.toString());

      // NOTE: in millis, Python is in seconds
      double timePerUnit = estimateStepDuration(current, now);
      if (target == null || shouldFinalize) {
        if (timePerUnit >= SECOND || timePerUnit == 0.0) { // seconds
          info.append(
              String.format(" %.0fs/%s", timePerUnit / SECOND, unit.toString().toLowerCase()));
        } else if (timePerUnit >= 1) { // milliseconds
          info.append(String.format(" %.0fms/%s", timePerUnit, unit.toString().toLowerCase()));
        } else { // microseconds
          info.append(
              String.format(" %.0fus/%s", timePerUnit * SECOND, unit.toString().toLowerCase()));
        }
      } else {
        double eta = timePerUnit * (target - iCurrent);
        String etaFormat;
        if (eta > HOUR) { // greater than an hour

          etaFormat =
              String.format(
                  "%d:%02d:%02d", // hh:mm:ss
                  (int) (eta / HOUR), (int) ((eta % HOUR) / MINUTE), (int) (eta % MINUTE) / SECOND);
        } else if (eta > MINUTE) {
          etaFormat =
              String.format(
                  "%d:%02d", // mm:ss
                  (int) (eta / MINUTE), (int) (eta % MINUTE) / SECOND);
        } else {
          etaFormat = String.format("%ds", (int) (eta / SECOND)); // seconds
        }
        info.append(" - ETA: ").append(etaFormat);
      }

      this.valuesOrdered.forEach(
          key -> {
            info.append(String.format(" - %s:", key));
            List<Number> vals = values.get(key);
            double avg = vals.get(0).doubleValue() / Math.max(1.0, vals.get(1).doubleValue());
            if (Math.abs(avg) > 1e-3) { // Normal number
              info.append(String.format(" %.4f", avg));
            } else { // Floating point notation.
              info.append(String.format(" %.4e", avg));
            }
          });
      totalWidth += info.length();
      if (prevTotalWidth > totalWidth) {
        info.append(repeat(" ", prevTotalWidth - totalWidth));
      }
      if (shouldFinalize) {
        info.append('\n');
      }
      print(info.toString(), true);

    } else if (verbose == VerboseMode.SEMI_VERBOSE) {
      if (shouldFinalize) {
        int numDigits = target > 0 ? (int) Math.log10(target) + 1 : 1;
        String formatStr = String.format("%%%dd/%%d [", numDigits);
        final StringBuilder tmpInfo =
            new StringBuilder(String.format(formatStr, iCurrent, target)).append(info);
        valuesOrdered.forEach(
            k -> {
              tmpInfo.append(String.format(" - %s:", k));
              List<Number> valEntry = values.get(k);
              // TODO average
              double avg =
                  valEntry.get(0).doubleValue() / Math.max(1.0, valEntry.get(1).doubleValue());
              if (avg > 1e-3) {
                tmpInfo.append(String.format(" %.4f", avg));
              } else {
                tmpInfo.append(String.format(" %.4ef", avg));
              }
            });
        print(tmpInfo.toString(), true);
      }
    }
    this.lastUpdate = now;
  }

  /**
   * Print the string to the output stream without flushing
   *
   * @param s the string
   */
  private void print(String s) {
    print(s, false);
  }

  /**
   * Print the string to the output stream
   *
   * @param s the string
   * @param doFlush whether to flush the output after printing.
   */
  private void print(String s, boolean doFlush) {
    if (this.console != null) {
      this.console.printf(s);
      if (doFlush) {
        this.console.flush();
      }
    } else {
      writer.print(s);
      if (doFlush) {
        writer.flush();
      }
    }
  }

  /**
   * Estimate the duration of a single step, in millis
   *
   * <p>Given the step number, current, and the corresponding time, now, this function returns an
   * estimate for how long a single step takes. If this is called before one step has been completed
   * (i.e.{@code current == 0}) then zero is given as an estimate. The duration estimate ignores the
   * duration of the (assumed to be non-representative) first step for estimates when more steps are
   * available (i.e. {@code current 1}).
   *
   * @param current Index of current step
   * @param now the current time
   * @return the estimate of the duration of a single step.
   */
  private double estimateStepDuration(Integer current, long now) {
    if (current != null) {
      // there are a few special scenarios here:
      // 1) somebody is calling the progress bar without ever supplying step 1
      // 2) somebody is calling the progress bar and supplies step one mulitple
      //    times, e.g. as part of a finalizing call
      // in these cases, we just fall back to the simple calculation
      double timePerUnit;
      if (current == 0) {
        timePerUnit = (now - start);
      } else if (timeAfterFirstStep != null && current > 2) {
        timePerUnit = (double) (now - timeAfterFirstStep) / (double) (current - 1);
      } else {
        timePerUnit = (double) (now - start) / (double) current;
      }
      if (current == 1) {
        timeAfterFirstStep = now;
      }
      return timePerUnit;

    } else {
      return 0;
    }
  }

  /**
   * Repeats the string s, count times.
   *
   * @param s the string to repeat
   * @param count the number of times to repeat
   * @return the repeated string
   */
  private String repeat(String s, int count) {
    // TODO JDK 11 update with s.repeat(count)
    return new String(new char[count]).replace("\0", s);
  }

  /** updates the progress bar by one unit */
  public void increment() {
    add(1);
  }

  /**
   * updates the progress bar by one unit
   *
   * @param logValues map of log values to apply
   */
  public void increment(Map<String, Number> logValues) {
    add(1, logValues);
  }

  /**
   * update the progress bar
   *
   * @param n the number of units to add to the current number
   */
  public void add(int n) {
    add(n, null);
  }

  /**
   * update the progress bar
   *
   * @param n the number of units to add to the current number
   * @param logValues map of log values to apply
   */
  public void add(int n, Map<String, Number> logValues) {
    this.update(this.seenSoFar + n, logValues);
  }

  /** @return the target */
  public Integer getTarget() {
    return target;
  }

  /** @param target the target to set */
  public void setTarget(Integer target) {
    this.target = target;
  }

  public enum CountMode {
    /** the progress bar should count steps */
    STEPS,
    /** the progress bar should count samples */
    SAMPLES
  }

  /** Verbosity mode */
  public enum VerboseMode {
    /** Do not log output */
    SILENT,
    /** verbose, try to use {@link Console}, if available */
    VERBOSE,
    /** Semi verbose, Use {@link System#out} */
    SEMI_VERBOSE
  }
}
