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

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LambdaCallbackTest {

  @Test
  void onEpochBegin() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedEpoch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnEpochBegin(
        (epoch, log) -> {
          assertEquals(expectedEpoch, epoch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onEpochBegin(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onEpochEnd() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedEpoch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnEpochEnd(
        (epoch, log) -> {
          assertEquals(expectedEpoch, epoch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onEpochEnd(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onTrainBatchBegin() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedBatch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnTrainBatchBegin(
        (batch, log) -> {
          assertEquals(expectedBatch, batch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onTrainBatchBegin(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onTrainBatchEnd() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedBatch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnTrainBatchEnd(
        (batch, log) -> {
          assertEquals(expectedBatch, batch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onTrainBatchEnd(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onTrainBegin() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnTrainBegin(
        logs -> {
          assertEquals(exceptedLog, logs);
          called.set(true);
        });

    Map<String, Number> log = new HashMap<>();
    log.put("acc", 0.98);
    instance.onTrainBegin(log);

    assertTrue(called.get());
  }

  @Test
  void onTrainEnd() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    Map<String, Number> expectedLog = new HashMap<>();
    expectedLog.put("acc", 0.98);
    instance.setOnTrainEnd(
        logs -> {
          assertEquals(expectedLog, logs);
          called.set(true);
        });
    Map<String, Number> log = new HashMap<>();
    log.put("acc", 0.98);
    instance.onTrainEnd(log);

    assertTrue(called.get());
  }

  @Test
  void onTestBatchBegin() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedBatch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnTestBatchBegin(
        (batch, log) -> {
          assertEquals(expectedBatch, batch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onTestBatchBegin(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onTestBatchEnd() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedBatch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnTestBatchEnd(
        (batch, log) -> {
          assertEquals(expectedBatch, batch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onTestBatchEnd(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onTestBegin() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnTestBegin(
        logs -> {
          assertEquals(exceptedLog, logs);
          called.set(true);
        });

    Map<String, Number> log = new HashMap<>();
    log.put("acc", 0.98);
    instance.onTestBegin(log);

    assertTrue(called.get());
  }

  @Test
  void onTestEnd() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    Map<String, Number> expectedLog = new HashMap<>();
    expectedLog.put("acc", 0.98);
    instance.setOnTestEnd(
        logs -> {
          assertEquals(expectedLog, logs);
          called.set(true);
        });
    Map<String, Number> log = new HashMap<>();
    log.put("acc", 0.98);
    instance.onTestEnd(log);

    assertTrue(called.get());
  }

  @Test
  void onPredictBatchBegin() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedBatch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnPredictBatchBegin(
        (batch, log) -> {
          assertEquals(expectedBatch, batch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onPredictBatchBegin(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onPredictBatchEnd() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    int expectedBatch = 101;
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnPredictBatchEnd(
        (batch, log) -> {
          assertEquals(expectedBatch, batch);
          assertEquals(exceptedLog, log);
          called.set(true);
        });

    Map<String, Number> epochLog = new HashMap<>();
    epochLog.put("acc", 0.98);
    instance.onPredictBatchEnd(101, epochLog);

    assertTrue(called.get());
  }

  @Test
  void onPredictBegin() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    Map<String, Number> exceptedLog = new HashMap<>();
    exceptedLog.put("acc", 0.98);
    instance.setOnPredictBegin(
        logs -> {
          assertEquals(exceptedLog, logs);
          called.set(true);
        });

    Map<String, Number> log = new HashMap<>();
    log.put("acc", 0.98);
    instance.onPredictBegin(log);

    assertTrue(called.get());
  }

  @Test
  void onPredictEnd() {
    LambdaCallback instance = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);
    Map<String, Number> expectedLog = new HashMap<>();
    expectedLog.put("acc", 0.98);
    instance.setOnPredictEnd(
        logs -> {
          assertEquals(expectedLog, logs);
          called.set(true);
        });
    Map<String, Number> log = new HashMap<>();
    log.put("acc", 0.98);
    instance.onPredictEnd(log);

    assertTrue(called.get());
  }
}
