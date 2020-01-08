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

import com.google.common.base.Charsets;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.StringTensorBuffer;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArrays;
import org.tensorflow.tools.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.types.family.TType;

/**
 * String tensor type.
 */
public interface TString extends NdArray<String>, TType {

  /** Type metadata */
  DataType<TString> DTYPE = DataType.create("STRING", 7, -1, TStringImpl::mapTensor);

  /**
   * Allocates a new tensor for storing a single string value.
   *
   * @param value string to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TString> scalarOf(String value) {
    return copyOf(NdArrays.ofObjects(String.class, Shape.scalar()).setObject(value));
  }

  /**
   * Allocates a new tensor for storing a vector of strings.
   *
   * @param values strings to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TString> vectorOf(String... values) {
    return copyOf(NdArrays.ofObjects(String.class, Shape.make(values.length)).write(values));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of strings.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static Tensor<TString> copyOf(NdArray<String> src) {
    return TStringImpl.createTensor(src);
  }
}

/**
 * Hidden implementation of a {@code TString}
 */
class TStringImpl extends DenseNdArray<String> implements TString {

  static Tensor<TString> createTensor(NdArray<String> src) {

    // First, compute the capacity of the tensor to create
    long size = src.size() * 8;  // reserve space to store 64-bits offsets
    for (NdArray<String> s : src.scalars()) {
      byte[] bytes = s.getObject().getBytes(Charsets.UTF_8);
      size += bytes.length + varintLength(bytes.length);  // add space to store value + length
    }

    // Allocate the tensor of the right capacity and init its data from source array
    Tensor<TString> tensor = Tensor.allocate(TString.DTYPE, src.shape(), size);
    StringTensorBuffer buffer = (StringTensorBuffer)(((TStringImpl)tensor.data()).buffer());
    buffer.init(src);

    return tensor;
  }

  static TString mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TStringImpl(TensorBuffers.toStrings(nativeTensor, shape.size()), shape);
  }

  private TStringImpl(DataBuffer<String> buffer, Shape shape) {
    super(buffer, shape);
  }

  private static int varintLength(int length) {
    int len = 1;
    while (length >= 0x80) {
      length >>= 7;
      len++;
    }
    return len;
  }
}

