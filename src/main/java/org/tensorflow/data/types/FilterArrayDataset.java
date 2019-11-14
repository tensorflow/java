//package org.tensorflow.data.types;
//
//import org.tensorflow.EagerSession;
//import org.tensorflow.ExecutionEnvironment;
//import org.tensorflow.Operand;
//import org.tensorflow.data.types.OperandArrayDataset;
//import org.tensorflow.op.Ops;
//
//import java.util.Iterator;
//import java.util.function.BiFunction;
//import java.util.stream.StreamSupport;
//
//public class FilterArrayDataset<T> extends OperandArrayDataset<T> {
//    EagerSession env;
//    OperandArrayDataset<T> dataset;
//    BiFunction<Ops, Operand<T>[], Boolean> predicate;
//
//    public FilterArrayDataset(EagerSession env, OperandArrayDataset<T> dataset, BiFunction<Ops, Operand<T>[], Boolean> predicate) {
//        this.env = env;
//        this.dataset = dataset;
//        this.predicate = predicate;
//    }
//
//    @Override
//    public Iterator<Operand<T>[]> iterator() {
//        Ops tf = Ops.create(environment());
//        return StreamSupport
//                .stream(dataset.spliterator(), false)
//                .filter(x -> predicate.apply(tf, x))
//                .iterator();
//    }
//
//    @Override
//    public ExecutionEnvironment environment() {
//        return env;
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
//}
