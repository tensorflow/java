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

import static org.junit.jupiter.api.Assertions.assertTrue;

class CallbackListTest {

  @Test
  public void testUpdates() {
    Map<String, Number> logs = new HashMap<>();
    logs.put("acc", 0.98);
    LambdaCallback lambdaCB = new LambdaCallback();
    final AtomicBoolean called = new AtomicBoolean(false);

    lambdaCB.setOnEpochBegin(
        (epoch, log) -> {
          called.set(true);
        });

    CallbackList instance = new CallbackList(true);
    History history = instance.getHistory();
    instance.addCallback(lambdaCB);

    instance.onTrainBegin(null);
    instance.onEpochBegin(0, logs);
    instance.onEpochEnd(0, logs);
    instance.onTrainEnd(null);

    assertTrue(history.getHistory().containsKey("acc"));
    assert (history.getHistory().get("acc").size() == 1);
    assertTrue(called.get());
  }
}
