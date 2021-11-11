/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.audio;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Transforms a spectrogram into a form that's useful for speech recognition.
 * Mel Frequency Cepstral Coefficients are a way of representing audio data that's
 * been effective as an input feature for machine learning. They are created by
 * taking the spectrum of a spectrogram (a 'cepstrum'), and discarding some of the
 * higher frequencies that are less significant to the human ear. They have a long
 * history in the speech recognition world, and https://en.wikipedia.org/wiki/Mel-frequency_cepstrum
 * is a good resource to learn more.
 */
@OpMetadata(
    opType = Mfcc.OP_NAME,
    inputsClass = Mfcc.Inputs.class
)
@Operator(
    group = "audio"
)
public final class Mfcc extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Mfcc";

  private Output<TFloat32> output;

  public Mfcc(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Mfcc operation.
   *
   * @param scope current scope
   * @param spectrogram Typically produced by the Spectrogram op, with magnitude_squared
   * set to true.
   * @param sampleRate How many samples per second the source audio used.
   * @param options carries optional attribute values
   * @return a new instance of Mfcc
   */
  @Endpoint(
      describeByClass = true
  )
  public static Mfcc create(Scope scope, Operand<TFloat32> spectrogram, Operand<TInt32> sampleRate,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Mfcc");
    opBuilder.addInput(spectrogram.asOutput());
    opBuilder.addInput(sampleRate.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.upperFrequencyLimit != null) {
          opBuilder.setAttr("upper_frequency_limit", opts.upperFrequencyLimit);
        }
        if (opts.lowerFrequencyLimit != null) {
          opBuilder.setAttr("lower_frequency_limit", opts.lowerFrequencyLimit);
        }
        if (opts.filterbankChannelCount != null) {
          opBuilder.setAttr("filterbank_channel_count", opts.filterbankChannelCount);
        }
        if (opts.dctCoefficientCount != null) {
          opBuilder.setAttr("dct_coefficient_count", opts.dctCoefficientCount);
        }
      }
    }
    return new Mfcc(opBuilder.build());
  }

  /**
   * Sets the upperFrequencyLimit option.
   *
   * @param upperFrequencyLimit The highest frequency to use when calculating the
   * ceptstrum.
   * @return this Options instance.
   */
  public static Options upperFrequencyLimit(Float upperFrequencyLimit) {
    return new Options().upperFrequencyLimit(upperFrequencyLimit);
  }

  /**
   * Sets the lowerFrequencyLimit option.
   *
   * @param lowerFrequencyLimit The lowest frequency to use when calculating the
   * ceptstrum.
   * @return this Options instance.
   */
  public static Options lowerFrequencyLimit(Float lowerFrequencyLimit) {
    return new Options().lowerFrequencyLimit(lowerFrequencyLimit);
  }

  /**
   * Sets the filterbankChannelCount option.
   *
   * @param filterbankChannelCount Resolution of the Mel bank used internally.
   * @return this Options instance.
   */
  public static Options filterbankChannelCount(Long filterbankChannelCount) {
    return new Options().filterbankChannelCount(filterbankChannelCount);
  }

  /**
   * Sets the dctCoefficientCount option.
   *
   * @param dctCoefficientCount How many output channels to produce per time slice.
   * @return this Options instance.
   */
  public static Options dctCoefficientCount(Long dctCoefficientCount) {
    return new Options().dctCoefficientCount(dctCoefficientCount);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TFloat32> output() {
    return output;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.audio.Mfcc}
   */
  public static class Options {
    private Float upperFrequencyLimit;

    private Float lowerFrequencyLimit;

    private Long filterbankChannelCount;

    private Long dctCoefficientCount;

    private Options() {
    }

    /**
     * Sets the upperFrequencyLimit option.
     *
     * @param upperFrequencyLimit The highest frequency to use when calculating the
     * ceptstrum.
     * @return this Options instance.
     */
    public Options upperFrequencyLimit(Float upperFrequencyLimit) {
      this.upperFrequencyLimit = upperFrequencyLimit;
      return this;
    }

    /**
     * Sets the lowerFrequencyLimit option.
     *
     * @param lowerFrequencyLimit The lowest frequency to use when calculating the
     * ceptstrum.
     * @return this Options instance.
     */
    public Options lowerFrequencyLimit(Float lowerFrequencyLimit) {
      this.lowerFrequencyLimit = lowerFrequencyLimit;
      return this;
    }

    /**
     * Sets the filterbankChannelCount option.
     *
     * @param filterbankChannelCount Resolution of the Mel bank used internally.
     * @return this Options instance.
     */
    public Options filterbankChannelCount(Long filterbankChannelCount) {
      this.filterbankChannelCount = filterbankChannelCount;
      return this;
    }

    /**
     * Sets the dctCoefficientCount option.
     *
     * @param dctCoefficientCount How many output channels to produce per time slice.
     * @return this Options instance.
     */
    public Options dctCoefficientCount(Long dctCoefficientCount) {
      this.dctCoefficientCount = dctCoefficientCount;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Mfcc.class
  )
  public static class Inputs extends RawOpInputs<Mfcc> {
    /**
     * Typically produced by the Spectrogram op, with magnitude_squared
     * set to true.
     */
    public final Operand<TFloat32> spectrogram;

    /**
     * How many samples per second the source audio used.
     */
    public final Operand<TInt32> sampleRate;

    /**
     * The highest frequency to use when calculating the
     * ceptstrum.
     */
    public final float upperFrequencyLimit;

    /**
     * The lowest frequency to use when calculating the
     * ceptstrum.
     */
    public final float lowerFrequencyLimit;

    /**
     * Resolution of the Mel bank used internally.
     */
    public final long filterbankChannelCount;

    /**
     * How many output channels to produce per time slice.
     */
    public final long dctCoefficientCount;

    public Inputs(GraphOperation op) {
      super(new Mfcc(op), op, Arrays.asList("upper_frequency_limit", "lower_frequency_limit", "filterbank_channel_count", "dct_coefficient_count"));
      int inputIndex = 0;
      spectrogram = (Operand<TFloat32>) op.input(inputIndex++);
      sampleRate = (Operand<TInt32>) op.input(inputIndex++);
      upperFrequencyLimit = op.attributes().getAttrFloat("upper_frequency_limit");
      lowerFrequencyLimit = op.attributes().getAttrFloat("lower_frequency_limit");
      filterbankChannelCount = op.attributes().getAttrInt("filterbank_channel_count");
      dctCoefficientCount = op.attributes().getAttrInt("dct_coefficient_count");
    }
  }
}
