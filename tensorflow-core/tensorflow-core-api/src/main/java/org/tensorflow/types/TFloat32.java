/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.family.TNumber;

public interface TFloat32 extends FloatNdArray, TNumber {

  DataType<TFloat32> DTYPE = DataType.create("FLOAT", 1, 4, TFloat32Impl::mapTensor);

  static Tensor<TFloat32> scalarOf(float value) {
    Tensor<TFloat32> t = ofShape();
    t.data().setFloat(value);
    return t;
  }

  static Tensor<TFloat32> vectorOf(float... values) {
    Tensor<TFloat32> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TFloat32> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TFloat32> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TFloat32> copyOf(NdArray<Float> src) {
    Tensor<TFloat32> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TFloat32Impl extends FloatDenseNdArray implements TFloat32 {

  static TFloat32 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TFloat32Impl(TensorBuffers.toFloats(nativeTensor), shape);
  }

  private TFloat32Impl(FloatDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}

