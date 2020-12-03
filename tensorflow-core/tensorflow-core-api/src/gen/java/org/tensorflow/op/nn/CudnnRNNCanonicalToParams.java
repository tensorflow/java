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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts CudnnRNN params from canonical form to usable form. It supports the projection in LSTM.
 * <p>
 * Writes a set of weights into the opaque params buffer so they can be used in
 * upcoming training or inferences.
 * <p>
 * Note that the params buffer may not be compatible across different GPUs. So any
 * save and restoration should be converted to and from the canonical weights and
 * biases.
 * <p>
 * num_layers: Specifies the number of layers in the RNN model.
 * num_units: Specifies the size of the hidden state.
 * input_size: Specifies the size of the input state.
 * weights: the canonical form of weights that can be used for saving
 *     and restoration. They are more likely to be compatible across different
 *     generations.
 * biases: the canonical form of biases that can be used for saving
 *     and restoration. They are more likely to be compatible across different
 *     generations.
 * num_params_weights: number of weight parameter matrix for all layers.
 * num_params_biases: number of bias parameter vector for all layers.
 * rnn_mode: Indicates the type of the RNN model.
 * input_mode: Indicate whether there is a linear projection between the input and
 *     The actual computation before the first layer. 'skip_input' is only allowed
 *     when input_size == num_units; 'auto_select' implies 'skip_input' when
 *     input_size == num_units; otherwise, it implies 'linear_input'.
 * direction: Indicates whether a bidirectional model will be used.
 *     dir = (direction == bidirectional) ? 2 : 1
 * dropout: dropout probability. When set to 0., dropout is disabled.
 * seed: the 1st part of a seed to initialize dropout.
 * seed2: the 2nd part of a seed to initialize dropout.
 * num_proj: The output dimensionality for the projection matrices. If None or 0,
 *     no projection is performed.
 * 
 * @param <T> data type for {@code params()} output
 */
@Operator(group = "nn")
public final class CudnnRNNCanonicalToParams<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CudnnRNNCanonicalToParams}
   */
  public static class Options {
    
    /**
     * @param rnnMode 
     */
    public Options rnnMode(String rnnMode) {
      this.rnnMode = rnnMode;
      return this;
    }
    
    /**
     * @param inputMode 
     */
    public Options inputMode(String inputMode) {
      this.inputMode = inputMode;
      return this;
    }
    
    /**
     * @param direction 
     */
    public Options direction(String direction) {
      this.direction = direction;
      return this;
    }
    
    /**
     * @param dropout 
     */
    public Options dropout(Float dropout) {
      this.dropout = dropout;
      return this;
    }
    
    /**
     * @param seed 
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2 
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
    
    /**
     * @param numProj 
     */
    public Options numProj(Long numProj) {
      this.numProj = numProj;
      return this;
    }
    
    private String rnnMode;
    private String inputMode;
    private String direction;
    private Float dropout;
    private Long seed;
    private Long seed2;
    private Long numProj;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new CudnnRNNCanonicalToParams operation.
   * 
   * @param scope current scope
   * @param numLayers 
   * @param numUnits 
   * @param inputSize 
   * @param weights 
   * @param biases 
   * @param options carries optional attributes values
   * @return a new instance of CudnnRNNCanonicalToParams
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> CudnnRNNCanonicalToParams<T> create(Scope scope, Operand<TInt32> numLayers, Operand<TInt32> numUnits, Operand<TInt32> inputSize, Iterable<Operand<T>> weights, Iterable<Operand<T>> biases, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CudnnRNNCanonicalToParamsV2", scope.makeOpName("CudnnRNNCanonicalToParams"));
    opBuilder.addInput(numLayers.asOutput());
    opBuilder.addInput(numUnits.asOutput());
    opBuilder.addInput(inputSize.asOutput());
    opBuilder.addInputList(Operands.asOutputs(weights));
    opBuilder.addInputList(Operands.asOutputs(biases));
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
      }
    }
    return new CudnnRNNCanonicalToParams<T>(opBuilder.build());
  }
  
  /**
   * @param rnnMode 
   */
  public static Options rnnMode(String rnnMode) {
    return new Options().rnnMode(rnnMode);
  }
  
  /**
   * @param inputMode 
   */
  public static Options inputMode(String inputMode) {
    return new Options().inputMode(inputMode);
  }
  
  /**
   * @param direction 
   */
  public static Options direction(String direction) {
    return new Options().direction(direction);
  }
  
  /**
   * @param dropout 
   */
  public static Options dropout(Float dropout) {
    return new Options().dropout(dropout);
  }
  
  /**
   * @param seed 
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2 
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }
  
  /**
   * @param numProj 
   */
  public static Options numProj(Long numProj) {
    return new Options().numProj(numProj);
  }
  
  /**
   */
  public Output<T> params() {
    return params;
  }
  
  @Override
  public Output<T> asOutput() {
    return params;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CudnnRNNCanonicalToParamsV2";
  
  private Output<T> params;
  
  private CudnnRNNCanonicalToParams(Operation operation) {
    super(operation);
    int outputIdx = 0;
    params = operation.output(outputIdx++);
  }
}
