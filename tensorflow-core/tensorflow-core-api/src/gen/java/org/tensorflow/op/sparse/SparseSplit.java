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
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Split a `SparseTensor` into `num_split` tensors along one dimension.
 * <p>
 * If the `shape[split_dim]` is not an integer multiple of `num_split`. Slices
 * `[0 : shape[split_dim] % num_split]` gets one extra dimension.
 * For example, if `split_dim = 1` and `num_split = 2` and the input is
 * <p>
 *     input_tensor = shape = [2, 7]
 *     [    a   d e  ]
 *     [b c          ]
 * <p>
 * Graphically the output tensors are:
 * <p>
 *     output_tensor[0] = shape = [2, 4]
 *     [    a  ]
 *     [b c    ]
 * <p>
 *     output_tensor[1] = shape = [2, 3]
 *     [ d e  ]
 *     [      ]
 * 
 * @param <T> data type for {@code outputValues()} output
 */
@Operator(group = "sparse")
public final class SparseSplit<T> extends PrimitiveOp {
  
  /**
   * Factory method to create a class wrapping a new SparseSplit operation.
   * 
   * @param scope current scope
   * @param splitDim 0-D.  The dimension along which to split.  Must be in the range
   * `[0, rank(shape))`.
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * output indices: A list of 1-D tensors represents the indices of the output
   * sparse tensors.
   * @param numSplit The number of ways to split.
   * @return a new instance of SparseSplit
   */
  public static <T> SparseSplit<T> create(Scope scope, Operand<Long> splitDim, Operand<Long> indices, Operand<T> values, Operand<Long> shape, Long numSplit) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSplit", scope.makeOpName("SparseSplit"));
    opBuilder.addInput(splitDim.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("num_split", numSplit);
    return new SparseSplit<T>(opBuilder.build());
  }
  
  /**
   */
  public List<Output<Long>> outputIndices() {
    return outputIndices;
  }
  
  /**
   * A list of 1-D tensors represents the values of the output sparse
   * tensors.
   */
  public List<Output<T>> outputValues() {
    return outputValues;
  }
  
  /**
   * A list of 1-D tensors represents the shape of the output sparse
   * tensors.
   */
  public List<Output<Long>> outputShape() {
    return outputShape;
  }
  
  private List<Output<Long>> outputIndices;
  private List<Output<T>> outputValues;
  private List<Output<Long>> outputShape;
  
  @SuppressWarnings("unchecked")
  private SparseSplit(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputIndicesLength = operation.outputListLength("output_indices");
    outputIndices = Arrays.asList((Output<Long>[])operation.outputList(outputIdx, outputIndicesLength));
    outputIdx += outputIndicesLength;
    int outputValuesLength = operation.outputListLength("output_values");
    outputValues = Arrays.asList((Output<T>[])operation.outputList(outputIdx, outputValuesLength));
    outputIdx += outputValuesLength;
    int outputShapeLength = operation.outputListLength("output_shape");
    outputShape = Arrays.asList((Output<Long>[])operation.outputList(outputIdx, outputShapeLength));
    outputIdx += outputShapeLength;
  }
}
