package org.tensorflow.tensor;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.impl.dense.IntDenseNdArray;

public interface IntTensor extends IntNdArray, Tensor<Integer> {

  @Override
  IntTensor setInt(int value, long... coordinates);

  @Override
  IntTensor set(NdArray<Integer> src, long... coordinates);

  @Override
  IntTensor setObject(Integer value, long... coordinates);

  @Override
  IntTensor copyTo(NdArray<Integer> dst);

  @Override
  IntTensor read(DataBuffer<Integer> dst);

  @Override
  IntTensor read(IntDataBuffer dst);

  @Override
  IntTensor write(DataBuffer<Integer> src);

  @Override
  IntTensor write(IntDataBuffer src);
}

