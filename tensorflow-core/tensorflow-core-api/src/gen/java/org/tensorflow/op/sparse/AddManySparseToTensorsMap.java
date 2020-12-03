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
 * Add an `N`-minibatch `SparseTensor` to a `SparseTensorsMap`, return `N` handles.
 * <p>
 * A `SparseTensor` of rank `R` is represented by three tensors: `sparse_indices`,
 * `sparse_values`, and `sparse_shape`, where
 * <pre>{@code
 * sparse_indices.shape[1] == sparse_shape.shape[0] == R}</pre>
 * An `N`-minibatch of `SparseTensor` objects is represented as a `SparseTensor`
 * having a first `sparse_indices` column taking values between `[0, N)`, where
 * the minibatch size `N == sparse_shape[0]`.
 * <p>
 * The input `SparseTensor` must have rank `R` greater than 1, and the first
 * dimension is treated as the minibatch dimension.  Elements of the `SparseTensor`
 * must be sorted in increasing order of this first dimension.  The stored
 * `SparseTensor` objects pointed to by each row of the output `sparse_handles`
 * will have rank `R-1`.
 * <p>
 * The `SparseTensor` values can then be read out as part of a minibatch by passing
 * the given keys as vector elements to `TakeManySparseFromTensorsMap`.  To ensure
 * the correct `SparseTensorsMap` is accessed, ensure that the same
 * `container` and `shared_name` are passed to that Op.  If no `shared_name`
 * is provided here, instead use the <i>name</i> of the Operation created by calling
 * `sparse.AddManySparseToTensorsMap` as the `shared_name` passed to
 * `TakeManySparseFromTensorsMap`.  Ensure the Operations are colocated.
 */
@Operator(group = "sparse")
public final class AddManySparseToTensorsMap extends RawOp implements Operand<TInt64> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.AddManySparseToTensorsMap}
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
   * Factory method to create a class wrapping a new AddManySparseToTensorsMap operation.
   * 
   * @param scope current scope
   * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
   * `sparse_indices[:, 0]` must be ordered values in `[0, N)`.
   * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
   * The minibatch size `N == sparse_shape[0]`.
   * @param options carries optional attributes values
   * @return a new instance of AddManySparseToTensorsMap
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> AddManySparseToTensorsMap create(Scope scope, Operand<TInt64> sparseIndices, Operand<T> sparseValues, Operand<TInt64> sparseShape, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("AddManySparseToTensorsMap", scope.makeOpName("AddManySparseToTensorsMap"));
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
    return new AddManySparseToTensorsMap(opBuilder.build());
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
   * 1-D.  The handles of the `SparseTensor` now stored in the
   * `SparseTensorsMap`.  Shape: `[N]`.
   */
  public Output<TInt64> sparseHandles() {
    return sparseHandles;
  }
  
  @Override
  public Output<TInt64> asOutput() {
    return sparseHandles;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "AddManySparseToTensorsMap";
  
  private Output<TInt64> sparseHandles;
  
  private AddManySparseToTensorsMap(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseHandles = operation.output(outputIdx++);
  }
}
