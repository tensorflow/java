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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Gather slices from the variable pointed to by `resource` according to `indices`.
 * <p>
 * `indices` must be an integer tensor of any dimension (usually 0-D or 1-D).
 * Produces an output tensor with shape `indices.shape + params.shape[1:]` where:
 * <pre>{@code
 *     # Scalar indices
 *     output[:, ..., :] = params[indices, :, ... :]
 * 
 *     # Vector indices
 *     output[i, :, ..., :] = params[indices[i], :, ... :]
 * 
 *     # Higher rank indices
 *     output[i, ..., j, :, ... :] = params[indices[i, ..., j], :, ..., :]
 * }</pre>
 * 
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class ResourceGather<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.ResourceGather}
   */
  public static class Options {
    
    /**
     * @param batchDims 
     */
    public Options batchDims(Long batchDims) {
      this.batchDims = batchDims;
      return this;
    }
    
    /**
     * @param validateIndices 
     */
    public Options validateIndices(Boolean validateIndices) {
      this.validateIndices = validateIndices;
      return this;
    }
    
    private Long batchDims;
    private Boolean validateIndices;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ResourceGather operation.
   * 
   * @param scope current scope
   * @param resource 
   * @param indices 
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of ResourceGather
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> ResourceGather<U> create(Scope scope, Operand<?> resource, Operand<T> indices, DataType<U> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceGather", scope.makeOpName("ResourceGather"));
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    if (options != null) {
      for (Options opts : options) {
        if (opts.batchDims != null) {
          opBuilder.setAttr("batch_dims", opts.batchDims);
        }
        if (opts.validateIndices != null) {
          opBuilder.setAttr("validate_indices", opts.validateIndices);
        }
      }
    }
    return new ResourceGather<U>(opBuilder.build());
  }
  
  /**
   * @param batchDims 
   */
  public static Options batchDims(Long batchDims) {
    return new Options().batchDims(batchDims);
  }
  
  /**
   * @param validateIndices 
   */
  public static Options validateIndices(Boolean validateIndices) {
    return new Options().validateIndices(validateIndices);
  }
  
  /**
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ResourceGather";
  
  private Output<U> output;
  
  private ResourceGather(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
