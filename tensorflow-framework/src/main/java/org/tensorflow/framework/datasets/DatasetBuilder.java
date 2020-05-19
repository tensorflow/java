package org.tensorflow.framework.datasets;

import org.tensorflow.framework.data.Dataset;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Empty;
import org.tensorflow.types.TString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class DatasetBuilder {
  private static final String USER_HOME = System.getProperty("user.home");
  private static final String DATSETS_HOME = "tensorflow_datasets";
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

  private static Empty<TString> emptyString(Ops tf) {
    return tf.empty(tf.constant(new int[]{1}), TString.DTYPE);
  }

  public Map<String, Dataset> asDataset() throws IOException {
    System.out.println("AS DATASET");
    DatasetInfo info =
        FileUtils.readDatasetInfo(
            Paths.get(USER_HOME, DATSETS_HOME, name, version.toString(), DATASET_INFO_JSON));

    Map<String, Dataset> datasets = new HashMap<>();
    int count = 0;
    for (DatasetInfo.SplitInfo splitInfo : info.getSplits()) {
      String prefix = name + '-' + splitInfo.getName();
      System.out.println(prefix);
      String[] files =
          Files.list(Paths.get(USER_HOME, DATSETS_HOME, name, version.toString()))
              .filter(path -> path.getFileName().toString().startsWith(prefix))
              .map(path -> path.toAbsolutePath().toString())
              .toArray(String[]::new);

      Dataset dataset = Dataset.tfRecordDataset(tf, files[0], "", 1024);

//      dataset = dataset.mapAllComponents(
//          serialized -> {
//            tf.io.parseExample(
//                serialized,
//                tf.array("image_raw", "label"),
//                Collections.emptyList(),
//                Arrays.asList()
//                )
//          }
//
//      )


      //              .map(
//                  example ->
//                      tf.io
//                          .parseExample(
//                              example.get(0).asOutput().expect(TString.DTYPE),
//                              tf.array("image", "label"),
//                              emptyString(tf),
//                              tf.array("image", "label"),
//                              emptyString(tf),
//                              Arrays.asList(tf.constant(new long[] {}), tf.constant(new long[] {})),
//                              0L,
//                              Collections.emptyList(),
//                              Collections.emptyList(),
//                              Collections.emptyList(),
//                              Arrays.asList(
//                                  info.getSchema().getFeatures()[0].getShape(),
//                                  info.getSchema().getFeatures()[1].getShape()))
//                          .denseValues().stream()
//                          .map(o -> (Operand<?>) o)
//                          .collect(Collectors.toList()));

      /**
       * tf.io.parseSingleExample( example.get(0).asOutput().expect(TString.DTYPE),
       * Arrays.asList(tf.constant(new int[] {}), tf.constant(new int[] {})), 0L,
       * Collections.emptyList(), Arrays.asList("image", "label"), Collections.emptyList(),
       * Arrays.asList( info.getSchema().getFeatures()[0].getShape(),
       * info.getSchema().getFeatures()[1].getShape())) .denseValues().stream() .map(o ->
       * (Operand<?>) o) .collect(Collectors.toList()));
       */

      //              .mapOneComponent(
      //                  0, images -> tf.dtypes.cast(images,
      // info.getSchema().getFeatures()[0].getDtype()))
      //              .mapOneComponent(
      //                  1, labels -> tf.dtypes.cast(labels,
      // info.getSchema().getFeatures()[1].getDtype()))
      //              .setOutputShapes(
      //                  Arrays.asList(
      //                      info.getSchema().getFeatures()[0].getShape(),
      //                      info.getSchema().getFeatures()[1].getShape()))
      //              .setOutputTypes(
      //                  Arrays.asList(
      //                      info.getSchema().getFeatures()[0].getDtype(),
      //                      info.getSchema().getFeatures()[1].getDtype()));

      datasets.put(info.getSplits()[count++].getName(), dataset);
      System.out.println(dataset);
      System.out.println(Arrays.toString(files));
    }

    return datasets;
  }
}
