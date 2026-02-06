// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.op.nn.AvgPoolGrad;
import org.tensorflow.op.nn.BatchNormWithGlobalNormalization;
import org.tensorflow.op.nn.BatchNormWithGlobalNormalizationGrad;
import org.tensorflow.op.nn.BiasAdd;
import org.tensorflow.op.nn.BiasAddGrad;
import org.tensorflow.op.nn.BlockLSTM;
import org.tensorflow.op.nn.BlockLSTMGrad;
import org.tensorflow.op.nn.CTCLossV2;
import org.tensorflow.op.nn.ComputeAccidentalHits;
import org.tensorflow.op.nn.Conv;
import org.tensorflow.op.nn.Conv2d;
import org.tensorflow.op.nn.Conv2dBackpropFilter;
import org.tensorflow.op.nn.Conv2dBackpropInput;
import org.tensorflow.op.nn.Conv3d;
import org.tensorflow.op.nn.Conv3dBackpropFilter;
import org.tensorflow.op.nn.Conv3dBackpropInput;
import org.tensorflow.op.nn.CtcBeamSearchDecoder;
import org.tensorflow.op.nn.CtcGreedyDecoder;
import org.tensorflow.op.nn.CtcLoss;
import org.tensorflow.op.nn.CudnnRNN;
import org.tensorflow.op.nn.CudnnRNNBackprop;
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
import org.tensorflow.op.nn.EluGrad;
import org.tensorflow.op.nn.FixedUnigramCandidateSampler;
import org.tensorflow.op.nn.FractionalAvgPool;
import org.tensorflow.op.nn.FractionalAvgPoolGrad;
import org.tensorflow.op.nn.FractionalMaxPool;
import org.tensorflow.op.nn.FractionalMaxPoolGrad;
import org.tensorflow.op.nn.FusedBatchNorm;
import org.tensorflow.op.nn.FusedBatchNormGrad;
import org.tensorflow.op.nn.FusedPadConv2d;
import org.tensorflow.op.nn.FusedResizeAndPadConv2d;
import org.tensorflow.op.nn.GRUBlockCell;
import org.tensorflow.op.nn.GRUBlockCellGrad;
import org.tensorflow.op.nn.InTopK;
import org.tensorflow.op.nn.InvGrad;
import org.tensorflow.op.nn.IsotonicRegression;
import org.tensorflow.op.nn.L2Loss;
import org.tensorflow.op.nn.LSTMBlockCell;
import org.tensorflow.op.nn.LSTMBlockCellGrad;
import org.tensorflow.op.nn.LeakyRelu;
import org.tensorflow.op.nn.LearnedUnigramCandidateSampler;
import org.tensorflow.op.nn.LocalResponseNormalization;
import org.tensorflow.op.nn.LocalResponseNormalizationGrad;
import org.tensorflow.op.nn.LogSoftmax;
import org.tensorflow.op.nn.MaxPool;
import org.tensorflow.op.nn.MaxPool3d;
import org.tensorflow.op.nn.MaxPool3dGrad;
import org.tensorflow.op.nn.MaxPool3dGradGrad;
import org.tensorflow.op.nn.MaxPoolGrad;
import org.tensorflow.op.nn.MaxPoolGradGrad;
import org.tensorflow.op.nn.MaxPoolGradGradWithArgmax;
import org.tensorflow.op.nn.MaxPoolGradWithArgmax;
import org.tensorflow.op.nn.MaxPoolWithArgmax;
import org.tensorflow.op.nn.NthElement;
import org.tensorflow.op.nn.QuantizedAvgPool;
import org.tensorflow.op.nn.QuantizedBatchNormWithGlobalNormalization;
import org.tensorflow.op.nn.QuantizedBiasAdd;
import org.tensorflow.op.nn.QuantizedConv2DAndRelu;
import org.tensorflow.op.nn.QuantizedConv2DAndReluAndRequantize;
import org.tensorflow.op.nn.QuantizedConv2DAndRequantize;
import org.tensorflow.op.nn.QuantizedConv2DPerChannel;
import org.tensorflow.op.nn.QuantizedConv2DWithBias;
import org.tensorflow.op.nn.QuantizedConv2DWithBiasAndRelu;
import org.tensorflow.op.nn.QuantizedConv2DWithBiasAndReluAndRequantize;
import org.tensorflow.op.nn.QuantizedConv2DWithBiasAndRequantize;
import org.tensorflow.op.nn.QuantizedConv2DWithBiasSignedSumAndReluAndRequantize;
import org.tensorflow.op.nn.QuantizedConv2DWithBiasSumAndRelu;
import org.tensorflow.op.nn.QuantizedConv2DWithBiasSumAndReluAndRequantize;
import org.tensorflow.op.nn.QuantizedConv2d;
import org.tensorflow.op.nn.QuantizedDepthwiseConv2D;
import org.tensorflow.op.nn.QuantizedDepthwiseConv2DWithBias;
import org.tensorflow.op.nn.QuantizedDepthwiseConv2DWithBiasAndRelu;
import org.tensorflow.op.nn.QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize;
import org.tensorflow.op.nn.QuantizedInstanceNorm;
import org.tensorflow.op.nn.QuantizedMaxPool;
import org.tensorflow.op.nn.QuantizedRelu;
import org.tensorflow.op.nn.QuantizedRelu6;
import org.tensorflow.op.nn.QuantizedReluX;
import org.tensorflow.op.nn.Relu;
import org.tensorflow.op.nn.Relu6;
import org.tensorflow.op.nn.Relu6Grad;
import org.tensorflow.op.nn.ReluGrad;
import org.tensorflow.op.nn.Selu;
import org.tensorflow.op.nn.SeluGrad;
import org.tensorflow.op.nn.Softmax;
import org.tensorflow.op.nn.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.Softsign;
import org.tensorflow.op.nn.SoftsignGrad;
import org.tensorflow.op.nn.SpaceToBatch;
import org.tensorflow.op.nn.SpaceToDepth;
import org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.TopK;
import org.tensorflow.op.nn.UniformQuantizedConvolution;
import org.tensorflow.op.nn.UniformQuantizedConvolutionHybrid;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code nn} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class NnOps {
  private final Scope scope;

  private final Ops ops;

  NnOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Performs average pooling on the input.
   *  Each entry in {@code output} is the mean of the corresponding size {@code ksize}
   *  window in {@code value}.
   *
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
   * Computes gradients of the average pooling function.
   *
   * @param origInputShape 1-D.  Shape of the original input to {@code avg_pool}.
   * @param grad 4-D with shape {@code [batch, height, width, channels]}.  Gradients w.r.t.
   *  the output of {@code avg_pool}.
   * @param ksize The size of the sliding window for each dimension of the input.
   * @param strides The stride of the sliding window for each dimension of the input.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AvgPoolGrad} output and operands
   * @return a new instance of AvgPoolGrad
   */
  public <T extends TNumber> AvgPoolGrad<T> avgPoolGrad(Operand<TInt32> origInputShape,
      Operand<T> grad, List<Long> ksize, List<Long> strides, String padding,
      AvgPoolGrad.Options... options) {
    return AvgPoolGrad.create(scope, origInputShape, grad, ksize, strides, padding, options);
  }

  /**
   * Batch normalization.
   *  This op is deprecated. Prefer {@code tf.nn.batch_normalization}.
   *
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
   * Computes the LSTM cell forward propagation for all the time steps.
   *  This is equivalent to applying LSTMBlockCell in a loop, like so:
   *  <pre>
   *  for x1 in unpack(x):
   *    i1, cs1, f1, o1, ci1, co1, h1 = LSTMBlock(
   *      x1, cs_prev, h_prev, w, wci, wcf, wco, b)
   *    cs_prev = cs1
   *    h_prev = h1
   *    i.append(i1)
   *    cs.append(cs1)
   *    f.append(f1)
   *    o.append(o1)
   *    ci.append(ci1)
   *    co.append(co1)
   *    h.append(h1)
   *  return pack(i), pack(cs), pack(f), pack(o), pack(ci), pack(ch), pack(h)
   *
   *  Note that unlike LSTMBlockCell (and BlockLSTM) which uses ICFO gate layout,
   *  this op uses IFCO. So in order for the following snippet to be equivalent
   *  all gate-related outputs should be reordered.
   *  </pre>
   *
   * @param seqLenMax Maximum time length actually used by this input. Outputs are padded
   *  with zeros beyond this length.
   * @param x The sequence input to the LSTM, shape (timelen, batch_size, num_inputs).
   * @param csPrev Value of the initial cell state.
   * @param hPrev Initial output of cell (to be used for peephole).
   * @param w The weight matrix.
   * @param wci The weight matrix for input gate peephole connection.
   * @param wcf The weight matrix for forget gate peephole connection.
   * @param wco The weight matrix for output gate peephole connection.
   * @param b The bias vector.
   * @param options carries optional attribute values
   * @param <T> data type for {@code BlockLSTMV2} output and operands
   * @return a new instance of BlockLSTM
   */
  public <T extends TNumber> BlockLSTM<T> blockLSTM(Operand<TInt64> seqLenMax, Operand<T> x,
      Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf,
      Operand<T> wco, Operand<T> b, BlockLSTM.Options... options) {
    return BlockLSTM.create(scope, seqLenMax, x, csPrev, hPrev, w, wci, wcf, wco, b, options);
  }

  /**
   * Computes the LSTM cell backward propagation for the entire time sequence.
   *  This implementation is to be used in conjunction of BlockLSTMV2.
   *
   * @param seqLenMax Maximum time length actually used by this input. Outputs are padded
   *  with zeros beyond this length.
   * @param x The sequence input to the LSTM, shape (timelen, batch_size, num_inputs).
   * @param csPrev Value of the initial cell state.
   * @param hPrev Initial output of cell (to be used for peephole).
   * @param w The weight matrix.
   * @param wci The weight matrix for input gate peephole connection.
   * @param wcf The weight matrix for forget gate peephole connection.
   * @param wco The weight matrix for output gate peephole connection.
   * @param b The bias vector.
   * @param i The input gate over the whole time sequence.
   * @param cs The cell state before the tanh over the whole time sequence.
   * @param f The forget gate over the whole time sequence.
   * @param o The output gate over the whole time sequence.
   * @param ci The cell input over the whole time sequence.
   * @param co The cell after the tanh over the whole time sequence.
   * @param h The output h vector over the whole time sequence.
   * @param csGrad The current gradient of cs.
   * @param hGrad The gradient of h vector.
   * @param usePeephole Whether to use peephole weights.
   * @param <T> data type for {@code BlockLSTMGradV2} output and operands
   * @return a new instance of BlockLSTMGrad
   */
  public <T extends TNumber> BlockLSTMGrad<T> blockLSTMGrad(Operand<TInt64> seqLenMax, Operand<T> x,
      Operand<T> csPrev, Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf,
      Operand<T> wco, Operand<T> b, Operand<T> i, Operand<T> cs, Operand<T> f, Operand<T> o,
      Operand<T> ci, Operand<T> co, Operand<T> h, Operand<T> csGrad, Operand<T> hGrad,
      Boolean usePeephole) {
    return BlockLSTMGrad.create(scope, seqLenMax, x, csPrev, hPrev, w, wci, wcf, wco, b, i, cs, f, o, ci, co, h, csGrad, hGrad, usePeephole);
  }

  /**
   * Calculates the CTC Loss (log probability) for each batch entry.  Also calculates
   *  the gradient.  This class performs the softmax operation for you, so inputs
   *  should be e.g. linear projections of outputs by an LSTM.
   *
   * @param inputs 3-D, shape: {@code (max_time x batch_size x num_classes)}, the logits. Default blank
   *  label is 0 rather num_classes - 1.
   * @param labelsIndices The indices of a {@code SparseTensor<int32, 2>}.
   *  {@code labels_indices(i, :) == [b, t]} means {@code labels_values(i)} stores the id for
   *  {@code (batch b, time t)}.
   * @param labelsValues The values (labels) associated with the given batch and time.
   * @param sequenceLength A vector containing sequence lengths (batch).
   * @param options carries optional attribute values
   * @return a new instance of CTCLossV2
   */
  public CTCLossV2 cTCLossV2(Operand<TFloat32> inputs, Operand<TInt64> labelsIndices,
      Operand<TInt32> labelsValues, Operand<TInt32> sequenceLength, CTCLossV2.Options... options) {
    return CTCLossV2.create(scope, inputs, labelsIndices, labelsValues, sequenceLength, options);
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
   * Computes a N-D convolution given (N+1+batch_dims)-D {@code input} and (N+2)-D {@code filter} tensors.
   *  General function for computing a N-D convolution. It is required that
   *  {@code 1 <= N <= 3}.
   *
   * @param input Tensor of type T and shape {@code batch_shape + spatial_shape + [in_channels]} in the
   *  case that {@code channels_last_format = true} or shape
   *  {@code batch_shape + [in_channels] + spatial_shape} if {@code channels_last_format = false}.
   *  spatial_shape is N-dimensional with {@code N=2} or {@code N=3}.
   *  Also note that {@code batch_shape} is dictated by the parameter {@code batch_dims}
   *  and defaults to 1.
   * @param filter An {@code (N+2)-D} Tensor with the same type as {@code input} and shape
   *  {@code spatial_filter_shape + [in_channels, out_channels]}, where spatial_filter_shape
   *  is N-dimensional with {@code N=2} or {@code N=3}.
   * @param strides 1-D tensor of length {@code N+2}. The stride of the sliding window for each
   *  dimension of {@code input}. Must have {@code strides[0] = strides[N+1] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv} output and operands
   * @return a new instance of Conv
   */
  public <T extends TNumber> Conv<T> conv(Operand<T> input, Operand<T> filter, List<Long> strides,
      String padding, Conv.Options... options) {
    return Conv.create(scope, input, filter, strides, padding, options);
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
   * A RNN backed by cuDNN.
   *  Computes the RNN from the input and initial states, with respect to the params
   *  buffer. Accepts one extra input &quot;sequence_lengths&quot; than CudnnRNN.
   *  <p>rnn_mode: Indicates the type of the RNN model.
   *  input_mode: Indicates whether there is a linear projection between the input and
   *  the actual computation before the first layer. 'skip_input' is only allowed
   *  when input_size == num_units; 'auto_select' implies 'skip_input' when
   *  input_size == num_units; otherwise, it implies 'linear_input'.
   *  direction: Indicates whether a bidirectional model will be used. Should be
   *  &quot;unidirectional&quot; or &quot;bidirectional&quot;.
   *  dropout: Dropout probability. When set to 0., dropout is disabled.
   *  seed: The 1st part of a seed to initialize dropout.
   *  seed2: The 2nd part of a seed to initialize dropout.
   *  input: If time_major is true, this is a 3-D tensor with the shape of
   *  [seq_length, batch_size, input_size]. If time_major is false, the shape is
   *  [batch_size, seq_length, input_size].
   *  input_h: If time_major is true, this is a 3-D tensor with the shape of
   *  [num_layer * dir, batch_size, num_units]. If time_major is false, the shape
   *  is [batch_size, num_layer * dir, num_units].
   *  input_c: For LSTM, a 3-D tensor with the shape of
   *  [num_layer * dir, batch, num_units]. For other models, it is ignored.
   *  params: A 1-D tensor that contains the weights and biases in an opaque layout.
   *  The size must be created through CudnnRNNParamsSize, and initialized
   *  separately. Note that they might not be compatible across different
   *  generations. So it is a good idea to save and restore
   *  sequence_lengths: a vector of lengths of each input sequence.
   *  output: If time_major is true, this is a 3-D tensor with the shape of
   *  [seq_length, batch_size, dir * num_units]. If time_major is false, the
   *  shape is [batch_size, seq_length, dir * num_units].
   *  output_h: The same shape has input_h.
   *  output_c: The same shape as input_c for LSTM. An empty tensor for other models.
   *  is_training: Indicates whether this operation is used for inference or
   *  training.
   *  time_major: Indicates whether the input/output format is time major or batch
   *  major.
   *  reserve_space: An opaque tensor that can be used in backprop calculation. It
   *  is only produced if is_training is true.
   *
   * @param input The input value
   * @param inputH The inputH value
   * @param inputC The inputC value
   * @param params The params value
   * @param sequenceLengths The sequenceLengths value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNV3} output and operands
   * @return a new instance of CudnnRNN
   */
  public <T extends TNumber> CudnnRNN<T> cudnnRNN(Operand<T> input, Operand<T> inputH,
      Operand<T> inputC, Operand<T> params, Operand<TInt32> sequenceLengths,
      CudnnRNN.Options... options) {
    return CudnnRNN.create(scope, input, inputH, inputC, params, sequenceLengths, options);
  }

  /**
   * Backprop step of CudnnRNNV3.
   *  Compute the backprop of both data and weights in a RNN. Takes an extra
   *  &quot;sequence_lengths&quot; input than CudnnRNNBackprop.
   *  <p>rnn_mode: Indicates the type of the RNN model.
   *  input_mode: Indicates whether there is a linear projection between the input and
   *  the actual computation before the first layer. 'skip_input' is only allowed
   *  when input_size == num_units; 'auto_select' implies 'skip_input' when
   *  input_size == num_units; otherwise, it implies 'linear_input'.
   *  direction: Indicates whether a bidirectional model will be used. Should be
   *  &quot;unidirectional&quot; or &quot;bidirectional&quot;.
   *  dropout: Dropout probability. When set to 0., dropout is disabled.
   *  seed: The 1st part of a seed to initialize dropout.
   *  seed2: The 2nd part of a seed to initialize dropout.
   *  input: If time_major is true, this is a 3-D tensor with the shape of
   *  [seq_length, batch_size, input_size]. If time_major is false, the shape is
   *  [batch_size, seq_length, input_size].
   *  input_h: If time_major is true, this is a 3-D tensor with the shape of
   *  [num_layer * dir, batch_size, num_units]. If time_major is false, the shape
   *  is [batch_size, num_layer * dir, num_units].
   *  input_c: For LSTM, a 3-D tensor with the shape of
   *  [num_layer * dir, batch, num_units]. For other models, it is ignored.
   *  params: A 1-D tensor that contains the weights and biases in an opaque layout.
   *  The size must be created through CudnnRNNParamsSize, and initialized
   *  separately. Note that they might not be compatible across different
   *  generations. So it is a good idea to save and restore
   *  sequence_lengths: a vector of lengths of each input sequence.
   *  output: If time_major is true, this is a 3-D tensor with the shape of
   *  [seq_length, batch_size, dir * num_units]. If time_major is false, the
   *  shape is [batch_size, seq_length, dir * num_units].
   *  output_h: The same shape has input_h.
   *  output_c: The same shape as input_c for LSTM. An empty tensor for other models.
   *  output_backprop: A 3-D tensor with the same shape as output in the forward pass.
   *  output_h_backprop: A 3-D tensor with the same shape as output_h in the forward
   *  pass.
   *  output_c_backprop: A 3-D tensor with the same shape as output_c in the forward
   *  pass.
   *  time_major: Indicates whether the input/output format is time major or batch
   *  major.
   *  reserve_space: The same reserve_space produced in the forward operation.
   *  input_backprop: The backprop to input in the forward pass. Has the same shape
   *  as input.
   *  input_h_backprop: The backprop to input_h in the forward pass. Has the same
   *  shape as input_h.
   *  input_c_backprop: The backprop to input_c in the forward pass. Has the same
   *  shape as input_c.
   *  params_backprop: The backprop to the params buffer in the forward pass. Has the
   *  same shape as params.
   *
   * @param input The input value
   * @param inputH The inputH value
   * @param inputC The inputC value
   * @param params The params value
   * @param sequenceLengths The sequenceLengths value
   * @param output The output value
   * @param outputH The outputH value
   * @param outputC The outputC value
   * @param outputBackprop The outputBackprop value
   * @param outputHBackprop The outputHBackprop value
   * @param outputCBackprop The outputCBackprop value
   * @param reserveSpace The reserveSpace value
   * @param hostReserved The hostReserved value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNBackpropV3} output and operands
   * @return a new instance of CudnnRNNBackprop
   */
  public <T extends TNumber> CudnnRNNBackprop<T> cudnnRNNBackprop(Operand<T> input,
      Operand<T> inputH, Operand<T> inputC, Operand<T> params, Operand<TInt32> sequenceLengths,
      Operand<T> output, Operand<T> outputH, Operand<T> outputC, Operand<T> outputBackprop,
      Operand<T> outputHBackprop, Operand<T> outputCBackprop, Operand<T> reserveSpace,
      Operand<? extends TType> hostReserved, CudnnRNNBackprop.Options... options) {
    return CudnnRNNBackprop.create(scope, input, inputH, inputC, params, sequenceLengths, output, outputH, outputC, outputBackprop, outputHBackprop, outputCBackprop, reserveSpace, hostReserved, options);
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
   * @param numLayers The numLayers value
   * @param numUnits The numUnits value
   * @param inputSize The inputSize value
   * @param weights The weights value
   * @param biases The biases value
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
   * @param numLayers The numLayers value
   * @param numUnits The numUnits value
   * @param inputSize The inputSize value
   * @param params The params value
   * @param numParamsWeights The value of the numParamsWeights attribute
   * @param numParamsBiases The value of the numParamsBiases attribute
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
   * @param numLayers The numLayers value
   * @param numUnits The numUnits value
   * @param inputSize The inputSize value
   * @param T The value of the T attribute
   * @param S The value of the S attribute
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
   *  Given source and destination format strings of length n=4 or 5, the input
   *  tensor must be a vector of size n or n-2, or a 2D tensor of shape
   *  (n, 2) or (n-2, 2).
   *  <p>If the first dimension of the input tensor is n-2, it is assumed that
   *  non-spatial dimensions are omitted (i.e {@code N}, {@code C}).
   *  <p>For example, with {@code src_format} of {@code NHWC}, {@code dst_format} of {@code NCHW}, and input:
   *  <pre>
   *  [1, 2, 3, 4]
   *  </pre>
   *  <p>, the output will be:
   *  <pre>
   *  [1, 4, 2, 3]
   *  </pre>
   *  <p>With {@code src_format} of {@code NDHWC}, {@code dst_format} of {@code NCDHW}, and input:
   *  <pre>
   *  [[1, 6], [2, 7], [3, 8], [4, 9], [5, 10]]
   *  </pre>
   *  <p>, the output will be:
   *  <pre>
   *  [[1, 6], [5, 10], [2, 7], [3, 8], [4, 9]]
   *  </pre>
   *  <p>With {@code src_format} of {@code NHWC}, {@code dst_format} of {@code NCHW}, and input:
   *  <pre>
   *  [1, 2]
   *  </pre>
   *  <p>, the output will be:
   *  <pre>
   *  [1, 2]
   *  </pre>
   *
   * @param x Tensor of rank 1 or 2 in source data format.
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
   *  <li>The width of the output tensor is {@code input_depth * block_size}, whereas the
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
   * @param input The input value
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
   * @param input The input value
   * @param filter The filter value
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
   * Computes the exponential linear function.
   *  The ELU function is defined as:
   *  <ul>
   *  <li>$ e ^ x - 1 $ if $ x &lt; 0 $</li>
   *  <li>$ x $ if $ x &gt;= 0 $</li>
   *  </ul>
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.nn.elu(1.0)
   *  &lt;tf.Tensor: shape=(), dtype=float32, numpy=1.0&gt;
   *  tf.nn.elu(0.0)
   *  &lt;tf.Tensor: shape=(), dtype=float32, numpy=0.0&gt;
   *  tf.nn.elu(-1000.0)
   *  &lt;tf.Tensor: shape=(), dtype=float32, numpy=-1.0&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *  <p>See  <a href="http://arxiv.org/abs/1511.07289">Fast and Accurate Deep Network Learning by Exponential Linear Units (ELUs)
   *  </a>
   *
   * @param features The features value
   * @param <T> data type for {@code Elu} output and operands
   * @return a new instance of Elu
   */
  public <T extends TNumber> Elu<T> elu(Operand<T> features) {
    return Elu.create(scope, features);
  }

  /**
   * Computes gradients for the exponential linear (Elu) operation.
   *
   * @param gradients The backpropagated gradients to the corresponding Elu operation.
   * @param outputs The outputs of the corresponding Elu operation.
   * @param <T> data type for {@code EluGrad} output and operands
   * @return a new instance of EluGrad
   */
  public <T extends TNumber> EluGrad<T> eluGrad(Operand<T> gradients, Operand<T> outputs) {
    return EluGrad.create(scope, gradients, outputs);
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
   * Computes gradient of the FractionalAvgPool function.
   *  Unlike FractionalMaxPoolGrad, we don't need to find arg_max for
   *  FractionalAvgPoolGrad, we just need to evenly back-propagate each element of
   *  out_backprop to those indices that form the same pooling cell. Therefore, we
   *  just need to know the shape of original input tensor, instead of the whole
   *  tensor.
   *
   * @param origInputTensorShape Original input tensor shape for {@code fractional_avg_pool}
   * @param outBackprop 4-D with shape {@code [batch, height, width, channels]}.  Gradients
   *  w.r.t. the output of {@code fractional_avg_pool}.
   * @param rowPoolingSequence row pooling sequence, form pooling region with
   *  col_pooling_sequence.
   * @param colPoolingSequence column pooling sequence, form pooling region with
   *  row_pooling sequence.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FractionalAvgPoolGrad} output and operands
   * @return a new instance of FractionalAvgPoolGrad
   */
  public <T extends TNumber> FractionalAvgPoolGrad<T> fractionalAvgPoolGrad(
      Operand<TInt64> origInputTensorShape, Operand<T> outBackprop,
      Operand<TInt64> rowPoolingSequence, Operand<TInt64> colPoolingSequence,
      FractionalAvgPoolGrad.Options... options) {
    return FractionalAvgPoolGrad.create(scope, origInputTensorShape, outBackprop, rowPoolingSequence, colPoolingSequence, options);
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
   * Computes gradient of the FractionalMaxPool function.
   *
   * @param origInput Original input for {@code fractional_max_pool}
   * @param origOutput Original output for {@code fractional_max_pool}
   * @param outBackprop 4-D with shape {@code [batch, height, width, channels]}.  Gradients
   *  w.r.t. the output of {@code fractional_max_pool}.
   * @param rowPoolingSequence row pooling sequence, form pooling region with
   *  col_pooling_sequence.
   * @param colPoolingSequence column pooling sequence, form pooling region with
   *  row_pooling sequence.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FractionalMaxPoolGrad} output and operands
   * @return a new instance of FractionalMaxPoolGrad
   */
  public <T extends TNumber> FractionalMaxPoolGrad<T> fractionalMaxPoolGrad(Operand<T> origInput,
      Operand<T> origOutput, Operand<T> outBackprop, Operand<TInt64> rowPoolingSequence,
      Operand<TInt64> colPoolingSequence, FractionalMaxPoolGrad.Options... options) {
    return FractionalMaxPoolGrad.create(scope, origInput, origOutput, outBackprop, rowPoolingSequence, colPoolingSequence, options);
  }

  /**
   * Batch normalization.
   *  Note that the size of 4D Tensors are defined by either &quot;NHWC&quot; or &quot;NCHW&quot;.
   *  The size of 1D Tensors matches the dimension C of the 4D Tensors.
   *
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
   * @param input 4-D with shape {@code [batch, in_height, in_width, in_channels]}.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   *  rows must be the same as the rank of {@code input}.
   * @param filter 4-D with shape
   *  {@code [filter_height, filter_width, in_channels, out_channels]}.
   * @param mode The value of the mode attribute
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
   * @param input 4-D with shape {@code [batch, in_height, in_width, in_channels]}.
   * @param sizeOutput A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   *  new size for the images.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   *  rows must be the same as the rank of {@code input}.
   * @param filter 4-D with shape
   *  {@code [filter_height, filter_width, in_channels, out_channels]}.
   * @param mode The value of the mode attribute
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
   * Computes the GRU cell forward propagation for 1 time step.
   *  Args
   *  x: Input to the GRU cell.
   *  h_prev: State input from the previous GRU cell.
   *  w_ru: Weight matrix for the reset and update gate.
   *  w_c: Weight matrix for the cell connection gate.
   *  b_ru: Bias vector for the reset and update gate.
   *  b_c: Bias vector for the cell connection gate.
   *  <p>Returns
   *  r: Output of the reset gate.
   *  u: Output of the update gate.
   *  c: Output of the cell connection gate.
   *  h: Current state of the GRU cell.
   *  <p>Note on notation of the variables:
   *  <p>Concatenation of a and b is represented by a_b
   *  Element-wise dot product of a and b is represented by ab
   *  Element-wise dot product is represented by \circ
   *  Matrix multiplication is represented by *
   *  <p>Biases are initialized with :
   *  {@code b_ru} - constant_initializer(1.0)
   *  {@code b_c} - constant_initializer(0.0)
   *  <p>This kernel op implements the following mathematical equations:
   *  <pre>
   *  x_h_prev = [x, h_prev]
   *
   *  [r_bar u_bar] = x_h_prev * w_ru + b_ru
   *
   *  r = sigmoid(r_bar)
   *  u = sigmoid(u_bar)
   *
   *  h_prevr = h_prev \circ r
   *
   *  x_h_prevr = [x h_prevr]
   *
   *  c_bar = x_h_prevr * w_c + b_c
   *  c = tanh(c_bar)
   *
   *  h = (1-u) \circ c + u \circ h_prev
   *  </pre>
   *
   * @param x The x value
   * @param hPrev The hPrev value
   * @param wRu The wRu value
   * @param wC The wC value
   * @param bRu The bRu value
   * @param bC The bC value
   * @param <T> data type for {@code GRUBlockCell} output and operands
   * @return a new instance of GRUBlockCell
   */
  public <T extends TNumber> GRUBlockCell<T> gRUBlockCell(Operand<T> x, Operand<T> hPrev,
      Operand<T> wRu, Operand<T> wC, Operand<T> bRu, Operand<T> bC) {
    return GRUBlockCell.create(scope, x, hPrev, wRu, wC, bRu, bC);
  }

  /**
   * Computes the GRU cell back-propagation for 1 time step.
   *  Args
   *  x: Input to the GRU cell.
   *  h_prev: State input from the previous GRU cell.
   *  w_ru: Weight matrix for the reset and update gate.
   *  w_c: Weight matrix for the cell connection gate.
   *  b_ru: Bias vector for the reset and update gate.
   *  b_c: Bias vector for the cell connection gate.
   *  r: Output of the reset gate.
   *  u: Output of the update gate.
   *  c: Output of the cell connection gate.
   *  d_h: Gradients of the h_new wrt to objective function.
   *  <p>Returns
   *  d_x: Gradients of the x wrt to objective function.
   *  d_h_prev: Gradients of the h wrt to objective function.
   *  d_c_bar Gradients of the c_bar wrt to objective function.
   *  d_r_bar_u_bar Gradients of the r_bar &amp; u_bar wrt to objective function.
   *  <p>This kernel op implements the following mathematical equations:
   *  <p>Note on notation of the variables:
   *  <p>Concatenation of a and b is represented by a_b
   *  Element-wise dot product of a and b is represented by ab
   *  Element-wise dot product is represented by \circ
   *  Matrix multiplication is represented by *
   *  <p>Additional notes for clarity:
   *  <p>{@code w_ru} can be segmented into 4 different matrices.
   *  <pre>
   *  w_ru = [w_r_x w_u_x
   *          w_r_h_prev w_u_h_prev]
   *  </pre>
   *  <p>Similarly, {@code w_c} can be segmented into 2 different matrices.
   *  <pre>
   *  w_c = [w_c_x w_c_h_prevr]
   *  </pre>
   *  <p>Same goes for biases.
   *  <pre>
   *  b_ru = [b_ru_x b_ru_h]
   *  b_c = [b_c_x b_c_h]
   *  </pre>
   *  <p>Another note on notation:
   *  <pre>
   *  d_x = d_x_component_1 + d_x_component_2
   *
   *  where d_x_component_1 = d_r_bar * w_r_x^T + d_u_bar * w_r_x^T
   *  and d_x_component_2 = d_c_bar * w_c_x^T
   *
   *  d_h_prev = d_h_prev_component_1 + d_h_prevr \circ r + d_h \circ u
   *  where d_h_prev_componenet_1 = d_r_bar * w_r_h_prev^T + d_u_bar * w_r_h_prev^T
   *  </pre>
   *  <p>Mathematics behind the Gradients below:
   *  <pre>
   *  d_c_bar = d_h \circ (1-u) \circ (1-c \circ c)
   *  d_u_bar = d_h \circ (h-c) \circ u \circ (1-u)
   *
   *  d_r_bar_u_bar = [d_r_bar d_u_bar]
   *
   *  [d_x_component_1 d_h_prev_component_1] = d_r_bar_u_bar * w_ru^T
   *
   *  [d_x_component_2 d_h_prevr] = d_c_bar * w_c^T
   *
   *  d_x = d_x_component_1 + d_x_component_2
   *
   *  d_h_prev = d_h_prev_component_1 + d_h_prevr \circ r + u
   *  </pre>
   *  <p>Below calculation is performed in the python wrapper for the Gradients
   *  (not in the gradient kernel.)
   *  <pre>
   *  d_w_ru = x_h_prevr^T * d_c_bar
   *
   *  d_w_c = x_h_prev^T * d_r_bar_u_bar
   *
   *  d_b_ru = sum of d_r_bar_u_bar along axis = 0
   *
   *  d_b_c = sum of d_c_bar along axis = 0
   *  </pre>
   *
   * @param x The x value
   * @param hPrev The hPrev value
   * @param wRu The wRu value
   * @param wC The wC value
   * @param bRu The bRu value
   * @param bC The bC value
   * @param r The r value
   * @param u The u value
   * @param c The c value
   * @param dH The dH value
   * @param <T> data type for {@code GRUBlockCellGrad} output and operands
   * @return a new instance of GRUBlockCellGrad
   */
  public <T extends TNumber> GRUBlockCellGrad<T> gRUBlockCellGrad(Operand<T> x, Operand<T> hPrev,
      Operand<T> wRu, Operand<T> wC, Operand<T> bRu, Operand<T> bC, Operand<T> r, Operand<T> u,
      Operand<T> c, Operand<T> dH) {
    return GRUBlockCellGrad.create(scope, x, hPrev, wRu, wC, bRu, bC, r, u, c, dH);
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
   * Computes the gradient for the inverse of {@code x} wrt its input.
   *  Specifically, {@code grad = -dy * y*y}, where {@code y = 1/x}, and {@code dy}
   *  is the corresponding input gradient.
   *
   * @param y The y value
   * @param dy The dy value
   * @param <T> data type for {@code InvGrad} output and operands
   * @return a new instance of InvGrad
   */
  public <T extends TType> InvGrad<T> invGrad(Operand<T> y, Operand<T> dy) {
    return InvGrad.create(scope, y, dy);
  }

  /**
   * Solves a batch of isotonic regression problems.
   *
   * @param input A (batch_size, dim)-tensor holding a batch of inputs.
   * @return a new instance of IsotonicRegression, with default output types
   */
  public IsotonicRegression<TFloat32> isotonicRegression(Operand<? extends TNumber> input) {
    return IsotonicRegression.create(scope, input);
  }

  /**
   * Solves a batch of isotonic regression problems.
   *
   * @param input A (batch_size, dim)-tensor holding a batch of inputs.
   * @param outputDtype Dtype of output.
   * @param <U> data type for {@code IsotonicRegression} output and operands
   * @return a new instance of IsotonicRegression
   */
  public <U extends TNumber> IsotonicRegression<U> isotonicRegression(
      Operand<? extends TNumber> input, Class<U> outputDtype) {
    return IsotonicRegression.create(scope, input, outputDtype);
  }

  /**
   * L2 Loss.
   *  Computes half the L2 norm of a tensor without the {@code sqrt}:
   *  <pre>
   *  output = sum(t ** 2) / 2
   *  </pre>
   *
   * @param t Typically 2-D, but may have any dimensions.
   * @param <T> data type for {@code L2Loss} output and operands
   * @return a new instance of L2Loss
   */
  public <T extends TNumber> L2Loss<T> l2Loss(Operand<T> t) {
    return L2Loss.create(scope, t);
  }

  /**
   * Computes the LSTM cell forward propagation for 1 time step.
   *  This implementation uses 1 weight matrix and 1 bias vector, and there's an
   *  optional peephole connection.
   *  <p>This kernel op implements the following mathematical equations:
   *  <pre>
   *  xh = [x, h_prev]
   *  [i, f, ci, o] = xh * w + b
   *  f = f + forget_bias
   *
   *  if not use_peephole:
   *    wci = wcf = wco = 0
   *
   *  i = sigmoid(cs_prev * wci + i)
   *  f = sigmoid(cs_prev * wcf + f)
   *  ci = tanh(ci)
   *
   *  cs = ci .* i + cs_prev .* f
   *  cs = clip(cs, cell_clip)
   *
   *  o = sigmoid(cs * wco + o)
   *  co = tanh(cs)
   *  h = co .* o
   *  </pre>
   *
   * @param x The input to the LSTM cell, shape (batch_size, num_inputs).
   * @param csPrev Value of the cell state at previous time step.
   * @param hPrev Output of the previous cell at previous time step.
   * @param w The weight matrix.
   * @param wci The weight matrix for input gate peephole connection.
   * @param wcf The weight matrix for forget gate peephole connection.
   * @param wco The weight matrix for output gate peephole connection.
   * @param b The bias vector.
   * @param options carries optional attribute values
   * @param <T> data type for {@code LSTMBlockCell} output and operands
   * @return a new instance of LSTMBlockCell
   */
  public <T extends TNumber> LSTMBlockCell<T> lSTMBlockCell(Operand<T> x, Operand<T> csPrev,
      Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf, Operand<T> wco, Operand<T> b,
      LSTMBlockCell.Options... options) {
    return LSTMBlockCell.create(scope, x, csPrev, hPrev, w, wci, wcf, wco, b, options);
  }

  /**
   * Computes the LSTM cell backward propagation for 1 timestep.
   *  This implementation is to be used in conjunction of LSTMBlockCell.
   *
   * @param x The input to the LSTM cell, shape (batch_size, num_inputs).
   * @param csPrev The previous cell state.
   * @param hPrev The previous h state.
   * @param w The weight matrix.
   * @param wci The weight matrix for input gate peephole connection.
   * @param wcf The weight matrix for forget gate peephole connection.
   * @param wco The weight matrix for output gate peephole connection.
   * @param b The bias vector.
   * @param i The input gate.
   * @param cs The cell state before the tanh.
   * @param f The forget gate.
   * @param o The output gate.
   * @param ci The cell input.
   * @param co The cell after the tanh.
   * @param csGrad The current gradient of cs.
   * @param hGrad The gradient of h vector.
   * @param usePeephole Whether the cell uses peephole connections.
   * @param <T> data type for {@code LSTMBlockCellGrad} output and operands
   * @return a new instance of LSTMBlockCellGrad
   */
  public <T extends TNumber> LSTMBlockCellGrad<T> lSTMBlockCellGrad(Operand<T> x, Operand<T> csPrev,
      Operand<T> hPrev, Operand<T> w, Operand<T> wci, Operand<T> wcf, Operand<T> wco, Operand<T> b,
      Operand<T> i, Operand<T> cs, Operand<T> f, Operand<T> o, Operand<T> ci, Operand<T> co,
      Operand<T> csGrad, Operand<T> hGrad, Boolean usePeephole) {
    return LSTMBlockCellGrad.create(scope, x, csPrev, hPrev, w, wci, wcf, wco, b, i, cs, f, o, ci, co, csGrad, hGrad, usePeephole);
  }

  /**
   * Computes rectified linear: {@code max(features, features * alpha)}.
   *
   * @param features The features value
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
   * Gradients for Local Response Normalization.
   *
   * @param inputGrads 4-D with shape {@code [batch, height, width, channels]}.
   * @param inputImage 4-D with shape {@code [batch, height, width, channels]}.
   * @param outputImage 4-D with shape {@code [batch, height, width, channels]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code LRNGrad} output and operands
   * @return a new instance of LocalResponseNormalizationGrad
   */
  public <T extends TNumber> LocalResponseNormalizationGrad<T> localResponseNormalizationGrad(
      Operand<T> inputGrads, Operand<T> inputImage, Operand<T> outputImage,
      LocalResponseNormalizationGrad.Options... options) {
    return LocalResponseNormalizationGrad.create(scope, inputGrads, inputImage, outputImage, options);
  }

  /**
   * Computes log softmax activations.
   *  For each batch {@code i} and class {@code j} we have
   *  <pre>
   *  logsoftmax[i, j] = logits[i, j] - log(sum(exp(logits[i])))
   *  </pre>
   *
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
   * Computes gradients of the maxpooling function.
   *
   * @param input The original input.
   * @param grad 4-D with shape {@code [batch, height, width, channels]}.  Gradients w.r.t. the
   *  output of {@code max_pool}.
   * @param argmax The indices of the maximum values chosen for each output of {@code max_pool}.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolGradWithArgmax} output and operands
   * @return a new instance of MaxPoolGradWithArgmax
   */
  public <T extends TNumber> MaxPoolGradWithArgmax<T> maxPoolGradWithArgmax(Operand<T> input,
      Operand<T> grad, Operand<? extends TNumber> argmax, List<Long> ksize, List<Long> strides,
      String padding, MaxPoolGradWithArgmax.Options... options) {
    return MaxPoolGradWithArgmax.create(scope, input, grad, argmax, ksize, strides, padding, options);
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
      List<Long> ksize, List<Long> strides, String padding, MaxPoolWithArgmax.Options... options) {
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
   * @param input 4-D with shape {@code [batch, height, width, channels]}.  Input to pool over.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   *  input tensor.
   * @param Targmax The value of the Targmax attribute
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
   * @param outType The value of the outType attribute
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
   * @param input The input value
   * @param bias A 1D bias Tensor with size matching the last dimension of 'input'.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minBias The float value that the lowest quantized bias value represents.
   * @param maxBias The float value that the highest quantized bias value represents.
   * @param outType The value of the outType attribute
   * @param <V> data type for {@code QuantizedBiasAdd} output and operands
   * @return a new instance of QuantizedBiasAdd
   */
  public <V extends TNumber> QuantizedBiasAdd<V> quantizedBiasAdd(Operand<? extends TNumber> input,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minBias, Operand<TFloat32> maxBias, Class<V> outType) {
    return QuantizedBiasAdd.create(scope, input, bias, minInput, maxInput, minBias, maxBias, outType);
  }

  /**
   * The QuantizedConv2DAndRelu operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DAndRelu} output and operands
   * @return a new instance of QuantizedConv2DAndRelu
   */
  public <V extends TNumber> QuantizedConv2DAndRelu<V> quantizedConv2DAndRelu(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      QuantizedConv2DAndRelu.Options... options) {
    return QuantizedConv2DAndRelu.create(scope, input, filter, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DAndReluAndRequantize operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DAndReluAndRequantize} output and operands
   * @return a new instance of QuantizedConv2DAndReluAndRequantize
   */
  public <V extends TNumber> QuantizedConv2DAndReluAndRequantize<V> quantizedConv2DAndReluAndRequantize(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<V> outType, List<Long> strides, String padding,
      QuantizedConv2DAndReluAndRequantize.Options... options) {
    return QuantizedConv2DAndReluAndRequantize.create(scope, input, filter, minInput, maxInput, minFilter, maxFilter, minFreezedOutput, maxFreezedOutput, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DAndRequantize operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DAndRequantize} output and operands
   * @return a new instance of QuantizedConv2DAndRequantize
   */
  public <V extends TNumber> QuantizedConv2DAndRequantize<V> quantizedConv2DAndRequantize(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<V> outType, List<Long> strides, String padding,
      QuantizedConv2DAndRequantize.Options... options) {
    return QuantizedConv2DAndRequantize.create(scope, input, filter, minInput, maxInput, minFilter, maxFilter, minFreezedOutput, maxFreezedOutput, outType, strides, padding, options);
  }

  /**
   * Computes QuantizedConv2D per channel.
   *
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param minInput The minimum value of the input tensor
   * @param maxInput The maximum value of the input tensor.
   * @param minFilter The minimum value of the filter tensor.
   * @param maxFilter The maximum value of the filter tensor.
   * @param outType The quantized type of output tensor that needs to be converted.
   * @param strides list of stride values.
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DPerChannel} output and operands
   * @return a new instance of QuantizedConv2DPerChannel
   */
  public <V extends TNumber> QuantizedConv2DPerChannel<V> quantizedConv2DPerChannel(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      QuantizedConv2DPerChannel.Options... options) {
    return QuantizedConv2DPerChannel.create(scope, input, filter, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DWithBias operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param bias The bias value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DWithBias} output and operands
   * @return a new instance of QuantizedConv2DWithBias
   */
  public <V extends TNumber> QuantizedConv2DWithBias<V> quantizedConv2DWithBias(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter, Operand<TFloat32> bias,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      QuantizedConv2DWithBias.Options... options) {
    return QuantizedConv2DWithBias.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DWithBiasAndRelu operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param bias The bias value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DWithBiasAndRelu} output and operands
   * @return a new instance of QuantizedConv2DWithBiasAndRelu
   */
  public <V extends TNumber> QuantizedConv2DWithBiasAndRelu<V> quantizedConv2DWithBiasAndRelu(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter, Operand<TFloat32> bias,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      QuantizedConv2DWithBiasAndRelu.Options... options) {
    return QuantizedConv2DWithBiasAndRelu.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DWithBiasAndReluAndRequantize operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param bias The bias value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedConv2DWithBiasAndReluAndRequantize} output and operands
   * @return a new instance of QuantizedConv2DWithBiasAndReluAndRequantize
   */
  public <W extends TNumber> QuantizedConv2DWithBiasAndReluAndRequantize<W> quantizedConv2DWithBiasAndReluAndRequantize(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> outType, List<Long> strides, String padding,
      QuantizedConv2DWithBiasAndReluAndRequantize.Options... options) {
    return QuantizedConv2DWithBiasAndReluAndRequantize.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, minFreezedOutput, maxFreezedOutput, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DWithBiasAndRequantize operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param bias The bias value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedConv2DWithBiasAndRequantize} output and operands
   * @return a new instance of QuantizedConv2DWithBiasAndRequantize
   */
  public <W extends TNumber> QuantizedConv2DWithBiasAndRequantize<W> quantizedConv2DWithBiasAndRequantize(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> outType, List<Long> strides, String padding,
      QuantizedConv2DWithBiasAndRequantize.Options... options) {
    return QuantizedConv2DWithBiasAndRequantize.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, minFreezedOutput, maxFreezedOutput, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DWithBiasSignedSumAndReluAndRequantize operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param bias The bias value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param summand The summand value
   * @param minSummand The minSummand value
   * @param maxSummand The maxSummand value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <X> data type for {@code QuantizedConv2DWithBiasSignedSumAndReluAndRequantize} output and operands
   * @return a new instance of QuantizedConv2DWithBiasSignedSumAndReluAndRequantize
   */
  public <X extends TNumber> QuantizedConv2DWithBiasSignedSumAndReluAndRequantize<X> quantizedConv2DWithBiasSignedSumAndReluAndRequantize(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Operand<? extends TNumber> summand,
      Operand<TFloat32> minSummand, Operand<TFloat32> maxSummand, Class<X> outType,
      List<Long> strides, String padding,
      QuantizedConv2DWithBiasSignedSumAndReluAndRequantize.Options... options) {
    return QuantizedConv2DWithBiasSignedSumAndReluAndRequantize.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, minFreezedOutput, maxFreezedOutput, summand, minSummand, maxSummand, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DWithBiasSumAndRelu operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param bias The bias value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param summand The summand value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DWithBiasSumAndRelu} output and operands
   * @return a new instance of QuantizedConv2DWithBiasSumAndRelu
   */
  public <V extends TNumber> QuantizedConv2DWithBiasSumAndRelu<V> quantizedConv2DWithBiasSumAndRelu(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter, Operand<TFloat32> bias,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Operand<TFloat32> summand, Class<V> outType, List<Long> strides,
      String padding, QuantizedConv2DWithBiasSumAndRelu.Options... options) {
    return QuantizedConv2DWithBiasSumAndRelu.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, summand, outType, strides, padding, options);
  }

  /**
   * The QuantizedConv2DWithBiasSumAndReluAndRequantize operation
   *
   * @param input The input value
   * @param filter The filter value
   * @param bias The bias value
   * @param minInput The minInput value
   * @param maxInput The maxInput value
   * @param minFilter The minFilter value
   * @param maxFilter The maxFilter value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param summand The summand value
   * @param minSummand The minSummand value
   * @param maxSummand The maxSummand value
   * @param outType The value of the outType attribute
   * @param strides The value of the strides attribute
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <X> data type for {@code QuantizedConv2DWithBiasSumAndReluAndRequantize} output and operands
   * @return a new instance of QuantizedConv2DWithBiasSumAndReluAndRequantize
   */
  public <X extends TNumber> QuantizedConv2DWithBiasSumAndReluAndRequantize<X> quantizedConv2DWithBiasSumAndReluAndRequantize(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Operand<? extends TNumber> summand,
      Operand<TFloat32> minSummand, Operand<TFloat32> maxSummand, Class<X> outType,
      List<Long> strides, String padding,
      QuantizedConv2DWithBiasSumAndReluAndRequantize.Options... options) {
    return QuantizedConv2DWithBiasSumAndReluAndRequantize.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, minFreezedOutput, maxFreezedOutput, summand, minSummand, maxSummand, outType, strides, padding, options);
  }

  /**
   * Computes a 2D convolution given quantized 4D input and filter tensors.
   *  The inputs are quantized tensors where the lowest value represents the real
   *  number of the associated minimum, and the highest represents the maximum.
   *  This means that you can only interpret the quantized output in the same way, by
   *  taking the returned minimum and maximum values into account.
   *
   * @param input The input value
   * @param filter filter's input_depth dimension must match input's depth dimensions.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minFilter The float value that the lowest quantized filter value represents.
   * @param maxFilter The float value that the highest quantized filter value represents.
   * @param outType The value of the outType attribute
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
   * Computes quantized depthwise Conv2D.
   *
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param minInput The float value that the minimum quantized input value represents.
   * @param maxInput The float value that the maximum quantized input value represents.
   * @param minFilter The float value that the minimum quantized filter value represents.
   * @param maxFilter The float value that the maximum quantized filter value represents.
   * @param outType The type of the output.
   * @param strides List of stride values.
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedDepthwiseConv2D} output and operands
   * @return a new instance of QuantizedDepthwiseConv2D
   */
  public <V extends TNumber> QuantizedDepthwiseConv2D<V> quantizedDepthwiseConv2D(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      QuantizedDepthwiseConv2D.Options... options) {
    return QuantizedDepthwiseConv2D.create(scope, input, filter, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * Computes quantized depthwise Conv2D with Bias.
   *
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param bias The original bias tensor.
   * @param minInput The float value that the minimum quantized input value represents.
   * @param maxInput The float value that the maximum quantized input value represents.
   * @param minFilter The float value that the minimum quantized filter value represents.
   * @param maxFilter The float value that the maximum quantized filter value represents.
   * @param outType The type of the output.
   * @param strides List of stride values.
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedDepthwiseConv2DWithBias} output and operands
   * @return a new instance of QuantizedDepthwiseConv2DWithBias
   */
  public <V extends TNumber> QuantizedDepthwiseConv2DWithBias<V> quantizedDepthwiseConv2DWithBias(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter, Operand<TFloat32> bias,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      QuantizedDepthwiseConv2DWithBias.Options... options) {
    return QuantizedDepthwiseConv2DWithBias.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * Computes quantized depthwise Conv2D with Bias and Relu.
   *
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param bias The original bias tensor.
   * @param minInput The float value that the minimum quantized input value represents.
   * @param maxInput The float value that the maximum quantized input value represents.
   * @param minFilter The float value that the minimum quantized filter value represents.
   * @param maxFilter The float value that the maximum quantized filter value represents.
   * @param outType The type of the output.
   * @param strides List of stride values.
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedDepthwiseConv2DWithBiasAndRelu} output and operands
   * @return a new instance of QuantizedDepthwiseConv2DWithBiasAndRelu
   */
  public <V extends TNumber> QuantizedDepthwiseConv2DWithBiasAndRelu<V> quantizedDepthwiseConv2DWithBiasAndRelu(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter, Operand<TFloat32> bias,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      QuantizedDepthwiseConv2DWithBiasAndRelu.Options... options) {
    return QuantizedDepthwiseConv2DWithBiasAndRelu.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, outType, strides, padding, options);
  }

  /**
   * Computes quantized depthwise Conv2D with Bias, Relu and Requantize.
   *
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param bias The original bias tensor.
   * @param minInput The float value that the minimum quantized input value represents.
   * @param maxInput The float value that the maximum quantized input value represents.
   * @param minFilter The float value that the minimum quantized filter value represents.
   * @param maxFilter The float value that the maximum quantized filter value represents.
   * @param minFreezedOutput The minimum float value of the output tensor.
   * @param maxFreezedOutput The maximum float value of the output tensor.
   * @param outType The type of the output.
   * @param strides List of stride values.
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize} output and operands
   * @return a new instance of QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize
   */
  public <W extends TNumber> QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<W> quantizedDepthwiseConv2DWithBiasAndReluAndRequantize(
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> outType, List<Long> strides, String padding,
      QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize.Options... options) {
    return QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize.create(scope, input, filter, bias, minInput, maxInput, minFilter, maxFilter, minFreezedOutput, maxFreezedOutput, outType, strides, padding, options);
  }

  /**
   * Quantized Instance normalization.
   *
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
   * @param features The features value
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType The value of the outType attribute
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
   * @param features The features value
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType The value of the outType attribute
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
   * @param features The features value
   * @param maxValue The maxValue value
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType The value of the outType attribute
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
   *  <p>tf.nn.relu([-2., 0., 3.]).numpy()
   *  array([0., 0., 3.], dtype=float32)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param features The features value
   * @param <T> data type for {@code Relu} output and operands
   * @return a new instance of Relu
   */
  public <T extends TNumber> Relu<T> relu(Operand<T> features) {
    return Relu.create(scope, features);
  }

  /**
   * Computes rectified linear 6: {@code min(max(features, 0), 6)}.
   *
   * @param features The features value
   * @param <T> data type for {@code Relu6} output and operands
   * @return a new instance of Relu6
   */
  public <T extends TNumber> Relu6<T> relu6(Operand<T> features) {
    return Relu6.create(scope, features);
  }

  /**
   * Computes rectified linear 6 gradients for a Relu6 operation.
   *
   * @param gradients The backpropagated gradients to the corresponding Relu6 operation.
   * @param features The features passed as input to the corresponding Relu6 operation, or
   *  its output; using either one produces the same result.
   * @param <T> data type for {@code Relu6Grad} output and operands
   * @return a new instance of Relu6Grad
   */
  public <T extends TNumber> Relu6Grad<T> relu6Grad(Operand<T> gradients, Operand<T> features) {
    return Relu6Grad.create(scope, gradients, features);
  }

  /**
   * Computes rectified linear gradients for a Relu operation.
   *
   * @param gradients The backpropagated gradients to the corresponding Relu operation.
   * @param features The features passed as input to the corresponding Relu operation, OR
   *  the outputs of that operation (both work equivalently).
   * @param <T> data type for {@code ReluGrad} output and operands
   * @return a new instance of ReluGrad
   */
  public <T extends TNumber> ReluGrad<T> reluGrad(Operand<T> gradients, Operand<T> features) {
    return ReluGrad.create(scope, gradients, features);
  }

  /**
   * Computes scaled exponential linear: {@code scale * alpha * (exp(features) - 1)}
   *  if &lt; 0, {@code scale * features} otherwise.
   *  <p>To be used together with
   *  {@code initializer = tf.variance_scaling_initializer(factor=1.0, mode='FAN_IN')}.
   *  For correct dropout, use {@code tf.contrib.nn.alpha_dropout}.
   *  <p>See  <a href="https://arxiv.org/abs/1706.02515">Self-Normalizing Neural Networks</a>
   *
   * @param features The features value
   * @param <T> data type for {@code Selu} output and operands
   * @return a new instance of Selu
   */
  public <T extends TNumber> Selu<T> selu(Operand<T> features) {
    return Selu.create(scope, features);
  }

  /**
   * Computes gradients for the scaled exponential linear (Selu) operation.
   *
   * @param gradients The backpropagated gradients to the corresponding Selu operation.
   * @param outputs The outputs of the corresponding Selu operation.
   * @param <T> data type for {@code SeluGrad} output and operands
   * @return a new instance of SeluGrad
   */
  public <T extends TNumber> SeluGrad<T> seluGrad(Operand<T> gradients, Operand<T> outputs) {
    return SeluGrad.create(scope, gradients, outputs);
  }

  /**
   * Computes softmax activations.
   *  For each batch {@code i} and class {@code j} we have
   *  <pre>
   *  $$softmax[i, j] = exp(logits[i, j]) / sum_j(exp(logits[i, j]))$$
   *  </pre>
   *
   * @param logits 2-D with shape {@code [batch_size, num_classes]}.
   * @param <T> data type for {@code Softmax} output and operands
   * @return a new instance of Softmax
   */
  public <T extends TNumber> Softmax<T> softmax(Operand<T> logits) {
    return Softmax.create(scope, logits);
  }

  /**
   * Computes softmax cross entropy cost and gradients to backpropagate.
   *  Inputs are the logits, not probabilities.
   *
   * @param features batch_size x num_classes matrix
   * @param labels batch_size x num_classes matrix
   *  The caller must ensure that each batch of labels represents a valid
   *  probability distribution.
   * @param <T> data type for {@code SoftmaxCrossEntropyWithLogits} output and operands
   * @return a new instance of SoftmaxCrossEntropyWithLogits
   */
  public <T extends TNumber> SoftmaxCrossEntropyWithLogits<T> softmaxCrossEntropyWithLogits(
      Operand<T> features, Operand<T> labels) {
    return SoftmaxCrossEntropyWithLogits.create(scope, features, labels);
  }

  /**
   * Computes softsign: {@code features / (abs(features) + 1)}.
   *
   * @param features The features value
   * @param <T> data type for {@code Softsign} output and operands
   * @return a new instance of Softsign
   */
  public <T extends TNumber> Softsign<T> softsign(Operand<T> features) {
    return Softsign.create(scope, features);
  }

  /**
   * Computes softsign gradients for a softsign operation.
   *
   * @param gradients The backpropagated gradients to the corresponding softsign operation.
   * @param features The features passed as input to the corresponding softsign operation.
   * @param <T> data type for {@code SoftsignGrad} output and operands
   * @return a new instance of SoftsignGrad
   */
  public <T extends TNumber> SoftsignGrad<T> softsignGrad(Operand<T> gradients,
      Operand<T> features) {
    return SoftsignGrad.create(scope, gradients, features);
  }

  /**
   * SpaceToBatch for 4-D tensors of type T.
   *  This is a legacy version of the more general SpaceToBatchND.
   *  <p>Zero-pads and then rearranges (permutes) blocks of spatial data into batch.
   *  More specifically, this op outputs a copy of the input tensor where values from
   *  the {@code height} and {@code width} dimensions are moved to the {@code batch} dimension. After
   *  the zero-padding, both {@code height} and {@code width} of the input must be divisible by the
   *  block size.
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
   *
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
   * @param blockSize The value of the blockSize attribute
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
   * @param input The input value
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
   * Computes softmax cross entropy cost and gradients to backpropagate.
   *  Unlike {@code SoftmaxCrossEntropyWithLogits}, this operation does not accept
   *  a matrix of label probabilities, but rather a single label per row
   *  of features.  This label is considered to have probability 1.0 for the
   *  given row.
   *  <p>Inputs are the logits, not probabilities.
   *
   * @param features batch_size x num_classes matrix
   * @param labels batch_size vector with values in [0, num_classes).
   *  This is the label for the given minibatch entry.
   * @param <T> data type for {@code SparseSoftmaxCrossEntropyWithLogits} output and operands
   * @return a new instance of SparseSoftmaxCrossEntropyWithLogits
   */
  public <T extends TNumber> SparseSoftmaxCrossEntropyWithLogits<T> sparseSoftmaxCrossEntropyWithLogits(
      Operand<T> features, Operand<? extends TNumber> labels) {
    return SparseSoftmaxCrossEntropyWithLogits.create(scope, features, labels);
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
   * @param input 1-D or higher with last dimension at least {@code k}.
   * @param k 0-D.  Number of top elements to look for along the last dimension (along each
   *  row for matrices).
   * @param options carries optional attribute values
   * @param <T> data type for {@code TopKV2} output and operands
   * @return a new instance of TopK, with default output types
   */
  public <T extends TNumber> TopK<T, TInt32> topK(Operand<T> input, Operand<? extends TNumber> k,
      TopK.Options... options) {
    return TopK.create(scope, input, k, options);
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
   * @param input 1-D or higher with last dimension at least {@code k}.
   * @param k 0-D.  Number of top elements to look for along the last dimension (along each
   *  row for matrices).
   * @param indexType The value of the indexType attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code TopKV2} output and operands
   * @param <V> data type for {@code TopKV2} output and operands
   * @return a new instance of TopK
   */
  public <T extends TNumber, V extends TNumber> TopK<T, V> topK(Operand<T> input,
      Operand<? extends TNumber> k, Class<V> indexType, TopK.Options... options) {
    return TopK.create(scope, input, k, indexType, options);
  }

  /**
   * Perform quantized convolution of quantized Tensor {@code lhs} and quantized Tensor {@code rhs}. to make quantized {@code output}.
   *  Given quantized {@code lhs} and quantized {@code rhs}, performs quantized dot on {@code lhs} and {@code rhs} to make quantized {@code output}.
   *  <p>{@code lhs} and {@code rhs} must be Tensors of same rank, and meet following shape conditions.
   *  <ul>
   *  <li>{@code lhs_feature} % {@code feature_group_count} == 0</li>
   *  <li>{@code lhs_feature} % {@code rhs_input_feature} == 0</li>
   *  <li>{@code lhs_feature} / {@code feature_group_count} == {@code rhs_input_feature}</li>
   *  <li>{@code rhs_output_feature} % {@code feature_group_count} == 0</li>
   *  <li>{@code lhs_batch} % {@code batch_group_count} == 0</li>
   *  <li>{@code rhs_output_feature} % {@code batch_group_count} == 0</li>
   *  </ul>
   *  <p>{@code lhs} and {@code rhs} must be quantized Tensor, where data value is quantized using the formula:
   *  <pre>
   *  quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val)
   *  </pre>
   *  <p>{@code output} is also quantized, using the same formula.
   *  If {@code rhs} is per-tensor quantized, {@code output} must be also per-tensor quantized.
   *
   * @param lhs Must be a quantized tensor, rank &gt;= 3.
   * @param rhs Must be a quantized tensor, same rank as {@code lhs}.
   * @param lhsScales The float value(s) used as scale factors when quantizing the original data that {@code lhs} represents.
   *  Must be a scalar {@code Tensor} ({@code lhs} supports only per-tensor quantization).
   * @param lhsZeroPoints The int32 value(s) used as zero points when quantizing original data that {@code lhs} represents.
   *  Same shape condition as {@code lhs_scales}.
   * @param rhsScales The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
   *  Must be a scalar {@code Tensor} for per-tensor quantization,
   *  or 1D {@code Tensor} of size {@code rhs.dim_size(kernel_output_feature_dimension)}, for per-channel quantization.
   * @param rhsZeroPoints The int32 value(s) used as zero points when quantizing original data that {@code rhs} represents.
   *  Same shape condition as {@code rhs_scales}.
   * @param outputScales The float value(s) to use as scale factors when quantizing original data that {@code output} represents.
   *  Must be a scalar {@code Tensor} for per-tensor quantization,
   *  or 1D {@code Tensor} of size {@code rhs.dim_size(kernel_output_feature_dimension)}
   *  <ul>
   *  <li>which is equal to {@code output.dim_size(output_feature_dimension)},
   *  for per-channel quantization.
   *  If {@code rhs} is per-tensor quantized, output must be also per-tensor quantized.
   *  This means that if {@code rhs_scales} and {@code rhs_zero_points} are scalar {@code Tensor}s, {@code output_scales} and {@code output_zero_points} must be scalar {@code Tensor}s as well.</li>
   *  </ul>
   * @param outputZeroPoints The int32 value(s) used as zero points when quantizing original data that output represents.
   *  Same shape condition as {@code output_scales}.
   * @param Tout The type of {@code output} {@code Tensor}.
   * @param padding string from: {@code "SAME"}, {@code "VALID"}, or {@code "EXPLICIT"}, indicating the type of padding algorithm to use.
   * @param lhsQuantizationMinVal The min value of the quantized data stored in {@code lhs}.
   *  For example, if {@code Tin} is {@code qint8}, this must be set to -127 if narrow range quantized or -128 if not.
   * @param lhsQuantizationMaxVal The max value of the quantized data stored in {@code lhs}.
   *  For example, if {@code Tin} is {@code qint8}, this must be set to 127.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in {@code rhs}.
   *  For example, if {@code Tin} is {@code qint8}, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in {@code rhs}.
   *  For example, if {@code Tin} is {@code qint8}, this must be set to 127.
   * @param outputQuantizationMinVal The min value of the quantized data stored in {@code output}.
   *  For example, if  {@code Tout} is {@code qint8}, this must be set to -127 if narrow range quantized or -128 if not.
   * @param outputQuantizationMaxVal The max value of the quantized data stored in {@code output}.
   *  For example, if {@code Tout} is {@code qint8}, this must be set to 127.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformQuantizedConvolution} output and operands
   * @param <T> data type for {@code UniformQuantizedConvolution} output and operands
   * @return a new instance of UniformQuantizedConvolution
   */
  public <U extends TNumber, T extends TNumber> UniformQuantizedConvolution<U> uniformQuantizedConvolution(
      Operand<T> lhs, Operand<T> rhs, Operand<TFloat32> lhsScales, Operand<TInt32> lhsZeroPoints,
      Operand<TFloat32> rhsScales, Operand<TInt32> rhsZeroPoints, Operand<TFloat32> outputScales,
      Operand<TInt32> outputZeroPoints, Class<U> Tout, String padding, Long lhsQuantizationMinVal,
      Long lhsQuantizationMaxVal, Long rhsQuantizationMinVal, Long rhsQuantizationMaxVal,
      Long outputQuantizationMinVal, Long outputQuantizationMaxVal,
      UniformQuantizedConvolution.Options... options) {
    return UniformQuantizedConvolution.create(scope, lhs, rhs, lhsScales, lhsZeroPoints, rhsScales, rhsZeroPoints, outputScales, outputZeroPoints, Tout, padding, lhsQuantizationMinVal, lhsQuantizationMaxVal, rhsQuantizationMinVal, rhsQuantizationMaxVal, outputQuantizationMinVal, outputQuantizationMaxVal, options);
  }

  /**
   * Perform hybrid quantized convolution of float Tensor {@code lhs} and quantized Tensor {@code rhs}.
   *  Given float {@code lhs} and quantized {@code rhs}, internally performs quantization on {@code lhs},
   *  and then performs quantized convolution on quantized {@code lhs} and {@code rhs}.
   *  <p>The internal quantization on {@code lhs} is a quantization to {@code Trhs}, dynamic range,
   *  per-batch (per-axis along axis {@code dimension_numbers.input_batch_dimension}), asymmetric,
   *  and not narrow range (the range is [Trhs_MIN, Trhs_MAX]).
   *  <p>{@code lhs} and {@code rhs} must be Tensors of same rank, and meet following shape conditions.
   *  <ul>
   *  <li>lhs_feature % feature_group_count == 0</li>
   *  <li>lhs_feature % rhs_input_feature == 0</li>
   *  <li>lhs_feature / feature_group_count == rhs_input_feature</li>
   *  <li>rhs_output_feature % feature_group_count == 0</li>
   *  <li>lhs_batch % batch_group_count == 0</li>
   *  <li>rhs_output_feature % batch_group_count == 0</li>
   *  </ul>
   *  <p>{@code rhs} must be quantized Tensor, where its data value is quantized using the formula:
   *  quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val).
   *
   * @param lhs Must be a non-quantized Tensor of {@code Tlhs}, rank &gt;= 3.
   * @param rhs Must be a quantized Tensor of {@code Trhs}, same rank as {@code lhs}.
   * @param rhsScales The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
   *  Must be a scalar Tensor for per-tensor quantization,
   *  or 1D Tensor of size {@code rhs.dim_size(kernel_output_feature_dimension)}, for per-channel quantization.
   * @param rhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that {@code rhs} represents.
   *  Same shape condition as {@code rhs_scales}.
   * @param Tout The type of output Tensor.
   * @param padding string from: {@code "SAME"}, {@code "VALID"}, or {@code "EXPLICIT"}, indicating the type of padding algorithm to use.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in {@code rhs}.
   *  For example, if {@code Trhs} is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in {@code rhs}.
   *  For example, if {@code Trhs} is qint8, this must be set to 127.
   * @param options carries optional attribute values
   * @param <V> data type for {@code UniformQuantizedConvolutionHybrid} output and operands
   * @return a new instance of UniformQuantizedConvolutionHybrid
   */
  public <V extends TNumber> UniformQuantizedConvolutionHybrid<V> uniformQuantizedConvolutionHybrid(
      Operand<? extends TNumber> lhs, Operand<? extends TNumber> rhs, Operand<TFloat32> rhsScales,
      Operand<TInt32> rhsZeroPoints, Class<V> Tout, String padding, Long rhsQuantizationMinVal,
      Long rhsQuantizationMaxVal, UniformQuantizedConvolutionHybrid.Options... options) {
    return UniformQuantizedConvolutionHybrid.create(scope, lhs, rhs, rhsScales, rhsZeroPoints, Tout, padding, rhsQuantizationMinVal, rhsQuantizationMaxVal, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
