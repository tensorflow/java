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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.types.TStringInitializer;
import org.tensorflow.internal.types.TStringMapper;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.types.family.TType;

/**
 * String type.
 *
 * <p>This type can be used to store any arbitrary byte sequence of variable length.
 *
 * <p>Since the size of a tensor is fixed, creating a tensor of this type requires to provide all of
 * its values initially, so TensorFlow can compute and allocate the right amount of memory. Then the
 * data in the tensor is initialized once and cannot be modified afterwards.
 */
public interface TString extends NdArray<String>, TType {

  /** readable-name for the data type */
  static final String NAME = "STRING";

  /** Type metadata */
  DataType<TString> DTYPE = DataType.create(NAME, 7, -1, new TStringMapper());

  /**
   * Allocates a new tensor for storing a string scalar.
   *
   * <p>The string is encoded into bytes using the UTF-8 charset.
   *
   * @param value scalar value to store in the new tensor
   * @return the new tensor
   */
  static TString scalarOf(String value) {
    return tensorOf(NdArrays.scalarOfObject(value));
  }

  /**
   * Allocates a new tensor for storing a vector of strings.
   *
   * <p>The strings are encoded into bytes using the UTF-8 charset.
   *
   * @param values values to store in the new tensor
   * @return the new tensor
   */
  static TString vectorOf(String... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return tensorOf(NdArrays.vectorOfObjects(values));
  }

  /**
   * Allocates a new tensor which is a copy of a given array.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied. The
   * strings are encoded into bytes using the UTF-8 charset.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TString tensorOf(NdArray<String> src) {
    return tensorOf(StandardCharsets.UTF_8, src);
  }

  /**
   * Allocates a new tensor which is a copy of a given array.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied. The
   * strings are encoded into bytes using the charset passed in parameter.
   *
   * <p>If charset is different than default UTF-8, then it must also be provided explicitly when
   * reading data from the tensor, using {@link #using(Charset)}:
   *
   * <pre>{@code
   * // Given `originalStrings` an initialized vector of strings
   * TString tensor = TString.tensorOf(Charsets.UTF_16, originalStrings);
   * ...
   * TString tensorStrings = tensor.data().using(Charsets.UTF_16);
   * assertEquals(originalStrings.getObject(0), tensorStrings.getObject(0));
   * }</pre>
   *
   * @param charset charset to use for encoding the strings into bytes
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TString tensorOf(Charset charset, NdArray<String> src) {
    TStringInitializer<String> initializer = new TStringInitializer<>(src, s -> s.getBytes(charset));
    return Tensor.of(TString.DTYPE, src.shape(), initializer.computeRequiredSize(), initializer);
  }

  /**
   * Allocates a new tensor with the given shape and data.
   *
   * <p>The data will be copied from the provided buffer to the tensor after it is allocated. The
   * strings are encoded into bytes using the UTF-8 charset.
   *
   * @param shape shape of the tensor
   * @param data buffer of strings to initialize the tensor with
   * @return the new tensor
   */
  static TString tensorOf(Shape shape, DataBuffer<String> data) {
    return tensorOf(NdArrays.wrap(shape, data));
  }

  /**
   * Allocates a new tensor with the given shape and data.
   *
   * <p>The data will be copied from the provided buffer to the tensor after it is allocated. The
   * strings are encoded into bytes using the charset passed in parameter.
   *
   * <p>If charset is different than default UTF-8, then it must also be provided explicitly when
   * reading data from the tensor, using {@link #using(Charset)}:
   *
   * <pre>{@code
   * // Given `originalStrings` an initialized buffer of strings
   * TString tensor =
   *    TString.tensorOf(Charsets.UTF_16, Shape.of(originalString.size()), originalStrings);
   * ...
   * TString tensorStrings = tensor.data().using(Charsets.UTF_16);
   * assertEquals(originalStrings.getObject(0), tensorStrings.getObject(0));
   * }</pre>
   *
   * @param charset charset to use for encoding the strings into bytes
   * @param shape shape of the tensor
   * @param data buffer of strings to initialize the tensor with
   * @return the new tensor
   */
  static TString tensorOf(Charset charset, Shape shape, DataBuffer<String> data) {
    return tensorOf(charset, NdArrays.wrap(shape, data));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of raw bytes.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * <p>If data must be read as raw bytes as well, the user must specify it explicitly by invoking
   * {@link #asBytes()} on the returned data:
   *
   * <pre>{@code
   * byte[] bytes = tensor.data().asBytes().getObject(0);  // returns first sequence of bytes in the tensor
   * }</pre>
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TString tensorOfBytes(NdArray<byte[]> src) {
    TStringInitializer<byte[]> initializer = new TStringInitializer<>(src, Function.identity());
    return Tensor.of(TString.DTYPE, src.shape(), initializer.computeRequiredSize(), initializer);
  }

  /**
   * Allocates a new tensor with the given shape and raw bytes.
   *
   * <p>The data will be copied from the provided buffer to the tensor after it has been allocated.
   *
   * <p>If data must be read as raw bytes as well, the user must specify it explicitly by invoking
   * {@link #asBytes()} on the returned data:
   *
   * <pre>{@code
   * byte[] bytes = tensor.data().asBytes().getObject(0);  // returns first sequence of bytes in the tensor
   * }</pre>
   *
   * @param shape shape of the tensor to create
   * @param data the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static TString tensorOfBytes(Shape shape, DataBuffer<byte[]> data) {
    return tensorOfBytes(NdArrays.wrap(shape, data));
  }

  /**
   * Use a specific charset for decoding data from a string tensor, instead of the default UTF-8.
   *
   * <p>The charset must match the one used for encoding the string values when the tensor was
   * created. For example:
   *
   * <pre>{@code
   * TString tensor =
   *    TString.tensorOf(StandardCharsets.UTF_16, NdArrays.scalarOfObject("TensorFlow");
   *
   * assertEquals("TensorFlow", tensor.data().using(StandardCharsets.UTF_16).getObject());
   * }</pre>
   *
   * @param charset charset to use
   * @return string tensor data using this charset
   */
  TString using(Charset charset);

  /** @return the tensor data as a n-dimensional array of raw byte sequences. */
  NdArray<byte[]> asBytes();
}
