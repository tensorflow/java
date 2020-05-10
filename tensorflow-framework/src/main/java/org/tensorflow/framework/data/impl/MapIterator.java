package org.tensorflow.framework.data.impl;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.framework.data.DatasetIterator;
import org.tensorflow.framework.data.DatasetOptional;
import org.tensorflow.op.Ops;

import java.util.List;
import java.util.function.BiFunction;

public class MapIterator extends DatasetIterator {
  private final BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper;

  public MapIterator(
      DatasetIterator source,
      BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper) {
    super(source);
    this.mapper = mapper;
  }

  @Override
  public List<Operand<?>> getNext() {
    return mapper.apply(tf, super.getNext());
  }

  @Override
  public DatasetOptional getNextAsOptional() {
    DatasetOptional optional = super.getNextAsOptional();
    return new MapOptional(optional, mapper, outputTypes, outputShapes);
  }
}
