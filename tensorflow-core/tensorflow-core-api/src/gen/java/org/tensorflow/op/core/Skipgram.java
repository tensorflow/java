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

import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Parses a text file and creates a batch of examples.
 */
@Operator
public final class Skipgram extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Skipgram}
   */
  public static class Options {
    
    /**
     * @param windowSize The number of words to predict to the left and right of the target.
     */
    public Options windowSize(Long windowSize) {
      this.windowSize = windowSize;
      return this;
    }
    
    /**
     * @param minCount The minimum number of word occurrences for it to be included in the
     * vocabulary.
     */
    public Options minCount(Long minCount) {
      this.minCount = minCount;
      return this;
    }
    
    /**
     * @param subsample Threshold for word occurrence. Words that appear with higher
     * frequency will be randomly down-sampled. Set to 0 to disable.
     */
    public Options subsample(Float subsample) {
      this.subsample = subsample;
      return this;
    }
    
    private Long windowSize;
    private Long minCount;
    private Float subsample;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Skipgram operation.
   * 
   * @param scope current scope
   * @param filename The corpus's text file name.
   * @param batchSize The size of produced batch.
   * @param options carries optional attributes values
   * @return a new instance of Skipgram
   */
  @Endpoint(describeByClass = true)
  public static Skipgram create(Scope scope, String filename, Long batchSize, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Skipgram", scope.makeOpName("Skipgram"));
    opBuilder = scope.apply(opBuilder);
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
   * @param windowSize The number of words to predict to the left and right of the target.
   */
  public static Options windowSize(Long windowSize) {
    return new Options().windowSize(windowSize);
  }
  
  /**
   * @param minCount The minimum number of word occurrences for it to be included in the
   * vocabulary.
   */
  public static Options minCount(Long minCount) {
    return new Options().minCount(minCount);
  }
  
  /**
   * @param subsample Threshold for word occurrence. Words that appear with higher
   * frequency will be randomly down-sampled. Set to 0 to disable.
   */
  public static Options subsample(Float subsample) {
    return new Options().subsample(subsample);
  }
  
  /**
   * A vector of words in the corpus.
   */
  public Output<TString> vocabWord() {
    return vocabWord;
  }
  
  /**
   * Frequencies of words. Sorted in the non-ascending order.
   */
  public Output<TInt32> vocabFreq() {
    return vocabFreq;
  }
  
  /**
   * Number of words per epoch in the data file.
   */
  public Output<TInt64> wordsPerEpoch() {
    return wordsPerEpoch;
  }
  
  /**
   * The current epoch number.
   */
  public Output<TInt32> currentEpoch() {
    return currentEpoch;
  }
  
  /**
   * The total number of words processed so far.
   */
  public Output<TInt64> totalWordsProcessed() {
    return totalWordsProcessed;
  }
  
  /**
   * A vector of word ids.
   */
  public Output<TInt32> examples() {
    return examples;
  }
  
  /**
   * A vector of word ids.
   */
  public Output<TInt32> labels() {
    return labels;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Skipgram";
  
  private Output<TString> vocabWord;
  private Output<TInt32> vocabFreq;
  private Output<TInt64> wordsPerEpoch;
  private Output<TInt32> currentEpoch;
  private Output<TInt64> totalWordsProcessed;
  private Output<TInt32> examples;
  private Output<TInt32> labels;
  
  private Skipgram(Operation operation) {
    super(operation);
    int outputIdx = 0;
    vocabWord = operation.output(outputIdx++);
    vocabFreq = operation.output(outputIdx++);
    wordsPerEpoch = operation.output(outputIdx++);
    currentEpoch = operation.output(outputIdx++);
    totalWordsProcessed = operation.output(outputIdx++);
    examples = operation.output(outputIdx++);
    labels = operation.output(outputIdx++);
  }
}
