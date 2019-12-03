package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.util.buffer.DoubleDataBuffer;
import org.tensorflow.util.ndarray.DoubleNdArray;
import org.tensorflow.util.ndarray.NdArray;
import org.tensorflow.util.ndarray.Shape;
import org.tensorflow.util.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.family.TDecimal;

public interface TDouble extends DoubleNdArray, TDecimal {

  DataType<TDouble> DTYPE = DataType.create("DOUBLE", 2, 8, TDoubleImpl::mapTensor);

  static Tensor<TDouble> scalarOf(double value) {
    Tensor<TDouble> t = ofShape();
    t.data().setDouble(value);
    return t;
  }

  static Tensor<TDouble> vectorOf(double... values) {
    Tensor<TDouble> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TDouble> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TDouble> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  static Tensor<TDouble> copyOf(NdArray<Double> src) {
    Tensor<TDouble> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TDoubleImpl extends DoubleDenseNdArray implements TDouble {

  static TDouble mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TDoubleImpl(TensorBuffers.toDoubles(nativeTensor), shape);
  }

  private TDoubleImpl(DoubleDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}
