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
import org.tensorflow.framework.constraints.Constraint;
import org.tensorflow.framework.initializers.Initializer;
import org.tensorflow.framework.layers.impl.InputSpec;
import org.tensorflow.framework.layers.impl.VariableDef;
import org.tensorflow.framework.losses.Loss;
import org.tensorflow.framework.metrics.Metric;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * The base abstract class for Layers.
 *
 * <p>A layer is a callable object that takes as input one or more tensors and that outputs one or
 * more tensors. It involves computation, defined in the call() method, and a state (weight
 * variables), defined either in the constructor or in the first call to the {@link #call} method
 * method.
 *
 * <p>Users will just instantiate a layer and then treat it as a callable.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public abstract class Layer<T extends TNumber> {

  private static final Map<String, Integer> nameMap = new HashMap<>();
  private final String name;
  private final Class<T> type;
  private final List<Variable<T>> weights = new ArrayList<>();
  private final List<Variable<T>> trainableWeights = new ArrayList<>();
  private final List<Variable<T>> nonTrainableWeights = new ArrayList<>();
  private final List<Loss> losses = new ArrayList<>();
  private final List<Metric<? extends TNumber>> metrics = new ArrayList<>();
  private final Map<Variable<T>, VariableDef<T>> variableMap = new HashMap<>();
  // Note that, unlike other classes, tf may not be set in the constructor, but may be set later.
  // the idea behind this is that the model can be built with the layers before the model
  // sets the tf instance probably during the model.compile phase.
  private final Ops tf;
  private boolean trainable;
  // TODO change to Regularizer class
  private Object activityRegularizer;
  private boolean built;
  private boolean stateful;
  private boolean supportsMasking;
  // These are the inputShapes as presented to build
  private List<Shape> inputShapes;
  private List<InputSpec> inputSpecs;
  // These are the shapes/dimensions presented by Options.
  private Shape batchInputShape;
  private Long batchSize;
  private Shape inputShape;
  private Options instanceOptions;

  /**
   * Creates the base Layer class
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer, if null will generate a name using the method
   *     {@link #genName()}
   * @param trainable whether the layer's variables should be trainable or not.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Layer(Ops tf, String name, boolean trainable, Class<T> type, Options options) {
    this.name = name == null ? genName() : name;
    this.setTrainable(trainable);
    this.type = type;
    this.batchSize = Shape.UNKNOWN_SIZE;
    loadOptions(options);
    this.tf = tf.withSubScope(getName());
  }

  private void loadOptions(Options options) {
    if (options != null) {
      instanceOptions = options;
      if (instanceOptions.batchInputShape != null) {
        this.batchInputShape = instanceOptions.batchInputShape;
      }
      if (instanceOptions.batchSize != null) {
        this.batchSize = instanceOptions.batchSize;
      }
      if (instanceOptions.inputShape != null) {
        this.inputShape = instanceOptions.inputShape;
      }
      if (instanceOptions.activityRegularizer != null) {
        this.activityRegularizer = instanceOptions.activityRegularizer;
      }
      if (instanceOptions.metrics != null) {
        this.metrics.addAll(instanceOptions.metrics);
      }
      if (instanceOptions.losses != null) {
        this.losses.addAll(instanceOptions.losses);
      }
    }
  }

  /**
   * Generates an unique name by appending an integer value to the {@link Class#getSimpleName} in
   * the form <code>{@link Class#getSimpleName}_&lt;identifier&gt;</code>, e.g <code>Dense_1</code>
   * The first call to generate an unique name will only return {@link Class#getSimpleName} with out
   * the suffix, e.g <code>Dense</code>.
   *
   * @return the generated name for the class.
   */
  private String genName() {
    String base = getClass().getSimpleName();
    Integer id = nameMap.get(base);
    if (id == null) {
      nameMap.put(base, 0);
      return base;
    } else {
      id++;
      nameMap.put(base, id);
      return String.format("%s_%d", base, id);
    }
  }

  /**
   * Invokes the layer's algorithm using a single input, returning a single output. Training mode is
   * true.
   *
   * <p>This is a convenience call on top of {@link {@link #call}}.
   *
   * @param input the input Operand
   * @return the output Operand, or null if no output is generated from the layer's logic.
   */
  public  Operand<T> call(Operand<? extends TType> input) {

    return call(input, null, true, getType());
  }

  /**
   * Invokes the layer's algorithm using a single input, returning a single output. Training mode is
   * true.
   *
   * <p>This is a convenience call on top of {@link {@link #call}}.
   *
   * @param input the input Operand
   * @return the output Operand, or null if no output is generated from the layer's logic.
   */
  public <U extends TType> Operand<U> call(Operand<? extends TType> input, Class<U> type) {

    return call(input, null, true, type);
  }

  /**
   * Invokes the layer's algorithm using a single input, returning a single output.
   *
   * <p>This is a convenience call on top of {@link #call}.
   *
   * @param input the input Operand
   * @param training whether the call is in inference mode or training mode
   * @return the output Operand, or null if no output is generated from the layer's logic.
   */
  public <U extends TType> Operand<U> call(
      Operand<? extends TType> input, boolean training, Class<U> type) {
    return call(input, null, training, type);
  }

  /**
   * Invokes the layer's algorithm using a single input, returning a single output.
   *
   * <p>This is a convenience call on top of {@link #call}.
   *
   * @param input the input Operand
   * @param mask the mask to apply to the result, may be null
   * @param training whether the call is in inference mode or training mode
   * @return the output Operand, or null if no output is generated from the layer's logic.
   */
  public <U extends TType> Operand<U> call(
      Operand<? extends TType> input, Operand<TBool> mask, boolean training, Class<U> type) {
    List<Operand<U>> result =
        call(Collections.singletonList(input), Collections.singletonList(mask), training, type);
    return result != null ? result.get(0) : null;
  }

  /**
   * Invokes the layer's algorithm Training mode is true.
   *
   * @param inputs the input Operands
   * @return the output Operands
   */
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs, Class<U> type) {
    return call(inputs, null, false, type);
  }

  /**
   * Invokes the layer's logic using a list of inputs, returning a list of outputs.
   *
   * @param inputs the input Operands
   * @param masks a list of masks, one for each input, to apply to the result, may be null
   * @param training whether the call is in inference mode or training mode
   * @return the output Operands.
   */
  public abstract <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> type);

  /**
   * Post processes a layer's call result
   *
   * @param inputs the input Operands
   * @return the output Operands.
   */
  protected <U extends TType> List<Operand<U>> callPostProcess(
      List<Operand<U>> inputs, boolean training) {
    return handleActivityRegister(inputs);
  }

  /**
   * Converts a list of inputs to a new list of the internal data type defined for this layer.
   *
   * @param inputs the inputs.
   * @return the new list converted to the new type.
   */
  protected List<Operand<T>> convertList(List<Operand<? extends TType>> inputs) {
    return convertList(inputs, getType());
  }
  /**
   * Converts a list of inputs to a new list of the internal data type defined for this layer.
   *
   * @param inputs the inputs.
   * @return the new list converted to the new type.
   */
  protected <U extends TType> List<Operand<U>> convertList(
      List<Operand<? extends TType>> inputs, Class<U> resultType) {
    List<Operand<U>> result = new ArrayList<>();
    inputs.forEach(input -> result.add(cast(getTF(), input, resultType)));
    return result;
  }

  /**
   * Converts a list of inputs with this class type, to a new list of the new type
   *
   * @param inputs the inputs.
   * @param newType the new type.
   * @param <R> the data type for the new type.
   * @return the new list converted to the new type.
   */
  protected <R extends TType> List<Operand<R>> convertTo(
      List<Operand<T>> inputs, Class<R> newType) {
    List<Operand<R>> result = new ArrayList<>();
    inputs.forEach(input -> result.add(cast(getTF(), input, newType)));
    return result;
  }

  private <U extends TType> List<Operand<U>> handleActivityRegister(List<Operand<U>> inputs) {
    if (this.activityRegularizer != null) {
      // TODO activityRegularizer
      return inputs;

    } else {
      return inputs;
    }
  }

  /**
   * Creates the variables of the layer (optional, for subclass implementers). This is a method that
   * implementers of subclasses of <code>Layer</code> or <code>Model</code> can override if they
   * need a state-creation step in-between layer instantiation and layer call. This is typically
   * used to create the weights of <code>Layer</code> subclasses.
   *
   * <p>This method is a convenience method that calls {@link #build(List)}.
   *
   * @param inputShape the shapes of the inputs, one per input
   */
  protected void build(Shape... inputShape) {
    build(Arrays.asList(inputShape));
  }

  /**
   * Creates the variables of the layer (optional, for subclass implementers). This is a method that
   * implementers of subclasses of <code>Layer</code> or <code>Model</code> can override if they
   * need a state-creation step in-between layer instantiation and layer call. This is typically
   * used to create the weights of <code>Layer</code> subclasses.
   *
   * @param inputShapes the shapes of the inputs, one per input
   * @throws IllegalStateException if the TensorFlow Ops is null.
   */
  protected void build(List<Shape> inputShapes) {
    if (tf == null) throw new IllegalStateException("The TensorFlow Ops has not been set yet");
    built = true;
    this.inputShapes = inputShapes;
  }

  /**
   * Computes the output shape of the layer.
   *
   * <p>This implementation calls {@link #build(List)} if not already called, and returns the input
   * shapes as the output shapes. Sub-classes may want to alter this default behavior
   *
   * <p>If the layer has not been built, this method will call {@link #build(List)} on the layer.
   * This assumes that the layer will later be used with inputs that match the input shape provided
   * here.
   *
   * @param inputShapes the input shapes, one per input
   * @return the output shapes, one per output
   */
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    if (!built) build(inputShapes);
    return inputShapes;
  }

  /**
   * Gets the unique name for this layer
   *
   * @return the unique name for this layer
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the trainable setting
   *
   * @return true, if this layer is trainable
   */
  public boolean isTrainable() {
    return trainable;
  }

  /**
   * Sets the trainable indicator
   *
   * @param trainable the trainable indicator
   */
  public void setTrainable(boolean trainable) {
    this.trainable = trainable;
  }

  /**
   * Gets the data type for the layer's weights and computation.
   *
   * @return the data type for the layer's weights and computation.
   */
  public Class<T> getType() {
    return type;
  }

  /**
   * Gets the layer's weights
   *
   * @return the layer's weights
   */
  public List<Variable<T>> getWeights() {
    return weights;
  }

  public void setWeights(List<Variable<T>> weights) {
    this.weights.clear();
    this.weights.addAll(weights);
  }

  /**
   * Gets the layer's trainable weights
   *
   * @return the layer's trainable weights
   */
  public List<Variable<T>> getTrainableWeights() {
    return trainableWeights;
  }

  /**
   * Gets the layer's non-trainable weights
   *
   * @return the layer's non-trainable weights
   */
  public List<Variable<T>> getNonTrainableWeights() {
    return nonTrainableWeights;
  }

  /**
   * Adds a weight to the layer
   *
   * @param name the variable's name
   * @param shape the variable's shape
   * @param initializer the variable initializer
   * @param trainable whether the variable should be part of the layer's "trainableWeights"
   * @throws IllegalStateException if the property {@link #tf} has not been set yet.
   */
  public Variable<T> addWeight(
      String name,
      Shape shape,
      Initializer<T> initializer,
      Constraint constraint,
      boolean trainable,
      long seed) {
    if (tf == null) {
      throw new IllegalStateException("Parameter \"tf\" has not been set");
    }
    VariableDef<T> variableDef =
        new VariableDef<>(tf, name, shape, initializer, constraint, trainable, seed, getType());

    Variable<T> variable = variableDef.getVariable();

    variableMap.put(variable, variableDef);
    weights.add(variable);
    if (trainable) trainableWeights.add(variable);
    else nonTrainableWeights.add(variable);
    return variable;
  }

  /**
   * Adds a weight to the layer
   *
   * @param variable the variable to add
   * @param initializer the variable initializer
   * @param trainable whether the variable should be part of the layer's "trainableWeights"
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and type.
   * @throws IllegalStateException if the property {@link #tf} has not been set yet.
   */
  public Variable<T> addWeight(
      String name,
      Variable<T> variable,
      Initializer<T> initializer,
      Constraint constraint,
      boolean trainable,
      long seed) {
    if (tf == null) {
      throw new IllegalStateException("Parameter \"tf\" has not been set");
    }
    if (variable == null) {
      throw new IllegalStateException("Parameter \"variable\" has not been set");
    }
    VariableDef<T> variableDef =
        new VariableDef<>(tf, name, variable, initializer, constraint, trainable, seed);
    variableMap.put(variable, variableDef);
    weights.add(variable);
    if (trainable) trainableWeights.add(variable);
    else nonTrainableWeights.add(variable);
    return variable;
  }

  /**
   * Gets the Operands that initializes all the weights
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and type.
   * @return the Operands that initializes all the weights
   */
  public List<Operand<T>> initializeWeights(long seed) {
    List<Operand<T>> result = new ArrayList<>();
    weights.forEach(w -> result.add(initializeWeight(w, seed)));
    return result;
  }

  /**
   * Creates an Operand that initializes a weight
   *
   * @param weight the weight to initialize
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and type.
   * @return the Operand that initializes the weight
   * @throws IllegalArgumentException if the weight does not have a registered initializer
   */
  public Operand<T> initializeWeight(Variable<T> weight, long seed) {
    VariableDef<T> varDef = variableMap.get(weight);
    if (varDef == null) { // this should not happen if addWeight was used to create/add the weight
      addWeight(null, weight, null, null, true, seed);
      varDef = variableMap.get(weight);
    }
    return varDef.init();
  }

  /**
   * Computes an output mask tensor.
   *
   * @param inputs the input Operands
   * @param masks the mask Operands.
   * @return null or a list of Operands, one for each output from the layer,
   */
  @SuppressWarnings("UnusedParameters")
  public List<Operand<TBool>> computeMask(
      List<Operand<? extends TType>> inputs, List<Operand<? extends TType>> masks) {
    // the default implementation merely returns the masks.
    if (isSupportsMasking()) {
      if (masks == null) return null;
      return masks.stream().map(m -> cast(getTF(), m, TBool.class)).collect(Collectors.toList());
    }
    if (masks == null || masks.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("%s  does not support masking, but was passed a mask", getName()));
    }

    return null;
  }

  /**
   * Gets the Losses assigned to this layer
   *
   * @return the Losses assigned to this layer
   */
  public List<Loss> getLosses() {
    return losses;
  }

  /**
   * Adds a loss to this layer
   *
   * @param loss the loss to add
   */
  public void addLoss(Loss loss) {
    losses.add(loss);
  }

  /**
   * Adds losses to this layer
   *
   * @param losses the losses to add
   */
  public void addLosses(List<Loss> losses) {
    this.losses.addAll(losses);
  }

  /**
   * Gets the Losses assigned to this layer
   *
   * @return the Losses assigned to this layer
   */
  public List<Metric<? extends TNumber>> getMetrics() {
    return metrics;
  }

  /**
   * Adds a metric to this layer
   *
   * @param metric the metric to add
   */
  public void addMetric(Metric<T> metric) {
    metrics.add(metric);
  }

  /**
   * Adds metrics to this layer
   *
   * @param metrics the metric to add
   */
  public void addMetrics(List<Metric<T>> metrics) {
    this.metrics.addAll(metrics);
  }

  /**
   * Determines whether or not the build method has been called.
   *
   * @return true, if the build method has been called.
   */
  public boolean isBuilt() {
    return built;
  }

  /**
   * Sets the build indicator
   *
   * @param built the build indicator
   */
  public void setBuilt(boolean built) {
    this.built = built;
  }

  /**
   * Gets the input shapes, one per input
   *
   * @return the input shapes, one per input
   */
  public List<Shape> getInputShapes() {
    return inputShapes;
  }

  /**
   * Sets the input shapes, one per input
   *
   * @param inputShapes the input shapes
   */
  public void setInputShapes(List<Shape> inputShapes) {
    this.inputShapes = inputShapes;
  }

  /**
   * Adds an inputSpec
   *
   * @param inputSpec the inputSpec
   */
  public void addInputSpec(InputSpec inputSpec) {
    if (inputSpecs == null) {
      inputSpecs = new ArrayList<>();
    }
    inputSpecs.add(inputSpec);
  }

  /**
   * Gets the inputSpecs, one per input
   *
   * @return the inputSpecs, one per input
   */
  public List<InputSpec> getInputSpecs() {
    return inputSpecs;
  }

  /**
   * Sets the inputSpecs, one per input
   *
   * @param inputSpecs the inputSpecs
   */
  public void setInputSpecs(List<InputSpec> inputSpecs) {
    this.inputSpecs = inputSpecs;
  }

  /**
   * Gets the {@link #tf} property
   *
   * @return the {@link #tf} property
   */
  public Ops getTF() {
    return tf;
  }

  /**
   * Gets the stateful property.
   *
   * <p>A stateful layer is a layer whose updates are run during inference too, for instance
   * stateful RNNs.
   *
   * @return true, if this layer is stateful
   */
  public boolean isStateful() {
    return stateful;
  }

  /**
   * Sets the stateful property.
   *
   * <p>A stateful layer is a layer whose updates are run during inference too, for instance
   * stateful RNNs.
   *
   * @param stateful true, if this layer is stateful.
   */
  public void setStateful(boolean stateful) {
    this.stateful = stateful;
  }

  /**
   * Gets the batch input shape
   *
   * @return the batch input shape
   */
  public Shape getBatchInputShape() {
    return batchInputShape;
  }

  /**
   * Sets the batch input shape
   *
   * @param batchInputShape the batch input shape
   */
  public void setBatchInputShape(Shape batchInputShape) {
    this.batchInputShape = batchInputShape;
  }

  /**
   * Gets the batch size
   *
   * @return the batch size
   */
  public Long getBatchSize() {
    return batchSize;
  }

  /**
   * Sets the batch size
   *
   * @param batchSize the batch size
   */
  public void setBatchSize(Long batchSize) {
    this.batchSize = batchSize;
  }

  /**
   * Gets the input shape for this layer
   *
   * @return the input shape for this layer
   */
  public Shape getInputShape() {
    return inputShape;
  }

  /**
   * Sets the input shape for this layer
   *
   * @param inputShape the input shape for this layer
   */
  public void setInputShape(Shape inputShape) {
    this.inputShape = inputShape;
  }

  /**
   * Gets the options instance for this layer.
   *
   * @return the options instance for this layer.
   */
  public Options getInstanceOptions() {
    return instanceOptions;
  }

  /**
   * Gets the activity Regularizer
   *
   * @return the activity Regularizer
   */
  // TODO change to Regularizer class
  public Object getActivityRegularizer() {
    return activityRegularizer;
  }

  /**
   * Sets the activity Regularizer
   *
   * @param activityRegularizer the activity Regularizer
   */
  // TODO change to Regularizer class
  public void setActivityRegularizer(Object activityRegularizer) {
    this.activityRegularizer = activityRegularizer;
  }

  /**
   * Gets the indicator that this layer supports masking.
   *
   * @return the indicator that this layer supports masking.
   */
  public boolean isSupportsMasking() {
    return supportsMasking;
  }

  /**
   * Sets the indicator that this layer supports masking.
   *
   * @param supportsMasking the indicator that this layer supports masking.
   */
  public void setSupportsMasking(boolean supportsMasking) {
    this.supportsMasking = supportsMasking;
  }

  /**
   * Assigns a value to the variable
   *
   * @param variable the variable to assign to
   * @param value the value to assign
   * @return the operand that assigns the value to this variable
   * @throws IllegalArgumentException if the variable is not known.
   */
  public Operand<T> assign(Variable<T> variable, Operand<T> value) {
    VariableDef varDef = variableMap.get(variable);
    if (varDef == null) {
      throw new IllegalStateException(String.format("Variable %s was not found.", variable));
    }
    return varDef.assign(value);
  }

  /**
   * Adds a value to the variable
   *
   * @param variable the variable to add to
   * @param value the value to add
   * @return the operand that adds the value to this variable
   * @throws IllegalArgumentException if the variable is not known.
   */
  public Operand<T> assignAdd(Variable<T> variable, Operand<T> value) {
    VariableDef varDef = variableMap.get(variable);
    if (varDef == null) {
      throw new IllegalStateException(String.format("Variable %s was not found.", variable));
    }
    return varDef.assignAdd(value);
  }

  /**
   * Subtracts a value from the variable
   *
   * @param variable the variable to subtract from
   * @param value the value to subtract
   * @return the operand that subtracts the value from this variable
   * @throws IllegalArgumentException if the variable is not known.
   */
  public Operand<T> assignSub(Variable<T> variable, Operand<T> value) {
    VariableDef varDef = variableMap.get(variable);
    if (varDef == null) {
      throw new IllegalStateException(String.format("Variable %s was not found.", variable));
    }
    return varDef.assignSub(value);
  }

  /** Optional attributes for {@link Layer} */
  public static class Options {
    protected Shape inputShape;
    protected Shape batchInputShape;
    protected Long batchSize;
    protected List<Metric<? extends TNumber>> metrics;
    protected List<Loss> losses;
    // TODO change to Regularizer class
    protected Object activityRegularizer;

    public static Options create() {
      return new Options();
    }

    /**
     * Sets the inputShape
     *
     * @param inputShape the input shape for the layer
     * @return this options instance
     */
    public Layer.Options inputShape(Shape inputShape) {
      this.inputShape = inputShape;
      return this;
    }

    /**
     * Sets the batchSize
     *
     * @param batchSize the batch input shape for the layer
     * @return this Options instance
     */
    public Layer.Options batchSize(Long batchSize) {
      this.batchSize = batchSize;
      return this;
    }

    /**
     * Sets the shared name
     *
     * @param batchInputShape the batch input shape for the layer
     * @return this Options instance
     */
    public Layer.Options batchInputShape(Shape batchInputShape) {
      this.batchInputShape = batchInputShape;
      return this;
    }

    /**
     * Sets the activityRegularizer
     *
     * @param activityRegularizer the activity Regularizer
     * @return this Options instance
     */
    // TODO change to Regularizer class
    public Layer.Options activityRegularizer(Object activityRegularizer) {
      this.activityRegularizer = activityRegularizer;
      return this;
    }

    /**
     * Adds a metric
     *
     * @param metric the metric
     * @return this Options instance
     */
    public Layer.Options metric(Metric<? extends TNumber> metric) {
      if (this.metrics == null) {
        this.metrics = new ArrayList<>();
      }
      metrics.add(metric);
      return this;
    }

    /**
     * Adds metrics
     *
     * @param metrics the metrics to add
     * @return this Options instance
     */
    public Layer.Options metrics(List<Metric<? extends TNumber>> metrics) {
      if (this.metrics == null) {
        this.metrics = new ArrayList<>(metrics);
      } else {
        this.metrics.addAll(metrics);
      }
      return this;
    }

    /**
     * Adds a loss
     *
     * @param loss the Loss
     * @return this Options instance
     */
    public Layer.Options loss(Loss loss) {
      if (losses == null) {
        losses = new ArrayList<>();
      }
      losses.add(loss);
      return this;
    }

    /**
     * Adds losses
     *
     * @param losses the losses to add
     * @return this Options instance
     */
    public Layer.Options losses(List<Loss> losses) {
      if (this.losses == null) {
        this.losses = new ArrayList<>(losses);
      } else {
        this.losses.addAll(losses);
      }
      return this;
    }
  }
}
