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

/**
 * An interface representing a collection or group of tensors.  Provides methods for resource management.
 */
public interface HasTensors extends AutoCloseable {

  /**
   * Get the tensors held by this object.
   */
  Iterable<Tensor> tensors();

  /**
   * Detach these tensors from any scopes managing them.  They must be manually closed or attached to another scope.
   *
   * @see Tensor#detach()
   */
  default void detach() {
    tensors().forEach(Tensor::detach);
  }

  /**
   * Attach all of these tensors to the most recent scope.
   *
   * @throws IllegalStateException if there is no active scope.
   * @see Tensor#attachToCurrentScope()
   */
  default void attachToCurrentScope() {
    TensorScope scope = TensorScope.currentScope();
    if (scope == null) {
      throw new IllegalStateException("Can't attach to current scope: no active tensor scopes.");
    }

    tensors().forEach(scope::attach);
  }

  /**
   * Attach these tensors to the parent of their current scope, removing it from its current scope.
   *
   * <p>Note that if tensors have different scopes, each tensor will be attached to its scope's parent.
   * {@link TensorScope#attach(HasTensors)} or {@link #attachToCurrentScope()} can be used to ensure all tensors have
   * the same scope.
   *
   * @throws IllegalStateException if any tensors do not have a scope, or their scope does not have a parent.
   */
  default void attachToParent() {
    tensors().forEach(Tensor::attachToParent);
  }

  /**
   * Release resources associated with these tensors.
   *
   * <p><b>WARNING:</b>This must be invoked for all tensors that were not been produced by an eager
   * operation or memory will be leaked.  May be done automatically via {@link TensorScope}.
   *
   * <p>The Tensor objects are no longer usable after {@code close} returns.
   *
   * @see Tensor#close()
   */
  @Override
  default void close() {
    tensors().forEach(Tensor::close);
  }
}
