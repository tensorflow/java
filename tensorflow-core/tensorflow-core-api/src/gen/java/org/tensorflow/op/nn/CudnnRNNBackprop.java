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
 * Backprop step of CudnnRNNV3.
 * Compute the backprop of both data and weights in a RNN. Takes an extra
 * &quot;sequence_lengths&quot; input than CudnnRNNBackprop.
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
 * output_backprop: A 3-D tensor with the same shape as output in the forward pass.
 * output_h_backprop: A 3-D tensor with the same shape as output_h in the forward
 * pass.
 * output_c_backprop: A 3-D tensor with the same shape as output_c in the forward
 * pass.
 * time_major: Indicates whether the input/output format is time major or batch
 * major.
 * reserve_space: The same reserve_space produced in the forward operation.
 * input_backprop: The backprop to input in the forward pass. Has the same shape
 * as input.
 * input_h_backprop: The backprop to input_h in the forward pass. Has the same
 * shape as input_h.
 * input_c_backprop: The backprop to input_c in the forward pass. Has the same
 * shape as input_c.
 * params_backprop: The backprop to the params buffer in the forward pass. Has the
 * same shape as params.
 *
 * @param <T> data type for {@code input_backprop} output
 */
public final class CudnnRNNBackprop<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CudnnRNNBackpropV3";

  private Output<T> inputBackprop;

  private Output<T> inputHBackprop;

  private Output<T> inputCBackprop;

  private Output<T> paramsBackprop;

  private CudnnRNNBackprop(Operation operation) {
    super(operation);
    int outputIdx = 0;
    inputBackprop = operation.output(outputIdx++);
    inputHBackprop = operation.output(outputIdx++);
    inputCBackprop = operation.output(outputIdx++);
    paramsBackprop = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CudnnRNNBackpropV3 operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param inputH the inputH value
   * @param inputC the inputC value
   * @param params the params value
   * @param sequenceLengths the sequenceLengths value
   * @param output the output value
   * @param outputH the outputH value
   * @param outputC the outputC value
   * @param outputBackprop the outputBackprop value
   * @param outputHBackprop the outputHBackprop value
   * @param outputCBackprop the outputCBackprop value
   * @param reserveSpace the reserveSpace value
   * @param hostReserved the hostReserved value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CudnnRNNBackpropV3} output and operands
   * @return a new instance of CudnnRNNBackprop
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CudnnRNNBackprop<T> create(Scope scope, Operand<T> input,
      Operand<T> inputH, Operand<T> inputC, Operand<T> params, Operand<TInt32> sequenceLengths,
      Operand<T> output, Operand<T> outputH, Operand<T> outputC, Operand<T> outputBackprop,
      Operand<T> outputHBackprop, Operand<T> outputCBackprop, Operand<T> reserveSpace,
      Operand<? extends TType> hostReserved, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CudnnRNNBackpropV3", scope.makeOpName("CudnnRNNBackprop"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputH.asOutput());
    opBuilder.addInput(inputC.asOutput());
    opBuilder.addInput(params.asOutput());
    opBuilder.addInput(sequenceLengths.asOutput());
    opBuilder.addInput(output.asOutput());
    opBuilder.addInput(outputH.asOutput());
    opBuilder.addInput(outputC.asOutput());
    opBuilder.addInput(outputBackprop.asOutput());
    opBuilder.addInput(outputHBackprop.asOutput());
    opBuilder.addInput(outputCBackprop.asOutput());
    opBuilder.addInput(reserveSpace.asOutput());
    opBuilder.addInput(hostReserved.asOutput());
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
        if (opts.timeMajor != null) {
          opBuilder.setAttr("time_major", opts.timeMajor);
        }
      }
    }
    return new CudnnRNNBackprop<>(opBuilder.build());
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
   * Sets the timeMajor option.
   *
   * @param timeMajor the timeMajor option
   * @return this Options instance.
   */
  public static Options timeMajor(Boolean timeMajor) {
    return new Options().timeMajor(timeMajor);
  }

  /**
   * Gets inputBackprop.
   *
   * @return inputBackprop.
   */
  public Output<T> inputBackprop() {
    return inputBackprop;
  }

  /**
   * Gets inputHBackprop.
   *
   * @return inputHBackprop.
   */
  public Output<T> inputHBackprop() {
    return inputHBackprop;
  }

  /**
   * Gets inputCBackprop.
   *
   * @return inputCBackprop.
   */
  public Output<T> inputCBackprop() {
    return inputCBackprop;
  }

  /**
   * Gets paramsBackprop.
   *
   * @return paramsBackprop.
   */
  public Output<T> paramsBackprop() {
    return paramsBackprop;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CudnnRNNBackprop}
   */
  public static class Options {
    private String rnnMode;

    private String inputMode;

    private String direction;

    private Float dropout;

    private Long seed;

    private Long seed2;

    private Long numProj;

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
