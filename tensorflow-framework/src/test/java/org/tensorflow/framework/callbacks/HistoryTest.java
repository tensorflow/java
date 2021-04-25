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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryTest {

  @Test
  void testOnTrainBegin() {
    History instance = new History();

    instance.onTrainBegin(null);
    Map<String, Number> logs = new HashMap<>();
    logs.put("acc", 0.99);
    logs.put("err", 0.012345);
    int totalEpochs = 100;
    for (int epoch = 0; epoch < totalEpochs; epoch++) {
      instance.onEpochEnd(epoch, logs);
    }
    assertEquals(totalEpochs, instance.getEpoch().size());

    Map<String, List<Number>> results = instance.getHistory();
    assertEquals(2, results.size());
    assertEquals(results.get("acc").size(), totalEpochs);
    assertEquals(results.get("err").size(), totalEpochs);

    instance.onTrainBegin(null);
    assertEquals(0, instance.getEpoch().size());
    for (int epoch = 0; epoch < totalEpochs; epoch++) {
      instance.onEpochEnd(epoch, logs);
    }

    assertEquals(totalEpochs, instance.getEpoch().size());

    results = instance.getHistory();
    assertEquals(2, results.size());
    assertEquals(results.get("acc").size(), totalEpochs * 2);
    assertEquals(results.get("err").size(), totalEpochs * 2);
  }
}
