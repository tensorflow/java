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
package org.tensorflow.framework.losses;

/**
 * Type of AbstractLoss Reduction
 *
 * <p>{@link #AUTO} indicates that the reduction option will be determined by the usage context. For
 * almost all cases this defaults to {@link #SUM_OVER_BATCH_SIZE}.
 *
 * <p>{@link #NONE} Weighted losses with one dimension reduced (axis=-1, or axis specified by loss
 * function).
 *
 * <p>{@link #SUM} Scalar sum of weighted losses.
 *
 * <p>{@link #SUM_OVER_BATCH_SIZE} Scalar <code>SUM</code> divided by number of elements in losses.
 */
public enum Reduction {
  AUTO,
  NONE,
  SUM,
  SUM_OVER_BATCH_SIZE;

  /**
   * Get the Reduction based on name
   *
   * @param name the name of the reduction
   * @return the Reduction
   */
  public static Reduction ofName(String name) {
    return Reduction.valueOf(name.toUpperCase());
  }
}
