//package org.tensorflow.data.types;
//
//import org.tensorflow.Operand;
//import org.tensorflow.data.impl.Dataset;
//import org.tensorflow.op.Ops;
//
//import java.util.Iterator;
//import java.util.function.BiFunction;
//import java.util.stream.StreamSupport;
//
//public class MapArrayDataset<T, U> extends WrapDataset<Operand<T>[]> implements ArrayDataset<U> {
//
//    private final BiFunction<Ops, Operand<T>[], Operand<U>[]> transform;
//
//    public MapArrayDataset(Dataset<Operand<T>[]> dataset, BiFunction<Ops, Operand<T>[], Operand<U>[]> transform) {
//        super(dataset);
//        this.transform = transform;
//    }
//
//    @Override
//    public Iterator<Operand<U>[]> iterator() {
//        Ops tf = Ops.create(environment());
//        return StreamSupport
//                .stream(this.dataset.spliterator(), false)
//                .map(x -> transform.apply(tf, x))
//                .iterator();
//    }
//
//
//
//}
