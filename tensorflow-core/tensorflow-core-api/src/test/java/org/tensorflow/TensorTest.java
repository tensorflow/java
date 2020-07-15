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
import org.tensorflow.op.Ops;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/** Unit tests for {@link org.tensorflow.Tensor}. */
public class TensorTest {
  private static final double EPSILON = 1e-7;
  private static final float EPSILON_F = 1e-7f;

  @Test
  public void createWithRawData() {
    double[] doubles = {1d, 2d, 3d, 4d};
    Shape doubles_shape = Shape.of(4);
    boolean[] bools = {true, false, true, false};
    byte[] bools_ = {1, 0, 1, 0};
    Shape bools_shape = Shape.of(4);
    String strings = "test";
    Shape strings_shape = Shape.scalar();
    byte[] strings_; // raw TF_STRING
    try (Tensor<TString> t = TString.tensorOf(NdArrays.scalarOfObject(strings))) {
      strings_ = new byte[(int)t.numBytes()];
      t.rawData().read(strings_);
    }

    // validate creating a tensor using a raw data byte buffers
    {
      try (Tensor<TBool> t = Tensor.of(TBool.DTYPE, bools_shape, DataBuffers.of(bools_))) {
        boolean[] actual = new boolean[bools_.length];
        t.data().read(DataBuffers.of(actual));
        assertArrayEquals(bools, actual);
      }

      // note: the buffer is expected to contain raw TF_STRING (as per C API)
      try (Tensor<TString> t = Tensor.of(TString.DTYPE, strings_shape, DataBuffers.of(strings_))) {
        assertEquals(strings, t.data().getObject());
      }
    }

    // validate creating a tensor using a direct byte buffer (in host order)
    {
      DoubleBuffer buf = ByteBuffer.allocateDirect(8 * doubles.length).order(ByteOrder.nativeOrder())
          .asDoubleBuffer().put(doubles);
      try (Tensor<TFloat64> t = TFloat64.tensorOf(doubles_shape, d -> d.write(DataBuffers.of(buf)))) {
        double[] actual = new double[doubles.length];
        t.data().read(DataBuffers.of(actual));
        assertArrayEquals(doubles, actual, EPSILON);
      }
    }

    // validate shape checking
    try (Tensor<TBool> t = Tensor.of(TBool.DTYPE, Shape.of(bools_.length * 2), DataBuffers.of(bools_))) {
      fail("should have failed on incompatible buffer");
    } catch (IllegalArgumentException e) {
      // expected
    }
  }

  @Test
  public void createFromBufferWithNativeByteOrder() {
    double[] doubles = {1d, 2d, 3d, 4d};
    DoubleBuffer buf =
        ByteBuffer.allocate(8 * doubles.length)
            .order(ByteOrder.nativeOrder())
            .asDoubleBuffer()
            .put(doubles);
    flipBuffer(buf);
    try (Tensor<TFloat64> t = TFloat64.tensorOf(Shape.of(4), DataBuffers.of(buf))) {
      double[] actual = new double[doubles.length];
      t.data().read(DataBuffers.of(actual));
      assertArrayEquals(doubles, actual, EPSILON);
    }
  }

  @Test
  public void createFromBufferWithNonNativeByteOrder() {
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
    try (Tensor<TFloat64> t = TFloat64.tensorOf(Shape.of(4), DataBuffers.of(buf))) {
      double[] actual = new double[doubles.length];
      t.data().read(DataBuffers.of(actual));
      assertArrayEquals(doubles, actual, EPSILON);
    }
  }

