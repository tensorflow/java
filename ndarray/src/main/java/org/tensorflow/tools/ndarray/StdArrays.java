package org.tensorflow.tools.ndarray;

import static org.tensorflow.tools.ndarray.NdArrays.vectorOf;
import static org.tensorflow.tools.ndarray.NdArrays.vectorOfObjects;

import org.tensorflow.tools.Shape;

/**
 * Utility class for working with {@link NdArray} instances mixed with standard Java arrays.
 */
public final class StdArrays {

  /**
   * Copy a single-dimension array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of ints in a new {@link IntNdArray}
   *
   * @param array source array
   * @return the {@code IntNdArray} copy
   */
  public static IntNdArray ndCopyOf(int[][][][][][] array) {
    IntNdArray ndArray = NdArrays.ofInts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of longs in a new {@link LongNdArray}
   *
   * @param array source array
   * @return the {@code LongNdArray} copy
   */
  public static LongNdArray ndCopyOf(long[][][][][][] array) {
    LongNdArray ndArray = NdArrays.ofLongs(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of floats in a new {@link FloatNdArray}
   *
   * @param array source array
   * @return the {@code FloatNdArray} copy
   */
  public static FloatNdArray ndCopyOf(float[][][][][][] array) {
    FloatNdArray ndArray = NdArrays.ofFloats(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of doubles in a new {@link DoubleNdArray}
   *
   * @param array source array
   * @return the {@code DoubleNdArray} copy
   */
  public static DoubleNdArray ndCopyOf(double[][][][][][] array) {
    DoubleNdArray ndArray = NdArrays.ofDoubles(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of bytes in a new {@link ByteNdArray}
   *
   * @param array source array
   * @return the {@code ByteNdArray} copy
   */
  public static ByteNdArray ndCopyOf(byte[][][][][][] array) {
    ByteNdArray ndArray = NdArrays.ofBytes(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of shorts in a new {@link ShortNdArray}
   *
   * @param array source array
   * @return the {@code ShortNdArray} copy
   */
  public static ShortNdArray ndCopyOf(short[][][][][][] array) {
    ShortNdArray ndArray = NdArrays.ofShorts(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of booleans in a new {@link BooleanNdArray}
   *
   * @param array source array
   * @return the {@code BooleanNdArray} copy
   */
  public static BooleanNdArray ndCopyOf(boolean[][][][][][] array) {
    BooleanNdArray ndArray = NdArrays.ofBooleans(shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[] array) {
    @SuppressWarnings("unchecked")
    NdArray<T> ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 2-dimensions array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 3-dimensions array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 4-dimensions array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 5-dimensions array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a 6-dimensions array of objects in a new {@link NdArray}
   *
   * @param array source array
   * @return the {@code NdArray} copy
   */
  public static <T> NdArray<T> ndCopyOf(T[][][][][][] array) {
    @SuppressWarnings("unchecked")
    NdArray<T>ndArray = NdArrays.ofObjects(componentTypeOf(array), shapeOf(array));
    copyTo(ndArray, array);
    return ndArray;
  }

  /**
   * Copy a single-dimension array of ints into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(IntNdArray dst, int[] array) {
    vectorOf(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of ints into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(IntNdArray dst, int[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of ints into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(IntNdArray dst, int[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of ints into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(IntNdArray dst, int[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of ints into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(IntNdArray dst, int[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of ints into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(IntNdArray dst, int[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy a single-dimension array of longs into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(LongNdArray dst, long[] array) {
    vectorOf(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of longs into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(LongNdArray dst, long[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of longs into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(LongNdArray dst, long[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of longs into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(LongNdArray dst, long[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of longs into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(LongNdArray dst, long[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of longs into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(LongNdArray dst, long[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy a single-dimension array of floats into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(FloatNdArray dst, float[] array) {
    vectorOf(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of floats into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(FloatNdArray dst, float[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of floats into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(FloatNdArray dst, float[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of floats into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(FloatNdArray dst, float[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of floats into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(FloatNdArray dst, float[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of floats into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(FloatNdArray dst, float[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy a single-dimension array of doubles into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(DoubleNdArray dst, double[] array) {
    vectorOf(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of doubles into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(DoubleNdArray dst, double[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of doubles into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(DoubleNdArray dst, double[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of doubles into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(DoubleNdArray dst, double[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of doubles into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(DoubleNdArray dst, double[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of doubles into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(DoubleNdArray dst, double[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy a single-dimension array of bytes into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ByteNdArray dst, byte[] array) {
    vectorOf(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of bytes into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ByteNdArray dst, byte[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of bytes into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ByteNdArray dst, byte[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of bytes into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ByteNdArray dst, byte[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of bytes into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ByteNdArray dst, byte[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of bytes into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ByteNdArray dst, byte[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy a single-dimension array of shorts into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ShortNdArray dst, short[] array) {
    vectorOf(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of shorts into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ShortNdArray dst, short[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of shorts into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ShortNdArray dst, short[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of shorts into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ShortNdArray dst, short[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of shorts into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ShortNdArray dst, short[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of shorts into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(ShortNdArray dst, short[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy a single-dimension array of booleans into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(BooleanNdArray dst, boolean[] array) {
    vectorOf(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of booleans into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(BooleanNdArray dst, boolean[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of booleans into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(BooleanNdArray dst, boolean[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of booleans into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(BooleanNdArray dst, boolean[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of booleans into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(BooleanNdArray dst, boolean[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of booleans into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static void copyTo(BooleanNdArray dst, boolean[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOf(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Copy a single-dimension array of objects into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-1 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-1 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(NdArray<T> dst, T[] array) {
    vectorOfObjects(array).copyTo(dst);
  }

  /**
   * Copy a 2-dimensions array of objects into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-2 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-2 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(NdArray<T> dst, T[][] array) {
    dst.elements(0).forEachIndexed((idx, e) ->
        vectorOfObjects(array[(int)idx[0]]).copyTo(e)
    );
  }

  /**
   * Copy a 3-dimensions array of objects into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-3 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-3 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(NdArray<T> dst, T[][][] array) {
    dst.elements(1).forEachIndexed((idx, e) ->
        vectorOfObjects(array[(int)idx[0]][(int)idx[1]]).copyTo(e)
    );
  }

  /**
   * Copy a 4-dimensions array of objects into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-4 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-4 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(NdArray<T> dst, T[][][][] array) {
    dst.elements(2).forEachIndexed((idx, e) ->
        vectorOfObjects(array[(int)idx[0]][(int)idx[1]][(int)idx[2]]).copyTo(e)
    );
  }

  /**
   * Copy a 5-dimensions array of objects into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-5 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-5 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(NdArray<T> dst, T[][][][][] array) {
    dst.elements(3).forEachIndexed((idx, e) ->
        vectorOfObjects(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]]).copyTo(e)
    );
  }

  /**
   * Copy a 6-dimensions array of objects into the {@code dst} {@link NdArray}
   *
   * @param dst destination rank-6 array
   * @param array source array
   * @throws IllegalArgumentException if {@code dst} is not of rank-6 or has an incompatible shape
   *                                  with the source array
   */
  public static <T> void copyTo(NdArray<T> dst, T[][][][][][] array) {
    dst.elements(4).forEachIndexed((idx, e) ->
        vectorOfObjects(array[(int)idx[0]][(int)idx[1]][(int)idx[2]][(int)idx[3]][(int)idx[4]]).copyTo(e)
    );
  }

  /**
   * Compute the shape of a single-dimension int array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions int array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions int array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions int array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions int array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions int array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(int[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a single-dimension long array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions long array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions long array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions long array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions long array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions long array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(long[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a single-dimension float array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions float array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions float array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions float array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions float array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions float array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(float[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a single-dimension double array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions double array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions double array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions double array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions double array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions double array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(double[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a single-dimension byte array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions byte array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions byte array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions byte array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions byte array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions byte array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(byte[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a single-dimension short array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions short array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions short array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions short array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions short array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions short array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(short[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a single-dimension boolean array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions boolean array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions boolean array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions boolean array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions boolean array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions boolean array.
   *
   * @param array 6D array
   * @return shape of the array
   */
  public static Shape shapeOf(boolean[][][][][][] array) {
    return Shape.of(computeShape(array, new long[6]));
  }

  /**
   * Compute the shape of a single-dimension object array.
   *
   * @param array 1D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[] array) {
    return Shape.of(array.length);
  }

  /**
   * Compute the shape of a 3-dimensions object array.
   *
   * @param array 2D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][] array) {
    return Shape.of(computeShape(array, new long[2]));
  }

  /**
   * Compute the shape of a 3-dimensions object array.
   *
   * @param array 3D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][][] array) {
    return Shape.of(computeShape(array, new long[3]));
  }

  /**
   * Compute the shape of a 4-dimensions object array.
   *
   * @param array 4D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][][][] array) {
    return Shape.of(computeShape(array, new long[4]));
  }

  /**
   * Compute the shape of a 5-dimensions object array.
   *
   * @param array 5D array
   * @return shape of the array
   */
  public static <T> Shape shapeOf(T[][][][][] array) {
    return Shape.of(computeShape(array, new long[5]));
  }

  /**
   * Compute the shape of a 6-dimensions object array.
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
}
