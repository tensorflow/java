/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.utils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.tensorflow.ndarray.*;

// TODO used in the Callbacks, this should be a part of NDArray?

/** NDArray math Utilities */
public class ND {

  /**
   * Returns a string representation of the contents of the specified array.
   *
   * <p>The string representation consists of a list of the array's elements, enclosed in square
   * brackets ("[]"). Adjacent elements are separated by the characters ", " (a comma followed by a
   * space). Elements are converted to strings as by String.valueOf(int). Returns "null" if a is
   * null.
   *
   * @param array the array to convert.
   * @return the String representaion of the contents of the specified array
   */
  public static String toString(NdArray<?> array) {
    if (array == null) return "null";
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    AtomicBoolean first = new AtomicBoolean(true);
    array
        .elements(0)
        .forEachIndexed(
            (idx, v) -> {
              if (!first.get()) {
                sb.append(", ");
              } else {
                first.set(false);
              }
              Object f = v.getObject();
              if (v.rank() == 0) {
                sb.append(f);
              } else {
                sb.append(toString(v));
              }
            });
    sb.append("]");
    return sb.toString();
  }

  /**
   * Transforms a flat index into coordinates based on shape.
   *
   * @param shape the shape
   * @param index the index
   * @return the coordinates
   */
  private static long[] getCoordinates(Shape shape, long index) {
    long[] coordinates = new long[shape.numDimensions()];

    int numDims = shape.numDimensions();
    int i = numDims - 1;
    for (; i >= 0; i--) {
      long size = shape.get(i);
      long mod = index % size;
      coordinates[i] = mod;
      index -= mod;
    }
    return coordinates;
  }

