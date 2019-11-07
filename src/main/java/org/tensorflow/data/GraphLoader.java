package org.tensorflow.data;

import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.op.Ops;

public interface GraphLoader<T> extends Dataset<T>, AutoCloseable {
  void build(Ops tf);
  Operand<T>[] getBatchOperands();
  void feedBatchToSessionRunner(Ops tf, Session.Runner runner, long batchIndex, boolean fetchBatches);
}
