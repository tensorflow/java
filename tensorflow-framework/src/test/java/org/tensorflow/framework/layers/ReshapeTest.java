package org.tensorflow.framework.layers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

import static org.junit.jupiter.api.Assertions.*;

class ReshapeTest {
    private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

    float[][][] inputArray = {
            {
                    {2.70857435f, 8.25453567f, 9.75479311f, 1.10273526f},
                    {8.69836437f, 2.27818352f, 8.60856328f, 1.43265882f},
                    {0.75845834f, 5.60463474f, 7.35998787f, 0.06365667f}
            },
            {
                    {4.87355239f, 9.90221978f, 5.39014402f, 2.05263398f},
                    {5.91652733f, 0.9186602f, 0.91375672f, 0.56053326f},
                    {2.08046551f, 8.53763374f, 6.40378721f, 5.83284758f}
            }
    };

    float[][][] inputArrayNN2 = {
            {
                    {2.70857435f, 8.25453567f}, {9.75479311f, 1.10273526f},
                    {8.69836437f, 2.27818352f}, {8.60856328f, 1.43265882f},
                    {0.75845834f, 5.60463474f}, {7.35998787f, 0.06365667f}
            },
            {
                    {4.87355239f, 9.90221978f}, {5.39014402f, 2.05263398f},
                    {5.91652733f, 0.9186602f}, {0.91375672f, 0.56053326f},
                    {2.08046551f, 8.53763374f}, {6.40378721f, 5.83284758f}
            }
    };

    @Test
    public void testCall43() {
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape targetShape = Shape.of(4,3);
            long batchSize = 2;
            Reshape<TFloat32> instance = new Reshape<>(tf, targetShape,
                    TFloat32.class, Layer.Options.create().inputShape(Shape.of(batchSize, 3, 4)) );

            Operand<TFloat64> result =
                    instance.call(
                            tf.dtypes.cast(tf.constant(inputArray), TFloat64.class), TFloat64.class);

            assertArrayEquals(targetShape.prepend(batchSize).asArray(), result.shape().asArray());


        }
    }

    @Test
    public void testCallUnknown_1() {
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape targetShape = Shape.of(Shape.UNKNOWN_SIZE,1);
            long batchSize = 2;
            Reshape<TFloat32> instance = new Reshape<>(tf, targetShape,
                    TFloat32.class, Layer.Options.create().inputShape(Shape.of(batchSize, 3, 4)) );

            Shape expectedShape = Shape.of(batchSize, 12, 1);

            Operand<TFloat64> result =
                    instance.call(
                            tf.dtypes.cast(tf.constant(inputArray), TFloat64.class), TFloat64.class);

            assertArrayEquals(expectedShape.asArray(), result.shape().asArray());


        }
    }

    @Test
    public void testCall1_Unknown_1() {
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape targetShape = Shape.of(1, Shape.UNKNOWN_SIZE);
            long batchSize = 2;
            Reshape<TFloat32> instance = new Reshape<>(tf, targetShape,
                    TFloat32.class, Layer.Options.create().inputShape(Shape.of(batchSize, 3, 4)) );

            Operand<TFloat64> result =
                    instance.call(
                            tf.dtypes.cast(tf.constant(inputArray), TFloat64.class), TFloat64.class);

            Shape expectedShape = Shape.of(batchSize, 1, 12);
            assertArrayEquals(expectedShape.asArray(), result.shape().asArray());


        }
    }

    @Test
    public void testCallUnknownUnknown2() {
        try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();
            Shape targetShape = Shape.of(Shape.UNKNOWN_SIZE,1);
            long batchSize = 2;
            Reshape<TFloat32> instance = new Reshape<>(tf, targetShape,
                    TFloat32.class, Layer.Options.create().inputShape(Shape.of(Shape.UNKNOWN_SIZE, Shape.UNKNOWN_SIZE, 2)) );

            Operand<TFloat64> result =
                    instance.call(
                            tf.dtypes.cast(tf.constant(inputArrayNN2), TFloat64.class), TFloat64.class);

            Shape expectedShape = Shape.of(batchSize, 12, 1);
            assertArrayEquals(expectedShape.asArray(), result.shape().asArray());


        }
    }
}