  @Test
  public void createWithTypedBuffer() {
    IntBuffer ints = IntBuffer.wrap(new int[]{1, 2, 3, 4});
    FloatBuffer floats = FloatBuffer.wrap(new float[]{1f, 2f, 3f, 4f});
    DoubleBuffer doubles = DoubleBuffer.wrap(new double[]{1d, 2d, 3d, 4d});
    LongBuffer longs = LongBuffer.wrap(new long[]{1L, 2L, 3L, 4L});

    // validate creating a tensor using a typed buffer
    {
      Shape shape = Shape.of(4);
      try (Tensor<TFloat64> t = TFloat64.tensorOf(shape, DataBuffers.of(doubles))) {
        DoubleBuffer actual = DoubleBuffer.allocate(doubles.capacity());
        t.data().read(DataBuffers.of(actual));
        assertEquals(doubles, actual);
      }
      try (Tensor<TFloat32> t = TFloat32.tensorOf(shape, DataBuffers.of(floats))) {
        FloatBuffer actual = FloatBuffer.allocate(floats.capacity());
        t.data().read(DataBuffers.of(actual));
        assertEquals(floats, actual);
      }
      try (Tensor<TInt32> t = TInt32.tensorOf(shape, DataBuffers.of(ints))) {
        IntBuffer actual = IntBuffer.allocate(ints.capacity());
        t.data().read(DataBuffers.of(actual));
        assertEquals(ints, actual);
      }
      try (Tensor<TInt64> t = TInt64.tensorOf(shape, DataBuffers.of(longs))) {
        LongBuffer actual = LongBuffer.allocate(longs.capacity());
        t.data().read(DataBuffers.of(actual));
        assertEquals(longs, actual);
      }
    }

    // validate shape-checking
    {
      Shape shape = Shape.of(5);
      try (Tensor<TFloat64> t = TFloat64.tensorOf(shape, DataBuffers.of(doubles))) {
        fail("should have failed on incompatible buffer");
      } catch (BufferUnderflowException e) {
        // expected
      }
      try (Tensor<TFloat32> t = TFloat32.tensorOf(shape, DataBuffers.of(floats))) {
        fail("should have failed on incompatible buffer");
      } catch (BufferUnderflowException e) {
        // expected
      }
      try (Tensor<TInt32> t = TInt32.tensorOf(shape, DataBuffers.of(ints))) {
        fail("should have failed on incompatible buffer");
      } catch (BufferUnderflowException e) {
        // expected
      }
      try (Tensor<TInt64> t = TInt64.tensorOf(shape, DataBuffers.of(longs))) {
        fail("should have failed on incompatible buffer");
      } catch (BufferUnderflowException e) {
        // expected
      }
    }
  }

  @Test
  public void readFromRawData() {
    int[] ints = {1, 2, 3};
    float[] floats = {1f, 2f, 3f};
    double[] doubles = {1d, 2d, 3d};
    long[] longs = {1L, 2L, 3L};
    boolean[] bools = {true, false, true};

    try (Tensor<TInt32> tints = TInt32.vectorOf(ints);
        Tensor<TFloat32> tfloats = TFloat32.vectorOf(floats);
        Tensor<TFloat64> tdoubles = TFloat64.vectorOf(doubles);
        Tensor<TInt64> tlongs = TInt64.vectorOf(longs);
        Tensor<TBool> tbools = TBool.vectorOf(bools)) {

      // validate that any datatype is readable with ByteBuffer (content, position)
      {
        ByteBuffer bbuf = ByteBuffer.allocate(1024).order(ByteOrder.nativeOrder());

        clearBuffer(bbuf); // FLOAT
        assertEquals(tfloats.numBytes(), tfloats.rawData().size());
        tfloats.rawData().copyTo(DataBuffers.of(bbuf), tfloats.numBytes());
        assertEquals(floats[0], bbuf.asFloatBuffer().get(0), EPSILON);

        clearBuffer(bbuf); // DOUBLE
        assertEquals(tdoubles.numBytes(), tdoubles.rawData().size());
        tdoubles.rawData().copyTo(DataBuffers.of(bbuf), tdoubles.numBytes());
        assertEquals(doubles[0], bbuf.asDoubleBuffer().get(0), EPSILON);

        clearBuffer(bbuf); // INT32
        assertEquals(tints.numBytes(), tints.rawData().size());
        tints.rawData().copyTo(DataBuffers.of(bbuf), tints.numBytes());
        assertEquals(ints[0], bbuf.asIntBuffer().get(0));

        clearBuffer(bbuf); // INT64
        assertEquals(tlongs.numBytes(), tlongs.rawData().size());
        tlongs.rawData().copyTo(DataBuffers.of(bbuf), tlongs.numBytes());
        assertEquals(longs[0], bbuf.asLongBuffer().get(0));

        clearBuffer(bbuf); // BOOL
        assertEquals(tbools.numBytes(), tbools.rawData().size());
        tbools.rawData().copyTo(DataBuffers.of(bbuf), tbools.numBytes());
        assertEquals(bools[0], bbuf.get(0) != 0);
      }

      // validate the use of direct buffers
      {
        ByteBuffer bbuf =
            ByteBuffer.allocateDirect((int)tdoubles.numBytes()).order(ByteOrder.nativeOrder());
        tdoubles.rawData().copyTo(DataBuffers.of(bbuf), tdoubles.numBytes());
        assertEquals(doubles[0], bbuf.asDoubleBuffer().get(0), EPSILON);
      }

      // validate byte order conversion
      {
        DoubleBuffer foreignBuf =
            ByteBuffer.allocate((int)tdoubles.numBytes())
                .order(
                    ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
                        ? ByteOrder.BIG_ENDIAN
                        : ByteOrder.LITTLE_ENDIAN)
                .asDoubleBuffer();
        tdoubles.rawData().asDoubles().copyTo(DataBuffers.of(foreignBuf), foreignBuf.capacity());
        double[] actual = new double[foreignBuf.remaining()];
        foreignBuf.get(actual);
        assertArrayEquals(doubles, actual, EPSILON);
      }
    }
  }

