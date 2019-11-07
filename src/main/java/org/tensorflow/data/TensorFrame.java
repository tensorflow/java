package org.tensorflow.data;

public abstract class TensorFrame<T> implements Dataset<T> {
  private long batchSize = 1;
  private boolean dropRemainder = false;

  public TensorFrame<T> batch(long batchSize, boolean dropRemainder) {
    this.batchSize = batchSize;
    this.dropRemainder = dropRemainder;
    return this;
  }

  public long getBatchSize() {
    return batchSize;
  }

  public long numBatches() {
    return numElementsPerTensor() / batchSize;
  }
}
