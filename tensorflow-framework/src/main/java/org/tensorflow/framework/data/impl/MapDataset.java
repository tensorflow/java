package org.tensorflow.framework.data.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.data.Dataset;
import org.tensorflow.framework.data.DatasetIterator;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class MapDataset extends Dataset {
  private final Function<List<Operand<?>>, List<Operand<?>>> mapper;

  public MapDataset(Dataset other, Function<List<Operand<?>>, List<Operand<?>>> mapper) {
    super(other);
    this.mapper = mapper;
  }

  @Override
  public DatasetIterator makeOneShotIterator() {
    return new MapIterator(super.makeOneShotIterator(), mapper);
  }

  @Override
  public DatasetIterator makeInitializeableIterator() {
    return new MapIterator(super.makeInitializeableIterator(), mapper);
  }
}
