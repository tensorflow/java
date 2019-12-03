package org.tensorflow.op;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.nn.AvgPool;
import org.tensorflow.op.nn.AvgPool3d;
import org.tensorflow.op.nn.AvgPool3dGrad;
import org.tensorflow.op.nn.BatchNormWithGlobalNormalization;
import org.tensorflow.op.nn.BatchNormWithGlobalNormalizationGrad;
import org.tensorflow.op.nn.BiasAdd;
import org.tensorflow.op.nn.BiasAddGrad;
import org.tensorflow.op.nn.ComputeAccidentalHits;
import org.tensorflow.op.nn.Conv2d;
import org.tensorflow.op.nn.Conv2dBackpropFilter;
import org.tensorflow.op.nn.Conv2dBackpropInput;
import org.tensorflow.op.nn.Conv3d;
import org.tensorflow.op.nn.Conv3dBackpropFilter;
import org.tensorflow.op.nn.Conv3dBackpropInput;
import org.tensorflow.op.nn.CtcBeamSearchDecoder;
import org.tensorflow.op.nn.CtcGreedyDecoder;
import org.tensorflow.op.nn.CtcLoss;
import org.tensorflow.op.nn.CudnnRnnCanonicalToParams;
import org.tensorflow.op.nn.CudnnRnnParamsSize;
import org.tensorflow.op.nn.CudnnRnnParamsToCanonical;
import org.tensorflow.op.nn.DataFormatDimMap;
import org.tensorflow.op.nn.DataFormatVecPermute;
import org.tensorflow.op.nn.DepthToSpace;
import org.tensorflow.op.nn.DepthwiseConv2dNative;
import org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter;
import org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput;
import org.tensorflow.op.nn.Dilation2d;
import org.tensorflow.op.nn.Dilation2dBackpropFilter;
import org.tensorflow.op.nn.Dilation2dBackpropInput;
import org.tensorflow.op.nn.Elu;
import org.tensorflow.op.nn.FixedUnigramCandidateSampler;
import org.tensorflow.op.nn.FractionalAvgPool;
import org.tensorflow.op.nn.FractionalMaxPool;
import org.tensorflow.op.nn.FusedBatchNorm;
import org.tensorflow.op.nn.FusedBatchNormGrad;
import org.tensorflow.op.nn.FusedPadConv2d;
import org.tensorflow.op.nn.FusedResizeAndPadConv2d;
import org.tensorflow.op.nn.InTopK;
import org.tensorflow.op.nn.L2Loss;
import org.tensorflow.op.nn.LearnedUnigramCandidateSampler;
import org.tensorflow.op.nn.LocalResponseNormalization;
import org.tensorflow.op.nn.LogSoftmax;
import org.tensorflow.op.nn.MaxPool;
import org.tensorflow.op.nn.MaxPool3d;
import org.tensorflow.op.nn.MaxPool3dGrad;
import org.tensorflow.op.nn.MaxPool3dGradGrad;
import org.tensorflow.op.nn.MaxPoolGrad;
import org.tensorflow.op.nn.MaxPoolGradGrad;
import org.tensorflow.op.nn.MaxPoolGradGradWithArgmax;
import org.tensorflow.op.nn.MaxPoolWithArgmax;
import org.tensorflow.op.nn.NthElement;
import org.tensorflow.op.nn.QuantizedAvgPool;
import org.tensorflow.op.nn.QuantizedBatchNormWithGlobalNormalization;
import org.tensorflow.op.nn.QuantizedBiasAdd;
import org.tensorflow.op.nn.QuantizedConv2d;
import org.tensorflow.op.nn.QuantizedInstanceNorm;
import org.tensorflow.op.nn.QuantizedMaxPool;
import org.tensorflow.op.nn.QuantizedRelu;
import org.tensorflow.op.nn.QuantizedRelu6;
import org.tensorflow.op.nn.QuantizedReluX;
import org.tensorflow.op.nn.Relu;
import org.tensorflow.op.nn.Relu6;
import org.tensorflow.op.nn.Selu;
import org.tensorflow.op.nn.Softmax;
import org.tensorflow.op.nn.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.Softsign;
import org.tensorflow.op.nn.SpaceToBatch;
import org.tensorflow.op.nn.SpaceToDepth;
import org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.TopK;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code nn} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class NnOps {
  private final Scope scope;

  NnOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link QuantizedBatchNormWithGlobalNormalization} operation
   *
   * @param t A 4D input Tensor.
   * @param tMin The value represented by the lowest quantized input.
   * @param tMax The value represented by the highest quantized input.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   * @param mMin The value represented by the lowest quantized mean.
   * @param mMax The value represented by the highest quantized mean.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   * @param vMin The value represented by the lowest quantized variance.
   * @param vMax The value represented by the highest quantized variance.
   * @param beta A 1D beta Tensor with size matching the last dimension of t.
   * @param betaMin The value represented by the lowest quantized offset.
   * @param betaMax The value represented by the highest quantized offset.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   * @param gammaMin The value represented by the lowest quantized gamma.
   * @param gammaMax The value represented by the highest quantized gamma.
   * @param outType 
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   * @return a new instance of QuantizedBatchNormWithGlobalNormalization
   * @see org.tensorflow.op.nn.QuantizedBatchNormWithGlobalNormalization
   */
  public <U, T> QuantizedBatchNormWithGlobalNormalization<U> quantizedBatchNormWithGlobalNormalization(
      Operand<T> t, Operand<TFloat> tMin, Operand<TFloat> tMax, Operand<T> m, Operand<TFloat> mMin,
      Operand<TFloat> mMax, Operand<T> v, Operand<TFloat> vMin, Operand<TFloat> vMax,
      Operand<T> beta, Operand<TFloat> betaMin, Operand<TFloat> betaMax, Operand<T> gamma,
      Operand<TFloat> gammaMin, Operand<TFloat> gammaMax, DataType<U> outType,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    return QuantizedBatchNormWithGlobalNormalization.create(scope, t, tMin, tMax, m, mMin, mMax, v, vMin, vMax, beta, betaMin, betaMax, gamma, gammaMin, gammaMax, outType, varianceEpsilon, scaleAfterNormalization);
  }

  /**
   * Builds an {@link DataFormatVecPermute} operation
   *
   * @param x Vector of size 4 or Tensor of shape (4, 2) in source data format.
   * @param options carries optional attributes values
   * @return a new instance of DataFormatVecPermute
   * @see org.tensorflow.op.nn.DataFormatVecPermute
   */
  public <T extends TNumber> DataFormatVecPermute<T> dataFormatVecPermute(Operand<T> x,
      DataFormatVecPermute.Options... options) {
    return DataFormatVecPermute.create(scope, x, options);
  }

  /**
   * Builds an {@link DepthwiseConv2dNative} operation
   *
   * @param input 
   * @param filter 
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of DepthwiseConv2dNative
   * @see org.tensorflow.op.nn.DepthwiseConv2dNative
   */
  public <T extends TNumber> DepthwiseConv2dNative<T> depthwiseConv2dNative(Operand<T> input,
      Operand<T> filter, List<Long> strides, String padding,
      DepthwiseConv2dNative.Options... options) {
    return DepthwiseConv2dNative.create(scope, input, filter, strides, padding, options);
  }

  /**
   * Builds an {@link DepthToSpace} operation
   *
   * @param input 
   * @param blockSize The size of the spatial block, same as in Space2Depth.
   * @param options carries optional attributes values
   * @return a new instance of DepthToSpace
   * @see org.tensorflow.op.nn.DepthToSpace
   */
  public <T> DepthToSpace<T> depthToSpace(Operand<T> input, Long blockSize,
      DepthToSpace.Options... options) {
    return DepthToSpace.create(scope, input, blockSize, options);
  }

  /**
   * Builds an {@link AvgPool} operation
   *
   * @param value 4-D with shape `[batch, height, width, channels]`.
   * @param ksize The size of the sliding window for each dimension of `value`.
   * @param strides The stride of the sliding window for each dimension of `value`.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of AvgPool
   * @see org.tensorflow.op.nn.AvgPool
   */
  public <T extends TNumber> AvgPool<T> avgPool(Operand<T> value, List<Long> ksize,
      List<Long> strides, String padding, AvgPool.Options... options) {
    return AvgPool.create(scope, value, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link MaxPoolWithArgmax} operation
   *
   * @param input 4-D with shape `[batch, height, width, channels]`.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolWithArgmax
   * @see org.tensorflow.op.nn.MaxPoolWithArgmax
   */
  public <T extends TNumber> MaxPoolWithArgmax<T, TInt64> maxPoolWithArgmax(Operand<T> input,
      List<Long> ksize, List<Long> strides, String padding, MaxPoolWithArgmax.Options... options) {
    return MaxPoolWithArgmax.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link ComputeAccidentalHits} operation
   *
   * @param trueClasses The true_classes output of UnpackSparseLabels.
   * @param sampledCandidates The sampled_candidates output of CandidateSampler.
   * @param numTrue Number of true labels per context.
   * @param options carries optional attributes values
   * @return a new instance of ComputeAccidentalHits
   * @see org.tensorflow.op.nn.ComputeAccidentalHits
   */
  public ComputeAccidentalHits computeAccidentalHits(Operand<TInt64> trueClasses,
      Operand<TInt64> sampledCandidates, Long numTrue, ComputeAccidentalHits.Options... options) {
    return ComputeAccidentalHits.create(scope, trueClasses, sampledCandidates, numTrue, options);
  }

  /**
   * Builds an {@link Conv2dBackpropInput} operation
   *
   * @param inputSizes An integer vector representing the shape of `input`,
   * @param filter 4-D with shape
   * @param outBackprop 4-D with shape `[batch, out_height, out_width, out_channels]`.
   * @param strides The stride of the sliding window for each dimension of the input
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv2dBackpropInput
   * @see org.tensorflow.op.nn.Conv2dBackpropInput
   */
  public <T extends TNumber> Conv2dBackpropInput<T> conv2dBackpropInput(Operand<TInt32> inputSizes,
      Operand<T> filter, Operand<T> outBackprop, List<Long> strides, String padding,
      Conv2dBackpropInput.Options... options) {
    return Conv2dBackpropInput.create(scope, inputSizes, filter, outBackprop, strides, padding, options);
  }

  /**
   * Builds an {@link CudnnRnnParamsToCanonical} operation
   *
   * @param numLayers 
   * @param numUnits 
   * @param inputSize 
   * @param params 
   * @param numParams 
   * @param options carries optional attributes values
   * @return a new instance of CudnnRnnParamsToCanonical
   * @see org.tensorflow.op.nn.CudnnRnnParamsToCanonical
   */
  public <T extends TNumber> CudnnRnnParamsToCanonical<T> cudnnRnnParamsToCanonical(
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize,
      Operand<T> params, Long numParams, CudnnRnnParamsToCanonical.Options... options) {
    return CudnnRnnParamsToCanonical.create(scope, numLayers, numUnits, inputSize, params, numParams, options);
  }

  /**
   * Builds an {@link FractionalAvgPool} operation
   *
   * @param value 4-D with shape `[batch, height, width, channels]`.
   * @param poolingRatio Pooling ratio for each dimension of `value`, currently only
   * @param options carries optional attributes values
   * @return a new instance of FractionalAvgPool
   * @see org.tensorflow.op.nn.FractionalAvgPool
   */
  public <T extends TNumber> FractionalAvgPool<T> fractionalAvgPool(Operand<T> value,
      List<Float> poolingRatio, FractionalAvgPool.Options... options) {
    return FractionalAvgPool.create(scope, value, poolingRatio, options);
  }

  /**
   * Builds an {@link SparseSoftmaxCrossEntropyWithLogits} operation
   *
   * @param features batch_size x num_classes matrix
   * @param labels batch_size vector with values in [0, num_classes).
   * @return a new instance of SparseSoftmaxCrossEntropyWithLogits
   * @see org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits
   */
  public <T extends TNumber, U extends TNumber> SparseSoftmaxCrossEntropyWithLogits<T> sparseSoftmaxCrossEntropyWithLogits(
      Operand<T> features, Operand<U> labels) {
    return SparseSoftmaxCrossEntropyWithLogits.create(scope, features, labels);
  }

  /**
   * Builds an {@link QuantizedMaxPool} operation
   *
   * @param input The 4D (batch x rows x cols x depth) Tensor to MaxReduce over.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the input
   * @param padding The type of padding algorithm to use.
   * @return a new instance of QuantizedMaxPool
   * @see org.tensorflow.op.nn.QuantizedMaxPool
   */
  public <T> QuantizedMaxPool<T> quantizedMaxPool(Operand<T> input, Operand<TFloat> minInput,
      Operand<TFloat> maxInput, List<Long> ksize, List<Long> strides, String padding) {
    return QuantizedMaxPool.create(scope, input, minInput, maxInput, ksize, strides, padding);
  }

  /**
   * Builds an {@link NthElement} operation
   *
   * @param input 1-D or higher with last dimension at least `n+1`.
   * @param n 0-D. Position of sorted vector to select along the last dimension (along
   * @param options carries optional attributes values
   * @return a new instance of NthElement
   * @see org.tensorflow.op.nn.NthElement
   */
  public <T extends TNumber> NthElement<T> nthElement(Operand<T> input, Operand<TInt32> n,
      NthElement.Options... options) {
    return NthElement.create(scope, input, n, options);
  }

  /**
   * Builds an {@link QuantizedRelu} operation
   *
   * @param features 
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType 
   * @return a new instance of QuantizedRelu
   * @see org.tensorflow.op.nn.QuantizedRelu
   */
  public <U, T> QuantizedRelu<U> quantizedRelu(Operand<T> features, Operand<TFloat> minFeatures,
      Operand<TFloat> maxFeatures, DataType<U> outType) {
    return QuantizedRelu.create(scope, features, minFeatures, maxFeatures, outType);
  }

  /**
   * Builds an {@link MaxPoolWithArgmax} operation
   *
   * @param input 4-D with shape `[batch, height, width, channels]`.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * @param Targmax 
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolWithArgmax
   * @see org.tensorflow.op.nn.MaxPoolWithArgmax
   */
  public <T extends TNumber, U extends TNumber> MaxPoolWithArgmax<T, U> maxPoolWithArgmax(
      Operand<T> input, List<Long> ksize, List<Long> strides, DataType<U> Targmax, String padding,
      MaxPoolWithArgmax.Options... options) {
    return MaxPoolWithArgmax.create(scope, input, ksize, strides, Targmax, padding, options);
  }

  /**
   * Builds an {@link InTopK} operation
   *
   * @param predictions A `batch_size` x `classes` tensor.
   * @param targets A `batch_size` vector of class ids.
   * @param k Number of top elements to look at for computing precision.
   * @return a new instance of InTopK
   * @see org.tensorflow.op.nn.InTopK
   */
  public <T extends TNumber> InTopK inTopK(Operand<TFloat> predictions, Operand<T> targets,
      Operand<T> k) {
    return InTopK.create(scope, predictions, targets, k);
  }

  /**
   * Builds an {@link L2Loss} operation
   *
   * @param t Typically 2-D, but may have any dimensions.
   * @return a new instance of L2Loss
   * @see org.tensorflow.op.nn.L2Loss
   */
  public <T extends TNumber> L2Loss<T> l2Loss(Operand<T> t) {
    return L2Loss.create(scope, t);
  }

  /**
   * Builds an {@link Conv3d} operation
   *
   * @param input Shape `[batch, in_depth, in_height, in_width, in_channels]`.
   * @param filter Shape `[filter_depth, filter_height, filter_width, in_channels,
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv3d
   * @see org.tensorflow.op.nn.Conv3d
   */
  public <T extends TNumber> Conv3d<T> conv3d(Operand<T> input, Operand<T> filter,
      List<Long> strides, String padding, Conv3d.Options... options) {
    return Conv3d.create(scope, input, filter, strides, padding, options);
  }

  /**
   * Builds an {@link DepthwiseConv2dNativeBackpropFilter} operation
   *
   * @param input 4-D with shape based on `data_format`.  For example, if
   * @param filterSizes An integer vector representing the tensor shape of `filter`,
   * @param outBackprop 4-D with shape  based on `data_format`.
   * @param strides The stride of the sliding window for each dimension of the input
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of DepthwiseConv2dNativeBackpropFilter
   * @see org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter
   */
  public <T extends TNumber> DepthwiseConv2dNativeBackpropFilter<T> depthwiseConv2dNativeBackpropFilter(
      Operand<T> input, Operand<TInt32> filterSizes, Operand<T> outBackprop, List<Long> strides,
      String padding, DepthwiseConv2dNativeBackpropFilter.Options... options) {
    return DepthwiseConv2dNativeBackpropFilter.create(scope, input, filterSizes, outBackprop, strides, padding, options);
  }

  /**
   * Builds an {@link FusedBatchNorm} operation
   *
   * @param x A 4D Tensor for input data.
   * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
   * @param offset A 1D Tensor for offset, to shift to the normalized x.
   * @param mean A 1D Tensor for population mean. Used for inference only;
   * @param variance A 1D Tensor for population variance. Used for inference only;
   * @param options carries optional attributes values
   * @return a new instance of FusedBatchNorm
   * @see org.tensorflow.op.nn.FusedBatchNorm
   */
  public <T extends TNumber, U extends TNumber> FusedBatchNorm<T, U> fusedBatchNorm(Operand<T> x,
      Operand<U> scale, Operand<U> offset, Operand<U> mean, Operand<U> variance,
      FusedBatchNorm.Options... options) {
    return FusedBatchNorm.create(scope, x, scale, offset, mean, variance, options);
  }

  /**
   * Builds an {@link MaxPoolGrad} operation
   *
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad 4-D.  Gradients w.r.t. the output of `max_pool`.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolGrad
   * @see org.tensorflow.op.nn.MaxPoolGrad
   */
  public <T extends TNumber> MaxPoolGrad<T> maxPoolGrad(Operand<T> origInput, Operand<T> origOutput,
      Operand<T> grad, Operand<TInt32> ksize, Operand<TInt32> strides, String padding,
      MaxPoolGrad.Options... options) {
    return MaxPoolGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link QuantizedReluX} operation
   *
   * @param features 
   * @param maxValue 
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType 
   * @return a new instance of QuantizedReluX
   * @see org.tensorflow.op.nn.QuantizedReluX
   */
  public <U, T> QuantizedReluX<U> quantizedReluX(Operand<T> features, Operand<TFloat> maxValue,
      Operand<TFloat> minFeatures, Operand<TFloat> maxFeatures, DataType<U> outType) {
    return QuantizedReluX.create(scope, features, maxValue, minFeatures, maxFeatures, outType);
  }

  /**
   * Builds an {@link MaxPool3dGradGrad} operation
   *
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad Output backprop of shape `[batch, depth, rows, cols, channels]`.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPool3dGradGrad
   * @see org.tensorflow.op.nn.MaxPool3dGradGrad
   */
  public <T extends TNumber> MaxPool3dGradGrad<T> maxPool3dGradGrad(Operand<T> origInput,
      Operand<T> origOutput, Operand<T> grad, List<Long> ksize, List<Long> strides, String padding,
      MaxPool3dGradGrad.Options... options) {
    return MaxPool3dGradGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link MaxPoolGradGradWithArgmax} operation
   *
   * @param input The original input.
   * @param grad 4-D with shape `[batch, height, width, channels]`.  Gradients w.r.t. the
   * @param argmax The indices of the maximum values chosen for each output of `max_pool`.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolGradGradWithArgmax
   * @see org.tensorflow.op.nn.MaxPoolGradGradWithArgmax
   */
  public <T extends TNumber, U extends TNumber> MaxPoolGradGradWithArgmax<T> maxPoolGradGradWithArgmax(
      Operand<T> input, Operand<T> grad, Operand<U> argmax, List<Long> ksize, List<Long> strides,
      String padding, MaxPoolGradGradWithArgmax.Options... options) {
    return MaxPoolGradGradWithArgmax.create(scope, input, grad, argmax, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link MaxPool} operation
   *
   * @param input 4-D input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPool
   * @see org.tensorflow.op.nn.MaxPool
   */
  public <T> MaxPool<T> maxPool(Operand<T> input, Operand<TInt32> ksize, Operand<TInt32> strides,
      String padding, MaxPool.Options... options) {
    return MaxPool.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link MaxPool3dGrad} operation
   *
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad Output backprop of shape `[batch, depth, rows, cols, channels]`.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPool3dGrad
   * @see org.tensorflow.op.nn.MaxPool3dGrad
   */
  public <U extends TNumber, T extends TNumber> MaxPool3dGrad<U> maxPool3dGrad(Operand<T> origInput,
      Operand<T> origOutput, Operand<U> grad, List<Long> ksize, List<Long> strides, String padding,
      MaxPool3dGrad.Options... options) {
    return MaxPool3dGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link CudnnRnnParamsSize} operation
   *
   * @param numLayers 
   * @param numUnits 
   * @param inputSize 
   * @param T 
   * @param S 
   * @param options carries optional attributes values
   * @return a new instance of CudnnRnnParamsSize
   * @see org.tensorflow.op.nn.CudnnRnnParamsSize
   */
  public <U extends TNumber, T extends TNumber> CudnnRnnParamsSize<U> cudnnRnnParamsSize(
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize, DataType<T> T,
      DataType<U> S, CudnnRnnParamsSize.Options... options) {
    return CudnnRnnParamsSize.create(scope, numLayers, numUnits, inputSize, T, S, options);
  }

  /**
   * Builds an {@link MaxPoolGradGrad} operation
   *
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad 4-D.  Gradients of gradients w.r.t. the input of `max_pool`.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPoolGradGrad
   * @see org.tensorflow.op.nn.MaxPoolGradGrad
   */
  public <T extends TNumber> MaxPoolGradGrad<T> maxPoolGradGrad(Operand<T> origInput,
      Operand<T> origOutput, Operand<T> grad, Operand<TInt32> ksize, Operand<TInt32> strides,
      String padding, MaxPoolGradGrad.Options... options) {
    return MaxPoolGradGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link SoftmaxCrossEntropyWithLogits} operation
   *
   * @param features batch_size x num_classes matrix
   * @param labels batch_size x num_classes matrix
   * @return a new instance of SoftmaxCrossEntropyWithLogits
   * @see org.tensorflow.op.nn.SoftmaxCrossEntropyWithLogits
   */
  public <T extends TNumber> SoftmaxCrossEntropyWithLogits<T> softmaxCrossEntropyWithLogits(
      Operand<T> features, Operand<T> labels) {
    return SoftmaxCrossEntropyWithLogits.create(scope, features, labels);
  }

  /**
   * Builds an {@link DepthwiseConv2dNativeBackpropInput} operation
   *
   * @param inputSizes An integer vector representing the shape of `input`, based
   * @param filter 4-D with shape
   * @param outBackprop 4-D with shape  based on `data_format`.
   * @param strides The stride of the sliding window for each dimension of the input
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of DepthwiseConv2dNativeBackpropInput
   * @see org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput
   */
  public <T extends TNumber> DepthwiseConv2dNativeBackpropInput<T> depthwiseConv2dNativeBackpropInput(
      Operand<TInt32> inputSizes, Operand<T> filter, Operand<T> outBackprop, List<Long> strides,
      String padding, DepthwiseConv2dNativeBackpropInput.Options... options) {
    return DepthwiseConv2dNativeBackpropInput.create(scope, inputSizes, filter, outBackprop, strides, padding, options);
  }

  /**
   * Builds an {@link QuantizedConv2d} operation
   *
   * @param input 
   * @param filter filter's input_depth dimension must match input's depth dimensions.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minFilter The float value that the lowest quantized filter value represents.
   * @param maxFilter The float value that the highest quantized filter value represents.
   * @param outType 
   * @param strides The stride of the sliding window for each dimension of the input
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of QuantizedConv2d
   * @see org.tensorflow.op.nn.QuantizedConv2d
   */
  public <V, T, U> QuantizedConv2d<V> quantizedConv2d(Operand<T> input, Operand<U> filter,
      Operand<TFloat> minInput, Operand<TFloat> maxInput, Operand<TFloat> minFilter,
      Operand<TFloat> maxFilter, DataType<V> outType, List<Long> strides, String padding,
      QuantizedConv2d.Options... options) {
    return QuantizedConv2d.create(scope, input, filter, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * Builds an {@link Relu} operation
   *
   * @param features 
   * @return a new instance of Relu
   * @see org.tensorflow.op.nn.Relu
   */
  public <T> Relu<T> relu(Operand<T> features) {
    return Relu.create(scope, features);
  }

  /**
   * Builds an {@link SpaceToDepth} operation
   *
   * @param input 
   * @param blockSize The size of the spatial block.
   * @param options carries optional attributes values
   * @return a new instance of SpaceToDepth
   * @see org.tensorflow.op.nn.SpaceToDepth
   */
  public <T> SpaceToDepth<T> spaceToDepth(Operand<T> input, Long blockSize,
      SpaceToDepth.Options... options) {
    return SpaceToDepth.create(scope, input, blockSize, options);
  }

  /**
   * Builds an {@link CtcLoss} operation
   *
   * @param inputs 3-D, shape: `(max_time x batch_size x num_classes)`, the logits.
   * @param labelsIndices The indices of a `SparseTensor<int32, 2>`.
   * @param labelsValues The values (labels) associated with the given batch and time.
   * @param sequenceLength A vector containing sequence lengths (batch).
   * @param options carries optional attributes values
   * @return a new instance of CtcLoss
   * @see org.tensorflow.op.nn.CtcLoss
   */
  public CtcLoss ctcLoss(Operand<TFloat> inputs, Operand<TInt64> labelsIndices,
      Operand<TInt32> labelsValues, Operand<TInt32> sequenceLength, CtcLoss.Options... options) {
    return CtcLoss.create(scope, inputs, labelsIndices, labelsValues, sequenceLength, options);
  }

  /**
   * Builds an {@link LocalResponseNormalization} operation
   *
   * @param input 4-D.
   * @param options carries optional attributes values
   * @return a new instance of LocalResponseNormalization
   * @see org.tensorflow.op.nn.LocalResponseNormalization
   */
  public <T extends TNumber> LocalResponseNormalization<T> localResponseNormalization(
      Operand<T> input, LocalResponseNormalization.Options... options) {
    return LocalResponseNormalization.create(scope, input, options);
  }

  /**
   * Builds an {@link LearnedUnigramCandidateSampler} operation
   *
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to randomly sample.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attributes values
   * @return a new instance of LearnedUnigramCandidateSampler
   * @see org.tensorflow.op.nn.LearnedUnigramCandidateSampler
   */
  public LearnedUnigramCandidateSampler learnedUnigramCandidateSampler(Operand<TInt64> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax,
      LearnedUnigramCandidateSampler.Options... options) {
    return LearnedUnigramCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Builds an {@link AvgPool3dGrad} operation
   *
   * @param origInputShape The original input dimensions.
   * @param grad Output backprop of shape `[batch, depth, rows, cols, channels]`.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of AvgPool3dGrad
   * @see org.tensorflow.op.nn.AvgPool3dGrad
   */
  public <T extends TNumber> AvgPool3dGrad<T> avgPool3dGrad(Operand<TInt32> origInputShape,
      Operand<T> grad, List<Long> ksize, List<Long> strides, String padding,
      AvgPool3dGrad.Options... options) {
    return AvgPool3dGrad.create(scope, origInputShape, grad, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link BatchNormWithGlobalNormalizationGrad} operation
   *
   * @param t A 4D input Tensor.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   * @param backprop 4D backprop Tensor.
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   * @return a new instance of BatchNormWithGlobalNormalizationGrad
   * @see org.tensorflow.op.nn.BatchNormWithGlobalNormalizationGrad
   */
  public <T> BatchNormWithGlobalNormalizationGrad<T> batchNormWithGlobalNormalizationGrad(
      Operand<T> t, Operand<T> m, Operand<T> v, Operand<T> gamma, Operand<T> backprop,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    return BatchNormWithGlobalNormalizationGrad.create(scope, t, m, v, gamma, backprop, varianceEpsilon, scaleAfterNormalization);
  }

  /**
   * Builds an {@link Selu} operation
   *
   * @param features 
   * @return a new instance of Selu
   * @see org.tensorflow.op.nn.Selu
   */
  public <T extends TNumber> Selu<T> selu(Operand<T> features) {
    return Selu.create(scope, features);
  }

  /**
   * Builds an {@link Conv3dBackpropFilter} operation
   *
   * @param input Shape `[batch, depth, rows, cols, in_channels]`.
   * @param filterSizes An integer vector representing the tensor shape of `filter`,
   * @param outBackprop Backprop signal of shape `[batch, out_depth, out_rows, out_cols,
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv3dBackpropFilter
   * @see org.tensorflow.op.nn.Conv3dBackpropFilter
   */
  public <T extends TNumber> Conv3dBackpropFilter<T> conv3dBackpropFilter(Operand<T> input,
      Operand<TInt32> filterSizes, Operand<T> outBackprop, List<Long> strides, String padding,
      Conv3dBackpropFilter.Options... options) {
    return Conv3dBackpropFilter.create(scope, input, filterSizes, outBackprop, strides, padding, options);
  }

  /**
   * Builds an {@link BatchNormWithGlobalNormalization} operation
   *
   * @param t A 4D input Tensor.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   * @param beta A 1D beta Tensor with size matching the last dimension of t.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   * @return a new instance of BatchNormWithGlobalNormalization
   * @see org.tensorflow.op.nn.BatchNormWithGlobalNormalization
   */
  public <T> BatchNormWithGlobalNormalization<T> batchNormWithGlobalNormalization(Operand<T> t,
      Operand<T> m, Operand<T> v, Operand<T> beta, Operand<T> gamma, Float varianceEpsilon,
      Boolean scaleAfterNormalization) {
    return BatchNormWithGlobalNormalization.create(scope, t, m, v, beta, gamma, varianceEpsilon, scaleAfterNormalization);
  }

  /**
   * Builds an {@link AvgPool3d} operation
   *
   * @param input Shape `[batch, depth, rows, cols, channels]` tensor to pool over.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of AvgPool3d
   * @see org.tensorflow.op.nn.AvgPool3d
   */
  public <T extends TNumber> AvgPool3d<T> avgPool3d(Operand<T> input, List<Long> ksize,
      List<Long> strides, String padding, AvgPool3d.Options... options) {
    return AvgPool3d.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link QuantizedBiasAdd} operation
   *
   * @param input 
   * @param bias A 1D bias Tensor with size matching the last dimension of 'input'.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minBias The float value that the lowest quantized bias value represents.
   * @param maxBias The float value that the highest quantized bias value represents.
   * @param outType 
   * @return a new instance of QuantizedBiasAdd
   * @see org.tensorflow.op.nn.QuantizedBiasAdd
   */
  public <V, T, U> QuantizedBiasAdd<V> quantizedBiasAdd(Operand<T> input, Operand<U> bias,
      Operand<TFloat> minInput, Operand<TFloat> maxInput, Operand<TFloat> minBias,
      Operand<TFloat> maxBias, DataType<V> outType) {
    return QuantizedBiasAdd.create(scope, input, bias, minInput, maxInput, minBias, maxBias, outType);
  }

  /**
   * Builds an {@link DataFormatDimMap} operation
   *
   * @param x A Tensor with each element as a dimension index in source data format.
   * @param options carries optional attributes values
   * @return a new instance of DataFormatDimMap
   * @see org.tensorflow.op.nn.DataFormatDimMap
   */
  public <T extends TNumber> DataFormatDimMap<T> dataFormatDimMap(Operand<T> x,
      DataFormatDimMap.Options... options) {
    return DataFormatDimMap.create(scope, x, options);
  }

  /**
   * Builds an {@link TopK} operation
   *
   * @param input 1-D or higher with last dimension at least `k`.
   * @param k 0-D.  Number of top elements to look for along the last dimension (along each
   * @param options carries optional attributes values
   * @return a new instance of TopK
   * @see org.tensorflow.op.nn.TopK
   */
  public <T extends TNumber> TopK<T> topK(Operand<T> input, Operand<TInt32> k,
      TopK.Options... options) {
    return TopK.create(scope, input, k, options);
  }

  /**
   * Builds an {@link QuantizedInstanceNorm} operation
   *
   * @param x A 4D input Tensor.
   * @param xMin The value represented by the lowest quantized input.
   * @param xMax The value represented by the highest quantized input.
   * @param options carries optional attributes values
   * @return a new instance of QuantizedInstanceNorm
   * @see org.tensorflow.op.nn.QuantizedInstanceNorm
   */
  public <T> QuantizedInstanceNorm<T> quantizedInstanceNorm(Operand<T> x, Operand<TFloat> xMin,
      Operand<TFloat> xMax, QuantizedInstanceNorm.Options... options) {
    return QuantizedInstanceNorm.create(scope, x, xMin, xMax, options);
  }

  /**
   * Builds an {@link BiasAddGrad} operation
   *
   * @param outBackprop Any number of dimensions.
   * @param options carries optional attributes values
   * @return a new instance of BiasAddGrad
   * @see org.tensorflow.op.nn.BiasAddGrad
   */
  public <T> BiasAddGrad<T> biasAddGrad(Operand<T> outBackprop, BiasAddGrad.Options... options) {
    return BiasAddGrad.create(scope, outBackprop, options);
  }

  /**
   * Builds an {@link LogSoftmax} operation
   *
   * @param logits 2-D with shape `[batch_size, num_classes]`.
   * @return a new instance of LogSoftmax
   * @see org.tensorflow.op.nn.LogSoftmax
   */
  public <T extends TNumber> LogSoftmax<T> logSoftmax(Operand<T> logits) {
    return LogSoftmax.create(scope, logits);
  }

  /**
   * Builds an {@link Dilation2dBackpropInput} operation
   *
   * @param input 4-D with shape `[batch, in_height, in_width, depth]`.
   * @param filter 3-D with shape `[filter_height, filter_width, depth]`.
   * @param outBackprop 4-D with shape `[batch, out_height, out_width, depth]`.
   * @param strides 1-D of length 4. The stride of the sliding window for each dimension of
   * @param rates 1-D of length 4. The input stride for atrous morphological dilation.
   * @param padding The type of padding algorithm to use.
   * @return a new instance of Dilation2dBackpropInput
   * @see org.tensorflow.op.nn.Dilation2dBackpropInput
   */
  public <T extends TNumber> Dilation2dBackpropInput<T> dilation2dBackpropInput(Operand<T> input,
      Operand<T> filter, Operand<T> outBackprop, List<Long> strides, List<Long> rates,
      String padding) {
    return Dilation2dBackpropInput.create(scope, input, filter, outBackprop, strides, rates, padding);
  }

  /**
   * Builds an {@link Relu6} operation
   *
   * @param features 
   * @return a new instance of Relu6
   * @see org.tensorflow.op.nn.Relu6
   */
  public <T extends TNumber> Relu6<T> relu6(Operand<T> features) {
    return Relu6.create(scope, features);
  }

  /**
   * Builds an {@link CtcBeamSearchDecoder} operation
   *
   * @param inputs 3-D, shape: `(max_time x batch_size x num_classes)`, the logits.
   * @param sequenceLength A vector containing sequence lengths, size `(batch)`.
   * @param beamWidth A scalar >= 0 (beam search beam width).
   * @param topPaths A scalar >= 0, <= beam_width (controls output size).
   * @param options carries optional attributes values
   * @return a new instance of CtcBeamSearchDecoder
   * @see org.tensorflow.op.nn.CtcBeamSearchDecoder
   */
  public CtcBeamSearchDecoder ctcBeamSearchDecoder(Operand<TFloat> inputs,
      Operand<TInt32> sequenceLength, Long beamWidth, Long topPaths,
      CtcBeamSearchDecoder.Options... options) {
    return CtcBeamSearchDecoder.create(scope, inputs, sequenceLength, beamWidth, topPaths, options);
  }

  /**
   * Builds an {@link Softmax} operation
   *
   * @param logits 2-D with shape `[batch_size, num_classes]`.
   * @return a new instance of Softmax
   * @see org.tensorflow.op.nn.Softmax
   */
  public <T extends TNumber> Softmax<T> softmax(Operand<T> logits) {
    return Softmax.create(scope, logits);
  }

  /**
   * Builds an {@link Conv2d} operation
   *
   * @param input A 4-D tensor. The dimension order is interpreted according to the value
   * @param filter A 4-D tensor of shape
   * @param strides 1-D tensor of length 4.  The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv2d
   * @see org.tensorflow.op.nn.Conv2d
   */
  public <T extends TNumber> Conv2d<T> conv2d(Operand<T> input, Operand<T> filter,
      List<Long> strides, String padding, Conv2d.Options... options) {
    return Conv2d.create(scope, input, filter, strides, padding, options);
  }

  /**
   * Builds an {@link QuantizedRelu6} operation
   *
   * @param features 
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType 
   * @return a new instance of QuantizedRelu6
   * @see org.tensorflow.op.nn.QuantizedRelu6
   */
  public <U, T> QuantizedRelu6<U> quantizedRelu6(Operand<T> features, Operand<TFloat> minFeatures,
      Operand<TFloat> maxFeatures, DataType<U> outType) {
    return QuantizedRelu6.create(scope, features, minFeatures, maxFeatures, outType);
  }

  /**
   * Builds an {@link QuantizedAvgPool} operation
   *
   * @param input 4-D with shape `[batch, height, width, channels]`.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the input
   * @param padding The type of padding algorithm to use.
   * @return a new instance of QuantizedAvgPool
   * @see org.tensorflow.op.nn.QuantizedAvgPool
   */
  public <T> QuantizedAvgPool<T> quantizedAvgPool(Operand<T> input, Operand<TFloat> minInput,
      Operand<TFloat> maxInput, List<Long> ksize, List<Long> strides, String padding) {
    return QuantizedAvgPool.create(scope, input, minInput, maxInput, ksize, strides, padding);
  }

  /**
   * Builds an {@link Conv3dBackpropInput} operation
   *
   * @param inputSizes An integer vector representing the tensor shape of `input`,
   * @param filter Shape `[depth, rows, cols, in_channels, out_channels]`.
   * @param outBackprop Backprop signal of shape `[batch, out_depth, out_rows, out_cols,
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv3dBackpropInput
   * @see org.tensorflow.op.nn.Conv3dBackpropInput
   */
  public <U extends TNumber, T extends TNumber> Conv3dBackpropInput<U> conv3dBackpropInput(
      Operand<T> inputSizes, Operand<U> filter, Operand<U> outBackprop, List<Long> strides,
      String padding, Conv3dBackpropInput.Options... options) {
    return Conv3dBackpropInput.create(scope, inputSizes, filter, outBackprop, strides, padding, options);
  }

  /**
   * Builds an {@link FusedPadConv2d} operation
   *
   * @param input 4-D with shape `[batch, in_height, in_width, in_channels]`.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * @param filter 4-D with shape
   * @param mode 
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   * @param padding The type of padding algorithm to use.
   * @return a new instance of FusedPadConv2d
   * @see org.tensorflow.op.nn.FusedPadConv2d
   */
  public <T extends TNumber> FusedPadConv2d<T> fusedPadConv2d(Operand<T> input,
      Operand<TInt32> paddings, Operand<T> filter, String mode, List<Long> strides,
      String padding) {
    return FusedPadConv2d.create(scope, input, paddings, filter, mode, strides, padding);
  }

  /**
   * Builds an {@link FixedUnigramCandidateSampler} operation
   *
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to randomly sample.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attributes values
   * @return a new instance of FixedUnigramCandidateSampler
   * @see org.tensorflow.op.nn.FixedUnigramCandidateSampler
   */
  public FixedUnigramCandidateSampler fixedUnigramCandidateSampler(Operand<TInt64> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax,
      FixedUnigramCandidateSampler.Options... options) {
    return FixedUnigramCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Builds an {@link Dilation2dBackpropFilter} operation
   *
   * @param input 4-D with shape `[batch, in_height, in_width, depth]`.
   * @param filter 3-D with shape `[filter_height, filter_width, depth]`.
   * @param outBackprop 4-D with shape `[batch, out_height, out_width, depth]`.
   * @param strides 1-D of length 4. The stride of the sliding window for each dimension of
   * @param rates 1-D of length 4. The input stride for atrous morphological dilation.
   * @param padding The type of padding algorithm to use.
   * @return a new instance of Dilation2dBackpropFilter
   * @see org.tensorflow.op.nn.Dilation2dBackpropFilter
   */
  public <T extends TNumber> Dilation2dBackpropFilter<T> dilation2dBackpropFilter(Operand<T> input,
      Operand<T> filter, Operand<T> outBackprop, List<Long> strides, List<Long> rates,
      String padding) {
    return Dilation2dBackpropFilter.create(scope, input, filter, outBackprop, strides, rates, padding);
  }

  /**
   * Builds an {@link Elu} operation
   *
   * @param features 
   * @return a new instance of Elu
   * @see org.tensorflow.op.nn.Elu
   */
  public <T extends TNumber> Elu<T> elu(Operand<T> features) {
    return Elu.create(scope, features);
  }

  /**
   * Builds an {@link Dilation2d} operation
   *
   * @param input 4-D with shape `[batch, in_height, in_width, depth]`.
   * @param filter 3-D with shape `[filter_height, filter_width, depth]`.
   * @param strides The stride of the sliding window for each dimension of the input
   * @param rates The input stride for atrous morphological dilation. Must be:
   * @param padding The type of padding algorithm to use.
   * @return a new instance of Dilation2d
   * @see org.tensorflow.op.nn.Dilation2d
   */
  public <T extends TNumber> Dilation2d<T> dilation2d(Operand<T> input, Operand<T> filter,
      List<Long> strides, List<Long> rates, String padding) {
    return Dilation2d.create(scope, input, filter, strides, rates, padding);
  }

  /**
   * Builds an {@link MaxPool3d} operation
   *
   * @param input Shape `[batch, depth, rows, cols, channels]` tensor to pool over.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of MaxPool3d
   * @see org.tensorflow.op.nn.MaxPool3d
   */
  public <T extends TNumber> MaxPool3d<T> maxPool3d(Operand<T> input, List<Long> ksize,
      List<Long> strides, String padding, MaxPool3d.Options... options) {
    return MaxPool3d.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Builds an {@link Conv2dBackpropFilter} operation
   *
   * @param input 4-D with shape `[batch, in_height, in_width, in_channels]`.
   * @param filterSizes An integer vector representing the tensor shape of `filter`,
   * @param outBackprop 4-D with shape `[batch, out_height, out_width, out_channels]`.
   * @param strides The stride of the sliding window for each dimension of the input
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv2dBackpropFilter
   * @see org.tensorflow.op.nn.Conv2dBackpropFilter
   */
  public <T extends TNumber> Conv2dBackpropFilter<T> conv2dBackpropFilter(Operand<T> input,
      Operand<TInt32> filterSizes, Operand<T> outBackprop, List<Long> strides, String padding,
      Conv2dBackpropFilter.Options... options) {
    return Conv2dBackpropFilter.create(scope, input, filterSizes, outBackprop, strides, padding, options);
  }

  /**
   * Builds an {@link CudnnRnnCanonicalToParams} operation
   *
   * @param numLayers 
   * @param numUnits 
   * @param inputSize 
   * @param weights 
   * @param biases 
   * @param options carries optional attributes values
   * @return a new instance of CudnnRnnCanonicalToParams
   * @see org.tensorflow.op.nn.CudnnRnnCanonicalToParams
   */
  public <T extends TNumber> CudnnRnnCanonicalToParams<T> cudnnRnnCanonicalToParams(
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize,
      Iterable<Operand<T>> weights, Iterable<Operand<T>> biases,
      CudnnRnnCanonicalToParams.Options... options) {
    return CudnnRnnCanonicalToParams.create(scope, numLayers, numUnits, inputSize, weights, biases, options);
  }

  /**
   * Builds an {@link CtcGreedyDecoder} operation
   *
   * @param inputs 3-D, shape: `(max_time x batch_size x num_classes)`, the logits.
   * @param sequenceLength A vector containing sequence lengths, size `(batch_size)`.
   * @param options carries optional attributes values
   * @return a new instance of CtcGreedyDecoder
   * @see org.tensorflow.op.nn.CtcGreedyDecoder
   */
  public CtcGreedyDecoder ctcGreedyDecoder(Operand<TFloat> inputs, Operand<TInt32> sequenceLength,
      CtcGreedyDecoder.Options... options) {
    return CtcGreedyDecoder.create(scope, inputs, sequenceLength, options);
  }

  /**
   * Builds an {@link FusedResizeAndPadConv2d} operation
   *
   * @param input 4-D with shape `[batch, in_height, in_width, in_channels]`.
   * @param size A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * @param filter 4-D with shape
   * @param mode 
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of FusedResizeAndPadConv2d
   * @see org.tensorflow.op.nn.FusedResizeAndPadConv2d
   */
  public <T extends TNumber> FusedResizeAndPadConv2d<T> fusedResizeAndPadConv2d(Operand<T> input,
      Operand<TInt32> size, Operand<TInt32> paddings, Operand<T> filter, String mode,
      List<Long> strides, String padding, FusedResizeAndPadConv2d.Options... options) {
    return FusedResizeAndPadConv2d.create(scope, input, size, paddings, filter, mode, strides, padding, options);
  }

  /**
   * Builds an {@link FractionalMaxPool} operation
   *
   * @param value 4-D with shape `[batch, height, width, channels]`.
   * @param poolingRatio Pooling ratio for each dimension of `value`, currently only
   * @param options carries optional attributes values
   * @return a new instance of FractionalMaxPool
   * @see org.tensorflow.op.nn.FractionalMaxPool
   */
  public <T extends TNumber> FractionalMaxPool<T> fractionalMaxPool(Operand<T> value,
      List<Float> poolingRatio, FractionalMaxPool.Options... options) {
    return FractionalMaxPool.create(scope, value, poolingRatio, options);
  }

  /**
   * Builds an {@link FusedBatchNormGrad} operation
   *
   * @param yBackprop A 4D Tensor for the gradient with respect to y.
   * @param x A 4D Tensor for input data.
   * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
   * @param reserveSpace1 When is_training is True, a 1D Tensor for the computed batch
   * @param reserveSpace2 When is_training is True, a 1D Tensor for the computed batch
   * @param options carries optional attributes values
   * @return a new instance of FusedBatchNormGrad
   * @see org.tensorflow.op.nn.FusedBatchNormGrad
   */
  public <T extends TNumber, U extends TNumber> FusedBatchNormGrad<T, U> fusedBatchNormGrad(
      Operand<T> yBackprop, Operand<T> x, Operand<TFloat> scale, Operand<U> reserveSpace1,
      Operand<U> reserveSpace2, FusedBatchNormGrad.Options... options) {
    return FusedBatchNormGrad.create(scope, yBackprop, x, scale, reserveSpace1, reserveSpace2, options);
  }

  /**
   * Builds an {@link Softsign} operation
   *
   * @param features 
   * @return a new instance of Softsign
   * @see org.tensorflow.op.nn.Softsign
   */
  public <T extends TNumber> Softsign<T> softsign(Operand<T> features) {
    return Softsign.create(scope, features);
  }

  /**
   * Builds an {@link SpaceToBatch} operation
   *
   * @param input 4-D with shape `[batch, height, width, depth]`.
   * @param paddings 2-D tensor of non-negative integers with shape `[2, 2]`. It specifies
   * @param blockSize 
   * @return a new instance of SpaceToBatch
   * @see org.tensorflow.op.nn.SpaceToBatch
   */
  public <T, U extends TNumber> SpaceToBatch<T> spaceToBatch(Operand<T> input, Operand<U> paddings,
      Long blockSize) {
    return SpaceToBatch.create(scope, input, paddings, blockSize);
  }

  /**
   * Builds an {@link BiasAdd} operation
   *
   * @param value Any number of dimensions.
   * @param bias 1-D with size the last dimension of `value`.
   * @param options carries optional attributes values
   * @return a new instance of BiasAdd
   * @see org.tensorflow.op.nn.BiasAdd
   */
  public <T> BiasAdd<T> biasAdd(Operand<T> value, Operand<T> bias, BiasAdd.Options... options) {
    return BiasAdd.create(scope, value, bias, options);
  }
}
