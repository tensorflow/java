//package org.tensorflow.data;
//
//import org.tensorflow.EagerSession;
//import org.tensorflow.Shape;
//import org.tensorflow.Tensor;
//import org.tensorflow.op.Ops;
//import org.tensorflow.op.core.Constant;
//import org.tensorflow.op.data.AnonymousIterator;
//import org.tensorflow.op.data.IteratorGetNext;
//import org.tensorflow.op.data.MakeIterator;
//
//import java.nio.IntBuffer;
//import java.util.Arrays;
//import java.util.List;
//
//public class Tester {
//
//    public static void testEagerFrame() {
//        try (EagerSession env = EagerSession.create()) {
//            Ops tf = Ops.create(env);
//
//            AnonymousIterator iter = tf.data.anonymousIterator(List.of(Integer.class), List.of(Shape.make(1, 3)));
//            Constant<Integer> X = Constant.create(tf.scope(),
//                    new int[][] {
//                            {1, 2, 3},
//                            {4, 5, 6},
//                            {7, 8, 9},
//                            {10, 11, 12},
//                            {13, 14, 15},
//                            {16, 17, 18}
//                    });
//
//
//            MakeIterator makeIterator = tf.data.makeIterator(X, iter);
//
//            IteratorGetNext next = tf.data.iteratorGetNext(iter, List.of(Integer.class), List.of(Shape.make(1, 3)));
//
//
//
//            Constant<Integer> y = Constant.create(tf.scope(),
//                    new int[] {6, 5, 4, 3, 2, 1}
//                    );
//
//            EagerArrayDataset<Integer> dataset = new EagerArrayDataset<>(env, X, y).batch(6, false);
//            dataset.forEach(batch -> {
//                System.out.println("Batch");
//                printIntTensor(batch[0].asOutput().tensor());
//                printIntTensor(batch[1].asOutput().tensor());
//            });
//        }
//    }
//
//    static void printIntTensor(Tensor<?> tensor) {
//        IntBuffer buffer = IntBuffer.allocate(tensor.numElements());
//        tensor.writeTo(buffer);
//        System.out.println("Batch: " + Arrays.toString(buffer.array()));
//    }
//
//
//    public static void main(String[] args) {
//        testEagerFrame();
//    }
//}
