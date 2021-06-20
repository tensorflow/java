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

import kotlin.test.Test
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.WithOps
import org.tensorflow.op.kotlin.KotlinOps
import org.tensorflow.op.kotlin.tf
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32

private fun WithOps.DenseLayer(
    name: String,
    x: Operand<TFloat32>,
    n: Int,
    activation: WithOps.(Operand<TFloat32>) -> Operand<TFloat32> = { tf.nn.relu(it) },
): Operand<TFloat32> =
    tf.withSubScope(name) {
      val inputDims = x.shape()[1]
      val W = tf.variable(tf.ones<TFloat32>(tf.array(inputDims.toInt(), n)))
      val b = tf.variable(tf.ones<TFloat32>(tf.array(n)))
      activation((x matMul W) + b)
    }

public class ExampleTest {
  @Test
  public fun mnistExample() {
    Graph {
      val input =
          tf.placeholderWithDefault(
              tf.ones<TFloat32>(tf.array(1, 28, 28, 3)), Shape.of(-1, 28, 28, 3))

      var x: Operand<TFloat32> = tf.reshape(input, tf.array(-1))
      tf.dtypes.cast<TInt32>(x)
      x = DenseLayer("Layer1", x, 256)
      x = DenseLayer("Layer2", x, 64)
      val output = DenseLayer("OutputLayer", x, 10) { tf.math.sigmoid(x) }

      useSession { session ->
        val outputValue = session.runner().fetch(output).run()[0] as TFloat32
        println(outputValue.getFloat(0))
      }
    }
  }
}
