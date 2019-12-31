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
import org.tensorflow.types.family.TDecimal;

public interface TFloat extends FloatNdArray, TDecimal {

  DataType<TFloat> DTYPE = DataType.create("FLOAT", 1, 4, TFloatImpl::mapTensor);

  static Tensor<TFloat> scalarOf(float value) {
    Tensor<TFloat> t = ofShape();
    t.data().setFloat(value);
    return t;
  }

  static Tensor<TFloat> vectorOf(float... values) {
    Tensor<TFloat> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TFloat> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TFloat> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TFloat> copyOf(NdArray<Float> src) {
    Tensor<TFloat> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TFloatImpl extends FloatDenseNdArray implements TFloat {

  static TFloat mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TFloatImpl(TensorBuffers.toFloats(nativeTensor), shape);
  }

  private TFloatImpl(FloatDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}

