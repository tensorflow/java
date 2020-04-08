package org.tensorflow.data;

import org.junit.Before;
import org.tensorflow.tools.ndarray.IntNdArray;
import org.tensorflow.tools.ndarray.StdArrays;

public class DatasetTestBase {

    IntNdArray testMatrix1;
    IntNdArray testMatrix2;

    @Before
    public void setUp() {
        testMatrix1 = StdArrays.ndCopyOf(
                new int[][]{
                        {1, 2, 3, 4, 5},
                        {2, 4, 6, 8, 10},
                        {3, 6, 8, 12, 15},
                        {4, 8, 12, 16, 20}
                }
        );

        testMatrix2 = StdArrays.ndCopyOf(
                new int[][]{
                        {1}, {0}, {1}, {1}
                }
        );
    }

//  static int[] concat(int[] first, int[] second) {
//    int[] concatenated = new int[first.length + second.length];
//    System.arraycopy(first, 0, concatenated, 0, first.length);
//    System.arraycopy(second, 0, concatenated, first.length, second.length);
//    return concatenated;
//  }
//
//  static int[] getIntTensorAsArray(Tensor<TInt32> intTensor) {
//    IntBuffer buffer = IntBuffer.allocate((int) intTensor.shape().size());
//    intTensor.writeTo(buffer);
//    return buffer.array();
//  }
}