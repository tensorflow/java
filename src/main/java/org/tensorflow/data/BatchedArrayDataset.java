package org.tensorflow.data;

import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.data.types.OperandArrayDataset;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.Constant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

public class BatchedArrayDataset<T> extends OperandArrayDataset<T> {
    EagerSession eagerSessionRef;
    OperandArrayDataset<T> arrayDataset;
    long batchSize;

    public BatchedArrayDataset(EagerSession session, OperandArrayDataset<T> dataset, long batchSize) {
        this.eagerSessionRef = session;
        this.arrayDataset = dataset;
        this.batchSize = batchSize;
    }

    @Override
    public Iterator<Operand<T>[]> iterator() {

        return new Iterator<>() {
            Ops tf = Ops.create(eagerSessionRef);
            Spliterator<Operand<T>[]> spliterator = arrayDataset.spliterator();
            long size = spliterator.getExactSizeIfKnown();
            long current = 0;

            @Override
            public boolean hasNext() {
                return size > 0 && size - current >= batchSize;
            }

            @Override
            public Operand<T>[] next() {
                List<List<Operand<T>>> batchTensors = new ArrayList<>();
                for (int i = 0; i < numOperands(); i++) batchTensors.add(new ArrayList<>());

                for (int i = 0; i < batchSize; i++) {
                    spliterator.tryAdvance(tensors -> {
                        for (int j = 0; j < tensors.length; j++) {
                            batchTensors.get(j).add(tensors[j]);
                        }
                    });
                }

                Operand<T>[] batch = (Operand<T>[]) new Operand[numOperands()];
                for (int i = 0; i < batchTensors.size(); i++) {
                    Concat<T> concat = tf.concat(batchTensors.get(i), Constant.create(tf.scope(), 0));
                    batch[i] = concat;
                }

                return batch;
            }
        };
    }

    @Override
    public long size() {
        return this.arrayDataset.size() / batchSize;
    }

    @Override
    public int numOperands() {
        return this.arrayDataset.numOperands();
    }

    @Override
    public ExecutionEnvironment environment() {
        return eagerSessionRef;
    }
}
