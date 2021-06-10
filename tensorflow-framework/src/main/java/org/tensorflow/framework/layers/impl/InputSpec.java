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
package org.tensorflow.framework.layers.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.family.TType;

/**
 * Specifies the rank, data type and shape of every input to a layer.
 *
 * <p>These objects enable the layer to run input compatibility checks for input structure, input
 * rank, input shape, and input data type.
 *
 * <p>A {@link Shape#UNKNOWN_SIZE} entry in a shape is compatible with any dimension, a {@link
 * Shape#unknown()} shape is compatible with any shape.
 */
public class InputSpec {
  private Class<? extends TType> dataType;
  private Shape shape;
  private Integer rank;
  private Integer maxRank;
  private Integer minRank;
  private Map<Integer, Long> axes;
  private boolean allowLastAxisSqueeze;

  /** Creates an empty InputSpec. */
  public InputSpec() {}

  /**
   * Creates an InputSpec.
   *
   * @param options the options used to set the input spec.
   */
  public InputSpec(Options options) {
    dataType = options.dataType;
    rank = options.rank;
    maxRank = options.maxRank;
    minRank = options.minRank;
    axes = options.axes;
    allowLastAxisSqueeze = options.allowLastAxisSqueeze;

    if (options.shape != null && options.shape.numDimensions() != Shape.UNKNOWN_SIZE) {
      shape = options.shape;
      rank = shape.numDimensions();
    }

    if (axes != null && (rank != null || maxRank != null)) {
      maxRank = rank != null ? rank : maxRank;

      Integer maxAxis = axes.keySet().stream().max(Long::compare).get();
      if (maxAxis >= maxRank) {
        throw new IllegalArgumentException(
            String.format(
                "Axis %d is greater than the maximum allowed value: %d, %s",
                maxAxis, maxRank, shape));
      }
    }
  }

  /**
   * Returns a Shape object that matches the shape specifications.
   *
   * <p>If the InputSpec's {@link #shape} or expected {@link #rank} is defined, this method will
   * return a fully or partially-known shape. Otherwise, the returned Shape is {@link
   * Shape#unknown()}.
   *
   * @return the generated shape
   */
  public Shape toShape() {
    if (rank == null && shape == null) {
      return Shape.unknown();
    } else if (shape != null) {
      return shape;
    } else {
      long[] dims = new long[rank];
      Arrays.fill(dims, Shape.UNKNOWN_SIZE);
      if (axes != null) {
        for (Integer key : axes.keySet()) {
          int dimIdx = Math.floorMod(key, rank);
          dims[dimIdx] = axes.get(key);
        }
      }
      return Shape.of(dims);
    }
  }

  /**
   * Checks compatibility between the layer and provided inputs.
   *
   * @param input the input to check.
   * @param layerName layer name for error message formatting.
   * @param <T> the data type for the input.
   * @throws IllegalArgumentException if the provided input's shape is not compatible wiht this
   *     InputSpec.
   */
  public <T extends TType> void assertInputCompatibility(Operand<T> input, String layerName) {
    Shape staticShape = input.shape();

    if (staticShape.numDimensions() != Shape.UNKNOWN_SIZE) {
      if (rank != null && !isAllowLastAxisSqueeze()) {
        if (staticShape.numDimensions() != rank) {
          throw new IllegalArgumentException(
              String.format(
                  "Input of %s is incompatible with the layer: expected rank=%d, , found rank=%d. . Full shape received: %s",
                  layerName, rank, staticShape.numDimensions(), staticShape));
        }
      }
      if (maxRank != null) {
        if (staticShape.numDimensions() > maxRank) {
          throw new IllegalArgumentException(
              String.format(
                  "Input of %s is incompatible with the layer: expected max rank =%d, , found rank = %d.",
                  layerName, maxRank, staticShape.numDimensions()));
        }
      }
      if (minRank != null) {
        if (staticShape.numDimensions() < minRank) {
          throw new IllegalArgumentException(
              String.format(
                  "Input of %s is incompatible with the layer: expected min rank =%d, found rank = %d.",
                  layerName, minRank, staticShape.numDimensions()));
        }
      }

      if (dataType != null && !dataType.equals(input.type())) {
        throw new IllegalArgumentException(
            String.format(
                "Input of %s is incompatible with the layer: expected data type = %s, found data type = %s.",
                layerName, dataType.getSimpleName(), input.type().getSimpleName()));
      }

      // check each axis
      if (axes != null) {
        axes.forEach(
            (x, v) -> {
              if (shape.size(x) != Shape.UNKNOWN_SIZE && shape.size(x) != v) {
                throw new IllegalArgumentException(
                    String.format(
                        "Input of %s is incompatible with the layer: expected axis  = %d of input shape to have value %d,  but received input with shape %s",
                        layerName, x, v, staticShape));
              }
            });
      }

      // Check shape.
      if (shape != null) {
        Shape specShape = shape;
        Shape inputShape = staticShape;
        if (isAllowLastAxisSqueeze()) {
          if (inputShape.size(inputShape.numDimensions() - 1) == 1) {
            inputShape = inputShape.take(inputShape.numDimensions() - 1);
          }
          if (specShape.size(specShape.numDimensions() - 1) == 1) {
            specShape = specShape.take(specShape.numDimensions() - 1);
          }
        }
        for (int i = 0; i < specShape.numDimensions(); i++) {
          if (specShape.size(i) != Shape.UNKNOWN_SIZE
              && inputShape.size(i) != Shape.UNKNOWN_SIZE
              && specShape.size(i) != inputShape.size(i)) {
            throw new IllegalArgumentException(
                String.format(
                    "Input of %s is incompatible with the layer:: expected shape= %s, found shape = %s",
                    layerName, shape, staticShape));
          }
        }
      }
    }
  }

