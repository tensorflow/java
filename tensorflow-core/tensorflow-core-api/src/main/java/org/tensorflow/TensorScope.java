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
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;


/**
 * A scope that can be used to manage tensor resources.  Any tensors created between a scope's creation and calling
 * {@code close()}, that haven't been detached, are guaranteed to be closed with the scope (even if they are created in
 * a sub-scope).  Tensors may be manually closed earlier without issue.
 * <p>
 * Tensors are automatically tracked on creation.  A tensor can me manually added to a scope with {@link
 * TensorScope#attach(Tensor)} or {@link Tensor#attachToCurrentScope()}, or by passing them to {@link
 * TensorScope#TensorScope(Tensor...)}.  The tensor will then be closed when the first of it's managing scopes closes.
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
   * Returns {@code scopeStack.get().peek()}, the last opened scope not yet closed on this thread.
   */
  static TensorScope getCurrentScope() {
    return scopeStack.get().peek();
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
   * Create a new tensor scope with the given thread locality.
   */
  public TensorScope() {
    localScopeStack = scopeStack.get();

    synchronized (TensorScope.class) {
      allScopes.add(this);
    }
    localScopeStack.push(this);
  }

  /**
   * Create a new tensor scope with the given thread locality, and attach the given tensors.
   */
  public TensorScope(Tensor... tensors) {
    this();
    attach(tensors);
  }

  /**
   * Attach a tensor to this scope.  This happens automatically to tensors that are created in the scope.
   */
  public void attach(Tensor t) {
    RawTensor rt = t.asRawTensor();
    rt.attached = true;
    tensors.add(rt);
  }


  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   */
  public void attach(Tensor... tensors) {
    if (tensors != null) {
      for (Tensor t : tensors) {
        attach(t);
      }
    }
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

  private boolean closed = false;
  private final Set<RawTensor> tensors = new HashSet<>();
  private final Deque<TensorScope> localScopeStack;
}
