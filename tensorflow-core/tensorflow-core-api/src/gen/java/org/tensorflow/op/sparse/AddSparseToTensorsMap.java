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

package org.tensorflow.op.sparse;

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
 * Add a `SparseTensor` to a `SparseTensorsMap` return its handle.
 * <p>
 * A `SparseTensor` is represented by three tensors: `sparse_indices`,
 * `sparse_values`, and `sparse_shape`.
 * <p>
 * This operator takes the given `SparseTensor` and adds it to a container
 * object (a `SparseTensorsMap`).  A unique key within this container is generated
 * in the form of an `int64`, and this is the value that is returned.
 * <p>
 * The `SparseTensor` can then be read out as part of a minibatch by passing
 * the key as a vector element to `TakeManySparseFromTensorsMap`.  To ensure
 * the correct `SparseTensorsMap` is accessed, ensure that the same
 * `container` and `shared_name` are passed to that Op.  If no `shared_name`
 * is provided here, instead use the <i>name</i> of the Operation created by calling
 * `sparse.AddSparseToTensorsMap` as the `shared_name` passed to
 * `TakeManySparseFromTensorsMap`.  Ensure the Operations are colocated.
 */
@Operator(group = "sparse")
public final class AddSparseToTensorsMap extends RawOp implements Operand<TInt64> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.AddSparseToTensorsMap}
   */
  public static class Options {
    
    /**
     * @param container The container name for the `SparseTensorsMap` created by this op.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName The shared name for the `SparseTensorsMap` created by this op.
     * If blank, the new Operation's unique name is used.
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
   * Factory method to create a class wrapping a new AddSparseToTensorsMap operation.
   * 
   * @param scope current scope
   * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
   * @param options carries optional attributes values
   * @return a new instance of AddSparseToTensorsMap
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> AddSparseToTensorsMap create(Scope scope, Operand<TInt64> sparseIndices, Operand<T> sparseValues, Operand<TInt64> sparseShape, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("AddSparseToTensorsMap", scope.makeOpName("AddSparseToTensorsMap"));
    opBuilder.addInput(sparseIndices.asOutput());
    opBuilder.addInput(sparseValues.asOutput());
    opBuilder.addInput(sparseShape.asOutput());
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
    return new AddSparseToTensorsMap(opBuilder.build());
  }
  
  /**
   * @param container The container name for the `SparseTensorsMap` created by this op.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName The shared name for the `SparseTensorsMap` created by this op.
   * If blank, the new Operation's unique name is used.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * 0-D.  The handle of the `SparseTensor` now stored in the
   * `SparseTensorsMap`.
   */
  public Output<TInt64> sparseHandle() {
    return sparseHandle;
  }
  
  @Override
  public Output<TInt64> asOutput() {
    return sparseHandle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "AddSparseToTensorsMap";
  
  private Output<TInt64> sparseHandle;
  
  private AddSparseToTensorsMap(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseHandle = operation.output(outputIdx++);
  }
}
