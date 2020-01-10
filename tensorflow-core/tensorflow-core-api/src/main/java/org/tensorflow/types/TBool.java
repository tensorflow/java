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
import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.ndarray.BooleanNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.types.family.TType;

public interface TBool extends BooleanNdArray, TType {

  DataType<TBool> DTYPE = DataType.create("BOOL", 10, 1, TBoolImpl::mapTensor);

  static Tensor<TBool> scalarOf(boolean value) {
    Tensor<TBool> t = ofShape();
    t.data().setBoolean(value);
    return t;
  }

  static Tensor<TBool> vectorOf(boolean... values) {
    Tensor<TBool> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TBool> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TBool> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TBool> copyOf(NdArray<Boolean> src) {
    Tensor<TBool> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TBoolImpl extends BooleanDenseNdArray implements TBool {

  static TBool mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TBoolImpl(TensorBuffers.toBooleans(nativeTensor), shape);
  }

  private TBoolImpl(BooleanDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
