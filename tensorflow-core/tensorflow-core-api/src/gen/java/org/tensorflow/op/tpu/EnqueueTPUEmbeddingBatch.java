/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TString;

/**
 * An op that enqueues a list of input batch tensors to TPUEmbedding.
 * An op that enqueues a list of input batch tensors to TPUEmbedding.
 */
@OpMetadata(
    opType = EnqueueTPUEmbeddingBatch.OP_NAME,
    inputsClass = EnqueueTPUEmbeddingBatch.Inputs.class
)
public final class EnqueueTPUEmbeddingBatch extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EnqueueTPUEmbeddingBatch";

  public EnqueueTPUEmbeddingBatch(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new EnqueueTPUEmbeddingBatch operation.
   *
   * @param scope current scope
   * @param batch A list of 1D tensors, one for each embedding table, containing the
   * batch inputs encoded as dist_belief.SparseFeatures protos. If the weight
   * field in the SparseFeatures proto is not populated for an ID, a weight of
   * 1.0 is assumed.
   * @param modeOverride A string input that overrides the mode specified in the
   * TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   * 'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   * in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static EnqueueTPUEmbeddingBatch create(Scope scope, Iterable<Operand<TString>> batch,
      Operand<TString> modeOverride, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EnqueueTPUEmbeddingBatch");
    opBuilder.addInputList(Operands.asOutputs(batch));
    opBuilder.addInput(modeOverride.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.deviceOrdinal != null) {
          opBuilder.setAttr("device_ordinal", opts.deviceOrdinal);
        }
        if (opts.combiners != null) {
          String[] combinersArray = new String[opts.combiners.size()];
          for (int i = 0 ; i < combinersArray.length ; i++) {
            combinersArray[i] = opts.combiners.get(i);
          }
          opBuilder.setAttr("combiners", combinersArray);
        }
      }
    }
    return new EnqueueTPUEmbeddingBatch(opBuilder.build());
  }

  /**
   * Sets the deviceOrdinal option.
   *
   * @param deviceOrdinal The TPU device to use. This should be -1 when the Op
   * is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
   * device.
   * @return this Options instance.
   */
  public static Options deviceOrdinal(Long deviceOrdinal) {
    return new Options().deviceOrdinal(deviceOrdinal);
  }

  /**
   * Sets the combiners option.
   *
   * @param combiners A list of string scalars, one for each embedding table that specify
   * how to normalize the embedding activations after weighted summation.
   * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
   * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
   * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
   * all tables.
   * @return this Options instance.
   */
  public static Options combiners(List<String> combiners) {
    return new Options().combiners(combiners);
  }

  /**
   * Sets the combiners option.
   *
   * @param combiners A list of string scalars, one for each embedding table that specify
   * how to normalize the embedding activations after weighted summation.
   * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
   * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
   * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
   * all tables.
   * @return this Options instance.
   */
  public static Options combiners(String... combiners) {
    return new Options().combiners(combiners);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.EnqueueTPUEmbeddingBatch}
   */
  public static class Options {
    private Long deviceOrdinal;

    private List<String> combiners;

    private Options() {
    }

    /**
     * Sets the deviceOrdinal option.
     *
     * @param deviceOrdinal The TPU device to use. This should be -1 when the Op
     * is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
     * device.
     * @return this Options instance.
     */
    public Options deviceOrdinal(Long deviceOrdinal) {
      this.deviceOrdinal = deviceOrdinal;
      return this;
    }

    /**
     * Sets the combiners option.
     *
     * @param combiners A list of string scalars, one for each embedding table that specify
     * how to normalize the embedding activations after weighted summation.
     * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
     * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
     * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
     * all tables.
     * @return this Options instance.
     */
    public Options combiners(List<String> combiners) {
      this.combiners = combiners;
      return this;
    }

    /**
     * Sets the combiners option.
     *
     * @param combiners A list of string scalars, one for each embedding table that specify
     * how to normalize the embedding activations after weighted summation.
     * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
     * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
     * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
     * all tables.
     * @return this Options instance.
     */
    public Options combiners(String... combiners) {
      this.combiners = Arrays.asList(combiners);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EnqueueTPUEmbeddingBatch.class
  )
  public static class Inputs extends RawOpInputs<EnqueueTPUEmbeddingBatch> {
    /**
     * A list of 1D tensors, one for each embedding table, containing the
     * batch inputs encoded as dist_belief.SparseFeatures protos. If the weight
     * field in the SparseFeatures proto is not populated for an ID, a weight of
     * 1.0 is assumed.
     */
    public final Iterable<Operand<TString>> batch;

    /**
     * A string input that overrides the mode specified in the
     * TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
     * 'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
     * in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
     */
    public final Operand<TString> modeOverride;

    /**
     * The TPU device to use. This should be -1 when the Op
     * is running on a TPU device, and >= 0 when the Op is running on the CPU
     * device.
     */
    public final long deviceOrdinal;

    /**
     * A list of string scalars, one for each embedding table that specify
     * how to normalize the embedding activations after weighted summation.
     * Supported combiners are 'mean', 'sum', or 'sqrtn'. It is invalid to have
     * the sum of the weights be 0 for 'mean' or the sum of the squared weights be
     * 0 for 'sqrtn'. If combiners isn't passed, the default is to use 'sum' for
     * all tables.
     */
    public final String[] combiners;

    public Inputs(GraphOperation op) {
      super(new EnqueueTPUEmbeddingBatch(op), op, Arrays.asList("device_ordinal", "combiners"));
      int inputIndex = 0;
      int batchLength = op.inputListLength("batch");
      batch = Arrays.asList((Operand<TString>[]) op.inputList(inputIndex, batchLength));
      inputIndex += batchLength;
      modeOverride = (Operand<TString>) op.input(inputIndex++);
      deviceOrdinal = op.attributes().getAttrInt("device_ordinal");
      combiners = op.attributes().getAttrStringList("combiners");
    }
  }
}
