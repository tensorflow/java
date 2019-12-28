package org.tensorflow.data.impl;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.data.Dataset;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;

import java.util.List;

public class BatchDataset extends Dataset {
  private org.tensorflow.op.data.BatchDataset batchDataset;


  public BatchDataset(Ops tf, Operand<?> variant,
                      Constant<TInt64> batchSize, Constant<TBool> dropRemainder,
                      List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    super(tf, outputTypes, outputShapes);
    this.batchDataset = tf.data.batchDataset(variant, batchSize, dropRemainder, outputTypes, outputShapes);
  }

  @Override
  public Operand<?> getVariant() {
    return this.batchDataset;
  }
}
