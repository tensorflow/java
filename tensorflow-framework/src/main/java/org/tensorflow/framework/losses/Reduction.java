package org.tensorflow.framework.losses;

/**
 * Type of Loss Reduction
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
