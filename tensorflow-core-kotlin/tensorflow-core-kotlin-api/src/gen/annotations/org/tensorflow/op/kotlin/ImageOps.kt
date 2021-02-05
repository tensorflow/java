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
import org.tensorflow.op.image.AdjustContrast
import org.tensorflow.op.image.AdjustHue
import org.tensorflow.op.image.AdjustSaturation
import org.tensorflow.op.image.CombinedNonMaxSuppression
import org.tensorflow.op.image.CropAndResize
import org.tensorflow.op.image.CropAndResizeGradBoxes
import org.tensorflow.op.image.CropAndResizeGradImage
import org.tensorflow.op.image.DecodeAndCropJpeg
import org.tensorflow.op.image.DecodeBmp
import org.tensorflow.op.image.DecodeGif
import org.tensorflow.op.image.DecodeJpeg
import org.tensorflow.op.image.DecodePng
import org.tensorflow.op.image.DrawBoundingBoxes
import org.tensorflow.op.image.EncodeJpeg
import org.tensorflow.op.image.EncodeJpegVariableQuality
import org.tensorflow.op.image.EncodePng
import org.tensorflow.op.image.ExtractImagePatches
import org.tensorflow.op.image.ExtractJpegShape
import org.tensorflow.op.image.HsvToRgb
import org.tensorflow.op.image.NonMaxSuppression
import org.tensorflow.op.image.NonMaxSuppressionWithOverlaps
import org.tensorflow.op.image.QuantizedResizeBilinear
import org.tensorflow.op.image.RandomCrop
import org.tensorflow.op.image.ResizeArea
import org.tensorflow.op.image.ResizeBicubic
import org.tensorflow.op.image.ResizeBilinear
import org.tensorflow.op.image.ResizeNearestNeighbor
import org.tensorflow.op.image.RgbToHsv
import org.tensorflow.op.image.SampleDistortedBoundingBox
import org.tensorflow.op.image.ScaleAndTranslate
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.TUint8
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `image` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class ImageOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.ImageOps = ops.java.image

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Adjust the contrast of one or more images.
     *  
     *  `images` is a tensor of at least 3 dimensions.  The last 3 dimensions are
     *  interpreted as `&#91;height, width, channels]`.  The other dimensions only
     *  represent a collection of images, such as `&#91;batch, height, width, channels].`
     *  
     *  Contrast is adjusted independently for each channel of each image.
     *  
     *  For each channel, the Op first computes the mean of the image pixels in the
     *  channel and then adjusts each component of each pixel to
     *  `(x - mean) * contrast_factor + mean`.
     * 
     * @param T data type for ` output()` output
     * @param images Images to adjust.  At least 3-D.
     * @param contrastFactor A float multiplier for adjusting contrast.
     * @return a new instance of AdjustContrast
     * @see org.tensorflow.op.ImageOps.adjustContrast
     */
    public fun <T : TNumber> adjustContrast(images: Operand<T>, contrastFactor: Operand<TFloat32>):
            AdjustContrast<T> = java.adjustContrast<T>(    
        images,
        contrastFactor
        )

    /**
     * Adjust the hue of one or more images.
     *  
     *  `images` is a tensor of at least 3 dimensions.  The last dimension is
     *  interpreted as channels, and must be three.
     *  
     *  The input image is considered in the RGB colorspace. Conceptually, the RGB
     *  colors are first mapped into HSV. A delta is then applied all the hue values,
     *  and then remapped back to RGB colorspace.
     * 
     * @param T data type for ` output()` output
     * @param images Images to adjust.  At least 3-D.
     * @param delta A float delta to add to the hue.
     * @return a new instance of AdjustHue
     * @see org.tensorflow.op.ImageOps.adjustHue
     */
    public fun <T : TNumber> adjustHue(images: Operand<T>, delta: Operand<TFloat32>): AdjustHue<T> =
            java.adjustHue<T>(    
        images,
        delta
        )

    /**
     * Adjust the saturation of one or more images.
     *  
     *  `images` is a tensor of at least 3 dimensions.  The last dimension is
     *  interpreted as channels, and must be three.
     *  
     *  The input image is considered in the RGB colorspace. Conceptually, the RGB
     *  colors are first mapped into HSV. A scale is then applied all the saturation
     *  values, and then remapped back to RGB colorspace.
     * 
     * @param T data type for ` output()` output
     * @param images Images to adjust.  At least 3-D.
     * @param scale A float scale to add to the saturation.
     * @return a new instance of AdjustSaturation
     * @see org.tensorflow.op.ImageOps.adjustSaturation
     */
    public fun <T : TNumber> adjustSaturation(images: Operand<T>, scale: Operand<TFloat32>):
            AdjustSaturation<T> = java.adjustSaturation<T>(    
        images,
        scale
        )

    /**
     * Greedily selects a subset of bounding boxes in descending order of score,
     *  
     *  This operation performs non_max_suppression on the inputs per batch, across
     *  all classes.
     *  Prunes away boxes that have high intersection-over-union (IOU) overlap
     *  with previously selected boxes.  Bounding boxes are supplied as
     *  &#91;y1, x1, y2, x2], where (y1, x1) and (y2, x2) are the coordinates of any
     *  diagonal pair of box corners and the coordinates can be provided as normalized
     *  (i.e., lying in the interval &#91;0, 1]) or absolute.  Note that this algorithm
     *  is agnostic to where the origin is in the coordinate system. Also note that
     *  this algorithm is invariant to orthogonal transformations and translations
     *  of the coordinate system; thus translating or reflections of the coordinate
     *  system result in the same boxes being selected by the algorithm.
     *  The output of this operation is the final boxes, scores and classes tensor
     *  returned after performing non_max_suppression.
     * 
     * @param boxes A 4-D float tensor of shape `&#91;batch_size, num_boxes, q, 4]`. If `q` is 1
     * then
     *  same boxes are used for all classes otherwise, if `q` is equal to number of
     *  classes, class-specific boxes are used.
     * @param scores A 3-D float tensor of shape `&#91;batch_size, num_boxes, num_classes]`
     *  representing a single score corresponding to each box (each row of boxes).
     * @param maxOutputSizePerClass A scalar integer tensor representing the maximum number of
     *  boxes to be selected by non max suppression per class
     * @param maxTotalSize A scalar representing maximum number of boxes retained over all classes.
     * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
     *  boxes overlap too much with respect to IOU.
     * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to
     * remove
     *  boxes based on score.
     * @param options carries optional attributes values
     * @return a new instance of CombinedNonMaxSuppression
     * @see org.tensorflow.op.ImageOps.combinedNonMaxSuppression
     * @param padPerClass If false, the output nmsed boxes, scores and classes
     *  are padded/clipped to `max_total_size`. If true, the
     *  output nmsed boxes, scores and classes are padded to be of length
     *  `max_size_per_class`*`num_classes`, unless it exceeds `max_total_size` in
     *  which case it is clipped to `max_total_size`. Defaults to false.
     * @param clipBoxes If true, assume the box coordinates are between &#91;0, 1] and clip the
     * output boxes
     *  if they fall beyond &#91;0, 1]. If false, do not do clipping and output the box
     *  coordinates as it is.
     */
    public fun combinedNonMaxSuppression(
        boxes: Operand<TFloat32>,
        scores: Operand<TFloat32>,
        maxOutputSizePerClass: Operand<TInt32>,
        maxTotalSize: Operand<TInt32>,
        iouThreshold: Operand<TFloat32>,
        scoreThreshold: Operand<TFloat32>,
        padPerClass: Boolean? = null,
        clipBoxes: Boolean? = null
    ): CombinedNonMaxSuppression = java.combinedNonMaxSuppression(    
        boxes,
        scores,
        maxOutputSizePerClass,
        maxTotalSize,
        iouThreshold,
        scoreThreshold,
        *listOfNotNull(
            padPerClass?.let{ org.tensorflow.op.image.CombinedNonMaxSuppression.padPerClass(it) },
            clipBoxes?.let{ org.tensorflow.op.image.CombinedNonMaxSuppression.clipBoxes(it) }
        ).toTypedArray()
        )

    /**
     * Extracts crops from the input image tensor and resizes them.
     *  
     *  Extracts crops from the input image tensor and resizes them using bilinear
     *  sampling or nearest neighbor sampling (possibly with aspect ratio change) to a
     *  common output size specified by `crop_size`. This is more general than the
     *  `crop_to_bounding_box` op which extracts a fixed size slice from the input image
     *  and does not allow resizing or aspect ratio change.
     *  
     *  Returns a tensor with `crops` from the input `image` at positions defined at the
     *  bounding box locations in `boxes`. The cropped boxes are all resized (with
     *  bilinear or nearest neighbor interpolation) to a fixed
     *  `size = &#91;crop_height, crop_width]`. The result is a 4-D tensor
     *  `&#91;num_boxes, crop_height, crop_width, depth]`. The resizing is corner aligned.
     *  In particular, if `boxes = &#91;&#91;0, 0, 1, 1]]`, the method will give identical
     *  results to using `tf.image.resize_bilinear()` or
     *  `tf.image.resize_nearest_neighbor()`(depends on the `method` argument) with
     *  `align_corners=True`.
     * 
     * @param image A 4-D tensor of shape `&#91;batch, image_height, image_width, depth]`.
     *  Both `image_height` and `image_width` need to be positive.
     * @param boxes A 2-D tensor of shape `&#91;num_boxes, 4]`. The `i`-th row of the tensor
     *  specifies the coordinates of a box in the `box_ind&#91;i]` image and is specified
     *  in normalized coordinates `&#91;y1, x1, y2, x2]`. A normalized coordinate value of
     *  `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
     *  `&#91;0, 1]` interval of normalized image height is mapped to
     *  `&#91;0, image_height - 1]` in image height coordinates. We do allow `y1` > `y2`, in
     *  which case the sampled crop is an up-down flipped version of the original
     *  image. The width dimension is treated similarly. Normalized coordinates
     *  outside the `&#91;0, 1]` range are allowed, in which case we use
     *  `extrapolation_value` to extrapolate the input image values.
     * @param boxInd A 1-D tensor of shape `&#91;num_boxes]` with int32 values in `&#91;0, batch)`.
     *  The value of `box_ind&#91;i]` specifies the image that the `i`-th box refers to.
     * @param cropSize A 1-D tensor of 2 elements, `size = &#91;crop_height, crop_width]`. All
     *  cropped image patches are resized to this size. The aspect ratio of the image
     *  content is not preserved. Both `crop_height` and `crop_width` need to be
     *  positive.
     * @param options carries optional attributes values
     * @return a new instance of CropAndResize
     * @see org.tensorflow.op.ImageOps.cropAndResize
     * @param method A string specifying the sampling method for resizing. It can be either
     *  `"bilinear"` or `"nearest"` and default to `"bilinear"`. Currently two sampling
     *  methods are supported: Bilinear and Nearest Neighbor.
     * @param extrapolationValue Value used for extrapolation, when applicable.
     */
    public fun cropAndResize(
        image: Operand<out TNumber>,
        boxes: Operand<TFloat32>,
        boxInd: Operand<TInt32>,
        cropSize: Operand<TInt32>,
        method: String? = null,
        extrapolationValue: Float? = null
    ): CropAndResize = java.cropAndResize(    
        image,
        boxes,
        boxInd,
        cropSize,
        *listOfNotNull(
            method?.let{ org.tensorflow.op.image.CropAndResize.method(it) },
            extrapolationValue?.let{ org.tensorflow.op.image.CropAndResize.extrapolationValue(it) }
        ).toTypedArray()
        )

    /**
     * Computes the gradient of the crop_and_resize op wrt the input boxes tensor.
     * 
     * @param grads A 4-D tensor of shape `&#91;num_boxes, crop_height, crop_width, depth]`.
     * @param image A 4-D tensor of shape `&#91;batch, image_height, image_width, depth]`.
     *  Both `image_height` and `image_width` need to be positive.
     * @param boxes A 2-D tensor of shape `&#91;num_boxes, 4]`. The `i`-th row of the tensor
     *  specifies the coordinates of a box in the `box_ind&#91;i]` image and is specified
     *  in normalized coordinates `&#91;y1, x1, y2, x2]`. A normalized coordinate value of
     *  `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
     *  `&#91;0, 1]` interval of normalized image height is mapped to
     *  `&#91;0, image_height - 1] in image height coordinates. We do allow y1 > y2, in
     *  which case the sampled crop is an up-down flipped version of the original
     *  image. The width dimension is treated similarly. Normalized coordinates
     *  outside the `&#91;0, 1]` range are allowed, in which case we use
     *  `extrapolation_value` to extrapolate the input image values.
     * @param boxInd A 1-D tensor of shape `&#91;num_boxes]` with int32 values in `&#91;0, batch)`.
     *  The value of `box_ind&#91;i]` specifies the image that the `i`-th box refers to.
     * @param options carries optional attributes values
     * @return a new instance of CropAndResizeGradBoxes
     * @see org.tensorflow.op.ImageOps.cropAndResizeGradBoxes
     * @param method A string specifying the interpolation method. Only 'bilinear' is
     *  supported for now.
     */
    public fun cropAndResizeGradBoxes(
        grads: Operand<TFloat32>,
        image: Operand<out TNumber>,
        boxes: Operand<TFloat32>,
        boxInd: Operand<TInt32>,
        method: String? = null
    ): CropAndResizeGradBoxes = java.cropAndResizeGradBoxes(    
        grads,
        image,
        boxes,
        boxInd,
        *listOfNotNull(
            method?.let{ org.tensorflow.op.image.CropAndResizeGradBoxes.method(it) }
        ).toTypedArray()
        )

    /**
     * Computes the gradient of the crop_and_resize op wrt the input image tensor.
     * 
     * @param T data type for ` output()` output
     * @param grads A 4-D tensor of shape `&#91;num_boxes, crop_height, crop_width, depth]`.
     * @param boxes A 2-D tensor of shape `&#91;num_boxes, 4]`. The `i`-th row of the tensor
     *  specifies the coordinates of a box in the `box_ind&#91;i]` image and is specified
     *  in normalized coordinates `&#91;y1, x1, y2, x2]`. A normalized coordinate value of
     *  `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
     *  `&#91;0, 1]` interval of normalized image height is mapped to
     *  `&#91;0, image_height - 1] in image height coordinates. We do allow y1 > y2, in
     *  which case the sampled crop is an up-down flipped version of the original
     *  image. The width dimension is treated similarly. Normalized coordinates
     *  outside the `&#91;0, 1]` range are allowed, in which case we use
     *  `extrapolation_value` to extrapolate the input image values.
     * @param boxInd A 1-D tensor of shape `&#91;num_boxes]` with int32 values in `&#91;0, batch)`.
     *  The value of `box_ind&#91;i]` specifies the image that the `i`-th box refers to.
     * @param imageSize A 1-D tensor with value `&#91;batch, image_height, image_width, depth]`
     *  containing the original image size. Both `image_height` and `image_width` need
     *  to be positive.
     * @param T
     * @param options carries optional attributes values
     * @return a new instance of CropAndResizeGradImage
     * @see org.tensorflow.op.ImageOps.cropAndResizeGradImage
     * @param method A string specifying the interpolation method. Only 'bilinear' is
     *  supported for now.
     */
    public fun <T : TNumber> cropAndResizeGradImage(
        grads: Operand<TFloat32>,
        boxes: Operand<TFloat32>,
        boxInd: Operand<TInt32>,
        imageSize: Operand<TInt32>,
        T_: Class<T>,
        method: String? = null
    ): CropAndResizeGradImage<T> = java.cropAndResizeGradImage<T>(    
        grads,
        boxes,
        boxInd,
        imageSize,
        T_,
        *listOfNotNull(
            method?.let{ org.tensorflow.op.image.CropAndResizeGradImage.method(it) }
        ).toTypedArray()
        )

    /**
     * Decode and Crop a JPEG-encoded image to a uint8 tensor.
     *  
     *  The attr `channels` indicates the desired number of color channels for the
     *  decoded image.
     *  
     *  Accepted values are:
     *  <ul>
     *  <li>
     *  0: Use the number of channels in the JPEG-encoded image.
     *  </li>
     *  <li>
     *  1: output a grayscale image.
     *  </li>
     *  <li>
     *  3: output an RGB image.
     *  </li>
     *  </ul>
     *  If needed, the JPEG-encoded image is transformed to match the requested number
     *  of color channels.
     *  
     *  The attr `ratio` allows downscaling the image by an integer factor during
     *  decoding.  Allowed values are: 1, 2, 4, and 8.  This is much faster than
     *  downscaling the image later.
     *  
     *  It is equivalent to a combination of decode and crop, but much faster by only
     *  decoding partial jpeg image.
     * 
     * @param contents 0-D.  The JPEG-encoded image.
     * @param cropWindow 1-D.  The crop window: &#91;crop_y, crop_x, crop_height, crop_width].
     * @param options carries optional attributes values
     * @return a new instance of DecodeAndCropJpeg
     * @see org.tensorflow.op.ImageOps.decodeAndCropJpeg
     * @param channels Number of color channels for the decoded image.
     * @param ratio Downscaling ratio.
     * @param fancyUpscaling If true use a slower but nicer upscaling of the
     *  chroma planes (yuv420/422 only).
     * @param tryRecoverTruncated If true try to recover an image from truncated input.
     * @param acceptableFraction The minimum required fraction of lines before a truncated
     *  input is accepted.
     * @param dctMethod string specifying a hint about the algorithm used for
     *  decompression.  Defaults to "" which maps to a system-specific
     *  default.  Currently valid values are &#91;"INTEGER_FAST",
     *  "INTEGER_ACCURATE"].  The hint may be ignored (e.g., the internal
     *  jpeg library changes to a version that does not have that specific
     *  option.)
     */
    public fun decodeAndCropJpeg(
        contents: Operand<TString>,
        cropWindow: Operand<TInt32>,
        channels: Long? = null,
        ratio: Long? = null,
        fancyUpscaling: Boolean? = null,
        tryRecoverTruncated: Boolean? = null,
        acceptableFraction: Float? = null,
        dctMethod: String? = null
    ): DecodeAndCropJpeg = java.decodeAndCropJpeg(    
        contents,
        cropWindow,
        *listOfNotNull(
            channels?.let{ org.tensorflow.op.image.DecodeAndCropJpeg.channels(it) },
            ratio?.let{ org.tensorflow.op.image.DecodeAndCropJpeg.ratio(it) },
            fancyUpscaling?.let{ org.tensorflow.op.image.DecodeAndCropJpeg.fancyUpscaling(it) },
            tryRecoverTruncated?.let{ org.tensorflow.op.image.DecodeAndCropJpeg.tryRecoverTruncated(it) },
            acceptableFraction?.let{ org.tensorflow.op.image.DecodeAndCropJpeg.acceptableFraction(it) },
            dctMethod?.let{ org.tensorflow.op.image.DecodeAndCropJpeg.dctMethod(it) }
        ).toTypedArray()
        )

    /**
     * Decode the first frame of a BMP-encoded image to a uint8 tensor.
     *  
     *  The attr `channels` indicates the desired number of color channels for the
     *  decoded image.
     *  
     *  Accepted values are:
     *  <ul>
     *  <li>
     *  0: Use the number of channels in the BMP-encoded image.
     *  </li>
     *  <li>
     *  3: output an RGB image.
     *  </li>
     *  <li>
     *  4: output an RGBA image.
     * 
     * @param contents 0-D.  The BMP-encoded image.
     * @param options carries optional attributes values
     * @return a new instance of DecodeBmp
     * @see org.tensorflow.op.ImageOps.decodeBmp
     * @param channels @param channels
     */
    public fun decodeBmp(contents: Operand<TString>, channels: Long? = null): DecodeBmp =
            java.decodeBmp(    
        contents,
        *listOfNotNull(
            channels?.let{ org.tensorflow.op.image.DecodeBmp.channels(it) }
        ).toTypedArray()
        )

    /**
     * Decode the frame(s) of a GIF-encoded image to a uint8 tensor.
     *  
     *  GIF images with frame or transparency compression are not supported.
     *  On Linux and MacOS systems, convert animated GIFs from compressed to
     *  uncompressed by running:
     *  
     *      convert $src.gif -coalesce $dst.gif
     *  
     *  This op also supports decoding JPEGs and PNGs, though it is cleaner to use
     *  `tf.io.decode_image`.
     * 
     * @param contents 0-D.  The GIF-encoded image.
     * @return a new instance of DecodeGif
     * @see org.tensorflow.op.ImageOps.decodeGif
     */
    public fun decodeGif(contents: Operand<TString>): DecodeGif = java.decodeGif(    
        contents
        )

    /**
     * Decode a JPEG-encoded image to a uint8 tensor.
     *  
     *  The attr `channels` indicates the desired number of color channels for the
     *  decoded image.
     *  
     *  Accepted values are:
     *  <ul>
     *  <li>
     *  0: Use the number of channels in the JPEG-encoded image.
     *  </li>
     *  <li>
     *  1: output a grayscale image.
     *  </li>
     *  <li>
     *  3: output an RGB image.
     *  </li>
     *  </ul>
     *  If needed, the JPEG-encoded image is transformed to match the requested number
     *  of color channels.
     *  
     *  The attr `ratio` allows downscaling the image by an integer factor during
     *  decoding.  Allowed values are: 1, 2, 4, and 8.  This is much faster than
     *  downscaling the image later.
     *  
     *  This op also supports decoding PNGs and non-animated GIFs since the interface is
     *  the same, though it is cleaner to use `tf.io.decode_image`.
     * 
     * @param contents 0-D.  The JPEG-encoded image.
     * @param options carries optional attributes values
     * @return a new instance of DecodeJpeg
     * @see org.tensorflow.op.ImageOps.decodeJpeg
     * @param channels Number of color channels for the decoded image.
     * @param ratio Downscaling ratio.
     * @param fancyUpscaling If true use a slower but nicer upscaling of the
     *  chroma planes (yuv420/422 only).
     * @param tryRecoverTruncated If true try to recover an image from truncated input.
     * @param acceptableFraction The minimum required fraction of lines before a truncated
     *  input is accepted.
     * @param dctMethod string specifying a hint about the algorithm used for
     *  decompression.  Defaults to "" which maps to a system-specific
     *  default.  Currently valid values are &#91;"INTEGER_FAST",
     *  "INTEGER_ACCURATE"].  The hint may be ignored (e.g., the internal
     *  jpeg library changes to a version that does not have that specific
     *  option.)
     */
    public fun decodeJpeg(
        contents: Operand<TString>,
        channels: Long? = null,
        ratio: Long? = null,
        fancyUpscaling: Boolean? = null,
        tryRecoverTruncated: Boolean? = null,
        acceptableFraction: Float? = null,
        dctMethod: String? = null
    ): DecodeJpeg = java.decodeJpeg(    
        contents,
        *listOfNotNull(
            channels?.let{ org.tensorflow.op.image.DecodeJpeg.channels(it) },
            ratio?.let{ org.tensorflow.op.image.DecodeJpeg.ratio(it) },
            fancyUpscaling?.let{ org.tensorflow.op.image.DecodeJpeg.fancyUpscaling(it) },
            tryRecoverTruncated?.let{ org.tensorflow.op.image.DecodeJpeg.tryRecoverTruncated(it) },
            acceptableFraction?.let{ org.tensorflow.op.image.DecodeJpeg.acceptableFraction(it) },
            dctMethod?.let{ org.tensorflow.op.image.DecodeJpeg.dctMethod(it) }
        ).toTypedArray()
        )

    /**
     * Decode a PNG-encoded image to a uint8 or uint16 tensor.
     *  
     *  The attr `channels` indicates the desired number of color channels for the
     *  decoded image.
     *  
     *  Accepted values are:
     *  <ul>
     *  <li>
     *  0: Use the number of channels in the PNG-encoded image.
     *  </li>
     *  <li>
     *  1: output a grayscale image.
     *  </li>
     *  <li>
     *  3: output an RGB image.
     *  </li>
     *  <li>
     *  4: output an RGBA image.
     *  </li>
     *  </ul>
     *  If needed, the PNG-encoded image is transformed to match the requested number
     *  of color channels.
     *  
     *  This op also supports decoding JPEGs and non-animated GIFs since the interface
     *  is the same, though it is cleaner to use `tf.io.decode_image`.
     * 
     * @param T data type for ` image()` output
     * @param contents 0-D.  The PNG-encoded image.
     * @param options carries optional attributes values
     * @return a new instance of DecodePng
     * @see org.tensorflow.op.ImageOps.decodePng
     * @param channels Number of color channels for the decoded image.
     */
    public fun decodePng(contents: Operand<TString>, channels: Long? = null): DecodePng<TUint8> =
            java.decodePng(    
        contents,
        *listOfNotNull(
            channels?.let{ org.tensorflow.op.image.DecodePng.channels(it) }
        ).toTypedArray()
        )

    /**
     * Decode a PNG-encoded image to a uint8 or uint16 tensor.
     *  
     *  The attr `channels` indicates the desired number of color channels for the
     *  decoded image.
     *  
     *  Accepted values are:
     *  <ul>
     *  <li>
     *  0: Use the number of channels in the PNG-encoded image.
     *  </li>
     *  <li>
     *  1: output a grayscale image.
     *  </li>
     *  <li>
     *  3: output an RGB image.
     *  </li>
     *  <li>
     *  4: output an RGBA image.
     *  </li>
     *  </ul>
     *  If needed, the PNG-encoded image is transformed to match the requested number
     *  of color channels.
     *  
     *  This op also supports decoding JPEGs and non-animated GIFs since the interface
     *  is the same, though it is cleaner to use `tf.io.decode_image`.
     * 
     * @param T data type for ` image()` output
     * @param contents 0-D.  The PNG-encoded image.
     * @param dtype
     * @param options carries optional attributes values
     * @return a new instance of DecodePng
     * @see org.tensorflow.op.ImageOps.decodePng
     * @param channels Number of color channels for the decoded image.
     */
    public fun <T : TNumber> decodePng(
        contents: Operand<TString>,
        dtype: Class<T>,
        channels: Long? = null
    ): DecodePng<T> = java.decodePng<T>(    
        contents,
        dtype,
        *listOfNotNull(
            channels?.let{ org.tensorflow.op.image.DecodePng.channels(it) }
        ).toTypedArray()
        )

    /**
     * Draw bounding boxes on a batch of images.
     *  
     *  Outputs a copy of `images` but draws on top of the pixels zero or more bounding
     *  boxes specified by the locations in `boxes`. The coordinates of the each
     *  bounding box in `boxes` are encoded as `&#91;y_min, x_min, y_max, x_max]`. The
     *  bounding box coordinates are floats in `&#91;0.0, 1.0]` relative to the width and
     *  height of the underlying image.
     *  
     *  For example, if an image is 100 x 200 pixels (height x width) and the bounding
     *  box is `&#91;0.1, 0.2, 0.5, 0.9]`, the upper-left and bottom-right coordinates of
     *  the bounding box will be `(40, 10)` to `(100, 50)` (in (x,y) coordinates).
     *  
     *  Parts of the bounding box may fall outside the image.
     * 
     * @param T data type for ` output()` output
     * @param images 4-D with shape `&#91;batch, height, width, depth]`. A batch of images.
     * @param boxes 3-D with shape `&#91;batch, num_bounding_boxes, 4]` containing bounding
     *  boxes.
     * @param colors 2-D. A list of RGBA colors to cycle through for the boxes.
     * @return a new instance of DrawBoundingBoxes
     * @see org.tensorflow.op.ImageOps.drawBoundingBoxes
     */
    public fun <T : TNumber> drawBoundingBoxes(
        images: Operand<T>,
        boxes: Operand<TFloat32>,
        colors: Operand<TFloat32>
    ): DrawBoundingBoxes<T> = java.drawBoundingBoxes<T>(    
        images,
        boxes,
        colors
        )

    /**
     * JPEG-encode an image.
     *  
     *  `image` is a 3-D uint8 Tensor of shape `&#91;height, width, channels]`.
     *  
     *  The attr `format` can be used to override the color format of the encoded
     *  output.  Values can be:
     *  <ul>
     *  <li>
     *  `''`: Use a default format based on the number of channels in the image.
     *  </li>
     *  <li>
     *  `grayscale`: Output a grayscale JPEG image.  The `channels` dimension
     *      of `image` must be 1.
     *  </li>
     *  <li>
     *  `rgb`: Output an RGB JPEG image. The `channels` dimension
     *      of `image` must be 3.
     *  </li>
     *  </ul>
     *  If `format` is not specified or is the empty string, a default format is picked
     *  in function of the number of channels in `image`:
     *  <ul>
     *  <li>
     *  1: Output a grayscale image.
     *  </li>
     *  <li>
     *  3: Output an RGB image.
     * 
     * @param image 3-D with shape `&#91;height, width, channels]`.
     * @param options carries optional attributes values
     * @return a new instance of EncodeJpeg
     * @see org.tensorflow.op.ImageOps.encodeJpeg
     * @param format Per pixel image format.
     * @param quality Quality of the compression from 0 to 100 (higher is better and slower).
     * @param progressive If True, create a JPEG that loads progressively (coarse to fine).
     * @param optimizeSize If True, spend CPU/RAM to reduce size with no quality change.
     * @param chromaDownsampling See http://en.wikipedia.org/wiki/Chroma_subsampling.
     * @param densityUnit Unit used to specify `x_density` and `y_density`:
     *  pixels per inch (`'in'`) or centimeter (`'cm'`).
     * @param xDensity Horizontal pixels per density unit.
     * @param yDensity Vertical pixels per density unit.
     * @param xmpMetadata If not empty, embed this XMP metadata in the image header.
     */
    public fun encodeJpeg(
        image: Operand<TUint8>,
        format: String? = null,
        quality: Long? = null,
        progressive: Boolean? = null,
        optimizeSize: Boolean? = null,
        chromaDownsampling: Boolean? = null,
        densityUnit: String? = null,
        xDensity: Long? = null,
        yDensity: Long? = null,
        xmpMetadata: String? = null
    ): EncodeJpeg = java.encodeJpeg(    
        image,
        *listOfNotNull(
            format?.let{ org.tensorflow.op.image.EncodeJpeg.format(it) },
            quality?.let{ org.tensorflow.op.image.EncodeJpeg.quality(it) },
            progressive?.let{ org.tensorflow.op.image.EncodeJpeg.progressive(it) },
            optimizeSize?.let{ org.tensorflow.op.image.EncodeJpeg.optimizeSize(it) },
            chromaDownsampling?.let{ org.tensorflow.op.image.EncodeJpeg.chromaDownsampling(it) },
            densityUnit?.let{ org.tensorflow.op.image.EncodeJpeg.densityUnit(it) },
            xDensity?.let{ org.tensorflow.op.image.EncodeJpeg.xDensity(it) },
            yDensity?.let{ org.tensorflow.op.image.EncodeJpeg.yDensity(it) },
            xmpMetadata?.let{ org.tensorflow.op.image.EncodeJpeg.xmpMetadata(it) }
        ).toTypedArray()
        )

    /**
     * JPEG encode input image with provided compression quality.
     *  
     *  `image` is a 3-D uint8 Tensor of shape `&#91;height, width, channels]`.
     *  `quality` is an int32 jpeg compression quality value between 0 and 100.
     * 
     * @param images Images to adjust.  At least 3-D.
     * @param quality An int quality to encode to.
     * @return a new instance of EncodeJpegVariableQuality
     * @see org.tensorflow.op.ImageOps.encodeJpegVariableQuality
     */
    public fun encodeJpegVariableQuality(images: Operand<TUint8>, quality: Operand<TInt32>):
            EncodeJpegVariableQuality = java.encodeJpegVariableQuality(    
        images,
        quality
        )

    /**
     * PNG-encode an image.
     *  
     *  `image` is a 3-D uint8 or uint16 Tensor of shape `&#91;height, width, channels]`
     *  where `channels` is:
     *  <ul>
     *  <li>
     *  1: for grayscale.
     *  </li>
     *  <li>
     *  2: for grayscale + alpha.
     *  </li>
     *  <li>
     *  3: for RGB.
     *  </li>
     *  <li>
     *  4: for RGBA.
     *  </li>
     *  </ul>
     *  The ZLIB compression level, `compression`, can be -1 for the PNG-encoder
     *  default or a value from 0 to 9.  9 is the highest compression level, generating
     *  the smallest output, but is slower.
     * 
     * @param image 3-D with shape `&#91;height, width, channels]`.
     * @param options carries optional attributes values
     * @return a new instance of EncodePng
     * @see org.tensorflow.op.ImageOps.encodePng
     * @param compression Compression level.
     */
    public fun encodePng(image: Operand<out TNumber>, compression: Long? = null): EncodePng =
            java.encodePng(    
        image,
        *listOfNotNull(
            compression?.let{ org.tensorflow.op.image.EncodePng.compression(it) }
        ).toTypedArray()
        )

    /**
     * Extract `patches` from `images` and put them in the "depth" output dimension.
     * 
     * @param T data type for ` patches()` output
     * @param images 4-D Tensor with shape `&#91;batch, in_rows, in_cols, depth]`.
     * @param ksizes The size of the sliding window for each dimension of `images`.
     * @param strides How far the centers of two consecutive patches are in
     *  the images. Must be: `&#91;1, stride_rows, stride_cols, 1]`.
     * @param rates Must be: `&#91;1, rate_rows, rate_cols, 1]`. This is the
     *  input stride, specifying how far two consecutive patch samples are in the
     *  input. Equivalent to extracting patches with
     *  `patch_sizes_eff = patch_sizes + (patch_sizes - 1) * (rates - 1)`, followed by
     *  subsampling them spatially by a factor of `rates`. This is equivalent to
     *  `rate` in dilated (a.k.a. Atrous) convolutions.
     * @param padding The type of padding algorithm to use.
     * @return a new instance of ExtractImagePatches
     * @see org.tensorflow.op.ImageOps.extractImagePatches
     */
    public fun <T : TType> extractImagePatches(
        images: Operand<T>,
        ksizes: List<Long>,
        strides: List<Long>,
        rates: List<Long>,
        padding: String
    ): ExtractImagePatches<T> = java.extractImagePatches<T>(    
        images,
        ksizes,
        strides,
        rates,
        padding
        )

    /**
     * Extract the shape information of a JPEG-encoded image.
     *  
     *  This op only parses the image header, so it is much faster than DecodeJpeg.
     * 
     * @param T data type for ` imageShape()` output
     * @param contents 0-D. The JPEG-encoded image.
     * @return a new instance of ExtractJpegShape
     * @see org.tensorflow.op.ImageOps.extractJpegShape
     */
    public fun extractJpegShape(contents: Operand<TString>): ExtractJpegShape<TInt32> =
            java.extractJpegShape(    
        contents
        )

    /**
     * Extract the shape information of a JPEG-encoded image.
     *  
     *  This op only parses the image header, so it is much faster than DecodeJpeg.
     * 
     * @param T data type for ` imageShape()` output
     * @param contents 0-D. The JPEG-encoded image.
     * @param outputType (Optional) The output type of the operation (int32 or int64).
     *  Defaults to int32.
     * @return a new instance of ExtractJpegShape
     * @see org.tensorflow.op.ImageOps.extractJpegShape
     */
    public fun <T : TNumber> extractJpegShape(contents: Operand<TString>, outputType: Class<T>):
            ExtractJpegShape<T> = java.extractJpegShape<T>(    
        contents,
        outputType
        )

    /**
     * Convert one or more images from HSV to RGB.
     *  
     *  Outputs a tensor of the same shape as the `images` tensor, containing the RGB
     *  value of the pixels. The output is only well defined if the value in `images`
     *  are in `&#91;0,1]`.
     *  
     *  See `rgb_to_hsv` for a description of the HSV encoding.
     * 
     * @param T data type for ` output()` output
     * @param images 1-D or higher rank. HSV data to convert. Last dimension must be size 3.
     * @return a new instance of HsvToRgb
     * @see org.tensorflow.op.ImageOps.hsvToRgb
     */
    public fun <T : TNumber> hsvToRgb(images: Operand<T>): HsvToRgb<T> = java.hsvToRgb<T>(    
        images
        )

    /**
     * Greedily selects a subset of bounding boxes in descending order of score,
     *  
     *  pruning away boxes that have high intersection-over-union (IOU) overlap
     *  with previously selected boxes.  Bounding boxes with score less than
     *  `score_threshold` are removed.  Bounding boxes are supplied as
     *  &#91;y1, x1, y2, x2], where (y1, x1) and (y2, x2) are the coordinates of any
     *  diagonal pair of box corners and the coordinates can be provided as normalized
     *  (i.e., lying in the interval &#91;0, 1]) or absolute.  Note that this algorithm
     *  is agnostic to where the origin is in the coordinate system and more
     *  generally is invariant to orthogonal transformations and translations
     *  of the coordinate system; thus translating or reflections of the coordinate
     *  system result in the same boxes being selected by the algorithm.
     *  The output of this operation is a set of integers indexing into the input
     *  collection of bounding boxes representing the selected boxes.  The bounding
     *  box coordinates corresponding to the selected indices can then be obtained
     *  using the `tf.gather operation`.  For example:
     *    selected_indices = tf.image.non_max_suppression_v2(
     *        boxes, scores, max_output_size, iou_threshold, score_threshold)
     *    selected_boxes = tf.gather(boxes, selected_indices)
     *  This op also supports a Soft-NMS (with Gaussian weighting) mode (c.f.
     *  Bodla et al, https://arxiv.org/abs/1704.04503) where boxes reduce the score
     *  of other overlapping boxes instead of directly causing them to be pruned.
     *  To enable this Soft-NMS mode, set the `soft_nms_sigma` parameter to be
     *  larger than 0.
     * 
     * @param T data type for ` selectedScores()` output
     * @param boxes A 2-D float tensor of shape `&#91;num_boxes, 4]`.
     * @param scores A 1-D float tensor of shape `&#91;num_boxes]` representing a single
     *  score corresponding to each box (each row of boxes).
     * @param maxOutputSize A scalar integer tensor representing the maximum number of
     *  boxes to be selected by non max suppression.
     * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
     *  boxes overlap too much with respect to IOU.
     * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to
     * remove
     *  boxes based on score.
     * @param softNmsSigma A 0-D float tensor representing the sigma parameter for Soft NMS; see
     * Bodla et
     *  al (c.f. https://arxiv.org/abs/1704.04503).  When `soft_nms_sigma=0.0` (which
     *  is default), we fall back to standard (hard) NMS.
     * @param options carries optional attributes values
     * @return a new instance of NonMaxSuppression
     * @see org.tensorflow.op.ImageOps.nonMaxSuppression
     * @param padToMaxOutputSize If true, the output `selected_indices` is padded to be of length
     *  `max_output_size`. Defaults to false.
     */
    public fun <T : TNumber> nonMaxSuppression(
        boxes: Operand<T>,
        scores: Operand<T>,
        maxOutputSize: Operand<TInt32>,
        iouThreshold: Operand<T>,
        scoreThreshold: Operand<T>,
        softNmsSigma: Operand<T>,
        padToMaxOutputSize: Boolean? = null
    ): NonMaxSuppression<T> = java.nonMaxSuppression<T>(    
        boxes,
        scores,
        maxOutputSize,
        iouThreshold,
        scoreThreshold,
        softNmsSigma,
        *listOfNotNull(
            padToMaxOutputSize?.let{ org.tensorflow.op.image.NonMaxSuppression.padToMaxOutputSize(it) }
        ).toTypedArray()
        )

    /**
     * Greedily selects a subset of bounding boxes in descending order of score,
     *  
     *  pruning away boxes that have high overlaps
     *  with previously selected boxes.  Bounding boxes with score less than
     *  `score_threshold` are removed. N-by-n overlap values are supplied as square matrix,
     *  which allows for defining a custom overlap criterium (eg. intersection over union,
     *  intersection over area, etc.).
     *  
     *  The output of this operation is a set of integers indexing into the input
     *  collection of bounding boxes representing the selected boxes.  The bounding
     *  box coordinates corresponding to the selected indices can then be obtained
     *  using the `tf.gather operation`.  For example:
     *  
     *    selected_indices = tf.image.non_max_suppression_with_overlaps(
     *        overlaps, scores, max_output_size, overlap_threshold, score_threshold)
     *    selected_boxes = tf.gather(boxes, selected_indices)
     * 
     * @param overlaps A 2-D float tensor of shape `&#91;num_boxes, num_boxes]` representing
     *  the n-by-n box overlap values.
     * @param scores A 1-D float tensor of shape `&#91;num_boxes]` representing a single
     *  score corresponding to each box (each row of boxes).
     * @param maxOutputSize A scalar integer tensor representing the maximum number of
     *  boxes to be selected by non max suppression.
     * @param overlapThreshold A 0-D float tensor representing the threshold for deciding whether
     *  boxes overlap too.
     * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to
     * remove
     *  boxes based on score.
     * @return a new instance of NonMaxSuppressionWithOverlaps
     * @see org.tensorflow.op.ImageOps.nonMaxSuppressionWithOverlaps
     */
    public fun nonMaxSuppressionWithOverlaps(
        overlaps: Operand<TFloat32>,
        scores: Operand<TFloat32>,
        maxOutputSize: Operand<TInt32>,
        overlapThreshold: Operand<TFloat32>,
        scoreThreshold: Operand<TFloat32>
    ): NonMaxSuppressionWithOverlaps = java.nonMaxSuppressionWithOverlaps(    
        overlaps,
        scores,
        maxOutputSize,
        overlapThreshold,
        scoreThreshold
        )

    /**
     * Resize quantized `images` to `size` using quantized bilinear interpolation.
     *  
     *  Input images and output images must be quantized types.
     * 
     * @param T data type for ` resizedImages()` output
     * @param images 4-D with shape `&#91;batch, height, width, channels]`.
     * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
     *  new size for the images.
     * @param min
     * @param max
     * @param options carries optional attributes values
     * @return a new instance of QuantizedResizeBilinear
     * @see org.tensorflow.op.ImageOps.quantizedResizeBilinear
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and output
     * tensors are
     *  aligned, preserving the values at the corner pixels. Defaults to false.
     * @param halfPixelCenters @param halfPixelCenters
     */
    public fun <T : TType> quantizedResizeBilinear(
        images: Operand<T>,
        size: Operand<TInt32>,
        min: Operand<TFloat32>,
        max: Operand<TFloat32>,
        alignCorners: Boolean? = null,
        halfPixelCenters: Boolean? = null
    ): QuantizedResizeBilinear<T> = java.quantizedResizeBilinear<T>(    
        images,
        size,
        min,
        max,
        *listOfNotNull(
            alignCorners?.let{ org.tensorflow.op.image.QuantizedResizeBilinear.alignCorners(it) },
            halfPixelCenters?.let{ org.tensorflow.op.image.QuantizedResizeBilinear.halfPixelCenters(it) }
        ).toTypedArray()
        )

    /**
     * Randomly crop `image`.
     *  
     *  `size` is a 1-D int64 tensor with 2 elements representing the crop height and
     *  width.  The values must be non negative.
     *  
     *  This Op picks a random location in `image` and crops a `height` by `width`
     *  rectangle from that location.  The random location is picked so the cropped
     *  area will fit inside the original image.
     * 
     * @param T data type for ` output()` output
     * @param image 3-D of shape `&#91;height, width, channels]`.
     * @param size 1-D of length 2 containing: `crop_height`, `crop_width`..
     * @param options carries optional attributes values
     * @return a new instance of RandomCrop
     * @see org.tensorflow.op.ImageOps.randomCrop
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 An second seed to avoid seed collision.
     */
    public fun <T : TNumber> randomCrop(
        image: Operand<T>,
        size: Operand<TInt64>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomCrop<T> = java.randomCrop<T>(    
        image,
        size,
        *listOfNotNull(
            seed?.let{ org.tensorflow.op.image.RandomCrop.seed(it) },
            seed2?.let{ org.tensorflow.op.image.RandomCrop.seed2(it) }
        ).toTypedArray()
        )

    /**
     * Resize `images` to `size` using area interpolation.
     *  
     *  Input images can be of different types but output images are always float.
     *  
     *  The range of pixel values for the output image might be slightly different
     *  from the range for the input image because of limited numerical precision.
     *  To guarantee an output range, for example `&#91;0.0, 1.0]`, apply
     *  `tf.clip_by_value` to the output.
     *  
     *  Each output pixel is computed by first transforming the pixel's footprint into
     *  the input tensor and then averaging the pixels that intersect the footprint. An
     *  input pixel's contribution to the average is weighted by the fraction of its
     *  area that intersects the footprint.  This is the same as OpenCV's INTER_AREA.
     * 
     * @param images 4-D with shape `&#91;batch, height, width, channels]`.
     * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
     *  new size for the images.
     * @param options carries optional attributes values
     * @return a new instance of ResizeArea
     * @see org.tensorflow.op.ImageOps.resizeArea
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and output
     * tensors are
     *  aligned, preserving the values at the corner pixels. Defaults to false.
     */
    public fun resizeArea(
        images: Operand<out TNumber>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null
    ): ResizeArea = java.resizeArea(    
        images,
        size,
        *listOfNotNull(
            alignCorners?.let{ org.tensorflow.op.image.ResizeArea.alignCorners(it) }
        ).toTypedArray()
        )

    /**
     * Resize `images` to `size` using bicubic interpolation.
     *  
     *  Input images can be of different types but output images are always float.
     * 
     * @param images 4-D with shape `&#91;batch, height, width, channels]`.
     * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
     *  new size for the images.
     * @param options carries optional attributes values
     * @return a new instance of ResizeBicubic
     * @see org.tensorflow.op.ImageOps.resizeBicubic
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and output
     * tensors are
     *  aligned, preserving the values at the corner pixels. Defaults to false.
     * @param halfPixelCenters @param halfPixelCenters
     */
    public fun resizeBicubic(
        images: Operand<out TNumber>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null,
        halfPixelCenters: Boolean? = null
    ): ResizeBicubic = java.resizeBicubic(    
        images,
        size,
        *listOfNotNull(
            alignCorners?.let{ org.tensorflow.op.image.ResizeBicubic.alignCorners(it) },
            halfPixelCenters?.let{ org.tensorflow.op.image.ResizeBicubic.halfPixelCenters(it) }
        ).toTypedArray()
        )

    /**
     * Resize `images` to `size` using bilinear interpolation.
     *  
     *  Input images can be of different types but output images are always float.
     * 
     * @param images 4-D with shape `&#91;batch, height, width, channels]`.
     * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
     *  new size for the images.
     * @param options carries optional attributes values
     * @return a new instance of ResizeBilinear
     * @see org.tensorflow.op.ImageOps.resizeBilinear
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and output
     * tensors are
     *  aligned, preserving the values at the corner pixels. Defaults to false.
     * @param halfPixelCenters @param halfPixelCenters
     */
    public fun resizeBilinear(
        images: Operand<out TNumber>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null,
        halfPixelCenters: Boolean? = null
    ): ResizeBilinear = java.resizeBilinear(    
        images,
        size,
        *listOfNotNull(
            alignCorners?.let{ org.tensorflow.op.image.ResizeBilinear.alignCorners(it) },
            halfPixelCenters?.let{ org.tensorflow.op.image.ResizeBilinear.halfPixelCenters(it) }
        ).toTypedArray()
        )

    /**
     * Resize `images` to `size` using nearest neighbor interpolation.
     * 
     * @param T data type for ` resizedImages()` output
     * @param images 4-D with shape `&#91;batch, height, width, channels]`.
     * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
     *  new size for the images.
     * @param options carries optional attributes values
     * @return a new instance of ResizeNearestNeighbor
     * @see org.tensorflow.op.ImageOps.resizeNearestNeighbor
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and output
     * tensors are
     *  aligned, preserving the values at the corner pixels. Defaults to false.
     * @param halfPixelCenters @param halfPixelCenters
     */
    public fun <T : TNumber> resizeNearestNeighbor(
        images: Operand<T>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null,
        halfPixelCenters: Boolean? = null
    ): ResizeNearestNeighbor<T> = java.resizeNearestNeighbor<T>(    
        images,
        size,
        *listOfNotNull(
            alignCorners?.let{ org.tensorflow.op.image.ResizeNearestNeighbor.alignCorners(it) },
            halfPixelCenters?.let{ org.tensorflow.op.image.ResizeNearestNeighbor.halfPixelCenters(it) }
        ).toTypedArray()
        )

    /**
     * Converts one or more images from RGB to HSV.
     *  
     *  Outputs a tensor of the same shape as the `images` tensor, containing the HSV
     *  value of the pixels. The output is only well defined if the value in `images`
     *  are in `&#91;0,1]`.
     *  
     *  `output&#91;..., 0]` contains hue, `output&#91;..., 1]` contains saturation, and
     *  `output&#91;..., 2]` contains value. All HSV values are in `&#91;0,1]`. A hue of 0
     *  corresponds to pure red, hue 1/3 is pure green, and 2/3 is pure blue.
     *  
     *  Usage Example:
     *  
     *  >>> blue_image = tf.stack(&#91;
     *  ...    tf.zeros(&#91;5,5]),
     *  ...    tf.zeros(&#91;5,5]),
     *  ...    tf.ones(&#91;5,5])],
     *  ...    axis=-1)
     *  >>> blue_hsv_image = tf.image.rgb_to_hsv(blue_image)
     *  >>> blue_hsv_image&#91;0,0].numpy()
     *  array(&#91;0.6666667, 1. , 1. ], dtype=float32)
     * 
     * @param T data type for ` output()` output
     * @param images 1-D or higher rank. RGB data to convert. Last dimension must be size 3.
     * @return a new instance of RgbToHsv
     * @see org.tensorflow.op.ImageOps.rgbToHsv
     */
    public fun <T : TNumber> rgbToHsv(images: Operand<T>): RgbToHsv<T> = java.rgbToHsv<T>(    
        images
        )

    /**
     * Generate a single randomly distorted bounding box for an image.
     *  
     *  Bounding box annotations are often supplied in addition to ground-truth labels
     *  in image recognition or object localization tasks. A common technique for
     *  training such a system is to randomly distort an image while preserving
     *  its content, i.e. <i>data augmentation</i>. This Op outputs a randomly distorted
     *  localization of an object, i.e. bounding box, given an `image_size`,
     *  `bounding_boxes` and a series of constraints.
     *  
     *  The output of this Op is a single bounding box that may be used to crop the
     *  original image. The output is returned as 3 tensors: `begin`, `size` and
     *  `bboxes`. The first 2 tensors can be fed directly into `tf.slice` to crop the
     *  image. The latter may be supplied to `tf.image.draw_bounding_boxes` to visualize
     *  what the bounding box looks like.
     *  
     *  Bounding boxes are supplied and returned as `&#91;y_min, x_min, y_max, x_max]`. The
     *  bounding box coordinates are floats in `&#91;0.0, 1.0]` relative to the width and
     *  height of the underlying image.
     *  
     *  For example,
     *  ```
     *      # Generate a single distorted bounding box.
     *      begin, size, bbox_for_draw = tf.image.sample_distorted_bounding_box(
     *          tf.shape(image),
     *          bounding_boxes=bounding_boxes)
     * 
     *      # Draw the bounding box in an image summary.
     *      image_with_box = tf.image.draw_bounding_boxes(tf.expand_dims(image, 0),
     *                                                    bbox_for_draw)
     *      tf.summary.image('images_with_box', image_with_box)
     * 
     *      # Employ the bounding box to distort the image.
     *      distorted_image = tf.slice(image, begin, size)
     *  ```
     * 
     *  Note that if no bounding box information is available, setting
     *  `use_image_if_no_bounding_boxes = true` will assume there is a single implicit
     *  bounding box covering the whole image. If `use_image_if_no_bounding_boxes` is
     *  false and no bounding boxes are supplied, an error is raised.
     * 
     * @param T data type for ` begin()` output
     * @param imageSize 1-D, containing `&#91;height, width, channels]`.
     * @param boundingBoxes 3-D with shape `&#91;batch, N, 4]` describing the N bounding boxes
     *  associated with the image.
     * @param minObjectCovered The cropped area of the image must contain at least this
     *  fraction of any bounding box supplied. The value of this parameter should be
     *  non-negative. In the case of 0, the cropped area does not need to overlap
     *  any of the bounding boxes supplied.
     * @param options carries optional attributes values
     * @return a new instance of SampleDistortedBoundingBox
     * @see org.tensorflow.op.ImageOps.sampleDistortedBoundingBox
     * @param seed If either `seed` or `seed2` are set to non-zero, the random number
     *  generator is seeded by the given `seed`.  Otherwise, it is seeded by a random
     *  seed.
     * @param seed2 A second seed to avoid seed collision.
     * @param aspectRatioRange The cropped area of the image must have an aspect ratio =
     *  width / height within this range.
     * @param areaRange The cropped area of the image must contain a fraction of the
     *  supplied image within this range.
     * @param maxAttempts Number of attempts at generating a cropped region of the image
     *  of the specified constraints. After `max_attempts` failures, return the entire
     *  image.
     * @param useImageIfNoBoundingBoxes Controls behavior if no bounding boxes supplied.
     *  If true, assume an implicit bounding box covering the whole input. If false,
     *  raise an error.
     */
    public fun <T : TNumber> sampleDistortedBoundingBox(
        imageSize: Operand<T>,
        boundingBoxes: Operand<TFloat32>,
        minObjectCovered: Operand<TFloat32>,
        seed: Long? = null,
        seed2: Long? = null,
        aspectRatioRange: List<Float>? = null,
        areaRange: List<Float>? = null,
        maxAttempts: Long? = null,
        useImageIfNoBoundingBoxes: Boolean? = null
    ): SampleDistortedBoundingBox<T> = java.sampleDistortedBoundingBox<T>(    
        imageSize,
        boundingBoxes,
        minObjectCovered,
        *listOfNotNull(
            seed?.let{ org.tensorflow.op.image.SampleDistortedBoundingBox.seed(it) },
            seed2?.let{ org.tensorflow.op.image.SampleDistortedBoundingBox.seed2(it) },
            aspectRatioRange?.let{ org.tensorflow.op.image.SampleDistortedBoundingBox.aspectRatioRange(it)
            },
            areaRange?.let{ org.tensorflow.op.image.SampleDistortedBoundingBox.areaRange(it) },
            maxAttempts?.let{ org.tensorflow.op.image.SampleDistortedBoundingBox.maxAttempts(it) },
            useImageIfNoBoundingBoxes?.let{
            org.tensorflow.op.image.SampleDistortedBoundingBox.useImageIfNoBoundingBoxes(it) }
        ).toTypedArray()
        )

    /**
     * 
     * @param images
     * @param size
     * @param scale
     * @param translation
     * @param options carries optional attributes values
     * @return a new instance of ScaleAndTranslate
     * @see org.tensorflow.op.ImageOps.scaleAndTranslate
     * @param kernelType @param kernelType
     * @param antialias @param antialias
     */
    public fun scaleAndTranslate(
        images: Operand<out TNumber>,
        size: Operand<TInt32>,
        scale: Operand<TFloat32>,
        translation: Operand<TFloat32>,
        kernelType: String? = null,
        antialias: Boolean? = null
    ): ScaleAndTranslate = java.scaleAndTranslate(    
        images,
        size,
        scale,
        translation,
        *listOfNotNull(
            kernelType?.let{ org.tensorflow.op.image.ScaleAndTranslate.kernelType(it) },
            antialias?.let{ org.tensorflow.op.image.ScaleAndTranslate.antialias(it) }
        ).toTypedArray()
        )

    /**
     * Computes the gradient of the crop_and_resize op wrt the input image tensor.
     * 
     * @param T data type for ` output()` output
     * @param grads A 4-D tensor of shape `&#91;num_boxes, crop_height, crop_width, depth]`.
     * @param boxes A 2-D tensor of shape `&#91;num_boxes, 4]`. The `i`-th row of the tensor
     *  specifies the coordinates of a box in the `box_ind&#91;i]` image and is specified
     *  in normalized coordinates `&#91;y1, x1, y2, x2]`. A normalized coordinate value of
     *  `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
     *  `&#91;0, 1]` interval of normalized image height is mapped to
     *  `&#91;0, image_height - 1] in image height coordinates. We do allow y1 > y2, in
     *  which case the sampled crop is an up-down flipped version of the original
     *  image. The width dimension is treated similarly. Normalized coordinates
     *  outside the `&#91;0, 1]` range are allowed, in which case we use
     *  `extrapolation_value` to extrapolate the input image values.
     * @param boxInd A 1-D tensor of shape `&#91;num_boxes]` with int32 values in `&#91;0, batch)`.
     *  The value of `box_ind&#91;i]` specifies the image that the `i`-th box refers to.
     * @param imageSize A 1-D tensor with value `&#91;batch, image_height, image_width, depth]`
     *  containing the original image size. Both `image_height` and `image_width` need
     *  to be positive.
     * @param T
     * @param options carries optional attributes values
     * @return a new instance of CropAndResizeGradImage
     * @see org.tensorflow.op.ImageOps.cropAndResizeGradImage
     * @param method A string specifying the interpolation method. Only 'bilinear' is
     *  supported for now.
     */
    @JvmName("cropAndResizeGradImageReified")
    public inline fun <reified T : TNumber> cropAndResizeGradImage(
        grads: Operand<TFloat32>,
        boxes: Operand<TFloat32>,
        boxInd: Operand<TInt32>,
        imageSize: Operand<TInt32>,
        method: String? = null
    ): CropAndResizeGradImage<T> = cropAndResizeGradImage<T>(grads, boxes, boxInd, imageSize,
            T::class.java, method)

    /**
     * Decode a PNG-encoded image to a uint8 or uint16 tensor.
     *  
     *  The attr `channels` indicates the desired number of color channels for the
     *  decoded image.
     *  
     *  Accepted values are:
     *  <ul>
     *  <li>
     *  0: Use the number of channels in the PNG-encoded image.
     *  </li>
     *  <li>
     *  1: output a grayscale image.
     *  </li>
     *  <li>
     *  3: output an RGB image.
     *  </li>
     *  <li>
     *  4: output an RGBA image.
     *  </li>
     *  </ul>
     *  If needed, the PNG-encoded image is transformed to match the requested number
     *  of color channels.
     *  
     *  This op also supports decoding JPEGs and non-animated GIFs since the interface
     *  is the same, though it is cleaner to use `tf.io.decode_image`.
     * 
     * @param T data type for ` image()` output
     * @param contents 0-D.  The PNG-encoded image.
     * @param dtype
     * @param options carries optional attributes values
     * @return a new instance of DecodePng
     * @see org.tensorflow.op.ImageOps.decodePng
     * @param channels Number of color channels for the decoded image.
     */
    @JvmName("decodePngReified")
    public inline fun <reified T : TNumber> decodePngTyped(contents: Operand<TString>,
            channels: Long? = null): DecodePng<T> = decodePng<T>(contents, T::class.java, channels)

    /**
     * Extract the shape information of a JPEG-encoded image.
     *  
     *  This op only parses the image header, so it is much faster than DecodeJpeg.
     * 
     * @param T data type for ` imageShape()` output
     * @param contents 0-D. The JPEG-encoded image.
     * @param outputType (Optional) The output type of the operation (int32 or int64).
     *  Defaults to int32.
     * @return a new instance of ExtractJpegShape
     * @see org.tensorflow.op.ImageOps.extractJpegShape
     */
    @JvmName("extractJpegShapeReified")
    public inline fun <reified T : TNumber> extractJpegShapeTyped(contents: Operand<TString>):
            ExtractJpegShape<T> = extractJpegShape<T>(contents, T::class.java)
}
