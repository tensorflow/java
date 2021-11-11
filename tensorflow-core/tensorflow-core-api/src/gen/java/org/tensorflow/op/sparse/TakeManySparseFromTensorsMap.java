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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Read {@code SparseTensors} from a {@code SparseTensorsMap} and concatenate them.
 * The input {@code sparse_handles} must be an {@code int64} matrix of shape {@code [N, 1]} where
 * {@code N} is the minibatch size and the rows correspond to the output handles of
 * {@code AddSparseToTensorsMap} or {@code AddManySparseToTensorsMap}.  The ranks of the
 * original {@code SparseTensor} objects that went into the given input ops must all
 * match.  When the final {@code SparseTensor} is created, it has rank one
 * higher than the ranks of the incoming {@code SparseTensor} objects
 * (they have been concatenated along a new row dimension on the left).
 * <p>The output {@code SparseTensor} object's shape values for all dimensions but the
 * first are the max across the input {@code SparseTensor} objects' shape values
 * for the corresponding dimensions.  Its first shape value is {@code N}, the minibatch
 * size.
 * <p>The input {@code SparseTensor} objects' indices are assumed ordered in
 * standard lexicographic order.  If this is not the case, after this
 * step run {@code SparseReorder} to restore index ordering.
 * <p>For example, if the handles represent an input, which is a {@code [2, 3]} matrix
 * representing two original {@code SparseTensor} objects:
 * <pre>
 *     index = [ 0]
 *             [10]
 *             [20]
 *     values = [1, 2, 3]
 *     shape = [50]
 * </pre>
 * <p>and
 * <pre>
 *     index = [ 2]
 *             [10]
 *     values = [4, 5]
 *     shape = [30]
 * </pre>
 * <p>then the final {@code SparseTensor} will be:
 * <pre>
 *     index = [0  0]
 *             [0 10]
 *             [0 20]
 *             [1  2]
 *             [1 10]
 *     values = [1, 2, 3, 4, 5]
 *     shape = [2 50]
 * </pre>
 *
 * @param <T> data type for {@code sparse_values} output
 */
@OpMetadata(
    opType = TakeManySparseFromTensorsMap.OP_NAME,
    inputsClass = TakeManySparseFromTensorsMap.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class TakeManySparseFromTensorsMap<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TakeManySparseFromTensorsMap";

  private Output<TInt64> sparseIndices;

  private Output<T> sparseValues;

  private Output<TInt64> sparseShape;

  public TakeManySparseFromTensorsMap(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sparseIndices = operation.output(outputIdx++);
    sparseValues = operation.output(outputIdx++);
    sparseShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TakeManySparseFromTensorsMap operation.
   *
   * @param scope current scope
   * @param sparseHandles 1-D, The {@code N} serialized {@code SparseTensor} objects.
   * Shape: {@code [N]}.
   * @param dtype The {@code dtype} of the {@code SparseTensor} objects stored in the
   * {@code SparseTensorsMap}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TakeManySparseFromTensorsMap} output and operands
   * @return a new instance of TakeManySparseFromTensorsMap
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TakeManySparseFromTensorsMap<T> create(Scope scope,
      Operand<TInt64> sparseHandles, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TakeManySparseFromTensorsMap");
    opBuilder.addInput(sparseHandles.asOutput());
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
    return new TakeManySparseFromTensorsMap<>(opBuilder.build());
  }

  /**
   * Sets the container option.
   *
   * @param container The container name for the {@code SparseTensorsMap} read by this op.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName The shared name for the {@code SparseTensorsMap} read by this op.
   * It should not be blank; rather the {@code shared_name} or unique Operation name
   * of the Op that created the original {@code SparseTensorsMap} should be used.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Gets sparseIndices.
   * 2-D.  The {@code indices} of the minibatch {@code SparseTensor}.
   * @return sparseIndices.
   */
  public Output<TInt64> sparseIndices() {
    return sparseIndices;
  }

  /**
   * Gets sparseValues.
   * 1-D.  The {@code values} of the minibatch {@code SparseTensor}.
   * @return sparseValues.
   */
  public Output<T> sparseValues() {
    return sparseValues;
  }

  /**
   * Gets sparseShape.
   * 1-D.  The {@code shape} of the minibatch {@code SparseTensor}.
   * @return sparseShape.
   */
  public Output<TInt64> sparseShape() {
    return sparseShape;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.TakeManySparseFromTensorsMap}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the container option.
     *
     * @param container The container name for the {@code SparseTensorsMap} read by this op.
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName The shared name for the {@code SparseTensorsMap} read by this op.
     * It should not be blank; rather the {@code shared_name} or unique Operation name
     * of the Op that created the original {@code SparseTensorsMap} should be used.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TakeManySparseFromTensorsMap.class
  )
  public static class Inputs extends RawOpInputs<TakeManySparseFromTensorsMap<?>> {
    /**
     * 1-D, The {@code N} serialized {@code SparseTensor} objects.
     * Shape: {@code [N]}.
     */
    public final Operand<TInt64> sparseHandles;

    /**
     * The `dtype` of the `SparseTensor` objects stored in the
     * `SparseTensorsMap`.
     */
    public final DataType dtype;

    /**
     * The container name for the `SparseTensorsMap` read by this op.
     */
    public final String container;

    /**
     * The shared name for the `SparseTensorsMap` read by this op.
     * It should not be blank; rather the `shared_name` or unique Operation name
     * of the Op that created the original `SparseTensorsMap` should be used.
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new TakeManySparseFromTensorsMap<>(op), op, Arrays.asList("dtype", "container", "shared_name"));
      int inputIndex = 0;
      sparseHandles = (Operand<TInt64>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
