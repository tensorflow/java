/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================

*/
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MultiReleaseTest {

  @Test
  public void testMultirelease() {
    String javaVersion = System.getProperty("java.version");
    System.out.println("Testing on Java version " + javaVersion);
    int value = MRTest.version();
    if (javaVersion.compareTo("11") >= 0) {
      assertEquals(11, value);
    } else {
      assertEquals(8, value);
    }
  }
}