  @Test
  public void scalars() {
    try (Tensor<TFloat32> t = TFloat32.scalarOf(2.718f)) {
      assertEquals(TFloat32.DTYPE, t.dataType());
      assertEquals(0, t.shape().numDimensions());
      assertEquals(2.718f, t.data().getFloat(), EPSILON_F);
    }

    try (Tensor<TFloat64> t = TFloat64.scalarOf(3.1415)) {
      assertEquals(TFloat64.DTYPE, t.dataType());
      assertEquals(0, t.shape().numDimensions());
      assertEquals(3.1415, t.data().getDouble(), EPSILON);
    }

    try (Tensor<TInt32> t = TInt32.scalarOf(-33)) {
      assertEquals(TInt32.DTYPE, t.dataType());
      assertEquals(0, t.shape().numDimensions());
      assertEquals(-33, t.data().getInt());
    }

    try (Tensor<TInt64> t = TInt64.scalarOf(8589934592L)) {
      assertEquals(TInt64.DTYPE, t.dataType());
      assertEquals(0, t.shape().numDimensions());
      assertEquals(8589934592L, t.data().getLong());
    }

    try (Tensor<TBool> t = TBool.scalarOf(true)) {
      assertEquals(TBool.DTYPE, t.dataType());
      assertEquals(0, t.shape().numDimensions());
      assertTrue(t.data().getBoolean());
    }

    try (Tensor<TString> t = TString.scalarOf("sombrero")) {
      assertEquals(TString.DTYPE, t.dataType());
      assertEquals(0, t.shape().numDimensions());
      assertEquals("sombrero", t.data().getObject());
    }

    final byte[] bytes = {1, 2, 3, 4};
    try (Tensor<TString> t = TString.tensorOfBytes(NdArrays.scalarOfObject(bytes))) {
      assertEquals(TString.DTYPE, t.dataType());
      assertEquals(0, t.shape().numDimensions());
      assertArrayEquals(bytes, t.data().asBytes().getObject());
    }
  }

