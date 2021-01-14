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
 ==============================================================================
 */
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;

/**
 * Unit tests for {@link TensorScope}
 */
public class TensorScopeTest {

  private static TFloat32 makeTensor(long size) {
    return TFloat32.tensorOf(Shape.of(size), x -> {
      for (long i = 0; i < size; i++) {
        x.setFloat(0, i);
      }
    });
  }

  @Test
  public void testBasicScope() {
    TensorScope scope = new TensorScope();

    TFloat32 tensor = makeTensor(10);
    TFloat32 detachTensor = makeTensor(10);
    detachTensor.detach();

    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    assertFalse(detachTensor.isAttached());
    assertFalse(detachTensor.isClosed());

    scope.close();

    assertTrue(tensor.isClosed());
    assertTrue(scope.isClosed());
    assertFalse(detachTensor.isClosed());
    detachTensor.close();
  }

  @Test
  public void testNestedScope() {
    TensorScope outerScope = new TensorScope();
    TensorScope scope = new TensorScope();

    TFloat32 tensor = makeTensor(10);
    TFloat32 detachTensor = makeTensor(10);
    detachTensor.detach();

    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    assertFalse(detachTensor.isAttached());
    assertFalse(detachTensor.isClosed());

    outerScope.close();

    assertTrue(tensor.isClosed());
    assertTrue(scope.isClosed());
    assertTrue(outerScope.isClosed());
    assertFalse(detachTensor.isClosed());
    detachTensor.close();
  }

  @Test
  public void testAttach(){
    TensorScope firstScope = new TensorScope();
    TFloat32 tensor = makeTensor(10);
    TensorScope secondScope = new TensorScope(tensor);

    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    secondScope.close();

    assertTrue(tensor.isClosed());
    firstScope.close();
  }

  @Test
  public void testNoAutoAttach(){
    TensorScope scope = new TensorScope(false);
    TFloat32 tensor = makeTensor(10);
    assertFalse(tensor.isAttached());

    TFloat32 detachTensor = makeTensor(10);
    assertFalse(detachTensor.isAttached());

    scope.attach(detachTensor);
    assertTrue(detachTensor.isAttached());

    detachTensor.detach();
    assertFalse(detachTensor.isAttached());

    tensor.close();
    detachTensor.close();
    scope.close();
  }


}
