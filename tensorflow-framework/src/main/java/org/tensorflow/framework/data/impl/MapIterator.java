package org.tensorflow.framework.data.impl;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.framework.data.Dataset;
import org.tensorflow.framework.data.DatasetIterator;
import org.tensorflow.framework.data.DatasetOptional;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapIterator extends DatasetIterator {
  private final BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper;

  public MapIterator(
      DatasetIterator source,
      BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper,
      List<DataType<?>> outputTypes,
      List<Shape> outputShapes) {
    super(
        source.getOpsInstance(),
        source.getIteratorResource(),
        source.getInitializer(),
        outputTypes,
        outputShapes);

    this.mapper = mapper;
  }

  @Override
  public List<Operand<?>> getNext() {
    return mapper.apply(tf, super.getNext());
  }

  @Override
  public DatasetOptional getNextAsOptional() {
    DatasetOptional optional = super.getNextAsOptional();
    return new MapOptional(optional, mapper, getOutputTypes(), getOutputShapes());
  }
}
