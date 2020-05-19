package org.tensorflow.framework.datasets;

import org.junit.Test;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.framework.data.Dataset;
import org.tensorflow.op.Ops;
import org.tensorflow.op.io.FifoQueue;
import org.tensorflow.op.io.ReaderRead;
import org.tensorflow.op.io.TfRecordReader;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DatasetBuilderTest {

  public static void main(String[] args) {}

  @Test
  public void testTF() {
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);

      TfRecordReader reader = tf.io.tfRecordReader();
      tf.io.
      FifoQueue queue = tf.io.fifoQueue(Collections.singletonList(TString.DTYPE));
      ReaderRead read = tf.io.readerRead(reader, queue);
      System.out.println(read.key().data().getObject());
      System.out.println(read.value().data().getObject());
    }
  }

  @Test
  public void testt() throws IOException {
    Ops tf = Ops.create();
    MNIST mnist = new MNIST(tf);

    //    mnist.read();

    Map<String, Dataset> splits = mnist.asDataset();
    Dataset train = splits.get("train");
    System.out.println(train);
    //    Dataset test = splits.get("test");

    int i = 0;
    for (List<Operand<?>> components : train) {
      System.out.println(
          i + ": " + components.get(1).asOutput().expect(TInt32.DTYPE).data().getInt());
    }
  }
}
