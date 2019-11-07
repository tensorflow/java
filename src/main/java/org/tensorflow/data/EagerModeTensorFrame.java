// package org.tensorflow.data;

// import org.tensorflow.Operand;
// import org.tensorflow.Tensor;
// import org.tensorflow.op.Ops;
// import org.tensorflow.op.core.Constant;
// import org.tensorflow.op.core.Slice;

// import java.util.Iterator;

// public class EagerModeTensorFrame<T> extends TensorFrame<T> implements EagerLoader<T> {
//     // Number of elements in this tensor frame
//     private long numElements;

//     // Tensors contained in this frame
//     private Constant<T>[] data;

//     @SafeVarargs
//     public EagerModeTensorFrame(Constant<T> firstTensor, Constant<T>... tensors) {
//         numElements = firstTensor.asOutput().shape().size(0);
//         for (Constant<T> tensor : tensors) {
//             if (numElements != tensor.asOutput().shape().size(0)) {
//                 throw new IllegalArgumentException("All input tensors must share the same first dimension.");
//             }
//         }

//         data = (Constant<T>[]) new Constant[tensors.length + 1];
//         data[0] = firstTensor;
//         System.arraycopy(tensors, 0, data, 1, tensors.length);
//     }


//     Slice<T>[] getSlice(Ops tf, long start, long size) {
//         Slice<T>[] slices = (Slice<T>[]) new Slice[this.data.length];



//         for (int i = 0; i < slices.length; i++) {
//             long[] startSelector = Utils.batchStartSelector(tf, (int) start, this.data[i].asOutput().shape().numDimensions());
//             long[] sizeSelector = Utils.batchSizeSelector(tf, (int) size, this.data[i].asOutput().shape().numDimensions());
//             slices[i] = tf.slice(this.data[i], Constant.create(tf.scope(),startSelector), Constant.create(tf.scope(),sizeSelector));
//         }

//         return slices;
//     }

//     @Override
//     public long numTensors() {
//         return this.data.length;
//     }

//     @Override
//     public long numElementsPerTensor() {
//         return this.numElements;
//     }

// //    @Override
//     public Iterator<Operand<T>[]> batchIterator(Ops tf) {
//         return new Iterator<>() {
//             private long currentBatch = 0;

//             @Override
//             public boolean hasNext() {
//                 return this.currentBatch < numBatches();
//             }

//             @Override
//             public Operand<T>[] next() {
//                 return getSlice(tf, currentBatch++ * getBatchSize(), getBatchSize());
//             }
//         };
//     }
// }
