/*
 Copyright 2026 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.global.tensorflow;
import org.tensorflow.proto.OpDef;
import org.tensorflow.proto.OpList;

/**
 * Regression test for PR #632: Registering >10 custom gradients must work; all registered opTypes
 * must be present in the native gradient registry (TensorFlow.hasGradient(opType) becomes true).
 */
public class CustomGradientRegistryCapacityTest {

  private static List<String> listAllOpTypes() {
    TF_Buffer buf = tensorflow.TF_GetAllOpList();
    try {
      OpList opList = OpList.parseFrom(buf.dataAsByteBuffer());
      List<String> names = new ArrayList<>(opList.getOpCount());
      for (OpDef op : opList.getOpList()) {
        names.add(op.getName());
      }
      Collections.sort(names);
      return names;
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TF_GetAllOpList()", e);
    }
  }

  @Test
  public void registerMoreThanTenGradients_thenHasGradientIsTrue() {

    // 1) Discover op types that currently have NO gradient registered.
    List<String> ops = listAllOpTypes();

    List<String> noGrad = new ArrayList<>();
    for (String opType : ops) {
      if (!TensorFlow.hasGradient(opType)) {
        // Avoid internal/private ops to reduce risk of weirdness (optional)
        if (!opType.startsWith("_")) {
          noGrad.add(opType);
        }
      }
    }

    // 2) Pick 11 opTypes (stable order: alphabetical).
    //    We intentionally pick from "noGrad" so the test is meaningful:
    //    before: hasGradient=false, after register: true.
    assertTrue(noGrad.size() >= 11, "Need at least 11 ops with no gradient in this runtime.");

    List<String> selected = noGrad.subList(0, 11);

    // 3) Before: ensure hasGradient is false
    for (String opType : selected) {
      assertFalse(
          TensorFlow.hasGradient(opType),
          "Precondition failed: expected no gradient for " + opType);
    }

    // 4) Register 11 custom gradients (simple zerosLike per input)
    for (String opType : selected) {
      TensorFlow.registerCustomGradient(
          opType,
          (tf, op, gradInputs) -> {
            int n = op.numInputs();
            ArrayList<org.tensorflow.Operand<?>> grads = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
              grads.add(tf.zerosLike(op.input(i)));
            }
            return grads;
          });
    }

    // 5) After: ensure hasGradient is true for all
    for (String opType : selected) {
      assertTrue(
          TensorFlow.hasGradient(opType), "Expected gradient to be registered for " + opType);
    }
  }
}
