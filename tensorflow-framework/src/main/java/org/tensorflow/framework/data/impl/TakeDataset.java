package org.tensorflow.framework.data.impl;

import org.tensorflow.framework.data.Dataset;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TInt64;

import java.util.List;

public class TakeDataset extends Dataset {
  private org.tensorflow.op.data.TakeDataset takeDataset;

  public TakeDataset(Ops tf, Operand<?> variant, Constant<TInt64> count,
                     List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    super(tf, outputTypes, outputShapes);
    this.takeDataset = tf.data.takeDataset(variant, count, outputTypes, outputShapes);
  }

  @Override
  public Operand<?> getVariant() {
    return takeDataset;
  }
}
