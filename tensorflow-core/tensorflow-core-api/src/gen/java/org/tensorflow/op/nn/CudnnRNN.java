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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * A RNN backed by cuDNN.
 * Computes the RNN from the input and initial states, with respect to the params
 * buffer. Accepts one extra input &quot;sequence_lengths&quot; than CudnnRNN.
 * <p>rnn_mode: Indicates the type of the RNN model.
 * input_mode: Indicates whether there is a linear projection between the input and
 * the actual computation before the first layer. 'skip_input' is only allowed
 * when input_size == num_units; 'auto_select' implies 'skip_input' when
 * input_size == num_units; otherwise, it implies 'linear_input'.
 * direction: Indicates whether a bidirectional model will be used. Should be
 * &quot;unidirectional&quot; or &quot;bidirectional&quot;.
 * dropout: Dropout probability. When set to 0., dropout is disabled.
 * seed: The 1st part of a seed to initialize dropout.
 * seed2: The 2nd part of a seed to initialize dropout.
 * input: If time_major is true, this is a 3-D tensor with the shape of
 * [seq_length, batch_size, input_size]. If time_major is false, the shape is
 * [batch_size, seq_length, input_size].
 * input_h: If time_major is true, this is a 3-D tensor with the shape of
 * [num_layer * dir, batch_size, num_units]. If time_major is false, the shape
 * is [batch_size, num_layer * dir, num_units].
 * input_c: For LSTM, a 3-D tensor with the shape of
 * [num_layer * dir, batch, num_units]. For other models, it is ignored.
 * params: A 1-D tensor that contains the weights and biases in an opaque layout.
 * The size must be created through CudnnRNNParamsSize, and initialized
 * separately. Note that they might not be compatible across different
 * generations. So it is a good idea to save and restore
 * sequence_lengths: a vector of lengths of each input sequence.
 * output: If time_major is true, this is a 3-D tensor with the shape of
 * [seq_length, batch_size, dir * num_units]. If time_major is false, the
 * shape is [batch_size, seq_length, dir * num_units].
 * output_h: The same shape has input_h.
 * output_c: The same shape as input_c for LSTM. An empty tensor for other models.
 * is_training: Indicates whether this operation is used for inference or
 * training.
 * time_major: Indicates whether the input/output format is time major or batch
 * major.
 * reserve_space: An opaque tensor that can be used in backprop calculation. It
 * is only produced if is_training is true.
 *
 * @param <T> data type for {@code output} output
 */
public final class CudnnRNN<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CudnnRNNV3";

  private Output<T> output;

  private Output<T> outputH;

  private Output<T> outputC;

  private Output<T> reserveSpace;

  private Output<? extends TType> hostReserved;

  @SuppressWarnings("unchecked")
  private CudnnRNN(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputH = operation.output(outputIdx++);
    outputC = operation.output(outputIdx++);
    reserveSpace = operation.output(outputIdx++);
    hostReserved = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CudnnRNNV3 operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param inputH the inputH value
   * @param inputC the inputC value
   * @param params the params value
   * @param sequenceLengths the sequenceLengths value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNV3} output and operands
   * @return a new instance of CudnnRNN
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CudnnRNN<T> create(Scope scope, Operand<T> input,
      Operand<T> inputH, Operand<T> inputC, Operand<T> params, Operand<TInt32> sequenceLengths,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("CudnnRNN"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputH.asOutput());
    opBuilder.addInput(inputC.asOutput());
    opBuilder.addInput(params.asOutput());
    opBuilder.addInput(sequenceLengths.asOutput());
    opBuilder = scope.apply(opBuilder);
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
        if (opts.isTraining != null) {
          opBuilder.setAttr("is_training", opts.isTraining);
        }
        if (opts.timeMajor != null) {
          opBuilder.setAttr("time_major", opts.timeMajor);
        }
      }
    }
    return new CudnnRNN<>(opBuilder.build());
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
   * Sets the isTraining option.
   *
   * @param isTraining the isTraining option
   * @return this Options instance.
   */
  public static Options isTraining(Boolean isTraining) {
    return new Options().isTraining(isTraining);
  }

  /**
   * Sets the timeMajor option.
   *
   * @param timeMajor the timeMajor option
   * @return this Options instance.
   */
  public static Options timeMajor(Boolean timeMajor) {
    return new Options().timeMajor(timeMajor);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  /**
   * Gets outputH.
   *
   * @return outputH.
   */
  public Output<T> outputH() {
    return outputH;
  }

  /**
   * Gets outputC.
   *
   * @return outputC.
   */
  public Output<T> outputC() {
    return outputC;
  }

  /**
   * Gets reserveSpace.
   *
   * @return reserveSpace.
   */
  public Output<T> reserveSpace() {
    return reserveSpace;
  }

  /**
   * Gets hostReserved.
   *
   * @return hostReserved.
   */
  public Output<? extends TType> hostReserved() {
    return hostReserved;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CudnnRNN}
   */
  public static class Options {
    private String rnnMode;

    private String inputMode;

    private String direction;

    private Float dropout;

    private Long seed;

    private Long seed2;

    private Long numProj;

    private Boolean isTraining;

    private Boolean timeMajor;

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

    /**
     * Sets the isTraining option.
     *
     * @param isTraining the isTraining option
     * @return this Options instance.
     */
    public Options isTraining(Boolean isTraining) {
      this.isTraining = isTraining;
      return this;
    }

    /**
     * Sets the timeMajor option.
     *
     * @param timeMajor the timeMajor option
     * @return this Options instance.
     */
    public Options timeMajor(Boolean timeMajor) {
      this.timeMajor = timeMajor;
      return this;
    }
  }
}
