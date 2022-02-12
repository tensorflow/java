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
package org.tensorflow.framework.initializers

import org.tensorflow.Operand
import org.tensorflow.op.Ops
import org.tensorflow.op.kotlin.KotlinOps
import org.tensorflow.op.kotlin.tf
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TType

/**
 * Create an initializer
 * @see org.tensorflow.framework.initializers.Initializer
 */
public inline fun <T : TType> Initializer(
    crossinline initializer: KotlinOps.(dims: Operand<TInt64>, dataType: Class<T>) -> Operand<T>
): Initializer<T> =
    org.tensorflow.framework.initializers.Initializer { tf, dims, dataType ->
      initializer(tf.tf, dims, dataType)
    }

/** Call an initializer. */
public inline fun <reified T : TType> Initializer<T>.call(
    tf: Ops,
    dims: Operand<TInt64>
): Operand<T> = call(tf, dims, T::class.java)!!
