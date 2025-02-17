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
package org.tensorflow.framework.callbacks;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.family.TNumber;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Callback that streams epoch results to a CSV file.
 *
 * <p>Supports all values that can be represented as a string
 *
 * @param <T> the data type for the weights in the model
 */
public class CSVLogger<T extends TNumber> extends Callback implements AutoCloseable {

  public static final char DEFAULT_SEPARATOR = ',';
  public static final boolean DEFAULT_APPEND = false;

  private final File file;
  private final char separator;
  private final boolean append;
  private List<String> keys;
  private boolean appendHeader = true;

  private CSVPrinter writer;

  /**
   * Creates a CSVLogger callback using {@link #DEFAULT_SEPARATOR} to separate elements in the csv
   * file, and {@link #DEFAULT_APPEND} for the append value.
   *
   * @param file the csv file
   */
  public CSVLogger(File file) {
    this(file, DEFAULT_SEPARATOR, DEFAULT_APPEND);
  }

  /**
   * Creates a CSVLogger callback using {@link #DEFAULT_SEPARATOR} to separate elements in the csv
   * file, and {@link #DEFAULT_APPEND} for the append value.
   *
   * @param filename filename of the csv file
   */
  public CSVLogger(String filename) {
    this(new File(filename), DEFAULT_SEPARATOR, DEFAULT_APPEND);
  }

  /**
   * Creates a CSVLogger callback using {@link #DEFAULT_APPEND} for the append value.
   *
   * @param file the csv file
   * @param separator string used to separate elements in the csv file.
   */
  public CSVLogger(File file, char separator) {
    this(file, separator, false);
  }

  /**
   * Creates a CSVLogger callback using {@link #DEFAULT_APPEND} for the append value.
   *
   * @param filename filename of the csv file
   * @param separator string used to separate elements in the csv file.
   */
  public CSVLogger(String filename, char separator) {
    this(new File(filename), separator, false);
  }

  /**
   * Creates a CSVLogger callback.
   *
   * @param filename filename of the csv file
   * @param separator the character used to separate elements in the csv file.
   * @param append if true, append if file exists (useful for continuing training). if false,
   *     overwrite existing file.
   */
  public CSVLogger(String filename, char separator, boolean append) {
    this(new File(filename), separator, append);
  }

  /**
   * Creates a CSVLogger callback.
   *
   * @param file the csv file
   * @param separator the character used to separate elements in the csv file.
   * @param append if true, append if file exists (useful for continuing training). if false,
   *     overwrite existing file.
   */
  public CSVLogger(File file, char separator, boolean append) {
    this.file = file;
    this.separator = separator;
    this.append = append;
  }

  /** {@inheritDoc} */
  @Override
  public void onTrainBegin(Map<String, Number> logs) {
    appendHeader = !append || !file.exists();
  }

  // TODO Should we handle Java arrays??
  @SuppressWarnings("unchecked")
  private String handleValue(Object val) {

    if (val instanceof String) {
      return val.toString();
    } else if (val instanceof NdArray) { // todo
      boolean isScalar = ((NdArray<?>) val).rank() == 0;
      if (isScalar) {
        return ((NdArray<?>) val).getObject().toString();
      } else {
        NdArray<?> array = (NdArray<T>) val;
        return ndArrayToString(array);
      }
    } else if (val instanceof Collection) {
      return "["
          + ((Collection<T>) val).stream().map(Object::toString).collect(Collectors.joining(","))
          + "]";
    } else {
      return val.toString();
    }
  }

  /**
   * coverts an NdArray to a printable string
   *
   * @param ndArray the NdArray
   * @return the printable string
   */
  private String ndArrayToString(NdArray<?> ndArray) {
    Iterator<? extends NdArray<?>> iterator = ndArray.scalars().iterator();
    Shape shape = ndArray.shape();
    if (shape.numDimensions() == 0) {
      if (!iterator.hasNext()) {
        return "";
      }
      return valToString(iterator.next().getObject());
    }
    return ndArrayToString(iterator, shape, 0);
  }

  /**
   * coverts an NdArray iterator to a printable string
   *
   * @param iterator the NdArray iterator
   * @param shape the shape of the NdArray item
   * @param dimension the dimension within the overall NDArray tree
   * @return the printable string
   */
  private String ndArrayToString(Iterator<? extends NdArray<?>> iterator, Shape shape, int dimension) {
    if (dimension < shape.numDimensions() - 1) {
      StringJoiner joiner = new StringJoiner("", "[", "]");
      for (long i = 0, size = shape.size(dimension); i < size; ++i) {
        String element = ndArrayToString(iterator, shape, dimension + 1);
        joiner.add(element);
      }
      return joiner.toString();
    } else {
      StringJoiner joiner = new StringJoiner(", ", "[", "]");
      for (long i = 0, size = shape.size(dimension); i < size; ++i) {
        Object element = iterator.next().getObject();
        joiner.add(valToString(element));
      }
      return joiner.toString();
    }
  }

  /**
   * Converts a value to a printable string
   *
   * @param val the value
   * @return the printable string
   */
  private String valToString(Object val) {
    if (val instanceof Number) {
      Number nVal = (Number) val;
      if (nVal instanceof Float || nVal instanceof Double) {
        return String.format("%e", nVal.doubleValue());
      } else if (nVal instanceof Byte) {
        return String.format("0x%2x", nVal.byteValue());
      } else {
        return String.format("%d", nVal.longValue());
      }
    } else {
      return val.toString();
    }
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public void onEpochEnd(int epoch, Map<String, Number> logs) {
    logs = logs == null ? Collections.EMPTY_MAP : logs;

    if (keys == null) {
      keys = new ArrayList<>(logs.keySet());
      Collections.sort(this.keys);
    }

    if (writer == null) {
      try {
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("epoch");
        fieldNames.addAll(this.keys);
        CSVFormat csvFormat =
            appendHeader
                ? CSVFormat.EXCEL
                    .withHeader(fieldNames.toArray(new String[0]))
                    .withDelimiter(separator)
                : CSVFormat.EXCEL.withDelimiter(separator);
        writer = new CSVPrinter(new FileWriter(file, append), csvFormat);
      } catch (IOException ex) {
        Logger.getLogger(CSVLogger.class.getName()).log(Level.SEVERE, null, ex);
        return;
      }
    }

    /* TODO include when integrated with Model
    if (getModel().isStopTraining()) {
      final Map<String, Number> flogs = logs;
      keys.forEach(
          key -> {
            if (!flogs.containsKey(key)) {
              flogs.put(key, Double.NaN);
            }
          });
    }
     */
    try {
      final List<String> values = new ArrayList<>();
      final Map<String, Number> logsFinal = logs;
      values.add(String.valueOf(epoch));
      keys.forEach(key -> values.add(handleValue(logsFinal.get(key))));
      writer.printRecord(values);
      writer.flush();
    } catch (IOException ex) {
      Logger.getLogger(CSVLogger.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws IOException {
    if (writer != null) {
      writer.close();
      writer = null;
    }
  }
}
