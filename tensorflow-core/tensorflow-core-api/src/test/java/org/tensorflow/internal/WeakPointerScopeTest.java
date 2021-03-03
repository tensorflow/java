package org.tensorflow.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.junit.jupiter.api.Test;
import org.tensorflow.EagerSession;

public class WeakPointerScopeTest {

  @Test
  public void resourcesAttachedAreFreedOnScopeClose() {
    Pointer pointer = new IntPointer(10L);
    assertEquals(0, pointer.referenceCount());

    try (WeakPointerScope scope = new WeakPointerScope()) {
      scope.attach(pointer);
      assertEquals(1, pointer.referenceCount());
    }
    assertTrue(pointer.isNull());
  }

  @Test
  public void resourcesDetachedAreNotFreedOnScopeCloseWhenRetained() {
    Pointer pointer = new IntPointer(10L);

    try (WeakPointerScope scope = new WeakPointerScope()) {
      scope.attach(pointer);
      scope.detach(pointer.retainReference());
    }
    assertFalse(pointer.isNull());
    assertEquals(1, pointer.referenceCount());
    pointer.deallocate();
  }

  @Test
  public void resourcesDetachedAreFreedWhenNotRetained() {
    Pointer pointer = new IntPointer(10L);

    try (WeakPointerScope scope = new WeakPointerScope()) {
      scope.attach(pointer);

      scope.detach(pointer);
      assertTrue(pointer.isNull());
    }
  }

  @Test
  public void attachingResourceMoreThanOnceHasNoEffect() {
    Pointer pointer = new IntPointer(10L);

    try (WeakPointerScope scope = new WeakPointerScope()) {
      scope.attach(pointer);
      scope.attach(pointer);
      assertEquals(1, pointer.referenceCount());

      Pointer pointerCopy = new Pointer(pointer);
      assertEquals(1, pointerCopy.referenceCount());
      scope.attach(pointerCopy);
      assertEquals(1, pointerCopy.referenceCount());
    }
    assertTrue(pointer.isNull());
  }

  @Test
  public void detachingUnattachedResourceHasNoEffect() {
    Pointer pointer = new IntPointer(10L);
    pointer.retainReference();
    assertEquals(1, pointer.referenceCount());

    try (WeakPointerScope scope = new WeakPointerScope()) {
      scope.detach(pointer);
      assertEquals(1, pointer.referenceCount());
    }
    assertFalse(pointer.isNull());
    pointer.deallocate();
  }

  @Test
  public void operationOnClosedScopeFails() {
    Pointer pointer = new IntPointer(10L);
    WeakPointerScope scope = new WeakPointerScope();
    scope.close();

    assertThrows(IllegalStateException.class, () -> scope.attach(pointer));
    assertThrows(IllegalStateException.class, () -> scope.detach(pointer));
    assertThrows(IllegalStateException.class, () -> scope.close());

    pointer.deallocate();
  }

  @Test
  public void attachingResourceDoesNotPreventItToBeGarbageCollected() throws InterruptedException {
    try (WeakPointerScope scope = new WeakPointerScope()) {
      Pointer pointer = new IntPointer(10L);
      scope.attach(pointer);
      System.gc();
      Thread.sleep(50);

      long before = Pointer.totalBytes();
      pointer = null;
      System.gc();
      Thread.sleep(50);
      long after = Pointer.totalBytes();

      assertEquals(4 * 10L, before - after);
    }
  }
}
