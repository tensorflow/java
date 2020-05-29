package org.tensorflow.framework.layers;

import org.tensorflow.DataType;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class Module<T extends TType> {
  protected final Ops tf;
  private final String name;
  private List<ModuleVariable<T>> weights;

  public Module(Ops tf, String name, DataType<T> dtype) {
    this.tf = tf.withName(name);
    this.name = name;
    this.weights = new LinkedList<>();
  }

  public abstract Iterable<Module<T>> getDirectSubmodules();

  public Iterable<Module<T>> getSubmodules(boolean recurse) {
    if (!recurse) return getDirectSubmodules();

    List<Module<T>> submodules = new ArrayList<>();

    for (Module<T> module : getDirectSubmodules())
      module.getSubmodules(true).forEach(submodules::add);

    return submodules;
  }

  public Variable<T> addWeight(String name, boolean trainable, Shape shape, DataType<T> dtype) {
    ModuleVariable<T> moduleVariable = new ModuleVariable<>(name, tf.variable(shape, dtype), trainable);
    this.weights.add(moduleVariable);

    return moduleVariable.variable;
  }

  List<ModuleVariable<T>> getModuleWeights() {
    return StreamSupport.stream(getSubmodules(true).spliterator(), false)
        .flatMap(module -> module.weights.stream())
        .collect(Collectors.toList());
  }

  public String getName() {
    return name;
  }
}

class ModuleVariable<T extends TType> {
  String name;
  boolean trainable;
  Variable<T> variable;

  public ModuleVariable(String name, Variable<T> variable, boolean trainable) {
    this.trainable = trainable;
    this.variable = variable;
  }
}
