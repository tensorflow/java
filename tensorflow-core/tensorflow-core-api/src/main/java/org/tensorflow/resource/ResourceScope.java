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
=======================================================================

*/
package org.tensorflow.resource;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ResourceScope implements AutoCloseable {
  private static final Map<Resource, Integer> references = new WeakIdentityHashMap<>();

  private static void addReference(Resource resource, boolean doGC) {
    synchronized (references) {
      references.put(resource, references.getOrDefault(resource, 0) + 1);
    }
    if (doGC) {
      ResourceManager.cleanup(resource);
    }
  }

  private static void removeReference(Resource resource) {
    boolean doDeallocate = false;
    synchronized (references) {
      if (references.containsKey(resource)) {
        int refs = references.get(resource) - 1;
        if (refs <= 0) {
          references.remove(resource);
          doDeallocate = true;
        } else {
          references.put(resource, refs);
        }
      } else {
        doDeallocate = true;
      }
    }
    // separate to ensure no deadlock from deallocators that reference other resources
    if (doDeallocate) {
      resource.deallocator().deallocate();
    }
  }

  /**
   * Adds a resource to this scope. The resource will be kept alive at least until this scope is
   * closed, or it is unreachable if this is a weak scope.
   */
  public void add(Resource resource) {
    synchronized (resources) {
      ensureOpen();
      if (resources.add(resource)) {
        addReference(resource, isWeak);
      }
    }
  }

  /**
   * Remove a resource from this scope. If this was the only scope referencing it, it will be
   * deallocated.
   */
  public void remove(Resource resource) {
    synchronized (resources) {
      ensureOpen();
      if (resources.remove(resource)) {
        removeReference(resource);
      }
    }
  }

  /** Ensures {@code other} is not closed before {@code this}. */
  public void dependsOn(ResourceScope other) {
    synchronized (resources) {
      ensureOpen();
      if (dependencies.add(other)) {
        other.addConsumer();
      }
    }
  }

  @Override
  public void close() {
    synchronized (resources) {
      closeHelper(resources, consumers, dependencies, isClosed);
    }
  }

  public Deallocator deallocator() {
    return () -> {
      synchronized (resources) {
        closeHelper(resources, consumers, dependencies, isClosed);
      }
    };
  }

  public static ResourceScope strongScope(boolean implicit) {
    return new ResourceScope(false, implicit);
  }

  public static ResourceScope weakScope(boolean implicit) {
    return new ResourceScope(true, implicit);
  }

  public static ResourceScope strongImplicitScope() {
    return strongScope(true);
  }

  public static ResourceScope strongExplicitScope() {
    return strongScope(false);
  }

  public static ResourceScope weakImplicitScope() {
    return weakScope(true);
  }

  public static ResourceScope weakExplicitScope() {
    return weakScope(false);
  }

  /**
   * @param weak will resources be GCd
   * @param implicit will the scope itself be closed on GC
   */
  ResourceScope(boolean weak, boolean implicit) {
    isWeak = weak;
    isImplicit = implicit;
    if (isWeak) {
      resources = Collections.newSetFromMap(new WeakIdentityHashMap<>());
    } else {
      resources = Collections.newSetFromMap(new IdentityHashMap<>());
    }
    if (isImplicit) {
      ResourceManager.addGc(this, deallocator());
    }
  }

  private void addConsumer() {
    consumers.incrementAndGet();
  }

  private void removeConsumer() {
    consumers.decrementAndGet();
  }

  private void ensureOpen() {
    if (isClosed[0]) {
      throw new IllegalStateException("Resource scope has been closed");
    }
  }

  private static void closeHelper(
      Set<Resource> resources, AtomicInteger consumers, Set<ResourceScope> dependencies, boolean[] isClosed) {
    if (consumers.get() > 0) {
      throw new IllegalStateException(
          "There are still "
              + consumers
              + " open scopes with "
              + "dependencies on this scope, can not close.");
    }
    dependencies.forEach(ResourceScope::removeConsumer);
    dependencies.clear();
    resources.forEach(ResourceScope::removeReference);
    resources.clear();
    isClosed[0] = true;
  }

  private final Set<Resource> resources;
  private final boolean isWeak;
  private final boolean isImplicit;
  private final boolean[] isClosed = new boolean[] {false};
  private final AtomicInteger consumers = new AtomicInteger(0);
  private final Set<ResourceScope> dependencies =
      Collections.newSetFromMap(new IdentityHashMap<>());
}
