package org.tensorflow.data;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;

import java.util.Iterator;

public interface EagerLoader<T> extends Dataset<T> {
    Iterator<Operand<T>[]> batchIterator(Ops tf);
}
