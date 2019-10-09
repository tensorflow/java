package org.tensorflow.op;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.quantization.Dequantize;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVars;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannel;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient;
import org.tensorflow.op.quantization.Quantize;
import org.tensorflow.op.quantization.QuantizeAndDequantize;
import org.tensorflow.op.quantization.QuantizeDownAndShrinkRange;
import org.tensorflow.op.quantization.RequantizationRange;
import org.tensorflow.op.quantization.Requantize;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code quantization} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class QuantizationOps {
  private final Scope scope;

  QuantizationOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link FakeQuantWithMinMaxArgsGradient} operation
   *
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxArgs operation.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxArgs operation.
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxArgsGradient
   * @see org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient
   */
  public FakeQuantWithMinMaxArgsGradient fakeQuantWithMinMaxArgsGradient(Operand<TFloat> gradients,
      Operand<TFloat> inputs, FakeQuantWithMinMaxArgsGradient.Options... options) {
    return FakeQuantWithMinMaxArgsGradient.create(scope, gradients, inputs, options);
  }

  /**
   * Builds an {@link FakeQuantWithMinMaxVarsGradient} operation
   *
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxVars operation.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxVars operation.
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxVarsGradient
   * @see org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient
   */
  public FakeQuantWithMinMaxVarsGradient fakeQuantWithMinMaxVarsGradient(Operand<TFloat> gradients,
      Operand<TFloat> inputs, Operand<TFloat> min, Operand<TFloat> max,
      FakeQuantWithMinMaxVarsGradient.Options... options) {
    return FakeQuantWithMinMaxVarsGradient.create(scope, gradients, inputs, min, max, options);
  }

  /**
   * Builds an {@link Dequantize} operation
   *
   * @param input 
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param options carries optional attributes values
   * @return a new instance of Dequantize
   * @see org.tensorflow.op.quantization.Dequantize
   */
  public <T> Dequantize dequantize(Operand<T> input, Operand<TFloat> minRange,
      Operand<TFloat> maxRange, Dequantize.Options... options) {
    return Dequantize.create(scope, input, minRange, maxRange, options);
  }

  /**
   * Builds an {@link Requantize} operation
   *
   * @param input 
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @param requestedOutputMin The float value that the minimum quantized output value represents.
   * @param requestedOutputMax The float value that the maximum quantized output value represents.
   * @param outType The type of the output. Should be a lower bit depth than Tinput.
   * @return a new instance of Requantize
   * @see org.tensorflow.op.quantization.Requantize
   */
  public <U, T> Requantize<U> requantize(Operand<T> input, Operand<TFloat> inputMin,
      Operand<TFloat> inputMax, Operand<TFloat> requestedOutputMin,
      Operand<TFloat> requestedOutputMax, DataType<U> outType) {
    return Requantize.create(scope, input, inputMin, inputMax, requestedOutputMin, requestedOutputMax, outType);
  }

  /**
   * Builds an {@link FakeQuantWithMinMaxVarsPerChannelGradient} operation
   *
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxVars operation,
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxVars operation, shape
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxVarsPerChannelGradient
   * @see org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient
   */
  public FakeQuantWithMinMaxVarsPerChannelGradient fakeQuantWithMinMaxVarsPerChannelGradient(
      Operand<TFloat> gradients, Operand<TFloat> inputs, Operand<TFloat> min, Operand<TFloat> max,
      FakeQuantWithMinMaxVarsPerChannelGradient.Options... options) {
    return FakeQuantWithMinMaxVarsPerChannelGradient.create(scope, gradients, inputs, min, max, options);
  }

  /**
   * Builds an {@link QuantizeAndDequantize} operation
   *
   * @param input 
   * @param inputMin 
   * @param inputMax 
   * @param numBits 
   * @param options carries optional attributes values
   * @return a new instance of QuantizeAndDequantize
   * @see org.tensorflow.op.quantization.QuantizeAndDequantize
   */
  public <T extends TNumber> QuantizeAndDequantize<T> quantizeAndDequantize(Operand<T> input,
      Operand<T> inputMin, Operand<T> inputMax, Operand<TInt32> numBits,
      QuantizeAndDequantize.Options... options) {
    return QuantizeAndDequantize.create(scope, input, inputMin, inputMax, numBits, options);
  }

  /**
   * Builds an {@link RequantizationRange} operation
   *
   * @param input 
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @return a new instance of RequantizationRange
   * @see org.tensorflow.op.quantization.RequantizationRange
   */
  public <T> RequantizationRange requantizationRange(Operand<T> input, Operand<TFloat> inputMin,
      Operand<TFloat> inputMax) {
    return RequantizationRange.create(scope, input, inputMin, inputMax);
  }

  /**
   * Builds an {@link FakeQuantWithMinMaxVarsPerChannel} operation
   *
   * @param inputs 
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxVarsPerChannel
   * @see org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannel
   */
  public FakeQuantWithMinMaxVarsPerChannel fakeQuantWithMinMaxVarsPerChannel(Operand<TFloat> inputs,
      Operand<TFloat> min, Operand<TFloat> max,
      FakeQuantWithMinMaxVarsPerChannel.Options... options) {
    return FakeQuantWithMinMaxVarsPerChannel.create(scope, inputs, min, max, options);
  }

  /**
   * Builds an {@link FakeQuantWithMinMaxArgs} operation
   *
   * @param inputs 
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxArgs
   * @see org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs
   */
  public FakeQuantWithMinMaxArgs fakeQuantWithMinMaxArgs(Operand<TFloat> inputs,
      FakeQuantWithMinMaxArgs.Options... options) {
    return FakeQuantWithMinMaxArgs.create(scope, inputs, options);
  }

  /**
   * Builds an {@link QuantizeDownAndShrinkRange} operation
   *
   * @param input 
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @param outType The type of the output. Should be a lower bit depth than Tinput.
   * @return a new instance of QuantizeDownAndShrinkRange
   * @see org.tensorflow.op.quantization.QuantizeDownAndShrinkRange
   */
  public <U, T> QuantizeDownAndShrinkRange<U> quantizeDownAndShrinkRange(Operand<T> input,
      Operand<TFloat> inputMin, Operand<TFloat> inputMax, DataType<U> outType) {
    return QuantizeDownAndShrinkRange.create(scope, input, inputMin, inputMax, outType);
  }

  /**
   * Builds an {@link FakeQuantWithMinMaxVars} operation
   *
   * @param inputs 
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxVars
   * @see org.tensorflow.op.quantization.FakeQuantWithMinMaxVars
   */
  public FakeQuantWithMinMaxVars fakeQuantWithMinMaxVars(Operand<TFloat> inputs,
      Operand<TFloat> min, Operand<TFloat> max, FakeQuantWithMinMaxVars.Options... options) {
    return FakeQuantWithMinMaxVars.create(scope, inputs, min, max, options);
  }

  /**
   * Builds an {@link Quantize} operation
   *
   * @param input 
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param T 
   * @param options carries optional attributes values
   * @return a new instance of Quantize
   * @see org.tensorflow.op.quantization.Quantize
   */
  public <T> Quantize<T> quantize(Operand<TFloat> input, Operand<TFloat> minRange,
      Operand<TFloat> maxRange, DataType<T> T, Quantize.Options... options) {
    return Quantize.create(scope, input, minRange, maxRange, T, options);
  }
}
