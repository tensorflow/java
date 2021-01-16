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
import java.util.concurrent.ConcurrentHashMap;


/**
 * A scope that can be used to manage tensor resources.  Any tensors created between a scope's creation and calling
 * {@code close()} that haven't been detached or attached to a different scope are guaranteed to be closed with the
 * scope (even if they are created in a sub-scope).  Tensors may be manually closed earlier without issue.
 * <p>
 * Tensors are automatically tracked on creation.  A tensor can me manually added to a scope with {@link
 * TensorScope#attach(Tensor)} or {@link Tensor#attachToCurrentScope()}.  A tensor may only have one scope: if it
 * currently has a scope when {@code attach} is called, it is removed from its original scope.
 * <p>
 * {@link Tensor#detach()} detaches the tensor from it's scope, requiring the user to close it manually or attach it to
 * another scope.
 * <p>
 * Scopes will be inherited at thread creation, but further scope creation on different threads will be independent,
 * other than having the same parent. Closing a scope will close it's children regardless of which threads they are on.
 */
public class TensorScope implements AutoCloseable {

  private static final InheritableThreadLocal<TensorScope> currentScope = new InheritableThreadLocal<>();

  public static TensorScope currentScope() {
    TensorScope scope = currentScope.get();

    if (scope == null || !scope.closed) {
      return scope;
    }

    // scope could be closed in another thread, in which case this thread's currentScope wouldn't be updated
    while (scope != null && scope.closed) {
      scope = scope.parent;
    }
    currentScope.set(scope);
    return scope;
  }

  public static void detach(Tensor tensor) {
    // ensure that I'm not attaching or detaching at the same time in different threads
    RawTensor rt = tensor.asRawTensor();
    synchronized (rt) {
      if (rt.scope != null) {
        rt.scope.tensors.remove(rt);
        rt.scope = null;
      }
    }
  }

  /**
   * Create a new tensor scope.  If {@code autoAttach} is false, will not automatically manage tensors.
   *
   * @see TensorScope
   */
  public TensorScope() {
    this.parent = currentScope();
    currentScope.set(this);

    if (this.parent != null) {
      synchronized (this.parent) {
        this.parent.children.add(this);
      }
    }
  }

  /**
   * Attach a tensor to this scope.  This happens automatically to tensors that are created in the scope.
   *
   * @return this
   */
  public synchronized TensorScope attach(Tensor tensor) {
    if (this.closed) {
      throw new IllegalStateException("Scope has been closed, can not attach new tensor.");
    }

    RawTensor rt = tensor.asRawTensor();
    // ensure that I'm not attaching or detaching at the same time in different threads
    synchronized (rt) {
      detach(tensor);
      rt.scope = this;
      tensors.add(rt);
    }

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   *
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
   *
   * @return this
   */
  public TensorScope attach(HasTensors tensors) {
    tensors.tensors().forEach(this::attach);

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   *
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
   *
   * @return this
   */
  public TensorScope attach(Iterable<Tensor> tensors) {
    tensors.forEach(this::attach);

    return this;
  }

  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   *
   * @return this
   */
  @SafeVarargs
  public final TensorScope attach(Iterable<Tensor>... tensors) {
    if (tensors != null) {
      for (Iterable<Tensor> ht : tensors) {
        attach(ht);
      }
    }

    return this;
  }

  /**
   * Closes this scope and its tensors, and any inner scopes.
   */
  @Override
  public synchronized void close() {
    if (closed) {
      return;
    }

    children.forEach(TensorScope::close);
    tensors.forEach(Tensor::close);

    closed = true;

    if (parent != null) {
      parent.children.remove(this);
    }

    if (currentScope() == this) {
      currentScope.set(this.parent);
    }
  }

  /**
   * Release the tensors and child scopes of this scope to it's parent, <b>without closing them</b>.
   *
   * @throws IllegalStateException if this scope has no parent.
   */
  public synchronized void releaseToParent() {
    release(true);
  }

  /**
   * Release the tensors and child scopes of this scope <b>without closing them</b>, to it's parent if it has one.
   *
   * <p><b>WARNING:</b> this method may release resources without assigning them to another scope if
   * {@code requireParent} is false.  {@link #releaseToParent()} should be used instead wherever possible.
   *
   * @param requireParent Whether to require a parent scope to release resources to.
   * @throws IllegalStateException if this scope has no parent, but {@code requireParent} is true.
   */
  public synchronized void release(boolean requireParent) {
    if (closed) {
      return;
    }

    if (this.parent == null && requireParent) {
      throw new IllegalStateException("Can't release to parent: scope does not have parent.");
    }

    if (this.parent != null) {
      TensorScope newParent = this.parent;
      newParent.children.addAll(children);
      children.forEach(x -> x.parent = newParent);
      tensors.forEach(newParent::attach);
    } else {
      children.forEach(x -> x.parent = null);
      tensors.forEach(TensorScope::detach);
    }

    children.clear();
    tensors.clear();

    close();
  }

  /**
   * Gets whether the scope is closed.
   */
  public synchronized boolean isClosed() {
    return closed;
  }

  private boolean closed = false;
  private final Set<RawTensor> tensors = ConcurrentHashMap.newKeySet();
  TensorScope parent;
  private final Set<TensorScope> children = ConcurrentHashMap.newKeySet();
}
