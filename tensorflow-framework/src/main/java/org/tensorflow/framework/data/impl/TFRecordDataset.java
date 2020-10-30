/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.data.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.data.Dataset;
import org.tensorflow.op.Ops;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

import java.util.Collections;

public class TFRecordDataset extends Dataset {

  public TFRecordDataset(
      Ops tf,
      Operand<TString> filenames,
      Operand<TString> compressionType,
      Operand<TInt64> bufferSize) {
    super(
        tf,
        tf.data.tfRecordDataset(filenames, compressionType, bufferSize),
        Collections.singletonList(TString.class),
        Collections.singletonList(Shape.scalar()));
  }
}
