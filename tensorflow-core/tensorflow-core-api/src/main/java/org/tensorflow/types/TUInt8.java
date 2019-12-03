package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.util.buffer.ByteDataBuffer;
import org.tensorflow.util.ndarray.ByteNdArray;
import org.tensorflow.util.ndarray.NdArray;
import org.tensorflow.util.ndarray.Shape;
import org.tensorflow.util.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.types.family.TNumber;

public interface TUInt8 extends ByteNdArray, TNumber {

  DataType<TUInt8> DTYPE = DataType.create("UINT8", 4, 1, TUInt8Impl::mapTensor);

  static Tensor<TUInt8> scalarOf(byte value) {
    Tensor<TUInt8> t = ofShape();
    t.data().setByte(value);
    return t;
  }

  static Tensor<TUInt8> vectorOf(byte... values) {
    Tensor<TUInt8> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TUInt8> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TUInt8> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TUInt8> copyOf(NdArray<Byte> src) {
    Tensor<TUInt8> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TUInt8Impl extends ByteDenseNdArray implements TUInt8 {

  static TUInt8 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TUInt8Impl(TensorBuffers.toBytes(nativeTensor), shape);
  }

  private TUInt8Impl(ByteDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
