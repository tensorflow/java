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
package org.tensorflow.jupyter

import org.jetbrains.kotlinx.jupyter.api.declare
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.tensorflow.EagerSession
import org.tensorflow.Operand
import org.tensorflow.op.Op
import org.tensorflow.op.kotlin.tf

public class TensorflowKotlinCoreIntegration : JupyterIntegration() {
  override fun Builder.onLoaded() {
    import(
        "org.tensorflow.*",
        "org.tensorflow.op.*",
        "org.tensorflow.op.kotlin.*",
        "org.tensorflow.types.*",
        "org.tensorflow.types.family.*",
        "org.tensorflow.ndarray.*",
        "org.tensorflow.ndarray.index.*")

    render<Operand<*>> { it.asOutput().toString() }
    render<Op> { it.op().toString() }

    onLoaded {
      EagerSession.getDefault()
      declare("tf" to EagerSession.getDefault().tf)
    }
  }
}
