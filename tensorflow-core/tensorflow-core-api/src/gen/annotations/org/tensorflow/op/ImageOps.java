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
import org.tensorflow.op.image.AdjustContrast;
import org.tensorflow.op.image.AdjustHue;
import org.tensorflow.op.image.AdjustSaturation;
import org.tensorflow.op.image.CombinedNonMaxSuppression;
import org.tensorflow.op.image.CropAndResize;
import org.tensorflow.op.image.CropAndResizeGradBoxes;
import org.tensorflow.op.image.CropAndResizeGradImage;
import org.tensorflow.op.image.DecodeAndCropJpeg;
import org.tensorflow.op.image.DecodeBmp;
import org.tensorflow.op.image.DecodeGif;
import org.tensorflow.op.image.DecodeJpeg;
import org.tensorflow.op.image.DecodePng;
import org.tensorflow.op.image.DrawBoundingBoxes;
import org.tensorflow.op.image.EncodeJpeg;
import org.tensorflow.op.image.EncodeJpegVariableQuality;
import org.tensorflow.op.image.EncodePng;
import org.tensorflow.op.image.ExtractImagePatches;
import org.tensorflow.op.image.ExtractJpegShape;
import org.tensorflow.op.image.HsvToRgb;
import org.tensorflow.op.image.NonMaxSuppression;
import org.tensorflow.op.image.NonMaxSuppressionWithOverlaps;
import org.tensorflow.op.image.QuantizedResizeBilinear;
import org.tensorflow.op.image.RandomCrop;
import org.tensorflow.op.image.ResizeArea;
import org.tensorflow.op.image.ResizeBicubic;
import org.tensorflow.op.image.ResizeBilinear;
import org.tensorflow.op.image.ResizeNearestNeighbor;
import org.tensorflow.op.image.RgbToHsv;
import org.tensorflow.op.image.SampleDistortedBoundingBox;
import org.tensorflow.op.image.ScaleAndTranslate;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code image} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class ImageOps {
  private final Scope scope;

  private final Ops ops;

  ImageOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Adjust the contrast of one or more images.
   *  <p>
   *  `images` is a tensor of at least 3 dimensions.  The last 3 dimensions are
   *  interpreted as `[height, width, channels]`.  The other dimensions only
   *  represent a collection of images, such as `[batch, height, width, channels].`
   *  <p>
   *  Contrast is adjusted independently for each channel of each image.
   *  <p>
   *  For each channel, the Op first computes the mean of the image pixels in the
   *  channel and then adjusts each component of each pixel to
   *  `(x - mean) * contrast_factor + mean`.
   *
   * @param <T> data type for {@code output()} output
   * @param images Images to adjust.  At least 3-D.
   * @param contrastFactor A float multiplier for adjusting contrast.
   * @return a new instance of AdjustContrast
   */
  public <T extends TNumber> AdjustContrast<T> adjustContrast(Operand<T> images,
      Operand<TFloat32> contrastFactor) {
    return AdjustContrast.create(scope, images, contrastFactor);
  }

  /**
   * Adjust the hue of one or more images.
   *  <p>
   *  `images` is a tensor of at least 3 dimensions.  The last dimension is
   *  interpreted as channels, and must be three.
   *  <p>
   *  The input image is considered in the RGB colorspace. Conceptually, the RGB
   *  colors are first mapped into HSV. A delta is then applied all the hue values,
   *  and then remapped back to RGB colorspace.
   *
   * @param <T> data type for {@code output()} output
   * @param images Images to adjust.  At least 3-D.
   * @param delta A float delta to add to the hue.
   * @return a new instance of AdjustHue
   */
  public <T extends TNumber> AdjustHue<T> adjustHue(Operand<T> images, Operand<TFloat32> delta) {
    return AdjustHue.create(scope, images, delta);
  }

  /**
   * Adjust the saturation of one or more images.
   *  <p>
   *  `images` is a tensor of at least 3 dimensions.  The last dimension is
   *  interpreted as channels, and must be three.
   *  <p>
   *  The input image is considered in the RGB colorspace. Conceptually, the RGB
   *  colors are first mapped into HSV. A scale is then applied all the saturation
   *  values, and then remapped back to RGB colorspace.
   *
   * @param <T> data type for {@code output()} output
   * @param images Images to adjust.  At least 3-D.
   * @param scale A float scale to add to the saturation.
   * @return a new instance of AdjustSaturation
   */
  public <T extends TNumber> AdjustSaturation<T> adjustSaturation(Operand<T> images,
      Operand<TFloat32> scale) {
    return AdjustSaturation.create(scope, images, scale);
  }

  /**
   * Greedily selects a subset of bounding boxes in descending order of score,
   *  <p>
   *  This operation performs non_max_suppression on the inputs per batch, across
   *  all classes.
   *  Prunes away boxes that have high intersection-over-union (IOU) overlap
   *  with previously selected boxes.  Bounding boxes are supplied as
   *  [y1, x1, y2, x2], where (y1, x1) and (y2, x2) are the coordinates of any
   *  diagonal pair of box corners and the coordinates can be provided as normalized
   *  (i.e., lying in the interval [0, 1]) or absolute.  Note that this algorithm
   *  is agnostic to where the origin is in the coordinate system. Also note that
   *  this algorithm is invariant to orthogonal transformations and translations
   *  of the coordinate system; thus translating or reflections of the coordinate
   *  system result in the same boxes being selected by the algorithm.
   *  The output of this operation is the final boxes, scores and classes tensor
   *  returned after performing non_max_suppression.
   *
   * @param boxes A 4-D float tensor of shape `[batch_size, num_boxes, q, 4]`. If `q` is 1 then
   *  same boxes are used for all classes otherwise, if `q` is equal to number of
   *  classes, class-specific boxes are used.
   * @param scores A 3-D float tensor of shape `[batch_size, num_boxes, num_classes]`
   *  representing a single score corresponding to each box (each row of boxes).
   * @param maxOutputSizePerClass A scalar integer tensor representing the maximum number of
   *  boxes to be selected by non max suppression per class
   * @param maxTotalSize A scalar representing maximum number of boxes retained over all classes.
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   *  boxes overlap too much with respect to IOU.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   *  boxes based on score.
   * @param options carries optional attributes values
   * @return a new instance of CombinedNonMaxSuppression
   */
  public CombinedNonMaxSuppression combinedNonMaxSuppression(Operand<TFloat32> boxes,
      Operand<TFloat32> scores, Operand<TInt32> maxOutputSizePerClass, Operand<TInt32> maxTotalSize,
      Operand<TFloat32> iouThreshold, Operand<TFloat32> scoreThreshold,
      CombinedNonMaxSuppression.Options... options) {
    return CombinedNonMaxSuppression.create(scope, boxes, scores, maxOutputSizePerClass, maxTotalSize, iouThreshold, scoreThreshold, options);
  }

  /**
   * Extracts crops from the input image tensor and resizes them.
   *  <p>
   *  Extracts crops from the input image tensor and resizes them using bilinear
   *  sampling or nearest neighbor sampling (possibly with aspect ratio change) to a
   *  common output size specified by `crop_size`. This is more general than the
   *  `crop_to_bounding_box` op which extracts a fixed size slice from the input image
   *  and does not allow resizing or aspect ratio change.
   *  <p>
   *  Returns a tensor with `crops` from the input `image` at positions defined at the
   *  bounding box locations in `boxes`. The cropped boxes are all resized (with
   *  bilinear or nearest neighbor interpolation) to a fixed
   *  `size = [crop_height, crop_width]`. The result is a 4-D tensor
   *  `[num_boxes, crop_height, crop_width, depth]`. The resizing is corner aligned.
   *  In particular, if `boxes = [[0, 0, 1, 1]]`, the method will give identical
   *  results to using `tf.image.resize_bilinear()` or
   *  `tf.image.resize_nearest_neighbor()`(depends on the `method` argument) with
   *  `align_corners=True`.
   *
   * @param image A 4-D tensor of shape `[batch, image_height, image_width, depth]`.
   *  Both `image_height` and `image_width` need to be positive.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   *  specifies the coordinates of a box in the `box_ind[i]` image and is specified
   *  in normalized coordinates `[y1, x1, y2, x2]`. A normalized coordinate value of
   *  `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
   *  `[0, 1]` interval of normalized image height is mapped to
   *  `[0, image_height - 1]` in image height coordinates. We do allow `y1` > `y2`, in
   *  which case the sampled crop is an up-down flipped version of the original
   *  image. The width dimension is treated similarly. Normalized coordinates
   *  outside the `[0, 1]` range are allowed, in which case we use
   *  `extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   *  The value of `box_ind[i]` specifies the image that the `i`-th box refers to.
   * @param cropSize A 1-D tensor of 2 elements, `size = [crop_height, crop_width]`. All
   *  cropped image patches are resized to this size. The aspect ratio of the image
   *  content is not preserved. Both `crop_height` and `crop_width` need to be
   *  positive.
   * @param options carries optional attributes values
   * @return a new instance of CropAndResize
   */
  public <T extends TNumber> CropAndResize cropAndResize(Operand<T> image, Operand<TFloat32> boxes,
      Operand<TInt32> boxInd, Operand<TInt32> cropSize, CropAndResize.Options... options) {
    return CropAndResize.create(scope, image, boxes, boxInd, cropSize, options);
  }

  /**
   * Computes the gradient of the crop_and_resize op wrt the input boxes tensor.
   *
   * @param grads A 4-D tensor of shape `[num_boxes, crop_height, crop_width, depth]`.
   * @param image A 4-D tensor of shape `[batch, image_height, image_width, depth]`.
   *  Both `image_height` and `image_width` need to be positive.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   *  specifies the coordinates of a box in the `box_ind[i]` image and is specified
   *  in normalized coordinates `[y1, x1, y2, x2]`. A normalized coordinate value of
   *  `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
   *  `[0, 1]` interval of normalized image height is mapped to
   *  `[0, image_height - 1] in image height coordinates. We do allow y1 > y2, in
   *  which case the sampled crop is an up-down flipped version of the original
   *  image. The width dimension is treated similarly. Normalized coordinates
   *  outside the `[0, 1]` range are allowed, in which case we use
   *  `extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   *  The value of `box_ind[i]` specifies the image that the `i`-th box refers to.
   * @param options carries optional attributes values
   * @return a new instance of CropAndResizeGradBoxes
   */
  public <T extends TNumber> CropAndResizeGradBoxes cropAndResizeGradBoxes(Operand<TFloat32> grads,
      Operand<T> image, Operand<TFloat32> boxes, Operand<TInt32> boxInd,
      CropAndResizeGradBoxes.Options... options) {
    return CropAndResizeGradBoxes.create(scope, grads, image, boxes, boxInd, options);
  }

  /**
   * Computes the gradient of the crop_and_resize op wrt the input image tensor.
   *
   * @param <T> data type for {@code output()} output
   * @param grads A 4-D tensor of shape `[num_boxes, crop_height, crop_width, depth]`.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   *  specifies the coordinates of a box in the `box_ind[i]` image and is specified
   *  in normalized coordinates `[y1, x1, y2, x2]`. A normalized coordinate value of
   *  `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
   *  `[0, 1]` interval of normalized image height is mapped to
   *  `[0, image_height - 1] in image height coordinates. We do allow y1 > y2, in
   *  which case the sampled crop is an up-down flipped version of the original
   *  image. The width dimension is treated similarly. Normalized coordinates
   *  outside the `[0, 1]` range are allowed, in which case we use
   *  `extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   *  The value of `box_ind[i]` specifies the image that the `i`-th box refers to.
   * @param imageSize A 1-D tensor with value `[batch, image_height, image_width, depth]`
   *  containing the original image size. Both `image_height` and `image_width` need
   *  to be positive.
   * @param T
   * @param options carries optional attributes values
   * @return a new instance of CropAndResizeGradImage
   */
  public <T extends TNumber> CropAndResizeGradImage<T> cropAndResizeGradImage(
      Operand<TFloat32> grads, Operand<TFloat32> boxes, Operand<TInt32> boxInd,
      Operand<TInt32> imageSize, Class<T> T, CropAndResizeGradImage.Options... options) {
    return CropAndResizeGradImage.create(scope, grads, boxes, boxInd, imageSize, T, options);
  }

  /**
   * Decode and Crop a JPEG-encoded image to a uint8 tensor.
   *  <p>
   *  The attr `channels` indicates the desired number of color channels for the
   *  decoded image.
   *  <p>
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
   *  <p>
   *  The attr `ratio` allows downscaling the image by an integer factor during
   *  decoding.  Allowed values are: 1, 2, 4, and 8.  This is much faster than
   *  downscaling the image later.
   *  <p>
   *  It is equivalent to a combination of decode and crop, but much faster by only
   *  decoding partial jpeg image.
   *
   * @param contents 0-D.  The JPEG-encoded image.
   * @param cropWindow 1-D.  The crop window: [crop_y, crop_x, crop_height, crop_width].
   * @param options carries optional attributes values
   * @return a new instance of DecodeAndCropJpeg
   */
  public DecodeAndCropJpeg decodeAndCropJpeg(Operand<TString> contents, Operand<TInt32> cropWindow,
      DecodeAndCropJpeg.Options... options) {
    return DecodeAndCropJpeg.create(scope, contents, cropWindow, options);
  }

  /**
   * Decode the first frame of a BMP-encoded image to a uint8 tensor.
   *  <p>
   *  The attr `channels` indicates the desired number of color channels for the
   *  decoded image.
   *  <p>
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
   */
  public DecodeBmp decodeBmp(Operand<TString> contents, DecodeBmp.Options... options) {
    return DecodeBmp.create(scope, contents, options);
  }

  /**
   * Decode the frame(s) of a GIF-encoded image to a uint8 tensor.
   *  <p>
   *  GIF images with frame or transparency compression are not supported.
   *  On Linux and MacOS systems, convert animated GIFs from compressed to
   *  uncompressed by running:
   *  <p>
   *      convert $src.gif -coalesce $dst.gif
   *  <p>
   *  This op also supports decoding JPEGs and PNGs, though it is cleaner to use
   *  `tf.io.decode_image`.
   *
   * @param contents 0-D.  The GIF-encoded image.
   * @return a new instance of DecodeGif
   */
  public DecodeGif decodeGif(Operand<TString> contents) {
    return DecodeGif.create(scope, contents);
  }

  /**
   * Decode a JPEG-encoded image to a uint8 tensor.
   *  <p>
   *  The attr `channels` indicates the desired number of color channels for the
   *  decoded image.
   *  <p>
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
   *  <p>
   *  The attr `ratio` allows downscaling the image by an integer factor during
   *  decoding.  Allowed values are: 1, 2, 4, and 8.  This is much faster than
   *  downscaling the image later.
   *  <p>
   *  This op also supports decoding PNGs and non-animated GIFs since the interface is
   *  the same, though it is cleaner to use `tf.io.decode_image`.
   *
   * @param contents 0-D.  The JPEG-encoded image.
   * @param options carries optional attributes values
   * @return a new instance of DecodeJpeg
   */
  public DecodeJpeg decodeJpeg(Operand<TString> contents, DecodeJpeg.Options... options) {
    return DecodeJpeg.create(scope, contents, options);
  }

  /**
   * Decode a PNG-encoded image to a uint8 or uint16 tensor.
   *  <p>
   *  The attr `channels` indicates the desired number of color channels for the
   *  decoded image.
   *  <p>
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
   *  <p>
   *  This op also supports decoding JPEGs and non-animated GIFs since the interface
   *  is the same, though it is cleaner to use `tf.io.decode_image`.
   *
   * @param <T> data type for {@code image()} output
   * @param contents 0-D.  The PNG-encoded image.
   * @param options carries optional attributes values
   * @return a new instance of DecodePng
   */
  public DecodePng<TUint8> decodePng(Operand<TString> contents, DecodePng.Options... options) {
    return DecodePng.create(scope, contents, options);
  }

  /**
   * Decode a PNG-encoded image to a uint8 or uint16 tensor.
   *  <p>
   *  The attr `channels` indicates the desired number of color channels for the
   *  decoded image.
   *  <p>
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
   *  <p>
   *  This op also supports decoding JPEGs and non-animated GIFs since the interface
   *  is the same, though it is cleaner to use `tf.io.decode_image`.
   *
   * @param <T> data type for {@code image()} output
   * @param contents 0-D.  The PNG-encoded image.
   * @param dtype
   * @param options carries optional attributes values
   * @return a new instance of DecodePng
   */
  public <T extends TNumber> DecodePng<T> decodePng(Operand<TString> contents, Class<T> dtype,
      DecodePng.Options... options) {
    return DecodePng.create(scope, contents, dtype, options);
  }

  /**
   * Draw bounding boxes on a batch of images.
   *  <p>
   *  Outputs a copy of `images` but draws on top of the pixels zero or more bounding
   *  boxes specified by the locations in `boxes`. The coordinates of the each
   *  bounding box in `boxes` are encoded as `[y_min, x_min, y_max, x_max]`. The
   *  bounding box coordinates are floats in `[0.0, 1.0]` relative to the width and
   *  height of the underlying image.
   *  <p>
   *  For example, if an image is 100 x 200 pixels (height x width) and the bounding
   *  box is `[0.1, 0.2, 0.5, 0.9]`, the upper-left and bottom-right coordinates of
   *  the bounding box will be `(40, 10)` to `(100, 50)` (in (x,y) coordinates).
   *  <p>
   *  Parts of the bounding box may fall outside the image.
   *
   * @param <T> data type for {@code output()} output
   * @param images 4-D with shape `[batch, height, width, depth]`. A batch of images.
   * @param boxes 3-D with shape `[batch, num_bounding_boxes, 4]` containing bounding
   *  boxes.
   * @param colors 2-D. A list of RGBA colors to cycle through for the boxes.
   * @return a new instance of DrawBoundingBoxes
   */
  public <T extends TNumber> DrawBoundingBoxes<T> drawBoundingBoxes(Operand<T> images,
      Operand<TFloat32> boxes, Operand<TFloat32> colors) {
    return DrawBoundingBoxes.create(scope, images, boxes, colors);
  }

  /**
   * JPEG-encode an image.
   *  <p>
   *  `image` is a 3-D uint8 Tensor of shape `[height, width, channels]`.
   *  <p>
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
   * @param image 3-D with shape `[height, width, channels]`.
   * @param options carries optional attributes values
   * @return a new instance of EncodeJpeg
   */
  public EncodeJpeg encodeJpeg(Operand<TUint8> image, EncodeJpeg.Options... options) {
    return EncodeJpeg.create(scope, image, options);
  }

  /**
   * JPEG encode input image with provided compression quality.
   *  <p>
   *  `image` is a 3-D uint8 Tensor of shape `[height, width, channels]`.
   *  `quality` is an int32 jpeg compression quality value between 0 and 100.
   *
   * @param images Images to adjust.  At least 3-D.
   * @param quality An int quality to encode to.
   * @return a new instance of EncodeJpegVariableQuality
   */
  public EncodeJpegVariableQuality encodeJpegVariableQuality(Operand<TUint8> images,
      Operand<TInt32> quality) {
    return EncodeJpegVariableQuality.create(scope, images, quality);
  }

  /**
   * PNG-encode an image.
   *  <p>
   *  `image` is a 3-D uint8 or uint16 Tensor of shape `[height, width, channels]`
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
   * @param image 3-D with shape `[height, width, channels]`.
   * @param options carries optional attributes values
   * @return a new instance of EncodePng
   */
  public <T extends TNumber> EncodePng encodePng(Operand<T> image, EncodePng.Options... options) {
    return EncodePng.create(scope, image, options);
  }

  /**
   * Extract `patches` from `images` and put them in the "depth" output dimension.
   *
   * @param <T> data type for {@code patches()} output
   * @param images 4-D Tensor with shape `[batch, in_rows, in_cols, depth]`.
   * @param ksizes The size of the sliding window for each dimension of `images`.
   * @param strides How far the centers of two consecutive patches are in
   *  the images. Must be: `[1, stride_rows, stride_cols, 1]`.
   * @param rates Must be: `[1, rate_rows, rate_cols, 1]`. This is the
   *  input stride, specifying how far two consecutive patch samples are in the
   *  input. Equivalent to extracting patches with
   *  `patch_sizes_eff = patch_sizes + (patch_sizes - 1) * (rates - 1)`, followed by
   *  subsampling them spatially by a factor of `rates`. This is equivalent to
   *  `rate` in dilated (a.k.a. Atrous) convolutions.
   * @param padding The type of padding algorithm to use.
   * @return a new instance of ExtractImagePatches
   */
  public <T extends TType> ExtractImagePatches<T> extractImagePatches(Operand<T> images,
      List<Long> ksizes, List<Long> strides, List<Long> rates, String padding) {
    return ExtractImagePatches.create(scope, images, ksizes, strides, rates, padding);
  }

  /**
   * Extract the shape information of a JPEG-encoded image.
   *  <p>
   *  This op only parses the image header, so it is much faster than DecodeJpeg.
   *
   * @param <T> data type for {@code imageShape()} output
   * @param contents 0-D. The JPEG-encoded image.
   * @return a new instance of ExtractJpegShape
   */
  public ExtractJpegShape<TInt32> extractJpegShape(Operand<TString> contents) {
    return ExtractJpegShape.create(scope, contents);
  }

  /**
   * Extract the shape information of a JPEG-encoded image.
   *  <p>
   *  This op only parses the image header, so it is much faster than DecodeJpeg.
   *
   * @param <T> data type for {@code imageShape()} output
   * @param contents 0-D. The JPEG-encoded image.
   * @param outputType (Optional) The output type of the operation (int32 or int64).
   *  Defaults to int32.
   * @return a new instance of ExtractJpegShape
   */
  public <T extends TNumber> ExtractJpegShape<T> extractJpegShape(Operand<TString> contents,
      Class<T> outputType) {
    return ExtractJpegShape.create(scope, contents, outputType);
  }

  /**
   * Convert one or more images from HSV to RGB.
   *  <p>
   *  Outputs a tensor of the same shape as the `images` tensor, containing the RGB
   *  value of the pixels. The output is only well defined if the value in `images`
   *  are in `[0,1]`.
   *  <p>
   *  See `rgb_to_hsv` for a description of the HSV encoding.
   *
   * @param <T> data type for {@code output()} output
   * @param images 1-D or higher rank. HSV data to convert. Last dimension must be size 3.
   * @return a new instance of HsvToRgb
   */
  public <T extends TNumber> HsvToRgb<T> hsvToRgb(Operand<T> images) {
    return HsvToRgb.create(scope, images);
  }

  /**
   * Greedily selects a subset of bounding boxes in descending order of score,
   *  <p>
   *  pruning away boxes that have high intersection-over-union (IOU) overlap
   *  with previously selected boxes.  Bounding boxes with score less than
   *  `score_threshold` are removed.  Bounding boxes are supplied as
   *  [y1, x1, y2, x2], where (y1, x1) and (y2, x2) are the coordinates of any
   *  diagonal pair of box corners and the coordinates can be provided as normalized
   *  (i.e., lying in the interval [0, 1]) or absolute.  Note that this algorithm
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
   * @param <T> data type for {@code selectedScores()} output
   * @param boxes A 2-D float tensor of shape `[num_boxes, 4]`.
   * @param scores A 1-D float tensor of shape `[num_boxes]` representing a single
   *  score corresponding to each box (each row of boxes).
   * @param maxOutputSize A scalar integer tensor representing the maximum number of
   *  boxes to be selected by non max suppression.
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   *  boxes overlap too much with respect to IOU.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   *  boxes based on score.
   * @param softNmsSigma A 0-D float tensor representing the sigma parameter for Soft NMS; see Bodla et
   *  al (c.f. https://arxiv.org/abs/1704.04503).  When `soft_nms_sigma=0.0` (which
   *  is default), we fall back to standard (hard) NMS.
   * @param options carries optional attributes values
   * @return a new instance of NonMaxSuppression
   */
  public <T extends TNumber> NonMaxSuppression<T> nonMaxSuppression(Operand<T> boxes,
      Operand<T> scores, Operand<TInt32> maxOutputSize, Operand<T> iouThreshold,
      Operand<T> scoreThreshold, Operand<T> softNmsSigma, NonMaxSuppression.Options... options) {
    return NonMaxSuppression.create(scope, boxes, scores, maxOutputSize, iouThreshold, scoreThreshold, softNmsSigma, options);
  }

  /**
   * Greedily selects a subset of bounding boxes in descending order of score,
   *  <p>
   *  pruning away boxes that have high overlaps
   *  with previously selected boxes.  Bounding boxes with score less than
   *  `score_threshold` are removed. N-by-n overlap values are supplied as square matrix,
   *  which allows for defining a custom overlap criterium (eg. intersection over union,
   *  intersection over area, etc.).
   *  <p>
   *  The output of this operation is a set of integers indexing into the input
   *  collection of bounding boxes representing the selected boxes.  The bounding
   *  box coordinates corresponding to the selected indices can then be obtained
   *  using the `tf.gather operation`.  For example:
   *  <p>
   *    selected_indices = tf.image.non_max_suppression_with_overlaps(
   *        overlaps, scores, max_output_size, overlap_threshold, score_threshold)
   *    selected_boxes = tf.gather(boxes, selected_indices)
   *
   * @param overlaps A 2-D float tensor of shape `[num_boxes, num_boxes]` representing
   *  the n-by-n box overlap values.
   * @param scores A 1-D float tensor of shape `[num_boxes]` representing a single
   *  score corresponding to each box (each row of boxes).
   * @param maxOutputSize A scalar integer tensor representing the maximum number of
   *  boxes to be selected by non max suppression.
   * @param overlapThreshold A 0-D float tensor representing the threshold for deciding whether
   *  boxes overlap too.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   *  boxes based on score.
   * @return a new instance of NonMaxSuppressionWithOverlaps
   */
  public NonMaxSuppressionWithOverlaps nonMaxSuppressionWithOverlaps(Operand<TFloat32> overlaps,
      Operand<TFloat32> scores, Operand<TInt32> maxOutputSize, Operand<TFloat32> overlapThreshold,
      Operand<TFloat32> scoreThreshold) {
    return NonMaxSuppressionWithOverlaps.create(scope, overlaps, scores, maxOutputSize, overlapThreshold, scoreThreshold);
  }

  /**
   * Resize quantized `images` to `size` using quantized bilinear interpolation.
   *  <p>
   *  Input images and output images must be quantized types.
   *
   * @param <T> data type for {@code resizedImages()} output
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   *  new size for the images.
   * @param min
   * @param max
   * @param options carries optional attributes values
   * @return a new instance of QuantizedResizeBilinear
   */
  public <T extends TType> QuantizedResizeBilinear<T> quantizedResizeBilinear(Operand<T> images,
      Operand<TInt32> size, Operand<TFloat32> min, Operand<TFloat32> max,
      QuantizedResizeBilinear.Options... options) {
    return QuantizedResizeBilinear.create(scope, images, size, min, max, options);
  }

  /**
   * Randomly crop `image`.
   *  <p>
   *  `size` is a 1-D int64 tensor with 2 elements representing the crop height and
   *  width.  The values must be non negative.
   *  <p>
   *  This Op picks a random location in `image` and crops a `height` by `width`
   *  rectangle from that location.  The random location is picked so the cropped
   *  area will fit inside the original image.
   *
   * @param <T> data type for {@code output()} output
   * @param image 3-D of shape `[height, width, channels]`.
   * @param size 1-D of length 2 containing: `crop_height`, `crop_width`..
   * @param options carries optional attributes values
   * @return a new instance of RandomCrop
   */
  public <T extends TNumber> RandomCrop<T> randomCrop(Operand<T> image, Operand<TInt64> size,
      RandomCrop.Options... options) {
    return RandomCrop.create(scope, image, size, options);
  }

  /**
   * Resize `images` to `size` using area interpolation.
   *  <p>
   *  Input images can be of different types but output images are always float.
   *  <p>
   *  The range of pixel values for the output image might be slightly different
   *  from the range for the input image because of limited numerical precision.
   *  To guarantee an output range, for example `[0.0, 1.0]`, apply
   *  `tf.clip_by_value` to the output.
   *  <p>
   *  Each output pixel is computed by first transforming the pixel's footprint into
   *  the input tensor and then averaging the pixels that intersect the footprint. An
   *  input pixel's contribution to the average is weighted by the fraction of its
   *  area that intersects the footprint.  This is the same as OpenCV's INTER_AREA.
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   *  new size for the images.
   * @param options carries optional attributes values
   * @return a new instance of ResizeArea
   */
  public <T extends TNumber> ResizeArea resizeArea(Operand<T> images, Operand<TInt32> size,
      ResizeArea.Options... options) {
    return ResizeArea.create(scope, images, size, options);
  }

  /**
   * Resize `images` to `size` using bicubic interpolation.
   *  <p>
   *  Input images can be of different types but output images are always float.
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   *  new size for the images.
   * @param options carries optional attributes values
   * @return a new instance of ResizeBicubic
   */
  public <T extends TNumber> ResizeBicubic resizeBicubic(Operand<T> images, Operand<TInt32> size,
      ResizeBicubic.Options... options) {
    return ResizeBicubic.create(scope, images, size, options);
  }

  /**
   * Resize `images` to `size` using bilinear interpolation.
   *  <p>
   *  Input images can be of different types but output images are always float.
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   *  new size for the images.
   * @param options carries optional attributes values
   * @return a new instance of ResizeBilinear
   */
  public <T extends TNumber> ResizeBilinear resizeBilinear(Operand<T> images, Operand<TInt32> size,
      ResizeBilinear.Options... options) {
    return ResizeBilinear.create(scope, images, size, options);
  }

  /**
   * Resize `images` to `size` using nearest neighbor interpolation.
   *
   * @param <T> data type for {@code resizedImages()} output
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   *  new size for the images.
   * @param options carries optional attributes values
   * @return a new instance of ResizeNearestNeighbor
   */
  public <T extends TNumber> ResizeNearestNeighbor<T> resizeNearestNeighbor(Operand<T> images,
      Operand<TInt32> size, ResizeNearestNeighbor.Options... options) {
    return ResizeNearestNeighbor.create(scope, images, size, options);
  }

  /**
   * Converts one or more images from RGB to HSV.
   *  <p>
   *  Outputs a tensor of the same shape as the `images` tensor, containing the HSV
   *  value of the pixels. The output is only well defined if the value in `images`
   *  are in `[0,1]`.
   *  <p>
   *  `output[..., 0]` contains hue, `output[..., 1]` contains saturation, and
   *  `output[..., 2]` contains value. All HSV values are in `[0,1]`. A hue of 0
   *  corresponds to pure red, hue 1/3 is pure green, and 2/3 is pure blue.
   *  <p>
   *  Usage Example:
   *  <p>
   *  >>> blue_image = tf.stack([
   *  ...    tf.zeros([5,5]),
   *  ...    tf.zeros([5,5]),
   *  ...    tf.ones([5,5])],
   *  ...    axis=-1)
   *  >>> blue_hsv_image = tf.image.rgb_to_hsv(blue_image)
   *  >>> blue_hsv_image[0,0].numpy()
   *  array([0.6666667, 1. , 1. ], dtype=float32)
   *
   * @param <T> data type for {@code output()} output
   * @param images 1-D or higher rank. RGB data to convert. Last dimension must be size 3.
   * @return a new instance of RgbToHsv
   */
  public <T extends TNumber> RgbToHsv<T> rgbToHsv(Operand<T> images) {
    return RgbToHsv.create(scope, images);
  }

  /**
   * Generate a single randomly distorted bounding box for an image.
   *  <p>
   *  Bounding box annotations are often supplied in addition to ground-truth labels
   *  in image recognition or object localization tasks. A common technique for
   *  training such a system is to randomly distort an image while preserving
   *  its content, i.e. <i>data augmentation</i>. This Op outputs a randomly distorted
   *  localization of an object, i.e. bounding box, given an `image_size`,
   *  `bounding_boxes` and a series of constraints.
   *  <p>
   *  The output of this Op is a single bounding box that may be used to crop the
   *  original image. The output is returned as 3 tensors: `begin`, `size` and
   *  `bboxes`. The first 2 tensors can be fed directly into `tf.slice` to crop the
   *  image. The latter may be supplied to `tf.image.draw_bounding_boxes` to visualize
   *  what the bounding box looks like.
   *  <p>
   *  Bounding boxes are supplied and returned as `[y_min, x_min, y_max, x_max]`. The
   *  bounding box coordinates are floats in `[0.0, 1.0]` relative to the width and
   *  height of the underlying image.
   *  <p>
   *  For example,
   *  <pre>{@code
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
   *  }</pre>
   *  Note that if no bounding box information is available, setting
   *  `use_image_if_no_bounding_boxes = true` will assume there is a single implicit
   *  bounding box covering the whole image. If `use_image_if_no_bounding_boxes` is
   *  false and no bounding boxes are supplied, an error is raised.
   *
   * @param <T> data type for {@code begin()} output
   * @param imageSize 1-D, containing `[height, width, channels]`.
   * @param boundingBoxes 3-D with shape `[batch, N, 4]` describing the N bounding boxes
   *  associated with the image.
   * @param minObjectCovered The cropped area of the image must contain at least this
   *  fraction of any bounding box supplied. The value of this parameter should be
   *  non-negative. In the case of 0, the cropped area does not need to overlap
   *  any of the bounding boxes supplied.
   * @param options carries optional attributes values
   * @return a new instance of SampleDistortedBoundingBox
   */
  public <T extends TNumber> SampleDistortedBoundingBox<T> sampleDistortedBoundingBox(
      Operand<T> imageSize, Operand<TFloat32> boundingBoxes, Operand<TFloat32> minObjectCovered,
      SampleDistortedBoundingBox.Options... options) {
    return SampleDistortedBoundingBox.create(scope, imageSize, boundingBoxes, minObjectCovered, options);
  }

  /**
   *
   * @param images
   * @param size
   * @param scale
   * @param translation
   * @param options carries optional attributes values
   * @return a new instance of ScaleAndTranslate
   */
  public <T extends TNumber> ScaleAndTranslate scaleAndTranslate(Operand<T> images,
      Operand<TInt32> size, Operand<TFloat32> scale, Operand<TFloat32> translation,
      ScaleAndTranslate.Options... options) {
    return ScaleAndTranslate.create(scope, images, size, scale, translation, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
