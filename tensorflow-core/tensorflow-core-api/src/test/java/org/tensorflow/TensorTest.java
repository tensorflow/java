/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/**
 * Unit tests for {@link org.tensorflow.Tensor}.
 */
public class TensorTest {

  private static final double EPSILON = 1e-7;
  private static final float EPSILON_F = 1e-7f;

  @Test
  public void createWithRawData() {
    try (TensorScope scope = new TensorScope()) {
      double[] doubles = {1d, 2d, 3d, 4d};
      Shape doubles_shape = Shape.of(4);
      boolean[] bools = {true, false, true, false};
      byte[] bools_ = {1, 0, 1, 0};
      Shape bools_shape = Shape.of(4);
      String strings = "test";
      Shape strings_shape = Shape.scalar();
      byte[] strings_; // raw TF_STRING
      TString t = TString.tensorOf(scope, NdArrays.scalarOfObject(strings));
      strings_ = new byte[(int) t.numBytes()];
      t.asRawTensor().data().read(strings_);

      // validate creating a tensor using a raw data byte buffers
      {
        TBool t1 = Tensor.of(scope, TBool.class, bools_shape, DataBuffers.of(bools_));
        boolean[] actual = new boolean[bools_.length];
        t1.read(DataBuffers.of(actual));
        assertArrayEquals(bools, actual);

        // note: the buffer is expected to contain raw TF_STRING (as per C API)
        TString t2 = Tensor.of(scope, TString.class, strings_shape, DataBuffers.of(strings_));
        assertEquals(strings, t2.getObject());
      }

      // validate creating a tensor using a direct byte buffer (in host order)
      {
        DoubleBuffer buf = ByteBuffer.allocateDirect(8 * doubles.length).order(ByteOrder.nativeOrder())
            .asDoubleBuffer().put(doubles);
        TFloat64 t1 = TFloat64.tensorOf(scope, doubles_shape, d -> d.write(DataBuffers.of(buf)));
        double[] actual = new double[doubles.length];
        t1.read(DataBuffers.of(actual));
        assertArrayEquals(doubles, actual, EPSILON);
      }

      // validate shape checking
      try {
        TBool t1 = Tensor.of(scope, TBool.class, Shape.of(bools_.length * 2), DataBuffers.of(bools_));
        fail("should have failed on incompatible buffer");
      } catch (IllegalArgumentException e) {
        // expected
      }
    }

  }

  @Test
  public void createFromBufferWithNativeByteOrder() {
    try (TensorScope scope = new TensorScope()) {
      double[] doubles = {1d, 2d, 3d, 4d};
      DoubleBuffer buf =
          ByteBuffer.allocate(8 * doubles.length)
              .order(ByteOrder.nativeOrder())
              .asDoubleBuffer()
              .put(doubles);
      flipBuffer(buf);
      TFloat64 t = TFloat64.tensorOf(scope, Shape.of(4), DataBuffers.of(buf));
      double[] actual = new double[doubles.length];
      t.read(DataBuffers.of(actual));
      assertArrayEquals(doubles, actual, EPSILON);
    }
  }

  @Test
  public void createFromBufferWithNonNativeByteOrder() {
    try (TensorScope scope = new TensorScope()) {
      double[] doubles = {1d, 2d, 3d, 4d};
      DoubleBuffer buf =
          ByteBuffer.allocate(8 * doubles.length)
              .order(
                  ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
                      ? ByteOrder.BIG_ENDIAN
                      : ByteOrder.LITTLE_ENDIAN)
              .asDoubleBuffer()
              .put(doubles);
      flipBuffer(buf);
      TFloat64 t = TFloat64.tensorOf(scope, Shape.of(4), DataBuffers.of(buf));
      double[] actual = new double[doubles.length];
      t.read(DataBuffers.of(actual));
      assertArrayEquals(doubles, actual, EPSILON);
    }
  }

