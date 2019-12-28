package org.tensorflow.data;

import org.tensorflow.tools.Shape;

public class Utils {

    private static Shape head(Shape shape) {
        return Shape.make(shape.size(0));
    }

    public static Shape tail(Shape shape) {
        long[] tail = new long[shape.numDimensions() - 1];
        for (int i = 1; i < shape.numDimensions(); i++) {
            tail[i - 1] = shape.size(i);
        }

        return Shape.make(tail);
    }

    public static long[] shapeArray(Shape shape) {
        long[] arr = new long[shape.numDimensions()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = shape.size(i);
        }
        return arr;
    }

    public static long[] array(long first, long... arr) {
        long[] result = new long[arr.length + 1];
        result[0] = first;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }
}
