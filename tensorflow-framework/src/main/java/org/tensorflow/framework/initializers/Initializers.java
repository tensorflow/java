package org.tensorflow.framework.initializers;

import org.tensorflow.op.Ops;

import java.util.function.Function;

/** An Enum Type used to create a new Initializer with default parameters. */
public enum Initializers {
  IDENTITY(Identity::new),
  ONES(Ones::new),
  ZEROS(Zeros::new),
  GLOROT_NORMAL(GlorotNormal::new),
  GLOROT_UNIFORM(GlorotUniform::new),
  ORTHOGONAL(Orthogonal::new),
  RANDOM_NORMAL(RandomNormal::new),
  RANDOM_UNIFORM(RandomUniform::new),
  TRUNCATED_NORMAL(TruncatedNormal::new),
  VARIANCE_SCALING(VarianceScaling::new),
  HE_NORMAL(HeNormal::new),
  HE_UNIFORM(HeUniform::new),
  LECUN_NORMAL(LeCunNormal::new),
  LECUN_UNIFORM(LeCunUniform::new);

  private final Function<Ops, Initializer> creator;

  /**
   * Creates an Optimizers enum
   *
   * @param creator the lambda function that accepts a Graph argument used to create the default
   *     Optimizer
   */
  Initializers(Function<Ops, Initializer> creator) {
    this.creator = creator;
  }

  /**
   * Creates an Initializer with default settings.
   *
   * @param tf the TensorFlow Ops
   * @return the Optimizer
   */
  public Initializer createInitializer(Ops tf) {
    return creator.apply(tf);
  }
}
