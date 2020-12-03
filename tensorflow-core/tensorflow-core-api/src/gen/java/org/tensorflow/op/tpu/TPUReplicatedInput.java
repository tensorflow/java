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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Connects N inputs to an N-way replicated TPU computation.
 * <p>
 * This operation holds a replicated input to a `tpu.replicate()` computation subgraph.
 * Each replicated input has the same shape and type alongside the output.
 * <p>
 * For example:
 * <pre>{@code
 * %a = "tf.opA"()
 * %b = "tf.opB"()
 * %replicated_input = "tf.TPUReplicatedInput"(%a, %b)
 * %computation = "tf.Computation"(%replicated_input)
 * }</pre>
 * The above computation has a replicated input of two replicas.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class TPUReplicatedInput<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.TPUReplicatedInput}
   */
  public static class Options {
    
    /**
     * @param isMirroredVariable 
     */
    public Options isMirroredVariable(Boolean isMirroredVariable) {
      this.isMirroredVariable = isMirroredVariable;
      return this;
    }
    
    /**
     * @param index 
     */
    public Options index(Long index) {
      this.index = index;
      return this;
    }
    
    /**
     * @param isPacked 
     */
    public Options isPacked(Boolean isPacked) {
      this.isPacked = isPacked;
      return this;
    }
    
    private Boolean isMirroredVariable;
    private Long index;
    private Boolean isPacked;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TPUReplicatedInput operation.
   * 
   * @param scope current scope
   * @param inputs 
   * @param options carries optional attributes values
   * @return a new instance of TPUReplicatedInput
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TPUReplicatedInput<T> create(Scope scope, Iterable<Operand<T>> inputs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TPUReplicatedInput", scope.makeOpName("TPUReplicatedInput"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.isMirroredVariable != null) {
          opBuilder.setAttr("is_mirrored_variable", opts.isMirroredVariable);
        }
        if (opts.index != null) {
          opBuilder.setAttr("index", opts.index);
        }
        if (opts.isPacked != null) {
          opBuilder.setAttr("is_packed", opts.isPacked);
        }
      }
    }
    return new TPUReplicatedInput<T>(opBuilder.build());
  }
  
  /**
   * @param isMirroredVariable 
   */
  public static Options isMirroredVariable(Boolean isMirroredVariable) {
    return new Options().isMirroredVariable(isMirroredVariable);
  }
  
  /**
   * @param index 
   */
  public static Options index(Long index) {
    return new Options().index(index);
  }
  
  /**
   * @param isPacked 
   */
  public static Options isPacked(Boolean isPacked) {
    return new Options().isPacked(isPacked);
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TPUReplicatedInput";
  
  private Output<T> output;
  
  private TPUReplicatedInput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
