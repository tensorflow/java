//package org.tensorflow.data.container;
//
//import org.tensorflow.EagerSession;
//import org.tensorflow.ExecutionEnvironment;
//import org.tensorflow.Operand;
//import org.tensorflow.data.Utils;
//import org.tensorflow.data.container.Array;
//import org.tensorflow.data.container.Container;
//import org.tensorflow.data.impl.Dataset;
//import org.tensorflow.op.Ops;
//import org.tensorflow.op.core.Constant;
//
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.function.BiFunction;
//import java.util.stream.Collectors;
//import java.util.stream.LongStream;
//import java.util.stream.StreamSupport;
//
//public class EagerTensorArrayDataset<T> extends AbstractDataset<Operand<T>, Operand<T>[]> {
//    // Tensors contained in this frame
//    private Array<Operand<T>> data;
//
//    public EagerTensorArrayDataset(EagerSession session, Constant<T> firstTensor, Constant<T>... tensors) {
//        super(session);
//
//        // Verify all tensors share first dimension
//        long numElements = firstTensor.asOutput().shape().size(0);
//        for (Constant<T> tensor : tensors) {
//            if (numElements != tensor.asOutput().shape().size(0)) {
//                throw new IllegalArgumentException("All input tensors must share the same first dimension.");
//            }
//        }
//
//        data = new Array<>(firstTensor, tensors);
//    }
//
//    private Array<Operand<T>> elementAt(Ops tf, long index) {
//        return data.map(op -> Utils.sliceOperand(tf, op, index, 1));
//    }
//
//
//    @Override
//    public Dataset<Operand<T>[]> batch(long batchSize, boolean dropLastBatch) {
//        return null;
//    }
//
//    @Override
//    public <V> Dataset<V> map(BiFunction<Ops, Operand<T>[], V> transform) {
//        return null;
//    }
//
//    @Override
//    public Dataset<Operand<T>[]> filter(BiFunction<Ops, Operand<T>[], Boolean> predicate) {
//        return null;
//    }
//
//    @Override
//    public Iterator<Operand<T>[]> iterator() {
//        return null;
//    }
//}
//
//
