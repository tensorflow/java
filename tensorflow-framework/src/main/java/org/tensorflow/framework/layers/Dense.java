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
import org.tensorflow.framework.activations.Activation;
import org.tensorflow.framework.initializers.Glorot;
import org.tensorflow.framework.initializers.Initializer;
import org.tensorflow.framework.initializers.VarianceScaling;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.layers.impl.InputSpec;
import org.tensorflow.framework.layers.impl.VariableDef;
import org.tensorflow.framework.op.FrameworkOps;
import org.tensorflow.framework.regularizers.Regularizer;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * A regular densely-connected NN layer.
 *
 * <p><code>Dense</code> implements the operation: <code>
 * output = activation(dot(input, kernel) + bias)</code> where <code>activation</code> is the
 * element-wise activation function passed as the <code>activation</code> argument, <code>kernel
 * </code> is a weights matrix created by the layer, and <code>bias</code> is a bias vector created
 * by the layer (only applicable if <code>useBias</code> is <code>true</code>).
 *
 * <p><em>Note:</em> If the input to the layer has a rank greater than 2, then <code>Dense</code>
 * computes the dot product between the <code>inputs</code> and the <code>kernel</code> along the
 * last axis of the <code>inputs</code> and axis 1 of the <code>kernel</code> (using <code>
 * tf.tensordot</code>). For example, if input has dimensions <code>(batch_size, d0,
 * d1)</code>, then we create a <code>kernel</code> with shape <code>(d1, units)</code>, and the
 * <code>kernel</code> operates along axis 2 of the <code>input</code>, on every sub-tensor of shape
 * <code>(1, 1, d1)</code> (there are <code>batch_size * d0</code> such sub-tensors). The output in
 * this case will have shape <code>(batch_size, d0, units)</code>.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Dense<T extends TFloating> extends Layer<T> {

  private final Integer units;
  private final Activation<T> activation;
  private final boolean useBias;
  private final long seed;

  private final UnaryOperator<Operand<T>> kernelConstraint;
  private final UnaryOperator<Operand<T>> biasConstraint;
  private final Regularizer biasRegularizer;
  private final Regularizer kernelRegularizer;

  private Initializer<T> kernelInitializer;
  private Initializer<T> biasInitializer;
  private Variable<T> kernel;
  private Variable<T> bias;

  /**
   * Creates a Dense layer.
   *
   * @param tf the TensorFlow Ops.
   * @param units Positive integer, dimensionality of the output space.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public Dense(Ops tf, Integer units, long seed, Class<T> type) {
    this(tf, null, units, null, true, null, null, null, null, null, null, null, seed, type, null);
  }

  /**
   * Creates a Dense layer.
   *
   * @param tf the TensorFlow Ops.
   * @param units Positive integer, dimensionality of the output space.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public Dense(Ops tf, Integer units, long seed, Class<T> type, Options options) {
    this(
        tf, null, units, null, true, null, null, null, null, null, null, null, seed, type, options);
  }

  /**
   * Creates a Dense layer.
   *
   * @param tf the TensorFlow Ops.
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param units Positive integer, dimensionality of the output space.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public Dense(Ops tf, String name, Integer units, long seed, Class<T> type) {
    this(tf, name, units, null, true, null, null, null, null, null, null, null, seed, type, null);
  }

  /**
   * Creates a Dense layer.
   *
   * @param tf the TensorFlow Ops.
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param units Positive integer, dimensionality of the output space.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public Dense(Ops tf, String name, Integer units, long seed, Class<T> type, Options options) {
    this(
        tf, name, units, null, true, null, null, null, null, null, null, null, seed, type, options);
  }

  /**
   * Creates a Dense layer.
   *
   * @param tf the TensorFlow Ops.
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param units Positive integer, dimensionality of the output space.
   * @param activation Activation function to use. If you don't specify anything, no activation is
   *     applied (ie. "linear" activation: <code>a(x) = x</code>).
   * @param useBias whether the layer uses a bias vector.
   * @param kernelInitializer Initializer for the <code>kernel</code> weights matrix.
   * @param biasInitializer Initializer for the bias vector.
   * @param kernelRegularizer Regularizer applied to the kernel weights matrix.
   * @param biasRegularizer Regularizer function applied to the bias vector.
   * @param activityRegularizer Regularizer function applied to the output of the layer (its
   *     "activation").
   * @param kernelConstraint a constraint on the kernel variable
   * @param biasConstraint a constraint on the bias variable
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public Dense(
      Ops tf,
      String name,
      Integer units,
      Activation<T> activation,
      boolean useBias,
      Initializer<T> kernelInitializer,
      Initializer<T> biasInitializer,
      Regularizer kernelRegularizer,
      Regularizer biasRegularizer,
      Regularizer activityRegularizer,
      UnaryOperator<Operand<T>> kernelConstraint,
      UnaryOperator<Operand<T>> biasConstraint,
      long seed,
      Class<T> type) {
    this(
        tf,
        name,
        units,
        activation,
        useBias,
        kernelInitializer,
        biasInitializer,
        kernelRegularizer,
        biasRegularizer,
        activityRegularizer,
        kernelConstraint,
        biasConstraint,
        seed,
        type,
        null);
  }
  /**
   * Creates a Dense layer.
   *
   * @param tf the TensorFlow Ops.
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param units Positive integer, dimensionality of the output space.
   * @param activation Activation function to use. If you don't specify anything, no activation is
   *     applied (ie. "linear" activation: <code>a(x) = x</code>).
   * @param useBias whether the layer uses a bias vector.
   * @param kernelInitializer Initializer for the <code>kernel</code> weights matrix.
   * @param biasInitializer Initializer for the bias vector.
   * @param kernelRegularizer Regularizer applied to the kernel weights matrix.
   * @param biasRegularizer Regularizer function applied to the bias vector.
   * @param activityRegularizer Regularizer function applied to the output of the layer (its
   *     "activation").
   * @param kernelConstraint a constraint on the kernel variable
   * @param biasConstraint a constraint on the bias variable
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  @SuppressWarnings("unchecked")
  public Dense(
      Ops tf,
      String name,
      Integer units,
      Activation<T> activation,
      boolean useBias,
      Initializer<T> kernelInitializer,
      Initializer<T> biasInitializer,
      Regularizer kernelRegularizer,
      Regularizer biasRegularizer,
      Regularizer activityRegularizer,
      UnaryOperator<Operand<T>> kernelConstraint,
      UnaryOperator<Operand<T>> biasConstraint,
      long seed,
      Class<T> type,
      Options options) {
    super(tf, name, true, type, options);
    this.units = units;
    this.activation = activation;
    this.useBias = useBias;

    this.kernelInitializer =
        kernelInitializer != null
            ? kernelInitializer
            : (Initializer<T>) new Glorot<>(tf, VarianceScaling.Distribution.UNIFORM, seed);
    this.biasInitializer = biasInitializer != null ? biasInitializer : new Zeros<>(tf);
    this.kernelConstraint = kernelConstraint;
    this.biasConstraint = biasConstraint;
    this.biasRegularizer = biasRegularizer;
    this.kernelRegularizer = kernelRegularizer;
    setActivityRegularizer(activityRegularizer);
    this.seed = seed;
    addInputSpec(new InputSpec(InputSpec.Options.create().minRank(2)));
    setSupportsMasking(true);
  }

  /**
   * Implements the operation: {@code output = activation(dot(input, kernel) + bias)}
   *
   * @param inputs the input Operands, an N-D tensor with shape: {@code (batch_size, ...,
   *     input_dim)}. The most common situation would be a 2D input with shape @code (batch_size,
   *     input_dim)}.
   * @param masks a list of masks, one for each input, to apply to the result, may be null
   * @param training whether the call is in inference mode or training mode
   * @param resultType the data tupe for the result
   * @param <U> the data tupe for the result
   * @return the output with shape {@code (batch_size, ..., units)}. For instance, for a 2D input
   *     with shape {@code (batch_size, input_dim)}, the output would have shape {@code (batch_size,
   *     units)}.
   */
  @Override
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    if (inputs == null || inputs.size() != 1)
      throw new IllegalArgumentException("Dense only supports 1 input.");
    Operand<? extends TType> singleInput = inputs.get(0);
    Operand<T> input = cast(getTF(), singleInput, getType());
    if (!isBuilt()) build(input.shape());
    Shape inputShape = input.shape();
    int rank = inputShape.numDimensions();
    Operand<T> tOutput;
    if (rank == 2 || rank == Shape.UNKNOWN_SIZE) {
      tOutput = getTF().linalg.matMul(input, getKernel());
    } else {
      FrameworkOps fops = FrameworkOps.create(getTF());
      tOutput = fops.math.tensordot(input, getKernel(), new int[] {rank - 1, 0});
      // Reshape the output back to the original number of dimensions of the input.
      Shape newShape = inputShape.take(rank - 1).append(getUnits());
      tOutput = getTF().reshape(tOutput, getTF().constant(newShape));
    }
    if (isUseBias()) {
      tOutput = getTF().nn.biasAdd(tOutput, getBias());
    }
    if (activation != null) {
      tOutput = activation.call(tOutput);
    }

    return callPostProcess(Collections.singletonList(cast(getTF(), tOutput, resultType)), training);
  }

  /** {@inheritDoc} */
  @Override
  public void build(List<Shape> inputShapes) {
    super.build(inputShapes);
    if (inputShapes == null || inputShapes.size() != 1) {
      throw new IllegalArgumentException("Dense only supports 1 input.");
    }
    if (!TFloating.class.isAssignableFrom(getType()))
      throw new IllegalArgumentException(
          String.format(
              "Unable to build Dense layer with non-floating point type: %s",
              getType().toString()));

    if (kernelInitializer == null) {
      // Cast is required because Glorot is TFloating.
      kernelInitializer = new Glorot<>(getTF(), VarianceScaling.Distribution.UNIFORM, getSeed());
    }
    if (biasInitializer == null) {
      biasInitializer = new Zeros<>(getTF());
    }

    Shape inputShape = inputShapes.get(0);
    if (inputShape.size(-1) == Shape.UNKNOWN_SIZE) {
      throw new IllegalArgumentException(
          "The last dimension of the inputs to `Dense` should be defined. Found `UNKNOWN`.");
    }
    long lastDim = inputShape.size(-1);
    addInputSpec(new InputSpec(InputSpec.Options.create().minRank(2).axesMap(-1, lastDim)));

    kernel =
        addWeight(
            "kernel",
            Shape.of(lastDim, this.getUnits()),
            kernelInitializer,
            kernelConstraint,
            kernelRegularizer,
            true,
            getSeed());
    if (isUseBias())
      bias =
          addWeight(
              "bias",
              Shape.of(this.getUnits()),
              biasInitializer,
              biasConstraint,
              biasRegularizer,
              true,
              getSeed());
  }

  public Operand<T> applyConstraint(Variable variable) {
    VariableDef<T> variableDef = getVariableDef(variable);
    if(variableDef != null && variableDef.getConstraint() != null) {
        return variableDef.getConstraint().apply(variable);
    }else {
      return variable;
    }
  }

  /** {@inheritDoc} */
  @Override
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    if (inputShapes == null || inputShapes.size() != 1)
      throw new IllegalArgumentException("Dense layer:  there must be one input shape");
    if (!isBuilt()) build(inputShapes);
    Shape singleShape = inputShapes.get(0);
    if (singleShape.size(-1) == Shape.UNKNOWN_SIZE)
      throw new IllegalArgumentException(
          String.format(
              "Dense layer: The innermost dimension of input_shape must be defined, but saw: %s",
              singleShape));
    Shape headShape = singleShape.take(singleShape.numDimensions() - 1).append(getUnits());

    return Collections.singletonList(headShape);
  }

  /**
   * Gets the dense units
   *
   * @return the dense units
   */
  public Integer getUnits() {
    return units;
  }

  /**
   * Gets the use bias flag
   *
   * @return the use bias flag
   */
  public boolean isUseBias() {
    return useBias;
  }

  /**
   * Gets the seed
   *
   * @return the seed
   */
  public long getSeed() {
    return seed;
  }

  /**
   * Gets the kernel variable
   *
   * @return the kernel variable
   */
  public Variable<T> getKernel() {
    return kernel;
  }

  /**
   * Gets the bias variable
   *
   * @return the bias variable
   */
  public Variable<T> getBias() {
    return bias;
  }
}
