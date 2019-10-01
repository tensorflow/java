package org.tensorflow.op;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.op.image.AdjustContrast;
import org.tensorflow.op.image.AdjustHue;
import org.tensorflow.op.image.AdjustSaturation;
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
import org.tensorflow.op.image.ExtractGlimpse;
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
import org.tensorflow.types.UInt8;

/**
 * An API for building {@code image} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class ImageOps {
  private final Scope scope;

  ImageOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link AdjustHue} operation
   *
   * @param images Images to adjust.  At least 3-D.
   * @param delta A float delta to add to the hue.
   * @return a new instance of AdjustHue
   * @see org.tensorflow.op.image.AdjustHue
   */
  public <T extends Number> AdjustHue<T> adjustHue(Operand<T> images, Operand<Float> delta) {
    return AdjustHue.create(scope, images, delta);
  }

  /**
   * Builds an {@link EncodePng} operation
   *
   * @param image 3-D with shape `[height, width, channels]`.
   * @param options carries optional attributes values
   * @return a new instance of EncodePng
   * @see org.tensorflow.op.image.EncodePng
   */
  public <T extends Number> EncodePng encodePng(Operand<T> image, EncodePng.Options... options) {
    return EncodePng.create(scope, image, options);
  }

  /**
   * Builds an {@link SampleDistortedBoundingBox} operation
   *
   * @param imageSize 1-D, containing `[height, width, channels]`.
   * @param boundingBoxes 3-D with shape `[batch, N, 4]` describing the N bounding boxes
   * @param minObjectCovered The cropped area of the image must contain at least this
   * @param options carries optional attributes values
   * @return a new instance of SampleDistortedBoundingBox
   * @see org.tensorflow.op.image.SampleDistortedBoundingBox
   */
  public <T extends Number> SampleDistortedBoundingBox<T> sampleDistortedBoundingBox(
      Operand<T> imageSize, Operand<Float> boundingBoxes, Operand<Float> minObjectCovered,
      SampleDistortedBoundingBox.Options... options) {
    return SampleDistortedBoundingBox.create(scope, imageSize, boundingBoxes, minObjectCovered, options);
  }

  /**
   * Builds an {@link DecodeGif} operation
   *
   * @param contents 0-D.  The GIF-encoded image.
   * @return a new instance of DecodeGif
   * @see org.tensorflow.op.image.DecodeGif
   */
  public DecodeGif decodeGif(Operand<String> contents) {
    return DecodeGif.create(scope, contents);
  }

  /**
   * Builds an {@link RandomCrop} operation
   *
   * @param image 3-D of shape `[height, width, channels]`.
   * @param size 1-D of length 2 containing: `crop_height`, `crop_width`..
   * @param options carries optional attributes values
   * @return a new instance of RandomCrop
   * @see org.tensorflow.op.image.RandomCrop
   */
  public <T extends Number> RandomCrop<T> randomCrop(Operand<T> image, Operand<Long> size,
      RandomCrop.Options... options) {
    return RandomCrop.create(scope, image, size, options);
  }

  /**
   * Builds an {@link DecodePng} operation
   *
   * @param contents 0-D.  The PNG-encoded image.
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of DecodePng
   * @see org.tensorflow.op.image.DecodePng
   */
  public <T extends Number> DecodePng<T> decodePng(Operand<String> contents, Class<T> dtype,
      DecodePng.Options... options) {
    return DecodePng.create(scope, contents, dtype, options);
  }

  /**
   * Builds an {@link NonMaxSuppression} operation
   *
   * @param boxes A 2-D float tensor of shape `[num_boxes, 4]`.
   * @param scores A 1-D float tensor of shape `[num_boxes]` representing a single
   * @param maxOutputSize A scalar integer tensor representing the maximum number of
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   * @param options carries optional attributes values
   * @return a new instance of NonMaxSuppression
   * @see org.tensorflow.op.image.NonMaxSuppression
   */
  public <T extends Number, U extends Number> NonMaxSuppression nonMaxSuppression(Operand<T> boxes,
      Operand<T> scores, Operand<Integer> maxOutputSize, Operand<U> iouThreshold,
      Operand<U> scoreThreshold, NonMaxSuppression.Options... options) {
    return NonMaxSuppression.create(scope, boxes, scores, maxOutputSize, iouThreshold, scoreThreshold, options);
  }

  /**
   * Builds an {@link ResizeBilinear} operation
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   * @param options carries optional attributes values
   * @return a new instance of ResizeBilinear
   * @see org.tensorflow.op.image.ResizeBilinear
   */
  public <T extends Number> ResizeBilinear resizeBilinear(Operand<T> images, Operand<Integer> size,
      ResizeBilinear.Options... options) {
    return ResizeBilinear.create(scope, images, size, options);
  }

  /**
   * Builds an {@link DecodePng} operation
   *
   * @param contents 0-D.  The PNG-encoded image.
   * @param options carries optional attributes values
   * @return a new instance of DecodePng
   * @see org.tensorflow.op.image.DecodePng
   */
  public DecodePng<UInt8> decodePng(Operand<String> contents, DecodePng.Options... options) {
    return DecodePng.create(scope, contents, options);
  }

  /**
   * Builds an {@link ExtractJpegShape} operation
   *
   * @param contents 0-D. The JPEG-encoded image.
   * @return a new instance of ExtractJpegShape
   * @see org.tensorflow.op.image.ExtractJpegShape
   */
  public ExtractJpegShape<Integer> extractJpegShape(Operand<String> contents) {
    return ExtractJpegShape.create(scope, contents);
  }

  /**
   * Builds an {@link AdjustContrast} operation
   *
   * @param images Images to adjust.  At least 3-D.
   * @param contrastFactor A float multiplier for adjusting contrast.
   * @return a new instance of AdjustContrast
   * @see org.tensorflow.op.image.AdjustContrast
   */
  public <T extends Number> AdjustContrast<T> adjustContrast(Operand<T> images,
      Operand<Float> contrastFactor) {
    return AdjustContrast.create(scope, images, contrastFactor);
  }

  /**
   * Builds an {@link CropAndResizeGradImage} operation
   *
   * @param grads A 4-D tensor of shape `[num_boxes, crop_height, crop_width, depth]`.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   * @param imageSize A 1-D tensor with value `[batch, image_height, image_width, depth]`
   * @param T 
   * @param options carries optional attributes values
   * @return a new instance of CropAndResizeGradImage
   * @see org.tensorflow.op.image.CropAndResizeGradImage
   */
  public <T extends Number> CropAndResizeGradImage<T> cropAndResizeGradImage(Operand<Float> grads,
      Operand<Float> boxes, Operand<Integer> boxInd, Operand<Integer> imageSize, Class<T> T,
      CropAndResizeGradImage.Options... options) {
    return CropAndResizeGradImage.create(scope, grads, boxes, boxInd, imageSize, T, options);
  }

  /**
   * Builds an {@link CropAndResize} operation
   *
   * @param image A 4-D tensor of shape `[batch, image_height, image_width, depth]`.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   * @param cropSize A 1-D tensor of 2 elements, `size = [crop_height, crop_width]`. All
   * @param options carries optional attributes values
   * @return a new instance of CropAndResize
   * @see org.tensorflow.op.image.CropAndResize
   */
  public <T extends Number> CropAndResize cropAndResize(Operand<T> image, Operand<Float> boxes,
      Operand<Integer> boxInd, Operand<Integer> cropSize, CropAndResize.Options... options) {
    return CropAndResize.create(scope, image, boxes, boxInd, cropSize, options);
  }

  /**
   * Builds an {@link AdjustSaturation} operation
   *
   * @param images Images to adjust.  At least 3-D.
   * @param scale A float scale to add to the saturation.
   * @return a new instance of AdjustSaturation
   * @see org.tensorflow.op.image.AdjustSaturation
   */
  public <T extends Number> AdjustSaturation<T> adjustSaturation(Operand<T> images,
      Operand<Float> scale) {
    return AdjustSaturation.create(scope, images, scale);
  }

  /**
   * Builds an {@link DecodeAndCropJpeg} operation
   *
   * @param contents 0-D.  The JPEG-encoded image.
   * @param cropWindow 1-D.  The crop window: [crop_y, crop_x, crop_height, crop_width].
   * @param options carries optional attributes values
   * @return a new instance of DecodeAndCropJpeg
   * @see org.tensorflow.op.image.DecodeAndCropJpeg
   */
  public DecodeAndCropJpeg decodeAndCropJpeg(Operand<String> contents, Operand<Integer> cropWindow,
      DecodeAndCropJpeg.Options... options) {
    return DecodeAndCropJpeg.create(scope, contents, cropWindow, options);
  }

  /**
   * Builds an {@link DecodeJpeg} operation
   *
   * @param contents 0-D.  The JPEG-encoded image.
   * @param options carries optional attributes values
   * @return a new instance of DecodeJpeg
   * @see org.tensorflow.op.image.DecodeJpeg
   */
  public DecodeJpeg decodeJpeg(Operand<String> contents, DecodeJpeg.Options... options) {
    return DecodeJpeg.create(scope, contents, options);
  }

  /**
   * Builds an {@link ExtractGlimpse} operation
   *
   * @param input A 4-D float tensor of shape `[batch_size, height, width, channels]`.
   * @param size A 1-D tensor of 2 elements containing the size of the glimpses
   * @param offsets A 2-D integer tensor of shape `[batch_size, 2]` containing
   * @param options carries optional attributes values
   * @return a new instance of ExtractGlimpse
   * @see org.tensorflow.op.image.ExtractGlimpse
   */
  public ExtractGlimpse extractGlimpse(Operand<Float> input, Operand<Integer> size,
      Operand<Float> offsets, ExtractGlimpse.Options... options) {
    return ExtractGlimpse.create(scope, input, size, offsets, options);
  }

  /**
   * Builds an {@link DecodeBmp} operation
   *
   * @param contents 0-D.  The BMP-encoded image.
   * @param options carries optional attributes values
   * @return a new instance of DecodeBmp
   * @see org.tensorflow.op.image.DecodeBmp
   */
  public DecodeBmp decodeBmp(Operand<String> contents, DecodeBmp.Options... options) {
    return DecodeBmp.create(scope, contents, options);
  }

  /**
   * Builds an {@link ExtractJpegShape} operation
   *
   * @param contents 0-D. The JPEG-encoded image.
   * @param outputType (Optional) The output type of the operation (int32 or int64).
   * @return a new instance of ExtractJpegShape
   * @see org.tensorflow.op.image.ExtractJpegShape
   */
  public <T extends Number> ExtractJpegShape<T> extractJpegShape(Operand<String> contents,
      Class<T> outputType) {
    return ExtractJpegShape.create(scope, contents, outputType);
  }

  /**
   * Builds an {@link ResizeArea} operation
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   * @param options carries optional attributes values
   * @return a new instance of ResizeArea
   * @see org.tensorflow.op.image.ResizeArea
   */
  public <T extends Number> ResizeArea resizeArea(Operand<T> images, Operand<Integer> size,
      ResizeArea.Options... options) {
    return ResizeArea.create(scope, images, size, options);
  }

  /**
   * Builds an {@link RgbToHsv} operation
   *
   * @param images 1-D or higher rank. RGB data to convert. Last dimension must be size 3.
   * @return a new instance of RgbToHsv
   * @see org.tensorflow.op.image.RgbToHsv
   */
  public <T extends Number> RgbToHsv<T> rgbToHsv(Operand<T> images) {
    return RgbToHsv.create(scope, images);
  }

  /**
   * Builds an {@link EncodeJpegVariableQuality} operation
   *
   * @param images Images to adjust.  At least 3-D.
   * @param quality An int quality to encode to.
   * @return a new instance of EncodeJpegVariableQuality
   * @see org.tensorflow.op.image.EncodeJpegVariableQuality
   */
  public EncodeJpegVariableQuality encodeJpegVariableQuality(Operand<UInt8> images,
      Operand<Integer> quality) {
    return EncodeJpegVariableQuality.create(scope, images, quality);
  }

  /**
   * Builds an {@link CropAndResizeGradBoxes} operation
   *
   * @param grads A 4-D tensor of shape `[num_boxes, crop_height, crop_width, depth]`.
   * @param image A 4-D tensor of shape `[batch, image_height, image_width, depth]`.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   * @param options carries optional attributes values
   * @return a new instance of CropAndResizeGradBoxes
   * @see org.tensorflow.op.image.CropAndResizeGradBoxes
   */
  public <T extends Number> CropAndResizeGradBoxes cropAndResizeGradBoxes(Operand<Float> grads,
      Operand<T> image, Operand<Float> boxes, Operand<Integer> boxInd,
      CropAndResizeGradBoxes.Options... options) {
    return CropAndResizeGradBoxes.create(scope, grads, image, boxes, boxInd, options);
  }

  /**
   * Builds an {@link ExtractImagePatches} operation
   *
   * @param images 4-D Tensor with shape `[batch, in_rows, in_cols, depth]`.
   * @param ksizes The size of the sliding window for each dimension of `images`.
   * @param strides How far the centers of two consecutive patches are in
   * @param rates Must be: `[1, rate_rows, rate_cols, 1]`. This is the
   * @param padding The type of padding algorithm to use.
   * @return a new instance of ExtractImagePatches
   * @see org.tensorflow.op.image.ExtractImagePatches
   */
  public <T extends Number> ExtractImagePatches<T> extractImagePatches(Operand<T> images,
      List<Long> ksizes, List<Long> strides, List<Long> rates, String padding) {
    return ExtractImagePatches.create(scope, images, ksizes, strides, rates, padding);
  }

  /**
   * Builds an {@link DrawBoundingBoxes} operation
   *
   * @param images 4-D with shape `[batch, height, width, depth]`. A batch of images.
   * @param boxes 3-D with shape `[batch, num_bounding_boxes, 4]` containing bounding
   * @return a new instance of DrawBoundingBoxes
   * @see org.tensorflow.op.image.DrawBoundingBoxes
   */
  public <T extends Number> DrawBoundingBoxes<T> drawBoundingBoxes(Operand<T> images,
      Operand<Float> boxes) {
    return DrawBoundingBoxes.create(scope, images, boxes);
  }

  /**
   * Builds an {@link NonMaxSuppressionWithOverlaps} operation
   *
   * @param overlaps A 2-D float tensor of shape `[num_boxes, num_boxes]` representing
   * @param scores A 1-D float tensor of shape `[num_boxes]` representing a single
   * @param maxOutputSize A scalar integer tensor representing the maximum number of
   * @param overlapThreshold A 0-D float tensor representing the threshold for deciding whether
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   * @return a new instance of NonMaxSuppressionWithOverlaps
   * @see org.tensorflow.op.image.NonMaxSuppressionWithOverlaps
   */
  public NonMaxSuppressionWithOverlaps nonMaxSuppressionWithOverlaps(Operand<Float> overlaps,
      Operand<Float> scores, Operand<Integer> maxOutputSize, Operand<Float> overlapThreshold,
      Operand<Float> scoreThreshold) {
    return NonMaxSuppressionWithOverlaps.create(scope, overlaps, scores, maxOutputSize, overlapThreshold, scoreThreshold);
  }

  /**
   * Builds an {@link ResizeBicubic} operation
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   * @param options carries optional attributes values
   * @return a new instance of ResizeBicubic
   * @see org.tensorflow.op.image.ResizeBicubic
   */
  public <T extends Number> ResizeBicubic resizeBicubic(Operand<T> images, Operand<Integer> size,
      ResizeBicubic.Options... options) {
    return ResizeBicubic.create(scope, images, size, options);
  }

  /**
   * Builds an {@link QuantizedResizeBilinear} operation
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of QuantizedResizeBilinear
   * @see org.tensorflow.op.image.QuantizedResizeBilinear
   */
  public <T> QuantizedResizeBilinear<T> quantizedResizeBilinear(Operand<T> images,
      Operand<Integer> size, Operand<Float> min, Operand<Float> max,
      QuantizedResizeBilinear.Options... options) {
    return QuantizedResizeBilinear.create(scope, images, size, min, max, options);
  }

  /**
   * Builds an {@link ResizeNearestNeighbor} operation
   *
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   * @param options carries optional attributes values
   * @return a new instance of ResizeNearestNeighbor
   * @see org.tensorflow.op.image.ResizeNearestNeighbor
   */
  public <T extends Number> ResizeNearestNeighbor<T> resizeNearestNeighbor(Operand<T> images,
      Operand<Integer> size, ResizeNearestNeighbor.Options... options) {
    return ResizeNearestNeighbor.create(scope, images, size, options);
  }

  /**
   * Builds an {@link HsvToRgb} operation
   *
   * @param images 1-D or higher rank. HSV data to convert. Last dimension must be size 3.
   * @return a new instance of HsvToRgb
   * @see org.tensorflow.op.image.HsvToRgb
   */
  public <T extends Number> HsvToRgb<T> hsvToRgb(Operand<T> images) {
    return HsvToRgb.create(scope, images);
  }

  /**
   * Builds an {@link EncodeJpeg} operation
   *
   * @param image 3-D with shape `[height, width, channels]`.
   * @param options carries optional attributes values
   * @return a new instance of EncodeJpeg
   * @see org.tensorflow.op.image.EncodeJpeg
   */
  public EncodeJpeg encodeJpeg(Operand<UInt8> image, EncodeJpeg.Options... options) {
    return EncodeJpeg.create(scope, image, options);
  }
}
