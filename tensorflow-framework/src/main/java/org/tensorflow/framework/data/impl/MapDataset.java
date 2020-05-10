package org.tensorflow.framework.data.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.data.Dataset;
import org.tensorflow.framework.data.DatasetIterator;
import org.tensorflow.op.Ops;

import java.util.List;
import java.util.function.BiFunction;

public class MapDataset extends Dataset {
  private final Dataset source;
  private final BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper;

  public MapDataset(Dataset source, BiFunction<Ops, List<Operand<?>>, List<Operand<?>>> mapper) {
    super(source.getOpsInstance(), source.getOutputTypes(), source.getOutputShapes());
    this.mapper = mapper;
    this.source = source;
  }

  @Override
  public DatasetIterator makeOneShotIterator() {
    return super.makeOneShotIterator().map(mapper);
  }

  @Override
  public Operand<?> getVariant() {
    return source.getVariant();
  }
}
