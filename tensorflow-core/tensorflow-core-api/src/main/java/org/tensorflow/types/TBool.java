package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.util.buffer.BooleanDataBuffer;
import org.tensorflow.util.buffer.ByteDataBuffer;
import org.tensorflow.util.buffer.DataBuffers;
import org.tensorflow.util.buffer.adapter.BooleanDataAdapter;
import org.tensorflow.util.ndarray.BooleanNdArray;
import org.tensorflow.util.ndarray.NdArray;
import org.tensorflow.util.ndarray.Shape;
import org.tensorflow.util.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.types.family.TType;

public interface TBool extends BooleanNdArray, TType {

  DataType<TBool> DTYPE = DataType.create("BOOL", 10, 1, TBoolImpl::mapTensor);

  static Tensor<TBool> scalarOf(boolean value) {
    Tensor<TBool> t = ofShape();
    t.data().setBoolean(value);
    return t;
  }

  static Tensor<TBool> vectorOf(boolean... values) {
    Tensor<TBool> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TBool> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TBool> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TBool> copyOf(NdArray<Boolean> src) {
    Tensor<TBool> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TBoolImpl extends BooleanDenseNdArray implements TBool {

  static TBool mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TBoolImpl(DataBuffers.toBooleans(TensorBuffers.toBytes(nativeTensor), ADAPTER), shape);
  }

  private TBoolImpl(BooleanDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }

  private static BooleanDataAdapter ADAPTER = new BooleanDataAdapter() {

    @Override
    public void writeBoolean(ByteDataBuffer buffer, boolean value, long index) {
      buffer.setByte((byte)(value ? 1 : 0), index);
    }

    @Override
    public boolean readBoolean(ByteDataBuffer buffer, long index) {
      return buffer.getByte(index) > 0;
    }

    @Override
    public int sizeInBytes() {
      return TBool.DTYPE.byteSize();
    }
  };
}
