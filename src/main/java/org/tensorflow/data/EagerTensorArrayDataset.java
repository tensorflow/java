 package org.tensorflow.data;

 import org.tensorflow.EagerSession;
 import org.tensorflow.ExecutionEnvironment;
 import org.tensorflow.Operand;
 import org.tensorflow.data.types.OperandArrayDataset;
 import org.tensorflow.op.Ops;
 import org.tensorflow.op.core.Constant;
 import org.tensorflow.op.core.Slice;

 import java.util.Iterator;

 public class EagerTensorArrayDataset<T> extends OperandArrayDataset<T> {
     // EagerSession reference
     EagerSession eagerSessionRef;

     // Number of elements in this tensor frame
     private long numElements;

     // Tensors contained in this frame
     private Constant<T>[] data;

     @SafeVarargs
     public EagerTensorArrayDataset(EagerSession session, Constant<T> firstTensor, Constant<T>... tensors) {
         eagerSessionRef = session;
         numElements = firstTensor.asOutput().shape().size(0);
         for (Constant<T> tensor : tensors) {
             if (numElements != tensor.asOutput().shape().size(0)) {
                 throw new IllegalArgumentException("All input tensors must share the same first dimension.");
             }
         }

         data = (Constant<T>[]) new Constant[tensors.length + 1];
         data[0] = firstTensor;
         System.arraycopy(tensors, 0, data, 1, tensors.length);
     }


     Slice<T>[] getSlice(Ops tf, long start, long size) {
         Slice<T>[] slices = (Slice<T>[]) new Slice[this.data.length];

         for (int i = 0; i < slices.length; i++) {
             long[] startSelector = Utils.batchStartSelector(tf, (int) start, this.data[i].asOutput().shape().numDimensions());
             long[] sizeSelector = Utils.batchSizeSelector(tf, (int) size, this.data[i].asOutput().shape().numDimensions());
             slices[i] = tf.slice(this.data[i], Constant.create(tf.scope(), startSelector), Constant.create(tf.scope(),sizeSelector));
         }

         return slices;
     }

     @Override
     public Iterator<Operand<T>[]> iterator() {
         return new Iterator<>() {
             Ops tf = Ops.create(eagerSessionRef);
             long current = 0;

             @Override
             public boolean hasNext() {
                return current < size();
             }

             @Override
             public Slice<T>[] next() {
                 // Iterator over elements 1 at a time.
                 return getSlice(tf, current++, 1);
             }

         };
     }


     @Override
     public int numOperands() {
         return data.length;
     }

     @Override
     public long size() {
         return numElements;
     }

     @Override
     public ExecutionEnvironment environment() {
         return eagerSessionRef;
     }
 }
