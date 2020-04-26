package org.tensorflow.framework.datasets;

import java.io.IOException;

public class DatasetBuilder {
  private final String name;
  private final Version version;

  public DatasetBuilder(String name, Version version) {
    this.name = name;
    this.version = version;
  }

  public  void downloadAndPrepare() throws IOException {
    GCSUtils.downloadGCSDataset(name + "/" +  version.toString(),
        "/home/dhruv" + "/tensorflow_datasets/" + name + "/" + version.toString());
  }
}
