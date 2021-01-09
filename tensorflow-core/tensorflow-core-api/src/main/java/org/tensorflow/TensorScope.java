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
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.ndarray.NdArray;

public class TensorScope implements AutoCloseable{

  static final ThreadLocal<Deque<TensorScope>> scopeStack = ThreadLocal.withInitial(ArrayDeque::new);

  /** Returns {@code scopeStack.get().peek()}, the last opened scope not yet closed. */
  public static TensorScope getInnerScope() {
    return scopeStack.get().peek();
  }

  /** Returns {@code scopeStack.get().iterator()}, all scopes not yet closed. */
  public static Iterator<TensorScope> getScopeIterator() {
    return scopeStack.get().iterator();
  }

  /**
   * Detaches the given tensor from any scopes managing it, requiring it to be manually closed.
   */
  public static void detach(Tensor t){
    RawTensor raw = t.asRawTensor();
    getScopeIterator().forEachRemaining(scope -> scope.detachTensor(raw));
  }

  public TensorScope(){
    scopeStack.get().push(this);
  }

  /**
   * Attach a tensor to this scope.  This happens automatically to tensors that are created in the scope.
   */
  public void attach(Tensor t){
    tensors.add(t.asRawTensor());
  }


  /**
   * Attach tensors to this scope.  This happens automatically to tensors that are created in the scope.
   */
  public void attach(Tensor... tensors){
    for(Tensor t : tensors){
      attach(t);
    }
  }

  private void detachTensor(Tensor t){
    tensors.remove(t.asRawTensor());
  }

  @Override
  public void close() throws Exception {
    tensors.forEach(Tensor::close);
  }

  private final Set<RawTensor> tensors = new LinkedHashSet<>();
}
