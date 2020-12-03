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
 * Slice a `SparseTensor` based on the `start` and `size`.
 * <p>
 * For example, if the input is
 * <p>
 *     input_tensor = shape = [2, 7]
 *     [    a   d e  ]
 *     [b c          ]
 * <p>
 * Graphically the output tensors are:
 * <p>
 *     sparse_slice([0, 0], [2, 4]) = shape = [2, 4]
 *     [    a  ]
 *     [b c    ]
 * <p>
 *     sparse_slice([0, 4], [2, 3]) = shape = [2, 3]
 *     [ d e  ]
 *     [      ]
 * 
 * @param <T> data type for {@code outputValues()} output
 */
@Operator(group = "sparse")
public final class SparseSlice<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseSlice operation.
   * 
   * @param scope current scope
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * @param start 1-D. tensor represents the start of the slice.
   * @param size 1-D. tensor represents the size of the slice.
   * output indices: A list of 1-D tensors represents the indices of the output
   * sparse tensors.
   * @return a new instance of SparseSlice
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseSlice<T> create(Scope scope, Operand<TInt64> indices, Operand<T> values, Operand<TInt64> shape, Operand<TInt64> start, Operand<TInt64> size) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSlice", scope.makeOpName("SparseSlice"));
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(start.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseSlice<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * A list of 1-D tensors represents the values of the output sparse
   * tensors.
   */
  public Output<T> outputValues() {
    return outputValues;
  }
  
  /**
   * A list of 1-D tensors represents the shape of the output sparse
   * tensors.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseSlice";
  
  private Output<TInt64> outputIndices;
  private Output<T> outputValues;
  private Output<TInt64> outputShape;
  
  private SparseSlice(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }
}
