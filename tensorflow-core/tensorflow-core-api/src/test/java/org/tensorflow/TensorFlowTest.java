/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.tensorflow.proto.framework.OpList;

/** Unit tests for {@link org.tensorflow.TensorFlow}. */
public class TensorFlowTest {

  @Test
  public void version() {
    assertTrue(TensorFlow.version().length() > 0);
  }

  @Test
  public void registeredOpList() {
    assertNotNull(TensorFlow.registeredOpList());
  }

  @Test
  public void loadLibrary() {
    File customOpLibrary = Paths.get("").resolve("bazel-bin/libcustom_ops_test.so").toFile();

    // Disable this test if the custom op library is not available. This may happen on some
    // platforms (e.g. Windows) or when using a development profile that skips the native build
    assumeTrue(customOpLibrary.exists());

    try (Graph g = new Graph()) {
      // Build a graph with an unrecognized operation.
      try {
        g.baseScope().opBuilder("MyTest", "MyTest").build();
        fail("should not be able to construct graphs with unregistered ops");
      } catch (IllegalArgumentException e) {
        // expected exception
      }

      // Load the library containing the operation.
      OpList opList = TensorFlow.loadLibrary(customOpLibrary.getAbsolutePath());
      assertNotNull(opList);
      assertEquals(1, opList.getOpCount());
      assertEquals(opList.getOpList().get(0).getName(), "MyTest");

      // Now graph building should succeed.
      g.baseScope().opBuilder("MyTest", "MyTest").build();
    }
  }
}
