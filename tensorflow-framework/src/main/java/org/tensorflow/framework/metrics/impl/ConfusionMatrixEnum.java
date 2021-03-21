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
package org.tensorflow.framework.metrics.impl;

/** Enumerate the values for a confusion matrix. */
public enum ConfusionMatrixEnum {
  /** These are cases in which the prediction is true, and reality is true. */
  TRUE_POSITIVES("tp"),
  /** These are cases in which the prediction is true, and reality is false. */
  FALSE_POSITIVES("fp"),
  /** These are cases in which the prediction is false, and reality is false. */
  TRUE_NEGATIVES("tn"),
  /** These are cases in which the prediction is false, and reality is true. */
  FALSE_NEGATIVES("fn");

  private final String abbrev;

  /**
   * Creates a ConfusionMatrixEnum
   *
   * @param abbrev the abbreviation for the confusion condition as required by the underlying
   *     TensorFlow api.
   */
  ConfusionMatrixEnum(String abbrev) {
    this.abbrev = abbrev;
  }

  /**
   * Gets the ConfusionMatrixEnum for this enum value, regardless of case.
   *
   * @param item either the name of the enumeration value or the abbreviation.
   * @return ConfusionMatrixEnum for this enum value, or null if not found.
   */
  public static ConfusionMatrixEnum get(String item) {
    ConfusionMatrixEnum cm = valueOf(item.toUpperCase());
    if (cm == null) {
      for (ConfusionMatrixEnum m : values()) {
        if (m.getAbbreviation().equals(item.toLowerCase())) {
          return m;
        }
      }
    }
    return null;
  }

  /**
   * Gets the abbreviation for this enum value
   *
   * @return the abbreviation for this enum value as required by the underlying TensorFlow api.
   */
  public String getAbbreviation() {
    return abbrev;
  }
}
