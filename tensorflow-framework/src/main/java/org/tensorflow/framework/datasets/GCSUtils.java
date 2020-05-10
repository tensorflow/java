package org.tensorflow.framework.datasets;

import org.apache.http.client.utils.URIBuilder;
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
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GCSUtils {
  public static final String GCS_URL = "storage.googleapis.com";
  public static final String GCS_BUCKET = "tfds-data";
  public static final String GCS_DATASET_INFO_DIR = "dataset_info";
  public static final String GCS_DATSETS_DIR = "datasets";

  public static URL getGCSUrlWithPrefix(String prefix, String... paths) {
    String[] segments = new String[paths.length + 1];
    segments[0] = GCS_BUCKET;
    System.arraycopy(paths, 0, segments, 1, paths.length);
    try {
      return new URIBuilder()
          .setScheme("https")
          .setHost(GCS_URL)
          .setPathSegments(segments)
          .setParameter("prefix", prefix)
          .build()
          .toURL();
    } catch (URISyntaxException | MalformedURLException e) {
      throw new IllegalArgumentException("Invalid URL Syntax.");
    }
  }

  public static URL getGcsUrl(String... paths) {
    return getGCSUrlWithPrefix("", paths);
  }

  public static void downloadGCSFile(String gcsPath, String outPath, String prefixFilter)
      throws IOException {
    URL url = getGCSUrlWithPrefix(prefixFilter, gcsPath);
    try (BufferedInputStream input = new BufferedInputStream(url.openStream());
        FileOutputStream output = new FileOutputStream(outPath)) {
      byte[] buffer = new byte[1024];
      for (int count; (count = input.read(buffer, 0, buffer.length)) != -1; ) {
        output.write(buffer, 0, count);
      }
    }
  }

  public static List<String> gcsFiles(String prefix) throws IOException {
    String url = getGCSUrlWithPrefix(prefix).toString();

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

  public static List<String> listGCSDatasetInfoFiles(String datasetName) throws IOException {
    String prefix = GCS_DATASET_INFO_DIR + "/" + datasetName + "/";
    return gcsFiles(prefix).stream()
        .filter(name -> name.startsWith(prefix) && name.length() > prefix.length())
        .collect(Collectors.toList());
  }

  public static boolean isDatasetOnGCS(String datasetName) {
    String prefix = GCS_DATSETS_DIR + "/" + datasetName + "/";
    try {
      List<String> files = gcsFiles(prefix);
      return files.size() > 2;
    } catch (IOException e) {
      return false;
    }
  }

  public static void downloadGCSDataset(String datasetName, String localDatasetDir)
      throws IOException {
    String prefix = GCS_DATSETS_DIR + "/" + datasetName;
    new File(localDatasetDir).mkdir();

    List<String> gcsPathsToDownload =
        gcsFiles(prefix).stream()
            .filter(name -> !name.startsWith(prefix + "/" + "diffs"))
            .collect(Collectors.toList());

    int count = 0;
    for (String gcsPath : gcsPathsToDownload) {
      String localPath = localDatasetDir + "/" + basePath(gcsPath);
      downloadGCSFile(gcsPath, localPath, null);
      System.out.println(
          "Downloaded " + count++ + " / " + gcsPathsToDownload.size() + " to " + localPath);
    }
  }

  public static String basePath(String path) {
    String[] components = path.split("/");
    return components[components.length - 1];
  }
}
