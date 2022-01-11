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

import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.DoubleSparseSlice;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A sparse array for the double data type
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Double, long...)} methods
 *
 * <pre>{@code
 * DoubleSparseNdArray st = new DoubleSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorsOf(new double[] {1, 2}),
 *      Shape.of(3, 4));
 *
 * }</pre>
 *
 * <p>represents the dense array:
 *
 * <pre>{@code
 * [[1, 0, 0, 0]
 *  [0, 0, 2, 0]
 *  [0, 0, 0, 0]]
 *
 * }</pre>
 */
public class DoubleSparseNdArray extends AbstractSparseNdArray<Double, DoubleNdArray>
    implements DoubleNdArray {

  /**
   * Creates a DoubleSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D DoubleNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  protected DoubleSparseNdArray(
      LongNdArray indices, DoubleNdArray values, double defaultValue, DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a DoubleSparseNdArray with a default value of zero.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D DoubleNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  DoubleSparseNdArray(LongNdArray indices, DoubleNdArray values, DimensionalSpace dimensions) {
    this(indices, values, 0d, dimensions);
  }

  /**
   * Creates a DoubleSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  DoubleSparseNdArray(DoubleDataBuffer dataBuffer, DimensionalSpace dimensions) {
    this(dataBuffer, 0d, dimensions);
  }
  /**
   * Creates a DoubleSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  DoubleSparseNdArray(
      DoubleDataBuffer dataBuffer, double defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    // use write to set up the indices and values
    write(dataBuffer);
  }

  /**
   * Creates a zero-filled DoubleSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  DoubleSparseNdArray(DimensionalSpace dimensions) {
    this(0d, dimensions);
  }

  /**
   * Creates a zero-filled DoubleSparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  DoubleSparseNdArray(double defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
  }

  /**
   * Creates a new DoubleSparseNdArray
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
  public static DoubleSparseNdArray create(
      LongNdArray indices, DoubleNdArray values, DimensionalSpace dimensions) {
    return new DoubleSparseNdArray(indices, values, dimensions);
  }

  /**
   * Creates a new DoubleSparseNdArray
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
  public static DoubleSparseNdArray create(
      LongNdArray indices, DoubleNdArray values, double defaultValue, DimensionalSpace dimensions) {
    return new DoubleSparseNdArray(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new DoubleSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(
      DoubleDataBuffer dataBuffer, DimensionalSpace dimensions) {
    return new DoubleSparseNdArray(dataBuffer, dimensions);
  }

  /**
   * Creates a new DoubleSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(
      DoubleDataBuffer dataBuffer, double defaultValue, DimensionalSpace dimensions) {
    return new DoubleSparseNdArray(dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty DoubleSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(DimensionalSpace dimensions) {
    return new DoubleSparseNdArray(dimensions);
  }

  /**
   * Creates a new empty DoubleSparseNdArray from a data buffer
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(double defaultValue, DimensionalSpace dimensions) {
    return new DoubleSparseNdArray(defaultValue, dimensions);
  }

  /**
   * Creates a new empty DoubleSparseNdArray from a double data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(DoubleDataBuffer buffer, Shape shape) {
    return new DoubleSparseNdArray(buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty DoubleSparseNdArray from a double data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(
      DoubleDataBuffer buffer, double defaultValue, Shape shape) {
    return new DoubleSparseNdArray(buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new DoubleSparseNdArray from a DoubleNdArray
   *
   * @param src the DoubleNdArray
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(DoubleNdArray src) {
    DoubleDataBuffer buffer = DataBuffers.ofDoubles(src.size());
    src.read(buffer);
    return new DoubleSparseNdArray(buffer, DimensionalSpace.create(src.shape()));
  }
  /**
   * Creates a new DoubleSparseNdArray from a DoubleNdArray
   *
   * @param src the DoubleNdArray
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static DoubleSparseNdArray create(DoubleNdArray src, double defaultValue) {
    DoubleDataBuffer buffer = DataBuffers.ofDoubles(src.size());
    src.read(buffer);
    return new DoubleSparseNdArray(buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a DoubleNdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a DoubleNdArray of the specified shape
   */
  public DoubleNdArray createValues(Shape shape) {
    return NdArrays.ofDoubles(shape);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new DoubleSparseSlice(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public double getDouble(long... coordinates) {
    return getObject(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray setDouble(double value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray read(DataBuffer<Double> dst) {
    return read((DoubleDataBuffer) dst);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray read(DoubleDataBuffer dst) {
    // set buf to the default values, then overwrite with the indices/values.
    Double[] defaults = new Double[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              double value = getValues().getDouble(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray write(DoubleDataBuffer src) {
    List<long[]> indices = new ArrayList<>();
    List<Double> values = new ArrayList<>();

    for (long i = 0; i < src.size(); i++) {
      if (!src.getObject(i).equals(getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    double[] valuesArray = new double[values.size()];
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
  public DoubleNdArray write(DataBuffer<Double> src) {
    return write((DoubleDataBuffer) src);
  }

  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  public DoubleNdArray toDense() {
    DoubleDataBuffer dataBuffer = DataBuffers.ofDoubles(shape().size());
    read(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public DoubleNdArray fromDense(DoubleNdArray src) {
    DoubleDataBuffer buffer = DataBuffers.ofDoubles(src.size());
    src.read(buffer);
    write(buffer);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray slice(Index... indices) {
    return (DoubleNdArray) super.slice(indices);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray get(long... coordinates) {
    return (DoubleNdArray) super.get(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray setObject(Double value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray set(NdArray<Double> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray copyTo(NdArray<Double> dst) {
    return (DoubleNdArray) super.copyTo(dst);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray createDefaultArray() {
    return NdArrays.scalarOf(getDefaultValue());
  }
}
