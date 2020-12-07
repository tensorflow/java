/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.tensorflow.op.Index;

public class IndexTest {
  @Test
  public void testNullConversions(){
    assertTrue(Index.slice(null, 0).isBeginMask(),
        "Passed null for slice start but didn't set begin mask");

    assertTrue(Index.slice(null, Index.point(0)).isBeginMask(),
        "Passed null for slice start but didn't set begin mask");

    assertTrue(Index.slice(null, null).isBeginMask(),
        "Passed null for slice start but didn't set begin mask");

    assertTrue(Index.slice(0, null).isEndMask(),
        "Passed null for slice end but didn't set end mask");

    assertTrue(Index.slice(Index.point(0), null).isEndMask(),
        "Passed null for slice end but didn't set end mask");

    assertTrue(Index.slice(null, null).isEndMask(),
        "Passed null for slice end but didn't set end mask");
  }
}