  @Test
  public void createWithTypedBuffer() {
    try (TensorScope scope = new TensorScope()) {
      IntBuffer ints = IntBuffer.wrap(new int[]{1, 2, 3, 4});
      FloatBuffer floats = FloatBuffer.wrap(new float[]{1f, 2f, 3f, 4f});
      DoubleBuffer doubles = DoubleBuffer.wrap(new double[]{1d, 2d, 3d, 4d});
      LongBuffer longs = LongBuffer.wrap(new long[]{1L, 2L, 3L, 4L});

      // validate creating a tensor using a typed buffer
      {
        Shape shape = Shape.of(4);
        TFloat64 tDouble = TFloat64.tensorOf(scope, shape, DataBuffers.of(doubles));
        DoubleBuffer doubleActual = DoubleBuffer.allocate(doubles.capacity());
        tDouble.read(DataBuffers.of(doubleActual));
        assertEquals(doubles, doubleActual);

        TFloat32 tFloat = TFloat32.tensorOf(scope, shape, DataBuffers.of(floats));
        FloatBuffer floatActual = FloatBuffer.allocate(floats.capacity());
        tFloat.read(DataBuffers.of(floatActual));
        assertEquals(floats, floatActual);

        TInt32 tInt = TInt32.tensorOf(scope, shape, DataBuffers.of(ints));
        IntBuffer intActual = IntBuffer.allocate(ints.capacity());
        tInt.read(DataBuffers.of(intActual));
        assertEquals(ints, intActual);

        TInt64 tLong = TInt64.tensorOf(scope, shape, DataBuffers.of(longs));
        LongBuffer longActual = LongBuffer.allocate(longs.capacity());
        tLong.read(DataBuffers.of(longActual));
        assertEquals(longs, longActual);

      }

      // validate shape-checking
      {
        Shape shape = Shape.of(5);
        try (TFloat64 t = TFloat64.tensorOf(scope, shape, DataBuffers.of(doubles))) {
          fail("should have failed on incompatible buffer");
        } catch (BufferUnderflowException e) {
          // expected
        }
        try (TFloat32 t = TFloat32.tensorOf(scope, shape, DataBuffers.of(floats))) {
          fail("should have failed on incompatible buffer");
        } catch (BufferUnderflowException e) {
          // expected
        }
        try (TInt32 t = TInt32.tensorOf(scope, shape, DataBuffers.of(ints))) {
          fail("should have failed on incompatible buffer");
        } catch (BufferUnderflowException e) {
          // expected
        }
        try (TInt64 t = TInt64.tensorOf(scope, shape, DataBuffers.of(longs))) {
          fail("should have failed on incompatible buffer");
        } catch (BufferUnderflowException e) {
          // expected
        }
      }
    }
  }

  @Test
  public void readFromRawData() {
    try (TensorScope scope = new TensorScope()) {
      int[] ints = {1, 2, 3};
      float[] floats = {1f, 2f, 3f};
      double[] doubles = {1d, 2d, 3d};
      long[] longs = {1L, 2L, 3L};
      boolean[] bools = {true, false, true};

      TInt32 tints = TInt32.vectorOf(scope, ints);
      TFloat32 tfloats = TFloat32.vectorOf(scope, floats);
      TFloat64 tdoubles = TFloat64.vectorOf(scope, doubles);
      TInt64 tlongs = TInt64.vectorOf(scope, longs);
      TBool tbools = TBool.vectorOf(scope, bools);

      // validate that any datatype is readable with ByteBuffer (content, position)
      {
        ByteBuffer bbuf = ByteBuffer.allocate(1024).order(ByteOrder.nativeOrder());

        clearBuffer(bbuf); // FLOAT
        assertEquals(tfloats.numBytes(), tfloats.asRawTensor().data().size());
        tfloats.asRawTensor().data().copyTo(DataBuffers.of(bbuf), tfloats.numBytes());
        assertEquals(floats[0], bbuf.asFloatBuffer().get(0), EPSILON);

        clearBuffer(bbuf); // DOUBLE
        assertEquals(tdoubles.numBytes(), tdoubles.asRawTensor().data().size());
        tdoubles.asRawTensor().data().copyTo(DataBuffers.of(bbuf), tdoubles.numBytes());
        assertEquals(doubles[0], bbuf.asDoubleBuffer().get(0), EPSILON);

        clearBuffer(bbuf); // INT3
        assertEquals(tints.numBytes(), tints.asRawTensor().data().size());
        tints.asRawTensor().data().copyTo(DataBuffers.of(bbuf), tints.numBytes());
        assertEquals(ints[0], bbuf.asIntBuffer().get(0));

        clearBuffer(bbuf); // INT64
        assertEquals(tlongs.numBytes(), tlongs.asRawTensor().data().size());
        tlongs.asRawTensor().data().copyTo(DataBuffers.of(bbuf), tlongs.numBytes());
        assertEquals(longs[0], bbuf.asLongBuffer().get(0));

        clearBuffer(bbuf); // BOOL
        assertEquals(tbools.numBytes(), tbools.asRawTensor().data().size());
        tbools.asRawTensor().data().copyTo(DataBuffers.of(bbuf), tbools.numBytes());
        assertEquals(bools[0], bbuf.get(0) != 0);
      }

      // validate the use of direct buffers
      {
        ByteBuffer bbuf =
            ByteBuffer.allocateDirect((int) tdoubles.numBytes()).order(ByteOrder.nativeOrder());
        tdoubles.asRawTensor().data().copyTo(DataBuffers.of(bbuf), tdoubles.numBytes());
        assertEquals(doubles[0], bbuf.asDoubleBuffer().get(0), EPSILON);
      }

      // validate byte order conversion
      {
        DoubleBuffer foreignBuf =
            ByteBuffer.allocate((int) tdoubles.numBytes())
                .order(
                    ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
                        ? ByteOrder.BIG_ENDIAN
                        : ByteOrder.LITTLE_ENDIAN)
                .asDoubleBuffer();
        tdoubles.asRawTensor().data().asDoubles().copyTo(DataBuffers.of(foreignBuf), foreignBuf.capacity());
        double[] actual = new double[foreignBuf.remaining()];
        foreignBuf.get(actual);
        assertArrayEquals(doubles, actual, EPSILON);
      }
    }
  }

