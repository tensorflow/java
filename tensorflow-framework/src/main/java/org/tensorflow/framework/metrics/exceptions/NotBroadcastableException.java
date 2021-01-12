/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.metrics.exceptions;

import org.tensorflow.ndarray.Shape;

/**
 * Exception that indicates that static shapes are not able to broadcast among each other during
 * arithmetic operations. Static shapes do not have unknown rank or any unknown dimensions {@link
 * Shape#hasUnknownDimension()}. The term broadcasting describes how TensorFlow treats arrays with
 * different shapes during arithmetic operations.
 *
 * <p>Broadcasting is the process of making arrays to have compatible shapes for arithmetic
 * operations. Two shapes are compatible if for each dimension pair they are either equal or one of
 * them is one. When trying to broadcast a Tensor to a shape, it starts with the trailing
 * dimensions, and works its way forward.
 *
 * @see <a href="https://numpy.org/doc/stable/user/basics.broadcasting.html">Numpy Broadcasting</a>
 */
public class NotBroadcastableException extends IllegalArgumentException {

  /**
   * Creates a new NotBroadcastableException exception with the specified detail message
   *
   * @param message the detail message.
   */
  public NotBroadcastableException(String message) {
    super(message);
  }

  /**
   * Creates a new NotBroadcastableException exception with the specified detail message
   *
   * @param message the detail message.
   * @param cause the cause
   */
  public NotBroadcastableException(String message, Throwable cause) {
    super(message, cause);
  }
}
