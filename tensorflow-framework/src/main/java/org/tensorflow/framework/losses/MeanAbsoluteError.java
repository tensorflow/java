package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

public class MeanAbsoluteError extends Loss {

  /**
   * Creates a MeanAbsoluteError Loss using {@link Class#getSimpleName()} as the loss name and a Loss Reduction of
   * {@link * Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   */
  public MeanAbsoluteError(Ops tf) {
        super(tf);
    }

    /**
     * Creates a MeanAbsoluteError Loss using {@link Class#getSimpleName()} as the loss name
     *
     * @param tf the TensorFlow Ops
     * @param reduction Type of Reduction to apply to the loss.
     */
    public MeanAbsoluteError(Ops tf, Reduction reduction) {
        super(tf, null,reduction);
    }

    /**
     * Creates a MeanAbsoluteError
     *
     * @param tf the TensorFlow Ops
     * @param name the name of the loss
     * @param reduction Type of Reduction to apply to the loss.
     */
    public MeanAbsoluteError(Ops tf, String name, Reduction reduction) {
        super(tf, name, reduction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends TNumber> Operand<T> call(Operand<T> labels, Operand<T> predictions, Operand<T> sampleWeights) {
        Operand losses = Losses.mean_absolute_error(tf, labels, predictions);
        return super.computeWeightedLoss(losses, getReduction(), sampleWeights);
    }

}