  @Test
  public void scalars() {
    try (TensorScope scope = new TensorScope()) {
      TFloat32 tFloat = TFloat32.scalarOf(scope, 2.718f);
      assertEquals(TFloat32.class, tFloat.type());
      assertEquals(DataType.DT_FLOAT, tFloat.dataType());
      assertEquals(0, tFloat.shape().numDimensions());
      assertEquals(2.718f, tFloat.getFloat(), EPSILON_F);

      TFloat64 tDouble = TFloat64.scalarOf(scope, 3.1415);
      assertEquals(TFloat64.class, tDouble.type());
      assertEquals(DataType.DT_DOUBLE, tDouble.dataType());
      assertEquals(0, tDouble.shape().numDimensions());
      assertEquals(3.1415, tDouble.getDouble(), EPSILON);

      TInt32 tInt = TInt32.scalarOf(scope, -33);
      assertEquals(TInt32.class, tInt.type());
      assertEquals(DataType.DT_INT32, tInt.dataType());
      assertEquals(0, tInt.shape().numDimensions());
      assertEquals(-33, tInt.getInt());

      TInt64 tLong = TInt64.scalarOf(scope, 8589934592L);
      assertEquals(TInt64.class, tLong.type());
      assertEquals(DataType.DT_INT64, tLong.dataType());
      assertEquals(0, tLong.shape().numDimensions());
      assertEquals(8589934592L, tLong.getLong());

      TBool tBool = TBool.scalarOf(scope, true);
      assertEquals(TBool.class, tBool.type());
      assertEquals(DataType.DT_BOOL, tBool.dataType());
      assertEquals(0, tBool.shape().numDimensions());
      assertTrue(tBool.getBoolean());

      TString tString = TString.scalarOf(scope, "sombrero");
      assertEquals(TString.class, tString.type());
      assertEquals(DataType.DT_STRING, tString.dataType());
      assertEquals(0, tString.shape().numDimensions());
      assertEquals("sombrero", tString.getObject());

      final byte[] bytes = {1, 2, 3, 4};
      TString tByteString = TString.tensorOfBytes(scope, NdArrays.scalarOfObject(bytes));
      assertEquals(TString.class, tByteString.type());
      assertEquals(DataType.DT_STRING, tByteString.dataType());
      assertEquals(0, tByteString.shape().numDimensions());
      assertArrayEquals(bytes, tByteString.asBytes().getObject());
    }
  }

