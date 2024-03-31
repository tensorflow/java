/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.xla;

import java.util.Arrays;
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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * An op that performs gradient updates of embedding tables.
 * The gradients argument is a TensorList having the same length and shapes as the
 * return value of XlaRecvTPUEmbeddingActivations, but contains gradients of the
 * model's loss with respect to the embedding activations. The embedding tables are
 * updated from these gradients via the optimizer specified in the
 * TPUEmbeddingConfiguration proto given to tpu.initialize_system.
 */
@OpMetadata(
    opType = XlaSendTPUEmbeddingGradients.OP_NAME,
    inputsClass = XlaSendTPUEmbeddingGradients.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSendTPUEmbeddingGradients extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSendTPUEmbeddingGradients";

  public XlaSendTPUEmbeddingGradients(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new XlaSendTPUEmbeddingGradients operation.
   *
   * @param scope current scope
   * @param gradients A TensorList of gradients with which to update embedding tables.
   * @param learningRates A TensorList of learning rates used for updating the embedding
   * tables via the optimizer. The length of the TensorList must be equal to the
   * number of dynamic learning rate tags specified in the
   * TPUEmbeddingConfiguration proto.
   * @param deduplicationData A Tensor with type=DT_VARIANT containing the deduplication
   * data. The tensor is an XLA nested tuple containing N elements (where N is
   * the ratio of the number of embedding to tensor cores per TPU chip). Each
   * element of the nested tuple is a tuple of rank 1 tensors. Each tensor either
   * contains indices (DT_UINT32) for embedding lookup on the TensorCore or
   * weights (DT_FLOAT) to apply to the output of the embedding lookup operation.
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @param options carries optional attribute values
   * @return a new instance of XlaSendTPUEmbeddingGradients
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSendTPUEmbeddingGradients create(Scope scope,
      Iterable<Operand<TFloat32>> gradients, Iterable<Operand<TFloat32>> learningRates,
      Operand<? extends TType> deduplicationData, String config, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSendTPUEmbeddingGradients");
    opBuilder.addInputList(Operands.asOutputs(gradients));
    opBuilder.addInputList(Operands.asOutputs(learningRates));
    opBuilder.addInput(deduplicationData.asOutput());
    opBuilder.setAttr("config", config);
    if (options != null) {
      for (Options opts : options) {
        if (opts.NumLearningRateTags != null) {
          opBuilder.setAttr("NumLearningRateTags", opts.NumLearningRateTags);
        }
      }
    }
    return new XlaSendTPUEmbeddingGradients(opBuilder.build());
  }

  /**
   * Sets the NumLearningRateTags option.
   *
   * @param NumLearningRateTags number of learning rate tags
   * @return this Options instance.
   */
  public static Options NumLearningRateTags(Long NumLearningRateTags) {
    return new Options().NumLearningRateTags(NumLearningRateTags);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSendTPUEmbeddingGradients}
   */
  public static class Options {
    private Long NumLearningRateTags;

    private Options() {
    }

    /**
     * Sets the NumLearningRateTags option.
     *
     * @param NumLearningRateTags number of learning rate tags
     * @return this Options instance.
     */
    public Options NumLearningRateTags(Long NumLearningRateTags) {
      this.NumLearningRateTags = NumLearningRateTags;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = XlaSendTPUEmbeddingGradients.class
  )
  public static class Inputs extends RawOpInputs<XlaSendTPUEmbeddingGradients> {
    /**
     * A TensorList of gradients with which to update embedding tables.
     */
    public final Iterable<Operand<TFloat32>> gradients;

    /**
     * A TensorList of learning rates used for updating the embedding
     * tables via the optimizer. The length of the TensorList must be equal to the
     * number of dynamic learning rate tags specified in the
     * TPUEmbeddingConfiguration proto.
     */
    public final Iterable<Operand<TFloat32>> learningRates;

    /**
     * A Tensor with type=DT_VARIANT containing the deduplication
     * data. The tensor is an XLA nested tuple containing N elements (where N is
     * the ratio of the number of embedding to tensor cores per TPU chip). Each
     * element of the nested tuple is a tuple of rank 1 tensors. Each tensor either
     * contains indices (DT_UINT32) for embedding lookup on the TensorCore or
     * weights (DT_FLOAT) to apply to the output of the embedding lookup operation.
     */
    public final Operand<? extends TType> deduplicationData;

    /**
     * Serialized TPUEmbeddingConfiguration proto.
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new XlaSendTPUEmbeddingGradients(op), op, Arrays.asList("config"));
      int inputIndex = 0;
      int gradientsLength = op.inputListLength("gradients");
      gradients = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, gradientsLength));
      inputIndex += gradientsLength;
      int learningRatesLength = op.inputListLength("learning_rates");
      learningRates = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, learningRatesLength));
      inputIndex += learningRatesLength;
      deduplicationData = (Operand<? extends TType>) op.input(inputIndex++);
      config = op.attributes().getAttrString("config");
    }
  }
}
