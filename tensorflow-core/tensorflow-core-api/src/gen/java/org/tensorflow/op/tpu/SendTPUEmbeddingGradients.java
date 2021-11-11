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

package org.tensorflow.op.tpu;

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
import org.tensorflow.types.TFloat32;

/**
 * Performs gradient updates of embedding tables.
 */
@OpMetadata(
    opType = SendTPUEmbeddingGradients.OP_NAME,
    inputsClass = SendTPUEmbeddingGradients.Inputs.class
)
public final class SendTPUEmbeddingGradients extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SendTPUEmbeddingGradients";

  public SendTPUEmbeddingGradients(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new SendTPUEmbeddingGradients operation.
   *
   * @param scope current scope
   * @param inputs A TensorList of gradients with which to update embedding tables.
   * This argument has the same length and shapes as the return value of
   * RecvTPUEmbeddingActivations, but contains gradients of the model's loss
   * with respect to the embedding activations. The embedding tables are updated
   * from these gradients via the optimizer specified in the TPU embedding
   * configuration given to tpu.initialize_system.
   * @param learningRates A TensorList of float32 scalars, one for each dynamic learning
   * rate tag: see the comments in
   * //third_party/tensorflow/core/protobuf/tpu/optimization_parameters.proto.
   * Multiple tables can share the same dynamic learning rate tag as specified
   * in the configuration. If the learning rates for all tables are constant,
   * this list should be empty.
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @param options carries optional attribute values
   * @return a new instance of SendTPUEmbeddingGradients
   */
  @Endpoint(
      describeByClass = true
  )
  public static SendTPUEmbeddingGradients create(Scope scope, Iterable<Operand<TFloat32>> inputs,
      Iterable<Operand<TFloat32>> learningRates, String config, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SendTPUEmbeddingGradients");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder.addInputList(Operands.asOutputs(learningRates));
    opBuilder.setAttr("config", config);
    if (options != null) {
      for (Options opts : options) {
        if (opts.NN != null) {
          opBuilder.setAttr("NN", opts.NN);
        }
      }
    }
    return new SendTPUEmbeddingGradients(opBuilder.build());
  }

  /**
   * Sets the NN option.
   *
   * @param NN the NN option
   * @return this Options instance.
   */
  public static Options NN(Long NN) {
    return new Options().NN(NN);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.SendTPUEmbeddingGradients}
   */
  public static class Options {
    private Long NN;

    private Options() {
    }

    /**
     * Sets the NN option.
     *
     * @param NN the NN option
     * @return this Options instance.
     */
    public Options NN(Long NN) {
      this.NN = NN;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SendTPUEmbeddingGradients.class
  )
  public static class Inputs extends RawOpInputs<SendTPUEmbeddingGradients> {
    /**
     * A TensorList of gradients with which to update embedding tables.
     * This argument has the same length and shapes as the return value of
     * RecvTPUEmbeddingActivations, but contains gradients of the model's loss
     * with respect to the embedding activations. The embedding tables are updated
     * from these gradients via the optimizer specified in the TPU embedding
     * configuration given to tpu.initialize_system.
     */
    public final Iterable<Operand<TFloat32>> inputs;

    /**
     * A TensorList of float32 scalars, one for each dynamic learning
     * rate tag: see the comments in
     * //third_party/tensorflow/core/protobuf/tpu/optimization_parameters.proto.
     * Multiple tables can share the same dynamic learning rate tag as specified
     * in the configuration. If the learning rates for all tables are constant,
     * this list should be empty.
     */
    public final Iterable<Operand<TFloat32>> learningRates;

    /**
     * Serialized TPUEmbeddingConfiguration proto.
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new SendTPUEmbeddingGradients(op), op, Arrays.asList("config"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      int learningRatesLength = op.inputListLength("learning_rates");
      learningRates = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, learningRatesLength));
      inputIndex += learningRatesLength;
      config = op.attributes().getAttrString("config");
    }
  }
}
