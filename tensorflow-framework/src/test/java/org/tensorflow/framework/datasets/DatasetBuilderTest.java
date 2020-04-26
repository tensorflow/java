package org.tensorflow.framework.datasets;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DatasetBuilderTest {

  @Test
  public void downloadAndPrepareTest() throws IOException {
    DatasetBuilder builder = new DatasetBuilder("mnist", new Version(3, 0, 1));
    builder.downloadAndPrepare();
  }
}