  /**
   * Gets the expected Data Type of the input.
   *
   * @return the expected Data Type of the input.
   */
  public Class<? extends TType> getDataType() {
    return dataType;
  }

  /**
   * Sets the expected Data Type of the input.
   *
   * @param dataType the expected Data Type of the input.
   */
  public void setDataType(Class<? extends TType> dataType) {
    this.dataType = dataType;
  }

  /**
   * Gets the Dictionary mapping integer axes to a specific dimension value.
   *
   * @return the Dictionary mapping integer axes to a specific dimension value.
   */
  public Map<Integer, Long> getAxesMap() {
    return axes;
  }

  /**
   * Sets the Dictionary mapping integer axes to a specific dimension value.
   *
   * @param axes the Dictionary mapping integer axes to a specific dimension value.
   */
  public void setAxesMap(Map<Integer, Long> axes) {
    this.axes = axes;
  }

  /**
   * Gets the expected shape of the input (may include {@link Shape#UNKNOWN_SIZE} for unchecked
   * axes). Includes the batch size.
   *
   * @return the expected shape of the input including batch size.
   */
  public Shape getShape() {
    return shape;
  }

  /**
   * Sets the expected shape of the input (may include {@link Shape#UNKNOWN_SIZE} for unchecked
   * axes). Includes the batch size.
   *
   * @param shape the expected shape of the input including batch size.
   */
  public void setShape(Shape shape) {
    this.shape = shape;
  }

  /**
   * Gets the expected rank of the input
   *
   * @return the expected rank of the input
   */
  public Integer getRank() {
    return rank;
  }

  /**
   * Sets the expected rank of the input
   *
   * @param rank the expected rank of the input
   */
  public void setRank(Integer rank) {
    this.rank = rank;
  }

  /**
   * Gets the maximum rank of the input.
   *
   * @return the maximum rank of the input.
   */
  public Integer getMaxRank() {
    return maxRank;
  }

  /**
   * Sets the maximum rank of the input.
   *
   * @param maxRank he maximum rank of the input.
   */
  public void setMaxRank(Integer maxRank) {
    this.maxRank = maxRank;
  }

  /**
   * Gets the minimum rank of the input.
   *
   * @return the minimum rank of the input.
   */
  public Integer getMinRank() {
    return minRank;
  }

  /**
   * Sets the minimum rank of the input.
   *
   * @param minRank he maximum rank of the input.
   */
  public void setMinRank(Integer minRank) {
    this.minRank = minRank;
  }

  /**
   * Gets the allow last axis squeeze indicator for the input. If true, then allow inputs of rank
   * N+1 as long as the last axis of the input is 1, as well as inputs of rank N-1 as long as the
   * last axis of the spec is 1.
   *
   * @return the allow last axis squeeze indicator
   */
  public boolean isAllowLastAxisSqueeze() {
    return allowLastAxisSqueeze;
  }

  /**
   * Sets the allow last axis squeeze indicator for the input. If true, then allow inputs of rank
   * N+1 as long as the last axis of the input is 1, as well as inputs of rank N-1 as long as the
   * last axis of the spec is 1.
   *
   * @param allowLastAxisSqueeze the allow last axis squeeze indicator
   */
  public void setAllowLastAxisSqueeze(boolean allowLastAxisSqueeze) {
    this.allowLastAxisSqueeze = allowLastAxisSqueeze;
  }

