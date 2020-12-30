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

package org.tensorflow.op.train;

import org.tensorflow.Operand;
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
 * Given a path to new and old vocabulary files, returns a remapping Tensor of
 * <p>
 * length `num_new_vocab`, where `remapping[i]` contains the row number in the old
 * vocabulary that corresponds to row `i` in the new vocabulary (starting at line
 * `new_vocab_offset` and up to `num_new_vocab` entities), or `-1` if entry `i`
 * in the new vocabulary is not in the old vocabulary.  The old vocabulary is
 * constrained to the first `old_vocab_size` entries if `old_vocab_size` is not the
 * default value of -1.
 * <p>
 * `num_vocab_offset` enables
 * use in the partitioned variable case, and should generally be set through
 * examining partitioning info.  The format of the files should be a text file,
 * with each line containing a single entity within the vocabulary.
 * <p>
 * For example, with `new_vocab_file` a text file containing each of the following
 * elements on a single line: `[f0, f1, f2, f3]`, old_vocab_file = [f1, f0, f3],
 * `num_new_vocab = 3, new_vocab_offset = 1`, the returned remapping would be
 * `[0, -1, 2]`.
 * <p>
 * The op also returns a count of how many entries in the new vocabulary
 * were present in the old vocabulary, which is used to calculate the number of
 * values to initialize in a weight matrix remapping
 * <p>
 * This functionality can be used to remap both row vocabularies (typically,
 * features) and column vocabularies (typically, classes) from TensorFlow
 * checkpoints.  Note that the partitioning logic relies on contiguous vocabularies
 * corresponding to div-partitioned variables.  Moreover, the underlying remapping
 * uses an IndexTable (as opposed to an inexact CuckooTable), so client code should
 * use the corresponding index_table_from_file() as the FeatureColumn framework
 * does (as opposed to tf.feature_to_id(), which uses a CuckooTable).
 */
@Operator(group = "train")
public final class GenerateVocabRemapping extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.GenerateVocabRemapping}
   */
  public static class Options {
    
    /**
     * @param oldVocabSize Number of entries in the old vocab file to consider.  If -1,
     * use the entire old vocabulary.
     */
    public Options oldVocabSize(Long oldVocabSize) {
      this.oldVocabSize = oldVocabSize;
      return this;
    }
    
    private Long oldVocabSize;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new GenerateVocabRemapping operation.
   * 
   * @param scope current scope
   * @param newVocabFile Path to the new vocab file.
   * @param oldVocabFile Path to the old vocab file.
   * @param newVocabOffset How many entries into the new vocab file to start reading.
   * @param numNewVocab Number of entries in the new vocab file to remap.
   * @param options carries optional attributes values
   * @return a new instance of GenerateVocabRemapping
   */
  @Endpoint(describeByClass = true)
  public static GenerateVocabRemapping create(Scope scope, Operand<TString> newVocabFile, Operand<TString> oldVocabFile, Long newVocabOffset, Long numNewVocab, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("GenerateVocabRemapping", scope.makeOpName("GenerateVocabRemapping"));
    opBuilder.addInput(newVocabFile.asOutput());
    opBuilder.addInput(oldVocabFile.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("new_vocab_offset", newVocabOffset);
    opBuilder.setAttr("num_new_vocab", numNewVocab);
    if (options != null) {
      for (Options opts : options) {
        if (opts.oldVocabSize != null) {
          opBuilder.setAttr("old_vocab_size", opts.oldVocabSize);
        }
      }
    }
    return new GenerateVocabRemapping(opBuilder.build());
  }
  
  /**
   * @param oldVocabSize Number of entries in the old vocab file to consider.  If -1,
   * use the entire old vocabulary.
   */
  public static Options oldVocabSize(Long oldVocabSize) {
    return new Options().oldVocabSize(oldVocabSize);
  }
  
  /**
   * A Tensor of length num_new_vocab where the element at index i
   * is equal to the old ID that maps to the new ID i.  This element is -1 for any
   * new ID that is not found in the old vocabulary.
   */
  public Output<TInt64> remapping() {
    return remapping;
  }
  
  /**
   * Number of new vocab entries found in old vocab.
   */
  public Output<TInt32> numPresent() {
    return numPresent;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "GenerateVocabRemapping";
  
  private Output<TInt64> remapping;
  private Output<TInt32> numPresent;
  
  private GenerateVocabRemapping(Operation operation) {
    super(operation);
    int outputIdx = 0;
    remapping = operation.output(outputIdx++);
    numPresent = operation.output(outputIdx++);
  }
}
