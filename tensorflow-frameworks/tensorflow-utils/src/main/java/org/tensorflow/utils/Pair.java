package org.tensorflow.utils;

public class Pair<T> extends Tuple2<T, T> {
    public Pair(T first, T second){
        super(first, second);
    }
}

//    public static long[] dimsFromShape(Shape shape) {
//        long[] dims = new long[shape.numDimensions()];
//        for (int i = 0; i < shape.numDimensions(); i++) {
//            dims[i] = shape.size(i);
//        }
//        return dims;
//    }
//

//
//    public static void printIntTensor(Tensor<?> tensor) {
//        IntBuffer buffer = IntBuffer.allocate(new TensorShape(shapeFromDims(tensor.shape())).numElements());
//        tensor.writeTo(buffer);
//        System.out.println(Arrays.toString(buffer.array()));
//    }
//
//    public static void printBoolTensor(Tensor<?> tensor) {
//        ByteBuffer buffer = ByteBuffer.allocate(new TensorShape(shapeFromDims(tensor.shape())).numElements());
//        tensor.writeTo(buffer);
//        System.out.println(Arrays.toString(buffer.array()));
//    }
//
//    public static Shape shapeFromDims(long... dims) {
//        return Shape.make(head(dims), tail(dims));
//    }