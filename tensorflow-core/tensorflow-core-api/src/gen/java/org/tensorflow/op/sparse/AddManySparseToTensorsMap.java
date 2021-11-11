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
 * Add an {@code N}-minibatch {@code SparseTensor} to a {@code SparseTensorsMap}, return {@code N} handles.
 * A {@code SparseTensor} of rank {@code R} is represented by three tensors: {@code sparse_indices},
 * {@code sparse_values}, and {@code sparse_shape}, where
 * <p>{@code sparse_indices.shape[1] == sparse_shape.shape[0] == R}
 * <p>An {@code N}-minibatch of {@code SparseTensor} objects is represented as a {@code SparseTensor}
 * having a first {@code sparse_indices} column taking values between {@code [0, N)}, where
 * the minibatch size {@code N == sparse_shape[0]}.
 * <p>The input {@code SparseTensor} must have rank {@code R} greater than 1, and the first
 * dimension is treated as the minibatch dimension.  Elements of the {@code SparseTensor}
 * must be sorted in increasing order of this first dimension.  The stored
 * {@code SparseTensor} objects pointed to by each row of the output {@code sparse_handles}
 * will have rank {@code R-1}.
 * <p>The {@code SparseTensor} values can then be read out as part of a minibatch by passing
 * the given keys as vector elements to {@code TakeManySparseFromTensorsMap}.  To ensure
 * the correct {@code SparseTensorsMap} is accessed, ensure that the same
 * {@code container} and {@code shared_name} are passed to that Op.  If no {@code shared_name}
 * is provided here, instead use the <em>name</em> of the Operation created by calling
 * {@code sparse.AddManySparseToTensorsMap} as the {@code shared_name} passed to
 * {@code TakeManySparseFromTensorsMap}.  Ensure the Operations are colocated.
 */
@OpMetadata(
    opType = AddManySparseToTensorsMap.OP_NAME,
    inputsClass = AddManySparseToTensorsMap.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class AddManySparseToTensorsMap extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AddManySparseToTensorsMap";

  private Output<TInt64> sparseHandles;

  public AddManySparseToTensorsMap(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sparseHandles = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AddManySparseToTensorsMap operation.
   *
   * @param scope current scope
   * @param sparseIndices 2-D.  The {@code indices} of the minibatch {@code SparseTensor}.
   * {@code sparse_indices[:, 0]} must be ordered values in {@code [0, N)}.
   * @param sparseValues 1-D.  The {@code values} of the minibatch {@code SparseTensor}.
   * @param sparseShape 1-D.  The {@code shape} of the minibatch {@code SparseTensor}.
   * The minibatch size {@code N == sparse_shape[0]}.
   * @param options carries optional attribute values
   * @return a new instance of AddManySparseToTensorsMap
   */
  @Endpoint(
      describeByClass = true
  )
  public static AddManySparseToTensorsMap create(Scope scope, Operand<TInt64> sparseIndices,
      Operand<? extends TType> sparseValues, Operand<TInt64> sparseShape, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AddManySparseToTensorsMap");
    opBuilder.addInput(sparseIndices.asOutput());
    opBuilder.addInput(sparseValues.asOutput());
    opBuilder.addInput(sparseShape.asOutput());
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
   * Sets the container option.
   *
   * @param container The container name for the {@code SparseTensorsMap} created by this op.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName The shared name for the {@code SparseTensorsMap} created by this op.
   * If blank, the new Operation's unique name is used.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Gets sparseHandles.
   * 1-D.  The handles of the {@code SparseTensor} now stored in the
   * {@code SparseTensorsMap}.  Shape: {@code [N]}.
   * @return sparseHandles.
   */
  public Output<TInt64> sparseHandles() {
    return sparseHandles;
  }

  @Override
  public Output<TInt64> asOutput() {
    return sparseHandles;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.AddManySparseToTensorsMap}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private Options() {
    }

    /**
     * Sets the container option.
     *
     * @param container The container name for the {@code SparseTensorsMap} created by this op.
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName The shared name for the {@code SparseTensorsMap} created by this op.
     * If blank, the new Operation's unique name is used.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AddManySparseToTensorsMap.class
  )
  public static class Inputs extends RawOpInputs<AddManySparseToTensorsMap> {
    /**
     * 2-D.  The {@code indices} of the minibatch {@code SparseTensor}.
     * {@code sparse_indices[:, 0]} must be ordered values in {@code [0, N)}.
     */
    public final Operand<TInt64> sparseIndices;

    /**
     * 1-D.  The {@code values} of the minibatch {@code SparseTensor}.
     */
    public final Operand<? extends TType> sparseValues;

    /**
     * 1-D.  The {@code shape} of the minibatch {@code SparseTensor}.
     * The minibatch size {@code N == sparse_shape[0]}.
     */
    public final Operand<TInt64> sparseShape;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The container name for the `SparseTensorsMap` created by this op.
     */
    public final String container;

    /**
     * The shared name for the `SparseTensorsMap` created by this op.
     * If blank, the new Operation's unique name is used.
     */
    public final String sharedName;

    public Inputs(GraphOperation op) {
      super(new AddManySparseToTensorsMap(op), op, Arrays.asList("T", "container", "shared_name"));
      int inputIndex = 0;
      sparseIndices = (Operand<TInt64>) op.input(inputIndex++);
      sparseValues = (Operand<? extends TType>) op.input(inputIndex++);
      sparseShape = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
    }
  }
}
