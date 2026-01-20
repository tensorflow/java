package org.tensorflow.framework.callbacks.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathPlaceholderStringFormatTest {

  @Test
  public void testPlaceholder() {
    String filePath = "weights.{epoch:02d}-{val_loss:.2f}.hdf5";

    Map<String, Number> logs = new HashMap<>();
    logs.put("val_loss", 0.71);

    // test with val_loss and  1 digit epoch
    String result = PathPlaceholderStringFormat.convertFilePath(filePath, 1, logs);
    String expect = "weights.01-0.71.hdf5";
    assertEquals(expect, result);

    // test with val_loss and  2 digit epoch
    result = PathPlaceholderStringFormat.convertFilePath(filePath, 12, logs);
    expect = "weights.12-0.71.hdf5";
    assertEquals(expect, result);

    // test with val_loss and  2 digit epoch and an added log variable
    logs.put("acc", 0.21);
    logs.put("val_loss", 0.99);
    result = PathPlaceholderStringFormat.convertFilePath(filePath, 12, logs);
    expect = "weights.12-0.99.hdf5";
    assertEquals(expect, result);

    // test with empty logs variable
    logs.clear();
    result = PathPlaceholderStringFormat.convertFilePath(filePath, 123, logs);
    expect = "weights.123-0.00.hdf5";
    assertEquals(expect, result);

    // test with no formatting
    filePath = "weights.hdf5";
    result = PathPlaceholderStringFormat.convertFilePath(filePath, 0, logs);
    expect = "weights.hdf5";
    assertEquals(expect, result);
  }
}
