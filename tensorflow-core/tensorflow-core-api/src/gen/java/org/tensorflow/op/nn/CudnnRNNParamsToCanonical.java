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

package org.tensorflow.op.nn;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Retrieves CudnnRNN params in canonical form. It supports the projection in LSTM.
 * Retrieves a set of weights from the opaque params buffer that can be saved and
 * restored in a way compatible with future runs.
 * <p>Note that the params buffer may not be compatible across different GPUs. So any
 * save and restoration should be converted to and from the canonical weights and
 * biases.
 * <p>num_layers: Specifies the number of layers in the RNN model.
 * num_units: Specifies the size of the hidden state.
 * input_size: Specifies the size of the input state.
 * num_params_weights: number of weight parameter matrix for all layers.
 * num_params_biases: number of bias parameter vector for all layers.
 * weights: the canonical form of weights that can be used for saving
 * and restoration. They are more likely to be compatible across different
 * generations.
 * biases: the canonical form of biases that can be used for saving
 * and restoration. They are more likely to be compatible across different
 * generations.
 * rnn_mode: Indicates the type of the RNN model.
 * input_mode: Indicate whether there is a linear projection between the input and
 * The actual computation before the first layer. 'skip_input' is only allowed
 * when input_size == num_units; 'auto_select' implies 'skip_input' when
 * input_size == num_units; otherwise, it implies 'linear_input'.
 * direction: Indicates whether a bidirectional model will be used.
 * dir = (direction == bidirectional) ? 2 : 1
 * dropout: dropout probability. When set to 0., dropout is disabled.
 * seed: the 1st part of a seed to initialize dropout.
 * seed2: the 2nd part of a seed to initialize dropout.
 * num_proj: The output dimensionality for the projection matrices. If None or 0,
 * no projection is performed.
 *
 * @param <T> data type for {@code weights} output
 */
@Operator(
    group = "nn"
)
public final class CudnnRNNParamsToCanonical<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CudnnRNNParamsToCanonicalV2";

  private List<Output<T>> weights;

  private List<Output<T>> biases;

  @SuppressWarnings("unchecked")
  private CudnnRNNParamsToCanonical(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int weightsLength = operation.outputListLength("weights");
    weights = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, weightsLength));
    outputIdx += weightsLength;
    int biasesLength = operation.outputListLength("biases");
    biases = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, biasesLength));
    outputIdx += biasesLength;
  }

  /**
   * Factory method to create a class wrapping a new CudnnRNNParamsToCanonicalV2 operation.
   *
   * @param scope current scope
   * @param numLayers the numLayers value
   * @param numUnits the numUnits value
   * @param inputSize the inputSize value
   * @param params the params value
   * @param numParamsWeights the value of the numParamsWeights property
   * @param numParamsBiases the value of the numParamsBiases property
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNParamsToCanonicalV2} output and operands
   * @return a new instance of CudnnRNNParamsToCanonical
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CudnnRNNParamsToCanonical<T> create(Scope scope,
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize,
      Operand<T> params, Long numParamsWeights, Long numParamsBiases, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CudnnRNNParamsToCanonicalV2", scope.makeOpName("CudnnRNNParamsToCanonical"));
    opBuilder.addInput(numLayers.asOutput());
    opBuilder.addInput(numUnits.asOutput());
    opBuilder.addInput(inputSize.asOutput());
    opBuilder.addInput(params.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num_params_weights", numParamsWeights);
    opBuilder.setAttr("num_params_biases", numParamsBiases);
    if (options != null) {
      for (Options opts : options) {
        if (opts.rnnMode != null) {
          opBuilder.setAttr("rnn_mode", opts.rnnMode);
        }
        if (opts.inputMode != null) {
          opBuilder.setAttr("input_mode", opts.inputMode);
        }
        if (opts.direction != null) {
          opBuilder.setAttr("direction", opts.direction);
        }
        if (opts.dropout != null) {
          opBuilder.setAttr("dropout", opts.dropout);
        }
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
        if (opts.numProj != null) {
          opBuilder.setAttr("num_proj", opts.numProj);
        }
      }
    }
    return new CudnnRNNParamsToCanonical<>(opBuilder.build());
  }

  /**
   * Sets the rnnMode option.
   *
   * @param rnnMode the rnnMode option
   * @return this Options instance.
   */
  public static Options rnnMode(String rnnMode) {
    return new Options().rnnMode(rnnMode);
  }

  /**
   * Sets the inputMode option.
   *
   * @param inputMode the inputMode option
   * @return this Options instance.
   */
  public static Options inputMode(String inputMode) {
    return new Options().inputMode(inputMode);
  }

  /**
   * Sets the direction option.
   *
   * @param direction the direction option
   * @return this Options instance.
   */
  public static Options direction(String direction) {
    return new Options().direction(direction);
  }

  /**
   * Sets the dropout option.
   *
   * @param dropout the dropout option
   * @return this Options instance.
   */
  public static Options dropout(Float dropout) {
    return new Options().dropout(dropout);
  }

  /**
   * Sets the seed option.
   *
   * @param seed the seed option
   * @return this Options instance.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }

  /**
   * Sets the seed2 option.
   *
   * @param seed2 the seed2 option
   * @return this Options instance.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }

  /**
   * Sets the numProj option.
   *
   * @param numProj the numProj option
   * @return this Options instance.
   */
  public static Options numProj(Long numProj) {
    return new Options().numProj(numProj);
  }

  /**
   * Gets weights.
   *
   * @return weights.
   */
  public List<Output<T>> weights() {
    return weights;
  }

  /**
   * Gets biases.
   *
   * @return biases.
   */
  public List<Output<T>> biases() {
    return biases;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CudnnRNNParamsToCanonical}
   */
  public static class Options {
    private String rnnMode;

    private String inputMode;

    private String direction;

    private Float dropout;

    private Long seed;

    private Long seed2;

    private Long numProj;

    private Options() {
    }

    /**
     * Sets the rnnMode option.
     *
     * @param rnnMode the rnnMode option
     * @return this Options instance.
     */
    public Options rnnMode(String rnnMode) {
      this.rnnMode = rnnMode;
      return this;
    }

    /**
     * Sets the inputMode option.
     *
     * @param inputMode the inputMode option
     * @return this Options instance.
     */
    public Options inputMode(String inputMode) {
      this.inputMode = inputMode;
      return this;
    }

    /**
     * Sets the direction option.
     *
     * @param direction the direction option
     * @return this Options instance.
     */
    public Options direction(String direction) {
      this.direction = direction;
      return this;
    }

    /**
     * Sets the dropout option.
     *
     * @param dropout the dropout option
     * @return this Options instance.
     */
    public Options dropout(Float dropout) {
      this.dropout = dropout;
      return this;
    }

    /**
     * Sets the seed option.
     *
     * @param seed the seed option
     * @return this Options instance.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }

    /**
     * Sets the seed2 option.
     *
     * @param seed2 the seed2 option
     * @return this Options instance.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }

    /**
     * Sets the numProj option.
     *
     * @param numProj the numProj option
     * @return this Options instance.
     */
    public Options numProj(Long numProj) {
      this.numProj = numProj;
      return this;
    }
  }
}
