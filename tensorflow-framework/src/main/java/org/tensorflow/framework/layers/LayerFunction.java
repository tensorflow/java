package org.tensorflow.framework.layers;

import org.tensorflow.Operand;
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface LayerFunction<T extends TType>
    extends Function<List<Operand<T>>, List<Operand<T>>> {
  List<Operand<T>> apply(List<Operand<T>> inputs);
}
