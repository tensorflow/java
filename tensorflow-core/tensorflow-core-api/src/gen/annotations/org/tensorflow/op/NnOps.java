// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import java.util.List;
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
import org.tensorflow.op.nn.CudnnRNNCanonicalToParams;
import org.tensorflow.op.nn.CudnnRNNParamsToCanonical;
import org.tensorflow.op.nn.CudnnRnnParamsSize;
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
import org.tensorflow.op.nn.LeakyRelu;
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
import org.tensorflow.op.nn.SigmoidCrossEntropyWithLogits;
import org.tensorflow.op.nn.Softmax;
import org.tensorflow.op.nn.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.Softsign;
import org.tensorflow.op.nn.SpaceToBatch;
import org.tensorflow.op.nn.SpaceToDepth;
import org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.TopK;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code nn} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class NnOps {
  public final NnRawOps raw;

  private final Scope scope;

  private final Ops ops;

  NnOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
    raw = new NnRawOps(ops);
  }

  /**
   * Performs average pooling on the input.
   *  Each entry in {@code output} is the mean of the corresponding size {@code ksize}
   *  window in {@code value}.
   *
   * @param <T> data type for {@code output} output
   * @param value 4-D with shape {@code [batch, height, width, channels]}.
   * @param ksize The size of the sliding window for each dimension of {@code value}.
   * @param strides The stride of the sliding window for each dimension of {@code value}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AvgPool} output and operands
   * @return a new instance of AvgPool
   */
  public <T extends TNumber> AvgPool<T> avgPool(Operand<T> value, List<Long> ksize,
      List<Long> strides, String padding, AvgPool.Options... options) {
    return AvgPool.create(scope, value, ksize, strides, padding, options);
  }

  /**
   * Performs 3D average pooling on the input.
   *  Each entry in {@code output} is the mean of the corresponding size {@code ksize} window in
   *  {@code value}.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape {@code [batch, depth, rows, cols, channels]} tensor to pool over.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   *  the input tensor. Must have {@code ksize[0] = ksize[4] = 1}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AvgPool3D} output and operands
   * @return a new instance of AvgPool3d
   */
  public <T extends TNumber> AvgPool3d<T> avgPool3d(Operand<T> input, List<Long> ksize,
      List<Long> strides, String padding, AvgPool3d.Options... options) {
    return AvgPool3d.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Computes gradients of average pooling function.
   *
   * @param <T> data type for {@code output} output
   * @param origInputShape The original input dimensions.
   * @param grad Output backprop of shape {@code [batch, depth, rows, cols, channels]}.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   *  the input tensor. Must have {@code ksize[0] = ksize[4] = 1}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AvgPool3DGrad} output and operands
   * @return a new instance of AvgPool3dGrad
   */
  public <T extends TNumber> AvgPool3dGrad<T> avgPool3dGrad(Operand<TInt32> origInputShape,
      Operand<T> grad, List<Long> ksize, List<Long> strides, String padding,
      AvgPool3dGrad.Options... options) {
    return AvgPool3dGrad.create(scope, origInputShape, grad, ksize, strides, padding, options);
  }

  /**
   * Batch normalization.
   *  This op is deprecated. Prefer {@code tf.nn.batch_normalization}.
   *
   * @param <T> data type for {@code result} output
   * @param t A 4D input Tensor.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   *  This is the first output from tf.nn.moments,
   *  or a saved moving average thereof.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   *  This is the second output from tf.nn.moments,
   *  or a saved moving average thereof.
   * @param beta A 1D beta Tensor with size matching the last dimension of t.
   *  An offset to be added to the normalized tensor.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   *  If &quot;scale_after_normalization&quot; is true, this tensor will be multiplied
   *  with the normalized tensor.
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   *  needs to be multiplied with gamma.
   * @param <T> data type for {@code BatchNormWithGlobalNormalization} output and operands
   * @return a new instance of BatchNormWithGlobalNormalization
   */
  public <T extends TType> BatchNormWithGlobalNormalization<T> batchNormWithGlobalNormalization(
      Operand<T> t, Operand<T> m, Operand<T> v, Operand<T> beta, Operand<T> gamma,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    return BatchNormWithGlobalNormalization.create(scope, t, m, v, beta, gamma, varianceEpsilon, scaleAfterNormalization);
  }

  /**
   * Gradients for batch normalization.
   *  This op is deprecated. See {@code tf.nn.batch_normalization}.
   *
   * @param <T> data type for {@code dx} output
   * @param t A 4D input Tensor.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   *  This is the first output from tf.nn.moments,
   *  or a saved moving average thereof.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   *  This is the second output from tf.nn.moments,
   *  or a saved moving average thereof.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   *  If &quot;scale_after_normalization&quot; is true, this Tensor will be multiplied
   *  with the normalized Tensor.
   * @param backprop 4D backprop Tensor.
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   *  needs to be multiplied with gamma.
   * @param <T> data type for {@code BatchNormWithGlobalNormalizationGrad} output and operands
   * @return a new instance of BatchNormWithGlobalNormalizationGrad
   */
  public <T extends TType> BatchNormWithGlobalNormalizationGrad<T> batchNormWithGlobalNormalizationGrad(
      Operand<T> t, Operand<T> m, Operand<T> v, Operand<T> gamma, Operand<T> backprop,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    return BatchNormWithGlobalNormalizationGrad.create(scope, t, m, v, gamma, backprop, varianceEpsilon, scaleAfterNormalization);
  }

  /**
   * Adds {@code bias} to {@code value}.
   *  This is a special case of {@code tf.add} where {@code bias} is restricted to be 1-D.
   *  Broadcasting is supported, so {@code value} may have any number of dimensions.
   *
   * @param <T> data type for {@code output} output
   * @param value Any number of dimensions.
   * @param bias 1-D with size the last dimension of {@code value}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code BiasAdd} output and operands
   * @return a new instance of BiasAdd
   */
  public <T extends TType> BiasAdd<T> biasAdd(Operand<T> value, Operand<T> bias,
      BiasAdd.Options... options) {
    return BiasAdd.create(scope, value, bias, options);
  }

  /**
   * The backward operation for &quot;BiasAdd&quot; on the &quot;bias&quot; tensor.
   *  It accumulates all the values from out_backprop into the feature dimension.
   *  For NHWC data format, the feature dimension is the last. For NCHW data format,
   *  the feature dimension is the third-to-last.
   *
   * @param <T> data type for {@code output} output
   * @param outBackprop Any number of dimensions.
   * @param options carries optional attribute values
   * @param <T> data type for {@code BiasAddGrad} output and operands
   * @return a new instance of BiasAddGrad
   */
  public <T extends TType> BiasAddGrad<T> biasAddGrad(Operand<T> outBackprop,
      BiasAddGrad.Options... options) {
    return BiasAddGrad.create(scope, outBackprop, options);
  }

  /**
   * Computes the ids of the positions in sampled_candidates that match true_labels.
   *  When doing log-odds NCE, the result of this op should be passed through a
   *  SparseToDense op, then added to the logits of the sampled candidates. This has
   *  the effect of 'removing' the sampled labels that match the true labels by
   *  making the classifier sure that they are sampled labels.
   *
   * @param trueClasses The true_classes output of UnpackSparseLabels.
   * @param sampledCandidates The sampled_candidates output of CandidateSampler.
   * @param numTrue Number of true labels per context.
   * @param options carries optional attribute values
   * @return a new instance of ComputeAccidentalHits
   */
  public ComputeAccidentalHits computeAccidentalHits(Operand<TInt64> trueClasses,
      Operand<TInt64> sampledCandidates, Long numTrue, ComputeAccidentalHits.Options... options) {
    return ComputeAccidentalHits.create(scope, trueClasses, sampledCandidates, numTrue, options);
  }

  /**
   * Computes a 2-D convolution given 4-D {@code input} and {@code filter} tensors.
   *  Given an input tensor of shape {@code [batch, in_height, in_width, in_channels]}
   *  and a filter / kernel tensor of shape
   *  {@code [filter_height, filter_width, in_channels, out_channels]}, this op
   *  performs the following:
   *  <ol>
   *  <li>Flattens the filter to a 2-D matrix with shape
   *  {@code [filter_height * filter_width * in_channels, output_channels]}.</li>
   *  <li>Extracts image patches from the input tensor to form a <em>virtual</em>
   *  tensor of shape {@code [batch, out_height, out_width, filter_height * filter_width * in_channels]}.</li>
   *  <li>For each patch, right-multiplies the filter matrix and the image patch
   *  vector.</li>
   *  </ol>
   *  <p>In detail, with the default NHWC format,
   *  <pre>
   *  output[b, i, j, k] =
   *      sum_{di, dj, q} input[b, strides[1] * i + di, strides[2] * j + dj, q] *
   *                      filter[di, dj, q, k]
   *  </pre>
   *  <p>Must have {@code strides[0] = strides[3] = 1}.  For the most common case of the same
   *  horizontal and vertices strides, {@code strides = [1, stride, stride, 1]}.
   *
   * @param <T> data type for {@code output} output
   * @param input A 4-D tensor. The dimension order is interpreted according to the value
   *  of {@code data_format}, see below for details.
   * @param filter A 4-D tensor of shape
   *  {@code [filter_height, filter_width, in_channels, out_channels]}
   * @param strides 1-D tensor of length 4.  The stride of the sliding window for each
   *  dimension of {@code input}. The dimension order is determined by the value of
   *  {@code data_format}, see below for details.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv2D} output and operands
   * @return a new instance of Conv2d
   */
  public <T extends TNumber> Conv2d<T> conv2d(Operand<T> input, Operand<T> filter,
      List<Long> strides, String padding, Conv2d.Options... options) {
    return Conv2d.create(scope, input, filter, strides, padding, options);
  }

  /**
   * Computes the gradients of convolution with respect to the filter.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D with shape {@code [batch, in_height, in_width, in_channels]}.
   * @param filterSizes An integer vector representing the tensor shape of {@code filter},
   *  where {@code filter} is a 4-D
   *  {@code [filter_height, filter_width, in_channels, out_channels]} tensor.
   * @param outBackprop 4-D with shape {@code [batch, out_height, out_width, out_channels]}.
   *  Gradients w.r.t. the output of the convolution.
   * @param strides The stride of the sliding window for each dimension of the input
   *  of the convolution. Must be in the same order as the dimension specified with
   *  format.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv2DBackpropFilter} output and operands
   * @return a new instance of Conv2dBackpropFilter
   */
  public <T extends TNumber> Conv2dBackpropFilter<T> conv2dBackpropFilter(Operand<T> input,
      Operand<TInt32> filterSizes, Operand<T> outBackprop, List<Long> strides, String padding,
      Conv2dBackpropFilter.Options... options) {
    return Conv2dBackpropFilter.create(scope, input, filterSizes, outBackprop, strides, padding, options);
  }

  /**
   * Computes the gradients of convolution with respect to the input.
   *
   * @param <T> data type for {@code output} output
   * @param inputSizes An integer vector representing the shape of {@code input},
   *  where {@code input} is a 4-D {@code [batch, height, width, channels]} tensor.
   * @param filter 4-D with shape
   *  {@code [filter_height, filter_width, in_channels, out_channels]}.
   * @param outBackprop 4-D with shape {@code [batch, out_height, out_width, out_channels]}.
   *  Gradients w.r.t. the output of the convolution.
   * @param strides The stride of the sliding window for each dimension of the input
   *  of the convolution. Must be in the same order as the dimension specified with
   *  format.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv2DBackpropInput} output and operands
   * @return a new instance of Conv2dBackpropInput
   */
  public <T extends TNumber> Conv2dBackpropInput<T> conv2dBackpropInput(Operand<TInt32> inputSizes,
      Operand<T> filter, Operand<T> outBackprop, List<Long> strides, String padding,
      Conv2dBackpropInput.Options... options) {
    return Conv2dBackpropInput.create(scope, inputSizes, filter, outBackprop, strides, padding, options);
  }

  /**
   * Computes a 3-D convolution given 5-D {@code input} and {@code filter} tensors.
   *  In signal processing, cross-correlation is a measure of similarity of
   *  two waveforms as a function of a time-lag applied to one of them. This
   *  is also known as a sliding dot product or sliding inner-product.
   *  <p>Our Conv3D implements a form of cross-correlation.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape {@code [batch, in_depth, in_height, in_width, in_channels]}.
   * @param filter Shape {@code [filter_depth, filter_height, filter_width, in_channels, out_channels]}. {@code in_channels} must match between {@code input} and {@code filter}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv3D} output and operands
   * @return a new instance of Conv3d
   */
  public <T extends TNumber> Conv3d<T> conv3d(Operand<T> input, Operand<T> filter,
      List<Long> strides, String padding, Conv3d.Options... options) {
    return Conv3d.create(scope, input, filter, strides, padding, options);
  }

  /**
   * Computes the gradients of 3-D convolution with respect to the filter.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape {@code [batch, depth, rows, cols, in_channels]}.
   * @param filterSizes An integer vector representing the tensor shape of {@code filter},
   *  where {@code filter} is a 5-D
   *  {@code [filter_depth, filter_height, filter_width, in_channels, out_channels]}
   *  tensor.
   * @param outBackprop Backprop signal of shape {@code [batch, out_depth, out_rows, out_cols, out_channels]}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv3DBackpropFilterV2} output and operands
   * @return a new instance of Conv3dBackpropFilter
   */
  public <T extends TNumber> Conv3dBackpropFilter<T> conv3dBackpropFilter(Operand<T> input,
      Operand<TInt32> filterSizes, Operand<T> outBackprop, List<Long> strides, String padding,
      Conv3dBackpropFilter.Options... options) {
    return Conv3dBackpropFilter.create(scope, input, filterSizes, outBackprop, strides, padding, options);
  }

  /**
   * Computes the gradients of 3-D convolution with respect to the input.
   *
   * @param <U> data type for {@code output} output
   * @param inputSizes An integer vector representing the tensor shape of {@code input},
   *  where {@code input} is a 5-D
   *  {@code [batch, depth, rows, cols, in_channels]} tensor.
   * @param filter Shape {@code [depth, rows, cols, in_channels, out_channels]}.
   *  {@code in_channels} must match between {@code input} and {@code filter}.
   * @param outBackprop Backprop signal of shape {@code [batch, out_depth, out_rows, out_cols, out_channels]}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <U> data type for {@code Conv3DBackpropInputV2} output and operands
   * @return a new instance of Conv3dBackpropInput
   */
  public <U extends TNumber> Conv3dBackpropInput<U> conv3dBackpropInput(
      Operand<? extends TNumber> inputSizes, Operand<U> filter, Operand<U> outBackprop,
      List<Long> strides, String padding, Conv3dBackpropInput.Options... options) {
    return Conv3dBackpropInput.create(scope, inputSizes, filter, outBackprop, strides, padding, options);
  }

  /**
   * Performs beam search decoding on the logits given in input.
   *  A note about the attribute merge_repeated: For the beam search decoder,
   *  this means that if consecutive entries in a beam are the same, only
   *  the first of these is emitted.  That is, when the top path is &quot;A B B B B&quot;,
   *  &quot;A B&quot; is returned if merge_repeated = True but &quot;A B B B B&quot; is
   *  returned if merge_repeated = False.
   *
   * @param <T> data type for {@code log_probability} output
   * @param inputs 3-D, shape: {@code (max_time x batch_size x num_classes)}, the logits.
   * @param sequenceLength A vector containing sequence lengths, size {@code (batch)}.
   * @param beamWidth A scalar &gt;= 0 (beam search beam width).
   * @param topPaths A scalar &gt;= 0, &lt;= beam_width (controls output size).
   * @param options carries optional attribute values
   * @param <T> data type for {@code CTCBeamSearchDecoder} output and operands
   * @return a new instance of CtcBeamSearchDecoder
   */
  public <T extends TNumber> CtcBeamSearchDecoder<T> ctcBeamSearchDecoder(Operand<T> inputs,
      Operand<TInt32> sequenceLength, Long beamWidth, Long topPaths,
      CtcBeamSearchDecoder.Options... options) {
    return CtcBeamSearchDecoder.create(scope, inputs, sequenceLength, beamWidth, topPaths, options);
  }

  /**
   * Performs greedy decoding on the logits given in inputs.
   *  A note about the attribute merge_repeated: if enabled, when
   *  consecutive logits' maximum indices are the same, only the first of
   *  these is emitted.  Labeling the blank '*', the sequence &quot;A B B * B B&quot;
   *  becomes &quot;A B B&quot; if merge_repeated = True and &quot;A B B B B&quot; if
   *  merge_repeated = False.
   *  <p>Regardless of the value of merge_repeated, if the maximum index of a given
   *  time and batch corresponds to the blank, index {@code (num_classes - 1)}, no new
   *  element is emitted.
   *
   * @param <T> data type for {@code log_probability} output
   * @param inputs 3-D, shape: {@code (max_time x batch_size x num_classes)}, the logits.
   * @param sequenceLength A vector containing sequence lengths, size {@code (batch_size)}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code CTCGreedyDecoder} output and operands
   * @return a new instance of CtcGreedyDecoder
   */
  public <T extends TNumber> CtcGreedyDecoder<T> ctcGreedyDecoder(Operand<T> inputs,
      Operand<TInt32> sequenceLength, CtcGreedyDecoder.Options... options) {
    return CtcGreedyDecoder.create(scope, inputs, sequenceLength, options);
  }

  /**
   * Calculates the CTC Loss (log probability) for each batch entry.  Also calculates
   *  the gradient.  This class performs the softmax operation for you, so inputs
   *  should be e.g. linear projections of outputs by an LSTM.
   *
   * @param <T> data type for {@code loss} output
   * @param inputs 3-D, shape: {@code (max_time x batch_size x num_classes)}, the logits.
   * @param labelsIndices The indices of a {@code SparseTensor<int32, 2>}.
   *  {@code labels_indices(i, :) == [b, t]} means {@code labels_values(i)} stores the id for
   *  {@code (batch b, time t)}.
   * @param labelsValues The values (labels) associated with the given batch and time.
   * @param sequenceLength A vector containing sequence lengths (batch).
   * @param options carries optional attribute values
   * @param <T> data type for {@code CTCLoss} output and operands
   * @return a new instance of CtcLoss
   */
  public <T extends TNumber> CtcLoss<T> ctcLoss(Operand<T> inputs, Operand<TInt64> labelsIndices,
      Operand<TInt32> labelsValues, Operand<TInt32> sequenceLength, CtcLoss.Options... options) {
    return CtcLoss.create(scope, inputs, labelsIndices, labelsValues, sequenceLength, options);
  }

  /**
   * Converts CudnnRNN params from canonical form to usable form. It supports the projection in LSTM.
   *  Writes a set of weights into the opaque params buffer so they can be used in
   *  upcoming training or inferences.
   *  <p>Note that the params buffer may not be compatible across different GPUs. So any
   *  save and restoration should be converted to and from the canonical weights and
   *  biases.
   *  <p>num_layers: Specifies the number of layers in the RNN model.
   *  num_units: Specifies the size of the hidden state.
   *  input_size: Specifies the size of the input state.
   *  weights: the canonical form of weights that can be used for saving
   *  and restoration. They are more likely to be compatible across different
   *  generations.
   *  biases: the canonical form of biases that can be used for saving
   *  and restoration. They are more likely to be compatible across different
   *  generations.
   *  num_params_weights: number of weight parameter matrix for all layers.
   *  num_params_biases: number of bias parameter vector for all layers.
   *  rnn_mode: Indicates the type of the RNN model.
   *  input_mode: Indicate whether there is a linear projection between the input and
   *  The actual computation before the first layer. 'skip_input' is only allowed
   *  when input_size == num_units; 'auto_select' implies 'skip_input' when
   *  input_size == num_units; otherwise, it implies 'linear_input'.
   *  direction: Indicates whether a bidirectional model will be used.
   *  dir = (direction == bidirectional) ? 2 : 1
   *  dropout: dropout probability. When set to 0., dropout is disabled.
   *  seed: the 1st part of a seed to initialize dropout.
   *  seed2: the 2nd part of a seed to initialize dropout.
   *  num_proj: The output dimensionality for the projection matrices. If None or 0,
   *  no projection is performed.
   *
   * @param <T> data type for {@code params} output
   * @param numLayers the numLayers value
   * @param numUnits the numUnits value
   * @param inputSize the inputSize value
   * @param weights the weights value
   * @param biases the biases value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNCanonicalToParamsV2} output and operands
   * @return a new instance of CudnnRNNCanonicalToParams
   */
  public <T extends TNumber> CudnnRNNCanonicalToParams<T> cudnnRNNCanonicalToParams(
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize,
      Iterable<Operand<T>> weights, Iterable<Operand<T>> biases,
      CudnnRNNCanonicalToParams.Options... options) {
    return CudnnRNNCanonicalToParams.create(scope, numLayers, numUnits, inputSize, weights, biases, options);
  }

  /**
   * Retrieves CudnnRNN params in canonical form. It supports the projection in LSTM.
   *  Retrieves a set of weights from the opaque params buffer that can be saved and
   *  restored in a way compatible with future runs.
   *  <p>Note that the params buffer may not be compatible across different GPUs. So any
   *  save and restoration should be converted to and from the canonical weights and
   *  biases.
   *  <p>num_layers: Specifies the number of layers in the RNN model.
   *  num_units: Specifies the size of the hidden state.
   *  input_size: Specifies the size of the input state.
   *  num_params_weights: number of weight parameter matrix for all layers.
   *  num_params_biases: number of bias parameter vector for all layers.
   *  weights: the canonical form of weights that can be used for saving
   *  and restoration. They are more likely to be compatible across different
   *  generations.
   *  biases: the canonical form of biases that can be used for saving
   *  and restoration. They are more likely to be compatible across different
   *  generations.
   *  rnn_mode: Indicates the type of the RNN model.
   *  input_mode: Indicate whether there is a linear projection between the input and
   *  The actual computation before the first layer. 'skip_input' is only allowed
   *  when input_size == num_units; 'auto_select' implies 'skip_input' when
   *  input_size == num_units; otherwise, it implies 'linear_input'.
   *  direction: Indicates whether a bidirectional model will be used.
   *  dir = (direction == bidirectional) ? 2 : 1
   *  dropout: dropout probability. When set to 0., dropout is disabled.
   *  seed: the 1st part of a seed to initialize dropout.
   *  seed2: the 2nd part of a seed to initialize dropout.
   *  num_proj: The output dimensionality for the projection matrices. If None or 0,
   *  no projection is performed.
   *
   * @param <T> data type for {@code weights} output
   * @param numLayers the numLayers value
   * @param numUnits the numUnits value
   * @param inputSize the inputSize value
   * @param params the params value
   * @param numParamsWeights the value of the numParamsWeights property
   * @param numParamsBiases the value of the numParamsBiases property
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNParamsToCanonicalV2} output and operands
   * @return a new instance of CudnnRNNParamsToCanonical
   */
  public <T extends TNumber> CudnnRNNParamsToCanonical<T> cudnnRNNParamsToCanonical(
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize,
      Operand<T> params, Long numParamsWeights, Long numParamsBiases,
      CudnnRNNParamsToCanonical.Options... options) {
    return CudnnRNNParamsToCanonical.create(scope, numLayers, numUnits, inputSize, params, numParamsWeights, numParamsBiases, options);
  }

  /**
   * Computes size of weights that can be used by a Cudnn RNN model.
   *  Return the params size that can be used by the Cudnn RNN model. Subsequent
   *  weight allocation and initialization should use this size.
   *  <p>num_layers: Specifies the number of layers in the RNN model.
   *  num_units: Specifies the size of the hidden state.
   *  input_size: Specifies the size of the input state.
   *  rnn_mode: Indicates the type of the RNN model.
   *  input_mode: Indicate whether there is a linear projection between the input and
   *  The actual computation before the first layer. 'skip_input' is only allowed
   *  when input_size == num_units; 'auto_select' implies 'skip_input' when
   *  input_size == num_units; otherwise, it implies 'linear_input'.
   *  direction: Indicates whether a bidirectional model will be used.
   *  dir = (direction == bidirectional) ? 2 : 1
   *  dropout: dropout probability. When set to 0., dropout is disabled.
   *  seed: the 1st part of a seed to initialize dropout.
   *  seed2: the 2nd part of a seed to initialize dropout.
   *  params_size: The size of the params buffer that should be allocated and
   *  initialized for this RNN model. Note that this params buffer may not be
   *  compatible across GPUs. Please use CudnnRNNParamsWeights and
   *  CudnnRNNParamsBiases to save and restore them in a way that is compatible
   *  across different runs.
   *
   * @param <T> data type for {@code params_size} output
   * @param numLayers the numLayers value
   * @param numUnits the numUnits value
   * @param inputSize the inputSize value
   * @param T the value of the T property
   * @param S the value of the S property
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNParamsSize} output and operands
   * @param <U> data type for {@code CudnnRNNParamsSize} output and operands
   * @return a new instance of CudnnRnnParamsSize
   */
  public <T extends TNumber, U extends TNumber> CudnnRnnParamsSize<T> cudnnRnnParamsSize(
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize, Class<U> T,
      Class<T> S, CudnnRnnParamsSize.Options... options) {
    return CudnnRnnParamsSize.create(scope, numLayers, numUnits, inputSize, T, S, options);
  }

  /**
   * Returns the dimension index in the destination data format given the one in
   *  the source data format.
   *
   * @param <T> data type for {@code y} output
   * @param x A Tensor with each element as a dimension index in source data format.
   *  Must be in the range [-4, 4).
   * @param options carries optional attribute values
   * @param <T> data type for {@code DataFormatDimMap} output and operands
   * @return a new instance of DataFormatDimMap
   */
  public <T extends TNumber> DataFormatDimMap<T> dataFormatDimMap(Operand<T> x,
      DataFormatDimMap.Options... options) {
    return DataFormatDimMap.create(scope, x, options);
  }

  /**
   * Permute input tensor from {@code src_format} to {@code dst_format}.
   *  Input tensor must be a vector of size 4, or a 4x2 tensor.
   *  <p>For example, with {@code src_format} of {@code NHWC}, {@code dst_format} of {@code NCHW}, and inputs:
   *  <pre>
   *  [1, 2, 3, 4]
   *  </pre>
   *  <p>and
   *  <pre>
   *  [[1, 2, 3, 4],
   *   [5, 6, 7, 8]]
   *  </pre>
   *  <p>, the outputs will be (respectively):
   *  <pre>
   *  [1, 4, 2, 3]
   *  </pre>
   *  <p>and
   *  <pre>
   *  [[1, 4, 2, 3],
   *   [5, 8, 6, 7]]
   *  </pre>
   *
   * @param <T> data type for {@code y} output
   * @param x Vector of size 4 or Tensor of shape (4, 2) in source data format.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DataFormatVecPermute} output and operands
   * @return a new instance of DataFormatVecPermute
   */
  public <T extends TNumber> DataFormatVecPermute<T> dataFormatVecPermute(Operand<T> x,
      DataFormatVecPermute.Options... options) {
    return DataFormatVecPermute.create(scope, x, options);
  }

  /**
   * DepthToSpace for tensors of type T.
   *  Rearranges data from depth into blocks of spatial data.
   *  This is the reverse transformation of SpaceToDepth. More specifically,
   *  this op outputs a copy of the input tensor where values from the {@code depth}
   *  dimension are moved in spatial blocks to the {@code height} and {@code width} dimensions.
   *  The attr {@code block_size} indicates the input block size and how the data is moved.
   *  <ul>
   *  <li>Chunks of data of size {@code block_size * block_size} from depth are rearranged
   *  into non-overlapping blocks of size {@code block_size x block_size}</li>
   *  <li>The width the output tensor is {@code input_depth * block_size}, whereas the
   *  height is {@code input_height * block_size}.</li>
   *  <li>The Y, X coordinates within each block of the output image are determined
   *  by the high order component of the input channel index.</li>
   *  <li>The depth of the input tensor must be divisible by
   *  {@code block_size * block_size}.</li>
   *  </ul>
   *  <p>The {@code data_format} attr specifies the layout of the input and output tensors
   *  with the following options:
   *  &quot;NHWC&quot;: {@code [ batch, height, width, channels ]}
   *  &quot;NCHW&quot;: {@code [ batch, channels, height, width ]}
   *  &quot;NCHW_VECT_C&quot;:
   *  {@code qint8 [ batch, channels / 4, height, width, 4 ]}
   *  <p>It is useful to consider the operation as transforming a 6-D Tensor.
   *  e.g. for data_format = NHWC,
   *  Each element in the input tensor can be specified via 6 coordinates,
   *  ordered by decreasing memory layout significance as:
   *  n,iY,iX,bY,bX,oC  (where n=batch index, iX, iY means X or Y coordinates
   *  within the input image, bX, bY means coordinates
   *  within the output block, oC means output channels).
   *  The output would be the input transposed to the following layout:
   *  n,iY,bY,iX,bX,oC
   *  <p>This operation is useful for resizing the activations between convolutions
   *  (but keeping all data), e.g. instead of pooling. It is also useful for training
   *  purely convolutional models.
   *  <p>For example, given an input of shape {@code [1, 1, 1, 4]}, data_format = &quot;NHWC&quot; and
   *  block_size = 2:
   *  <pre>
   *  x = [[[[1, 2, 3, 4]]]]
   *
   *  </pre>
   *  <p>This operation will output a tensor of shape {@code [1, 2, 2, 1]}:
   *  <pre>
   *     [[[[1], [2]],
   *       [[3], [4]]]]
   *  </pre>
   *  <p>Here, the input has a batch of 1 and each batch element has shape {@code [1, 1, 4]},
   *  the corresponding output will have 2x2 elements and will have a depth of
   *  1 channel (1 = {@code 4 / (block_size * block_size)}).
   *  The output element shape is {@code [2, 2, 1]}.
   *  <p>For an input tensor with larger depth, here of shape {@code [1, 1, 1, 12]}, e.g.
   *  <pre>
   *  x = [[[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]]]
   *  </pre>
   *  <p>This operation, for block size of 2, will return the following tensor of shape
   *  {@code [1, 2, 2, 3]}
   *  <pre>
   *     [[[[1, 2, 3], [4, 5, 6]],
   *       [[7, 8, 9], [10, 11, 12]]]]
   *
   *  </pre>
   *  <p>Similarly, for the following input of shape {@code [1 2 2 4]}, and a block size of 2:
   *  <pre>
   *  x =  [[[[1, 2, 3, 4],
   *         [5, 6, 7, 8]],
   *        [[9, 10, 11, 12],
   *         [13, 14, 15, 16]]]]
   *  </pre>
   *  <p>the operator will return the following tensor of shape {@code [1 4 4 1]}:
   *  <pre>
   *  x = [[[ [1],   [2],  [5],  [6]],
   *        [ [3],   [4],  [7],  [8]],
   *        [ [9],  [10], [13],  [14]],
   *        [ [11], [12], [15],  [16]]]]
   *
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param blockSize The size of the spatial block, same as in Space2Depth.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DepthToSpace} output and operands
   * @return a new instance of DepthToSpace
   */
  public <T extends TType> DepthToSpace<T> depthToSpace(Operand<T> input, Long blockSize,
      DepthToSpace.Options... options) {
    return DepthToSpace.create(scope, input, blockSize, options);
  }

  /**
   * Computes a 2-D depthwise convolution given 4-D {@code input} and {@code filter} tensors.
   *  Given an input tensor of shape {@code [batch, in_height, in_width, in_channels]}
   *  and a filter / kernel tensor of shape
   *  {@code [filter_height, filter_width, in_channels, channel_multiplier]}, containing
   *  {@code in_channels} convolutional filters of depth 1, {@code depthwise_conv2d} applies
   *  a different filter to each input channel (expanding from 1 channel to
   *  {@code channel_multiplier} channels for each), then concatenates the results
   *  together. Thus, the output has {@code in_channels * channel_multiplier} channels.
   *  <pre>
   *  for k in 0..in_channels-1
   *    for q in 0..channel_multiplier-1
   *      output[b, i, j, k * channel_multiplier + q] =
   *        sum_{di, dj} input[b, strides[1] * i + di, strides[2] * j + dj, k] *
   *                          filter[di, dj, k, q]
   *  </pre>
   *  <p>Must have {@code strides[0] = strides[3] = 1}.  For the most common case of the same
   *  horizontal and vertices strides, {@code strides = [1, stride, stride, 1]}.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param filter the filter value
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   *  of {@code input}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DepthwiseConv2dNative} output and operands
   * @return a new instance of DepthwiseConv2dNative
   */
  public <T extends TNumber> DepthwiseConv2dNative<T> depthwiseConv2dNative(Operand<T> input,
      Operand<T> filter, List<Long> strides, String padding,
      DepthwiseConv2dNative.Options... options) {
    return DepthwiseConv2dNative.create(scope, input, filter, strides, padding, options);
  }

  /**
   * Computes the gradients of depthwise convolution with respect to the filter.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D with shape based on {@code data_format}.  For example, if
   *  {@code data_format} is 'NHWC' then {@code input} is a 4-D {@code [batch, in_height, in_width, in_channels]} tensor.
   * @param filterSizes An integer vector representing the tensor shape of {@code filter},
   *  where {@code filter} is a 4-D
   *  {@code [filter_height, filter_width, in_channels, depthwise_multiplier]} tensor.
   * @param outBackprop 4-D with shape  based on {@code data_format}.
   *  For example, if {@code data_format} is 'NHWC' then
   *  out_backprop shape is {@code [batch, out_height, out_width, out_channels]}.
   *  Gradients w.r.t. the output of the convolution.
   * @param strides The stride of the sliding window for each dimension of the input
   *  of the convolution.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DepthwiseConv2dNativeBackpropFilter} output and operands
   * @return a new instance of DepthwiseConv2dNativeBackpropFilter
   */
  public <T extends TNumber> DepthwiseConv2dNativeBackpropFilter<T> depthwiseConv2dNativeBackpropFilter(
      Operand<T> input, Operand<TInt32> filterSizes, Operand<T> outBackprop, List<Long> strides,
      String padding, DepthwiseConv2dNativeBackpropFilter.Options... options) {
    return DepthwiseConv2dNativeBackpropFilter.create(scope, input, filterSizes, outBackprop, strides, padding, options);
  }

  /**
   * Computes the gradients of depthwise convolution with respect to the input.
   *
   * @param <T> data type for {@code output} output
   * @param inputSizes An integer vector representing the shape of {@code input}, based
   *  on {@code data_format}.  For example, if {@code data_format} is 'NHWC' then
   *  {@code input} is a 4-D {@code [batch, height, width, channels]} tensor.
   * @param filter 4-D with shape
   *  {@code [filter_height, filter_width, in_channels, depthwise_multiplier]}.
   * @param outBackprop 4-D with shape  based on {@code data_format}.
   *  For example, if {@code data_format} is 'NHWC' then
   *  out_backprop shape is {@code [batch, out_height, out_width, out_channels]}.
   *  Gradients w.r.t. the output of the convolution.
   * @param strides The stride of the sliding window for each dimension of the input
   *  of the convolution.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DepthwiseConv2dNativeBackpropInput} output and operands
   * @return a new instance of DepthwiseConv2dNativeBackpropInput
   */
  public <T extends TNumber> DepthwiseConv2dNativeBackpropInput<T> depthwiseConv2dNativeBackpropInput(
      Operand<TInt32> inputSizes, Operand<T> filter, Operand<T> outBackprop, List<Long> strides,
      String padding, DepthwiseConv2dNativeBackpropInput.Options... options) {
    return DepthwiseConv2dNativeBackpropInput.create(scope, inputSizes, filter, outBackprop, strides, padding, options);
  }

  /**
   * Computes the grayscale dilation of 4-D {@code input} and 3-D {@code filter} tensors.
   *  The {@code input} tensor has shape {@code [batch, in_height, in_width, depth]} and the
   *  {@code filter} tensor has shape {@code [filter_height, filter_width, depth]}, i.e., each
   *  input channel is processed independently of the others with its own structuring
   *  function. The {@code output} tensor has shape
   *  {@code [batch, out_height, out_width, depth]}. The spatial dimensions of the output
   *  tensor depend on the {@code padding} algorithm. We currently only support the default
   *  &quot;NHWC&quot; {@code data_format}.
   *  <p>In detail, the grayscale morphological 2-D dilation is the max-sum correlation
   *  (for consistency with {@code conv2d}, we use unmirrored filters):
   *  <pre>
   *  output[b, y, x, c] =
   *     max_{dy, dx} input[b,
   *                        strides[1] * y + rates[1] * dy,
   *                        strides[2] * x + rates[2] * dx,
   *                        c] +
   *                  filter[dy, dx, c]
   *  </pre>
   *  <p>Max-pooling is a special case when the filter has size equal to the pooling
   *  kernel size and contains all zeros.
   *  <p>Note on duality: The dilation of {@code input} by the {@code filter} is equal to the
   *  negation of the erosion of {@code -input} by the reflected {@code filter}.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D with shape {@code [batch, in_height, in_width, depth]}.
   * @param filter 3-D with shape {@code [filter_height, filter_width, depth]}.
   * @param strides The stride of the sliding window for each dimension of the input
   *  tensor. Must be: {@code [1, stride_height, stride_width, 1]}.
   * @param rates The input stride for atrous morphological dilation. Must be:
   *  {@code [1, rate_height, rate_width, 1]}.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code Dilation2D} output and operands
   * @return a new instance of Dilation2d
   */
  public <T extends TNumber> Dilation2d<T> dilation2d(Operand<T> input, Operand<T> filter,
      List<Long> strides, List<Long> rates, String padding) {
    return Dilation2d.create(scope, input, filter, strides, rates, padding);
  }

  /**
   * Computes the gradient of morphological 2-D dilation with respect to the filter.
   *
   * @param <T> data type for {@code filter_backprop} output
   * @param input 4-D with shape {@code [batch, in_height, in_width, depth]}.
   * @param filter 3-D with shape {@code [filter_height, filter_width, depth]}.
   * @param outBackprop 4-D with shape {@code [batch, out_height, out_width, depth]}.
   * @param strides 1-D of length 4. The stride of the sliding window for each dimension of
   *  the input tensor. Must be: {@code [1, stride_height, stride_width, 1]}.
   * @param rates 1-D of length 4. The input stride for atrous morphological dilation.
   *  Must be: {@code [1, rate_height, rate_width, 1]}.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code Dilation2DBackpropFilter} output and operands
   * @return a new instance of Dilation2dBackpropFilter
   */
  public <T extends TNumber> Dilation2dBackpropFilter<T> dilation2dBackpropFilter(Operand<T> input,
      Operand<T> filter, Operand<T> outBackprop, List<Long> strides, List<Long> rates,
      String padding) {
    return Dilation2dBackpropFilter.create(scope, input, filter, outBackprop, strides, rates, padding);
  }

  /**
   * Computes the gradient of morphological 2-D dilation with respect to the input.
   *
   * @param <T> data type for {@code in_backprop} output
   * @param input 4-D with shape {@code [batch, in_height, in_width, depth]}.
   * @param filter 3-D with shape {@code [filter_height, filter_width, depth]}.
   * @param outBackprop 4-D with shape {@code [batch, out_height, out_width, depth]}.
   * @param strides 1-D of length 4. The stride of the sliding window for each dimension of
   *  the input tensor. Must be: {@code [1, stride_height, stride_width, 1]}.
   * @param rates 1-D of length 4. The input stride for atrous morphological dilation.
   *  Must be: {@code [1, rate_height, rate_width, 1]}.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code Dilation2DBackpropInput} output and operands
   * @return a new instance of Dilation2dBackpropInput
   */
  public <T extends TNumber> Dilation2dBackpropInput<T> dilation2dBackpropInput(Operand<T> input,
      Operand<T> filter, Operand<T> outBackprop, List<Long> strides, List<Long> rates,
      String padding) {
    return Dilation2dBackpropInput.create(scope, input, filter, outBackprop, strides, rates, padding);
  }

  /**
   * Computes exponential linear: {@code exp(features) - 1} if &lt; 0, {@code features} otherwise.
   *  See  <a href="http://arxiv.org/abs/1511.07289">Fast and Accurate Deep Network Learning by Exponential Linear Units (ELUs)
   *  </a>
   *
   * @param <T> data type for {@code activations} output
   * @param features the features value
   * @param <T> data type for {@code Elu} output and operands
   * @return a new instance of Elu
   */
  public <T extends TNumber> Elu<T> elu(Operand<T> features) {
    return Elu.create(scope, features);
  }

  /**
   * Generates labels for candidate sampling with a learned unigram distribution.
   *  A unigram sampler could use a fixed unigram distribution read from a
   *  file or passed in as an in-memory array instead of building up the distribution
   *  from data on the fly. There is also an option to skew the distribution by
   *  applying a distortion power to the weights.
   *  <p>The vocabulary file should be in CSV-like format, with the last field
   *  being the weight associated with the word.
   *  <p>For each batch, this op picks a single set of sampled candidate labels.
   *  <p>The advantages of sampling candidates per-batch are simplicity and the
   *  possibility of efficient dense matrix multiplication. The disadvantage is that
   *  the sampled candidates must be chosen independently of the context and of the
   *  true labels.
   *
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   *  IDs of the num_true target_classes in the corresponding original label.
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to randomly sample.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   *  candidates in a batch are unique. This requires some approximation to
   *  estimate the post-rejection sampling probabilities.
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attribute values
   * @return a new instance of FixedUnigramCandidateSampler
   */
  public FixedUnigramCandidateSampler fixedUnigramCandidateSampler(Operand<TInt64> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax,
      FixedUnigramCandidateSampler.Options... options) {
    return FixedUnigramCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Performs fractional average pooling on the input.
   *  Fractional average pooling is similar to Fractional max pooling in the pooling
   *  region generation step. The only difference is that after pooling regions are
   *  generated, a mean operation is performed instead of a max operation in each
   *  pooling region.
   *
   * @param <T> data type for {@code output} output
   * @param value 4-D with shape {@code [batch, height, width, channels]}.
   * @param poolingRatio Pooling ratio for each dimension of {@code value}, currently only
   *  supports row and col dimension and should be &gt;= 1.0. For example, a valid
   *  pooling ratio looks like [1.0, 1.44, 1.73, 1.0]. The first and last elements
   *  must be 1.0 because we don't allow pooling on batch and channels
   *  dimensions. 1.44 and 1.73 are pooling ratio on height and width dimensions
   *  respectively.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FractionalAvgPool} output and operands
   * @return a new instance of FractionalAvgPool
   */
  public <T extends TNumber> FractionalAvgPool<T> fractionalAvgPool(Operand<T> value,
      List<Float> poolingRatio, FractionalAvgPool.Options... options) {
    return FractionalAvgPool.create(scope, value, poolingRatio, options);
  }

  /**
   * Performs fractional max pooling on the input.
   *  Fractional max pooling is slightly different than regular max pooling.  In
   *  regular max pooling, you downsize an input set by taking the maximum value of
   *  smaller N x N subsections of the set (often 2x2), and try to reduce the set by
   *  a factor of N, where N is an integer.  Fractional max pooling, as you might
   *  expect from the word &quot;fractional&quot;, means that the overall reduction ratio N
   *  does not have to be an integer.
   *  <p>The sizes of the pooling regions are generated randomly but are fairly uniform.
   *  For example, let's look at the height dimension, and the constraints on the
   *  list of rows that will be pool boundaries.
   *  <p>First we define the following:
   *  <ol>
   *  <li>input_row_length : the number of rows from the input set</li>
   *  <li>output_row_length : which will be smaller than the input</li>
   *  <li>alpha = input_row_length / output_row_length : our reduction ratio</li>
   *  <li>K = floor(alpha)</li>
   *  <li>row_pooling_sequence : this is the result list of pool boundary rows</li>
   *  </ol>
   *  <p>Then, row_pooling_sequence should satisfy:
   *  <ol>
   *  <li>a[0] = 0 : the first value of the sequence is 0</li>
   *  <li>a[end] = input_row_length : the last value of the sequence is the size</li>
   *  <li>K &lt;= (a[i+1] - a[i]) &lt;= K+1 : all intervals are K or K+1 size</li>
   *  <li>length(row_pooling_sequence) = output_row_length+1</li>
   *  </ol>
   *  <p>For more details on fractional max pooling, see this paper:
   *   <a href="http://arxiv.org/abs/1412.6071">Benjamin Graham, Fractional Max-Pooling</a>
   *
   * @param <T> data type for {@code output} output
   * @param value 4-D with shape {@code [batch, height, width, channels]}.
   * @param poolingRatio Pooling ratio for each dimension of {@code value}, currently only
   *  supports row and col dimension and should be &gt;= 1.0. For example, a valid
   *  pooling ratio looks like [1.0, 1.44, 1.73, 1.0]. The first and last elements
   *  must be 1.0 because we don't allow pooling on batch and channels
   *  dimensions. 1.44 and 1.73 are pooling ratio on height and width dimensions
   *  respectively.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FractionalMaxPool} output and operands
   * @return a new instance of FractionalMaxPool
   */
  public <T extends TNumber> FractionalMaxPool<T> fractionalMaxPool(Operand<T> value,
      List<Float> poolingRatio, FractionalMaxPool.Options... options) {
    return FractionalMaxPool.create(scope, value, poolingRatio, options);
  }

  /**
   * Batch normalization.
   *  Note that the size of 4D Tensors are defined by either &quot;NHWC&quot; or &quot;NCHW&quot;.
   *  The size of 1D Tensors matches the dimension C of the 4D Tensors.
   *
   * @param <T> data type for {@code y} output
   * @param <U> data type for {@code batch_mean} output
   * @param x A 4D Tensor for input data.
   * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
   * @param offset A 1D Tensor for offset, to shift to the normalized x.
   * @param mean A 1D Tensor for population mean. Used for inference only;
   *  must be empty for training.
   * @param variance A 1D Tensor for population variance. Used for inference only;
   *  must be empty for training.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FusedBatchNormV3} output and operands
   * @param <U> data type for {@code FusedBatchNormV3} output and operands
   * @return a new instance of FusedBatchNorm
   */
  public <T extends TNumber, U extends TNumber> FusedBatchNorm<T, U> fusedBatchNorm(Operand<T> x,
      Operand<U> scale, Operand<U> offset, Operand<U> mean, Operand<U> variance,
      FusedBatchNorm.Options... options) {
    return FusedBatchNorm.create(scope, x, scale, offset, mean, variance, options);
  }

  /**
   * Gradient for batch normalization.
   *  Note that the size of 4D Tensors are defined by either &quot;NHWC&quot; or &quot;NCHW&quot;.
   *  The size of 1D Tensors matches the dimension C of the 4D Tensors.
   *
   * @param <T> data type for {@code x_backprop} output
   * @param <U> data type for {@code scale_backprop} output
   * @param yBackprop A 4D Tensor for the gradient with respect to y.
   * @param x A 4D Tensor for input data.
   * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
   * @param reserveSpace1 When is_training is True, a 1D Tensor for the computed batch
   *  mean to be reused in gradient computation. When is_training is
   *  False, a 1D Tensor for the population mean to be reused in both
   *  1st and 2nd order gradient computation.
   * @param reserveSpace2 When is_training is True, a 1D Tensor for the computed batch
   *  variance (inverted variance in the cuDNN case) to be reused in
   *  gradient computation. When is_training is False, a 1D Tensor
   *  for the population variance to be reused in both 1st and 2nd
   *  order gradient computation.
   * @param reserveSpace3 When is_training is True, a 1D Tensor for some intermediate results to be reused
   *  in gradient computation. When is_training is False, a dummy empty Tensor will be
   *  created.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FusedBatchNormGradV3} output and operands
   * @param <U> data type for {@code FusedBatchNormGradV3} output and operands
   * @return a new instance of FusedBatchNormGrad
   */
  public <T extends TNumber, U extends TNumber> FusedBatchNormGrad<T, U> fusedBatchNormGrad(
      Operand<T> yBackprop, Operand<T> x, Operand<TFloat32> scale, Operand<U> reserveSpace1,
      Operand<U> reserveSpace2, Operand<U> reserveSpace3, FusedBatchNormGrad.Options... options) {
    return FusedBatchNormGrad.create(scope, yBackprop, x, scale, reserveSpace1, reserveSpace2, reserveSpace3, options);
  }

  /**
   * Performs a padding as a preprocess during a convolution.
   *  Similar to FusedResizeAndPadConv2d, this op allows for an optimized
   *  implementation where the spatial padding transformation stage is fused with the
   *  im2col lookup, but in this case without the bilinear filtering required for
   *  resizing. Fusing the padding prevents the need to write out the intermediate
   *  results as whole tensors, reducing memory pressure, and we can get some latency
   *  gains by merging the transformation calculations.
   *  The data_format attribute for Conv2D isn't supported by this op, and 'NHWC'
   *  order is used instead.
   *  Internally this op uses a single per-graph scratch buffer, which means that it
   *  will block if multiple versions are being run in parallel. This is because this
   *  operator is primarily an optimization to minimize memory usage.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D with shape {@code [batch, in_height, in_width, in_channels]}.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   *  rows must be the same as the rank of {@code input}.
   * @param filter 4-D with shape
   *  {@code [filter_height, filter_width, in_channels, out_channels]}.
   * @param mode the value of the mode property
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   *  of {@code input}. Must be in the same order as the dimension specified with format.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code FusedPadConv2D} output and operands
   * @return a new instance of FusedPadConv2d
   */
  public <T extends TNumber> FusedPadConv2d<T> fusedPadConv2d(Operand<T> input,
      Operand<TInt32> paddings, Operand<T> filter, String mode, List<Long> strides,
      String padding) {
    return FusedPadConv2d.create(scope, input, paddings, filter, mode, strides, padding);
  }

  /**
   * Performs a resize and padding as a preprocess during a convolution.
   *  It's often possible to do spatial transformations more efficiently as part of
   *  the packing stage of a convolution, so this op allows for an optimized
   *  implementation where these stages are fused together. This prevents the need to
   *  write out the intermediate results as whole tensors, reducing memory pressure,
   *  and we can get some latency gains by merging the transformation calculations.
   *  The data_format attribute for Conv2D isn't supported by this op, and defaults to
   *  'NHWC' order.
   *  Internally this op uses a single per-graph scratch buffer, which means that it
   *  will block if multiple versions are being run in parallel. This is because this
   *  operator is primarily an optimization to minimize memory usage.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D with shape {@code [batch, in_height, in_width, in_channels]}.
   * @param sizeOutput A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   *  new size for the images.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   *  rows must be the same as the rank of {@code input}.
   * @param filter 4-D with shape
   *  {@code [filter_height, filter_width, in_channels, out_channels]}.
   * @param mode the value of the mode property
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   *  of {@code input}. Must be in the same order as the dimension specified with format.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FusedResizeAndPadConv2D} output and operands
   * @return a new instance of FusedResizeAndPadConv2d
   */
  public <T extends TNumber> FusedResizeAndPadConv2d<T> fusedResizeAndPadConv2d(Operand<T> input,
      Operand<TInt32> sizeOutput, Operand<TInt32> paddings, Operand<T> filter, String mode,
      List<Long> strides, String padding, FusedResizeAndPadConv2d.Options... options) {
    return FusedResizeAndPadConv2d.create(scope, input, sizeOutput, paddings, filter, mode, strides, padding, options);
  }

  /**
   * Says whether the targets are in the top {@code K} predictions.
   *  This outputs a {@code batch_size} bool array, an entry {@code out[i]} is {@code true} if the
   *  prediction for the target class is among the top {@code k} predictions among
   *  all predictions for example {@code i}. Note that the behavior of {@code InTopK} differs
   *  from the {@code TopK} op in its handling of ties; if multiple classes have the
   *  same prediction value and straddle the top-{@code k} boundary, all of those
   *  classes are considered to be in the top {@code k}.
   *  <p>More formally, let
   *  <p>\(predictions_i\) be the predictions for all classes for example {@code i},
   *  \(targets_i\) be the target class for example {@code i},
   *  \(out_i\) be the output for example {@code i},
   *  <p>$$out_i = predictions_{i, targets_i} \in TopKIncludingTies(predictions_i)$$
   *
   * @param predictions A {@code batch_size} x {@code classes} tensor.
   * @param targets A {@code batch_size} vector of class ids.
   * @param k Number of top elements to look at for computing precision.
   * @param <T> data type for {@code InTopKV2} output and operands
   * @return a new instance of InTopK
   */
  public <T extends TNumber> InTopK inTopK(Operand<TFloat32> predictions, Operand<T> targets,
      Operand<T> k) {
    return InTopK.create(scope, predictions, targets, k);
  }

  /**
   * L2 Loss.
   *  Computes half the L2 norm of a tensor without the {@code sqrt}:
   *  <pre>
   *  output = sum(t ** 2) / 2
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param t Typically 2-D, but may have any dimensions.
   * @param <T> data type for {@code L2Loss} output and operands
   * @return a new instance of L2Loss
   */
  public <T extends TNumber> L2Loss<T> l2Loss(Operand<T> t) {
    return L2Loss.create(scope, t);
  }

  /**
   * Computes rectified linear: {@code max(features, features * alpha)}.
   *
   * @param <T> data type for {@code activations} output
   * @param features the features value
   * @param options carries optional attribute values
   * @param <T> data type for {@code LeakyRelu} output and operands
   * @return a new instance of LeakyRelu
   */
  public <T extends TNumber> LeakyRelu<T> leakyRelu(Operand<T> features,
      LeakyRelu.Options... options) {
    return LeakyRelu.create(scope, features, options);
  }

  /**
   * Generates labels for candidate sampling with a learned unigram distribution.
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>For each batch, this op picks a single set of sampled candidate labels.
   *  <p>The advantages of sampling candidates per-batch are simplicity and the
   *  possibility of efficient dense matrix multiplication. The disadvantage is that
   *  the sampled candidates must be chosen independently of the context and of the
   *  true labels.
   *
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   *  IDs of the num_true target_classes in the corresponding original label.
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to randomly sample.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   *  candidates in a batch are unique. This requires some approximation to
   *  estimate the post-rejection sampling probabilities.
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attribute values
   * @return a new instance of LearnedUnigramCandidateSampler
   */
  public LearnedUnigramCandidateSampler learnedUnigramCandidateSampler(Operand<TInt64> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax,
      LearnedUnigramCandidateSampler.Options... options) {
    return LearnedUnigramCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Local Response Normalization.
   *  The 4-D {@code input} tensor is treated as a 3-D array of 1-D vectors (along the last
   *  dimension), and each vector is normalized independently.  Within a given vector,
   *  each component is divided by the weighted, squared sum of inputs within
   *  {@code depth_radius}.  In detail,
   *  <pre>
   *  sqr_sum[a, b, c, d] =
   *      sum(input[a, b, c, d - depth_radius : d + depth_radius + 1] ** 2)
   *  output = input / (bias + alpha * sqr_sum) ** beta
   *  </pre>
   *  <p>For details, see  <a href="http://papers.nips.cc/paper/4824-imagenet-classification-with-deep-convolutional-neural-networks">Krizhevsky et al., ImageNet classification with deep
   *  convolutional neural networks (NIPS 2012)</a> .
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D.
   * @param options carries optional attribute values
   * @param <T> data type for {@code LRN} output and operands
   * @return a new instance of LocalResponseNormalization
   */
  public <T extends TNumber> LocalResponseNormalization<T> localResponseNormalization(
      Operand<T> input, LocalResponseNormalization.Options... options) {
    return LocalResponseNormalization.create(scope, input, options);
  }

  /**
   * Computes log softmax activations.
   *  For each batch {@code i} and class {@code j} we have
   *  <pre>
   *  logsoftmax[i, j] = logits[i, j] - log(sum(exp(logits[i])))
   *  </pre>
   *
   * @param <T> data type for {@code logsoftmax} output
   * @param logits 2-D with shape {@code [batch_size, num_classes]}.
   * @param <T> data type for {@code LogSoftmax} output and operands
   * @return a new instance of LogSoftmax
   */
  public <T extends TNumber> LogSoftmax<T> logSoftmax(Operand<T> logits) {
    return LogSoftmax.create(scope, logits);
  }

  /**
   * Performs max pooling on the input.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolV2} output and operands
   * @return a new instance of MaxPool
   */
  public <T extends TNumber> MaxPool<T> maxPool(Operand<T> input, Operand<TInt32> ksize,
      Operand<TInt32> strides, String padding, MaxPool.Options... options) {
    return MaxPool.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Performs 3D max pooling on the input.
   *
   * @param <T> data type for {@code output} output
   * @param input Shape {@code [batch, depth, rows, cols, channels]} tensor to pool over.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   *  the input tensor. Must have {@code ksize[0] = ksize[4] = 1}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPool3D} output and operands
   * @return a new instance of MaxPool3d
   */
  public <T extends TNumber> MaxPool3d<T> maxPool3d(Operand<T> input, List<Long> ksize,
      List<Long> strides, String padding, MaxPool3d.Options... options) {
    return MaxPool3d.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Computes gradients of 3D max pooling function.
   *
   * @param <U> data type for {@code output} output
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad Output backprop of shape {@code [batch, depth, rows, cols, channels]}.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   *  the input tensor. Must have {@code ksize[0] = ksize[4] = 1}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <U> data type for {@code MaxPool3DGrad} output and operands
   * @param <T> data type for {@code MaxPool3DGrad} output and operands
   * @return a new instance of MaxPool3dGrad
   */
  public <U extends TNumber, T extends TNumber> MaxPool3dGrad<U> maxPool3dGrad(Operand<T> origInput,
      Operand<T> origOutput, Operand<U> grad, List<Long> ksize, List<Long> strides, String padding,
      MaxPool3dGrad.Options... options) {
    return MaxPool3dGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Computes second-order gradients of the maxpooling function.
   *
   * @param <T> data type for {@code output} output
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad Output backprop of shape {@code [batch, depth, rows, cols, channels]}.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   *  the input tensor. Must have {@code ksize[0] = ksize[4] = 1}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPool3DGradGrad} output and operands
   * @return a new instance of MaxPool3dGradGrad
   */
  public <T extends TNumber> MaxPool3dGradGrad<T> maxPool3dGradGrad(Operand<T> origInput,
      Operand<T> origOutput, Operand<T> grad, List<Long> ksize, List<Long> strides, String padding,
      MaxPool3dGradGrad.Options... options) {
    return MaxPool3dGradGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Computes gradients of the maxpooling function.
   *
   * @param <T> data type for {@code output} output
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad 4-D.  Gradients w.r.t. the output of {@code max_pool}.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolGradV2} output and operands
   * @return a new instance of MaxPoolGrad
   */
  public <T extends TNumber> MaxPoolGrad<T> maxPoolGrad(Operand<T> origInput, Operand<T> origOutput,
      Operand<T> grad, Operand<TInt32> ksize, Operand<TInt32> strides, String padding,
      MaxPoolGrad.Options... options) {
    return MaxPoolGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Computes second-order gradients of the maxpooling function.
   *
   * @param <T> data type for {@code output} output
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad 4-D.  Gradients of gradients w.r.t. the input of {@code max_pool}.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolGradGradV2} output and operands
   * @return a new instance of MaxPoolGradGrad
   */
  public <T extends TNumber> MaxPoolGradGrad<T> maxPoolGradGrad(Operand<T> origInput,
      Operand<T> origOutput, Operand<T> grad, Operand<TInt32> ksize, Operand<TInt32> strides,
      String padding, MaxPoolGradGrad.Options... options) {
    return MaxPoolGradGrad.create(scope, origInput, origOutput, grad, ksize, strides, padding, options);
  }

  /**
   * Computes second-order gradients of the maxpooling function.
   *
   * @param <T> data type for {@code output} output
   * @param input The original input.
   * @param grad 4-D with shape {@code [batch, height, width, channels]}.  Gradients w.r.t. the
   *  input of {@code max_pool}.
   * @param argmax The indices of the maximum values chosen for each output of {@code max_pool}.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolGradGradWithArgmax} output and operands
   * @return a new instance of MaxPoolGradGradWithArgmax
   */
  public <T extends TNumber> MaxPoolGradGradWithArgmax<T> maxPoolGradGradWithArgmax(
      Operand<T> input, Operand<T> grad, Operand<? extends TNumber> argmax, List<Long> ksize,
      List<Long> strides, String padding, MaxPoolGradGradWithArgmax.Options... options) {
    return MaxPoolGradGradWithArgmax.create(scope, input, grad, argmax, ksize, strides, padding, options);
  }

  /**
   * Performs max pooling on the input and outputs both max values and indices.
   *  The indices in {@code argmax} are flattened, so that a maximum value at position
   *  {@code [b, y, x, c]} becomes flattened index:
   *  {@code (y * width + x) * channels + c} if {@code include_batch_in_index} is False;
   *  {@code ((b * height + y) * width + x) * channels + c} if {@code include_batch_in_index} is True.
   *  <p>The indices returned are always in {@code [0, height) x [0, width)} before flattening,
   *  even if padding is involved and the mathematically correct answer is outside
   *  (either negative or too large).  This is a bug, but fixing it is difficult to do
   *  in a safe backwards compatible way, especially due to flattening.
   *
   * @param <T> data type for {@code output} output
   * @param <U> data type for {@code argmax} output
   * @param input 4-D with shape {@code [batch, height, width, channels]}.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolWithArgmax} output and operands
   * @return a new instance of MaxPoolWithArgmax, with default output types
   */
  public <T extends TNumber> MaxPoolWithArgmax<T, TInt64> maxPoolWithArgmax(Operand<T> input,
      List<Long> ksize, List<Long> strides, String padding, MaxPoolWithArgmax.Options[] options) {
    return MaxPoolWithArgmax.create(scope, input, ksize, strides, padding, options);
  }

  /**
   * Performs max pooling on the input and outputs both max values and indices.
   *  The indices in {@code argmax} are flattened, so that a maximum value at position
   *  {@code [b, y, x, c]} becomes flattened index:
   *  {@code (y * width + x) * channels + c} if {@code include_batch_in_index} is False;
   *  {@code ((b * height + y) * width + x) * channels + c} if {@code include_batch_in_index} is True.
   *  <p>The indices returned are always in {@code [0, height) x [0, width)} before flattening,
   *  even if padding is involved and the mathematically correct answer is outside
   *  (either negative or too large).  This is a bug, but fixing it is difficult to do
   *  in a safe backwards compatible way, especially due to flattening.
   *
   * @param <T> data type for {@code output} output
   * @param <U> data type for {@code argmax} output
   * @param input 4-D with shape {@code [batch, height, width, channels]}.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param Targmax the value of the Targmax property
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolWithArgmax} output and operands
   * @param <U> data type for {@code MaxPoolWithArgmax} output and operands
   * @return a new instance of MaxPoolWithArgmax
   */
  public <T extends TNumber, U extends TNumber> MaxPoolWithArgmax<T, U> maxPoolWithArgmax(
      Operand<T> input, List<Long> ksize, List<Long> strides, Class<U> Targmax, String padding,
      MaxPoolWithArgmax.Options... options) {
    return MaxPoolWithArgmax.create(scope, input, ksize, strides, Targmax, padding, options);
  }

  /**
   * Finds values of the {@code n}-th order statistic for the last dimension.
   *  If the input is a vector (rank-1), finds the entries which is the nth-smallest
   *  value in the vector and outputs their values as scalar tensor.
   *  <p>For matrices (resp. higher rank input), computes the entries which is the
   *  nth-smallest value in each row (resp. vector along the last dimension). Thus,
   *  <pre>
   *  values.shape = input.shape[:-1]
   *  </pre>
   *
   * @param <T> data type for {@code values} output
   * @param input 1-D or higher with last dimension at least {@code n+1}.
   * @param n 0-D. Position of sorted vector to select along the last dimension (along
   *  each row for matrices). Valid range of n is {@code [0, input.shape[:-1])}
   * @param options carries optional attribute values
   * @param <T> data type for {@code NthElement} output and operands
   * @return a new instance of NthElement
   */
  public <T extends TNumber> NthElement<T> nthElement(Operand<T> input, Operand<TInt32> n,
      NthElement.Options... options) {
    return NthElement.create(scope, input, n, options);
  }

  /**
   * Produces the average pool of the input tensor for quantized types.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D with shape {@code [batch, height, width, channels]}.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param ksize The size of the window for each dimension of the input tensor.
   *  The length must be 4 to match the number of dimensions of the input.
   * @param strides The stride of the sliding window for each dimension of the input
   *  tensor.  The length must be 4 to match the number of dimensions of the input.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code QuantizedAvgPool} output and operands
   * @return a new instance of QuantizedAvgPool
   */
  public <T extends TNumber> QuantizedAvgPool<T> quantizedAvgPool(Operand<T> input,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, List<Long> ksize, List<Long> strides,
      String padding) {
    return QuantizedAvgPool.create(scope, input, minInput, maxInput, ksize, strides, padding);
  }

  /**
   * Quantized Batch normalization.
   *  This op is deprecated and will be removed in the future. Prefer
   *  {@code tf.nn.batch_normalization}.
   *
   * @param <U> data type for {@code result} output
   * @param t A 4D input Tensor.
   * @param tMin The value represented by the lowest quantized input.
   * @param tMax The value represented by the highest quantized input.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   *  This is the first output from tf.nn.moments,
   *  or a saved moving average thereof.
   * @param mMin The value represented by the lowest quantized mean.
   * @param mMax The value represented by the highest quantized mean.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   *  This is the second output from tf.nn.moments,
   *  or a saved moving average thereof.
   * @param vMin The value represented by the lowest quantized variance.
   * @param vMax The value represented by the highest quantized variance.
   * @param beta A 1D beta Tensor with size matching the last dimension of t.
   *  An offset to be added to the normalized tensor.
   * @param betaMin The value represented by the lowest quantized offset.
   * @param betaMax The value represented by the highest quantized offset.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   *  If &quot;scale_after_normalization&quot; is true, this tensor will be multiplied
   *  with the normalized tensor.
   * @param gammaMin The value represented by the lowest quantized gamma.
   * @param gammaMax The value represented by the highest quantized gamma.
   * @param outType the value of the outType property
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   *  needs to be multiplied with gamma.
   * @param <U> data type for {@code QuantizedBatchNormWithGlobalNormalization} output and operands
   * @param <T> data type for {@code QuantizedBatchNormWithGlobalNormalization} output and operands
   * @return a new instance of QuantizedBatchNormWithGlobalNormalization
   */
  public <U extends TNumber, T extends TNumber> QuantizedBatchNormWithGlobalNormalization<U> quantizedBatchNormWithGlobalNormalization(
      Operand<T> t, Operand<TFloat32> tMin, Operand<TFloat32> tMax, Operand<T> m,
      Operand<TFloat32> mMin, Operand<TFloat32> mMax, Operand<T> v, Operand<TFloat32> vMin,
      Operand<TFloat32> vMax, Operand<T> beta, Operand<TFloat32> betaMin, Operand<TFloat32> betaMax,
      Operand<T> gamma, Operand<TFloat32> gammaMin, Operand<TFloat32> gammaMax, Class<U> outType,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    return QuantizedBatchNormWithGlobalNormalization.create(scope, t, tMin, tMax, m, mMin, mMax, v, vMin, vMax, beta, betaMin, betaMax, gamma, gammaMin, gammaMax, outType, varianceEpsilon, scaleAfterNormalization);
  }

  /**
   * Adds Tensor 'bias' to Tensor 'input' for Quantized types.
   *  Broadcasts the values of bias on dimensions 0..N-2 of 'input'.
   *
   * @param <V> data type for {@code output} output
   * @param input the input value
   * @param bias A 1D bias Tensor with size matching the last dimension of 'input'.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minBias The float value that the lowest quantized bias value represents.
   * @param maxBias The float value that the highest quantized bias value represents.
   * @param outType the value of the outType property
   * @param <V> data type for {@code QuantizedBiasAdd} output and operands
   * @return a new instance of QuantizedBiasAdd
   */
  public <V extends TNumber> QuantizedBiasAdd<V> quantizedBiasAdd(Operand<? extends TNumber> input,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minBias, Operand<TFloat32> maxBias, Class<V> outType) {
    return QuantizedBiasAdd.create(scope, input, bias, minInput, maxInput, minBias, maxBias, outType);
  }

  /**
   * Computes a 2D convolution given quantized 4D input and filter tensors.
   *  The inputs are quantized tensors where the lowest value represents the real
   *  number of the associated minimum, and the highest represents the maximum.
   *  This means that you can only interpret the quantized output in the same way, by
   *  taking the returned minimum and maximum values into account.
   *
   * @param <V> data type for {@code output} output
   * @param input the input value
   * @param filter filter's input_depth dimension must match input's depth dimensions.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minFilter The float value that the lowest quantized filter value represents.
   * @param maxFilter The float value that the highest quantized filter value represents.
   * @param outType the value of the outType property
   * @param strides The stride of the sliding window for each dimension of the input
   *  tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2D} output and operands
   * @return a new instance of QuantizedConv2d
   */
  public <V extends TNumber> QuantizedConv2d<V> quantizedConv2d(Operand<? extends TNumber> input,
      Operand<? extends TNumber> filter, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Class<V> outType,
      List<Long> strides, String padding, QuantizedConv2d.Options... options) {
    return QuantizedConv2d.create(scope, input, filter, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * Quantized Instance normalization.
   *
   * @param <T> data type for {@code y} output
   * @param x A 4D input Tensor.
   * @param xMin The value represented by the lowest quantized input.
   * @param xMax The value represented by the highest quantized input.
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizedInstanceNorm} output and operands
   * @return a new instance of QuantizedInstanceNorm
   */
  public <T extends TNumber> QuantizedInstanceNorm<T> quantizedInstanceNorm(Operand<T> x,
      Operand<TFloat32> xMin, Operand<TFloat32> xMax, QuantizedInstanceNorm.Options... options) {
    return QuantizedInstanceNorm.create(scope, x, xMin, xMax, options);
  }

  /**
   * Produces the max pool of the input tensor for quantized types.
   *
   * @param <T> data type for {@code output} output
   * @param input The 4D (batch x rows x cols x depth) Tensor to MaxReduce over.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param ksize The size of the window for each dimension of the input tensor.
   *  The length must be 4 to match the number of dimensions of the input.
   * @param strides The stride of the sliding window for each dimension of the input
   *  tensor. The length must be 4 to match the number of dimensions of the input.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code QuantizedMaxPool} output and operands
   * @return a new instance of QuantizedMaxPool
   */
  public <T extends TNumber> QuantizedMaxPool<T> quantizedMaxPool(Operand<T> input,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, List<Long> ksize, List<Long> strides,
      String padding) {
    return QuantizedMaxPool.create(scope, input, minInput, maxInput, ksize, strides, padding);
  }

  /**
   * Computes Quantized Rectified Linear: {@code max(features, 0)}
   *
   * @param <U> data type for {@code activations} output
   * @param features the features value
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType the value of the outType property
   * @param <U> data type for {@code QuantizedRelu} output and operands
   * @return a new instance of QuantizedRelu
   */
  public <U extends TNumber> QuantizedRelu<U> quantizedRelu(Operand<? extends TNumber> features,
      Operand<TFloat32> minFeatures, Operand<TFloat32> maxFeatures, Class<U> outType) {
    return QuantizedRelu.create(scope, features, minFeatures, maxFeatures, outType);
  }

  /**
   * Computes Quantized Rectified Linear 6: {@code min(max(features, 0), 6)}
   *
   * @param <U> data type for {@code activations} output
   * @param features the features value
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType the value of the outType property
   * @param <U> data type for {@code QuantizedRelu6} output and operands
   * @return a new instance of QuantizedRelu6
   */
  public <U extends TNumber> QuantizedRelu6<U> quantizedRelu6(Operand<? extends TNumber> features,
      Operand<TFloat32> minFeatures, Operand<TFloat32> maxFeatures, Class<U> outType) {
    return QuantizedRelu6.create(scope, features, minFeatures, maxFeatures, outType);
  }

  /**
   * Computes Quantized Rectified Linear X: {@code min(max(features, 0), max_value)}
   *
   * @param <U> data type for {@code activations} output
   * @param features the features value
   * @param maxValue the maxValue value
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType the value of the outType property
   * @param <U> data type for {@code QuantizedReluX} output and operands
   * @return a new instance of QuantizedReluX
   */
  public <U extends TNumber> QuantizedReluX<U> quantizedReluX(Operand<? extends TNumber> features,
      Operand<TFloat32> maxValue, Operand<TFloat32> minFeatures, Operand<TFloat32> maxFeatures,
      Class<U> outType) {
    return QuantizedReluX.create(scope, features, maxValue, minFeatures, maxFeatures, outType);
  }

  /**
   * Computes rectified linear: {@code max(features, 0)}.
   *  See: https://en.wikipedia.org/wiki/Rectifier_(neural_networks)
   *  Example usage:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.nn.relu([-2., 0., -0., 3.]).numpy()
   *  array([ 0.,  0., -0.,  3.], dtype=float32)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param <T> data type for {@code activations} output
   * @param features the features value
   * @param <T> data type for {@code Relu} output and operands
   * @return a new instance of Relu
   */
  public <T extends TNumber> Relu<T> relu(Operand<T> features) {
    return Relu.create(scope, features);
  }

  /**
   * Computes rectified linear 6: {@code min(max(features, 0), 6)}.
   *
   * @param <T> data type for {@code activations} output
   * @param features the features value
   * @param <T> data type for {@code Relu6} output and operands
   * @return a new instance of Relu6
   */
  public <T extends TNumber> Relu6<T> relu6(Operand<T> features) {
    return Relu6.create(scope, features);
  }

  /**
   * Computes scaled exponential linear: {@code scale * alpha * (exp(features) - 1)}
   *  if &lt; 0, {@code scale * features} otherwise.
   *  <p>To be used together with
   *  {@code initializer = tf.variance_scaling_initializer(factor=1.0, mode='FAN_IN')}.
   *  For correct dropout, use {@code tf.contrib.nn.alpha_dropout}.
   *  <p>See  <a href="https://arxiv.org/abs/1706.02515">Self-Normalizing Neural Networks</a>
   *
   * @param <T> data type for {@code activations} output
   * @param features the features value
   * @param <T> data type for {@code Selu} output and operands
   * @return a new instance of Selu
   */
  public <T extends TNumber> Selu<T> selu(Operand<T> features) {
    return Selu.create(scope, features);
  }

  /**
   * Computes sigmoid cross entropy given <code>logits</code>.
   *
   *  <p>Measures the probability error in discrete classification tasks in which each class is
   *  independent and not mutually exclusive. For instance, one could perform multilabel
   *  classification where a picture can contain both an elephant and a dog at the same time.
   *
   *  <p>For brevity, let <code>x = logits</code>, <code>z = labels</code>. The logistic loss in
   *  pseudo-code is
   *
   *  <pre>
   *  z * -log(sigmoid(x)) + (1 - z) * -log(1 - sigmoid(x))
   *   = z * -log(1 / (1 + exp(-x))) + (1 - z) * -log(exp(-x) / (1 + exp(-x)))
   *   = z * log(1 + exp(-x)) + (1 - z) * (-log(exp(-x)) + log(1 + exp(-x)))
   *   = z * log(1 + exp(-x)) + (1 - z) * (x + log(1 + exp(-x))
   *   = (1 - z) * x + log(1 + exp(-x))
   *   = x - x * z + log(1 + exp(-x))
   *  </pre>
   *
   *  <p>For <code>x < 0</code>, to avoid overflow in <code>exp(-x)</code>, we reformulate the above
   *
   *  <pre>
   *  x - x * z + log(1 + exp(-x))
   *   = log(exp(x)) - x * z + log(1 + exp(-x))
   *   = - x * z + log(1 + exp(x))
   *  </pre>
   *
   *  <p>Hence, to ensure stability and avoid overflow, the implementation uses this equivalent
   *  formulation
   *
   *  <pre>
   *    max(x, 0) - x * z + log(1 + exp(-abs(x)))
   *  </pre>
   *
   *  <p></ode>logits</code> and <code>labels</code> must have the same type and shape.
   *
   *  <p>
   *
   * @param scope The TensorFlow scope
   * @param labels the labels
   * @param logits the logits of type float32 or float64
   * @param <T> the type of labels and logits
   * @return the component-wise logistic losses.
   * @throws IllegalArgumentException if logits' and labels' do not have the same shape
   */
  public <T extends TNumber> Operand<T> sigmoidCrossEntropyWithLogits(Operand<T> labels,
      Operand<T> logits) {
    return SigmoidCrossEntropyWithLogits.sigmoidCrossEntropyWithLogits(scope, labels, logits);
  }

  /**
   * Computes softmax activations.
   *  For each batch {@code i} and class {@code j} we have
   *  <pre>
   *  $$softmax[i, j] = exp(logits[i, j]) / sum_j(exp(logits[i, j]))$$
   *  </pre>
   *
   * @param <T> data type for {@code softmax} output
   * @param logits 2-D with shape {@code [batch_size, num_classes]}.
   * @param <T> data type for {@code Softmax} output and operands
   * @return a new instance of Softmax
   */
  public <T extends TNumber> Softmax<T> softmax(Operand<T> logits) {
    return Softmax.create(scope, logits);
  }

  /**
   * Computes softmax cross entropy between <code>logits</code> and <code>labels</code>.
   *
   *  <p>Measures the probability error in discrete classification tasks in which the classes are
   *  mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image is
   *  labeled with one and only one label: an image can be a dog or a truck, but not both.
   *
   *  <p><b>NOTE:</b>
   *
   *  <p>While the classes are mutually exclusive, their probabilities need not be. All that is
   *  required is that each row of <code>labels</code> is a valid probability distribution. If they
   *  are not, the computation of the gradient will be incorrect.
   *
   *  <p>If using exclusive <code>labels</code> (wherein one and only one class is true at a time),
   *  see {@link org.tensorflow.op.NnOps#sparseSoftmaxCrossEntropyWithLogits}
   *
   *  <p>Usage:
   *
   *  <pre>
   *    Operand&lt;TFloat32&gt; logits =
   *        tf.constant(new float[][] {{4.0F, 2.0F, 1.0F}, {0.0F, 5.0F, 1.0F}} );
   *    Operand&lt;TFloat32&gt; labels =
   *        tf.constant(new float[][] {{1.0F, 0.0F, 0.0F}, {0.0F, 0.8F, 0.2F}} );
   *    Operand&lt;TFloat32&gt; output =
   *        tf.nn.softmaxCrossEntropyWithLogits(labels, logits, -1);
   *    // output Shape = [2]
   *    // dataType = FLOAT (1)
   *    // values { 0.169846, 0.824745 }
   *  </pre>
   *
   *  <p>Backpropagation will happen into both <code>logits</code> and <code>labels</code>. To
   *  disallow backpropagation into <code>labels</code>, pass label tensors through <code>
   *  tf.stopGradient</code> before feeding it to this function.
   *
   * @param scope current scope
   * @param labels Each vector along the class dimension should hold a valid probability
   *      distribution e.g. for the case in which labels are of shape <code>[batch_size, num_classes]
   *      </code>, each row of <code>labels[i]</code> must be a valid probability distribution.
   * @param logits Per-label activations, typically a linear output. These activation energies are
   *      interpreted as unnormalized log probabilities.
   * @param axis The class dimension. -1 is the last dimension.
   * @param <T> the number type of the operands
   * @return the softmax cross entropy loss. Its type is the same as <code>logits</code> and its
   *      shape is the same as <code>labels</code> except that it does not have the last dimension of
   *      <code>labels</code>.
   */
  public <T extends TNumber, U extends TNumber> Operand<T> softmaxCrossEntropyWithLogits(
      Operand<U> labels, Operand<T> logits, int axis) {
    return SoftmaxCrossEntropyWithLogits.softmaxCrossEntropyWithLogits(scope, labels, logits, axis);
  }

  /**
   * Computes softsign: {@code features / (abs(features) + 1)}.
   *
   * @param <T> data type for {@code activations} output
   * @param features the features value
   * @param <T> data type for {@code Softsign} output and operands
   * @return a new instance of Softsign
   */
  public <T extends TNumber> Softsign<T> softsign(Operand<T> features) {
    return Softsign.create(scope, features);
  }

  /**
   * SpaceToBatch for 4-D tensors of type T.
   *  This is a legacy version of the more general SpaceToBatchND.
   *  <p>Zero-pads and then rearranges (permutes) blocks of spatial data into batch.
   *  More specifically, this op outputs a copy of the input tensor where values from
   *  the {@code height} and {@code width} dimensions are moved to the {@code batch} dimension. After
   *  the zero-padding, both {@code height} and {@code width} of the input must be divisible by the
   *  block size.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D with shape {@code [batch, height, width, depth]}.
   * @param paddings 2-D tensor of non-negative integers with shape {@code [2, 2]}. It specifies
   *  the padding of the input with zeros across the spatial dimensions as follows:
   *  <pre>
   *    paddings = [[pad_top, pad_bottom], [pad_left, pad_right]]
   *  </pre>
   *  <p>The effective spatial dimensions of the zero-padded input tensor will be:
   *  <pre>
   *    height_pad = pad_top + height + pad_bottom
   *    width_pad = pad_left + width + pad_right
   *  </pre>
   *  <p>The attr {@code block_size} must be greater than one. It indicates the block size.
   *  <ul>
   *  <li>Non-overlapping blocks of size {@code block_size x block size} in the height and
   *  width dimensions are rearranged into the batch dimension at each location.</li>
   *  <li>The batch of the output tensor is {@code batch * block_size * block_size}.</li>
   *  <li>Both height_pad and width_pad must be divisible by block_size.</li>
   *  </ul>
   *  <p>The shape of the output will be:
   *  <pre>
   *  [batch*block_size*block_size, height_pad/block_size, width_pad/block_size,
   *   depth]
   *  </pre>
   *  <p>Some examples:
   *  <p>(1) For the following input of shape {@code [1, 2, 2, 1]} and block_size of 2:
   *  <pre>
   *  x = [[[[1], [2]], [[3], [4]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [4, 1, 1, 1]} and value:
   *  <pre>
   *  [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   *  </pre>
   *  <p>(2) For the following input of shape {@code [1, 2, 2, 3]} and block_size of 2:
   *  <pre>
   *  x = [[[[1, 2, 3], [4, 5, 6]],
   *        [[7, 8, 9], [10, 11, 12]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [4, 1, 1, 3]} and value:
   *  <pre>
   *  [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   *  </pre>
   *  <p>(3) For the following input of shape {@code [1, 4, 4, 1]} and block_size of 2:
   *  <pre>
   *  x = [[[[1],   [2],  [3],  [4]],
   *        [[5],   [6],  [7],  [8]],
   *        [[9],  [10], [11],  [12]],
   *        [[13], [14], [15],  [16]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [4, 2, 2, 1]} and value:
   *  <pre>
   *  x = [[[[1], [3]], [[9], [11]]],
   *       [[[2], [4]], [[10], [12]]],
   *       [[[5], [7]], [[13], [15]]],
   *       [[[6], [8]], [[14], [16]]]]
   *  </pre>
   *  <p>(4) For the following input of shape {@code [2, 2, 4, 1]} and block_size of 2:
   *  <pre>
   *  x = [[[[1],   [2],  [3],  [4]],
   *        [[5],   [6],  [7],  [8]]],
   *       [[[9],  [10], [11],  [12]],
   *        [[13], [14], [15],  [16]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [8, 1, 2, 1]} and value:
   *  <pre>
   *  x = [[[[1], [3]]], [[[9], [11]]], [[[2], [4]]], [[[10], [12]]],
   *       [[[5], [7]]], [[[13], [15]]], [[[6], [8]]], [[[14], [16]]]]
   *  </pre>
   *  <p>Among others, this operation is useful for reducing atrous convolution into
   *  regular convolution.
   * @param blockSize the value of the blockSize property
   * @param <T> data type for {@code SpaceToBatch} output and operands
   * @return a new instance of SpaceToBatch
   */
  public <T extends TType> SpaceToBatch<T> spaceToBatch(Operand<T> input,
      Operand<? extends TNumber> paddings, Long blockSize) {
    return SpaceToBatch.create(scope, input, paddings, blockSize);
  }

  /**
   * SpaceToDepth for tensors of type T.
   *  Rearranges blocks of spatial data, into depth. More specifically,
   *  this op outputs a copy of the input tensor where values from the {@code height}
   *  and {@code width} dimensions are moved to the {@code depth} dimension.
   *  The attr {@code block_size} indicates the input block size.
   *  <ul>
   *  <li>Non-overlapping blocks of size {@code block_size x block size} are rearranged
   *  into depth at each location.</li>
   *  <li>The depth of the output tensor is {@code block_size * block_size * input_depth}.</li>
   *  <li>The Y, X coordinates within each block of the input become the high order
   *  component of the output channel index.</li>
   *  <li>The input tensor's height and width must be divisible by block_size.</li>
   *  </ul>
   *  <p>The {@code data_format} attr specifies the layout of the input and output tensors
   *  with the following options:
   *  &quot;NHWC&quot;: {@code [ batch, height, width, channels ]}
   *  &quot;NCHW&quot;: {@code [ batch, channels, height, width ]}
   *  &quot;NCHW_VECT_C&quot;:
   *  {@code qint8 [ batch, channels / 4, height, width, 4 ]}
   *  <p>It is useful to consider the operation as transforming a 6-D Tensor.
   *  e.g. for data_format = NHWC,
   *  Each element in the input tensor can be specified via 6 coordinates,
   *  ordered by decreasing memory layout significance as:
   *  n,oY,bY,oX,bX,iC  (where n=batch index, oX, oY means X or Y coordinates
   *  within the output image, bX, bY means coordinates
   *  within the input block, iC means input channels).
   *  The output would be a transpose to the following layout:
   *  n,oY,oX,bY,bX,iC
   *  <p>This operation is useful for resizing the activations between convolutions
   *  (but keeping all data), e.g. instead of pooling. It is also useful for training
   *  purely convolutional models.
   *  <p>For example, given an input of shape {@code [1, 2, 2, 1]}, data_format = &quot;NHWC&quot; and
   *  block_size = 2:
   *  <pre>
   *  x = [[[[1], [2]],
   *        [[3], [4]]]]
   *  </pre>
   *  <p>This operation will output a tensor of shape {@code [1, 1, 1, 4]}:
   *  <pre>
   *  [[[[1, 2, 3, 4]]]]
   *  </pre>
   *  <p>Here, the input has a batch of 1 and each batch element has shape {@code [2, 2, 1]},
   *  the corresponding output will have a single element (i.e. width and height are
   *  both 1) and will have a depth of 4 channels (1 * block_size * block_size).
   *  The output element shape is {@code [1, 1, 4]}.
   *  <p>For an input tensor with larger depth, here of shape {@code [1, 2, 2, 3]}, e.g.
   *  <pre>
   *  x = [[[[1, 2, 3], [4, 5, 6]],
   *        [[7, 8, 9], [10, 11, 12]]]]
   *  </pre>
   *  <p>This operation, for block_size of 2, will return the following tensor of shape
   *  {@code [1, 1, 1, 12]}
   *  <pre>
   *  [[[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]]]
   *  </pre>
   *  <p>Similarly, for the following input of shape {@code [1 4 4 1]}, and a block size of 2:
   *  <pre>
   *  x = [[[[1],   [2],  [5],  [6]],
   *        [[3],   [4],  [7],  [8]],
   *        [[9],  [10], [13],  [14]],
   *        [[11], [12], [15],  [16]]]]
   *  </pre>
   *  <p>the operator will return the following tensor of shape {@code [1 2 2 4]}:
   *  <pre>
   *  x = [[[[1, 2, 3, 4],
   *         [5, 6, 7, 8]],
   *        [[9, 10, 11, 12],
   *         [13, 14, 15, 16]]]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param blockSize The size of the spatial block.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SpaceToDepth} output and operands
   * @return a new instance of SpaceToDepth
   */
  public <T extends TType> SpaceToDepth<T> spaceToDepth(Operand<T> input, Long blockSize,
      SpaceToDepth.Options... options) {
    return SpaceToDepth.create(scope, input, blockSize, options);
  }

  /**
   * Computes sparse softmax cross entropy between <code>logits</code> and <code>labels</code>.
   *
   *  <p>Measures the probability error in discrete classification tasks in which the classes are
   *  mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image is
   *  labeled with one and only one label: an image can be a dog or a truck, but not both.
   *
   *  <p><b>NOTE:</b>
   *
   *  <p>For this operation, the probability of a given label is considered exclusive. That is, soft
   *  classes are not allowed, and the <code>labels</code> vector must provide a single specific
   *  index for the true class for each row of <code>logits</code> (each minibatch entry). For soft
   *  softmax classification with a probability distribution for each entry, {@link
   *  org.tensorflow.op.NnOps#softmaxCrossEntropyWithLogits}.
   *
   *  <p><b>WARNING:</b>
   *
   *  <p>This op expects unscaled logits, since it performs a <code>softmax</code> on <code>logits
   *  </code> internally for efficiency. Do not call this op with the output of <code>softmax</code>,
   *  as it will produce incorrect results.
   *
   *  <p>A common use case is to have logits of shape <code>[batchSize, numClasses]</code> and have
   *  labels of shape <code>[batchSize]</code>, but higher dimensions are supported, in which case
   *  the <code>dim</code>-th dimension is assumed to be of size <code>numClasses</code>. <code>
   *  logits</code> must have the <cod>dataType</cod> of <code>TFloat16</code>, <code>TFloat32</code>
   *  , or <code>TFloat64</code>, and <code>labels</code> must have the dtype of <code>TInt32</code>
   *  or <code>TInt64</code>.
   *
   * @param scope current scope
   * @param labels <code>Tensor</code> of shape <code>[d_0, d_1, ..., d_{r-1}]</code> (where <code>r
   *      </code> is rank of <code>labels</code> and result) and the dataType is <code>TInt32</code>
   *      or <code>TInt64</code>. Each entry in <code>labels</code> must be an index in <code>[0,
   *      numClasses)</code>. Other values will raise an exception when this op is run on CPU, and
   *      return <code>NaN</code> for corresponding loss and gradient rows on GPU.
   * @param logits Per-label activations (typically a linear output) of shape <code>[d_0, d_1, ...,
   *      d_{r-1}, numClasses]</code> and dataType of <code>TFloat16</code>, <code>TFloat32</code>,
   *      or <code>TFloat64</code>. These activation energies are interpreted as unnormalized log
   *      probabilities.
   * @return A <code>Tensor</code> of the same shape as <code>labels</code> and of the same type as
   *      <code>logits</code> with the softmax cross entropy loss.
   * @throws IllegalArgumentException If logits are scalars (need to have rank >= 1) or if the rank
   *      of the labels is not equal to the rank of the logits minus one.
   */
  public <T extends TNumber, U extends TNumber> Operand sparseSoftmaxCrossEntropyWithLogits(
      Operand<T> labels, Operand<U> logits) {
    return SparseSoftmaxCrossEntropyWithLogits.sparseSoftmaxCrossEntropyWithLogits(scope, labels, logits);
  }

  /**
   * Finds values and indices of the {@code k} largest elements for the last dimension.
   *  If the input is a vector (rank-1), finds the {@code k} largest entries in the vector
   *  and outputs their values and indices as vectors.  Thus {@code values[j]} is the
   *  {@code j}-th largest entry in {@code input}, and its index is {@code indices[j]}.
   *  <p>For matrices (resp. higher rank input), computes the top {@code k} entries in each
   *  row (resp. vector along the last dimension).  Thus,
   *  <pre>
   *  values.shape = indices.shape = input.shape[:-1] + [k]
   *  </pre>
   *  <p>If two elements are equal, the lower-index element appears first.
   *
   * @param <T> data type for {@code values} output
   * @param input 1-D or higher with last dimension at least {@code k}.
   * @param k 0-D.  Number of top elements to look for along the last dimension (along each
   *  row for matrices).
   * @param options carries optional attribute values
   * @param <T> data type for {@code TopKV2} output and operands
   * @return a new instance of TopK
   */
  public <T extends TNumber> TopK<T> topK(Operand<T> input, Operand<TInt32> k,
      TopK.Options... options) {
    return TopK.create(scope, input, k, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
