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

import org.junit.jupiter.api.Test
import org.tensorflow.ndarray.Shape
import org.tensorflow.ndarray.get
import org.tensorflow.op.kotlin.KotlinOps
import org.tensorflow.op.kotlin.tf
import org.tensorflow.op.kotlin.withSubScope
import org.tensorflow.types.TFloat32

public fun KotlinOps.DenseLayer(
    name: String,
    x: Operand<TFloat32>,
    n: Int,
    activation: KotlinOps.(Operand<TFloat32>) -> Operand<TFloat32> = { tf.nn.relu(it) }
): Operand<TFloat32> = tf.withSubScope(name) {
    val inputDims = x.shape()[1]
    val W = tf.variable(tf.math.add(tf.zeros(tf.array(inputDims.toInt(), n), TFloat32.DTYPE), constant(1f)))
    val b = tf.variable(tf.math.add(tf.zeros(tf.array(n), TFloat32.DTYPE), constant(1f)))
    activation(tf.math.add(tf.linalg.matMul(x, W), b))
}

public class Example {
    @Test
    public fun mnistExample() {
        Graph {
            val input = tf.placeholderWithDefault(
                tf.math.add(tf.zeros(tf.array(1, 28, 28, 3), TFloat32.DTYPE), tf.constant(1f)),
                Shape.of(-1, 28, 28, 3)
            )

            val output = with(tf) {
                var x: Operand<TFloat32> = tf.reshape(input, tf.array(-1))
                x = DenseLayer("Layer1", x, 256)
                x = DenseLayer("Layer2", x, 64)
                DenseLayer("OutputLayer", x, 10) { tf.math.sigmoid(x) }
            }

            useSession {
                val outputValue = it.run(fetches = listOf(output))[output]
                println(outputValue.data())
            }
        }
    }
}
