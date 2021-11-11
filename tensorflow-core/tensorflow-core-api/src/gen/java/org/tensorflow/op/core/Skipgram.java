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

package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Parses a text file and creates a batch of examples.
 */
@OpMetadata(
    opType = Skipgram.OP_NAME,
    inputsClass = Skipgram.Inputs.class
)
@Operator
public final class Skipgram extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Skipgram";

  private Output<TString> vocabWord;

  private Output<TInt32> vocabFreq;

  private Output<TInt64> wordsPerEpoch;

  private Output<TInt32> currentEpoch;

  private Output<TInt64> totalWordsProcessed;

  private Output<TInt32> examples;

  private Output<TInt32> labels;

  public Skipgram(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    vocabWord = operation.output(outputIdx++);
    vocabFreq = operation.output(outputIdx++);
    wordsPerEpoch = operation.output(outputIdx++);
    currentEpoch = operation.output(outputIdx++);
    totalWordsProcessed = operation.output(outputIdx++);
    examples = operation.output(outputIdx++);
    labels = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Skipgram operation.
   *
   * @param scope current scope
   * @param filename The corpus's text file name.
   * @param batchSize The size of produced batch.
   * @param options carries optional attribute values
   * @return a new instance of Skipgram
   */
  @Endpoint(
      describeByClass = true
  )
  public static Skipgram create(Scope scope, String filename, Long batchSize, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Skipgram");
    opBuilder.setAttr("filename", filename);
    opBuilder.setAttr("batch_size", batchSize);
    if (options != null) {
      for (Options opts : options) {
        if (opts.windowSize != null) {
          opBuilder.setAttr("window_size", opts.windowSize);
        }
        if (opts.minCount != null) {
          opBuilder.setAttr("min_count", opts.minCount);
        }
        if (opts.subsample != null) {
          opBuilder.setAttr("subsample", opts.subsample);
        }
      }
    }
    return new Skipgram(opBuilder.build());
  }

  /**
   * Sets the windowSize option.
   *
   * @param windowSize The number of words to predict to the left and right of the target.
   * @return this Options instance.
   */
  public static Options windowSize(Long windowSize) {
    return new Options().windowSize(windowSize);
  }

  /**
   * Sets the minCount option.
   *
   * @param minCount The minimum number of word occurrences for it to be included in the
   * vocabulary.
   * @return this Options instance.
   */
  public static Options minCount(Long minCount) {
    return new Options().minCount(minCount);
  }

  /**
   * Sets the subsample option.
   *
   * @param subsample Threshold for word occurrence. Words that appear with higher
   * frequency will be randomly down-sampled. Set to 0 to disable.
   * @return this Options instance.
   */
  public static Options subsample(Float subsample) {
    return new Options().subsample(subsample);
  }

  /**
   * Gets vocabWord.
   * A vector of words in the corpus.
   * @return vocabWord.
   */
  public Output<TString> vocabWord() {
    return vocabWord;
  }

  /**
   * Gets vocabFreq.
   * Frequencies of words. Sorted in the non-ascending order.
   * @return vocabFreq.
   */
  public Output<TInt32> vocabFreq() {
    return vocabFreq;
  }

  /**
   * Gets wordsPerEpoch.
   * Number of words per epoch in the data file.
   * @return wordsPerEpoch.
   */
  public Output<TInt64> wordsPerEpoch() {
    return wordsPerEpoch;
  }

  /**
   * Gets currentEpoch.
   * The current epoch number.
   * @return currentEpoch.
   */
  public Output<TInt32> currentEpoch() {
    return currentEpoch;
  }

  /**
   * Gets totalWordsProcessed.
   * The total number of words processed so far.
   * @return totalWordsProcessed.
   */
  public Output<TInt64> totalWordsProcessed() {
    return totalWordsProcessed;
  }

  /**
   * Gets examples.
   * A vector of word ids.
   * @return examples.
   */
  public Output<TInt32> examples() {
    return examples;
  }

  /**
   * Gets labels.
   * A vector of word ids.
   * @return labels.
   */
  public Output<TInt32> labels() {
    return labels;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Skipgram}
   */
  public static class Options {
    private Long windowSize;

    private Long minCount;

    private Float subsample;

    private Options() {
    }

    /**
     * Sets the windowSize option.
     *
     * @param windowSize The number of words to predict to the left and right of the target.
     * @return this Options instance.
     */
    public Options windowSize(Long windowSize) {
      this.windowSize = windowSize;
      return this;
    }

    /**
     * Sets the minCount option.
     *
     * @param minCount The minimum number of word occurrences for it to be included in the
     * vocabulary.
     * @return this Options instance.
     */
    public Options minCount(Long minCount) {
      this.minCount = minCount;
      return this;
    }

    /**
     * Sets the subsample option.
     *
     * @param subsample Threshold for word occurrence. Words that appear with higher
     * frequency will be randomly down-sampled. Set to 0 to disable.
     * @return this Options instance.
     */
    public Options subsample(Float subsample) {
      this.subsample = subsample;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Skipgram.class
  )
  public static class Inputs extends RawOpInputs<Skipgram> {
    /**
     * The corpus's text file name.
     */
    public final String filename;

    /**
     * The size of produced batch.
     */
    public final long batchSize;

    /**
     * The number of words to predict to the left and right of the target.
     */
    public final long windowSize;

    /**
     * The minimum number of word occurrences for it to be included in the
     * vocabulary.
     */
    public final long minCount;

    /**
     * Threshold for word occurrence. Words that appear with higher
     * frequency will be randomly down-sampled. Set to 0 to disable.
     */
    public final float subsample;

    public Inputs(GraphOperation op) {
      super(new Skipgram(op), op, Arrays.asList("filename", "batch_size", "window_size", "min_count", "subsample"));
      int inputIndex = 0;
      filename = op.attributes().getAttrString("filename");
      batchSize = op.attributes().getAttrInt("batch_size");
      windowSize = op.attributes().getAttrInt("window_size");
      minCount = op.attributes().getAttrInt("min_count");
      subsample = op.attributes().getAttrFloat("subsample");
    }
  }
}
