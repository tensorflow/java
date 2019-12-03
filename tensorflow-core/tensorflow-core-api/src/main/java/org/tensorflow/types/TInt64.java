package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.util.buffer.LongDataBuffer;
import org.tensorflow.util.ndarray.LongNdArray;
import org.tensorflow.util.ndarray.NdArray;
import org.tensorflow.util.ndarray.Shape;
import org.tensorflow.util.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.types.family.TNumber;

public interface TInt64 extends LongNdArray, TNumber {

  DataType<TInt64> DTYPE = DataType.create("INT64", 9, 8, TInt64Impl::mapTensor);

  static Tensor<TInt64> scalarOf(long value) {
    Tensor<TInt64> t = ofShape();
    t.data().setLong(value);
    return t;
  }

  static Tensor<TInt64> vectorOf(long... values) {
    Tensor<TInt64> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TInt64> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TInt64> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TInt64> copyOf(NdArray<Long> src) {
    Tensor<TInt64> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TInt64Impl extends LongDenseNdArray implements TInt64 {

  static TInt64 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TInt64Impl(TensorBuffers.toLongs(nativeTensor), shape);
  }

  private TInt64Impl(LongDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
