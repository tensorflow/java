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
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes size of weights that can be used by a Cudnn RNN model.
 * Return the params size that can be used by the Cudnn RNN model. Subsequent
 * weight allocation and initialization should use this size.
 * <p>num_layers: Specifies the number of layers in the RNN model.
 * num_units: Specifies the size of the hidden state.
 * input_size: Specifies the size of the input state.
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
 * params_size: The size of the params buffer that should be allocated and
 * initialized for this RNN model. Note that this params buffer may not be
 * compatible across GPUs. Please use CudnnRNNParamsWeights and
 * CudnnRNNParamsBiases to save and restore them in a way that is compatible
 * across different runs.
 *
 * @param <T> data type for {@code params_size} output
 */
@OpMetadata(
    opType = CudnnRnnParamsSize.OP_NAME,
    inputsClass = CudnnRnnParamsSize.Inputs.class
)
@Operator(
    group = "nn"
)
public final class CudnnRnnParamsSize<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CudnnRNNParamsSize";

  private Output<T> paramsSize;

  public CudnnRnnParamsSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    paramsSize = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CudnnRNNParamsSize operation.
   *
   * @param scope current scope
   * @param numLayers The numLayers value
   * @param numUnits The numUnits value
   * @param inputSize The inputSize value
   * @param T The value of the T attribute
   * @param S The value of the S attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNParamsSize} output and operands
   * @param <U> data type for {@code CudnnRNNParamsSize} output and operands
   * @return a new instance of CudnnRnnParamsSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TNumber> CudnnRnnParamsSize<T> create(Scope scope,
      Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize, Class<U> T,
      Class<T> S, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CudnnRnnParamsSize");
    opBuilder.addInput(numLayers.asOutput());
    opBuilder.addInput(numUnits.asOutput());
    opBuilder.addInput(inputSize.asOutput());
    opBuilder.setAttr("T", Operands.toDataType(T));
    opBuilder.setAttr("S", Operands.toDataType(S));
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
    return new CudnnRnnParamsSize<>(opBuilder.build());
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
   * Gets paramsSize.
   *
   * @return paramsSize.
   */
  public Output<T> paramsSize() {
    return paramsSize;
  }

  @Override
  public Output<T> asOutput() {
    return paramsSize;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CudnnRnnParamsSize}
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

  @OpInputsMetadata(
      outputsClass = CudnnRnnParamsSize.class
  )
  public static class Inputs extends RawOpInputs<CudnnRnnParamsSize<?>> {
    /**
     * The numLayers input
     */
    public final Operand<TInt32> numLayers;

    /**
     * The numUnits input
     */
    public final Operand<TInt32> numUnits;

    /**
     * The inputSize input
     */
    public final Operand<TInt32> inputSize;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The S attribute
     */
    public final DataType S;

    /**
     * The rnnMode attribute
     */
    public final String rnnMode;

    /**
     * The inputMode attribute
     */
    public final String inputMode;

    /**
     * The direction attribute
     */
    public final String direction;

    /**
     * The dropout attribute
     */
    public final float dropout;

    /**
     * The seed attribute
     */
    public final long seed;

    /**
     * The seed2 attribute
     */
    public final long seed2;

    /**
     * The numProj attribute
     */
    public final long numProj;

    public Inputs(GraphOperation op) {
      super(new CudnnRnnParamsSize<>(op), op, Arrays.asList("T", "S", "rnn_mode", "input_mode", "direction", "dropout", "seed", "seed2", "num_proj"));
      int inputIndex = 0;
      numLayers = (Operand<TInt32>) op.input(inputIndex++);
      numUnits = (Operand<TInt32>) op.input(inputIndex++);
      inputSize = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      S = op.attributes().getAttrType("S");
      rnnMode = op.attributes().getAttrString("rnn_mode");
      inputMode = op.attributes().getAttrString("input_mode");
      direction = op.attributes().getAttrString("direction");
      dropout = op.attributes().getAttrFloat("dropout");
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
      numProj = op.attributes().getAttrInt("num_proj");
    }
  }
}
