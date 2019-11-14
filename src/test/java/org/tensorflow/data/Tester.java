package org.tensorflow.data;

import org.tensorflow.EagerSession;
import org.tensorflow.Tensor;
import org.tensorflow.data.impl.EagerArrayDataset;
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

            EagerArrayDataset<Integer> dataset = new EagerArrayDataset<>(env, X, y).batch(6, false);
            dataset.forEach(batch -> {
                System.out.println("Batch");
                printIntTensor(batch[0].asOutput().tensor());
                printIntTensor(batch[1].asOutput().tensor());
            });
        }
    }

    static void printIntTensor(Tensor<?> tensor) {
        IntBuffer buffer = IntBuffer.allocate(tensor.numElements());
        tensor.writeTo(buffer);
        System.out.println("Batch: " + Arrays.toString(buffer.array()));
    }

    public static void main(String[] args) {
        testEagerFrame();
    }
}
