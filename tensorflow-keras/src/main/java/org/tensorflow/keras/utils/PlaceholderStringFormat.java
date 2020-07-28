/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.keras.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Utility that handles Python style placholder formats, used in Callbacks */
public class PlaceholderStringFormat {
  private static final Pattern PYTHON_MATCH = Pattern.compile("\\{(\\w+):([\\w\\.]+)\\}");

  /**
   * Convert a Python style placeholder file path format to file path
   *
   * @param filename the placeholder file path format
   * @param epoch the epoch number to place into the filename format
   * @param logs a Dictionary of log values that are replaced into the filename format
   * @return the resulting filename after applying all placeholders.
   */
  public static String convertFilePath(String filename, int epoch, Map<String, Number> logs) {
    List<String> vars = new ArrayList<>();
    Map<String, Number> valMap = new HashMap<>();
    String format = getFilePath(filename, vars);
    List<Number> objects = new ArrayList<>();
    vars.forEach(
        key -> {
          if (key.equals("epoch")) objects.add(epoch);
          else objects.add((Number) logs.get(key));
        });
    return String.format(format, objects.toArray(new Number[objects.size()]));
  }

  /**
   * Get the variable identifiers from the placeholder filename string
   *
   * @param filename the filename name in Placeholder format.
   * @param vars the list of variables from the caller, with added variables parsed from the
   *     filename
   * @return a string in String.format style
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
      sb.append(filename.substring(beginIndex, start));
      sb.append('%').append(index).append('$').append(format);
      beginIndex = end;
    }
    sb.append(filename.substring(beginIndex));
    return sb.toString();
  }
}
