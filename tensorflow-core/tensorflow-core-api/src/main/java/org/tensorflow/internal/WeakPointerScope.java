package org.tensorflow.internal;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import org.bytedeco.javacpp.Pointer;

/**
 * A minimalist pointer scope only keeping weak references to its elements.
 *
 * <p>As opposed to {@link org.bytedeco.javacpp.PointerScope}, instances of this class will not
 * prevent the garbage collector to free the memory of a pointer that is no longer reachable, even
 * if it has been attached to the scope.</p>
 *
 * <p>When the scope is closed, all pointers that are still valid will be automatically deallocated
 * while those already garbage-collected will be ignored.</p>
 */
public class WeakPointerScope implements AutoCloseable {

  /**
   * Attach a pointer to this scope.
   *
   * <p>Pointers attached to the scope will be automatically freed once the scope is closed, unless
   * they have been already released by the garbage collector</p>
   *
   * <p>It this {@code pointer} was already attached to this scope, this method has no effect.</p>
   *
   * @param pointer pointer to attach
   * @throws IllegalStateException if that scope has already been closed
   */
  public void attach(Pointer pointer) {
    checkScope();
    if (pointers.add(pointer)) {
      pointer.retainReference();
    }
  }

  /**
   * Detach a pointer from this scope.
   *
   * <p>Detaching a pointer from the scope will prevent its memory to be freed when closing the
   * scope.</p>
   *
   * <p>If this {@code pointer} is not attached to this scope, this method has no effect.</p>
   *
   * @param pointer pointer to detach
   * @throws IllegalStateException if that scope has already been closed
   */
  public void detach(Pointer pointer) {
    checkScope();
    if (pointers.remove(pointer)) {
      pointer.releaseReference();
    }
  }

  @Override
  public synchronized void close() {
    checkScope();
    pointers.forEach(Pointer::releaseReference);
    pointers = null;
  }

  private Set<Pointer> pointers = Collections.newSetFromMap(new WeakHashMap<>());

  private void checkScope() {
    if (pointers == null) {
      throw new IllegalStateException("Pointer scope has been closed");
    }
  }
}
