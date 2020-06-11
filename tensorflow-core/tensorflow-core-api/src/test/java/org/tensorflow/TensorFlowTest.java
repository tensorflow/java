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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.proto.framework.OpList;

/** Unit tests for {@link org.tensorflow.TensorFlow}. */
@RunWith(JUnit4.class)
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
    Path customOpsLibraryPath = Paths.get("").resolve("bazel-bin/libcustom_ops_test.so");

    // Skip this test if the custom ops library was not build (can be the case when building in
    // dev mode or on platforms that do not support custom ops, like Windows)
    assumeTrue(customOpsLibraryPath.toFile().exists());

    try (Graph g = new Graph()) {
      // Build a graph with an unrecognized operation.
      try {
        g.opBuilder("MyTest", "MyTest").build();
        fail("should not be able to construct graphs with unregistered ops");
      } catch (IllegalArgumentException e) {
        // expected exception
      }

      // Load the library containing the operation.
      OpList opList = TensorFlow.loadLibrary(customOpsLibraryPath.toString());
      assertNotNull(opList);
      assertEquals(1, opList.getOpCount());
      assertEquals(opList.getOpList().get(0).getName(), "MyTest");

      // Now graph building should succeed.
      g.opBuilder("MyTest", "MyTest").build();
    }
  }
}
