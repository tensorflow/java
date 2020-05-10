package org.tensorflow.framework.data.impl;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.framework.data.DatasetIterator;
import org.tensorflow.framework.data.DatasetOptional;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;

import java.util.List;
import java.util.function.BiFunction;

public class MapOptional extends DatasetOptional {
  private final BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper;

  public MapOptional(
      DatasetOptional source,
      BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper,
      List<DataType<?>> outputTypes,
      List<Shape> outputShapes) {
    super(source.getOpsInstance(), source.getOptionalVariant(), outputTypes, outputShapes);
    this.mapper = mapper;
  }

  @Override
  public List<Operand<?>> getValue() {
    return mapper.apply(tf, super.getValue());
  }
}
