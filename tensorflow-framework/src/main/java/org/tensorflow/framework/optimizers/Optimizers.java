package org.tensorflow.framework.optimizers;

import org.tensorflow.Graph;

import java.util.function.Function;

/** Enumerator used to create a new Optimizer with default parameters. */
public enum Optimizers {
  ADADELTA(AdaDelta::new),
  ADAGRAD(AdaGrad::new),
  ADAGRAD_DA(AdaGradDA::new),
  ADAM(Adam::new),
  ADAMAX(Adamax::new),
  FTRL(Ftrl::new),
  NADAM(Nadam::new),
  RMSPROP(RMSProp::new),
  MOMENTUM(Momentum::new),
  GRADIENT_DESCENT(GradientDescent::new);

  private final Function<Graph, Optimizer> creator;

  /**
   * Creates an Optimizers enum
   *
   * @param creator the lambda function that accepts a Graph argument used to create the default
   *     Optimizer
   */
  Optimizers(Function<Graph, Optimizer> creator) {
    this.creator = creator;
  }

  /**
   * Creates an Optimizer with default settings.
   *
   * @param graph the TensorFlow Graph
   * @return the Optimizer
   */
  public Optimizer createOptimizer(Graph graph) {
    return creator.apply(graph);
  }
}
