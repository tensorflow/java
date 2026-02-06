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
import org.tensorflow.op.image.DecodeImage;
import org.tensorflow.op.image.DecodeJpeg;
import org.tensorflow.op.image.DecodePng;
import org.tensorflow.op.image.DecodeWebP;
import org.tensorflow.op.image.DrawBoundingBoxes;
import org.tensorflow.op.image.EncodeJpeg;
import org.tensorflow.op.image.EncodeJpegVariableQuality;
import org.tensorflow.op.image.EncodePng;
import org.tensorflow.op.image.ExtractGlimpse;
import org.tensorflow.op.image.ExtractImagePatches;
import org.tensorflow.op.image.ExtractJpegShape;
import org.tensorflow.op.image.GenerateBoundingBoxProposals;
import org.tensorflow.op.image.HsvToRgb;
import org.tensorflow.op.image.ImageProjectiveTransformV2;
import org.tensorflow.op.image.ImageProjectiveTransformV3;
import org.tensorflow.op.image.NearestNeighbors;
import org.tensorflow.op.image.NonMaxSuppression;
import org.tensorflow.op.image.NonMaxSuppressionWithOverlaps;
import org.tensorflow.op.image.QuantizedResizeBilinear;
import org.tensorflow.op.image.RandomCrop;
import org.tensorflow.op.image.ResizeArea;
import org.tensorflow.op.image.ResizeBicubic;
import org.tensorflow.op.image.ResizeBicubicGrad;
import org.tensorflow.op.image.ResizeBilinear;
import org.tensorflow.op.image.ResizeBilinearGrad;
import org.tensorflow.op.image.ResizeNearestNeighbor;
import org.tensorflow.op.image.ResizeNearestNeighborGrad;
import org.tensorflow.op.image.RgbToHsv;
import org.tensorflow.op.image.SampleDistortedBoundingBox;
import org.tensorflow.op.image.ScaleAndTranslate;
import org.tensorflow.op.image.ScaleAndTranslateGrad;
import org.tensorflow.op.image.StatelessSampleDistortedBoundingBox;
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
 * @see Ops
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
   *  {@code images} is a tensor of at least 3 dimensions.  The last 3 dimensions are
   *  interpreted as {@code [height, width, channels]}.  The other dimensions only
   *  represent a collection of images, such as {@code [batch, height, width, channels].}
   *  <p>Contrast is adjusted independently for each channel of each image.
   *  <p>For each channel, the Op first computes the mean of the image pixels in the
   *  channel and then adjusts each component of each pixel to
   *  {@code (x - mean) * contrast_factor + mean}.
   *
   * @param images Images to adjust.  At least 3-D.
   * @param contrastFactor A float multiplier for adjusting contrast.
   * @param <T> data type for {@code AdjustContrastv2} output and operands
   * @return a new instance of AdjustContrast
   */
  public <T extends TNumber> AdjustContrast<T> adjustContrast(Operand<T> images,
      Operand<TFloat32> contrastFactor) {
    return AdjustContrast.create(scope, images, contrastFactor);
  }

  /**
   * Adjust the hue of one or more images.
   *  {@code images} is a tensor of at least 3 dimensions.  The last dimension is
   *  interpreted as channels, and must be three.
   *  <p>The input image is considered in the RGB colorspace. Conceptually, the RGB
   *  colors are first mapped into HSV. A delta is then applied all the hue values,
   *  and then remapped back to RGB colorspace.
   *
   * @param images Images to adjust.  At least 3-D.
   * @param delta A float delta to add to the hue.
   * @param <T> data type for {@code AdjustHue} output and operands
   * @return a new instance of AdjustHue
   */
  public <T extends TNumber> AdjustHue<T> adjustHue(Operand<T> images, Operand<TFloat32> delta) {
    return AdjustHue.create(scope, images, delta);
  }

  /**
   * Adjust the saturation of one or more images.
   *  {@code images} is a tensor of at least 3 dimensions.  The last dimension is
   *  interpreted as channels, and must be three.
   *  <p>The input image is considered in the RGB colorspace. Conceptually, the RGB
   *  colors are first mapped into HSV. A scale is then applied all the saturation
   *  values, and then remapped back to RGB colorspace.
   *
   * @param images Images to adjust.  At least 3-D.
   * @param scale A float scale to add to the saturation.
   * @param <T> data type for {@code AdjustSaturation} output and operands
   * @return a new instance of AdjustSaturation
   */
  public <T extends TNumber> AdjustSaturation<T> adjustSaturation(Operand<T> images,
      Operand<TFloat32> scale) {
    return AdjustSaturation.create(scope, images, scale);
  }

  /**
   * Greedily selects a subset of bounding boxes in descending order of score,
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
   * @param boxes A 4-D float tensor of shape {@code [batch_size, num_boxes, q, 4]}. If {@code q} is 1 then
   *  same boxes are used for all classes otherwise, if {@code q} is equal to number of
   *  classes, class-specific boxes are used.
   * @param scores A 3-D float tensor of shape {@code [batch_size, num_boxes, num_classes]}
   *  representing a single score corresponding to each box (each row of boxes).
   * @param maxOutputSizePerClass A scalar integer tensor representing the maximum number of
   *  boxes to be selected by non max suppression per class
   * @param maxTotalSize An int32 scalar representing the maximum number of boxes retained over all
   *  classes. Note that setting this value to a large number may result in OOM error
   *  depending on the system workload.
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   *  boxes overlap too much with respect to IOU.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   *  boxes based on score.
   * @param options carries optional attribute values
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
   *  Extracts crops from the input image tensor and resizes them using bilinear
   *  sampling or nearest neighbor sampling (possibly with aspect ratio change) to a
   *  common output size specified by {@code crop_size}. This is more general than the
   *  {@code crop_to_bounding_box} op which extracts a fixed size slice from the input image
   *  and does not allow resizing or aspect ratio change.
   *  <p>Returns a tensor with {@code crops} from the input {@code image} at positions defined at the
   *  bounding box locations in {@code boxes}. The cropped boxes are all resized (with
   *  bilinear or nearest neighbor interpolation) to a fixed
   *  {@code size = [crop_height, crop_width]}. The result is a 4-D tensor
   *  {@code [num_boxes, crop_height, crop_width, depth]}. The resizing is corner aligned.
   *  In particular, if {@code boxes = [[0, 0, 1, 1]]}, the method will give identical
   *  results to using {@code tf.image.resize_bilinear()} or
   *  {@code tf.image.resize_nearest_neighbor()}(depends on the {@code method} argument) with
   *  {@code align_corners=True}.
   *
   * @param image A 4-D tensor of shape {@code [batch, image_height, image_width, depth]}.
   *  Both {@code image_height} and {@code image_width} need to be positive.
   * @param boxes A 2-D tensor of shape {@code [num_boxes, 4]}. The {@code i}-th row of the tensor
   *  specifies the coordinates of a box in the {@code box_ind[i]} image and is specified
   *  in normalized coordinates {@code [y1, x1, y2, x2]}. A normalized coordinate value of
   *  {@code y} is mapped to the image coordinate at {@code y * (image_height - 1)}, so as the
   *  {@code [0, 1]} interval of normalized image height is mapped to
   *  {@code [0, image_height - 1]} in image height coordinates. We do allow {@code y1} &gt; {@code y2}, in
   *  which case the sampled crop is an up-down flipped version of the original
   *  image. The width dimension is treated similarly. Normalized coordinates
   *  outside the {@code [0, 1]} range are allowed, in which case we use
   *  {@code extrapolation_value} to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape {@code [num_boxes]} with int32 values in {@code [0, batch)}.
   *  The value of {@code box_ind[i]} specifies the image that the {@code i}-th box refers to.
   * @param cropSize A 1-D tensor of 2 elements, {@code size = [crop_height, crop_width]}. All
   *  cropped image patches are resized to this size. The aspect ratio of the image
   *  content is not preserved. Both {@code crop_height} and {@code crop_width} need to be
   *  positive.
   * @param options carries optional attribute values
   * @return a new instance of CropAndResize
   */
  public CropAndResize cropAndResize(Operand<? extends TNumber> image, Operand<TFloat32> boxes,
      Operand<TInt32> boxInd, Operand<TInt32> cropSize, CropAndResize.Options... options) {
    return CropAndResize.create(scope, image, boxes, boxInd, cropSize, options);
  }

  /**
   * Computes the gradient of the crop_and_resize op wrt the input boxes tensor.
   *
   * @param grads A 4-D tensor of shape {@code [num_boxes, crop_height, crop_width, depth]}.
   * @param image A 4-D tensor of shape {@code [batch, image_height, image_width, depth]}.
   *  Both {@code image_height} and {@code image_width} need to be positive.
   * @param boxes A 2-D tensor of shape {@code [num_boxes, 4]}. The {@code i}-th row of the tensor
   *  specifies the coordinates of a box in the {@code box_ind[i]} image and is specified
   *  in normalized coordinates {@code [y1, x1, y2, x2]}. A normalized coordinate value of
   *  {@code y} is mapped to the image coordinate at {@code y * (image_height - 1)}, so as the
   *  {@code [0, 1]} interval of normalized image height is mapped to
   *  {@code [0, image_height - 1] in image height coordinates. We do allow y1 > y2, in which case the sampled crop is an up-down flipped version of the original image. The width dimension is treated similarly. Normalized coordinates outside the }[0, 1]{@code range are allowed, in which case we use}extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape {@code [num_boxes]} with int32 values in {@code [0, batch)}.
   *  The value of {@code box_ind[i]} specifies the image that the {@code i}-th box refers to.
   * @param options carries optional attribute values
   * @return a new instance of CropAndResizeGradBoxes
   */
  public CropAndResizeGradBoxes cropAndResizeGradBoxes(Operand<TFloat32> grads,
      Operand<? extends TNumber> image, Operand<TFloat32> boxes, Operand<TInt32> boxInd,
      CropAndResizeGradBoxes.Options... options) {
    return CropAndResizeGradBoxes.create(scope, grads, image, boxes, boxInd, options);
  }

  /**
   * Computes the gradient of the crop_and_resize op wrt the input image tensor.
   *
   * @param grads A 4-D tensor of shape {@code [num_boxes, crop_height, crop_width, depth]}.
   * @param boxes A 2-D tensor of shape {@code [num_boxes, 4]}. The {@code i}-th row of the tensor
   *  specifies the coordinates of a box in the {@code box_ind[i]} image and is specified
   *  in normalized coordinates {@code [y1, x1, y2, x2]}. A normalized coordinate value of
   *  {@code y} is mapped to the image coordinate at {@code y * (image_height - 1)}, so as the
   *  {@code [0, 1]} interval of normalized image height is mapped to
   *  {@code [0, image_height - 1] in image height coordinates. We do allow y1 > y2, in which case the sampled crop is an up-down flipped version of the original image. The width dimension is treated similarly. Normalized coordinates outside the }[0, 1]{@code range are allowed, in which case we use}extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape {@code [num_boxes]} with int32 values in {@code [0, batch)}.
   *  The value of {@code box_ind[i]} specifies the image that the {@code i}-th box refers to.
   * @param imageSize A 1-D tensor with value {@code [batch, image_height, image_width, depth]}
   *  containing the original image size. Both {@code image_height} and {@code image_width} need
   *  to be positive.
   * @param T The value of the T attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code CropAndResizeGradImage} output and operands
   * @return a new instance of CropAndResizeGradImage
   */
  public <T extends TNumber> CropAndResizeGradImage<T> cropAndResizeGradImage(
      Operand<TFloat32> grads, Operand<TFloat32> boxes, Operand<TInt32> boxInd,
      Operand<TInt32> imageSize, Class<T> T, CropAndResizeGradImage.Options... options) {
    return CropAndResizeGradImage.create(scope, grads, boxes, boxInd, imageSize, T, options);
  }

  /**
   * Decode and Crop a JPEG-encoded image to a uint8 tensor.
   *  The attr {@code channels} indicates the desired number of color channels for the
   *  decoded image.
   *  <p>Accepted values are:
   *  <ul>
   *  <li>0: Use the number of channels in the JPEG-encoded image.</li>
   *  <li>1: output a grayscale image.</li>
   *  <li>3: output an RGB image.</li>
   *  </ul>
   *  <p>If needed, the JPEG-encoded image is transformed to match the requested number
   *  of color channels.
   *  <p>The attr {@code ratio} allows downscaling the image by an integer factor during
   *  decoding.  Allowed values are: 1, 2, 4, and 8.  This is much faster than
   *  downscaling the image later.
   *  <p>It is equivalent to a combination of decode and crop, but much faster by only
   *  decoding partial jpeg image.
   *
   * @param contents 0-D.  The JPEG-encoded image.
   * @param cropWindow 1-D.  The crop window: [crop_y, crop_x, crop_height, crop_width].
   * @param options carries optional attribute values
   * @return a new instance of DecodeAndCropJpeg
   */
  public DecodeAndCropJpeg decodeAndCropJpeg(Operand<TString> contents, Operand<TInt32> cropWindow,
      DecodeAndCropJpeg.Options... options) {
    return DecodeAndCropJpeg.create(scope, contents, cropWindow, options);
  }

  /**
   * Decode the first frame of a BMP-encoded image to a uint8 tensor.
   *  The attr {@code channels} indicates the desired number of color channels for the
   *  decoded image.
   *  <p>Accepted values are:
   *  <ul>
   *  <li>0: Use the number of channels in the BMP-encoded image.</li>
   *  <li>3: output an RGB image.</li>
   *  <li>4: output an RGBA image.</li>
   *  </ul>
   *
   * @param contents 0-D.  The BMP-encoded image.
   * @param options carries optional attribute values
   * @return a new instance of DecodeBmp
   */
  public DecodeBmp decodeBmp(Operand<TString> contents, DecodeBmp.Options... options) {
    return DecodeBmp.create(scope, contents, options);
  }

  /**
   * Decode the frame(s) of a GIF-encoded image to a uint8 tensor.
   *  GIF images with frame or transparency compression are not supported.
   *  On Linux and MacOS systems, convert animated GIFs from compressed to
   *  uncompressed by running:
   *  <pre>
   *  convert $src.gif -coalesce $dst.gif
   *  </pre>
   *  <p>This op also supports decoding JPEGs and PNGs, though it is cleaner to use
   *  {@code tf.io.decode_image}.
   *
   * @param contents 0-D.  The GIF-encoded image.
   * @return a new instance of DecodeGif
   */
  public DecodeGif decodeGif(Operand<TString> contents) {
    return DecodeGif.create(scope, contents);
  }

  /**
   * Function for decode_bmp, decode_gif, decode_jpeg, decode_webp, and decode_png.
   *  Detects whether an image is a BMP, GIF, JPEG, WebP, or PNG, and performs the
   *  appropriate operation to convert the input bytes string into a Tensor of type
   *  dtype.
   *  <p><em>NOTE</em>: decode_gif and decode_webp return a 4-D
   *  array [num_frames, height, width, 3], as opposed to decode_bmp,
   *  decode_jpeg, and decode_png, which always return 3-D arrays [height,
   *  width, num_channels]. Make sure to take this into account when
   *  constructing your graph if you are intermixing animated files with
   *  BMP, JPEG, and/or PNG files. Alternately, set the expand_animations
   *  argument of this function to False, in which case the op will return
   *  3-dimensional tensors and will truncate animations to the first frame.
   *  <p><em>NOTE</em>: If the first frame of an animated GIF does not occupy the entire
   *  canvas (maximum frame width x maximum frame height), then it fills the
   *  unoccupied areas (in the first frame) with zeros (black). For frames after the
   *  first frame that does not occupy the entire canvas, it uses the previous
   *  frame to fill the unoccupied areas.
   *
   * @param contents 0-D. The encoded image bytes.
   * @param options carries optional attribute values
   * @return a new instance of DecodeImage, with default output types
   */
  public DecodeImage<TUint8> decodeImage(Operand<TString> contents,
      DecodeImage.Options... options) {
    return DecodeImage.create(scope, contents, options);
  }

  /**
   * Function for decode_bmp, decode_gif, decode_jpeg, decode_webp, and decode_png.
   *  Detects whether an image is a BMP, GIF, JPEG, WebP, or PNG, and performs the
   *  appropriate operation to convert the input bytes string into a Tensor of type
   *  dtype.
   *  <p><em>NOTE</em>: decode_gif and decode_webp return a 4-D
   *  array [num_frames, height, width, 3], as opposed to decode_bmp,
   *  decode_jpeg, and decode_png, which always return 3-D arrays [height,
   *  width, num_channels]. Make sure to take this into account when
   *  constructing your graph if you are intermixing animated files with
   *  BMP, JPEG, and/or PNG files. Alternately, set the expand_animations
   *  argument of this function to False, in which case the op will return
   *  3-dimensional tensors and will truncate animations to the first frame.
   *  <p><em>NOTE</em>: If the first frame of an animated GIF does not occupy the entire
   *  canvas (maximum frame width x maximum frame height), then it fills the
   *  unoccupied areas (in the first frame) with zeros (black). For frames after the
   *  first frame that does not occupy the entire canvas, it uses the previous
   *  frame to fill the unoccupied areas.
   *
   * @param contents 0-D. The encoded image bytes.
   * @param dtype The desired DType of the returned Tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DecodeImage} output and operands
   * @return a new instance of DecodeImage
   */
  public <T extends TNumber> DecodeImage<T> decodeImage(Operand<TString> contents, Class<T> dtype,
      DecodeImage.Options... options) {
    return DecodeImage.create(scope, contents, dtype, options);
  }

  /**
   * Decode a JPEG-encoded image to a uint8 tensor.
   *  The attr {@code channels} indicates the desired number of color channels for the
   *  decoded image.
   *  <p>Accepted values are:
   *  <ul>
   *  <li>0: Use the number of channels in the JPEG-encoded image.</li>
   *  <li>1: output a grayscale image.</li>
   *  <li>3: output an RGB image.</li>
   *  </ul>
   *  <p>If needed, the JPEG-encoded image is transformed to match the requested number
   *  of color channels.
   *  <p>The attr {@code ratio} allows downscaling the image by an integer factor during
   *  decoding.  Allowed values are: 1, 2, 4, and 8.  This is much faster than
   *  downscaling the image later.
   *  <p>This op also supports decoding PNGs and non-animated GIFs since the interface is
   *  the same, though it is cleaner to use {@code tf.io.decode_image}.
   *
   * @param contents 0-D.  The JPEG-encoded image.
   * @param options carries optional attribute values
   * @return a new instance of DecodeJpeg
   */
  public DecodeJpeg decodeJpeg(Operand<TString> contents, DecodeJpeg.Options... options) {
    return DecodeJpeg.create(scope, contents, options);
  }

  /**
   * Decode a PNG-encoded image to a uint8 or uint16 tensor.
   *  The attr {@code channels} indicates the desired number of color channels for the
   *  decoded image.
   *  <p>Accepted values are:
   *  <ul>
   *  <li>0: Use the number of channels in the PNG-encoded image.</li>
   *  <li>1: output a grayscale image.</li>
   *  <li>3: output an RGB image.</li>
   *  <li>4: output an RGBA image.</li>
   *  </ul>
   *  <p>If needed, the PNG-encoded image is transformed to match the requested number
   *  of color channels.
   *  <p>This op also supports decoding JPEGs and non-animated GIFs since the interface
   *  is the same, though it is cleaner to use {@code tf.io.decode_image}.
   *
   * @param contents 0-D.  The PNG-encoded image.
   * @param options carries optional attribute values
   * @return a new instance of DecodePng, with default output types
   */
  public DecodePng<TUint8> decodePng(Operand<TString> contents, DecodePng.Options... options) {
    return DecodePng.create(scope, contents, options);
  }

  /**
   * Decode a PNG-encoded image to a uint8 or uint16 tensor.
   *  The attr {@code channels} indicates the desired number of color channels for the
   *  decoded image.
   *  <p>Accepted values are:
   *  <ul>
   *  <li>0: Use the number of channels in the PNG-encoded image.</li>
   *  <li>1: output a grayscale image.</li>
   *  <li>3: output an RGB image.</li>
   *  <li>4: output an RGBA image.</li>
   *  </ul>
   *  <p>If needed, the PNG-encoded image is transformed to match the requested number
   *  of color channels.
   *  <p>This op also supports decoding JPEGs and non-animated GIFs since the interface
   *  is the same, though it is cleaner to use {@code tf.io.decode_image}.
   *
   * @param contents 0-D.  The PNG-encoded image.
   * @param dtype The value of the dtype attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code DecodePng} output and operands
   * @return a new instance of DecodePng
   */
  public <T extends TNumber> DecodePng<T> decodePng(Operand<TString> contents, Class<T> dtype,
      DecodePng.Options... options) {
    return DecodePng.create(scope, contents, dtype, options);
  }

  /**
   * Decode a WebP-encoded image to a uint8 tensor.
   *  The attr {@code channels} indicates the desired number of color channels for the
   *  decoded image.
   *  <p>Accepted values are:
   *  <ul>
   *  <li>0: Use the number of channels in the WebP-encoded image.</li>
   *  <li>3: output an RGB image.</li>
   *  <li>4: output an RGBA image.</li>
   *  </ul>
   *  <p>The number of channels must currently match that of the underlying file.
   *  For WebP animations, only 4-channel RGBA is supported.
   *
   * @param contents 0-D.  The WebP-encoded image.
   * @param options carries optional attribute values
   * @return a new instance of DecodeWebP, with default output types
   */
  public DecodeWebP<TUint8> decodeWebP(Operand<TString> contents, DecodeWebP.Options... options) {
    return DecodeWebP.create(scope, contents, options);
  }

  /**
   * Decode a WebP-encoded image to a uint8 tensor.
   *  The attr {@code channels} indicates the desired number of color channels for the
   *  decoded image.
   *  <p>Accepted values are:
   *  <ul>
   *  <li>0: Use the number of channels in the WebP-encoded image.</li>
   *  <li>3: output an RGB image.</li>
   *  <li>4: output an RGBA image.</li>
   *  </ul>
   *  <p>The number of channels must currently match that of the underlying file.
   *  For WebP animations, only 4-channel RGBA is supported.
   *
   * @param contents 0-D.  The WebP-encoded image.
   * @param dtype The value of the dtype attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code DecodeWebP} output and operands
   * @return a new instance of DecodeWebP
   */
  public <T extends TNumber> DecodeWebP<T> decodeWebP(Operand<TString> contents, Class<T> dtype,
      DecodeWebP.Options... options) {
    return DecodeWebP.create(scope, contents, dtype, options);
  }

  /**
   * Draw bounding boxes on a batch of images.
   *  Outputs a copy of {@code images} but draws on top of the pixels zero or more bounding
   *  boxes specified by the locations in {@code boxes}. The coordinates of the each
   *  bounding box in {@code boxes} are encoded as {@code [y_min, x_min, y_max, x_max]}. The
   *  bounding box coordinates are floats in {@code [0.0, 1.0]} relative to the width and
   *  height of the underlying image.
   *  <p>For example, if an image is 100 x 200 pixels (height x width) and the bounding
   *  box is {@code [0.1, 0.2, 0.5, 0.9]}, the upper-left and bottom-right coordinates of
   *  the bounding box will be {@code (40, 10)} to {@code (100, 50)} (in (x,y) coordinates).
   *  <p>Parts of the bounding box may fall outside the image.
   *
   * @param images 4-D with shape {@code [batch, height, width, depth]}. A batch of images.
   * @param boxes 3-D with shape {@code [batch, num_bounding_boxes, 4]} containing bounding
   *  boxes.
   * @param colors 2-D. A list of RGBA colors to cycle through for the boxes.
   * @param <T> data type for {@code DrawBoundingBoxesV2} output and operands
   * @return a new instance of DrawBoundingBoxes
   */
  public <T extends TNumber> DrawBoundingBoxes<T> drawBoundingBoxes(Operand<T> images,
      Operand<TFloat32> boxes, Operand<TFloat32> colors) {
    return DrawBoundingBoxes.create(scope, images, boxes, colors);
  }

  /**
   * JPEG-encode an image.
   *  {@code image} is a 3-D uint8 Tensor of shape {@code [height, width, channels]}.
   *  <p>The attr {@code format} can be used to override the color format of the encoded
   *  output.  Values can be:
   *  <ul>
   *  <li>{@code ''}: Use a default format based on the number of channels in the image.</li>
   *  <li>{@code grayscale}: Output a grayscale JPEG image.  The {@code channels} dimension
   *  of {@code image} must be 1.</li>
   *  <li>{@code rgb}: Output an RGB JPEG image. The {@code channels} dimension
   *  of {@code image} must be 3.</li>
   *  </ul>
   *  <p>If {@code format} is not specified or is the empty string, a default format is picked
   *  in function of the number of channels in {@code image}:
   *  <ul>
   *  <li>1: Output a grayscale image.</li>
   *  <li>3: Output an RGB image.</li>
   *  </ul>
   *
   * @param image 3-D with shape {@code [height, width, channels]}.
   * @param options carries optional attribute values
   * @return a new instance of EncodeJpeg
   */
  public EncodeJpeg encodeJpeg(Operand<TUint8> image, EncodeJpeg.Options... options) {
    return EncodeJpeg.create(scope, image, options);
  }

  /**
   * JPEG encode input image with provided compression quality.
   *  {@code image} is a 3-D uint8 Tensor of shape {@code [height, width, channels]}.
   *  {@code quality} is an int32 jpeg compression quality value between 0 and 100.
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
   *  {@code image} is a 3-D uint8 or uint16 Tensor of shape {@code [height, width, channels]}
   *  where {@code channels} is:
   *  <ul>
   *  <li>1: for grayscale.</li>
   *  <li>2: for grayscale + alpha.</li>
   *  <li>3: for RGB.</li>
   *  <li>4: for RGBA.</li>
   *  </ul>
   *  <p>The ZLIB compression level, {@code compression}, can be -1 for the PNG-encoder
   *  default or a value from 0 to 9.  9 is the highest compression level, generating
   *  the smallest output, but is slower.
   *
   * @param image 3-D with shape {@code [height, width, channels]}.
   * @param options carries optional attribute values
   * @return a new instance of EncodePng
   */
  public EncodePng encodePng(Operand<? extends TNumber> image, EncodePng.Options... options) {
    return EncodePng.create(scope, image, options);
  }

  /**
   * Extracts a glimpse from the input tensor.
   *  Returns a set of windows called glimpses extracted at location
   *  {@code offsets} from the input tensor. If the windows only partially
   *  overlaps the inputs, the non overlapping areas will be filled with
   *  random noise.
   *  <p>The result is a 4-D tensor of shape {@code [batch_size, glimpse_height, glimpse_width, channels]}. The channels and batch dimensions are the
   *  same as that of the input tensor. The height and width of the output
   *  windows are specified in the {@code size} parameter.
   *  <p>The argument {@code normalized} and {@code centered} controls how the windows are built:
   *  <ul>
   *  <li>If the coordinates are normalized but not centered, 0.0 and 1.0
   *  correspond to the minimum and maximum of each height and width
   *  dimension.</li>
   *  <li>If the coordinates are both normalized and centered, they range from
   *  -1.0 to 1.0. The coordinates (-1.0, -1.0) correspond to the upper
   *  left corner, the lower right corner is located at (1.0, 1.0) and the
   *  center is at (0, 0).</li>
   *  <li>If the coordinates are not normalized they are interpreted as
   *  numbers of pixels.</li>
   *  </ul>
   *
   * @param input A 4-D float tensor of shape {@code [batch_size, height, width, channels]}.
   * @param sizeOutput A 1-D tensor of 2 elements containing the size of the glimpses
   *  to extract.  The glimpse height must be specified first, following
   *  by the glimpse width.
   * @param offsets A 2-D integer tensor of shape {@code [batch_size, 2]} containing
   *  the y, x locations of the center of each window.
   * @param options carries optional attribute values
   * @return a new instance of ExtractGlimpse
   */
  public ExtractGlimpse extractGlimpse(Operand<TFloat32> input, Operand<TInt32> sizeOutput,
      Operand<TFloat32> offsets, ExtractGlimpse.Options... options) {
    return ExtractGlimpse.create(scope, input, sizeOutput, offsets, options);
  }

  /**
   * Extract {@code patches} from {@code images} and put them in the &quot;depth&quot; output dimension.
   *
   * @param images 4-D Tensor with shape {@code [batch, in_rows, in_cols, depth]}.
   * @param ksizes The size of the sliding window for each dimension of {@code images}.
   * @param strides How far the centers of two consecutive patches are in
   *  the images. Must be: {@code [1, stride_rows, stride_cols, 1]}.
   * @param rates Must be: {@code [1, rate_rows, rate_cols, 1]}. This is the
   *  input stride, specifying how far two consecutive patch samples are in the
   *  input. Equivalent to extracting patches with
   *  {@code patch_sizes_eff = patch_sizes + (patch_sizes - 1) * (rates - 1)}, followed by
   *  subsampling them spatially by a factor of {@code rates}. This is equivalent to
   *  {@code rate} in dilated (a.k.a. Atrous) convolutions.
   * @param padding The type of padding algorithm to use.
   * @param <T> data type for {@code ExtractImagePatches} output and operands
   * @return a new instance of ExtractImagePatches
   */
  public <T extends TType> ExtractImagePatches<T> extractImagePatches(Operand<T> images,
      List<Long> ksizes, List<Long> strides, List<Long> rates, String padding) {
    return ExtractImagePatches.create(scope, images, ksizes, strides, rates, padding);
  }

  /**
   * Extract the shape information of a JPEG-encoded image.
   *  This op only parses the image header, so it is much faster than DecodeJpeg.
   *
   * @param contents 0-D. The JPEG-encoded image.
   * @return a new instance of ExtractJpegShape, with default output types
   */
  public ExtractJpegShape<TInt32> extractJpegShape(Operand<TString> contents) {
    return ExtractJpegShape.create(scope, contents);
  }

  /**
   * Extract the shape information of a JPEG-encoded image.
   *  This op only parses the image header, so it is much faster than DecodeJpeg.
   *
   * @param contents 0-D. The JPEG-encoded image.
   * @param outputType (Optional) The output type of the operation (int32 or int64).
   *  Defaults to int32.
   * @param <T> data type for {@code ExtractJpegShape} output and operands
   * @return a new instance of ExtractJpegShape
   */
  public <T extends TNumber> ExtractJpegShape<T> extractJpegShape(Operand<TString> contents,
      Class<T> outputType) {
    return ExtractJpegShape.create(scope, contents, outputType);
  }

  /**
   * This op produces Region of Interests from given bounding boxes(bbox_deltas) encoded wrt anchors according to eq.2 in arXiv:1506.01497
   *  <pre>
   *    The op selects top `pre_nms_topn` scoring boxes, decodes them with respect to anchors,
   *    applies non-maximal suppression on overlapping boxes with higher than
   *    `nms_threshold` intersection-over-union (iou) value, discarding boxes where shorter
   *    side is less than `min_size`.
   *    Inputs:
   *    `scores`: A 4D tensor of shape [Batch, Height, Width, Num Anchors] containing the scores per anchor at given position
   *    `bbox_deltas`: is a tensor of shape [Batch, Height, Width, 4 x Num Anchors] boxes encoded to each anchor
   *    `anchors`: A 1D tensor of shape [4 x Num Anchors], representing the anchors.
   *    Outputs:
   *    `rois`: output RoIs, a 3D tensor of shape [Batch, post_nms_topn, 4], padded by 0 if less than post_nms_topn candidates found.
   *    `roi_probabilities`: probability scores of each roi in 'rois', a 2D tensor of shape [Batch,post_nms_topn], padded with 0 if needed, sorted by scores.
   *  </pre>
   *
   * @param scores A 4-D float tensor of shape {@code [num_images, height, width, num_achors]} containing scores of the boxes for given anchors, can be unsorted.
   * @param bboxDeltas A 4-D float tensor of shape {@code [num_images, height, width, 4 x num_anchors]}. encoding boxes with respec to each anchor.
   *  Coordinates are given in the form [dy, dx, dh, dw].
   * @param imageInfo A 2-D float tensor of shape {@code [num_images, 5]} containing image information Height, Width, Scale.
   * @param anchors A 2-D float tensor of shape {@code [num_anchors, 4]} describing the anchor boxes. Boxes are formatted in the form [y1, x1, y2, x2].
   * @param nmsThreshold A scalar float tensor for non-maximal-suppression threshold.
   * @param preNmsTopn A scalar int tensor for the number of top scoring boxes to be used as input.
   * @param minSize A scalar float tensor. Any box that has a smaller size than min_size will be discarded.
   * @param options carries optional attribute values
   * @return a new instance of GenerateBoundingBoxProposals
   */
  public GenerateBoundingBoxProposals generateBoundingBoxProposals(Operand<TFloat32> scores,
      Operand<TFloat32> bboxDeltas, Operand<TFloat32> imageInfo, Operand<TFloat32> anchors,
      Operand<TFloat32> nmsThreshold, Operand<TInt32> preNmsTopn, Operand<TFloat32> minSize,
      GenerateBoundingBoxProposals.Options... options) {
    return GenerateBoundingBoxProposals.create(scope, scores, bboxDeltas, imageInfo, anchors, nmsThreshold, preNmsTopn, minSize, options);
  }

  /**
   * Convert one or more images from HSV to RGB.
   *  Outputs a tensor of the same shape as the {@code images} tensor, containing the RGB
   *  value of the pixels. The output is only well defined if the value in {@code images}
   *  are in {@code [0,1]}.
   *  <p>See {@code rgb_to_hsv} for a description of the HSV encoding.
   *
   * @param images 1-D or higher rank. HSV data to convert. Last dimension must be size 3.
   * @param <T> data type for {@code HSVToRGB} output and operands
   * @return a new instance of HsvToRgb
   */
  public <T extends TNumber> HsvToRgb<T> hsvToRgb(Operand<T> images) {
    return HsvToRgb.create(scope, images);
  }

  /**
   * Applies the given transform to each of the images.
   *  If one row of {@code transforms} is {@code [a0, a1, a2, b0, b1, b2, c0, c1]}, then it maps
   *  the <em>output</em> point {@code (x, y)} to a transformed <em>input</em> point
   *  {@code (x', y') = ((a0 x + a1 y + a2) / k, (b0 x + b1 y + b2) / k)}, where
   *  {@code k = c0 x + c1 y + 1}. If the transformed point lays outside of the input
   *  image, the output pixel is set to 0.
   *
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param transforms 2-D Tensor, {@code [batch, 8]} or {@code [1, 8]} matrix, where each row corresponds to a 3 x 3
   *  projective transformation matrix, with the last entry assumed to be 1. If there
   *  is one row, the same transformation will be applied to all images.
   * @param outputShape 1-D Tensor [new_height, new_width].
   * @param interpolation Interpolation method, &quot;NEAREST&quot; or &quot;BILINEAR&quot;.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ImageProjectiveTransformV2} output and operands
   * @return a new instance of ImageProjectiveTransformV2
   */
  public <T extends TNumber> ImageProjectiveTransformV2<T> imageProjectiveTransformV2(
      Operand<T> images, Operand<TFloat32> transforms, Operand<TInt32> outputShape,
      String interpolation, ImageProjectiveTransformV2.Options... options) {
    return ImageProjectiveTransformV2.create(scope, images, transforms, outputShape, interpolation, options);
  }

  /**
   * Applies the given transform to each of the images.
   *  If one row of {@code transforms} is {@code [a0, a1, a2, b0, b1, b2, c0, c1]}, then it maps
   *  the <em>output</em> point {@code (x, y)} to a transformed <em>input</em> point
   *  {@code (x', y') = ((a0 x + a1 y + a2) / k, (b0 x + b1 y + b2) / k)}, where
   *  {@code k = c0 x + c1 y + 1}. If the transformed point lays outside of the input
   *  image, the output pixel is set to fill_value.
   *
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param transforms 2-D Tensor, {@code [batch, 8]} or {@code [1, 8]} matrix, where each row corresponds to a 3 x 3
   *  projective transformation matrix, with the last entry assumed to be 1. If there
   *  is one row, the same transformation will be applied to all images.
   * @param outputShape 1-D Tensor [new_height, new_width].
   * @param fillValue float, the value to be filled when fill_mode is constant&quot;.
   * @param interpolation Interpolation method, &quot;NEAREST&quot; or &quot;BILINEAR&quot;.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ImageProjectiveTransformV3} output and operands
   * @return a new instance of ImageProjectiveTransformV3
   */
  public <T extends TNumber> ImageProjectiveTransformV3<T> imageProjectiveTransformV3(
      Operand<T> images, Operand<TFloat32> transforms, Operand<TInt32> outputShape,
      Operand<TFloat32> fillValue, String interpolation,
      ImageProjectiveTransformV3.Options... options) {
    return ImageProjectiveTransformV3.create(scope, images, transforms, outputShape, fillValue, interpolation, options);
  }

  /**
   * Selects the k nearest centers for each point.
   *  Rows of points are assumed to be input points. Rows of centers are assumed to be
   *  the list of candidate centers. For each point, the k centers that have least L2
   *  distance to it are computed.
   *
   * @param points Matrix of shape (n, d). Rows are assumed to be input points.
   * @param centers Matrix of shape (m, d). Rows are assumed to be centers.
   * @param k Number of nearest centers to return for each point. If k is larger than m, then
   *  only m centers are returned.
   * @return a new instance of NearestNeighbors
   */
  public NearestNeighbors nearestNeighbors(Operand<TFloat32> points, Operand<TFloat32> centers,
      Operand<TInt64> k) {
    return NearestNeighbors.create(scope, points, centers, k);
  }

  /**
   * Greedily selects a subset of bounding boxes in descending order of score,
   *  pruning away boxes that have high intersection-over-union (IOU) overlap
   *  with previously selected boxes.  Bounding boxes with score less than
   *  {@code score_threshold} are removed.  Bounding boxes are supplied as
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
   *  using the {@code tf.gather operation}.  For example:
   *  selected_indices = tf.image.non_max_suppression_v2(
   *  boxes, scores, max_output_size, iou_threshold, score_threshold)
   *  selected_boxes = tf.gather(boxes, selected_indices)
   *  This op also supports a Soft-NMS (with Gaussian weighting) mode (c.f.
   *  Bodla et al, https://arxiv.org/abs/1704.04503) where boxes reduce the score
   *  of other overlapping boxes instead of directly causing them to be pruned.
   *  To enable this Soft-NMS mode, set the {@code soft_nms_sigma} parameter to be
   *  larger than 0.
   *
   * @param boxes A 2-D float tensor of shape {@code [num_boxes, 4]}.
   * @param scores A 1-D float tensor of shape {@code [num_boxes]} representing a single
   *  score corresponding to each box (each row of boxes).
   * @param maxOutputSize A scalar integer tensor representing the maximum number of
   *  boxes to be selected by non max suppression.
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   *  boxes overlap too much with respect to IOU.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   *  boxes based on score.
   * @param softNmsSigma A 0-D float tensor representing the sigma parameter for Soft NMS; see Bodla et
   *  al (c.f. https://arxiv.org/abs/1704.04503).  When {@code soft_nms_sigma=0.0} (which
   *  is default), we fall back to standard (hard) NMS.
   * @param options carries optional attribute values
   * @param <T> data type for {@code NonMaxSuppressionV5} output and operands
   * @return a new instance of NonMaxSuppression
   */
  public <T extends TNumber> NonMaxSuppression<T> nonMaxSuppression(Operand<T> boxes,
      Operand<T> scores, Operand<TInt32> maxOutputSize, Operand<T> iouThreshold,
      Operand<T> scoreThreshold, Operand<T> softNmsSigma, NonMaxSuppression.Options... options) {
    return NonMaxSuppression.create(scope, boxes, scores, maxOutputSize, iouThreshold, scoreThreshold, softNmsSigma, options);
  }

  /**
   * Greedily selects a subset of bounding boxes in descending order of score,
   *  pruning away boxes that have high overlaps
   *  with previously selected boxes.  Bounding boxes with score less than
   *  {@code score_threshold} are removed. N-by-n overlap values are supplied as square matrix,
   *  which allows for defining a custom overlap criterium (eg. intersection over union,
   *  intersection over area, etc.).
   *  <p>The output of this operation is a set of integers indexing into the input
   *  collection of bounding boxes representing the selected boxes.  The bounding
   *  box coordinates corresponding to the selected indices can then be obtained
   *  using the {@code tf.gather operation}.  For example:
   *  <p>selected_indices = tf.image.non_max_suppression_with_overlaps(
   *  overlaps, scores, max_output_size, overlap_threshold, score_threshold)
   *  selected_boxes = tf.gather(boxes, selected_indices)
   *
   * @param overlaps A 2-D float tensor of shape {@code [num_boxes, num_boxes]} representing
   *  the n-by-n box overlap values.
   * @param scores A 1-D float tensor of shape {@code [num_boxes]} representing a single
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
   * Resize quantized {@code images} to {@code size} using quantized bilinear interpolation.
   *  Input images and output images must be quantized types.
   *
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   *  new size for the images.
   * @param min The min value
   * @param max The max value
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizedResizeBilinear} output and operands
   * @return a new instance of QuantizedResizeBilinear
   */
  public <T extends TNumber> QuantizedResizeBilinear<T> quantizedResizeBilinear(Operand<T> images,
      Operand<TInt32> sizeOutput, Operand<TFloat32> min, Operand<TFloat32> max,
      QuantizedResizeBilinear.Options... options) {
    return QuantizedResizeBilinear.create(scope, images, sizeOutput, min, max, options);
  }

  /**
   * Randomly crop {@code image}.
   *  {@code size} is a 1-D int64 tensor with 2 elements representing the crop height and
   *  width.  The values must be non negative.
   *  <p>This Op picks a random location in {@code image} and crops a {@code height} by {@code width}
   *  rectangle from that location.  The random location is picked so the cropped
   *  area will fit inside the original image.
   *
   * @param image 3-D of shape {@code [height, width, channels]}.
   * @param sizeOutput 1-D of length 2 containing: {@code crop_height}, {@code crop_width}..
   * @param options carries optional attribute values
   * @param <T> data type for {@code RandomCrop} output and operands
   * @return a new instance of RandomCrop
   */
  public <T extends TNumber> RandomCrop<T> randomCrop(Operand<T> image, Operand<TInt64> sizeOutput,
      RandomCrop.Options... options) {
    return RandomCrop.create(scope, image, sizeOutput, options);
  }

  /**
   * Resize {@code images} to {@code size} using area interpolation.
   *  Input images can be of different types but output images are always float.
   *  <p>The range of pixel values for the output image might be slightly different
   *  from the range for the input image because of limited numerical precision.
   *  To guarantee an output range, for example {@code [0.0, 1.0]}, apply
   *  {@code tf.clip_by_value} to the output.
   *  <p>Each output pixel is computed by first transforming the pixel's footprint into
   *  the input tensor and then averaging the pixels that intersect the footprint. An
   *  input pixel's contribution to the average is weighted by the fraction of its
   *  area that intersects the footprint.  This is the same as OpenCV's INTER_AREA.
   *
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   *  new size for the images.
   * @param options carries optional attribute values
   * @return a new instance of ResizeArea
   */
  public ResizeArea resizeArea(Operand<? extends TNumber> images, Operand<TInt32> sizeOutput,
      ResizeArea.Options... options) {
    return ResizeArea.create(scope, images, sizeOutput, options);
  }

  /**
   * Resize {@code images} to {@code size} using bicubic interpolation.
   *  Input images can be of different types but output images are always float.
   *
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   *  new size for the images.
   * @param options carries optional attribute values
   * @return a new instance of ResizeBicubic
   */
  public ResizeBicubic resizeBicubic(Operand<? extends TNumber> images, Operand<TInt32> sizeOutput,
      ResizeBicubic.Options... options) {
    return ResizeBicubic.create(scope, images, sizeOutput, options);
  }

  /**
   * Computes the gradient of bicubic interpolation.
   *
   * @param grads 4-D with shape {@code [batch, height, width, channels]}.
   * @param originalImage 4-D with shape {@code [batch, orig_height, orig_width, channels]},
   *  The image tensor that was resized.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResizeBicubicGrad} output and operands
   * @return a new instance of ResizeBicubicGrad
   */
  public <T extends TNumber> ResizeBicubicGrad<T> resizeBicubicGrad(Operand<TFloat32> grads,
      Operand<T> originalImage, ResizeBicubicGrad.Options... options) {
    return ResizeBicubicGrad.create(scope, grads, originalImage, options);
  }

  /**
   * Resize {@code images} to {@code size} using bilinear interpolation.
   *  Input images can be of different types but output images are always float.
   *
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   *  new size for the images.
   * @param options carries optional attribute values
   * @return a new instance of ResizeBilinear
   */
  public ResizeBilinear resizeBilinear(Operand<? extends TNumber> images,
      Operand<TInt32> sizeOutput, ResizeBilinear.Options... options) {
    return ResizeBilinear.create(scope, images, sizeOutput, options);
  }

  /**
   * Computes the gradient of bilinear interpolation.
   *
   * @param grads 4-D with shape {@code [batch, height, width, channels]}.
   * @param originalImage 4-D with shape {@code [batch, orig_height, orig_width, channels]},
   *  The image tensor that was resized.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResizeBilinearGrad} output and operands
   * @return a new instance of ResizeBilinearGrad
   */
  public <T extends TNumber> ResizeBilinearGrad<T> resizeBilinearGrad(Operand<TFloat32> grads,
      Operand<T> originalImage, ResizeBilinearGrad.Options... options) {
    return ResizeBilinearGrad.create(scope, grads, originalImage, options);
  }

  /**
   * Resize {@code images} to {@code size} using nearest neighbor interpolation.
   *
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   *  new size for the images.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResizeNearestNeighbor} output and operands
   * @return a new instance of ResizeNearestNeighbor
   */
  public <T extends TNumber> ResizeNearestNeighbor<T> resizeNearestNeighbor(Operand<T> images,
      Operand<TInt32> sizeOutput, ResizeNearestNeighbor.Options... options) {
    return ResizeNearestNeighbor.create(scope, images, sizeOutput, options);
  }

  /**
   * Computes the gradient of nearest neighbor interpolation.
   *
   * @param grads 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code orig_height, orig_width}. The
   *  original input size.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResizeNearestNeighborGrad} output and operands
   * @return a new instance of ResizeNearestNeighborGrad
   */
  public <T extends TNumber> ResizeNearestNeighborGrad<T> resizeNearestNeighborGrad(
      Operand<T> grads, Operand<TInt32> sizeOutput, ResizeNearestNeighborGrad.Options... options) {
    return ResizeNearestNeighborGrad.create(scope, grads, sizeOutput, options);
  }

  /**
   * Converts one or more images from RGB to HSV.
   *  Outputs a tensor of the same shape as the {@code images} tensor, containing the HSV
   *  value of the pixels. The output is only well defined if the value in {@code images}
   *  are in {@code [0,1]}.
   *  <p>{@code output[..., 0]} contains hue, {@code output[..., 1]} contains saturation, and
   *  {@code output[..., 2]} contains value. All HSV values are in {@code [0,1]}. A hue of 0
   *  corresponds to pure red, hue 1/3 is pure green, and 2/3 is pure blue.
   *  <p>Usage Example:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>blue_image = tf.stack([
   *  ...    tf.zeros([5,5]),
   *  ...    tf.zeros([5,5]),
   *  ...    tf.ones([5,5])],
   *  ...    axis=-1)
   *  blue_hsv_image = tf.image.rgb_to_hsv(blue_image)
   *  blue_hsv_image[0,0].numpy()
   *  array([0.6666667, 1. , 1. ], dtype=float32)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param images 1-D or higher rank. RGB data to convert. Last dimension must be size 3.
   * @param <T> data type for {@code RGBToHSV} output and operands
   * @return a new instance of RgbToHsv
   */
  public <T extends TNumber> RgbToHsv<T> rgbToHsv(Operand<T> images) {
    return RgbToHsv.create(scope, images);
  }

  /**
   * Generate a single randomly distorted bounding box for an image.
   *  Bounding box annotations are often supplied in addition to ground-truth labels
   *  in image recognition or object localization tasks. A common technique for
   *  training such a system is to randomly distort an image while preserving
   *  its content, i.e. <em>data augmentation</em>. This Op outputs a randomly distorted
   *  localization of an object, i.e. bounding box, given an {@code image_size},
   *  {@code bounding_boxes} and a series of constraints.
   *  <p>The output of this Op is a single bounding box that may be used to crop the
   *  original image. The output is returned as 3 tensors: {@code begin}, {@code size} and
   *  {@code bboxes}. The first 2 tensors can be fed directly into {@code tf.slice} to crop the
   *  image. The latter may be supplied to {@code tf.image.draw_bounding_boxes} to visualize
   *  what the bounding box looks like.
   *  <p>Bounding boxes are supplied and returned as {@code [y_min, x_min, y_max, x_max]}. The
   *  bounding box coordinates are floats in {@code [0.0, 1.0]} relative to the width and
   *  height of the underlying image.
   *  <p>For example,
   *  <pre>
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
   *  </pre>
   *  <p>Note that if no bounding box information is available, setting
   *  {@code use_image_if_no_bounding_boxes = true} will assume there is a single implicit
   *  bounding box covering the whole image. If {@code use_image_if_no_bounding_boxes} is
   *  false and no bounding boxes are supplied, an error is raised.
   *
   * @param imageSize 1-D, containing {@code [height, width, channels]}.
   * @param boundingBoxes 3-D with shape {@code [batch, N, 4]} describing the N bounding boxes
   *  associated with the image.
   * @param minObjectCovered The cropped area of the image must contain at least this
   *  fraction of any bounding box supplied. The value of this parameter should be
   *  non-negative. In the case of 0, the cropped area does not need to overlap
   *  any of the bounding boxes supplied.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SampleDistortedBoundingBoxV2} output and operands
   * @return a new instance of SampleDistortedBoundingBox
   */
  public <T extends TNumber> SampleDistortedBoundingBox<T> sampleDistortedBoundingBox(
      Operand<T> imageSize, Operand<TFloat32> boundingBoxes, Operand<TFloat32> minObjectCovered,
      SampleDistortedBoundingBox.Options... options) {
    return SampleDistortedBoundingBox.create(scope, imageSize, boundingBoxes, minObjectCovered, options);
  }

  /**
   * The ScaleAndTranslate operation
   *
   * @param images The images value
   * @param sizeOutput The sizeOutput value
   * @param scale The scale value
   * @param translation The translation value
   * @param options carries optional attribute values
   * @return a new instance of ScaleAndTranslate
   */
  public ScaleAndTranslate scaleAndTranslate(Operand<? extends TNumber> images,
      Operand<TInt32> sizeOutput, Operand<TFloat32> scale, Operand<TFloat32> translation,
      ScaleAndTranslate.Options... options) {
    return ScaleAndTranslate.create(scope, images, sizeOutput, scale, translation, options);
  }

  /**
   * The ScaleAndTranslateGrad operation
   *
   * @param grads The grads value
   * @param originalImage The originalImage value
   * @param scale The scale value
   * @param translation The translation value
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScaleAndTranslateGrad} output and operands
   * @return a new instance of ScaleAndTranslateGrad
   */
  public <T extends TNumber> ScaleAndTranslateGrad<T> scaleAndTranslateGrad(Operand<T> grads,
      Operand<T> originalImage, Operand<TFloat32> scale, Operand<TFloat32> translation,
      ScaleAndTranslateGrad.Options... options) {
    return ScaleAndTranslateGrad.create(scope, grads, originalImage, scale, translation, options);
  }

  /**
   * Generate a randomly distorted bounding box for an image deterministically.
   *  Bounding box annotations are often supplied in addition to ground-truth labels
   *  in image recognition or object localization tasks. A common technique for
   *  training such a system is to randomly distort an image while preserving its
   *  content, i.e. <em>data augmentation</em>. This Op, given the same {@code seed},
   *  deterministically outputs a randomly distorted localization of an object, i.e.
   *  bounding box, given an {@code image_size}, {@code bounding_boxes} and a series of
   *  constraints.
   *  <p>The output of this Op is a single bounding box that may be used to crop the
   *  original image. The output is returned as 3 tensors: {@code begin}, {@code size} and
   *  {@code bboxes}. The first 2 tensors can be fed directly into {@code tf.slice} to crop the
   *  image. The latter may be supplied to {@code tf.image.draw_bounding_boxes} to visualize
   *  what the bounding box looks like.
   *  <p>Bounding boxes are supplied and returned as {@code [y_min, x_min, y_max, x_max]}. The
   *  bounding box coordinates are floats in {@code [0.0, 1.0]} relative to the width and
   *  the height of the underlying image.
   *  <p>The output of this Op is guaranteed to be the same given the same {@code seed} and is
   *  independent of how many times the function is called, and independent of global
   *  seed settings (e.g. {@code tf.random.set_seed}).
   *  <p>Example usage:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>image = np.array([[[1], [2], [3]], [[4], [5], [6]], [[7], [8], [9]]])
   *  bbox = tf.constant(
   *  ...   [0.0, 0.0, 1.0, 1.0], dtype=tf.float32, shape=[1, 1, 4])
   *  seed = (1, 2)
   *  <strong>Generate a single distorted bounding box.</strong><br>
   *  <p>bbox_begin, bbox_size, bbox_draw = (
   *  ...   tf.image.stateless_sample_distorted_bounding_box(
   *  ...     tf.shape(image), bounding_boxes=bbox, seed=seed))
   *  <strong>Employ the bounding box to distort the image.</strong><br>
   *  <p>tf.slice(image, bbox_begin, bbox_size)
   *  &lt;tf.Tensor: shape=(2, 2, 1), dtype=int64, numpy=
   *  array([[[1],
   *  [2]],
   *  [[4],
   *  [5]]])&gt;
   *  <strong>Draw the bounding box in an image summary.</strong><br>
   *  <p>colors = np.array([[1.0, 0.0, 0.0], [0.0, 0.0, 1.0]])
   *  tf.image.draw_bounding_boxes(
   *  ...   tf.expand_dims(tf.cast(image, tf.float32),0), bbox_draw, colors)
   *  &lt;tf.Tensor: shape=(1, 3, 3, 1), dtype=float32, numpy=
   *  array([[[[1.],
   *  [1.],
   *  [3.]],
   *  [[1.],
   *  [1.],
   *  [6.]],
   *  [[7.],
   *  [8.],
   *  [9.]]]], dtype=float32)&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *  <p>Note that if no bounding box information is available, setting
   *  {@code use_image_if_no_bounding_boxes = true} will assume there is a single implicit
   *  bounding box covering the whole image. If {@code use_image_if_no_bounding_boxes} is
   *  false and no bounding boxes are supplied, an error is raised.
   *
   * @param imageSize 1-D, containing {@code [height, width, channels]}.
   * @param boundingBoxes 3-D with shape {@code [batch, N, 4]} describing the N bounding boxes
   *  associated with the image.
   * @param minObjectCovered The cropped area of the image must contain at least this
   *  fraction of any bounding box supplied. The value of this parameter should be
   *  non-negative. In the case of 0, the cropped area does not need to overlap
   *  any of the bounding boxes supplied.
   * @param seed 1-D with shape {@code [2]}. The seed to the random number generator. Must have dtype
   *  {@code int32} or {@code int64}. (When using XLA, only {@code int32} is allowed.)
   * @param options carries optional attribute values
   * @param <T> data type for {@code StatelessSampleDistortedBoundingBox} output and operands
   * @return a new instance of StatelessSampleDistortedBoundingBox
   */
  public <T extends TNumber> StatelessSampleDistortedBoundingBox<T> statelessSampleDistortedBoundingBox(
      Operand<T> imageSize, Operand<TFloat32> boundingBoxes, Operand<TFloat32> minObjectCovered,
      Operand<? extends TNumber> seed, StatelessSampleDistortedBoundingBox.Options... options) {
    return StatelessSampleDistortedBoundingBox.create(scope, imageSize, boundingBoxes, minObjectCovered, seed, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
