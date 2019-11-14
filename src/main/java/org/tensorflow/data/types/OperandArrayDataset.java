//package org.tensorflow.data.types;
//
//import org.tensorflow.EagerSession;
//import org.tensorflow.Operand;
//import org.tensorflow.data.types.BatchedArrayDataset;
//import org.tensorflow.data.types.FilterArrayDataset;
//import org.tensorflow.data.MapArrayDataset;
//import org.tensorflow.op.Ops;
//
//import java.util.function.BiFunction;
//
//public abstract class OperandArrayDataset<T> extends Dataset<Operand<T>[]> {
//    // Empty Class to to make Array type explicit.
//    @Override
//    public OperandArrayDataset<T> batch(long batchSize, boolean dropRemainder) {
//        if (environment() instanceof EagerSession) {
//            return new BatchedArrayDataset<>((EagerSession) environment(), this, batchSize);
//        }
//
//        throw new IllegalArgumentException("Cannot batch a graph dataset.");
//    }
//
//    public <V> Dataset<V> map(BiFunction<Ops, Operand<T>[], V> transform) {
//        if (environment() instanceof EagerSession) {
//            return new MapArrayDataset<>((EagerSession) environment(), this, transform);
//        }
//
//        throw new IllegalArgumentException("Cannot map a graph dataset.");
//    }
//
//    public Dataset<Operand<T>[]> filter(BiFunction<Ops, Operand<T>[], Boolean> predicate) {
//        if (environment() instanceof EagerSession) {
//            return new FilterArrayDataset<>((EagerSession) environment(), this, predicate);
//        }
//
//        throw new IllegalArgumentException("Cannot filter a graph dataset.");
//    }
//}
