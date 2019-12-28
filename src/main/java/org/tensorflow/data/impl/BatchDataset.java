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
  org.tensorflow.op.data.BatchDataset batchDataset;
  private List<DataType<?>> outputTypes;
  private List<Shape> outputShapes;

  public BatchDataset(Ops tf, Operand<?> variant,
                      Constant<TInt64> batchSize, Constant<TBool> dropRemainder,
                      List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    super(tf);
    this.batchDataset = tf.data.batchDataset(variant, batchSize, dropRemainder, outputTypes, outputShapes);
    this.outputTypes = outputTypes;
    this.outputShapes = outputShapes;
  }

  @Override
  public Operand<?> getVariant() {
    return batchDataset;
  }

  @Override
  public List<DataType<?>> getOutputTypes() {
    return outputTypes;
  }

  @Override
  public List<Shape> getOutputShapes() {
    return outputShapes;
  }
}
