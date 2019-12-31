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
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.ndarray.DoubleNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.family.TDecimal;

public interface TDouble extends DoubleNdArray, TDecimal {

  DataType<TDouble> DTYPE = DataType.create("DOUBLE", 2, 8, TDoubleImpl::mapTensor);

  static Tensor<TDouble> scalarOf(double value) {
    Tensor<TDouble> t = ofShape();
    t.data().setDouble(value);
    return t;
  }

  static Tensor<TDouble> vectorOf(double... values) {
    Tensor<TDouble> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TDouble> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TDouble> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TDouble> copyOf(NdArray<Double> src) {
    Tensor<TDouble> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TDoubleImpl extends DoubleDenseNdArray implements TDouble {

  static TDouble mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TDoubleImpl(TensorBuffers.toDoubles(nativeTensor), shape);
  }

  private TDoubleImpl(DoubleDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
