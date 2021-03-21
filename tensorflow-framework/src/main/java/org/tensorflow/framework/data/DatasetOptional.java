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

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.List;

/**
 * An optional represents the result of a dataset getNext operation that may fail, when the end of
 * the dataset has been reached.
 */
public class DatasetOptional {
  protected Ops tf;
  private final Operand<?> optionalVariant;
  private final List<Class<? extends TType>> outputTypes;
  private final List<Shape> outputShapes;
  /**
   * Creates a DatasetOptional
   *
   * @param tf the TensorFlow Ops
   * @param optionalVariant the optional Operand that represents the dataset.
   * @param outputTypes A list of classes corresponding to the tensor type of each component of a
   *     dataset element.
   * @param outputShapes A list of `Shape` objects corresponding to the shapes of each component of
   *     a dataset element.
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
   * Creates a DatasetOptional from another DatasetOptional
   *
   * @param other the other DatasetOptional
   */
  protected DatasetOptional(DatasetOptional other) {
    this.tf = other.tf;
    this.optionalVariant = other.optionalVariant;
    this.outputTypes = other.outputTypes;
    this.outputShapes = other.outputShapes;
  }

  /**
   * Creates a DatasetOptional from a list of components
   *
   * @param tf the TensorFlow Ops
   * @param components the components
   * @param outputTypes A list of classes corresponding to the tensor type of each component of a
   *     dataset element.
   * @param outputShapes A list of `Shape` objects corresponding to the shapes of each component of
   *     a dataset element.
   * @return a DatasetOptional initialized with the components
   */
  public static DatasetOptional fromComponents(
      Ops tf,
      List<Operand<?>> components,
      List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    Operand<?> optionalVariant = tf.data.optionalFromValue(components);
    return new DatasetOptional(tf, optionalVariant, outputTypes, outputShapes);
  }

  public Operand<?> getOptionalVariant() {
    return optionalVariant;
  }

  /**
   * Gets the operand indicating whether this optional has a value.
   *
   * @return the operand indicating whether this optional has a value.
   */
  public Operand<TBool> hasValue() {
    return tf.data.optionalHasValue(optionalVariant).hasValue();
  }

  /**
   * Gets the value of the dataset element represented by this optional, if it exists.
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

  public Ops getOpsInstance() {
    return tf;
  }
}
