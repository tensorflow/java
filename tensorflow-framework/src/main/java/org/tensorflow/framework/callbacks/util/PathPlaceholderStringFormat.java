/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.callbacks.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * These are utilities for generating a file path from an original file path containing named
 * formatting options.
 */
public class PathPlaceholderStringFormat {
  private static final Pattern PYTHON_MATCH = Pattern.compile("\\{(\\w+):([\\w.]+)}");

  /**
   * Converts a filepath containing named formatting options, which will be filled with the value of
   * <code>epoch</code> and keys in <code>logs</code> (passed in <code>onEpochEnd</code>).
   *
   * <p>For example:
   *
   * <p>if <code>filepath</code> is * <code>weights.{epoch:02d}-{val_loss:.2f}.hdf5</code>, then the
   * model checkpoints will be saved with the epoch number and the validation loss in the filename
   * (e.g. <code>"weights.561-0.71.hdf5"</code>).
   *
   * @param filename the filename containing the formatting options
   * @param epoch the epoch
   * @param logs the logs map that contain the values
   * @return the converted file path name
   */
  public static String convertFilePath(String filename, int epoch, Map<String, Number> logs) {
    List<String> vars = new ArrayList<>();
    String format = getFilePath(filename, vars);
    List<Number> values = new ArrayList<>();
    vars.forEach(
        key -> {
          if (key.equals("epoch")) values.add(epoch);
          else if (logs.containsKey(key)) values.add(logs.get(key).doubleValue());
          else values.add(0.0);
        });
    return String.format(format, values.toArray());
  }

  /**
   * Creates a {@link String#format} string for formatting the filepath for including the log values
   * identified by the original filepath placeholder names
   *
   * @param filename the filename with the placeholders.
   * @param vars the list is populated with the log names from the placeholder names found in the
   *     original file path string that will be included in resulting name
   * @return the String format for formatting the values identified from the placeholder names.
   */
  private static String getFilePath(String filename, List<String> vars) {
    Matcher m = PYTHON_MATCH.matcher(filename);
    StringBuilder sb = new StringBuilder();
    int beginIndex = 0;
    Map<String, Integer> indexMap = new HashMap<>();
    int lastIndex = 1;
    while (m.find()) {
      int start = m.start(0);
      int end = m.end(0);
      String variable = m.group(1);
      vars.add(variable);
      String format = m.group(2);
      Integer index = indexMap.get(variable);
      if (index == null) {
        indexMap.put(variable, lastIndex);
        index = lastIndex++;
      }
      sb.append(filename, beginIndex, start);
      sb.append('%').append(index).append('$').append(format);
      beginIndex = end;
    }
    sb.append(filename.substring(beginIndex));
    return sb.toString();
  }
}
