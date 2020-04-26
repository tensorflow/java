package org.tensorflow.framework.datasets;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.tensorflow.framework.datasets.GCSUtils.*;

public class GCSUtilsTest {

  @Test
  public void testIsDatasetOnGCS() {
    assertTrue(isDatasetOnGCS("mnist"));
    assertFalse(isDatasetOnGCS("non-existent-dataset"));
  }

  @Test
  public void testDownload() throws IOException {
    new DatasetBuilder("mnist", new Version(3, 0, 1))
        .downloadAndPrepare();
  }
}