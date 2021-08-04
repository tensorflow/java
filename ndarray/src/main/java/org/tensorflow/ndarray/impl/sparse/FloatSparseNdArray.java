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
package org.tensorflow.ndarray.impl.sparse;

import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.FloatSparseSlice;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sparse array for the float data type
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Float, long...)} methods
 *
 * <pre>{@code
 * FloatSparseNdArray st = new FloatSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf(1f, 3.14f}),
 *      Shape.of(3, 4));
 *
 * }</pre>
 *
 * <p>represents the dense array:
 *
 * <pre>{@code
 * [[1, 0, 0, 0]
 *  [0, 0, 3.14, 0]
 *  [0, 0, 0, 0]]
 *
 * }</pre>
 */
public class FloatSparseNdArray extends AbstractSparseNdArray<Float, FloatNdArray>
    implements FloatNdArray {

  /**
   * Creates a FloatSparseNdArray with a default value of zero.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D FloatNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  FloatSparseNdArray(LongNdArray indices, FloatNdArray values, DimensionalSpace dimensions) {
    this(indices, values, 0f, dimensions);
  }

  /**
   * Creates a FloatSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D FloatNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  FloatSparseNdArray(
      LongNdArray indices, FloatNdArray values, float defaultValue, DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a FloatSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  FloatSparseNdArray(FloatDataBuffer dataBuffer, DimensionalSpace dimensions) {
    this(dataBuffer, 0f, dimensions);
  }

  /**
   * Creates a FloatSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  FloatSparseNdArray(FloatDataBuffer dataBuffer, float defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    // use write to set up the indices and values
    write(dataBuffer);
  }

  /**
   * Creates a zero-filled FloatSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  FloatSparseNdArray(DimensionalSpace dimensions) {
    this(0f, dimensions);
  }

  /**
   * Creates a zero-filled FloatSparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  FloatSparseNdArray(float defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
  }

  /**
   * Creates a new FloatSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of any type and shape {@code [N]}, which supplies the values for
   *     each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter
   *     {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a
   *     value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array.
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(
      LongNdArray indices, FloatNdArray values, DimensionalSpace dimensions) {
    return new FloatSparseNdArray(indices, values, dimensions);
  }

  /**
   * Creates a new FloatSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of any type and shape {@code [N]}, which supplies the values for
   *     each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter
   *     {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a
   *     value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array.
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(
      LongNdArray indices, FloatNdArray values, float defaultValue, DimensionalSpace dimensions) {
    return new FloatSparseNdArray(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new FloatSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(FloatDataBuffer dataBuffer, DimensionalSpace dimensions) {
    return new FloatSparseNdArray(dataBuffer, dimensions);
  }

  /**
   * Creates a new FloatSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(
      FloatDataBuffer dataBuffer, float defaultValue, DimensionalSpace dimensions) {
    return new FloatSparseNdArray(dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty FloatSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(DimensionalSpace dimensions) {
    return new FloatSparseNdArray(dimensions);
  }

  /**
   * Creates a new empty FloatSparseNdArray from a data buffer
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(float defaultValue, DimensionalSpace dimensions) {
    return new FloatSparseNdArray(defaultValue, dimensions);
  }

  /**
   * Creates a new empty FloatSparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(FloatDataBuffer buffer, Shape shape) {
    return new FloatSparseNdArray(buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty FloatSparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(FloatDataBuffer buffer, float defaultValue, Shape shape) {
    return new FloatSparseNdArray(buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new FloatSparseNdArray from a FloatNdArray
   *
   * @param src the FloatNdArray
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(FloatNdArray src) {
    FloatDataBuffer buffer = DataBuffers.ofFloats(src.size());
    src.read(buffer);
    return new FloatSparseNdArray(buffer, DimensionalSpace.create(src.shape()));
  }
  /**
   * Creates a new FloatSparseNdArray from a FloatNdArray
   *
   * @param src the FloatNdArray
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static FloatSparseNdArray create(FloatNdArray src, float defaultValue) {
    FloatDataBuffer buffer = DataBuffers.ofFloats(src.size());
    src.read(buffer);
    return new FloatSparseNdArray(buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a FloatNdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a FloatNdArray of the specified shape
   */
  public FloatNdArray createValues(Shape shape) {
    return NdArrays.ofFloats(shape);
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new FloatSparseSlice(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public float getFloat(long... coordinates) {
    return getObject(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray setFloat(float value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray read(DataBuffer<Float> dst) {
    return read((FloatDataBuffer) dst);
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray read(FloatDataBuffer dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Float[] defaults = new Float[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              float value = getValues().getFloat(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray write(FloatDataBuffer src) {
    List<long[]> indices = new ArrayList<>();
    List<Float> values = new ArrayList<>();

    for (long i = 0; i < src.size(); i++) {
      if (!src.getObject(i).equals(getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    float[] valuesArray = new float[values.size()];
    for (int i = 0; i < indices.size(); i++) {
      indicesArray[i] = indices.get(i);
      valuesArray[i] = values.get(i);
    }

    setIndices(StdArrays.ndCopyOf(indicesArray));
    setValues(NdArrays.vectorOf(valuesArray));
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray write(DataBuffer<Float> src) {
    return write((FloatDataBuffer) src);
  }

  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  public FloatNdArray toDense() {
    FloatDataBuffer dataBuffer = DataBuffers.ofFloats(shape().size());
    read(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public FloatNdArray fromDense(FloatNdArray src) {
    FloatDataBuffer buffer = DataBuffers.ofFloats(src.size());
    src.read(buffer);
    write(buffer);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray slice(Index... indices) {
    return (FloatNdArray) super.slice(indices);
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray get(long... coordinates) {
    return (FloatNdArray) super.get(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray setObject(Float value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray set(NdArray<Float> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray copyTo(NdArray<Float> dst) {
    return (FloatNdArray) super.copyTo(dst);
  }

  /** {@inheritDoc} */
  @Override
  public FloatNdArray createDefaultArray() {
    return NdArrays.scalarOf(getDefaultValue());
  }
}
