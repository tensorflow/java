package org.tensorflow.framework.datasets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
  public static void downlad(URL url, Path outPath) throws IOException {
    File outFile = outPath.toFile();
    if (outFile.getParentFile().exists() || outFile.getParentFile().mkdirs()) {
      try (BufferedInputStream input = new BufferedInputStream(url.openStream());
          FileOutputStream output = new FileOutputStream(outPath.toFile())) {
        byte[] buffer = new byte[1024];
        for (int count; (count = input.read(buffer, 0, buffer.length)) != -1; ) {
          output.write(buffer, 0, count);
        }
      }
    }
  }

  public static String readFile(Path path) throws IOException {
    return new String(Files.readAllBytes(path));
  }

  public static DatasetInfo readDatasetInfo(Path path) throws IOException {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    return gson.fromJson(readFile(path), DatasetInfo.class);
  }
}