  @Test
  public void nDimensional() {
    DoubleNdArray vector = StdArrays.ndCopyOf(new double[]{1.414, 2.718, 3.1415});
    try (Tensor<TFloat64> t = TFloat64.tensorOf(vector)) {
      assertEquals(TFloat64.DTYPE, t.dataType());
      assertEquals(1, t.shape().numDimensions());
      assertEquals(3, t.shape().size(0));
      assertEquals(vector, t.data());
    }

    IntNdArray matrix = StdArrays.ndCopyOf(new int[][]{{1, 2, 3}, {4, 5, 6}});
    try (Tensor<TInt32> t = TInt32.tensorOf(matrix)) {
      assertEquals(TInt32.DTYPE, t.dataType());
      assertEquals(2, t.shape().numDimensions());
      assertEquals(2, t.shape().size(0));
      assertEquals(3, t.shape().size(1));
      assertEquals(matrix, t.data());
    }

    LongNdArray threeD = StdArrays.ndCopyOf(new long[][][]{
      {{1}, {3}, {5}, {7}, {9}}, {{2}, {4}, {6}, {8}, {0}},
    });
    try (Tensor<TInt64> t = TInt64.tensorOf(threeD)) {
      assertEquals(TInt64.DTYPE, t.dataType());
      assertEquals(3, t.shape().numDimensions());
      assertEquals(2, t.shape().size(0));
      assertEquals(5, t.shape().size(1));
      assertEquals(1, t.shape().size(2));
      assertEquals(threeD, t.data());
    }

    BooleanNdArray fourD = StdArrays.ndCopyOf(new boolean[][][][]{
      {{{false, false, false, true}, {false, false, true, false}}},
      {{{false, false, true, true}, {false, true, false, false}}},
      {{{false, true, false, true}, {false, true, true, false}}},
    });
    try (Tensor<TBool> t = TBool.tensorOf(fourD)) {
      assertEquals(TBool.DTYPE, t.dataType());
      assertEquals(4, t.shape().numDimensions());
      assertEquals(3, t.shape().size(0));
      assertEquals(1, t.shape().size(1));
      assertEquals(2, t.shape().size(2));
      assertEquals(4, t.shape().size(3));
      assertEquals(fourD, t.data());
    }
  }

