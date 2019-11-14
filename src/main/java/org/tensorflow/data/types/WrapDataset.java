//package org.tensorflow.data.types;
//
//import org.tensorflow.ExecutionEnvironment;
//import org.tensorflow.data.impl.Dataset;
//import org.tensorflow.op.Ops;
//
//import java.util.Iterator;
//import java.util.function.BiFunction;
//
//public abstract class WrapDataset<U> implements Dataset<U> {
//    protected final Dataset<U> dataset;
//
//    public WrapDataset(Dataset<U> dataset) {
//        this.dataset = dataset;
//    }
//
//    @Override
//    public Dataset<U> batch(long batchSize, boolean dropLastBatch) {
//        return dataset.batch(batchSize, dropLastBatch);
//    }
//
//    @Override
//    public <V> Dataset<V> map(BiFunction<Ops, U, V> transform) {
//        return dataset.map(transform);
//    }
//
//    @Override
//    public Dataset<U> filter(BiFunction<Ops, U, Boolean> predicate) {
//        return dataset.filter(predicate);
//    }
//
//    @Override
//    public long size() {
//        return dataset.size();
//    }
//
//    @Override
//    public int numOperands() {
//        return dataset.numOperands();
//    }
//
//    @Override
//    public ExecutionEnvironment environment() {
//        return dataset.environment();
//    }
//
//    @Override
//    public abstract Iterator<U> iterator();
//}