  /** Optional attributes for {@link InputSpec} */
  public static class Options {

    private Class<? extends TType> dataType;
    private Shape shape;
    private Integer rank;
    private Integer maxRank;
    private Integer minRank;
    private Map<Integer, Long> axes;
    private boolean allowLastAxisSqueeze;

    /**
     * Creates an InputSpecs.Options instance
     *
     * @return the InputSpecs.Options instance
     */
    public static Options create() {
      return new Options();
    }

    /**
     * Sets the expected Data Type of the input.
     *
     * @param dataType the expected Data Type of the input.
     * @return this Options instance.
     */
    public Options dataType(Class<? extends TType> dataType) {
      this.dataType = dataType;
      return this;
    }

    /**
     * Sets the expected shape of the input (may include {@link Shape#UNKNOWN_SIZE} for unchecked
     * axes). Includes the batch size.
     *
     * @param shape the expected shape of the input
     * @return this Options instance.
     */
    public Options shape(Shape shape) {
      this.shape = shape;
      return this;
    }

    /**
     * Sets the expected rank of the input
     *
     * @param rank the expected rank of the input
     * @return this Options instance.
     */
    public Options rank(Integer rank) {
      this.rank = rank;
      return this;
    }

    /**
     * Sets the maximum rank of the input.
     *
     * @param maxRank the maximum rank of the input.
     * @return this Options instance.
     */
    public Options maxRank(Integer maxRank) {
      this.maxRank = maxRank;
      return this;
    }

    /**
     * Sets the minimum rank of the input.
     *
     * @param minRank the minimum rank of the input.
     * @return this Options instance.
     */
    public Options minRank(Integer minRank) {
      this.minRank = minRank;
      return this;
    }
    /**
     * Sets the Dictionary mapping integer axes to a specific dimension value.
     *
     * @param axes the Dictionary mapping integer axes to a specific dimension value.
     * @return this Options instance.
     */
    public Options axesMap(Map<Integer, Long> axes) {
      this.axes = axes;
      return this;
    }
    /**
     * Sets the Dictionary mapping integer axes to a specific dimension value.
     *
     * @param key the integer axis
     * @param dim the dimension value for the specified axis
     * @return this Options instance.
     */
    public Options axesMap(Integer key, Long dim) {
      if (this.axes == null) {
        this.axes = new HashMap<>();
      }
      this.axes.put(key, dim);
      return this;
    }

    /**
     * Sets the allow last axis squeeze indicator for the input. If true, then allow inputs of rank
     * N+1 as long as the last axis of the input is 1, as well as inputs of rank N-1 as long as the
     * last axis of the spec is 1.
     *
     * @param allowLastAxisSqueeze indicator that the allow last axis squeeze indicator for the
     *     input
     * @return this Options instance.
     */
    public Options allowLastAxisSqueeze(boolean allowLastAxisSqueeze) {
      this.allowLastAxisSqueeze = allowLastAxisSqueeze;
      return this;
    }
    /**
     * Gets the expected Data Type of the input.
     *
     * @return the expected Data Type of the input.
     */
    public Class<? extends TType> getDataType() {
      return dataType;
    }
    /**
     * Gets the expected shape of the input (may include {@link Shape#UNKNOWN_SIZE} for unchecked
     * axes). Includes the batch size.
     *
     * @return the expected shape of the input including batch size.
     */
    public Shape getShape() {
      return shape;
    }
    /**
     * Gets the expected rank of the input
     *
     * @return the expected rank of the input
     */
    public Integer getRank() {
      return rank;
    }
    /**
     * Gets the maximum rank of the input.
     *
     * @return the maximum rank of the input.
     */
    public Integer getMaxRank() {
      return maxRank;
    }
    /**
     * Gets the minimum rank of the input.
     *
     * @return the minimum rank of the input.
     */
    public Integer getMinRank() {
      return minRank;
    }

    /**
     * Gets the Dictionary mapping integer axes to a specific dimension value.
     *
     * @return the Dictionary mapping integer axes to a specific dimension value.
     */
    public Map<Integer, Long> getAxesMap() {
      return axes;
    }
    /**
     * Gets the allow last axis squeeze indicator for the input. If true, then allow inputs of rank
     * N+1 as long as the last axis of the input is 1, as well as inputs of rank N-1 as long as the
     * last axis of the spec is 1.
     *
     * @return the allow last axis squeeze indicator
     */
    public boolean isAllowLastAxisSqueeze() {
      return allowLastAxisSqueeze;
    }
  }
}
