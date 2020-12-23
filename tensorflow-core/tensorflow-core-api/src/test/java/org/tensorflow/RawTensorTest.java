/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

public class RawTensorTest {

  @Test
  public void rawToTypedTensor() {
    RawTensor rawTensor = RawTensor.allocate(TFloat32.class, Shape.of(2, 2), -1);
    TFloat32 floatTensor = rawTensor.asTypedTensor();
    assertSame(floatTensor.asRawTensor(), rawTensor);
    try {
      TInt32 intTensor = rawTensor.asTypedTensor();
      fail();
    } catch (ClassCastException e) {
      // ok
    }
  }

  @Test
  public void allocateTensorWithSize() {
    try (RawTensor rawTensor = RawTensor.allocate(TFloat32.class, Shape.of(2, 2), 16)) {
      assertEquals(16, rawTensor.numBytes());
    }
    try (RawTensor rawTensor = RawTensor.allocate(TFloat32.class, Shape.of(2, 2), 100)) {
      assertEquals(100, rawTensor.numBytes());
    }
    try (RawTensor rawTensor = RawTensor.allocate(TFloat32.class, Shape.of(2, 2), 10)) {
      fail();
    } catch (IllegalArgumentException e) {
      // ok
    }
    try (RawTensor rawTensor = RawTensor.allocate(TString.class, Shape.of(2, 2), 100)) {
      assertEquals(100, rawTensor.numBytes());
    }
  }

  @Test
  public void allocateTensorWithoutSize() {
    try (RawTensor rawTensor = RawTensor.allocate(TFloat32.class, Shape.of(2, 2), -1)) {
      assertEquals(16, rawTensor.numBytes());
      // ok
    }
    try (RawTensor rawTensor = RawTensor.allocate(TString.class, Shape.of(2, 2), -1)) {
      fail();
    } catch (IllegalArgumentException e) {
      // ok
    }
  }
}
