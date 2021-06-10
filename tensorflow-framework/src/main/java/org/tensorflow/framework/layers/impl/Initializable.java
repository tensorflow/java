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
=======================================================================*/
package org.tensorflow.framework.layers.impl;

import java.util.function.Consumer;
import org.tensorflow.op.Ops;

/** Interface for initializing layers */
public interface Initializable {

  /**
   * Initializes the layer with the TensorFlow Platform
   *
   * @param tf the TensorFlow Ops
   * @return the sub-scoped Ops generated from the tf Ops parameger
   */
  Ops init(Ops tf);

  /**
   * Registers functions that are applied when this layer is registered with the TensorFlow
   * platform.
   *
   * @param consumer the function to register
   */
  void onInit(Consumer<Ops> consumer);
}
