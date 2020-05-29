package org.tensorflow.framework.layers;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TFloat32;

import java.util.Collections;
import java.util.List;

class Dense extends Layer<TFloat32> {
  private final int units;
  public static Variable<TFloat32> kernel;
  public static Variable<TFloat32> bias;

  public Dense(Ops tf, int units) {
    super(tf, "dense", 1, true, false, TFloat32.DTYPE);
    this.units = units;
  }

  @Override
  public void build(List<Shape> inputShapes) {
    kernel = addWeight("KERNEL", true, inputShapes.get(0), TFloat32.DTYPE);
    bias = addWeight("BIAS", true, inputShapes.get(0), TFloat32.DTYPE);
    this.built = true;
  }

  @Override
  public List<Shape> computeOutputShapes(List<Shape> inputShapes) {
    return Collections.singletonList(inputShapes.get(0).replaceLast(units));
  }

  @Override
  public List<Operand<TFloat32>> call(List<Operand<TFloat32>> inputs) {
    Operand<TFloat32> input = inputs.get(0);
    return Collections.singletonList(tf.math.add(tf.linalg.matMul(input, kernel), bias));
  }

  @Override
  public Iterable<Module<TFloat32>> getDirectSubmodules() {
    return Collections::emptyIterator;
  }
}

public class LayerTest {}
