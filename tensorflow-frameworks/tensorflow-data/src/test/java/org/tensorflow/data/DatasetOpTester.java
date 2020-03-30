package org.tensorflow.data;

import org.bytedeco.javacpp.annotation.Const;
import org.junit.Test;
import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.data.*;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArrays;
import org.tensorflow.tools.ndarray.StdArrays;
import org.tensorflow.types.TInt32;
// import org.tensorflow.utils.Tuple2;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

public class DatasetOpTester {

  @Test
  public void testEagerBatching() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);

      NdArray<Integer> X = StdArrays.ndCopyOf(
              new int[][]{
                      {1, 2, 3},
                      {4, 5, 6},
                      {7, 8, 9},
                      {10, 11, 12}
              }
      );

      NdArray<Integer> y = StdArrays.ndCopyOf(
          new int[][]{
              {1},
              {4},
              {7},
              {10}
          }
      );


      List<Operand<?>> tensors = Arrays.asList(tf.val(X), tf.val(y));

      // // Try running TensorDataset
      List<DataType<?>> outputTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);
      List<Shape> outputShapes = Arrays.asList(
          Shape.of(3),
          Shape.of(1));

      TensorSliceDataset tensorDataset = TensorSliceDataset.create(tf.scope(), tensors, outputShapes);

      BatchDataset batchDataset = BatchDataset.create(
          tf.scope(),fee
          tensorDataset,
          tf.val(2L),
          tf.val(true),
          outputTypes,
          Arrays.asList(
              Shape.of(2, 3),
              Shape.of(2, 1))
      );


      AnonymousIterator anonymousIter = AnonymousIterator.create(tf.scope(), outputTypes, Arrays.asList(
          Shape.of(2, 3),
          Shape.of(2, 1)));

      MakeIterator makeIterator = tf.data.makeIterator(batchDataset, anonymousIter.handle());

      while (true) {
        try {
          IteratorGetNext getNext = tf.data.iteratorGetNext(anonymousIter.handle(), outputTypes, outputShapes);
          List<Output<?>> outputs = getNext.components();
          System.out.println("BATCH: ");
          printIntTensor(outputs.get(0).tensor());
          printIntTensor(outputs.get(1).tensor());
          System.out.println();
        } catch (IndexOutOfBoundsException e) {
          System.out.println("finished iterating.");
          break;
        }
      }
    }
  }

  @Test
  public void testCleanEagerBatching() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);

      Constant<TInt32> X = tf.val(
          new int[][]{
              {1, 2, 3},
              {4, 5, 6},
              {7, 8, 9},
              {10, 11, 12}
          }
      );

      Constant<TInt32> y = tf.val(
          new int[][]{
              {1},
              {4},
              {7},
              {10}
          }
      );


      // // Try running TensorDataset
      List<Operand<?>> tensors = Arrays.asList(X, y);
      List<DataType<?>> outputTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);

      Dataset dataset = Dataset
          .fromTensorSlices(tf, tensors, outputTypes)
          .batch(50)
          .take(3)
          .skip(1);
      for (List<Output<?>> output : dataset) {
        Tensor<?> XBatch = output.get(0).tensor();
        Tensor<?> yBatch = output.get(1).tensor();

        System.out.println("New Batch: ");
        System.out.print("   X is");
        printIntTensor(XBatch);
        System.out.print("   y is");
        printIntTensor(yBatch);
      }
    }
  }


  @Test
  public  void testGraphBatching() {
    try (Graph graph = new Graph()) {

      Ops tf = Ops.create(graph);
      try (Session session = new Session(graph)) {
        long BATCH_SIZE = 2L;

        Constant<TInt32> X = tf.val(
            new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
            }
        );

        Constant<TInt32> y = tf.val(
            new int[][]{
                {1},
                {4},
                {7},
                {10}
            }
        );

        // // Try running TensorDataset
        List<DataType<?>> outputTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);
        List<Shape> outputShapes = Arrays.asList(
            Shape.of(3),
            Shape.of(1));

        TensorSliceDataset tensorDataset = TensorSliceDataset.create(tf.scope(),
            Arrays.asList(X, y),
            outputShapes);

        BatchDataset batchDataset = BatchDataset.create(
            tf.scope(),
            tensorDataset,
            tf.val(BATCH_SIZE),
            tf.val(true),
            outputTypes,
            Arrays.asList(
                Shape.of(BATCH_SIZE, 3),
                Shape.of(BATCH_SIZE, 1))
        );

        Iterator anonymousIter = Iterator.create(tf.scope(), null, null, outputTypes, Arrays.asList(
            Shape.of(BATCH_SIZE, 3),
            Shape.of(BATCH_SIZE, 1)));

        MakeIterator makeIterator = tf.data.makeIterator(batchDataset, anonymousIter);

        session.runner()
            .addTarget(makeIterator.op())
            .run();

        IteratorGetNext getNext = tf.data.iteratorGetNext(anonymousIter, outputTypes, outputShapes);
        Operand<?> XOp = getNext.components().get(0);
        Operand<?> yOp = getNext.components().get(1);
        while (true) {
          try {
            List<Tensor<?>> outputs = session.runner()
                .addTarget(getNext.op())
                .fetch(XOp)
                .fetch(yOp)
                .run();
            System.out.println("BATCH: ");
            printIntTensor(outputs.get(0));
            printIntTensor(outputs.get(1));
            System.out.println();
          } catch (IndexOutOfBoundsException e) {
            System.out.println("finished iterating.");
            break;
          }
        }
      }
    }

  }

  @Test
  public void testCleanGraphBatching() {
    try (Graph graph = new Graph()) {

      Ops tf = Ops.create(graph);
      try (Session session = new Session(graph)) {
        long BATCH_SIZE = 2L;

        Constant<TInt32> X = tf.val(
            new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
            }
        );

        Constant<TInt32> y = tf.val(
            new int[][]{
                {1},
                {4},
                {7},
                {10}
            }
        );

        // // Try running TensorDataset
        List<Operand<?>> tensors = Arrays.asList(X, y);
        List<DataType<?>> outputTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);

        Dataset dataset = Dataset
            .fromTensorSlices(tf, tensors, outputTypes)
            .batch(2);

        OneShotIterator oneShotIterator = dataset.makeOneShotIterator();
        List<Output<?>> components = oneShotIterator.getComponents();
        Operand<?> XOp = components.get(0);
        Operand<?> yOp = components.get(1);

        // Run MakeIterator Op
        session.runner()
            .addTarget(oneShotIterator.getMakeIteratorOp())
            .run();

        while (true) {
          try {
            List<Tensor<?>> outputs = session.runner()
                .fetch(XOp)
                .fetch(yOp)
                .run();

            System.out.println("BATCH: ");
            printIntTensor(outputs.get(0));
            printIntTensor(outputs.get(1));
            System.out.println();
          } catch (IndexOutOfBoundsException e) {
            System.out.println("finished iterating.");
            break;
          }
        }
      }
    }
  }

  public static void printIntTensor(Tensor<?> tensor) {
    tensor.expect(TInt32.DTYPE).data().;
    System.out.println("Tensor: shape=" + Arrays.toString(tensor.shape().asArray()) + " vals=" + Arrays.toString(buffer.array()));
  }
}
