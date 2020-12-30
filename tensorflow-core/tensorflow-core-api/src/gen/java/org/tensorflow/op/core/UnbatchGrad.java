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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Gradient of Unbatch.
 * <p>
 * Acts like Batch but using the given batch_index index of batching things as they
 * become available. This ensures that the gradients are propagated back in the
 * same session which did the forward pass.
 * <p>
 * original_input: The input to the Unbatch operation this is the gradient of.
 * batch_index: The batch_index given to the Unbatch operation this is the gradient
 * of.
 * grad: The downstream gradient.
 * id: The id scalar emitted by Batch.
 * batched_grad: The return value, either an empty tensor or the batched gradient.
 * container: Container to control resource sharing.
 * shared_name: Instances of UnbatchGrad with the same container and shared_name
 *  are assumed to possibly belong to the same batch. If left empty, the op name
 *  will be used as the shared name.
 * 
 * @param <T> data type for {@code batchedGrad()} output
 */
@Operator
public final class UnbatchGrad<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.UnbatchGrad}
   */
  public static class Options {
    
    /**
     * @param container 
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName 
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    private String container;
    private String sharedName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new UnbatchGrad operation.
   * 
   * @param scope current scope
   * @param originalInput 
   * @param batchIndex 
   * @param grad 
   * @param id 
   * @param options carries optional attributes values
   * @return a new instance of UnbatchGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> UnbatchGrad<T> create(Scope scope, Operand<T> originalInput, Operand<TInt64> batchIndex, Operand<T> grad, Operand<TInt64> id, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("UnbatchGrad", scope.makeOpName("UnbatchGrad"));
    opBuilder.addInput(originalInput.asOutput());
    opBuilder.addInput(batchIndex.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(id.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new UnbatchGrad<T>(opBuilder.build());
  }
  
  /**
   * @param container 
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName 
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   */
  public Output<T> batchedGrad() {
    return batchedGrad;
  }
  
  @Override
  public Output<T> asOutput() {
    return batchedGrad;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "UnbatchGrad";
  
  private Output<T> batchedGrad;
  
  private UnbatchGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    batchedGrad = operation.output(outputIdx++);
  }
}
