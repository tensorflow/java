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

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;


/**
 * A scope that can be used to manage tensor resources.  If auto-attach is used, any tensors created between a scope's
 * creation and calling {@code close()}, that haven't been detached, are guaranteed to be closed with the scope (even if
 * they are created in a sub-scope).  Tensors may be manually closed earlier without issue.
 * <p>
 * When auto-attach is true, tensors are automatically tracked on creation.  A tensor can me manually added to a scope
 * with {@link TensorScope#attach(Tensor)} or {@link Tensor#attachToCurrentScope()}.  The tensor will then be closed when the first of it's managing scopes closes.
 * <p>
 * {@link Tensor#detach()} detaches the tensor from all scopes, requiring the user to close it manually or attach it to
 * another scope.
 * <p>
 * Note that scope management is thread local, except for detach, which will detach even from scopes on other threads.
 */
public class TensorScope implements AutoCloseable {

  private static final Set<TensorScope> allScopes = Collections.newSetFromMap(new WeakHashMap<>());

  private static final ThreadLocal<Deque<TensorScope>> scopeStack = ThreadLocal.withInitial(ArrayDeque::new);

  /**
   * Attach the tensor to the most recent scope that accepts automatic attachment.
   *
   * @return true if attached.
   */
  static boolean autoAttach(Tensor tensor) {
    Iterator<TensorScope> iterator = scopeStack.get().descendingIterator();
    while (iterator.hasNext()) {
      TensorScope scope = iterator.next();
      if (scope.autoAttach) {
        scope.attach(tensor);
        return true;
      }
    }
    return false;
  }

  /**
   * Return true if there is a scope that accepts auto attachment on the stack.
   */
  public static boolean hasAutoScope() {
    Iterator<TensorScope> iterator = scopeStack.get().descendingIterator();
    while (iterator.hasNext()) {
      TensorScope scope = iterator.next();
      if (scope.autoAttach) {
        return true;
      }
    }
    return false;
  }

  /**
   * Detaches the given tensor from any scopes managing it, requiring it to be manually closed.
   */
  public static void detach(Tensor t) {
    RawTensor raw = t.asRawTensor();
    synchronized (TensorScope.class) {
      allScopes.forEach(x -> x.detachTensor(raw));
    }
    raw.attached = false;
  }

  /**
   * Create a new tensor scope.  If {@code autoAttach} is false, will not automatically manage tensors.
   *
   * @see TensorScope
   */
  public TensorScope(boolean autoAttach) {
    this.autoAttach = autoAttach;

    synchronized (TensorScope.class) {
      allScopes.add(this);
    }

    localScopeStack = scopeStack.get();
    localScopeStack.push(this);
  }

  /**
   * Create a new tensor scope that automatically manages tensors.
   */
  public TensorScope() {
    this(true);
  }

  /**
   * Attach a tensor to this scope.  This happens automatically to tensors that are created in the scope.
   * @return this
   */
  public TensorScope attach(Tensor t) {
    RawTensor rt = t.asRawTensor();
    rt.attached = true;
    tensors.add(rt);

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   * @return this
   */
  public TensorScope attach(Tensor... tensors) {
    if (tensors != null) {
      for (Tensor t : tensors) {
        attach(t);
      }
    }

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   * @return this
   */
  public TensorScope attach(HasTensors tensors) {
    tensors.tensors().forEach(this::attach);

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   * @return this
   */
  public TensorScope attach(HasTensors... tensors) {
    if (tensors != null) {
      for (HasTensors ht : tensors) {
        attach(ht);
      }
    }

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   * @return this
   */
  public TensorScope attach(Iterable<Tensor> tensors) {
    tensors.forEach(this::attach);

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   * @return this
   */
  public TensorScope attach(Iterable<Tensor>... tensors) {
    if (tensors != null) {
      for (Iterable<Tensor> ht : tensors) {
        attach(ht);
      }
    }

    return this;
  }

  private void detachTensor(Tensor t) {
    tensors.remove(t.asRawTensor());
  }

  private void closeScope() {
    tensors.forEach(Tensor::close);

    synchronized (TensorScope.class) {
      allScopes.remove(this);
    }

    closed = true;
  }

  /**
   * Closes this scope and its tensors, and any inner scopes.
   */
  @Override
  public void close() {
    if (closed) {
      return;
    }

    if (!localScopeStack.contains(this)) {
      throw new IllegalStateException("This scope is not on the scope stack, but was not closed."
          + "  This should not be possible.");
    }

    while (true) {
      TensorScope ts = localScopeStack.removeLast();
      ts.closeScope();
      if (ts == this) {
        return;
      }
    }
  }

  /**
   * Gets whether the scope is closed.
   */
  public boolean isClosed() {
    return closed;
  }

  private final boolean autoAttach;
  private boolean closed = false;
  private final Set<RawTensor> tensors = new HashSet<>();
  private final Deque<TensorScope> localScopeStack;
}
