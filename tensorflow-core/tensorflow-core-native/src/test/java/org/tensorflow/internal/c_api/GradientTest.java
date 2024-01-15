/*
 Copyright 2024 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/
package org.tensorflow.internal.c_api;

import org.bytedeco.javacpp.PointerPointer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tensorflow.internal.c_api.global.tensorflow.TFJ_HasGradient;
import static org.tensorflow.internal.c_api.global.tensorflow.TFJ_RegisterCustomGradient;

// WARNING: Gradient registry in native library is stateful across all tests
public class GradientTest {

  @Test
  public void testExistingGradientCheck() {
    assertTrue(TFJ_HasGradient("Cast"));
  }

  @Test
  public void testNonExistingGradientCheck() {
    assertFalse(TFJ_HasGradient("NthElement"));
  }

  @Test
  public void testNonExistingOpGradientCheck() {
    assertFalse(TFJ_HasGradient("IDontExists"));
  }

  @Test
  public void registerCustomGradientAdapter() {
    assertTrue(TFJ_RegisterCustomGradient("Merge", new TFJ_GradFuncAdapter()));
  }

  @Test
  public void registerCustomGradientAdapterFailedIfGradFuncAlreadyRegistered() {
    assertFalse(TFJ_RegisterCustomGradient("Add", new TFJ_GradFuncAdapter()));
  }
}
