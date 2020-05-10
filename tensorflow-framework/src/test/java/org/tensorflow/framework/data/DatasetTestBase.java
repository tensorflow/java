package org.tensorflow.framework.data;

import org.junit.Before;
import org.tensorflow.tools.ndarray.IntNdArray;
import org.tensorflow.tools.ndarray.StdArrays;

public class DatasetTestBase {

  IntNdArray testMatrix1;
  IntNdArray testMatrix2;

  @Before
  public void setUp() {
    testMatrix1 =
        StdArrays.ndCopyOf(
            new int[][] {
              {1, 2, 3, 4, 5},
              {2, 4, 6, 8, 10},
              {3, 6, 9, 12, 15},
              {4, 8, 12, 16, 20}
            });

    testMatrix2 = StdArrays.ndCopyOf(new int[][] {{1}, {0}, {1}, {1}});
  }
}
