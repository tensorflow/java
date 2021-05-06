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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.tensorflow.types.TFloat64;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class CSVLoggerTest {

  @Test
  public void testStandAlone() {
    try {
      int epoch = 0;
      double[] values = {0.95, 0.90, 0.85, 0.90, 0.99, Double.NaN};
      File tmpFile = File.createTempFile("tf-test", ".csv");
      Map<String, Number> logs = new HashMap<>();
      try (CSVLogger<TFloat64> csvLogger = new CSVLogger<>(tmpFile)) {
        csvLogger.onTrainBegin(null);
        for (int i = 0; i < values.length; i++) {
          logs.put("accuracy", values[epoch]);
          csvLogger.onEpochEnd(epoch++, logs);
        }

        try (Reader reader = new FileReader(tmpFile)) {
          Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
          int iv = 0;
          for (CSVRecord record : records) {
            String epochStr = record.get("epoch");
            assertNotNull(epochStr);
            String valueStr = record.get("accuracy");
            assertNotNull(valueStr);
            assertEquals(iv, Integer.valueOf(epochStr));
            double v = Double.valueOf(valueStr);
            assertEquals(values[iv++], v, 0e-6);
          }
        } finally {
          tmpFile.deleteOnExit();
        }
      }

    } catch (IOException ex) {
      fail(ex);
    }
  }

  @Test
  public void testStandAlone2Vals() {
    try {
      int epoch = 0;
      double[] valuesAcc = {0.95, 0.90, 0.85, 0.90, 0.99, Double.NaN};
      double[] valuesErr = {1e-1, 1e-2, 1e-3, 1e-4, 1e-5, Double.NaN};
      File tmpFile = File.createTempFile("tf-test", ".csv");
      Map<String, Number> logs = new HashMap<>();
      try (CSVLogger<TFloat64> csvLogger = new CSVLogger<>(tmpFile)) {
        csvLogger.onTrainBegin(null);
        for (int i = 0; i < valuesAcc.length; i++) {
          logs.put("accuracy", valuesAcc[epoch]);
          logs.put("error", valuesErr[epoch]);
          csvLogger.onEpochEnd(epoch++, logs);
        }

        try (Reader reader = new FileReader(tmpFile)) {
          Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
          int iv = 0;
          for (CSVRecord record : records) {
            String epochStr = record.get("epoch");
            assertNotNull(epochStr);
            String valueStr = record.get("accuracy");
            assertNotNull(valueStr);
            String errorStr = record.get("error");
            assertNotNull(errorStr);
            assertEquals(iv, Integer.valueOf(epochStr));
            double v = Double.valueOf(valueStr);
            assertEquals(valuesAcc[iv], v, 0e-6);
            double e = Double.valueOf(errorStr);
            assertEquals(valuesErr[iv], e, 0e-8);
            iv++;
          }
        } finally {
          tmpFile.deleteOnExit();
        }
      }

    } catch (IOException ex) {
      fail(ex);
    }
  }
}
