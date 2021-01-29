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

import java.util.HashSet;
import java.util.Set;


/**
 * A scope that can be used to manage tensor resources.  Any tensors created between a scope's creation and calling
 * {@code close()} that haven't been detached or attached to a different scope are guaranteed to be closed with the
 * scope (even if they are created in a sub-scope).  Tensors may be manually closed earlier without issue.
 * <p>
 * Tensors are automatically tracked on creation.  A tensor can me manually added to a scope with {@link
 * TensorScope#attach(Tensor)} or {@link Tensor#attachToCurrent()}.  A tensor may only have one scope: if it currently
 * has a scope when {@code attach} is called, it is removed from its original scope.
 * <p>
 * {@link Tensor#detach()} detaches the tensor from it's scope, requiring the user to close it manually or attach it to
 * another scope.
 * <p>
 * <b>Like Tensors, TensorScope is not thread safe.</b>
 */
public final class TensorScope implements AutoCloseable {


  /**
   * Create a new tensor scope.  If {@code autoAttach} is false, will not automatically manage tensors.
   *
   * @see TensorScope
   */
  public TensorScope() {
  }

  /**
   * Closes this scope and its tensors, and any inner scopes.
   */
  @Override
  public synchronized void close() {
    if (closed) {
      return;
    }
    tensors.forEach(Tensor::close);

    closed = true;
  }

  /**
   * Detach all of this scope's tensors, then close the scope.
   * <p>
   * <b>EXTREMELY DANGEROUS: this will close this scope, but does not close any of it's resources.</b>
   *
   * @return All of this scope's now-detached tensors
   */
  public synchronized Set<Tensor> detachAll() {
    Set<Tensor> detachedTensors = new HashSet<>(this.tensors);
    detachedTensors.forEach(TensorScope::detach);
    closed = true;
    tensors.clear();
    return detachedTensors;
  }

  public static <T extends Tensor> T detach(T tensor) {
    // ensure that I'm not attaching or detaching at the same time in different threads
    RawTensor rt = tensor.asRawTensor();
    synchronized (rt) {
      if (rt.tensorScope != null) {
        rt.tensorScope.tensors.remove(rt);
        rt.tensorScope = null;
      }
    }
    return tensor;
  }

  /**
   * @see #detach(Tensor)
   */
  public static void detach(Tensor... tensors) {
    for (Tensor t : tensors) {
      detach(t);
    }
  }

  /**
   * @see #detach(Tensor)
   */
  public static <T extends TensorContainer> T detach(T tensors) {
    detach(tensors.tensors());
    return tensors;
  }

  /**
   * @see #detach(Tensor)
   */
  public static void detach(TensorContainer... tensors) {
    for (TensorContainer ht : tensors) {
      detach(ht);
    }
  }

  /**
   * @see #detach(Tensor)
   */
  public static <T extends Iterable<? extends Tensor>> T detach(T tensors) {
    tensors.forEach(TensorScope::detach);
    return tensors;
  }

  /**
   * @see #detach(Tensor)
   */
  @SafeVarargs
  public static void detach(Iterable<? extends Tensor>... tensors) {
    for (Iterable<? extends Tensor> iterable : tensors) {
      detach(iterable);
    }
  }

  /**
   * Attach a tensor to this scope.  This happens automatically to tensors that are created in the scope.
   *
   * @return this
   */
  public synchronized <T extends Tensor> T attach(T tensor) {
    if (this.closed) {
      throw new IllegalStateException("Scope has been closed, can not attach new tensor.");
    }

    RawTensor rt = tensor.asRawTensor();
    // ensure that I'm not attaching or detaching at the same time in different threads
    synchronized (rt) {
      detach(tensor);
      rt.tensorScope = this;
      tensors.add(rt);
    }

    return tensor;
  }

  /**
   * @see #attach(Tensor)
   */
  public void attach(Tensor... tensors) {
    if (tensors != null) {
      for (Tensor t : tensors) {
        attach(t);
      }
    }
  }

  /**
   * @see #attach(Tensor)
   */
  public <T extends TensorContainer> T attach(T tensors) {
    attach(tensors.tensors());
    return tensors;
  }

  /**
   * @see #attach(Tensor)
   */
  public void attach(TensorContainer... tensors) {
    if (tensors != null) {
      for (TensorContainer ht : tensors) {
        attach(ht);
      }
    }
  }

  /**
   * @see #attach(Tensor)
   */
  public <T extends Iterable<? extends Tensor>> T attach(T tensors) {
    tensors.forEach(this::attach);

    return tensors;
  }

  /**
   * @see #attach(Tensor)
   */
  @SafeVarargs
  public final void attach(Iterable<? extends Tensor>... tensors) {
    if (tensors != null) {
      for (Iterable<? extends Tensor> ht : tensors) {
        attach(ht);
      }
    }
  }

  /**
   * @see #attach(Tensor)
   */
  public TensorScope withTensors(Tensor... tensors) {
    attach(tensors);
    return this;
  }

  /**
   * @see #attach(Tensor)
   */
  public TensorScope withTensors(TensorContainer... tensors) {
    attach(tensors);
    return this;
  }

  /**
   * @see #attach(Tensor)
   */
  @SafeVarargs
  public final TensorScope withTensors(Iterable<? extends Tensor>... tensors) {
    attach(tensors);
    return this;
  }

  /**
   * Gets whether the scope is closed.
   */
  public synchronized boolean isClosed() {
    return closed;
  }

  private boolean closed = false;
  private final Set<RawTensor> tensors = new HashSet<>();
}
