package org.tensorflow.framework.datasets;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GCSUtils {
  public static final String GCS_URL = "http://storage.googleapis.com";
  public static final String GCS_BUCKET = GCS_URL + "/" + "tfds-data";

  public static final String GCS_DATASET_INFO_DIR = "dataset_info";
  public static final String GCS_DATSETS_DIR = "datasets";

  private static String buildGcsUrl(String path, String prefixFilter) {
    return GCS_BUCKET
        + (path.length() > 0 ? "/" + path : "")
        + (prefixFilter != null ? "?prefix=" + prefixFilter : "");
  }

  private static String datasetDirectory(String datasetName) {
    return GCS_DATSETS_DIR + "/" + datasetName;
  }

  private static String datasetInfoPrefix(String datasetName) {
    return GCS_DATASET_INFO_DIR + "/" + datasetName;
  }

  public static void downloadGCSFile(String gcsPath, String outPath, String prefixFilter)
      throws IOException {
    String url = buildGcsUrl(gcsPath, prefixFilter);
    try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream());
         FileOutputStream output = new FileOutputStream(outPath)) {
      byte[] buffer = new byte[1024];
      for (int count; (count = input.read(buffer, 0, buffer.length)) != -1; ) {
        output.write(buffer, 0, count);
      }
    }
  }

  public static List<String> gcsFiles(String gcsPath) throws IOException {
    String url = buildGcsUrl("", gcsPath);

    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

      Document document = builder.parse(url);
      NodeList nodes = document.getElementsByTagName("Contents");

      List<String> filenames = new ArrayList<>();
      for (int i = 0; i < nodes.getLength(); i++) {
        filenames.add(nodes.item(i).getFirstChild().getTextContent());
      }

      return filenames;
    } catch (SAXException | ParserConfigurationException e) {
      throw new IOException("Could not parse XML at " + url);
    }
  }

  public static List<String> gcsDatasetInfoFiles(String datasetName) throws IOException {
    String prefix = datasetInfoPrefix(datasetName);
    return  gcsFiles(prefix)
        .stream()
        .filter(file -> file.startsWith(prefix) && file.length() > prefix.length())
        .collect(Collectors.toList());
  }

  public static boolean isDatasetOnGCS(String datasetName) {
    try {
      List<String> files = gcsFiles(datasetDirectory(datasetName));
      return files.size() > 2;
    } catch (IOException e) {
      return false;
    }
  }

  public static void downloadGCSDataset(String datasetName,
                                        String localDatasetDir) throws IOException {
    String prefix = datasetDirectory(datasetName);
    List<String> gcsPathsToDownload = gcsFiles(prefix).stream()
        .filter(name -> !name.startsWith(prefix + "/" + "diffs"))
        .collect(Collectors.toList());

    File localDir = new File(localDatasetDir);
    localDir.mkdir();
    System.out.println("local DIR" + localDatasetDir);
    int count = 0;
    for (String gcsPath : gcsPathsToDownload) {
      String localPath = localDatasetDir + "/" + basePath(gcsPath);
      downloadGCSFile(gcsPath, localPath, null);
      System.out.println("Downloaded " + count++ + " / " + gcsPathsToDownload.size() + " to " + localPath);
    }
  }

  private static String basePath(String path) {
    String[] components = path.split("/");
    return components[components.length - 1];
  }
}
