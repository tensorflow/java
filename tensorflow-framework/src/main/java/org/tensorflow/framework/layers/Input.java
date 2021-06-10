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

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.Collections;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Layer that handles model input.
 *
 * @param <T> the data type for the layer's calculations.
 */
public class Input<T extends TNumber> extends Layer<T> {

  private final Class<? extends TType> inputType;
  private boolean placeholder;
  private Operand<? extends TType> output;
  private final Operand<? extends TType> input;

  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   */
  public Input(Operand<? extends TType> input, Class<T> type) {

    this(null, input, null, type, null);
  }

  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   * @param options the Layer options
   */
  public Input(Operand<? extends TType> input, Class<T> type, Options options) {

    this(null, input, null, type, options);
  }

  /**
   * Creates an input layer.
   *
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   */
  public Input(String name, Operand<? extends TType> input, Class<T> type) {

    this(name, input, null, type, null);
  }

  /**
   * Creates an input layer.
   *
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options
   */
  public Input(String name, Operand<? extends TType> input, Class<T> type, Options options) {

    this(name, input, null, type, options);
  }

  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   */
  public Input(Class<? extends TType> inputType, Class<T> type) {
    this(null, null, inputType, type, null);
  }

  /**
   * Creates an input layer using {@link Class#getSimpleName()} for the name.
   *
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options, may be null
   */
  public Input(Class<? extends TType> inputType, Class<T> type, Options options) {
    this(null, null, inputType, type, options);
  }

  /**
   * Creates an input layer.
   *
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   */
  public Input(String name, Class<? extends TType> inputType, Class<T> type) {
    this(name, null, inputType, type, null);
  }

  /**
   * Creates an input layer.
   *
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options, may be null
   */
  public Input(String name, Class<? extends TType> inputType, Class<T> type, Options options) {
    this(name, null, inputType, type, options);
  }

  /**
   * Creates an input layer
   *
   * @param name the unique name for this layer, if null, will generate a name based on {@link
   *     Class#getSimpleName()}
   * @param input The input
   * @param inputType the data type for the input and output, if null, input.type() is used
   * @param type the data type for the layer's weights and computation.
   * @throws IllegalArgumentException if inputShape and either batchSize or batchInputShape are not
   *     null, and if both inputShape and input are null.
   */
  public Input(
      String name,
      Operand<? extends TType> input,
      Class<? extends TType> inputType,
      Class<T> type) {
    this(name, input, inputType, type, null);
  }
  /**
   * Creates an input layer
   *
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
      String name,
      Operand<? extends TType> input,
      Class<? extends TType> inputType,
      Class<T> type,
      Options options) {
    super(name, true, type, options);
    Options inputOptions = getInstanceOptions();

    if (inputType == null && input == null) {
      throw new IllegalArgumentException("both input and inputType cannot be null");
    }

    if (input != null && inputType != null && !input.type().equals(inputType)) {
      throw new IllegalArgumentException(
          String.format("input.type() differs from inputType: %s vs. %s", input.type(), inputType));
    }

    if (inputOptions != null) {
      if (inputOptions.inputShape != null
          && (inputOptions.batchSize != null || inputOptions.batchInputShape != null)) {
        throw new IllegalArgumentException(
            "Only provide the inputShape or the batchSize or batchInputShape parameters at the size.");
      }
    }

    Shape lShape;

    if (inputOptions != null && inputOptions.batchInputShape != null) {
      lShape =
          inputOptions.batchInputShape.takeLast(inputOptions.batchInputShape.numDimensions() - 1);
      setBatchInputShape(inputOptions.batchInputShape);
      if (getBatchSize() == null) {
        setBatchSize(inputOptions.batchInputShape.size(0));
      }
    } else {
      if (input == null) {
        lShape =
            (inputOptions == null || inputOptions.inputShape == null)
                ? Shape.of(Shape.UNKNOWN_SIZE)
                : inputOptions.inputShape;
      } else {
        lShape =
            (inputOptions == null || inputOptions.inputShape == null)
                ? input.shape()
                : inputOptions.inputShape;
      }

      setBatchSize(
          (inputOptions == null || inputOptions.batchSize == null)
              ? Shape.UNKNOWN_SIZE
              : inputOptions.batchSize);

      setBatchInputShape(Shape.of(getBatchSize()).append(lShape));
    }
    setInputShape(lShape);

    this.inputType = inputType == null ? input.type() : inputType;
    this.input = input;
  }

  public Ops init(Ops tf) {
    super.init(tf);
    super.build(super.getInputShape());
    if (input != null) {
      output = input;
      placeholder = false;
    } else {
      output = getTF().placeholder(this.inputType, Placeholder.shape(getBatchInputShape()));
      placeholder = true;
    }
    return getTF();
  }

  /**
   * Gets the input Operand. This is a convenience method to create the input for a Model.
   *
   * @param tf the TensorFlow Ops.
   * @param type the data type for the layer's weights and computation.
   * @param <T> the data type for the layer's calculations.
   * @return the output
   */
  public static <T extends TNumber> Operand<T> input(Ops tf, Class<T> type) {
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
  public static <T extends TNumber> Operand<T> input(Ops tf, Class<T> type, Options options) {
    Input<T> layer = new Input<>(type, type, options);
    layer.init(tf);
    return layer.getOutput(type);
  }

  /**
   * Gets the input Operand. This is a convenience method to create the input for a Model.
   *
   * @param tf the TensorFlow Ops.
   * @param input The input
   * @param type the data type for the layer's weights and computation.
   * @param <T> the data type for the layer's calculations.
   * @return the output
   */
  public static <T extends TFloating> Operand<T> input(Ops tf, Operand<T> input, Class<T> type) {
    return input(tf, input, type, null);
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
  public static <T extends TFloating> Operand<T> input(
      Ops tf, Operand<T> input, Class<T> type, Options options) {
    Input<T> layer = new Input<>(input, type, options);
    layer.init(tf);
    return layer.getOutput(type);
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      Ops tf,
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    init(tf);
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
   * @param <U> the data type for the result
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
