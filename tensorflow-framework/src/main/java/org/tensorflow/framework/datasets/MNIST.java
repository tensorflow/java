package org.tensorflow.framework.datasets;

import org.apache.http.client.utils.URIBuilder;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.ndarray.ByteNdArray;
import org.tensorflow.tools.ndarray.NdArrays;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MNIST extends DatasetBuilder {
  public static final String HOMEPAGE = "yann.lecun.com/exdb/mnist";
  private static final String USER_HOME = System.getProperty("user.home");
  private static final String DATSETS_HOME = "tensorflow_datasets";

  public static final String NAME = "mnist";
  public static final Version TFDS_VERSION = new Version(3, 0, 1);

  public static final String TRAIN_IMAGES = "train-images-idx3-ubyte.gz";
  public static final String TRAIN_LABELS = "train-labels-idx1-ubyte.gz";

  public static final String TEST_IMAGES = "t10k-images-idx3-ubyte.gz";
  public static final String TEST_LABELS = "t10k-labels-idx1-ubyte.gz";

  public static final int IMG_WIDTH = 28;
  public static final int IMG_LENGTH = 28;

  public static Map<String, String> files = new HashMap<>();

  static {
    files.put("train_data", TRAIN_IMAGES);
    files.put("train_labels", TRAIN_LABELS);
    files.put("test_data", TEST_IMAGES);
    files.put("test_labels", TEST_LABELS);
  }

  public MNIST() {
    super(NAME, TFDS_VERSION);
  }

  public void downloadAndPrepare() throws IOException {
    for (String filename : files.values()) {
      try {
        URL url =
            new URIBuilder()
                .setScheme("http")
                .setHost(HOMEPAGE)
                .setPathSegments(filename)
                .build()
                .toURL();

        FileUtils.downlad(url, Paths.get(USER_HOME, DATSETS_HOME, NAME, "raw", filename));
      } catch (URISyntaxException e) {
        throw new IOException();
      }
    }
  }


  public static void read() throws IOException {
    ByteDataBuffer buffer = DataBuffers.ofBytes(IMG_LENGTH * IMG_WIDTH * 10000);
    File file = Paths.get(USER_HOME, DATSETS_HOME, NAME, TRAIN_IMAGES).toFile();
    try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
      byte[] bytes = new byte[1024];
      input.skip(1);
      for (int count; (count = input.read(bytes, 0, bytes.length)) != -1; ) {
        buffer.write(bytes, 0, count);
      }
    }

    ByteNdArray array = NdArrays.wrap(buffer, Shape.of(28, 28, 1000));

    System.out.println(array.getByte(0, 0, 0));
  }
}
