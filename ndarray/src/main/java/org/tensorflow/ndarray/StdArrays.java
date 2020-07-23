package org.tensorflow.ndarray;

import static org.tensorflow.ndarray.NdArrays.vectorOf;

import java.lang.reflect.Array;
import org.tensorflow.ndarray.buffer.DataBuffers;

/**
 * Utility class for working with {@link NdArray} instances mixed with standard Java arrays.
 */
public final class StdArrays {

  /**
   * Copy an array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][][][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy an array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][][][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy an array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][][][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy an array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][][][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy an array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][][][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy an array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][][][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy an array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][][][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy an array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[] array) {
    @SuppressWarnings("unchecked")
    NdArray<T> ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 2-dimensional array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 3-dimensional array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 4-dimensional array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 5-dimensional array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a 6-dimensional array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][][][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(array, ndArray);
    return ndArray;
  }

  /**
   * Copy a {@link IntNdArray} in a new 1-dimension standard array of ints
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static int[] array1dCopyOf(IntNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 1);
    int[] array = new int[dims[0]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link IntNdArray} in a new 2-dimension standard array of ints
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static int[][] array2dCopyOf(IntNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 2);
    int[][] array = new int[dims[0]][dims[1]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link IntNdArray} in a new 3-dimension standard array of ints
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static int[][][] array3dCopyOf(IntNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 3);
    int[][][] array = new int[dims[0]][dims[1]][dims[2]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link IntNdArray} in a new 4-dimension standard array of ints
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static int[][][][] array4dCopyOf(IntNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 4);
    int[][][][] array = new int[dims[0]][dims[1]][dims[2]][dims[3]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link IntNdArray} in a new 5-dimension standard array of ints
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static int[][][][][] array5dCopyOf(IntNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 5);
    int[][][][][] array = new int[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link IntNdArray} in a new 6-dimension standard array of ints
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static int[][][][][][] array6dCopyOf(IntNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 6);
    int[][][][][][] array = new int[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]][dims[5]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link LongNdArray} in a new 1-dimension standard array of longs
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static long[] array1dCopyOf(LongNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 1);
    long[] array = new long[dims[0]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link LongNdArray} in a new 2-dimension standard array of longs
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static long[][] array2dCopyOf(LongNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 2);
    long[][] array = new long[dims[0]][dims[1]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link LongNdArray} in a new 3-dimension standard array of longs
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static long[][][] array3dCopyOf(LongNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 3);
    long[][][] array = new long[dims[0]][dims[1]][dims[2]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link LongNdArray} in a new 4-dimension standard array of longs
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static long[][][][] array4dCopyOf(LongNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 4);
    long[][][][] array = new long[dims[0]][dims[1]][dims[2]][dims[3]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link LongNdArray} in a new 5-dimension standard array of longs
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static long[][][][][] array5dCopyOf(LongNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 5);
    long[][][][][] array = new long[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link LongNdArray} in a new 6-dimension standard array of longs
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static long[][][][][][] array6dCopyOf(LongNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 6);
    long[][][][][][] array = new long[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]][dims[5]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link FloatNdArray} in a new 1-dimension standard array of floats
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static float[] array1dCopyOf(FloatNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 1);
    float[] array = new float[dims[0]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link FloatNdArray} in a new 2-dimension standard array of floats
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static float[][] array2dCopyOf(FloatNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 2);
    float[][] array = new float[dims[0]][dims[1]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link FloatNdArray} in a new 3-dimension standard array of floats
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static float[][][] array3dCopyOf(FloatNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 3);
    float[][][] array = new float[dims[0]][dims[1]][dims[2]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link FloatNdArray} in a new 4-dimension standard array of floats
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static float[][][][] array4dCopyOf(FloatNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 4);
    float[][][][] array = new float[dims[0]][dims[1]][dims[2]][dims[3]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link FloatNdArray} in a new 5-dimension standard array of floats
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static float[][][][][] array5dCopyOf(FloatNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 5);
    float[][][][][] array = new float[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link FloatNdArray} in a new 6-dimension standard array of floats
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static float[][][][][][] array6dCopyOf(FloatNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 6);
    float[][][][][][] array = new float[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]][dims[5]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link DoubleNdArray} in a new 1-dimension standard array of doubles
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static double[] array1dCopyOf(DoubleNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 1);
    double[] array = new double[dims[0]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link DoubleNdArray} in a new 2-dimension standard array of doubles
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static double[][] array2dCopyOf(DoubleNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 2);
    double[][] array = new double[dims[0]][dims[1]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link DoubleNdArray} in a new 3-dimension standard array of doubles
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static double[][][] array3dCopyOf(DoubleNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 3);
    double[][][] array = new double[dims[0]][dims[1]][dims[2]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link DoubleNdArray} in a new 4-dimension standard array of doubles
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static double[][][][] array4dCopyOf(DoubleNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 4);
    double[][][][] array = new double[dims[0]][dims[1]][dims[2]][dims[3]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link DoubleNdArray} in a new 5-dimension standard array of doubles
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static double[][][][][] array5dCopyOf(DoubleNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 5);
    double[][][][][] array = new double[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link DoubleNdArray} in a new 6-dimension standard array of doubles
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static double[][][][][][] array6dCopyOf(DoubleNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 6);
    double[][][][][][] array = new double[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]][dims[5]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ByteNdArray} in a new 1-dimension standard array of bytes
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static byte[] array1dCopyOf(ByteNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 1);
    byte[] array = new byte[dims[0]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ByteNdArray} in a new 2-dimension standard array of bytes
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static byte[][] array2dCopyOf(ByteNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 2);
    byte[][] array = new byte[dims[0]][dims[1]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ByteNdArray} in a new 3-dimension standard array of bytes
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static byte[][][] array3dCopyOf(ByteNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 3);
    byte[][][] array = new byte[dims[0]][dims[1]][dims[2]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ByteNdArray} in a new 4-dimension standard array of bytes
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static byte[][][][] array4dCopyOf(ByteNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 4);
    byte[][][][] array = new byte[dims[0]][dims[1]][dims[2]][dims[3]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ByteNdArray} in a new 5-dimension standard array of bytes
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static byte[][][][][] array5dCopyOf(ByteNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 5);
    byte[][][][][] array = new byte[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ByteNdArray} in a new 6-dimension standard array of bytes
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static byte[][][][][][] array6dCopyOf(ByteNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 6);
    byte[][][][][][] array = new byte[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]][dims[5]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ShortNdArray} in a new 1-dimension standard array of shorts
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static short[] array1dCopyOf(ShortNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 1);
    short[] array = new short[dims[0]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ShortNdArray} in a new 2-dimension standard array of shorts
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static short[][] array2dCopyOf(ShortNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 2);
    short[][] array = new short[dims[0]][dims[1]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ShortNdArray} in a new 3-dimension standard array of shorts
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static short[][][] array3dCopyOf(ShortNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 3);
    short[][][] array = new short[dims[0]][dims[1]][dims[2]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ShortNdArray} in a new 4-dimension standard array of shorts
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static short[][][][] array4dCopyOf(ShortNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 4);
    short[][][][] array = new short[dims[0]][dims[1]][dims[2]][dims[3]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ShortNdArray} in a new 5-dimension standard array of shorts
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static short[][][][][] array5dCopyOf(ShortNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 5);
    short[][][][][] array = new short[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link ShortNdArray} in a new 6-dimension standard array of shorts
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static short[][][][][][] array6dCopyOf(ShortNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 6);
    short[][][][][][] array = new short[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]][dims[5]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link BooleanNdArray} in a new 1-dimension standard array of booleans
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static boolean[] array1dCopyOf(BooleanNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 1);
    boolean[] array = new boolean[dims[0]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link BooleanNdArray} in a new 2-dimension standard array of booleans
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static boolean[][] array2dCopyOf(BooleanNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 2);
    boolean[][] array = new boolean[dims[0]][dims[1]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link BooleanNdArray} in a new 3-dimension standard array of booleans
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static boolean[][][] array3dCopyOf(BooleanNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 3);
    boolean[][][] array = new boolean[dims[0]][dims[1]][dims[2]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link BooleanNdArray} in a new 4-dimension standard array of booleans
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static boolean[][][][] array4dCopyOf(BooleanNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 4);
    boolean[][][][] array = new boolean[dims[0]][dims[1]][dims[2]][dims[3]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link BooleanNdArray} in a new 5-dimension standard array of booleans
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static boolean[][][][][] array5dCopyOf(BooleanNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 5);
    boolean[][][][][] array = new boolean[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link BooleanNdArray} in a new 6-dimension standard array of booleans
   *
   * @param ndArray source array
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static boolean[][][][][][] array6dCopyOf(BooleanNdArray ndArray) {
    int[] dims = computeArrayDims(ndArray, 6);
    boolean[][][][][][] array = new boolean[dims[0]][dims[1]][dims[2]][dims[3]][dims[4]][dims[5]];
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link NdArray<T>} in a new 1-dimension standard array of objects
   *
   * @param ndArray source array
   * @param objectType type of object
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-1 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static <T> T[] array1dCopyOf(NdArray<T> ndArray, Class<T> objectType) {
    int[] dims = computeArrayDims(ndArray, 1);
    T[] array = (T[])Array.newInstance(objectType, dims[0]);
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link NdArray<T>} in a new 2-dimension standard array of objects
   *
   * @param ndArray source array
   * @param objectType type of object
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-2 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static <T> T[][] array2dCopyOf(NdArray<T> ndArray, Class<T> objectType) {
    int[] dims = computeArrayDims(ndArray, 2);
    T[][] array = (T[][])Array.newInstance(objectType, dims[0], dims[1]);
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link NdArray<T>} in a new 3-dimension standard array of objects
   *
   * @param ndArray source array
   * @param objectType type of object
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-3 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static <T> T[][][] array3dCopyOf(NdArray<T> ndArray, Class<T> objectType) {
    int[] dims = computeArrayDims(ndArray, 3);
    T[][][] array = (T[][][])Array.newInstance(objectType, dims[0], dims[1], dims[2]);
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link NdArray<T>} in a new 4-dimension standard array of objects
   *
   * @param ndArray source array
   * @param objectType type of object
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-4 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static <T> T[][][][] array4dCopyOf(NdArray<T> ndArray, Class<T> objectType) {
    int[] dims = computeArrayDims(ndArray, 4);
    T[][][][] array = (T[][][][])Array.newInstance(objectType, dims[0], dims[1], dims[2], dims[3]);
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link NdArray<T>} in a new 5-dimension standard array of objects
   *
   * @param ndArray source array
   * @param objectType type of object
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-5 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static <T> T[][][][][] array5dCopyOf(NdArray<T> ndArray, Class<T> objectType) {
    int[] dims = computeArrayDims(ndArray, 5);
    T[][][][][] array =
        (T[][][][][])Array.newInstance(objectType, dims[0], dims[1], dims[2], dims[3], dims[4]);
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy a {@link NdArray<T>} in a new 6-dimension standard array of objects
   *
   * @param ndArray source array
   * @param objectType type of object
   * @return the array copy
   * @throws IllegalArgumentException if {@code ndArray} is not of rank-6 or has a shape that
   *                                  exceeds standard arrays limits
   */
  public static <T> T[][][][][][] array6dCopyOf(NdArray<T> ndArray, Class<T> objectType) {
    int[] dims = computeArrayDims(ndArray, 6);
    T[][][][][][] array =
        (T[][][][][][])Array.newInstance(objectType, dims[0], dims[1], dims[2], dims[3], dims[4], dims[5]);
    copyFrom(ndArray, array);
    return array;
  }

