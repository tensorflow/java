package org.tensorflow.framework.data.impl;

import org.tensorflow.framework.data.Dataset;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;

import java.util.List;
import java.util.stream.Collectors;

public class TensorSliceDataset extends Dataset {
  private org.tensorflow.op.data.TensorSliceDataset dataset;

  public TensorSliceDataset(Ops tf, List<Operand<?>> components, List<DataType<?>> outputTypes) {
    super(tf, outputTypes,
        components.stream()
            .map(c -> c.asOutput().shape().tail())
            .collect(Collectors.toList()));

    if (!(components.size() == outputTypes.size())) {
      throw new IllegalArgumentException("Lists `tensors` and `dtypes` must have the same number of elements.");
    }

    this.dataset = tf.data.tensorSliceDataset(components, this.getOutputShapes());
  }

  @Override
  public Operand<?> getVariant() {
    return dataset;
  }
}
