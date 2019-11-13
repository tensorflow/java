package org.tensorflow.data;

import org.tensorflow.EagerSession;
import org.tensorflow.Operand;
import org.tensorflow.Tensor;
import org.tensorflow.data.types.Dataset;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class Tester {

    public static void testEagerFrame() {
        try (EagerSession env = EagerSession.create()) {
            Ops tf = Ops.create(env);
            Constant<Integer> X = Constant.create(tf.scope(),
                    new int[][] {
                            {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9},
                            {10, 11, 12},
                            {13, 14, 15},
                            {16, 17, 18}
                    });
            Constant<Integer> y = Constant.create(tf.scope(),
                    new int[] {6, 5, 4, 3, 2, 1}
                    );

            Dataset<Operand<Integer>, Operand<Integer>[]> dataset =
                    new EagerTensorArrayDataset<>(env, X, y)
                        .filter((tfRef, tensors) -> {
                                    // Extract X / Y tensors
                                    Operand<Integer> X0 = tensors[0];
                                    Operand<Integer> y0 = tensors[1];
                                    Operand<Boolean> compare = tfRef.math.less(y0, Constant.create(tfRef.scope(), 5));
                                    ByteBuffer buff = ByteBuffer.allocate(compare.asOutput().tensor().numElements());
                                    compare.asOutput().tensor().writeTo(buff);
                                    return buff.get(0) > 0;
                                });
//                        .map((tfRef, tensors) -> {
//                            // Extract X / Y tensors
//                            Operand<Integer> X0 = tensors[0];
//                            Operand<Integer> y0 = tensors[1];
//
//                            // Transform X values
//                            Operand<Integer> X1 = tfRef.math.mul(X0, Constant.create(tfRef.scope(), 2));
//
//                            return (Operand<Integer>[]) new Operand[] { X1, y0 };})
//                        .batch(2);
            dataset.forEach(batch -> {
                System.out.println("Batch");
                printIntTensor(batch[0].asOutput().tensor());
                printIntTensor(batch[1].asOutput().tensor());
            });
        }
    }

    static void printIntTensor(Tensor<?> tensor) {
        ByteBuffer buffer = ByteBuffer.allocate(tensor.numElements());
        tensor.writeTo(buffer);
        System.out.println("Batch: " + Arrays.toString(buffer.array()));
    }

    public static void main(String[] args) {
        testEagerFrame();
    }
}
