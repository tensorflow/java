package org.tensorflow.data;

import org.junit.Before;
import org.junit.Test;
import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

public class DatasetIteratorTest {
    int[][] testMatrix1;
    int[][] testMatrix2;

    @Before
    public void setUp() {
        testMatrix1 = new int[][]{
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8, 10},
                {3, 6, 8, 12, 15},
                {4, 8, 12, 16, 20}
        };

        testMatrix2 = new int[][]{
                {1}, {0}, {1}, {1}
        };
    }

    @Test
    public void getNext() {
    }

    @Test
    public void makeInitializer() {
    }

    @Test
    public void fromStructure() {
        try (Graph graph = new Graph()) {
            Ops tf = Ops.create(graph);

            List<Operand<?>> tensors = Arrays.asList(
                    tf.constant(testMatrix1),
                    tf.constant(testMatrix2)
            );

            List<DataType<?>> dataTypes = Arrays.asList(
                    TInt32.DTYPE, TInt32.DTYPE
            );

            Dataset dataset =
                    Dataset.fromTensorSlices(tf, tensors, dataTypes);

            DatasetIterator iterator = dataset.makeOneShotIterator();

            List<Output<?>> components = iterator.getNext();
            Operand<?> X = components.get(0);
            Operand<?> y = components.get(1);

            try (Session session = new Session(graph)) {
                session.run(tf.init());

                while (true) {
                    try {
                        List<Tensor<?>> outputs = session.runner()
                                .fetch(X)
                                .fetch(y)
                                .run();

                        try (Tensor<TInt32> XBatch =
                                     outputs.get(0).expect(TInt32.DTYPE);
                             Tensor<TInt32> yBatch =
                                     outputs.get(1).expect(TInt32.DTYPE);) {

                            System.out.println("New Batch:");
                            System.out.println("features");
                            XBatch.data().scalars().forEach(System.out::println);
                            System.out.println("labels");
                            yBatch.data().scalars().forEach(System.out::println);

                            System.out.println();
                        }
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
            }
        }
    }

    @Test
    public void fromStructureEager() {

        Ops tf = Ops.create();

        List<Operand<?>> tensors = Arrays.asList(
                tf.constant(testMatrix1),
                tf.constant(testMatrix2)
        );

        List<DataType<?>> dataTypes = Arrays.asList(
                TInt32.DTYPE, TInt32.DTYPE
        );

        Dataset dataset = Dataset.fromTensorSlices(tf, tensors, dataTypes);

        for (List<Output<?>> outputs : dataset) {
            try (Tensor<TInt32> XBatch =
                         outputs.get(0).tensor().expect(TInt32.DTYPE);
                 Tensor<TInt32> yBatch =
                         outputs.get(1).tensor().expect(TInt32.DTYPE);) {

                System.out.println("New Batch:");
                System.out.println("features");
                XBatch.data().scalars().forEach(System.out::println);
                System.out.println("labels");
                yBatch.data().scalars().forEach(System.out::println);

                System.out.println();
            }
        }
    }
}