  /**
   * Copy an array of ints into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(int[] src, IntNdArray dst) {
    NdArrays.vectorOf(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of ints into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(int[][] src, IntNdArray dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of ints into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(int[][][] src, IntNdArray dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of ints into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(int[][][][] src, IntNdArray dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of ints into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(int[][][][][] src, IntNdArray dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of ints into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(int[][][][][][] src, IntNdArray dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy an array of longs into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(long[] src, LongNdArray dst) {
    NdArrays.vectorOf(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of longs into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(long[][] src, LongNdArray dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of longs into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(long[][][] src, LongNdArray dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of longs into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(long[][][][] src, LongNdArray dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of longs into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(long[][][][][] src, LongNdArray dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of longs into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(long[][][][][][] src, LongNdArray dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy an array of floats into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(float[] src, FloatNdArray dst) {
    NdArrays.vectorOf(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of floats into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(float[][] src, FloatNdArray dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of floats into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(float[][][] src, FloatNdArray dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of floats into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(float[][][][] src, FloatNdArray dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of floats into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(float[][][][][] src, FloatNdArray dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of floats into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(float[][][][][][] src, FloatNdArray dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy an array of doubles into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(double[] src, DoubleNdArray dst) {
    NdArrays.vectorOf(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of doubles into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(double[][] src, DoubleNdArray dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of doubles into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(double[][][] src, DoubleNdArray dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of doubles into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(double[][][][] src, DoubleNdArray dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of doubles into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(double[][][][][] src, DoubleNdArray dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of doubles into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(double[][][][][][] src, DoubleNdArray dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy an array of bytes into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(byte[] src, ByteNdArray dst) {
    NdArrays.vectorOf(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of bytes into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(byte[][] src, ByteNdArray dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of bytes into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(byte[][][] src, ByteNdArray dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of bytes into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(byte[][][][] src, ByteNdArray dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of bytes into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(byte[][][][][] src, ByteNdArray dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of bytes into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(byte[][][][][][] src, ByteNdArray dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy an array of shorts into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(short[] src, ShortNdArray dst) {
    NdArrays.vectorOf(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of shorts into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(short[][] src, ShortNdArray dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of shorts into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(short[][][] src, ShortNdArray dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of shorts into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(short[][][][] src, ShortNdArray dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of shorts into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(short[][][][][] src, ShortNdArray dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of shorts into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(short[][][][][][] src, ShortNdArray dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy an array of booleans into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(boolean[] src, BooleanNdArray dst) {
    NdArrays.vectorOf(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of booleans into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(boolean[][] src, BooleanNdArray dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of booleans into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(boolean[][][] src, BooleanNdArray dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of booleans into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(boolean[][][][] src, BooleanNdArray dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of booleans into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(boolean[][][][][] src, BooleanNdArray dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of booleans into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(boolean[][][][][][] src, BooleanNdArray dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOf(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy an array of objects into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-1 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(T[] src, NdArray<T> dst) {
    NdArrays.vectorOfObjects(src).copyTo(dst);
  }

  /**
   * Copy a 2-dimensional array of objects into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-2 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(T[][] src, NdArray<T> dst) {
    dst.elements(0).forEachIndexed((idx, e) ->
        NdArrays.vectorOfObjects(src[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensional array of objects into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-3 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(T[][][] src, NdArray<T> dst) {
    dst.elements(1).forEachIndexed((idx, e) ->
        NdArrays.vectorOfObjects(src[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensional array of objects into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-4 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(T[][][][] src, NdArray<T> dst) {
    dst.elements(2).forEachIndexed((idx, e) ->
        NdArrays.vectorOfObjects(src[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensional array of objects into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-5 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(T[][][][][] src, NdArray<T> dst) {
    dst.elements(3).forEachIndexed((idx, e) ->
        NdArrays.vectorOfObjects(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensional array of objects into the {@code dst} {@link NdArray}
   *
   * @param src source array
   * @param dst destination rank-6 array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(T[][][][][][] src, NdArray<T> dst) {
    dst.elements(4).forEachIndexed((idx, e) ->
        NdArrays.vectorOfObjects(src[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }


  /**
   * Copy a {@link NdArray} to an array of ints
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(IntNdArray src, int[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of ints
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(IntNdArray src, int[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of ints
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(IntNdArray src, int[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of ints
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(IntNdArray src, int[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of ints
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(IntNdArray src, int[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of ints
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(IntNdArray src, int[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Copy a {@link NdArray} to an array of longs
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(LongNdArray src, long[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of longs
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(LongNdArray src, long[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of longs
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(LongNdArray src, long[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of longs
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(LongNdArray src, long[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of longs
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(LongNdArray src, long[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of longs
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(LongNdArray src, long[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Copy a {@link NdArray} to an array of floats
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(FloatNdArray src, float[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of floats
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(FloatNdArray src, float[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of floats
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(FloatNdArray src, float[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of floats
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(FloatNdArray src, float[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of floats
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(FloatNdArray src, float[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of floats
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(FloatNdArray src, float[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Copy a {@link NdArray} to an array of doubles
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(DoubleNdArray src, double[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of doubles
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(DoubleNdArray src, double[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of doubles
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(DoubleNdArray src, double[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of doubles
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(DoubleNdArray src, double[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of doubles
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(DoubleNdArray src, double[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of doubles
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(DoubleNdArray src, double[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Copy a {@link NdArray} to an array of bytes
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ByteNdArray src, byte[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of bytes
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ByteNdArray src, byte[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of bytes
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ByteNdArray src, byte[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of bytes
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ByteNdArray src, byte[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of bytes
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ByteNdArray src, byte[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of bytes
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ByteNdArray src, byte[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Copy a {@link NdArray} to an array of shorts
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ShortNdArray src, short[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of shorts
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ShortNdArray src, short[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of shorts
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ShortNdArray src, short[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of shorts
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ShortNdArray src, short[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of shorts
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ShortNdArray src, short[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of shorts
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(ShortNdArray src, short[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Copy a {@link NdArray} to an array of booleans.
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(BooleanNdArray src, boolean[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of booleans
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(BooleanNdArray src, boolean[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of booleans
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(BooleanNdArray src, boolean[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of booleans
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(BooleanNdArray src, boolean[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of booleans
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(BooleanNdArray src, boolean[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of booleans
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static void copyFrom(BooleanNdArray src, boolean[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Copy a {@link NdArray} to an array of objects
   *
   * @param src source rank-1 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-1
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static <T> void copyFrom(NdArray<T> src, T[] dst) {
    if (src.rank() != 1) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    if (src.size() > dst.length) {
      throw new ArrayIndexOutOfBoundsException(String.valueOf(src.size()) + " > " + dst.length);
    }
    src.read(DataBuffers.of(dst, false, false));
  }

  /**
   * Copy a {@link NdArray} to a 2-dimensional array of objects
   *
   * @param src source rank-2 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-2
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static <T> void copyFrom(NdArray<T> src, T[][] dst) {
    if (src.rank() != 2) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(0).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 3-dimensional array of objects
   *
   * @param src source rank-3 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-3
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static <T> void copyFrom(NdArray<T> src, T[][][] dst) {
    if (src.rank() != 3) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(1).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 4-dimensional array of objects
   *
   * @param src source rank-4 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-4
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static <T> void copyFrom(NdArray<T> src, T[][][][] dst) {
    if (src.rank() != 4) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(2).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 5-dimensional array of objects
   *
   * @param src source rank-5 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-5
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static <T> void copyFrom(NdArray<T> src, T[][][][][] dst) {
    if (src.rank() != 5) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(3).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]])
    );
  }

  /**
   * Copy a {@link NdArray} to a 6-dimensional array of objects
   *
   * @param src source rank-6 array
   * @param dst destination array
   * @throws IllegalArgumentException if {@code src} is not of rank-6
   * @throws ArrayIndexOutOfBoundsException if not all elements of {@code src} can fit it the destination array
   */
  public static <T> void copyFrom(NdArray<T> src, T[][][][][][] dst) {
    if (src.rank() != 6) {
      throw new IllegalArgumentException("Array cannot be copied from NdArray of rank " + src.rank());
    }
    src.elements(4).forEachIndexed((idx, e) ->
        copyFrom(e, dst[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]])
    );
  }

  /**
   * Compute the shape of an int array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional int array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional int array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional int array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional int array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional int array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a long array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional long array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional long array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional long array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional long array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional long array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a float array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional float array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional float array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional float array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional float array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional float array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a double array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional double array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional double array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional double array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional double array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional double array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a byte array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional byte array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional byte array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional byte array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional byte array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional byte array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a short array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional short array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional short array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional short array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional short array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional short array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a boolean array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional boolean array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional boolean array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional boolean array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional boolean array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional boolean array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of an object array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 2-dimensional object array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensional object array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensional object array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensional object array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensional object array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  private static void dimSize(int arrayLength, long[] shape, int dimIdx) {
    if (shape[dimIdx] == 0) {
      shape[dimIdx] = arrayLength;
    } else if (shape[dimIdx] != arrayLength) {
      shape[dimIdx] = Shape.UNKNOWN_SIZE;
    }
  }

  private static long[] computeShape(int[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static long[] computeShape(int[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(int[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(int[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(int[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(long[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static long[] computeShape(long[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(long[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(long[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(long[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(float[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static long[] computeShape(float[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(float[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(float[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(float[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(double[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static long[] computeShape(double[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(double[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(double[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(double[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(byte[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static long[] computeShape(byte[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(byte[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(byte[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(byte[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(short[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static long[] computeShape(short[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(short[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(short[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(short[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(boolean[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static long[] computeShape(boolean[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(boolean[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(boolean[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static long[] computeShape(boolean[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static <T> long[] computeShape(T[][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 2);
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == null) {
        throw new IllegalStateException("One of the subarray is null");
      }
      dimSize(array[i].length, shape, shape.length - 1);
    }
    return shape;
  }

  private static <T> long[] computeShape(T[][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 3);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static <T> long[] computeShape(T[][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 4);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static <T> long[] computeShape(T[][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 5);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static <T> long[] computeShape(T[][][][][][] array, long[] shape) {
    if (array == null) {
      throw new IllegalStateException("The array or one of its subarray is null");
    }
    dimSize(array.length, shape, shape.length - 6);
    for (int i = 0; i < array.length; ++i) {
      computeShape(array[i], shape);
    }
    return shape;
  }

  private static <T> Class<T> componentTypeOf(Object array) {
    Class<?> componentType = array.getClass().getComponentType();
    while (componentType.isArray()) {
      componentType = componentType.getComponentType();
    }
    return (Class<T>)componentType;
  }

  private static int[] computeArrayDims(NdArray<?> ndArray, int expectedRank) {
    Shape shape = ndArray.shape();
    if (shape.numDimensions() != expectedRank) {
      throw new IllegalArgumentException("NdArray must be of rank " + expectedRank);
    }
    int[] arrayShape = new int[expectedRank];
    for (int i = 0; i < expectedRank; ++i) {
      long dimSize = shape.size(i);
      if (dimSize > Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Dimension " + i + " is too large to fit in a standard array (" + shape.size(i) + ")");
      }
      arrayShape[i] = (int)dimSize;
    }
    return arrayShape;
  }
}
