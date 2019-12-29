package org.tensorflow.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tensorflow.Tensor;
import org.tensorflow.tools.Shape;

public class TFloat16Test extends NumericTypesTestBase<TFloat16, Float> {

  @Override
  Tensor<TFloat16> allocateTensor(Shape shape) {
    return TFloat16.ofShape(shape);
  }

  @Override
  Float valueOf(Integer value) {
    return value.floatValue();
  }

  @Test
  public void testFloat32to16() {

    // Zero and subnormals
    assertEquals((short)0x0000, TFloat16Impl.float32to16(0.0f));
    assertEquals((short)0x8000, TFloat16Impl.float32to16(-0.0f));
    assertEquals((short)0x0001, TFloat16Impl.float32to16(6e-8f));
    assertEquals((short)0x8200, TFloat16Impl.float32to16(-3.052e-5f));
    assertEquals((short)0x0000, TFloat16Impl.float32to16(6e-9f));

    // Infinite and NaN
    assertEquals((short)0x7C00, TFloat16Impl.float32to16(Float.POSITIVE_INFINITY));
    assertEquals((short)0xFC00, TFloat16Impl.float32to16(Float.NEGATIVE_INFINITY));
    assertEquals((short)0x7C00, TFloat16Impl.float32to16(65536.0f));
    assertEquals((short)0x7C00, TFloat16Impl.float32to16(165536.0f));
    assertEquals((short)0xFC00, TFloat16Impl.float32to16(-65536.0f));
    assertEquals((short)0x7E00, TFloat16Impl.float32to16(Float.NaN));
    assertEquals((short)0x7E00, TFloat16Impl.float32to16(Float.intBitsToFloat(0xFFFFFFFF)));

    // Normalized
    assertEquals((short)0x3C00, TFloat16Impl.float32to16(1.0f));
    assertEquals((short)0xBC00, TFloat16Impl.float32to16(-1.0f));
    assertEquals((short)0x5640, TFloat16Impl.float32to16(100.0f));
    assertEquals((short)0xD650, TFloat16Impl.float32to16(-101.0f));
    assertEquals((short)0x3C7E, TFloat16Impl.float32to16(1.123f));

    // Rounding up
    assertEquals((short)0x3C7E, TFloat16Impl.float32to16(1.1235f));   // 1.123
    assertEquals((short)0x3C7F, TFloat16Impl.float32to16(1.1236f));   // 1.124
    assertEquals((short)0x4000, TFloat16Impl.float32to16(2.0009f));   // 2.0
    assertEquals((short)0x4001, TFloat16Impl.float32to16(2.001f));    // 2.002
    assertEquals((short)0x5C00, TFloat16Impl.float32to16(256.125f));  // 256.0
    assertEquals((short)0x5C01, TFloat16Impl.float32to16(256.126f));  // 256.3
    assertEquals((short)0x5C01, TFloat16Impl.float32to16(256.30f));   // 256.3
    assertEquals((short)0x5C01, TFloat16Impl.float32to16(256.374f));  // 256.3
    assertEquals((short)0x5C02, TFloat16Impl.float32to16(256.375f));  // 256.5
    assertEquals((short)0x5C02, TFloat16Impl.float32to16(256.51f));   // 256.5
  }

  @Test
  public void testFloat16to32() {

    // Zero and subnormals
    assertEquals(0.0f, TFloat16Impl.float16to32((short)0x0000), 0);
    assertEquals(-0.0f, TFloat16Impl.float16to32((short)0x8000), 0);
    assertEquals(6e-8f, TFloat16Impl.float16to32((short)0x0001), 1e-8f);
    assertEquals(-3.052e-5f, TFloat16Impl.float16to32((short)0x8200), 1e-8f);

    // Infinite and NaN
    assertEquals(Float.POSITIVE_INFINITY, TFloat16Impl.float16to32((short)0x7C00), 0);
    assertEquals(Float.NEGATIVE_INFINITY, TFloat16Impl.float16to32((short)0xFC00), 0);
    assertEquals(Float.NaN, TFloat16Impl.float16to32((short)0x7E00), 0);
    assertEquals(Float.intBitsToFloat(0xFFFFFFFF), TFloat16Impl.float16to32((short)0x7E00), 0);

    // Normalized
    assertEquals(1.0f, TFloat16Impl.float16to32((short)0x3C00), 1e-1f);
    assertEquals(-1.0f, TFloat16Impl.float16to32((short)0xBC00), 1e-1f);
    assertEquals(100.0f, TFloat16Impl.float16to32((short)0x5640), 1e-1f);
    assertEquals(-101.0f, TFloat16Impl.float16to32((short)0xD650), 1e-1f);
    assertEquals(1.123f, TFloat16Impl.float16to32((short)0x3C7E), 1e-3f);
    assertEquals(1.123f, TFloat16Impl.float16to32((short)0x3C7E), 1e-3f);
    assertEquals(-62.34f, TFloat16Impl.float16to32((short)0xD3CB), 1e-2f);
  }
}
