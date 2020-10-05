package org.tensorflow.framework.losses;

/** Type of Loss Reduction */
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
