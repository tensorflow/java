//package org.tensorflow.data;
//
//import org.junit.Test;
//import org.tensorflow.*;
//import org.tensorflow.op.Ops;
//
//import java.nio.FloatBuffer;
//import java.util.Iterator;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//class GraphModeTensorFrameTest {
//    @Test
//    void testGraphModeSession() {
//        int batchSize = 2;
//        try (Graph graph = new Graph();
//             Tensor<Float> XTensor = Tensors.create(
//                     new float[][]{
//                             {1, 2, 3},
//                             {4, 5, 6},
//                             {7, 8, 9},
//                             {10, 11, 12},
//                             {13, 14, 15},
//                             {16, 17, 18},
//                             {19, 20, 21},
//                             {22, 23, 24},
//                             {25, 26, 27}
//                     }
//             );
//
//             Tensor<Float> yTensor = Tensors.create(new float[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
//             GraphTensorArrayDataset<Float> graphModeTensorFrame
//                     = new GraphTensorArrayDataset<>(graph, Float.class, XTensor, yTensor)) {
//
//            Ops tf = Ops.create(graph);
//
//            graphModeTensorFrame.build(tf);
//            graphModeTensorFrame.batch(batchSize, true);
//
//
//            Iterator<Tensor<?>[]> batchIterator = graphModeTensorFrame.batchIterator(tf);
//
//            int batchIndex = 0;
//            while (batchIterator.hasNext()) {
//                System.out.println(batchIndex);
//                Tensor<?>[] batch = batchIterator.next();
//
//                try (Tensor<?> XBatch = batch[0];
//                     Tensor<?> yBatch = batch[1]) {
//                    FloatBuffer XBuffer = FloatBuffer.allocate(3 * 2);
//                    FloatBuffer yBuffer = FloatBuffer.allocate(2);
//
//                    XBatch.writeTo(XBuffer);
//                    yBatch.writeTo(yBuffer);
//
//                    float[] xarray = XBuffer.array();
//
//                    for (int i = 0; i < XBatch.shape()[0]; i++) {
//                        for (int j = 0; j < XBatch.shape()[1]; j++) {
//                            float real = xarray[i * batchSize + j];
//                            assertEquals(batchIndex * graphModeTensorFrame.getBatchSize() * XBatch.shape()[1] + i * batchSize + j + 1, real);
//                        }
//                    }
//                }
//
//                batchIndex++;
//            }
//        }
//    }
//}