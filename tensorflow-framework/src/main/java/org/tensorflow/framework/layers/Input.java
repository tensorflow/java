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
package org.tensorflow.framework.layers;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.util.Collections;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Layer that handles model input.
 *
 * @param <T> the data type for the layer's calculations.
 */
public class Input<T extends TNumber> extends Layer<T> {

  private final Class<? extends TType> inputType;
  private final boolean placeholder;
  private final Operand<? extends TType> output;

  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param tf the TensorFlow Ops.
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   */
  public Input(Ops tf, Operand<? extends TType> input, Class<T> type) {

    this(tf, null, input, null, type, null);
  }


  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param tf the TensorFlow Ops.
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   * @param options the Layer options
   */
  public Input(Ops tf, Operand<? extends TType> input, Class<T> type, Options options) {

    this(tf, null, input, null, type, options);
  }

  /**
   * Creates an input layer.
   *
   * @param tf the TensorFlow Op
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   */
  public Input(
          Ops tf, String name, Operand<? extends TType> input, Class<T> type) {

    this(tf, name, input, null, type, null);
  }

  /**
   * Creates an input layer.
   *
   * @param tf the TensorFlow Op
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   */
  public Input(
      Ops tf, String name, Operand<? extends TType> input, Class<T> type, Options options) {

    this(tf, name, input, null, type, options);
  }

  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param tf the TensorFlow Ops, before the first call to the {@link #call} method method is
   *     called.
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   */
  public Input(Ops tf, Class<? extends TType> inputType, Class<T> type) {
    this(tf, null, null, inputType, type, null);
  }

  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param tf the TensorFlow Ops, before the first call to the {@link #call} method method is
   *     called.
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options, may be null
   */
  public Input(Ops tf, Class<? extends TType> inputType, Class<T> type, Options options) {
    this(tf, null, null, inputType, type, options);
  }

  /**
   * Creates an input layer.
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   */
  public Input(
          Ops tf, String name, Class<? extends TType> inputType, Class<T> type) {
    this(tf, name, null, inputType, type, null);
  }

  /**
   * Creates an input layer.
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options, may be null
   */
  public Input(
      Ops tf, String name, Class<? extends TType> inputType, Class<T> type, Options options) {
    this(tf, name, null, inputType, type, options);
  }

  /**
   * Creates an input layer
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param input The input
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   * @throws IllegalArgumentException if inputShape and either batchSize or batchInputShape are not
   *     null, and if both inputShape and input are null.
   */
  public Input(
          Ops tf,
          String name,
          Operand<? extends TType> input,
          Class<? extends TType> inputType,
          Class<T> type) {
    this(tf, name, input, inputType, type, null);
  }
  /**
   * Creates an input layer
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param input The input
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options, may be null
   * @throws IllegalArgumentException if inputShape and either batchSize or batchInputShape are not
   *     null, and if both inputShape and input are null.
   */
  public Input(
      Ops tf,
      String name,
      Operand<? extends TType> input,
      Class<? extends TType> inputType,
      Class<T> type,
      Options options) {
    super(tf, name, true, type, options);
    Options c = getInstanceOptions();

    if (inputType == null && input == null) {
      throw new IllegalArgumentException("both input and inputType cannot be null");
      }

    if (input != null && inputType != null && !input.type().equals(inputType)) {
      throw new IllegalArgumentException(
          String.format("input.type() differs from inputType: %s vs. %s", input.type(), inputType));
      }

    //if ((c == null || c.inputShape == null) && input == null) {
    //  throw new IllegalArgumentException("both input and inputShape cannot be null");
    //  }

    if (c != null) {
      if ( c.inputShape != null
          && (c.batchSize != null || c.batchInputShape != null)) {
        throw new IllegalArgumentException(
            "Only provide the inputShape or the batchSize or batchInputShape parameters at the size.");
      }
    }

    Shape lShape;

    if (c != null && c.batchInputShape != null) {
      lShape = c.batchInputShape.takeLast(c.batchInputShape.numDimensions() - 1);
      setBatchInputShape(c.batchInputShape);
      if (getBatchSize() == null) {
        setBatchSize(c.batchInputShape.size(0));
      }
    } else {
        if(input == null) {
          lShape = (c == null || c.inputShape == null) ? Shape.of(Shape.UNKNOWN_SIZE) : c.inputShape;
        }else {
          lShape = (c == null || c.inputShape == null) ? input.shape() : c.inputShape;
        }

      setBatchSize((c == null || c.batchSize == null) ? Shape.UNKNOWN_SIZE : c.batchSize);

      setBatchInputShape(Shape.of(getBatchSize()).append(lShape));
    }
    setInputShape(lShape);

    this.inputType = inputType == null ? input.type() : inputType;
    super.build(lShape);
    if (input != null) {
      output = input;
      placeholder = false;
    } else {
      output = getTF().placeholder(this.inputType, Placeholder.shape(getBatchInputShape()));
      placeholder = true;
    }
  }

  /**
   * Gets the input Operand. This is a convenience method to create the input for a Model.
   *
   * @param tf the TensorFlow Ops.
   * @param type the data type for the layer's weights and computation.
   * @param <T> the data type for the layer's calculations.
   * @return the output
   */
  public static <T extends TNumber> Operand<T> input(
          Ops tf, Class<T> type) {
    return input(tf, type, null);
  }

  /**
   * Gets the input Operand. This is a convenience method to create the input for a Model.
   *
   * @param tf the TensorFlow Ops.
   * @param type the data type for the layer's weights and computation.
   * @param options the Layer options
   * @param <T> the data type for the layer's calculations.
   * @return the output
   */
  public static <T extends TNumber> Operand<T> input(
      Ops tf, Class<T> type, Options options) {
    Input<T> layer = new Input<>(tf, type, type, options);
    return layer.getOutput(type);
  }

  /**
   * Gets the input Operand. This is a convenience method to create the input for a Model.
   *
   * @param tf the TensorFlow Ops.
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   * @param options the Layer options
   * @param <T> the data type for the layer's calculations.
   * @return the output
   */
  public static <T extends TFloating> Operand<? extends TType> input(
      Ops tf, Operand<? extends TType> input, Class<T> type, Options options) {
    Input<T> layer = new Input<>(tf, input, type, options);
    return layer.getOutput();
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    return callPostProcess(Collections.singletonList(getOutput(resultType)), training);
  }

  /**
   * Gets the output Operand.
   *
   * <p>Note: a calling class should call this method directly, rather than calling one of the
   * {@link #call} methods
   *
   * @return the output Operand.
   */
  public Operand<? extends TType> getOutput() {
    return output;
  }

  /**
   * Gets the output Operand.
   *
   * <p>Note: a calling class should call this method directly, rather than calling one of the
   * {@link #call} methods
   *
   * @param resultType the output data type
   * @return the output Operand.
   */
  public <U extends TType> Operand<U> getOutput(Class<U> resultType) {

    return cast(getTF(), output, resultType);
  }

  /**
   * Identifies whether the output is a placeholder or not.
   *
   * @return true, if the output represents a placeholder
   */
  public boolean isPlaceholder() {
    return placeholder;
  }

  /**
   * The data type expected by the input.
   *
   * @return The data type expected by the input.
   */
  public Class<? extends TType> getInputType() {
    return inputType;
  }
}