  @Test
  public void nDimensional() {
    try (TensorScope scope = new TensorScope()) {
      DoubleNdArray vector = StdArrays.ndCopyOf(new double[]{1.414, 2.718, 3.1415});
      TFloat64 tDouble = TFloat64.tensorOf(scope, vector);
      assertEquals(TFloat64.class, tDouble.type());
      assertEquals(DataType.DT_DOUBLE, tDouble.dataType());
      assertEquals(1, tDouble.shape().numDimensions());
      assertEquals(3, tDouble.shape().size(0));
      assertEquals(vector, tDouble);

      IntNdArray matrix = StdArrays.ndCopyOf(new int[][]{{1, 2, 3}, {4, 5, 6}});
      TInt32 tInt = TInt32.tensorOf(scope, matrix);
      assertEquals(TInt32.class, tInt.type());
      assertEquals(DataType.DT_INT32, tInt.dataType());
      assertEquals(2, tInt.shape().numDimensions());
      assertEquals(2, tInt.shape().size(0));
      assertEquals(3, tInt.shape().size(1));
      assertEquals(matrix, tInt);

      LongNdArray threeD = StdArrays.ndCopyOf(new long[][][]{
          {{1}, {3}, {5}, {7}, {9}}, {{2}, {4}, {6}, {8}, {0}},
      });
      TInt64 tLong = TInt64.tensorOf(scope, threeD);
      assertEquals(TInt64.class, tLong.type());
      assertEquals(DataType.DT_INT64, tLong.dataType());
      assertEquals(3, tLong.shape().numDimensions());
      assertEquals(2, tLong.shape().size(0));
      assertEquals(5, tLong.shape().size(1));
      assertEquals(1, tLong.shape().size(2));
      assertEquals(threeD, tLong);

      BooleanNdArray fourD = StdArrays.ndCopyOf(new boolean[][][][]{
          {{{false, false, false, true}, {false, false, true, false}}},
          {{{false, false, true, true}, {false, true, false, false}}},
          {{{false, true, false, true}, {false, true, true, false}}},
      });
      TBool tBool = TBool.tensorOf(scope, fourD);
      assertEquals(TBool.class, tBool.type());
      assertEquals(DataType.DT_BOOL, tBool.dataType());
      assertEquals(4, tBool.shape().numDimensions());
      assertEquals(3, tBool.shape().size(0));
      assertEquals(1, tBool.shape().size(1));
      assertEquals(2, tBool.shape().size(2));
      assertEquals(4, tBool.shape().size(3));
      assertEquals(fourD, tBool);
    }
  }

  @Test
  public void testNDimensionalStringTensor() {
    try (TensorScope scope = new TensorScope()) {
      NdArray<String> matrix = NdArrays.ofObjects(String.class, Shape.of(4, 3));
      for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 3; ++j) {
          matrix.setObject(String.format("(%d, %d) = %d", i, j, i << j), i, j);
        }
      }
      TString tString = TString.tensorOf(scope, matrix);
      assertEquals(TString.class, tString.type());
      assertEquals(DataType.DT_STRING, tString.dataType());
      assertEquals(2, tString.shape().numDimensions());
      assertEquals(4, tString.shape().size(0));
      assertEquals(3, tString.shape().size(1));
      assertEquals(matrix, tString);

