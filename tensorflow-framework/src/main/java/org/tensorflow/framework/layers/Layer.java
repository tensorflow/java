package org.tensorflow.framework.layers;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.family.TType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Layer<T extends TType> extends Module<T> implements LayerFunction<T> {
  private final boolean trainable;
  private final boolean dynamic;
  private final DataType<T> dtype;

  public List<Node<T>> inboundNodes;
  public List<Node<T>> outboundNodes;

  protected boolean built;

  public Layer(Ops tf, String name, boolean trainable, boolean dynamic, DataType<T> dtype) {
    super(tf, name, dtype);
    this.trainable = trainable;
    this.dynamic = dynamic;
    this.dtype = dtype;
  }

  /**
   * Builds this layer (add layer weights) NOTE: This method MUST set `built` to true
   *
   * <p>{@code this.built = true}
   */
  public abstract void build(List<Shape> inputShapes);

  public abstract List<Shape> computeOutputShapes(List<Shape> inputShapes);

  protected abstract List<Operand<T>> call(List<Operand<T>> inputs);

  @SafeVarargs
  public final List<Operand<T>> apply(Operand<T>... inputs) {
    return apply(Arrays.asList(inputs));
  }

  @Override
  public final List<Operand<T>> apply(List<Operand<T>> inputs) {
    if (!isBuilt()) throw new IllegalStateException("Cannot call a layer until it is built.");

    if (isDynamic() && tf.scope().env().isGraph())
      throw new IllegalStateException("Dynamic layers can only be used " + "in eager mode.");

    List<Shape> expectedOutputShapes = computeOutputShapes(getShapes(inputs));
    List<Operand<T>> outputs = call(inputs);

    for (int i = 0; i < inputs.size(); i++) {
      if (expectedOutputShapes.get(i) != outputs.get(i).asOutput().shape()) {
        throw new IllegalStateException(
            "Shape "
                + outputs.get(i).asOutput().shape()
                + " at output "
                + i
                + "does not "
                + "match expected shape "
                + expectedOutputShapes.get(i));
      }
    }

    return outputs;
  }

  @Override
  public Iterable<Module<T>> getDirectSubmodules() {
    return Collections::emptyIterator;
  }

  /**
   * Returns a list of all trainable and non-trainable weights (in that order)
   *
   * @return all the weights of this layer (concatenation of getTrainableWeights() and
   *     getNonTrainableWeights())
   */
  public List<Variable<T>> getWeights() {
    List<Variable<T>> weights = getTrainableWeights();
    weights.addAll(getNonTrainableWeights());
    return weights;
  }

  /**
   * List of variables to be included in backpropagation
   *
   * @return all trainable weights of this layer
   */
  public List<Variable<T>> getTrainableWeights() {
    return getModuleWeights().stream()
        .filter(w -> w.trainable)
        .map(w -> w.variable)
        .collect(Collectors.toList());
  }

  /**
   * List of variables to be excluded from backpropagation
   *
   * @return all non-trainable weights of this layer
   */
  public List<Variable<T>> getNonTrainableWeights() {
    return getModuleWeights().stream()
        .filter(w -> !w.trainable)
        .map(w -> w.variable)
        .collect(Collectors.toList());
  }

  private <U extends TType> List<Shape> getShapes(List<Operand<U>> operands) {
    return operands.stream().map(op -> op.asOutput().shape()).collect(Collectors.toList());
  }

  public boolean isTrainable() {
    return trainable;
  }

  public boolean isDynamic() {
    return dynamic;
  }

  public boolean isBuilt() {
    return built;
  }

  public DataType<T> getDtype() {
    return dtype;
  }
}
