package org.tensorflow.tensor;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;

public interface BooleanTensor extends BooleanNdArray, Tensor<Boolean> {

  @Override
  BooleanTensor setBoolean(boolean value, long... coordinates);

  @Override
  BooleanTensor set(NdArray<Boolean> src, long... coordinates);

  @Override
  BooleanTensor setObject(Boolean value, long... coordinates);

  @Override
  BooleanTensor copyTo(NdArray<Boolean> dst);

  @Override
  BooleanTensor read(DataBuffer<Boolean> dst);

  @Override
  BooleanTensor read(BooleanDataBuffer dst);

  @Override
  BooleanTensor write(DataBuffer<Boolean> src);

  @Override
  BooleanTensor write(BooleanDataBuffer src);
}

