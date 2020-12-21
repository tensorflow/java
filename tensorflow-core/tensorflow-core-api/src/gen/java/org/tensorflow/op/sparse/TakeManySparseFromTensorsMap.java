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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Read `SparseTensors` from a `SparseTensorsMap` and concatenate them.
 * <p>
 * The input `sparse_handles` must be an `int64` matrix of shape `[N, 1]` where
 * `N` is the minibatch size and the rows correspond to the output handles of
 * `AddSparseToTensorsMap` or `AddManySparseToTensorsMap`.  The ranks of the
 * original `SparseTensor` objects that went into the given input ops must all
 * match.  When the final `SparseTensor` is created, it has rank one
 * higher than the ranks of the incoming `SparseTensor` objects
 * (they have been concatenated along a new row dimension on the left).
 * <p>
 * The output `SparseTensor` object's shape values for all dimensions but the
 * first are the max across the input `SparseTensor` objects' shape values
 * for the corresponding dimensions.  Its first shape value is `N`, the minibatch
 * size.
 * <p>
 * The input `SparseTensor` objects' indices are assumed ordered in
 * standard lexicographic order.  If this is not the case, after this
 * step run `SparseReorder` to restore index ordering.
 * <p>
 * For example, if the handles represent an input, which is a `[2, 3]` matrix
 * representing two original `SparseTensor` objects:
 * <pre>{@code
 *     index = [ 0]
 *             [10]
 *             [20]
 *     values = [1, 2, 3]
 *     shape = [50]
 * }</pre>
 * and
 * <pre>{@code
 *     index = [ 2]
 *             [10]
 *     values = [4, 5]
 *     shape = [30]
 * }</pre>
 * then the final `SparseTensor` will be:
 * <pre>{@code
 *     index = [0  0]
 *             [0 10]
 *             [0 20]
 *             [1  2]
 *             [1 10]
 *     values = [1, 2, 3, 4, 5]
 *     shape = [2 50]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code sparseValues()} output
 */
@Operator(group = "sparse")
public final class TakeManySparseFromTensorsMap<T extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.TakeManySparseFromTensorsMap}
   */
  public static class Options {
    
    /**
     * @param container The container name for the `SparseTensorsMap` read by this op.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName The shared name for the `SparseTensorsMap` read by this op.
     * It should not be blank; rather the `shared_name` or unique Operation name
     * of the Op that created the original `SparseTensorsMap` should be used.
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
   * Factory method to create a class wrapping a new TakeManySparseFromTensorsMap operation.
   * 
   * @param scope current scope
   * @param sparseHandles 1-D, The `N` serialized `SparseTensor` objects.
   * Shape: `[N]`.
   * @param dtype The `dtype` of the `SparseTensor` objects stored in the
   * `SparseTensorsMap`.
   * @param options carries optional attributes values
   * @return a new instance of TakeManySparseFromTensorsMap
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TakeManySparseFromTensorsMap<T> create(Scope scope, Operand<TInt64> sparseHandles, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TakeManySparseFromTensorsMap", scope.makeOpName("TakeManySparseFromTensorsMap"));
    opBuilder.addInput(sparseHandles.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
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
    return new TakeManySparseFromTensorsMap<T>(opBuilder.build());
  }
  
  /**
   * @param container The container name for the `SparseTensorsMap` read by this op.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName The shared name for the `SparseTensorsMap` read by this op.
   * It should not be blank; rather the `shared_name` or unique Operation name
   * of the Op that created the original `SparseTensorsMap` should be used.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * 2-D.  The `indices` of the minibatch `SparseTensor`.
   */
  public Output<TInt64> sparseIndices() {
    return sparseIndices;
  }
  
  /**
   * 1-D.  The `values` of the minibatch `SparseTensor`.
   */
  public Output<T> sparseValues() {
    return sparseValues;
  }
  
  /**
   * 1-D.  The `shape` of the minibatch `SparseTensor`.
   */
  public Output<TInt64> sparseShape() {
    return sparseShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TakeManySparseFromTensorsMap";
  
  private Output<TInt64> sparseIndices;
  private Output<T> sparseValues;
  private Output<TInt64> sparseShape;
  
  private TakeManySparseFromTensorsMap(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseIndices = operation.output(outputIdx++);
    sparseValues = operation.output(outputIdx++);
    sparseShape = operation.output(outputIdx++);
  }
}
