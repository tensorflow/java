package org.tensorflow.data;

import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.data.*;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

public class DatasetOpTester {

  public static void main(String[] args) {
    try(Graph graph = new Graph()) {
      TensorFlow.initEnvironment(graph);
      Ops tf = TensorFlow.ops();



    }
  }

//   public static void testEagerBatching() {
//       try (EagerSession session = EagerSession.create()) {
//           Ops tf = Ops.create(session);
//
//           Constant<Integer> X = Constant.create(tf.scope(),
//                   new int[][] {
//                           {1, 2, 3},
//                           {4, 5, 6},
//                           {7, 8, 9},
//                           {10, 11, 12}
//                   }
//           );
//
//           Constant<Integer> y = Constant.create(tf.scope(),
//                   new int[][] {
//                           {1},
//                           {4},
//                           {7},
//                           {10}
//                   }
//           );
//
//
//           List<Operand<?>> tensors = Arrays.asList(X, y);
//
//           // // Try running TensorDataset
//           List<Class<?>> outputTypes = Arrays.asList(Integer.class, Integer.class);
//           List<Shape> outputShapes = Arrays.asList(
//                   Shape.make( 3),
//                   Shape.make( 1));
//
//           TensorSliceDataset tensorDataset = TensorSliceDataset.create(tf.scope(), tensors, outputShapes);
//
//           BatchDataset batchDataset = BatchDataset.create(
//                   tf.scope(),
//                   tensorDataset,
//                   Constant.create(tf.scope(), 2L),
//                   Constant.create(tf.scope(), true),
//                   outputTypes,
//                   Arrays.asList(
//                           Shape.make( 2, 3),
//                           Shape.make( 2, 1))
//           );
//
//
//
//           AnonymousIterator anonymousIter = AnonymousIterator.create(tf.scope(),  outputTypes, Arrays.asList(
//                   Shape.make( 2, 3),
//                   Shape.make( 2, 1)));
//
//           MakeIterator makeIterator = tf.data.makeIterator(batchDataset, anonymousIter);
//
//           while (true) {
//               try {
//                   IteratorGetNext getNext = tf.data.iteratorGetNext(anonymousIter, outputTypes, outputShapes);
//                   List<Output<?>> outputs = getNext.components();
//                   System.out.println("BATCH: ");
//                   printIntTensor(outputs.get(0).tensor());
//                   printIntTensor(outputs.get(1).tensor());
//                   System.out.println();
//               } catch (IndexOutOfBoundsException e) {
//                   System.out.println("finished iterating.");
//                   break;
//               }
//           }
//       }
//   }
//
//   public static void testCleanEagerBatching() {
//       try (EagerSession session = EagerSession.create()) {
//           Ops tf = Ops.create(session);
//
//           Constant<Integer> X = Constant.create(tf.scope(),
//                   new int[][] {
//                           {1, 2, 3},
//                           {4, 5, 6},
//                           {7, 8, 9},
//                           {10, 11, 12}
//                   }
//           );
//
//           Constant<Integer> y = Constant.create(tf.scope(),
//                   new int[][] {
//                           {1},
//                           {4},
//                           {7},
//                           {10}
//                   }
//           );
//
//
//           // // Try running TensorDataset
//           List<Operand<?>> tensors = Arrays.asList(X, y);
//           List<Class<?>> outputTypes = Arrays.asList(Integer.class, Integer.class);
//
//           Dataset dataset = Dataset
//                   .fromTensorSlices(tf, tensors, outputTypes)
//                   .batch(tf, 2);
//
//           for (List<Output<?>> output : dataset.asIterable(tf)) {
//               Tensor<?> XBatch = output.get(0).tensor();
//               Tensor<?> yBatch = output.get(1).tensor();
//
//               System.out.println("New Batch: ");
//               System.out.print("   X is");
//               printIntTensor(XBatch);
//               System.out.print("   y is");
//               printIntTensor(yBatch);
//           }
//       }
//   }
//
//
//   public static void testGraphBatching() {
//       try (Graph graph = new Graph()) {
//
//           Ops tf = Ops.create(graph);
//           try (Session session = new Session(graph)) {
//               long BATCH_SIZE = 2L;
//
//               Constant<Integer> X = Constant.create(tf.scope(),
//                       new int[][]{
//                               {1, 2, 3},
//                               {4, 5, 6},
//                               {7, 8, 9},
//                               {10, 11, 12}
//                       }
//               );
//
//               Constant<Integer> y = Constant.create(tf.scope(),
//                       new int[][]{
//                               {1},
//                               {4},
//                               {7},
//                               {10}
//                       }
//               );
//
//               // // Try running TensorDataset
//               List<Class<?>> outputTypes = Arrays.asList(Integer.class, Integer.class);
//               List<Shape> outputShapes = Arrays.asList(
//                       Shape.make(3),
//                       Shape.make(1));
//
//               TensorSliceDataset tensorDataset = TensorSliceDataset.create(tf.scope(),
//                       Arrays.asList(X, y),
//                       outputShapes);
//
//               BatchDataset batchDataset = BatchDataset.create(
//                       tf.scope(),
//                       tensorDataset,
//                       Constant.create(tf.scope(), BATCH_SIZE),
//                       Constant.create(tf.scope(), true),
//                       outputTypes,
//                       Arrays.asList(
//                               Shape.make(BATCH_SIZE, 3),
//                               Shape.make(BATCH_SIZE, 1))
//               );
//
//               Iterator anonymousIter = Iterator.create(tf.scope(), "anoniterator", "iterator", outputTypes, Arrays.asList(
//                       Shape.make(BATCH_SIZE, 3),
//                       Shape.make(BATCH_SIZE, 1)));
//
//               MakeIterator makeIterator = tf.data.makeIterator(batchDataset, anonymousIter);
//
//               session.runner()
//                       .addTarget(makeIterator.op())
//                       .run();
//
//               IteratorGetNext getNext = tf.data.iteratorGetNext(anonymousIter, outputTypes, outputShapes);
//               Operand<?> XOp = getNext.components().get(0);
//               Operand<?> yOp = getNext.components().get(1);
//               while (true) {
//                   try {
//                       List<Tensor<?>> outputs = session.runner()
//                               .addTarget(getNext.op())
//                               .fetch(XOp)
//                               .fetch(yOp)
//                               .run();
//                       System.out.println("BATCH: ");
//                       printIntTensor(outputs.get(0));
//                       printIntTensor(outputs.get(1));
//                       System.out.println();
//                   } catch (IndexOutOfBoundsException e) {
//                       System.out.println("finished iterating.");
//                       break;
//                   }
//               }
//           }
//       }
//
//   }
//
//   public static void testCleanGraphBatching() {
//       try (Graph graph = new Graph()) {
//
//           Ops tf = Ops.create(graph);
//           try (Session session = new Session(graph)) {
//               long BATCH_SIZE = 2L;
//
//               Constant<Integer> X = Constant.create(tf.scope(),
//                       new int[][]{
//                               {1, 2, 3},
//                               {4, 5, 6},
//                               {7, 8, 9},
//                               {10, 11, 12}
//                       }
//               );
//
//               Constant<Integer> y = Constant.create(tf.scope(),
//                       new int[][]{
//                               {1},
//                               {4},
//                               {7},
//                               {10}
//                       }
//               );
//
//               // // Try running TensorDataset
//               List<Operand<?>> tensors = Arrays.asList(X, y);
//               List<Class<?>> outputTypes = Arrays.asList(Integer.class, Integer.class);
//
//               Dataset dataset = Dataset
//                       .fromTensorSlices(tf, tensors, outputTypes)
//                       .batch(tf, 2);
//
//               Pair<Operation, List<Output<?>>> oneShotIterator = dataset.makeOneShotIterator(tf);
//               Operation makeIteratorOp = oneShotIterator.first();
//               List<Output<?>> iteratorComponents = oneShotIterator.second();
//
//               session.runner()
//                       .addTarget(makeIteratorOp)
//                       .run();
//
//               Operand<?> XOp = iteratorComponents.get(0);
//               Operand<?> yOp = iteratorComponents.get(1);
//
//               while (true) {
//                   try {
//                       List<Tensor<?>> outputs = session.runner()
//                               .fetch(XOp)
//                               .fetch(yOp)
//                               .run();
//
//                       System.out.println("BATCH: ");
//                       printIntTensor(outputs.get(0));
//                       printIntTensor(outputs.get(1));
//                       System.out.println();
//                   } catch (IndexOutOfBoundsException e) {
//                       System.out.println("finished iterating.");
//                       break;
//                   }
//               }
//           }
//       }
//   }
//
//   public static void main(String[] args) {
//       testCleanGraphBatching();
//   }
//
//   public static void printIntTensor(Tensor<?> tensor) {
//       IntBuffer buffer = IntBuffer.allocate(tensor.numElements());
//       tensor.writeTo(buffer);
//       System.out.println("Tensor: shape=" + Arrays.toString(tensor.shape()) + " vals=" + Arrays.toString(buffer.array()));
//   }
}
