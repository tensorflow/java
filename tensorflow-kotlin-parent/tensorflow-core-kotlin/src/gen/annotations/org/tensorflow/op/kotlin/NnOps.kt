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
package org.tensorflow.op.kotlin

import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.nn.AvgPool
import org.tensorflow.op.nn.AvgPool3d
import org.tensorflow.op.nn.AvgPool3dGrad
import org.tensorflow.op.nn.BatchNormWithGlobalNormalization
import org.tensorflow.op.nn.BatchNormWithGlobalNormalizationGrad
import org.tensorflow.op.nn.BiasAdd
import org.tensorflow.op.nn.BiasAddGrad
import org.tensorflow.op.nn.ComputeAccidentalHits
import org.tensorflow.op.nn.Conv2d
import org.tensorflow.op.nn.Conv2dBackpropFilter
import org.tensorflow.op.nn.Conv2dBackpropInput
import org.tensorflow.op.nn.Conv3d
import org.tensorflow.op.nn.Conv3dBackpropFilter
import org.tensorflow.op.nn.Conv3dBackpropInput
import org.tensorflow.op.nn.CtcBeamSearchDecoder
import org.tensorflow.op.nn.CtcGreedyDecoder
import org.tensorflow.op.nn.CtcLoss
import org.tensorflow.op.nn.CudnnRNNCanonicalToParams
import org.tensorflow.op.nn.CudnnRNNParamsToCanonical
import org.tensorflow.op.nn.CudnnRnnParamsSize
import org.tensorflow.op.nn.DataFormatDimMap
import org.tensorflow.op.nn.DataFormatVecPermute
import org.tensorflow.op.nn.DepthToSpace
import org.tensorflow.op.nn.DepthwiseConv2dNative
import org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter
import org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput
import org.tensorflow.op.nn.Dilation2d
import org.tensorflow.op.nn.Dilation2dBackpropFilter
import org.tensorflow.op.nn.Dilation2dBackpropInput
import org.tensorflow.op.nn.Elu
import org.tensorflow.op.nn.FixedUnigramCandidateSampler
import org.tensorflow.op.nn.FractionalAvgPool
import org.tensorflow.op.nn.FractionalMaxPool
import org.tensorflow.op.nn.FusedBatchNorm
import org.tensorflow.op.nn.FusedBatchNormGrad
import org.tensorflow.op.nn.FusedPadConv2d
import org.tensorflow.op.nn.FusedResizeAndPadConv2d
import org.tensorflow.op.nn.InTopK
import org.tensorflow.op.nn.L2Loss
import org.tensorflow.op.nn.LeakyRelu
import org.tensorflow.op.nn.LearnedUnigramCandidateSampler
import org.tensorflow.op.nn.LocalResponseNormalization
import org.tensorflow.op.nn.LogSoftmax
import org.tensorflow.op.nn.MaxPool
import org.tensorflow.op.nn.MaxPool3d
import org.tensorflow.op.nn.MaxPool3dGrad
import org.tensorflow.op.nn.MaxPool3dGradGrad
import org.tensorflow.op.nn.MaxPoolGrad
import org.tensorflow.op.nn.MaxPoolGradGrad
import org.tensorflow.op.nn.MaxPoolGradGradWithArgmax
import org.tensorflow.op.nn.MaxPoolWithArgmax
import org.tensorflow.op.nn.NthElement
import org.tensorflow.op.nn.QuantizedAvgPool
import org.tensorflow.op.nn.QuantizedBatchNormWithGlobalNormalization
import org.tensorflow.op.nn.QuantizedBiasAdd
import org.tensorflow.op.nn.QuantizedConv2d
import org.tensorflow.op.nn.QuantizedInstanceNorm
import org.tensorflow.op.nn.QuantizedMaxPool
import org.tensorflow.op.nn.QuantizedRelu
import org.tensorflow.op.nn.QuantizedRelu6
import org.tensorflow.op.nn.QuantizedReluX
import org.tensorflow.op.nn.Relu
import org.tensorflow.op.nn.Relu6
import org.tensorflow.op.nn.Selu
import org.tensorflow.op.nn.Softmax
import org.tensorflow.op.nn.Softsign
import org.tensorflow.op.nn.SpaceToBatch
import org.tensorflow.op.nn.SpaceToDepth
import org.tensorflow.op.nn.TopK
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `nn` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class NnOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.NnOps = ops.java.nn

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    public val raw: NnRawOps = NnRawOps(ops)

    /**
     * Performs average pooling on the input.
     *  Each entry in ``` output``` is the mean of the corresponding size ``` ksize```
     *  window in ``` value```.
     *
     * @param T data type for ` output` output
     * @param value 4-D with shape ` [batch, height, width, channels]`.
     * @param ksize The size of the sliding window for each dimension of ` value`.
     * @param strides The stride of the sliding window for each dimension of ` value`.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` AvgPool` output and operands
     * @return a new instance of AvgPool
     * @see org.tensorflow.op.NnOps.avgPool
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> avgPool(
        value: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null
    ): AvgPool<T> = java.avgPool<T>(
        value,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.AvgPool.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Performs 3D average pooling on the input.
     *  Each entry in ``` output``` is the mean of the corresponding size ``` ksize``` window in
     *  ``` value```.
     *
     * @param T data type for ` output` output
     * @param input Shape ` [batch, depth, rows, cols, channels]` tensor to pool over.
     * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
     *  the input tensor. Must have ``` ksize[0] = ksize[4] = 1```.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` AvgPool3D` output and operands
     * @return a new instance of AvgPool3d
     * @see org.tensorflow.op.NnOps.avgPool3d
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> avgPool3d(
        input: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null
    ): AvgPool3d<T> = java.avgPool3d<T>(
        input,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.AvgPool3d.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes gradients of average pooling function.
     *
     * @param T data type for ` output` output
     * @param origInputShape The original input dimensions.
     * @param grad Output backprop of shape ` [batch, depth, rows, cols, channels]`.
     * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
     *  the input tensor. Must have ``` ksize[0] = ksize[4] = 1```.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` AvgPool3DGrad` output and operands
     * @return a new instance of AvgPool3dGrad
     * @see org.tensorflow.op.NnOps.avgPool3dGrad
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> avgPool3dGrad(
        origInputShape: Operand<TInt32>,
        grad: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null
    ): AvgPool3dGrad<T> = java.avgPool3dGrad<T>(
        origInputShape,
        grad,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.AvgPool3dGrad.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Batch normalization.
     *  This op is deprecated. Prefer ``` tf.nn.batch_normalization```.
     *
     * @param T data type for ` result` output
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
     * @param T data type for ` BatchNormWithGlobalNormalization` output and operands
     * @return a new instance of BatchNormWithGlobalNormalization
     * @see org.tensorflow.op.NnOps.batchNormWithGlobalNormalization
     */
    public fun <T : TType> batchNormWithGlobalNormalization(
        t: Operand<T>,
        m: Operand<T>,
        v: Operand<T>,
        beta: Operand<T>,
        gamma: Operand<T>,
        varianceEpsilon: Float,
        scaleAfterNormalization: Boolean
    ): BatchNormWithGlobalNormalization<T> = java.batchNormWithGlobalNormalization<T>(
        t,
        m,
        v,
        beta,
        gamma,
        varianceEpsilon,
        scaleAfterNormalization
    )

    /**
     * Gradients for batch normalization.
     *  This op is deprecated. See ``` tf.nn.batch_normalization```.
     *
     * @param T data type for ` dx` output
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
     * @param T data type for ` BatchNormWithGlobalNormalizationGrad` output and operands
     * @return a new instance of BatchNormWithGlobalNormalizationGrad
     * @see org.tensorflow.op.NnOps.batchNormWithGlobalNormalizationGrad
     */
    public fun <T : TType> batchNormWithGlobalNormalizationGrad(
        t: Operand<T>,
        m: Operand<T>,
        v: Operand<T>,
        gamma: Operand<T>,
        backprop: Operand<T>,
        varianceEpsilon: Float,
        scaleAfterNormalization: Boolean
    ): BatchNormWithGlobalNormalizationGrad<T> = java.batchNormWithGlobalNormalizationGrad<T>(
        t,
        m,
        v,
        gamma,
        backprop,
        varianceEpsilon,
        scaleAfterNormalization
    )

    /**
     * Adds ``` bias``` to ``` value```.
     *  This is a special case of ``` tf.add``` where ``` bias``` is restricted to be 1-D.
     *  Broadcasting is supported, so ``` value``` may have any number of dimensions.
     *
     * @param T data type for ` output` output
     * @param value Any number of dimensions.
     * @param bias 1-D with size the last dimension of ` value`.
     * @param options carries optional attribute values
     * @param T data type for ` BiasAdd` output and operands
     * @return a new instance of BiasAdd
     * @see org.tensorflow.op.NnOps.biasAdd
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the bias tensor will be added to the last dimension
     *  of the value tensor.
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     *  The tensor will be added to &quot;in_channels&quot;, the third-to-the-last
     *  dimension.
     * @return this Options instance.
     */
    public fun <T : TType> biasAdd(
        value: Operand<T>,
        bias: Operand<T>,
        dataFormat: String? = null
    ): BiasAdd<T> = java.biasAdd<T>(
        value,
        bias,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.BiasAdd.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * The backward operation for &quot;BiasAdd&quot; on the &quot;bias&quot; tensor.
     *  It accumulates all the values from out_backprop into the feature dimension.
     *  For NHWC data format, the feature dimension is the last. For NCHW data format,
     *  the feature dimension is the third-to-last.
     *
     * @param T data type for ` output` output
     * @param outBackprop Any number of dimensions.
     * @param options carries optional attribute values
     * @param T data type for ` BiasAddGrad` output and operands
     * @return a new instance of BiasAddGrad
     * @see org.tensorflow.op.NnOps.biasAddGrad
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the bias tensor will be added to the last dimension
     *  of the value tensor.
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     *  The tensor will be added to &quot;in_channels&quot;, the third-to-the-last
     *  dimension.
     * @return this Options instance.
     */
    public fun <T : TType> biasAddGrad(outBackprop: Operand<T>, dataFormat: String? = null):
        BiasAddGrad<T> = java.biasAddGrad<T>(
        outBackprop,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.BiasAddGrad.dataFormat(it) }
        ).toTypedArray()
    )

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
     * @see org.tensorflow.op.NnOps.computeAccidentalHits
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun computeAccidentalHits(
        trueClasses: Operand<TInt64>,
        sampledCandidates: Operand<TInt64>,
        numTrue: Long,
        seed: Long? = null,
        seed2: Long? = null
    ): ComputeAccidentalHits = java.computeAccidentalHits(
        trueClasses,
        sampledCandidates,
        numTrue,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.nn.ComputeAccidentalHits.seed(it) },
            seed2?.let { org.tensorflow.op.nn.ComputeAccidentalHits.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Computes a 2-D convolution given 4-D ``` input``` and ``` filter``` tensors.
     *  Given an input tensor of shape ``` [batch, in_height, in_width, in_channels]```
     *  and a filter / kernel tensor of shape
     *  ``` [filter_height, filter_width, in_channels, out_channels]```, this op
     *  performs the following:
     *  <ol>
     *  <li>Flattens the filter to a 2-D matrix with shape
     *  ``` [filter_height * filter_width * in_channels, output_channels]```.</li>
     *  <li>Extracts image patches from the input tensor to form a <em>virtual</em>
     *  tensor of shape ``` [batch, out_height, out_width, filter_height * filter_width *
     * in_channels]}.</li>
     *  <li>For each patch, right-multiplies the filter matrix and the image patch
     *  vector.</li>
     *  </ol>
     *  In detail, with the default NHWC format,
     *
     *  output[b, i, j, k] =
     *      sum_{di, dj, q```
     *  input&#91;b, strides&#91;1] * i + di, strides&#91;2] * j + dj, q] *
     *                      filter&#91;di, dj, q, k]
     *
     *  Must have ``` strides[0] = strides[3] = 1```.  For the most common case of the same
     *  horizontal and vertices strides, ``` strides = [1, stride, stride, 1]```.
     *
     * @param T data type for ` output` output
     * @param input A 4-D tensor. The dimension order is interpreted according to the value
     *  of ``` data_format```, see below for details.
     * @param filter A 4-D tensor of shape
     *  ``` [filter_height, filter_width, in_channels, out_channels]```
     * @param strides 1-D tensor of length 4.  The stride of the sliding window for each
     *  dimension of ``` input```. The dimension order is determined by the value of
     *  ``` data_format```, see below for details.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` Conv2D` output and operands
     * @return a new instance of Conv2d
     * @see org.tensorflow.op.NnOps.conv2d
     * @param useCudnnOnGpu Sets the useCudnnOnGpu option.
     *
     * @param useCudnnOnGpu the useCudnnOnGpu option
     * @return this Options instance.
     * @param explicitPaddings Sets the explicitPaddings option.
     *
     * @param explicitPaddings If ` padding` is ` "EXPLICIT"`, the list of explicit padding amounts.
     * For the ith
     *  dimension, the amount of padding inserted before and after the dimension is
     *  ``` explicit_paddings[2 * i]``` and ``` explicit_paddings[2 * i + 1]```, respectively. If
     *  ``` padding``` is not ``` "EXPLICIT"```, ``` explicit_paddings``` must be empty.
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, height, width, channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, channels, height, width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each
     *  filter element on that dimension. The dimension order is determined by the
     *  value of ``` data_format```, see above for details. Dilations in the batch and
     *  depth dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> conv2d(
        input: Operand<T>,
        filter: Operand<T>,
        strides: List<Long>,
        padding: String,
        useCudnnOnGpu: Boolean? = null,
        explicitPaddings: List<Long>? = null,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): Conv2d<T> = java.conv2d<T>(
        input,
        filter,
        strides,
        padding,
        *listOfNotNull(
            useCudnnOnGpu?.let { org.tensorflow.op.nn.Conv2d.useCudnnOnGpu(it) },
            explicitPaddings?.let { org.tensorflow.op.nn.Conv2d.explicitPaddings(it) },
            dataFormat?.let { org.tensorflow.op.nn.Conv2d.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.Conv2d.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes the gradients of convolution with respect to the filter.
     *
     * @param T data type for ` output` output
     * @param input 4-D with shape ` [batch, in_height, in_width, in_channels]`.
     * @param filterSizes An integer vector representing the tensor shape of ` filter`,
     *  where ``` filter``` is a 4-D
     *  ``` [filter_height, filter_width, in_channels, out_channels]``` tensor.
     * @param outBackprop 4-D with shape ` [batch, out_height, out_width, out_channels]`.
     *  Gradients w.r.t. the output of the convolution.
     * @param strides The stride of the sliding window for each dimension of the input
     *  of the convolution. Must be in the same order as the dimension specified with
     *  format.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` Conv2DBackpropFilter` output and operands
     * @return a new instance of Conv2dBackpropFilter
     * @see org.tensorflow.op.NnOps.conv2dBackpropFilter
     * @param useCudnnOnGpu Sets the useCudnnOnGpu option.
     *
     * @param useCudnnOnGpu the useCudnnOnGpu option
     * @return this Options instance.
     * @param explicitPaddings Sets the explicitPaddings option.
     *
     * @param explicitPaddings If ` padding` is ` "EXPLICIT"`, the list of explicit padding amounts.
     * For the ith
     *  dimension, the amount of padding inserted before and after the dimension is
     *  ``` explicit_paddings[2 * i]``` and ``` explicit_paddings[2 * i + 1]```, respectively. If
     *  ``` padding``` is not ``` "EXPLICIT"```, ``` explicit_paddings``` must be empty.
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each filter
     *  element on that dimension. The dimension order is determined by the value of
     *  ``` data_format```, see above for details. Dilations in the batch and depth
     *  dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> conv2dBackpropFilter(
        input: Operand<T>,
        filterSizes: Operand<TInt32>,
        outBackprop: Operand<T>,
        strides: List<Long>,
        padding: String,
        useCudnnOnGpu: Boolean? = null,
        explicitPaddings: List<Long>? = null,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): Conv2dBackpropFilter<T> = java.conv2dBackpropFilter<T>(
        input,
        filterSizes,
        outBackprop,
        strides,
        padding,
        *listOfNotNull(
            useCudnnOnGpu?.let { org.tensorflow.op.nn.Conv2dBackpropFilter.useCudnnOnGpu(it) },
            explicitPaddings?.let { org.tensorflow.op.nn.Conv2dBackpropFilter.explicitPaddings(it) },
            dataFormat?.let { org.tensorflow.op.nn.Conv2dBackpropFilter.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.Conv2dBackpropFilter.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes the gradients of convolution with respect to the input.
     *
     * @param T data type for ` output` output
     * @param inputSizes An integer vector representing the shape of ` input`,
     *  where ``` input``` is a 4-D ``` [batch, height, width, channels]``` tensor.
     * @param filter 4-D with shape
     *  ``` [filter_height, filter_width, in_channels, out_channels]```.
     * @param outBackprop 4-D with shape ` [batch, out_height, out_width, out_channels]`.
     *  Gradients w.r.t. the output of the convolution.
     * @param strides The stride of the sliding window for each dimension of the input
     *  of the convolution. Must be in the same order as the dimension specified with
     *  format.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` Conv2DBackpropInput` output and operands
     * @return a new instance of Conv2dBackpropInput
     * @see org.tensorflow.op.NnOps.conv2dBackpropInput
     * @param useCudnnOnGpu Sets the useCudnnOnGpu option.
     *
     * @param useCudnnOnGpu the useCudnnOnGpu option
     * @return this Options instance.
     * @param explicitPaddings Sets the explicitPaddings option.
     *
     * @param explicitPaddings If ` padding` is ` "EXPLICIT"`, the list of explicit padding amounts.
     * For the ith
     *  dimension, the amount of padding inserted before and after the dimension is
     *  ``` explicit_paddings[2 * i]``` and ``` explicit_paddings[2 * i + 1]```, respectively. If
     *  ``` padding``` is not ``` "EXPLICIT"```, ``` explicit_paddings``` must be empty.
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each filter
     *  element on that dimension. The dimension order is determined by the value of
     *  ``` data_format```, see above for details. Dilations in the batch and depth
     *  dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> conv2dBackpropInput(
        inputSizes: Operand<TInt32>,
        filter: Operand<T>,
        outBackprop: Operand<T>,
        strides: List<Long>,
        padding: String,
        useCudnnOnGpu: Boolean? = null,
        explicitPaddings: List<Long>? = null,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): Conv2dBackpropInput<T> = java.conv2dBackpropInput<T>(
        inputSizes,
        filter,
        outBackprop,
        strides,
        padding,
        *listOfNotNull(
            useCudnnOnGpu?.let { org.tensorflow.op.nn.Conv2dBackpropInput.useCudnnOnGpu(it) },
            explicitPaddings?.let { org.tensorflow.op.nn.Conv2dBackpropInput.explicitPaddings(it) },
            dataFormat?.let { org.tensorflow.op.nn.Conv2dBackpropInput.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.Conv2dBackpropInput.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes a 3-D convolution given 5-D ``` input``` and ``` filter``` tensors.
     *  In signal processing, cross-correlation is a measure of similarity of
     *  two waveforms as a function of a time-lag applied to one of them. This
     *  is also known as a sliding dot product or sliding inner-product.
     *  Our Conv3D implements a form of cross-correlation.
     *
     * @param T data type for ` output` output
     * @param input Shape ` [batch, in_depth, in_height, in_width, in_channels]`.
     * @param filter Shape ` [filter_depth, filter_height, filter_width, in_channels,
     * out_channels]`. ` in_channels` must match between ` input` and ` filter`.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` Conv3D` output and operands
     * @return a new instance of Conv3d
     * @see org.tensorflow.op.NnOps.conv3d
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each
     *  filter element on that dimension. The dimension order is determined by the
     *  value of ``` data_format```, see above for details. Dilations in the batch and
     *  depth dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> conv3d(
        input: Operand<T>,
        filter: Operand<T>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): Conv3d<T> = java.conv3d<T>(
        input,
        filter,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.Conv3d.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.Conv3d.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes the gradients of 3-D convolution with respect to the filter.
     *
     * @param T data type for ` output` output
     * @param input Shape ` [batch, depth, rows, cols, in_channels]`.
     * @param filterSizes An integer vector representing the tensor shape of ` filter`,
     *  where ``` filter``` is a 5-D
     *  ``` [filter_depth, filter_height, filter_width, in_channels, out_channels]```
     *  tensor.
     * @param outBackprop Backprop signal of shape ` [batch, out_depth, out_rows, out_cols,
     * out_channels]`.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` Conv3DBackpropFilterV2` output and operands
     * @return a new instance of Conv3dBackpropFilter
     * @see org.tensorflow.op.NnOps.conv3dBackpropFilter
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each
     *  filter element on that dimension. The dimension order is determined by the
     *  value of ``` data_format```, see above for details. Dilations in the batch and
     *  depth dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> conv3dBackpropFilter(
        input: Operand<T>,
        filterSizes: Operand<TInt32>,
        outBackprop: Operand<T>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): Conv3dBackpropFilter<T> = java.conv3dBackpropFilter<T>(
        input,
        filterSizes,
        outBackprop,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.Conv3dBackpropFilter.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.Conv3dBackpropFilter.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes the gradients of 3-D convolution with respect to the input.
     *
     * @param U data type for ` output` output
     * @param inputSizes An integer vector representing the tensor shape of ` input`,
     *  where ``` input``` is a 5-D
     *  ``` [batch, depth, rows, cols, in_channels]``` tensor.
     * @param filter Shape ` [depth, rows, cols, in_channels, out_channels]`.
     *  ``` in_channels``` must match between ``` input``` and ``` filter```.
     * @param outBackprop Backprop signal of shape ` [batch, out_depth, out_rows, out_cols,
     * out_channels]`.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param U data type for ` Conv3DBackpropInputV2` output and operands
     * @return a new instance of Conv3dBackpropInput
     * @see org.tensorflow.op.NnOps.conv3dBackpropInput
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each
     *  filter element on that dimension. The dimension order is determined by the
     *  value of ``` data_format```, see above for details. Dilations in the batch and
     *  depth dimensions must be 1.
     * @return this Options instance.
     */
    public fun <U : TNumber> conv3dBackpropInput(
        inputSizes: Operand<out TNumber>,
        filter: Operand<U>,
        outBackprop: Operand<U>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): Conv3dBackpropInput<U> = java.conv3dBackpropInput<U>(
        inputSizes,
        filter,
        outBackprop,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.Conv3dBackpropInput.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.Conv3dBackpropInput.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Performs beam search decoding on the logits given in input.
     *  A note about the attribute merge_repeated: For the beam search decoder,
     *  this means that if consecutive entries in a beam are the same, only
     *  the first of these is emitted.  That is, when the top path is &quot;A B B B B&quot;,
     *  &quot;A B&quot; is returned if merge_repeated = True but &quot;A B B B B&quot; is
     *  returned if merge_repeated = False.
     *
     * @param T data type for ` log_probability` output
     * @param inputs 3-D, shape: ` (max_time x batch_size x num_classes)`, the logits.
     * @param sequenceLength A vector containing sequence lengths, size ` (batch)`.
     * @param beamWidth A scalar &gt;= 0 (beam search beam width).
     * @param topPaths A scalar &gt;= 0, &lt;= beam_width (controls output size).
     * @param options carries optional attribute values
     * @param T data type for ` CTCBeamSearchDecoder` output and operands
     * @return a new instance of CtcBeamSearchDecoder
     * @see org.tensorflow.op.NnOps.ctcBeamSearchDecoder
     * @param mergeRepeated Sets the mergeRepeated option.
     *
     * @param mergeRepeated If true, merge repeated classes in output.
     * @return this Options instance.
     */
    public fun <T : TNumber> ctcBeamSearchDecoder(
        inputs: Operand<T>,
        sequenceLength: Operand<TInt32>,
        beamWidth: Long,
        topPaths: Long,
        mergeRepeated: Boolean? = null
    ): CtcBeamSearchDecoder<T> = java.ctcBeamSearchDecoder<T>(
        inputs,
        sequenceLength,
        beamWidth,
        topPaths,
        *listOfNotNull(
            mergeRepeated?.let { org.tensorflow.op.nn.CtcBeamSearchDecoder.mergeRepeated(it) }
        ).toTypedArray()
    )

    /**
     * Performs greedy decoding on the logits given in inputs.
     *  A note about the attribute merge_repeated: if enabled, when
     *  consecutive logits' maximum indices are the same, only the first of
     *  these is emitted.  Labeling the blank '*', the sequence &quot;A B B * B B&quot;
     *  becomes &quot;A B B&quot; if merge_repeated = True and &quot;A B B B B&quot; if
     *  merge_repeated = False.
     *  Regardless of the value of merge_repeated, if the maximum index of a given
     *  time and batch corresponds to the blank, index ``` (num_classes - 1)```, no new
     *  element is emitted.
     *
     * @param T data type for ` log_probability` output
     * @param inputs 3-D, shape: ` (max_time x batch_size x num_classes)`, the logits.
     * @param sequenceLength A vector containing sequence lengths, size ` (batch_size)`.
     * @param options carries optional attribute values
     * @param T data type for ` CTCGreedyDecoder` output and operands
     * @return a new instance of CtcGreedyDecoder
     * @see org.tensorflow.op.NnOps.ctcGreedyDecoder
     * @param mergeRepeated Sets the mergeRepeated option.
     *
     * @param mergeRepeated If True, merge repeated classes in output.
     * @return this Options instance.
     */
    public fun <T : TNumber> ctcGreedyDecoder(
        inputs: Operand<T>,
        sequenceLength: Operand<TInt32>,
        mergeRepeated: Boolean? = null
    ): CtcGreedyDecoder<T> = java.ctcGreedyDecoder<T>(
        inputs,
        sequenceLength,
        *listOfNotNull(
            mergeRepeated?.let { org.tensorflow.op.nn.CtcGreedyDecoder.mergeRepeated(it) }
        ).toTypedArray()
    )

    /**
     * Calculates the CTC Loss (log probability) for each batch entry.  Also calculates
     *  the gradient.  This class performs the softmax operation for you, so inputs
     *  should be e.g. linear projections of outputs by an LSTM.
     *
     * @param T data type for ` loss` output
     * @param inputs 3-D, shape: ` (max_time x batch_size x num_classes)`, the logits.
     * @param labelsIndices The indices of a ` SparseTensor<int32, 2>`.
     *  ``` labels_indices(i, :) == [b, t]``` means ``` labels_values(i)``` stores the id for
     *  ``` (batch b, time t)```.
     * @param labelsValues The values (labels) associated with the given batch and time.
     * @param sequenceLength A vector containing sequence lengths (batch).
     * @param options carries optional attribute values
     * @param T data type for ` CTCLoss` output and operands
     * @return a new instance of CtcLoss
     * @see org.tensorflow.op.NnOps.ctcLoss
     * @param preprocessCollapseRepeated Sets the preprocessCollapseRepeated option.
     *
     * @param preprocessCollapseRepeated Scalar, if true then repeated labels are
     *  collapsed prior to the CTC calculation.
     * @return this Options instance.
     * @param ctcMergeRepeated Sets the ctcMergeRepeated option.
     *
     * @param ctcMergeRepeated Scalar.  If set to false, <em>during</em> CTC calculation
     *  repeated non-blank labels will not be merged and are interpreted as
     *  individual labels.  This is a simplified version of CTC.
     * @return this Options instance.
     * @param ignoreLongerOutputsThanInputs Sets the ignoreLongerOutputsThanInputs option.
     *
     * @param ignoreLongerOutputsThanInputs Scalar. If set to true, during CTC
     *  calculation, items that have longer output sequences than input sequences
     *  are skipped: they don't contribute to the loss term and have zero-gradient.
     * @return this Options instance.
     */
    public fun <T : TNumber> ctcLoss(
        inputs: Operand<T>,
        labelsIndices: Operand<TInt64>,
        labelsValues: Operand<TInt32>,
        sequenceLength: Operand<TInt32>,
        preprocessCollapseRepeated: Boolean? = null,
        ctcMergeRepeated: Boolean? = null,
        ignoreLongerOutputsThanInputs: Boolean? = null
    ): CtcLoss<T> = java.ctcLoss<T>(
        inputs,
        labelsIndices,
        labelsValues,
        sequenceLength,
        *listOfNotNull(
            preprocessCollapseRepeated?.let {
                org.tensorflow.op.nn.CtcLoss.preprocessCollapseRepeated(it)
            },
            ctcMergeRepeated?.let { org.tensorflow.op.nn.CtcLoss.ctcMergeRepeated(it) },
            ignoreLongerOutputsThanInputs?.let {
                org.tensorflow.op.nn.CtcLoss.ignoreLongerOutputsThanInputs(it)
            }
        ).toTypedArray()
    )

    /**
     * Converts CudnnRNN params from canonical form to usable form. It supports the projection in
     * LSTM.
     *  Writes a set of weights into the opaque params buffer so they can be used in
     *  upcoming training or inferences.
     *  Note that the params buffer may not be compatible across different GPUs. So any
     *  save and restoration should be converted to and from the canonical weights and
     *  biases.
     *  num_layers: Specifies the number of layers in the RNN model.
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
     * @param T data type for ` params` output
     * @param numLayers the numLayers value
     * @param numUnits the numUnits value
     * @param inputSize the inputSize value
     * @param weights the weights value
     * @param biases the biases value
     * @param options carries optional attribute values
     * @param T data type for ` CudnnRNNCanonicalToParamsV2` output and operands
     * @return a new instance of CudnnRNNCanonicalToParams
     * @see org.tensorflow.op.NnOps.cudnnRNNCanonicalToParams
     * @param rnnMode Sets the rnnMode option.
     *
     * @param rnnMode the rnnMode option
     * @return this Options instance.
     * @param inputMode Sets the inputMode option.
     *
     * @param inputMode the inputMode option
     * @return this Options instance.
     * @param direction Sets the direction option.
     *
     * @param direction the direction option
     * @return this Options instance.
     * @param dropout Sets the dropout option.
     *
     * @param dropout the dropout option
     * @return this Options instance.
     * @param seed Sets the seed option.
     *
     * @param seed the seed option
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 the seed2 option
     * @return this Options instance.
     * @param numProj Sets the numProj option.
     *
     * @param numProj the numProj option
     * @return this Options instance.
     */
    public fun <T : TNumber> cudnnRNNCanonicalToParams(
        numLayers: Operand<TInt32>,
        numUnits: Operand<TInt32>,
        inputSize: Operand<TInt32>,
        weights: Iterable<Operand<T>>,
        biases: Iterable<Operand<T>>,
        rnnMode: String? = null,
        inputMode: String? = null,
        direction: String? = null,
        dropout: Float? = null,
        seed: Long? = null,
        seed2: Long? = null,
        numProj: Long? = null
    ): CudnnRNNCanonicalToParams<T> = java.cudnnRNNCanonicalToParams<T>(
        numLayers,
        numUnits,
        inputSize,
        weights,
        biases,
        *listOfNotNull(
            rnnMode?.let { org.tensorflow.op.nn.CudnnRNNCanonicalToParams.rnnMode(it) },
            inputMode?.let { org.tensorflow.op.nn.CudnnRNNCanonicalToParams.inputMode(it) },
            direction?.let { org.tensorflow.op.nn.CudnnRNNCanonicalToParams.direction(it) },
            dropout?.let { org.tensorflow.op.nn.CudnnRNNCanonicalToParams.dropout(it) },
            seed?.let { org.tensorflow.op.nn.CudnnRNNCanonicalToParams.seed(it) },
            seed2?.let { org.tensorflow.op.nn.CudnnRNNCanonicalToParams.seed2(it) },
            numProj?.let { org.tensorflow.op.nn.CudnnRNNCanonicalToParams.numProj(it) }
        ).toTypedArray()
    )

    /**
     * Retrieves CudnnRNN params in canonical form. It supports the projection in LSTM.
     *  Retrieves a set of weights from the opaque params buffer that can be saved and
     *  restored in a way compatible with future runs.
     *  Note that the params buffer may not be compatible across different GPUs. So any
     *  save and restoration should be converted to and from the canonical weights and
     *  biases.
     *  num_layers: Specifies the number of layers in the RNN model.
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
     * @param T data type for ` weights` output
     * @param numLayers the numLayers value
     * @param numUnits the numUnits value
     * @param inputSize the inputSize value
     * @param params the params value
     * @param numParamsWeights the value of the numParamsWeights property
     * @param numParamsBiases the value of the numParamsBiases property
     * @param options carries optional attribute values
     * @param T data type for ` CudnnRNNParamsToCanonicalV2` output and operands
     * @return a new instance of CudnnRNNParamsToCanonical
     * @see org.tensorflow.op.NnOps.cudnnRNNParamsToCanonical
     * @param rnnMode Sets the rnnMode option.
     *
     * @param rnnMode the rnnMode option
     * @return this Options instance.
     * @param inputMode Sets the inputMode option.
     *
     * @param inputMode the inputMode option
     * @return this Options instance.
     * @param direction Sets the direction option.
     *
     * @param direction the direction option
     * @return this Options instance.
     * @param dropout Sets the dropout option.
     *
     * @param dropout the dropout option
     * @return this Options instance.
     * @param seed Sets the seed option.
     *
     * @param seed the seed option
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 the seed2 option
     * @return this Options instance.
     * @param numProj Sets the numProj option.
     *
     * @param numProj the numProj option
     * @return this Options instance.
     */
    public fun <T : TNumber> cudnnRNNParamsToCanonical(
        numLayers: Operand<TInt32>,
        numUnits: Operand<TInt32>,
        inputSize: Operand<TInt32>,
        params: Operand<T>,
        numParamsWeights: Long,
        numParamsBiases: Long,
        rnnMode: String? = null,
        inputMode: String? = null,
        direction: String? = null,
        dropout: Float? = null,
        seed: Long? = null,
        seed2: Long? = null,
        numProj: Long? = null
    ): CudnnRNNParamsToCanonical<T> = java.cudnnRNNParamsToCanonical<T>(
        numLayers,
        numUnits,
        inputSize,
        params,
        numParamsWeights,
        numParamsBiases,
        *listOfNotNull(
            rnnMode?.let { org.tensorflow.op.nn.CudnnRNNParamsToCanonical.rnnMode(it) },
            inputMode?.let { org.tensorflow.op.nn.CudnnRNNParamsToCanonical.inputMode(it) },
            direction?.let { org.tensorflow.op.nn.CudnnRNNParamsToCanonical.direction(it) },
            dropout?.let { org.tensorflow.op.nn.CudnnRNNParamsToCanonical.dropout(it) },
            seed?.let { org.tensorflow.op.nn.CudnnRNNParamsToCanonical.seed(it) },
            seed2?.let { org.tensorflow.op.nn.CudnnRNNParamsToCanonical.seed2(it) },
            numProj?.let { org.tensorflow.op.nn.CudnnRNNParamsToCanonical.numProj(it) }
        ).toTypedArray()
    )

    /**
     * Computes size of weights that can be used by a Cudnn RNN model.
     *  Return the params size that can be used by the Cudnn RNN model. Subsequent
     *  weight allocation and initialization should use this size.
     *  num_layers: Specifies the number of layers in the RNN model.
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
     * @param T data type for ` params_size` output
     * @param numLayers the numLayers value
     * @param numUnits the numUnits value
     * @param inputSize the inputSize value
     * @param T the value of the T property
     * @param S the value of the S property
     * @param options carries optional attribute values
     * @param T data type for ` CudnnRNNParamsSize` output and operands
     * @param U data type for ` CudnnRNNParamsSize` output and operands
     * @return a new instance of CudnnRnnParamsSize
     * @see org.tensorflow.op.NnOps.cudnnRnnParamsSize
     * @param rnnMode Sets the rnnMode option.
     *
     * @param rnnMode the rnnMode option
     * @return this Options instance.
     * @param inputMode Sets the inputMode option.
     *
     * @param inputMode the inputMode option
     * @return this Options instance.
     * @param direction Sets the direction option.
     *
     * @param direction the direction option
     * @return this Options instance.
     * @param dropout Sets the dropout option.
     *
     * @param dropout the dropout option
     * @return this Options instance.
     * @param seed Sets the seed option.
     *
     * @param seed the seed option
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 the seed2 option
     * @return this Options instance.
     * @param numProj Sets the numProj option.
     *
     * @param numProj the numProj option
     * @return this Options instance.
     */
    public fun <T : TNumber, U : TNumber> cudnnRnnParamsSize(
        numLayers: Operand<TInt32>,
        numUnits: Operand<TInt32>,
        inputSize: Operand<TInt32>,
        T_: Class<U>,
        S: Class<T>,
        rnnMode: String? = null,
        inputMode: String? = null,
        direction: String? = null,
        dropout: Float? = null,
        seed: Long? = null,
        seed2: Long? = null,
        numProj: Long? = null
    ): CudnnRnnParamsSize<T> = java.cudnnRnnParamsSize<T, U>(
        numLayers,
        numUnits,
        inputSize,
        T_,
        S,
        *listOfNotNull(
            rnnMode?.let { org.tensorflow.op.nn.CudnnRnnParamsSize.rnnMode(it) },
            inputMode?.let { org.tensorflow.op.nn.CudnnRnnParamsSize.inputMode(it) },
            direction?.let { org.tensorflow.op.nn.CudnnRnnParamsSize.direction(it) },
            dropout?.let { org.tensorflow.op.nn.CudnnRnnParamsSize.dropout(it) },
            seed?.let { org.tensorflow.op.nn.CudnnRnnParamsSize.seed(it) },
            seed2?.let { org.tensorflow.op.nn.CudnnRnnParamsSize.seed2(it) },
            numProj?.let { org.tensorflow.op.nn.CudnnRnnParamsSize.numProj(it) }
        ).toTypedArray()
    )

    /**
     * Returns the dimension index in the destination data format given the one in
     *  the source data format.
     *
     * @param T data type for ` y` output
     * @param x A Tensor with each element as a dimension index in source data format.
     *  Must be in the range &#91;-4, 4).
     * @param options carries optional attribute values
     * @param T data type for ` DataFormatDimMap` output and operands
     * @return a new instance of DataFormatDimMap
     * @see org.tensorflow.op.NnOps.dataFormatDimMap
     * @param srcFormat Sets the srcFormat option.
     *
     * @param srcFormat source data format.
     * @return this Options instance.
     * @param dstFormat Sets the dstFormat option.
     *
     * @param dstFormat destination data format.
     * @return this Options instance.
     */
    public fun <T : TNumber> dataFormatDimMap(
        x: Operand<T>,
        srcFormat: String? = null,
        dstFormat: String? = null
    ): DataFormatDimMap<T> = java.dataFormatDimMap<T>(
        x,
        *listOfNotNull(
            srcFormat?.let { org.tensorflow.op.nn.DataFormatDimMap.srcFormat(it) },
            dstFormat?.let { org.tensorflow.op.nn.DataFormatDimMap.dstFormat(it) }
        ).toTypedArray()
    )

    /**
     * Permute input tensor from ``` src_format``` to ``` dst_format```.
     *  Input tensor must be a vector of size 4, or a 4x2 tensor.
     *  For example, with ``` src_format``` of ``` NHWC```, ``` dst_format``` of ``` NCHW```, and
     * inputs:
     *
     *  &#91;1, 2, 3, 4]
     *
     *  and
     *
     *  &#91;&#91;1, 2, 3, 4],
     *   &#91;5, 6, 7, 8]]
     *
     *  , the outputs will be (respectively):
     *
     *  &#91;1, 4, 2, 3]
     *
     *  and
     *
     *  &#91;&#91;1, 4, 2, 3],
     *   &#91;5, 8, 6, 7]]
     *
     *
     * @param T data type for ` y` output
     * @param x Vector of size 4 or Tensor of shape (4, 2) in source data format.
     * @param options carries optional attribute values
     * @param T data type for ` DataFormatVecPermute` output and operands
     * @return a new instance of DataFormatVecPermute
     * @see org.tensorflow.op.NnOps.dataFormatVecPermute
     * @param srcFormat Sets the srcFormat option.
     *
     * @param srcFormat source data format.
     * @return this Options instance.
     * @param dstFormat Sets the dstFormat option.
     *
     * @param dstFormat destination data format.
     * @return this Options instance.
     */
    public fun <T : TNumber> dataFormatVecPermute(
        x: Operand<T>,
        srcFormat: String? = null,
        dstFormat: String? = null
    ): DataFormatVecPermute<T> = java.dataFormatVecPermute<T>(
        x,
        *listOfNotNull(
            srcFormat?.let { org.tensorflow.op.nn.DataFormatVecPermute.srcFormat(it) },
            dstFormat?.let { org.tensorflow.op.nn.DataFormatVecPermute.dstFormat(it) }
        ).toTypedArray()
    )

    /**
     * DepthToSpace for tensors of type T.
     *  Rearranges data from depth into blocks of spatial data.
     *  This is the reverse transformation of SpaceToDepth. More specifically,
     *  this op outputs a copy of the input tensor where values from the ``` depth```
     *  dimension are moved in spatial blocks to the ``` height``` and ``` width``` dimensions.
     *  The attr ``` block_size``` indicates the input block size and how the data is moved.
     *  <ul>
     *  <li>Chunks of data of size ``` block_size * block_size``` from depth are rearranged
     *  into non-overlapping blocks of size ``` block_size x block_size```</li>
     *  <li>The width the output tensor is ``` input_depth * block_size```, whereas the
     *  height is ``` input_height * block_size```.</li>
     *  <li>The Y, X coordinates within each block of the output image are determined
     *  by the high order component of the input channel index.</li>
     *  <li>The depth of the input tensor must be divisible by
     *  ``` block_size * block_size```.</li>
     *  </ul>
     *  The ``` data_format``` attr specifies the layout of the input and output tensors
     *  with the following options:
     *  &quot;NHWC&quot;: ``` [ batch, height, width, channels ]```
     *  &quot;NCHW&quot;: ``` [ batch, channels, height, width ]```
     *  &quot;NCHW_VECT_C&quot;:
     *  ``` qint8 [ batch, channels / 4, height, width, 4 ]```
     *  It is useful to consider the operation as transforming a 6-D Tensor.
     *  e.g. for data_format = NHWC,
     *  Each element in the input tensor can be specified via 6 coordinates,
     *  ordered by decreasing memory layout significance as:
     *  n,iY,iX,bY,bX,oC  (where n=batch index, iX, iY means X or Y coordinates
     *  within the input image, bX, bY means coordinates
     *  within the output block, oC means output channels).
     *  The output would be the input transposed to the following layout:
     *  n,iY,bY,iX,bX,oC
     *  This operation is useful for resizing the activations between convolutions
     *  (but keeping all data), e.g. instead of pooling. It is also useful for training
     *  purely convolutional models.
     *  For example, given an input of shape ``` [1, 1, 1, 4]```, data_format = &quot;NHWC&quot;
     * and
     *  block_size = 2:
     *
     *  x = &#91;&#91;&#91;&#91;1, 2, 3, 4]]]]
     *
     *
     *  This operation will output a tensor of shape ``` [1, 2, 2, 1]```:
     *
     *     &#91;&#91;&#91;&#91;1], &#91;2]],
     *       &#91;&#91;3], &#91;4]]]]
     *
     *  Here, the input has a batch of 1 and each batch element has shape ``` [1, 1, 4]```,
     *  the corresponding output will have 2x2 elements and will have a depth of
     *  1 channel (1 = ``` 4 / (block_size * block_size)```).
     *  The output element shape is ``` [2, 2, 1]```.
     *  For an input tensor with larger depth, here of shape ``` [1, 1, 1, 12]```, e.g.
     *
     *  x = &#91;&#91;&#91;&#91;1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]]]
     *
     *  This operation, for block size of 2, will return the following tensor of shape
     *  ``` [1, 2, 2, 3]```
     *
     *     &#91;&#91;&#91;&#91;1, 2, 3], &#91;4, 5, 6]],
     *       &#91;&#91;7, 8, 9], &#91;10, 11, 12]]]]
     *
     *
     *  Similarly, for the following input of shape ``` [1 2 2 4]```, and a block size of 2:
     *
     *  x =  &#91;&#91;&#91;&#91;1, 2, 3, 4],
     *         &#91;5, 6, 7, 8]],
     *        &#91;&#91;9, 10, 11, 12],
     *         &#91;13, 14, 15, 16]]]]
     *
     *  the operator will return the following tensor of shape ``` [1 4 4 1]```:
     *
     *  x = &#91;&#91;&#91; &#91;1],   &#91;2],  &#91;5],  &#91;6]],
     *        &#91; &#91;3],   &#91;4],  &#91;7],  &#91;8]],
     *        &#91; &#91;9],  &#91;10], &#91;13],  &#91;14]],
     *        &#91; &#91;11], &#91;12], &#91;15],  &#91;16]]]]
     *
     *
     *
     * @param T data type for ` output` output
     * @param input the input value
     * @param blockSize The size of the spatial block, same as in Space2Depth.
     * @param options carries optional attribute values
     * @param T data type for ` DepthToSpace` output and operands
     * @return a new instance of DepthToSpace
     * @see org.tensorflow.op.NnOps.depthToSpace
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat the dataFormat option
     * @return this Options instance.
     */
    public fun <T : TType> depthToSpace(
        input: Operand<T>,
        blockSize: Long,
        dataFormat: String? = null
    ): DepthToSpace<T> = java.depthToSpace<T>(
        input,
        blockSize,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.DepthToSpace.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes a 2-D depthwise convolution given 4-D ``` input``` and ``` filter``` tensors.
     *  Given an input tensor of shape ``` [batch, in_height, in_width, in_channels]```
     *  and a filter / kernel tensor of shape
     *  ``` [filter_height, filter_width, in_channels, channel_multiplier]```, containing
     *  ``` in_channels``` convolutional filters of depth 1, ``` depthwise_conv2d``` applies
     *  a different filter to each input channel (expanding from 1 channel to
     *  ``` channel_multiplier``` channels for each), then concatenates the results
     *  together. Thus, the output has ``` in_channels * channel_multiplier} channels.
     *
     *  for k in 0..in_channels-1
     *    for q in 0..channel_multiplier-1
     *      output[b, i, j, k * channel_multiplier + q] =
     *        sum_{di, dj```
     *  input&#91;b, strides&#91;1] * i + di, strides&#91;2] * j + dj, k] *
     *                          filter&#91;di, dj, k, q]
     *
     *  Must have ``` strides[0] = strides[3] = 1```.  For the most common case of the same
     *  horizontal and vertices strides, ``` strides = [1, stride, stride, 1]```.
     *
     * @param T data type for ` output` output
     * @param input the input value
     * @param filter the filter value
     * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
     *  of ``` input```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` DepthwiseConv2dNative` output and operands
     * @return a new instance of DepthwiseConv2dNative
     * @see org.tensorflow.op.NnOps.depthwiseConv2dNative
     * @param explicitPaddings Sets the explicitPaddings option.
     *
     * @param explicitPaddings the explicitPaddings option
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, height, width, channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, channels, height, width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each filter
     *  element on that dimension. The dimension order is determined by the value of
     *  ``` data_format```, see above for details. Dilations in the batch and depth
     *  dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> depthwiseConv2dNative(
        input: Operand<T>,
        filter: Operand<T>,
        strides: List<Long>,
        padding: String,
        explicitPaddings: List<Long>? = null,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): DepthwiseConv2dNative<T> = java.depthwiseConv2dNative<T>(
        input,
        filter,
        strides,
        padding,
        *listOfNotNull(
            explicitPaddings?.let { org.tensorflow.op.nn.DepthwiseConv2dNative.explicitPaddings(it) },
            dataFormat?.let { org.tensorflow.op.nn.DepthwiseConv2dNative.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.DepthwiseConv2dNative.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes the gradients of depthwise convolution with respect to the filter.
     *
     * @param T data type for ` output` output
     * @param input 4-D with shape based on ` data_format`.  For example, if
     *  ``` data_format``` is 'NHWC' then ``` input``` is a 4-D ``` [batch, in_height, in_width,
     * in_channels]``` tensor.
     * @param filterSizes An integer vector representing the tensor shape of ` filter`,
     *  where ``` filter``` is a 4-D
     *  ``` [filter_height, filter_width, in_channels, depthwise_multiplier]``` tensor.
     * @param outBackprop 4-D with shape  based on ` data_format`.
     *  For example, if ``` data_format``` is 'NHWC' then
     *  out_backprop shape is ``` [batch, out_height, out_width, out_channels]```.
     *  Gradients w.r.t. the output of the convolution.
     * @param strides The stride of the sliding window for each dimension of the input
     *  of the convolution.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` DepthwiseConv2dNativeBackpropFilter` output and operands
     * @return a new instance of DepthwiseConv2dNativeBackpropFilter
     * @see org.tensorflow.op.NnOps.depthwiseConv2dNativeBackpropFilter
     * @param explicitPaddings Sets the explicitPaddings option.
     *
     * @param explicitPaddings the explicitPaddings option
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, height, width, channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, channels, height, width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each filter
     *  element on that dimension. The dimension order is determined by the value of
     *  ``` data_format```, see above for details. Dilations in the batch and depth
     *  dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> depthwiseConv2dNativeBackpropFilter(
        input: Operand<T>,
        filterSizes: Operand<TInt32>,
        outBackprop: Operand<T>,
        strides: List<Long>,
        padding: String,
        explicitPaddings: List<Long>? = null,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): DepthwiseConv2dNativeBackpropFilter<T> = java.depthwiseConv2dNativeBackpropFilter<T>(
        input,
        filterSizes,
        outBackprop,
        strides,
        padding,
        *listOfNotNull(
            explicitPaddings?.let {
                org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter.explicitPaddings(it)
            },
            dataFormat?.let { org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes the gradients of depthwise convolution with respect to the input.
     *
     * @param T data type for ` output` output
     * @param inputSizes An integer vector representing the shape of ` input`, based
     *  on ``` data_format```.  For example, if ``` data_format``` is 'NHWC' then
     *  ``` input``` is a 4-D ``` [batch, height, width, channels]``` tensor.
     * @param filter 4-D with shape
     *  ``` [filter_height, filter_width, in_channels, depthwise_multiplier]```.
     * @param outBackprop 4-D with shape  based on ` data_format`.
     *  For example, if ``` data_format``` is 'NHWC' then
     *  out_backprop shape is ``` [batch, out_height, out_width, out_channels]```.
     *  Gradients w.r.t. the output of the convolution.
     * @param strides The stride of the sliding window for each dimension of the input
     *  of the convolution.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` DepthwiseConv2dNativeBackpropInput` output and operands
     * @return a new instance of DepthwiseConv2dNativeBackpropInput
     * @see org.tensorflow.op.NnOps.depthwiseConv2dNativeBackpropInput
     * @param explicitPaddings Sets the explicitPaddings option.
     *
     * @param explicitPaddings the explicitPaddings option
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, height, width, channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, channels, height, width].
     * @return this Options instance.
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each filter
     *  element on that dimension. The dimension order is determined by the value of
     *  ``` data_format```, see above for details. Dilations in the batch and depth
     *  dimensions must be 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> depthwiseConv2dNativeBackpropInput(
        inputSizes: Operand<TInt32>,
        filter: Operand<T>,
        outBackprop: Operand<T>,
        strides: List<Long>,
        padding: String,
        explicitPaddings: List<Long>? = null,
        dataFormat: String? = null,
        dilations: List<Long>? = null
    ): DepthwiseConv2dNativeBackpropInput<T> = java.depthwiseConv2dNativeBackpropInput<T>(
        inputSizes,
        filter,
        outBackprop,
        strides,
        padding,
        *listOfNotNull(
            explicitPaddings?.let {
                org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput.explicitPaddings(it)
            },
            dataFormat?.let { org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput.dataFormat(it) },
            dilations?.let { org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Computes the grayscale dilation of 4-D ``` input``` and 3-D ``` filter``` tensors.
     *  The ``` input``` tensor has shape ``` [batch, in_height, in_width, depth]``` and the
     *  ``` filter``` tensor has shape ``` [filter_height, filter_width, depth]```, i.e., each
     *  input channel is processed independently of the others with its own structuring
     *  function. The ``` output``` tensor has shape
     *  ``` [batch, out_height, out_width, depth]```. The spatial dimensions of the output
     *  tensor depend on the ``` padding``` algorithm. We currently only support the default
     *  &quot;NHWC&quot; ``` data_format```.
     *  In detail, the grayscale morphological 2-D dilation is the max-sum correlation
     *  (for consistency with ``` conv2d}, we use unmirrored filters):
     *
     *  output[b, y, x, c] =
     *     max_{dy, dx```
     *  input&#91;b,
     *                        strides&#91;1] * y + rates&#91;1] * dy,
     *                        strides&#91;2] * x + rates&#91;2] * dx,
     *                        c] +
     *                  filter&#91;dy, dx, c]
     *
     *  Max-pooling is a special case when the filter has size equal to the pooling
     *  kernel size and contains all zeros.
     *  Note on duality: The dilation of ``` input``` by the ``` filter``` is equal to the
     *  negation of the erosion of ``` -input``` by the reflected ``` filter```.
     *
     * @param T data type for ` output` output
     * @param input 4-D with shape ` [batch, in_height, in_width, depth]`.
     * @param filter 3-D with shape ` [filter_height, filter_width, depth]`.
     * @param strides The stride of the sliding window for each dimension of the input
     *  tensor. Must be: ``` [1, stride_height, stride_width, 1]```.
     * @param rates The input stride for atrous morphological dilation. Must be:
     *  ``` [1, rate_height, rate_width, 1]```.
     * @param padding The type of padding algorithm to use.
     * @param T data type for ` Dilation2D` output and operands
     * @return a new instance of Dilation2d
     * @see org.tensorflow.op.NnOps.dilation2d
     */
    public fun <T : TNumber> dilation2d(
        input: Operand<T>,
        filter: Operand<T>,
        strides: List<Long>,
        rates: List<Long>,
        padding: String
    ): Dilation2d<T> = java.dilation2d<T>(
        input,
        filter,
        strides,
        rates,
        padding
    )

    /**
     * Computes the gradient of morphological 2-D dilation with respect to the filter.
     *
     * @param T data type for ` filter_backprop` output
     * @param input 4-D with shape ` [batch, in_height, in_width, depth]`.
     * @param filter 3-D with shape ` [filter_height, filter_width, depth]`.
     * @param outBackprop 4-D with shape ` [batch, out_height, out_width, depth]`.
     * @param strides 1-D of length 4. The stride of the sliding window for each dimension of
     *  the input tensor. Must be: ``` [1, stride_height, stride_width, 1]```.
     * @param rates 1-D of length 4. The input stride for atrous morphological dilation.
     *  Must be: ``` [1, rate_height, rate_width, 1]```.
     * @param padding The type of padding algorithm to use.
     * @param T data type for ` Dilation2DBackpropFilter` output and operands
     * @return a new instance of Dilation2dBackpropFilter
     * @see org.tensorflow.op.NnOps.dilation2dBackpropFilter
     */
    public fun <T : TNumber> dilation2dBackpropFilter(
        input: Operand<T>,
        filter: Operand<T>,
        outBackprop: Operand<T>,
        strides: List<Long>,
        rates: List<Long>,
        padding: String
    ): Dilation2dBackpropFilter<T> = java.dilation2dBackpropFilter<T>(
        input,
        filter,
        outBackprop,
        strides,
        rates,
        padding
    )

    /**
     * Computes the gradient of morphological 2-D dilation with respect to the input.
     *
     * @param T data type for ` in_backprop` output
     * @param input 4-D with shape ` [batch, in_height, in_width, depth]`.
     * @param filter 3-D with shape ` [filter_height, filter_width, depth]`.
     * @param outBackprop 4-D with shape ` [batch, out_height, out_width, depth]`.
     * @param strides 1-D of length 4. The stride of the sliding window for each dimension of
     *  the input tensor. Must be: ``` [1, stride_height, stride_width, 1]```.
     * @param rates 1-D of length 4. The input stride for atrous morphological dilation.
     *  Must be: ``` [1, rate_height, rate_width, 1]```.
     * @param padding The type of padding algorithm to use.
     * @param T data type for ` Dilation2DBackpropInput` output and operands
     * @return a new instance of Dilation2dBackpropInput
     * @see org.tensorflow.op.NnOps.dilation2dBackpropInput
     */
    public fun <T : TNumber> dilation2dBackpropInput(
        input: Operand<T>,
        filter: Operand<T>,
        outBackprop: Operand<T>,
        strides: List<Long>,
        rates: List<Long>,
        padding: String
    ): Dilation2dBackpropInput<T> = java.dilation2dBackpropInput<T>(
        input,
        filter,
        outBackprop,
        strides,
        rates,
        padding
    )

    /**
     * Computes exponential linear: ``` exp(features) - 1``` if &lt; 0, ``` features``` otherwise.
     *  See  <a href="http://arxiv.org/abs/1511.07289">Fast and Accurate Deep Network Learning by
     * Exponential Linear Units (ELUs)
     *  </a>
     *
     * @param T data type for ` activations` output
     * @param features the features value
     * @param T data type for ` Elu` output and operands
     * @return a new instance of Elu
     * @see org.tensorflow.op.NnOps.elu
     */
    public fun <T : TNumber> elu(features: Operand<T>): Elu<T> = java.elu<T>(
        features
    )

    /**
     * Generates labels for candidate sampling with a learned unigram distribution.
     *  A unigram sampler could use a fixed unigram distribution read from a
     *  file or passed in as an in-memory array instead of building up the distribution
     *  from data on the fly. There is also an option to skew the distribution by
     *  applying a distortion power to the weights.
     *  The vocabulary file should be in CSV-like format, with the last field
     *  being the weight associated with the word.
     *  For each batch, this op picks a single set of sampled candidate labels.
     *  The advantages of sampling candidates per-batch are simplicity and the
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
     * @param rangeMax The sampler will sample integers from the interval &#91;0, range_max).
     * @param options carries optional attribute values
     * @return a new instance of FixedUnigramCandidateSampler
     * @see org.tensorflow.op.NnOps.fixedUnigramCandidateSampler
     * @param vocabFile Sets the vocabFile option.
     *
     * @param vocabFile Each valid line in this file (which should have a CSV-like format)
     *  corresponds to a valid word ID. IDs are in sequential order, starting from
     *  num_reserved_ids. The last entry in each line is expected to be a value
     *  corresponding to the count or relative probability. Exactly one of vocab_file
     *  and unigrams needs to be passed to this op.
     * @return this Options instance.
     * @param distortion Sets the distortion option.
     *
     * @param distortion The distortion is used to skew the unigram probability distribution.
     *  Each weight is first raised to the distortion's power before adding to the
     *  internal unigram distribution. As a result, distortion = 1.0 gives regular
     *  unigram sampling (as defined by the vocab file), and distortion = 0.0 gives
     *  a uniform distribution.
     * @return this Options instance.
     * @param numReservedIds Sets the numReservedIds option.
     *
     * @param numReservedIds Optionally some reserved IDs can be added in the range &#91;0,
     *  ..., num_reserved_ids) by the users. One use case is that a special unknown
     *  word token is used as ID 0. These IDs will have a sampling probability of 0.
     * @return this Options instance.
     * @param numShards Sets the numShards option.
     *
     * @param numShards A sampler can be used to sample from a subset of the original range
     *  in order to speed up the whole computation through parallelism. This parameter
     *  (together with 'shard') indicates the number of partitions that are being
     *  used in the overall computation.
     * @return this Options instance.
     * @param shard Sets the shard option.
     *
     * @param shard A sampler can be used to sample from a subset of the original range
     *  in order to speed up the whole computation through parallelism. This parameter
     *  (together with 'num_shards') indicates the particular partition number of a
     *  sampler op, when partitioning is being used.
     * @return this Options instance.
     * @param unigrams Sets the unigrams option.
     *
     * @param unigrams A list of unigram counts or probabilities, one per ID in sequential
     *  order. Exactly one of vocab_file and unigrams should be passed to this op.
     * @return this Options instance.
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun fixedUnigramCandidateSampler(
        trueClasses: Operand<TInt64>,
        numTrue: Long,
        numSampled: Long,
        unique: Boolean,
        rangeMax: Long,
        vocabFile: String? = null,
        distortion: Float? = null,
        numReservedIds: Long? = null,
        numShards: Long? = null,
        shard: Long? = null,
        unigrams: List<Float>? = null,
        seed: Long? = null,
        seed2: Long? = null
    ): FixedUnigramCandidateSampler = java.fixedUnigramCandidateSampler(
        trueClasses,
        numTrue,
        numSampled,
        unique,
        rangeMax,
        *listOfNotNull(
            vocabFile?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.vocabFile(it) },
            distortion?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.distortion(it) },
            numReservedIds?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.numReservedIds(it) },
            numShards?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.numShards(it) },
            shard?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.shard(it) },
            unigrams?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.unigrams(it) },
            seed?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.seed(it) },
            seed2?.let { org.tensorflow.op.nn.FixedUnigramCandidateSampler.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Performs fractional average pooling on the input.
     *  Fractional average pooling is similar to Fractional max pooling in the pooling
     *  region generation step. The only difference is that after pooling regions are
     *  generated, a mean operation is performed instead of a max operation in each
     *  pooling region.
     *
     * @param T data type for ` output` output
     * @param value 4-D with shape ` [batch, height, width, channels]`.
     * @param poolingRatio Pooling ratio for each dimension of ` value`, currently only
     *  supports row and col dimension and should be &gt;= 1.0. For example, a valid
     *  pooling ratio looks like &#91;1.0, 1.44, 1.73, 1.0]. The first and last elements
     *  must be 1.0 because we don't allow pooling on batch and channels
     *  dimensions. 1.44 and 1.73 are pooling ratio on height and width dimensions
     *  respectively.
     * @param options carries optional attribute values
     * @param T data type for ` FractionalAvgPool` output and operands
     * @return a new instance of FractionalAvgPool
     * @see org.tensorflow.op.NnOps.fractionalAvgPool
     * @param pseudoRandom Sets the pseudoRandom option.
     *
     * @param pseudoRandom When set to True, generates the pooling sequence in a
     *  pseudorandom fashion, otherwise, in a random fashion. Check paper  <a
     * href="http://arxiv.org/abs/1412.6071">Benjamin
     *  Graham, Fractional Max-Pooling</a>  for
     *  difference between pseudorandom and random.
     * @return this Options instance.
     * @param overlapping Sets the overlapping option.
     *
     * @param overlapping When set to True, it means when pooling, the values at the boundary
     *  of adjacent pooling cells are used by both cells. For example:
     *  ``` index  0  1  2  3  4```
     *  ``` value  20 5  16 3  7```
     *  If the pooling sequence is &#91;0, 2, 4], then 16, at index 2 will be used twice.
     *  The result would be &#91;41/3, 26/3] for fractional avg pooling.
     * @return this Options instance.
     * @param deterministic Sets the deterministic option.
     *
     * @param deterministic When set to True, a fixed pooling region will be used when
     *  iterating over a FractionalAvgPool node in the computation graph. Mainly used
     *  in unit test to make FractionalAvgPool deterministic.
     * @return this Options instance.
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <T : TNumber> fractionalAvgPool(
        value: Operand<T>,
        poolingRatio: List<Float>,
        pseudoRandom: Boolean? = null,
        overlapping: Boolean? = null,
        deterministic: Boolean? = null,
        seed: Long? = null,
        seed2: Long? = null
    ): FractionalAvgPool<T> = java.fractionalAvgPool<T>(
        value,
        poolingRatio,
        *listOfNotNull(
            pseudoRandom?.let { org.tensorflow.op.nn.FractionalAvgPool.pseudoRandom(it) },
            overlapping?.let { org.tensorflow.op.nn.FractionalAvgPool.overlapping(it) },
            deterministic?.let { org.tensorflow.op.nn.FractionalAvgPool.deterministic(it) },
            seed?.let { org.tensorflow.op.nn.FractionalAvgPool.seed(it) },
            seed2?.let { org.tensorflow.op.nn.FractionalAvgPool.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Performs fractional max pooling on the input.
     *  Fractional max pooling is slightly different than regular max pooling.  In
     *  regular max pooling, you downsize an input set by taking the maximum value of
     *  smaller N x N subsections of the set (often 2x2), and try to reduce the set by
     *  a factor of N, where N is an integer.  Fractional max pooling, as you might
     *  expect from the word &quot;fractional&quot;, means that the overall reduction ratio N
     *  does not have to be an integer.
     *  The sizes of the pooling regions are generated randomly but are fairly uniform.
     *  For example, let's look at the height dimension, and the constraints on the
     *  list of rows that will be pool boundaries.
     *  First we define the following:
     *  <ol>
     *  <li>input_row_length : the number of rows from the input set</li>
     *  <li>output_row_length : which will be smaller than the input</li>
     *  <li>alpha = input_row_length / output_row_length : our reduction ratio</li>
     *  <li>K = floor(alpha)</li>
     *  <li>row_pooling_sequence : this is the result list of pool boundary rows</li>
     *  </ol>
     *  Then, row_pooling_sequence should satisfy:
     *  <ol>
     *  <li>a&#91;0] = 0 : the first value of the sequence is 0</li>
     *  <li>a&#91;end] = input_row_length : the last value of the sequence is the size</li>
     *  <li>K &lt;= (a&#91;i+1] - a&#91;i]) &lt;= K+1 : all intervals are K or K+1 size</li>
     *  <li>length(row_pooling_sequence) = output_row_length+1</li>
     *  </ol>
     *  For more details on fractional max pooling, see this paper:
     *   <a href="http://arxiv.org/abs/1412.6071">Benjamin Graham, Fractional Max-Pooling</a>
     *
     * @param T data type for ` output` output
     * @param value 4-D with shape ` [batch, height, width, channels]`.
     * @param poolingRatio Pooling ratio for each dimension of ` value`, currently only
     *  supports row and col dimension and should be &gt;= 1.0. For example, a valid
     *  pooling ratio looks like &#91;1.0, 1.44, 1.73, 1.0]. The first and last elements
     *  must be 1.0 because we don't allow pooling on batch and channels
     *  dimensions. 1.44 and 1.73 are pooling ratio on height and width dimensions
     *  respectively.
     * @param options carries optional attribute values
     * @param T data type for ` FractionalMaxPool` output and operands
     * @return a new instance of FractionalMaxPool
     * @see org.tensorflow.op.NnOps.fractionalMaxPool
     * @param pseudoRandom Sets the pseudoRandom option.
     *
     * @param pseudoRandom When set to True, generates the pooling sequence in a
     *  pseudorandom fashion, otherwise, in a random fashion. Check paper  <a
     * href="http://arxiv.org/abs/1412.6071">Benjamin
     *  Graham, Fractional Max-Pooling</a>  for
     *  difference between pseudorandom and random.
     * @return this Options instance.
     * @param overlapping Sets the overlapping option.
     *
     * @param overlapping When set to True, it means when pooling, the values at the boundary
     *  of adjacent pooling cells are used by both cells. For example:
     *  ``` index  0  1  2  3  4```
     *  ``` value  20 5  16 3  7```
     *  If the pooling sequence is &#91;0, 2, 4], then 16, at index 2 will be used twice.
     *  The result would be &#91;20, 16] for fractional max pooling.
     * @return this Options instance.
     * @param deterministic Sets the deterministic option.
     *
     * @param deterministic When set to True, a fixed pooling region will be used when
     *  iterating over a FractionalMaxPool node in the computation graph. Mainly used
     *  in unit test to make FractionalMaxPool deterministic.
     * @return this Options instance.
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <T : TNumber> fractionalMaxPool(
        value: Operand<T>,
        poolingRatio: List<Float>,
        pseudoRandom: Boolean? = null,
        overlapping: Boolean? = null,
        deterministic: Boolean? = null,
        seed: Long? = null,
        seed2: Long? = null
    ): FractionalMaxPool<T> = java.fractionalMaxPool<T>(
        value,
        poolingRatio,
        *listOfNotNull(
            pseudoRandom?.let { org.tensorflow.op.nn.FractionalMaxPool.pseudoRandom(it) },
            overlapping?.let { org.tensorflow.op.nn.FractionalMaxPool.overlapping(it) },
            deterministic?.let { org.tensorflow.op.nn.FractionalMaxPool.deterministic(it) },
            seed?.let { org.tensorflow.op.nn.FractionalMaxPool.seed(it) },
            seed2?.let { org.tensorflow.op.nn.FractionalMaxPool.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Batch normalization.
     *  Note that the size of 4D Tensors are defined by either &quot;NHWC&quot; or
     * &quot;NCHW&quot;.
     *  The size of 1D Tensors matches the dimension C of the 4D Tensors.
     *
     * @param T data type for ` y` output
     * @param U data type for ` batch_mean` output
     * @param x A 4D Tensor for input data.
     * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
     * @param offset A 1D Tensor for offset, to shift to the normalized x.
     * @param mean A 1D Tensor for population mean. Used for inference only;
     *  must be empty for training.
     * @param variance A 1D Tensor for population variance. Used for inference only;
     *  must be empty for training.
     * @param options carries optional attribute values
     * @param T data type for ` FusedBatchNormV3` output and operands
     * @param U data type for ` FusedBatchNormV3` output and operands
     * @return a new instance of FusedBatchNorm
     * @see org.tensorflow.op.NnOps.fusedBatchNorm
     * @param epsilon Sets the epsilon option.
     *
     * @param epsilon A small float number added to the variance of x.
     * @return this Options instance.
     * @param exponentialAvgFactor Sets the exponentialAvgFactor option.
     *
     * @param exponentialAvgFactor the exponentialAvgFactor option
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format for x and y. Either &quot;NHWC&quot; (default) or
     * &quot;NCHW&quot;.
     * @return this Options instance.
     * @param isTraining Sets the isTraining option.
     *
     * @param isTraining A bool value to indicate the operation is for training (default)
     *  or inference.
     * @return this Options instance.
     */
    public fun <T : TNumber, U : TNumber> fusedBatchNorm(
        x: Operand<T>,
        scale: Operand<U>,
        offset: Operand<U>,
        mean: Operand<U>,
        variance: Operand<U>,
        epsilon: Float? = null,
        exponentialAvgFactor: Float? = null,
        dataFormat: String? = null,
        isTraining: Boolean? = null
    ): FusedBatchNorm<T, U> = java.fusedBatchNorm<T, U>(
        x,
        scale,
        offset,
        mean,
        variance,
        *listOfNotNull(
            epsilon?.let { org.tensorflow.op.nn.FusedBatchNorm.epsilon(it) },
            exponentialAvgFactor?.let { org.tensorflow.op.nn.FusedBatchNorm.exponentialAvgFactor(it) },
            dataFormat?.let { org.tensorflow.op.nn.FusedBatchNorm.dataFormat(it) },
            isTraining?.let { org.tensorflow.op.nn.FusedBatchNorm.isTraining(it) }
        ).toTypedArray()
    )

    /**
     * Gradient for batch normalization.
     *  Note that the size of 4D Tensors are defined by either &quot;NHWC&quot; or
     * &quot;NCHW&quot;.
     *  The size of 1D Tensors matches the dimension C of the 4D Tensors.
     *
     * @param T data type for ` x_backprop` output
     * @param U data type for ` scale_backprop` output
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
     * @param reserveSpace3 When is_training is True, a 1D Tensor for some intermediate results to
     * be reused
     *  in gradient computation. When is_training is False, a dummy empty Tensor will be
     *  created.
     * @param options carries optional attribute values
     * @param T data type for ` FusedBatchNormGradV3` output and operands
     * @param U data type for ` FusedBatchNormGradV3` output and operands
     * @return a new instance of FusedBatchNormGrad
     * @see org.tensorflow.op.NnOps.fusedBatchNormGrad
     * @param epsilon Sets the epsilon option.
     *
     * @param epsilon A small float number added to the variance of x.
     * @return this Options instance.
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format for y_backprop, x, x_backprop.
     *  Either &quot;NHWC&quot; (default) or &quot;NCHW&quot;.
     * @return this Options instance.
     * @param isTraining Sets the isTraining option.
     *
     * @param isTraining A bool value to indicate the operation is for training (default)
     *  or inference.
     * @return this Options instance.
     */
    public fun <T : TNumber, U : TNumber> fusedBatchNormGrad(
        yBackprop: Operand<T>,
        x: Operand<T>,
        scale: Operand<TFloat32>,
        reserveSpace1: Operand<U>,
        reserveSpace2: Operand<U>,
        reserveSpace3: Operand<U>,
        epsilon: Float? = null,
        dataFormat: String? = null,
        isTraining: Boolean? = null
    ): FusedBatchNormGrad<T, U> = java.fusedBatchNormGrad<T, U>(
        yBackprop,
        x,
        scale,
        reserveSpace1,
        reserveSpace2,
        reserveSpace3,
        *listOfNotNull(
            epsilon?.let { org.tensorflow.op.nn.FusedBatchNormGrad.epsilon(it) },
            dataFormat?.let { org.tensorflow.op.nn.FusedBatchNormGrad.dataFormat(it) },
            isTraining?.let { org.tensorflow.op.nn.FusedBatchNormGrad.isTraining(it) }
        ).toTypedArray()
    )

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
     * @param T data type for ` output` output
     * @param input 4-D with shape ` [batch, in_height, in_width, in_channels]`.
     * @param paddings A two-column matrix specifying the padding sizes. The number of
     *  rows must be the same as the rank of ``` input```.
     * @param filter 4-D with shape
     *  ``` [filter_height, filter_width, in_channels, out_channels]```.
     * @param mode the value of the mode property
     * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
     *  of ``` input```. Must be in the same order as the dimension specified with format.
     * @param padding The type of padding algorithm to use.
     * @param T data type for ` FusedPadConv2D` output and operands
     * @return a new instance of FusedPadConv2d
     * @see org.tensorflow.op.NnOps.fusedPadConv2d
     */
    public fun <T : TNumber> fusedPadConv2d(
        input: Operand<T>,
        paddings: Operand<TInt32>,
        filter: Operand<T>,
        mode: String,
        strides: List<Long>,
        padding: String
    ): FusedPadConv2d<T> = java.fusedPadConv2d<T>(
        input,
        paddings,
        filter,
        mode,
        strides,
        padding
    )

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
     * @param T data type for ` output` output
     * @param input 4-D with shape ` [batch, in_height, in_width, in_channels]`.
     * @param sizeOutput A 1-D int32 Tensor of 2 elements: ` new_height, new_width`.  The
     *  new size for the images.
     * @param paddings A two-column matrix specifying the padding sizes. The number of
     *  rows must be the same as the rank of ``` input```.
     * @param filter 4-D with shape
     *  ``` [filter_height, filter_width, in_channels, out_channels]```.
     * @param mode the value of the mode property
     * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
     *  of ``` input```. Must be in the same order as the dimension specified with format.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` FusedResizeAndPadConv2D` output and operands
     * @return a new instance of FusedResizeAndPadConv2d
     * @see org.tensorflow.op.NnOps.fusedResizeAndPadConv2d
     * @param resizeAlignCorners Sets the resizeAlignCorners option.
     *
     * @param resizeAlignCorners If true, the centers of the 4 corner pixels of the input and output
     * tensors are
     *  aligned, preserving the values at the corner pixels. Defaults to false.
     * @return this Options instance.
     */
    public fun <T : TNumber> fusedResizeAndPadConv2d(
        input: Operand<T>,
        sizeOutput: Operand<TInt32>,
        paddings: Operand<TInt32>,
        filter: Operand<T>,
        mode: String,
        strides: List<Long>,
        padding: String,
        resizeAlignCorners: Boolean? = null
    ): FusedResizeAndPadConv2d<T> = java.fusedResizeAndPadConv2d<T>(
        input,
        sizeOutput,
        paddings,
        filter,
        mode,
        strides,
        padding,
        *listOfNotNull(
            resizeAlignCorners?.let { org.tensorflow.op.nn.FusedResizeAndPadConv2d.resizeAlignCorners(it) }
        ).toTypedArray()
    )

    /**
     * Says whether the targets are in the top ``` K``` predictions.
     *  This outputs a ``` batch_size``` bool array, an entry ``` out[i]``` is ``` true``` if the
     *  prediction for the target class is among the top ``` k``` predictions among
     *  all predictions for example ``` i```. Note that the behavior of ``` InTopK``` differs
     *  from the ``` TopK``` op in its handling of ties; if multiple classes have the
     *  same prediction value and straddle the top-``` k``` boundary, all of those
     *  classes are considered to be in the top ``` k```.
     *  More formally, let
     *  \(predictions_i\) be the predictions for all classes for example ``` i```,
     *  \(targets_i\) be the target class for example ``` i```,
     *  \(out_i\) be the output for example ``` i},
     *  $$out_i = predictions_{i, targets_i```
     *  \in TopKIncludingTies(predictions_i)$$
     *
     * @param predictions A ` batch_size` x ` classes` tensor.
     * @param targets A ` batch_size` vector of class ids.
     * @param k Number of top elements to look at for computing precision.
     * @param T data type for ` InTopKV2` output and operands
     * @return a new instance of InTopK
     * @see org.tensorflow.op.NnOps.inTopK
     */
    public fun <T : TNumber> inTopK(
        predictions: Operand<TFloat32>,
        targets: Operand<T>,
        k: Operand<T>
    ): InTopK = java.inTopK<T>(
        predictions,
        targets,
        k
    )

    /**
     * L2 Loss.
     *  Computes half the L2 norm of a tensor without the ``` sqrt```:
     *
     *  output = sum(t ** 2) / 2
     *
     *
     * @param T data type for ` output` output
     * @param t Typically 2-D, but may have any dimensions.
     * @param T data type for ` L2Loss` output and operands
     * @return a new instance of L2Loss
     * @see org.tensorflow.op.NnOps.l2Loss
     */
    public fun <T : TNumber> l2Loss(t: Operand<T>): L2Loss<T> = java.l2Loss<T>(
        t
    )

    /**
     * Computes rectified linear: ``` max(features, features * alpha)```.
     *
     * @param T data type for ` activations` output
     * @param features the features value
     * @param options carries optional attribute values
     * @param T data type for ` LeakyRelu` output and operands
     * @return a new instance of LeakyRelu
     * @see org.tensorflow.op.NnOps.leakyRelu
     * @param alpha Sets the alpha option.
     *
     * @param alpha the alpha option
     * @return this Options instance.
     */
    public fun <T : TNumber> leakyRelu(features: Operand<T>, alpha: Float? = null): LeakyRelu<T> =
        java.leakyRelu<T>(
            features,
            *listOfNotNull(
                alpha?.let { org.tensorflow.op.nn.LeakyRelu.alpha(it) }
            ).toTypedArray()
        )

    /**
     * Generates labels for candidate sampling with a learned unigram distribution.
     *  See explanations of candidate sampling and the data formats at
     *  go/candidate-sampling.
     *  For each batch, this op picks a single set of sampled candidate labels.
     *  The advantages of sampling candidates per-batch are simplicity and the
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
     * @param rangeMax The sampler will sample integers from the interval &#91;0, range_max).
     * @param options carries optional attribute values
     * @return a new instance of LearnedUnigramCandidateSampler
     * @see org.tensorflow.op.NnOps.learnedUnigramCandidateSampler
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun learnedUnigramCandidateSampler(
        trueClasses: Operand<TInt64>,
        numTrue: Long,
        numSampled: Long,
        unique: Boolean,
        rangeMax: Long,
        seed: Long? = null,
        seed2: Long? = null
    ): LearnedUnigramCandidateSampler = java.learnedUnigramCandidateSampler(
        trueClasses,
        numTrue,
        numSampled,
        unique,
        rangeMax,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.nn.LearnedUnigramCandidateSampler.seed(it) },
            seed2?.let { org.tensorflow.op.nn.LearnedUnigramCandidateSampler.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Local Response Normalization.
     *  The 4-D ``` input``` tensor is treated as a 3-D array of 1-D vectors (along the last
     *  dimension), and each vector is normalized independently.  Within a given vector,
     *  each component is divided by the weighted, squared sum of inputs within
     *  ``` depth_radius```.  In detail,
     *
     *  sqr_sum&#91;a, b, c, d] =
     *      sum(input&#91;a, b, c, d - depth_radius : d + depth_radius + 1] ** 2)
     *  output = input / (bias + alpha * sqr_sum) ** beta
     *
     *  For details, see  <a
     * href="http://papers.nips.cc/paper/4824-imagenet-classification-with-deep-convolutional-neural-networks">Krizhevsky
     * et al., ImageNet classification with deep
     *  convolutional neural networks (NIPS 2012)</a> .
     *
     * @param T data type for ` output` output
     * @param input 4-D.
     * @param options carries optional attribute values
     * @param T data type for ` LRN` output and operands
     * @return a new instance of LocalResponseNormalization
     * @see org.tensorflow.op.NnOps.localResponseNormalization
     * @param depthRadius Sets the depthRadius option.
     *
     * @param depthRadius 0-D.  Half-width of the 1-D normalization window.
     * @return this Options instance.
     * @param bias Sets the bias option.
     *
     * @param bias An offset (usually positive to avoid dividing by 0).
     * @return this Options instance.
     * @param alpha Sets the alpha option.
     *
     * @param alpha A scale factor, usually positive.
     * @return this Options instance.
     * @param beta Sets the beta option.
     *
     * @param beta An exponent.
     * @return this Options instance.
     */
    public fun <T : TNumber> localResponseNormalization(
        input: Operand<T>,
        depthRadius: Long? = null,
        bias: Float? = null,
        alpha: Float? = null,
        beta: Float? = null
    ): LocalResponseNormalization<T> = java.localResponseNormalization<T>(
        input,
        *listOfNotNull(
            depthRadius?.let { org.tensorflow.op.nn.LocalResponseNormalization.depthRadius(it) },
            bias?.let { org.tensorflow.op.nn.LocalResponseNormalization.bias(it) },
            alpha?.let { org.tensorflow.op.nn.LocalResponseNormalization.alpha(it) },
            beta?.let { org.tensorflow.op.nn.LocalResponseNormalization.beta(it) }
        ).toTypedArray()
    )

    /**
     * Computes log softmax activations.
     *  For each batch ``` i``` and class ``` j``` we have
     *
     *  logsoftmax&#91;i, j] = logits&#91;i, j] - log(sum(exp(logits&#91;i])))
     *
     *
     * @param T data type for ` logsoftmax` output
     * @param logits 2-D with shape ` [batch_size, num_classes]`.
     * @param T data type for ` LogSoftmax` output and operands
     * @return a new instance of LogSoftmax
     * @see org.tensorflow.op.NnOps.logSoftmax
     */
    public fun <T : TNumber> logSoftmax(logits: Operand<T>): LogSoftmax<T> = java.logSoftmax<T>(
        logits
    )

    /**
     * Performs max pooling on the input.
     *
     * @param T data type for ` output` output
     * @param input 4-D input to pool over.
     * @param ksize The size of the window for each dimension of the input tensor.
     * @param strides The stride of the sliding window for each dimension of the
     *  input tensor.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPoolV2` output and operands
     * @return a new instance of MaxPool
     * @see org.tensorflow.op.NnOps.maxPool
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> maxPool(
        input: Operand<T>,
        ksize: Operand<TInt32>,
        strides: Operand<TInt32>,
        padding: String,
        dataFormat: String? = null
    ): MaxPool<T> = java.maxPool<T>(
        input,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.MaxPool.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Performs 3D max pooling on the input.
     *
     * @param T data type for ` output` output
     * @param input Shape ` [batch, depth, rows, cols, channels]` tensor to pool over.
     * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
     *  the input tensor. Must have ``` ksize[0] = ksize[4] = 1```.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPool3D` output and operands
     * @return a new instance of MaxPool3d
     * @see org.tensorflow.op.NnOps.maxPool3d
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> maxPool3d(
        input: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null
    ): MaxPool3d<T> = java.maxPool3d<T>(
        input,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.MaxPool3d.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes gradients of 3D max pooling function.
     *
     * @param U data type for ` output` output
     * @param origInput The original input tensor.
     * @param origOutput The original output tensor.
     * @param grad Output backprop of shape ` [batch, depth, rows, cols, channels]`.
     * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
     *  the input tensor. Must have ``` ksize[0] = ksize[4] = 1```.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param U data type for ` MaxPool3DGrad` output and operands
     * @param T data type for ` MaxPool3DGrad` output and operands
     * @return a new instance of MaxPool3dGrad
     * @see org.tensorflow.op.NnOps.maxPool3dGrad
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     */
    public fun <U : TNumber, T : TNumber> maxPool3dGrad(
        origInput: Operand<T>,
        origOutput: Operand<T>,
        grad: Operand<U>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null
    ): MaxPool3dGrad<U> = java.maxPool3dGrad<U, T>(
        origInput,
        origOutput,
        grad,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.MaxPool3dGrad.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes second-order gradients of the maxpooling function.
     *
     * @param T data type for ` output` output
     * @param origInput The original input tensor.
     * @param origOutput The original output tensor.
     * @param grad Output backprop of shape ` [batch, depth, rows, cols, channels]`.
     * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
     *  the input tensor. Must have ``` ksize[0] = ksize[4] = 1```.
     * @param strides 1-D tensor of length 5. The stride of the sliding window for each
     *  dimension of ``` input```. Must have ``` strides[0] = strides[4] = 1```.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPool3DGradGrad` output and operands
     * @return a new instance of MaxPool3dGradGrad
     * @see org.tensorflow.op.NnOps.maxPool3dGradGrad
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     *  default format &quot;NDHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_depth, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     *  &#91;batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> maxPool3dGradGrad(
        origInput: Operand<T>,
        origOutput: Operand<T>,
        grad: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        dataFormat: String? = null
    ): MaxPool3dGradGrad<T> = java.maxPool3dGradGrad<T>(
        origInput,
        origOutput,
        grad,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.MaxPool3dGradGrad.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes gradients of the maxpooling function.
     *
     * @param T data type for ` output` output
     * @param origInput The original input tensor.
     * @param origOutput The original output tensor.
     * @param grad 4-D.  Gradients w.r.t. the output of ` max_pool`.
     * @param ksize The size of the window for each dimension of the input tensor.
     * @param strides The stride of the sliding window for each dimension of the
     *  input tensor.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPoolGradV2` output and operands
     * @return a new instance of MaxPoolGrad
     * @see org.tensorflow.op.NnOps.maxPoolGrad
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> maxPoolGrad(
        origInput: Operand<T>,
        origOutput: Operand<T>,
        grad: Operand<T>,
        ksize: Operand<TInt32>,
        strides: Operand<TInt32>,
        padding: String,
        dataFormat: String? = null
    ): MaxPoolGrad<T> = java.maxPoolGrad<T>(
        origInput,
        origOutput,
        grad,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.MaxPoolGrad.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes second-order gradients of the maxpooling function.
     *
     * @param T data type for ` output` output
     * @param origInput The original input tensor.
     * @param origOutput The original output tensor.
     * @param grad 4-D.  Gradients of gradients w.r.t. the input of ` max_pool`.
     * @param ksize The size of the window for each dimension of the input tensor.
     * @param strides The stride of the sliding window for each dimension of the
     *  input tensor.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPoolGradGradV2` output and operands
     * @return a new instance of MaxPoolGradGrad
     * @see org.tensorflow.op.NnOps.maxPoolGradGrad
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     *  default format &quot;NHWC&quot;, the data is stored in the order of:
     *  &#91;batch, in_height, in_width, in_channels].
     *  Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     *  &#91;batch, in_channels, in_height, in_width].
     * @return this Options instance.
     */
    public fun <T : TNumber> maxPoolGradGrad(
        origInput: Operand<T>,
        origOutput: Operand<T>,
        grad: Operand<T>,
        ksize: Operand<TInt32>,
        strides: Operand<TInt32>,
        padding: String,
        dataFormat: String? = null
    ): MaxPoolGradGrad<T> = java.maxPoolGradGrad<T>(
        origInput,
        origOutput,
        grad,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.MaxPoolGradGrad.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes second-order gradients of the maxpooling function.
     *
     * @param T data type for ` output` output
     * @param input The original input.
     * @param grad 4-D with shape ` [batch, height, width, channels]`.  Gradients w.r.t. the
     *  input of ``` max_pool```.
     * @param argmax The indices of the maximum values chosen for each output of ` max_pool`.
     * @param ksize The size of the window for each dimension of the input tensor.
     * @param strides The stride of the sliding window for each dimension of the
     *  input tensor.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPoolGradGradWithArgmax` output and operands
     * @return a new instance of MaxPoolGradGradWithArgmax
     * @see org.tensorflow.op.NnOps.maxPoolGradGradWithArgmax
     * @param includeBatchInIndex Sets the includeBatchInIndex option.
     *
     * @param includeBatchInIndex Whether to include batch dimension in flattened index of `
     * argmax`.
     * @return this Options instance.
     */
    public fun <T : TNumber> maxPoolGradGradWithArgmax(
        input: Operand<T>,
        grad: Operand<T>,
        argmax: Operand<out TNumber>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        includeBatchInIndex: Boolean? = null
    ): MaxPoolGradGradWithArgmax<T> = java.maxPoolGradGradWithArgmax<T>(
        input,
        grad,
        argmax,
        ksize,
        strides,
        padding,
        *listOfNotNull(
            includeBatchInIndex?.let {
                org.tensorflow.op.nn.MaxPoolGradGradWithArgmax.includeBatchInIndex(it)
            }
        ).toTypedArray()
    )

    /**
     * Performs max pooling on the input and outputs both max values and indices.
     *  The indices in ``` argmax``` are flattened, so that a maximum value at position
     *  ``` [b, y, x, c]``` becomes flattened index:
     *  ``` (y * width + x) * channels + c``` if ``` include_batch_in_index``` is False;
     *  ``` ((b * height + y) * width + x) * channels + c``` if ``` include_batch_in_index``` is
     * True.
     *  The indices returned are always in ``` [0, height) x [0, width)``` before flattening,
     *  even if padding is involved and the mathematically correct answer is outside
     *  (either negative or too large).  This is a bug, but fixing it is difficult to do
     *  in a safe backwards compatible way, especially due to flattening.
     *
     * @param T data type for ` output` output
     * @param U data type for ` argmax` output
     * @param input 4-D with shape ` [batch, height, width, channels]`.  Input to pool over.
     * @param ksize The size of the window for each dimension of the input tensor.
     * @param strides The stride of the sliding window for each dimension of the
     *  input tensor.
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPoolWithArgmax` output and operands
     * @return a new instance of MaxPoolWithArgmax, with default output types
     * @see org.tensorflow.op.NnOps.maxPoolWithArgmax
     */
    public fun <T : TNumber> maxPoolWithArgmax(
        input: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        options: Array<MaxPoolWithArgmax.Options>
    ): MaxPoolWithArgmax<T, TInt64> = java.maxPoolWithArgmax<T>(
        input,
        ksize,
        strides,
        padding,
        options
    )

    /**
     * Performs max pooling on the input and outputs both max values and indices.
     *  The indices in ``` argmax``` are flattened, so that a maximum value at position
     *  ``` [b, y, x, c]``` becomes flattened index:
     *  ``` (y * width + x) * channels + c``` if ``` include_batch_in_index``` is False;
     *  ``` ((b * height + y) * width + x) * channels + c``` if ``` include_batch_in_index``` is
     * True.
     *  The indices returned are always in ``` [0, height) x [0, width)``` before flattening,
     *  even if padding is involved and the mathematically correct answer is outside
     *  (either negative or too large).  This is a bug, but fixing it is difficult to do
     *  in a safe backwards compatible way, especially due to flattening.
     *
     * @param T data type for ` output` output
     * @param U data type for ` argmax` output
     * @param input 4-D with shape ` [batch, height, width, channels]`.  Input to pool over.
     * @param ksize The size of the window for each dimension of the input tensor.
     * @param strides The stride of the sliding window for each dimension of the
     *  input tensor.
     * @param Targmax the value of the Targmax property
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPoolWithArgmax` output and operands
     * @param U data type for ` MaxPoolWithArgmax` output and operands
     * @return a new instance of MaxPoolWithArgmax
     * @see org.tensorflow.op.NnOps.maxPoolWithArgmax
     * @param includeBatchInIndex Sets the includeBatchInIndex option.
     *
     * @param includeBatchInIndex Whether to include batch dimension in flattened index of `
     * argmax`.
     * @return this Options instance.
     */
    public fun <T : TNumber, U : TNumber> maxPoolWithArgmax(
        input: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        Targmax: Class<U>,
        padding: String,
        includeBatchInIndex: Boolean? = null
    ): MaxPoolWithArgmax<T, U> = java.maxPoolWithArgmax<T, U>(
        input,
        ksize,
        strides,
        Targmax,
        padding,
        *listOfNotNull(
            includeBatchInIndex?.let { org.tensorflow.op.nn.MaxPoolWithArgmax.includeBatchInIndex(it) }
        ).toTypedArray()
    )

    /**
     * Finds values of the ``` n```-th order statistic for the last dimension.
     *  If the input is a vector (rank-1), finds the entries which is the nth-smallest
     *  value in the vector and outputs their values as scalar tensor.
     *  For matrices (resp. higher rank input), computes the entries which is the
     *  nth-smallest value in each row (resp. vector along the last dimension). Thus,
     *
     *  values.shape = input.shape&#91;:-1]
     *
     *
     * @param T data type for ` values` output
     * @param input 1-D or higher with last dimension at least ` n+1`.
     * @param n 0-D. Position of sorted vector to select along the last dimension (along
     *  each row for matrices). Valid range of n is ``` [0, input.shape[:-1])```
     * @param options carries optional attribute values
     * @param T data type for ` NthElement` output and operands
     * @return a new instance of NthElement
     * @see org.tensorflow.op.NnOps.nthElement
     * @param reverse Sets the reverse option.
     *
     * @param reverse When set to True, find the nth-largest value in the vector and vice
     *  versa.
     * @return this Options instance.
     */
    public fun <T : TNumber> nthElement(
        input: Operand<T>,
        n: Operand<TInt32>,
        reverse: Boolean? = null
    ): NthElement<T> = java.nthElement<T>(
        input,
        n,
        *listOfNotNull(
            reverse?.let { org.tensorflow.op.nn.NthElement.reverse(it) }
        ).toTypedArray()
    )

    /**
     * Produces the average pool of the input tensor for quantized types.
     *
     * @param T data type for ` output` output
     * @param input 4-D with shape ` [batch, height, width, channels]`.
     * @param minInput The float value that the lowest quantized input value represents.
     * @param maxInput The float value that the highest quantized input value represents.
     * @param ksize The size of the window for each dimension of the input tensor.
     *  The length must be 4 to match the number of dimensions of the input.
     * @param strides The stride of the sliding window for each dimension of the input
     *  tensor.  The length must be 4 to match the number of dimensions of the input.
     * @param padding The type of padding algorithm to use.
     * @param T data type for ` QuantizedAvgPool` output and operands
     * @return a new instance of QuantizedAvgPool
     * @see org.tensorflow.op.NnOps.quantizedAvgPool
     */
    public fun <T : TNumber> quantizedAvgPool(
        input: Operand<T>,
        minInput: Operand<TFloat32>,
        maxInput: Operand<TFloat32>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String
    ): QuantizedAvgPool<T> = java.quantizedAvgPool<T>(
        input,
        minInput,
        maxInput,
        ksize,
        strides,
        padding
    )

    /**
     * Quantized Batch normalization.
     *  This op is deprecated and will be removed in the future. Prefer
     *  ``` tf.nn.batch_normalization```.
     *
     * @param U data type for ` result` output
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
     * @param U data type for ` QuantizedBatchNormWithGlobalNormalization` output and operands
     * @param T data type for ` QuantizedBatchNormWithGlobalNormalization` output and operands
     * @return a new instance of QuantizedBatchNormWithGlobalNormalization
     * @see org.tensorflow.op.NnOps.quantizedBatchNormWithGlobalNormalization
     */
    public fun <U : TNumber, T : TNumber> quantizedBatchNormWithGlobalNormalization(
        t: Operand<T>,
        tMin: Operand<TFloat32>,
        tMax: Operand<TFloat32>,
        m: Operand<T>,
        mMin: Operand<TFloat32>,
        mMax: Operand<TFloat32>,
        v: Operand<T>,
        vMin: Operand<TFloat32>,
        vMax: Operand<TFloat32>,
        beta: Operand<T>,
        betaMin: Operand<TFloat32>,
        betaMax: Operand<TFloat32>,
        gamma: Operand<T>,
        gammaMin: Operand<TFloat32>,
        gammaMax: Operand<TFloat32>,
        outType: Class<U>,
        varianceEpsilon: Float,
        scaleAfterNormalization: Boolean
    ): QuantizedBatchNormWithGlobalNormalization<U> =
        java.quantizedBatchNormWithGlobalNormalization<U, T>(
            t,
            tMin,
            tMax,
            m,
            mMin,
            mMax,
            v,
            vMin,
            vMax,
            beta,
            betaMin,
            betaMax,
            gamma,
            gammaMin,
            gammaMax,
            outType,
            varianceEpsilon,
            scaleAfterNormalization
        )

    /**
     * Adds Tensor 'bias' to Tensor 'input' for Quantized types.
     *  Broadcasts the values of bias on dimensions 0..N-2 of 'input'.
     *
     * @param V data type for ` output` output
     * @param input the input value
     * @param bias A 1D bias Tensor with size matching the last dimension of 'input'.
     * @param minInput The float value that the lowest quantized input value represents.
     * @param maxInput The float value that the highest quantized input value represents.
     * @param minBias The float value that the lowest quantized bias value represents.
     * @param maxBias The float value that the highest quantized bias value represents.
     * @param outType the value of the outType property
     * @param V data type for ` QuantizedBiasAdd` output and operands
     * @return a new instance of QuantizedBiasAdd
     * @see org.tensorflow.op.NnOps.quantizedBiasAdd
     */
    public fun <V : TNumber> quantizedBiasAdd(
        input: Operand<out TNumber>,
        bias: Operand<out TNumber>,
        minInput: Operand<TFloat32>,
        maxInput: Operand<TFloat32>,
        minBias: Operand<TFloat32>,
        maxBias: Operand<TFloat32>,
        outType: Class<V>
    ): QuantizedBiasAdd<V> = java.quantizedBiasAdd<V>(
        input,
        bias,
        minInput,
        maxInput,
        minBias,
        maxBias,
        outType
    )

    /**
     * Computes a 2D convolution given quantized 4D input and filter tensors.
     *  The inputs are quantized tensors where the lowest value represents the real
     *  number of the associated minimum, and the highest represents the maximum.
     *  This means that you can only interpret the quantized output in the same way, by
     *  taking the returned minimum and maximum values into account.
     *
     * @param V data type for ` output` output
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
     * @param V data type for ` QuantizedConv2D` output and operands
     * @return a new instance of QuantizedConv2d
     * @see org.tensorflow.op.NnOps.quantizedConv2d
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each
     *  filter element on that dimension. The dimension order is determined by the
     *  value of ``` data_format```, see above for details. Dilations in the batch and
     *  depth dimensions must be 1.
     * @return this Options instance.
     */
    public fun <V : TNumber> quantizedConv2d(
        input: Operand<out TNumber>,
        filter: Operand<out TNumber>,
        minInput: Operand<TFloat32>,
        maxInput: Operand<TFloat32>,
        minFilter: Operand<TFloat32>,
        maxFilter: Operand<TFloat32>,
        outType: Class<V>,
        strides: List<Long>,
        padding: String,
        dilations: List<Long>? = null
    ): QuantizedConv2d<V> = java.quantizedConv2d<V>(
        input,
        filter,
        minInput,
        maxInput,
        minFilter,
        maxFilter,
        outType,
        strides,
        padding,
        *listOfNotNull(
            dilations?.let { org.tensorflow.op.nn.QuantizedConv2d.dilations(it) }
        ).toTypedArray()
    )

    /**
     * Quantized Instance normalization.
     *
     * @param T data type for ` y` output
     * @param x A 4D input Tensor.
     * @param xMin The value represented by the lowest quantized input.
     * @param xMax The value represented by the highest quantized input.
     * @param options carries optional attribute values
     * @param T data type for ` QuantizedInstanceNorm` output and operands
     * @return a new instance of QuantizedInstanceNorm
     * @see org.tensorflow.op.NnOps.quantizedInstanceNorm
     * @param outputRangeGiven Sets the outputRangeGiven option.
     *
     * @param outputRangeGiven If True, ` given_y_min` and ` given_y_min`
     *  and ``` given_y_max``` are used as the output range. Otherwise,
     *  the implementation computes the output range.
     * @return this Options instance.
     * @param givenYMin Sets the givenYMin option.
     *
     * @param givenYMin Output in ` y_min` if ` output_range_given` is True.
     * @return this Options instance.
     * @param givenYMax Sets the givenYMax option.
     *
     * @param givenYMax Output in ` y_max` if ` output_range_given` is True.
     * @return this Options instance.
     * @param varianceEpsilon Sets the varianceEpsilon option.
     *
     * @param varianceEpsilon A small float number to avoid dividing by 0.
     * @return this Options instance.
     * @param minSeparation Sets the minSeparation option.
     *
     * @param minSeparation Minimum value of ` y_max - y_min`
     * @return this Options instance.
     */
    public fun <T : TNumber> quantizedInstanceNorm(
        x: Operand<T>,
        xMin: Operand<TFloat32>,
        xMax: Operand<TFloat32>,
        outputRangeGiven: Boolean? = null,
        givenYMin: Float? = null,
        givenYMax: Float? = null,
        varianceEpsilon: Float? = null,
        minSeparation: Float? = null
    ): QuantizedInstanceNorm<T> = java.quantizedInstanceNorm<T>(
        x,
        xMin,
        xMax,
        *listOfNotNull(
            outputRangeGiven?.let { org.tensorflow.op.nn.QuantizedInstanceNorm.outputRangeGiven(it) },
            givenYMin?.let { org.tensorflow.op.nn.QuantizedInstanceNorm.givenYMin(it) },
            givenYMax?.let { org.tensorflow.op.nn.QuantizedInstanceNorm.givenYMax(it) },
            varianceEpsilon?.let { org.tensorflow.op.nn.QuantizedInstanceNorm.varianceEpsilon(it) },
            minSeparation?.let { org.tensorflow.op.nn.QuantizedInstanceNorm.minSeparation(it) }
        ).toTypedArray()
    )

    /**
     * Produces the max pool of the input tensor for quantized types.
     *
     * @param T data type for ` output` output
     * @param input The 4D (batch x rows x cols x depth) Tensor to MaxReduce over.
     * @param minInput The float value that the lowest quantized input value represents.
     * @param maxInput The float value that the highest quantized input value represents.
     * @param ksize The size of the window for each dimension of the input tensor.
     *  The length must be 4 to match the number of dimensions of the input.
     * @param strides The stride of the sliding window for each dimension of the input
     *  tensor. The length must be 4 to match the number of dimensions of the input.
     * @param padding The type of padding algorithm to use.
     * @param T data type for ` QuantizedMaxPool` output and operands
     * @return a new instance of QuantizedMaxPool
     * @see org.tensorflow.op.NnOps.quantizedMaxPool
     */
    public fun <T : TNumber> quantizedMaxPool(
        input: Operand<T>,
        minInput: Operand<TFloat32>,
        maxInput: Operand<TFloat32>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String
    ): QuantizedMaxPool<T> = java.quantizedMaxPool<T>(
        input,
        minInput,
        maxInput,
        ksize,
        strides,
        padding
    )

    /**
     * Computes Quantized Rectified Linear: ``` max(features, 0)```
     *
     * @param U data type for ` activations` output
     * @param features the features value
     * @param minFeatures The float value that the lowest quantized value represents.
     * @param maxFeatures The float value that the highest quantized value represents.
     * @param outType the value of the outType property
     * @param U data type for ` QuantizedRelu` output and operands
     * @return a new instance of QuantizedRelu
     * @see org.tensorflow.op.NnOps.quantizedRelu
     */
    public fun <U : TNumber> quantizedRelu(
        features: Operand<out TNumber>,
        minFeatures: Operand<TFloat32>,
        maxFeatures: Operand<TFloat32>,
        outType: Class<U>
    ): QuantizedRelu<U> = java.quantizedRelu<U>(
        features,
        minFeatures,
        maxFeatures,
        outType
    )

    /**
     * Computes Quantized Rectified Linear 6: ``` min(max(features, 0), 6)```
     *
     * @param U data type for ` activations` output
     * @param features the features value
     * @param minFeatures The float value that the lowest quantized value represents.
     * @param maxFeatures The float value that the highest quantized value represents.
     * @param outType the value of the outType property
     * @param U data type for ` QuantizedRelu6` output and operands
     * @return a new instance of QuantizedRelu6
     * @see org.tensorflow.op.NnOps.quantizedRelu6
     */
    public fun <U : TNumber> quantizedRelu6(
        features: Operand<out TNumber>,
        minFeatures: Operand<TFloat32>,
        maxFeatures: Operand<TFloat32>,
        outType: Class<U>
    ): QuantizedRelu6<U> = java.quantizedRelu6<U>(
        features,
        minFeatures,
        maxFeatures,
        outType
    )

    /**
     * Computes Quantized Rectified Linear X: ``` min(max(features, 0), max_value)```
     *
     * @param U data type for ` activations` output
     * @param features the features value
     * @param maxValue the maxValue value
     * @param minFeatures The float value that the lowest quantized value represents.
     * @param maxFeatures The float value that the highest quantized value represents.
     * @param outType the value of the outType property
     * @param U data type for ` QuantizedReluX` output and operands
     * @return a new instance of QuantizedReluX
     * @see org.tensorflow.op.NnOps.quantizedReluX
     */
    public fun <U : TNumber> quantizedReluX(
        features: Operand<out TNumber>,
        maxValue: Operand<TFloat32>,
        minFeatures: Operand<TFloat32>,
        maxFeatures: Operand<TFloat32>,
        outType: Class<U>
    ): QuantizedReluX<U> = java.quantizedReluX<U>(
        features,
        maxValue,
        minFeatures,
        maxFeatures,
        outType
    )

    /**
     * Computes rectified linear: ``` max(features, 0)```.
     *  See: https://en.wikipedia.org/wiki/Rectifier_(neural_networks)
     *  Example usage:
     *  <blockquote>
     *  <blockquote>
     *  <blockquote>
     *  tf.nn.relu(&#91;-2., 0., -0., 3.]).numpy()
     *  array(&#91; 0.,  0., -0.,  3.], dtype=float32)
     *  </blockquote>
     *  </blockquote>
     *  </blockquote>
     *
     * @param T data type for ` activations` output
     * @param features the features value
     * @param T data type for ` Relu` output and operands
     * @return a new instance of Relu
     * @see org.tensorflow.op.NnOps.relu
     */
    public fun <T : TNumber> relu(features: Operand<T>): Relu<T> = java.relu<T>(
        features
    )

    /**
     * Computes rectified linear 6: ``` min(max(features, 0), 6)```.
     *
     * @param T data type for ` activations` output
     * @param features the features value
     * @param T data type for ` Relu6` output and operands
     * @return a new instance of Relu6
     * @see org.tensorflow.op.NnOps.relu6
     */
    public fun <T : TNumber> relu6(features: Operand<T>): Relu6<T> = java.relu6<T>(
        features
    )

    /**
     * Computes scaled exponential linear: ``` scale * alpha * (exp(features) - 1)```
     *  if &lt; 0, ``` scale * features``` otherwise.
     *  To be used together with
     *  ``` initializer = tf.variance_scaling_initializer(factor=1.0, mode='FAN_IN')```.
     *  For correct dropout, use ``` tf.contrib.nn.alpha_dropout```.
     *  See  <a href="https://arxiv.org/abs/1706.02515">Self-Normalizing Neural Networks</a>
     *
     * @param T data type for ` activations` output
     * @param features the features value
     * @param T data type for ` Selu` output and operands
     * @return a new instance of Selu
     * @see org.tensorflow.op.NnOps.selu
     */
    public fun <T : TNumber> selu(features: Operand<T>): Selu<T> = java.selu<T>(
        features
    )

    /**
     * Computes sigmoid cross entropy given <code>logits</code>.
     *
     *  Measures the probability error in discrete classification tasks in which each class is
     *  independent and not mutually exclusive. For instance, one could perform multilabel
     *  classification where a picture can contain both an elephant and a dog at the same time.
     *
     *  For brevity, let <code>x = logits</code>, <code>z = labels</code>. The logistic loss in
     *  pseudo-code is
     *
     *
     *  z * -log(sigmoid(x)) + (1 - z) * -log(1 - sigmoid(x))
     *   = z * -log(1 / (1 + exp(-x))) + (1 - z) * -log(exp(-x) / (1 + exp(-x)))
     *   = z * log(1 + exp(-x)) + (1 - z) * (-log(exp(-x)) + log(1 + exp(-x)))
     *   = z * log(1 + exp(-x)) + (1 - z) * (x + log(1 + exp(-x))
     *   = (1 - z) * x + log(1 + exp(-x))
     *   = x - x * z + log(1 + exp(-x))
     *
     *
     *  For <code>x < 0</code>, to avoid overflow in <code>exp(-x)</code>, we reformulate the above
     *
     *
     *  x - x * z + log(1 + exp(-x))
     *   = log(exp(x)) - x * z + log(1 + exp(-x))
     *   = - x * z + log(1 + exp(x))
     *
     *
     *  Hence, to ensure stability and avoid overflow, the implementation uses this equivalent
     *  formulation
     *
     *
     *    max(x, 0) - x * z + log(1 + exp(-abs(x)))
     *
     *
     *  </ode>logits</code> and <code>labels</code> must have the same type and shape.
     *
     *
     *
     * @param scope The TensorFlow scope
     * @param labels the labels
     * @param logits the logits of type float32 or float64
     * @param T the type of labels and logits
     * @return the component-wise logistic losses.
     * @throws IllegalArgumentException if logits' and labels' do not have the same shape
     * @see org.tensorflow.op.NnOps.sigmoidCrossEntropyWithLogits
     */
    public fun <T : TNumber> sigmoidCrossEntropyWithLogits(labels: Operand<T>, logits: Operand<T>):
        Operand<T> = java.sigmoidCrossEntropyWithLogits<T>(
        labels,
        logits
    )

    /**
     * Computes softmax activations.
     *  For each batch ``` i``` and class ``` j``` we have
     *
     *  $$softmax&#91;i, j] = exp(logits&#91;i, j]) / sum_j(exp(logits&#91;i, j]))$$
     *
     *
     * @param T data type for ` softmax` output
     * @param logits 2-D with shape ` [batch_size, num_classes]`.
     * @param T data type for ` Softmax` output and operands
     * @return a new instance of Softmax
     * @see org.tensorflow.op.NnOps.softmax
     */
    public fun <T : TNumber> softmax(logits: Operand<T>): Softmax<T> = java.softmax<T>(
        logits
    )

    /**
     * Computes softmax cross entropy between <code>logits</code> and <code>labels</code>.
     *
     *  Measures the probability error in discrete classification tasks in which the classes are
     *  mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image
     * is
     *  labeled with one and only one label: an image can be a dog or a truck, but not both.
     *
     *  <b>NOTE:</b>
     *
     *  While the classes are mutually exclusive, their probabilities need not be. All that is
     *  required is that each row of <code>labels</code> is a valid probability distribution. If
     * they
     *  are not, the computation of the gradient will be incorrect.
     *
     *  If using exclusive <code>labels</code> (wherein one and only one class is true at a time),
     *  see [ org.tensorflow.op.NnOps#sparseSoftmaxCrossEntropyWithLogits}
     *
     *  Usage:
     *
     *
     *    Operand&lt;TFloat32&gt; logits =
     *        tf.constant(new float&#91;]&#91;] {{4.0F, 2.0F, 1.0F}, {0.0F, 5.0F, 1.0F}} );
     *    Operand&lt;TFloat32&gt; labels =
     *        tf.constant(new float&#91;]&#91;] {{1.0F, 0.0F, 0.0F}, {0.0F, 0.8F, 0.2F}} );
     *    Operand&lt;TFloat32&gt; output =
     *        tf.nn.softmaxCrossEntropyWithLogits(labels, logits, -1);
     *    // output Shape = &#91;2]
     *    // dataType = FLOAT (1)
     *    // values { 0.169846, 0.824745 ]
     *
     *
     *  Backpropagation will happen into both <code>logits</code> and <code>labels</code>. To
     *  disallow backpropagation into <code>labels</code>, pass label tensors through <code>
     *  tf.stopGradient</code> before feeding it to this function.
     *
     * @param scope current scope
     * @param labels Each vector along the class dimension should hold a valid probability
     *      distribution e.g. for the case in which labels are of shape <code>&#91;batch_size,
     * num_classes]
     *      </code>, each row of <code>labels&#91;i]</code> must be a valid probability
     * distribution.
     * @param logits Per-label activations, typically a linear output. These activation energies
     * are
     *      interpreted as unnormalized log probabilities.
     * @param axis The class dimension. -1 is the last dimension.
     * @param T the number type of the operands
     * @return the softmax cross entropy loss. Its type is the same as <code>logits</code> and its
     *      shape is the same as <code>labels</code> except that it does not have the last dimension
     * of
     *      <code>labels</code>.
     * @see org.tensorflow.op.NnOps.softmaxCrossEntropyWithLogits
     */
    public fun <T : TNumber, U : TNumber> softmaxCrossEntropyWithLogits(
        labels: Operand<U>,
        logits: Operand<T>,
        axis: Int
    ): Operand<T> = java.softmaxCrossEntropyWithLogits<T, U>(
        labels,
        logits,
        axis
    )

    /**
     * Computes softsign: ``` features / (abs(features) + 1)```.
     *
     * @param T data type for ` activations` output
     * @param features the features value
     * @param T data type for ` Softsign` output and operands
     * @return a new instance of Softsign
     * @see org.tensorflow.op.NnOps.softsign
     */
    public fun <T : TNumber> softsign(features: Operand<T>): Softsign<T> = java.softsign<T>(
        features
    )

    /**
     * SpaceToBatch for 4-D tensors of type T.
     *  This is a legacy version of the more general SpaceToBatchND.
     *  Zero-pads and then rearranges (permutes) blocks of spatial data into batch.
     *  More specifically, this op outputs a copy of the input tensor where values from
     *  the ``` height``` and ``` width``` dimensions are moved to the ``` batch``` dimension.
     * After
     *  the zero-padding, both ``` height``` and ``` width``` of the input must be divisible by the
     *  block size.
     *
     * @param T data type for ` output` output
     * @param input 4-D with shape ` [batch, height, width, depth]`.
     * @param paddings 2-D tensor of non-negative integers with shape ` [2, 2]`. It specifies
     *  the padding of the input with zeros across the spatial dimensions as follows:
     *
     *    paddings = &#91;&#91;pad_top, pad_bottom], &#91;pad_left, pad_right]]
     *
     *  The effective spatial dimensions of the zero-padded input tensor will be:
     *
     *    height_pad = pad_top + height + pad_bottom
     *    width_pad = pad_left + width + pad_right
     *
     *  The attr ``` block_size``` must be greater than one. It indicates the block size.
     *  <ul>
     *  <li>Non-overlapping blocks of size ``` block_size x block size``` in the height and
     *  width dimensions are rearranged into the batch dimension at each location.</li>
     *  <li>The batch of the output tensor is ``` batch * block_size * block_size```.</li>
     *  <li>Both height_pad and width_pad must be divisible by block_size.</li>
     *  </ul>
     *  The shape of the output will be:
     *
     *  &#91;batch*block_size*block_size, height_pad/block_size, width_pad/block_size,
     *   depth]
     *
     *  Some examples:
     *  (1) For the following input of shape ``` [1, 2, 2, 1]``` and block_size of 2:
     *
     *  x = &#91;&#91;&#91;&#91;1], &#91;2]], &#91;&#91;3], &#91;4]]]]
     *
     *  The output tensor has shape ``` [4, 1, 1, 1]``` and value:
     *
     *  &#91;&#91;&#91;&#91;1]]], &#91;&#91;&#91;2]]], &#91;&#91;&#91;3]]], &#91;&#91;&#91;4]]]]
     *
     *  (2) For the following input of shape ``` [1, 2, 2, 3]``` and block_size of 2:
     *
     *  x = &#91;&#91;&#91;&#91;1, 2, 3], &#91;4, 5, 6]],
     *        &#91;&#91;7, 8, 9], &#91;10, 11, 12]]]]
     *
     *  The output tensor has shape ``` [4, 1, 1, 3]``` and value:
     *
     *  &#91;&#91;&#91;&#91;1, 2, 3]]], &#91;&#91;&#91;4, 5, 6]]], &#91;&#91;&#91;7, 8, 9]]],
     * &#91;&#91;&#91;10, 11, 12]]]]
     *
     *  (3) For the following input of shape ``` [1, 4, 4, 1]``` and block_size of 2:
     *
     *  x = &#91;&#91;&#91;&#91;1],   &#91;2],  &#91;3],  &#91;4]],
     *        &#91;&#91;5],   &#91;6],  &#91;7],  &#91;8]],
     *        &#91;&#91;9],  &#91;10], &#91;11],  &#91;12]],
     *        &#91;&#91;13], &#91;14], &#91;15],  &#91;16]]]]
     *
     *  The output tensor has shape ``` [4, 2, 2, 1]``` and value:
     *
     *  x = &#91;&#91;&#91;&#91;1], &#91;3]], &#91;&#91;9], &#91;11]]],
     *       &#91;&#91;&#91;2], &#91;4]], &#91;&#91;10], &#91;12]]],
     *       &#91;&#91;&#91;5], &#91;7]], &#91;&#91;13], &#91;15]]],
     *       &#91;&#91;&#91;6], &#91;8]], &#91;&#91;14], &#91;16]]]]
     *
     *  (4) For the following input of shape ``` [2, 2, 4, 1]``` and block_size of 2:
     *
     *  x = &#91;&#91;&#91;&#91;1],   &#91;2],  &#91;3],  &#91;4]],
     *        &#91;&#91;5],   &#91;6],  &#91;7],  &#91;8]]],
     *       &#91;&#91;&#91;9],  &#91;10], &#91;11],  &#91;12]],
     *        &#91;&#91;13], &#91;14], &#91;15],  &#91;16]]]]
     *
     *  The output tensor has shape ``` [8, 1, 2, 1]``` and value:
     *
     *  x = &#91;&#91;&#91;&#91;1], &#91;3]]], &#91;&#91;&#91;9], &#91;11]]], &#91;&#91;&#91;2],
     * &#91;4]]], &#91;&#91;&#91;10], &#91;12]]],
     *       &#91;&#91;&#91;5], &#91;7]]], &#91;&#91;&#91;13], &#91;15]]], &#91;&#91;&#91;6],
     * &#91;8]]], &#91;&#91;&#91;14], &#91;16]]]]
     *
     *  Among others, this operation is useful for reducing atrous convolution into
     *  regular convolution.
     * @param blockSize the value of the blockSize property
     * @param T data type for ` SpaceToBatch` output and operands
     * @return a new instance of SpaceToBatch
     * @see org.tensorflow.op.NnOps.spaceToBatch
     */
    public fun <T : TType> spaceToBatch(
        input: Operand<T>,
        paddings: Operand<out TNumber>,
        blockSize: Long
    ): SpaceToBatch<T> = java.spaceToBatch<T>(
        input,
        paddings,
        blockSize
    )

    /**
     * SpaceToDepth for tensors of type T.
     *  Rearranges blocks of spatial data, into depth. More specifically,
     *  this op outputs a copy of the input tensor where values from the ``` height```
     *  and ``` width``` dimensions are moved to the ``` depth``` dimension.
     *  The attr ``` block_size``` indicates the input block size.
     *  <ul>
     *  <li>Non-overlapping blocks of size ``` block_size x block size``` are rearranged
     *  into depth at each location.</li>
     *  <li>The depth of the output tensor is ``` block_size * block_size * input_depth```.</li>
     *  <li>The Y, X coordinates within each block of the input become the high order
     *  component of the output channel index.</li>
     *  <li>The input tensor's height and width must be divisible by block_size.</li>
     *  </ul>
     *  The ``` data_format``` attr specifies the layout of the input and output tensors
     *  with the following options:
     *  &quot;NHWC&quot;: ``` [ batch, height, width, channels ]```
     *  &quot;NCHW&quot;: ``` [ batch, channels, height, width ]```
     *  &quot;NCHW_VECT_C&quot;:
     *  ``` qint8 [ batch, channels / 4, height, width, 4 ]```
     *  It is useful to consider the operation as transforming a 6-D Tensor.
     *  e.g. for data_format = NHWC,
     *  Each element in the input tensor can be specified via 6 coordinates,
     *  ordered by decreasing memory layout significance as:
     *  n,oY,bY,oX,bX,iC  (where n=batch index, oX, oY means X or Y coordinates
     *  within the output image, bX, bY means coordinates
     *  within the input block, iC means input channels).
     *  The output would be a transpose to the following layout:
     *  n,oY,oX,bY,bX,iC
     *  This operation is useful for resizing the activations between convolutions
     *  (but keeping all data), e.g. instead of pooling. It is also useful for training
     *  purely convolutional models.
     *  For example, given an input of shape ``` [1, 2, 2, 1]```, data_format = &quot;NHWC&quot;
     * and
     *  block_size = 2:
     *
     *  x = &#91;&#91;&#91;&#91;1], &#91;2]],
     *        &#91;&#91;3], &#91;4]]]]
     *
     *  This operation will output a tensor of shape ``` [1, 1, 1, 4]```:
     *
     *  &#91;&#91;&#91;&#91;1, 2, 3, 4]]]]
     *
     *  Here, the input has a batch of 1 and each batch element has shape ``` [2, 2, 1]```,
     *  the corresponding output will have a single element (i.e. width and height are
     *  both 1) and will have a depth of 4 channels (1 * block_size * block_size).
     *  The output element shape is ``` [1, 1, 4]```.
     *  For an input tensor with larger depth, here of shape ``` [1, 2, 2, 3]```, e.g.
     *
     *  x = &#91;&#91;&#91;&#91;1, 2, 3], &#91;4, 5, 6]],
     *        &#91;&#91;7, 8, 9], &#91;10, 11, 12]]]]
     *
     *  This operation, for block_size of 2, will return the following tensor of shape
     *  ``` [1, 1, 1, 12]```
     *
     *  &#91;&#91;&#91;&#91;1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]]]
     *
     *  Similarly, for the following input of shape ``` [1 4 4 1]```, and a block size of 2:
     *
     *  x = &#91;&#91;&#91;&#91;1],   &#91;2],  &#91;5],  &#91;6]],
     *        &#91;&#91;3],   &#91;4],  &#91;7],  &#91;8]],
     *        &#91;&#91;9],  &#91;10], &#91;13],  &#91;14]],
     *        &#91;&#91;11], &#91;12], &#91;15],  &#91;16]]]]
     *
     *  the operator will return the following tensor of shape ``` [1 2 2 4]```:
     *
     *  x = &#91;&#91;&#91;&#91;1, 2, 3, 4],
     *         &#91;5, 6, 7, 8]],
     *        &#91;&#91;9, 10, 11, 12],
     *         &#91;13, 14, 15, 16]]]]
     *
     *
     * @param T data type for ` output` output
     * @param input the input value
     * @param blockSize The size of the spatial block.
     * @param options carries optional attribute values
     * @param T data type for ` SpaceToDepth` output and operands
     * @return a new instance of SpaceToDepth
     * @see org.tensorflow.op.NnOps.spaceToDepth
     * @param dataFormat Sets the dataFormat option.
     *
     * @param dataFormat the dataFormat option
     * @return this Options instance.
     */
    public fun <T : TType> spaceToDepth(
        input: Operand<T>,
        blockSize: Long,
        dataFormat: String? = null
    ): SpaceToDepth<T> = java.spaceToDepth<T>(
        input,
        blockSize,
        *listOfNotNull(
            dataFormat?.let { org.tensorflow.op.nn.SpaceToDepth.dataFormat(it) }
        ).toTypedArray()
    )

    /**
     * Computes sparse softmax cross entropy between <code>logits</code> and <code>labels</code>.
     *
     *  Measures the probability error in discrete classification tasks in which the classes are
     *  mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image
     * is
     *  labeled with one and only one label: an image can be a dog or a truck, but not both.
     *
     *  <b>NOTE:</b>
     *
     *  For this operation, the probability of a given label is considered exclusive. That is, soft
     *  classes are not allowed, and the <code>labels</code> vector must provide a single specific
     *  index for the true class for each row of <code>logits</code> (each minibatch entry). For
     * soft
     *  softmax classification with a probability distribution for each entry, [
     *  org.tensorflow.op.NnOps#softmaxCrossEntropyWithLogits].
     *
     *  <b>WARNING:</b>
     *
     *  This op expects unscaled logits, since it performs a <code>softmax</code> on <code>logits
     *  </code> internally for efficiency. Do not call this op with the output of
     * <code>softmax</code>,
     *  as it will produce incorrect results.
     *
     *  A common use case is to have logits of shape <code>&#91;batchSize, numClasses]</code> and
     * have
     *  labels of shape <code>&#91;batchSize]</code>, but higher dimensions are supported, in which
     * case
     *  the <code>dim</code>-th dimension is assumed to be of size <code>numClasses</code>. <code>
     *  logits</code> must have the <cod>dataType</cod> of <code>TFloat16</code>,
     * <code>TFloat32</code>
     *  , or <code>TFloat64</code>, and <code>labels</code> must have the dtype of
     * <code>TInt32</code>
     *  or <code>TInt64</code>.
     *
     * @param scope current scope
     * @param labels <code>Tensor</code> of shape <code>&#91;d_0, d_1, ..., d_{r-1}]</code> (where
     * <code>r
     *      </code> is rank of <code>labels</code> and result) and the dataType is
     * <code>TInt32</code>
     *      or <code>TInt64</code>. Each entry in <code>labels</code> must be an index in
     * <code>&#91;0,
     *      numClasses)</code>. Other values will raise an exception when this op is run on CPU,
     * and
     *      return <code>NaN</code> for corresponding loss and gradient rows on GPU.
     * @param logits Per-label activations (typically a linear output) of shape <code>&#91;d_0, d_1,
     * ...,
     *      d_{r-1}, numClasses]</code> and dataType of <code>TFloat16</code>,
     * <code>TFloat32</code>,
     *      or <code>TFloat64</code>. These activation energies are interpreted as unnormalized log
     *      probabilities.
     * @return A <code>Tensor</code> of the same shape as <code>labels</code> and of the same type
     * as
     *      <code>logits</code> with the softmax cross entropy loss.
     * @throws IllegalArgumentException If logits are scalars (need to have rank >= 1) or if the
     * rank
     *      of the labels is not equal to the rank of the logits minus one.
     * @see org.tensorflow.op.NnOps.sparseSoftmaxCrossEntropyWithLogits
     */
    public fun <T : TNumber, U : TNumber> sparseSoftmaxCrossEntropyWithLogits(
        labels: Operand<T>,
        logits: Operand<U>
    ): Operand<*> = java.sparseSoftmaxCrossEntropyWithLogits<T, U>(
        labels,
        logits
    )

    /**
     * Finds values and indices of the ``` k``` largest elements for the last dimension.
     *  If the input is a vector (rank-1), finds the ``` k``` largest entries in the vector
     *  and outputs their values and indices as vectors.  Thus ``` values[j]``` is the
     *  ``` j```-th largest entry in ``` input```, and its index is ``` indices[j]```.
     *  For matrices (resp. higher rank input), computes the top ``` k``` entries in each
     *  row (resp. vector along the last dimension).  Thus,
     *
     *  values.shape = indices.shape = input.shape&#91;:-1] + &#91;k]
     *
     *  If two elements are equal, the lower-index element appears first.
     *
     * @param T data type for ` values` output
     * @param input 1-D or higher with last dimension at least ` k`.
     * @param k 0-D.  Number of top elements to look for along the last dimension (along each
     *  row for matrices).
     * @param options carries optional attribute values
     * @param T data type for ` TopKV2` output and operands
     * @return a new instance of TopK
     * @see org.tensorflow.op.NnOps.topK
     * @param sorted Sets the sorted option.
     *
     * @param sorted If true the resulting ` k` elements will be sorted by the values in
     *  descending order.
     * @return this Options instance.
     */
    public fun <T : TNumber> topK(
        input: Operand<T>,
        k: Operand<TInt32>,
        sorted: Boolean? = null
    ): TopK<T> = java.topK<T>(
        input,
        k,
        *listOfNotNull(
            sorted?.let { org.tensorflow.op.nn.TopK.sorted(it) }
        ).toTypedArray()
    )

    /**
     * Computes size of weights that can be used by a Cudnn RNN model.
     *  Return the params size that can be used by the Cudnn RNN model. Subsequent
     *  weight allocation and initialization should use this size.
     *  num_layers: Specifies the number of layers in the RNN model.
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
     * @param T data type for ` params_size` output
     * @param numLayers the numLayers value
     * @param numUnits the numUnits value
     * @param inputSize the inputSize value
     * @param T the value of the T property
     * @param S the value of the S property
     * @param options carries optional attribute values
     * @param T data type for ` CudnnRNNParamsSize` output and operands
     * @param U data type for ` CudnnRNNParamsSize` output and operands
     * @return a new instance of CudnnRnnParamsSize
     * @see org.tensorflow.op.NnOps.cudnnRnnParamsSize
     * @param rnnMode Sets the rnnMode option.
     *
     * @param rnnMode the rnnMode option
     * @return this Options instance.
     * @param inputMode Sets the inputMode option.
     *
     * @param inputMode the inputMode option
     * @return this Options instance.
     * @param direction Sets the direction option.
     *
     * @param direction the direction option
     * @return this Options instance.
     * @param dropout Sets the dropout option.
     *
     * @param dropout the dropout option
     * @return this Options instance.
     * @param seed Sets the seed option.
     *
     * @param seed the seed option
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 the seed2 option
     * @return this Options instance.
     * @param numProj Sets the numProj option.
     *
     * @param numProj the numProj option
     * @return this Options instance.
     */
    @JvmName("cudnnRnnParamsSizeReified")
    public inline fun <reified T : TNumber, reified U : TNumber> cudnnRnnParamsSize(
        numLayers: Operand<TInt32>,
        numUnits: Operand<TInt32>,
        inputSize: Operand<TInt32>,
        rnnMode: String? = null,
        inputMode: String? = null,
        direction: String? = null,
        dropout: Float? = null,
        seed: Long? = null,
        seed2: Long? = null,
        numProj: Long? = null
    ): CudnnRnnParamsSize<T> = cudnnRnnParamsSize<T, U>(
        numLayers, numUnits, inputSize,
        U::class.java, T::class.java, rnnMode, inputMode, direction, dropout, seed, seed2,
        numProj
    )

    /**
     * Performs max pooling on the input and outputs both max values and indices.
     *  The indices in ``` argmax``` are flattened, so that a maximum value at position
     *  ``` [b, y, x, c]``` becomes flattened index:
     *  ``` (y * width + x) * channels + c``` if ``` include_batch_in_index``` is False;
     *  ``` ((b * height + y) * width + x) * channels + c``` if ``` include_batch_in_index``` is
     * True.
     *  The indices returned are always in ``` [0, height) x [0, width)``` before flattening,
     *  even if padding is involved and the mathematically correct answer is outside
     *  (either negative or too large).  This is a bug, but fixing it is difficult to do
     *  in a safe backwards compatible way, especially due to flattening.
     *
     * @param T data type for ` output` output
     * @param U data type for ` argmax` output
     * @param input 4-D with shape ` [batch, height, width, channels]`.  Input to pool over.
     * @param ksize The size of the window for each dimension of the input tensor.
     * @param strides The stride of the sliding window for each dimension of the
     *  input tensor.
     * @param Targmax the value of the Targmax property
     * @param padding The type of padding algorithm to use.
     * @param options carries optional attribute values
     * @param T data type for ` MaxPoolWithArgmax` output and operands
     * @param U data type for ` MaxPoolWithArgmax` output and operands
     * @return a new instance of MaxPoolWithArgmax
     * @see org.tensorflow.op.NnOps.maxPoolWithArgmax
     * @param includeBatchInIndex Sets the includeBatchInIndex option.
     *
     * @param includeBatchInIndex Whether to include batch dimension in flattened index of `
     * argmax`.
     * @return this Options instance.
     */
    @JvmName("maxPoolWithArgmaxReified")
    public inline fun <T : TNumber, reified U : TNumber> maxPoolWithArgmax(
        input: Operand<T>,
        ksize: List<Long>,
        strides: List<Long>,
        padding: String,
        includeBatchInIndex: Boolean? = null
    ): MaxPoolWithArgmax<T, U> = maxPoolWithArgmax<T, U>(
        input, ksize, strides, U::class.java,
        padding, includeBatchInIndex
    )

    /**
     * Quantized Batch normalization.
     *  This op is deprecated and will be removed in the future. Prefer
     *  ``` tf.nn.batch_normalization```.
     *
     * @param U data type for ` result` output
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
     * @param U data type for ` QuantizedBatchNormWithGlobalNormalization` output and operands
     * @param T data type for ` QuantizedBatchNormWithGlobalNormalization` output and operands
     * @return a new instance of QuantizedBatchNormWithGlobalNormalization
     * @see org.tensorflow.op.NnOps.quantizedBatchNormWithGlobalNormalization
     */
    @JvmName("quantizedBatchNormWithGlobalNormalizationReified")
    public inline fun <reified U : TNumber, T : TNumber> quantizedBatchNormWithGlobalNormalization(
        t: Operand<T>,
        tMin: Operand<TFloat32>,
        tMax: Operand<TFloat32>,
        m: Operand<T>,
        mMin: Operand<TFloat32>,
        mMax: Operand<TFloat32>,
        v: Operand<T>,
        vMin: Operand<TFloat32>,
        vMax: Operand<TFloat32>,
        beta: Operand<T>,
        betaMin: Operand<TFloat32>,
        betaMax: Operand<TFloat32>,
        gamma: Operand<T>,
        gammaMin: Operand<TFloat32>,
        gammaMax: Operand<TFloat32>,
        varianceEpsilon: Float,
        scaleAfterNormalization: Boolean
    ): QuantizedBatchNormWithGlobalNormalization<U> = quantizedBatchNormWithGlobalNormalization<U,
        T>(
        t, tMin, tMax, m, mMin, mMax, v, vMin, vMax, beta, betaMin, betaMax, gamma, gammaMin,
        gammaMax, U::class.java, varianceEpsilon, scaleAfterNormalization
    )

    /**
     * Adds Tensor 'bias' to Tensor 'input' for Quantized types.
     *  Broadcasts the values of bias on dimensions 0..N-2 of 'input'.
     *
     * @param V data type for ` output` output
     * @param input the input value
     * @param bias A 1D bias Tensor with size matching the last dimension of 'input'.
     * @param minInput The float value that the lowest quantized input value represents.
     * @param maxInput The float value that the highest quantized input value represents.
     * @param minBias The float value that the lowest quantized bias value represents.
     * @param maxBias The float value that the highest quantized bias value represents.
     * @param outType the value of the outType property
     * @param V data type for ` QuantizedBiasAdd` output and operands
     * @return a new instance of QuantizedBiasAdd
     * @see org.tensorflow.op.NnOps.quantizedBiasAdd
     */
    @JvmName("quantizedBiasAddReified")
    public inline fun <reified V : TNumber> quantizedBiasAdd(
        input: Operand<out TNumber>,
        bias: Operand<out TNumber>,
        minInput: Operand<TFloat32>,
        maxInput: Operand<TFloat32>,
        minBias: Operand<TFloat32>,
        maxBias: Operand<TFloat32>
    ): QuantizedBiasAdd<V> = quantizedBiasAdd<V>(
        input, bias, minInput, maxInput, minBias, maxBias,
        V::class.java
    )

    /**
     * Computes a 2D convolution given quantized 4D input and filter tensors.
     *  The inputs are quantized tensors where the lowest value represents the real
     *  number of the associated minimum, and the highest represents the maximum.
     *  This means that you can only interpret the quantized output in the same way, by
     *  taking the returned minimum and maximum values into account.
     *
     * @param V data type for ` output` output
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
     * @param V data type for ` QuantizedConv2D` output and operands
     * @return a new instance of QuantizedConv2d
     * @see org.tensorflow.op.NnOps.quantizedConv2d
     * @param dilations Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     *  ``` input```. If set to k &gt; 1, there will be k-1 skipped cells between each
     *  filter element on that dimension. The dimension order is determined by the
     *  value of ``` data_format```, see above for details. Dilations in the batch and
     *  depth dimensions must be 1.
     * @return this Options instance.
     */
    @JvmName("quantizedConv2dReified")
    public inline fun <reified V : TNumber> quantizedConv2d(
        input: Operand<out TNumber>,
        filter: Operand<out TNumber>,
        minInput: Operand<TFloat32>,
        maxInput: Operand<TFloat32>,
        minFilter: Operand<TFloat32>,
        maxFilter: Operand<TFloat32>,
        strides: List<Long>,
        padding: String,
        dilations: List<Long>? = null
    ): QuantizedConv2d<V> = quantizedConv2d<V>(
        input, filter, minInput, maxInput, minFilter,
        maxFilter, V::class.java, strides, padding, dilations
    )

    /**
     * Computes Quantized Rectified Linear: ``` max(features, 0)```
     *
     * @param U data type for ` activations` output
     * @param features the features value
     * @param minFeatures The float value that the lowest quantized value represents.
     * @param maxFeatures The float value that the highest quantized value represents.
     * @param outType the value of the outType property
     * @param U data type for ` QuantizedRelu` output and operands
     * @return a new instance of QuantizedRelu
     * @see org.tensorflow.op.NnOps.quantizedRelu
     */
    @JvmName("quantizedReluReified")
    public inline fun <reified U : TNumber> quantizedRelu(
        features: Operand<out TNumber>,
        minFeatures: Operand<TFloat32>,
        maxFeatures: Operand<TFloat32>
    ): QuantizedRelu<U> = quantizedRelu<U>(features, minFeatures, maxFeatures, U::class.java)

    /**
     * Computes Quantized Rectified Linear 6: ``` min(max(features, 0), 6)```
     *
     * @param U data type for ` activations` output
     * @param features the features value
     * @param minFeatures The float value that the lowest quantized value represents.
     * @param maxFeatures The float value that the highest quantized value represents.
     * @param outType the value of the outType property
     * @param U data type for ` QuantizedRelu6` output and operands
     * @return a new instance of QuantizedRelu6
     * @see org.tensorflow.op.NnOps.quantizedRelu6
     */
    @JvmName("quantizedRelu6Reified")
    public inline fun <reified U : TNumber> quantizedRelu6(
        features: Operand<out TNumber>,
        minFeatures: Operand<TFloat32>,
        maxFeatures: Operand<TFloat32>
    ): QuantizedRelu6<U> = quantizedRelu6<U>(features, minFeatures, maxFeatures, U::class.java)

    /**
     * Computes Quantized Rectified Linear X: ``` min(max(features, 0), max_value)```
     *
     * @param U data type for ` activations` output
     * @param features the features value
     * @param maxValue the maxValue value
     * @param minFeatures The float value that the lowest quantized value represents.
     * @param maxFeatures The float value that the highest quantized value represents.
     * @param outType the value of the outType property
     * @param U data type for ` QuantizedReluX` output and operands
     * @return a new instance of QuantizedReluX
     * @see org.tensorflow.op.NnOps.quantizedReluX
     */
    @JvmName("quantizedReluXReified")
    public inline fun <reified U : TNumber> quantizedReluX(
        features: Operand<out TNumber>,
        maxValue: Operand<TFloat32>,
        minFeatures: Operand<TFloat32>,
        maxFeatures: Operand<TFloat32>
    ): QuantizedReluX<U> = quantizedReluX<U>(
        features, maxValue, minFeatures, maxFeatures,
        U::class.java
    )
}