  /**
   * Gets the square root of an array.
   *
   * @param a the array
   * @return the square root of the array.
   */
  public static FloatNdArray sqrt(FloatNdArray a) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat((float) Math.sqrt(v.getFloat()), idx);
            });
    return result;
  }

  /**
   * Gets the square of an array.
   *
   * @param a the array
   * @return the square of the array.
   */
  public static FloatNdArray square(FloatNdArray a) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(v.getFloat() * v.getFloat(), idx);
            });
    return result;
  }

  /**
   * Gets the square of an array.
   *
   * @param a the array
   * @return the square of the array.
   */
  public static DoubleNdArray square(DoubleNdArray a) {
    DoubleNdArray result = NdArrays.ofDoubles(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setDouble(v.getDouble() * v.getDouble(), idx);
            });
    return result;
  }

  /**
   * Adds two arrays
   *
   * @param a the array
   * @param b the array
   * @return the resulting array from the add operation
   */
  public static FloatNdArray add(FloatNdArray a, FloatNdArray b) {
    if (a.shape().size() != b.shape().size())
      throw new IllegalArgumentException("a and b muse have the same number of dimensions");
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(v.getFloat() + b.getFloat(idx), idx);
            });
    return result;
  }

  /**
   * Adds an array with a scalar value
   *
   * @param a the array
   * @param scalar the scalar value
   * @return the resulting array from the add operation
   */
  public static FloatNdArray add(FloatNdArray a, float scalar) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());

    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(v.getFloat() + scalar, idx);
            });
    return result;
  }

  /**
   * Adds an array with a scalar value
   *
   * @param scalar the scalar value
   * @param a the array
   * @return the resulting array from the add operation
   */
  public static FloatNdArray add(float scalar, FloatNdArray a) {
    return add(a, scalar);
  }

  /**
   * subtracts one array from the other
   *
   * @param a the minuend array
   * @param b the subtrahend array
   * @return the resulting array from the subtraction operation
   */
  public static FloatNdArray sub(FloatNdArray a, FloatNdArray b) {
    if (a.shape().size() != b.shape().size())
      throw new IllegalArgumentException("a and b muse have the same number of dimensions");
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(v.getFloat() - b.getFloat(idx), idx);
            });
    return result;
  }

  /**
   * subtracte scalar from an array
   *
   * @param a the minuend array
   * @param scalar the subtrahend value
   * @return the resulting array from the subtraction operation
   */
  public static FloatNdArray sub(FloatNdArray a, float scalar) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(v.getFloat() - scalar, idx);
            });
    return result;
  }

  /**
   * subtract an array from a scalar
   *
   * @param scalar the minuend value
   * @param a the subtrahend array
   * @return the resulting array from the subtraction operation
   */
  public static FloatNdArray sub(float scalar, FloatNdArray a) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(scalar - v.getFloat(), idx);
            });
    return result;
  }

  /**
   * Multiply 2 arrays
   *
   * @param a the first array
   * @param b the second array
   * @return the resulting array from the muliply operation
   */
  public static FloatNdArray mul(FloatNdArray a, FloatNdArray b) {
    if (!a.shape().equals(b.shape()))
      throw new IllegalArgumentException(
          String.format(
              "ValueError: operands do not have same shapes %s %s ", a.shape(), b.shape()));
    boolean sameSize = a.shape().size() == b.shape().size();
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();

    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              if (sameSize) {
                result.setFloat(v.getFloat() * b.getFloat(idx), idx);
              } else {
                float value = v.getFloat() * b.getFloat(idx[0], 0L);
                result.setFloat(value, idx);
              }
            });
    return result;
  }

  /**
   * Multiply an array with a scalar value
   *
   * @param a the array
   * @param scalar the scalar value
   * @return the resulting array from the Multiply operation
   */
  public static FloatNdArray mul(FloatNdArray a, float scalar) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    if (a.shape().isScalar()) {
      a.scalars().forEach(f -> result.setFloat(f.getFloat() * scalar));
    } else {
      a.scalars().forEachIndexed((idx, f) -> result.setFloat(f.getFloat() * scalar, idx));
    }

    return result;
  }

  /**
   * Multiply a scalar value with an array
   *
   * @param scalar the scalar value
   * @param a the array
   * @return the resulting array from the Multiply operation
   */
  public static FloatNdArray mul(float scalar, FloatNdArray a) {
    return mul(a, scalar);
  }

  /**
   * Multiply 2 arrays
   *
   * @param a the first array
   * @param b the second array
   * @return the resulting array from the muliply operation
   */
  public static DoubleNdArray mul(DoubleNdArray a, DoubleNdArray b) {
    if (!a.shape().equals(b.shape()))
      throw new IllegalArgumentException(
          String.format(
              "ValueError: operands do not have same shapes %s %s ", a.shape(), b.shape()));
    boolean sameSize = a.shape().size() == b.shape().size();
    DoubleNdArray result = NdArrays.ofDoubles(a.shape());
    int nDims = a.shape().numDimensions();

    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              if (sameSize) {
                result.setDouble(v.getDouble() * b.getDouble(idx), idx);
              } else {
                double value = v.getDouble() * b.getDouble(idx[0], 0L);
                result.setDouble(value, idx);
              }
            });
    return result;
  }

  /**
   * Multiply an array with a scalar value
   *
   * @param a the array
   * @param scalar the scalar value
   * @return the resulting array from the Multiply operation
   */
  public static DoubleNdArray mul(DoubleNdArray a, float scalar) {
    DoubleNdArray result = NdArrays.ofDoubles(a.shape());
    if (a.shape().isScalar()) {
      a.scalars().forEach(f -> result.setDouble(f.getDouble() * scalar));
    } else {
      a.scalars().forEachIndexed((idx, f) -> result.setDouble(f.getDouble() * scalar, idx));
    }

    return result;
  }

  /**
   * Multiply a scalar value with an array
   *
   * @param scalar the scalar value
   * @param a the array
   * @return the resulting array from the Multiply operation
   */
  public static DoubleNdArray mul(float scalar, DoubleNdArray a) {
    return mul(a, scalar);
  }

  /**
   * Divide two arrays
   *
   * @param a the dividend array
   * @param b the divisor array
   * @return the resulting array from the Divide operation
   */
  public static FloatNdArray div(FloatNdArray a, FloatNdArray b) {
    if (a.shape().size() != b.shape().size())
      throw new IllegalArgumentException("a and b muse have the same number of dimensions");
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(v.getFloat() / b.getFloat(idx), idx);
            });
    return result;
  }

  /**
   * Divide an array by a scalar
   *
   * @param a the dividend array
   * @param scalar the scalar divisor
   * @return the resulting array from the Divide operation
   */
  public static FloatNdArray div(FloatNdArray a, float scalar) {
    if (scalar == 0) throw new IllegalArgumentException("Cannot divide by zero");
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(v.getFloat() / scalar, idx);
            });
    return result;
  }

  /**
   * Divide a scalar by an array
   *
   * @param scalar the scalar dividend
   * @param a the divisor array
   * @return the resulting array from the Divide operation
   */
  public static FloatNdArray div(float scalar, FloatNdArray a) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              float value = v.getFloat() == 0.0F ? Float.NaN : scalar / v.getFloat();
              result.setFloat(value, idx);
            });
    return result;
  }

  /**
   * Raise the first array by the power of the second array
   *
   * @param a the first array
   * @param b the second array
   * @return the array result of the power operation
   */
  public static FloatNdArray pow(FloatNdArray a, FloatNdArray b) {
    if (a.shape().size() != b.shape().size())
      throw new IllegalArgumentException("a and b muse have the same number of dimensions");
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat((float) Math.pow(v.getFloat(), b.getFloat(idx)), idx);
            });
    return result;
  }

  /**
   * Raise the first array by the power of the scalar value
   *
   * @param a the first array
   * @param scalar the scalar value
   * @return the array result of the power operation
   */
  public static FloatNdArray pow(FloatNdArray a, float scalar) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat((float) Math.pow(v.getFloat(), scalar), idx);
            });
    return result;
  }

  /**
   * Raise the scalar value by the power of the array
   *
   * @param scalar the scalar value
   * @param a the first array
   * @return the array result of the power operation
   */
  public static FloatNdArray pow(float scalar, FloatNdArray a) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat((float) Math.pow(scalar, v.getFloat()), idx);
            });
    return result;
  }

  /**
   * Flatten an array to 1D
   *
   * @param a the array to flatten
   * @return the flattened array
   */
  public static float[] flatten(FloatNdArray a) {
    float[] result = new float[(int) a.shape().size()];
    int nDims = a.shape().numDimensions();
    AtomicInteger counter = new AtomicInteger();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result[counter.getAndAdd(1)] = v.getFloat();
            });
    return result;
  }

  /**
   * Get the maximum value of the array
   *
   * @param a the array
   * @return the maximum value of the array
   */
  public static float max(FloatNdArray a) {
    AtomicReference<Float> maximum = new AtomicReference<>(Float.MIN_VALUE);
    a.scalars().forEach(f -> maximum.set(Math.max(maximum.get(), f.getFloat())));
    return maximum.get();
  }

  /**
   * Get the minimum value of the array
   *
   * @param a the array
   * @return the minimum value of the array
   */
  public static float min(FloatNdArray a) {
    AtomicReference<Float> minimum = new AtomicReference<>(Float.MAX_VALUE);
    a.scalars().forEach(f -> minimum.set(Math.min(minimum.get(), f.getFloat())));
    return minimum.get();
  }

  /**
   * Get the maximum value of comparing the arrays
   *
   * @param a the first array
   * @param a the second array
   * @return the resulting array with the maximum values between each element of the arrays.
   * @throws AssertionError if the two arrays are not the same size.
   */
  public static FloatNdArray max(FloatNdArray a, FloatNdArray b) {
    if (a.shape().size() != b.shape().size())
      throw new IllegalArgumentException("a and b muse have the same number of dimensions");
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(Math.max(v.getFloat(), b.getFloat(idx)), idx);
            });
    return result;
  }

  /**
   * Get the maximum value of comparing each item of the array to scalar
   *
   * @param a the array
   * @param scalar the scalar value
   * @return the resulting array with the maximum values between each element of the array and the
   *     scalar value
   */
  public static FloatNdArray max(FloatNdArray a, float scalar) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(Math.max(v.getFloat(), scalar), idx);
            });
    return result;
  }

  /**
   * Get the maximum value of comparing each item of the array to scalar
   *
   * @param scalar the scalar value
   * @param a the array
   * @return the resulting array with the maximum values between each element of the array and the
   *     scalar value
   */
  public static FloatNdArray max(float scalar, FloatNdArray a) {
    return max(a, scalar);
  }

  /**
   * Get the minimum value of comparing the arrays
   *
   * @param a the first array
   * @param a the second array
   * @return the resulting array with the minimum values between each element of the arrays.
   * @throws AssertionError if the two arrays are not the same size.
   */
  public static FloatNdArray min(FloatNdArray a, FloatNdArray b) {
    if (a.shape().size() != b.shape().size())
      throw new IllegalArgumentException("a and b muse have the same number of dimensions");
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(Math.min(v.getFloat(), b.getFloat(idx)), idx);
            });
    return result;
  }

  /**
   * Get the minimum value of comparing each item of the array to scalar
   *
   * @param a the array
   * @param scalar the scalar value
   * @return the resulting array with the minimum values between each element of the array and the
   *     scalar value
   */
  public static FloatNdArray min(FloatNdArray a, float scalar) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    int nDims = a.shape().numDimensions();
    a.elements(nDims - 1)
        .forEachIndexed(
            (idx, v) -> {
              result.setFloat(Math.min(v.getFloat(), scalar), idx);
            });
    return result;
  }

  /**
   * Get the minimum value of comparing each item of the array to scalar
   *
   * @param scalar the scalar value
   * @param a the array
   * @return the resulting array with the minimum values between each element of the array and the
   *     scalar value
   */
  public static FloatNdArray min(float scalar, FloatNdArray a) {
    return min(a, scalar);
  }

  /**
   * Get the absolute value of each member of the array
   *
   * @param a the array
   * @return the array with the absolute value of each item.
   */
  public static FloatNdArray abs(FloatNdArray a) {
    FloatNdArray result = NdArrays.ofFloats(a.shape());
    a.scalars().forEachIndexed((idx, f) -> result.setFloat(Math.abs(f.getFloat()), idx));
    return result;
  }

  /**
   * Get the absolute value of each member of the array
   *
   * @param a the array
   * @return the array with the absolute value of each item.
   */
  public static DoubleNdArray abs(DoubleNdArray a) {
    DoubleNdArray result = NdArrays.ofDoubles(a.shape());
    a.scalars().forEachIndexed((idx, f) -> result.setDouble(Math.abs(f.getDouble()), idx));
    return result;
  }

  /**
   * Sum all elements of an array
   *
   * @param a the array
   * @return an a array with one element containing the sum.
   */
  public static FloatNdArray sum(FloatNdArray a) {
    AtomicReference<Float> sum = new AtomicReference<>(0.f);
    a.scalars().forEach(f -> sum.set(sum.get() + f.getFloat()));
    return NdArrays.scalarOf(sum.get());
  }

  /**
   * Sum all elements of an array based on the specified axis
   *
   * @param a the array
   * @param axis the axis to sum
   * @return an a array the sum over the axis less the diemsnion
   */
  public static FloatNdArray sum(FloatNdArray a, int axis) {
    return sum(a, axis, false);
  }

  /**
   * Sum all elements of an array based on the specified axis
   *
   * @param a the array
   * @param axis the axis to sum
   * @param keepDims indicates whether the dimensions over the sum should be kept or not.
   * @return an a array the sum over the axis
   */
  public static FloatNdArray sum(FloatNdArray a, int axis, boolean keepDims) {
    Shape shape = a.shape();
    int nDims = shape.numDimensions();
    int xis = nDims - 1 - axis;
    long totalSize = shape.size();
    long axisSize = shape.get(xis);
    final float[] sums = new float[(int) axisSize];

    a.scalars()
        .forEachIndexed(
            (idx, f) -> {
              sums[(int) idx[xis]] += f.getFloat();
            });

    if (keepDims) {
      long[] newDims = shape.asArray();
      newDims[axis] = 1;
      final AtomicInteger counter = new AtomicInteger();
      FloatNdArray arrayK = NdArrays.ofFloats(Shape.of(newDims));
      arrayK
          .elements(newDims.length - 1)
          .forEachIndexed(
              (idx, v) -> {
                v.setFloat(sums[counter.getAndAdd(1)]);
              });
      return arrayK;
    } else {
      return NdArrays.vectorOf(sums);
    }
  }

  /**
   * Sum all elements of an array based on the specified axis
   *
   * @param a the array
   * @param axes the axis to sum
   * @param keepDims indicates whether the dimensions over the sum should be kept or not.
   * @return an a array the sum over the axis
   */
  public static FloatNdArray sum(FloatNdArray a, Integer[] axes, boolean keepDims) {
    Shape shape = a.shape();
    if (axes == null) {
      FloatNdArray result = sum(a);
      if (keepDims) {
        float scalar = result.getFloat(0);
        long[] dims = {1, 1};
        Shape bShape = Shape.of(dims);
        FloatNdArray resultK = NdArrays.ofFloats(bShape);
        resultK.setFloat(scalar, 0, 0);
        return resultK;
      }
      return result;
    } else if (axes.length == 1) {
      return sum(a, axes[0], keepDims);
    } else {
      // TODO
      throw new UnsupportedOperationException("Multi Axis Not implemented Yet");
    }
  }

  /**
   * Sum all elements of an array
   *
   * @param a the array
   * @return an a array with one element containing the sum.
   */
  public static DoubleNdArray sum(DoubleNdArray a) {
    AtomicReference<Double> sum = new AtomicReference<>(0.);
    a.scalars().forEach(f -> sum.set(sum.get() + f.getDouble()));
    return NdArrays.scalarOf(sum.get());
  }

  /**
   * Sum all elements of an array based on the specified axis
   *
   * @param a the array
   * @param axis the axis to sum
   * @return an a array the sum over the axis less the diemsnion
   */
  public static DoubleNdArray sum(DoubleNdArray a, int axis) {
    return sum(a, axis, false);
  }

  /**
   * Sum all elements of an array over on the specified axis
   *
   * @param a the array
   * @param axis the axis to sum
   * @param keepDims indicates whether the dimensions over the sum should be kept or not.
   * @return an a array the sum over the axis
   */
  public static DoubleNdArray sum(DoubleNdArray a, int axis, boolean keepDims) {
    Shape shape = a.shape();
    int nDims = shape.numDimensions();
    int xis = nDims - 1 - axis;
    long totalSize = shape.size();
    long axisSize = shape.get(xis);
    final double[] sums = new double[(int) axisSize];

    a.scalars()
        .forEachIndexed(
            (idx, f) -> {
              sums[(int) idx[xis]] += f.getDouble();
            });

    if (keepDims) {
      long[] newDims = shape.asArray();
      newDims[axis] = 1;
      final AtomicInteger counter = new AtomicInteger();
      DoubleNdArray arrayK = NdArrays.ofDoubles(Shape.of(newDims));
      arrayK
          .elements(newDims.length - 1)
          .forEachIndexed(
              (idx, v) -> {
                v.setDouble(sums[counter.getAndAdd(1)]);
              });
      return arrayK;
    } else {
      return NdArrays.vectorOf(sums);
    }
  }

  /**
   * Sum all elements of an array based on the specified axis
   *
   * @param a the array
   * @param axes the axis to sum
   * @param keepDims indicates whether the dimensions over the sum should be kept or not.
   * @return an a array the sum over the axis
   */
  public static DoubleNdArray sum(DoubleNdArray a, Integer[] axes, boolean keepDims) {
    Shape shape = a.shape();
    if (axes == null) {
      DoubleNdArray result = sum(a);
      if (keepDims) {
        double scalar = result.getDouble(0);
        long[] dims = {1, 1};
        Shape bShape = Shape.of(dims);
        DoubleNdArray resultK = NdArrays.ofDoubles(bShape);
        resultK.setDouble(scalar, 0, 0);
        return resultK;
      }
      return result;
    } else if (axes.length == 1) {
      return sum(a, axes[0], keepDims);
    } else {
      // TODO
      throw new UnsupportedOperationException("Multi Axis Not implemented Yet");
    }
  }

  /**
   * Calculate the l2 norm of the array
   *
   * @param x the array
   * @return the l2 norm of the array
   */
  public static FloatNdArray l2_norm(FloatNdArray x) {
    return l2_norm(x, -1);
  }

  /**
   * Calculate the l2 norm of the array
   *
   * @param x the array
   * @param axis the axis to calculate over
   * @return the l2 norm of the array
   */
  public static FloatNdArray l2_norm(FloatNdArray x, int axis) {
    float epsilon = 1e-12F;
    FloatNdArray square_sum = ND.sum(ND.square(x), axis, true);
    FloatNdArray x_inv_norm = ND.div(1, ND.sqrt(ND.max(square_sum, epsilon)));
    return ND.mul(x, x_inv_norm);
  }

  /**
   * Print the array to System.out.
   *
   * @param a the array
   */
  public static void print(FloatNdArray a) {
    System.out.println("Shape: " + a.shape());
    if (a.shape().isScalar()) {
      a.scalars().forEach(f -> System.out.printf("Scalar: == %f\n", f.getFloat()));
    } else {
      a.elements(a.shape().numDimensions() - 1)
          .forEachIndexed(
              (idx, v) -> {
                System.out.printf("%s == %f\n", Arrays.toString(idx), v.getFloat());
              });
    }
    System.out.println();
  }

  /**
   * Print the array to System.out.
   *
   * @param header a string to print before the print of the array
   * @param a the array
   */
  public static void print(String header, FloatNdArray a) {
    System.out.print(header);
    System.out.print(" : ");

    print(a);
  }

  /**
   * Convert a float array to an NdArray with the provided shape.
   *
   * @param y the float array
   * @param shape the shape for the NdArray
   * @return a float array to an NdArray with the provided shape.
   */
  public static FloatNdArray create(float[] y, Shape shape) {
    FloatNdArray result = NdArrays.ofFloats(shape);
    AtomicInteger index = new AtomicInteger();
    result
        .elements(shape.numDimensions() - 1)
        .forEachIndexed(
            (idx, v) -> {
              v.setFloat(y[index.getAndAdd(1)]);
            });
    return result;
  }
}
