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

public class ResourceScope implements Resource, AutoCloseable{
  private static final Map<Resource, Integer> references = new WeakIdentityHashMap<>();

  protected static void addReference(Resource resource, boolean doGC){
    synchronized (references) {
      references.put(resource, references.getOrDefault(resource, 0) + 1);
    }
    if (doGC) {
      ResourceManager.cleanup(resource);
    }
  }

  protected static void removeReference(Resource resource){
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
    // separate to ensure no deadlock from weird deallocators
    if(doDeallocate){
      resource.deallocator().deallocate();
    }
  }

  private void ensureOpen(){
    synchronized (resources) {
      if (isClosed[0]) {
        throw new IllegalStateException("Resource scope has been closed");
      }
    }
  }

  public void add(Resource resource) {
    synchronized (resources) {
      ensureOpen();
      resources.add(resource);
      addReference(resource, isWeak);
    }
  }

  public void remove(Resource resource) {
    synchronized (resources) {
      ensureOpen();
      if (resources.remove(resource)) {
        removeReference(resource);
      }
    }
  }

  @Override
  public void close() throws Exception {
    synchronized (resources) {
      resources.forEach(ResourceScope::removeReference);
      resources.clear();
      isClosed[0] = true;
    }
  }

  @Override
  public Deallocator deallocator() {
    return () -> {
      synchronized (resources) {
        resources.forEach(ResourceScope::removeReference);
        resources.clear();
        isClosed[0] = true;
      }
    };
  }

  ResourceScope(boolean weak){
    isWeak = weak;
    if(isWeak){
      resources = Collections.newSetFromMap(new WeakIdentityHashMap<>());
    } else {
      resources = Collections.newSetFromMap(new IdentityHashMap<>());
    }
  }

  public static ResourceScope strongScope(){
    return new ResourceScope(false);
  }

  public static ResourceScope weakScope(){
    return new ResourceScope(true);
  }

  private final Set<Resource> resources;
  private final boolean isWeak;
  private final boolean[] isClosed = new boolean[]{ false };

  {
    ResourceManager.addGc(this, deallocator());
  }
}
