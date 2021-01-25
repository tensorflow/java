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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
  public void testAttach() {
    TensorScope firstScope = new TensorScope();
    TFloat32 tensor = makeTensor(10);
    TensorScope secondScope = new TensorScope().withTensors(tensor);

    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    secondScope.close();

    assertTrue(tensor.isClosed());
    firstScope.close();
  }

  @Test
  public void testReleaseToParentScope() {
    TensorScope outerScope = new TensorScope();
    TensorScope scope = new TensorScope();

    TFloat32 tensor = makeTensor(10);

    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    scope.releaseAllToParent();

    assertTrue(scope.isClosed());
    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    outerScope.close();

    assertTrue(tensor.isClosed());
    assertTrue(outerScope.isClosed());
  }

  @Test
  public void testAttachToParentScope() {
    TensorScope outerScope = new TensorScope();
    TensorScope scope = new TensorScope();

    TFloat32 tensor = makeTensor(10);

    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    scope.release(tensor);

    scope.close();

    assertTrue(scope.isClosed());
    assertTrue(tensor.isAttached());
    assertFalse(tensor.isClosed());

    outerScope.close();

    assertTrue(tensor.isClosed());
    assertTrue(outerScope.isClosed());
  }

  @Test
  public void testWithCleanup() {
    final Tensor[] tensor = new Tensor[1];
    TensorScope.withCleanup(() -> {
      tensor[0] = makeTensor(2);
    });
    assertTrue(tensor[0].isClosed());
  }

  @Test
  public void testGetWithCleanup() {
    Tensor tensor = TensorScope.getWithCleanup(() -> makeTensor(2));
    assertTrue(tensor.isClosed());
  }

  @Test
  public void testProduceTensorWithCleanup() {
    final Tensor[] closedTensor = new Tensor[1];
    Tensor openTensor = TensorScope.produceTensorWithCleanup(() -> {
      closedTensor[0] = makeTensor(2);
      return makeTensor(3);
    });

    assertTrue(closedTensor[0].isClosed());
    assertFalse(openTensor.isClosed());
    openTensor.close();
  }

  private static class TestTensorContainer<T extends Tensor> implements TensorContainer {

    private final List<T> tensors;

    TestTensorContainer(List<T> tensors) {
      this.tensors = tensors;
    }

    @SafeVarargs
    TestTensorContainer(T... tensors) {
      this(Arrays.asList(tensors));
    }

    @Override
    public Iterable<? extends Tensor> tensors() {
      return tensors;
    }

    public List<T> getTensors() {
      return tensors;
    }
  }

  @Test
  public void testProduceTensorContainerWithCleanup() {
    final TestTensorContainer<TFloat32>[] closedTensor = new TestTensorContainer[1];
    TestTensorContainer<TFloat32> openTensor = TensorScope.produceTensorContainerWithCleanup(() -> {
      closedTensor[0] = new TestTensorContainer<>(makeTensor(2));
      return new TestTensorContainer<>(makeTensor(3));
    });

    assertTrue(closedTensor[0].getTensors().get(0).isClosed());
    assertFalse(openTensor.getTensors().get(0).isClosed());
    openTensor.getTensors().get(0).close();
  }

  @Test
  public void testProduceTensorsWithCleanup(){
    final List<TFloat32>[] closedTensor = new List[1];
    List<TFloat32> openTensor = TensorScope.produceTensorsWithCleanup(() -> {
      closedTensor[0] = Collections.singletonList(makeTensor(2));
      return Collections.singletonList(makeTensor(2));
    });

    assertTrue(closedTensor[0].get(0).isClosed());
    assertFalse(openTensor.get(0).isClosed());
    openTensor.get(0).close();
  }

}
