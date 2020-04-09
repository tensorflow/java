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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.RunOptions;

/** Unit tests for {@link org.tensorflow.SavedModelBundle}. */
@RunWith(JUnit4.class)
public class SavedModelBundleTest {

  private static final String SAVED_MODEL_PATH;
  static {
    try {
      SAVED_MODEL_PATH = Paths.get(SavedModelBundleTest.class.getResource("/saved_model").toURI()).toString();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void load() {
    try (SavedModelBundle bundle = SavedModelBundle.load(SAVED_MODEL_PATH, "serve")) {
      assertNotNull(bundle.session());
      assertNotNull(bundle.graph());
      assertNotNull(bundle.metaGraphDef());
    }
  }

  @Test
  public void loadNonExistentBundle() {
    try {
      SavedModelBundle bundle = SavedModelBundle.load("__BAD__", "serve");
      bundle.close();
      fail("not expected");
    } catch (org.tensorflow.TensorFlowException e) {
      // expected exception
      assertTrue(e.getMessage().contains("Could not find SavedModel"));
    }
  }

  @Test
  public void loader() {
    try (SavedModelBundle bundle = SavedModelBundle.loader(SAVED_MODEL_PATH)
        .withTags("serve")
        .withConfigProto(sillyConfigProto())
        .withRunOptions(sillyRunOptions())
        .load()) {
      assertNotNull(bundle.session());
      assertNotNull(bundle.graph());
      assertNotNull(bundle.metaGraphDef());
    }
  }

  private static RunOptions sillyRunOptions() {
    return RunOptions.newBuilder()
        .setTraceLevel(RunOptions.TraceLevel.FULL_TRACE)
        .build();
  }

  public static ConfigProto sillyConfigProto() {
    return ConfigProto.newBuilder()
        .setInterOpParallelismThreads(1)
        .setIntraOpParallelismThreads(1)
        .build();
  }
}
