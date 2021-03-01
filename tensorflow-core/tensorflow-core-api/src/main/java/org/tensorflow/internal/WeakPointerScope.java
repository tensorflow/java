package org.tensorflow.internal;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
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
   * @param pointer pointer to attach
   */
  public void attach(Pointer pointer) {
    pointers.add(pointer.retainReference());
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
   */
  public void detach(Pointer pointer) {
    if (pointers.remove(pointer)) {
      pointer.releaseReference();
    }
  }

  @Override
  public synchronized void close() {
    pointers.forEach(Pointer::releaseReference);
    pointers.clear();
  }

  private final Set<Pointer> pointers = Collections.newSetFromMap(new WeakHashMap<>());
}
