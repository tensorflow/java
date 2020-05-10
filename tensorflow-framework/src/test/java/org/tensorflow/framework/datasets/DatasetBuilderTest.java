package org.tensorflow.framework.datasets;

import java.io.IOException;
import org.junit.Test;

public class DatasetBuilderTest {

  public static void main(String[] args) {
    
  }

  @Test
  public void testt() throws IOException {
    MNIST mnist = new MNIST();

    mnist.downloadAndPrepare();
    mnist.downloadAndPrepare(true);
  }
}