  @Test
  public void testNDimensionalStringTensor() {
    NdArray<String> matrix = NdArrays.ofObjects(String.class, Shape.of(4, 3));
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 3; ++j) {
        matrix.setObject(String.format("(%d, %d) = %d", i, j, i << j), i, j);
      }
    }
    try (Tensor<TString> t = TString.tensorOf(matrix)) {
      assertEquals(TString.DTYPE, t.dataType());
      assertEquals(2, t.shape().numDimensions());
      assertEquals(4, t.shape().size(0));
      assertEquals(3, t.shape().size(1));
      assertEquals(matrix, t.data());
    }

    NdArray<byte[]> byteMatrix = NdArrays.ofObjects(byte[].class, matrix.shape());
    matrix.scalars().forEachIndexed((i, s) -> byteMatrix.setObject(s.getObject().getBytes(UTF_8), i));
    try (Tensor<TString> t = TString.tensorOfBytes(byteMatrix)) {
      assertEquals(TString.DTYPE, t.dataType());
      assertEquals(2, t.shape().numDimensions());
      assertEquals(4, t.shape().size(0));
      assertEquals(3, t.shape().size(1));
      assertEquals(byteMatrix, t.data().asBytes());
      assertEquals(matrix, t.data());
    }
  }

  @Test
  public void testUint8TensorFromArray() {
    byte[] vector = new byte[] {1, 2, 3, 4};
    try (Tensor<TUint8> t = TUint8.vectorOf(vector)) {
      assertEquals(TUint8.DTYPE, t.dataType());
      assertEquals(1, t.shape().numDimensions());
      assertEquals(4, t.shape().size(0));

      byte[] got = new byte[4];
      t.data().read(DataBuffers.of(got));
      assertArrayEquals(vector, got);
    }
  }

  @Test
  public void testCreateFromArrayOfBoxed() {
    Integer[] vector = new Integer[] {1, 2, 3, 4};
    try (Tensor<TInt32> t = TInt32.tensorOf(Shape.of(4), d -> d.write(DataBuffers.ofObjects(vector)))) {
      assertEquals(TInt32.DTYPE, t.dataType());
      assertEquals(1, t.shape().numDimensions());
      assertEquals(4, t.shape().size(0));

      Integer[] got = new Integer[4];
      t.data().read(DataBuffers.ofObjects(got));
      assertArrayEquals(vector, got);
    }
  }

  @Test
  public void failCreateOnMismatchedDimensions() {
    int[][][] invalid = new int[3][1][];
    for (int x = 0; x < invalid.length; ++x) {
      for (int y = 0; y < invalid[x].length; ++y) {
        invalid[x][y] = new int[x + y + 1];
      }
    }
    try (Tensor<TInt32> t = TInt32.tensorOf(StdArrays.ndCopyOf(invalid))) {
      fail("Tensor.create() should fail because of differing sizes in the 3rd dimension");
    } catch (IllegalArgumentException e) {
      // The expected exception.
    }
  }

  @Test
  public void tensorWithZeroDimension() {
    // Note: Historically, TF Java failed on purpose when trying to allocate a tensor with a shape
    // that has one or more dimensions set to 0 elements. But Python API allows it, so we should do
    // the same.
    try (Tensor<TInt32> t = TInt32.tensorOf(Shape.of(3, 0, 1))) {
      assertEquals(0, t.numBytes());
      assertEquals(0, t.shape().size());
    }
    try (Tensor<TInt32> t = TInt32.tensorOf(StdArrays.ndCopyOf(new int[3][0][1]))) {
      assertEquals(0, t.numBytes());
      assertEquals(0, t.shape().size());
    }
  }

  @Test
  public void allocateTensorWithSize() {
    try (Tensor<TInt32> t = Tensor.of(TInt32.DTYPE, Shape.of(2, 2, 2), 8 * TInt32.DTYPE.byteSize())) {
      // ok
    }
    try (Tensor<TInt32> t = Tensor.of(TInt32.DTYPE, Shape.of(2, 2, 2), 9 * TInt32.DTYPE.byteSize())) {
      // ok (size requested is larger that minimum space required)
    }
    try {
      Tensor.of(TInt32.DTYPE, Shape.of(2, 2, 2), 8 * TInt32.DTYPE.byteSize() - 1);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void useAfterClose() {
    int n = 4;
    Tensor<?> t = TInt32.scalarOf(n);
    t.close();
    try {
      t.data();
    } catch (IllegalStateException e) {
      // The expected exception.
    }
  }

  @Test
  public void eagerTensorIsReleasedAfterSessionIsClosed() {
    Tensor<TInt32> sum;
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      sum = tf.math.add(tf.constant(10), tf.constant(20)).asTensor();
      sum.nativeHandle(); // does not throw
      assertEquals(30, sum.data().getInt());
    }
    try {
      sum.nativeHandle();
      fail("Tensor native handle should have been closed by ending eager session");
    } catch (IllegalStateException e) {
      // as expected
    }
    try {
      sum.data().getInt();
      fail("Tensor data should not be accessible after tensor is closed");
    } catch (IllegalStateException e) {
      // as expected
    }
  }

  @Test
  public void fromHandle() {
    // fromHandle is a package-visible method intended for use when the C TF_Tensor object has been
    // created independently of the Java code. In practice, two Tensor instances MUST NOT have the
    // same native handle.
    //
    // An exception is made for this test, where the pitfalls of this is avoided by not calling
    // close() on both Tensors.
    final FloatNdArray matrix = StdArrays.ndCopyOf(new float[][]{{1, 2, 3}, {4, 5, 6}});
    try (Tensor<TFloat32> src = TFloat32.tensorOf(matrix)) {
      Tensor<TFloat32> cpy = Tensor.fromHandle(src.nativeHandle()).expect(TFloat32.DTYPE);
      assertEquals(src.dataType(), cpy.dataType());
      assertEquals(src.shape().numDimensions(), cpy.shape().numDimensions());
      assertEquals(src.shape(), cpy.shape());
      assertEquals(matrix, cpy.data());
    }
  }

  @Test
  public void gracefullyFailCreationFromNullArrayForStringTensor() {
    // Motivated by: https://github.com/tensorflow/tensorflow/issues/17130
    byte[][] array = new byte[1][];
    try {
      TUint8.tensorOf(StdArrays.ndCopyOf(array));
    } catch (IllegalStateException e) {
      // expected.
    }
    byte[][][] array2 = new byte[2][2][2];
    array2[1] = null;
    try {
      TUint8.tensorOf(StdArrays.ndCopyOf(array));
    } catch (IllegalStateException e) {
      // expected.
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
