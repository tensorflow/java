/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionName;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionToFunctionDef;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Function;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.proto.framework.FunctionDef;
import org.tensorflow.proto.framework.NodeDef;

/**
 * A class holding a native function handle and providing cached access to it's {@link FunctionDef}.
 */
class NativeFunction {
  public NativeFunction(TF_Function nativeHandle) {
    this.nativeHandle = nativeHandle;
  }

  /** Get the native handle. No guarantees about liveness are made. */
  public TF_Function getNativeHandle() {
    return nativeHandle;
  }

  /** Get the function's {@link FunctionDef} */
  public synchronized FunctionDef getFunctionDef() {
    if (functionDef == null) {
      try (PointerScope scope = new PointerScope()) {
        TF_Buffer funcDefBuffer = TF_Buffer.newBuffer();
        TF_Status status = TF_Status.newStatus();

        TF_FunctionToFunctionDef(nativeHandle, funcDefBuffer, status);
        status.throwExceptionIfNotOK();

        try {
          functionDef = FunctionDef.parseFrom(funcDefBuffer.dataAsByteBuffer());
        } catch (InvalidProtocolBufferException e) {
          throw new IllegalStateException("Failed to parse FunctionDef proto", e);
        }
      }
    }

    return functionDef;
  }

  /** Get the first-level dependencies of the function. */
  public synchronized List<String> getDependencies() {
    if (dependencies == null) {
      Set<String> deps = new LinkedHashSet<>();

      for (NodeDef node : getFunctionDef().getNodeDefList()) {
        node.getAttrMap()
            .values()
            .forEach(
                (attr) -> {
                  if (attr.hasFunc()) {
                    deps.add(attr.getFunc().getName());
                  } else if (attr.hasList()) {
                    attr.getList()
                        .getFuncList()
                        .forEach(
                            funcs -> {
                              deps.add(funcs.getName());
                            });
                  }
                });
      }
      dependencies = Collections.unmodifiableList(new ArrayList<>(deps));
    }

    return dependencies;
  }

  /** Get whether the function is stateful (whether it has stateful ops). */
  public synchronized boolean isStateful() {
    if (stateful == null) {
      stateful =
          getFunctionDef().getSignature().getIsStateful()
              || getFunctionDef().getNodeDefList().stream()
                  .anyMatch(x -> TensorFlow.isOpStateful(x.getOp()));
    }
    return stateful;
  }

  /** Get the name of the function. */
  public synchronized String getName() {
    if (name == null) {
      try (PointerScope scope = new PointerScope()) {
        return TF_FunctionName(nativeHandle).getString();
      }
    }

    return name;
  }

  synchronized Set<TF_Function> getAllDependencies(Collection<NativeFunction> availableFunctions) {
    Map<String, NativeFunction> fnMap =
        availableFunctions.stream().collect(Collectors.toMap(NativeFunction::getName, e -> e));
    Set<String> done = new LinkedHashSet<>(1 + getDependencies().size());

    Queue<NativeFunction> todo = new ArrayDeque<>(1 + getDependencies().size());
    todo.add(this);

    while (!todo.isEmpty()) {
      NativeFunction next = todo.remove();

      if (!done.add(next.getName())) {
        continue;
      }

      for (String dep : next.getDependencies()) {
        if (!done.contains(dep)) {
          NativeFunction fn = fnMap.get(dep);

          if (fn == null) {
            throw new IllegalStateException(
                "Function " + dep + " is required, but not present in graph.");
          }

          todo.add(fn);
        }
      }
    }

    done.remove(getName());

    return done.stream()
        .map(fnMap::get)
        .map(NativeFunction::getNativeHandle)
        .collect(Collectors.toSet());
  }

  private final TF_Function nativeHandle;

  private FunctionDef functionDef = null;
  private List<String> dependencies = null;
  private Boolean stateful = null;
  private String name = null;
}
