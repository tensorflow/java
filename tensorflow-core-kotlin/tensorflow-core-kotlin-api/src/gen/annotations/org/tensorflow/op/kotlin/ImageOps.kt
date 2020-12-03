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

import org.tensorflow.DataType
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
 * An API for building {@code image} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class ImageOps(
    /**
     * Get the parent {@link KotlinOps} object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.ImageOps = ops.java.image

    /**
     * Returns the current {@link Scope scope} of this API
     */
    public val scope: Scope = ops.scope

    public fun <T : TNumber> adjustContrast(images: Operand<T>, contrastFactor: Operand<TFloat32>):
        AdjustContrast<T> = java.adjustContrast<T>(
            images,
            contrastFactor
        )

    public fun <T : TNumber> adjustHue(images: Operand<T>, delta: Operand<TFloat32>): AdjustHue<T> =
        java.adjustHue<T>(
            images,
            delta
        )

    public fun <T : TNumber> adjustSaturation(images: Operand<T>, scale: Operand<TFloat32>):
        AdjustSaturation<T> = java.adjustSaturation<T>(
            images,
            scale
        )

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
            padPerClass?.let { org.tensorflow.op.image.CombinedNonMaxSuppression.padPerClass(it) },
            clipBoxes?.let { org.tensorflow.op.image.CombinedNonMaxSuppression.clipBoxes(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> cropAndResize(
        image: Operand<T>,
        boxes: Operand<TFloat32>,
        boxInd: Operand<TInt32>,
        cropSize: Operand<TInt32>,
        method: String? = null,
        extrapolationValue: Float? = null
    ): CropAndResize = java.cropAndResize<T>(
        image,
        boxes,
        boxInd,
        cropSize,
        *listOfNotNull(
            method?.let { org.tensorflow.op.image.CropAndResize.method(it) },
            extrapolationValue?.let { org.tensorflow.op.image.CropAndResize.extrapolationValue(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> cropAndResizeGradBoxes(
        grads: Operand<TFloat32>,
        image: Operand<T>,
        boxes: Operand<TFloat32>,
        boxInd: Operand<TInt32>,
        method: String? = null
    ): CropAndResizeGradBoxes = java.cropAndResizeGradBoxes<T>(
        grads,
        image,
        boxes,
        boxInd,
        *listOfNotNull(
            method?.let { org.tensorflow.op.image.CropAndResizeGradBoxes.method(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> cropAndResizeGradImage(
        grads: Operand<TFloat32>,
        boxes: Operand<TFloat32>,
        boxInd: Operand<TInt32>,
        imageSize: Operand<TInt32>,
        T_: DataType<T>,
        method: String? = null
    ): CropAndResizeGradImage<T> = java.cropAndResizeGradImage<T>(
        grads,
        boxes,
        boxInd,
        imageSize,
        T_,
        *listOfNotNull(
            method?.let { org.tensorflow.op.image.CropAndResizeGradImage.method(it) }
        ).toTypedArray()
    )

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
            channels?.let { org.tensorflow.op.image.DecodeAndCropJpeg.channels(it) },
            ratio?.let { org.tensorflow.op.image.DecodeAndCropJpeg.ratio(it) },
            fancyUpscaling?.let { org.tensorflow.op.image.DecodeAndCropJpeg.fancyUpscaling(it) },
            tryRecoverTruncated?.let { org.tensorflow.op.image.DecodeAndCropJpeg.tryRecoverTruncated(it) },
            acceptableFraction?.let { org.tensorflow.op.image.DecodeAndCropJpeg.acceptableFraction(it) },
            dctMethod?.let { org.tensorflow.op.image.DecodeAndCropJpeg.dctMethod(it) }
        ).toTypedArray()
    )

    public fun decodeBmp(contents: Operand<TString>, channels: Long? = null): DecodeBmp =
        java.decodeBmp(
            contents,
            *listOfNotNull(
                channels?.let { org.tensorflow.op.image.DecodeBmp.channels(it) }
            ).toTypedArray()
        )

    public fun decodeGif(contents: Operand<TString>): DecodeGif = java.decodeGif(
        contents
    )

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
            channels?.let { org.tensorflow.op.image.DecodeJpeg.channels(it) },
            ratio?.let { org.tensorflow.op.image.DecodeJpeg.ratio(it) },
            fancyUpscaling?.let { org.tensorflow.op.image.DecodeJpeg.fancyUpscaling(it) },
            tryRecoverTruncated?.let { org.tensorflow.op.image.DecodeJpeg.tryRecoverTruncated(it) },
            acceptableFraction?.let { org.tensorflow.op.image.DecodeJpeg.acceptableFraction(it) },
            dctMethod?.let { org.tensorflow.op.image.DecodeJpeg.dctMethod(it) }
        ).toTypedArray()
    )

    public fun decodePng(contents: Operand<TString>, channels: Long? = null): DecodePng<TUint8> =
        java.decodePng(
            contents,
            *listOfNotNull(
                channels?.let { org.tensorflow.op.image.DecodePng.channels(it) }
            ).toTypedArray()
        )

    public fun <T : TNumber> decodePng(
        contents: Operand<TString>,
        dtype: DataType<T>,
        channels: Long? = null
    ): DecodePng<T> = java.decodePng<T>(
        contents,
        dtype,
        *listOfNotNull(
            channels?.let { org.tensorflow.op.image.DecodePng.channels(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> drawBoundingBoxes(
        images: Operand<T>,
        boxes: Operand<TFloat32>,
        colors: Operand<TFloat32>
    ): DrawBoundingBoxes<T> = java.drawBoundingBoxes<T>(
        images,
        boxes,
        colors
    )

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
            format?.let { org.tensorflow.op.image.EncodeJpeg.format(it) },
            quality?.let { org.tensorflow.op.image.EncodeJpeg.quality(it) },
            progressive?.let { org.tensorflow.op.image.EncodeJpeg.progressive(it) },
            optimizeSize?.let { org.tensorflow.op.image.EncodeJpeg.optimizeSize(it) },
            chromaDownsampling?.let { org.tensorflow.op.image.EncodeJpeg.chromaDownsampling(it) },
            densityUnit?.let { org.tensorflow.op.image.EncodeJpeg.densityUnit(it) },
            xDensity?.let { org.tensorflow.op.image.EncodeJpeg.xDensity(it) },
            yDensity?.let { org.tensorflow.op.image.EncodeJpeg.yDensity(it) },
            xmpMetadata?.let { org.tensorflow.op.image.EncodeJpeg.xmpMetadata(it) }
        ).toTypedArray()
    )

    public fun encodeJpegVariableQuality(images: Operand<TUint8>, quality: Operand<TInt32>):
        EncodeJpegVariableQuality = java.encodeJpegVariableQuality(
            images,
            quality
        )

    public fun <T : TNumber> encodePng(image: Operand<T>, compression: Long? = null): EncodePng =
        java.encodePng<T>(
            image,
            *listOfNotNull(
                compression?.let { org.tensorflow.op.image.EncodePng.compression(it) }
            ).toTypedArray()
        )

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

    public fun extractJpegShape(contents: Operand<TString>): ExtractJpegShape<TInt32> =
        java.extractJpegShape(
            contents
        )

    public fun <T : TNumber> extractJpegShape(contents: Operand<TString>, outputType: DataType<T>):
        ExtractJpegShape<T> = java.extractJpegShape<T>(
            contents,
            outputType
        )

    public fun <T : TNumber> hsvToRgb(images: Operand<T>): HsvToRgb<T> = java.hsvToRgb<T>(
        images
    )

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
            padToMaxOutputSize?.let { org.tensorflow.op.image.NonMaxSuppression.padToMaxOutputSize(it) }
        ).toTypedArray()
    )

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
            alignCorners?.let { org.tensorflow.op.image.QuantizedResizeBilinear.alignCorners(it) },
            halfPixelCenters?.let { org.tensorflow.op.image.QuantizedResizeBilinear.halfPixelCenters(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> randomCrop(
        image: Operand<T>,
        size: Operand<TInt64>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomCrop<T> = java.randomCrop<T>(
        image,
        size,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.image.RandomCrop.seed(it) },
            seed2?.let { org.tensorflow.op.image.RandomCrop.seed2(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> resizeArea(
        images: Operand<T>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null
    ): ResizeArea = java.resizeArea<T>(
        images,
        size,
        *listOfNotNull(
            alignCorners?.let { org.tensorflow.op.image.ResizeArea.alignCorners(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> resizeBicubic(
        images: Operand<T>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null,
        halfPixelCenters: Boolean? = null
    ): ResizeBicubic = java.resizeBicubic<T>(
        images,
        size,
        *listOfNotNull(
            alignCorners?.let { org.tensorflow.op.image.ResizeBicubic.alignCorners(it) },
            halfPixelCenters?.let { org.tensorflow.op.image.ResizeBicubic.halfPixelCenters(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> resizeBilinear(
        images: Operand<T>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null,
        halfPixelCenters: Boolean? = null
    ): ResizeBilinear = java.resizeBilinear<T>(
        images,
        size,
        *listOfNotNull(
            alignCorners?.let { org.tensorflow.op.image.ResizeBilinear.alignCorners(it) },
            halfPixelCenters?.let { org.tensorflow.op.image.ResizeBilinear.halfPixelCenters(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> resizeNearestNeighbor(
        images: Operand<T>,
        size: Operand<TInt32>,
        alignCorners: Boolean? = null,
        halfPixelCenters: Boolean? = null
    ): ResizeNearestNeighbor<T> = java.resizeNearestNeighbor<T>(
        images,
        size,
        *listOfNotNull(
            alignCorners?.let { org.tensorflow.op.image.ResizeNearestNeighbor.alignCorners(it) },
            halfPixelCenters?.let { org.tensorflow.op.image.ResizeNearestNeighbor.halfPixelCenters(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> rgbToHsv(images: Operand<T>): RgbToHsv<T> = java.rgbToHsv<T>(
        images
    )

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
            seed?.let { org.tensorflow.op.image.SampleDistortedBoundingBox.seed(it) },
            seed2?.let { org.tensorflow.op.image.SampleDistortedBoundingBox.seed2(it) },
            aspectRatioRange?.let {
                org.tensorflow.op.image.SampleDistortedBoundingBox.aspectRatioRange(it)
            },
            areaRange?.let { org.tensorflow.op.image.SampleDistortedBoundingBox.areaRange(it) },
            maxAttempts?.let { org.tensorflow.op.image.SampleDistortedBoundingBox.maxAttempts(it) },
            useImageIfNoBoundingBoxes?.let {
                org.tensorflow.op.image.SampleDistortedBoundingBox.useImageIfNoBoundingBoxes(it)
            }
        ).toTypedArray()
    )

    public fun <T : TNumber> scaleAndTranslate(
        images: Operand<T>,
        size: Operand<TInt32>,
        scale: Operand<TFloat32>,
        translation: Operand<TFloat32>,
        kernelType: String? = null,
        antialias: Boolean? = null
    ): ScaleAndTranslate = java.scaleAndTranslate<T>(
        images,
        size,
        scale,
        translation,
        *listOfNotNull(
            kernelType?.let { org.tensorflow.op.image.ScaleAndTranslate.kernelType(it) },
            antialias?.let { org.tensorflow.op.image.ScaleAndTranslate.antialias(it) }
        ).toTypedArray()
    )
}
