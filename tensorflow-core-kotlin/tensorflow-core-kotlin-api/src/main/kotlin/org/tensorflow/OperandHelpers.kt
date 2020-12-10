/*
  Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow

import org.tensorflow.ndarray.Shape
import org.tensorflow.ndarray.Shaped

/**
 * Require the [Shaped] object have a certain shape.
 *
 * Throws [IllegalStateException] on failure.
 */
public fun <T : Shaped> T.requireShape(shape: Shape): T = apply {
    check(this.shape().isCompatibleWith(shape)) {
        "Shape ${this.shape()} is not compatible with the required shape $shape"
    }
}

/**
 * Require the [Shaped] object have a certain shape.
 *
 * Throws [IllegalStateException] on failure.
 */
public fun <T : Shaped> T.requireShape(vararg shape: Long): T = apply {
    check(this.shape().isCompatibleWith(Shape.of(*shape))) {
        "Shape ${this.shape()} is not compatible with the required shape $shape"
    }
}
