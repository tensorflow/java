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

import java.util.*
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration

private const val tensorflowPropertiesFile = "org/tensorflow/jupyter/tensorflow.properties"

public class TensorflowKotlinIntegration : JupyterIntegration() {
  override fun Builder.onLoaded() {
    val os = System.getProperty("os.name").lowercase()
    val ext =
        when {
          os.contains("mac") -> "macosx-x86_64"
          os.startsWith("windows") -> "windows-x86_64"
          else -> "linux-x86_64"
        } + "-gpu"

    val version =
        this@TensorflowKotlinIntegration.javaClass.classLoader.getResourceAsStream(
                tensorflowPropertiesFile)
            .let {
              it
                  ?: error(
                      "No $tensorflowPropertiesFile resource found, can't determine the library version")
              Properties().apply { load(it) }.getProperty("version")
                  ?: error(
                      "No version property found in the $tensorflowPropertiesFile resource, did you overwrite it?")
            }

    // TODO use ext instead of platform https://github.com/Kotlin/kotlin-jupyter/issues/285
    dependencies("org.tensorflow:tensorflow-core-platform-gpu:$version")
    dependencies("org.tensorflow:tensorflow-core-kotlin-jupyter:$version")
  }
}
