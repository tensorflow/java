package org.tensorflow.framework.layers;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.family.TType;

import java.util.Arrays;
import java.util.List;

public class Sequential<T extends TType> extends Layer<T> {
  private final List<Layer<T>> layers;

  @SafeVarargs
  public Sequential(Ops tf, DataType<T> dtype, Layer<T>... layers) {
    super(tf, "Sequential", true, true, dtype);
    this.layers = Arrays.asList(layers);
  }

  @Override
  public void build(List<Shape> inputShapes) {
    List<Shape> shapes = inputShapes;

    for (Layer<T> layer : layers) {
      layer.build(shapes);
      shapes = layer.computeOutputShapes(shapes);
    }
  }

  @Override
  public List<Shape> computeOutputShapes(List<Shape> inputShapes) {
    List<Shape> shapes = inputShapes;
    for (Layer<T> layer : layers) {
      shapes = layer.computeOutputShapes(shapes);
    }

    return shapes;
  }

  @Override
  protected List<Operand<T>> call(List<Operand<T>> inputs) {
    List<Operand<T>> outputs = inputs;
    for (Layer<T> layer : layers) {
      outputs = layer.call(inputs);
    }

    return outputs;
  }

  @Override
  public Iterable<Module<T>> getDirectSubmodules() {
    return (List<Module<T>>) (List<?>) layers;
  }
}
