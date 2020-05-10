//package org.tensorflow.framework.datasets;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//
//import org.junit.Test;
//import org.junit.Before;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;
//import static org.tensorflow.framework.datasets.GCSUtils.isDatasetOnGCS;
//import static org.tensorflow.framework.datasets.GCSUtils.listGCSDatasetInfoFiles;
//
//
//public class GCSUtilsTest {
//
//  @Test
//  public void testIsDatasetOnGCS() {
//    assertTrue(isDatasetOnGCS("mnist"));
//    assertFalse(isDatasetOnGCS("non-existent-dataset"));
//  }
//
//  @Test
//  public void testDownload() throws URISyntaxException, IOException {
//    listGCSDatasetInfoFiles("mnist").forEach(System.out::println);
//  }
//
//  @Test
//  public void testGcsDownload() throws IOException {
//    new DatasetBuilder("mnist", new Version(3, 0, 1)).downloadAndPrepare();
//  }
//}
