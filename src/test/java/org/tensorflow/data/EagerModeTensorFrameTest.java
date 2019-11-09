// package org.tensorflow.data;

// import org.junit.jupiter.api.Test;
// import org.tensorflow.Operand;
// import org.tensorflow.op.Ops;
// import org.tensorflow.op.core.Constant;

// import java.nio.FloatBuffer;
// import java.util.Iterator;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// class EagerModeTensorFrameTest {
//     @Test
//     void test() {
//         Ops tf = Ops.create();

//         Constant<Float> tensor1 = tf.constant(new float[][] {
//                 {1, 2},
//                 {3, 4},
//                 {5, 6},
//                 {7, 8}
//         });

//         Constant<Float> tensor2 = tf.constant(new float[] {
//                 10, 11, 12, 13
//         });

//         int BATCH_SIZE = 2;

//         EagerModeTensorFrame<Float> frame = new EagerModeTensorFrame<Float>(tensor1, tensor2);
//         frame.batch(BATCH_SIZE, true);
//         Iterator<Operand<Float>[]> batchIterator = frame.batchIterator(tf);

//         int batch = 0;
//         while (batchIterator.hasNext()) {
//             Operand<Float>[] arr = batchIterator.next();
//             Operand<Float> XBatch = arr[0];
//             Operand<Float> yBatch = arr[1];

//             FloatBuffer fbX = FloatBuffer.allocate(4);
//             FloatBuffer fby = FloatBuffer.allocate(2);

//             XBatch.asOutput().tensor().writeTo(fbX);
//             yBatch.asOutput().tensor().writeTo(fby);

//             int xstart = batch * BATCH_SIZE * 2;
//             int ystart = batch * BATCH_SIZE + 10;

//             float[] xarray = fbX.array();
//             float[] yarray = fby.array();

//             for (int i = 0; i < xarray.length; i++) {
//                 assertEquals(i + xstart + 1, xarray[i]);
//             }

//             for (int i = 0; i < yarray.length; i++) {
//                 assertEquals(i + ystart, yarray[i]);
//             }

//             batch ++;
//         }
//     }
// }