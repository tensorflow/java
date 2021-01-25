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

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


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

  /**
   * Runs {@code block}, then closes any tensors created during its execution.
   * <p>To release tensors, use {@link #withCleanup(Consumer)} or one of the {@code produceWithCleanup} methods.
   */
  public static void withCleanup(Runnable block) {
    TensorScope.withCleanup((scope) -> block.run());
  }

  /**
   * Runs {@code block}, then closes any tensors created during its execution (or attached to the scope).
   * <p>Tensors can be released using the passed scope.
   */
  public static void withCleanup(Consumer<TensorScope> block) {
    try (TensorScope scope = new TensorScope()) {
      block.accept(scope);
    }
  }

  /**
   * Runs {@code block} and returns the result, then closes any tensors created during its execution.
   * <p>To release tensors, use {@link #getWithCleanup(Function)} or one of the {@code produceWithCleanup} methods.
   * <p><b>Does not release or detach the result.  If you return a tensor, it will be closed unless otherwise released.</b>
   */
  public static <T> T getWithCleanup(Supplier<T> block) {
    return TensorScope.getWithCleanup((scope) -> block.get());
  }

  /**
   * Runs {@code block} and returns the result, then closes any tensors created during its execution (or attached to the
   * scope).
   * <p>Tensors can be released using the passed scope.
   * <p><b>Does not release or detach the result.  If you return a tensor, it will be closed unless otherwise released.</b>
   */
  public static <T> T getWithCleanup(Function<TensorScope, T> block) {
    try (TensorScope scope = new TensorScope()) {
      return block.apply(scope);
    }
  }

  /**
   * Runs {@code block} and releases and returns the result, then closes any <b>other</b> tensors created during its
   * execution.
   * <p>To release other tensors, use {@link #produceTensorWithCleanup(Function)}.
   *
   * @return the released result of {@code block}
   */
  public static <T extends Tensor> T produceTensorWithCleanup(Supplier<T> block) {
    return produceTensorWithCleanup((scope) -> block.get());
  }

  /**
   * Runs {@code block} and releases and returns the result, then closes any <b>other</b> tensors created during its
   * execution (or attached to the scope).
   * <p>Tensors can be released using the passed scope.
   *
   * @return the released result of {@code block}
   */
  public static <T extends Tensor> T produceTensorWithCleanup(Function<TensorScope, T> block) {
    try (TensorScope scope = new TensorScope()) {
      return scope.release(block.apply(scope));
    }
  }


  /**
   * Runs {@code block} and releases and returns the result, then closes any <b>other</b> tensors created during its
   * execution.
   * <p>To release other tensors, use {@link #produceTensorWithCleanup(Function)}.
   *
   * @return the released result of {@code block}
   */
  public static <T extends TensorContainer> T produceTensorContainerWithCleanup(Supplier<T> block) {
    return produceTensorContainerWithCleanup((scope) -> block.get());
  }

  /**
   * Runs {@code block} and releases and returns the result, then closes any <b>other</b> tensors created during its
   * execution (or attached to the scope).
   * <p>Tensors can be released using the passed scope.
   *
   * @return the released result of {@code block}
   */
  public static <T extends TensorContainer> T produceTensorContainerWithCleanup(Function<TensorScope, T> block) {
    try (TensorScope scope = new TensorScope()) {
      return scope.release(block.apply(scope));
    }
  }


  /**
   * Runs {@code block} and releases and returns the result, then closes any <b>other</b> tensors created during its
   * execution.
   * <p>To release other tensors, use {@link #produceTensorWithCleanup(Function)}.
   *
   * @return the released result of {@code block}
   */
  public static <T extends Iterable<? extends Tensor>> T produceTensorsWithCleanup(Supplier<T> block) {
    return TensorScope.produceTensorsWithCleanup((scope) -> block.get());
  }

  /**
   * Runs {@code block} and releases and returns the result, then closes any <b>other</b> tensors created during its
   * execution (or attached to the scope).
   * <p>Tensors can be released using the passed scope.
   *
   * @return the released result of {@code block}
   */
  public static <T extends Iterable<? extends Tensor>> T produceTensorsWithCleanup(Function<TensorScope, T> block) {
    try (TensorScope scope = new TensorScope()) {
      return scope.release(block.apply(scope));
    }
  }

  /**
   * Create a new tensor scope.  If {@code autoAttach} is false, will not automatically manage tensors.
   *
   * @see TensorScope
   */
  TensorScope() {
    this.parent = currentScope();
    currentScope.set(this);

    if (this.parent != null) {
      synchronized (this.parent) {
        this.parent.children.add(this);
      }
    }
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
   * <p>
   * Semantically, calling this method makes all of the resources in this scope the parent's responsibility, as if this
   * scope had never existed.
   * <p>
   * This will close this scope, but does not close any of it's resources.
   *
   * @throws IllegalStateException if this scope has no parent. If this happens, * the scope is not closed and no
   * resources are released.
   */
  public synchronized void releaseAllToParent() {
    release(true);
  }

  /**
   * Release the tensors and child scopes of this scope to it's parent, or detach them if this scope has no parent.
   * <p>
   * Semantically, calling this method makes all of the resources in this scope the parent's responsibility, as if this
   * scope had never existed.  It can be used in a method to transfer control to the caller, leaving how the resources
   * are managed up to the caller.
   * <p>
   * This will close this scope, but does not close any of it's resources.
   */
  public synchronized void releaseAll() {
    release(false);
  }

  /**
   * Release the tensors and child scopes of this scope <b>without closing them</b>, to it's parent if it has one.
   *
   * <p><b>WARNING:</b> this method may release resources without assigning them to another scope if
   * {@code requireParent} is false.  {@link #releaseAllToParent()} should be used instead wherever possible.
   *
   * @param requireParent Whether to require a parent scope to release resources to.
   * @throws IllegalStateException if this scope has no parent, but {@code requireParent} is true. If this happens, the
   * scope is not closed and no resources are released.
   */
  private synchronized void release(boolean requireParent) {
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
  public TensorScope withTensors(Iterable<? extends Tensor>... tensors) {
    attach(tensors);
    return this;
  }

  /**
   * Attach this tensor to the parent of this scope, removing it from its current scope, or detach it if there is no
   * current scope or the current scope does not have a parent.
   *
   * <p>Semantically, this makes the tensor's resources this scope's parent's responsibility.
   *
   * @param requireParent whether to require a parent scope to release resources to.
   * @throws IllegalStateException if there is no current scope or the current scope does not have a parent, but {@code
   * requireParent} is true.  If this happens, the tensor's scope is not changed.
   */
  private <T extends Tensor> T release(T tensor, boolean requireParent) {
    if (parent == null && requireParent) {
      throw new IllegalStateException(
          "Can't release to parent: not in a current scope, or the current scope does not have a parent.");
    }

    detach(tensor);
    if (parent != null) {
      parent.attach(tensor);
    }
    return tensor;
  }


  /**
   * Attach this tensor to the parent of this scope, removing it from its current scope, or detach it if there is no
   * current scope or the current scope does not have a parent.
   *
   * <p>Semantically, this makes the tensor's resources this scope's parent's responsibility.
   */
  public <T extends Tensor> T release(T tensor) {
    return release(tensor, false);
  }

  /**
   * @see #release(Tensor)
   */
  public void release(Tensor... tensors) {
    for (Tensor t : tensors) {
      release(t);
    }
  }

  /**
   * @see #release(Tensor)
   */
  public <T extends TensorContainer> T release(T tensors) {
    release(tensors.tensors());
    return tensors;
  }

  /**
   * @see #release(Tensor)
   */
  public void release(TensorContainer... tensors) {
    for (TensorContainer ht : tensors) {
      release(ht);
    }
  }

  /**
   * @see #release(Tensor)
   */
  public <T extends Iterable<? extends Tensor>> T release(T tensors) {
    tensors.forEach(this::release);
    return tensors;
  }

  /**
   * @see #release(Tensor)
   */
  @SafeVarargs
  public final void release(Iterable<? extends Tensor>... tensors) {
    for (Iterable<? extends Tensor> iterable : tensors) {
      release(iterable);
    }
  }

  /**
   * Attach this tensor to the parent of this scope, removing it from its current scope.
   *
   * <p>Semantically, this makes the tensor's resources this scope's parent's responsibility.
   *
   * @throws IllegalStateException if there is no current scope or the current scope does not have a parent, but {@code
   * requireParent} is true.  If this happens, the tensor's scope is not changed.
   */
  public <T extends Tensor> T releaseToParent(T tensor) {
    return release(tensor, true);
  }

  /**
   * Gets whether the scope is closed.
   */
  public synchronized boolean isClosed() {
    return closed;
  }

  private boolean closed = false;
  private final Set<RawTensor> tensors = ConcurrentHashMap.newKeySet();
  private TensorScope parent;
  private final Set<TensorScope> children = ConcurrentHashMap.newKeySet();
}
