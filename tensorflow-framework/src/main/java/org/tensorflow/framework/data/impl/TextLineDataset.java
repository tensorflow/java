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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

import java.util.Collections;

/** A Dataset comprising lines from one or more text files. */
public class TextLineDataset extends Dataset {

  /**
   * Creates a Dataset comprising lines from one or more text files.
   *
   * @param tf the TensorFlow Ops
   * @param filenames the names of one or more files containing the text lines
   * @param compressionType the compression type, either "" (no compression), "ZLIB", or "GZIP"
   * @param bufferSize the number of bytes in the read buffer
   */
  public TextLineDataset(
      Ops tf,
      Operand<TString> filenames,
      Operand<TString> compressionType,
      Operand<TInt64> bufferSize) {
    super(
        tf,
        tf.data.textLineDataset(filenames, compressionType, bufferSize),
        Collections.singletonList(TString.class),
        Collections.singletonList(Shape.scalar()));
  }
}
