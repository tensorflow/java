package org.tensorflow.framework.datasets;

import org.tensorflow.framework.data.Dataset;
import org.tensorflow.op.Ops;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public abstract class DatasetBuilder {
  private static final String USER_HOME = System.getProperty("user.home");
  private static final String DATSETS_HOME = "tensorflow_datatsets";
  private static final String DATASET_INFO_JSON = "dataset_info.json";

  private final Ops tf;
  private final String name;
  private final Version version;
  private boolean usingGCS;

  public DatasetBuilder(Ops tf, String name, Version version) {
    this.tf = tf;
    this.name = name;
    this.version = version;
    this.usingGCS = false;
  }

  public abstract void downloadAndPrepare() throws IOException;

  public void downloadAndPrepare(boolean useGCS) throws IOException {
    if (useGCS) {
      downloadAndPrepare();
      usingGCS = true;
    } else {
      GCSUtils.downloadGCSDataset(
          Paths.get(name, version.toString()).toString(),
          Paths.get(USER_HOME, DATSETS_HOME, name, version.toString()).toString());
    }
  }

  public Map<String, Dataset> asDataset() throws IOException {
    DatasetInfo info = FileUtils.readDatasetInfo(Paths.get(USER_HOME, DATSETS_HOME, name, DATASET_INFO_JSON));
    Dataset.tfRecordDataset()
    for (DatasetInfo.Schema.Feature feature : info.getSchema().getFeatures()) {
      feature.
    }
  }
}
