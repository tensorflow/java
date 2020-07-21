package org.tensorflow.benchmark;

import static org.tensorflow.ndarray.NdArrays.vectorOf;

import java.io.IOException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TInt32;

@Fork(value = 1, jvmArgs = {"-Xms4G", "-Xmx4G"})
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
public class TensorBenchmark {

  public static void main(String[] args) throws IOException, RunnerException {
    org.openjdk.jmh.Main.main(args);
  }

  @Benchmark
  @Measurement(batchSize = 1000)
  public void initTensorByStdArrays() {
    int[][][][] data = new int[][][][] {
        {
            {
                {0, 0, 0}, {0, 0, 1}, {0, 0, 2}
            },
            {
                {0, 1, 0}, {0, 1, 1}, {0, 1, 2}
            },
            {
                {0, 2, 0}, {0, 2, 1}, {0, 2, 2}
            }
        }, {
            {
                {1, 0, 0}, {1, 0, 1}, {1, 0, 2}
            },
            {
                {1, 1, 0}, {1, 1, 1}, {1, 1, 2}
            },
            {
                {1, 2, 0}, {1, 2, 1}, {1, 2, 2}
            }
        }, {
            {
                {2, 0, 0}, {2, 0, 1}, {2, 0, 2}
            },
            {
                {2, 1, 0}, {2, 1, 1}, {2, 1, 2}
            },
            {
                {2, 2, 0}, {2, 2, 1}, {2, 2, 2}
            }
        }
    };
    TInt32.tensorOf(StdArrays.shapeOf(data), d -> StdArrays.copyTo(data, d));
  }

  @Benchmark
  @Measurement(batchSize = 1000)
  public void initTensorByVectors() {
    TInt32.tensorOf(Shape.of(3, 3, 3, 3), d -> d
        .set(vectorOf(0, 0, 0), 0, 0, 0)
        .set(vectorOf(0, 0, 1), 0, 0, 1)
        .set(vectorOf(0, 0, 2), 0, 0, 2)
        .set(vectorOf(0, 1, 0), 0, 1, 0)
        .set(vectorOf(0, 1, 1), 0, 1, 1)
        .set(vectorOf(0, 1, 2), 0, 1, 2)
        .set(vectorOf(0, 2, 0), 0, 2, 0)
        .set(vectorOf(0, 2, 1), 0, 2, 1)
        .set(vectorOf(0, 2, 2), 0, 2, 2)
        .set(vectorOf(1, 0, 0), 1, 0, 0)
        .set(vectorOf(1, 0, 1), 1, 0, 1)
        .set(vectorOf(1, 0, 2), 1, 0, 2)
        .set(vectorOf(1, 1, 0), 1, 1, 0)
        .set(vectorOf(1, 1, 1), 1, 1, 1)
        .set(vectorOf(1, 1, 2), 1, 1, 2)
        .set(vectorOf(1, 2, 0), 1, 2, 0)
        .set(vectorOf(1, 2, 1), 1, 2, 1)
        .set(vectorOf(1, 2, 2), 1, 2, 2)
        .set(vectorOf(2, 0, 0), 2, 0, 0)
        .set(vectorOf(2, 0, 1), 2, 0, 1)
        .set(vectorOf(2, 0, 2), 2, 0, 2)
        .set(vectorOf(2, 1, 0), 2, 1, 0)
        .set(vectorOf(2, 1, 1), 2, 1, 1)
        .set(vectorOf(2, 1, 2), 2, 1, 2)
        .set(vectorOf(2, 2, 0), 2, 2, 0)
        .set(vectorOf(2, 2, 1), 2, 2, 1)
        .set(vectorOf(2, 2, 2), 2, 2, 2)
    );
  }

  @Benchmark
  @Measurement(batchSize = 1000)
  public void initTensorByFlatArray() {
    IntDataBuffer data = DataBuffers.of(
        0, 0, 0,
        0, 0, 1,
        0, 0, 2,
        0, 1, 0,
        0, 1, 1,
        0, 1, 2,
        0, 2, 0,
        0, 2, 1,
        0, 2, 2,
        1, 0, 0,
        1, 0, 1,
        1, 0, 2,
        1, 1, 0,
        1, 1, 1,
        1, 1, 2,
        1, 2, 0,
        1, 2, 1,
        1, 2, 2,
        2, 0, 0,
        2, 0, 1,
        2, 0, 2,
        2, 1, 0,
        2, 1, 1,
        2, 1, 2,
        2, 2, 0,
        2, 2, 1,
        2, 2, 2
    );
    TInt32.tensorOf(Shape.of(3, 3, 3, 3), data);
  }
}
