/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.data;

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * An optional represents the result of a dataset getNext operation that may fail, when the end of
 * the dataset has been reached.
 */
public class DatasetOptional {
  protected Ops tf;

  /**
   * Gets the optional variant for this Dataset
   *
   * @return the optional variant for this Dataset
   */
  public Operand<?> getOptionalVariant() {
    return optionalVariant;
  }

  private Operand<?> optionalVariant;
  private List<Class<? extends TType>> outputTypes;
  private List<Shape> outputShapes;

  /**
   * Creates a DatasetOptional dataset
   *
   * @param tf the TensorFlow Ops
   * @param optionalVariant the tensor that represents the dataset.
   * @param outputTypes a list of output types produced by this data set.
   * @param outputShapes a list of output shapes produced by this data set.
   */
  public DatasetOptional(
      Ops tf,
      Operand<?> optionalVariant,
      List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    this.tf = tf;
    this.optionalVariant = optionalVariant;
    this.outputTypes = outputTypes;
    this.outputShapes = outputShapes;
  }

  /**
   * Creates a Dataset that is a copy of another Dataset
   *
   * @param other the other Dataset
   */
  protected DatasetOptional(DatasetOptional other) {
    this.tf = other.tf;
    this.optionalVariant = other.optionalVariant;
    this.outputTypes = other.outputTypes;
    this.outputShapes = other.outputShapes;
  }

  /**
   * Gets the indicator of whether this optional has a value.
   *
   * @return the indicator of whether this optional has a value.
   */
  public Operand<TBool> hasValue() {
    return tf.data.optionalHasValue(optionalVariant).hasValue();
  }

  /**
   * Returns the value of the dataset element represented by this optional, if it exists.
   *
   * @return the value of the dataset element represented by this optional, if it exists.
   */
  public List<Operand<?>> getValue() {
    List<Operand<?>> components = new ArrayList<>();
    tf.data
        .optionalGetValue(optionalVariant, outputTypes, outputShapes)
        .iterator()
        .forEachRemaining(components::add);

    return components;
  }

  /**
   * Creates a DatasetOptional from components.
   *
   * @param tf the TensorFlow Ops
   * @param components the components that constitute the DatasetOptional
   * @param outputTypes a list of output types produced by this data set.
   * @param outputShapes a list of output shapes produced by this data set.
   * @return a a DatasetOptional
   */
  public static DatasetOptional fromComponents(
      Ops tf,
      List<Operand<?>> components,
      List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    Operand<?> optionalVariant = tf.data.optionalFromValue(components);
    return new DatasetOptional(tf, optionalVariant, outputTypes, outputShapes);
  }

  /**
   * Gets the TensorFlow Ops instance for this dataset
   *
   * @return the TensorFlow Ops instance for this dataset
   */
  public Ops getOpsInstance() {
    return tf;
  }
}
