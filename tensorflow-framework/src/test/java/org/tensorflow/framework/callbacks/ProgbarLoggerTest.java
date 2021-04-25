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
import org.tensorflow.framework.callbacks.util.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;

class ProgbarLoggerTest {

  @Test
  void testNoTarget() {
    File tmpFile = null;
    try {
      tmpFile = File.createTempFile("tf-test-progbar", ".txt");
      System.out.println(tmpFile);
      try (PrintWriter writer = new PrintWriter(new FileWriter(tmpFile))) {
        int numEpochs = 1;
        int numSteps = 10;
        Map<String, Object> params = new HashMap<>();

        params.put("verbose", ProgressBar.VerboseMode.VERBOSE);
        params.put("size", numSteps);
        params.put("num_steps", numSteps);
        params.put("writer", writer);
        ProgbarLogger instance =
            new ProgbarLogger(params, null, ProgressBar.CountMode.STEPS, Arrays.asList("acc"));

        Map<String, Number> logs = new HashMap<>();
        logs.put("acc", 0.95);
        instance.onTrainBegin(null);
        for (int epoch = 0; epoch < numEpochs; epoch++) {
          instance.onEpochBegin(epoch, null);
          for (int step = 0; step < numSteps; step++) {
            instance.onTrainBatchBegin(step, logs);
            try {
              Thread.sleep(100);
            } catch (InterruptedException ignore) {
            }
            instance.onTrainBatchEnd(step, logs);
          }
          // instance.onEpochEnd(epoch, logs);
        }
        instance.onTrainEnd(null);
      } catch (IOException ex) {
        fail(ex);
      }
      List<String> results = readResults(tmpFile);
      //      1/Unknown - 0s 105ms/steps - acc: 0.9500
      //     10/Unknown - 1s 104ms/steps - acc: 0.9500
      Pattern p1 =
          Pattern.compile("     [1 ][0-9]/Unknown - [0-9]s [1-9][0-9][0-9]ms/steps - acc: 0.9500");

      results.forEach(
          line -> {
            if (!line.trim().isEmpty()) {
              Matcher m = p1.matcher(line);
              if (!m.matches()) {
                fail("unexpected output \"" + line + "\"");
              }
            }
          });
    } catch (IOException ex) {
      fail(ex);
    } finally {
      if (tmpFile != null) {
        // tmpFile.delete();
      }
    }
  }

  @Test
  void testTarget() {

    File tmpFile = null;
    try {
      tmpFile = File.createTempFile("tf-test-progbar", ".txt");
      try (PrintWriter writer = new PrintWriter(new FileWriter(tmpFile))) {
        int numEpochs = 10;
        int numSteps = 10;
        Map<String, Object> params = new HashMap<>();

        params.put("verbose", ProgressBar.VerboseMode.VERBOSE);
        params.put("size", numSteps);
        params.put("num_steps", numSteps);
        params.put("steps", numSteps);
        params.put("writer", writer);
        ProgbarLogger instance =
            new ProgbarLogger(params, null, ProgressBar.CountMode.STEPS, Arrays.asList("acc"));

        Map<String, Number> logs = new HashMap<>();
        logs.put("acc", 0.88);

        instance.onTrainBegin(null);
        for (int epoch = 0; epoch < numEpochs; epoch++) {
          instance.onEpochBegin(epoch, null);
          for (int step = 0; step < numSteps; step++) {
            instance.onTrainBatchBegin(step, logs);
            try {
              Thread.sleep(10);
            } catch (InterruptedException ignore) {
            }
            instance.onTrainBatchEnd(step, logs);
          }

          instance.onEpochEnd(epoch, logs);
        }
        instance.onTrainEnd(null);
      }

      List<String> results = readResults(tmpFile);
      // 1/10 [==>...........................] - 0s - ETA: 0s - acc: 0.8800
      Pattern p1 = Pattern.compile(" [1-9]/10 \\[==*>\\.*\\] - 0s - ETA: 0s - acc: 0.8800");
      // 10/10 [==============================] - 0s 12ms/steps - acc: 0.8800
      Pattern p2 = Pattern.compile("10/10 \\[==*\\] - 0s [1-9][0-9]*ms/steps - acc: 0.8800");
      String finalLine = "10/10 [==============================] - 0s - ETA: 0s - acc: 0.8800";
      results.forEach(
          line -> {
            if (!line.trim().isEmpty()) {
              Matcher m = p1.matcher(line);
              if (!m.matches()) {
                m = p2.matcher(line);
                if (!m.matches()) {
                  if (!line.equals(finalLine)) {
                    fail("unexpected output \"" + line + "\"");
                  }
                }
              }
            }
          });

    } catch (IOException ex) {
      fail(ex);
    } finally {
      if (tmpFile != null) {
        tmpFile.delete();
      }
    }
  }

  private List<String> readResults(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException ex) {
      fail("cannot read tmp file", ex);
    }
    return null; // should not happen
  }
}
