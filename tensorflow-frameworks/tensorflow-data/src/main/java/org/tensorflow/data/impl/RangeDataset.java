package org.tensorflow.data.impl;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.data.Dataset;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TInt64;

import java.util.List;

public class RangeDataset extends Dataset {
  private org.tensorflow.op.data.RangeDataset rangeDataset;


  public RangeDataset(Ops tf, Operand<TInt64> start, Operand<TInt64> stop, Operand<TInt64> step,
                      List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    super(tf, outputTypes, outputShapes);
    this.rangeDataset = tf.data.rangeDataset(start, stop, step, outputTypes, outputShapes);
  }

  @Override
  public Operand<?> getVariant() {
    return this.rangeDataset;
  }
}
