/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.tools.benchmark;

import static org.tensorflow.tools.ndarray.index.Indices.all;
import static org.tensorflow.tools.ndarray.index.Indices.at;
import static org.tensorflow.tools.ndarray.index.Indices.odd;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.tools.ndarray.NdArrays;
import org.tensorflow.tools.ndarray.StdArrays;

@Fork(value = 0, jvmArgs = {"-Xms4G", "-Xmx4G"})
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
public class NdArrayBenchmark {

	public static void main(String[] args) throws IOException, RunnerException {
		org.openjdk.jmh.Main.main(args);
	}

	@Setup
	public void setUp() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(TEST_IMAGE));

		int numPixels = image.getWidth() * image.getHeight();
		pixels = NdArrays.ofFloats(Shape.of(numPixels, 3));
		channels = NdArrays.ofFloats(Shape.of(3, numPixels));

		Raster imageData = image.getData();
		float[] pixel = new float[3];
		for (int y = 0, pixelIdx = 0; y < image.getHeight(); ++y) {
			for (int x = 0; x < image.getWidth(); ++x, ++pixelIdx) {
				imageData.getPixel(x, y, pixel);
				StdArrays.copyTo(pixels.get(pixelIdx), pixel);
				StdArrays.copyTo(channels.slice(all(), at(pixelIdx)), pixel);
			}
		}
		batches = NdArrays.ofFloats(Shape.of(BATCH_SIZE, 3, numPixels));
		firstBatch = batches.get(0);
	}

	@Benchmark
  @Measurement(batchSize = 2049 * 1537)
	public void getElementAtIndex() {
	  pixels.get(0);
	}

	@Benchmark
	@Measurement(batchSize = 2049 * 1537)
	public void slicing() {
		batches.slice(at(0), all(), at(0));
	}

	@Benchmark
	public void readingAllPixelsChannelsBySequence() {
		pixels.scalars().forEach(pixel -> pixel.getFloat());
	}

	@Benchmark
	public void readingAllPixelsChannelsBySequenceSlices() {
		pixels.scalars().asSlices().forEach(pixel -> pixel.getFloat());
	}

	@Benchmark
	@Measurement(batchSize = 100)
	public void readingAllPixelsChannelsByIndex() {
		long[] shape = pixels.shape().asArray();
		for (int i = 0; i < shape[0]; ++i) {
			for (int j = 0; j < shape[1]; ++j) {
				pixels.getFloat(i, j);
			}
		}
	}

	@Benchmark
  @Measurement(batchSize = BATCH_SIZE)
	public void writeFirstBatchChannels() {
	  firstBatch.set(channels);
	}

	@Benchmark
	public void writeAllBatchChannels() {
	  batches.elements(0).forEach(batch ->
			batch.set(channels)
		);
	}

	@Benchmark
	@Measurement(batchSize = 2049 * 1537)
	public void writeOnePixelBySlicing() {
		batches.slice(at(0), all(), at(0)).set(pixels.get(0));
	}

	@Benchmark
	public void writeAllPixelsBySlicing() {
		batches.elements(0).forEach(batch ->
				pixels.elements(0).forEachIndexed((coords, pixel) ->
            batch.slice(all(), at(coords[0])).set(pixel)
				)
		);
	}

	@Benchmark
	@Measurement(batchSize = 2049 * 1537)
	public void writeOnePixelsByIndex() {
		batches
				.setFloat(pixels.getFloat(0, 0), 0, 0, 0)
				.setFloat(pixels.getFloat(0, 1), 0, 1, 0)
				.setFloat(pixels.getFloat(0, 2), 0, 2, 0);
	}

	@Benchmark
	public void writeAllPixelsByIndex() {
		batches.elements(0).forEach(batch ->
				pixels.elements(0).forEachIndexed((coords, pixel) -> {
				  long pixelIndex = coords[0];
					batch
							.setFloat(pixel.getFloat(0), 0, pixelIndex)
							.setFloat(pixel.getFloat(1), 1, pixelIndex)
							.setFloat(pixel.getFloat(2), 2, pixelIndex);
				})
		);
	}

	private static final String TEST_IMAGE = "castle.jpg";
	private static final int BATCH_SIZE = 60;

	private FloatNdArray pixels;
	private FloatNdArray channels;
	private FloatNdArray batches;
	private FloatNdArray firstBatch;
}
