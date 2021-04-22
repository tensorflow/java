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
package org.tensorflow.framework.metrics;

/**
 * Specifies the Riemann summation method used.
 *
 * @see <a href="https://www.biostat.wisc.edu/~page/rocpr.pdf">Davis &amp; Goadrich. 2006</a>
 * @see <a href="https://en.wikipedia.org/wiki/Riemann_sum">Riemann summation method</a>
 */
public enum AUCSummationMethod {
  /**
   * Apply mid-point summation scheme for {@link AUCCurve#ROC}. For {@link AUCCurve#PR},
   * interpolates (true/false) positives but not the ratio that is precision
   */
  INTERPOLATION,
  /** Apply right summation for increasing intervals and left summation for decreasing intervals */
  MAJORING,
  /** Apply left summation for increasing intervals and right summation for decreasing intervals */
  MINORING;

  /**
   * Gets the AUCSummationMethod enum value by name, regardless of case
   *
   * @param name the name of the AUCSummationMethod enum value.
   * @return the AUCSummationMethod enum value.
   */
  public AUCSummationMethod get(String name) {
    return AUCSummationMethod.valueOf(name.toUpperCase());
  }
}