      NdArray<byte[]> byteMatrix = NdArrays.ofObjects(byte[].class, matrix.shape());
      matrix.scalars().forEachIndexed((i, s) -> byteMatrix.setObject(s.getObject().getBytes(UTF_8), i));
      TString tByteString = TString.tensorOfBytes(scope, byteMatrix);
      assertEquals(TString.class, tByteString.type());
      assertEquals(DataType.DT_STRING, tByteString.dataType());
      assertEquals(2, tByteString.shape().numDimensions());
      assertEquals(4, tByteString.shape().size(0));
      assertEquals(3, tByteString.shape().size(1));
      assertEquals(byteMatrix, tByteString.asBytes());
      assertEquals(matrix, tByteString);
    }
  }

  @Test
  public void testUint8TensorFromArray() {
    try (TensorScope scope = new TensorScope()) {
      byte[] vector = new byte[]{1, 2, 3, 4};
      TUint8 t = TUint8.vectorOf(scope, vector);
      assertEquals(TUint8.class, t.type());
      assertEquals(DataType.DT_UINT8, t.dataType());
      assertEquals(1, t.shape().numDimensions());
      assertEquals(4, t.shape().size(0));

      byte[] got = new byte[4];
      t.read(DataBuffers.of(got));
      assertArrayEquals(vector, got);
    }
  }

  @Test
  public void testCreateFromArrayOfBoxed() {
    try (TensorScope scope = new TensorScope()) {
      Integer[] vector = new Integer[]{1, 2, 3, 4};
      TInt32 t = TInt32.tensorOf(scope, Shape.of(4), d -> d.write(DataBuffers.ofObjects(vector)));
      assertEquals(TInt32.class, t.type());
      assertEquals(DataType.DT_INT32, t.dataType());
      assertEquals(1, t.shape().numDimensions());
      assertEquals(4, t.shape().size(0));

      Integer[] got = new Integer[4];
      t.read(DataBuffers.ofObjects(got));
      assertArrayEquals(vector, got);
    }
  }

  @Test
  public void failCreateOnMismatchedDimensions() {
    try (TensorScope scope = new TensorScope()) {
      int[][][] invalid = new int[3][1][];
      for (int x = 0; x < invalid.length; ++x) {
        for (int y = 0; y < invalid[x].length; ++y) {
          invalid[x][y] = new int[x + y + 1];
        }
      }
      try (TInt32 t = TInt32.tensorOf(scope, StdArrays.ndCopyOf(invalid))) {
        fail("Tensor.create() should fail because of differing sizes in the 3rd dimension");
      } catch (IllegalArgumentException e) {
        // The expected exception.
      }
    }
  }

  @Test
  public void tensorWithZeroDimension() {
    try (TensorScope scope = new TensorScope()) {
      // Note: Historically, TF Java failed on purpose when trying to allocate a tensor with a shape
      // that has one or more dimensions set to 0 elements. But Python API allows it, so we should do
      // the same.
      TInt32 t = TInt32.tensorOf(scope, Shape.of(3, 0, 1));
      assertEquals(0, t.numBytes());
      assertEquals(0, t.shape().size());

      TInt32 t2 = TInt32.tensorOf(scope, StdArrays.ndCopyOf(new int[3][0][1]));
      assertEquals(0, t2.numBytes());
      assertEquals(0, t2.shape().size());
    }
  }

  @Test
  public void allocateTensorWithSize() {
    try (TensorScope scope = new TensorScope()) {
      // ok
      Tensor.of(scope, TInt32.class, Shape.of(2, 2, 2), 8 * 4);

      // ok (size requested is larger that minimum space required)
      Tensor.of(scope, TInt32.class, Shape.of(2, 2, 2), 9 * 4);

      try {
        Tensor.of(scope, TInt32.class, Shape.of(2, 2, 2), 8 * 4 - 1);
        fail();
      } catch (IllegalArgumentException e) {
        // as expected
      }
    }
  }

  @Test
  public void useAfterClose() {
    try (TensorScope scope = new TensorScope()) {
      int n = 4;
      TInt32 t = TInt32.scalarOf(scope, n);
      t.close();
      try {
        t.numBytes();
      } catch (IllegalStateException e) {
        // The expected exception.
      }
    }
  }

  @Test
  public void fromHandle() {
    try (TensorScope scope = new TensorScope()) {
      // fromHandle is a package-visible method intended for use when the C TF_Tensor object has been
      // created independently of the Java code. In practice, two Tensor instances MUST NOT have the
      // same native handle.
      //
      // An exception is made for this test, where the pitfalls of this is avoided by not calling
      // close() on both Tensors.
      final FloatNdArray matrix = StdArrays.ndCopyOf(new float[][]{{1, 2, 3}, {4, 5, 6}});
      TFloat32 src = TFloat32.tensorOf(scope, matrix);
      TFloat32 cpy = (TFloat32) RawTensor.fromHandle(scope, src.asRawTensor().nativeHandle()).asTypedTensor();
      assertEquals(src.type(), cpy.type());
      assertEquals(src.dataType(), cpy.dataType());
      assertEquals(src.shape().numDimensions(), cpy.shape().numDimensions());
      assertEquals(src.shape(), cpy.shape());
      assertEquals(matrix, cpy);

      // don't want to call close
      TensorScope.detach(cpy);
    }
  }

  @Test
  public void gracefullyFailCreationFromNullArrayForStringTensor() {
    try (TensorScope scope = new TensorScope()) {
      // Motivated by: https://github.com/tensorflow/tensorflow/issues/17130
      byte[][] array = new byte[1][];
      try {
        TUint8.tensorOf(scope, StdArrays.ndCopyOf(array));
      } catch (IllegalStateException e) {
        // expected.
      }
      byte[][][] array2 = new byte[2][2][2];
      array2[1] = null;
      try {
        TUint8.tensorOf(scope, StdArrays.ndCopyOf(array));
      } catch (IllegalStateException e) {
        // expected.
      }
    }
  }

  // Workaround for cross compiliation
  // (e.g., javac -source 1.9 -target 1.8).
  //
  // In Java 8 and prior, subclasses of java.nio.Buffer (e.g., java.nio.DoubleBuffer) inherited the
  // "flip()" and "clear()" methods from java.nio.Buffer resulting in the signature:
  //   Buffer flip();
  // In Java 9 these subclasses had their own methods like:
  //   DoubleBuffer flip();
  // As a result, compiling for 1.9 source for a target of JDK 1.8 would result in errors at runtime
  // like:
  //
  // java.lang.NoSuchMethodError: java.nio.DoubleBuffer.flip()Ljava/nio/DoubleBuffer
  private static void flipBuffer(Buffer buf) {
    buf.flip();
  }

  // See comment for flipBuffer()
  private static void clearBuffer(Buffer buf) {
    buf.clear();
  }
}
