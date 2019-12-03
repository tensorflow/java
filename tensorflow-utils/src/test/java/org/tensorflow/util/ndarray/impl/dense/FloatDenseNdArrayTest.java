/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.util.ndarray.impl.dense;

import org.tensorflow.util.ndarray.FloatNdArrayTestBase;
import org.tensorflow.util.buffer.DataBuffer;
import org.tensorflow.util.buffer.DataBuffers;
import org.tensorflow.util.ndarray.FloatNdArray;
import org.tensorflow.util.ndarray.NdArrays;
import org.tensorflow.util.ndarray.Shape;

public class FloatDenseNdArrayTest extends FloatNdArrayTestBase {

  @Override protected FloatNdArray allocate(Shape shape) {
    return NdArrays.ofFloats(shape);
  }

  @Override protected DataBuffer<Float> allocateBuffer(long size) {
    return DataBuffers.ofFloats(size);
  }
}
