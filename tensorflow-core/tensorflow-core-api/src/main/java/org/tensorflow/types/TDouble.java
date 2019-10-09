package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.nd.DoubleNdArray;
import org.tensorflow.nio.nd.NdArray;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.nio.nd.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.family.TDecimal;

public interface TDouble extends DoubleNdArray, TDecimal {

  DataType<TDouble> DTYPE = DataType.create("DOUBLE", 2, 8, TDoubleImpl::mapTensor);

  static Tensor<TDouble> scalar(double value) {
    Tensor<TDouble> t = tensorOfShape();
    t.data().setDouble(value);
    return t;
  }

  static Tensor<TDouble> vector(double... values) {
    Tensor<TDouble> t = tensorOfShape(values.length);
    t.data().write(values);
    return t;
  }

  static Tensor<TDouble> tensor(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  static Tensor<TDouble> tensorOfShape(long... dimensionSizes) {
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
